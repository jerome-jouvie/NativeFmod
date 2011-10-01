//===============================================================================================
//3D.EXE
//Copyright (c), Firelight Technologies Pty, Ltd, 1999-2004.
//
//This test shows EAX, DS3D and Software all being used together and the simple commands needed
//to set up some 3d audio.
//This application also displays the use of FSOUND_GetDriverCaps to get information on the
//3D capabilities of the selected driver
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

import java.nio.ByteBuffer; 

import javax.swing.JPanel;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Defines.FSOUND_CAPS;
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;
import org.jouvieje.Fmod.Defines.FSOUND_REVERB_PRESETS;

import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_MIXERTYPES;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Examples.Util.ConsoleGUI;
import org.jouvieje.Fmod.Examples.Util.FmodExampleFrame;
import org.jouvieje.Fmod.Examples.Util.Medias;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Structures.FSOUND_REVERB_PROPERTIES;
import org.jouvieje.Fmod.Structures.FSOUND_SAMPLE;
import org.jouvieje.libloader.LibLoader;

public class _3d extends ConsoleGUI implements FSOUND_OUTPUTTYPES, FSOUND_CAPS, FSOUND_MIXERTYPES, FSOUND_MISC_VALUES, FSOUND_MODES, FSOUND_REVERB_PRESETS, VERSIONS {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new FmodExampleFrame(new _3d());
	}

	public _3d() {
		super();
		initFmod();
		initialize();
	}

	public JPanel getPanel() {
		return this;
	}

	public String getTitle() {
		return "FMOD 3D example.";
	}

	private boolean init = false;
	private boolean deinit = false;

	//samples
	private FSOUND_SAMPLE samp1 = null;
	private ByteBuffer samp1Buff = null;
	private FSOUND_SAMPLE samp2 = null;
	private ByteBuffer samp2Buff = null;
	private FSOUND_SAMPLE samp3 = null;
	private ByteBuffer samp3Buff = null;

	//channels
	private int channel1 = -1;
	private int channel2 = -1;

	//50ms update for interface
	private final int INTERFACE_UPDATETIME = 50;

	private boolean listenerflag = true;
	private float[] listenerpos = new float[]{0.f, 0.f, 0.f};
	private float t = 0;
	private float[] lastpos = {0, 0, 0};

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
		print("---------------------------------------------------------\n"); // print driver names
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

		// ==========================================================================================
		// SELECT DRIVER
		// ==========================================================================================

		// The following list are the drivers for the output method selected above.
		print("\n---------------------------------------------------------\n");
		//switch/case cannot be used because values of the differents FSOUND_OUTPUT_ are not known (by Java) : we have to use if elseif ...
		output = Fmod.FSOUND_GetOutput();
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
			print(i + " - " + Fmod.FSOUND_GetDriverName(i) + "\n"); // print driver names
			
			int[] caps = new int[1];
			Fmod.FSOUND_GetDriverCaps(i, caps);

			if((caps[0] & FSOUND_CAPS_HARDWARE) != 0) print("  * Driver supports hardware 3D sound!\n");
			if((caps[0] & FSOUND_CAPS_EAX2) != 0)     print("  * Driver supports EAX 2 reverb!\n");
			if((caps[0] & FSOUND_CAPS_EAX3) != 0)     print("  * Driver supports EAX 3 reverb!\n");
		}
		print("---------------------------------------------------------\n"); // print driver names
		print("Press a corresponding number or E to quit");

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

		int[] caps = new int[1];
		Fmod.FSOUND_GetDriverCaps(Fmod.FSOUND_GetDriver(), caps);

		print("\n");
		print("---------------------------------------------------------\n");
		print("Driver capabilities\n");
		print("---------------------------------------------------------\n");
		if(caps[0] != 0) print("- This driver will support software mode only.\n  It does not properly support 3D sound hardware.\n");
		if((caps[0] & FSOUND_CAPS_HARDWARE) != 0) print("- Driver supports hardware 3D sound !\n");
		if((caps[0] & FSOUND_CAPS_EAX2) != 0)     print("- Driver supports EAX 2 reverb !\n");
		if((caps[0] & FSOUND_CAPS_EAX3) != 0)     print("- Driver supports EAX 3 reverb !\n");
		print("---------------------------------------------------------\n");

		Fmod.FSOUND_SetMixer(FSOUND_MIXER_QUALITY_AUTODETECT);

		// ==========================================================================================
		// INITIALIZE
		// ==========================================================================================
		if(!Fmod.FSOUND_Init(44100, 32, 0)) {
			printExit("Init : " + Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			return;
		}

		// ==========================================================================================
		// LOAD SAMPLES
		// ==========================================================================================

		// ==========================================================================================
		// 3D MONO
		// ==========================================================================================
		samp1Buff = Medias.loadMediaIntoMemory("/Media/drumloop.wav");
		samp1 = Fmod.FSOUND_Sample_Load(FSOUND_FREE, samp1Buff, FSOUND_HW3D | FSOUND_LOADMEMORY, 0, samp1Buff.capacity());
		if(samp1 == null) {
			printExit("samp1 : " + Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			stop();
			return;
		}

		// increasing mindistnace makes it louder in 3d space
		Fmod.FSOUND_Sample_SetMinMaxDistance(samp1, 4.0f, 10000.0f);
		Fmod.FSOUND_Sample_SetMode(samp1, FSOUND_LOOP_NORMAL);

		// ==========================================================================================
		// 3D MONO
		// ==========================================================================================
		samp2Buff = Medias.loadMediaIntoMemory("/Media/jaguar.wav");
		samp2 = Fmod.FSOUND_Sample_Load(FSOUND_UNMANAGED, samp2Buff, FSOUND_HW3D | FSOUND_LOADMEMORY, 0, samp2Buff.capacity());
		if(samp2 == null) {
			printExit("samp2 : " + Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			stop();
			return;
		}
		// increasing mindistance makes it louder in 3d space
		Fmod.FSOUND_Sample_SetMinMaxDistance(samp2, 4.0f, 10000.0f);
		Fmod.FSOUND_Sample_SetMode(samp2, FSOUND_LOOP_NORMAL);

		// ==========================================================================================
		// 2D STEREO
		// ==========================================================================================
		samp3Buff = Medias.loadMediaIntoMemory("/Media/chimes.wav");
		samp3 = Fmod.FSOUND_Sample_Load(FSOUND_UNMANAGED, samp3Buff, FSOUND_HW2D | FSOUND_LOADMEMORY, 0, samp3Buff.capacity());
		if(samp3 == null) {
			printExit("samp3 : " + Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			stop();
			return;
		}

		// ==========================================================================================
		// DISPLAY HELP
		// ==========================================================================================

		print("FSOUND Output Method : ");
		output = Fmod.FSOUND_GetOutput();
		if(output == FSOUND_OUTPUT_NOSOUND)     print("FSOUND_OUTPUT_NOSOUND");
		else if(output == FSOUND_OUTPUT_WINMM)  print("FSOUND_OUTPUT_WINMM");
		else if(output == FSOUND_OUTPUT_DSOUND) print("FSOUND_OUTPUT_DSOUND");
		else if(output == FSOUND_OUTPUT_ASIO)   print("FSOUND_OUTPUT_ASIO");
		else if(output == FSOUND_OUTPUT_OSS)    print("FSOUND_OUTPUT_OSS");
		else if(output == FSOUND_OUTPUT_ESD)    print("FSOUND_OUTPUT_ESD");
		else if(output == FSOUND_OUTPUT_ALSA)   print("FSOUND_OUTPUT_ALSA");

		print("\nFSOUND Mixer         : ");
		int mixer = Fmod.FSOUND_GetMixer();
		if(mixer == FSOUND_MIXER_QUALITY_FPU)        print("FSOUND_MIXER_QUALITY_FPU");
		else if(mixer == FSOUND_MIXER_QUALITY_MMXP5) print("FSOUND_MIXER_QUALITY_MMXP5");
		else if(mixer == FSOUND_MIXER_QUALITY_MMXP6) print("FSOUND_MIXER_QUALITY_MMXP6");

		print("\nFSOUND Driver        : ");
		print(Fmod.FSOUND_GetDriverName(Fmod.FSOUND_GetDriver()) + "\n");

		int[] num2d = new int[2];
		int[] num3d = new int[2];
		Fmod.FSOUND_GetNumHWChannels(num2d, num3d, null);

		print("Hardware 2D channels : " + num2d[0] + "\n");
		print("Hardware 3D channels : " + num3d[0] + "\n");

		print("=========================================================================\n");
		print("Press 1        Pause/Unpause 16bit 3D sound at any time\n");
		print("      2        Pause/Unpause 8bit 3D sound at any time\n");
		print("      3        Play 16bit STEREO 2D sound at any time\n");
		print("      4        Change to EAX Reverb mode CONCERTHALL (DirectSound/SBLive only)\n");
		print("      5        Change to EAX Reverb mode SEWERPIPE (DirectSound/SBLive only)\n");
		print("      6        Change to EAX Reverb mode PSYCHOTIC (DirectSound/SBLive only)\n");
		print("      <        Move listener left (in still mode)\n");
		print("      >        Move listener right (in still mode)\n");
		print("      SPACE    Stop/Start listener automatic movement\n");
		print("      e        Quit\n");
		print("=========================================================================\n");

		// ==========================================================================================
		// PLAY 2 LOOPING SOUNDS
		// ==========================================================================================

		//sample 1
		float[] pos = new float[]{-10.0f, 0.0f, 0.0f};
		float[] vel = new float[]{0.f, 0.f, 0.f};

		channel1 = Fmod.FSOUND_PlaySoundEx(FSOUND_FREE, samp1, null, true);
		Fmod.FSOUND_3D_SetAttributes(channel1, pos, vel);

		if(!Fmod.FSOUND_SetPaused(channel1, false)) {
			print(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()) + "\n");
		}

		//sample 2
		pos = new float[]{15.0f, 0.0f, 0.0f};
		vel = new float[]{0, 0, 0};

		channel2 = Fmod.FSOUND_PlaySoundEx(FSOUND_FREE, samp2, null, true);
		Fmod.FSOUND_3D_SetAttributes(channel2, pos, vel);

		Fmod.FSOUND_SetVolume(channel2, 128);
		if(!Fmod.FSOUND_SetPaused(channel2, false)) {
			print(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()) + "\n");
		}

		// ==========================================================================================
		// MAIN LOOP
		// ==========================================================================================
		boolean exit = false;
		do {
			switch(getKey()) {
				case '1':
					Fmod.FSOUND_SetPaused(channel1, !Fmod.FSOUND_GetPaused(channel1));
					break;
				case '2':
					Fmod.FSOUND_SetPaused(channel2, !Fmod.FSOUND_GetPaused(channel2));
					break;
				case '3':
					Fmod.FSOUND_PlaySound(FSOUND_FREE, samp3);
					break;
				case '4':
					FSOUND_REVERB_PROPERTIES props = FSOUND_REVERB_PROPERTIES.create(FSOUND_PRESET_CONCERTHALL);
					Fmod.FSOUND_Reverb_SetProperties(props);
					break;
				case '5':
					props = FSOUND_REVERB_PROPERTIES.create(FSOUND_PRESET_SEWERPIPE);
					Fmod.FSOUND_Reverb_SetProperties(props);
					break;
				case '6':
					props = FSOUND_REVERB_PROPERTIES.create(FSOUND_PRESET_PSYCHOTIC);
					Fmod.FSOUND_Reverb_SetProperties(props);
					break;
				case ' ':
					listenerflag = !listenerflag;
					break;
				case '<':
					if(!listenerflag) {
						listenerpos[0] -= 1.0f;
						if(listenerpos[0] < -35) listenerpos[0] = -35;
					}
					break;
				case '>':
					if(!listenerflag) {
						listenerpos[0] += 1.0f;
						if(listenerpos[0] > 30) listenerpos[0] = 30;
					}
					break;
				case 'e':
				case 'E':
					exit = true;
					break;
			}
			// ==========================================================================================
			// UPDATE THE LISTENER
			// ==========================================================================================

			if(listenerflag) {
				listenerpos[0] = ((float)Math.sin(t * 0.05f) * 33.0f); // left right pingpong
			}

			// ********* NOTE ******* READ NEXT COMMENT!!!!!
			// vel = how far we moved last FRAME (m/f), then time compensate it to SECONDS (m/s).
			vel[0] = (listenerpos[0] - lastpos[0]) * (1000 / INTERFACE_UPDATETIME);
			vel[1] = (listenerpos[1] - lastpos[1]) * (1000 / INTERFACE_UPDATETIME);
			vel[2] = (listenerpos[2] - lastpos[2]) * (1000 / INTERFACE_UPDATETIME);

			// store pos for next time
			lastpos[0] = listenerpos[0];
			lastpos[1] = listenerpos[1];
			lastpos[2] = listenerpos[2];

			Fmod.FSOUND_3D_Listener_SetAttributes(listenerpos, vel, 0.f, 0.f, 1.0f, 0.f, 1.0f, 0.f);

			t += (30 * (1.0f / INTERFACE_UPDATETIME)); // t is just a time value .. it increments in 30m/s steps in this example

			// print out a small visual display
			char[] s = "|.......................<1>......................<2>....................|".toCharArray();
			s[(int)(listenerpos[0]) + 35] = 'L';
			printr(new String(s));

			Fmod.FSOUND_Update();

			try {
				Thread.sleep(INTERFACE_UPDATETIME - 1); // -1 for time taken for printf etc
			}
			catch(Exception e) {}
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

		// ==========================================================================================
		// CLEANUP AND SHUTDOWN
		// ==========================================================================================

		// you dont need to free samples if you let fsound's sample manager look after samples, as
		// it will free them all for you.
		if(samp1 != null && !samp1.isNull()) {
			Fmod.FSOUND_Sample_Free(samp1);
		}
		samp1Buff = null;
		if(samp2 != null && !samp2.isNull()) {
			Fmod.FSOUND_Sample_Free(samp2);
		}
		samp2Buff = null;
		if(samp3 != null && !samp3.isNull()) {
			Fmod.FSOUND_Sample_Free(samp3);
		}
		samp3Buff = null;

		print("Shutdown FMOD\n");
		Fmod.FSOUND_Close();
	}
}