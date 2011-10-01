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
 * This sample was used to verify the works of FSOUND_REVERB_PRESETS
 */

package org.jouvieje.Fmod.Extras;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;
import org.jouvieje.Fmod.Defines.FSOUND_REVERB_PRESETS;
import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Structures.FSOUND_REVERB_PROPERTIES;
import org.jouvieje.Fmod.Structures.FSOUND_STREAM;



public class FsoundReverbPresets implements FSOUND_MODES, FSOUND_MISC_VALUES, FSOUND_OUTPUTTYPES, FSOUND_REVERB_PRESETS, VERSIONS
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
		
		System.out.println("FsoundReverbPresets example");
		System.out.println("Your sound card must support EAX. If not, reverb can't be used.");
		System.out.println("");
		
		// ==========================================================================================
		// INITIALIZE FMOD
		// ==========================================================================================
		System.out.print("Initialise Fmod...");
		Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_DSOUND);			//Select direct sound because we use reverb
		Fmod.FSOUND_Init(44100, 32, 0);
		System.out.println("Finish");
		System.out.println("");
		
		// ==========================================================================================
		// CREATING FSOUND_REVERB_PROPERTIES
		// ==========================================================================================
		System.out.println("Creating a FSOUND_REVERB_PROPERTIES using City presets");
		FSOUND_REVERB_PROPERTIES cityPresets = FSOUND_REVERB_PROPERTIES.create(FSOUND_PRESET_CITY);
		
		Console.readString("Press a key to show City presets properties :");
		System.out.println("AirAbsorptionHF = "+cityPresets.getAirAbsorptionHF());
		System.out.println("DecayHFRatio = "+cityPresets.getDecayHFRatio());
		System.out.println("DecayLFRatio = "+cityPresets.getDecayLFRatio());
		System.out.println("DecayTime = "+cityPresets.getDecayTime());
		System.out.println("Density = "+cityPresets.getDensity());
		System.out.println("Diffusion = "+cityPresets.getDiffusion());
		System.out.println("EchoDepth = "+cityPresets.getEchoDepth());
		System.out.println("EchoTime = "+cityPresets.getEchoTime());
		System.out.println("EnvDiffusion = "+cityPresets.getEnvDiffusion());
		System.out.println("Environment"+cityPresets.getEnvironment());
		System.out.println("EnvSize = "+cityPresets.getEnvSize());
		System.out.println("Flags = "+cityPresets.getFlags());
		System.out.println("HFReference = "+cityPresets.getHFReference());
		System.out.println("LFReference = "+cityPresets.getLFReference());
		System.out.println("ModulationDepth = "+cityPresets.getModulationDepth());
		System.out.println("ModulationTime = "+cityPresets.getModulationTime());
		System.out.println("Reflections = "+cityPresets.getReflections());
		System.out.println("ReflectionsDelay = "+cityPresets.getReflectionsDelay());
		System.out.println("Reverb = "+cityPresets.getReverb());
		System.out.println("ReverbDelay = "+cityPresets.getReverbDelay());
		System.out.println("Room = "+cityPresets.getRoom());
		System.out.println("RoomHF = "+cityPresets.getRoomHF());
		System.out.println("RoomLF = "+cityPresets.getRoomLF());
		System.out.println("RoomRolloffFactor = "+cityPresets.getRoomRolloffFactor());
		System.out.println("");
		
		/**
		 * Other presets
		 */
		FSOUND_REVERB_PROPERTIES presetOff = FSOUND_REVERB_PROPERTIES.create(FSOUND_PRESET_OFF);
		FSOUND_REVERB_PROPERTIES concertHall = FSOUND_REVERB_PROPERTIES.create(FSOUND_PRESET_CONCERTHALL);
		FSOUND_REVERB_PROPERTIES sewerPipe = FSOUND_REVERB_PROPERTIES.create(FSOUND_PRESET_SEWERPIPE);
		FSOUND_REVERB_PROPERTIES psychotic = FSOUND_REVERB_PROPERTIES.create(FSOUND_PRESET_PSYCHOTIC);
		
		// ==========================================================================================
		// OPEN AND PLAY THE STREAM
		// ==========================================================================================
		String streamName = "Media/drumloop.wav";
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
		System.out.println("Playing the stream ");
		Fmod.FSOUND_Stream_SetMode(stream, Fmod.FSOUND_LOOP_NORMAL);
		Fmod.FSOUND_Stream_Play(FSOUND_FREE, stream);
		System.out.println("");
		
		// ==========================================================================================
		// SELECT A PRESET
		// ==========================================================================================
		/*
		 * NOTE
		 * My sound card does not support EAX so reverb do not works.
		 * Tell me if presets have an effect on the must.
		 */
		char key = ' ';
		System.out.println("Press a key from 0 to 4 to select a preset.");
		System.out.println("Press E to quit.");
		do
		{
			String choice = Console.readString("");
			if(choice.length() >= 1)
				key = choice.charAt(0);
			if(key == '0')
			{
				System.out.println("Preset Off");
				Fmod.FSOUND_Reverb_SetProperties(presetOff);
			}
			else if(key == '1')
			{
				System.out.println("City Presets");
				Fmod.FSOUND_Reverb_SetProperties(cityPresets);
			}
			else if(key == '2')
			{
				System.out.println("Concert Hall");
				Fmod.FSOUND_Reverb_SetProperties(concertHall);
			}
			else if(key == '3')
			{
				System.out.println("Sewer Pipe");
				Fmod.FSOUND_Reverb_SetProperties(sewerPipe);
			}
			else if(key == '4')
			{
				System.out.println("Psychotic");
				Fmod.FSOUND_Reverb_SetProperties(psychotic);
			}
		}
		while(key != 'e');

		System.out.println("");
		Console.readString("Press a key to quit ...");
		System.out.println("");
		
		// ==========================================================================================
		// END
		// ==========================================================================================
		System.out.print("Closing the stream...");
		Fmod.FSOUND_Stream_Stop(stream);
		Fmod.FSOUND_Stream_Close(stream);
		System.out.println("Finish");
		System.out.print("Deleting presets...");
		presetOff.release();
		cityPresets.release();
		concertHall.release();
		sewerPipe.release();
		psychotic.release();
		System.out.println("Finish");
		System.out.print("Closing Fmod...");
		Fmod.FSOUND_Close();
		System.out.println("Finish");
		System.exit(0);	}
}