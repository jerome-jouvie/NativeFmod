//===============================================================================================
//STREAM.EXE
//Copyright (c), Firelight Technologies Pty, Ltd, 1999-2004.
//
//This example takes a command line parameter, a wav/mp2/mp3/ogg etc file, and uses the streamer 
//system to play it back.
//===============================================================================================

/**
 * I've ported the C++ Fmod sample to use it with NativeFmod
 * 
 * @author Jérôme JOUVIE (Jouvieje)
 * 
 * WANT TO CONTACT ME ?
 * @author Jérôme JOUVIE (Jouvieje)
 * @site   http://jerome.jouvie.free.fr/
 * @mail   jerome.jouvie@gmail.com
 */

package org.jouvieje.Fmod.Extras;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import javax.swing.JOptionPane;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Callbacks.FSOUND_CLOSECALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_OPENCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_READCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_SEEKCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_STREAMCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_TELLCALLBACK;
import org.jouvieje.Fmod.Defines.FSOUND_INIT_FLAGS;
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;

import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Misc.BufferUtils;
import org.jouvieje.Fmod.Misc.ObjectPointer;
import org.jouvieje.Fmod.Misc.Pointer;
import org.jouvieje.Fmod.Structures.FSOUND_SAMPLE;
import org.jouvieje.Fmod.Structures.FSOUND_STREAM;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JTextArea;
import java.awt.GridBagConstraints;

import javax.swing.JApplet;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JScrollPane;

public class StreamApplet extends JApplet implements Runnable, FSOUND_OUTPUTTYPES, FSOUND_INIT_FLAGS, FSOUND_MODES, FSOUND_MISC_VALUES, VERSIONS {
	private static final long serialVersionUID = 1L;
	private FSOUND_STREAM stream;
	private FSOUND_SAMPLE sptr;

	private ByteBuffer streamingBuffer;
	private int channel = -1;
	private boolean streamEnded = false;
	private char key;
	private boolean init = false;
	private boolean deinit = false;

	public void init() {
		/*====================== NativeFmod Init & Version checking ============================*/

		try {
			JOptionPane.showMessageDialog(null, "Before loading libraries");
			Init.DEBUG = true;
			Init.loadLibraries();
			JOptionPane.showMessageDialog(null, "After loading libraries");
		}
		catch(InitException e) {
			printExit("NativeFmod error! " + e.getMessage());
			return;
		}

		try {
			/*
			 * Checking NativeFmodEx version
			 */
			if(NATIVEFMOD_LIBRARY_VERSION != NATIVEFMOD_JAR_VERSION) {
				printExit("Error!  NativeFmod library version (" + NATIVEFMOD_LIBRARY_VERSION
						+ ") is different to jar version (" + NATIVEFMOD_JAR_VERSION + ")");
				return;
			}

			/*====================== Checking FMOD Version checking ============================*/

			JOptionPane.showMessageDialog(null, "FMOD Library version...");
			if(Fmod.FSOUND_GetVersion() < FMOD_VERSION) {
				printExit("Error : You are using the wrong DLL version!  You should be using FMOD " + FMOD_VERSION);
				return;
			}
			JOptionPane.showMessageDialog(null, "FMOD Library version: OK");

			init = true;

			/*======================= Initialize the GUI ===========================*/

			initialize();
		}
		catch(HeadlessException e) {
			e.printStackTrace();
			printExit("Unexpected exception: " + e.getMessage());
			return;
		}
	}

	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	public void run() {
		if(!init) return;

		String[] args = new String[1];
		args[0] = "/Media/jbtennis.wav";

		print("-------------------------------------------------------------\n");
		print("FMOD Streamer example. [mp2 mp3 wav ogg wma asf]\n");
		print("Copyright (c) Firelight Technologies Pty, Ltd, 1999-2004.\n");
		print("-------------------------------------------------------------\n");
		print("Output: No Sound-\n");
		print("-------------------------------------------------------------\n");

		// Set custom file callbacks?  This doesnt have to be done, its just here as an example.
		Fmod.FSOUND_File_SetCallbacks(myopen, myclose, myread, myseek, mytell);

		Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_NOSOUND);

