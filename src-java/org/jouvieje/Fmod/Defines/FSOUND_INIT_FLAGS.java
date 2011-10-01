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

package org.jouvieje.Fmod.Defines;

/**
 * Initialization flags. Use them with FSOUND_Init in the flags parameter to change various behaviour.
 * FSOUND_INIT_ENABLESYSTEMCHANNELFX Is an init mode which enables the FSOUND mixer buffer to be affected by DirectX 8 effects.
 * Note that due to limitations of DirectSound, FSOUND_Init may fail if this is enabled because the buffersize is too small.
 * This can be fixed with FSOUND_SetBufferSize. Increase the BufferSize until it works.
 * When it is enabled you can use the FSOUND_FX api, and use FSOUND_SYSTEMCHANNEL as the channel id when setting parameters.
 */
public interface FSOUND_INIT_FLAGS
{
	/**Win32 only - Causes MIDI playback to force software decoding.*/
	public final static int FSOUND_INIT_USEDEFAULTMIDISYNTH = 0x0001;
	/**Win32 only - For DirectSound output - sound is not muted when window is out of focus.*/
	public final static int FSOUND_INIT_GLOBALFOCUS = 0x0002;
	/**Win32 only - For DirectSound output - Allows FSOUND_FX api to be used on global software mixer output! (use FSOUND_SYSTEMCHANNEL as channel id)*/
	public final static int FSOUND_INIT_ENABLESYSTEMCHANNELFX = 0x0004;
	/**This latency adjusts FSOUND_GetCurrentLevels, but incurs a small cpu and memory hit*/
	public final static int FSOUND_INIT_ACCURATEVULEVELS = 0x0008;
	/**PS2 only - Disable reverb on CORE 0 (SPU2 voices 00-23) to regain SRAM*/
	public final static int FSOUND_INIT_PS2_DISABLECORE0REVERB = 0x0010;
	/**PS2 only - Disable reverb on CORE 1 (SPU2 voices 24-47) to regain SRAM*/
	public final static int FSOUND_INIT_PS2_DISABLECORE1REVERB = 0x0020;
	/**PS2 only - By default FMOD uses DMA CH0 for mixing, CH1 for uploads, this flag swaps them around*/
	public final static int FSOUND_INIT_PS2_SWAPDMACORES = 0x0040;
	/**Callbacks are not latency adjusted, and are called at mix time. Also information functions are immediate*/
	public final static int FSOUND_INIT_DONTLATENCYADJUST = 0x0080;
	/**GC only - Initializes GC audio libraries*/
	public final static int FSOUND_INIT_GC_INITLIBS = 0x0100;
	/**Turns off fmod streamer thread, and makes streaming update from FSOUND_Update called by the user*/
	public final static int FSOUND_INIT_STREAM_FROM_MAIN_THREAD = 0x0200;
	/**PS2 only - Turns on volume ramping system to remove hardware clicks.*/
	public final static int FSOUND_INIT_PS2_USEVOLUMERAMPING = 0x0400;
	/**Win32 only - For DirectSound output. 3D commands are batched together and executed at FSOUND_Update.*/
	public final static int FSOUND_INIT_DSOUND_DEFERRED = 0x0800;
	/**Win32 only - For DirectSound output. FSOUND_HW3D buffers use a slightly higher quality algorithm when 3d hardware acceleration is not present.*/
	public final static int FSOUND_INIT_DSOUND_HRTF_LIGHT = 0x1000;
	/**Win32 only - For DirectSound output. FSOUND_HW3D buffers use full quality 3d playback when 3d hardware acceleration is not present.*/
	public final static int FSOUND_INIT_DSOUND_HRTF_FULL = 0x2000;
	/** XBox only - By default directsound attenuates all sound by 6db to avoid clipping/distortion.<BR>CAUTION.  If you use this flag you are responsible for the final mix to make sure clipping / distortion doesn't happen. */
	public final static int FSOUND_INIT_XBOX_REMOVEHEADROOM = 0x4000;
	/** PSP only - If streams skip / stutter when device is powered on, either increase stream buffersize, or use this flag instead to play silence while the UMD is recovering. */
	public final static int FSOUND_INIT_PSP_SILENCEONUNDERRUN = 0x8000;

}
