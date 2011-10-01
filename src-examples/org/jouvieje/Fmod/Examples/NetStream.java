//===============================================================================================
// netstream.exe
// Copyright (c), Firelight Technologies Pty, Ltd, 1999-2004.
//
// This example shows how to play internet streams (SHOUTcast/Icecast2/HTTP)
//===============================================================================================

/**
 * I've ported the C++ FMOD example to use it with NativeFmod
 * It plays a mp3 music from the net. So, you need an internet connexion to run this sample.
 * I've put a mp3 file in my site to try this samples at this link :
 * 		http://jerome.jouvie.free.fr/downloads/NativeFmod/jules.mp3
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

import javax.swing.JPanel;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Callbacks.FSOUND_METADATACALLBACK;
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;

import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Examples.Util.ConsoleGUI;
import org.jouvieje.Fmod.Examples.Util.FmodExampleFrame;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Misc.Pointer;
import org.jouvieje.Fmod.Structures.FSOUND_STREAM;
import org.jouvieje.libloader.LibLoader;

public class NetStream extends ConsoleGUI implements FSOUND_OUTPUTTYPES, FSOUND_MODES, FSOUND_MISC_VALUES, VERSIONS {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new FmodExampleFrame(new NetStream());
	}

	public NetStream() {
		super();
		initFmod();
		initialize();
	}

	public JPanel getPanel() {
		return this;
	}

	public String getTitle() {
		return "FMOD NetStream example.";
	}

	private String[] status_str = new String[]{"NOTCONNECTED", "CONNECTING  ", "BUFFERING   ", "READY       ", "ERROR       "};

	private String artist = "";
	private String title = "";
	private int metanum = 0;

	private boolean init = false;
	private boolean deinit = false;

	private FSOUND_STREAM stream = null;

	private FSOUND_METADATACALLBACK metacallback = new FSOUND_METADATACALLBACK(){
		public boolean FSOUND_METADATACALLBACK(String name, String value, Pointer userdata) {
			if(name.compareTo("ARTIST") == 0) {
				artist = value;
			}
			else if(name.compareTo("TITLE") == 0) {
				title = value;
				metanum++;
			}
			return true;
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
			printExit("NativeFmod error! " + e.getMessage());
			return;
		}

		/*
		 * Checking NativeFmodEx version
		 */
		if(NATIVEFMOD_LIBRARY_VERSION != NATIVEFMOD_JAR_VERSION) {
			printExit("Error!  NativeFmod library version (" + NATIVEFMOD_LIBRARY_VERSION
					+ ") is different to jar version (" + NATIVEFMOD_JAR_VERSION + ")");
			return;
		}

		/*==================================================*/

		/*
		 * Checking Fmod version
		 */
		if(Fmod.FSOUND_GetVersion() < FMOD_VERSION) {
			printExit("Error : You are using the wrong DLL version!  You should be using FMOD " + FMOD_VERSION);
			return;
		}

		init = true;
	}

	public void run() {
		if(!init) return;

		print("-------------------------------------------------------------\n");
		print("FMOD netstream example.\n");
		print("Copyright (c) Firelight Technologies Pty, Ltd, 1999-2004.\n");
		print("-------------------------------------------------------------\n");
		print("Example: netstream http://www.fmod.org/stream.mp3\n\n");

		setInput("http://127.0.0.1/jouvieje/downloads/NativeFmod/jules.mp3");
		final String fileName = readInput("Please enter a music path and hit ENTER.");

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
			default:
				printExit("Operating system not supported !!!\n");
				return;
		}

		// ==========================================================================================
		// SELECT DRIVER
		// ==========================================================================================
		print("---------------------------------------------------------\n");
		int output = Fmod.FSOUND_GetOutput();
		if(output == FSOUND_OUTPUT_NOSOUND)     print("NoSound");
		else if(output == FSOUND_OUTPUT_WINMM)  print("Windows Multimedia Waveout");
		else if(output == FSOUND_OUTPUT_DSOUND) print("Direct Sound");
		else if(output == FSOUND_OUTPUT_A3D)    print("A3D");
		else if(output == FSOUND_OUTPUT_OSS)    print("Open Sound System");
		else if(output == FSOUND_OUTPUT_ESD)    print("Enlightenment Sound Daemon");
		else if(output == FSOUND_OUTPUT_ALSA)   print("ALSA");
		else if(output == FSOUND_OUTPUT_MAC)    print("Mac SoundManager");

		print(" Driver list\n");
		print("---------------------------------------------------------\n");

		for(int i = 0; i <= Fmod.FSOUND_GetNumDrivers() - 1; i++) {
			print(i + " - " + Fmod.FSOUND_GetDriverName(i) + "\n");
		}
		print("---------------------------------------------------------\n"); // print driver names

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

		Fmod.FSOUND_SetDriver(driver); // Select sound card (0 = default)

		// ==========================================================================================
		// INITIALIZE
		// ==========================================================================================
		if(!Fmod.FSOUND_Init(44100, 32, 0)) {
			String error = Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError());
			print("Shutdown FMOD\n");
			Fmod.FSOUND_Close();
			printExit(error);
			return;
		}

		/*
		 * Internet streams can work with a much smaller stream buffer than normal streams because they
		 * use another level of buffering on top of the stream buffer.
		 */
		Fmod.FSOUND_Stream_SetBufferSize(100);

		/*
		 * Here's where we set the size of the network buffer and some buffering parameters.
		 * In this case we want a network buffer of 64k, we want it to prebuffer 60% of that when we first
		 * connect, and we want it to rebuffer 80% of that whenever we encounter a buffer underrun.
		 */
		Fmod.FSOUND_Stream_Net_SetBufferProperties(64000, 60, 80);

		/*
		 * Open the stream using FSOUND_NONBLOCKING because the connect/buffer process might take a long time
		 */
		stream = Fmod.FSOUND_Stream_Open(fileName, FSOUND_NORMAL | FSOUND_NONBLOCKING, 0, 0);
		if(stream == null) {
			String error = Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError());
			print("Shutdown FMOD\n");
			Fmod.FSOUND_Close();
			printExit(error);
			return;
		}

		print("\nPress ENTER to quit...\n");

		int channel = -1;
		int openstate = 0;
		int[] read_percent = new int[1];
		int[] status = new int[1];
		resetInput();
		do {
			/*
			 * Play the stream if it's not already playing
			 */
			if(channel < 0) {
				channel = Fmod.FSOUND_Stream_PlayEx(FSOUND_FREE, stream, null, true);
				Fmod.FSOUND_SetPaused(channel, false);

				if(channel != -1) Fmod.FSOUND_Stream_Net_SetMetadataCallback(stream, metacallback, null);
			}

			openstate = Fmod.FSOUND_Stream_GetOpenState(stream);
			if((openstate == -1) || (openstate == -3)) {
				print("\n");
				print("ERROR: failed to open stream!\n");
				print("SERVER: " + Fmod.FSOUND_Stream_Net_GetLastServerStatus() + "\n");
				break;
			}

			Fmod.FSOUND_Stream_Net_GetStatus(stream, status, read_percent, null, null);

			/*
			 * Show how much of the net buffer is used and what the status is
			 */
			if(metanum != 0) {
				print(artist + " - " + title + "\n");
				metanum = 0;
			}
			byte[] b = "                                                  ".getBytes();
			for(int i = 0; i < (read_percent[0] >> 1) + (read_percent[0] & 1); i++) {
				b[i] = '=';
			}

			printr("|" + new String(b) + "| " + read_percent[0] + "%  " + status_str[status[0]]);

			try {
				Thread.sleep(500);
			}
			catch(Exception e) {}
		}
		while(!keyHit() && !deinit);

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
}