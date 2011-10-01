/**
 * 				NativeFmod Project
 *
 * Want to use FMOD API (www.fmod.org) in the Java language ? NativeFmod is made for you.
 * Copyright © 2004-2007 Jérôme JOUVIE (Jouvieje)
 *
 * Created on 28 avr. 2004
 * @version NativeFmod v3.4 (for FMOD v3.75)
 * @author Jérôme JOUVIE (Jouvieje)
 *
 *
 * WANT TO CONTACT ME ?
 * E-mail :
 * 		jerome.jouvie@gmail.com
 * My web sites :
 * 		http://jerome.jouvie.free.fr/
 *
 *
 * INTRODUCTION
 * Fmod is an API (Application Programming Interface) that allow you to use music
 * and creating sound effects with a lot of sort of musics.
 * Fmod is at :
 * 		http://www.fmod.org/
 * The reason of this project is that Fmod can't be used in Java direcly, so I've created
 * NativeFmod project.
 *
 *
 * GNU LESSER GENERAL PUBLIC LICENSE
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1 of the License,
 * or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the
 * Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307 USA
 */

package org.jouvieje.FmodDyn;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Callbacks.FMUSIC_CALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_ALLOCCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_CLOSECALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_DSPCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_FREECALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_METADATACALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_OPENCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_READCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_REALLOCCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_SEEKCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_STREAMCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_TELLCALLBACK;
import org.jouvieje.Fmod.Defines.FSOUND_CAPS;
import org.jouvieje.Fmod.Defines.FSOUND_CDPLAYMODES;
import org.jouvieje.Fmod.Defines.FSOUND_DSP_PRIORITIES;
import org.jouvieje.Fmod.Defines.FSOUND_INIT_FLAGS;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;
import org.jouvieje.Fmod.Defines.FSOUND_STATUS_FLAGS;
import org.jouvieje.Fmod.Enumerations.FMOD_ERRORS;
import org.jouvieje.Fmod.Enumerations.FMUSIC_TYPES;
import org.jouvieje.Fmod.Enumerations.FSOUND_FX_MODES;
import org.jouvieje.Fmod.Enumerations.FSOUND_MIXERTYPES;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Enumerations.FSOUND_SPEAKERMODES;
import org.jouvieje.Fmod.Enumerations.FSOUND_STREAM_NET_STATUS;
import org.jouvieje.Fmod.Enumerations.FSOUND_TAGFIELD_TYPE;
import org.jouvieje.Fmod.Exceptions.NonDirectBufferException;
import org.jouvieje.Fmod.Misc.BufferUtils;
import org.jouvieje.Fmod.Misc.Pointer;
import org.jouvieje.Fmod.Structures.FMUSIC_MODULE;
import org.jouvieje.Fmod.Structures.FSOUND_DSPUNIT;
import org.jouvieje.Fmod.Structures.FSOUND_REVERB_CHANNELPROPERTIES;
import org.jouvieje.Fmod.Structures.FSOUND_REVERB_PROPERTIES;
import org.jouvieje.Fmod.Structures.FSOUND_SAMPLE;
import org.jouvieje.Fmod.Structures.FSOUND_STREAM;
import org.jouvieje.Fmod.Structures.FSOUND_SYNCPOINT;

/**
 * Creates an instance of Fmod
 */
public class FMOD_INSTANCE extends Pointer
{
	/**
	 * Create a view of the <code>Pointer</code> object as a <code>FMOD_INSTANCE</code> object.<br>
	 * This view is valid only if the memory holded by the <code>Pointer</code> holds a FMOD_INSTANCE object.
	 */
	public static FMOD_INSTANCE createView(Pointer pointer)
	{
		return new FMOD_INSTANCE(Pointer.getPointer(pointer));
	}
	protected FMOD_INSTANCE(long pointer)
	{
		super(pointer);
	}

