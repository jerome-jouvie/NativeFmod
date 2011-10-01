//===============================================================================================
//CDDA.EXE
//Copyright (c), Firelight Technologies Pty, Ltd, 1999-2004.
//
//Use FMOD stream API to do digital CD playback. Also demonstrates how to use FMOD to 
//generate a CDDB query.
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
import org.jouvieje.Fmod.Examples.Util.ConsoleGUI;
import org.jouvieje.Fmod.Examples.Util.FmodExampleFrame;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Misc.BufferUtils;
import org.jouvieje.Fmod.Misc.Pointer;
import org.jouvieje.Fmod.Misc.PointerUtils;
import org.jouvieje.Fmod.Structures.FSOUND_STREAM;
import org.jouvieje.Fmod.Structures.FSOUND_TOC_TAG;
import org.jouvieje.libloader.LibLoader;

public class CDDA extends ConsoleGUI implements FSOUND_MODES, FSOUND_MISC_VALUES, VERSIONS {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new FmodExampleFrame(new CDDA());
	}
	
	public CDDA() {
		super();
		initFmod();
		initialize();
	}
	
	public JPanel getPanel() { return this; }
	public String getTitle() { return "FMOD CDDA example."; }
	
	private boolean init   = false;
	private boolean deinit = false;
	
	private FSOUND_STREAM stream;
	private int channel = -1;
	
	private int last_openstate = -1;
	private boolean firsttime = true;
	private int track = 0;
	
	public void initFmod() {
		/*
		 * NativeFmod Init
		 */
		try {
			Init.loadLibraries();
		} catch(InitException e) {
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
			printExit("Error : You are using the wrong DLL version!  You should be using FMOD "+FMOD_VERSION+"\n");
			return;
		}
		
		init = true;
	}

	public void run() {
		if(!init) {
			return;
		}
		
		print("-------------------------------------------------------------\n");
		print("FMOD CDDA example.\n");
		print("Copyright (c) Firelight Technologies Pty, Ltd, 2001-2004.\n");
		print("-------------------------------------------------------------\n");
		
		setInput("'E' (windows) or '/dev/cdrom1' (linux/mac)");
		String drive = readInput("Audio CD Drive: ");
		switch (LibLoader.getPlatform()) {
			case LibLoader.PLATFORM_WINDOWS:
				if(!((drive.charAt(0) >= 'a' && drive.charAt(0) <= 'z') || (drive.charAt(0) >= 'A' && drive.charAt(0) <= 'Z'))) {
					printExit("ERROR: Invalid drive");
					return;
				}
				drive += ":*j";
				break;
			case LibLoader.PLATFORM_LINUX:
			case LibLoader.PLATFORM_MACOSX:
				if(!drive.startsWith("/")) {
					printExit("ERROR: Invalid drive");
					return;
				}
				break;
		}

		if(!Fmod.FSOUND_Init(44100, 32, 0)) {
			String error = Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError());
			print("Shutdown FMOD\n");
			Fmod.FSOUND_Close();
			printExit(error);
			return;
		}

		Fmod.FSOUND_Stream_SetBufferSize(2000);

		stream = Fmod.FSOUND_Stream_Open(new String(drive), FSOUND_NORMAL | FSOUND_NONBLOCKING, 0, 0);
		if(stream == null) {
			String error = Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError());
			print("Shutdown FMOD\n");
			Fmod.FSOUND_Close();
			printExit(error);
			return;
		}

		Fmod.FSOUND_Stream_SetSubStream(stream, 0);
		
		print("\n");
		print("=========================================================================\n");
		print("Press f        Skip forward 2 seconds\n");
		print("      b        Skip back 2 seconds\n");
		print("      n        Next track\n");
		print("      SPACE    Pause/Unpause\n");
		print("      e        Quit\n");
		print("=========================================================================\n");
		
		boolean exit = false;
		do {
			if((stream != null) && (channel < 0)) {
				int this_openstate = Fmod.FSOUND_Stream_GetOpenState(stream);

				if(this_openstate == -3) {
					Pointer cd_error = new Pointer();
					int[] length = new int[1];
					if(Fmod.FSOUND_Stream_FindTagField(stream, 0, "CD_ERROR", cd_error, length)) {
						ByteBuffer buff = PointerUtils.toBuffer(cd_error, length[0]);
						print(BufferUtils.toString(buff) + "\n");
					}
					else {
						print("ERROR: Couldn't open CDDA stream\n");
					}
					Fmod.FSOUND_Stream_Close(stream);
					print("Shutdown FMOD\n");
					Fmod.FSOUND_Close();
					return;
				}
				
				if((last_openstate != 0) && (this_openstate == 0)) {
					if(firsttime) {
						FSOUND_TOC_TAG toc = new FSOUND_TOC_TAG();
						if(Fmod.FSOUND_Stream_FindTagField(stream, 0, "CD_TOC", toc, (int[])null)) {
							dump_cddb_query(toc);
						}

						Pointer cd_device_info = new Pointer();
						if(!Fmod.FSOUND_Stream_GetTagField(stream, 0, (int[])null, null, cd_device_info, (int[])null)) {
							Fmod.FSOUND_Stream_Close(stream);
							print("Shutdown FMOD\n");
							Fmod.FSOUND_Close();
							printExit("ERROR: Couldn't get CD_DEVICE_INFO tag");
							return;
						}

						print(PointerUtils.toString(cd_device_info)+"\n");
						print("\n=========================================================================\n");
						firsttime = false;
						
						Pointer cd_error = new Pointer();
						if(Fmod.FSOUND_Stream_FindTagField(stream, 0, "CD_ERROR", cd_error, (int[])null)) {
							Fmod.FSOUND_Stream_Close(stream);
							print("Shutdown FMOD\n");
							Fmod.FSOUND_Close();
							printExit(PointerUtils.toString(cd_error)+"\n");
							return;
						}
					}

					channel = Fmod.FSOUND_Stream_PlayEx(FSOUND_FREE, stream, null, true);
					Fmod.FSOUND_SetPaused(channel, false);
				}

				last_openstate = this_openstate;
			}
			
			if(channel != -1) {
				switch(getKey()) {
					case ' ':
						Fmod.FSOUND_SetPaused(channel, !Fmod.FSOUND_GetPaused(channel));
						break;
					case 'f':
						Fmod.FSOUND_Stream_SetTime(stream, Fmod.FSOUND_Stream_GetTime(stream) + 2000);
						break;
					case 'b':
						Fmod.FSOUND_Stream_SetTime(stream, Fmod.FSOUND_Stream_GetTime(stream) - 2000);
						break;
					case 'n':
						track++;
						if(track >= Fmod.FSOUND_Stream_GetNumSubStreams(stream)) {
							track = 0;
						}
						Fmod.FSOUND_Stream_SetSubStream(stream, track);
						channel = -1;
						last_openstate = -1;
						break;
					case 'e':
					case 'E':
						exit = true;
						break;
				}
			}
			
			//stream is open and ready
			if(Fmod.FSOUND_Stream_GetOpenState(stream) == 0) {
				/*
				 * Print informations on the track (number, time, cpu usage)
				 */
				printr("Track "+(track+1)+"/"+Fmod.FSOUND_Stream_GetNumSubStreams(stream)+"  " + 
						(Fmod.FSOUND_Stream_GetTime(stream)/1000/60)+":"+(Fmod.FSOUND_Stream_GetTime(stream)/1000%60)+"/" + 
						(Fmod.FSOUND_Stream_GetLengthMs(stream)/1000/60)+":"+(Fmod.FSOUND_Stream_GetLengthMs(stream)/1000%60)+"  " + 
						"cpu "+Fmod.FSOUND_GetCPUUsage());
			}

			Fmod.FSOUND_Update();
			
			try {
				Thread.sleep(100);
			} catch(Exception e){}
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

		if(stream != null && !stream.isNull()) Fmod.FSOUND_Stream_Close(stream);
		print("Shutdown FMOD\n");
		Fmod.FSOUND_Close();
	}
	
	private int cddb_sum(int n) {
		int ret = 0;

		while(n > 0) {
			ret += (n % 10);
			n /= 10;
		}

		return ret;
	}

	private long cddb_discid(FSOUND_TOC_TAG toc) {
		int i, t, n = 0;

		for(i = 0; i < toc.getNumtracks(); i++) {
			n += cddb_sum((toc.getMin()[i] * 60) + toc.getSec()[i]);
		}

		t = ((toc.getMin()[toc.getNumtracks()] * 60) + toc.getSec()[toc.getNumtracks()])
				- ((toc.getMin()[0] * 60) + toc.getSec()[0]);

		return ((n % 0xff) << 24 | t << 8 | toc.getNumtracks());
	}

	private void dump_cddb_query(FSOUND_TOC_TAG toc) {
		print("cddb query " + Integer.toHexString((int)cddb_discid(toc)) + " " + toc.getNumtracks());

		for(int i = 0; i < toc.getNumtracks(); i++) {
			print(" " + ((toc.getMin()[i] * (60 * 75)) + (toc.getSec()[i] * 75) + toc.getFrame()[i]));
		}

		print(" "+((toc.getMin()[toc.getNumtracks()] * 60) + toc.getSec()[toc.getNumtracks()])+"\n");
	}
}