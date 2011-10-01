/*===============================================================================================
 SIMPLE.EXE
 Copyright (c), Firelight Technologies Pty, Ltd 1999-2004.

 This example demonstrates some fundamental FMOD usage, including device enumeration, output
 mode selection, user file I/O callbacks, loading and playing samples and a music file, and
 calling some runtime manipulation and information functions.
===============================================================================================*/

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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

import javax.swing.JPanel;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Callbacks.FMUSIC_CALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_ALLOCCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_CLOSECALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_FREECALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_OPENCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_READCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_REALLOCCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_SEEKCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_TELLCALLBACK;
import org.jouvieje.Fmod.Defines.FSOUND_INIT_FLAGS;
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;

import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_MIXERTYPES;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Examples.Util.ConsoleGUI;
import org.jouvieje.Fmod.Examples.Util.FmodExampleFrame;
import org.jouvieje.Fmod.Examples.Util.Medias;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Misc.BufferUtils;
import org.jouvieje.Fmod.Misc.ObjectPointer;
import org.jouvieje.Fmod.Misc.Pointer;
import org.jouvieje.Fmod.Structures.FMUSIC_MODULE;
import org.jouvieje.Fmod.Structures.FSOUND_SAMPLE;
import org.jouvieje.libloader.LibLoader;

