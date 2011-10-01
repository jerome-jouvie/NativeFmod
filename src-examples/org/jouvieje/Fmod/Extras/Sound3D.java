/**
 * Created on 10 jul. 2004
 * @author Jérôme JOUVIE (Jouvieje)
 * 
 * WANT TO CONTACT ME ?
 * @author Jérôme JOUVIE (Jouvieje)
 * @site   http://jerome.jouvie.free.fr/
 * @mail   jerome.jouvie@gmail.com
 * 
 * ABOUT
 * Use 3D sound
 */
package org.jouvieje.Fmod.Extras;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;

import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_MIXERTYPES;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Structures.FSOUND_SAMPLE;
import org.jouvieje.libloader.LibLoader;

public class Sound3D implements FSOUND_OUTPUTTYPES, FSOUND_MIXERTYPES, FSOUND_MODES, FSOUND_MISC_VALUES, VERSIONS {
	private static String musicName = "Media/drumloop.wav";
	private static FSOUND_SAMPLE sample;
	private static int channel = 1;

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
			System.out.println("Error!  NativeFmod library version (" + NATIVEFMOD_LIBRARY_VERSION
					+ ") is different to jar version (" + NATIVEFMOD_JAR_VERSION + ")");
			System.exit(0);
		}

		/*==================================================*/

		/*
		 * Checking Fmod version
		 */
		if(Fmod.FSOUND_GetVersion() < FMOD_VERSION) {
			System.out
					.println("Error : You are using the wrong DLL version!  You should be using FMOD " + FMOD_VERSION);
			System.exit(1);
	    }
		
		// ==========================================================================================
		// INITIALIIEZ FMOD
		// ==========================================================================================
		System.out.println("Select Output :");
		switch (LibLoader.getPlatform()) {
			case LibLoader.PLATFORM_WINDOWS:
				System.out.println("1 - Direct Sound (Recommended)");
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
		
		int output;
		do {
			output = Console.readInt("Your choive = ");
		} while(output < 1 || output > 4);

		switch (LibLoader.getPlatform()) {
			case LibLoader.PLATFORM_WINDOWS:
				switch(output) {
					case 1: Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_DSOUND); break;
					case 2: Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_WINMM); break;
					case 3: Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_ASIO); break;
				}
				break;
			case LibLoader.PLATFORM_LINUX:
				switch(output) {
					case 1: Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_OSS); break;
					case 2: Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_ESD); break;
					case 3: Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_ALSA); break; 
				}
				break;
			case LibLoader.PLATFORM_MACOSX:
				Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_MAC);
				break;
			default:
				Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_NOSOUND);
				break;
		}
		
		System.out.println("");
		System.out.print("Initialize Fmod...");
		Fmod.FSOUND_SetDriver(0);
		Fmod.FSOUND_SetMixer(FSOUND_MIXER_QUALITY_AUTODETECT);
		Fmod.FSOUND_Init(44100, 32, 0);
		Fmod.FSOUND_3D_SetDistanceFactor(1.0f);
		Fmod.FSOUND_3D_SetRolloffFactor(1.0f);
		System.out.println("Finish");
		
		// ==========================================================================================
		// LOAD AND PLAY MUSIC
		// ==========================================================================================
		System.out.println("Loading the music");
		sample = Fmod.FSOUND_Sample_Load(
			FSOUND_FREE,
			musicName,
			FSOUND_HW3D,
			0,
			0); 
		Fmod.FSOUND_Sample_SetMode(sample, FSOUND_LOOP_NORMAL);
		
		System.out.println("Playing the music");
		channel = Fmod.FSOUND_PlaySoundEx(FSOUND_FREE, sample, null, true);
		Fmod.FSOUND_3D_SetMinMaxDistance(channel, 1.0f, 1000f);
		Fmod.FSOUND_3D_SetAttributes(channel,
			new float[] {0.0f, 0.0f, 0.0f},
			new float[] {0.0f, 0.0f, 0.0f});
		Fmod.FSOUND_SetVolume(channel, 255);
		Fmod.FSOUND_SetPaused(channel, false);
		
		System.out.println();
		Console.readString("Press a key to continue...");
		loop1();
		
		setPosition(0.0f);
		System.out.println();
		Console.readString("Press a key to continue...");
		loop2();
		
		System.out.println("");
		System.out.print("Closing Fmod ...");
		Fmod.FSOUND_Sample_Free(sample);
		Fmod.FSOUND_Close();
		System.out.println("Finish");
	}
	
	private static void loop1() {
		int updates = 0;
		final int NB_UPDATES = 80;
		final int UPDATE_TIME = 100;
		
		float position = -5.0f;
		float lastPosition = 0;
		float velocity = 0;
		
		do {
			updates++;
			
			//increase deplacement
			position += 0.125f;
			//            deplacement in units     s / time in ms
			velocity = (position - lastPosition) * (1000 / UPDATE_TIME);
			//save ourposition
			lastPosition = position;
			
			System.out.println("Distance from the music = "+position);
			
			Fmod.FSOUND_3D_Listener_SetAttributes(
				new float[] {position, 0, 0},
				new float[] {velocity, 0, 0},
				0, 0, 1.0f,
				0, 1.0f, 0);
			Fmod.FSOUND_Update();
			
			try {
				Thread.sleep(UPDATE_TIME);
			} catch(Exception e){}
		}
		while(updates < NB_UPDATES);
	}
	
	private static void loop2() {
		boolean continuer = true;
		float position = 0.0f;

		while(continuer) {
			System.out.println("");
			System.out.println("Menu of choice :");
			System.out.println("1 : set distance from the music");
			System.out.println("2 : print distance");
			System.out.println("0 : exit");
			int choice = Console.readInt("Your choice : ");
			
			switch(choice) {
				case 0:
					continuer = false;
					break;
				case 1:
					try {
						position = Float.parseFloat(Console.readString("Position = "));
						setPosition(position);
					} catch(Exception e) {
						System.err.println("Wrong value !!!");
					}
					break;
				case 2 : printInformations(); break;
			}
		}
		
	}
	
	/**
	 * Set our position
	 */
	private static void setPosition(float position) {
		float[] pos = new float[] {position, 0.0f, 0.0f};
		float[] vel = new float[] {position, 0.0f, 0.0f};
		
		//update our position (ears position)
		Fmod.FSOUND_3D_Listener_SetAttributes(
			pos, vel,
			0.0f, 0.0f, 1.0f,
			0.0f, 1.0f, 0.0f);
		//need to update 3d engine
		Fmod.FSOUND_Update();
	}
	
	/**
	 * Print our distance from the music
	 */
	private static void printInformations() {
		float[] jPosition = new float[3];
		Fmod.FSOUND_3D_Listener_GetAttributes(jPosition, new float[3],
			new float[1], new float[1], new float[1],
			new float[1], new float[1], new float[1]);
		
		System.out.print("Distance from the music = "+jPosition[0]+"\r");
	}
}