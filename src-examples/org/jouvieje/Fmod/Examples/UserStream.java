//===============================================================================================
//USERSTREAM.EXE
//Copyright (c), Firelight Technologies Pty, Ltd, 1999-2004.

//This sample specifically demonstrates the user callback streaming facility, and generates a
//very strange noise! :)
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
import java.nio.ShortBuffer;

import javax.swing.JPanel;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Callbacks.FSOUND_DSPCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_STREAMCALLBACK;
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;

import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Examples.Util.ConsoleGUI;
import org.jouvieje.Fmod.Examples.Util.FmodExampleFrame;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Misc.BufferUtils;
import org.jouvieje.Fmod.Misc.Pointer;
import org.jouvieje.Fmod.Structures.FSOUND_DSPUNIT;
import org.jouvieje.Fmod.Structures.FSOUND_STREAM;
import org.jouvieje.libloader.LibLoader;

public class UserStream extends ConsoleGUI implements FSOUND_OUTPUTTYPES, FSOUND_MISC_VALUES, FSOUND_MODES, VERSIONS {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new FmodExampleFrame(new UserStream());
	}

	public UserStream() {
		super();
		initFmod();
		initialize();
	}

	public JPanel getPanel() {
		return this;
	}

	public String getTitle() {
		return "FMOD User-stream example.";
	}

	private boolean init = false;
	private boolean deinit = false;

	private FSOUND_STREAM stream = null;
	private FSOUND_DSPUNIT dsp1 = null;
	private FSOUND_DSPUNIT dsp2 = null;

	/*
	 * Callback used to divided the volume by 2
	 * When this callback is called two times, the sound is divided by 4
	 */
	private FSOUND_DSPCALLBACK dspcallback = new FSOUND_DSPCALLBACK(){
		public ByteBuffer FSOUND_DSPCALLBACK(ByteBuffer originalbuffer, ByteBuffer newBuffer, int length, Pointer userdata) {
			/*
			 * newbuffer is a buffer that contains the music. You can consider this as a short array.
			 * This array contains the right and left channel like that :
			 * 
			 * | l | r | l | r | l | r | l | r | ...
			 * The odd index are for the left channel and the even channels are for the right.
			 */

			/*
			 * 4 bytes per samples : 2 for the left and 2 for the right channels.
			 */
			ShortBuffer shortBuffer = newBuffer.asShortBuffer();
			for(int i = 0; i <= length - 1; i++) {// >>2 = 16bit stereo (4 bytes per sample)
				/*
				 * The >> Operator :
				 * It is a bit manipulation, it move to the left each bit. (<< is the same operator but move bit to the left)
				 * Ex:
				 * short a = 13;		//in base 2 = 0000000000001101
				 * short b = a >> 1		//   a >> 1 = 0000000000000110 so in base 10 : 6
				 * The value of b is 6
				 * 
				 * >> 1 is equivalent to an integer division by 2, that is for that reason the volume is half of
				 * the original volume.
				 * When we call two time this callback, the volume is divided by 2*2=4, so the volume is the quart
				 * of the original volume
				 * 
				 * Rem: >> 2 is equivalent to an integer division by 4
				 */
				short left = (short)(shortBuffer.get(2 * i) >> 1); //Divide left channel by 2
				short right = (short)(shortBuffer.get(2 * i + 1) >> 1); //Divide right channel by 2

				shortBuffer.put(2 * i, left);
				shortBuffer.put(2 * i + 1, right);
			}
			newBuffer.rewind();
			return newBuffer;
		}
	};

	/*
	 * [DESCRIPTION]
	 * User streamer callback 
	 * 
	 * [PARAMETERS]
	 * 'stream'         pointer to the stream supplying the callback
	 * 'buff'           pointer to the streamer buffer to fill.
	 * 'len'            length of the data block in BYTES
	 * 
	 * [REMARKS]
	 * What a strange noise!!!
	 * (heh heh)
	 */
	/*
	 * This callback 'creates' the sound
	 */
	private FSOUND_STREAMCALLBACK streamcallback = new FSOUND_STREAMCALLBACK(){
		float t1 = 0, t2 = 0; // time
		float v1 = 0, v2 = 0; // velocity

		public boolean FSOUND_STREAMCALLBACK(FSOUND_STREAM stream, ByteBuffer buff, int len, Pointer userdata) {
			/*
			 * len is the length of the buffer in bytes. But we have 4 byte per SAMPLES,
			 * that is for that reason that we need to divide by 4 (>>2) the length
			 */
			for(int i = 0; i < len >> 2; i++) {// >>2 because 16bit stereo (4 bytes per sample)
				buff.putShort((short)(Math.sin(t1) * 32767.0f)); //left channel
				buff.putShort((short)(Math.sin(t2) * 32767.0f)); //right channel

				t1 += 0.01f + v1;
				t2 += 0.0142f + v2;
				v1 += (float)(Math.sin(t1) * 0.002f);
				v2 += (float)(Math.sin(t2) * 0.002f);
			}
			buff.rewind();

//			println("callback : buff = "+buff+", len = "+len+", time = "+((float)NativeFmod.FSOUND_Stream_GetTime(stream) / 1000.0f)+" param = "+userdata);
			return true;
		}
	};

	/*
	 * [DESCRIPTION]
	 * End of stream user callback, initialized with FSOUND_Stream_SetEndCallback or 
	 * FSOUND_Stream_SetSynchCallback
	 * 
	 * [PARAMETERS]
	 * 'stream'     A pointer to the stream that ended.
	 * 'buff'       This is NULL for end of stream callbacks, or a string for synch callbacks.
	 * 'len'        This is reserved and is always 0 for end and synch callbacks. ignore.
	 * 'param'      This is the value passed to FSOUND_Stream_SetEndCallback or 
	 * 			    FSOUND_Stream_SetSynchCallback as a user data value.
	 * 
	 * [RETURN_VALUE]
	 * TRUE or FALSE, the value is ignored.
	 */
	private FSOUND_STREAMCALLBACK endcallback = new FSOUND_STREAMCALLBACK(){
		public boolean FSOUND_STREAMCALLBACK(FSOUND_STREAM stream, ByteBuffer buff, int len, Pointer userdata) {
			//end of stream callback doesnt have a 'buff' value, if it doesnt it could be a synch point.
			print("\n");
			if(buff != null) {
				//Retrieve the String pointed by the Buffer
				String synchpoint = BufferUtils.toString(buff);
				print("SYNCHPOINT : " + synchpoint + "\n");
			}
			else print("STREAM ENDED!!\n");

			return true;
		}
	};

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
			printExit("Error!  NativeFmod library version (" + NATIVEFMOD_LIBRARY_VERSION
					+ ") is different to jar version (" + NATIVEFMOD_JAR_VERSION + ")\n");
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

		print("-------------------------------------------------------------\n");
		print("FSOUND Streamer example.\n");
		print("Copyright (c) Firelight Technologies Pty, Ltd, 2001-2004.\n");
		print("-------------------------------------------------------------\n");

		print("---------------------------------------------------------\n");
		print("Output Type\n");
		print("---------------------------------------------------------\n");
		switch (LibLoader.getPlatform()) {
			case LibLoader.PLATFORM_WINDOWS:
				print("1 - Direct Sound\n");
				print("2 - Windows Multimedia Waveout\n");
				print("3 - ASIO\n");
				break;
			case LibLoader.PLATFORM_LINUX:
				print("1 - OSS - Open Sound System\n");
				print("2 - ESD - Elightment Sound Daemon\n");
				print("3 - ALSA 0.9 - Advanced Linux Sound Architecture\n");
				break;
			case LibLoader.PLATFORM_MACOSX:
				print("1 - Mac SoundManager\n");
				break;
			default:
				print("1 - NoSound\n");
				break;
		}
		print("---------------------------------------------------------\n"); // print driver names
		print("Press a corresponding number\n");

		int output = -1;
		while(output < 1 || output > 4) {
			try {
				output = Integer.parseInt("" + getKey());
			}
			catch(NumberFormatException e) {
				output = -1;
			}
			Thread.yield();
		}

		switch (LibLoader.getPlatform()) {
			case LibLoader.PLATFORM_WINDOWS:
				switch(output) {
					case 1:
						Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_DSOUND);
						break;
					case 2:
						Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_WINMM);
						break;
					case 3:
						Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_ASIO);
						break;
				}
				break;
			case LibLoader.PLATFORM_LINUX:
				switch(output) {
					case 1:
						Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_OSS);
						break;
					case 2:
						Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_ESD);
						break;
					case 3:
						Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_ALSA);
						break;
				}
				break;
			case LibLoader.PLATFORM_MACOSX:
				Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_MAC);
				break;
			default:
				Fmod.FSOUND_SetOutput(FSOUND_OUTPUT_NOSOUND);
				break;
		}

		// ==========================================================================================
		// SELECT DRIVER
		// ==========================================================================================
		// The following list are the drivers for the output method selected above.
		print("---------------------------------------------------------\n");
		output = Fmod.FSOUND_GetOutput();
		if(output == FSOUND_OUTPUT_NOSOUND) print("NoSound");
		else if(output == FSOUND_OUTPUT_WINMM) print("Windows Multimedia Waveout");
		else if(output == FSOUND_OUTPUT_DSOUND) print("Direct Sound");
		else if(output == FSOUND_OUTPUT_ASIO) print("ASIO");
		else if(output == FSOUND_OUTPUT_OSS) print("Open Sound System");
		else if(output == FSOUND_OUTPUT_ESD) print("Enlightment Sound Daemon");
		else if(output == FSOUND_OUTPUT_ALSA) print("Audio Linux System Audio");
		else if(output == FSOUND_OUTPUT_MAC) print("Mac SoundManager");

		print(" Driver list\n");
		print("---------------------------------------------------------\n");

		for(int i = 0; i <= Fmod.FSOUND_GetNumDrivers() - 1; i++) {
			print(i + " - " + Fmod.FSOUND_GetDriverName(i) + "\n");
		}
		print("---------------------------------------------------------\n"); // print driver names
		print("Press a corresponding number\n");

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
		if(!Fmod.FSOUND_Init(44100, 16, 0)) {
			printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			return;
		}

		// ==========================================================================================
		// CREATE USER STREAM
		// ==========================================================================================
		stream = Fmod.FSOUND_Stream_Create(streamcallback, 6 * 2048, FSOUND_NORMAL | FSOUND_16BITS | FSOUND_STEREO,
				44100, null);
		if(stream == null) {
			printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			return;
		}

		Fmod.FSOUND_Stream_SetEndCallback(stream, endcallback, null);

		dsp1 = Fmod.FSOUND_Stream_CreateDSP(stream, dspcallback, 0, null); // priority 0 = it comes first in dsp chain.
		dsp2 = Fmod.FSOUND_Stream_CreateDSP(stream, dspcallback, 1, null); // priority 1 = it comes last

		print("=========================================================================\n");
		print("Playing stream...\n");

		// ==========================================================================================
		// PLAY STREAM
		// ==========================================================================================
		if(Fmod.FSOUND_Stream_Play(FSOUND_FREE, stream) == -1) {
			printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			return;
		}

		readInput("******* Hit ENTER to active stream DSP unit #1 to halve the stream volume...");
		Fmod.FSOUND_DSP_SetActive(dsp1, true);

		readInput("******* Now hit ENTER to active stream DSP unit #2 to quarter the stream volume...");
		Fmod.FSOUND_DSP_SetActive(dsp2, true);

		readInput("******* How hit a ENTER to finish...");
		Fmod.FSOUND_DSP_SetActive(dsp1, false);
		Fmod.FSOUND_DSP_SetActive(dsp2, false);

		stop();
	}

	public void stop() {
		if(!init || deinit) {
			return;
		}
		deinit = true;

		print("\n");

		if(dsp1 != null && !dsp1.isNull()) {
			Fmod.FSOUND_DSP_Free(dsp1);
		}
		if(dsp2 != null && !dsp2.isNull()) {
			Fmod.FSOUND_DSP_Free(dsp2);
		}
		if(stream != null && !stream.isNull()) {
			Fmod.FSOUND_Stream_Close(stream);
		}

		print("Shutdown FMOD\n");
		Fmod.FSOUND_Close();
	}
}
