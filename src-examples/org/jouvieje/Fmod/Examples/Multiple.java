/*===============================================================================================
 MULTIPLE.EXE
 Copyright (c), Firelight Technologies Pty, Ltd 1999-2004.

 This example demonstrates how to use dynamic loading of fmod.dll to achieve multiple soundcard
 output at the same time.  If you do not have 2 soundcards you will have to select the same
 device twice.
 Besides this, it is a good helper to display how fmod.dll can be loaded dynamically without 
 having to link an import library.

 IMPORTANT!!! You must copy fmod.dll to fmod2.dll or libfmod-3.63.so to libfmod-3.63_2.so to
 avoid operating systems caching the dll!
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

import java.nio.ByteBuffer;

import javax.swing.JPanel;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Defines.FSOUND_INIT_FLAGS;
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;
import org.jouvieje.Fmod.Defines.INIT_MODES;

import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Examples.Util.ConsoleGUI;
import org.jouvieje.Fmod.Examples.Util.FmodExampleFrame;
import org.jouvieje.Fmod.Examples.Util.Medias;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Structures.FSOUND_SAMPLE;
import org.jouvieje.FmodDyn.FMOD_INSTANCE;
import org.jouvieje.FmodDyn.FmodDyn;
import org.jouvieje.libloader.LibLoader;

public class Multiple extends ConsoleGUI implements FSOUND_OUTPUTTYPES, FSOUND_INIT_FLAGS, FSOUND_MODES, FSOUND_MISC_VALUES, VERSIONS {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new FmodExampleFrame(new Multiple());
	}

	public Multiple() {
		super();
		initFmod();
		initialize();
	}

	public JPanel getPanel() {
		return this;
	}

	public String getTitle() {
		return "FMOD Multiple example.";
	}

	//two instances of fmod
	private FMOD_INSTANCE fmod1;
	private FMOD_INSTANCE fmod2;

	//sample used
	private FSOUND_SAMPLE samp1;
	private FSOUND_SAMPLE samp2;

	private boolean init = false;
	private boolean deinit = false;

	private ByteBuffer sampBuff = null;

	public void initFmod() {
		/*
		 * NativeFmod Init
		 */
		try {
			Init.loadLibraries(INIT_MODES.INIT_FMOD_DYN);
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

		init = true;
	}

	public void run() {
		if(!init) {
			return;
		}

		String FMOD_LIB = "", FMOD_LIB2 = "";
		switch (LibLoader.getPlatform()) {
			case LibLoader.PLATFORM_WINDOWS:
				if(LibLoader.isWindowsCE()) {
					FMOD_LIB = "fmodce.dll";
					FMOD_LIB2 = "fmodce2.dll";
				}
				else {
					FMOD_LIB = "fmod.dll";
					FMOD_LIB2 = "fmod2.dll";
				}
				break;
			case LibLoader.PLATFORM_LINUX:
				FMOD_LIB = "libfmod-3.75.so";
				FMOD_LIB2 = "libfmod-3.75_2.so";
				break;
			case LibLoader.PLATFORM_MACOSX:
				printExit("This example does not support Mac.");
				return;
			default:
				printExit("OS not recognized !");
				return;
		}
		

		fmod1 = FmodDyn.FMOD_CreateInstance(FMOD_LIB);
		fmod2 = FmodDyn.FMOD_CreateInstance(FMOD_LIB2);

		if(fmod1 == null) {
			printExit("Error : Cannot find " + FMOD_LIB + "\n");
			return;
		}
		if(fmod2 == null) {
			printExit("Error : Cannot find " + FMOD_LIB2 + "\n");
			return;
		}

		if(fmod1.FSOUND_GetVersion() < FMOD_VERSION) {
			printExit("Error : You are using the wrong DLL version!  You should be using FMOD " + FMOD_VERSION);
			return;
		}
		if(fmod2.FSOUND_GetVersion() < FMOD_VERSION) {
			printExit("Error : You are using the wrong DLL version!  You should be using FMOD " + FMOD_VERSION);
			return;
		}

		switch (LibLoader.getPlatform()) {
			case LibLoader.PLATFORM_WINDOWS:
				fmod1.FSOUND_SetOutput(FSOUND_OUTPUT_DSOUND);
				fmod2.FSOUND_SetOutput(FSOUND_OUTPUT_DSOUND);
				break;
			case LibLoader.PLATFORM_LINUX:
				fmod1.FSOUND_SetOutput(FSOUND_OUTPUT_OSS);
				fmod2.FSOUND_SetOutput(FSOUND_OUTPUT_OSS);
				break;
			case LibLoader.PLATFORM_MACOSX:
				break;
		}

		/*
		 * SELECT DRIVER 1
		 */

		/*
		 * The following list are the drivers for the output method selected above.
		 */
		print("---------------------------------------------------------\n");
		print("Select soundcard #1\n");
		print("---------------------------------------------------------\n");

		for(int i = 0; i <= fmod1.FSOUND_GetNumDrivers() - 1; i++) {
			print(i + " - " + fmod1.FSOUND_GetDriverName(i) + "\n"); /* print driver names */
		}
		print("---------------------------------------------------------\n");

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

		fmod1.FSOUND_SetDriver(driver); /* Select sound card (0 = default) */

		print("\n");
		print("---------------------------------------------------------\n");
		print("Select soundcard #2\n");
		print("---------------------------------------------------------\n");

		for(int i = 0; i <= fmod2.FSOUND_GetNumDrivers() - 1; i++) {
			print(i + " - " + fmod2.FSOUND_GetDriverName(i) + "\n"); /* print driver names */
		}
		print("---------------------------------------------------------\n");

		driver = -1;
		while(driver < 0 || driver > Fmod.FSOUND_GetNumDrivers() - 1) {
			try {
				driver = Integer.parseInt("" + getKey());
			}
			catch(NumberFormatException e) {
				driver = -1;
			}
			Thread.yield();
		}

		fmod2.FSOUND_SetDriver(driver); /* Select sound card (0 = default) */

		/*
		 * INITIALIZE
		 */
		if(!fmod1.FSOUND_Init(44100, 32, FSOUND_INIT_USEDEFAULTMIDISYNTH)) {
			printExit(Fmod.FMOD_ErrorString(fmod1.FSOUND_GetError()));
			return;
		}

		/*
		 * INITIALIZE
		 */
		if(!fmod2.FSOUND_Init(44100, 32, FSOUND_INIT_USEDEFAULTMIDISYNTH)) {
			printExit(Fmod.FMOD_ErrorString(fmod2.FSOUND_GetError()));
			return;
		}

		/*
		 * LOAD SAMPLE (twice)
		 */
		sampBuff = Medias.loadMediaIntoMemory("/Media/drumloop.wav");
		samp1 = fmod1.FSOUND_Sample_Load(FSOUND_UNMANAGED, sampBuff, FSOUND_NORMAL | FSOUND_2D | FSOUND_LOADMEMORY, 0,
				sampBuff.capacity());
		if(samp1 == null) {
			printExit(Fmod.FMOD_ErrorString(fmod1.FSOUND_GetError()));
			return;
		}
		fmod1.FSOUND_Sample_SetMode(samp1, FSOUND_LOOP_OFF); /* this wav has loop points in it which turns looping on.. turn it off! */

		samp2 = fmod2.FSOUND_Sample_Load(FSOUND_UNMANAGED, sampBuff, FSOUND_NORMAL | FSOUND_2D | FSOUND_LOADMEMORY, 0,
				sampBuff.capacity());
		if(samp1 == null) {
			printExit(Fmod.FMOD_ErrorString(fmod2.FSOUND_GetError()));
			return;
		}
		fmod2.FSOUND_Sample_SetMode(samp2, FSOUND_LOOP_OFF); /* this wav has loop points in it which turns looping on.. turn it off! */

		/*
		 * DISPLAY HELP
		 */

		print("\n");
		print("=========================================================================\n");
		print("Press 1       Play 16bit sound on soundcard #1\n");
		print("      2       Play 16bit sound on soundcard #2\n");
		print("      e       Quit\n");
		print("=========================================================================\n");

		boolean exit = false;
		do {
			switch(getKey()) {
				case '1':
					fmod1.FSOUND_PlaySound(FSOUND_FREE, samp1);
					break;
				case '2':
					fmod2.FSOUND_PlaySound(FSOUND_FREE, samp2);
					break;
				case 'e':
					exit = true;
					break;
			}

			try {
				Thread.sleep(100);
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

		/*
		 * CLEANUP AND SHUTDOWN
		 */
		if(fmod1 != null && !fmod1.isNull()) {
			if(samp1 != null && !samp1.isNull()) {
				fmod1.FSOUND_Sample_Free(samp1);
			}
			print("Shutdown FMOD Instance 1\n");
			fmod1.FSOUND_Close();
			FmodDyn.FMOD_FreeInstance(fmod1);
		}
		if(fmod2 != null && !fmod2.isNull()) {
			if(samp2 != null && !samp2.isNull()) {
				fmod2.FSOUND_Sample_Free(samp2);
			}
			print("Shutdown FMOD Instance 2\n");
			fmod2.FSOUND_Close();
			FmodDyn.FMOD_FreeInstance(fmod2);
		}
		sampBuff = null;
	}
}
