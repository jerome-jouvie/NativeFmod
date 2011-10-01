/*===============================================================================================
 SIMPLEST.EXE
 Copyright (c), Firelight Technologies Pty, Ltd, 1999,2000.

 This is the simplest way to play a song through FMOD.  It is basically Init, Load, Play!
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
import org.jouvieje.Fmod.Defines.FSOUND_MODES;
import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Examples.Util.ConsoleGUI;
import org.jouvieje.Fmod.Examples.Util.FmodExampleFrame;
import org.jouvieje.Fmod.Examples.Util.Medias;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Structures.FMUSIC_MODULE;

public class Simplest extends ConsoleGUI implements FSOUND_MODES, VERSIONS {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new FmodExampleFrame(new Simplest());
	}
	
	private boolean init   = false;
	private boolean deinit = false;
	
	private ByteBuffer    modBuff = null;
	private FMUSIC_MODULE mod     = null;
	
	public Simplest() {
		super();
		initFmod();
		initialize();
	}
	
	public JPanel getPanel() { return this; }
	public String getTitle() { return "FMOD Simplest example."; }
	
	public void initFmod() {
		/*
		 * NativeFmod Init
		 */
		try {
			Init.loadLibraries();
		} catch(InitException e) {
			printExit("NativeFmod error! "+e.getMessage());
			return;
		}
		
		/*
		 * Checking NativeFmodEx version
		 */
		if(NATIVEFMOD_LIBRARY_VERSION != NATIVEFMOD_JAR_VERSION) {
			printExit("Error!  NativeFmod library version ("+NATIVEFMOD_LIBRARY_VERSION+") is different to jar version ("+NATIVEFMOD_JAR_VERSION+")");
			return;
		}
		
		/*==================================================*/
		
		/*
		 * Checking Fmod version
		 */
		if(Fmod.FSOUND_GetVersion() < FMOD_VERSION) {
	        printExit("Error : You are using the wrong DLL version!  You should be using FMOD "+FMOD_VERSION);
	        return;
	    }
	    
		init = true;
	}

	public void run() {
		if(!init) {
			return;
		}
		
		/*
		 * INITIALIZE
		 */
		if(!Fmod.FSOUND_Init(32000, 64, 0)) {
			printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError())+"\n");
			return;
		}
  
		/*
		 * LOAD SONG
		 */
		modBuff = Medias.loadMediaIntoMemory("/Media/invtro94.s3m");
		mod = Fmod.FMUSIC_LoadSongEx(modBuff, 0, modBuff.capacity(), FSOUND_LOADMEMORY, (int[])null, 0);
		if(mod == null) {
			print(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError())+"\n");
			return;
		}
		Fmod.FMUSIC_PlaySong(mod);   

		/*
		 * UPDATE INTERFACE
		 */
		print("Press ENTER to quit\n");
		print("=========================================================================\n");
		print("Playing "+Fmod.FMUSIC_GetName(mod)+"...\n");
		
		resetInput();
		do {
			printr( "order = "+Fmod.FMUSIC_GetOrder(mod)+"/"+Fmod.FMUSIC_GetNumOrders(mod) +
					", row = "+Fmod.FMUSIC_GetRow(mod)+"/"+Fmod.FMUSIC_GetPatternLength(mod, Fmod.FMUSIC_GetOrder(mod)) +
					" channels playing = "+Fmod.FSOUND_GetChannelsPlaying() +
					" cpu usage = "+Fmod.FSOUND_GetCPUUsage()+"    ");
					
			try {
				Thread.sleep(200);
			} catch(InterruptedException e){}
		}
		while(!keyHit() && !deinit);

		stop();
	}
	
	public void stop() {
		if(!init || deinit) {
			return;
		}
		deinit = true;
		
		print("\n");
		
		/*
		 * FREE SONG AND SHUT DOWN
		 */
		if(mod != null && !mod.isNull()) Fmod.FMUSIC_FreeSong(mod);
		print("Shutdown FMOD\n");
		Fmod.FSOUND_Close();
	}
}