/**
 * Created on 8 jul. 2004
 * @author Jérôme JOUVIE (Jouvieje)
 * 
 * WANT TO CONTACT ME ?
 * @author Jérôme JOUVIE (Jouvieje)
 * @site   http://jerome.jouvie.free.fr/
 * @mail   jerome.jouvie@gmail.com
 * 
 * ABOUT
 * This sample show you how to use FSOUND_STREAMCALLBACK
 */

package org.jouvieje.Fmod.Extras;

import java.nio.ByteBuffer;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Callbacks.FSOUND_STREAMCALLBACK;
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;

import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Misc.Pointer;
import org.jouvieje.Fmod.Structures.FSOUND_STREAM;
import org.jouvieje.libloader.LibLoader;

public class StreamCallback implements FSOUND_OUTPUTTYPES, FSOUND_MISC_VALUES, FSOUND_MODES, VERSIONS {
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
	        System.out.println("Error : You are using the wrong DLL version!  You should be using FMOD "+FMOD_VERSION);
	        System.exit(1);
	    }
		
		System.out.println("StreamCallback example");
		System.out.println("");
		
		// ==========================================================================================
		// SELECT OUPUT
		// ==========================================================================================
		System.out.println("Select Output :");
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
		
		// ==========================================================================================
		// INITIALIZE Fmod
		// ==========================================================================================
		System.out.print("Initialize Fmod...");
		if(!Fmod.FSOUND_Init(44100, 32, 0))
		{
			System.err.println(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			System.exit(1);
		}
		System.out.println("OK");
		
		// ==========================================================================================
		// OPEN THE STREAM
		// ==========================================================================================
		String streamName = "Media/drumloop.wav";
		System.out.println("");
		System.out.println("Opening the stream "+streamName);
		FSOUND_STREAM stream = Fmod.FSOUND_Stream_Open(streamName, FSOUND_NORMAL, 0, 0);
		if(stream == null)
		{
			System.err.println(streamName+" not found !");
			System.out.print("Closing Fmod...");
			Fmod.FSOUND_Close();
			System.out.println("Finish");
			System.exit(0);
		}
		
		// ==========================================================================================
		// ADDING CALLBACKS & PLAYING
		// ==========================================================================================
		System.out.println("Adding end callback to the stream");
		FSOUND_STREAMCALLBACK endCallback = new FSOUND_STREAMCALLBACK() {
			public boolean FSOUND_STREAMCALLBACK(FSOUND_STREAM stream, ByteBuffer buff, int len, Pointer userdata)
			{
				System.out.print("End of the stream. Press any key to exit ...");
				return true;
			}
		};
		Fmod.FSOUND_Stream_SetEndCallback(stream, endCallback, null);
		System.out.println("Playing the stream");
		Fmod.FSOUND_Stream_Play(FSOUND_FREE, stream);
		
		System.out.println("");
		
		Console.readString("Wait while the stream is playing...\n");
		System.out.println("");
		
		// ==========================================================================================
		// END
		// ==========================================================================================
		System.out.print("Close the music...");
		Fmod.FSOUND_Stream_Stop(stream);
		Fmod.FSOUND_Stream_Close(stream);
		System.out.println("Finish");
		System.out.print("Close Fmod...");
		Fmod.FSOUND_Close();
		System.out.println("Finish");
		System.exit(0);
	}
}