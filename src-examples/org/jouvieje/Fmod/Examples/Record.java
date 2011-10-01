/*
 RECORD.EXE
 Copyright (c), Firelight Technologies Pty, Ltd, 2000-2004.

 This example shows how to record data to a static sample, or record dynamically, and have
 a dsp unit processing the result.
 The reverb below is taken from /samples/fmod/fmod.c 
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.FileFormat.WavFormat.DataChunk;
import org.jouvieje.Fmod.FileFormat.WavFormat.FmtChunk;
import org.jouvieje.Fmod.FileFormat.WavFormat.RiffChunk;
import org.jouvieje.Fmod.FileFormat.WavFormat.WavHeader;
import org.jouvieje.Fmod.Misc.BufferUtils;
import org.jouvieje.Fmod.Misc.FileWriterUtils;
import org.jouvieje.Fmod.Misc.ObjectPointer;
import org.jouvieje.Fmod.Misc.Pointer;
import org.jouvieje.Fmod.Misc.PointerUtils;
import org.jouvieje.Fmod.Structures.FSOUND_SAMPLE;
import org.jouvieje.libloader.LibLoader;

public class Record extends ConsoleGUI implements FSOUND_INIT_FLAGS, FSOUND_DSP_PRIORITIES, FSOUND_MODES, FSOUND_MISC_VALUES, FSOUND_OUTPUTTYPES, FSOUND_MIXERTYPES, FSOUND_FX_MODES, VERSIONS {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new FmodExampleFrame(new Record());
	}

	public Record() {
		super();
		initFmod();
		initialize();
	}

	public JPanel getPanel() {
		return this;
	}

	public String getTitle() {
		return "FMOD Record example.";
	}

	private final boolean ENABLEREVERB = true;
	private final int RECORDRATE = 44100;
	private final int RECORDLEN = (RECORDRATE * 5); /* 5 seconds at RECORDRATE khz */
	private final int OUTPUTRATE = 44100;

	private int REVERB_NUMTAPS = 7;

	private boolean init = false;
	private boolean deinit = false;

	private FSOUND_SAMPLE samp1 = null;
	private int channel = 0;

	/*
	 * Reverb stuff
	 */
	private REVERBTAP[] DSP_ReverbTap = new REVERBTAP[REVERB_NUMTAPS];

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
					int val;
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
				tap.historyBuff.rewind();
				newBuffer.rewind();

				newBuffer.position(taillen * bytesPerOutputSample);
				for(int i = 0; i <= startlen * 2 - 1; i++) {
					int val;
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
			// no wrapping reverb buffer, just write dest
			else {
				// mix a scaled version of history buffer into output
				tap.historyBuff.position((tap.historyOffset << 2));
				Fmod.FSOUND_DSP_MixBuffers(newBuffer, tap.historyBuff, length, 44100, tap.volume, tap.pan,
						FSOUND_STEREO | FSOUND_16BITS);

				// now copy input into reverb/history buffer 
				for(int i = 0; i <= length * 2 - 1; i++) {
					int val;
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
	private void SetupReverb() {
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
			ObjectPointer userdata = ObjectPointer.create(DSP_ReverbTap[i]);
			DSP_ReverbTap[i].unit = Fmod.FSOUND_DSP_Create(DSP_ReverbCallback, FSOUND_DSP_DEFAULTPRIORITY_USER + 20 + 2 * i, userdata);

			Fmod.FSOUND_DSP_SetActive(DSP_ReverbTap[i].unit, true);
		}
	}

	private void closeReverb() {
		for(int i = 0; i <= REVERB_NUMTAPS - 1; i++) {
			if(DSP_ReverbTap[i].unit != null && !DSP_ReverbTap[i].unit.isNull()) {
				Fmod.FSOUND_DSP_Free(DSP_ReverbTap[i].unit);
				DSP_ReverbTap[i].unit = null;

				DSP_ReverbTap[i].historyBuff = null;
				DSP_ReverbTap[i].workArea = null;
			}
		}
	}

	/*
	 * [DESCRIPTION]
	 *  Writes out the contents of a record buffer to a file.
	 */
	private void saveToWav(FSOUND_SAMPLE sample) {
		if(sample == null) return;

		long mode = Fmod.FSOUND_Sample_GetMode(sample);
		short bits = ((mode & FSOUND_16BITS) != 0) ? (short)16 : (short)8;
		short channels = ((mode & FSOUND_STEREO) != 0) ? (short)2 : (short)1;
		int lenbytes = (Fmod.FSOUND_Sample_GetLength(sample) * channels * bits / 8);
		int[] vRate = new int[1];
		Fmod.FSOUND_Sample_GetDefaults(sample, vRate, null, null, null);
		int rate = vRate[0];

		/*
		 * WAV Structures
		 */
		WavHeader wavHeader = new WavHeader(
				new RiffChunk(new byte[]{'R', 'I', 'F', 'F'}, FmtChunk.SIZEOF_FMT_CHUNK + RiffChunk.SIZEOF_RIFF_CHUNK + lenbytes),
				new byte[]{'W', 'A', 'V', 'E'});
		FmtChunk fmtChunk = new FmtChunk(
				new RiffChunk(new byte[]{'f', 'm', 't', ' '}, FmtChunk.SIZEOF_FMT_CHUNK - RiffChunk.SIZEOF_RIFF_CHUNK),
				(short)1, channels, rate, rate * channels * bits / 8, (short)(1 * channels * bits / 8), bits);
		DataChunk dataChunk = new DataChunk(new RiffChunk(new byte[]{'d', 'a', 't', 'a'}, lenbytes));

		try {
			RandomAccessFile file = new RandomAccessFile("record.wav", "rw");

			/*
			 * Write out the WAV header.
			 */
			WavHeader.writeWavHeader(file, wavHeader);
			FmtChunk.writeFmtChunk(file, fmtChunk);
			DataChunk.writeDataChunk(file, dataChunk);

			/*
			 * Lock the sample to get acces to the data
			 */
			Pointer ptr1 = new Pointer();
			Pointer ptr2 = new Pointer();
			int[] len1 = new int[1];
			int[] len2 = new int[1];
			Fmod.FSOUND_Sample_Lock(sample, 0, lenbytes, ptr1, ptr2, len1, len2);

			//Write datas
			ByteBuffer datas = PointerUtils.toBuffer(ptr1, len1[0]);
			FileWriterUtils.writeByteBuffer(file, datas);

			/*
			 * Unlock the sample
			 */
			Fmod.FSOUND_Sample_Unlock(sample, ptr1, ptr2, len1[0], len2[0]);

			//Close the file
			file.close();
		}
		catch(FileNotFoundException e) {
			print("Fails to write in the file ! " + e.getMessage() + "\n");
		}
		catch(IOException e) {
			print("Fails to write in the file ! " + e.getMessage() + "\n");
		}
	}

	private long oldrecordpos = 0, oldplaypos = 0;
	private float smoothedvu = 0;

	/*
	 * [DESCRIPTION]
	 * Main example function
	 * 
	 * [PARAMETERS]
	 * 'argc'      Number of arguments passed on the command line
	 * 'argv'      Pointer to an array of arguments.
	 */
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

		/*
		 * SELECT OUTPUT METHOD
		 */

		print("---------------------------------------------------------\n");
		print("Output Type\n");
		print("---------------------------------------------------------\n");
		switch (LibLoader.getPlatform()) {
			case LibLoader.PLATFORM_WINDOWS:
				print("1 - Direct Sound\n");
				print("2 - Windows Multimedia Waveout\n");
				print("3 - NoSound\n");
				break;
			case LibLoader.PLATFORM_LINUX:
				print("1 - OSS - Open Sound System\n");
				print("2 - ESD - Elightment Sound Daemon\n");
				print("3 - ALSA 0.9 - Advanced Linux Sound Architecture\n");
				break;
			case LibLoader.PLATFORM_MACOSX:
				print("1 - Mac SoundManager\n");
				break;
		}
		print("---------------------------------------------------------\n"); /* print driver names */
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

		/*
		 * SELECT OUTPUT DRIVER
		 */

		/* The following list are the drivers for the output method selected above. */
		print("---------------------------------------------------------\n");
		//Print output name
		printOutput();
		print(" Driver list\n");
		print("---------------------------------------------------------\n");

		for(int i = 0; i < Fmod.FSOUND_GetNumDrivers(); i++) {
			print(i + " - " + Fmod.FSOUND_GetDriverName(i) + "\n"); /* print driver names */
		}
		print("---------------------------------------------------------\n"); /* print driver names */
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

		Fmod.FSOUND_SetDriver(driver); /* Select sound card (0 = default) */

		/*
		 * SELECT MIXER
		 */
		Fmod.FSOUND_SetMixer(FSOUND_MIXER_QUALITY_AUTODETECT);

		/*
		 * INITIALIZE
		 */
		if(!Fmod.FSOUND_Init(OUTPUTRATE, 64, FSOUND_INIT_ACCURATEVULEVELS)) {
			printExit(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			return;
		}

		/*
		 * SELECT INPUT DRIVER (can be done before or after init)
		 */

		/* The following list are the drivers for the output method selected above. */
		print("---------------------------------------------------------\n");
		printOutput();
		print(" Recording device driver list\n");
		print("---------------------------------------------------------\n");

		for(int i = 0; i < Fmod.FSOUND_Record_GetNumDrivers(); i++) {
			print(i + " - " + Fmod.FSOUND_Record_GetDriverName(i) + "\n"); /* print driver names */
		}
		print("---------------------------------------------------------\n"); /* print driver names */
		print("Press a corresponding number\n");

		int record = -1;
		while(record < 0 || record > Fmod.FSOUND_Record_GetNumDrivers() - 1) {
			try {
				record = Integer.parseInt("" + getKey());
			}
			catch(NumberFormatException e) {
				record = -1;
			}
			Thread.yield();
		}

		if(!Fmod.FSOUND_Record_SetDriver(record)) /* Select input sound card (0 = default) */
		{
			String error = Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError());
			print("Shutdown FMOD\n");
			Fmod.FSOUND_Close();
			printExit(error);
			return;
		}

		/*
		 * DISPLAY HELP
		 */
		print("FSOUND Output Method : ");
		output = Fmod.FSOUND_GetOutput();
		if(output == FSOUND_OUTPUT_NOSOUND)     print("FSOUND_OUTPUT_NOSOUND\n");
		else if(output == FSOUND_OUTPUT_WINMM)  print("FSOUND_OUTPUT_WINMM\n");
		else if(output == FSOUND_OUTPUT_DSOUND) print("FSOUND_OUTPUT_DSOUND\n");
		else if(output == FSOUND_OUTPUT_ASIO)   print("FSOUND_OUTPUT_ASIO\n");
		else if(output == FSOUND_OUTPUT_OSS)    print("FSOUND_OUTPUT_OSS\n");
		else if(output == FSOUND_OUTPUT_ESD)    print("FSOUND_OUTPUT_ESD\n");
		else if(output == FSOUND_OUTPUT_ALSA)   print("FSOUND_OUTPUT_ALSA\n");
		else if(output == FSOUND_OUTPUT_MAC)    print("FSOUND_OUTPUT_MAC\n");
		else                                    print("\n");

		print("FSOUND Mixer         : ");
		int mixer = Fmod.FSOUND_GetMixer();
		if(mixer == FSOUND_MIXER_QUALITY_FPU)        print("FSOUND_MIXER_QUALITY_FPU\n");
		else if(mixer == FSOUND_MIXER_QUALITY_MMXP5) print("FSOUND_MIXER_QUALITY_MMXP5\n");
		else if(mixer == FSOUND_MIXER_QUALITY_MMXP6) print("FSOUND_MIXER_QUALITY_MMXP6\n");
		else if(mixer == FSOUND_MIXER_MONO)          print("FSOUND_MIXER_MONO\n");
		else if(mixer == FSOUND_MIXER_QUALITY_MONO)  print("FSOUND_MIXER_QUALITY_MONO\n");
		else                                         print("\n");
		print("FSOUND Driver        : " + Fmod.FSOUND_GetDriverName(Fmod.FSOUND_GetDriver()) + "\n");
		print("FSOUND Record Driver : " + Fmod.FSOUND_Record_GetDriverName(Fmod.FSOUND_Record_GetDriver()) + "\n");

		/*
		 * RECORD INTO A STATIC SAMPLE
		 */

		/*
		 * Create a sample to record into
		 */
		if(Fmod.FSOUND_GetOutput() == FSOUND_OUTPUT_OSS) {
			samp1 = Fmod.FSOUND_Sample_Alloc(FSOUND_UNMANAGED, RECORDLEN, FSOUND_MONO | FSOUND_8BITS | FSOUND_UNSIGNED, RECORDRATE, 255, 128, 255);
		}
		else {
			samp1 = Fmod.FSOUND_Sample_Alloc(FSOUND_UNMANAGED, RECORDLEN, FSOUND_STEREO | FSOUND_16BITS, RECORDRATE, 255, 128, 255);
		}

		print("\n");
		print("=========================================================================\n");
		print("Press ENTER to start recording 5 seconds worth of data\n");
		print("=========================================================================\n");
		readInput("");

		if(!Fmod.FSOUND_Record_StartSample(samp1, false)) {/* it will record into this sample for 5 seconds then stop */
			String error = Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError());
			print("Shutdown FMOD\n");
			Fmod.FSOUND_Close();
			printExit(error);
			return;
		}

		do {
			printr("Recording position = " + Fmod.FSOUND_Record_GetPosition());

			try {
				Thread.sleep(500);
			}
			catch(InterruptedException e) {}
		}
		while(Fmod.FSOUND_Record_GetPosition() < RECORDLEN);

		Fmod.FSOUND_Record_Stop(); /* it already stopped anyway */

		print("\n");
		print("=========================================================================\n");
		print("Press ENTER to play back recorded data\n");
		print("=========================================================================\n");
		readInput("");

		channel = Fmod.FSOUND_PlaySound(FSOUND_FREE, samp1);

		print("Playing back sound...\n");

		do {
			printr("Playback position = " + Fmod.FSOUND_GetCurrentPosition(channel));

			try {
				Thread.sleep(500);
			}
			catch(InterruptedException e) {}
		}
		while(Fmod.FSOUND_IsPlaying(channel));

		if(Fmod.FSOUND_GetOutput() == FSOUND_OUTPUT_OSS) {
			Fmod.FSOUND_Sample_Free(samp1);
			print("Shutdown FMOD\n");
			Fmod.FSOUND_Close();
			printExit("");
			return;
		}

		/*
		 * SAVED TO 
		 */
		saveToWav(samp1);
		print("\nSaved to record.wav!\n\n");

		/*
		 * REALTIME FULL DUPLEX RECORD / PLAYBACK!
		 */
		print("=========================================================================\n");
		print("Press ENTER to do some full duplex realtime recording!\n");
		print("(with reverb for mmx users)\n");
		print("=========================================================================\n");
		readInput("");

		Fmod.FSOUND_Sample_SetMode(samp1, FSOUND_LOOP_NORMAL); /* make it a looping sample */

		if(!Fmod.FSOUND_Record_StartSample(samp1, true)) {/* start recording and make it loop also */
			String error = Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError());
			print("Shutdown FMOD\n");
			Fmod.FSOUND_Close();
			printExit(error);
			return;
		}

		/*
		 * Increase this value if the sound sounds corrupted or the time between recording
		 * and hearing the result is longer than it should be..
		*/
		final int RECORD_DELAY_MS = 25;
		final float RECORD_DELAY_SAMPLES = (RECORDRATE * RECORD_DELAY_MS / 1000);

		/*
		 * Let the record cursor move forward a little bit first before we try to play it
		 * (the position jumps in blocks, so any non 0 value will mean 1 block has been recorded)
		*/
		while(Fmod.FSOUND_Record_GetPosition() != 0) {
			try {
				Thread.sleep(10);
			}
			catch(InterruptedException e) {}
		}

		if(ENABLEREVERB) SetupReverb();

		channel = Fmod.FSOUND_PlaySound(FSOUND_FREE, samp1); /* play the sound */

		int originalfreq = Fmod.FSOUND_GetFrequency(channel);

