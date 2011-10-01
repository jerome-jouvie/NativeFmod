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
 * This sample show you how to use FMUSIC_CALLBACK
 */

package org.jouvieje.Fmod.Extras;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Callbacks.FMUSIC_CALLBACK;
import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Structures.FMUSIC_MODULE;



public class MusicCallback implements VERSIONS
{
	public static void main(String[] args)
	{
		/*
		 * NativeFmod Init
		 */
		try
		{
			Init.loadLibraries();
		}
		catch(InitException e)
		{
			System.out.println("NativeFmod error! "+e.getMessage());
			System.exit(1);
		}
		
		/*
		 * Checking NativeFmodEx version
		 */
		if(NATIVEFMOD_LIBRARY_VERSION != NATIVEFMOD_JAR_VERSION)
		{
			System.out.println("Error!  NativeFmod library version ("+NATIVEFMOD_LIBRARY_VERSION+") is different to jar version ("+NATIVEFMOD_JAR_VERSION+")");
			System.exit(0);
		}
		
		/*==================================================*/
		
		/*
		 * Checking Fmod version
		 */
	    if(Fmod.FSOUND_GetVersion() < FMOD_VERSION)
	    {
	        System.out.println("Error : You are using the wrong DLL version!  You should be using FMOD "+FMOD_VERSION);
	        System.exit(1);
	    }
		
		System.out.println("MusicCallback example");
		System.out.println("");
		
		// ==========================================================================================
		// INITIALIZE FMOD
		// ==========================================================================================
		System.out.print("Initialize Fmod...");
		if(!Fmod.FSOUND_Init(44100, 32, 0))
		{
			System.err.println(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			System.exit(1);
		}
		System.out.println("OK");
		
		// ==========================================================================================
		// LOADING THE SEQUENCE
		// ==========================================================================================
		String sequenceName = "Media/invtro94.s3m";
		System.out.println("");
		System.out.print("Loading the sequence "+sequenceName);
		FMUSIC_MODULE sequence = Fmod.FMUSIC_LoadSong(sequenceName);
		if(sequence == null)
		{
			System.out.println("");
			System.err.println(sequenceName+" not found !");
			System.out.println("Closing Fmod...");
			Fmod.FSOUND_Close();
			System.out.println("Finish");
			System.exit(1);
		}
		System.out.println("");
		
		// ==========================================================================================
		// CALLBACKS
		// ==========================================================================================
		System.out.println("Creating and adding callbacks");
		FMUSIC_CALLBACK zxxCallback = new FMUSIC_CALLBACK()
		{
			public void FMUSIC_CALLBACK(FMUSIC_MODULE mod, short param)
			{
				System.out.println("ZxxCallback : param="+param);
			}
		};
		FMUSIC_CALLBACK orderCallback = new FMUSIC_CALLBACK()
		{
			public void FMUSIC_CALLBACK(FMUSIC_MODULE mod, short param)
			{
				System.out.println("OrderCallback : param="+param);
			}
		};
		FMUSIC_CALLBACK rowCallback = new FMUSIC_CALLBACK()
		{
			public void FMUSIC_CALLBACK(FMUSIC_MODULE mod, short param)
			{
				System.out.println("RowCallback : param="+param);
			}
		};
		FMUSIC_CALLBACK instCallback = new FMUSIC_CALLBACK()
		{
			public void FMUSIC_CALLBACK(FMUSIC_MODULE mod, short param)
			{
				System.out.println("InstCallback : param="+param);
			}
		};
		Fmod.FMUSIC_SetZxxCallback(sequence, zxxCallback);
		Fmod.FMUSIC_SetOrderCallback(sequence, orderCallback, 1);		//Call at each order
		Fmod.FMUSIC_SetRowCallback(sequence, rowCallback, 10);		//Call at each 10 row
		Fmod.FMUSIC_SetInstCallback(sequence, instCallback, 1);		//Call at each instrument
		
		// ==========================================================================================
		// PLAYING
		// ==========================================================================================
		System.out.println("");
		System.out.println("During the playing, press any key to quit.");
		Console.readString("Press a key to play the sequence ...");
		Fmod.FMUSIC_SetLooping(sequence, false);
		Fmod.FMUSIC_PlaySong(sequence);
		Console.readString();
		
		// ==========================================================================================
		// END
		// ==========================================================================================
		System.out.print("Close the sequence...");
		Fmod.FMUSIC_StopSong(sequence);
		Fmod.FMUSIC_FreeSong(sequence);
		System.out.println("Finish");
		System.out.print("Close Fmod...");
		Fmod.FSOUND_Close();
		System.out.println("Finish");
		System.exit(0);
	}
}