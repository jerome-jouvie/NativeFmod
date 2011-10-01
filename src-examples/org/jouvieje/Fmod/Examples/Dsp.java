/*===========================================================================================
DSP.EXE
Copyright (c), Firelight Technologies Pty, Ltd, 1999-2004.

This example demonstrates advanced DSP usage.
You can now attach sounds to dsp units.  The dsp units to be attached to must have a NULL
callback.  It is simply a holder for sounds to attach to, and have a specific position in 
the DSP chain.. see the diagram below for a visual representation of the DSP chain.
It also demonstrates the use of hardware DirectX 8 FX.
===========================================================================================*/

/*
	Priority :    0          100        320-332       400          1000
	Name     : [CLEAR]-->[samp1-WET]-->[REVERB]-->[samp1-DRY]-->[CLIPCOPY]-->[SOUNDCARD]

	Note the above priority values correspond to the values in FMOD.H

	FSOUND_DSP_DEFAULTPRIORITY_CLEARUNIT        0       
	FSOUND_DSP_DEFAULTPRIORITY_SFXUNIT          100     
	FSOUND_DSP_DEFAULTPRIORITY_MUSICUNIT        200     
	FSOUND_DSP_DEFAULTPRIORITY_USER             300     
	FSOUND_DSP_DEFAULTPRIORITY_FFTUNIT          900     
	FSOUND_DSP_DEFAULTPRIORITY_CLIPANDCOPYUNIT  1000    

	Notice how 'SFX' unit is wet (has reverb).  This is because it is the default destination
	For sound effects if NULL is passed to PlaySoundEx or PlaySound is used.
	Also the Reverb DSP has itself positioned AFTER the 'SFX' unit so then we will hear reverb.
	Now if a sound is attached to the 'Dry' DSP unit located at priority 400, then it will not
	be affected by reverb!
*/

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
import org.jouvieje.Fmod.Callbacks.FSOUND_DSPCALLBACK;
import org.jouvieje.Fmod.Defines.FSOUND_DSP_PRIORITIES;
import org.jouvieje.Fmod.Defines.FSOUND_INIT_FLAGS;
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;
import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_FX_MODES;
import org.jouvieje.Fmod.Enumerations.FSOUND_MIXERTYPES;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Examples.Util.ConsoleGUI;
import org.jouvieje.Fmod.Examples.Util.FmodExampleFrame;
import org.jouvieje.Fmod.Examples.Util.Medias;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Misc.BufferUtils;
import org.jouvieje.Fmod.Misc.ObjectPointer;
import org.jouvieje.Fmod.Misc.Pointer;
import org.jouvieje.Fmod.Structures.FSOUND_DSPUNIT;
import org.jouvieje.Fmod.Structures.FSOUND_SAMPLE;

