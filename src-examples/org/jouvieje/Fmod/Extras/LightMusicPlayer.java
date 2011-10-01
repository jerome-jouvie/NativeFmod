/**
 * 							Light music player
 * 
 * Created on 5 mai. 2004
 * @author Jérôme JOUVIE (Jouvieje)
 * 
 * WANT TO CONTACT ME ?
 * @author Jérôme JOUVIE (Jouvieje)
 * @site   http://jerome.jouvie.free.fr/
 * @mail   jerome.jouvie@gmail.com
 * 
 * ABOUT
 * This player show how to use the Fmod and NativeFmod.
 */

package org.jouvieje.Fmod.Extras;

import java.nio.FloatBuffer;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Structures.FMUSIC_MODULE;
import org.jouvieje.Fmod.Structures.FSOUND_SAMPLE;
import org.jouvieje.Fmod.Structures.FSOUND_STREAM;

public class LightMusicPlayer implements VERSIONS {
	public static void main(String[] args) {
		try {
			LightMusicPlayer.run();
		} catch(Exception e) {
			System.err.println("AN EXCEPTION IS OCCURED IN THE PLAYER !!!");
			System.exit(1);
		}
		
		System.out.println("");
		Console.readString("Press a key to exit...");
		System.exit(0);
	}
	
	//these object store a pointer to the musics that are loaded
	static FMUSIC_MODULE sequence = null;
	static FSOUND_STREAM stream = null;
	static FSOUND_SAMPLE sample = null;
	//file name to the music
	static String sequenceName = "Media/canyon.mid";
	static String sampleName = "Media/drumloop.wav";
	static String streamName = "Media/jules.mp3";
	
	//to know if an audio file is loaded
	static boolean sequenceLoaded = false;
	static boolean sampleLoaded = false;
	static boolean streamLoaded = false;
	
	//to know if the audio cd is stop or playing
	static boolean cdStopped = true;
	static char CDDriveID = 0;
	
	/**
	 * Call this method to run the player
	 */
	public static void run() {
		System.out.println("***********************************************************");
		System.out.println("*      Console Music Player with Java and the Fmod API    *");
		System.out.println("***********************************************************");
		System.out.println("* Program made by Jouvieje                                *");
		System.out.println("* E-Mail : jerome.jouvie@gmail.com                        *");
		System.out.println("*                                                         *");
		System.out.println("* It can play all music files :                           *");
		System.out.println("* *.MID, *.WAV, *.MP2, *.MP3, *.OGG, *.RAW, *.MOD,        *");
		System.out.println("* *.S3M, *.XM, *.IT ...                                   *");
		System.out.println("*                                                         *");
		System.out.println("* This programm is just a console player demo. To use it, *");
		System.out.println("* you have to copy these files in the Media directory :   *");
		System.out.println("*    stream.mp3                                           *");
		System.out.println("*    sample.wav                                           *");
		System.out.println("*    sequence.mid                                         *");
		System.out.println("*                                                         *");
		System.out.println("* To use the Fmod API (www.fmod.org), I've created a full *");
		System.out.println("* wrapped of this API. I named this wrapped: NativeFmod   *");
		System.out.println("* Contact me to obtained it         :-)                   *");
		System.out.println("***********************************************************");
		
		Console.readString("\nPress a key to initialise the Fmod API...");
		initFmod();
		
		Console.readString("\nPress a key to go to run the player...");
		menuPlayer();
		
		System.out.println("\nClosing the player...");
		close();
	}
	
	/**
	 * Initialise the Fmod API
	 */
	private static void initFmod()
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
		