//		println("initial delay = "+(Fmod.FSOUND_GetCurrentPosition(channel)-Fmod.FSOUND_Record_GetPosition())); 

		resetInput();
		do {
			long playpos = Fmod.FSOUND_GetCurrentPosition(channel);
			int recordpos = Fmod.FSOUND_Record_GetPosition();

			/* 
			 * NOTE : As the recording and playback frequencies arent guarranteed to be exactly in 
			 * sync, we have to adjust the playback frequency to keep the 2 cursors just enough 
			 * apart not to overlap. (and sound corrupted)
			 * This code tries to keep it inside a reasonable size window just behind the record
			 * cursor. ie [........|play window|<-delay->|<-Record cursor.............] 
			 */

			/*
			 * Dont do this code if either of the cursors just wrapped
			 */
			long diff = 0;
			if(playpos > oldplaypos && recordpos > oldrecordpos) {
				diff = playpos - recordpos;

				if(diff > -RECORD_DELAY_SAMPLES) Fmod.FSOUND_SetFrequency(channel, originalfreq - 1000); /* slow it down */
				else if(diff < -(RECORD_DELAY_SAMPLES * 2)) Fmod.FSOUND_SetFrequency(channel, originalfreq + 1000); /* speed it up */
				else Fmod.FSOUND_SetFrequency(channel, originalfreq);
			}

			oldplaypos = playpos;
			oldrecordpos = recordpos;

			/*
			 * Print some info and a VU meter (vu is smoothed)
			 */
			float[] l = new float[1];
			float[] r = new float[1];
			float vuval;

			Fmod.FSOUND_GetCurrentLevels(channel, l, r);
			vuval = (l[0] + r[0]) * 0.5f;
			vuval *= 18.0f;

			final float VUSPEED = 0.2f;

			if(vuval > smoothedvu) smoothedvu = vuval;

			smoothedvu -= VUSPEED;
			if(smoothedvu < 0) smoothedvu = 0;

			String vu = "";
			for(int i = 0; i < (int)(smoothedvu); i++) {
				vu += '=';
			}

			printr("Play=" + playpos + " Rec=" + recordpos + " (gap=" + diff + ", freqchange=" + (Fmod.FSOUND_GetFrequency(channel) - originalfreq) + " hz) VU:" + vu);

			try {
				Thread.sleep(10);
			}
			catch(InterruptedException e) {}
		}
		while(!keyHit() && !deinit);

		stop();
	}

	public void stop() {
		if(!init || deinit) {
			return;
		}
		deinit = true;

		Fmod.FSOUND_StopSound(channel);
		Fmod.FSOUND_Record_Stop();

		if(ENABLEREVERB) {
			closeReverb();
		}

		/*
		 * CLEANUP AND SHUTDOWN
		 */

		if(samp1 != null && !samp1.isNull()) Fmod.FSOUND_Sample_Free(samp1);

		print("Shutdown FMOD\n");
		Fmod.FSOUND_Close();
	}

	private void printOutput() {
		int output = Fmod.FSOUND_GetOutput();
		if(output == FSOUND_OUTPUT_NOSOUND)     print("NoSound");
		else if(output == FSOUND_OUTPUT_WINMM)  print("Windows Multimedia Waveout");
		else if(output == FSOUND_OUTPUT_DSOUND) print("Direct Sound");
		else if(output == FSOUND_OUTPUT_ASIO)   print("ASIO");
		else if(output == FSOUND_OUTPUT_OSS)    print("Open Sound System");
		else if(output == FSOUND_OUTPUT_ESD)    print("Enlightment Sound Daemon");
		else if(output == FSOUND_OUTPUT_ALSA)   print("Advanced Linux Sound Architecture");
		else if(output == FSOUND_OUTPUT_MAC)    print("Mac SoundManager");
	}
}