public class Simple extends ConsoleGUI implements FSOUND_OUTPUTTYPES, FSOUND_INIT_FLAGS, FSOUND_MODES, FSOUND_MISC_VALUES, FSOUND_MIXERTYPES, VERSIONS {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new FmodExampleFrame(new Simple());
	}

	private boolean init = false;
	private boolean deinit = false;

	private FMUSIC_MODULE mod = null;
	private ByteBuffer modBuff = null;
	private FSOUND_SAMPLE samp1 = null;
	private ByteBuffer samp1Buff = null;
	private FSOUND_SAMPLE samp2 = null;
	private ByteBuffer samp2Buff = null;
	private FSOUND_SAMPLE samp3 = null;
	private ByteBuffer samp3Buff = null;

	public Simple() {
		super();
		initFmod();
		initialize();
	}

	public JPanel getPanel() {
		return this;
	}

	public String getTitle() {
		return "FMOD Simple example.";
	}

	/*
	 * File callbacks
	 */
	private FSOUND_OPENCALLBACK myopen = new FSOUND_OPENCALLBACK(){
		RandomAccessFile randomAccessFile;

		public Pointer FSOUND_OPENCALLBACK(String name) {
			try {
				randomAccessFile = new RandomAccessFile(name, "rw");
				return ObjectPointer.create(randomAccessFile);
			}
			catch(FileNotFoundException e) {
				print("Failed to open the file " + name + " [" + e.getMessage() + "]\n");
				return null;
			}
		}
	};

	private FSOUND_CLOSECALLBACK myclose = new FSOUND_CLOSECALLBACK(){
		public void FSOUND_CLOSECALLBACK(Pointer handle) {
			try {
				ObjectPointer objectPointer = ObjectPointer.createView(handle);
				RandomAccessFile file = (RandomAccessFile)objectPointer.getObject();
				file.close();
				objectPointer.release(); //Don't forget to release ObjectPointer !
			}
			catch(Exception e) {
				print("Failed to close the file [" + e.getMessage() + "]\n");
			}
		}
	};

	private FSOUND_READCALLBACK myread = new FSOUND_READCALLBACK(){
		public int FSOUND_READCALLBACK(ByteBuffer buffer, int size, Pointer handle) {
			try {
				RandomAccessFile file = (RandomAccessFile)ObjectPointer.createView(handle).getObject();

				//read our data
				byte[] data = new byte[size];
				int byteRead = file.read(data);

				//copy data read into buffer
				buffer.put(data, 0, byteRead);
				return byteRead;
			}
			catch(IOException e) {
				print("Error reading file [" + e.getMessage() + "]\n");
				return 0;
			}
		}
	};

	//Values taken from stdio.h
	public final static int SEEK_CUR = 1; //Seek from the current position of file pointer
	public final static int SEEK_END = 2; //Seek from the end of file
	public final static int SEEK_SET = 0; //Seek from the beginning of file

	private FSOUND_SEEKCALLBACK myseek = new FSOUND_SEEKCALLBACK(){
		public int FSOUND_SEEKCALLBACK(Pointer handle, int pos, byte mode) {
			try {
				RandomAccessFile file = (RandomAccessFile)ObjectPointer.createView(handle).getObject();
				long position = 0;
				switch(mode) {
					case SEEK_CUR:
						position = file.getFilePointer() + pos;
						break;
					case SEEK_END:
						position = file.length() + pos;
						break;
					case SEEK_SET:
						position = pos;
						break;
				}
				file.seek(position);
			}
			catch(IOException e) {
				print("Error seeking file [" + e.getMessage() + "]\n");
				return -1;
			}
			return 0;
		}
	};

	private FSOUND_TELLCALLBACK mytell = new FSOUND_TELLCALLBACK(){
		public int FSOUND_TELLCALLBACK(Pointer handle) {
			try {
				RandomAccessFile file = (RandomAccessFile)ObjectPointer.createView(handle).getObject();
				return (int)file.getFilePointer();
			}
			catch(IOException e) {
				print("Error in tell method [" + e.getMessage() + "]\n");
				return -1;
			}
		}
	};

	/*
	 * Memory allocation callbacks
	 */
	private FSOUND_ALLOCCALLBACK myalloc = new FSOUND_ALLOCCALLBACK(){
		public ByteBuffer FSOUND_ALLOCCALLBACK(int size) {
			print("FMOD Malloc'ed " + size + " bytes\n");
			return BufferUtils.newByteBuffer(size);
		}
	};

	private FSOUND_REALLOCCALLBACK myrealloc = new FSOUND_REALLOCCALLBACK(){
		public ByteBuffer FSOUND_REALLOCCALLBACK(ByteBuffer ptr, int size) {
			print("FMOD Realloced'ed " + size + " bytes\n");
			if(ptr.capacity() < size) {
				return BufferUtils.newByteBuffer(size);
			}
			else {
				return (ByteBuffer)ptr.limit(size);
			}
		}
	};

	private FSOUND_FREECALLBACK myfree = new FSOUND_FREECALLBACK(){
		public void FSOUND_FREECALLBACK(ByteBuffer ptr) {
			print("FMOD freed some memory\n");
			//TODO free
		}
	};

	private FMUSIC_CALLBACK ordercallback = new FMUSIC_CALLBACK(){
		public void FMUSIC_CALLBACK(FMUSIC_MODULE mod, short param) {
			print("\nOrder Callback : param " + param + "\n");
		}
	};

	private FMUSIC_CALLBACK instcallback = new FMUSIC_CALLBACK(){
		public void FMUSIC_CALLBACK(FMUSIC_MODULE mod, short param) {
			print("\nInst Callback : param " + param + "\n");
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
			printExit("Error!  NativeFmod library version (" + NATIVEFMOD_LIBRARY_VERSION + ") is different to jar version (" + NATIVEFMOD_JAR_VERSION + ")");
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
		if(!init) {
			return;
		}

		/*
		 * SELECT OUTPUT METHOD
		 */

		print("---------------------------------------------------------\n");
		print("Output Type\n");
		print("---------------------------------------------------------\n");
		switch (LibLoader.getPlatform()) {
			case LibLoader.PLATFORM_WINDOWS:
				print("1 - Direct Sound\n");
				print("2 - Windows Multimedia Waveout\n");
				print("3 - ASIO\n");
				break;
			case LibLoader.PLATFORM_LINUX:
				print("1 - OSS - Open Sound System\n");
				print("2 - ESD - Elightment Sound Daemon\n");
				print("3 - ALSA 0.9 - Advanced Linux Sound Architecture\n");
				break;
			case LibLoader.PLATFORM_MACOSX:
				print("1 - Mac SoundManager\n");
				break;
			default:
				print("1 - NoSound\n");
				break;
		}
		print("---------------------------------------------------------\n"); /* print driver names */
		print("Press a corresponding number\n");

		int output = -1;
		while(output < 1 || output > 4) {
			try {
				output = Integer.parseInt("" + getKey());
			}
			catch(NumberFormatException e) {
				output = -1;
			}
			Thread.yield();
		}

		switch (LibLoader.getPlatform()) {
			case LibLoader.PLATFORM_WINDOWS:
				switch(output) {
					case 1:
						Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_DSOUND);
						break;
					case 2:
						Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_WINMM);
						break;
					case 3:
						Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_ASIO);
						break;
				}
				break;
			case LibLoader.PLATFORM_LINUX:
				switch(output) {
					case 1:
						Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_OSS);
						break;
					case 2:
						Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_ESD);
						break;
					case 3:
						Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_ALSA);
						break;
				}
				break;
			case LibLoader.PLATFORM_MACOSX:
				Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_MAC);
				break;
			default:
				Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_NOSOUND);
				break;
		}

		/*
		 * Set custom file callbacks?  This doesnt have to be done, its just here as an example.
		 * Not MIDI files do not use file callbacks so midi loading will fail.  FMUSIC_LoadSongMemory could be used to load the midi.
		 */
		if(false) {
			Fmod.FSOUND_File_SetCallbacks(myopen, myclose, myread, myseek, mytell);
		}

		/*
		 * Set custom memory callbacks?  This is optional as well of course.
		 */
		if(false) {
			//user callbacks
			if(!Fmod.FSOUND_SetMemorySystem(null, 0, myalloc, myrealloc, myfree)) {
				printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
				return;
			}
		}
		else {
			//internal memory management - give it 1 mb and no more mallocs will come from fmod
			ByteBuffer memoryBuffer = BufferUtils.newByteBuffer(4 * 1024 * 1024);
			if(!Fmod.FSOUND_SetMemorySystem(memoryBuffer, memoryBuffer.capacity(), null, null, null)) {
				printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
				return;
			}
		}

		/*
		 * SELECT DRIVER
		 */

		/*
		 * The following list are the drivers for the output method selected above.
		 */
		print("---------------------------------------------------------\n");
		output = Fmod.FSOUND_GetOutput();
		if(output == FSOUND_OUTPUT_NOSOUND) print("NoSound");
		else if(output == FSOUND_OUTPUT_WINMM) print("Windows Multimedia Waveout");
		else if(output == FSOUND_OUTPUT_DSOUND) print("Direct Sound");
		else if(output == FSOUND_OUTPUT_ASIO) print("ASIO");
		else if(output == FSOUND_OUTPUT_OSS) print("Open Sound System");
		else if(output == FSOUND_OUTPUT_ESD) print("Enlightment Sound Daemon");
		else if(output == FSOUND_OUTPUT_ALSA) print("Advanced Linux Sound Architecture");
		else if(output == FSOUND_OUTPUT_MAC) print("Mac SoundManager");

		print(" Driver list\n");
		print("---------------------------------------------------------\n");

		for(int i = 0; i <= Fmod.FSOUND_GetNumDrivers() - 1; i++) {
			print(i + " - " + Fmod.FSOUND_GetDriverName(i) + "\n");
		}
		print("---------------------------------------------------------\n");
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

		/*
		 * INITIALIZE
		 */
		if(!Fmod.FSOUND_Init(44100, 32, FSOUND_INIT_USEDEFAULTMIDISYNTH)) {
			printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			return;
		}

		/*
		 * LOAD SONG
		 */

		/*
		 * The following list are the drivers for the output method selected above.
		 */
		print("---------------------------------------------------------\n");
		print(" Select Music Type\n");
		print("---------------------------------------------------------\n");
		print("1 - MOD\n");
		print("2 - MIDI (Using Default Software Synth)\n");
		print("---------------------------------------------------------\n");
		print("Press a corresponding number\n");

		int type = -1;
		while(type < 1 || type > 2) {
			try {
				type = Integer.parseInt("" + getKey());
			}
			catch(NumberFormatException e) {
				type = -1;
			}
			Thread.yield();
		}

		switch(type) {
			case 1:
				modBuff = Medias.loadMediaIntoMemory("/Media/invtro94.s3m");
				break;
			case 2:
				modBuff = Medias.loadMediaIntoMemory("/Media/canyon.mid");
				break;
		}

		mod = Fmod.FMUSIC_LoadSongEx(modBuff, 0, modBuff.capacity(), FSOUND_LOADMEMORY, (int[])null, 0);
		if(mod == null) {
			printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			return;
		}

		/*
		 * LOAD SAMPLES
		 */

		//PCM,44,100 Hz, 8 Bit, Mono
		samp1Buff = Medias.loadMediaIntoMemory("/Media/drumloop.wav");
		samp1 = Fmod.FSOUND_Sample_Load(FSOUND_UNMANAGED, samp1Buff, FSOUND_NORMAL | FSOUND_HW2D | FSOUND_LOADMEMORY,
				0, samp1Buff.capacity()); //hardware? why not, just to show it can be done
		if(samp1 == null) {
			printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			return;
		}
		Fmod.FSOUND_Sample_SetMode(samp1, FSOUND_LOOP_OFF); //this wav has loop points in it which turns looping on.. turn it off!

		/* PCM,22,050 Hz, 16 Bit, Mono */
		samp2Buff = Medias.loadMediaIntoMemory("/Media/jaguar.wav");
		samp2 = Fmod.FSOUND_Sample_Load(FSOUND_UNMANAGED, samp2Buff, FSOUND_NORMAL | FSOUND_LOADMEMORY, 0, samp2Buff
				.capacity());
		if(samp2 == null) {
			printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			return;
		}

		/* PCM,22,050 Hz, 8 Bit, Stereo */
		samp3Buff = Medias.loadMediaIntoMemory("/Media/chimes.wav");
		samp3 = Fmod.FSOUND_Sample_Load(FSOUND_UNMANAGED, samp3Buff, FSOUND_NORMAL | FSOUND_LOADMEMORY, 0, samp3Buff
				.capacity());
		if(samp3 == null) {
			printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			return;
		}

		/*
		 * DISPLAY HELP
		 */

		print("FSOUND Output Method : ");
		output = Fmod.FSOUND_GetOutput();
		if(output == FSOUND_OUTPUT_NOSOUND)     print("NoSound");
		else if(output == FSOUND_OUTPUT_WINMM)  print("Windows Multimedia Waveout");
		else if(output == FSOUND_OUTPUT_DSOUND) print("Direct Sound");
		else if(output == FSOUND_OUTPUT_ASIO)   print("ASIO");
		else if(output == FSOUND_OUTPUT_OSS)    print("Open Sound System");
		else if(output == FSOUND_OUTPUT_ESD)    print("Enlightment Sound Daemon");
		else if(output == FSOUND_OUTPUT_ALSA)   print("Audio Linux System Audio");

		print("\nFSOUND Mixer         : ");
		int mixer = Fmod.FSOUND_GetMixer();
		if(mixer == FSOUND_MIXER_QUALITY_FPU)        print("FSOUND_MIXER_QUALITY_FPU");
		else if(mixer == FSOUND_MIXER_QUALITY_MMXP5) print("FSOUND_MIXER_QUALITY_MMXP5");
		else if(mixer == FSOUND_MIXER_QUALITY_MMXP6) print("FSOUND_MIXER_QUALITY_MMXP6");

		print("\nFSOUND Driver        : " + Fmod.FSOUND_GetDriverName(Fmod.FSOUND_GetDriver()) + "\n");

		print("=========================================================================\n");
		print("Press 1       Play 16bit sound at any time\n");
		print("      2       Play 8bit sound at any time (limited to 3 at a time)\n");
		print("      3       Play 16bit STEREO sound at any time\n");
		print("      <       Rewind mod back 1 order\n");
		print("      >       FastForward mod forward 1 order\n");
		print("      SPACE   Pause/unpause music at any time\n");
		print("      e       Quit\n");
		print("=========================================================================\n");
		readInput("Press ENTER to continue...");

		print("\n");
		print("Playing " + Fmod.FMUSIC_GetName(mod) + "...\n");

		for(int count = 0; count < Fmod.FMUSIC_GetNumSamples(mod) && count < 20; count += 2) {
			String a = Fmod.FSOUND_Sample_GetName(Fmod.FMUSIC_GetSample(mod, count));
			String b = Fmod.FSOUND_Sample_GetName(Fmod.FMUSIC_GetSample(mod, count + 1));
			if(a == null) {
				a = "";
			}
			if(b == null) {
				b = "";
			}
			print(count + " " + a + " " + (count + 1) + " " + b + "\n");
		}

		Fmod.FSOUND_Sample_SetMaxPlaybacks(samp2, 3);

		/*
		 START PLAYING MUSIC!
		 */
		Fmod.FMUSIC_SetOrderCallback(mod, ordercallback, 1);
		Fmod.FMUSIC_SetInstCallback(mod, instcallback, 5);
		Fmod.FMUSIC_SetMasterVolume(mod, 192);
		Fmod.FMUSIC_SetLooping(mod, false);
		Fmod.FMUSIC_PlaySong(mod);

		boolean exit = false;
		do {
			printr("order = " + Fmod.FMUSIC_GetOrder(mod) + "/" + Fmod.FMUSIC_GetNumOrders(mod) + ", row = "
					+ Fmod.FMUSIC_GetRow(mod) + "/" + Fmod.FMUSIC_GetPatternLength(mod, Fmod.FMUSIC_GetOrder(mod))
					+ " time = " + (Fmod.FMUSIC_GetTime(mod) / 1000) + "." + (Fmod.FMUSIC_GetTime(mod) % 1000 / 10)
					+ " finished " + Fmod.FMUSIC_IsFinished(mod) + " channels = " + Fmod.FSOUND_GetChannelsPlaying()
					+ " cpu = " + Fmod.FSOUND_GetCPUUsage() + "%");

			int channel;
			switch(getKey()) {
				case ' ':
					Fmod.FMUSIC_SetPaused(mod, !Fmod.FMUSIC_GetPaused(mod));
					break;
				case '1':
					Fmod.FSOUND_PlaySound(FSOUND_FREE, samp1);
					break;
				case '2':
					channel = Fmod.FSOUND_PlaySoundEx(FSOUND_FREE, samp2, null, true);
					Fmod.FSOUND_SetCurrentPosition(channel, Fmod.FSOUND_Sample_GetLength(samp2) - 1);
					Fmod.FSOUND_SetFrequency(channel, -22050); //Play it backwards!
					Fmod.FSOUND_SetVolume(channel, 255);
					Fmod.FSOUND_SetPan(channel, 255); //pan it all the way to the right
					Fmod.FSOUND_SetPaused(channel, false);
					break;
				case '3':
					channel = Fmod.FSOUND_PlaySoundEx(FSOUND_FREE, samp3, null, true);
					Fmod.FSOUND_SetPaused(channel, false);
					break;
				case '>':
					Fmod.FMUSIC_SetOrder(mod, Fmod.FMUSIC_GetOrder(mod) + 1);
					break;
				case '<':
					Fmod.FMUSIC_SetOrder(mod, Fmod.FMUSIC_GetOrder(mod) - 1);
					break;
				case 'e':
				case 'E':
					exit = true;
					break;
			}
			resetInput();

			try {
				Thread.sleep(100);
			}
			catch(InterruptedException e) {}
		}
		while(!exit && !deinit);

		stop();
	}

	public void stop() {
		if(!init || deinit) {
			return;
		}
		deinit = true;

		print("\n");

		/*
		 CLEANUP AND SHUTDOWN
		 */
		if(mod != null && !mod.isNull()) Fmod.FMUSIC_StopSong(mod);
		modBuff = null;
		if(samp1 != null && !samp1.isNull()) Fmod.FSOUND_Sample_Free(samp1);
		samp1Buff = null;
		if(samp2 != null && !samp2.isNull()) Fmod.FSOUND_Sample_Free(samp2);
		samp2Buff = null;
		if(samp3 != null && !samp3.isNull()) Fmod.FSOUND_Sample_Free(samp3);
		samp3Buff = null;
		if(mod != null && !mod.isNull()) Fmod.FMUSIC_FreeSong(mod);

		print("Shutdown FMOD\n");
		Fmod.FSOUND_Close();
	}
}