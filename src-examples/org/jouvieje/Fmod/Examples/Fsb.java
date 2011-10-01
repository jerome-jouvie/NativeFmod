//===============================================================================================
//FSB.EXE
//Copyright (c), Firelight Technologies Pty, Ltd, 1999-2004.
//
//This example demonstrates use of the FMOD Sample Bank format and also usage of the 
//FSOUND_Sample_SetDefaultsEx function.
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
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;

import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Examples.Util.ConsoleGUI;
import org.jouvieje.Fmod.Examples.Util.FmodExampleFrame;
import org.jouvieje.Fmod.Examples.Util.Medias;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Structures.FMUSIC_MODULE;
import org.jouvieje.Fmod.Structures.FSOUND_SAMPLE;
import org.jouvieje.libloader.LibLoader;

public class Fsb extends ConsoleGUI implements FSOUND_OUTPUTTYPES, FSOUND_MODES, FSOUND_MISC_VALUES, VERSIONS {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new FmodExampleFrame(new Fsb());
	}

	public Fsb() {
		super();
		initFmod();
		initialize();
	}

	public JPanel getPanel() {
		return this;
	}

	public String getTitle() {
		return "FMOD Fsb example.";
	}

	private boolean init = false;
	private boolean deinit = false;

	private FMUSIC_MODULE mod = null;
	private ByteBuffer modBuff = null;

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
			printExit("Error!  NativeFmod library version (" + NATIVEFMOD_LIBRARY_VERSION + ") is different to jar version (" + NATIVEFMOD_JAR_VERSION + ")\n");
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

		FSOUND_SAMPLE sample;
		int sampleindex = -1;
		boolean variation = true;
		int lastopenstate = -1;

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
				printExit("Operating system not recognized !\n");
				return;
		}

		// ==========================================================================================
		// SELECT DRIVER
		// ==========================================================================================

		// The following list are the drivers for the output method selected above.
		print("---------------------------------------------------------\n");
		int output = Fmod.FSOUND_GetOutput();
		if(output == FSOUND_OUTPUT_NOSOUND)     print("NoSound");
		else if(output == FSOUND_OUTPUT_WINMM)  print("Windows Multimedia Waveout");
		else if(output == FSOUND_OUTPUT_DSOUND) print("Direct Sound");
		else if(output == FSOUND_OUTPUT_A3D)    print("A3D");
		else if(output == FSOUND_OUTPUT_OSS)    print("Open Sound System");
		else if(output == FSOUND_OUTPUT_ESD)    print("Enlightenment Sound Daemon");
		else if(output == FSOUND_OUTPUT_ALSA)   print("Advanced Linux Sound Architecture");
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

		// ==========================================================================================
		// OPEN FSB
		// ==========================================================================================
		modBuff = Medias.loadMediaIntoMemory("/Media/footsteps.fsb");
		mod = Fmod.FMUSIC_LoadSongEx(modBuff, 0, modBuff.capacity(), /*FSOUND_NONBLOCKING | */FSOUND_LOADMEMORY, (int[])null, 0);
		if(mod == null) {
			String error = Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError());
			print("Shutdown FMOD\n");
			Fmod.FSOUND_Close();
			printExit(error);
			return;
		}

		print("=========================================================================\n");
		print("\tSPACE to toggle pitch/volume variation\n");
		print("\te     to quit\n");
		print("=========================================================================\n");

		boolean exit = false;
		do {
			printr("Pitch/volume variation: " + (variation ? "on" : "off"));

			/*
				Set initial defaults for both samples. Do this only once as soon as the FSB has finished loading.
			*/
			if((lastopenstate != 0) && (Fmod.FMUSIC_GetOpenState(mod) == 0)) {
				sample = Fmod.FMUSIC_GetSample(mod, 0);
				Fmod.FSOUND_Sample_SetDefaultsEx(sample, -1, -1, -1, -1, 2000, 128, -1);
				sample = Fmod.FMUSIC_GetSample(mod, 1);
				Fmod.FSOUND_Sample_SetDefaultsEx(sample, -1, -1, -1, -1, 2000, 128, -1);
				lastopenstate = 0;
			}

			/*
				Play a sample from the FSB. Do this once every frame when the FSB has finished loading.
			*/
			if(Fmod.FMUSIC_GetOpenState(mod) == 0) {
				sample = Fmod.FMUSIC_GetSample(mod, sampleindex++ & 1);
				Fmod.FSOUND_PlaySound(FSOUND_FREE, sample);
			}

			switch(getKey()) {
				case ' ':
					variation = !variation;
					break;
				case 'e':
				case 'E':
					exit = true;
					break;
			}

			//Change the defaults/variations on both samples.
			if(variation) {
				sample = Fmod.FMUSIC_GetSample(mod, 0);
				Fmod.FSOUND_Sample_SetDefaultsEx(sample, -1, -1, -1, -1, 2000, 128, -1);
				sample = Fmod.FMUSIC_GetSample(mod, 1);
				Fmod.FSOUND_Sample_SetDefaultsEx(sample, -1, -1, -1, -1, 2000, 128, -1);
			}
			else {
				sample = Fmod.FMUSIC_GetSample(mod, 0);
				Fmod.FSOUND_Sample_SetDefaultsEx(sample, -1, -1, -1, -1, 0, 0, 0);
				sample = Fmod.FMUSIC_GetSample(mod, 1);
				Fmod.FSOUND_Sample_SetDefaultsEx(sample, -1, -1, -1, -1, 0, 0, 0);
			}

			try {
				Thread.sleep(600 + (variation ? ((int)(0x7fff * Math.random()) % 100) : 50));
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

		if(mod != null && !mod.isNull()) {
			Fmod.FMUSIC_FreeSong(mod);
		}
		modBuff = null;

		print("Shutdown FMOD\n");
		Fmod.FSOUND_Close();
	}
}