	/**
	 * @deprecated use FSOUND_CD_OpenTray instead
	 */
	public boolean FSOUND_CD_Eject(char drive)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_CD_Eject(pointer, drive);
	}

	/**
	 * Sets up the soundsystem output mode.<BR>
	 * <br><b>Remarks :</b><br>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.
	 * It must be called before FSOUND_Init, or after FSOUND_Close.<br>
	 * -------<br>
	 * Under Windows NT - Waveout is FASTER than DirectSound, achieves LOWER latency, AND
	 * is LESS buggy. DirectSound under NT is achieved by emulating waveout, and therefore is
	 * inferior to waveout. Use WAVEOUT under NT.<br>
	 * Under Windows 9x and W2K - DirectSound is faster than waveout and can achieve lower latency.
	 * Use DIRECTSOUND under Win9x and W2K.<br>
	 * -------<br>
	 * If you dont call FSOUND_SetOutput, FMOD will now autodetect DSOUND or WINMM based on the operating system.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 *
	 * @param outputtype The output system to be used. See FSOUND_OUTPUTTYPES for valid parameters and descriptions.
	 * -1 Is autodetect based on operating system.
	 * @return On success, TRUE is returned.
	 * On failure, (ie if FMOD is already active) FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 * @see FMOD_INSTANCE#FSOUND_GetDriverCaps(int, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_GetOutput()
	 * @see FMOD_INSTANCE#FSOUND_GetOutputHandle()
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FSOUND_OUTPUTTYPES
	 * @see FMOD_INSTANCE#FSOUND_SetDriver(int)
	 * @see FMOD_INSTANCE#FSOUND_SetOutput(int)
	 */
	public boolean FSOUND_SetOutput(int outputtype)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetOutput(pointer, outputtype);
	}

	/**
	 * Selects a soundcard driver.<br>
	 * It is used when an output mode has enumerated more than one output device, and you need to select between them.<br>
	 * <br><b>Remarks :</b><br>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.<br>
	 * It must be called before FSOUND_Init, or after FSOUND_Close.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube<br>
	 * @param driver Driver number to select :<br>
	 * 0 will select the DEFAULT sound driver.<br>
	 * <0 will select an INVALID driver which will case the DEVICE to be set to a null (nosound) driver.<br>
	 * >0 Selects other valid drivers that can be listed with FSOUND_GetDriverName.
	 * @return On success, TRUE is returned.<br>
	 * On failure, (ie if FMOD is already active) FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 * @see FMOD_INSTANCE#FSOUND_GetDriver()
	 * @see FMOD_INSTANCE#FSOUND_GetDriverName(int)
	 * @see FMOD_INSTANCE#FSOUND_GetNumDrivers()
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetOutput(int)
	 */
	public boolean FSOUND_SetDriver(int driver)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetDriver(pointer, driver);
	}

	/**
	 * Sets a digital mixer type.<br>
	 * <br><b>Remarks :</b><br>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.<br>
	 * It must be called before FSOUND_Init, or after FSOUND_Close.<br>
	 * This function does not nescessarily need to be called, autodetection will select the fastest mixer for your machine.<br>
	 * It is here if you need to test all mixer types for debugging purposes, or a mixer has a feature that the autodetected one doesnt.<br>
	 * (ie low quality mixers or volume ramping)<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mixer mixer type, see FSOUND_MIXERTYPES for valid parameters and descriptions.
	 * @return On success, TRUE is returned.<br>
	 * On failure, (ie if FMOD is already active) FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 * @see FMOD_INSTANCE#FSOUND_GetMixer()
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FSOUND_MIXERTYPES
	 */
	public boolean FSOUND_SetMixer(int mixer)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetMixer(pointer, mixer);
	}

	/**
	 * Sets the FMOD internal mixing buffer size. <br>
	 * It is configurable because low buffersizes use less memory, but are more instable.<br>
	 * More importantly, increasing buffer size will increase sound output stability, but on the other hand increases latency, and to some extent, CPU usage.<br>
	 * FMOD chooses the most optimal size by default for best stability, depending on the output type - and if the drivers are emulated or not (NT).<br>
	 * It is not recommended changing this value unless you really need to.<br>
	 * You may get worse performance than the default settings chosen by FMOD.
	 * <br><b>Remarks :</b><br>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.<br>
	 * It must be called before FSOUND_Init, or after FSOUND_Close.<br>
	 * ---------<br>
	 * The buffersize seting defaults to 50ms if it is not called for DSOUND. <br>
	 * It defaults to 200ms for Windows Multimedia wave-out or for emulated DirectSound drivers (such as NT drivers).<br>
	 * When the output is FSOUND_OUTPUT_ASIO the buffersize is ignored. The buffersize should be configured using the ASIO driver which can be done with the supplied asioconfig.exe in the FMOD SDK.<br>
	 * ---------<br>
	 * Buffer sizes lower than 50 are clamped at 50.<br>
	 * Buffer sizes are also rounded DOWN to the nearest multiple of 25. This is because FMOD mixes in blocks of 25ms.<br>
	 * Due to this buffersize command latency on software channels will be between 25 and 50ms on average (37.5ms) when the buffersize is set to 50.<br>
	 * ---------<br>
	 * Macintosh, PlayStation 2 and GameCube do not support this as they already achieve minimal latency and are forced to 25ms.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, XBox
	 * @param len_ms The buffer size in milliseconds.
	 * @return On success, TRUE is returned.
	 * On failure, (ie if FMOD is already active) FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetBufferLengthTotal()
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 */
	public boolean FSOUND_SetBufferSize(int len_ms)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetBufferSize(pointer, len_ms);
	}

	/**
	 * This is an optional function to set the window handle of the application you are writing, so Directsound can tell if it is in focus or not.<br>
	 * <br><b>Remarks :</b><br>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.<br>
	 * It must be called before FSOUND_Init, or after FSOUND_Close.<br>
	 * ---------<br>
	 * FMOD uses GetForegroundWindow if this function is not called.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE<br>
	 * @param hwnd Pointer to a HWND windows handle of your application.<br>
	 * NULL means it will pick the foreground application window.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 */
	public boolean FSOUND_SetHWND(Pointer hwnd)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetHWND(pointer, Pointer.getPointer(hwnd));
	}

	/**
	 * This sets the minimum allowable hardware channels before FMOD drops back to 100 percent software.<br>
	 * This is helpful for minimum spec cards, and not having to guess how many hardware channels they might have.<br>
	 * This way you can guarantee and assume a certain number of channels for your application and place them all in
	 * FSOUND_HW3D without fear of the playsound failing because it runs out of channels on a low spec card.
	 * <br><b>Remarks :</b><br>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.<br>
	 * It must be called before FSOUND_Init, or after FSOUND_Close.<br>
	 * ---------<br>
	 * As an example, if you set your minimum to 16, you can now safely guarantee that 16 sounds can be played at once that are created with FSOUND_HW3D. <br>
	 * This way if you do come across a card that only supports 4 channels, it will just drop back to playing ALL sounds in software mode. <br>
	 * It may sound worse, but at least it doesnt fail on the playsound. (which could sound even worse!)<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param min The minimum number of hardware channels allowable on a card before it uses the software engine 1004562604f the time.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetMaxHardwareChannels(int)
	 */
	public boolean FSOUND_SetMinHardwareChannels(int min)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetMinHardwareChannels(pointer, min);
	}

	/**
	 * This sets the maximum allocatable channels on a hardware card.<br>
	 * FMOD automatically detects and allocates the maximum number of 3d hardware channels, so calling this will limit that number if it becomes too much.<br>
	 * <br><b>Remarks :</b><br>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.<br>
	 * It must be called before FSOUND_Init, or after FSOUND_Close.<br>
	 * ---------<br>
	 * This function has nothing to do with FSOUND_SetMinHardwareChannels, in that this is not a function that forces FMOD into software mixing if a card has a certain number of channels.<br>
	 * This function only sets a limit on hardware channels, so if you card has 96 hardware channels, and you set FSOUND_SetMaxHardwareChannels(10), then you will only have 10 hardware channels to use.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param max The maximum number of hardware channels to allocate, even if the soundcard supports more.
	 * @return On success, TRUE is returned.
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetMaxHardwareChannels(int)
	 * @see FMOD_INSTANCE#FSOUND_SetMinHardwareChannels(int)
	 */
	public boolean FSOUND_SetMaxHardwareChannels(int max)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetMaxHardwareChannels(pointer, max);
	}

	/**
	 * Specifies a method for FMOD to allocate memory, either through callbacks or its own internal memory management.<br>
	 * You can also supply a pool of memory for FMOD to work with and it will do so with no extra calls to malloc or free.<br>
	 * This is useful for systems that want FMOD to use their own memory management, or fixed memory devices such as PocketPC,
	 * XBox, PS2 and GameCube that dont want any allocations occuring out of their control causing fragmentation or unpredictable
	 * overflows in a tight memory space.<br>
	 * See remarks for more useful information.<br>
	 * <br><b>Remarks :</b><br>
	 * FMOD has been tested to stay in a limit and fail gracefully if the fixed pool size is not large enough with FMOD_ERR_MEMORY errors.<br>
	 * FMOD only does allocation when creating streams, music or samples and the FSOUND_Init stage. It never allocates or deallocates memory during the course of runtime processing.<br>
	 * To find out the required fixed size the user can call FSOUND_GetMemoryStats with a larger than nescessary pool size (or no pool), and find out the maximum ram usage at any one time within FMOD.<br>
	 * -------------------------------------<br>
	 * FMOD behaves differently based on what you pass into this function in 3 different combinations.<br>
	 * Here are the examples.<br>
	 * NULL, 0, NULL, NULL, NULL : Falls back purely to ansi C malloc, realloc and free.<br>
	 * NULL, 0, myalloc, myrealloc, myfree : Calls user supplied callbacks every time fmod does a memory allocation or deallocation.<br>
	 * ptr, len, NULL, NULL, NULL : Uses "ptr" and manages memory internally. NO extra mallocs or frees are performed from this point.<br>
	 * -------------------------------------<br>
	 * Callbacks and memory pools cannot be combined, as if a pool is specified FMOD, manipulates the pool of memory internally with its own allocate and free scheme.<br>
	 * The memory management algorithm to work within a fixed size of ram is extremely efficient and faster than the standard C malloc or free.<br>
	 * -------------------------------------<br>
	 * On XBox you MUST specify a pointer and length. The memory provided must be enough to store all sample data.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param pool If you want a fixed block of memory for FMOD to use, pass it in here. Specify the length in poollen.<br>
	 * Specifying NULL doesnt use internal management and it relies on callbacks.
	 * @param poollen Length in bytes of the pool of memory for FMOD to use specified in. Specifying 0 turns off internal<br>
	 * memory management and relies purely on callbacks. Length must be a multiple of 512.
	 * @param useralloc Only supported if pool is NULL. Otherwise it overrides the FMOD internal calls to alloc.<br>
	 * Compatible with ansi malloc().
	 * @param userrealloc Only supported if pool is NULL. Otherwise it overrides the FMOD internal calls to realloc.<br>
	 * Compatible with ansi realloc().
	 * @param userfree Only supported if pool is NULL. Otherwise it overrides the FMOD internal calls to free.<br>
	 * Compatible with ansi free().
	 * @see FSOUND_ALLOCCALLBACK
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 * @see FSOUND_FREECALLBACK
	 * @see FMOD_INSTANCE#FSOUND_GetMemoryStats(IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FSOUND_REALLOCCALLBACK
	 */
	public boolean FSOUND_SetMemorySystem(
		ByteBuffer pool,
		int poollen,
		FSOUND_ALLOCCALLBACK useralloc,
		FSOUND_REALLOCCALLBACK userrealloc,
		FSOUND_FREECALLBACK userfree)
	{
		if(pointer == 0) throw new NullPointerException();
		if(pool != null && !pool.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetMemorySystem(
			pointer,
			pool, BufferUtils.getPositionInBytes(pool),
			poollen,
			useralloc,
			userrealloc,
			userfree);
	}

	/**
	 * Initializes the FMOD Sound System.<br>
	 * <br><b>Remarks :</b><br>
	 * You do not have control over how many hardware channels are available to you.<br>
	 * In a lot of cases it may be 0 (the sound card does not have the ability to supply hardware channels).<br>
	 * This is why it is usually a good idea to supply FSOUND_Init with a good number of software channels to fall back onto, for example 32.
	 * Hardware channels are 3D hardware channels only. There is no benefit in supporting hardware for 2d playback of sound effects.<br>
	 * With todays machines and FMOD's superior mixing routines, FMOD's software engine can sometimes be faster than the driver's hardware support! <br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mixrate Output rate in hz between 4000 and 65535. Any thing outside this will cause the function to fail and return FALSE.<br>
	 * PS2 Note. Only rates of 24000 and 48000 are supported.<br>
	 * SmartPhone Note. Use 22050 or the operating system may crash outside of the control of fmod.
	 * @param maxsoftwarechannels Maximum number of SOFTWARE channels available.<br>
	 * The number of HARDWARE channels is autodetected. The total number of channels available
	 * (hardware and software) after initialization can be found with FSOUND_GetMaxChannels. <br>
	 * Having a large number of maxchannels does not adversely affect cpu usage, but it means it
	 * has the POTENTIAL to mix a large number of channels, which can have an adverse effect on cpu usage.<br>
	 * 1024 is the highest number that can be set. Anything higher will return an error.
	 * @param flags See FSOUND_INIT_FLAGS. Controls some global or initialization time aspects of playback.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 * @see FMOD_INSTANCE#FSOUND_File_SetCallbacks(FSOUND_OPENCALLBACK, FSOUND_CLOSECALLBACK, FSOUND_READCALLBACK, FSOUND_SEEKCALLBACK, FSOUND_TELLCALLBACK)
	 * @see FMOD_INSTANCE#FSOUND_GetCurrentLevels(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_GetDriverCaps(int, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_GetMaxChannels()
	 * @see FMOD_INSTANCE#FSOUND_GetMemoryStats(IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_GetOutputHandle()
	 * @see FMOD_INSTANCE#FSOUND_GetOutputRate()
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FSOUND_INIT_FLAGS
	 * @see FMOD_INSTANCE#FSOUND_PlaySound(int, FSOUND_SAMPLE)
	 * @see FMOD_INSTANCE#FSOUND_PlaySoundEx(int, FSOUND_SAMPLE, FSOUND_DSPUNIT, boolean)
	 * @see FMOD_INSTANCE#FSOUND_SetBufferSize(int)
	 * @see FMOD_INSTANCE#FSOUND_SetDriver(int)
	 * @see FMOD_INSTANCE#FSOUND_SetHWND(Pointer)
	 * @see FMOD_INSTANCE#FSOUND_SetMaxHardwareChannels(int)
	 * @see FMOD_INSTANCE#FSOUND_SetMemorySystem(ByteBuffer, int, FSOUND_ALLOCCALLBACK, FSOUND_REALLOCCALLBACK, FSOUND_FREECALLBACK)
	 * @see FMOD_INSTANCE#FSOUND_SetMinHardwareChannels(int)
	 * @see FMOD_INSTANCE#FSOUND_SetMixer(int)
	 * @see FMOD_INSTANCE#FSOUND_SetOutput(int)
	 * @see FMOD_INSTANCE#FSOUND_SetSpeakerMode(int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 */
	public boolean FSOUND_Init(int mixrate, int maxsoftwarechannels, int flags)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Init(pointer, mixrate, maxsoftwarechannels, flags);
	}

	/**
	 * Shuts down the WHOLE FMOD Sound System.<br>
	 * <br><b>Remarks :</b><br>
	 * This also closes down the sample management system, freeing all MANAGED samples loaded (unless they were allocated with the FSOUND_UNMANAGED flag).<br>
	 * Streams are not freed. You must close them yourself.<br>
	 * CD Tracks are stopped.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @see FMOD_INSTANCE#FSOUND_CD_GetVolume(char)
	 * @see FMOD_INSTANCE#FSOUND_CD_SetVolume(char, int)
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Alloc(int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Get(int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Load(int, String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetBufferSize(int)
	 * @see FMOD_INSTANCE#FSOUND_SetDriver(int)
	 * @see FMOD_INSTANCE#FSOUND_SetHWND(Pointer)
	 * @see FMOD_INSTANCE#FSOUND_SetMaxHardwareChannels(int)
	 * @see FMOD_INSTANCE#FSOUND_SetMemorySystem(ByteBuffer, int, FSOUND_ALLOCCALLBACK, FSOUND_REALLOCCALLBACK, FSOUND_FREECALLBACK)
	 * @see FMOD_INSTANCE#FSOUND_SetMinHardwareChannels(int)
	 * @see FMOD_INSTANCE#FSOUND_SetMixer(int)
	 * @see FMOD_INSTANCE#FSOUND_SetOutput(int)
	 * @see FMOD_INSTANCE#FSOUND_SetSpeakerMode(int)
	 */
	public void FSOUND_Close()
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_Close(pointer);
	}

	/**
	 * This updates the 3d sound engine and DMA engine (only on some platforms), and should be called once a game frame.<br>
	 * This function will also update the software mixer if you have selected FSOUND_OUTPUT_NOSOUND_NONREALTIME as your output mode.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetAttributes(FloatBuffer, FloatBuffer, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetCurrent(int, int)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDopplerFactor(float)
	 */
	public void FSOUND_Update()
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_Update(pointer);
	}

	/**
	 * Sets the mode for the users speaker setup.<br>
	 * <br><b>Remarks :</b><br>
	 * Note - Only reliably works with FSOUND_OUTPUT_DSOUND on windows, or consoles such as PlayStation 2, GameCube and XBox.<br>
	 * For other output types speaker mode only interprets FSOUND_SPEAKERMODE_MONO, everything else is interpreted as stere.<br>
	 * ----------------------------------<br>
	 * To get true 5.1 dolby digital or DTS output you will need a soundcard that can encode it, and a receiver that can decode it. <br>
	 * ----------------------------------<br>
	 * Calling this will reset the pan separation setting. It sets it to 0 if FSOUND_SPEAKERMODE_MONO is chosen, and 1 otherwise. <br>
	 * You will need to reset the pan separation if required afterwards.<br>
	 * ----------------------------------<br>
	 * XBOX <br>
	 * This function must be called before FSOUND_Init to change the default speaker mode. To change on the fly, you must close down FMOD with FSOUND_Close then re-initialize it with FSOUND_Init.<br>
	 * If it is called after FSOUND_Init, only headphone speakermode is interpreted to switch headphone mode on and off.<br>
	 * By default the dashboard setting is used for the speakermode. It is not recommend you change this mode unless you let the user change it in-game.<br>
	 * ----------------------------------<br>
	 * PlayStation 2.<br>
	 * Only mono/stereo and prologic 2 modes are interpreted. Dolby digital or DTS are not supported.<br>
	 * This function must be called before playing sounds. Calling this after playing a sound will not make that existing sound work in Prologic 2.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param speakermode This is an enum describing the users speaker setup.
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetPanSeperation(float)
	 * @see FSOUND_SPEAKERMODES
	 */
	public void FSOUND_SetSpeakerMode(int speakermode)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_SetSpeakerMode(pointer, speakermode);
	}

	/**
	 * Sets the master volume for any sound effects played. Does not affect music or CD output.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param volume The volume to set. Valid ranges are from 0 (silent) to 255 (full volume)
	 * @see FMOD_INSTANCE#FMUSIC_SetMasterVolume(FMUSIC_MODULE, int)
	 * @see FMOD_INSTANCE#FSOUND_SetVolume(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetVolumeAbsolute(int, int)
	 */
	public void FSOUND_SetSFXMasterVolume(int volume)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_SetSFXMasterVolume(pointer, volume);
	}

	/**
	 * Sets the master pan seperation for 2d sound effects. <br>
	 * <br><b>Remarks :</b><br>
	 * This is set to 1.0 by default.<br>
	 * Music is not affected by this. To change pan seperation for music files individually,<br>
	 * see FMUSIC_SetPanSeperation<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param pansep The pan scalar. 1.0 means full pan seperation, 0 means mono.
	 * @see FMOD_INSTANCE#FMUSIC_SetPanSeperation(FMUSIC_MODULE, float)
	 * @see FMOD_INSTANCE#FSOUND_SetSpeakerMode(int)
	 */
	public void FSOUND_SetPanSeperation(float pansep)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_SetPanSeperation(pointer, pansep);
	}

	/**
	 * Specify user callbacks for FMOD's internal file manipulation functions.<br>
	 * If ANY of these parameters are NULL, then FMOD will switch back to its own file routines.<br>
	 * You can replace this with memory routines (ie name can be cast to a memory address for example, then open sets up
	 * a handle based on this information), or alternate file routines, ie a WAD file reader.<br>
	 * <br><b>Remarks :</b><br>
	 * Memory loader FMOD functions are not affected, such as FMUSIC_LoadSongMemory etc.<br>
	 * WARNING : This function is dangerous in the wrong hands. You must return the right values, and each command must work properly, or FMOD will not function, or it may even crash if you give it invalid data.<br>
	 * You must support SEEK_SET, SEEK_CUR and SEEK_END properly, or FMOD will not work properly. See standard I/O help files on how these work under fseek().<br>
	 * Read the documentation in REMARKS and do exactly what it says. See the "simple" example for how it is used properly.<br>
	 * The MIDI loader does not support user file callbacks. For WAD type data structures with embedded MIDI files FMUSIC_LoadSongMemory will have to be used.<br>
	 * --------------<br>
	 * PlayStation 2 NOTE! This function takes IOP function pointers, not EE pointers! It is for custom IOP file systems not EE based ones.<br>
	 * This function can only be called after FSOUND_Init on PlayStation 2, not before.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, PlayStation 2, XBox, GameCube
	 * @param useropen Callback for opening a file.
	 * @param userclose Callback for closing a file.
	 * @param userread Callback for reading from a file.
	 * @param userseek Callback for seeking within a file..
	 * @param usertell Callback for returning the offset from the base of the open file in bytes.
	 * @see FMOD_INSTANCE#FMUSIC_LoadSong(String)
	 * @see FSOUND_CLOSECALLBACK
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FSOUND_OPENCALLBACK
	 * @see FSOUND_READCALLBACK
	 * @see FSOUND_SEEKCALLBACK
	 * @see FSOUND_TELLCALLBACK
	 */
	public void FSOUND_File_SetCallbacks(
		FSOUND_OPENCALLBACK useropen,
		FSOUND_CLOSECALLBACK userclose,
		FSOUND_READCALLBACK userread,
		FSOUND_SEEKCALLBACK userseek,
		FSOUND_TELLCALLBACK usertell)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_File_SetCallbacks(
			pointer,
			useropen,
			userclose,
			userread,
			userseek,
			usertell);
	}

	/**
	 * Returns an error code set by FMOD.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return error code, see FMOD_ERRORS
	 * @see FMOD_ERRORS
	 * @see FMOD_INSTANCE#FSOUND_GetAmplitude(int)
	 * @see FMOD_INSTANCE#FSOUND_GetFrequency(int)
	 * @see FMOD_INSTANCE#FSOUND_GetPan(int)
	 * @see FMOD_INSTANCE#FSOUND_GetPriority(int)
	 * @see FMOD_INSTANCE#FSOUND_GetVolume(int)
	 */
	public int FSOUND_GetError()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetError(pointer);
	}

	/**
	 * Returns the FMOD version number.<br>
	 * <br><b>Remarks :</b><br>
	 * Use this to compare the header you are using against the compiled DLL version to make sure your DLL is up to date.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return FMOD version number.
	 */
	public float FSOUND_GetVersion()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetVersion(pointer);
	}

	/**
	 * Returns the current id to the output type.<br>
	 * See FSOUND_OUTPUTTYPES for valid parameters and descriptions.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return id to output type
	 * @see FMOD_INSTANCE#FSOUND_GetOutputHandle()
	 * @see FSOUND_OUTPUTTYPES
	 * @see FMOD_INSTANCE#FSOUND_SetFrequency(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetOutput(int)
	 */
	public int FSOUND_GetOutput()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetOutput(pointer);
	}

	/**
	 * Returns a pointer to the system level output device module.<br>
	 * This means a pointer to a DIRECTSOUND, WINMM handle, or with NOSOUND output, NULL<br>
	 * <br><b>Remarks :</b><br>
	 * Must be called after FSOUND_Init or else it will fail.<br>
	 * Cast the resulting pointer depending on what output system pointer you are after.<br>
	 * FSOUND_OUTPUT_NOSOUND, NULL is returned.<br>
	 * FSOUND_OUTPUT_WINMM, HWAVEOUT is returned.<br>
	 * FSOUND_OUTPUT_DSOUND, LPDIRECTSOUND is returned.<br>
	 * FSOUND_OUTPUT_OSS, File handle is returned, (cast to int)<br>
	 * FSOUND_OUTPUT_ESD, Handle returned by so_esd_open_sound (cast to int). <br>
	 * FSOUND_OUTPUT_ALSA snd_pcm_t * is returned.<br>
	 * FSOUND_OUTPUT_MAC SndChannelPtr is returned.<br>
	 * FSOUND_OUTPUT_XBOX LPDIRECTSOUND is returned.<br>
	 * FSOUND_OUTPUT_PS2 NULL is returned.<br>
	 * FSOUND_OUTPUT_GC NULL is returned.<br>
	 * FSOUND_OUTPUT_NOSOUND_NONREALTIME NULL is returned.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox
	 * @return Pointer to output handle specific to the device.
	 * @see FMOD_INSTANCE#FSOUND_GetOutput()
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FSOUND_OUTPUTTYPES
	 * @see FMOD_INSTANCE#FSOUND_SetFrequency(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetOutput(int)
	 */
	public Pointer FSOUND_GetOutputHandle()
	{
		if(pointer == 0) throw new NullPointerException();
		long ptr = FmodDynJNI.FMOD_INSTANCE_FSOUND_GetOutputHandle(pointer);
		return (ptr == 0) ? null : Pointer.newPointer(ptr);
	}

	/**
	 * Returns the currently selected driver number.<br>
	 * Drivers are enumerated when selecting a driver with FSOUND_SetDriver or other driver related
	 * functions such as FSOUND_GetNumDrivers or FSOUND_GetDriverName<br>
	 * <br><b>Remarks :</b><br>
	 * Currently selected driver id.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2
	 * @return Currently selected driver id.
	 * @see FMOD_INSTANCE#FSOUND_GetDriver()
	 * @see FMOD_INSTANCE#FSOUND_GetDriverName(int)
	 * @see FMOD_INSTANCE#FSOUND_GetNumDrivers()
	 * @see FMOD_INSTANCE#FSOUND_SetDriver(int)
	 */
	public int FSOUND_GetDriver()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetDriver(pointer);
	}

	/**
	 * Returns the currently used mixer type.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return FSOUND_GetMixer returns a defenition from FSOUND_MIXERTYPES. See FSOUND_MIXERTYPES for valid parameters and descriptions.
	 * @see FMOD_INSTANCE#FSOUND_DSP_Create(FSOUND_DSPCALLBACK, int, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_DSP_MixBuffers(ByteBuffer, ByteBuffer, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_GetMixer()
	 * @see FSOUND_MIXERTYPES
	 * @see FMOD_INSTANCE#FSOUND_SetMixer(int)
	 */
	public int FSOUND_GetMixer()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetMixer(pointer);
	}

	/**
	 * Returns the number of sound cards or devices enumerated for the current output type. (Direct Sound, WaveOut etc.)
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return Total number of enumerated sound devices.
	 * @see FMOD_INSTANCE#FSOUND_GetDriver()
	 * @see FMOD_INSTANCE#FSOUND_GetDriverCaps(int, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_GetDriverName(int)
	 * @see FMOD_INSTANCE#FSOUND_SetDriver(int)
	 */
	public int FSOUND_GetNumDrivers()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetNumDrivers(pointer);
	}

	/**
	 * Returns the name of the selected driver.
	 * <br>Drivers are enumerated when selecting a driver with FSOUND_SetDriver or
	 * other driver related functions such as FSOUND_GetNumDrivers or  FSOUND_GetDriver<br>
	 * <br><b>Remarks :</b><br>
	 * If no driver is selected, the default driver is used.
	 * @param id Enumerated driver ID. This must be in a valid range delimited by FSOUND_GetNumDrivers,
	 * @return On success, a pointer to a NULL terminated string containing the name of the specified
	 * device is returned. The number of drivers enumerated can be found with FSOUND_GetNumDrivers.
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetDriver()
	 * @see FMOD_INSTANCE#FSOUND_GetDriverCaps(int, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_GetDriverName(int)
	 * @see FMOD_INSTANCE#FSOUND_SetDriver(int)
	 */
	public String FSOUND_GetDriverName(int id)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetDriverName(pointer, id);
	}

	/**
	 * Returns information on capabilities of the current output mode.<br>
	 * <br><b>Remarks :</b><br>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.<br>
	 * It must be called before FSOUND_Init.<br>
	 * FSOUND_SetOutput must be called to tell FMOD which output mode you are talking about.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param id Enumerated driver ID. This must be in a valid range delimited by FSOUND_GetNumDrivers,
	 * @param caps Pointer to an unsigned int to have the caps bits stored.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FSOUND_CAPS
	 * @see FMOD_INSTANCE#FSOUND_GetNumDrivers()
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetOutput(int)
	 */
	public boolean FSOUND_GetDriverCaps(int id, int[] caps)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetDriverCaps(pointer, id, caps);
	}
	/**
	 * Returns information on capabilities of the current output mode.<br>
	 * <br><b>Remarks :</b><br>
	 * This function cannot be called after FMOD is already activated with FSOUND_Init.<br>
	 * It must be called before FSOUND_Init.<br>
	 * FSOUND_SetOutput must be called to tell FMOD which output mode you are talking about.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param id Enumerated driver ID. This must be in a valid range delimited by FSOUND_GetNumDrivers,
	 * @param caps Pointer to an unsigned int to have the caps bits stored.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FSOUND_CAPS
	 * @see FMOD_INSTANCE#FSOUND_GetNumDrivers()
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetOutput(int)
	 */
	public boolean FSOUND_GetDriverCaps(int id, IntBuffer caps)
	{
		if(pointer == 0) throw new NullPointerException();
		if(caps != null && !caps.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetDriverCaps(pointer, id, caps, BufferUtils.getPositionInBytes(caps));
	}

	/**
	 * Returns the current mixing rate<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return Currently set output rate in HZ.
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 */
	public int FSOUND_GetOutputRate()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetOutputRate(pointer);
	}

	/**
	 * Returns the total number of channels allocated.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return Number of channels allocated
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 */
	public int FSOUND_GetMaxChannels()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetMaxChannels(pointer);
	}

	/**
	 * Returns the current maximum index for a sample.<br>
	 * This figure grows as you allocate more samples (in blocks)<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return Maximum sample index
	 */
	public int FSOUND_GetMaxSamples()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetMaxSamples(pointer);
	}

	/**
	 * Gets the mode for currently set speakermode.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 */
	public int FSOUND_GetSpeakerMode()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetSpeakerMode(pointer);
	}

	/**
	 * Returns the master volume for any sound effects played.<br>
	 * It specifically has SFX in the function name, as it does not affect music or CD volume.<br>
	 * This must also be altered with FMUSIC_SetMasterVolume.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return On success, the SFX master volume is returned. Valid ranges are from 0 (silent) to 255 (full volume)
	 * @see FMOD_INSTANCE#FMUSIC_SetMasterVolume(FMUSIC_MODULE, int)
	 */
	public int FSOUND_GetSFXMasterVolume()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetSFXMasterVolume(pointer);
	}

	/**
	 * Returns the number of available hardware mixed 2d and 3d channels.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param num2d Pointer to integer to store the number of available hardware mixed 2d channels.
	 * @param num3d Pointer to integer to store the number of available hardware mixed 3d channels.
	 * @param total Usually num2d + num3d, but on some platforms like PS2 and GameCube, this will be the same
	 * as num2d and num3d (and not the sum) because 2d and 3d voices share the same pool.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 */
	public boolean FSOUND_GetNumHWChannels(int[] num2d, int[] num3d, int[] total)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetNumHWChannels(pointer, num2d, num3d, total);
	}
	/**
	 * Returns the number of available hardware mixed 2d and 3d channels.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param num2d Pointer to integer to store the number of available hardware mixed 2d channels.
	 * @param num3d Pointer to integer to store the number of available hardware mixed 3d channels.
	 * @param total Usually num2d + num3d, but on some platforms like PS2 and GameCube, this will be the same
	 * as num2d and num3d (and not the sum) because 2d and 3d voices share the same pool.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 */
	public boolean FSOUND_GetNumHWChannels(IntBuffer num2d, IntBuffer num3d, IntBuffer total)
	{
		if(pointer == 0) throw new NullPointerException();
		if(num2d != null && !num2d.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(num3d != null && !num3d.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(total != null && !total.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetNumHWChannels(pointer, num2d, BufferUtils.getPositionInBytes(num2d),
				num3d, BufferUtils.getPositionInBytes(num3d),
				total,  BufferUtils.getPositionInBytes(total));
	}

	/**
	 * Returns the number of active channels in FSOUND, or ones that are playing.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return Number of active channels
	 * @see FMOD_INSTANCE#FMUSIC_OptimizeChannels(FMUSIC_MODULE, int, int)
	 */
	public int FSOUND_GetChannelsPlaying()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetChannelsPlaying(pointer);
	}

	/**
	 * Returns in percent of cpu time the amount of cpu usage that FSOUND/FMUSIC mixing is taking.<br>
	 * <br><b>Remarks :</b><br>
	 * This value represents the cpu usage used by streams, the software mixer, and subsequent calls to dsound waveout etc.<br>
	 * MIDI playback is not counted as it is performed by directx.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return floating point value between 0.0 and 100.0.
	 * @see FMOD_INSTANCE#FSOUND_DSP_Create(FSOUND_DSPCALLBACK, int, Pointer)
	 */
	public float FSOUND_GetCPUUsage()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetCPUUsage(pointer);
	}

	/**
	 * Returns information on the memory usage of fmod.<br>
	 * This is useful for determining a fixed memory size to make FMOD work within for fixed memory machines such as pocketpc and consoles.<br>
	 * <br><b>Remarks :</b><br>
	 * Note that if using FSOUND_SetMemorySystem, the memory usage will be slightly higher than without it, as fmod has to have a small amount of memory overhead to manage the available memory.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param currentalloced Currently allocated memory at time of call.
	 * @param maxalloced Maximum allocated memory since FSOUND_Init or FSOUND_SetMemorySystem
	 * @see FSOUND_ALLOCCALLBACK
	 * @see FSOUND_FREECALLBACK
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FSOUND_REALLOCCALLBACK
	 * @see FMOD_INSTANCE#FSOUND_SetMemorySystem(ByteBuffer, int, FSOUND_ALLOCCALLBACK, FSOUND_REALLOCCALLBACK, FSOUND_FREECALLBACK)
	 */
	public void FSOUND_GetMemoryStats(int[] currentalloced, int[] maxalloced)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_GetMemoryStats(pointer, currentalloced, maxalloced);
	}
	/**
	 * Returns information on the memory usage of fmod.<br>
	 * This is useful for determining a fixed memory size to make FMOD work within for fixed memory machines such as pocketpc and consoles.<br>
	 * <br><b>Remarks :</b><br>
	 * Note that if using FSOUND_SetMemorySystem, the memory usage will be slightly higher than without it, as fmod has to have a small amount of memory overhead to manage the available memory.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param currentalloced Currently allocated memory at time of call.
	 * @param maxalloced Maximum allocated memory since FSOUND_Init or FSOUND_SetMemorySystem
	 * @see FSOUND_ALLOCCALLBACK
	 * @see FSOUND_FREECALLBACK
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FSOUND_REALLOCCALLBACK
	 * @see FMOD_INSTANCE#FSOUND_SetMemorySystem(ByteBuffer, int, FSOUND_ALLOCCALLBACK, FSOUND_REALLOCCALLBACK, FSOUND_FREECALLBACK)
	 */
	public void FSOUND_GetMemoryStats(IntBuffer currentalloced, IntBuffer maxalloced)
	{
		if(pointer == 0) throw new NullPointerException();
		if(currentalloced != null && !currentalloced.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(maxalloced != null && !maxalloced.isDirect()) {
			throw new NonDirectBufferException();
		}
		FmodDynJNI.FMOD_INSTANCE_FSOUND_GetMemoryStats(pointer, currentalloced, BufferUtils.getPositionInBytes(currentalloced),
				maxalloced, BufferUtils.getPositionInBytes(maxalloced));
	}

	/**
	 * Loads and decodes a static soundfile into memory.<br>
	 * This includes such files as .WAV, .MP2, .MP3, .OGG, .RAW and others.<br>
	 * <br><b>Remarks :</b><br>
	 * FMOD has a sample management system that holds onto any samples loaded or allocated, and frees them all when you call FSOUND_Close. <br>
	 * It takes the hassle out of having to keep hold of a lot of sample handles and remember to free them all at the end of your application.<br>
	 * It is basically an expandle array of handles that holds each sample until FMOD closes down where it does a cleanup. <br>
	 * FSOUND_UNMANAGED can be used so FMOD does NOT use the sample management system. You have to make sure they are freed yourself.<br>
	 * --------<br>
	 * Specify FSOUND_LOADMEMORY to load a file from a memory image. <br>
	 * The pointer you pass to name must be the actual image of the data you want to load.<br>
	 * The length parameter is to be filled out if FSOUND_LOADMEMORY is specified, otherwise if you do not specify memory loading, can be safely ignored and should be set to 0.<br>
	 * --------<br>
	 * Compressed formats are expanded into memory. If the file is quite large, it could take a while to load.<br>
	 * --------<br>
	 * If FSOUND_8BITS is specified and the file decodes to 16bit normally, FMOD will downgrade the sample to 8bit.<br>
	 * --------<br>
	 * On PlayStation 2, the name_or_data pointer and length variables must be 16 byte aligned, for DMA reasons.<br>
	 * --------<br>
	 * Note that FSOUND_NONBLOCKING is NOT supported with this function.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param index Sample pool index. See remarks for more on the sample pool.
	 * 0 or above - The absolute index into the sample pool. The pool will grow as the index gets larger.
	 * If a slot is already used it will be replaced.
	 * FSOUND_FREE - Let FSOUND select an arbitrary sample slot.
	 * FSOUND_UNMANAGED - Dont have this sample managed within fsounds sample management system
	 * @param name_or_data Name of sound file or pointer to memory image to load.
	 * @param mode Description of the data format, OR in the bits defined in FSOUND_MODES to describe the data being loaded.
	 * @param offset Optional. 0 by default. If > 0, this value is used to specify an offset in a file, so fmod will seek before opening.<br>
	 * length must also be specified if this value is used.
	 * @param length Optional. 0 by default. If > 0, this value is used to specify the length of a memory block when using FSOUND_LOADMEMORY,
	 * or it is the length of a file or file segment if the offset parameter is used.<br>
	 * On PlayStation 2 this must be 16 byte aligned for memory loading.
	 * @return On success, a sample pointer is returned.<br>
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_Sample_Alloc(int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Free(FSOUND_SAMPLE)
	 */
	public FSOUND_SAMPLE FSOUND_Sample_Load(int index, String name_or_data, int mode, int offset, int length)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_Load(pointer, index, name_or_data, mode, offset, length);
		return (cPtr == 0) ? null : FSOUND_SAMPLE.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Loads and decodes a static soundfile into memory.<br>
	 * This includes such files as .WAV, .MP2, .MP3, .OGG, .RAW and others.<br>
	 * <br><b>Remarks :</b><br>
	 * FMOD has a sample management system that holds onto any samples loaded or allocated, and frees them all when you call FSOUND_Close. <br>
	 * It takes the hassle out of having to keep hold of a lot of sample handles and remember to free them all at the end of your application.<br>
	 * It is basically an expandle array of handles that holds each sample until FMOD closes down where it does a cleanup. <br>
	 * FSOUND_UNMANAGED can be used so FMOD does NOT use the sample management system. You have to make sure they are freed yourself.<br>
	 * --------<br>
	 * Specify FSOUND_LOADMEMORY to load a file from a memory image. <br>
	 * The pointer you pass to name must be the actual image of the data you want to load.<br>
	 * The length parameter is to be filled out if FSOUND_LOADMEMORY is specified, otherwise if you do not specify memory loading, can be safely ignored and should be set to 0.<br>
	 * --------<br>
	 * Compressed formats are expanded into memory. If the file is quite large, it could take a while to load.<br>
	 * --------<br>
	 * If FSOUND_8BITS is specified and the file decodes to 16bit normally, FMOD will downgrade the sample to 8bit.<br>
	 * --------<br>
	 * On PlayStation 2, the name_or_data pointer and length variables must be 16 byte aligned, for DMA reasons.<br>
	 * --------<br>
	 * Note that FSOUND_NONBLOCKING is NOT supported with this function.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param index Sample pool index. See remarks for more on the sample pool.
	 * 0 or above - The absolute index into the sample pool. The pool will grow as the index gets larger.
	 * If a slot is already used it will be replaced.
	 * FSOUND_FREE - Let FSOUND select an arbitrary sample slot.
	 * FSOUND_UNMANAGED - Dont have this sample managed within fsounds sample management system
	 * @param name_or_data Name of sound file or pointer to memory image to load.
	 * @param mode Description of the data format, OR in the bits defined in FSOUND_MODES to describe the data being loaded.
	 * @param offset Optional. 0 by default. If > 0, this value is used to specify an offset in a file, so fmod will seek before opening.<br>
	 * length must also be specified if this value is used.
	 * @param length Optional. 0 by default. If > 0, this value is used to specify the length of a memory block when using FSOUND_LOADMEMORY,
	 * or it is the length of a file or file segment if the offset parameter is used.<br>
	 * On PlayStation 2 this must be 16 byte aligned for memory loading.
	 * @return On success, a sample pointer is returned.<br>
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_Sample_Alloc(int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Free(FSOUND_SAMPLE)
	 */
	public FSOUND_SAMPLE FSOUND_Sample_Load(int index, ByteBuffer name_or_data, int mode, int offset, int length)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_Load(pointer, index, name_or_data, BufferUtils.getPositionInBytes(name_or_data), mode, offset, length);
		return (cPtr == 0) ? null : FSOUND_SAMPLE.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Allocates a new empty sample.<br>
	 * Used if you want to create a sample from scratch and fill the databuffer with your own data
	 * (using FSOUND_Sample_Lock or FSOUND_Sample_Upload), instead of just loading a file with FSOUND_Sample_Load.<br>
	 * <br><b>Remarks :</b><br>
	 * FMOD has a sample management system that holds onto any samples loaded or allocated, and frees them all when you call FSOUND_Close.<br>
	 * It takes the hassle out of having to keep hold of a lot of sample handles and remember to free them all at the end of your application.<br>
	 * It is basically an expandle array of handles that holds each sample until FMOD closes down where it does a cleanup.<br>
	 * FSOUND_UNMANAGED can be used NOT to use the sample management system.<br>
	 * ------------<br>
	 * FSOUND_Sample_Alloc is only nescessary for lower level operations with sample data.<br>
	 * Usually  FSOUND_Load does the work for you. lower level operations mean such things as uploading data from memory or
	 * your own compressed data for example.<br>
	 * You can create a new sample from scratch by doing the following operations<br>
	 * 1. Allocate a new sample with FSOUND_Sample_Alloc<br>
	 * 2. Write data to the sample buffer with FSOUND_Sample_Lock and FSOUND_Sample_Unlock, or FSOUND_Sample_Upload.<br>
	 * Note FSOUND_Sample_Lock only returns a pointer to the sample data, whereas
	 * FSOUND_Sample_Upload does a copy from data you give it, with format conversion to the  correct format.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param index Sample pool index. See remarks for more on the sample pool.
	 * 0 or above - The absolute index into fsounds sample pool. The pool will grow as
	 * the index gets larger. If a slot is already used it will be replaced.
	 * FSOUND_FREE - Let FSOUND select an arbitrary sample slot.
	 * FSOUND_UNMANAGED - Dont have fsound free this sample upon FSOUND_Close
	 * @param length Default volume for this sample.
	 * @param mode Bitfield describing various characteristics of the sample. Valid parameters are described in FSOUND_MODES.
	 * @param deffreq Default volume for this sample.
	 * @param defvol Default volume for this sample.
	 * @param defpan Default pan for this sample.
	 * @param defpri Default priority for this sample.
	 * @return On success, a pointer to an allocated sample is returned.<br>
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_Sample_Alloc(int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Free(FSOUND_SAMPLE)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Load(int, String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Lock(FSOUND_SAMPLE, int, int, Pointer, Pointer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaults(FSOUND_SAMPLE, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetLoopPoints(FSOUND_SAMPLE, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Unlock(FSOUND_SAMPLE, ByteBuffer, ByteBuffer, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Upload(FSOUND_SAMPLE, ByteBuffer, int)
	 */
	public FSOUND_SAMPLE FSOUND_Sample_Alloc(int index, int length, int mode,
		int deffreq, int defvol, int defpan, int defpri)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_Alloc(pointer, index, length,
				mode, deffreq, defvol, defpan, defpri);
		return (cPtr == 0) ? null : FSOUND_SAMPLE.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Removes a sample from memory and makes its slot available again.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr r to the sample definition to be freed.
	 * @see FMOD_INSTANCE#FSOUND_Sample_Alloc(int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Load(int, String, int, int, int)
	 */
	public void FSOUND_Sample_Free(FSOUND_SAMPLE sptr)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_Free(pointer, Pointer.getPointer(sptr));
	}

	/**
	 * This function uploads new sound data from memory to a preallocated/existing sample and does conversion based on the specified source mode.<br>
	 * If sample data already exists at this handle then it is replaced with the new data being uploaded.<br>
	 * <br><b>Remarks :</b><br>
	 * Note that on PlayStation 2 the source data address is an IOP address not an EE address. <br>
	 * To get data from EE RAM to the sample you must allocate some IOP memory, dma it to IOP memory then call upload.<br>
	 * There are helper functions in fmodps2.h to achieve this.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the destination sample
	 * @param srcdata Pointer to the source data to be uploaded. On PlayStation 2 this is an IOP address not an EE address.
	 * @param mode Description of the source data format. Bitwise OR in these bits to describe the data being passed in.<br>
	 * See FSOUND_MODES for valid parameters and descriptions.<br>
	 * FSOUND_HW3D, FSOUND_HW2D and FSOUND_LOOP modes are ignored, the mode describes the source format, not the destination format.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_Sample_Alloc(int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Lock(FSOUND_SAMPLE, int, int, Pointer, Pointer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Unlock(FSOUND_SAMPLE, ByteBuffer, ByteBuffer, int, int)
	 */
	public boolean FSOUND_Sample_Upload(FSOUND_SAMPLE sptr, ByteBuffer srcdata, int mode)
	{
		if(pointer == 0) throw new NullPointerException();
		if(srcdata != null && !srcdata.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_Upload(pointer, Pointer.getPointer(sptr),
			srcdata, BufferUtils.getPositionInBytes(srcdata), mode);
	}

	/**
	 * Returns a pointer to the beginning of the sample data for a sample.<br>
	 * Data written to these pointers must be signed.<br>
	 * <br><b>Remarks :</b><br>
	 * You must always unlock the data again after you have finished with it, using FSOUND_Sample_Unlock.<br>
	 * For PCM based samples, data must be signed 8 or 16bit. For compressed samples such as those created with FSOUND_IMAADPCM, FSOUND_VAG, FSOUND_GCADPCM, the data must be in its original compressed format.<br>
	 * On PlayStation 2, with FSOUND_HW2D or FSOUND_HW3D based samples, this function does not return a readable or writable buffer, it returns the SPU2 address of the sample. To send data to it you must call FSOUND_SendData.<br>
	 * On GameCube, with FSOUND_HW2D or FSOUND_HW3D based samples, this function will not return the data contained within the sample. It is for upload purposes only.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample definition.
	 * @param offset Offset in BYTES to the position you want to lock in the sample buffer.
	 * @param length Number of BYTES you want to lock in the sample buffer.
	 * @param ptr1 Address of a pointer that will point to the first part of the locked data.
	 * @param ptr2 Address of a pointer that will point to the second part of the locked data.<br>
	 * This will be NULL if the data locked hasnt wrapped at the end of the buffer.
	 * @param len1 Length of data in BYTES that was locked for ptr1
	 * @param len2 Length of data in BYTES that was locked for ptr2. <br>
	 * This will be 0 if the data locked hasnt wrapped at the end of the buffer.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Sample_Alloc(int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Unlock(FSOUND_SAMPLE, ByteBuffer, ByteBuffer, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Upload(FSOUND_SAMPLE, ByteBuffer, int)
	 */
	public boolean FSOUND_Sample_Lock(FSOUND_SAMPLE sptr, int offset, int length,
			Pointer ptr1, Pointer ptr2, int[] len1, int[] len2)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_Lock(pointer, Pointer.getPointer(sptr),
				offset, length, ptr1, ptr2, len1, len2);
	}
	/**
	 * Returns a pointer to the beginning of the sample data for a sample.<br>
	 * Data written to these pointers must be signed.<br>
	 * <br><b>Remarks :</b><br>
	 * You must always unlock the data again after you have finished with it, using FSOUND_Sample_Unlock.<br>
	 * For PCM based samples, data must be signed 8 or 16bit. For compressed samples such as those created with FSOUND_IMAADPCM, FSOUND_VAG, FSOUND_GCADPCM, the data must be in its original compressed format.<br>
	 * On PlayStation 2, with FSOUND_HW2D or FSOUND_HW3D based samples, this function does not return a readable or writable buffer, it returns the SPU2 address of the sample. To send data to it you must call FSOUND_SendData.<br>
	 * On GameCube, with FSOUND_HW2D or FSOUND_HW3D based samples, this function will not return the data contained within the sample. It is for upload purposes only.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample definition.
	 * @param offset Offset in BYTES to the position you want to lock in the sample buffer.
	 * @param length Number of BYTES you want to lock in the sample buffer.
	 * @param ptr1 Address of a pointer that will point to the first part of the locked data.
	 * @param ptr2 Address of a pointer that will point to the second part of the locked data.<br>
	 * This will be NULL if the data locked hasnt wrapped at the end of the buffer.
	 * @param len1 Length of data in BYTES that was locked for ptr1
	 * @param len2 Length of data in BYTES that was locked for ptr2. <br>
	 * This will be 0 if the data locked hasnt wrapped at the end of the buffer.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Sample_Alloc(int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Unlock(FSOUND_SAMPLE, ByteBuffer, ByteBuffer, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Upload(FSOUND_SAMPLE, ByteBuffer, int)
	 */
	public boolean FSOUND_Sample_Lock(
			FSOUND_SAMPLE sptr, int offset, int length,
			Pointer ptr1, Pointer ptr2,
			IntBuffer len1, IntBuffer len2)
	{
		if(pointer == 0) throw new NullPointerException();
		if(len1 != null && !len1.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(len2 != null && !len2.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_Lock(pointer, Pointer.getPointer(sptr),
				offset, length, ptr1, ptr2,
				len1, BufferUtils.getPositionInBytes(len1),
				len2, BufferUtils.getPositionInBytes(len2));
	}

	/**
	 * Releases previous sample data lock from FSOUND_Sample_Lock<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample definition.
	 * @param ptr1 Pointer to the 1st locked portion of sample data, from FSOUND_Sample_Lock.
	 * @param ptr2 Length of data in BYTES that was locked for ptr1
	 * @param len1 Length of data in BYTES that was locked for ptr1
	 * @param len2 Length of data in BYTES that was locked for ptr2.<br>
	 * This will be 0 if the data locked hasnt wrapped at the end of the buffer.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Sample_Alloc(int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Lock(FSOUND_SAMPLE, int, int, Pointer, Pointer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Upload(FSOUND_SAMPLE, ByteBuffer, int)
	 */
	public boolean FSOUND_Sample_Unlock(FSOUND_SAMPLE sptr, Pointer ptr1, Pointer ptr2, int len1, int len2)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_Unlock(pointer,
			Pointer.getPointer(sptr), Pointer.getPointer(ptr1),
			Pointer.getPointer(ptr2), len1, len2);
	}
	/**
	 * Releases previous sample data lock from FSOUND_Sample_Lock<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample definition.
	 * @param ptr1 Pointer to the 1st locked portion of sample data, from FSOUND_Sample_Lock.
	 * @param ptr2 Length of data in BYTES that was locked for ptr1
	 * @param len1 Length of data in BYTES that was locked for ptr1
	 * @param len2 Length of data in BYTES that was locked for ptr2.<br>
	 * This will be 0 if the data locked hasnt wrapped at the end of the buffer.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Sample_Alloc(int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Lock(FSOUND_SAMPLE, int, int, Pointer, Pointer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_Upload(FSOUND_SAMPLE, ByteBuffer, int)
	 */
	public boolean FSOUND_Sample_Unlock(FSOUND_SAMPLE sptr, ByteBuffer ptr1, ByteBuffer ptr2, int len1, int len2)
	{
		if(pointer == 0) throw new NullPointerException();
		if(ptr1 != null && !ptr1.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(ptr2 != null && !ptr2.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_Unlock(pointer, Pointer.getPointer(sptr),
			ptr1, BufferUtils.getPositionInBytes(ptr1),
			ptr2, BufferUtils.getPositionInBytes(ptr2),
			len1, len2);
	}

	/**
	 * Sets a sample's mode. This can only be FSOUND_LOOP_OFF,FSOUND_LOOP_NORMAL, FSOUND_LOOP_BIDI or FSOUND_2D.<br>
	 * You cannot change the description of the contents of a sample or its location.<br>
	 * FSOUND_2D will be ignored on the Win32 platform if FSOUND_HW3D was used to create the sample.<br>
	 * <br><b>Remarks :</b><br>
	 * Only the following modes are accepted, others will be filtered out. <br>
	 * FSOUND_LOOP_BIDI, FSOUND_LOOP_NORMAL, FSOUND_LOOP_OFF, FSOUND_2D.<br>
	 * Normally FSOUND_2D is accepted only if the sound is software mixed. If this is not set, the mode is set for the sample to be 3D processed.<br>
	 * -------------------<br>
	 * On Playstation 2, XBox and GameCube, FSOUND_HW2D and FSOUND_HW3D are supported, so you can change between the 2 at runtime.<br>
	 * -------------------<br>
	 * On Windows, samples created with FSOUND_HW3D or FSOUND_HW2D do not support FSOUND_LOOP_BIDI. This is a limitation of Direct X.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample to have the mode set.
	 * @param mode The mode bits to set from FSOUND_MODES.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetLoopMode(int)
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetLoopPoints(FSOUND_SAMPLE, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetMode(FSOUND_SAMPLE)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaults(FSOUND_SAMPLE, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetLoopPoints(FSOUND_SAMPLE, int, int)
	 */
	public boolean FSOUND_Sample_SetMode(FSOUND_SAMPLE sptr, int mode)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_SetMode(pointer, Pointer.getPointer(sptr), mode);
	}

	/**
	 * Sets a sample's loop points, specified in SAMPLES, not bytes.<br>
	 * <br><b>Remarks :</b><br>
	 * Samples created with FSOUND_HW3D and FSOUND_HW2D under the FSOUND_OUTPUT_DSOUND output mode do not support this function. <br>
	 * Loop points set on such a sample with be ignored, and the sample will loop in its entirety. This is a limitation of DirectSound.<br>
	 * On XBOX, GameCube and Playstation 2 hardware voices using compressed data (ie XADPCM, VAG or GCADPCM), these values will not be sample accurate, but will be rounded to the nearest compression block size.<br>
	 * On PlayStation 2, the loopend is ignored. The hardware cannot change the end address, so the loopend is always equivalent to length - 1 no matter what you set.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample to have its loop points set.
	 * @param loopstart The starting position of the sample loop
	 * @param loopend The end position of the sample loop
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned
	 * @see FMOD_INSTANCE#FSOUND_Sample_Alloc(int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetLoopPoints(FSOUND_SAMPLE, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMode(FSOUND_SAMPLE, int)
	 */
	public boolean FSOUND_Sample_SetLoopPoints(FSOUND_SAMPLE sptr, int loopstart, int loopend)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_SetLoopPoints(
			pointer,
			Pointer.getPointer(sptr),
			loopstart,
			loopend);
	}

	/**
	 * Sets a sample's default attributes, so when it is played it uses these values without having to specify them later.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr  Pointer to the sample to have its attributes set.
	 * @param deffreq Default sample frequency. The value here is specified in hz. -1 to ignore.
	 * @param defvol Default sample volume. This is a value from 0 to 255. -1 to ignore.
	 * @param defpan Default sample pan position. This is a value from 0 to 255 or FSOUND_STEREOPAN.
	 * @param defpri Default sample priority. This is a value from 0 to 255. -1 to ignore.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Sample_Alloc(int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetDefaults(FSOUND_SAMPLE, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetDefaultsEx(FSOUND_SAMPLE, IntBuffer, IntBuffer, IntBuffer, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetLoopPoints(FSOUND_SAMPLE, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetMode(FSOUND_SAMPLE)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaultsEx(FSOUND_SAMPLE, int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMode(FSOUND_SAMPLE, int)
	 * @see FMOD_INSTANCE#FSOUND_SetFrequency(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetPan(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetPriority(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetVolume(int, int)
	 */
	public boolean FSOUND_Sample_SetDefaults(FSOUND_SAMPLE sptr, int deffreq, int defvol, int defpan, int defpri)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_SetDefaults(
			pointer,
			Pointer.getPointer(sptr),
			deffreq,
			defvol,
			defpan,
			defpri);
	}

	/**
	 * Sets a sample's default attributes, so when it is played it uses these values without having to specify them later.<br>
	 * <br><b>Remarks :</b><br>
	 * Frequency, volume and pan variation values specify a +/- variation to the specified default frequency, volume and pan values i.e. with deffreq=44100, varfreq=2000 the actual frequency value used will be in the range 42100 -> 46100.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample to have its attributes set.
	 * @param deffreq Default sample frequency. The value here is specified in hz. -1 to ignore.
	 * @param defvol Default sample volume. This is a value from 0 to 255. -1 to ignore.
	 * @param defpan Default sample pan position. This is a value from 0 to 255 or FSOUND_STEREOPAN.
	 * @param defpri Default sample priority. This is a value from 0 to 255. -1 to ignore.
	 * @param varfreq Frequency variation in hz to apply to deffreq each time this sample is played. -1 to ignore.
	 * @param varvol Volume variation to apply to defvol each time this sample is played. -1 to ignore.
	 * @param varpan Pan variation to apply to defpan each time this sample is played. -1 to ignore.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetDefaults(FSOUND_SAMPLE, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetDefaultsEx(FSOUND_SAMPLE, IntBuffer, IntBuffer, IntBuffer, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetLoopPoints(FSOUND_SAMPLE, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetMode(FSOUND_SAMPLE)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaults(FSOUND_SAMPLE, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetFrequency(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetPan(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetPriority(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetVolume(int, int)
	 */
	public boolean FSOUND_Sample_SetDefaultsEx(
		FSOUND_SAMPLE sptr,
		int deffreq,
		int defvol,
		int defpan,
		int defpri,
		int varfreq,
		int varvol,
		int varpan)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_SetDefaultsEx(
			pointer,
			Pointer.getPointer(sptr),
			deffreq,
			defvol,
			defpan,
			defpri,
			varfreq,
			varvol,
			varpan);
	}

	/**
	 * Sets the minimum and maximum audible distance for a sample.<br>
	 * MinDistance is the minimum distance that the sound emitter will cease to continue growing louder at (as it approaches the listener).<br>
	 * Within the mindistance it stays at the constant loudest volume possible. Outside of this mindistance it begins to attenuate.<br>
	 * MaxDistance is the distance a sound stops attenuating at. Beyond this point it will stay at the volume it would be
	 * at maxdistance units from the listener and will not attenuate any more.<br>
	 * MinDistance is useful to give the impression that the sound is loud or soft in 3d space. An example of this is a small quiet object,
	 * such as a bumblebee, which you could set a mindistance of to 0.1 for example, which would cause it to attenuate quickly and dissapear
	 * when only a few meters away from the listener.<br>
	 * Another example is a jumbo jet, which you could set to a mindistance of 100.0, which would keep the sound volume at max until the
	 * listener was 100 meters away, then it would be hundreds of meters more before it would fade out.<br>
	 * -------<br>
	 * In summary, increase the mindistance of a sound to make it 'louder' in a 3d world, and decrease it to make it 'quieter' in a 3d world.<br>
	 * maxdistance is effectively obsolete unless you need the sound to stop fading out at a certain point.<br>
	 * Do not adjust this from the default if you dont need to.<br>
	 * Some people have the confusion that maxdistance is the point the sound will fade out to, this is not the case.<br>
	 * <br><b>Remarks :</b><br>
	 * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. <br>
	 * See FSOUND_3D_SetDistanceFactor for more on this.<br>
	 * The default units for minimum and maximum distances are 1.0 and 1000000000.0f.<br>
	 * Volume drops off at mindistance / distance.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr The sample to have its minimum and maximum distance set.
	 * @param min The samples minimum volume distance in "units". See remarks for more on units.
	 * @param max The samples maximum volume distance in "units". See remarks for more on units.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_3D_GetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_GetMinMaxDistance(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDistanceFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetMinMaxDistance(int, float, float)
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetMinMaxDistance(FSOUND_SAMPLE, FloatBuffer, FloatBuffer)
	 */
	public boolean FSOUND_Sample_SetMinMaxDistance(FSOUND_SAMPLE sptr, float min, float max)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_SetMinMaxDistance(
			pointer,
			Pointer.getPointer(sptr),
			min,
			max);
	}

	/**
	 * Sets the maximum number of times a sample can play back at once.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample to have its playback behaviour changed.
	 * @param max
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_PlaySound(int, FSOUND_SAMPLE)
	 */
	public boolean FSOUND_Sample_SetMaxPlaybacks(FSOUND_SAMPLE sptr, int max)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_SetMaxPlaybacks(pointer, Pointer.getPointer(sptr), max);
	}

	/**
	 * Returns a pointer to a managed sample based on the index passed.<br>
	 * <br><b>Remarks :</b><br>
	 * Samples that are not created with FSOUND_UNMANAGED are stored in a table inside FMOD.<br>
	 * This way when FMOD can free all samples when FSOUND_Close is called and the user doesnt have to worry about cleaning up memory.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sampno The index in the sample management pool of the requested sample.
	 * @return Pointer to a sample.
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 */
	public FSOUND_SAMPLE FSOUND_Sample_Get(int sampno)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_Get(pointer, sampno);
		return (cPtr == 0) ? null : FSOUND_SAMPLE.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Returns a pointer to a NULL terminated string containing the sample's name.<br>
	 * <br><b>Remarks :</b><br>
	 * The name may not always make sense. From a MOD file it contains the sample text entered by the musician.<br>
	 * For non descriptive file formats it just contains the filename.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample to get the name from.
	 * @return On success, the name of the sample is returned.
	 * On failure, NULL is returned.
	 */
	public String FSOUND_Sample_GetName(FSOUND_SAMPLE sptr)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_GetName(pointer, Pointer.getPointer(sptr));
	}

	/**
	 * Returns the length of the sample in SAMPLES<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample to get the length from.
	 * @return On success, the length of sample in SAMPLES is returned.<br>
	 * On failure, 0 is returned.
	 */
	public int FSOUND_Sample_GetLength(FSOUND_SAMPLE sptr)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_GetLength(pointer, Pointer.getPointer(sptr));
	}

	/**
	 * Returns the start and end positions of the specified sample loop in SAMPLES (not bytes)<br>
	 * <br><b>Remarks :</b><br>
	 * Passing NULL in any of these parameters will result in the value being ignored.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample to get the loop point information from.
	 * @param loopstart Pointer to value to be filled with the sample loop start point. Can be NULL.
	 * @param loopend Pointer to value to be filled with the sample loop end point. Can be NULL.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaults(FSOUND_SAMPLE, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaultsEx(FSOUND_SAMPLE, int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetLoopPoints(FSOUND_SAMPLE, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMode(FSOUND_SAMPLE, int)
	 */
	public boolean FSOUND_Sample_GetLoopPoints(FSOUND_SAMPLE sptr, int[] loopstart, int[] loopend)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_GetLoopPoints(
			pointer,
			Pointer.getPointer(sptr),
			loopstart,
			loopend);
	}
	/**
	 * Returns the start and end positions of the specified sample loop in SAMPLES (not bytes)<br>
	 * <br><b>Remarks :</b><br>
	 * Passing NULL in any of these parameters will result in the value being ignored.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample to get the loop point information from.
	 * @param loopstart Pointer to value to be filled with the sample loop start point. Can be NULL.
	 * @param loopend Pointer to value to be filled with the sample loop end point. Can be NULL.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaults(FSOUND_SAMPLE, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaultsEx(FSOUND_SAMPLE, int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetLoopPoints(FSOUND_SAMPLE, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMode(FSOUND_SAMPLE, int)
	 */
	public boolean FSOUND_Sample_GetLoopPoints(FSOUND_SAMPLE sptr, IntBuffer loopstart, IntBuffer loopend)
	{
		if(pointer == 0) throw new NullPointerException();
		if(loopstart != null && !loopstart.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(loopend != null && !loopend.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_GetLoopPoints(
			pointer,
			Pointer.getPointer(sptr),
			loopstart, BufferUtils.getPositionInBytes(loopstart),
			loopend, BufferUtils.getPositionInBytes(loopend));
	}

	/**
	 * Returns the default volume, frequency, pan and priority values for the specified sample.<br>
	 * <br><b>Remarks :</b><br>
	 * Passing NULL in any of these parameters will result in the value being ignored.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample to get the default information from.
	 * @param deffreq Pointer to value to be filled with the sample default frequency. Can be NULL.
	 * @param defvol Pointer to value to be filled with the sample default volume. Can be NULL.
	 * @param defpan Pointer to value to be filled with the sample default priority. Can be NULL.
	 * @param defpri Pointer to value to be filled with the sample default priority. Can be NULL.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned<br>
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetDefaultsEx(FSOUND_SAMPLE, IntBuffer, IntBuffer, IntBuffer, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaults(FSOUND_SAMPLE, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaultsEx(FSOUND_SAMPLE, int, int, int, int, int, int, int)
	 */
	public boolean FSOUND_Sample_GetDefaults(
		FSOUND_SAMPLE sptr, int[] deffreq, int[] defvol, int[] defpan, int[] defpri)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_GetDefaults(
			pointer,
			Pointer.getPointer(sptr),
			deffreq,
			defvol,
			defpan,
			defpri);
	}
	/**
	 * Returns the default volume, frequency, pan and priority values for the specified sample.<br>
	 * <br><b>Remarks :</b><br>
	 * Passing NULL in any of these parameters will result in the value being ignored.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample to get the default information from.
	 * @param deffreq Pointer to value to be filled with the sample default frequency. Can be NULL.
	 * @param defvol Pointer to value to be filled with the sample default volume. Can be NULL.
	 * @param defpan Pointer to value to be filled with the sample default priority. Can be NULL.
	 * @param defpri Pointer to value to be filled with the sample default priority. Can be NULL.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned<br>
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetDefaultsEx(FSOUND_SAMPLE, IntBuffer, IntBuffer, IntBuffer, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaults(FSOUND_SAMPLE, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaultsEx(FSOUND_SAMPLE, int, int, int, int, int, int, int)
	 */
	public boolean FSOUND_Sample_GetDefaults(
		FSOUND_SAMPLE sptr, IntBuffer deffreq, IntBuffer defvol,
		IntBuffer defpan, IntBuffer defpri)
	{
		if(pointer == 0) throw new NullPointerException();
		if(deffreq != null && !deffreq.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(defvol != null && !defvol.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(defpan != null && !defpan.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(defpri != null && !defpri.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_GetDefaults(
			pointer,
			Pointer.getPointer(sptr),
			deffreq, BufferUtils.getPositionInBytes(deffreq),
			defvol, BufferUtils.getPositionInBytes(defvol),
			defpan, BufferUtils.getPositionInBytes(defpan),
			defpri, BufferUtils.getPositionInBytes(defpri));
	}

	/**
	 * Returns the default volume, frequency, pan, priority and random playback variations for the specified sample.<br>
	 * <br><b>Remarks :</b><br>
	 * Passing NULL in any of these parameters will result in the value being ignored.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample to get the default information from.
	 * @param deffreq Pointer to value to be filled with the sample default frequency. Can be NULL.
	 * @param defvol Pointer to value to be filled with the sample default volume. Can be NULL.
	 * @param defpan Pointer to value to be filled with the sample default pan. Can be NULL.
	 * @param defpri Pointer to value to be filled with the sample default priority. Can be NULL.
	 * @param varfreq Pointer to value to be filled with the sample random frequency variance. Can be NULL.
	 * @param varvol Pointer to value to be filled with the sample random volume variance. Can be NULL.
	 * @param varpan Pointer to value to be filled with the sample random pan variance. Can be NULL.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetDefaults(FSOUND_SAMPLE, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaults(FSOUND_SAMPLE, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaultsEx(FSOUND_SAMPLE, int, int, int, int, int, int, int)
	 */
	public boolean FSOUND_Sample_GetDefaultsEx(
		FSOUND_SAMPLE sptr, int[] deffreq, int[] defvol, int[] defpan, int[] defpri,
		int[] varfreq, int[] varvol, int[] varpan)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_GetDefaultsEx(
			pointer,
			Pointer.getPointer(sptr),
			deffreq,
			defvol,
			defpan,
			defpri,
			varfreq,
			varvol,
			varpan);
	}
	/**
	 * Returns the default volume, frequency, pan, priority and random playback variations for the specified sample.<br>
	 * <br><b>Remarks :</b><br>
	 * Passing NULL in any of these parameters will result in the value being ignored.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample to get the default information from.
	 * @param deffreq Pointer to value to be filled with the sample default frequency. Can be NULL.
	 * @param defvol Pointer to value to be filled with the sample default volume. Can be NULL.
	 * @param defpan Pointer to value to be filled with the sample default pan. Can be NULL.
	 * @param defpri Pointer to value to be filled with the sample default priority. Can be NULL.
	 * @param varfreq Pointer to value to be filled with the sample random frequency variance. Can be NULL.
	 * @param varvol Pointer to value to be filled with the sample random volume variance. Can be NULL.
	 * @param varpan Pointer to value to be filled with the sample random pan variance. Can be NULL.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetDefaults(FSOUND_SAMPLE, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaults(FSOUND_SAMPLE, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaultsEx(FSOUND_SAMPLE, int, int, int, int, int, int, int)
	 */
	public boolean FSOUND_Sample_GetDefaultsEx(
			FSOUND_SAMPLE sptr, IntBuffer deffreq, IntBuffer defvol, IntBuffer defpan, IntBuffer defpri,
			IntBuffer varfreq, IntBuffer varvol, IntBuffer varpan)
	{
		if(pointer == 0) throw new NullPointerException();
		if(deffreq != null && !deffreq.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(defvol != null && !defvol.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(defpan != null && !defpan.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(defpri != null && !defpri.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(varfreq != null && !varfreq.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(varvol != null && !varvol.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(varpan != null && !varpan.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_GetDefaultsEx(
			pointer,
			Pointer.getPointer(sptr),
			deffreq, BufferUtils.getPositionInBytes(deffreq),
			defvol, BufferUtils.getPositionInBytes(defvol),
			defpan, BufferUtils.getPositionInBytes(defpan),
			defpri, BufferUtils.getPositionInBytes(defpri),
			varfreq, BufferUtils.getPositionInBytes(varfreq),
			varvol, BufferUtils.getPositionInBytes(varvol),
			varpan, BufferUtils.getPositionInBytes(varpan));
	}

	/**
	 * Returns a bitfield containing information about the specified sample. <br>
	 * The values can be bitwise AND'ed with the values contained in FSOUND_MODES to see if certain criteria are true or not. <br>
	 * Information that can be retrieved from the same in this field are loop type, bitdepth and stereo/mono.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr Pointer to the sample to get the mode information on
	 * @return On success, the sample mode is returned.<br>
	 * On failure, 0 is returned.
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaults(FSOUND_SAMPLE, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaultsEx(FSOUND_SAMPLE, int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMode(FSOUND_SAMPLE, int)
	 * @see FMOD_INSTANCE#FSOUND_SetLoopMode(int, int)
	 */
	public int FSOUND_Sample_GetMode(FSOUND_SAMPLE sptr)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_GetMode(pointer, Pointer.getPointer(sptr));
	}

	/**
	 * Get the minimum and maximum audible distance for a sample.
	 * <br><b>Remarks :</b><br>
	 * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. <br>
	 * See FSOUND_3D_SetDistanceFactor for more on this.<br>
	 * The default units for minimum and maximum distances are 1.0 and 1000000000.0f.<br>
	 * Volume drops off at mindistance / distance.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr The sample to get minimum and maximum audible distance information from.
	 * @param min Pointer to value to be filled with the sample's minimum volume distance. See remarks for more on units.
	 * @param max Pointer to value to be filled with the sample's maximum volume distance. See remarks for more on units.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDistanceFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMinMaxDistance(FSOUND_SAMPLE, float, float)
	 */
	public boolean FSOUND_Sample_GetMinMaxDistance(FSOUND_SAMPLE sptr, float[] min, float[] max)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_GetMinMaxDistance(
			pointer,
			Pointer.getPointer(sptr),
			min,
			max);
	}
	/**
	 * Get the minimum and maximum audible distance for a sample.
	 * <br><b>Remarks :</b><br>
	 * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. <br>
	 * See FSOUND_3D_SetDistanceFactor for more on this.<br>
	 * The default units for minimum and maximum distances are 1.0 and 1000000000.0f.<br>
	 * Volume drops off at mindistance / distance.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param sptr The sample to get minimum and maximum audible distance information from.
	 * @param min Pointer to value to be filled with the sample's minimum volume distance. See remarks for more on units.
	 * @param max Pointer to value to be filled with the sample's maximum volume distance. See remarks for more on units.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDistanceFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMinMaxDistance(FSOUND_SAMPLE, float, float)
	 */
	public boolean FSOUND_Sample_GetMinMaxDistance(FSOUND_SAMPLE sptr, FloatBuffer min, FloatBuffer max)
	{
		if(pointer == 0) throw new NullPointerException();
		if(min != null && !min.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(max != null && !max.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Sample_GetMinMaxDistance(
			pointer,
			Pointer.getPointer(sptr),
			min, BufferUtils.getPositionInBytes(min),
			max, BufferUtils.getPositionInBytes(max));
	}

	/**
	 * Plays a sample in a specified channel, using the sample's default frequency, volume and pan settings.<br>
	 * <br><b>Remarks :</b><br>
	 * If you play a FSOUND_HW3D declared sample with this function, then the position and velocity
	 * are set to those of the listener. Other attributes such as volume, frequency and pan are taken
	 * from the sample's default volume, frequency, pan etc.<br>
	 * ----------<br>
	 * The channel handle :<br>
	 * The return value is reference counted. This stops the user from updating a stolen channel.<br>
	 * Basically it means the only sound you can change the attributes (ie volume/pan/frequency/3d position) for are the one you specifically called playsound for.<br>
	 * If another sound steals that channel, and you keep trying to change its attributes (ie volume/pan/frequency/3d position), it will do nothing.<br>
	 * This is great if you have sounds being updated from tasks and you just forget about it.<br>
	 * You can keep updating the sound attributes and if another task steals that channel, your original task wont change the attributes of the new sound!!!<br>
	 * The lower 12 bits contain the channel number. (yes this means a 4096 channel limit for FMOD :)<br>
	 * The upper 19 bits contain the reference count.<br>
	 * The top 1 bit is the sign bit.<br>
	 * ie<br>
	 * S RRRRRRRRRRRRRRRRRRR CCCCCCCCCCCC<br>
	 * ----------<br>
	 * Remember if not using FSOUND_FREE, then the channel pool is split up into software and hardware channels.<br>
	 * Software channels occupy the first n indicies specified by the value passed into FSOUND_Init.<br>
	 * Hardware channels occupy the next n indicies after this, and can be a variable amount, depending on the hardware.<br>
	 * Use FSOUND_GetNumHardwareChannels to query how many channels are available in hardware.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel 0+ <br>
	 * The absolute channel number in the channel pool.<br>
	 * Remember software channels come first, followed by hardware channels. <br>
	 * You cannot play a software sample on a hardware channel and vice versa.<br>
	 * FSOUND_FREE<br>
	 * Chooses a free channel to play in. If all channels are used then it
	 * selects a channel with a sample playing that has an EQUAL or LOWER priority
	 * than the sample to be played.<br>
	 * FSOUND_ALL<br>
	 * Passing this will cause ALL channels to play. (note this will make things VERY noisy!)<br>
	 * If FSOUND_ALL is used the last channel success flag will be returned.
	 * @param sptr Pointer to the sample to be played.
	 * @return On success, the channel handle that was selected is returned.<br>
	 * On failure, -1 is returned.
	 * @see FMOD_INSTANCE#FSOUND_FX_Enable(int, int)
	 * @see FMOD_INSTANCE#FSOUND_GetNumHWChannels(IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_GetNumSubChannels(int)
	 * @see FMOD_INSTANCE#FSOUND_GetSubChannel(int, int)
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_PlaySoundEx(int, FSOUND_SAMPLE, FSOUND_DSPUNIT, boolean)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMaxPlaybacks(FSOUND_SAMPLE, int)
	 * @see FMOD_INSTANCE#FSOUND_StopSound(int)
	 */
	public int FSOUND_PlaySound(int channel, FSOUND_SAMPLE sptr)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_PlaySound(pointer, channel, Pointer.getPointer(sptr));
	}

	/**
	 * Extended featured version of FSOUND_PlaySound.<br>
	 * New functionality includes the ability to start the sound paused. <br>
	 * This allows attributes of a channel to be set freely before the sound actually starts playing, until FSOUND_SetPaused(FALSE) is used.<br>
	 * Also added is the ability to associate the channel to a specified DSP unit. This allows the user to 'group' channels into seperate
	 * DSP units, which allows effects to be inserted between these 'groups', and allow various things like having one group affected by
	 * reverb (wet mix) and another group of channels unaffected (dry). <br>
	 * This is useful to seperate things like music from being affected by DSP effects, while other sound effects are.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this will cause ALL channels to play. (note this could make things VERY noisy!)<br>
	 * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.<br>
	 * ----------<br>
	 * The channel handle :<br>
	 * The return value is reference counted. This stops the user from updating a stolen channel.<br>
	 * This means the only sound you can change the attributes (ie volume/pan/frequency/3d position) for are the
	 * one you specifically called playsound for. If another sound steals that channel, and you keep trying to
	 * change its attributes (ie volume/pan/frequency/3d position), it will do nothing.<br>
	 * This is great if you have sounds being updated from tasks and you just forget about it.<br>
	 * You can keep updating the sound attributes and if another task steals that channel, your original task
	 * wont change the attributes of the new sound!!!<br>
	 * The lower 12 bits contain the channel number. (yes this means a 4096 channel limit for FMOD :)<br>
	 * The upper 19 bits contain the reference count.<br>
	 * The top 1 bit is the sign bit.<br>
	 * ie<br>
	 * S RRRRRRRRRRRRRRRRRRR CCCCCCCCCCCC<br>
	 * ----------<br>
	 * Remember if not using FSOUND_FREE, then the channel pool is split up into software and hardware channels.<br>
	 * Software channels occupy the first n indicies specified by the value passed into FSOUND_Init.<br>
	 * Hardware channels occupy the next n indicies after this, and can be a variable amount, depending on the hardware.<br>
	 * Use FSOUND_GetNumHardwareChannels to query how many channels are available in hardware.<br>
	 * ----------<br>
	 * If you attach a sound to a DSP unit (for grouping purposes), the callback for the DSP unit will be overwritten with
	 * fmod's internal mixer callback, so the callback the user supplied is rendered obsolete and is not called.<br>
	 * Also, do not attach sounds to system DSP units, the assignment will be ignored if you do.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel 0+ <br>
	 * The absolute channel number in the channel pool. <br>
	 * Remember software channels come first, followed by hardware channels. <br>
	 * You cannot play a software sample on a hardware channel and vice versa.<br>
	 * FSOUND_FREE<br>
	 * Chooses a free channel to play in. If all channels are used then it
	 * selects a channel with a sample playing that has an EQUAL or LOWER priority
	 * than the sample to be played.<br>
	 * FSOUND_ALL <br>
	 * Plays the sound on all channels.<br>
	 * @param sptr Pointer to the sample to be played.
	 * @param dsp Optional. NULL by default. Pointer to a dsp unit to attach the channel to for channel grouping.
	 * Only attach a sound to a user created DSP unit, and not a system DSP unit.
	 * @param startpaused Start the sound paused or not. Pausing the sound allows attributes to be set before the sound starts.
	 * @return On success, the channel handle that was selected is returned.<br>
	 * On failure, -1 is returned.
	 * @see FMOD_INSTANCE#FSOUND_DSP_Create(FSOUND_DSPCALLBACK, int, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_FX_Enable(int, int)
	 * @see FMOD_INSTANCE#FSOUND_GetNumSubChannels(int)
	 * @see FMOD_INSTANCE#FSOUND_GetSubChannel(int, int)
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_PlaySound(int, FSOUND_SAMPLE)
	 * @see FMOD_INSTANCE#FSOUND_SetPaused(int, boolean)
	 * @see FMOD_INSTANCE#FSOUND_StopSound(int)
	 */
	public int FSOUND_PlaySoundEx(int channel, FSOUND_SAMPLE sptr, FSOUND_DSPUNIT dsp, boolean startpaused)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_PlaySoundEx(
			pointer,
			channel,
			Pointer.getPointer(sptr),
			Pointer.getPointer(dsp),
			startpaused);
	}

	/**
	 * Stops a specified sound channel from playing, and frees it up for re-use.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this will cause ALL channels to stop.<br>
	 * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to stop. FSOUND_ALL can also be used (see remarks)
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_PlaySound(int, FSOUND_SAMPLE)
	 * @see FMOD_INSTANCE#FSOUND_PlaySoundEx(int, FSOUND_SAMPLE, FSOUND_DSPUNIT, boolean)
	 */
	public boolean FSOUND_StopSound(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_StopSound(pointer, channel);
	}

	/**
	 * Sets a channels frequency or playback rate, in HZ.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported here. Passing this will set ALL channels to specified frequency.<br>
	 * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.<br>
	 * Negative frequencies make the sound play backwards, so FSOUND_SetCurrentPosition would be needed to set the sound to the right position. <br>
	 * ---------<br>
	 * NOTE: FSOUND_HW3D limitations in Direct Sound.<br>
	 * Every hardware device has a minimum and maximum frequency. You can determine these by looking at the DirectSound caps and viewing the members
	 * dwMaxSecondarySampleRate and dwMaxSecondarySampleRate.<br>
	 * FMOD clamps frequencies to these values when playing back on hardware, so if you are setting the frequency outside of this range, the frequency
	 * will stay at either dwMinSecondarySampleRate or dwMaxSecondarySampleRate.<br>
	 * To find out these two values in fmod (maybe whether to decide to drop back to software mixing or not), you can use the following code.<br>
	 * ---------<br>
	 * include <dsound.h><br>
	 * int minhwfreq, maxhwfreq;<br>
	 * if (FSOUND_GetNumHardwareChannels() && FSOUND_GetOutput() == FSOUND_OUTPUT_DSOUND)<br>
	 * {<br>
	 *    HRESULT hr;<br>
	 *    DSCAPS dscaps;<br>
	 *    memset(&dscaps, 0, sizeof(DSCAPS));<br>
	 *    dscaps.dwSize = sizeof(DSCAPS);<br>
	 *    hr = IDirectSound_GetCaps((LPDIRECTSOUND)FSOUND_GetOutputHandle(), &dscaps);<br>
	 *    minhwfreq = dscaps.dwMinSecondarySampleRate;<br>
	 *    maxhwfreq = dscaps.dwMaxSecondarySampleRate;<br>
	 * }<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to change the frequency for. FSOUND_ALL can also be used (see remarks).
	 * @param freq The frequency to set. Valid ranges are from 100 to 705600, and -100 to -705600.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_DSP_MixBuffers(ByteBuffer, ByteBuffer, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_GetFrequency(int)
	 * @see FMOD_INSTANCE#FSOUND_GetOutput()
	 * @see FMOD_INSTANCE#FSOUND_GetOutputHandle()
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaults(FSOUND_SAMPLE, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaultsEx(FSOUND_SAMPLE, int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetCurrentPosition(int, int)
	 */
	public boolean FSOUND_SetFrequency(int channel, int freq)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetFrequency(pointer, channel, freq);
	}

	/**
	 * Sets a channels volume linearly.<br>
	 * This function IS affected by FSOUND_SetSFXMasterVolume.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this will set the volume of ALL channels available.<br>
	 * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to change the volume for. FSOUND_ALL can also be used (see remarks)
	 * @param vol The volume to set. Valid ranges are from 0 (silent) to 255 (full volume)
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_DSP_MixBuffers(ByteBuffer, ByteBuffer, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_GetAmplitude(int)
	 * @see FMOD_INSTANCE#FSOUND_GetSubChannel(int, int)
	 * @see FMOD_INSTANCE#FSOUND_GetVolume(int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaults(FSOUND_SAMPLE, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaultsEx(FSOUND_SAMPLE, int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetSFXMasterVolume(int)
	 * @see FMOD_INSTANCE#FSOUND_SetVolumeAbsolute(int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_PlayEx(int, FSOUND_STREAM, FSOUND_DSPUNIT, boolean)
	 */
	public boolean FSOUND_SetVolume(int channel, int vol)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetVolume(pointer, channel, vol);
	}

	/**
	 * Sets a channels volume linearly.<br>
	 * This function is NOT affected by master volume.<br>
	 * This function is used when you want to quiet everything down using FSOUND_SetSFXMasterVolume, but make a channel prominent.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this will set the absolute volume of ALL channels available.<br>
	 * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.<br>
	 * -------------<br>
	 * A good example of this function being used for a game needing a voice over.<br>
	 * If all the background sounds were too loud and drowned out the voice over, there is no way to
	 * feasibly go through all the sfx channels and lower the background noise volumes (some might be allocated by music). <br>
	 * Simply lower the background noise with FSOUND_SetSFXMasterVolume, and use FSOUND_SetVolumeAbsolute to bring
	 * up the volume of the voice over to full, and you will get one channel standing out amongst the rest.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to change the volume for. FSOUND_ALL can also be used (see remarks)
	 * @param vol The volume to set. Valid ranges are from 0 (silent) to 255 (full volume)
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetVolume(int)
	 * @see FMOD_INSTANCE#FSOUND_SetSFXMasterVolume(int)
	 * @see FMOD_INSTANCE#FSOUND_SetVolume(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetVolumeAbsolute(int, int)
	 */
	public boolean FSOUND_SetVolumeAbsolute(int channel, int vol)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetVolumeAbsolute(pointer, channel, vol);
	}

	/**
	 * Sets a channels pan position linearly<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this will set the pan of ALL channels available.<br>
	 * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.<br>
	 * ----------<br>
	 * Important : If you are playing a STEREO sample, and using normal middle panning, it will only come out at half the volume
	 * they are supposed to. To avoid this use FSOUND_STEREO pan.<br>
	 * Panning works in the following manner:<br>
	 * full left : 100to left, 0to right<br>
	 * full right : 0to left, 100to right<br>
	 * middle : 71to left, 71to right<br>
	 * FMOD Uses 'constant power' panning. The center position is 71 4743104n each channel as it keeps an even RMS output level when
	 * moving the sound from left to right. Placing 50 4743104n each channel for a middle position is incorrect.<br>
	 * The pan graph for constant power panning resembles a curve instead of straight lines.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to change the pan for. FSOUND_ALL can also be used (see remarks)
	 * @param pan The panning position for this channel to set.
	 * parameters are:
	 * - from 0 (full left) to 255 (full right)
	 * - FSOUND_STEREOPAN. This is meant for stereo samples, but will work on mono
	 * samples as well. It makes both left and right FULL volume instead of 50/50
	 * as middle panning does. See remarks section for more information on this.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_DSP_MixBuffers(ByteBuffer, ByteBuffer, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_GetPan(int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaults(FSOUND_SAMPLE, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaultsEx(FSOUND_SAMPLE, int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_PlayEx(int, FSOUND_STREAM, FSOUND_DSPUNIT, boolean)
	 */
	public boolean FSOUND_SetPan(int channel, int pan)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetPan(pointer, channel, pan);
	}

	/**
	 * Sets a channels surround sound status. This surround sound is a fake dolby trick that
	 * effectively pans the channel to the center, but inverts the waveform in one speaker to
	 * make it sound fuller or spacier, or like it is coming out of space between the 2 speakers.<br>
	 * Panning is ignored while surround is in effect.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this will set the surround sound mode of ALL channels available.<br>
	 * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.<br>
	 * ---------<br>
	 * Note this only works on software channels.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation2, GameCube
	 * @param channel The channel number/handle to change the surround for. FSOUND_ALL can also be used (see remarks).
	 * @param surround Toggle value - TRUE enables surround sound on the channel, FALSE disables it.
	 * @return On success, TRUE is returned.
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetSurround(int)
	 */
	public boolean FSOUND_SetSurround(int channel, boolean surround)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetSurround(pointer, channel, surround);
	}

	/**
	 * Mutes and un-mutes a channel.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this will mute/unmute ALL channels available.<br>
	 * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to mute/unmute. FSOUND_ALL can also be used (see remarks).
	 * @param mute Toggle value - TRUE mutes out the channel, FALSE reenables it.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetMute(int)
	 */
	public boolean FSOUND_SetMute(int channel, boolean mute)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetMute(pointer, channel, mute);
	}

	/**
	 * Sets a channels priority. Higher priority means it is less likely to get discarded when
	 * FSOUND_FREE is used to select a channel, when all channels are being used, and one has to
	 * be rejected. If a channel has an equal priority then it will be replaced.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this will set the priority of ALL channels available.<br>
	 * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to change the priority for. FSOUND_ALL can also be used (see remarks).
	 * @param priority The priority to set. Valid ranges are from 0 (lowest) to 255 (highest)
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetPriority(int)
	 * @see FMOD_INSTANCE#FSOUND_GetReserved(int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaults(FSOUND_SAMPLE, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetDefaultsEx(FSOUND_SAMPLE, int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetReserved(int, boolean)
	 */
	public boolean FSOUND_SetPriority(int channel, int priority)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetPriority(pointer, channel, priority);
	}

	/**
	 * This sets the reserved status of a channel.<br>
	 * Reserving a channel is related to setting its priority, but reserving a channel means
	 * it can NEVER be stolen by a channel request. It could be thought of as an extra high
	 * priority, but is different in that reserved channels do not steal from each other,
	 * whereas channels with equal priorities do (unless there are  channels with lower
	 * priorities that it can steal from).<br>
	 * If all channels were reserved and  another request for came in for a channel, it would
	 * simply fail and the sound would not be  played.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this will set ALL channels to be reserved/unreserved.<br>
	 * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to change the priority for. <br>
	 * FSOUND_ALL can also be used (see remarks).<br>
	 * FSOUND_FREE is NOT accepted.
	 * @param reserved Reserved flag. Values accepted are TRUE, to reserve a channel, and FALSE to  un-reserve a channel.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetPriority(int)
	 * @see FMOD_INSTANCE#FSOUND_GetReserved(int)
	 * @see FMOD_INSTANCE#FSOUND_SetPriority(int, int)
	 */
	public boolean FSOUND_SetReserved(int channel, boolean reserved)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetReserved(pointer, channel, reserved);
	}

	/**
	 * Pauses or unpauses a sound channel.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this will pause/unpause ALL channels available.<br>
	 * If FSOUND_ALL is used the last channel success flag will be returned. This return value is not useful in most circumstances.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to pause or unpause. FSOUND_ALL can also be used (see remarks)
	 * @param paused TRUE pauses this channel, FALSE unpauses it.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_FX_Enable(int, int)
	 * @see FMOD_INSTANCE#FSOUND_GetPaused(int)
	 * @see FMOD_INSTANCE#FSOUND_PlaySoundEx(int, FSOUND_SAMPLE, FSOUND_DSPUNIT, boolean)
	 * @see FMOD_INSTANCE#FSOUND_Stream_PlayEx(int, FSOUND_STREAM, FSOUND_DSPUNIT, boolean)
	 */
	public boolean FSOUND_SetPaused(int channel, boolean paused)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetPaused(pointer, channel, paused);
	}

	/**
	 * Sets the loop mode for a particular CHANNEL, not sample.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this will set loop modes for all channels available.<br>
	 * Note, this does not work for hardware sounds played on hardware channels while they are playing.
	 * The function has to be called when the channel is paused.<br>
	 * Software based sounds do not have this limitation, and can have their loop mode changed during playback,
	 * but for compatibility it is best to use the pause method, else you may get different behaviour if hardware voices do not exist.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to have its loop mode set.
	 * @param loopmode The loopmode to set. This can be FSOUND_LOOP_NORMAL, FSOUND_LOOP_BIDI or FSOUND_LOOP_OFF.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetMode(FSOUND_SAMPLE)
	 */
	public boolean FSOUND_SetLoopMode(int channel, int loopmode)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetLoopMode(pointer, channel, loopmode);
	}

	/**
	 * Sets the current position of the sound in SAMPLES not bytes.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this set the current position for the sound on ALL channels available.<br>
	 * On XBOX, GameCube and Playstation 2 hardware voices using compressed data (ie XADPCM, VAG or GCADPCM),
	 * this value will not be sample accurate, but will be rounded to the nearest compression block size. <br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to have its offset or position set.
	 * @param offset The offset in SAMPLES from the start of the sound for the position to be set to.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetCurrentPosition(int)
	 * @see FMOD_INSTANCE#FSOUND_SetFrequency(int, int)
	 */
	public boolean FSOUND_SetCurrentPosition(int channel, int offset)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_SetCurrentPosition(pointer, channel, offset);
	}

	/**
	 * This updates the position and velocity of a 3d sound playing on a channel.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND treats +X as right, +Y as up, and +Z as forwards.<br>
	 * ---------<br>
	 * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. <br>
	 * See FSOUND_3D_SetDistanceFactor for more on this.<br>
	 * ---------<br>
	 * FSOUND vectors expect 3 floats representing x y and z in that order. I.e. a typical definition of a vector is<br>
	 * typedef struct<br>
	 * {<br>
	 *   float x;<br>
	 *   float y;<br>
	 *   float z;<br>
	 * } VECTOR;<br>
	 * or simply an array of 3 floats.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel Channel you want to apply 3d positioning to.
	 * @param pos Pointer to a position vector (xyz float triplet) of the emitter in world space, measured in distance units.<br>
	 * This can be NULL to ignore it.
	 * @param vel Pointer to a velocity vector (xyz float triplet), of the emitter measured in distance units PER SECOND.<br>
	 * This can be NULL to ignore it.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_3D_GetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_GetMinMaxDistance(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_GetAttributes(FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetAttributes(FloatBuffer, FloatBuffer, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetCurrent(int, int)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDistanceFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetMinMaxDistance(int, float, float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetRolloffFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMinMaxDistance(FSOUND_SAMPLE, float, float)
	 * @see FMOD_INSTANCE#FSOUND_Update()
	 */
	public boolean FSOUND_3D_SetAttributes(int channel, float[] pos, float[] vel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_SetAttributes(pointer, channel, pos, vel);
	}
	/**
	 * This updates the position and velocity of a 3d sound playing on a channel.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND treats +X as right, +Y as up, and +Z as forwards.<br>
	 * ---------<br>
	 * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. <br>
	 * See FSOUND_3D_SetDistanceFactor for more on this.<br>
	 * ---------<br>
	 * FSOUND vectors expect 3 floats representing x y and z in that order. I.e. a typical definition of a vector is<br>
	 * typedef struct<br>
	 * {<br>
	 *   float x;<br>
	 *   float y;<br>
	 *   float z;<br>
	 * } VECTOR;<br>
	 * or simply an array of 3 floats.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel Channel you want to apply 3d positioning to.
	 * @param pos Pointer to a position vector (xyz float triplet) of the emitter in world space, measured in distance units.<br>
	 * This can be NULL to ignore it.
	 * @param vel Pointer to a velocity vector (xyz float triplet), of the emitter measured in distance units PER SECOND.<br>
	 * This can be NULL to ignore it.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_3D_GetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_GetMinMaxDistance(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_GetAttributes(FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetAttributes(FloatBuffer, FloatBuffer, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetCurrent(int, int)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDistanceFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetMinMaxDistance(int, float, float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetRolloffFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMinMaxDistance(FSOUND_SAMPLE, float, float)
	 * @see FMOD_INSTANCE#FSOUND_Update()
	 */
	public boolean FSOUND_3D_SetAttributes(int channel, FloatBuffer pos, FloatBuffer vel)
	{
		if(pointer == 0) throw new NullPointerException();
		if(pos != null && !pos.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(vel != null && !vel.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_SetAttributes(pointer, channel, pos, BufferUtils.getPositionInBytes(pos),
				vel, BufferUtils.getPositionInBytes(vel));
	}

	/**
	 * Sets the minimum and maximum audible distance for a channel.<br>
	 * MinDistance is the minimum distance that the sound emitter will cease to continue growing
	 * louder at (as it approaches the listener). Within the mindistance it stays at the constant loudest volume possible.
	 * Outside of this mindistance it begins to attenuate.<br>
	 * MaxDistance is the distance a sound stops attenuating at. Beyond this point it will stay at the volume it would be at
	 * maxdistance units from the listener and will not attenuate any more.<br>
	 * MinDistance is useful to give the impression that the sound is loud or soft in 3d space. An example of this is a small
	 * quiet object, such as a bumblebee, which you could set a mindistance of to 0.1 for example, which would cause it to
	 * attenuate quickly and dissapear when only a few meters away from the listener.<br>
	 * Another example is a jumbo jet, which you could set to a mindistance of 100.0, which would keep the sound volume at
	 * max until the listener was 100 meters away, then it would be hundreds of meters more before it would fade out.<br>
	 * -------<br>
	 * In summary, increase the mindistance of a sound to make it 'louder' in a 3d world, and
	 * decrease it to make it 'quieter' in a 3d world.<br>
	 * maxdistance is effectively obsolete unless you need the sound to stop fading out at a certain point.<br>
	 * Do not adjust this from the default if you dont need to.<br>
	 * Some people have the confusion that maxdistance is the point the sound will fade out to, this is not the case.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this will set the min/max distance on ALL channels available.<br>
	 * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. <br>
	 * See FSOUND_3D_SetDistanceFactor for more on this.<br>
	 * The default units for minimum and maximum distances are 1.0 and 1000000000.0f.<br>
	 * Volume drops off at mindistance / distance.<br>
	 * To define the min and max distance per sound and not per channel use FSOUND_Sample_SetMinMaxDistance.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel to have its minimum and maximum distance set.
	 * @param min The channels minimum volume distance in "units". See remarks for more on units.
	 * @param max The channels maximum volume distance in "units". See remarks for more on units.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_3D_GetMinMaxDistance(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDistanceFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMinMaxDistance(FSOUND_SAMPLE, float, float)
	 */
	public boolean FSOUND_3D_SetMinMaxDistance(int channel, float min, float max)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_SetMinMaxDistance(pointer, channel, min, max);
	}

	/**
	 * Returns if the channel is currently playing or not.br>
	 * <br><b>Remarks :</b><br>
	 * ___________________br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel Channel number/handle to get the playing status from.
	 * @return TRUE channel is currently active and playingbr>
	 * FALSE channel is currently idle.
	 */
	public boolean FSOUND_IsPlaying(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_IsPlaying(pointer, channel);
	}

	/**
	 * Returns the frequency in HZ of the specified channel.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel  The number/handle to get the frequency from.
	 * @return On success, the frequency in HZ of the specified channel is returned.<br>
	 * On failure, 0 is returned. To quailfy if this is a real error, call FSOUND_GetError.
	 * @see FMOD_INSTANCE#FSOUND_GetError()
	 * @see FMOD_INSTANCE#FSOUND_SetFrequency(int, int)
	 */
	public int FSOUND_GetFrequency(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetFrequency(pointer, channel);
	}

	/**
	 * Returns the linear volume of the specified channel between 0 and 255<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to get the volume from.
	 * @return On success, the following values are returned : 0 = silent to 255 = full volume.<br>
	 * On failure, 0 is returned. To quailfy if this is a real error, call FSOUND_GetError.
	 * @see FMOD_INSTANCE#FSOUND_GetError()
	 * @see FMOD_INSTANCE#FSOUND_SetVolume(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetVolumeAbsolute(int, int)
	 */
	public int FSOUND_GetVolume(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetVolume(pointer, channel);
	}

	/**
	 * Returns the volume of the channel based on all combinations of set volume, mastervolume and 3d position.<br>
	 * Works on software and hardware voices.<br>
	 * <br><b>Remarks :</b><br>
	 * This is not the same as FSOUND_GetCurrentLevels, as that function takes the actual waveform data into account.<br>
	 * This function simply gives a final volume based on 3d position and volume settings.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to get the amplitude from.
	 * @return On success, the following values are returned : 0 = silent to 255 = full volume.
	 * On failure, 0 is returned. To quailfy if this is a real error, call FSOUND_GetError.
	 * @see FMOD_INSTANCE#FSOUND_GetCurrentLevels(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_GetError()
	 * @see FMOD_INSTANCE#FSOUND_SetVolume(int, int)
	 */
	public int FSOUND_GetAmplitude(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetAmplitude(pointer, channel);
	}

	/**
	 * Returns the linear pan position of the specified channel between 0 and 255<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to get the pan from.
	 * @return On success, the following values are returned : 0 = full left to 128 = middle to 255 = full right, FSOUND_STEREOPAN<br>
	 * On failure, 0 is returned. To quailfy if this is a real error, call FSOUND_GetError.
	 * @see FMOD_INSTANCE#FSOUND_GetError()
	 * @see FMOD_INSTANCE#FSOUND_SetLevels(int, int, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetPan(int, int)
	 */
	public int FSOUND_GetPan(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetPan(pointer, channel);
	}

	/**
	 * Returns the surround sound status of the specified channel.<br>
	 * <br><b>Remarks :</b><br>
	 * Surround sound only works on software channels.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to get the surround sound status from.
	 * @return On success, TRUE is returned meaning the channel has surround sound turned ON<br>
	 * On failure, FALSE is returned meaning the channel has surround sound turned OFF
	 * @see FMOD_INSTANCE#FSOUND_SetSurround(int, boolean)
	 */
	public boolean FSOUND_GetSurround(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetSurround(pointer, channel);
	}

	/**
	 * Returns if the channel specified is muted or not<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to get the mute status from
	 * @return TRUE - The channel has mute turned ON
	 * FALSE - The channel has mute turned OFF
	 * @see FMOD_INSTANCE#FSOUND_SetMute(int, boolean)
	 */
	public boolean FSOUND_GetMute(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetMute(pointer, channel);
	}

	/**
	 * Gets a sound channels priority. Priority is used to determine if soundeffects should
	 * replace other sound effects when the channel limit has been reached.<br>
	 * See FSOUND_SetPriority for more information.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to get the priority from.
	 * @return On success, the priority of the channel is returned. Ranges between 0 and 255.<br>
	 * On failure, 0 is returned. To quailfy if this is a real error, call FSOUND_GetError.
	 * @see FMOD_INSTANCE#FSOUND_GetError()
	 * @see FMOD_INSTANCE#FSOUND_SetPriority(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetReserved(int, boolean)
	 */
	public int FSOUND_GetPriority(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetPriority(pointer, channel);
	}

	/**
	 * Gets a sound channels reserved status. priority is used to determine if soundeffects should muscle
	 * out other sound effects when the channel limit has been reached.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to get the reserved status from.
	 * @return TRUE Channel is reserved and cannot be selected.<br>
	 * FALSE Channel is reserved and can be selected.
	 * @see FMOD_INSTANCE#FSOUND_SetPriority(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetReserved(int, boolean)
	 */
	public boolean FSOUND_GetReserved(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetReserved(pointer, channel);
	}

	/**
	 * Gets current pause status of the channel.<br>
	 * <br><b>Remarks :</b><br>
	 * This function is useful for games that have a pause mode, and you dont want the sounds
	 * to continue playing, but you would like them to continue on from where they left off
	 * when you unpause.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The channel number/handle to get the paused status from.
	 * @return TRUE - The channel is currently paused.<br>
	 * FALSE - The channel is running.
	 * @see FMOD_INSTANCE#FSOUND_SetPaused(int, boolean)
	 */
	public boolean FSOUND_GetPaused(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetPaused(pointer, channel);
	}

	/**
	 * Gets the loop mode for a particular channel<br>
	 * <br><b>Remarks :</b><br>
	 * This works for all channel types, whereas setting it will not work.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel  The channel number/handle to get the loop mode from.
	 * @return On success, the loop mode is returned.<br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMode(FSOUND_SAMPLE, int)
	 */
	public int FSOUND_GetLoopMode(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetLoopMode(pointer, channel);
	}

	/**
	 * Returns the current playcursor position of the specified channel.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel Channel number/handle to get the current position from.
	 * @return On success, the play cursor position in SAMPLES is returned for the specified channel.<br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FSOUND_Record_StartSample(FSOUND_SAMPLE, boolean)
	 * @see FMOD_INSTANCE#FSOUND_SetCurrentPosition(int, int)
	 */
	public int FSOUND_GetCurrentPosition(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetCurrentPosition(pointer, channel);
	}

	/**
	 * Returns the current sample being played on the specified channel.<br>
	 * <br><b>Remarks :</b><br>
	 * Note that current sample does not return to NULL when a sound has ended.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel Channel number/handle to get the currently playing sample from.
	 * @return On success, a pointer to a sample handle is returned for the specified channel.
	 * On failure, NULL is returned.
	 */
	public FSOUND_SAMPLE FSOUND_GetCurrentSample(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_GetCurrentSample(pointer, channel);
		return (cPtr == 0) ? null : FSOUND_SAMPLE.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Returns a left and right VU/Level reading at the current position of the specified channel.<br>
	 * Levels are are only supported for software channels.<br>
	 * <br><b>Remarks :</b><br>
	 * By default this function is only point sampled and not latency adjusted (it will appear to trigger ahead of when you hear the sound).<br>
	 * To fix this and get a 'perfect' set of levels in realtime, use FSOUND_INIT_ACCURATEVULEVELS with FSOUND_Init.<br>
	 * -------------------<br>
	 * To get an overall VU reading for all sounds, add all VU values for each channel together, and then clip at 1.0. <br>
	 * Another (harder) way is to write a dsp unit that reads from the mixbuffer being passed into it.<br>
	 * Note: A true 'VU' should be smoothed, but in case people were after more accuracy than a smoothed value,
	 * it was decided to return the raw amplitude, and let the user smooth the result in their own way.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel Channel number/handle to retrieve left and right level from.
	 * @param l  Pointer to a floating point value to store left level, between 0 and 1.
	 * @param r Pointer to a floating point value to store right level, between 0 and 1.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetAmplitude(int)
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 */
	public boolean FSOUND_GetCurrentLevels(int channel, float[] l, float[] r)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetCurrentLevels(pointer, channel, l, r);
	}
	/**
	 * Returns a left and right VU/Level reading at the current position of the specified channel.<br>
	 * Levels are are only supported for software channels.<br>
	 * <br><b>Remarks :</b><br>
	 * By default this function is only point sampled and not latency adjusted (it will appear to trigger ahead of when you hear the sound).<br>
	 * To fix this and get a 'perfect' set of levels in realtime, use FSOUND_INIT_ACCURATEVULEVELS with FSOUND_Init.<br>
	 * -------------------<br>
	 * To get an overall VU reading for all sounds, add all VU values for each channel together, and then clip at 1.0. <br>
	 * Another (harder) way is to write a dsp unit that reads from the mixbuffer being passed into it.<br>
	 * Note: A true 'VU' should be smoothed, but in case people were after more accuracy than a smoothed value,
	 * it was decided to return the raw amplitude, and let the user smooth the result in their own way.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel Channel number/handle to retrieve left and right level from.
	 * @param l  Pointer to a floating point value to store left level, between 0 and 1.
	 * @param r Pointer to a floating point value to store right level, between 0 and 1.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetAmplitude(int)
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 */
	public boolean FSOUND_GetCurrentLevels(int channel, FloatBuffer l, FloatBuffer r)
	{
		if(pointer == 0) throw new NullPointerException();
		if(l != null && !l.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(r != null && !r.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetCurrentLevels(pointer, channel, l, BufferUtils.getPositionInBytes(l),
				r, BufferUtils.getPositionInBytes(r));
	}

	/**
	 * This function returns the number of sub-channels stored in a multi-channel channel handle, which is only possible<br>
	 * when playing back a multichannel .FSB file.<br>
	 * <br><b>Remarks :</b><br>
	 * A multichannel sound, only possible with the .FSB format, can contain multiple subchannels.
	 * When a multichannel sound is played, multiple channels are allocated at the same time.<br>
	 * For example, a 8 sounds/streams can be interleaved into a multichannel FSB. This function would return 8, as 8 real hardware/software voices are used during playback.<br>
	 * FSOUND_GetSubChannel can be used to get access to the secondary channels.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The value returned by FSOUND_Stream_Play, FSOUND_Stream_PlayEx, FSOUND_PlaySound, FSOUND_PlaySoundEx.
	 * @return On success, the number of subchannels is returned<br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetSubChannel(int, int)
	 * @see FMOD_INSTANCE#FSOUND_PlaySound(int, FSOUND_SAMPLE)
	 * @see FMOD_INSTANCE#FSOUND_PlaySoundEx(int, FSOUND_SAMPLE, FSOUND_DSPUNIT, boolean)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_PlayEx(int, FSOUND_STREAM, FSOUND_DSPUNIT, boolean)
	 */
	public int FSOUND_GetNumSubChannels(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetNumSubChannels(pointer, channel);
	}

	/**
	 * This function returns a channel handle from a subchannel within a multichannel FSB file, so that it can be maniuplated
	 * seperately, instead of controlling the whole multichannel array with the parent channel that the user retrieves
	 * from FSOUND_PlaySound etc.<br>
	 * <br><b>Remarks :</b><br>
	 * A multichannel sound, only possible with the .FSB format, can contain multiple subchannels. When a multichannel
	 * sound is played, multiple channels are allocated at the same time.<br>
	 * Normally you can just use the parent handle, and things like FSOUND_SetVolume will affect all subchannels at the
	 * same time. With this function, you can get access to the raw subchannels to allow manipulation of each voice
	 * seperately within the multichannel array.<br>
	 * For example, a 8 sounds/streams can be interleaved into a multichannel FSB. If you specified a subchannel of 7,
	 * it would return a channel handle to the last channel in the multichannel array.<br>
	 * A subchannel index of 0 is the parent channel, and the same as the voice passed in is a parameter.<br>
	 * The number of subchannels within a multichannel voice can be determined with FSOUND_GetNumSubChannels.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel The value returned by FSOUND_Stream_Play, FSOUND_Stream_PlayEx, FSOUND_PlaySound, FSOUND_PlaySoundEx.
	 * @param subchannel Offset from the parent channel into the multichannel array.
	 * @return On success, a raw channel handle is returned.
	 * On failure, -1 is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetNumSubChannels(int)
	 * @see FMOD_INSTANCE#FSOUND_PlaySound(int, FSOUND_SAMPLE)
	 * @see FMOD_INSTANCE#FSOUND_PlaySoundEx(int, FSOUND_SAMPLE, FSOUND_DSPUNIT, boolean)
	 * @see FMOD_INSTANCE#FSOUND_SetVolume(int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_PlayEx(int, FSOUND_STREAM, FSOUND_DSPUNIT, boolean)
	 */
	public int FSOUND_GetSubChannel(int channel, int subchannel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_GetSubChannel(pointer, channel, subchannel);
	}

	/**
	 * <br><b>Remarks :</b><br>
	 * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. <br>
	 * See FSOUND_3D_SetDistanceFactor for more on this.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel Channel you want to get 3d information from
	 * @param pos Pointer to a position vector (xyz float triplet) of the emitter in world space, measured in distance units.<br>
	 * This can be NULL to ignore it.
	 * @param vel Pointer to a velocity vector (xyz float triplet), of the emitter measured in distance units PER SECOND.<br>
	 * This can be NULL to ignore it.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetAttributes(FloatBuffer, FloatBuffer, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDistanceFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMinMaxDistance(FSOUND_SAMPLE, float, float)
	 */
	public boolean FSOUND_3D_GetAttributes(int channel, float[] pos, float[] vel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_GetAttributes(pointer, channel, pos, vel);
	}
	/**
	 * <br><b>Remarks :</b><br>
	 * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. <br>
	 * See FSOUND_3D_SetDistanceFactor for more on this.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel Channel you want to get 3d information from
	 * @param pos Pointer to a position vector (xyz float triplet) of the emitter in world space, measured in distance units.<br>
	 * This can be NULL to ignore it.
	 * @param vel Pointer to a velocity vector (xyz float triplet), of the emitter measured in distance units PER SECOND.<br>
	 * This can be NULL to ignore it.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetAttributes(FloatBuffer, FloatBuffer, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDistanceFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMinMaxDistance(FSOUND_SAMPLE, float, float)
	 */
	public boolean FSOUND_3D_GetAttributes(int channel, FloatBuffer pos, FloatBuffer vel)
	{
		if(pointer == 0) throw new NullPointerException();
		if(pos != null && !pos.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(vel != null && !vel.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_GetAttributes(pointer, channel, pos, BufferUtils.getPositionInBytes(pos),
				vel, BufferUtils.getPositionInBytes(vel));
	}

	/**
	 * Returns the current min and max distance for a channel.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel Channel number/handle to retrieve min and max distance from
	 * @param min Pointer to a floating point value to store mindistance.
	 * @param max Pointer to a floating point value to store maxdistance.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetMinMaxDistance(int, float, float)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMinMaxDistance(FSOUND_SAMPLE, float, float)
	 */
	public boolean FSOUND_3D_GetMinMaxDistance(int channel, float[] min, float[] max)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_GetMinMaxDistance(pointer, channel, min, max);
	}
	/**
	 * Returns the current min and max distance for a channel.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel Channel number/handle to retrieve min and max distance from
	 * @param min Pointer to a floating point value to store mindistance.
	 * @param max Pointer to a floating point value to store maxdistance.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetMinMaxDistance(int, float, float)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMinMaxDistance(FSOUND_SAMPLE, float, float)
	 */
	public boolean FSOUND_3D_GetMinMaxDistance(int channel, FloatBuffer min, FloatBuffer max)
	{
		if(pointer == 0) throw new NullPointerException();
		if(min != null && !min.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(max != null && !max.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_GetMinMaxDistance(pointer, channel, min, BufferUtils.getPositionInBytes(min),
				max, BufferUtils.getPositionInBytes(max));
	}

	/**
	 * This updates the position, velocity and orientation of a 3d sound listener.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND treats +X as right, +Y as up, and +Z as forwards. (left handed)<br>
	 * To map to your own coordinate system, flip and exchange these values. For example if you wanted to use
	 * right handed coordinates, you would negate the Z value of your own direction vector.<br>
	 * Orientation vectors are expected to be of UNIT length. This means the magnitude of the vector
	 * should be 1.0f.<br>
	 * ---------<br>
	 * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. <br>
	 * See FSOUND_3D_SetDistanceFactor for more on this.<br>
	 * ---------<br>
	 * Please remember to use units PER SECOND, NOT PER FRAME as this is a common mistake. <br>
	 * Do not just use (pos - lastpos) from the last frame's data for velocity, as this is not
	 * correct. You need to time compensate it so it is given in units per SECOND.<br>
	 * You could alter your pos - lastpos calculation to something like this. <br>
	 * vel = (pos-lastpos) / (time taken since last frame in seconds). Ie at 60fps the formula
	 * would look like this vel = (pos-lastpos) / 0.0166667.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param pos Pointer to a position vector (xyz float triplet), of the listener in world space, measured in distance units.
	 * This can be NULL to ignore it.
	 * @param vel Pointer to a velocity vector (xyz float triplet), of the listener measured in distance units PER SECOND.
	 * This can be NULL to ignore it.
	 * @param fx x component of a FORWARD unit length orientation vector
	 * @param fy y component of a FORWARD unit length orientation vector
	 * @param fz z component of a FORWARD unit length orientation vector
	 * @param tx x component of a TOP or upwards facing unit length orientation vector
	 * @param ty y component of a TOP or upwards facing unit length orientation vector
	 * @param tz z component of a TOP or upwards facing unit length orientation vector
	 * @see FMOD_INSTANCE#FSOUND_3D_GetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetCurrent(int, int)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDistanceFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDopplerFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetRolloffFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_Update()
	 */
	public void FSOUND_3D_Listener_SetAttributes(
		float[] pos, float[] vel, float fx, float fy,
		float fz, float tx, float ty, float tz)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_Listener_SetAttributes(pointer, pos, vel, fx, fy, fz, tx, ty, tz);
	}
	/**
	 * This updates the position, velocity and orientation of a 3d sound listener.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND treats +X as right, +Y as up, and +Z as forwards. (left handed)<br>
	 * To map to your own coordinate system, flip and exchange these values. For example if you wanted to use
	 * right handed coordinates, you would negate the Z value of your own direction vector.<br>
	 * Orientation vectors are expected to be of UNIT length. This means the magnitude of the vector
	 * should be 1.0f.<br>
	 * ---------<br>
	 * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. <br>
	 * See FSOUND_3D_SetDistanceFactor for more on this.<br>
	 * ---------<br>
	 * Please remember to use units PER SECOND, NOT PER FRAME as this is a common mistake. <br>
	 * Do not just use (pos - lastpos) from the last frame's data for velocity, as this is not
	 * correct. You need to time compensate it so it is given in units per SECOND.<br>
	 * You could alter your pos - lastpos calculation to something like this. <br>
	 * vel = (pos-lastpos) / (time taken since last frame in seconds). Ie at 60fps the formula
	 * would look like this vel = (pos-lastpos) / 0.0166667.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param pos Pointer to a position vector (xyz float triplet), of the listener in world space, measured in distance units.
	 * This can be NULL to ignore it.
	 * @param vel Pointer to a velocity vector (xyz float triplet), of the listener measured in distance units PER SECOND.
	 * This can be NULL to ignore it.
	 * @param fx x component of a FORWARD unit length orientation vector
	 * @param fy y component of a FORWARD unit length orientation vector
	 * @param fz z component of a FORWARD unit length orientation vector
	 * @param tx x component of a TOP or upwards facing unit length orientation vector
	 * @param ty y component of a TOP or upwards facing unit length orientation vector
	 * @param tz z component of a TOP or upwards facing unit length orientation vector
	 * @see FMOD_INSTANCE#FSOUND_3D_GetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetCurrent(int, int)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDistanceFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDopplerFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetRolloffFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_Update()
	 */
	public void FSOUND_3D_Listener_SetAttributes(
			FloatBuffer pos, FloatBuffer vel, float fx, float fy,
			float fz, float tx, float ty, float tz)
	{
		if(pointer == 0) throw new NullPointerException();
		if(pos != null && !pos.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(vel != null && !vel.isDirect()) {
			throw new NonDirectBufferException();
		}
		FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_Listener_SetAttributes(pointer, pos, BufferUtils.getPositionInBytes(pos),
				vel, BufferUtils.getPositionInBytes(vel),
				fx, fy, fz, tx, ty, tz);
	}

	/**
	 * This retreives the position, velocity and orientation of a 3d sound listener.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND treats +X as right, +Y as up, and +Z as forwards. (left handed)<br>
	 * To map to your own coordinate system, flip and exchange these values. For example if
	 * you wanted to use right handed coordinates, you would negate the Z value of your own direction vector.<br>
	 * Orientation vectors are expected to be of UNIT length. This means the magnitude of the vector should be 1.0f.<br>
	 * ---------<br>
	 * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. <br>
	 * See FSOUND_3D_SetDistanceFactor for more on this.<br>
	 * ---------<br>
	 * Please remember to use units PER SECOND, NOT PER FRAME as this is a common mistake. <br>
	 * Do not just use (pos - lastpos) from the last frame's data for velocity, as this is not correct. <br>
	 * You need to time compensate it so it is given in units per SECOND.<br>
	 * You could alter your pos - lastpos calculation to something like this. <br>
	 * vel = (pos-lastpos) / (time taken since last frame in seconds). <br>
	 * I.e. at 60fps the formula would look like this <br>
	 * vel = (pos-lastpos) / 0.0166667.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param pos Pointer to a position vector (xyz float triplet), of the listener in world space, <br>measured in distance units.<br>
	 * This can be NULL to ignore it.
	 * @param vel Pointer to a velocity vector (xyz float triplet), of the listener measured in  distance units PER SECOND.<br>
	 * This can be NULL to ignore it.
	 * @param fx pointer to x component of a FORWARD unit length orientation vector<br>
	 * This can be NULL to ignore it.
	 * @param fy pointer to y component of a FORWARD unit length orientation vector<br>
	 * This can be NULL to ignore it.
	 * @param fz pointer to z component of a FORWARD unit length orientation vector<br>
	 * This can be NULL to ignore it.
	 * @param tx pointer to x component of a TOP or upwards facing unit length orientation vector<br>
	 * This can be NULL to ignore it.
	 * @param ty pointer to y component of a TOP or upwards facing unit length orientation vector<br>
	 * This can be NULL to ignore it.
	 * @param tz pointer to z component of a TOP or upwards facing unit length orientation vector<br>
	 * This can be NULL to ignore it.
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetCurrent(int, int)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDistanceFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDopplerFactor(float)
	 */
	public void FSOUND_3D_Listener_GetAttributes(
		float[] pos, float[] vel, float[] fx, float[] fy,
		float[] fz, float[] tx, float[] ty, float[] tz)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_Listener_GetAttributes(pointer, pos, vel, fx, fy, fz, tx, ty, tz);
	}
	/**
	 * This retreives the position, velocity and orientation of a 3d sound listener.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND treats +X as right, +Y as up, and +Z as forwards. (left handed)<br>
	 * To map to your own coordinate system, flip and exchange these values. For example if
	 * you wanted to use right handed coordinates, you would negate the Z value of your own direction vector.<br>
	 * Orientation vectors are expected to be of UNIT length. This means the magnitude of the vector should be 1.0f.<br>
	 * ---------<br>
	 * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. By default this is set to meters which is a distance scale of 1.0. <br>
	 * See FSOUND_3D_SetDistanceFactor for more on this.<br>
	 * ---------<br>
	 * Please remember to use units PER SECOND, NOT PER FRAME as this is a common mistake. <br>
	 * Do not just use (pos - lastpos) from the last frame's data for velocity, as this is not correct. <br>
	 * You need to time compensate it so it is given in units per SECOND.<br>
	 * You could alter your pos - lastpos calculation to something like this. <br>
	 * vel = (pos-lastpos) / (time taken since last frame in seconds). <br>
	 * I.e. at 60fps the formula would look like this <br>
	 * vel = (pos-lastpos) / 0.0166667.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param pos Pointer to a position vector (xyz float triplet), of the listener in world space, <br>measured in distance units.<br>
	 * This can be NULL to ignore it.
	 * @param vel Pointer to a velocity vector (xyz float triplet), of the listener measured in  distance units PER SECOND.<br>
	 * This can be NULL to ignore it.
	 * @param fx pointer to x component of a FORWARD unit length orientation vector<br>
	 * This can be NULL to ignore it.
	 * @param fy pointer to y component of a FORWARD unit length orientation vector<br>
	 * This can be NULL to ignore it.
	 * @param fz pointer to z component of a FORWARD unit length orientation vector<br>
	 * This can be NULL to ignore it.
	 * @param tx pointer to x component of a TOP or upwards facing unit length orientation vector<br>
	 * This can be NULL to ignore it.
	 * @param ty pointer to y component of a TOP or upwards facing unit length orientation vector<br>
	 * This can be NULL to ignore it.
	 * @param tz pointer to z component of a TOP or upwards facing unit length orientation vector<br>
	 * This can be NULL to ignore it.
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetCurrent(int, int)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDistanceFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDopplerFactor(float)
	 */
	public void FSOUND_3D_Listener_GetAttributes(
		FloatBuffer pos, FloatBuffer vel, FloatBuffer fx, FloatBuffer fy,
		FloatBuffer fz, FloatBuffer tx, FloatBuffer ty, FloatBuffer tz)
	{
		if(pointer == 0) throw new NullPointerException();
		if(pos != null && !pos.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(vel != null && !vel.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(fx != null && !fx.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(fy != null && !fy.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(fz != null && !fz.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(tx != null && !tx.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(ty != null && !ty.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(tz != null && !tz.isDirect()) {
			throw new NonDirectBufferException();
		}
		FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_Listener_GetAttributes(pointer, pos, BufferUtils.getPositionInBytes(pos),
				vel, BufferUtils.getPositionInBytes(vel),
				fx, BufferUtils.getPositionInBytes(fx),
				fy, BufferUtils.getPositionInBytes(fy),
				fz, BufferUtils.getPositionInBytes(fz),
				tx, BufferUtils.getPositionInBytes(tx),
				ty, BufferUtils.getPositionInBytes(ty),
				tz, BufferUtils.getPositionInBytes(tz));
	}

	/**
	 * Sets the current listener number and number of listeners, if the user wants to simulate multiple listeners at once. <br>
	 * This is usually for the case in a game where there is a splitscreen and multiple players playing the game at once.<br>
	 * <br><b>Remarks :</b><br>
	 * Only affects FSOUND_3D_Listener_SetAttributes and FSOUND_3D_Listener_GetAttributes.<br>
	 * Setting more than 1 listener will turn off doppler and cause all panning to be ignored and 3d sound will come from the center (mono).<br>
	 * -------------<br>
	 * For WIN32 FSOUND_HW3D based sounds, channels must have their attributes set after this function is called, otherwise unexpected audible results may occur.<br>
	 * For example you cannot update your channels with FSOUND_3D_SetAttributes, call FSOUND_3D_Listener_SetCurrent,
	 * and then call FSOUND_Update and expect all the voices to update correctly. <br>
	 * The correct order is to call FSOUND_3D_Listener_SetCurrent first, then update all channels with FSOUND_3D_SetAttributes,
	 * hen call FSOUND_Update. <br>
	 * This is due to DirectSound not supporting multiple listeners, so FMOD has to do inverse transforms on the positions to simulate
	 * it with one listener, at the time FSOUND_3D_SetAttributes is called.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param current Current listener number. Listener commands following this function call will affect this listener number. (default: 0)
	 * @param numlisteners Number of listeners active. (default: 1)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_GetAttributes(FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetAttributes(FloatBuffer, FloatBuffer, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetCurrent(int, int)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Update()
	 */
	public void FSOUND_3D_Listener_SetCurrent(int current, int numlisteners)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_Listener_SetCurrent(pointer, current, numlisteners);
	}

	/**
	 * Sets the doppler shift scale factor.<br>
	 * <br><b>Remarks :</b><br>
	 * This is a general scaling factor for how much the pitch varies due to doppler shifting.<br>
	 * Increasing the value above 1.0 exaggerates the effect, whereas lowering it reduces the effect.<br>
	 * 0 removes the effect all together.<br>
	 * FMOD's speed of sound at a DopplerFactor of 1.0 is 340 m/s.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param scale Doppler shift scale. Default value for FSOUND is 1.0f
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_GetAttributes(FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetAttributes(FloatBuffer, FloatBuffer, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_Update()
	 */
	public void FSOUND_3D_SetDopplerFactor(float scale)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_SetDopplerFactor(pointer, scale);
	}

	/**
	 * Sets FMOD's 3d engine relative distance factor, compared to 1.0 meters. It equates to
	 * 'how many units per meter' does your engine have. <br>
	 * <br><b>Remarks :</b><br>
	 * By default this value is set at 1.0, or meters.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param scale 1.0 = 1 meter units. If you are using feet then scale would equal 3.28.
	 * @see FMOD_INSTANCE#FSOUND_3D_GetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_GetAttributes(FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetAttributes(FloatBuffer, FloatBuffer, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetMinMaxDistance(int, float, float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetRolloffFactor(float)
	 * @see FMOD_INSTANCE#FSOUND_Sample_GetMinMaxDistance(FSOUND_SAMPLE, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Sample_SetMinMaxDistance(FSOUND_SAMPLE, float, float)
	 */
	public void FSOUND_3D_SetDistanceFactor(float scale)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_SetDistanceFactor(pointer, scale);
	}

	/**
	 * Sets the global attenuation rolloff factor.<br>
	 * Normally volume for a sample will scale at 1 / distance. This gives a logarithmic attenuation of volume as the source
	 * gets further away (or closer).<br>
	 * Setting this value makes the sound drop off faster or slower. The higher the value, the faster volume will fall off. <br>
	 * The lower the value, the slower it will fall off.<br>
	 * For example a rolloff factor of 1 will simulate the real world, where as a value of 2 will make sounds attenuate 2 times quicker.<br>
	 * <br><b>Remarks :</b><br>
	 * A 'distance unit' is specified by FSOUND_3D_SetDistanceFactor. <br>
	 * By default this is set to meters which is a distance scale of 1.0. <br>
	 * See FSOUND_3D_SetDistanceFactor for more on this.<br>
	 * ---------<br>
	 * The default rolloff factor is 1.0.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param scale The rolloff factor to set for this sample. Valid ranges are 0 to 10.
	 * @see FMOD_INSTANCE#FSOUND_3D_Listener_SetAttributes(FloatBuffer, FloatBuffer, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetAttributes(int, FloatBuffer, FloatBuffer)
	 * @see FMOD_INSTANCE#FSOUND_3D_SetDistanceFactor(float)
	 */
	public void FSOUND_3D_SetRolloffFactor(float scale)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_3D_SetRolloffFactor(pointer, scale);
	}

	/**
	 * Enables effect processing for the specified channel. This command continues to add effects to a channel (up to 16)
	 * until FSOUND_FX_Disable is called.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this will enable fx on ALL channels available.<br>
	 * This command can only be issued while the channel is paused.<br>
	 * If an effect is not enabled, then it will not be affected by its corresponding FSOUND_FX_Set functions.<br>
	 * This function must be played after a paused PlaySoundEx (ie FSOUND_PlaySoundEx(FSOUND_FREE, sound, NULL, TRUE)), and before
	 * the FSOUND_SetPaused(FALSE) so that the hardware can get the resource before it starts playing.<br>
	 * A total of 16 FX per channel is allowed, any more will result in an error. FX are reset to 0 after a sound is stopped or
	 * played. (but as above, before the unpausing of a play-paused sound).<br>
	 * Warning : This function is expensive to call as it has to set up fx buffers etc. It is best to call it once, reserve
	 * the channel then reuse the channel index when calling playsound without calling it again.<br>
	 * Note : Channels with FX enabled sounds cannot have their frequency changed.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32
	 * @param channel Channel number/handle to enable fx for.
	 * @param fxtype A single fx enum value to enable certain effects.
	 * @return On success, an FX id is returned.
	 * On failure, -1 is returned.
	 * @see FMOD_INSTANCE#FSOUND_FX_Disable(int)
	 * @see FSOUND_FX_MODES
	 * @see FMOD_INSTANCE#FSOUND_FX_SetChorus(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetCompressor(int, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetDistortion(int, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetEcho(int, float, float, float, float, boolean)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetFlanger(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetGargle(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetI3DL2Reverb(int, int, int, float, float, float, int, float, int, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetParamEQ(int, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetWavesReverb(int, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_PlaySound(int, FSOUND_SAMPLE)
	 * @see FMOD_INSTANCE#FSOUND_PlaySoundEx(int, FSOUND_SAMPLE, FSOUND_DSPUNIT, boolean)
	 * @see FMOD_INSTANCE#FSOUND_SetPaused(int, boolean)
	 */
	public int FSOUND_FX_Enable(int channel, int fxtype)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_FX_Enable(pointer, channel, fxtype);
	}

	/**
	 * Disables effect processing for ALL effects on the specified channel.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported. Passing this will disable fx on ALL channels available.<br>
	 * This command can only be issued while the channel is paused or stopped.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32
	 * @param channel Channel number/handle to disable all fx for.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_FX_Enable(int, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetChorus(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetCompressor(int, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetDistortion(int, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetEcho(int, float, float, float, float, boolean)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetFlanger(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetGargle(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetI3DL2Reverb(int, int, int, float, float, float, int, float, int, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetParamEQ(int, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetWavesReverb(int, float, float, float, float)
	 */
	public boolean FSOUND_FX_Disable(int channel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_FX_Disable(pointer, channel);
	}

	/**
	 * Sets the parameters for the chorus effect on a particular channel<br>
	 * <br><b>Remarks :</b><br>
	 * Make sure you have enabled this effect with FSOUND_FX_CHORUS before using this function.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32
	 * @param fxid fx handle generated by FSOUND_FX_Enable, to set chorus parameters for.
	 * @param WetDryMix Ratio of wet (processed) signal to dry (unprocessed) signal. Must be in the range from 0 through 100 (all wet).
	 * @param Depth Percentage by which the delay time is modulated by the low-frequency oscillator, in hundredths of a percentage point.<br>
	 * Must be in the range from 0 through 100. The default value is 25.
	 * @param Feedback Percentage of output signal to feed back into the effects input, in the range from -99 to 99. The default value is 0.
	 * @param Frequency Frequency of the LFO, in the range from 0 to 10. The default value is 0.
	 * @param Waveform Waveform of the LFO. Defined values are <br>
	 * 0 triangle.<br>
	 * 1 sine.<br>
	 * By default, the waveform is a sine.
	 * @param Delay Number of milliseconds the input is delayed before it is played back, in the range from 0 to 20. The default value is 0 ms.
	 * @param Phase Phase differential between left and right LFOs, in the range from 0 through 4. Possible values are defined as follows: <br>
	 * 0 -180 degrees<br>
	 * 1 - 90 degrees<br>
	 * 2 0 degrees<br>
	 * 3 90 degrees<br>
	 * 4 180 degrees<br>
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_FX_Disable(int)
	 * @see FMOD_INSTANCE#FSOUND_FX_Enable(int, int)
	 * @see FSOUND_FX_MODES
	 * @see FMOD_INSTANCE#FSOUND_FX_SetCompressor(int, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetDistortion(int, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetEcho(int, float, float, float, float, boolean)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetFlanger(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetGargle(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetI3DL2Reverb(int, int, int, float, float, float, int, float, int, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetParamEQ(int, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetWavesReverb(int, float, float, float, float)
	 */
	public boolean FSOUND_FX_SetChorus(
		int fxid,
		float WetDryMix,
		float Depth,
		float Feedback,
		float Frequency,
		int Waveform,
		float Delay,
		int Phase)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_FX_SetChorus(
			pointer,
			fxid,
			WetDryMix,
			Depth,
			Feedback,
			Frequency,
			Waveform,
			Delay,
			Phase);
	}

	/**
	 * Sets the parameters for the compressor effect on a particular channel<br>
	 * <br><b>Remarks :</b><br>
	 * Make sure you have enabled this effect with FSOUND_FX_COMPRESSOR before using this function.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32
	 * @param fxid fx handle generated by FSOUND_FX_Enable, to set compressor parameters for.
	 * @param Gain Output gain of signal after compression, in the range from -60 to 60. The default value is 0 dB.
	 * @param Attack Time before compression reaches its full value, in the range from 0.01 to 500. The default value is 0.01 ms.
	 * @param Release Speed at which compression is stopped after input drops below fThreshold, in the range from 50 to 3000.<br>
	 * The default value is 50 ms.
	 * @param Threshold Point at which compression begins, in decibels, in the range from -60 to 0. The default value is -10 dB.
	 * @param Ratio Compression ratio, in the range from 1 to 100. The default value is 10, which means 10:1 compression.
	 * @param Predelay Time after lThreshold is reached before attack phase is started, in milliseconds, in the range from 0 to 4.<br>
	 * The default value is 0 ms.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_FX_Disable(int)
	 * @see FMOD_INSTANCE#FSOUND_FX_Enable(int, int)
	 * @see FSOUND_FX_MODES
	 * @see FMOD_INSTANCE#FSOUND_FX_SetChorus(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetDistortion(int, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetEcho(int, float, float, float, float, boolean)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetFlanger(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetGargle(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetI3DL2Reverb(int, int, int, float, float, float, int, float, int, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetParamEQ(int, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetWavesReverb(int, float, float, float, float)
	 */
	public boolean FSOUND_FX_SetCompressor(
		int fxid,
		float Gain,
		float Attack,
		float Release,
		float Threshold,
		float Ratio,
		float Predelay)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_FX_SetCompressor(
			pointer,
			fxid,
			Gain,
			Attack,
			Release,
			Threshold,
			Ratio,
			Predelay);
	}

	/**
	 * Sets the parameters for the distortion effect on a particular channel<br>
	 * <br><b>Remarks :</b><br>
	 * Make sure you have enabled this effect with FSOUND_FX_DISTORTION before using this function.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32
	 * @param fxid fx handle generated by FSOUND_FX_Enable, to set distortion parameters for.
	 * @param Gain Amount of signal change after distortion, in the range from -60 through 0. The default value is 0 dB.
	 * @param Edge Percentage of distortion intensity, in the range in the range from 0 through 100. The default value is 50 percent.
	 * @param PostEQCenterFrequency Center frequency of harmonic content addition, in the range from 100 through 8000.<br>
	 * The default value is 4000 Hz.
	 * @param PostEQBandwidth Width of frequency band that determines range of harmonic content addition, in the range from 100 through 8000.<br>
	 * The default value is 4000 Hz.
	 * @param PreLowpassCutoff Filter cutoff for high-frequency harmonics attenuation, in the range from 100 through 8000.<br>
	 * The default value is 4000 Hz.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_FX_Disable(int)
	 * @see FMOD_INSTANCE#FSOUND_FX_Enable(int, int)
	 * @see FSOUND_FX_MODES
	 * @see FMOD_INSTANCE#FSOUND_FX_SetChorus(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetCompressor(int, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetEcho(int, float, float, float, float, boolean)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetFlanger(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetGargle(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetI3DL2Reverb(int, int, int, float, float, float, int, float, int, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetParamEQ(int, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetWavesReverb(int, float, float, float, float)
	 */
	public boolean FSOUND_FX_SetDistortion(
		int fxid,
		float Gain,
		float Edge,
		float PostEQCenterFrequency,
		float PostEQBandwidth,
		float PreLowpassCutoff)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_FX_SetDistortion(
			pointer,
			fxid,
			Gain,
			Edge,
			PostEQCenterFrequency,
			PostEQBandwidth,
			PreLowpassCutoff);
	}

	/**
	 * Sets the parameters for the echo effect on a particular channel<br>
	 * <br><b>Remarks :</b><br>
	 * Make sure you have enabled this effect with FSOUND_FX_Enable and FSOUND_FX_ECHO before using this function.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32
	 * @param fxid fx handle generated by FSOUND_FX_Enable, to set echo parameters for.
	 * @param WetDryMix Ratio of wet (processed) signal to dry (unprocessed) signal. Must be in the range from 0 through 100 (all wet).
	 * @param Feedback Percentage of output fed back into input, in the range from 0 through 100. The default value is 0.
	 * @param LeftDelay Delay for left channel, in milliseconds, in the range from 1 through 2000. The default value is 333 ms.
	 * @param RightDelay Delay for right channel, in milliseconds, in the range from 1 through 2000. The default value is 333 ms.
	 * @param PanDelay Value that specifies whether to swap left and right delays with each successive echo.<br>
	 * The default value is FALSE, meaning no swap.<br>
	 * Possible values are defined as TRUE or FALSE.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_FX_Disable(int)
	 * @see FMOD_INSTANCE#FSOUND_FX_Enable(int, int)
	 * @see FSOUND_FX_MODES
	 * @see FMOD_INSTANCE#FSOUND_FX_SetChorus(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetCompressor(int, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetDistortion(int, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetFlanger(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetGargle(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetI3DL2Reverb(int, int, int, float, float, float, int, float, int, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetParamEQ(int, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetWavesReverb(int, float, float, float, float)
	 */
	public boolean FSOUND_FX_SetEcho(
		int fxid,
		float WetDryMix,
		float Feedback,
		float LeftDelay,
		float RightDelay,
		boolean PanDelay)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_FX_SetEcho(
			pointer,
			fxid,
			WetDryMix,
			Feedback,
			LeftDelay,
			RightDelay,
			PanDelay);
	}

	/**
	 * Sets the parameters for the flanger effect on a particular channel<br>
	 * <br><b>Remarks :</b><br>
	 * Make sure you have enabled this effect with FSOUND_FX_Enable and FSOUND_FX_FLANGER before using this function.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32
	 * @param fxid fx handle generated by FSOUND_FX_Enable, to set flanger parameters for.
	 * @param WetDryMix Ratio of wet (processed) signal to dry (unprocessed) signal. Must be in the range from 0 through 100 (all wet).
	 * @param Depth Percentage by which the delay time is modulated by the low-frequency oscillator (LFO), in hundredths of a percentage point.<br>
	 * Must be in the range from 0 through 100. The default value is 25.
	 * @param Feedback Percentage of output signal to feed back into the effects input, in the range from -99 to 99. The default value is 0
	 * @param Frequency Frequency of the LFO, in the range from 0 to 10. The default value is 0.
	 * @param Waveform Waveform of the LFO. By default, the waveform is a sine. Possible values are defined as follows: <br>
	 * 0 - Triangle. <br>
	 * 1 - Sine.
	 * @param Delay Number of milliseconds the input is delayed before it is played back, in the range from 0 to 4. The default value is 0 ms.
	 * @param Phase Phase differential between left and right LFOs, in the range from 0 through 4. Possible values are defined as follows: <br>
	 * 0 -180 degrees<br>
	 * 1 - 90 degrees<br>
	 * 2 0 degrees<br>
	 * 3 90 degrees<br>
	 * 4 180 degrees<br>
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_FX_Disable(int)
	 * @see FMOD_INSTANCE#FSOUND_FX_Enable(int, int)
	 * @see FSOUND_FX_MODES
	 * @see FMOD_INSTANCE#FSOUND_FX_SetChorus(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetCompressor(int, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetDistortion(int, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetEcho(int, float, float, float, float, boolean)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetGargle(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetI3DL2Reverb(int, int, int, float, float, float, int, float, int, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetParamEQ(int, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetWavesReverb(int, float, float, float, float)
	 */
	public boolean FSOUND_FX_SetFlanger(
		int fxid,
		float WetDryMix,
		float Depth,
		float Feedback,
		float Frequency,
		int Waveform,
		float Delay,
		int Phase)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_FX_SetFlanger(
			pointer,
			fxid,
			WetDryMix,
			Depth,
			Feedback,
			Frequency,
			Waveform,
			Delay,
			Phase);
	}

	/**
	 * Sets the parameters for the gargle effect on a particular channel<br>
	 * <br><b>Remarks :</b><br>
	 * Make sure you have enabled this effect with FSOUND_FX_Enable and FSOUND_FX_GARGLE before using this function.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32
	 * @param fxid fx handle generated by FSOUND_FX_Enable, to set gargle parameters for.
	 * @param RateHz Rate of modulation, in Hertz. Must be in the range from 1 through 1000.
	 * @param WaveShape Shape of the modulation wave. The following values are defined. <br>
	 * 0 - Triangular wave. <br>
	 * 1 - Square wave.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_FX_Disable(int)
	 * @see FMOD_INSTANCE#FSOUND_FX_Enable(int, int)
	 * @see FSOUND_FX_MODES
	 * @see FMOD_INSTANCE#FSOUND_FX_SetChorus(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetCompressor(int, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetDistortion(int, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetEcho(int, float, float, float, float, boolean)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetFlanger(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetI3DL2Reverb(int, int, int, float, float, float, int, float, int, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetParamEQ(int, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetWavesReverb(int, float, float, float, float)
	 */
	public boolean FSOUND_FX_SetGargle(int fxid, int RateHz, int WaveShape)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_FX_SetGargle(pointer, fxid, RateHz, WaveShape);
	}

	/**
	 * Sets the parameters for the I3DL2 Reverb effect on a particular channel<br>
	 * <br><b>Remarks :</b><br>
	 * Make sure you have enabled this effect with FSOUND_FX_Enable and FSOUND_FX_I3DL2REVERB before using this function.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32
	 * @param fxid fx handle generated by FSOUND_FX_Enable, to set I3DL2 Reverb parameters for.
	 * @param Room Attenuation of the room effect, in millibels (mB), in the range from -10000 to 0. The default value is -1000 mB.
	 * @param RoomHF Attenuation of the room high-frequency effect, in mB, in the range from -10000 to 0. The default value is 0 mB.
	 * @param RoomRolloffFactor Rolloff factor for the reflected signals, in the range from 0 to 10. The default value is 0.0.<br>
	 * The rolloff factor for the direct path is controlled by the listener.
	 * @param DecayTime Decay time, in seconds, in the range from .1 to 20. The default value is 1.49 seconds.
	 * @param DecayHFRatio Ratio of the decay time at high frequencies to the decay time at low frequencies, in the range from 0.1 to 2.<br>
	 * The default value is 0.83.
	 * @param Reflections Attenuation of early reflections relative to lRoom, in mB, in the range from -10000 to 1000.<br>
	 * The default value is -2602 mB.
	 * @param ReflectionsDelay Delay time of the first reflection relative to the direct path, in seconds, in the range from 0 to 0.3.<br>
	 * The default value is 0.007 seconds.
	 * @param Reverb Attenuation of late reverberation relative to lRoom, in mB, in the range from -10000 to 2000.<br>
	 * The default value is 200 mB.
	 * @param ReverbDelay Time limit between the early reflections and the late reverberation relative to the time of the first<br>
	 * reflection, in seconds, in the range from 0 to 0.1. The default value is 0.011 seconds.
	 * @param Diffusion Echo density in the late reverberation decay, in percent, in the range from 0 to 100.<br>
	 * The default value is 100.0 percent
	 * @param Density Modal density in the late reverberation decay, in percent, in the range from 0 to 100.<br>
	 * The default value is 100.0 percent.
	 * @param HFReference Reference high frequency, in hertz, in the range from 20 to 20000. The default value is 5000.0 Hz.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_FX_Disable(int)
	 * @see FMOD_INSTANCE#FSOUND_FX_Enable(int, int)
	 * @see FSOUND_FX_MODES
	 * @see FMOD_INSTANCE#FSOUND_FX_SetChorus(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetCompressor(int, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetDistortion(int, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetEcho(int, float, float, float, float, boolean)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetFlanger(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetGargle(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetParamEQ(int, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetWavesReverb(int, float, float, float, float)
	 */
	public boolean FSOUND_FX_SetI3DL2Reverb(
		int fxid,
		int Room,
		int RoomHF,
		float RoomRolloffFactor,
		float DecayTime,
		float DecayHFRatio,
		int Reflections,
		float ReflectionsDelay,
		int Reverb,
		float ReverbDelay,
		float Diffusion,
		float Density,
		float HFReference)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_FX_SetI3DL2Reverb(
			pointer,
			fxid,
			Room,
			RoomHF,
			RoomRolloffFactor,
			DecayTime,
			DecayHFRatio,
			Reflections,
			ReflectionsDelay,
			Reverb,
			ReverbDelay,
			Diffusion,
			Density,
			HFReference);
	}

	/**
	 * Sets the parameters for the Param EQ effect on a particular channel<br>
	 * <br><b>Remarks :</b><br>
	 * Make sure you have enabled this effect with FSOUND_FX_Enable and FSOUND_FX_PARAMEQ before using this function.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32
	 * @param fxid fx handle generated by FSOUND_FX_Enable, to set ParamEQ parameters for.
	 * @param Center Center frequency, in hertz, in the range from 80 to 16000. This value cannot exceed one-third
	 * of the frequency of the buffer.<br>
	 * Default is 8000.
	 * @param Bandwidth Bandwidth, in semitones, in the range from 1 to 36.<br>
	 * Default is 12.
	 * @param Gain Gain, in the range from -15 to 15.<br>
	 * Default is 0.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_FX_Disable(int)
	 * @see FMOD_INSTANCE#FSOUND_FX_Enable(int, int)
	 * @see FSOUND_FX_MODES
	 * @see FMOD_INSTANCE#FSOUND_FX_SetChorus(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetCompressor(int, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetDistortion(int, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetEcho(int, float, float, float, float, boolean)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetFlanger(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetGargle(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetI3DL2Reverb(int, int, int, float, float, float, int, float, int, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetWavesReverb(int, float, float, float, float)
	 */
	public boolean FSOUND_FX_SetParamEQ(int fxid, float Center, float Bandwidth, float Gain)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_FX_SetParamEQ(pointer, fxid, Center, Bandwidth, Gain);
	}

	/**
	 * Sets the parameters for the Waves Reverb effect on a particular channel<br>
	 * <br><b>Remarks :</b><br>
	 * Make sure you have enabled this effect with FSOUND_FX_WAVES_REVERB before using this function.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32
	 * @param fxid fx handle generated by FSOUND_FX_Enable, number/handle to set Waves Reverb parameters for.
	 * @param InGain Input gain of signal, in decibels (dB), in the range from -96 through 0.<br>
	 * The default value is 0 dB.
	 * @param ReverbMix Reverb mix, in dB, in the range from -96 through 0.<br>
	 * The default value is 0 dB.
	 * @param ReverbTime Reverb time, in milliseconds, in the range from .001 through 3000.<br>
	 * The default value is 1000.
	 * @param HighFreqRTRatio In the range from .001 through .999.<br>
	 * The default value is 0.001.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_FX_Disable(int)
	 * @see FMOD_INSTANCE#FSOUND_FX_Enable(int, int)
	 * @see FSOUND_FX_MODES
	 * @see FMOD_INSTANCE#FSOUND_FX_SetChorus(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetCompressor(int, float, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetDistortion(int, float, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetEcho(int, float, float, float, float, boolean)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetFlanger(int, float, float, float, float, int, float, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetGargle(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetI3DL2Reverb(int, int, int, float, float, float, int, float, int, float, float, float, float)
	 * @see FMOD_INSTANCE#FSOUND_FX_SetParamEQ(int, float, float, float)
	 */
	public boolean FSOUND_FX_SetWavesReverb(
		int fxid,
		float InGain,
		float ReverbMix,
		float ReverbTime,
		float HighFreqRTRatio)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_FX_SetWavesReverb(
			pointer,
			fxid,
			InGain,
			ReverbMix,
			ReverbTime,
			HighFreqRTRatio);
	}

	/**
	 * Sets the internal file buffersize for audio streaming of data for the NEXT stream opened with FSOUND_Stream_Open. <br>
	 * Larger values will consume more memory (see remarks), whereas smaller values may be subject to large delays in disk access,
	 * especially from CDROM.<br>
	 * <br><b>Remarks :</b><br>
	 * The default setting is 200ms. Under Windows CE it is default to 100ms.<br>
	 * To calculate memory usage for a stream buffer, it is a simple matter of calculating sizebytes = streambuffersize * sample rate / 1000 * (bitdepth / 8) * numchannels * 2,
	 * where numchannels is 1 for mono, or 2 for stereo files. It is multiplied by 2 because FSOUND stream buffers are double buffers.<br>
	 * Note this function does not affect user created streams, as the buffer size is specified in FSOUND_Stream_Create.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param ms Time in milliseconds between stream updates. FMOD tries to access the disk and
	 * decompress data every period specified. Values less than 50 result in an error.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_Create(FSOUND_STREAMCALLBACK, int, int, int, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 */
	public boolean FSOUND_Stream_SetBufferSize(int ms)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_SetBufferSize(pointer, ms);
	}

	/**
	 * Opens an audio file/url/cd ready for streaming. <br>
	 * This opens the file in preparation for playback in real-time, without needing to decode the whole file into memory first.<br>
	 * <br><b>Remarks :</b><br>
	 * WAV support supports windows codec compressed WAV files.<br>
	 * --------------<br>
	 * FSOUND_MPEGACCURATE is to be used cautiously. To open a file with this mode turned on, it has to scan the whole MP3 first. This can take several seconds if the file is big, or the harddisk/cpu is slow.<br>
	 * A way to speed up this process would be to load the compressed mp3 into memory first, and use the FSOUND_LOADMEMORY flag with this function.<br>
	 * --------------<br>
	 * NOTE : Internet stream limitations<br>
	 * - URLs must start with "http://".<br>
	 * - The only supported formats for HTTP streams are MP3 (must have .mp3 extension) and OggVorbis (must have .ogg extension).<br>
	 * --------------<br>
	 * FSB streaming is not supported if the format from FSBank is 'Retain original format'. On PC platforms, only PCM and ADPCM FSB files
	 * are allowed.<br>
	 * --------------<br>
	 * Note, on PlayStation 2 you cannot use FSOUND_LOADMEMORY, you may use FSOUND_LOADMEMORYIOP though.<br>
	 * --------------<br>
	 * When opening with the FSOUND_NONBLOCKING flag, this function always succeeds at the point of being called. <br>
	 * It will always return a valid channel handle, even though the file might fail to open. To determine any error in non blocking mode
	 * use FSOUND_Stream_GetOpenState.<br>
	 * --------------<br>
	 * NOTE: CDDA Streaming (Win32 only!)<br>
	 * To open a CD for CDDA streaming, specify the drive letter of a CD drive e.g. FSOUND_Stream_Open("d:", 0, 0, 0); FSOUND_Stream_Open will
	 * create a stream with multiple substreams, one for each CD track. Use FSOUND_Stream_SetSubStream to select which CD track to play.<br>
	 * A number of options can be passed to FSOUND_Stream_Open along with the drive letter. They are :<br>
	 * *? e.g. FSOUND_Stream_Open("d:*?", 0, 0, 0); This option will cause a tag field called "CD_DEVICE_INFO" to be attached to the stream.
	 * This tag field contains information on the specified CD device.<br>
	 * *! e.g. FSOUND_Stream_Open("d:*!", 0, 0, 0); This option will cause the stream to be opened in "quick open" mode.
	 * When a stream is opened in this mode, calls to FSOUND_Stream_SetSubStream will return immediately making it quick to select each substream
	 * in turn and get the length of each CD track. Note that a stream in quick open mode cannot be played! Use quick open mode to get track
	 * lengths and then re-open the stream without quick open mode to actually play it.<br>
	 * *j e.g. FSOUND_Stream_Open("d:*j", 0, 0, 0); This option turns jitter correction OFF.<br>
	 * *a e.g. FSOUND_Stream_Open("d:*a", 0, 0, 0); This option will force FMOD to use ASPI to access the specified CD drive as opposed to NTSCSI.
	 * It should generally only be used as a last resort if FMOD is unable to access drives that are known to be working with other programs.<br>
	 * Options can be combined like so: FSOUND_Stream_Open("d:*?!j", 0, 0, 0);<br>
	 * If a nonblocking CDDA stream fails to open, a tag field called "CD_ERROR" will be attached to the stream. This tag field contains a textual
	 * description of why the stream failed to open.<br>
	 * NOTE: FMOD will always try to use native NTSCSI support to communicate with CD devices before trying to use ASPI, unless the "*a" option is
	 * specified, in which case FMOD will only try to use ASPI. Unlike in pre-3.73 versions, FMOD can now access all CD drives when using ASPI.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param name Name of the file to open.
	 * @param mode Simple description of how to play the file.<br>
	 * For all formats except raw PCM,
	 * FSOUND_LOOP*, FSOUND_HW3D, FSOUND_HW2D, FSOUND_2D, FSOUND_LOADMEMORY, FSOUND_LOADRAW, FSOUND_MPEGACCURATE, FSOUND_NONBLOCKING
	 * flags are the only ones supported.<br>
	 * @param offset Optional. 0 by default. If > 0, this value is used to specify an offset in a file, so fmod will seek before
	 * opening. length must also be specified if this value is used.
	 * @param length Optional. 0 by default. If > 0, this value is used to specify the length of a memory block when using FSOUND_LOADMEMORY,
	 * or it is the length of a file or file segment if the offset parameter is used.<br>
	 * On PlayStation 2 this must be 16 byte aligned for memory loading.
	 * @return On success, a pointer to an opened stream is returned.<br>
	 * On failure, NULL is returned.
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetLength(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetLengthMs(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetOpenState(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetBufferProperties(IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetLastServerStatus()
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetStatus(FSOUND_STREAM, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetBufferProperties(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetMetadataCallback(FSOUND_STREAM, FSOUND_METADATACALLBACK, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetProxy(String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_PlayEx(int, FSOUND_STREAM, FSOUND_DSPUNIT, boolean)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetBufferSize(int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSubStream(FSOUND_STREAM, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Stop(FSOUND_STREAM)
	 */
	public FSOUND_STREAM FSOUND_Stream_Open(String name, int mode, int offset, int length)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_Open(pointer, name, mode, offset, length);
		return (cPtr == 0) ? null : FSOUND_STREAM.createView(Pointer.newPointer(cPtr));
	}
	/**
	 * Opens an audio file/url/cd ready for streaming. <br>
	 * This opens the file in preparation for playback in real-time, without needing to decode the whole file into memory first.<br>
	 * <br><b>Remarks :</b><br>
	 * WAV support supports windows codec compressed WAV files.<br>
	 * --------------<br>
	 * FSOUND_MPEGACCURATE is to be used cautiously. To open a file with this mode turned on, it has to scan the whole MP3 first. This can take several seconds if the file is big, or the harddisk/cpu is slow.<br>
	 * A way to speed up this process would be to load the compressed mp3 into memory first, and use the FSOUND_LOADMEMORY flag with this function.<br>
	 * --------------<br>
	 * NOTE : Internet stream limitations<br>
	 * - URLs must start with "http://".<br>
	 * - The only supported formats for HTTP streams are MP3 (must have .mp3 extension) and OggVorbis (must have .ogg extension).<br>
	 * --------------<br>
	 * FSB streaming is not supported if the format from FSBank is 'Retain original format'. On PC platforms, only PCM and ADPCM FSB files
	 * are allowed.<br>
	 * --------------<br>
	 * Note, on PlayStation 2 you cannot use FSOUND_LOADMEMORY, you may use FSOUND_LOADMEMORYIOP though.<br>
	 * --------------<br>
	 * When opening with the FSOUND_NONBLOCKING flag, this function always succeeds at the point of being called. <br>
	 * It will always return a valid channel handle, even though the file might fail to open. To determine any error in non blocking mode
	 * use FSOUND_Stream_GetOpenState.<br>
	 * --------------<br>
	 * NOTE: CDDA Streaming (Win32 only!)<br>
	 * To open a CD for CDDA streaming, specify the drive letter of a CD drive e.g. FSOUND_Stream_Open("d:", 0, 0, 0); FSOUND_Stream_Open will
	 * create a stream with multiple substreams, one for each CD track. Use FSOUND_Stream_SetSubStream to select which CD track to play.<br>
	 * A number of options can be passed to FSOUND_Stream_Open along with the drive letter. They are :<br>
	 * *? e.g. FSOUND_Stream_Open("d:*?", 0, 0, 0); This option will cause a tag field called "CD_DEVICE_INFO" to be attached to the stream.
	 * This tag field contains information on the specified CD device.<br>
	 * *! e.g. FSOUND_Stream_Open("d:*!", 0, 0, 0); This option will cause the stream to be opened in "quick open" mode.
	 * When a stream is opened in this mode, calls to FSOUND_Stream_SetSubStream will return immediately making it quick to select each substream
	 * in turn and get the length of each CD track. Note that a stream in quick open mode cannot be played! Use quick open mode to get track
	 * lengths and then re-open the stream without quick open mode to actually play it.<br>
	 * *j e.g. FSOUND_Stream_Open("d:*j", 0, 0, 0); This option turns jitter correction OFF.<br>
	 * *a e.g. FSOUND_Stream_Open("d:*a", 0, 0, 0); This option will force FMOD to use ASPI to access the specified CD drive as opposed to NTSCSI.
	 * It should generally only be used as a last resort if FMOD is unable to access drives that are known to be working with other programs.<br>
	 * Options can be combined like so: FSOUND_Stream_Open("d:*?!j", 0, 0, 0);<br>
	 * If a nonblocking CDDA stream fails to open, a tag field called "CD_ERROR" will be attached to the stream. This tag field contains a textual
	 * description of why the stream failed to open.<br>
	 * NOTE: FMOD will always try to use native NTSCSI support to communicate with CD devices before trying to use ASPI, unless the "*a" option is
	 * specified, in which case FMOD will only try to use ASPI. Unlike in pre-3.73 versions, FMOD can now access all CD drives when using ASPI.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param data pointer to data, you must use FSOUND_LOADMEMORY.
	 * @param mode Simple description of how to play the file.<br>
	 * For all formats except raw PCM,
	 * FSOUND_LOOP*, FSOUND_HW3D, FSOUND_HW2D, FSOUND_2D, FSOUND_LOADMEMORY, FSOUND_LOADRAW, FSOUND_MPEGACCURATE, FSOUND_NONBLOCKING
	 * flags are the only ones supported.<br>
	 * @param offset Optional. 0 by default. If > 0, this value is used to specify an offset in a file, so fmod will seek before
	 * opening. length must also be specified if this value is used.
	 * @param length Optional. 0 by default. If > 0, this value is used to specify the length of a memory block when using FSOUND_LOADMEMORY,
	 * or it is the length of a file or file segment if the offset parameter is used.<br>
	 * On PlayStation 2 this must be 16 byte aligned for memory loading.
	 * @return On success, a pointer to an opened stream is returned.<br>
	 * On failure, NULL is returned.
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetLength(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetLengthMs(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetOpenState(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetBufferProperties(IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetLastServerStatus()
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetStatus(FSOUND_STREAM, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetBufferProperties(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetMetadataCallback(FSOUND_STREAM, FSOUND_METADATACALLBACK, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetProxy(String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_PlayEx(int, FSOUND_STREAM, FSOUND_DSPUNIT, boolean)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetBufferSize(int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSubStream(FSOUND_STREAM, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Stop(FSOUND_STREAM)
	 */
	public FSOUND_STREAM FSOUND_Stream_Open(ByteBuffer data, int mode, int offset, int length)
	{
		if(pointer == 0) throw new NullPointerException();
		if(data != null && !data.isDirect()) {
			throw new NonDirectBufferException();
		}
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_Open(pointer, data, BufferUtils.getPositionInBytes(data), mode, offset, length);
		return (cPtr == 0) ? null : FSOUND_STREAM.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Creates a user definable stream file ready for playing. The stream is serviced through a callback.<br>
	 * <br><b>Remarks :</b><br>
	 * This method only supports SIGNED RAW streams to be written to the buffer supplied by the callback. <br>
	 * They can be 8 or 16 bit, mono or stereo.<br>
	 * 'lenbytes' may be rounded down to the nearest sample alignment in bytes. Ie if you specified 1001 bytes for a 16bit stereo sample stream,
	 * len would return 1000 in the callback. (250 samples * 4 bytes per sample)<br>
	 * PlayStation 2 IMPORTANT! : if FSOUND_SendData is NOT called from the stream callback the IOP will hang because
	 * it is waiting for this command to be executed before it can unlock its buffer.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param callback A pointer to a user defined stream callback function.
	 * @param length Size of the data in BYTES the callback will require to be written to the buffer.
	 * @param mode Description of the raw sample data being opened. see FSOUND_MODES for a description of these modes.
	 * @param samplerate Rate of playback. Be careful you dont set the sample rate too high so that the stream servicer (ie the harddisk)
	 * may not keep up.
	 * @param userdata User data that is passed back into the stream callback when triggered.
	 * @return User data that is passed back into the stream callback when triggered.
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_PlayEx(int, FSOUND_STREAM, FSOUND_DSPUNIT, boolean)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetBufferSize(int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Stop(FSOUND_STREAM)
	 * @see FSOUND_STREAMCALLBACK
	 */
	public FSOUND_STREAM FSOUND_Stream_Create(FSOUND_STREAMCALLBACK callback,
		int length, int mode, int samplerate, Pointer userdata)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_Create(pointer,
				callback, length, mode, samplerate,
				Pointer.getPointer(userdata));
		return (cPtr == 0) ? null : FSOUND_STREAM.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Shuts down and releases an FSOUND stream.<br>
	 * <br><b>Remarks :</b><br>
	 * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.<br>
	 * The only exception to this rule is for internet streams - this function will successfully close an internet stream that has been opened with FSOUND_NONBLOCKING before that stream is ready.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Pointer to the stream to be closed down.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_Create(FSOUND_STREAMCALLBACK, int, int, int, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetBufferProperties(IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetLastServerStatus()
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetStatus(FSOUND_STREAM, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetBufferProperties(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetMetadataCallback(FSOUND_STREAM, FSOUND_METADATACALLBACK, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetProxy(String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_PlayEx(int, FSOUND_STREAM, FSOUND_DSPUNIT, boolean)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Stop(FSOUND_STREAM)
	 */
	public boolean FSOUND_Stream_Close(FSOUND_STREAM stream)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_Close(pointer, Pointer.getPointer(stream));
	}

	/**
	 * Starts a pre-opened stream playing.<br>
	 * <br><b>Remarks :</b><br>
	 * When a stream starts to play, it inherits a special high priority (256). <br>
	 * It cannot be rejected by other sound effect channels in the normal fashion as the user can never set a priority above 255 normally. <br>
	 * --------------<br>
	 * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.<br>
	 * --------------<br>
	 * FSB streaming is not supported if the format from FSBank is 'Retain original format'. On PC platforms, only PCM and ADPCM FSB files are allowed.<br>
	 * --------------<br>
	 * FSOUND_STEREOPAN is recommended for stereo streams if you call FSOUND_SetPan. This puts the left and right channel to full volume.<br>
	 * Otherwise a normal pan will give half volume for left and right. See FSOUND_SetPan for more information on this.<br>
	 * --------------<br>
	 * You can use normal channel based commands (such as FSOUND_SetVolume etc) on the return handle, as it is a channel handle.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Pointer to the already opened stream to be played.
	 * @return On success, the channel handle the stream is playing in is returned.<br>
	 * On failure, -1 is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetNumSubChannels(int)
	 * @see FMOD_INSTANCE#FSOUND_GetSubChannel(int, int)
	 * @see FMOD_INSTANCE#FSOUND_Init(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetPan(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetVolume(int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Create(FSOUND_STREAMCALLBACK, int, int, int, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetBufferProperties(IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetLastServerStatus()
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetStatus(FSOUND_STREAM, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetBufferProperties(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetMetadataCallback(FSOUND_STREAM, FSOUND_METADATACALLBACK, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetProxy(String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_PlayEx(int, FSOUND_STREAM, FSOUND_DSPUNIT, boolean)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetEndCallback(FSOUND_STREAM, FSOUND_STREAMCALLBACK, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetLoopCount(FSOUND_STREAM, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSubStream(FSOUND_STREAM, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSubStreamSentence(FSOUND_STREAM, IntBuffer, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSyncCallback(FSOUND_STREAM, FSOUND_STREAMCALLBACK, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Stop(FSOUND_STREAM)
	 */
	public int FSOUND_Stream_Play(int channel, FSOUND_STREAM stream)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_Play(pointer, channel, Pointer.getPointer(stream));
	}

	/**
	 * Extended featured version of FSOUND_Stream_Play.<br>
	 * Added functionality includes the ability to start the stream paused. This allows attributes
	 * of a stream channel to be set freely before the stream actually starts playing, until FSOUND_SetPaused(FALSE) is used.<br>
	 * Also added is the ability to associate the stream channel to a specified DSP unit. This allows
	 * the user to 'group' channels into seperate DSP units, which allows effects to be inserted
	 * between these 'groups', and allow various things like having one group affected by reverb (wet mix) and another group of
	 * channels unaffected (dry). This is useful to seperate things like music from being affected
	 * by DSP effects, while other sound effects are.<br>
	 * <br><b>Remarks :</b><br>
	 * When a stream starts to play, it inherits a special high priority (256). <br>
	 * It cannot be rejected by other sound effect channels in the normal fashion as the user can never set a priority above 255 normally. <br>
	 * --------<br>
	 * FSOUND_STEREOPAN is recommended for stereo streams if you call FSOUND_SetPan. This puts the left and right channel to full volume.<br>
	 * Otherwise a normal pan will give half volume for left and right. See FSOUND_SetPan for more information on this.<br>
	 * --------<br>
	 * You can use normal channel based commands (such as FSOUND_SetVolume etc) on the return handle, as it is a channel handle.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param channel 0+ <br>
	 * The absolute channel number in the channel pool. <br>
	 * Remember software channels come first, followed by hardware channels. <br>
	 * You cannot play a software sample on a hardware channel and vice versa.<br>
	 * FSOUND_FREE<br>
	 * Chooses a free channel to play in. If all channels are used then it<br>
	 * selects a channel with a sample playing that has an EQUAL or LOWER priority  than the sample to be played.
	 * @param stream Pointer to the already opened stream to be played.
	 * @param dsp Pointer to a dsp unit to attach the channel to.
	 * @param startpaused Start the stream paused or not. Pausing the stream channel allows attributes to be set before it is unpaused.
	 * @return On success, a channel handle the stream is playing in is returned.<br>
	 * On failure, -1 is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetNumSubChannels(int)
	 * @see FMOD_INSTANCE#FSOUND_GetSubChannel(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetPan(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetPaused(int, boolean)
	 * @see FMOD_INSTANCE#FSOUND_SetVolume(int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Create(FSOUND_STREAMCALLBACK, int, int, int, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Stop(FSOUND_STREAM)
	 */
	public int FSOUND_Stream_PlayEx(int channel, FSOUND_STREAM stream, FSOUND_DSPUNIT dsp, boolean startpaused)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_PlayEx(
			pointer,
			channel,
			Pointer.getPointer(stream),
			Pointer.getPointer(dsp),
			startpaused);
	}

	/**
	 * Stops a stream from playing.<br>
	 * <br><b>Remarks :</b><br>
	 * The stream is still prepared and sitting in memory ready to go. Use FSOUND_Stream_Close on the stream to completely remove it.<br>
	 * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Pointer to a stream to stop.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Create(FSOUND_STREAMCALLBACK, int, int, int, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetBufferProperties(IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetLastServerStatus()
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetStatus(FSOUND_STREAM, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetBufferProperties(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetMetadataCallback(FSOUND_STREAM, FSOUND_METADATACALLBACK, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetProxy(String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_PlayEx(int, FSOUND_STREAM, FSOUND_DSPUNIT, boolean)
	 */
	public boolean FSOUND_Stream_Stop(FSOUND_STREAM stream)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_Stop(pointer, Pointer.getPointer(stream));
	}

	/**
	 * Sets the current stream's FILE position in BYTES.<br>
	 * <br><b>Remarks :</b><br>
	 * Position functions for streams talk in bytes and NOT samples.<br>
	 * The reason for not taking the header into account is people usually want to know the offset to seek to
	 * relative to the start of their data (ie as they see it in soundforge or whatever), not from offset 0
	 * which is almost meaningless if you dont know the format.<br>
	 * --------------<br>
	 * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Pointer to the stream to have its position set.
	 * @param position Offset in bytes from start of actual sound data (not including any header)
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetLength(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetPosition(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetTime(FSOUND_STREAM, int)
	 */
	public boolean FSOUND_Stream_SetPosition(FSOUND_STREAM stream, int position)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_SetPosition(pointer, Pointer.getPointer(stream), position);
	}

	/**
	 * Returns the current FILE position of the stream of the stream in BYTES.<br>
	 * <br><b>Remarks :</b><br>
	 * Position functions for streams work in bytes not samples.<br>
	 * Position information is also based on the current file position, not the actual playing
	 * position, so if the stream is only updated every 100ms, then the position will only be
	 * updated every 100ms.<br>
	 * -----<br>
	 * This function is not supported for URL based streams over the internet or CDDA streams<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Pointer to the stream to have its position returned.
	 * @return On success, the current stream's position in BYTES is returned.<br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetLength(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetPosition(FSOUND_STREAM, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetTime(FSOUND_STREAM, int)
	 */
	public int FSOUND_Stream_GetPosition(FSOUND_STREAM stream)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetPosition(pointer, Pointer.getPointer(stream));
	}

	/**
	 * Sets the current stream's FILE position in MILLISECONDS.<br>
	 * <br><b>Remarks :</b><br>
	 * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.<br>
	 * FSOUND_MPEGACCURATE will need to be used with mp3 files that use VBR encoding for more accuracy.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Pointer to the stream to have its position set.
	 * @param ms Time in milliseconds to seek to.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetLength(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetPosition(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetTime(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetPosition(FSOUND_STREAM, int)
	 */
	public boolean FSOUND_Stream_SetTime(FSOUND_STREAM stream, int ms)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_SetTime(pointer, Pointer.getPointer(stream), ms);
	}

	/**
	 * Returns the current time offset in stream in milliseconds.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_MPEGACCURATE will need to be used with mp3 files that use VBR encoding for more accuracy.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Pointer to the stream to get the currently playing time offset.
	 * @return On success, the current stream's position in milliseconds is returned.<br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetTime(FSOUND_STREAM, int)
	 */
	public int FSOUND_Stream_GetTime(FSOUND_STREAM stream)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetTime(pointer, Pointer.getPointer(stream));
	}

	/**
	 * Returns the size of the stream in BYTES.<br>
	 * <br><b>Remarks :</b><br>
	 * Position functions for streams work in bytes not samples.<br>
	 * -----<br>
	 * This function is not supported for URL based streams over the internet.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream A pointer to the stream to have its length returned.
	 * @return On success, the size of the stream in BYTES is returned.<br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetLengthMs(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetPosition(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetPosition(FSOUND_STREAM, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetTime(FSOUND_STREAM, int)
	 */
	public int FSOUND_Stream_GetLength(FSOUND_STREAM stream)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetLength(pointer, Pointer.getPointer(stream));
	}

	/**
	 * Returns the size of the stream in MILLISECONDS.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_MPEGACCURATE will need to be used with mp3 files that use VBR encoding for more accuracy.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream A pointer to the stream to have its total duration returned.
	 * @return On success, the size of the stream in MILLISECONDS is returned.<br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetLength(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 */
	public int FSOUND_Stream_GetLengthMs(FSOUND_STREAM stream)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetLengthMs(pointer, Pointer.getPointer(stream));
	}

	/**
	 * Set a streams mode.<br>
	 * <br><b>Remarks :</b><br>
	 * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.<br>
	 * Only the following modes are accepted, others will be filtered out.<br>
	 * FSOUND_LOOP_BIDI, FSOUND_LOOP_NORMAL, FSOUND_LOOP_OFF, FSOUND_2D. FSOUND_LOOP_BIDI is treated as FSOUND_LOOP_NORMAL. FSOUND_2D is accepted only if the sound is not hardware.<br>
	 * On playstation 2, FSOUND_HW3D and FSOUND_HW2D modes are accepted<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Pointer to the stream to have the mode set.
	 * @param mode The mode bits to set from FSOUND_MODES.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetMode(FSOUND_STREAM)
	 */
	public boolean FSOUND_Stream_SetMode(FSOUND_STREAM stream, int mode)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_SetMode(pointer, Pointer.getPointer(stream), mode);
	}

	/**
	 * Retrieves the mode of the stream.<br>
	 * <br><b>Remarks :</b><br>
	 * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Pointer to the stream to get the mode from.
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetMode(FSOUND_STREAM, int)
	 */
	public int FSOUND_Stream_GetMode(FSOUND_STREAM stream)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetMode(pointer, Pointer.getPointer(stream));
	}

	/**
	 * Sets the loop points for a stream.<br>
	 * <br><b>Remarks :</b><br>
	 * For streams, setting looppoints is reasonably accurate but should not be assumed to be perfectly sample accurate in all cases.<br>
	 * It depends on the compression format in some cases as seek positions need to be rounded to the nearest compression block offset.<br>
	 * FSOUND_MPEGACCURATE will need to be used with mp3 files that use VBR encoding for more accuracy.<br>
	 * You cannot call this function wile the stream is playing, it has to be stopped.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream  The stream to set the loop points on.
 	 * @param loopstartpcm The end of the loop, specified in PCM SAMPLES.
	 * @param loopendpcm The end of the loop, specified in PCM SAMPLES.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 */
	public boolean FSOUND_Stream_SetLoopPoints(FSOUND_STREAM stream, int loopstartpcm, int loopendpcm)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_SetLoopPoints(pointer,
			Pointer.getPointer(stream), loopstartpcm, loopendpcm);
	}

	/**
	 * Sets the stream to loop the number of times specified by the user. If not called it loops forever.<br>
	 * <br><b>Remarks :</b><br>
	 * This specifies how many loops, not how many times to play the sound back. Therefore when you specify 0, you will hear the sound once, if you specify 1, you will hear the sound twice, and so on.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Pointer to the stream to set loop count on.
	 * @param count Number of times to loop. 0 would be similar to having FSOUND_LOOP_OFF set. <0 is infinity.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 */
	public boolean FSOUND_Stream_SetLoopCount(FSOUND_STREAM stream, int count)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_SetLoopCount(pointer, Pointer.getPointer(stream), count);
	}

	/**
	 * If a stream is opened with FSOUND_NONBLOCKING, this function returns the state of the opening stream.<br>
	 * <br><b>Remarks :</b><br>
	 * A blocking stream will return NULL from FSOUND_Stream_Open so a return value of -3 is redundant in this case.<br>
	 * A blocking stream will always return 0 if it is not NULL.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream  Pointer to the stream to get the open state from.
	 * @return 0 = stream is opened and ready.<br>
	 * -1 = stream handle passed in is invalid.<br>
	 * -2 = stream is still opening or performing a SetSubStream command.<br>
	 * -3 = stream failed to open. (file not found, out of memory or other error).<br>
	 * -4 = connecting to remote host (internet streams only)<br>
	 * -5 = stream is buffering data (internet streams only)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSubStream
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSubStreamSentence
	 */
	public int FSOUND_Stream_GetOpenState(FSOUND_STREAM stream)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetOpenState(pointer, Pointer.getPointer(stream));
	}

	/**
	 * Returns the FSOUND_SAMPLE definition that the stream uses internally. <br>
	 * You can use this to get a variety of information like the songs name, default speed and more.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream A pointer to the stream to have its internal sample pointer returned.
	 * @return On success, a handle to the FSOUND_SAMPLE definition is returned.<br>
	 * On failure, 0 is returned.
	 */
	public FSOUND_SAMPLE FSOUND_Stream_GetSample(FSOUND_STREAM stream)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetSample(pointer, Pointer.getPointer(stream));
		return (cPtr == 0) ? null : FSOUND_SAMPLE.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Allows the user to add a custom DSP unit to a stream.<br>
	 * <br><b>Remarks :</b><br>
	 * The priority for a stream DSP unit is not related to the priorities specified in fmod.h.<br>
	 * The priorities are anything fom 0 onwards, and ALWAYS come after data is read/decoded for the stream. <br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, GameCube
	 * @param stream  The stream pointer to have a DSP attached to.
	 * @param callback A standard FSOUND_DSPCALLBACK function callback pointer.
	 * @param priority The priority, or position within the streams DSP chain to place the unit.
	 * @param userdata A user parameter that gets passed back into the DSP callback.
	 * @return On success, a handle to the FSOUND_DSPUNIT is returned. All DSP functions are performable on this.<br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FSOUND_DSP_Create(FSOUND_DSPCALLBACK, int, Pointer)
	 * @see FSOUND_DSPCALLBACK
	 */
	public FSOUND_DSPUNIT FSOUND_Stream_CreateDSP(
		FSOUND_STREAM stream,
		FSOUND_DSPCALLBACK callback,
		int priority,
		Pointer userdata)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr =
			FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_CreateDSP(
				pointer,
				Pointer.getPointer(stream),
				callback,
				priority,
				Pointer.getPointer(userdata));
		return (cPtr == 0) ? null : FSOUND_DSPUNIT.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Sets a callback function for when a stream has ended.<br>
	 * <br><b>Remarks :</b><br>
	 * Only calls back when a stream stops. (not when a looping stream reaches its end point)<br>
	 * Note it uses a FSOUND_STREAMCALLBACK function callback. This is normally for user streams but for
	 * the sake of re-usability this prototype is used. 'buff' and 'length' are NULL and 0 in this case
	 * when the callback occurs. The return value can be TRUE or FALSE it is ignored.<br>
	 * -----------<br>
	 * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Pointer to the stream to callback on when it is finished.
	 * @param callback Function to be called when stream ends.
	 * @param userdata data that is passed into the callback at the end of the stream.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FSOUND_STREAMCALLBACK
	 */
	public boolean FSOUND_Stream_SetEndCallback(
		FSOUND_STREAM stream,
		FSOUND_STREAMCALLBACK callback,
		Pointer userdata)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_SetEndCallback(
			pointer,
			Pointer.getPointer(stream),
			callback,
			Pointer.getPointer(userdata));
	}

	/**
	 * Sets a callback function for when a stream passes over a WAV tag/marker. These are markers that
	 * a sound editing program such as Sound Forge can drop into the actual wave data. FMOD will
	 * trigger callbacks with these markers when the stream plays, and pass in the string through the callback that the marker contains.<br>
	 * <br><b>Remarks :</b><br>
	 * Note it uses a FSOUND_STREAMCALLBACK function callback. This is normally for user streams but for
	 * the sake of re-usability this prototype is used. 'buff' is a null terminated string provided by
	 * the marker. 'len' is the offset in samples that the marker was set at.<br>
	 * The return value can be TRUE or FALSE, it is ignored.<br>
	 * -----------<br>
	 * Note you can save a WAV out using an MP3 wav codec (and then just rename the WAV to MP3 if you like) to get
	 * sync marker support for compressed MP3 files. FMOD will pick up on this and read the markers out.<br>
	 * --------------<br>
	 * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Pointer to the stream to callback when a sync point is reached.
	 * @param callback Function to call when sync point is reached.
	 * @param userdata user data that is passed into the callback.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_Stream_AddSyncPoint(FSOUND_STREAM, int, String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_DeleteSyncPoint(FSOUND_SYNCPOINT)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetNumSyncPoints(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetSyncPoint(FSOUND_STREAM, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetSyncPointInfo(FSOUND_SYNCPOINT, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FSOUND_STREAMCALLBACK
	 */
	public boolean FSOUND_Stream_SetSyncCallback(
		FSOUND_STREAM stream,
		FSOUND_STREAMCALLBACK callback,
		Pointer userdata)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_SetSyncCallback(
			pointer,
			Pointer.getPointer(stream),
			callback,
			Pointer.getPointer(userdata));
	}

	/**
	 * Adds a user synchronization callback point into a stream.<br>
	 * <br><b>Remarks :</b><br>
	 * If the stream has been opened with FSOUND_NONBLOCKING, this function will not succeed until the stream is ready.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream The stream to add a sync point to.
	 * @param pcmoffset Offset in SAMPLES (not bytes).
	 * @param name  The name of the syncpoint, which will be passed into the sync callback when it is triggered.
 	 * @return On success, a sync point handle is returned.<br>
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_DeleteSyncPoint(FSOUND_SYNCPOINT)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetNumSyncPoints(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetSyncPoint(FSOUND_STREAM, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetSyncPointInfo(FSOUND_SYNCPOINT, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSyncCallback(FSOUND_STREAM, FSOUND_STREAMCALLBACK, Pointer)
	 */
	public FSOUND_SYNCPOINT FSOUND_Stream_AddSyncPoint(FSOUND_STREAM stream, int pcmoffset, String name)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_AddSyncPoint(pointer,
				Pointer.getPointer(stream), pcmoffset, name);
		return (cPtr == 0) ? null : FSOUND_SYNCPOINT.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Removes a user synchronization callback point from a stream.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param point The sync point to remove
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_AddSyncPoint(FSOUND_STREAM, int, String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetNumSyncPoints(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetSyncPoint(FSOUND_STREAM, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSyncCallback(FSOUND_STREAM, FSOUND_STREAMCALLBACK, Pointer)
	 */
	public boolean FSOUND_Stream_DeleteSyncPoint(FSOUND_SYNCPOINT point)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_DeleteSyncPoint(pointer, Pointer.getPointer(point));
	}

	/**
	 * Returns the number of sync points within a stream.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream The stream to request the number of sync points from.
	 * @return On success, The number of sync points in a stream are returned.<br>
	 * On failure, -1 is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_AddSyncPoint(FSOUND_STREAM, int, String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_DeleteSyncPoint(FSOUND_SYNCPOINT)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetSyncPointInfo(FSOUND_SYNCPOINT, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSyncCallback(FSOUND_STREAM, FSOUND_STREAMCALLBACK, Pointer)
	 */
	public int FSOUND_Stream_GetNumSyncPoints(FSOUND_STREAM stream)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetNumSyncPoints(pointer, Pointer.getPointer(stream));
	}

	/**
	 * Obtains a sync point by index. This is useful when you havent created your own, ie it came from a wav file.<br>
	 * <br><b>Remarks :</b><br>
	 * Points are loaded in order of offset, so the index will represent the smallest point to the largest.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream The stream to request the sync point from.
	 * @param index The sync point offset into the stream.
	 * @return On success, a handle to a sync point is returned.<br>
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_AddSyncPoint(FSOUND_STREAM, int, String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_DeleteSyncPoint(FSOUND_SYNCPOINT)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetSyncPointInfo(FSOUND_SYNCPOINT, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSyncCallback(FSOUND_STREAM, FSOUND_STREAMCALLBACK, Pointer)
	 */
	public FSOUND_SYNCPOINT FSOUND_Stream_GetSyncPoint(FSOUND_STREAM stream, int index)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr =
			FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetSyncPoint(pointer, Pointer.getPointer(stream), index);
		return (cPtr == 0) ? null : FSOUND_SYNCPOINT.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Retrieves the name and pcm offset in samples for a specified sync point.<br>
	 * <br><b>Remarks :</b><br>
	 * Convert samples to time by dividing the PCM value by the default samplerate.
	 * This would give you the value in seconds. Multiply by 1000 to get milliseconds.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param point The handle to the sync point to retrieve information from.
	 * @param pcmoffset A pointer to an integer that will receive the sync point offset in pcm SAMPLES. A value of NULL will be ignored.
	 * @return On success, the name of the syncpoint is returned as a string.<br>
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_AddSyncPoint(FSOUND_STREAM, int, String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetNumSyncPoints(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetSyncPoint(FSOUND_STREAM, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSyncCallback(FSOUND_STREAM, FSOUND_STREAMCALLBACK, Pointer)
	 */
	public String FSOUND_Stream_GetSyncPointInfo(FSOUND_SYNCPOINT point, int[] pcmoffset)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetSyncPointInfo(pointer,
			Pointer.getPointer(point), pcmoffset);
	}
	/**
	 * Retrieves the name and pcm offset in samples for a specified sync point.<br>
	 * <br><b>Remarks :</b><br>
	 * Convert samples to time by dividing the PCM value by the default samplerate.
	 * This would give you the value in seconds. Multiply by 1000 to get milliseconds.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param point The handle to the sync point to retrieve information from.
	 * @param pcmoffset A pointer to an integer that will receive the sync point offset in pcm SAMPLES. A value of NULL will be ignored.
	 * @return On success, the name of the syncpoint is returned as a string.<br>
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_AddSyncPoint(FSOUND_STREAM, int, String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetNumSyncPoints(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetSyncPoint(FSOUND_STREAM, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSyncCallback(FSOUND_STREAM, FSOUND_STREAMCALLBACK, Pointer)
	 */
	public String FSOUND_Stream_GetSyncPointInfo(FSOUND_SYNCPOINT point, IntBuffer pcmoffset)
	{
		if(pointer == 0) throw new NullPointerException();
		if(pcmoffset != null && !pcmoffset.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetSyncPointInfo(pointer,
			Pointer.getPointer(point), pcmoffset, BufferUtils.getPositionInBytes(pcmoffset));
	}

	/**
	 * Seeks a stream to the substream inside a multi-stream FSB bank file, specified by its index.<br>
	 * <br><b>Remarks :</b><br>
	 * A stream will stop if this function is called, as it needs to seek and flush the buffer.<br>
	 * Indicies for this function are generated as user friendly constants when compiling the FSB bank, and are available in the relevant
	 * generated header file.<br>
	 * --------------<br>
	 * If the stream has been opened with FSOUND_NONBLOCKING, this function will ALWAYS succeed, but puts the stream back into a non-ready state.
	 * You then have to poll after calling this to make sure the stream is ready.<br>
	 * You can either do this by calling FSOUND_Stream_Play repeatedly/once a frame until it is succeeds, or FSOUND_Stream_GetOpenState.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Handle to the stream to have its position set.
	 * @param index The index of the stream within the FSB file.
	 * @return On success, TRUE is returned.
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetNumSubStreams(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetOpenState(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSubStreamSentence(FSOUND_STREAM, IntBuffer, int)
	 */
	public boolean FSOUND_Stream_SetSubStream(FSOUND_STREAM stream, int index)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_SetSubStream(pointer, Pointer.getPointer(stream), index);
	}

	/**
	 * Returns the number of substreams inside a multi-stream FSB bank file.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream  Pointer to the stream to query.
	 * @return On success, the number of FSB substreams is returned.<br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSubStream(FSOUND_STREAM, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSubStreamSentence(FSOUND_STREAM, IntBuffer, int)
	 */
	public int FSOUND_Stream_GetNumSubStreams(FSOUND_STREAM stream)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetNumSubStreams(pointer, Pointer.getPointer(stream));
	}

	/**
	 * This function allows the user to describe the playback order of a list of substreams. The substreams will be played back in order seamlessly.<br>
	 * <br><b>Remarks :</b><br>
	 * This feature only works with FSB files that have multiple streams stored within it.<br>
	 * To remove any sentence, simply call this function with NULL and 0.<br>
	 * FMOD copies the list from the supplied pointer. Once the pointer is used, the caller can discard the original array.<br>
	 * This function will fail if the stream is playing. The stream must be stopped for it to work.<br>
	 * ------------<br>
	 * If the stream is opened with FSOUND_NONBLOCKING, and the stream is not ready (it is still opening), then this function will return FALSE.<br>
	 * When it is ready, it will return TRUE, but after this call the stream is put back into a non-ready state, because it is asynchronously seeking again.<br>
	 * You then have to poll after calling this to make sure the stream is ready.<br>
	 * You can either do this by calling FSOUND_Stream_Play repeatedly/once a frame until it is succeeds, or FSOUND_Stream_GetOpenState.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Pointer to the stream to have its position returned.
	 * @param sentencelist This is a pointer to an array of 32bit integers, describing a list of substream indicies to play back.
	 * @param numitems The number of entries in the sentence list.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetNumSubStreams(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetOpenState(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSubStream(FSOUND_STREAM, int)
	 */
	public boolean FSOUND_Stream_SetSubStreamSentence(FSOUND_STREAM stream, int[] sentencelist, int numitems)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_SetSubStreamSentence(
			pointer,
			Pointer.getPointer(stream),
			sentencelist,
			numitems);
	}
	/**
	 * This function allows the user to describe the playback order of a list of substreams. The substreams will be played back in order seamlessly.<br>
	 * <br><b>Remarks :</b><br>
	 * This feature only works with FSB files that have multiple streams stored within it.<br>
	 * To remove any sentence, simply call this function with NULL and 0.<br>
	 * FMOD copies the list from the supplied pointer. Once the pointer is used, the caller can discard the original array.<br>
	 * This function will fail if the stream is playing. The stream must be stopped for it to work.<br>
	 * ------------<br>
	 * If the stream is opened with FSOUND_NONBLOCKING, and the stream is not ready (it is still opening), then this function will return FALSE.<br>
	 * When it is ready, it will return TRUE, but after this call the stream is put back into a non-ready state, because it is asynchronously seeking again.<br>
	 * You then have to poll after calling this to make sure the stream is ready.<br>
	 * You can either do this by calling FSOUND_Stream_Play repeatedly/once a frame until it is succeeds, or FSOUND_Stream_GetOpenState.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param stream Pointer to the stream to have its position returned.
	 * @param sentencelist This is a pointer to an array of 32bit integers, describing a list of substream indicies to play back.
	 * @param numitems The number of entries in the sentence list.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetNumSubStreams(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetOpenState(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_SetSubStream(FSOUND_STREAM, int)
	 */
	public boolean FSOUND_Stream_SetSubStreamSentence(FSOUND_STREAM stream, IntBuffer sentencelist, int numitems)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_SetSubStreamSentence(
			pointer,
			Pointer.getPointer(stream),
			sentencelist, BufferUtils.getPositionInBytes(sentencelist),
			numitems);
	}

	/**
	 * Get the number of tag fields associated with the specified stream.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh
	 * @param stream The stream to get the number of tag fields for.
	 * @param num Pointer to a variable that will receive the nubmer of tag fields associated with the specified stream.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_FindTagField(FSOUND_STREAM, int, String, Pointer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetTagField(FSOUND_STREAM, int, IntBuffer, Pointer, Pointer, IntBuffer)
	 */
	public boolean FSOUND_Stream_GetNumTagFields(FSOUND_STREAM stream, int[] num)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetNumTagFields(pointer, Pointer.getPointer(stream), num);
	}
	/**
	 * Get the number of tag fields associated with the specified stream.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh
	 * @param stream The stream to get the number of tag fields for.
	 * @param num Pointer to a variable that will receive the nubmer of tag fields associated with the specified stream.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_FindTagField(FSOUND_STREAM, int, String, Pointer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetTagField(FSOUND_STREAM, int, IntBuffer, Pointer, Pointer, IntBuffer)
	 */
	public boolean FSOUND_Stream_GetNumTagFields(FSOUND_STREAM stream, IntBuffer num)
	{
		if(pointer == 0) throw new NullPointerException();
		if(num != null && !num.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetNumTagFields(pointer, Pointer.getPointer(stream), num, BufferUtils.getPositionInBytes(num));
	}

	/**
	 * Get a tag field associated with an open stream.<br>
	 * <br><b>Remarks :</b><br>
	 * Do not attempt to modify or free any pointers returned by this function.<br>
	 * If this function returns successfully, "value" will contain a pointer to a piece of tag-field-specific
	 * data - do not assume it will always point to a null-terminated ASCII string.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh
	 * @param stream The number of the tag field to retrieve.
	 * @param num The number of the tag field to retrieve.
	 * @param type Pointer to a variable that will receive the type of the tag field that was retrieved. See FSOUND_TAGFIELD_TYPE
	 * @param name Pointer to a variable that will receive the name of the tag field as a null-terminated ASCII string.<BR>
	 * Use <code>BufferUtils</code> or <code>PointerUtils</code> class to retrieve the String value of tis parameter after calling this method.
	 * @param value Pointer to a variable that will receive a pointer to the tag field data.
	 * @param length Pointer to a variable that will receive the length of the tag field data.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_FindTagField(FSOUND_STREAM, int, String, Pointer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetNumTagFields(FSOUND_STREAM, IntBuffer)
	 * @see FSOUND_TAGFIELD_TYPE
	 */
	public boolean FSOUND_Stream_GetTagField(FSOUND_STREAM stream, int num, int[] type,
		Pointer name, Pointer value, int[] length)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetTagField(pointer, Pointer.getPointer(stream),
				num, type, name, value, length);
	}
	/**
	 * Get a tag field associated with an open stream.<br>
	 * <br><b>Remarks :</b><br>
	 * Do not attempt to modify or free any pointers returned by this function.<br>
	 * If this function returns successfully, "value" will contain a pointer to a piece of tag-field-specific
	 * data - do not assume it will always point to a null-terminated ASCII string.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh
	 * @param stream The number of the tag field to retrieve.
	 * @param num The number of the tag field to retrieve.
	 * @param type Pointer to a variable that will receive the type of the tag field that was retrieved. See FSOUND_TAGFIELD_TYPE
	 * @param name Pointer to a variable that will receive the name of the tag field as a null-terminated ASCII string.<BR>
	 * Use <code>BufferUtils</code> or <code>PointerUtils</code> class to retrieve the String value of tis parameter after calling this method.
	 * @param value Pointer to a variable that will receive a pointer to the tag field data.
	 * @param length Pointer to a variable that will receive the length of the tag field data.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_FindTagField(FSOUND_STREAM, int, String, Pointer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetNumTagFields(FSOUND_STREAM, IntBuffer)
	 * @see FSOUND_TAGFIELD_TYPE
	 */
	public boolean FSOUND_Stream_GetTagField(FSOUND_STREAM stream, int num, IntBuffer type,
		Pointer name, Pointer value, IntBuffer length)
	{
		if(pointer == 0) throw new NullPointerException();
		if(type != null && !type.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(length != null && !length.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_GetTagField(pointer,
			Pointer.getPointer(stream), num, type, BufferUtils.getPositionInBytes(type),
			name, value, length, BufferUtils.getPositionInBytes(length));
	}

	/**
	 * Find a tag field associated with an open stream by name and type.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh
	 * @param stream The stream to get the tag field from.
	 * @param type The type of the tag field to retrieve. See FSOUND_TAGFIELD_TYPE.
	 * @param name The name of the tag field to retrieve.
	 * @param value Pointer to a variable that will receive a pointer to the tag field data.
	 * @param length Pointer to a variable that will receive the length of the tag field data.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetNumTagFields(FSOUND_STREAM, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetTagField(FSOUND_STREAM, int, IntBuffer, Pointer, Pointer, IntBuffer)
	 * @see FSOUND_TAGFIELD_TYPE
	 */
	public boolean FSOUND_Stream_FindTagField(FSOUND_STREAM stream, int type, String name,
		Pointer value, int[] length)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_FindTagField(pointer, Pointer.getPointer(stream),
				type, name, value, length);
	}
	/**
	 * Find a tag field associated with an open stream by name and type.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh
	 * @param stream The stream to get the tag field from.
	 * @param type The type of the tag field to retrieve. See FSOUND_TAGFIELD_TYPE.
	 * @param name The name of the tag field to retrieve.
	 * @param value Pointer to a variable that will receive a pointer to the tag field data.
	 * @param length Pointer to a variable that will receive the length of the tag field data.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetNumTagFields(FSOUND_STREAM, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_GetTagField(FSOUND_STREAM, int, IntBuffer, Pointer, Pointer, IntBuffer)
	 * @see FSOUND_TAGFIELD_TYPE
	 */
	public boolean FSOUND_Stream_FindTagField(FSOUND_STREAM stream, int type, String name,
		Pointer value, IntBuffer length)
	{
		if(pointer == 0) throw new NullPointerException();
		if(length != null && !length.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_FindTagField(pointer, Pointer.getPointer(stream),
				type, name, value,
				length, BufferUtils.getPositionInBytes(length));
	}

	/**
	 * Set a proxy server to use for all subsequent internet connections.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Macintosh, Linux
	 * @param proxy The name of a proxy server in host:port format e.g. www.fmod.org:8888 (defaults to port 80 if no port is specified).<br>
	 * Basic authentication is supported. To use it, this parameter must be in<br>
	 * user:password@host:port format e.g. bob:sekrit123@www.fmod.org:8888<br>
	 * Set this parameter to NULL if no proxy is required.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetBufferProperties(IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetLastServerStatus()
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetStatus(FSOUND_STREAM, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetBufferProperties(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetMetadataCallback(FSOUND_STREAM, FSOUND_METADATACALLBACK, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Stop(FSOUND_STREAM)
	 */
	public boolean FSOUND_Stream_Net_SetProxy(String proxy)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_Net_SetProxy(pointer, proxy);
	}

	/**
	 * Sets the timeout for internet streams.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Macintosh, Linux
	 * @param timeout The timeout value in ms
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see Fmod#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see Fmod#FSOUND_Stream_Net_GetBufferProperties(IntBuffer, IntBuffer, IntBuffer)
	 * @see Fmod#FSOUND_Stream_Net_GetLastServerStatus()
	 * @see Fmod#FSOUND_Stream_Net_GetStatus(FSOUND_STREAM, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see Fmod#FSOUND_Stream_Net_SetBufferProperties(int, int, int)
	 * @see Fmod#FSOUND_Stream_Net_SetMetadataCallback(FSOUND_STREAM, FSOUND_METADATACALLBACK, Pointer)
	 * @see Fmod#FSOUND_Stream_Net_SetProxy(String)
	 * @see Fmod#FSOUND_Stream_Open(String, int, int, int)
	 * @see Fmod#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see Fmod#FSOUND_Stream_Stop(FSOUND_STREAM)
	 */
	public boolean FSOUND_Stream_Net_SetTimeout(int timeout)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FSOUND_Stream_Net_SetTimeout(pointer, timeout);
	}

	/**
	 * This function returns a pointer to the last HTTP status line that was received when connecting to an internet stream.<br>
	 * <br><b>Remarks :</b><br>
	 * The result of this function should be used for informational purposes only.<br>
	 * This function provides no facility to discover which internet stream the last HTTP status pertains to when there are
	 * multiple internet streams open.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Macintosh, Linux
	 * @return Pointer to the last HTTP status line (null-terminated ASCII string) that was received.
	 * @see FMOD_INSTANCE#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetBufferProperties(IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetStatus(FSOUND_STREAM, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetBufferProperties(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetMetadataCallback(FSOUND_STREAM, FSOUND_METADATACALLBACK, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetProxy(String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Stop(FSOUND_STREAM)
	 */
	public String FSOUND_Stream_Net_GetLastServerStatus()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_Net_GetLastServerStatus(pointer);
	}

	/**
	 * Sets buffer size and thresholds to use when opening new internet streams.<br>
	 * <br><b>Remarks :</b><br>
	 * Call this function before FSOUND_Stream_Open. This function has no effect on internet streams that are already open.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Macintosh, Linux
	 * @param buffersize Size in bytes of the streaming buffer. Make it bigger to avoid buffer underruns. (Default = 64000)
	 * @param prebuffer_percent How much to prebuffer when a stream is first opened.<br>
	 * Values are expressed as a percentage from 1 to 99. (Default = 95)
	 * @param rebuffer_percent How much to rebuffer after a stream has suffered a buffer underrun.<br>
	 * Values are expressed as a percentage from 1 to 99. (Default = 95)
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetBufferProperties(IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetLastServerStatus()
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetStatus(FSOUND_STREAM, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetMetadataCallback(FSOUND_STREAM, FSOUND_METADATACALLBACK, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetProxy(String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Stop(FSOUND_STREAM)
	 */
	public boolean FSOUND_Stream_Net_SetBufferProperties(int buffersize, int prebuffer_percent, int rebuffer_percent)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_Net_SetBufferProperties(
			pointer,
			buffersize,
			prebuffer_percent,
			rebuffer_percent);
	}

	/**
	 * Gets buffer size and thresholds that will be used when opening new internet streams.<br>
	 * <br><b>Remarks :</b><br>
	 * This function returns the values that will be used for subsequent internet stream opens. Internet streams that already exist may have different values.<br>
	 * Any of the parameters can be NULL, in which case, they will be ignored.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Macintosh, Linux
	 * @param buffersize Pointer to size in bytes of the streaming buffer.
	 * @param prebuffer_percent Pointer to how much to prebuffer when a stream is first opened.<br>
	 * Values are expressed as a percentage from 1 to 99.
	 * @param rebuffer_percent Pointer to how much to rebuffer after a stream has suffered a buffer underrun.<br>
	 * Values are expressed as a percentage from 1 to 99.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetLastServerStatus()
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetStatus(FSOUND_STREAM, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetBufferProperties(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetMetadataCallback(FSOUND_STREAM, FSOUND_METADATACALLBACK, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetProxy(String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Stop(FSOUND_STREAM)
	 */
	public boolean FSOUND_Stream_Net_GetBufferProperties(
		int[] buffersize,
		int[] prebuffer_percent,
		int[] rebuffer_percent)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_Net_GetBufferProperties(
			pointer,
			buffersize,
			prebuffer_percent,
			rebuffer_percent);
	}
	/**
	 * Gets buffer size and thresholds that will be used when opening new internet streams.<br>
	 * <br><b>Remarks :</b><br>
	 * This function returns the values that will be used for subsequent internet stream opens. Internet streams that already exist may have different values.<br>
	 * Any of the parameters can be NULL, in which case, they will be ignored.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Macintosh, Linux
	 * @param buffersize Pointer to size in bytes of the streaming buffer.
	 * @param prebuffer_percent Pointer to how much to prebuffer when a stream is first opened.<br>
	 * Values are expressed as a percentage from 1 to 99.
	 * @param rebuffer_percent Pointer to how much to rebuffer after a stream has suffered a buffer underrun.<br>
	 * Values are expressed as a percentage from 1 to 99.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetLastServerStatus()
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetStatus(FSOUND_STREAM, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetBufferProperties(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetMetadataCallback(FSOUND_STREAM, FSOUND_METADATACALLBACK, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetProxy(String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Stop(FSOUND_STREAM)
	 */
	public boolean FSOUND_Stream_Net_GetBufferProperties(
		IntBuffer buffersize,
		IntBuffer prebuffer_percent,
		IntBuffer rebuffer_percent)
	{
		if(pointer == 0) throw new NullPointerException();
		if(buffersize != null && !buffersize.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(prebuffer_percent != null && !prebuffer_percent.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(rebuffer_percent != null && !rebuffer_percent.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_Net_GetBufferProperties(
			pointer,
			buffersize, BufferUtils.getPositionInBytes(buffersize),
			prebuffer_percent, BufferUtils.getPositionInBytes(prebuffer_percent),
			rebuffer_percent, BufferUtils.getPositionInBytes(rebuffer_percent));
	}

	/**
	 * Set a metadata callback for an internet stream.<br>
	 * <br><b>Remarks :</b><br>
	 * The supplied metadata callback function will be called each time the specified internet stream receives a chunk of metadata.<br>
	 * Do not do any time-consuming processing in a metadata callback function or network subsystem performance may degrade.<br>
	 * Do not attempt to modify or free any memory passed to a metadata callback function.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Macintosh, Linux
	 * @param stream The stream to set the metadata callback for.
	 * @param callback Pointer to the metadata callback function to attach to this stream.
	 * @param userdata User-defined value that will be passed to the supplied metadata callback function in the userdata parameter.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FSOUND_METADATACALLBACK
	 * @see FMOD_INSTANCE#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetBufferProperties(IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetLastServerStatus()
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetStatus(FSOUND_STREAM, IntBuffer, IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetBufferProperties(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetProxy(String)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Stop(FSOUND_STREAM)
	 */
	public boolean FSOUND_Stream_Net_SetMetadataCallback(
		FSOUND_STREAM stream,
		FSOUND_METADATACALLBACK callback,
		Pointer userdata)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_Net_SetMetadataCallback(
			pointer,
			Pointer.getPointer(stream),
			callback,
			Pointer.getPointer(userdata));
	}

	/**
	 * Get various status information for an internet stream.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Macintosh, Linux
	 * @param stream The stream to get status information on.
	 * @param status Pointer to a variable that will receive a status value. See FSOUND_STREAM_NET_STATUS.
	 * @param bufferpercentused Pointer to a variable that will receive the percentage of the read buffer that is currently in use.
	 * @param bitrate Pointer to a variable that will receive the current bitrate of the stream.
	 * @param flags Pointer to a variable that will receive a flags field describing protocol and format information. See FSOUND_STATUS_FLAGS.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FSOUND_STATUS_FLAGS
	 * @see FMOD_INSTANCE#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetBufferProperties(IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetLastServerStatus()
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetBufferProperties(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetMetadataCallback(FSOUND_STREAM, FSOUND_METADATACALLBACK, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetProxy(String)
	 * @see FSOUND_STREAM_NET_STATUS
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Stop(FSOUND_STREAM)
	 */
	public boolean FSOUND_Stream_Net_GetStatus( FSOUND_STREAM stream,
		int[] status, int[] bufferpercentused, int[] bitrate,
		int[] flags)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_Net_GetStatus(pointer,
			Pointer.getPointer(stream), status, bufferpercentused, bitrate,
			flags);
	}
	/**
	 * Get various status information for an internet stream.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Macintosh, Linux
	 * @param stream The stream to get status information on.
	 * @param status Pointer to a variable that will receive a status value. See FSOUND_STREAM_NET_STATUS.
	 * @param bufferpercentused Pointer to a variable that will receive the percentage of the read buffer that is currently in use.
	 * @param bitrate Pointer to a variable that will receive the current bitrate of the stream.
	 * @param flags Pointer to a variable that will receive a flags field describing protocol and format information. See FSOUND_STATUS_FLAGS.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FSOUND_STATUS_FLAGS
	 * @see FMOD_INSTANCE#FSOUND_Stream_Close(FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetBufferProperties(IntBuffer, IntBuffer, IntBuffer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_GetLastServerStatus()
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetBufferProperties(int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetMetadataCallback(FSOUND_STREAM, FSOUND_METADATACALLBACK, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Net_SetProxy(String)
	 * @see FSOUND_STREAM_NET_STATUS
	 * @see FMOD_INSTANCE#FSOUND_Stream_Open(String, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Play(int, FSOUND_STREAM)
	 * @see FMOD_INSTANCE#FSOUND_Stream_Stop(FSOUND_STREAM)
	 */
	public boolean FSOUND_Stream_Net_GetStatus(FSOUND_STREAM stream,
		IntBuffer status, IntBuffer bufferpercentused, IntBuffer bitrate,
		IntBuffer flags)
	{
		if(pointer == 0) throw new NullPointerException();
		if(status != null && !status.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(bufferpercentused != null && !bufferpercentused.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(bitrate != null && !bitrate.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(flags != null && !flags.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Stream_Net_GetStatus(pointer,
			Pointer.getPointer(stream),
			status, BufferUtils.getPositionInBytes(status),
			bufferpercentused, BufferUtils.getPositionInBytes(bufferpercentused),
			bitrate, BufferUtils.getPositionInBytes(bitrate),
			flags, BufferUtils.getPositionInBytes(flags));
	}

	/**
	 * Plays a CD Audio track. <br>
	 * <br><b>Remarks :</b><br>
	 * See FSOUND_CD_SetPlayMode for information on how to control playback of a CD track.<br>
	 * FSOUND's CD Playback system, is a non intrusive, non polling system. <br>
	 * This may not mean much to a lot of people, but a polling player (take the windows default CD player) will consistantly
	 * poll the CD device to update its status, which causes other applications to jerk, or pause consistantly. <br>
	 * This would be inexcusable in a game, to have the game halt or jerk every second to few seconds or so. <br>
	 * FSOUND uses timing and prediction to loop tracks and update the status of the CD, and never touches the CD device during playback, for TRUE 00pu usage.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Linux, Macintosh (OSX CFM only)
	 * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
	 * @param track the CD track number to play. The first track starts at 1.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_CD_GetVolume(char)
	 * @see FMOD_INSTANCE#FSOUND_CD_SetPlayMode(char, byte)
	 * @see FMOD_INSTANCE#FSOUND_CD_SetVolume(char, int)
	 * @see FMOD_INSTANCE#FSOUND_CD_Stop(char)
	 */
	public boolean FSOUND_CD_Play(char drive, int track)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_CD_Play(pointer, drive, track);
	}

	/**
	 * Sets the playback mode of the CD.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Linux, Macintosh (OSX CFM Only)
	 * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
	 * @param mode See FSOUND_CDPLAYMODES for a list of valid parameters to send to this function.
	 * @see FMOD_INSTANCE#FSOUND_CD_Play(char, int)
	 * @see FSOUND_CDPLAYMODES
	 */
	public void FSOUND_CD_SetPlayMode(char drive, byte mode)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_CD_SetPlayMode(pointer, drive, mode);
	}

	/**
	 * Stops the currently playing CD audio track.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Linux, Macintosh (OSX CFM Only)
	 * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_CD_Play(char, int)
	 */
	public boolean FSOUND_CD_Stop(char drive)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_CD_Stop(pointer, drive);
	}

	/**
	 * Sets the pause status of the currently playing CD audio track.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Linux, Macintosh (OSX CFM Only)
	 * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
	 * @param paused TRUE to pause track, FALSE to unpause track.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 */
	public boolean FSOUND_CD_SetPaused(char drive, boolean paused)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_CD_SetPaused(pointer, drive, paused);
	}

	/**
	 * Sets the volume of the playing CD audio.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Linux, Macintosh (OSX CFM Only)
	 * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
	 * @param volume An integer value from 0-255. 0 being the lowest volume, 255 being the highest (full).
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_CD_Play(char, int)
	 * @see FMOD_INSTANCE#FSOUND_Close()
	 */
	public boolean FSOUND_CD_SetVolume(char drive, int volume)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_CD_SetVolume(pointer, drive, volume);
	}

	/**
	 * Performs a seek within a track specified by milliseconds.<br>
	 * <br><b>Remarks :</b><br>
	 * This function will start the track if it is not playing.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Linux, Macintosh (OSX CFM Only)
	 * @param drive The drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
	 * @param ms Time to seek into the current track in milliseconds.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_CD_GetTrackLength(char, int)
	 * @see FMOD_INSTANCE#FSOUND_CD_GetTrackTime(char)
	 */
	public boolean FSOUND_CD_SetTrackTime(char drive, int ms)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_CD_SetTrackTime(pointer, drive, ms);
	}

	/**
	 * Gets the pause status of the current CD audio track.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Linux, Macintosh (OSX CFM Only)
	 * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
	 * @return If the track is currently paused, TRUE is returned.<br>
	 * If the track is currently not paused, FALSE is returned.
	 */
	public boolean FSOUND_CD_GetPaused(char drive)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_CD_GetPaused(pointer, drive);
	}

	/**
	 * Returns the currently playing CD track number.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Linux, Macintosh (OSX CFM Only)
	 * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
	 * @return On success, the CD track number currently playing is returned. (starts from 1)<br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FSOUND_CD_GetNumTracks(char)
	 */
	public int FSOUND_CD_GetTrack(char drive)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_CD_GetTrack(pointer, drive);
	}

	/**
	 * Returns the number of tracks on the currently inserted CD.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Linux
	 * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
	 * @return On success, the number of CD tracks on the currently inserted is returned. <br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FSOUND_CD_GetTrack(char)
	 */
	public int FSOUND_CD_GetNumTracks(char drive)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_CD_GetNumTracks(pointer, drive);
	}

	/**
	 * Returns the volume of the playing CD audio.<br>
	 * <br><b>Remarks :</b><br>
	 * This is easily one of the slowest functions in the FMOD API. Please use it sparingly. <br>
	 * It seems like it shouldnt take long, but because of windows MCI API it does, and not just a little bit of time, it takes a LOT. <br>
	 * It seems to poll the CD driver and cause a large delay upon completion of the command. <br>
	 * Different algorithms were used to try and emulate this function such as simply using a timer, but this was very inaccurate, especially when pausing/unpausing a lot.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Linux, Macintosh (OSX CFM Only)
	 * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
	 * @return On success, a value between 0 and 255 is returned, 0 being the lowest volume and 255 being the highest.<br>
	 * On failure, -1 is returned.
	 * @see FMOD_INSTANCE#FSOUND_CD_GetTrackLength(char, int)
	 * @see FMOD_INSTANCE#FSOUND_CD_SetTrackTime(char, int)
	 */
	public int FSOUND_CD_GetVolume(char drive)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_CD_GetVolume(pointer, drive);
	}

	/**
	 * Opens/Closes the CD tray.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Linux
	 * @param drive The drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
	 * @param open If open is set to 1, the CD tray will be opened. If open is set to 0, the CD tray will be closed.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @since Fmod version 3.73
	 */
	public boolean FSOUND_CD_OpenTray(char drive, boolean open)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_CD_OpenTray(pointer, drive, open);
	}

	/**
	 * Gets the track length of a CD.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Linux, Macintosh (OSX CFM Only)
	 * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
	 * @param track The CD track number to query the length of. (starts from 1)
	 * @return On success, the length of the current track in milliseconds is returned.<br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FSOUND_CD_GetTrackTime(char)
	 * @see FMOD_INSTANCE#FSOUND_CD_SetTrackTime(char, int)
	 */
	public int FSOUND_CD_GetTrackLength(char drive, int track)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_CD_GetTrackLength(pointer, drive, track);
	}

	/**
	 * Returns the current track time playing on a CD.<br>
	 * <br><b>Remarks :</b><br>
	 * This is easily one of the slowest functions in the FMOD API. Please use it sparingly. <br>
	 * It seems like it shouldnt take long, but because of windows MCI API it does, and not just a little bit of time, it takes a LOT. <br>
	 * It seems to poll the CD driver and cause a large delay upon completion of the command. <br>
	 * Different algorithms were used to try and emulate this function such as simply using a timer, but this was very inaccurate, especially when pausing/unpausing a lot. <br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, Linux, Macintosh (OSX CFM Only)
	 * @param drive the drive ID to use. 0 is the default CD drive. Using D or E in single quotes would be D: or E: for example.
	 * @return On success, the position of the current playing track in milliseconds is returned.<br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FSOUND_CD_GetTrackLength(char, int)
	 * @see FMOD_INSTANCE#FSOUND_CD_SetTrackTime(char, int)
	 */
	public int FSOUND_CD_GetTrackTime(char drive)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_CD_GetTrackTime(pointer, drive);
	}

	/**
	 * Creates a DSP unit, and places it in the DSP chain position specified by the priority
	 * parameter. Read the remarks section carefully for issues regarding DSP units.<br>
	 * DSP units are freed with FSOUND_DSP_Free<br>
	 * <br><b>Remarks :</b><br>
	 * A dsp unit is NOT ACTIVE by default. You have to activate it with FSOUND_DSP_SetActive<br>
	 * ---------------------------------------------------------------------------------------<br>
	 * Priorities and default system units.<br>
	 * ---------------------------------------------------------------------------------------<br>
	 * A note on priorities. FSOUND processes DSP units in order of priority. A 0 priority
	 * unit gets processed first, a 1 priority unit gets processed next, and so on. <br>
	 * FSOUND actually uses these DSP units to mix its sound effects and music! Yes, you have
	 * access to them (be careful!). It is possible to totally remove, replace or deactivate
	 * all of FSOUND's system units so that it does nothing at all!<br>
	 * FSOUND has preinstalled default system units at the following priority locations:<br>
	 * FSOUND_DSP_DEFAULTPRIORITY_CLEARUNIT (priority 0) - Clear Unit. This unit clears out
	 * the mixbuffer for the next units to mix into. You can disable this unit and replace
	 * it with something other than a clearer, such as a scaler, which fades down the mix
	 * buffer instead of clearing it, to produce a very rough echo effect.<br>
	 * FSOUND_DSP_DEFAULTPRIORITY_SFXUNIT (priority 100) - SFX Unit. This unit mixes sound
	 * effect channels into the mix buffer, which was previously cleared with the Clear Unit. <br>
	 * FSOUND_DSP_DEFAULTPRIORITY_MUSICUNIT (priority 200) - Music Unit. This unit mixes all
	 * music channels into the mix buffer, which was previously mixed into with the SFX Unit. <br>
	 * FSOUND_DSP_DEFAULTPRIORITY_CLIPANDCOPYUNIT (priority 1000) - Clip and Copy Unit. This
	 * unit takes the finally mixed buffer, and clips it to the output stream size (if it
	 * needs to), and then sends it off to the sound device. It is done last. If this is
	 * disabled you will hear no sound.<br>
	 * ---------------------------------------------------------------------------------------<br>
	 * Buffer Lengths.<br>
	 * ---------------------------------------------------------------------------------------<br>
	 * The 'length' value of the DSP callback is roughly 20ms worth of data.<br>
	 * Use FSOUND_DSP_GetBufferLength to get the exact callback length.<br>
	 * ---------------------------------------------------------------------------------------<br>
	 * Buffer Widths<br>
	 * ---------------------------------------------------------------------------------------<br>
	 * Remember that FSOUND uses different buffer types depending on what type of mixer it is.<br>
	 * You will have to compensate for this by writing different routines depending on the
	 * mixer type (ie mmx or non mmx), just like FSOUND does. <br>
	 * Currently there are the 3 types of mixers and their buffer sizes.<br>
	 * You can get the type of mixer being used by calling the FSOUND_GetMixer function.<br>
	 * You may want to check on this inside your callback, or set up a function pointer system,
	 * whatever you think is suitable (it costs nothing to do a FSOUND_GetMixer every time).<br>
	 * - FSOUND_MIXER_BLENDMODE : This buffer is a stereo, signed 32bit buffer (8 bytes per
	 * sample). The data is in integer format.<br>
	 * Data written to this buffer is not clipped and passed to the output stream until the
	 * very end of the chain (the clip and copy unit). For this type of mixer, you dont
	 * have to worry about clipping becuase FSOUND does this for you.<br>
	 * - FSOUND_MIXER_QUALITY_FPU / FSOUND_MIXER_QUALITY_FPU_VOLUMERAMP: This buffer is also a
	 * stereo, signed 32bit buffer (8 bytes per sample). This data is in floating point
	 * format.<br>
	 * The same clip and copy rules apply here as for the above mixer.<br>
	 * - Any MMX based mixer : This buffer is a stereo, signed 16bit buffer (4 bytes per sample).<br>
	 * When writing to this buffer, you must make sure the result does not overflow this
	 * signed 16bit range.<br>
	 * If you add data into to this buffer, make sure it is clipped to a signed 16bit range
	 * before writing it back. FSOUND only copies this data to the output stream, it does
	 * not clip it.<br>
	 * ---------------------------------------------------------------------------------------<br>
	 * Speed<br>
	 * ---------------------------------------------------------------------------------------<br>
	 * DSP Units are processed then and there, inside the mixing routine. Remember to make
	 * your process as FAST as possible, or the output device's play cursor will catch up to
	 * FSOUND's write cursor while your routine takes its time to complete, and make it start
	 * to break up. <br>
	 * So basically, if it isnt fast, then FSOUND will not be able to send the data to the
	 * output device in time for the next mixer update, and the result will be corrupted sound.<br>
	 * FSOUND_DSP_MixBuffers is available now, so if you need to mix some raw data into the output
	 * buffer quickly, you can use FSOUND's own optimized mixer directly to do it!<br>
	 * Finally, you can see how your routine affects cpu usage, by using FSOUND_GetCPUUsage.<br>
	 * The cpu usage returned by this function includes any time spent in DSP units as well.<br>
	 * (this function times everything). If you are really bored, you can see how much FSOUND's
	 * system units take cpu-wise, by turning them on and off and seeing how they affect
	 * performance.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, GameCube
	 * @param callback This is a pointer to your DSP Unit callback, of type FSOUND_DSPCALLBACK.
	 * The prototype for a callback is declared in the following fashion.<br>
	 * Callbacks must return a pointer to the buffer you work on, so that
	 * the next dsp unit can work on it. <br>
	 * Here is the simplest case:<br>
	 * 	void *callback(void *originalbuffer, void *newbuffer, int length, void *userdata)<br>
	 * 	{<br>
	 * 		// originalbuffer = fsounds original mixbuffer.<br>
	 * 		// newbuffer = the buffer passed from the previous DSP unit.<br>
	 * 		// length = length in samples at this mix time.<br>
	 * 		// param = user parameter passed through in FSOUND_DSP_Create.<br>
	 * 		//<br>
	 * 		// modify the buffer in some fashion<br>
	 * 		return newbuffer;<br>
	 * 	}<br>
	 * See the definition of FSOUND_DSPCALLBACK for more.
	 * @param priority Order in the priority chain. Valid numbers are 0 to 1000, 0 being
	 * highest priority (first), with 1000 being lowest priority (last).<br>
	 * Note that FSOUNDs soundeffects mixers and copy routines are considered
	 * part of this DSP unit chain which you can play with.
	 * @param userdata User defined parameter, this gets passed into the callback when it is
	 * called. It is safe to leave this value 0.
	 * @return On success, a pointer to a new valid DSP unit is returned.
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FSOUND_DSP_Create(FSOUND_DSPCALLBACK, int, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_DSP_Free(FSOUND_DSPUNIT)
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetBufferLength()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetBufferLengthTotal()
	 * @see FMOD_INSTANCE#FSOUND_DSP_MixBuffers(ByteBuffer, ByteBuffer, int, int, int, int, int)
	 * @see FSOUND_DSP_PRIORITIES
	 * @see FMOD_INSTANCE#FSOUND_DSP_SetActive(FSOUND_DSPUNIT, boolean)
	 * @see FMOD_INSTANCE#FSOUND_DSP_SetPriority(FSOUND_DSPUNIT, int)
	 * @see FSOUND_DSPCALLBACK
	 * @see FMOD_INSTANCE#FSOUND_GetCPUUsage()
	 * @see FMOD_INSTANCE#FSOUND_GetMixer()
	 * @see FSOUND_MIXERTYPES
	 * @see FMOD_INSTANCE#FSOUND_PlaySoundEx(int, FSOUND_SAMPLE, FSOUND_DSPUNIT, boolean)
	 * @see FMOD_INSTANCE#FSOUND_Stream_CreateDSP(FSOUND_STREAM, FSOUND_DSPCALLBACK, int, Pointer)
	 */
	public FSOUND_DSPUNIT FSOUND_DSP_Create(FSOUND_DSPCALLBACK callback, int priority, Pointer userdata)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr =
			FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_Create(
				pointer,
				callback,
				priority,
				Pointer.getPointer(userdata));
		return (cPtr == 0) ? null : FSOUND_DSPUNIT.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Frees and removes a DSP unit from the DSP chain.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param unit Pointer to DSP unit to be freed.
	 * @see FMOD_INSTANCE#FSOUND_DSP_Create(FSOUND_DSPCALLBACK, int, Pointer)
	 */
	public void FSOUND_DSP_Free(FSOUND_DSPUNIT unit)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_Free(pointer, Pointer.getPointer(unit));
	}

	/**
	 * Changes a DSP Unit's priority position in the DSP chain.<br>
	 * <br><b>Remarks :</b><br>
	 * DSP units with the same priority as a previous unit already in the chain will be placed
	 * AFTER all like priority units.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param unit Pointer to DSP unit to have its priority changed.
	 * @param priority Order in the priority chain. Valid numbers are 0 to 1000, 0 being
	 * highest priority (first), with 1000 being lowest priority (last).
	 * @see FMOD_INSTANCE#FSOUND_DSP_Create(FSOUND_DSPCALLBACK, int, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetPriority(FSOUND_DSPUNIT)
	 */
	public void FSOUND_DSP_SetPriority(FSOUND_DSPUNIT unit, int priority)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_SetPriority(pointer, Pointer.getPointer(unit), priority);
	}

	/**
	 * Returns the priority status in the DSP chain, of a specified unit.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param unit Pointer to DSP unit to get priority value from.
	 * @return On success, the priority of the unit, from 0 to 1000.<br>
	 * On failure, -1 is returned.
	 * @see FMOD_INSTANCE#FSOUND_DSP_SetPriority(FSOUND_DSPUNIT, int)
	 */
	public int FSOUND_DSP_GetPriority(FSOUND_DSPUNIT unit)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_GetPriority(pointer, Pointer.getPointer(unit));
	}

	/**
	 * Allows the user to toggle a DSP unit on or off. <br>
	 * <br><b>Remarks :</b><br>
	 * It is possible to toggle on and off FSOUNDs internal DSP units, though not recommended.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param unit Pointer to DSP unit to have its active flag changed.
	 * @param active Flag to say whether DSP unit should be rendered active or inactive. valid
	 * values are TRUE or FALSE.
	 * @see FMOD_INSTANCE#FSOUND_DSP_ClearMixBuffer()
	 * @see FMOD_INSTANCE#FSOUND_DSP_Create(FSOUND_DSPCALLBACK, int, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetActive(FSOUND_DSPUNIT)
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetSpectrum()
	 */
	public void FSOUND_DSP_SetActive(FSOUND_DSPUNIT unit, boolean active)
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_SetActive(pointer, Pointer.getPointer(unit), active);
	}

	/**
	 * Returns if a DSP unit is active or not.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param unit Pointer to DSP unit to have its active flag returned.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_DSP_SetActive(FSOUND_DSPUNIT, boolean)
	 */
	public boolean FSOUND_DSP_GetActive(FSOUND_DSPUNIT unit)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_GetActive(pointer, Pointer.getPointer(unit));
	}

	/**
	 * Returns a pointer to FSOUND's system DSP clear unit.<br>
	 * <br><b>Remarks :</b><br>
	 * The FSOUND clear DSP unit simply sets the mix buffer to 0, silencing it.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return Pointer to the DSP unit.
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetClipAndCopyUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetFFTUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetMusicUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetSFXUnit()
	 */
	public FSOUND_DSPUNIT FSOUND_DSP_GetClearUnit()
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_GetClearUnit(pointer);
		return (cPtr == 0) ? null : FSOUND_DSPUNIT.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Returns a pointer to FSOUND's system DSP SFX mixer unit.<br>
	 * <br><b>Remarks :</b><br>
	 * The FSOUND SFX DSP unit mixes sound effects together spawned by the user.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return Pointer to the DSP unit.
	 * @see FMOD_INSTANCE#FSOUND_DSP_ClearMixBuffer()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetClearUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetClipAndCopyUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetFFTUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetMusicUnit()
	 */
	public FSOUND_DSPUNIT FSOUND_DSP_GetSFXUnit()
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_GetSFXUnit(pointer);
		return (cPtr == 0) ? null : FSOUND_DSPUNIT.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Returns a pointer to FSOUND's system DSP Music mixer unit.<br>
	 * <br><b>Remarks :</b><br>
	 * The FSOUND Music DSP executes the FMUSIC engine and mixes the sounds spawned by the music player.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return Pointer to the DSP unit.
	 * @see FMOD_INSTANCE#FSOUND_DSP_ClearMixBuffer()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetClearUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetClipAndCopyUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetFFTUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetSFXUnit()
	 */
	public FSOUND_DSPUNIT FSOUND_DSP_GetMusicUnit()
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_GetMusicUnit(pointer);
		return (cPtr == 0) ? null : FSOUND_DSPUNIT.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Returns a pointer to FSOUND's system DSP FFT processing unit.<br>
	 * <br><b>Remarks :</b><br>
	 * The FSOUND FFT DSP executes the FFT engine to allow FSOUND_DSP_GetSpectrum to be used.<br>
	 * The FFT unit is off by default, due to the cpu expense incurred in running. Turn it on to use FSOUND_DSP_GetSpectrum.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return Pointer to the DSP unit.
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetClearUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetClipAndCopyUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetMusicUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetSFXUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetSpectrum()
	 */
	public FSOUND_DSPUNIT FSOUND_DSP_GetFFTUnit()
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_GetFFTUnit(pointer);
		return (cPtr == 0) ? null : FSOUND_DSPUNIT.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Returns a pointer to FSOUND's system Clip and Copy DSP unit.<br>
	 * <br><b>Remarks :</b><br>
	 * The FSOUND ClipAndCopy DSP unit clips the 32bit buffer down to fit the soundcard's 16bit stereo output, and sends it off to the hardware.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return Pointer to the DSP unit.
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetClearUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetFFTUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetMusicUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetSFXUnit()
	 */
	public FSOUND_DSPUNIT FSOUND_DSP_GetClipAndCopyUnit()
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_GetClipAndCopyUnit(pointer);
		return (cPtr == 0) ? null : FSOUND_DSPUNIT.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Allows the user to mix their own data from one buffer to another, using FSOUNDs optimized
	 * mixer routines. This was mainly provided for DSP routines, though it can be used for
	 * anything.<br>
	 * <br><b>Remarks :</b><br>
	 * 'destbuffer' should always the format of the mixing output buffer, as it will use the mixer
	 * currently running to do the mixing.<br>
	 * For MMX it is 16bit stereo, so it is 4 bytes per output sample (word left, word right)<br>
	 * For Standard Blend mode it is 32bit stereo, so it is 8 bytes per output sample (left dword, right dword)<br>
	 * For FPU mixer it is 32bit float stereo, so it is 8 bytes per output sample (left float, right float)<br>
	 * FSOUND_GetMixer can be used to determine which mixer is being used.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, GameCube
	 * @param destbuffer Pointer to a buffer to have the data mixed into.
	 * @param srcbuffer Pointer to a buffer to have the data mixed into.
	 * @param len Amount to mix in SAMPLES.
	 * @param freq Speed to mix data to output buffer. Remember if you mix at a rate
	 * different than the output rate, the buffer lengths will have to be
	 * different to compensate. Ie if the output rate is 44100 and you supply
	 * a value of 88200 to FSOUND_DSP_MixBuffers, you will only need a destbuffer
	 * that is half the size of srcbuffer. If you supply a value of 22050 then
	 * you will need a destbuffer that is twice as big as srcbuffer. If they
	 * are both the same size then it will only mix half of the data.
	 * @param vol volume scalar value of mix. Valid values are 0 (silence) to 255 (full volume).<br>
	 * See FSOUND_SetVolume for more information.
	 * @param pan pan value for data being mixed. Valid values are 0 (full left), 128
	 * (middle), 255 (full right) and FSOUND_STEREOPAN.<br>
	 * See FSOUND_SetPan for  more information.
	 * @param mode Bit settings to describe the source buffer. Valid values are found in
	 * FSOUND_MODES, but only 8/16bit and stereo/mono flags are interpreted,
	 * other flags are ignored.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_DSP_Create(FSOUND_DSPCALLBACK, int, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_DSP_MixBuffers(ByteBuffer, ByteBuffer, int, int, int, int, int)
	 * @see FMOD_INSTANCE#FSOUND_GetMixer()
	 * @see FSOUND_MODES
	 * @see FMOD_INSTANCE#FSOUND_SetFrequency(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetPan(int, int)
	 * @see FMOD_INSTANCE#FSOUND_SetVolume(int, int)
	 */
	public boolean FSOUND_DSP_MixBuffers(ByteBuffer destbuffer, ByteBuffer srcbuffer,
		int len, int freq, int vol, int pan, int mode)
	{
		if(pointer == 0) throw new NullPointerException();
		if(destbuffer != null && !destbuffer.isDirect()) {
			throw new NonDirectBufferException();
		}
		if(srcbuffer != null && !srcbuffer.isDirect()) {
			throw new NonDirectBufferException();
		}
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_MixBuffers( pointer,
			destbuffer, BufferUtils.getPositionInBytes(destbuffer),
			srcbuffer, BufferUtils.getPositionInBytes(srcbuffer),
			len, freq, vol, pan, mode);
	}

	/**
	 * Clears the mixbuffer, especially handy if you are doing a large file operation which
	 * halts the system. <br>
	 * You might try and stop all the sounds, but if you do your file operation straight after
	 * this, it will not have a chance to flush the mixbuffer normally, so this function is called.<br>
	 * It stops the effect of stuttering looping sound while your file operation happens.<br>
	 * <br><b>Remarks :</b><br>
	 * The best way to do it is like this. Turn off the sfx and music DSP units, clear the mix buffer,
	 * then when the operation that halts the machine is done, just re-enable the sfx and music DSP units.<br>
	 * Disabling these units stops the timer trying to get 1 or 2 more mixes in during the file operation,
	 * which will cause more stuttering.<br>
	 * ie.<br>
	 * FSOUND_DSP_SetActive(FSOUND_DSP_GetSFXUnit(), FALSE);<br>
	 * FSOUND_DSP_SetActive(FSOUND_DSP_GetMusicUnit(), FALSE);<br>
	 * FSOUND_DSP_ClearMixBuffer();<br>
	 * //<br>
	 * // maching halting operation here<br>
	 * //<br>
	 * FSOUND_DSP_SetActive(FSOUND_DSP_GetSFXUnit(), TRUE);<br>
	 * FSOUND_DSP_SetActive(FSOUND_DSP_GetMusicUnit(), TRUE);<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @see FMOD_INSTANCE#FSOUND_DSP_ClearMixBuffer()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetMusicUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetSFXUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_SetActive(FSOUND_DSPUNIT, boolean)
	 */
	public void FSOUND_DSP_ClearMixBuffer()
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_ClearMixBuffer(pointer);
	}

	/**
	 * Returns the buffer lenth passed by the DSP system to DSP unit callbacks, so you can allocate memory etc
	 * using this data.<br>
	 * <br><b>Remarks :</b><br>
	 * Remember this is samples not bytes. To convert to bytes you
	 * will have to multiply by 4 for mmx mixers, 8 for other mixers.<br>
	 * (a stereo 16bit sample = 4 bytes, and a stereo 32bit sample (ie fpu) = 8 bytes)<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return The size of the DSP unit buffer in SAMPLES (not bytes).
	 * @see FMOD_INSTANCE#FSOUND_DSP_Create(FSOUND_DSPCALLBACK, int, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetBufferLengthTotal()
	 */
	public int FSOUND_DSP_GetBufferLength()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_GetBufferLength(pointer);
	}

	/**
	 * This is the total size in samples (not bytes) of the FSOUND mix buffer. This is affected
	 * by FSOUND_SetBufferSize.<br>
	 * <br><b>Remarks :</b><br>
	 * Remember this is samples not bytes. To convert to bytes you
	 * will have to multiply by 4 for mmx mixers, 8 for other mixers.<br>
	 * (a stereo 16bit sample = 4 bytes, and a stereo 32bit sample (ie fpu) = 8 bytes)<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @return The size of the FSOUND mixing buffer in SAMPLES (not bytes).
	 * @see FMOD_INSTANCE#FSOUND_DSP_Create(FSOUND_DSPCALLBACK, int, Pointer)
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetBufferLength()
	 * @see FMOD_INSTANCE#FSOUND_SetBufferSize(int)
	 */
	public int FSOUND_DSP_GetBufferLengthTotal()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_GetBufferLengthTotal(pointer);
	}

	/**
	 * Function to return a pointer to the current spectrum buffer. The buffer contains 512 floating
	 * point values that represent each frequency band's amplitude for the current FMOD SoundSystem
	 * mixing buffer. The range of frequencies covered by the spectrum is 1 to the nyquist frequency
	 * or half of the output rate. So if the output rate is 44100, then frequencies provided are up
	 * to 22050. (entry 511)<br>
	 * <br><b>Remarks :</b><br>
	 * Note that hardware sounds, MIDI, files do not register on the spectrum graph as they are not run through FMODs DSP system.<br>
	 * Note that to use this you have to turn on the FSOUND FFT DSP unit.
	 * This is achieved by calling FSOUND_DSP_GetFFTUnit, then using FSOUND_DSP_SetActive to turn it on.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, GameCube
	 * @return A pointer to a buffer containing 512 floats.
	 * @see FMOD_INSTANCE#FSOUND_DSP_GetFFTUnit()
	 * @see FMOD_INSTANCE#FSOUND_DSP_SetActive(FSOUND_DSPUNIT, boolean)
	 */
	public FloatBuffer FSOUND_DSP_GetSpectrum()
	{
		if(pointer == 0) throw new NullPointerException();
		ByteBuffer buffer = FmodDynJNI.FMOD_INSTANCE_FSOUND_DSP_GetSpectrum(pointer);
		if(buffer != null)
		{
			buffer.order(ByteOrder.nativeOrder());
			return buffer.asFloatBuffer();
		}
		return null;
	}

	/**
	 * Sets hardware reverb parameters for advanced tuning. <br>
	 * The best way to modify these is to set everything to use pre-defined presets given in the header, and then start modifying values.<br>
	 * <br><b>Remarks :</b><br>
	 * You must be using FSOUND_OUTPUT_DSOUND as the output mode for this to work. <br>
	 * In dsound, the reverb will only work if you have an EAX compatible soundcard such as the SBLive, and your sample/stream was created with the FSOUND_HW3D flag.<br>
	 * For GameCube, use FSOUND_AUXFX_xxx api.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, XBox, PlayStation 2
	 * @param prop A pointer to a FSOUND_REVERB_PROPERTIES struct. The definition for this structure is given in the link below.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Reverb_GetChannelProperties(int, FSOUND_REVERB_CHANNELPROPERTIES)
	 * @see FMOD_INSTANCE#FSOUND_Reverb_GetProperties(FSOUND_REVERB_PROPERTIES)
	 * @see FSOUND_REVERB_PROPERTIES
	 * @see FMOD_INSTANCE#FSOUND_Reverb_SetChannelProperties(int, FSOUND_REVERB_CHANNELPROPERTIES)
	 */
	public boolean FSOUND_Reverb_SetProperties(FSOUND_REVERB_PROPERTIES prop)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Reverb_SetProperties(pointer, Pointer.getPointer(prop));
	}

	/**
	 * Returns the current hardware reverb environment.<br>
	 * <br><b>Remarks :</b><br>
	 * These values are only relevant if you are in DSOUND mode with an EAX3 compatible soundcard, or XBOX and PS2.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, XBox, PlayStation 2
	 * @param prop Pointer to a FSOUND_REVERB_PROPERTIES structure definition. The definiotion for this structure is given in the link below.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Reverb_GetChannelProperties(int, FSOUND_REVERB_CHANNELPROPERTIES)
	 * @see FSOUND_REVERB_PROPERTIES
	 * @see FMOD_INSTANCE#FSOUND_Reverb_SetChannelProperties(int, FSOUND_REVERB_CHANNELPROPERTIES)
	 * @see FMOD_INSTANCE#FSOUND_Reverb_SetProperties(FSOUND_REVERB_PROPERTIES)
	 */
	public boolean FSOUND_Reverb_GetProperties(FSOUND_REVERB_PROPERTIES prop)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Reverb_GetProperties(pointer, Pointer.getPointer(prop));
	}

	/**
	 * Sets the channel specific reverb properties for hardware, including wet/dry mix (room size), and things like obstruction and
	 * occlusion properties.<br>
	 * <br><b>Remarks :</b><br>
	 * FSOUND_ALL is supported here. Passing this will set ALL channels to specified reverb properties.<br>
	 * If FSOUND_ALL is used the last channel success flag will be returned. This return value not useful in most circumstances.<br>
	 * -----------------<br>
	 * Under Win32, you must be using FSOUND_OUTPUT_DSOUND as the output mode for this to work. <br>
	 * In DSound, the reverb will only work if you have an EAX compatible soundcard such as the SBLive, and your sample/stream was created with the FSOUND_HW3D flag.<br>
	 * -----------------<br>
	 * On PlayStation2, the 'Room' parameter is the only parameter supported. The hardware only allows 'on' or 'off', so the reverb will be off when 'Room' is -10000 and on for every other value.<br>
	 * -----------------<br>
	 * On XBox, it is possible to apply reverb to 2d voices using this function. By default reverb is turned off for 2d voices.<br>
	 * If this 2d voice was being positioned in a 5.1 array with the xbox only function FSOUND_SetLevels, then calling this function will disable that capability in favour of enabling reverb for the 2d voice.<br>
	 * It is a limitation of the xbox hardware that only one of the other of these features can be executed at one time.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, XBox, PlayStation 2.
	 * @param channel The channel to have its reverb properties changed. FSOUND_ALL can also be used (see remarks)
	 * @param prop Pointer to a FSOUND_REVERB_CHANNELPROPERTIES structure definition. The definition for this structure is given in the link below.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FSOUND_REVERB_CHANNELPROPERTIES
	 * @see FMOD_INSTANCE#FSOUND_Reverb_GetChannelProperties(int, FSOUND_REVERB_CHANNELPROPERTIES)
	 * @see FMOD_INSTANCE#FSOUND_Reverb_GetProperties(FSOUND_REVERB_PROPERTIES)
	 * @see FMOD_INSTANCE#FSOUND_Reverb_SetProperties(FSOUND_REVERB_PROPERTIES)
	 * @see FMOD_INSTANCE#FSOUND_SetLevels(int, int, int, int, int, int, int)
	 */
	public boolean FSOUND_Reverb_SetChannelProperties(int channel, FSOUND_REVERB_CHANNELPROPERTIES prop)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Reverb_SetChannelProperties(
			pointer,
			channel,
			Pointer.getPointer(prop));
	}

	/**
	 * This function gets the current reverb properties for this channel.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, XBox
	 * @param channel The channel to have its reverb mix returned.
	 * @param prop Pointer to a FSOUND_REVERB_CHANNELPROPERTIES structure definition. The definition for this structure is given in the link below.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FSOUND_REVERB_CHANNELPROPERTIES
	 * @see FMOD_INSTANCE#FSOUND_Reverb_GetProperties(FSOUND_REVERB_PROPERTIES)
	 * @see FMOD_INSTANCE#FSOUND_Reverb_SetChannelProperties(int, FSOUND_REVERB_CHANNELPROPERTIES)
	 * @see FMOD_INSTANCE#FSOUND_Reverb_SetProperties(FSOUND_REVERB_PROPERTIES)
	 */
	public boolean FSOUND_Reverb_GetChannelProperties(int channel, FSOUND_REVERB_CHANNELPROPERTIES prop)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Reverb_GetChannelProperties(
			pointer,
			channel,
			Pointer.getPointer(prop));
	}

	/**
	 * Selects a soundcard recording driver.<br>
	 * It is used when an output mode has enumerated more than one recording device and you need to select between them.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh
	 * @param outputtype Recording driver number to select.<br>
	 * <=0 will select the DEFAULT recording sound driver.<br>
	 * >0 Selects other valid drivers that can be listed with FSOUND_Record_GetDriverName.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Record_GetDriver()
	 * @see FMOD_INSTANCE#FSOUND_Record_GetDriverName(int)
	 * @see FMOD_INSTANCE#FSOUND_Record_GetNumDrivers()
	 */
	public boolean FSOUND_Record_SetDriver(int outputtype)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Record_SetDriver(pointer, outputtype);
	}

	/**
	 * Returns the number of sound cards or devices enumerated for the current input type. (Direct
	 * Sound, WaveOut etc.)<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh
	 * @return Total number of enumerated sound devices.
	 * @see FMOD_INSTANCE#FSOUND_Record_GetDriver()
	 * @see FMOD_INSTANCE#FSOUND_Record_GetDriverName(int)
	 * @see FMOD_INSTANCE#FSOUND_Record_SetDriver(int)
	 */
	public int FSOUND_Record_GetNumDrivers()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Record_GetNumDrivers(pointer);
	}

	/**
	 * Returns the name of the selected recording driver. Drivers are enumerated when selecting a driver with
	 * FSOUND_Record_SetDriver or other driver related functions such as FSOUND_Record_GetNumDrivers or FSOUND_Record_GetDriver<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh
	 * @param id Enumerated driver ID. This must be in a valid range delimited by FSOUND_Record_GetNumDrivers,
	 * @return On success, a pointer to a NULL terminated string containing the name of the specified device is returned.<br>
	 * The number of drivers enumerated can be found with FSOUND_Record_GetNumDrivers.<br>
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FSOUND_Record_GetDriver()
	 * @see FMOD_INSTANCE#FSOUND_Record_GetNumDrivers()
	 * @see FMOD_INSTANCE#FSOUND_Record_SetDriver(int)
	 */
	public String FSOUND_Record_GetDriverName(int id)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Record_GetDriverName(pointer, id);
	}

	/**
	 * Returns the currently selected recording driver number. Drivers are enumerated when selecting a driver
	 * with FSOUND_Record_SetDriver or other driver related functions such as FSOUND_Record_GetNumDrivers or
	 * FSOUND_Record_GetDriverName<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh
	 * @return Currently selected driver id.
	 * @see FMOD_INSTANCE#FSOUND_Record_GetDriver()
	 * @see FMOD_INSTANCE#FSOUND_Record_GetDriverName(int)
	 * @see FMOD_INSTANCE#FSOUND_Record_GetNumDrivers()
	 * @see FMOD_INSTANCE#FSOUND_Record_SetDriver(int)
	 */
	public int FSOUND_Record_GetDriver()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Record_GetDriver(pointer);
	}

	/**
	 * Starts recording into a predefined sample using the sample's default playback rate as the recording rate.<br>
	 * <br><b>Remarks :</b><br>
	 * If you want to play back the sample at the same time is is recording, you will have to play the sound and try
	 * and keep it just behind the recording cursor.<br>
	 * Under FSOUND_OUTPUT_OSS mode, it is single duplex, so playback will stop when recording is in progress!
	 * Try FSOUND_OUTPUT_ALSA for full duplex as they have better drivers in this respect.<br>
	 * -------------<br>
	 * The recording/playback rates are slightly innacurate and are not identical (ie 44100.0 for playback, 44100.1 for recording),
	 * so one could possibly be faster or slower than the other. In this case the recording and the playback cursor could overlap,
	 * and the output will sound corrupted. <br>
	 * To counter this you might adjust the playback frequency of the channel you are playing the record sample on while it plays,
	 * using FSOUND_GetCurrentPosition and FSOUND_Record_GetPosition as calibration points.<br>
	 * In the recording sample there is an example of trying to play back sound as it records, and the mechanism to try and keep the
	 * 2 cursors a safe distance from each other is employed.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh
	 * @param sptr The sample to record into.
	 * @param loop TRUE or FALSE flag whether the recorder should keep recording once it has hit the end,
	 * and start from the start again, therefore creating a continuous recording session into that
	 * sample buffer. Looping the recording buffer is good for realtime processing of recorded
	 * information, as you can record and playback the sample at the same time.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetCurrentPosition(int)
	 * @see FMOD_INSTANCE#FSOUND_Record_GetPosition()
	 * @see FMOD_INSTANCE#FSOUND_Record_Stop()
	 */
	public boolean FSOUND_Record_StartSample(FSOUND_SAMPLE sptr, boolean loop)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Record_StartSample(pointer, Pointer.getPointer(sptr), loop);
	}

	/**
	 * Halts recording to the specified sample.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_Record_StartSample(FSOUND_SAMPLE, boolean)
	 */
	public boolean FSOUND_Record_Stop()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Record_Stop(pointer);
	}

	/**
	 * Gets the position in the sample buffer that has been recorded to.<br>
	 * <br><b>Remarks :</b><br>
	 * Note. This is not the 'recording cursor', but rather the latest point that the input has been copied to your sample<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh
	 * @return On success, the offset in SAMPLES, for the record buffer that the input device has just written up to is returned.<br>
	 * On failure (recording device hasnt been started), -1 is returned.
	 * @see FMOD_INSTANCE#FSOUND_Record_StartSample(FSOUND_SAMPLE, boolean)
	 */
	public int FSOUND_Record_GetPosition()
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FSOUND_Record_GetPosition(pointer);
	}

	/**
	 * To load a module or bank with a given filename. FMUSIC Supports loading of <br>
	 * - .MOD (protracker/fasttracker modules)<br>
	 * - .S3M (screamtracker 3 modules)<br>
	 * - .XM (fasttracker 2 modules)<br>
	 * - .IT (impulse tracker modules)<br>
	 * - .MID (MIDI files)<br>
	 * - .RMI (MIDI files)<br>
	 * - .SGT (DirectMusic segment files)<br>
	 * - .FSB (FMOD Sample Bank files)<br>
	 * <br><b>Remarks :</b><br>
	 * This function autodetects the format type, regardless of filename.<br>
	 * The MIDI loader does not support user file callbacks. For WAD type data structures with embedded MIDI files FMUSIC_LoadSongEx will have to be used with memory loading.<br>
	 * Various other functionality is not provided in MIDI. See relevant FMUSIC functions to see if a feature
	 * is supported or not.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param name Filename of module to load.
	 * @return On success, a pointer to a FMUSIC_MODULE handle is returned.<br>
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FMUSIC_FreeSong(FMUSIC_MODULE)
	 * @see FMOD_INSTANCE#FMUSIC_LoadSong(String)
	 * @see FMOD_INSTANCE#FMUSIC_LoadSongEx(String, int, int, int, IntBuffer, int)
	 * @see FMOD_INSTANCE#FSOUND_File_SetCallbacks(FSOUND_OPENCALLBACK, FSOUND_CLOSECALLBACK, FSOUND_READCALLBACK, FSOUND_SEEKCALLBACK, FSOUND_TELLCALLBACK)
	 */
	public FMUSIC_MODULE FMUSIC_LoadSong(String name)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FMUSIC_LoadSong(pointer, name);
		return (cPtr == 0) ? null : FMUSIC_MODULE.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * To load a module or bank with a given filename. FMUSIC Supports loading of <br>
	 * - .MOD (protracker/fasttracker modules)<br>
	 * - .S3M (screamtracker 3 modules)<br>
	 * - .XM (fasttracker 2 modules)<br>
	 * - .IT (impulse tracker modules)<br>
	 * - .MID (MIDI files)<br>
	 * - .RMI (MIDI files)<br>
	 * - .SGT (DirectMusic segment files)<br>
	 * - .FSB (FMOD Sample Bank files)<br>
	 * <br><b>Remarks :</b><br>
	 * Loading a song from a memory handle is dangerous in one respect, if the data is corrupted or truncated, then FMUSIC could crash
	 * internally trying to load it.<br>
	 * On PlayStation 2 the data and length pointer must be 16 byte aligned for DMA purposes.<br>
	 * The samplelist and samplelistnum parameters are useful for limiting the amount of data fmod loads. This feature is for the FSB format only.
	 * It is especially useful if you have a bank of sounds and want to randomize the loading a bit by telling which sounds to load with random values,
	 * and consequently which not to load.<br>
	 * On PlayStation 2, samplelistnum has a limit of 1536 entries.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation2, GameCube
	 * @param name Name of song to load.<br>
	 * On PlayStation 2 data must be 16 byte aligned if loading from memory.
	 * @param offset Optional. 0 by default. If > 0, this value is used to specify
	 * an offset in a file, so fmod will seek before opening.
	 * @param length Optional. 0 by default. If > 0, this value is used to specify the
	 * length of a memory block when using FSOUND_LOADMEMORY,
	 * or it is the length of a file or file segment if the offset parameter is used.<br>
	 * On PlayStation 2 this must be 16 byte aligned for memory loading.
	 * @param mode Mode for opening song. With module files, only FSOUND_LOADMEMORY,
	 * FSOUND_NONBLOCKING, FSOUND_LOOP_NORMAL, or FSOUND_LOOP_OFF are supported.<br>
	 * For FSB files, FSOUND_2D, FSOUND_HW3D, FSOUND_FORCEMONO also work.
	 * @param samplelist Optional. Pointer to array of sample indicies to load.
	 * Leave as NULL if you want all samples to be loaded (default behaviour).<br>
	 * See Remarks for more on this.
	 * @param samplelistnum Optional. Number of entries in the samplelist array.
	 * @return On success, a FMUSIC_MODULE handle is returned.<br>
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FMUSIC_FreeSong(FMUSIC_MODULE)
	 * @see FMOD_INSTANCE#FMUSIC_LoadSong(String)
	 */
	public FMUSIC_MODULE FMUSIC_LoadSongEx(String name,
		int offset, int length, int mode,
		int[] samplelist, int samplelistnum)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FMUSIC_LoadSongEx(
				pointer, name, offset, length, mode, samplelist, samplelistnum);
		return (cPtr == 0) ? null : FMUSIC_MODULE.createView(Pointer.newPointer(cPtr));
	}
	/**
	 * To load a module or bank with a given filename. FMUSIC Supports loading of <br>
	 * - .MOD (protracker/fasttracker modules)<br>
	 * - .S3M (screamtracker 3 modules)<br>
	 * - .XM (fasttracker 2 modules)<br>
	 * - .IT (impulse tracker modules)<br>
	 * - .MID (MIDI files)<br>
	 * - .RMI (MIDI files)<br>
	 * - .SGT (DirectMusic segment files)<br>
	 * - .FSB (FMOD Sample Bank files)<br>
	 * <br><b>Remarks :</b><br>
	 * Loading a song from a memory handle is dangerous in one respect, if the data is corrupted or truncated, then FMUSIC could crash
	 * internally trying to load it.<br>
	 * On PlayStation 2 the data and length pointer must be 16 byte aligned for DMA purposes.<br>
	 * The samplelist and samplelistnum parameters are useful for limiting the amount of data fmod loads. This feature is for the FSB format only.
	 * It is especially useful if you have a bank of sounds and want to randomize the loading a bit by telling which sounds to load with random values,
	 * and consequently which not to load.<br>
	 * On PlayStation 2, samplelistnum has a limit of 1536 entries.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation2, GameCube
	 * @param data Pointer to data containing song to load
	 * (if loading from memory and not file).<br>
	 * On PlayStation 2 data must be 16 byte aligned if loading from memory.
	 * @param offset Optional. 0 by default. If > 0, this value is used to specify
	 * an offset in a file, so fmod will seek before opening.
	 * @param length Optional. 0 by default. If > 0, this value is used to specify the
	 * length of a memory block when using FSOUND_LOADMEMORY,
	 * or it is the length of a file or file segment if the offset parameter is used.<br>
	 * On PlayStation 2 this must be 16 byte aligned for memory loading.
	 * @param mode Mode for opening song. With module files, only FSOUND_LOADMEMORY,
	 * FSOUND_NONBLOCKING, FSOUND_LOOP_NORMAL, or FSOUND_LOOP_OFF are supported.<br>
	 * For FSB files, FSOUND_2D, FSOUND_HW3D, FSOUND_FORCEMONO also work.
	 * @param samplelist Optional. Pointer to array of sample indicies to load.
	 * Leave as NULL if you want all samples to be loaded (default behaviour).<br>
	 * See Remarks for more on this.
	 * @param samplelistnum Optional. Number of entries in the samplelist array.
	 * @return On success, a FMUSIC_MODULE handle is returned.<br>
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FMUSIC_FreeSong(FMUSIC_MODULE)
	 * @see FMOD_INSTANCE#FMUSIC_LoadSong(String)
	 */
	public FMUSIC_MODULE FMUSIC_LoadSongEx(ByteBuffer data,
		int offset, int length, int mode,
		int[] samplelist, int samplelistnum)
	{
		if(pointer == 0) throw new NullPointerException();
		if(data != null && !data.isDirect()) {
			throw new NonDirectBufferException();
		}
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FMUSIC_LoadSongEx(pointer,
				data, BufferUtils.getPositionInBytes(data),
				offset, length, mode, samplelist, samplelistnum);
		return (cPtr == 0) ? null : FMUSIC_MODULE.createView(Pointer.newPointer(cPtr));
	}
	/**
	 * To load a module or bank with a given filename. FMUSIC Supports loading of <br>
	 * - .MOD (protracker/fasttracker modules)<br>
	 * - .S3M (screamtracker 3 modules)<br>
	 * - .XM (fasttracker 2 modules)<br>
	 * - .IT (impulse tracker modules)<br>
	 * - .MID (MIDI files)<br>
	 * - .RMI (MIDI files)<br>
	 * - .SGT (DirectMusic segment files)<br>
	 * - .FSB (FMOD Sample Bank files)<br>
	 * <br><b>Remarks :</b><br>
	 * Loading a song from a memory handle is dangerous in one respect, if the data is corrupted or truncated, then FMUSIC could crash
	 * internally trying to load it.<br>
	 * On PlayStation 2 the data and length pointer must be 16 byte aligned for DMA purposes.<br>
	 * The samplelist and samplelistnum parameters are useful for limiting the amount of data fmod loads. This feature is for the FSB format only.
	 * It is especially useful if you have a bank of sounds and want to randomize the loading a bit by telling which sounds to load with random values,
	 * and consequently which not to load.<br>
	 * On PlayStation 2, samplelistnum has a limit of 1536 entries.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation2, GameCube
	 * @param name Name of song to load.<br>
	 * On PlayStation 2 data must be 16 byte aligned if loading from memory.
	 * @param offset Optional. 0 by default. If > 0, this value is used to specify
	 * an offset in a file, so fmod will seek before opening.
	 * @param length Optional. 0 by default. If > 0, this value is used to specify the
	 * length of a memory block when using FSOUND_LOADMEMORY,
	 * or it is the length of a file or file segment if the offset parameter is used.<br>
	 * On PlayStation 2 this must be 16 byte aligned for memory loading.
	 * @param mode Mode for opening song. With module files, only FSOUND_LOADMEMORY,
	 * FSOUND_NONBLOCKING, FSOUND_LOOP_NORMAL, or FSOUND_LOOP_OFF are supported.<br>
	 * For FSB files, FSOUND_2D, FSOUND_HW3D, FSOUND_FORCEMONO also work.
	 * @param samplelist Optional. Pointer to array of sample indicies to load.
	 * Leave as NULL if you want all samples to be loaded (default behaviour).<br>
	 * See Remarks for more on this.
	 * @param samplelistnum Optional. Number of entries in the samplelist array.
	 * @return On success, a FMUSIC_MODULE handle is returned.<br>
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FMUSIC_FreeSong(FMUSIC_MODULE)
	 * @see FMOD_INSTANCE#FMUSIC_LoadSong(String)
	 */
	public FMUSIC_MODULE FMUSIC_LoadSongEx(String name,
		int offset, int length, int mode,
		IntBuffer samplelist, int samplelistnum)
	{
		if(pointer == 0) throw new NullPointerException();
		if(samplelist != null && !samplelist.isDirect()) {
			throw new NonDirectBufferException();
		}
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FMUSIC_LoadSongEx(pointer,
				name, offset, length, mode, samplelist, BufferUtils.getPositionInBytes(samplelist),
				samplelistnum);
		return (cPtr == 0) ? null : FMUSIC_MODULE.createView(Pointer.newPointer(cPtr));
	}
	/**
	 * To load a module or bank with a given filename. FMUSIC Supports loading of <br>
	 * - .MOD (protracker/fasttracker modules)<br>
	 * - .S3M (screamtracker 3 modules)<br>
	 * - .XM (fasttracker 2 modules)<br>
	 * - .IT (impulse tracker modules)<br>
	 * - .MID (MIDI files)<br>
	 * - .RMI (MIDI files)<br>
	 * - .SGT (DirectMusic segment files)<br>
	 * - .FSB (FMOD Sample Bank files)<br>
	 * <br><b>Remarks :</b><br>
	 * Loading a song from a memory handle is dangerous in one respect, if the data is corrupted or truncated, then FMUSIC could crash
	 * internally trying to load it.<br>
	 * On PlayStation 2 the data and length pointer must be 16 byte aligned for DMA purposes.<br>
	 * The samplelist and samplelistnum parameters are useful for limiting the amount of data fmod loads. This feature is for the FSB format only.
	 * It is especially useful if you have a bank of sounds and want to randomize the loading a bit by telling which sounds to load with random values,
	 * and consequently which not to load.<br>
	 * On PlayStation 2, samplelistnum has a limit of 1536 entries.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation2, GameCube
	 * @param data Pointer to data containing song to load
	 * (if loading from memory and not file).<br>
	 * On PlayStation 2 data must be 16 byte aligned if loading from memory.
	 * @param offset Optional. 0 by default. If > 0, this value is used to specify
	 * an offset in a file, so fmod will seek before opening.
	 * @param length Optional. 0 by default. If > 0, this value is used to specify the
	 * length of a memory block when using FSOUND_LOADMEMORY,
	 * or it is the length of a file or file segment if the offset parameter is used.<br>
	 * On PlayStation 2 this must be 16 byte aligned for memory loading.
	 * @param mode Mode for opening song. With module files, only FSOUND_LOADMEMORY,
	 * FSOUND_NONBLOCKING, FSOUND_LOOP_NORMAL, or FSOUND_LOOP_OFF are supported.<br>
	 * For FSB files, FSOUND_2D, FSOUND_HW3D, FSOUND_FORCEMONO also work.
	 * @param samplelist Optional. Pointer to array of sample indicies to load.
	 * Leave as NULL if you want all samples to be loaded (default behaviour).<br>
	 * See Remarks for more on this.
	 * @param samplelistnum Optional. Number of entries in the samplelist array.
	 * @return On success, a FMUSIC_MODULE handle is returned.<br>
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FMUSIC_FreeSong(FMUSIC_MODULE)
	 * @see FMOD_INSTANCE#FMUSIC_LoadSong(String)
	 */
	public FMUSIC_MODULE FMUSIC_LoadSongEx(ByteBuffer data,
		int offset, int length, int mode,
		IntBuffer samplelist, int samplelistnum)
	{
		if(pointer == 0) throw new NullPointerException();
		if(samplelist != null && !samplelist.isDirect()) {
			throw new NonDirectBufferException();
		}
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FMUSIC_LoadSongEx(pointer,
					data, BufferUtils.getPositionInBytes(data),
					offset, length, mode, samplelist,
					BufferUtils.getPositionInBytes(samplelist), samplelistnum);
		return (cPtr == 0) ? null : FMUSIC_MODULE.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * If a mod is opened with FSOUND_NONBLOCKING, this function returns the state of the opening mod.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the mod to get the open state from.
	 * @return 0 = mod is opened and ready.<br>
	 * -1 = mod handle passed in is invalid.<br>
	 * -2 = mod is still opening<br>
	 * -3 = mod failed to open. (file not found, out of memory or other error).
	 */
	public int FMUSIC_GetOpenState(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetOpenState(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Frees memory allocated for a song and removes it from the FMUSIC system.
	 * @param mod Pointer to the song to be freed.
	 * @return true on success
	 * @see FMOD_INSTANCE#FMUSIC_LoadSong(String)
	 * @see FMOD_INSTANCE#FMUSIC_LoadSongEx(String, int, int, int, IntBuffer, int)
	 */
	public boolean FMUSIC_FreeSong(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_FreeSong(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Starts a song playing.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to be played.
	 * @return TRUE song succeeded playing<br>
	 * FALSE song failed playing
	 * @see FMOD_INSTANCE#FMUSIC_IsPlaying(FMUSIC_MODULE)
	 * @see FMOD_INSTANCE#FMUSIC_SetLooping(FMUSIC_MODULE, boolean)
	 * @see FMOD_INSTANCE#FMUSIC_StopAllSongs()
	 * @see FMOD_INSTANCE#FMUSIC_StopSong(FMUSIC_MODULE)
	 */
	public boolean FMUSIC_PlaySong(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_PlaySong(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Stops a song from playing.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to be stopped.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FMUSIC_PlaySong(FMUSIC_MODULE)
	 * @see FMOD_INSTANCE#FMUSIC_StopAllSongs()
	 */
	public boolean FMUSIC_StopSong(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_StopSong(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Stops all songs from playing. This is useful if you have multiple songs playing at once and
	 * want a quick way to stop them<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @see FMOD_INSTANCE#FMUSIC_PlaySong(FMUSIC_MODULE)
	 * @see FMOD_INSTANCE#FMUSIC_StopSong(FMUSIC_MODULE)
	 */
	public void FMUSIC_StopAllSongs()
	{
		if(pointer == 0) throw new NullPointerException();
		FmodDynJNI.FMOD_INSTANCE_FMUSIC_StopAllSongs(pointer);
	}

	/**
	 * Sets a user callback for any Zxx commands encountered in an S3M, XM or IT file.<br>
	 * <br><b>Remarks :</b><br>
	 * The value passed into the param parameter of the callback is the xx value specified in the Zxx
	 * command by the musician.<br>
	 * ------------<br>
	 * It is important to note that this callback will be called from directly WITHIN the
	 * mixer / music update thread, therefore it is imperative that whatever you do from this
	 * callback be extremely efficient. If the routine takes too long then breakups in the sound
	 * will occur, or it will basically stop mixing until you return from the function.<br>
	 * This sort of function is usually best for just setting a flag, or do some simple variable
	 * manipulation, and then exiting, letting your main thread do what it needs to based on these
	 * flags or variables.<br>
	 * ------------<br>
	 * This callback is LATENCY adjusted by default, so that the callback happens when you HEAR the sound, not when it is mixed, for accurate synchronization.<br>
	 * Use FSOUND_INIT_DONTLATENCYADJUST if you want it to be called back at mix time, which is useful if you want to control the music interactively.<br>
	 * ------------<br>
	 * Note : This function is not supported with the MIDI format.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod The module or song to set the callback for.
	 * @param callback The callback function you supply to get called upon execution of a Zxx command.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMUSIC_CALLBACK
	 * @see FMOD_INSTANCE#FMUSIC_SetInstCallback(FMUSIC_MODULE, FMUSIC_CALLBACK, int)
	 * @see FMOD_INSTANCE#FMUSIC_SetOrderCallback(FMUSIC_MODULE, FMUSIC_CALLBACK, int)
	 * @see FMOD_INSTANCE#FMUSIC_SetRowCallback(FMUSIC_MODULE, FMUSIC_CALLBACK, int)
	 */
	public boolean FMUSIC_SetZxxCallback(FMUSIC_MODULE mod, FMUSIC_CALLBACK callback)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_SetZxxCallback(
			pointer,
			Pointer.getPointer(mod),
			callback);
	}

	/**
	 * Sets a user callback to occur on every row divisible by the rowstep parameter, played from a MOD, S3M, XM or IT file.<br>
	 * <br><b>Remarks :</b><br>
	 * It is important to note that this callback will be called from directly WITHIN the
	 * mixer / music update thread, therefore it is imperative that whatever you do from this
	 * callback be extremely efficient. If the routine takes too long then breakups in the sound
	 * will occur, or it will basically stop mixing until you return from the function.<br>
	 * This sort of function is usually best for just setting a flag, or do some simple variable
	 * manipulation, and then exiting, letting your main thread do what it needs to based on these
	 * flags or variables.<br>
	 * ------------<br>
	 * This callback is LATENCY adjusted, so that the callback happens when you HEAR the sound, not when it is mixed, for accurate synchronization.<br>
	 * Use FSOUND_INIT_DONTLATENCYADJUST if you want it to be called back at mix time, which is useful if you want to control the music interactively.<br>
	 * ------------<br>
	 * Note : This function is not supported with the MIDI format.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod The module or song to set the callback for.
	 * @param callback The callback function you supply to get called.
	 * @param rowstep Call the callback every multiple of this number of rows.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMUSIC_CALLBACK
	 * @see FMOD_INSTANCE#FMUSIC_SetInstCallback(FMUSIC_MODULE, FMUSIC_CALLBACK, int)
	 * @see FMOD_INSTANCE#FMUSIC_SetOrderCallback(FMUSIC_MODULE, FMUSIC_CALLBACK, int)
	 * @see FMOD_INSTANCE#FMUSIC_SetZxxCallback(FMUSIC_MODULE, FMUSIC_CALLBACK)
	 */
	public boolean FMUSIC_SetRowCallback(FMUSIC_MODULE mod, FMUSIC_CALLBACK callback, int rowstep)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_SetRowCallback(
			pointer,
			Pointer.getPointer(mod),
			callback,
			rowstep);
	}

	/**
	 * Sets a user callback to occur on every order divisible by the orderstep parameter, played from a MOD, S3M, XM or IT file.<br>
	 * <br><b>Remarks :</b><br>
	 * It is important to note that this callback will be called from directly WITHIN the
	 * mixer / music update thread, therefore it is imperative that whatever you do from this
	 * callback be extremely efficient. If the routine takes too long then breakups in the sound
	 * will occur, or it will basically stop mixing until you return from the function.<br>
	 * This sort of function is usually best for just setting a flag, or do some simple variable
	 * manipulation, and then exiting, letting your main thread do what it needs to based on these
	 * flags or variables.<br>
	 * ------------<br>
	 * This callback is LATENCY adjusted, so that the callback happens when you HEAR the sound, not when it is mixed, for accurate synchronization.<br>
	 * Use FSOUND_INIT_DONTLATENCYADJUST if you want it to be called back at mix time, which is useful if you want to control the music interactively.<br>
	 * ------------<br>
	 * Note : This function is not supported with the MIDI format.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod  The module or song to set the callback for.
	 * @param callback The callback function you supply to get called.
	 * @param orderstep Call the callback every multiple of this number of orders.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMUSIC_CALLBACK
	 * @see FMOD_INSTANCE#FMUSIC_SetInstCallback(FMUSIC_MODULE, FMUSIC_CALLBACK, int)
	 * @see FMOD_INSTANCE#FMUSIC_SetRowCallback(FMUSIC_MODULE, FMUSIC_CALLBACK, int)
	 * @see FMOD_INSTANCE#FMUSIC_SetZxxCallback(FMUSIC_MODULE, FMUSIC_CALLBACK)
	 */
	public boolean FMUSIC_SetOrderCallback(FMUSIC_MODULE mod, FMUSIC_CALLBACK callback, int orderstep)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_SetOrderCallback(
			pointer,
			Pointer.getPointer(mod),
			callback,
			orderstep);
	}

	/**
	 * Sets a user callback to occur every time a instrument is played, triggered from a MOD, S3M, XM or IT file.<br>
	 * <br><b>Remarks :</b><br>
	 * It is important to note that this callback will be called from directly WITHIN the
	 * mixer / music update thread, therefore it is imperative that whatever you do from this
	 * callback be extremely efficient. If the routine takes too long then breakups in the sound
	 * will occur, or it will basically stop mixing until you return from the function.<br>
	 * This sort of function is usually best for just setting a flag, or do some simple variable
	 * manipulation, and then exiting, letting your main thread do what it needs to based on these
	 * flags or variables.<br>
	 * ------------<br>
	 * This callback is LATENCY adjusted, so that the callback happens when you HEAR the sound, not when it is mixed, for accurate synchronization.<br>
	 * Use FSOUND_INIT_DONTLATENCYADJUST if you want it to be called back at mix time, which is useful if you want to control the music interactively.<br>
	 * ------------<br>
	 * Note : This function is not supported with the MIDI format.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod The module or song to set the callback for.
	 * @param callback The callback function you supply to get called.
	 * @param instrument Call the callback when this instrument number is triggered.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMUSIC_CALLBACK
	 * @see FMOD_INSTANCE#FMUSIC_SetOrderCallback(FMUSIC_MODULE, FMUSIC_CALLBACK, int)
	 * @see FMOD_INSTANCE#FMUSIC_SetRowCallback(FMUSIC_MODULE, FMUSIC_CALLBACK, int)
	 * @see FMOD_INSTANCE#FMUSIC_SetZxxCallback(FMUSIC_MODULE, FMUSIC_CALLBACK)
	 */
	public boolean FMUSIC_SetInstCallback(FMUSIC_MODULE mod, FMUSIC_CALLBACK callback, int instrument)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_SetInstCallback(
			pointer,
			Pointer.getPointer(mod),
			callback,
			instrument);
	}

	/**
	 * Replaces a mod's sample with a sample definition specified.<br>
	 * <br><b>Remarks :</b><br>
	 * Because of the instrument nature of some formats like XM, this function lists all the samples in order of instruments and their subsamples.<br>
	 * ie if instrument 1 has 2 samples and instrument 2 contains 3 samples, then sampno in this case would be 0 and 1 for instrument 1's samples,
	 * and 2,3 & 4 for instrument 2's samples. <br>
	 * ------------<br>
	 * FMOD does not free the existing mod sample that you may be overwriting. If you do overwrite an existing handle,
	 * it may be lost, and you may incur a memory leak. It is a good idea to free the existing sample first before overwriting it.<br>
	 * ------------<br>
	 * Important: For PlayStation 2, this function has to do a blocking query to the IOP, and can take significantly more time
	 * than a standard non blocking fmod function. This means it is best to cache the pointers for samples while loading,
	 * and not call this function in realtime.<br>
	 * ------------<br>
	 * This function is not supported with the MIDI format.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to set the sample for.
	 * @param sampno index to sample inside module.
	 * @param sptr  Pointer to the sample definition to replace mod sample
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FMUSIC_GetSample(FMUSIC_MODULE, int)
	 */
	public boolean FMUSIC_SetSample(FMUSIC_MODULE mod, int sampno, FSOUND_SAMPLE sptr)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_SetSample(
			pointer,
			Pointer.getPointer(mod),
			sampno,
			Pointer.getPointer(sptr));
	}

	/**
	 * Sets a user defined value to store with the music file to be retrieved later.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song.
	 * @param userdata Value to store with music object.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FMUSIC_GetUserData(FMUSIC_MODULE)
	 */
	public boolean FMUSIC_SetUserData(FMUSIC_MODULE mod, Pointer userdata)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_SetUserData(
			pointer,
			Pointer.getPointer(mod),
			Pointer.getPointer(userdata));
	}

	/**
	 * This function helps with channel usage. If you are desperate for channels, and you are prepared to
	 * let the music routines drop a few channels, then calling this function can help.<br>
	 * It basically doesnt try to play any new sounds if a certain channel limit is being played (including sound effects),
	 * and the new sound is below a certain specified volume.<br>
	 * ie.<br>
	 * You set it to maxchannels = 16, and minvolume = 0x10. <br>
	 * In this case, the mod will play normally as long as the total number of channels being played inclusing sound effefcts is below 16
	 * (see FSOUND_GetChannelsPlaying).<br>
	 * If the number of channels playing exceeds 16 (through a change in the music, or extra sound effects
	 * are spawned, then sounds with a musician specified volume of less than 0x10 will be ignored.<br>
	 * The volume is based on volume column/default volume/volume set commands in the mod. master volume,
	 * envelope volumes etc are not taken into account (this gives more control over how it will work from the
	 * tracker).<br>
	 * <br><b>Remarks :</b><br>
	 * maxchannels will default to the number of channels allocated by FSOUND, so this will never happen
	 * by default.<br>
	 * minvolume will default to 0, so it will always succeed by default.<br>
	 * To see how many channels are currently being MIXED, use FSOUND_GetChannelsPlaying.<br>
	 * As a musician mentioned to me once, most of his default volumes are set fairly high, and any low end
	 * volumes are usually echoes etc, and can afford to be dropped.<br>
	 * ------------<br>
	 * Note : This function is not supported with the MIDI format.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to set channel/volume optimization settings.
	 * @param maxchannels Channel count to be mixed before fmusic starts to drop channels from the song.
	 * @param minvolume If maxchannels is exceeded, then music channels with volumes below this value will not be played.
	 * Range is 0-64. This is the value the tracker displays. All trackers use 0-64.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_GetChannelsPlaying()
	 */
	public boolean FMUSIC_OptimizeChannels(FMUSIC_MODULE mod, int maxchannels, int minvolume)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_OptimizeChannels(
			pointer,
			Pointer.getPointer(mod),
			maxchannels,
			minvolume);
	}

	/**
	 * Turns on reverb for MIDI/RMI files.<br>
	 * <br><b>Remarks :</b><br>
	 * Reverb may be enabled through software emulation in the future for MOD based formats.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32
	 * @param reverb  Set to TRUE to turn MIDI reverb on, FALSE to turn MIDI reverb off.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 */
	public boolean FMUSIC_SetReverb(boolean reverb)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_SetReverb(pointer, reverb);
	}

	/**
	 * Sets looping mode for midi and mod files.<br>
	 * <br><b>Remarks :</b><br>
	 * Defaults to TRUE. To disable looping you must call this function using FALSE as the parameter.<br>
	 * For midi files this only takes effect before FMUSIC_PlaySong is called. For mod files this
	 * can be called at any time including during playback.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song.
	 * @param looping  Set to TRUE to make it loop forever, or FALSE to only have it play once.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FMUSIC_PlaySong(FMUSIC_MODULE)
	 */
	public boolean FMUSIC_SetLooping(FMUSIC_MODULE mod, boolean looping)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_SetLooping(pointer, Pointer.getPointer(mod), looping);
	}

	/**
	 * Sets a songs order position / current playing position.<br>
	 * <br><b>Remarks :</b><br>
	 * Note : This function is not supported with the MIDI format.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to have its order changed.
	 * @param order Order number to jump to.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FMUSIC_GetOrder(FMUSIC_MODULE)
	 */
	public boolean FMUSIC_SetOrder(FMUSIC_MODULE mod, int order)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_SetOrder(pointer, Pointer.getPointer(mod), order);
	}

	/**
	 * Pauses a song<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to be paused/unpaused.
	 * @param pause TRUE - song should be PAUSED, FALSE - song should be UNPAUSED
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FMUSIC_GetPaused(FMUSIC_MODULE)
	 */
	public boolean FMUSIC_SetPaused(FMUSIC_MODULE mod, boolean pause)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_SetPaused(pointer, Pointer.getPointer(mod), pause);
	}

	/**
	 * Sets a songs master volume.<br>
	 * <br><b>Remarks :</b><br>
	 * Note if the hardware midi synth is selected, you cannot set the master volume. <br>
	 * This command will only work reliably if FSOUND_INIT_USEDEFAULTMIDISYNTH is selected.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to have its master volume set.
	 * @param volume value from 0-256. 0 = silence, 256 = full volume.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FMUSIC_GetGlobalVolume(FMUSIC_MODULE)
	 * @see FMOD_INSTANCE#FMUSIC_GetMasterVolume(FMUSIC_MODULE)
	 * @see FMOD_INSTANCE#FSOUND_GetSFXMasterVolume()
	 * @see FMOD_INSTANCE#FSOUND_SetSFXMasterVolume(int)
	 */
	public boolean FMUSIC_SetMasterVolume(FMUSIC_MODULE mod, int volume)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_SetMasterVolume(pointer, Pointer.getPointer(mod), volume);
	}

	/**
	 * Sets a songs master speed scale, so that the song can be sped up or slowed down.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to have its speed scale set.
	 * @param speed Speed scale for song. 1.0 is default. Minimum is 0 (stopped), maximum is 10.0.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 */
	public boolean FMUSIC_SetMasterSpeed(FMUSIC_MODULE mod, float speed)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_SetMasterSpeed(pointer, Pointer.getPointer(mod), speed);
	}

	/**
	 * Sets the master pan seperation for a module.<br>
	 * <br><b>Remarks :</b><br>
	 * This is set to 1.0 by default.<br>
	 * ------------<br>
	 * Note : This function is not supported with the MIDI format.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod
	 * @param pansep The pan scale. 1.0 means full pan seperation, 0 means mono.
	 * @return On success, TRUE is returned.<br>
	 * On failure, FALSE is returned.
	 * @see FMOD_INSTANCE#FSOUND_SetPanSeperation(float)
	 */
	public boolean FMUSIC_SetPanSeperation(FMUSIC_MODULE mod, float pansep)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_SetPanSeperation(pointer, Pointer.getPointer(mod), pansep);
	}

	/**
	 * Returns the name of the song set by the composer. With MIDI format, the filename is returned.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve name from.
 	 * @return On success, the name of the song as a NULL terminated string is returned.
 	 * On failure, NULL is returned.<br>
	 */
	public String FMUSIC_GetName(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetName(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the format type a song.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve name from.
	 * @return On success, the following values are returned:<br>
	 * FMUSIC_TYPE_NONE<br>
	 * FMUSIC_TYPE_MOD<br>
	 * FMUSIC_TYPE_S3M<br>
	 * FMUSIC_TYPE_XM<br>
	 * FMUSIC_TYPE_IT<br>
	 * FMUSIC_TYPE_MIDI<br>
	 * On failure, FMUSIC_TYPE_NONE is returned.
	 * @see FMUSIC_TYPES
	 */
	public int FMUSIC_GetType(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetType(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the number of orders in this song.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve number of orders from.
	 * @return On success, the number of orders in this song is returned.<br>
	 * On failure, 0 is returned.
	 */
	public int FMUSIC_GetNumOrders(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetNumOrders(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the number of patterns contained in this song.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve number of patterns.
	 * @return On success, the number of patterns contained in this song is returned.<br>
	 * On failure, 0 is returned.
	 */
	public int FMUSIC_GetNumPatterns(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetNumPatterns(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the number of instruments contained in this song.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve number of instruments from.
	 * @return On success, the number of instruments contained in this song is returned.<br>
	 * On failure, 0 is returned.
	 */
	public int FMUSIC_GetNumInstruments(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetNumInstruments(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the number of samples contained in this song.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve number of samples.
	 * @return Number of samples contained in this song.<br>
	 * On failure, 0 is returned.
	 */
	public int FMUSIC_GetNumSamples(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetNumSamples(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the number of channels within this songs pattern data<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve number of channels from.
	 * @return Number of channels within this songs pattern data<br>
	 * On failure, 0 is returned.
	 */
	public int FMUSIC_GetNumChannels(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetNumChannels(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns a pointer to a sample inside a module. <br>
	 * Once you have access to the module's sample, you can do a lot of things
	 * to it, including locking and modifying the data within; using the
	 * FSOUND_Sample_ functionality.<br>
	 * <br><b>Remarks :</b><br>
	 * Because of the instrument nature of some formats like XM, this
	 * function lists all the samples in order of instruments and their subsamples.
	 * ie if instrument 1 has 2 samples and instrument 2 contains 3 samples, then
	 * sampno in this case would be 0 and 1 for instrument 1's samples, and 2,3 & 4
	 * for instrument 2's samples. <br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve a sample handle from
	 * @param sampno index to sample inside module.
	 * @return On success, a valid sample handle is returned.<br>
	 * On failure, NULL is returned.
	 * @see FMOD_INSTANCE#FMUSIC_SetSample(FMUSIC_MODULE, int, FSOUND_SAMPLE)
	 */
	public FSOUND_SAMPLE FMUSIC_GetSample(FMUSIC_MODULE mod, int sampno)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetSample(pointer, Pointer.getPointer(mod), sampno);
		return (cPtr == 0) ? null : FSOUND_SAMPLE.createView(Pointer.newPointer(cPtr));
	}

	/**
	 * Returns the the length in rows of the pattern for the specified order number.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to .
	 * @param orderno
	 * @return On success, the songs pattern length at the specified order is returned.<br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FMUSIC_GetOrder(FMUSIC_MODULE)
	 */
	public int FMUSIC_GetPatternLength(FMUSIC_MODULE mod, int orderno)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetPatternLength(pointer, Pointer.getPointer(mod), orderno);
	}

	/**
	 * Returns whether the song has completed playing, or when the last order has finished playing.
	 * This stays set even if the song loops.<br>
	 * <br><b>Remarks :</b><br>
	 * Some songs may use a pattern break or pattern jump and never reach the last pattern.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song that you want check if finished or not.
	 * @return TRUE Song has finished playing.<br>
	 * FALSE Song has not finished playing.
	 */
	public boolean FMUSIC_IsFinished(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_IsFinished(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns whether the song is currently playing or not<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve name from.
	 * @return TRUE Song is playing.<br>
	 * FALSE Song is stopped.
	 * @see FMOD_INSTANCE#FMUSIC_PlaySong(FMUSIC_MODULE)
	 */
	public boolean FMUSIC_IsPlaying(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_IsPlaying(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the song's current master volume<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve song master volume from.
	 * @return On success, the song's current master volume, from 0 (silence) to 256 (full volume) is returned<br>
	 * On failure, -1 is returned.
	 * @see FMOD_INSTANCE#FMUSIC_GetGlobalVolume(FMUSIC_MODULE)
	 * @see FMOD_INSTANCE#FMUSIC_SetMasterVolume(FMUSIC_MODULE, int)
	 */
	public int FMUSIC_GetMasterVolume(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetMasterVolume(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the song's current global volume<br>
	 * <br><b>Remarks :</b><br>
	 * GLOBAL volume is not the same as MASTER volume. GLOBAL volume is an internal
	 * overall volume which can be altered by the song itself (ie there might be commands
	 * to fade in a particular part of the song by scaling all the volumes in the song
	 * up slowly from nothing).<br>
	 * GLOBAL volume is different to MASTER volume in that the song can modify without
	 * your permission, whereas MASTER volume is an overall scalar that you can control.<br>
	 * For general use, MASTER volume is more useful, but you may want to reset a song's
	 * GLOBAL volume at certain times in the song. (for example the song might have faded
	 * out by using GLOBAL volume and you want to reset it)<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve song global volume from.
	 * @return Songs current global volume, from 0 (silence) to the maximum value
	 * determined by the music format. Global volume maximums are different in
	 * respect to each format, they range from 64 to 256.<br>
	 * On failure, -1 is returned.
	 * @see FMOD_INSTANCE#FMUSIC_GetMasterVolume(FMUSIC_MODULE)
	 * @see FMOD_INSTANCE#FMUSIC_SetMasterVolume(FMUSIC_MODULE, int)
	 */
	public int FMUSIC_GetGlobalVolume(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetGlobalVolume(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the song's current order number<br>
	 * <br><b>Remarks :</b><br>
	 * This value is latency adjusted by default, and returns the number you are hearing, not the 'mix-time' value.<br>
	 * Use FSOUND_INIT_DONTLATENCYADJUST if you want the value at mix time, which is useful if you want to control the music interactively, or from a DSP callback.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve current order number from.
	 * @return On success, the song's current order number is returned.<br>
	 * On failure, -1 is returned.
	 * @see FMOD_INSTANCE#FMUSIC_GetPattern(FMUSIC_MODULE)
	 * @see FMOD_INSTANCE#FMUSIC_GetPatternLength(FMUSIC_MODULE, int)
	 * @see FMOD_INSTANCE#FMUSIC_SetOrder(FMUSIC_MODULE, int)
	 */
	public int FMUSIC_GetOrder(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetOrder(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the song's current pattern number<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve current pattern number from.
	 * @return On success, The song's current pattern number is returned.<br>
	 * On failure, -1 is returned.
	 * @see FMOD_INSTANCE#FMUSIC_GetOrder(FMUSIC_MODULE)
	 */
	public int FMUSIC_GetPattern(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetPattern(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the song's current speed.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve current song speed from.
	 * @return On success, The song's current speed is returned<br>
	 * On failure, -1 is returned.
	 * @see FMOD_INSTANCE#FMUSIC_GetBPM(FMUSIC_MODULE)
	 */
	public int FMUSIC_GetSpeed(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetSpeed(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the song's current BPM.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve current song BPM from.
	 * @return Value: on success the song's current BPM is returned.<br>
	 * on failure, -1 is returned.
	 * @see FMOD_INSTANCE#FMUSIC_GetSpeed(FMUSIC_MODULE)
	 */
	public int FMUSIC_GetBPM(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetBPM(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the song's current row number.<br>
	 * <br><b>Remarks :</b><br>
	 * This value is latency adjusted by default, and returns the number you are hearing, not the 'mix-time' value.<br>
	 * Use FSOUND_INIT_DONTLATENCYADJUST if you want the value at mix time, which is useful if you want to control the music interactively, or from a DSP callback.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to retrieve current row from.
	 * @return On success, the song's current row number is returned.<br>
	 * On failure, -1 is returned.
	 */
	public int FMUSIC_GetRow(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetRow(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns whether song is currently paused or not.<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to get paused flag from.
	 * @return On success, TRUE is returned to say the song is currently paused<br>
	 * On failure, FALSE is returned to say the song is NOT currently paused
	 * @see FMOD_INSTANCE#FMUSIC_SetPaused(FMUSIC_MODULE, boolean)
	 */
	public boolean FMUSIC_GetPaused(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetPaused(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the time in milliseconds since the song was started. This is useful for
	 * synchronizing purposes becuase it will be exactly the same every time, and it is
	 * reliably retriggered upon starting the song. Trying to synchronize using other
	 * windows timers can lead to varying results, and inexact performance. This fixes that
	 * problem by actually using the number of samples sent to the soundcard as a reference.<br>
	 * <br><b>Remarks :</b><br>
	 * This value is latency adjusted by default, and returns the number you are hearing, not the 'mix-time' value.<br>
	 * Use FSOUND_INIT_DONTLATENCYADJUST if you want the value at mix time, which is useful if you want to control the music interactively, or from a DSP callback.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song to get time from.
	 * @return On success, the time played in milliseconds is returned.<br>
	 * On failure, -1 is returned.
	 */
	public int FMUSIC_GetTime(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetTime(pointer, Pointer.getPointer(mod));
	}

	/**
	 * Returns the real FSOUND channel playing based on the mod's FMUSIC channel.<br>
	 * <br><b>Remarks :</b><br>
	 * Note FMUSIC mod playback only allocates a real channel on a mod channel the first time an instrument is played.<br>
	 * NNA's will not register. This function only returns the primary real channel for the mod channel.<br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod Pointer to the song.
	 * @param modchannel The mod channel index, to query the real channel from.
	 * @return On success, the channel index for the respective mod channel is returned.<br>
	 * On failure, -1 is returned.
	 */
	public int FMUSIC_GetRealChannel(FMUSIC_MODULE mod, int modchannel)
	{
		if(pointer == 0) throw new NullPointerException();
		return FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetRealChannel(pointer, Pointer.getPointer(mod), modchannel);
	}

	/**
	 * Retrieves the data set by FMUSIC_SetUserData<br>
	 * <br><b>Remarks :</b><br>
	 * ___________________<br>
	 * Supported on the following platforms : Win32, WinCE, Linux, Macintosh, XBox, PlayStation 2, GameCube
	 * @param mod  Pointer to the song.
	 * @return On success, userdata set by FMUSIC_SetUserData is returned.<br>
	 * On failure, 0 is returned.
	 * @see FMOD_INSTANCE#FMUSIC_SetUserData(FMUSIC_MODULE, Pointer)
	 */
	//FIXME
	public Pointer FMUSIC_GetUserData(FMUSIC_MODULE mod)
	{
		if(pointer == 0) throw new NullPointerException();
		long cPtr = FmodDynJNI.FMOD_INSTANCE_FMUSIC_GetUserData(pointer, Pointer.getPointer(mod));
		return (cPtr == 0) ? null : Pointer.newPointer(cPtr);
	}
}