		// ==========================================================================================
		// INITIALIZE
		// ==========================================================================================
		if(!Fmod.FSOUND_Init(44100, 32, 0)) {
			String message = "FMOD Error: " + Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError());
			Fmod.FSOUND_Close();
			printExit(message);
			return;
		}

		Fmod.FSOUND_Stream_SetBufferSize(1000);

		// ==========================================================================================
		// OPEN STREAM (use if(true) for streaming from memory)
		// ==========================================================================================
		if(false) {
			int length = 0;
			byte[] datas = null;
			RandomAccessFile file = null;
			try {
				file = new RandomAccessFile(args[0], "rw"); //rw = read and write
			}
			catch(FileNotFoundException e) {
				String message = "Error: File Not Found";
				Fmod.FSOUND_Close();
				printExit(message);
				return;
			}
			try {
				length = (int)file.length();
				datas = new byte[length];
				file.read(datas);
				file.close();
			}
			catch(Exception e) {
				printExit("Error reading the file");
				return;
			}

			streamingBuffer = BufferUtils.newByteBuffer(datas.length);
			streamingBuffer.put(datas);
			streamingBuffer.rewind(); //Go to the beginning of the buffer

			//The memory pointer MUST remain valid while streaming ! (keeps buffer referenced somewhere)
			stream = Fmod.FSOUND_Stream_Open(streamingBuffer, FSOUND_NORMAL | FSOUND_MPEGACCURATE | FSOUND_LOADMEMORY,
					0, length);

			if(stream == null) {
				printExit("Can't creates the stream !!!");
				return;
			}
		}
		else {
			if(args[0].startsWith("http:")) {
				print("Connecting to " + args[0] + ", please wait (this may take some time)....\n");
			}
			stream = Fmod.FSOUND_Stream_Open(args[0], FSOUND_NORMAL | FSOUND_MPEGACCURATE, 0, 0);
			if(stream == null) {
				String message = "FMOD Error: " + Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError());
				Fmod.FSOUND_Close();
				printExit(message);
				return;
			}
		}

		// ==========================================================================================
		// SET AN END OF STREAM CALLBACK AND RIFF SYNCH POINTS CALLBACK
		// ==========================================================================================
		Fmod.FSOUND_Stream_SetEndCallback(stream, endcallback, null);
		Fmod.FSOUND_Stream_SetSyncCallback(stream, endcallback, null);

		print("=========================================================================\n");
		print("Press SPACE to pause/unpause\n");
		print("Press f   to fast forward 2 seconds\n");
		print("Press e   to quit\n");
		print("=========================================================================\n");

		sptr = Fmod.FSOUND_Stream_GetSample(stream);
		if(sptr != null) {
			int[] freq = new int[1];
			Fmod.FSOUND_Sample_GetDefaults(sptr, freq, null, null, null);
			print("Name      : " + Fmod.FSOUND_Sample_GetName(sptr) + "\n");
			print("Frequency : " + freq[0] + "\n");
			print("\n");
		}

		do {
			if(channel < 0) {
				// ==========================================================================================
				// PLAY STREAM
				// ==========================================================================================
				channel = Fmod.FSOUND_Stream_PlayEx(FSOUND_FREE, stream, null, true);
				Fmod.FSOUND_SetPaused(channel, false);
			}

			if(key == ' ') Fmod.FSOUND_SetPaused(channel, !Fmod.FSOUND_GetPaused(channel));
			else if(key == 'f') Fmod.FSOUND_Stream_SetTime(stream, Fmod.FSOUND_Stream_GetTime(stream) + 2000);
			key = 0;

			printr("pos " + Fmod.FSOUND_Stream_GetPosition(stream) + "/" + Fmod.FSOUND_Stream_GetLength(stream)
					+ " time " + (Fmod.FSOUND_Stream_GetTime(stream) / 1000 / 60) + ":"
					+ (Fmod.FSOUND_Stream_GetTime(stream) / 1000 % 60) + "/"
					+ (Fmod.FSOUND_Stream_GetLengthMs(stream) / 1000 / 60) + ":"
					+ (Fmod.FSOUND_Stream_GetLengthMs(stream) / 1000 % 60) + " cpu " + Fmod.FSOUND_GetCPUUsage() + "%");

			try {
				Thread.sleep(100);
			}
			catch(Exception e) {}
		}
		while(!streamEnded && key != 'e' && key != 'E');

		stop();
	}

	public void stop() {
		if(!init || deinit) {
			return;
		}
		deinit = true;

		print("\n");
		print("Shutdown stream\n");
		if(stream != null && !stream.isNull()) {
			Fmod.FSOUND_Stream_Close(stream);
		}
		print("Shutdown FMOD\n");
		Fmod.FSOUND_Close();
	}

	//====================== File System ============================

	private ByteBuffer loadFileIntoMemory(String name) throws IOException, FileNotFoundException {
		InputStream is = getClass().getResourceAsStream(name);
		BufferedInputStream bis = new BufferedInputStream(is);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] bytes = new byte[4 * 1024];
		int read;
		while((read = bis.read(bytes, 0, bytes.length)) != -1) {
			baos.write(bytes, 0, read);
		}
		bis.close();

		ByteBuffer buffer = BufferUtils.newByteBuffer(baos.size());
		buffer.put(baos.toByteArray());
		buffer.rewind();
		return buffer;
	}

	/*
	 * File callbacks
	 */
	private FSOUND_OPENCALLBACK myopen = new FSOUND_OPENCALLBACK(){
		public Pointer FSOUND_OPENCALLBACK(String name) {
			try {
				ByteBuffer file = loadFileIntoMemory(name);
				return ObjectPointer.create(file);
			}
			catch(Exception e) {
				e.printStackTrace();
				print("Failed to open the file " + name + " !!!\n");
				return null;
			}
		}
	};

	private FSOUND_CLOSECALLBACK myclose = new FSOUND_CLOSECALLBACK(){
		public void FSOUND_CLOSECALLBACK(Pointer handle) {
			ObjectPointer objectPointer = ObjectPointer.createView(handle);
			objectPointer.release();
		}
	};

	private FSOUND_READCALLBACK myread = new FSOUND_READCALLBACK(){
		public int FSOUND_READCALLBACK(ByteBuffer buffer, int size, Pointer handle) {
			ByteBuffer file = (ByteBuffer)ObjectPointer.createView(handle).getObject();
			ByteBuffer fileChunk = file.duplicate();
			fileChunk.limit(fileChunk.position() + size);
			if(fileChunk.remaining() != size) {
				printExit("Unexpected error while reading file !");
				return 0;
			}
			buffer.put(fileChunk);
			file.position(file.position() + size);
			return size;
		}
	};

	//Values taken from stdio.h
	public final int SEEK_CUR = 1; //Seek from the current position of file pointer
	public final int SEEK_END = 2; //Seek from the end of file
	public final int SEEK_SET = 0; //Seek from the beginning of file

	private FSOUND_SEEKCALLBACK myseek = new FSOUND_SEEKCALLBACK(){
		public int FSOUND_SEEKCALLBACK(Pointer handle, int pos, byte mode) {
			ByteBuffer file = (ByteBuffer)ObjectPointer.createView(handle).getObject();
			int position = 0;
			switch(mode) {
				case SEEK_CUR:
					position = file.position() + pos;
					break;
				case SEEK_END:
					position = file.capacity() + pos;
					break;
				case SEEK_SET:
					position = pos;
					break;
			}
			if(position < 0 || position > file.capacity()) {
				print("Error seeking file, invalid position (" + position + ")\n");
				return -1;
			}
			file.position(position);
			return 0;
		}
	};

	private FSOUND_TELLCALLBACK mytell = new FSOUND_TELLCALLBACK(){
		public int FSOUND_TELLCALLBACK(Pointer handle) {
			ByteBuffer buffer = (ByteBuffer)ObjectPointer.createView(handle).getObject();
			return buffer.position();
		}
	};

	/*
	 * [DESCRIPTION]
	 * End of stream user callback, initialized with FSOUND_Stream_SetEndCallback or 
	 * FSOUND_Stream_SetSynchCallback
	 * 
	 * [PARAMETERS]
	 * 'stream'      A pointer to the stream that ended.
	 * 'buff'        This is null for end of stream callbacks, or a string for synch callbacks.
	 * 'len'         This is reserved and is always 0 for end and synch callbacks. ignore.
	 * 'param'       This is the value passed to FSOUND_Stream_SetEndCallback or 
	 * 				 FSOUND_Stream_SetSynchCallback as a user data value.
	 * [RETURN_VALUE]
	 * true or false, the value is ignored.
	 */
	private FSOUND_STREAMCALLBACK endcallback = new FSOUND_STREAMCALLBACK(){
		public boolean FSOUND_STREAMCALLBACK(FSOUND_STREAM stream, ByteBuffer buff, int len, Pointer userdata) {
			// end of stream callback doesnt have a 'buff' value, if it doesnt it could be a synch point.
			print("\n");
			if(buff != null) {
				//To prints the String value stored in the ByteBuffer, you can use :
				String synchpoint = BufferUtils.toString(buff);
				print("SYNCHPOINT : " + synchpoint + "\n");
			}
			else {
				print("STREAM ENDED!!\n");
				streamEnded = true;
			}

			return true;
		}
	};

	//====================== GUI Interaction ============================

	private void print(String message) {
		getOutputTA().append(message);
	}

	private void printr(String message) {
		String text = getOutputTA().getText();
		int index = text.lastIndexOf("\n");
		if(index >= 0) text = text.substring(0, index + 1);
		getOutputTA().setText(text + message);
	}

	private void printExit(String message) {
		JOptionPane.showMessageDialog(this, message);
		stop();
//		System.exit(0);
	}

	//====================== GUI ============================

	private JTextArea outputTA = null;
	private JTextField inputTF = null;
	private JButton inputSendB = null;
	private JScrollPane outputSP = null;

	private void initialize() {
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.gridy = 1;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 2;
		gridBagConstraints2.gridy = 1;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints1.gridy = 1;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.gridx = 1;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.gridx = 0;

		this.setLayout(new GridBagLayout());
		this.setSize(new Dimension(353, 193));
		this.add(getOutputSP(), gridBagConstraints);
		this.add(getInputTF(), gridBagConstraints1);
		this.add(getInputSendB(), gridBagConstraints2);
		this.add(new JLabel("Input"), gridBagConstraints3);
	}

	private JScrollPane getOutputSP() {
		if(outputSP == null) {
			outputSP = new JScrollPane();
			outputSP.setViewportView(getOutputTA());
		}
		return outputSP;
	}

	private JTextArea getOutputTA() {
		if(outputTA == null) {
			outputTA = new JTextArea();
			outputTA.setEditable(false);
			outputTA.setBackground(new Color(220, 220, 220));
		}
		return outputTA;
	}

	private JTextField getInputTF() {
		if(inputTF == null) {
			inputTF = new JTextField();
		}
		return inputTF;
	}

	private JButton getInputSendB() {
		if(inputSendB == null) {
			inputSendB = new JButton();
			inputSendB.setText("Validate");
			inputSendB.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(inputTF.getText().length() > 0) key = inputTF.getText().charAt(0);
					else key = 0;
				}
			});
		}
		return inputSendB;
	}
} //  @jve:decl-index=0:visual-constraint="10,10"