		System.out.println("\nInitialization of Fmod & NativeFmod : ");
		System.out.print("NativeFmod Jar version...");
		System.out.println(NATIVEFMOD_JAR_VERSION);
		System.out.print("NativeFmod Library version...");
		System.out.println(NATIVEFMOD_LIBRARY_VERSION);
		System.out.print("Fmod Library version...");
		System.out.println(Fmod.FSOUND_GetVersion());
		System.out.print("Fmod version required ...");
		System.out.println(FMOD_VERSION);
		System.out.print("Fmod initialisation...");
		boolean initialise = Fmod.FSOUND_Init(44100, 32, 0);
		if(initialise)
			System.out.println("OK");
		else
			System.err.println("FAILED");
	}
	
	/**
	 * Close the player
	 * 
	 * Close all song loaded, the audio cd playing and the Fmod API
	 */
	public static void close()
	{
		System.out.println();
		//close music loaded
		closeMusic();
		
		//close the audio cd
		closeCd();
		
		System.out.print("Closing Fmod...");
		Fmod.FSOUND_Close();
		System.out.println("OK");
	}
	
	private static void closeMusic()
	{
		System.out.print("Closing music loaded...");
		if(sequenceLoaded)
		{
			sequenceLoaded = !Fmod.FMUSIC_FreeSong(sequence); 
		}
		if(sampleLoaded)
		{
			Fmod.FSOUND_Sample_Free(sample); 
			sampleLoaded = false;
		}
		if(streamLoaded)
		{
			Fmod.FSOUND_Stream_Stop(stream);
			streamLoaded = !Fmod.FSOUND_Stream_Close(stream);
		}
		if(!sequenceLoaded && !sampleLoaded && !streamLoaded)
			System.out.println("OK");
		else
			System.err.println("FAILED");
	}
	private static void closeCd()
	{
		System.out.print("Closing the audio CD...");
		if(!cdStopped)
			cdStopped = Fmod.FSOUND_CD_Stop(CDDriveID);
		if(cdStopped)
			System.out.println("OK");
		else
			System.err.println("FAILED");
	}
	
	private static void menuPlayer()
	{
		int choix = 0;
		do
		{
			System.out.println("\nMenu of the choices :");
			System.out.println(" 1 to play a music from the Hard Disc");
			System.out.println(" 2 to play a music from the CD Drive");
			System.out.println(" 0 to quit");
			choix = Console.readInt("Your choice = ");
			
			switch(choix)
			{
				case 1: menuHardDrive(); break;
				case 2: menuCdDrive(); break;
			}
		}
		while(choix != 0);
	}
	
	/**
	 * Menu to play music from the Hard Drive
	 */
	private static void menuHardDrive()
		{
			/**
			 * a sequence is a file like : mid, s3m ...
			 * a sample is a small stream song like song effect
			 * a stream is a file like : mp3, wav, ogg...(that is to say your music file)
			 */
			
			int choix = 0;
			do
			{
				System.out.println("\nMenu of the choices :");
				System.out.println(" 0 to return to the first menu");
				System.out.println(" 1 open a Stream -- 2 pause/play it -- 3 close it     (Media/jules.mp3)");
				System.out.println(" 4 open a Sample -- 5 pause/play it -- 6 close it     (Media/drumloop.wav)");
				System.out.println(" 7 open  a Sequence -- 8 pause/play it -- 9 close it  (Media/canyon.mid");
				System.out.println("\n 20 to have informations about the stream channel.");
				System.out.println(" 21 play the sample in loop (use this after beginning playing the sample).");
				System.out.println(" 22 mute the stream");
				System.out.println(" 23 active Spectrum");
				System.out.println(" 24 draw Spectrum");
				choix = Console.readInt("Your choice = ");
				
				switch(choix)
				{
					case 1: streamLoaded = true;
							stream = Fmod.FSOUND_Stream_Open(streamName, Fmod.FSOUND_NORMAL, 0, 0);
							Fmod.FSOUND_Stream_Play(1, stream); break;
					case 2: if(streamLoaded)Fmod.FSOUND_SetPaused(1, !Fmod.FSOUND_GetPaused(1)); break;
					case 3: streamLoaded = false; Fmod.FSOUND_Stream_Stop(stream); break;
			
					case 4: sampleLoaded = true;
							sample = Fmod.FSOUND_Sample_Load(0, sampleName, Fmod.FSOUND_NORMAL, 0, 0);
							Fmod.FSOUND_PlaySound(0, sample);
							break;
					case 5: if(sampleLoaded)Fmod.FSOUND_SetPaused(0, !Fmod.FSOUND_GetPaused(0)); break;
					case 6: sampleLoaded = false; Fmod.FSOUND_Sample_Free(sample); break;
					
					case 7: sequenceLoaded = true;
							sequence = Fmod.FMUSIC_LoadSong(sequenceName);
							Fmod.FMUSIC_SetLooping(sequence, false);
							Fmod.FMUSIC_PlaySong(sequence);
							break;
					case 8: if(sequenceLoaded)Fmod.FMUSIC_SetPaused(sequence, !Fmod.FMUSIC_GetPaused(sequence)); break;
					case 9: sequenceLoaded = false; Fmod.FMUSIC_StopSong(sequence); Fmod.FMUSIC_FreeSong(sequence); break;
					
					case 21: Fmod.FSOUND_SetLoopMode(0, Fmod.FSOUND_LOOP_NORMAL);
							 System.out.println("Wait until the music finihing to see if it replay or not!!!"); break;
					case 22: Fmod.FSOUND_SetMute(1, !Fmod.FSOUND_GetMute(1));
							 System.out.println("Wait until the music finihing to see if it replay or not!!!"); break;
					
					case 20: System.out.println("\n\nCPU Usage = "+Fmod.FSOUND_GetCPUUsage());
							System.out.println("Stream information :");
							System.out.println("Stream length (ms) : "+(Fmod.FSOUND_Stream_GetLengthMs(stream)));
							System.out.println("Stream position (ms) : "+(Fmod.FSOUND_Stream_GetTime(stream)));
							System.out.println("Stream length (byte) : "+(Fmod.FSOUND_Stream_GetLength(stream)));
							System.out.println("Stream position (byte) : "+(Fmod.FSOUND_Stream_GetPosition(stream)));
							System.out.println("Stream current position: "+(Fmod.FSOUND_GetCurrentPosition(1)));
							System.out.println("Stream is playing : "+(Fmod.FSOUND_IsPlaying(1)));
							System.out.println("Stream playing loop mode : "+(Fmod.FSOUND_GetLoopMode(1)+"\n"));
							Console.readString("\nAppuyer pour continuer..."); break;
					case 23:Fmod.FSOUND_DSP_SetActive(Fmod.FSOUND_DSP_GetFFTUnit(), true); break;
					case 24:afficheSpectre(Fmod.FSOUND_DSP_GetSpectrum());
							break;
				}
			}
			while(choix != 0);
			
			System.out.println();
			closeMusic();
		}

	private static void afficheSpectre(FloatBuffer buffer)
	{
		try
		{
			System.out.println(buffer);
			System.out.println("\nFirst 10 values of (512 values of) the spectrum :");
			for(int i = 0; i <= 9; i++)
			{
				System.out.println("Spectre value"+(i+1)+" : "+buffer.get());
			}
			buffer.rewind();
		}
		catch(Exception e){System.out.println("Null pointer exception");}
		Console.readString();
	}
	
	
	private static void menuCdDrive()
	{
		Fmod.FSOUND_CD_SetPlayMode(CDDriveID, (byte)Fmod.FSOUND_CD_PLAYCONTINUOUS);
		
		int choix = 0;
		do
		{
			System.out.println("\nMenu of the choices :");
			System.out.println(" 0 to return to the first menu");
			System.out.println(" 1 to select a CD-Rom Drive");
			System.out.println(" 2 to open the CD-Rom Tray (22 : close it)");
			System.out.println(" 3 to play the CD");
			System.out.println(" 4 to stop the CD");
			System.out.println(" 5 to pause the music");
			System.out.println(" 6 play all musics in loop");
			System.out.println(" 7 play the music random");
			System.out.println(" 8 decrease the volume level");
			System.out.println(" 9 increase the volume level");
			System.out.println(" 10 to have informations about the CD");
			choix = Console.readInt("Your choice = ");
			
			switch(choix)
			{
				//select a cd drive
				case 1: CDDriveID = selectCDRomDrive(); break;
				//eject the cd (if one is present in the cd drive)
				case 2: Fmod.FSOUND_CD_OpenTray(CDDriveID, true); break;
				//close the cd (if one is present in the cd drive)
				case 22: Fmod.FSOUND_CD_OpenTray(CDDriveID, false); break;
				//play the audio cd (if one is include in the current selected cd drive)
				case 3: System.out.print("\nWait while loading the audio cd...");
						boolean findCd = Fmod.FSOUND_CD_Play(CDDriveID, 1);
						cdStopped = false;
						if(findCd)
							System.out.println("\nAn audio Cd is found.");
						else
							System.err.println("\nAny audio Cd are found!\nTry to select the right cd drive (Key 1 in the menu)"); break;
				//stop the current audio cd playing
				case 4: Fmod.FSOUND_CD_Stop(CDDriveID); cdStopped = true; break;
				//pause the cd
				case 5: Fmod.FSOUND_CD_SetPaused(CDDriveID, !Fmod.FSOUND_CD_GetPaused(CDDriveID)); break;
				//select a mode of playing
				case 6: Fmod.FSOUND_CD_SetPlayMode(CDDriveID, (byte)Fmod.FSOUND_CD_PLAYCONTINUOUS); break;
				case 7: Fmod.FSOUND_CD_SetPlayMode(CDDriveID, (byte)Fmod.FSOUND_CD_PLAYRANDOM); break;
				//define the music volume from the cd drive
				case 8: Fmod.FSOUND_CD_SetVolume(CDDriveID, Fmod.FSOUND_CD_GetVolume(CDDriveID)-10); break;
				case 9: Fmod.FSOUND_CD_SetVolume(CDDriveID, Fmod.FSOUND_CD_GetVolume(CDDriveID)+10); break;
				case 10: informationAboutCD(CDDriveID); break;
			}
		}
		while(choix != 0);
		
		System.out.println();
		closeCd();
	}
	
	/**
	 * Display on screen some informations about the audio cd playing
	 */
	private static void informationAboutCD(char CDDriveID)
	{
		System.out.println("");
		System.out.println("Current music playing : "+Fmod.FSOUND_CD_GetTrack(CDDriveID));
		System.out.println("Number of music in the current CD = "+Fmod.FSOUND_CD_GetNumTracks(CDDriveID));
		System.out.println("Length of the current music = "+Fmod.FSOUND_CD_GetTrackLength(CDDriveID, Fmod.FSOUND_CD_GetTrack(CDDriveID)));
		System.out.println("Position in the current music = "+Fmod.FSOUND_CD_GetTrackTime(CDDriveID));
		System.out.println("Volume (Min=0 and Max=255) = "+Fmod.FSOUND_CD_GetVolume(CDDriveID));
		Console.readString("\nPress a key to continue...");
	}
	
	/**
	 * Select the CD Rom player containing the audio cd to play
	 * 0: default CD ROM player
	 */
	private static char selectCDRomDrive()
	{
		char CDDriveID = 0;
		boolean valide = false;
		do
		{
			try
			{
				System.out.println("\nSelect the CD-Rom ID (ex: D)");
				System.out.println(" (0 for the default CD-Rom)");
				CDDriveID = Console.readString("Lecteur ID : ").charAt(0);
				valide = true;
			}
			catch(Exception e)
			{
				System.err.println("\nWrong ID!");
				valide = false;
			}
		}
		while(!valide);
		
		return CDDriveID;
	}
}