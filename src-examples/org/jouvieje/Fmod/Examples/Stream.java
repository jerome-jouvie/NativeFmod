//===============================================================================================
//STREAM.EXE
//Copyright (c), Firelight Technologies Pty, Ltd, 1999-2004.
//
//This example takes a command line parameter, a wav/mp2/mp3/ogg etc file, and uses the streamer 
//system to play it back.
//===============================================================================================

/**
 * I've ported the C++ FMOD example to use it with NativeFmod
 * 
 * @author Jérôme JOUVIE (Jouvieje)
 * 
 * WANT TO CONTACT ME ?
 * E-mail :
 * 		jerome.jouvie@gmail.com
 * My web sites :
 * 		http://jerome.jouvie.free.fr/
 */

package org.jouvieje.Fmod.Examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Callbacks.FSOUND_STREAMCALLBACK;
import org.jouvieje.Fmod.Defines.FSOUND_INIT_FLAGS;
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;

import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Examples.Util.FileFilters;
import org.jouvieje.Fmod.Examples.Util.ConsoleGUI;
import org.jouvieje.Fmod.Examples.Util.FmodExampleFrame;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Misc.BufferUtils;
import org.jouvieje.Fmod.Misc.Pointer;
import org.jouvieje.Fmod.Structures.FSOUND_SAMPLE;
import org.jouvieje.Fmod.Structures.FSOUND_STREAM;
import org.jouvieje.libloader.LibLoader;