public class Dsp extends ConsoleGUI implements FSOUND_INIT_FLAGS, FSOUND_DSP_PRIORITIES, FSOUND_MODES, FSOUND_MISC_VALUES, FSOUND_OUTPUTTYPES, FSOUND_MIXERTYPES, FSOUND_FX_MODES, VERSIONS {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new FmodExampleFrame(new Dsp());
	}

	public Dsp() {
		super();
		initFmod();
		initialize();
	}

	public JPanel getPanel() {
		return this;
	}

	public String getTitle() {
		return "FMOD Dsp example.";
	}

	private boolean init = false;
	private boolean deinit = false;

	private FSOUND_SAMPLE samp1 = null;
	private ByteBuffer samp1Buff = null;
	private FSOUND_SAMPLE samp2 = null;
	private ByteBuffer samp2Buff = null;

	/*
	 * Reverb stuff
	 */
	private final int REVERB_NUMTAPS = 7;
	private final REVERBTAP[] DSP_ReverbTap = new REVERBTAP[REVERB_NUMTAPS];

	/*
	 * Dry sfx unit
	 */
	private FSOUND_DSPUNIT drySFXUnit = null;

	/*
	 * [DESCRIPTION]
	 *  Callback to mix in one reverb tap.  It copies the buffer into its own history buffer also.
	 *  
	 * [PARAMETERS]
	 *  'originalbuffer'    Pointer to the original mixbuffer, not any buffers passed down 
	 *                      through the dsp chain.  They are in newbuffer.
	 *  'newbuffer'         Pointer to buffer passed from previous DSP unit.
	 *  'length'            Length in SAMPLES of buffer being passed.
	 *  'userdata'          User parameter.  In this case it is a pointer to DSP_LowPassBuffer.
	 * 
	 * [RETURN_VALUE]
	 *  a pointer to the buffer that was passed in, with a tap mixed into it.
	 */
	private FSOUND_DSPCALLBACK DSP_ReverbCallback = new FSOUND_DSPCALLBACK(){
		public ByteBuffer FSOUND_DSPCALLBACK(ByteBuffer originalbuffer, ByteBuffer newBuffer, int length, Pointer userdata) {
			REVERBTAP tap = (REVERBTAP)ObjectPointer.createView(userdata).getObject();

			int mixerType = Fmod.FSOUND_GetMixer();
			int bytesPerOutputSample;
			if(mixerType == Fmod.FSOUND_MIXER_QUALITY_MMXP5 || mixerType == Fmod.FSOUND_MIXER_QUALITY_MMXP6) bytesPerOutputSample = 4; // 16bit stereo
			else bytesPerOutputSample = 8; // 32bit stereo

			/*
			 * reverb history buffer is a ringbuffer.  If the length makes the copy wrap, then split the copy 
			 * into end part, and start part.. 
			 */
			if(tap.historyOffset + length > tap.historyLen) {
				int taillen = tap.historyLen - tap.historyOffset;
				int startlen = length - taillen;

				// mix a scaled version of history buffer into output
				tap.historyBuff.position(tap.historyOffset << 2);
				Fmod.FSOUND_DSP_MixBuffers(newBuffer, tap.historyBuff, taillen, 44100, tap.volume, tap.pan,
						FSOUND_STEREO | FSOUND_16BITS);
				tap.historyBuff.rewind();

				newBuffer.position(taillen * bytesPerOutputSample);
				Fmod.FSOUND_DSP_MixBuffers(newBuffer, tap.historyBuff, startlen, 44100, tap.volume, tap.pan,
						FSOUND_STEREO | FSOUND_16BITS);
				newBuffer.rewind();

				// now copy input into reverb/history buffer 
				tap.historyBuff.position(tap.historyOffset << 2);
				for(int i = 0; i <= taillen * 2 - 1; i++) {
					int val = 0;
					if(mixerType == Fmod.FSOUND_MIXER_QUALITY_FPU) val = (int)newBuffer.getFloat();
					else if(mixerType == Fmod.FSOUND_MIXER_QUALITY_MMXP5 || mixerType == Fmod.FSOUND_MIXER_QUALITY_MMXP6) {
						val = (int)newBuffer.getShort();
					}
					else {
						val = newBuffer.getInt();
					}

					val = (val > Short.MAX_VALUE ? Short.MAX_VALUE : val < Short.MIN_VALUE ? Short.MIN_VALUE : val);
					tap.historyBuff.putShort((short)val);
				}
				tap.historyBuff.rewind();
				newBuffer.rewind();

				newBuffer.position(taillen * bytesPerOutputSample);
				for(int i = 0; i <= startlen * 2 - 1; i++) {
					int val = 0;
					if(mixerType == Fmod.FSOUND_MIXER_QUALITY_FPU) val = (int)newBuffer.getFloat();
					else if(mixerType == Fmod.FSOUND_MIXER_QUALITY_MMXP5 || mixerType == Fmod.FSOUND_MIXER_QUALITY_MMXP6) {
						val = (int)newBuffer.getShort();
					}
					else {
						val = newBuffer.getInt();
					}

					val = (val > Short.MAX_VALUE ? Short.MAX_VALUE : val < Short.MIN_VALUE ? Short.MIN_VALUE : val);
					tap.historyBuff.putShort((short)val);
				}
			}
			// no wrapping reverb buffer, just write dest
			else {
				// mix a scaled version of history buffer into output
				tap.historyBuff.position((tap.historyOffset << 2));
				Fmod.FSOUND_DSP_MixBuffers(newBuffer, tap.historyBuff, length, 44100, tap.volume, tap.pan, FSOUND_STEREO | FSOUND_16BITS);

				// now copy input into reverb/history buffer 
				for(int i = 0; i <= length * 2 - 1; i++) {
					int val = 0;
					if(mixerType == Fmod.FSOUND_MIXER_QUALITY_FPU) {
						val = (int)newBuffer.getFloat();
					}
					else if(mixerType == Fmod.FSOUND_MIXER_QUALITY_MMXP5 || mixerType == Fmod.FSOUND_MIXER_QUALITY_MMXP6) {
						val = (int)newBuffer.getShort();
					}
					else {
						val = newBuffer.getInt();
					}

					val = (val > Short.MAX_VALUE ? Short.MAX_VALUE : val < Short.MIN_VALUE ? Short.MIN_VALUE : val);
					tap.historyBuff.putShort((short)val);
				}
			}
			tap.historyBuff.rewind();
			newBuffer.rewind();

			tap.historyOffset += length;
			if(tap.historyOffset >= tap.historyLen) {
				tap.historyOffset -= tap.historyLen;
			}

			// reverb history has been mixed into new buffer, so return it.

			return newBuffer;
		}
	};

	/*
	 * REVERB SETUP
	 */
	private void setupReverb() {
		/* something to fiddle with. */
		int[] delay = new int[]{131, 149, 173, 211, 281, 401, 457}; /* prime numbers make it sound cool! */
		int[] volume = new int[]{120, 100, 95, 90, 80, 60, 50};
		int[] pan = new int[]{100, 128, 128, 152, 128, 100, 152};

		for(int i = 0; i <= REVERB_NUMTAPS - 1; i++) {
			DSP_ReverbTap[i] = new REVERBTAP();
			DSP_ReverbTap[i].delayMs = delay[i];
			DSP_ReverbTap[i].volume = volume[i];
			DSP_ReverbTap[i].pan = pan[i];
			DSP_ReverbTap[i].historyOffset = 0;
			DSP_ReverbTap[i].historyLen = (DSP_ReverbTap[i].delayMs * 44100 / 1000);
			if(DSP_ReverbTap[i].historyLen < Fmod.FSOUND_DSP_GetBufferLength()) {
				DSP_ReverbTap[i].historyLen = Fmod.FSOUND_DSP_GetBufferLength(); /* just in case our calc is not the same. */
			}

			DSP_ReverbTap[i].historyBuff = BufferUtils.newByteBuffer(4 * DSP_ReverbTap[i].historyLen); /* * 4 is for 16bit stereo (mmx only) */
			DSP_ReverbTap[i].workArea = null;
			DSP_ReverbTap[i].userData = ObjectPointer.create(DSP_ReverbTap[i]);
			DSP_ReverbTap[i].unit = Fmod.FSOUND_DSP_Create(DSP_ReverbCallback, FSOUND_DSP_DEFAULTPRIORITY_USER + 20 + 2 * i, DSP_ReverbTap[i].userData);

			Fmod.FSOUND_DSP_SetActive(DSP_ReverbTap[i].unit, true);
		}
	}

	private void closeReverb() {
		for(int i = 0; i <= REVERB_NUMTAPS - 1; i++) {
			if(DSP_ReverbTap[i].unit != null && !DSP_ReverbTap[i].unit.isNull()) {
				Fmod.FSOUND_DSP_Free(DSP_ReverbTap[i].unit);
				DSP_ReverbTap[i].unit = null;

				if(DSP_ReverbTap[i].userData != null) DSP_ReverbTap[i].userData.release();
				DSP_ReverbTap[i].userData = null;

				DSP_ReverbTap[i].historyBuff = null;
				DSP_ReverbTap[i].workArea = null;
			}
		}
	}

	private int fxChannel = FSOUND_FREE;
	private int echoId = -1, echoId2 = -1, flangeId = -1;
	private boolean firstTime = true;

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

		int eqId1 = -1, eqId2 = -1;

		/*
		 * INITIALIZE
		 */
		Fmod.FSOUND_SetBufferSize(100); /* This is nescessary to get FX to work on output buffer */
		if(!Fmod.FSOUND_Init(44100, 32, FSOUND_INIT_ENABLESYSTEMCHANNELFX)) {
			try {
				Thread.sleep(1000);
			}
			catch(Exception e) {}

			if(!Fmod.FSOUND_Init(44100, 32, FSOUND_INIT_ENABLESYSTEMCHANNELFX)) {
				printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
				return;
			}
		}

		/*
		 * LOAD SAMPLES
		 */

		/* PCM,44,100 Hz, 8 Bit, Mono */
		samp1Buff = Medias.loadMediaIntoMemory("/Media/drumloop.wav");
		samp1 = Fmod.FSOUND_Sample_Load(FSOUND_FREE, samp1Buff, FSOUND_2D | FSOUND_LOADMEMORY, 0, samp1Buff.capacity());
		if(samp1 == null) {
			printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			return;
		}
		Fmod.FSOUND_Sample_SetMode(samp1, FSOUND_LOOP_OFF);

		/* PCM,44,100 Hz, 16 Bit, Stereo */
		samp2Buff = Medias.loadMediaIntoMemory("/Media/jules.mp3");
		samp2 = Fmod.FSOUND_Sample_Load(FSOUND_FREE, samp2Buff, FSOUND_HW2D | FSOUND_ENABLEFX | FSOUND_LOADMEMORY, 0, samp2Buff.capacity());
		if(samp2 == null) {
			printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			return;
		}

		/*
		 * DISPLAY HELP
		 */
		print("FSOUND Output Method : ");
		int output = Fmod.FSOUND_GetOutput();
		if(output == FSOUND_OUTPUT_NOSOUND)     print("FSOUND_OUTPUT_NOSOUND");
		else if(output == FSOUND_OUTPUT_WINMM)  print("FSOUND_OUTPUT_WINMM");
		else if(output == FSOUND_OUTPUT_DSOUND) print("FSOUND_OUTPUT_DSOUND");
		else if(output == FSOUND_OUTPUT_ASIO)   print("FSOUND_OUTPUT_ASIO");
		else if(output == FSOUND_OUTPUT_OSS)    print("FSOUND_OUTPUT_OSS");
		else if(output == FSOUND_OUTPUT_ALSA)   print("FSOUND_OUTPUT_ALSA");
		else if(output == FSOUND_OUTPUT_ESD)    print("FSOUND_OUTPUT_ESD");

		print("\nFSOUND Mixer         : ");
		int mixer = Fmod.FSOUND_GetMixer();
		if(mixer == FSOUND_MIXER_QUALITY_FPU)        print("FSOUND_MIXER_QUALITY_FPU");
		else if(mixer == FSOUND_MIXER_QUALITY_MMXP5) print("FSOUND_MIXER_QUALITY_MMXP5");
		else if(mixer == FSOUND_MIXER_QUALITY_MMXP6) print("FSOUND_MIXER_QUALITY_MMXP6");

		print("\nFSOUND Driver        : " + Fmod.FSOUND_GetDriverName(Fmod.FSOUND_GetDriver()) + "\n");

		print("=========================================================================\n");
		print("Press 1       Play SOFTWARE sound affected by following reverb dsp unit (wet)\n");
		print("      2       Play SOFTWARE sound unaffected by following reverb dsp unit (dry)\n");
		if(Fmod.FSOUND_GetOutput() == Fmod.FSOUND_OUTPUT_DSOUND) {
			print("      3       Play HARDWARE FX enabled sound using Direct X 8 (echo+flange)\n");
			print("      4       Set EQ on global software output to be affect by DX8 FX\n");
			print("              Press 1 or 2 to hear the effect (3 is unaffected)\n");
			print("      5       Turn off EQ on global software output\n");
		}
		print("      E       Quit\n");
		print("=========================================================================\n");

		/*
		 * SET UP DSPs!
		 */
		setupReverb();

		/*
		 * Note if we are using a dsp unit for playing sounds, callback and parameter are ignored!
		 */
		drySFXUnit = Fmod.FSOUND_DSP_Create(null, FSOUND_DSP_DEFAULTPRIORITY_USER + 100, null);
		Fmod.FSOUND_DSP_SetActive(drySFXUnit, true);

		/*
		 * You must pause the software output before getting the FX handle on it.
		 */
		if(Fmod.FSOUND_GetOutput() == FSOUND_OUTPUT_DSOUND) {
			Fmod.FSOUND_SetPaused(FSOUND_SYSTEMCHANNEL, true);

			eqId1 = Fmod.FSOUND_FX_Enable(FSOUND_SYSTEMCHANNEL, FSOUND_FX_PARAMEQ);
			eqId2 = Fmod.FSOUND_FX_Enable(FSOUND_SYSTEMCHANNEL, FSOUND_FX_PARAMEQ);

			Fmod.FSOUND_SetPaused(FSOUND_SYSTEMCHANNEL, false);
		}

		/*
		 * START PLAYING!
		 */
		boolean exit = false;
		do {
			printr("channels playing = " + Fmod.FSOUND_GetChannelsPlaying() + " cpu usage = " + Fmod.FSOUND_GetCPUUsage());

			switch(getKey()) {
				case '1':
					Fmod.FSOUND_PlaySound(FSOUND_FREE, samp1);
					break;
				case '2':
					Fmod.FSOUND_PlaySoundEx(FSOUND_FREE, samp1, drySFXUnit, false);
					break;
				case '3':
					if(Fmod.FSOUND_GetOutput() == Fmod.FSOUND_OUTPUT_DSOUND) {
						firstTime = (fxChannel == Fmod.FSOUND_FREE);

						fxChannel = Fmod.FSOUND_PlaySoundEx(fxChannel, samp2, drySFXUnit, true);

						/* 
						 * NOTE! Even though it is for hardware FX, set it to a DrySFXUnit just 
						 * in case a non hardware output mode has been selected (such as 
						 * WINMM/Linux etc) and it actually drops back to 100% software 
						 */

						Fmod.FSOUND_SetVolume(fxChannel, 120); /* turn it down a bit! */

						if(firstTime) {
							echoId = Fmod.FSOUND_FX_Enable(fxChannel, FSOUND_FX_ECHO);
							echoId2 = Fmod.FSOUND_FX_Enable(fxChannel, FSOUND_FX_ECHO);
							flangeId = Fmod.FSOUND_FX_Enable(fxChannel, FSOUND_FX_FLANGER);
						}

						Fmod.FSOUND_SetPaused(fxChannel, false);

						Fmod.FSOUND_FX_SetEcho(echoId, 80.0f, 70.0f, 100.0f, 100.0f, true);
						Fmod.FSOUND_FX_SetEcho(echoId2, 100, 70.0f, 10, 10, false);
					}
					break;
				case '4':
					Fmod.FSOUND_FX_SetParamEQ(eqId1, 8000, 36, -15);
					Fmod.FSOUND_FX_SetParamEQ(eqId2, 16000, 36, -15);
					break;
				case '5':
					Fmod.FSOUND_FX_SetParamEQ(eqId1, 8000, 15, 0);
					Fmod.FSOUND_FX_SetParamEQ(eqId2, 8000, 15, 0);
					break;
				case 'e':
				case 'E':
					exit = true;
					break;
			}

			try {
				Thread.sleep(100);
			}
			catch(InterruptedException e) {}
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

		if(drySFXUnit != null && !drySFXUnit.isNull()) {
			Fmod.FSOUND_DSP_Free(drySFXUnit);
		}

		closeReverb();

		if(samp1 != null && !samp1.isNull()) {
			Fmod.FSOUND_Sample_Free(samp1);
		}
		samp1Buff = null;
		if(samp2 != null && !samp2.isNull()) {
			Fmod.FSOUND_Sample_Free(samp2);
		}
		samp2Buff = null;

		print("Shutdown FMOD\n");
		Fmod.FSOUND_Close();
	}
}