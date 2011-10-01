/**
 * Created on 15 jul. 2004
 * @author Jérôme JOUVIE (Jouvieje)
 * 
 * WANT TO CONTACT ME ?
 * @author Jérôme JOUVIE (Jouvieje)
 * @site   http://jerome.jouvie.free.fr/
 * @mail   jerome.jouvie@gmail.com
 * 
 * ABOUT
 * Class that shows the initialization of Fmod : Output, Mixer, Driver and Fmod Init
 */

package org.jouvieje.Fmod.Extras;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Defines.FSOUND_CAPS;

import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_MIXERTYPES;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.libloader.LibLoader;

public class OutputDriverMixerInit implements FSOUND_OUTPUTTYPES, FSOUND_CAPS, FSOUND_MIXERTYPES, VERSIONS {
	static boolean result = true;
	static int choice = -1;
	
	public static void main(String[] args) {
		/*
		 * NativeFmod Init
		 */
		try {
			Init.loadLibraries();
		}
		catch(InitException e) {
			System.out.println("NativeFmod error! " + e.getMessage());
			System.exit(1);
		}

		/*
		 * Checking NativeFmodEx version
		 */
		if(NATIVEFMOD_LIBRARY_VERSION != NATIVEFMOD_JAR_VERSION) {
			System.out.println("Error!  NativeFmod library version ("+NATIVEFMOD_LIBRARY_VERSION+") is different to jar version ("+NATIVEFMOD_JAR_VERSION+")");
			System.exit(0);
		}
		
		/*==================================================*/
		
		/*
		 * Checking Fmod version
		 */
		if(Fmod.FSOUND_GetVersion() < FMOD_VERSION) {
	        System.out.println("Error : You are using the wrong DLL version!  You should be using FMOD "+FMOD_VERSION);
	        System.exit(1);
	    }
		
		System.out.println("This sample will show you how to initialize Fmod.");
		Console.readString("Press a key to continue...");
		
		// ==========================================================================================
		// SELECT THE OUTPUT
		// ==========================================================================================
		System.out.println("");
		System.out.println("Select Output :");
		//Print available outputs
		switch (LibLoader.getPlatform()) {
			case LibLoader.PLATFORM_WINDOWS:
				System.out.println("1 - Direct Sound");
				System.out.println("2 - Windows Multimedia Waveout");
				System.out.println("3 - ASIO");
				break;
			case LibLoader.PLATFORM_LINUX:
				System.out.println("1 - OSS - Open Sound System");
				System.out.println("2 - ESD - Elightment Sound Daemon");
				System.out.println("3 - ALSA 0.9 - Advanced Linux Sound Architecture");  
				break;
			case LibLoader.PLATFORM_MACOSX:
				System.out.println("1 - Mac SoundManager");
				break;
			default:
				System.out.println("1 - NoSound");
				break;
		}
		
		//Read the choice of the user
		do {
			choice = Console.readInt("Your choive = ");
		} while(choice < 1 || choice > 4);
		
		//Select the output
		switch (LibLoader.getPlatform()) {
			case LibLoader.PLATFORM_WINDOWS:
				switch(choice) {
					case 1: result = Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_DSOUND); break;
					case 2: result = Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_WINMM); break;
					case 3: result = Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_ASIO); break;
				}
				break;
			case LibLoader.PLATFORM_LINUX:
				switch(choice) {
					case 1: result = Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_OSS); break;
					case 2: result = Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_ESD); break;
					case 3: result = Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_ALSA); break; 
				}
				break;
			case LibLoader.PLATFORM_MACOSX:
				result = Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_MAC);
				break;
			default:
				result = Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_NOSOUND);
				break;
		}
		
		//Check
		if(!result) {
			System.err.println("Output fails : "+Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			System.exit(1);
		}
		System.out.println("Output selected = "+getOutput());
		Console.readString("Press ENTER to continue...");
		
		// ==========================================================================================
		// SELECT DRIVER
		// ==========================================================================================
		System.out.println("");
		System.out.println(getOutput()+" Driver list :");
		//Print driver information list
		for(int i = 0; i <= Fmod.FSOUND_GetNumDrivers() - 1; i++) {
			System.out.println(i+" - "+Fmod.FSOUND_GetDriverName(i));
			
			int[] caps = new int[1];
			Fmod.FSOUND_GetDriverCaps(i, caps);
			
			if((caps[0] & FSOUND_CAPS_HARDWARE) != 0)
				System.out.println("  * Driver supports hardware 3D sound!");
			if((caps[0] & FSOUND_CAPS_EAX2) != 0)
				System.out.println("  * Driver supports EAX 2 reverb!");
			if((caps[0] & FSOUND_CAPS_EAX3) != 0)
				System.out.println("  * Driver supports EAX 3 reverb!");
		}
		
		do {
			choice = Console.readInt("Your choice = ");
		} while(choice < 0 || choice > Fmod.FSOUND_GetNumDrivers()-1);
		
		//Select sound card (0 = default)
		if(!Fmod.FSOUND_SetDriver(choice)) {
			System.err.println("Driver fails : "+Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			System.exit(1);
		}
		
		System.out.println("");
		System.out.println("Driver = "+Fmod.FSOUND_GetDriverName(Fmod.FSOUND_GetDriver()));
		System.out.println("Driver capabilities :");
		
		int[] caps = new int[1];
		Fmod.FSOUND_GetDriverCaps(Fmod.FSOUND_GetDriver(), caps);
		if(caps[0] == 0)
			System.out.println(" -> This driver will support software mode only. It does not properly support 3D sound hardware.");
		if((caps[0] & FSOUND_CAPS_HARDWARE) != 0)
			System.out.println(" -> Driver supports hardware 3D sound!");
		if((caps[0] & FSOUND_CAPS_EAX2) != 0)
			System.out.println(" -> Driver supports EAX 2 reverb!");
		if((caps[0] & FSOUND_CAPS_EAX3) != 0)
			System.out.println(" -> Driver supports EAX 3 reverb!");
		
		System.out.println("");
		Console.readString("Press a key to continue...");
		
		// ==========================================================================================
		// SELECT MIXER
		// ==========================================================================================
		System.out.println("");
		System.out.println("Choose a Mixer :");
		System.out.println("1 - FSOUND_MIXER_QUALITY_AUTODETECT (Mixer will be automaticly choosen by Fmod when the call FSOUND_Init)");
		System.out.println("2 - FSOUND_MIXER_QUALITY_FPU");
		System.out.println("3 - FSOUND_MIXER_QUALITY_MMXP5");
		System.out.println("4 - FSOUND_MIXER_QUALITY_MMXP6");
		do {
			choice = Console.readInt("Your choive = ");
		} while(choice < 1 || choice > 5);

		switch(choice) {
			case 1: result = Fmod.FSOUND_SetMixer(FSOUND_MIXER_QUALITY_AUTODETECT); break;
			case 2: result = Fmod.FSOUND_SetMixer(FSOUND_MIXER_QUALITY_FPU); break;
			case 3: result = Fmod.FSOUND_SetMixer(FSOUND_MIXER_QUALITY_MMXP5); break;
			case 4: result = Fmod.FSOUND_SetMixer(FSOUND_MIXER_QUALITY_MMXP6); break;
		}
		if(!result) {
			System.err.println("Mixer fail : "+Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			System.exit(1);
		}
		System.out.println("Mixer selected = "+getMixer());
		System.out.println("");
		Console.readString("Press a key to continue...");
		
		// ==========================================================================================
		// INITIALIZE FMOD
		// ==========================================================================================
		System.out.println("");
		System.out.print("Initialization of Fmod...");
		if(!Fmod.FSOUND_Init(44100, 32, 0)) {
			System.err.println(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			System.exit(1);
		}
		System.out.println("OK");
		System.out.println("Output : "+getOutput());
		System.out.println("Driver : "+Fmod.FSOUND_GetDriverName(Fmod.FSOUND_GetDriver()));
		System.out.println("Mixer : "+getMixer());
		
		// ==========================================================================================
		// CLOSE FMOD
		// ==========================================================================================
		System.out.println("");
		System.out.print("Closing Fmod...");
		Fmod.FSOUND_Close();
		System.out.println("OK");
	}
	
	/**
	 * @return
	 */
	private static String getOutput() {
		int output = Fmod.FSOUND_GetOutput();
		if(output == FSOUND_OUTPUT_NOSOUND)
			return "NoSound (FSOUND_OUTPUT_NOSOUND)";
		else if(output == FSOUND_OUTPUT_WINMM)
			return "Windows Multimedia Waveout (FSOUND_OUTPUT_WINMM)";
		else if(output == FSOUND_OUTPUT_DSOUND)
			return "Direct Sound (FSOUND_OUTPUT_DSOUND)";
		else if(output == FSOUND_OUTPUT_ASIO)
			return "ASIO (FSOUND_OUTPUT_ASIO)";
		else if(output == FSOUND_OUTPUT_OSS)
			return "Open Sound System (FSOUND_OUTPUT_OSS)";
		else if(output == FSOUND_OUTPUT_ESD)
			return "Enlightment Sound Daemon (FSOUND_OUTPUT_ESD)";
		else if(output == FSOUND_OUTPUT_ALSA)
			return "Advanced Linux Sound Architecture (FSOUND_OUTPUT_ALSA)";
		else if(output == FSOUND_OUTPUT_MAC)
			return "Mac SoundManager (FSOUND_OUTPUT_MAC)";
		else
			return "ERROR : invalid output = "+output;
	}
	
	private static String getMixer() {
		int mixer = Fmod.FSOUND_GetMixer();
		if(mixer == FSOUND_MIXER_QUALITY_FPU)
			return "FSOUND_MIXER_QUALITY_FPU";
		else if(mixer == FSOUND_MIXER_QUALITY_MMXP5)
			return "FSOUND_MIXER_QUALITY_MMXP5";
		else if(mixer == FSOUND_MIXER_QUALITY_MMXP6)
			return "FSOUND_MIXER_QUALITY_MMXP6";
		else if(mixer == FSOUND_MIXER_QUALITY_AUTODETECT)
			return "FSOUND_MIXER_QUALITY_AUTODETECT";
		else if(mixer == FSOUND_MIXER_MAX)
			return "FSOUND_MIXER_MAX";
		else
			return "ERROR : invalid mixer = "+mixer;
	}
}