import java.awt.HeadlessException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class Stream extends ConsoleGUI implements FSOUND_OUTPUTTYPES, FSOUND_INIT_FLAGS, FSOUND_MODES, FSOUND_MISC_VALUES, VERSIONS {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new FmodExampleFrame(new Stream());
	}

	private boolean init = false;
	private boolean deinit = false;

	private FSOUND_STREAM stream = null;
	private boolean streamEnded = false;

	public Stream() {
		super();
		initFmod();
		initialize();
	}

	public JPanel getPanel() {
		return this;
	}

	public String getTitle() {
		return "FMOD Stream example.";
	}

	public void initFmod() {
		/*====================== NativeFmod Init & Version checking ============================*/

		try {
			Init.loadLibraries();
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

			if(Fmod.FSOUND_GetVersion() < FMOD_VERSION) {
				printExit("Error : You are using the wrong DLL version!  You should be using FMOD " + FMOD_VERSION);
				return;
			}

			initialize();
		}
		catch(HeadlessException e) {
			printExit("Unexpected exception: " + e.getMessage());
			return;
		}

		init = true;
	}

	public void run() {
		if(!init) {
			return;
		}

		FSOUND_SAMPLE sptr;
		ByteBuffer streamingBuffer;
		int channel = -1;

		print("-------------------------------------------------------------\n");
		print("FMOD Streamer example. [mp2 mp3 wav ogg wma asf]\n");
		print("Copyright (c) Firelight Technologies Pty, Ltd, 1999-2004.\n");
		print("-------------------------------------------------------------\n");

		switch (LibLoader.getPlatform()) {
			case LibLoader.PLATFORM_WINDOWS:
				Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_WINMM);
				break;
			case LibLoader.PLATFORM_LINUX:
				Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_OSS);
				break;
			case LibLoader.PLATFORM_MACOSX:
				Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_MAC);
				break;
		}

		// ==========================================================================================
		// SELECT DRIVER
		// ==========================================================================================
		// The following list are the drivers for the output method selected above.
		int output = Fmod.FSOUND_GetOutput();
		if(output == FSOUND_OUTPUT_NOSOUND)     print("NoSound");
		else if(output == FSOUND_OUTPUT_WINMM)  print("Windows Multimedia Waveout");
		else if(output == FSOUND_OUTPUT_DSOUND) print("Direct Sound");
		else if(output == FSOUND_OUTPUT_ASIO)   print("ASIO");
		else if(output == FSOUND_OUTPUT_OSS)    print("Open Sound System");
		else if(output == FSOUND_OUTPUT_ESD)    print("Enlightment Sound Daemon");
		else if(output == FSOUND_OUTPUT_ALSA)   print("Advanced Linux Sound Architecture");
		else if(output == FSOUND_OUTPUT_MAC)    print("Mac SoundManager");
		print(" Driver list\n");
		print("---------------------------------------------------------\n");
		for(int i = 0; i <= Fmod.FSOUND_GetNumDrivers() - 1; i++) {
			print(i + " - " + Fmod.FSOUND_GetDriverName(i) + "\n");
		}
		print("---------------------------------------------------------\n"); // print driver names
		print("Press a corresponding number\n");

		int driver = -1;
		while(driver < 0 || driver > Fmod.FSOUND_GetNumDrivers() - 1) {
			try {
				driver = Integer.parseInt("" + getKey());
			}
			catch(NumberFormatException e) {
				driver = -1;
			}
			Thread.yield();
		}

		Fmod.FSOUND_SetDriver(driver); /* Select sound card (0 = default) */

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
		if(getFileChooser().showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
			stop();
			printExit("File not selected !");
			return;
		}
		File filename = getFileChooser().getSelectedFile();
		if(false) {
			int length = 0;
			byte[] datas = null;
			RandomAccessFile file = null;
			try {
				file = new RandomAccessFile(filename, "rw"); //rw = read and write
			}
			catch(FileNotFoundException e) {
				Fmod.FSOUND_Close();
				printExit("Error: File Not Found");
				return;
			}
			try {
				length = (int)file.length();
				datas = new byte[length];
				file.read(datas);
				file.close();
			}
			catch(Exception e) {
				printExit("Error reading the file [" + e.getMessage() + "]");
				return;
			}

			streamingBuffer = BufferUtils.newByteBuffer(datas.length);
			streamingBuffer.put(datas);
			streamingBuffer.rewind(); //Go to the beginning of the buffer

			//The memory pointer MUST remain valid while streaming ! (keeps buffer referenced somewhere)
			stream = Fmod.FSOUND_Stream_Open(streamingBuffer, FSOUND_NORMAL | FSOUND_MPEGACCURATE | FSOUND_LOADMEMORY,
					0, length);

			if(stream == null) {
				printExit("Can't creates the stream! ");
				return;
			}
		}
		else {
			String name = filename.getPath();
			if(name.startsWith("http:")) {
				print("Connecting to " + filename + ", please wait (this may take some time)....\n");
			}
			stream = Fmod.FSOUND_Stream_Open(name, FSOUND_NORMAL | FSOUND_MPEGACCURATE, 0, 0);
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
		print("Press f     to fast forward 2 seconds\n");
		print("Press e     to quit\n");
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

			switch(getKey()) {
				case ' ':
					Fmod.FSOUND_SetPaused(channel, !Fmod.FSOUND_GetPaused(channel));
					break;
				case 'f':
					Fmod.FSOUND_Stream_SetTime(stream, Fmod.FSOUND_Stream_GetTime(stream) + 2000);
					break;
				case 'e':
				case 'E':
					streamEnded = true;
					break;
			}

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
		while(!streamEnded && !deinit);

		stop();
	}

	public void stop() {
		if(!init || deinit) {
			return;
		}
		deinit = true;

		print("\n");

		if(stream != null && !stream.isNull()) {
			Fmod.FSOUND_Stream_Close(stream);
		}

		print("Shutdown FMOD\n");
		Fmod.FSOUND_Close();
	}

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

	//====================== GUI ============================

	private JFileChooser fileChooser = null;

	private JFileChooser getFileChooser() {
		if(fileChooser == null) {
			fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Open a music");
			fileChooser.setCurrentDirectory(new File("."));
			fileChooser.setMultiSelectionEnabled(false);
			fileChooser.resetChoosableFileFilters();
			fileChooser.addChoosableFileFilter(FileFilters.streamableFiles);
		}
		return fileChooser;
	}
} //  @jve:decl-index=0:visual-constraint="10,10"