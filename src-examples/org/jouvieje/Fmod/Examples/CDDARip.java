//===============================================================================================
//CDDARIP.EXE
//Copyright (c), Firelight Technologies Pty, Ltd, 1999-2004.
//
//Use CDDA streaming to rip a CD track to a wav file
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
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

import javax.swing.JPanel;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Callbacks.FSOUND_DSPCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_STREAMCALLBACK;
import org.jouvieje.Fmod.Defines.FSOUND_DSP_PRIORITIES;
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;

import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Examples.Util.ConsoleGUI;
import org.jouvieje.Fmod.Examples.Util.FmodExampleFrame;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.FileFormat.WavFormat.DataChunk;
import org.jouvieje.Fmod.FileFormat.WavFormat.FmtChunk;
import org.jouvieje.Fmod.FileFormat.WavFormat.RiffChunk;
import org.jouvieje.Fmod.FileFormat.WavFormat.WavHeader;
import org.jouvieje.Fmod.Misc.Pointer;
import org.jouvieje.Fmod.Structures.FSOUND_DSPUNIT;
import org.jouvieje.Fmod.Structures.FSOUND_STREAM;
import org.jouvieje.libloader.LibLoader;

public class CDDARip extends ConsoleGUI implements FSOUND_OUTPUTTYPES, FSOUND_DSP_PRIORITIES, FSOUND_MODES, FSOUND_MISC_VALUES, VERSIONS {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new FmodExampleFrame(new CDDARip());
	}

	public CDDARip() {
		super();
		initFmod();
		initialize();
	}

	public JPanel getPanel() {
		return this;
	}

	public String getTitle() {
		return "FMOD CDDA-Rip example.";
	}

	private boolean init = false;
	private boolean deinit = false;

	private boolean stream_ended = false;
	private long start_time, elapsed_time;

	private RandomAccessFile file = null;
	private long byteswritten = 0;

	private FSOUND_STREAMCALLBACK endcallback = new FSOUND_STREAMCALLBACK(){
		public boolean FSOUND_STREAMCALLBACK(FSOUND_STREAM stream, ByteBuffer buff, int len, Pointer userdata) {
			//Use to sop the writting in the file
			stream_ended = true;
			return true;
		}
	};

	private FSOUND_DSPCALLBACK DSP_RawWriteCallback = new FSOUND_DSPCALLBACK(){
		public ByteBuffer FSOUND_DSPCALLBACK(ByteBuffer originalbuffer, ByteBuffer newBuffer, int length,
				Pointer userdata) {
			if(file != null && !stream_ended) {
				/**
				 * length is the length of newBuffer in SAMPLES. But 1 SAMPLE is 4 bytes, so the length in byte is 4*length
				 * newbuffer holds 4*length bytes.
				 * 
				 * First we convert the CPointer (general C++ object) to a byte array (~ byte array).
				 * After, we get our 4*length elements as a byte array.
				 */
				byte[] datas = new byte[length * 4];
				newBuffer.get(datas, 0, length * 4);

				try {
					//write the byte array into the file
					file.write(datas);
				}
				catch(Exception e) {}

				byteswritten += length * 4;
			}

			return newBuffer;
		}
	};

	public void initFmod() {
		/*
		 * NativeFmod Init
		 */
		try {
			Init.loadLibraries();
		}
		catch(InitException e) {
			printExit("NativeFmod error! " + e.getMessage() + "\n");
			return;
		}

		/*
		 * Checking NativeFmodEx version
		 */
		if(NATIVEFMOD_LIBRARY_VERSION != NATIVEFMOD_JAR_VERSION) {
			printExit("Error!  NativeFmod library version (" + NATIVEFMOD_LIBRARY_VERSION
					+ ") is different to jar version (" + NATIVEFMOD_JAR_VERSION + ")\n");
			return;
		}

		/*==================================================*/

		/*
		 * Checking Fmod version
		 */
		if(Fmod.FSOUND_GetVersion() < FMOD_VERSION) {
			printExit("Error : You are using the wrong DLL version!  You should be using FMOD " + FMOD_VERSION + "\n");
			return;
		}

		init = true;
	}

	public void run() {
		if(!init) return;

		FSOUND_STREAM stream;
		FSOUND_DSPUNIT rawwrite_dsp;

		int track_num, read_percent;

		start_time = System.currentTimeMillis();

		print("-------------------------------------------------------------\n");
		print("FMOD CDDA-Rip example.\n");
		print("Copyright (c) Firelight Technologies Pty, Ltd, 2001-2004.\n");
		print("-------------------------------------------------------------\n");

		//Drive letter
		setInput("'E' (windows) or '/dev/cdrom1' (linux/mac)");
		String drive = readInput("\nAudio CD drive:");
		switch (LibLoader.getPlatform()) {
			case LibLoader.PLATFORM_WINDOWS:
				if(!((drive.charAt(0) >= 'a' && drive.charAt(0) <= 'z') || (drive.charAt(0) >= 'A' && drive.charAt(0) <= 'Z'))) {
					printExit("ERROR: Invalid drive");
					return;
				}
				drive += ":*j";
				break;
			case LibLoader.PLATFORM_LINUX:
			case LibLoader.PLATFORM_MACOSX:
				if(!drive.startsWith("/")) {
					printExit("ERROR: Invalid drive");
					return;
				}
				break;
		}

		Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_NOSOUND_NONREALTIME);

		if(!Fmod.FSOUND_Init(44100, 32, 0)) {
			String error = Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError());
			print("Shutdown FMOD\n");
			Fmod.FSOUND_Close();
			printExit(error);
			return;
		}

		//Output file
		setInput("track_ripped");
		String filename = readInput("Audio track rip file name:") + ".wav";

		try {
			file = new RandomAccessFile(new File(filename), "rw");
		}
		catch(IOException e) {
			file = null;
		}
		if(file == null) {
			print("Shutdown FMOD\n");
			Fmod.FSOUND_Close();
			printExit("ERROR: Couldn't open " + filename + " for writing");
			return;
		}

		/*
		 * Before we've even written the headers for the wav out, seek to the offset the raw data will start from.
		 */
		try {
			file.seek(WavHeader.SIZEOF_WAV_HEADER + FmtChunk.SIZEOF_FMT_CHUNK + DataChunk.SIZEOF_DATA_CHUNK);
		}
		catch(IOException e) {}

		/*
		 * Create a DSP callback which will capture the mixed data and write it to disk
		 */
		rawwrite_dsp = Fmod.FSOUND_DSP_Create(DSP_RawWriteCallback, FSOUND_DSP_DEFAULTPRIORITY_USER, null);
		Fmod.FSOUND_DSP_SetActive(rawwrite_dsp, true);

		Fmod.FSOUND_Stream_SetBufferSize(2000);

		stream = Fmod.FSOUND_Stream_Open(drive, FSOUND_NORMAL, 0, 0);
		if(stream == null) {
			Fmod.FSOUND_DSP_SetActive(rawwrite_dsp, false);
			Fmod.FSOUND_DSP_Free(rawwrite_dsp);
			print("Shutdown FMOD\n");
			Fmod.FSOUND_Close();
			printExit("ERROR: Couldn't create CDDA stream");
			return;
		}

		setInput("1");
		track_num = Integer.valueOf(readInput("Audio track number ro rip:")).intValue();
		if((track_num < 1) || ((track_num - 1) >= Fmod.FSOUND_Stream_GetNumSubStreams(stream))) {
			Fmod.FSOUND_Stream_Close(stream);
			Fmod.FSOUND_DSP_SetActive(rawwrite_dsp, false);
			Fmod.FSOUND_DSP_Free(rawwrite_dsp);
			print("Shutdown FMOD\n");
			Fmod.FSOUND_Close();
			printExit("ERROR: Invalid track number");
			return;
		}

		Fmod.FSOUND_Stream_SetEndCallback(stream, endcallback, null);
		Fmod.FSOUND_Stream_SetSubStream(stream, track_num - 1);
		Fmod.FSOUND_Stream_Play(FSOUND_FREE, stream);

		print("\nRipping " + new String(drive) + " track " + track_num + "  ("
				+ (Fmod.FSOUND_Stream_GetLengthMs(stream) / 1000 / 60) + ":"
				+ (Fmod.FSOUND_Stream_GetLengthMs(stream) / 1000 % 60) + ")\n");

		do {
			read_percent = (int)(((float)Fmod.FSOUND_Stream_GetTime(stream) / (float)Fmod.FSOUND_Stream_GetLengthMs(stream)) * 100.0f);

			byte[] b = "                                                  ".getBytes();
			
			for(int i = 0; i < (read_percent >> 1) + (read_percent & 1); i++) {
				b[i] = '=';
			}

			printr("|" + new String(b) + "| " + read_percent + "%   ");

			Fmod.FSOUND_Update();
		}
		while(!stream_ended);

		Fmod.FSOUND_Stream_Close(stream);
		Fmod.FSOUND_DSP_SetActive(rawwrite_dsp, false);
		Fmod.FSOUND_DSP_Free(rawwrite_dsp);

		/*
		 * Now finalize the wav file by seeking to the start and putting in the headers.
		 */
		/*
		 * WAV Structures
		 */
		WavHeader wavHeader = new WavHeader(new RiffChunk(new byte[]{'R', 'I', 'F', 'F'},
				FmtChunk.SIZEOF_FMT_CHUNK + RiffChunk.SIZEOF_RIFF_CHUNK + (int)byteswritten), new byte[]{'W', 'A', 'V', 'E'});
		FmtChunk fmtChunk = new FmtChunk(new RiffChunk(new byte[]{'f', 'm', 't', ' '},
				FmtChunk.SIZEOF_FMT_CHUNK - RiffChunk.SIZEOF_RIFF_CHUNK),
				(short)1, (short)2, 44100, 44100 * 2 * 16 / 8, (short)(1 * 2 * 16 / 8), (short)16);
		DataChunk dataChunk = new DataChunk(new RiffChunk(new byte[]{'d', 'a', 't', 'a'}, (int)byteswritten));

		try {
			//Go to the beginning of the file
			file.seek(0);

			WavHeader.writeWavHeader(file, wavHeader);
			FmtChunk.writeFmtChunk(file, fmtChunk);
			DataChunk.writeDataChunk(file, dataChunk);

			//Close the stream
			file.close();
		}
		catch(Exception e) {}

		stop();
	}

	public void stop() {
		if(!init || deinit) {
			return;
		}
		deinit = true;

		print("\n");

		print("Shutdown FMOD\n");
		Fmod.FSOUND_Close();

		elapsed_time = System.currentTimeMillis() - start_time;
		print("\nElapsed time: " + (elapsed_time / 1000 / 60) + ":" + (elapsed_time / 1000 % 60) + "\n");
	}
}