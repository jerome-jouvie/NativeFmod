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
 * Sample description bitfields, OR them together for loading and describing samples.
 * NOTE. If the file format being loaded already has a defined format, such as WAV or MP3, then
 * trying to override the pre-defined format with a new set of format flags will not work. For
 * example, an 8 bit WAV file will not load as 16bit if you specify FSOUND_16BITS. It will just
 * ignore the flag and go ahead loading it as 8bits. For these type of formats the only flags
 * you can specify that will really alter the behaviour of how it is loaded, are the following.
 * ---------
 * Looping behaviour - FSOUND_LOOP_OFF, FSOUND_LOOP_NORMAL, FSOUND_LOOP_BIDI
 * Load destination - FSOUND_HW3D, FSOUND_HW2D, FSOUND_2D
 * Loading behaviour - FSOUND_NONBLOCKING, FSOUND_LOADMEMORY, FSOUND_LOADRAW, FSOUND_MPEGACCURATE, FSOUND_MPEGHALFRATE, FSOUND_FORCEMONO
 * Playback behaviour - FSOUND_STREAMABLE, FSOUND_ENABLEFX
 * PlayStation 2 only - FSOUND_USECORE0, FSOUND_USECORE1, FSOUND_LOADMEMORYIOP
 * ---------
 * See flag descriptions for what these do.
 */
public interface FSOUND_MODES
{
	/**For non looping samples. */
	public final static int FSOUND_LOOP_OFF = 0x00000001;
	/**For forward looping samples. */
	public final static int FSOUND_LOOP_NORMAL = 0x00000002;
	/**For bidirectional looping samples. (no effect if in hardware). */
	public final static int FSOUND_LOOP_BIDI = 0x00000004;
	/**For 8 bit samples. */
	public final static int FSOUND_8BITS = 0x00000008;
	/**For 16 bit samples. */
	public final static int FSOUND_16BITS = 0x00000010;
	/**For mono samples. */
	public final static int FSOUND_MONO = 0x00000020;
	/**For stereo samples. */
	public final static int FSOUND_STEREO = 0x00000040;
	/**For user created source data containing unsigned samples. */
	public final static int FSOUND_UNSIGNED = 0x00000080;
	/**For user created source data containing signed data. */
	public final static int FSOUND_SIGNED = 0x00000100;
	/**For user created source data stored as delta values. */
	public final static int FSOUND_DELTA = 0x00000200;
	/**For user created source data stored using IT214 compression. */
	public final static int FSOUND_IT214 = 0x00000400;
	/**For user created source data stored using IT215 compression. */
	public final static int FSOUND_IT215 = 0x00000800;
	/**Attempts to make samples use 3d hardware acceleration. (if the card supports it) */
	public final static int FSOUND_HW3D = 0x00001000;
	/**Tells software (not hardware) based sample not to be included in 3d processing. */
	public final static int FSOUND_2D = 0x00002000;
	/**For a streamimg sound where you feed the data to it. */
	public final static int FSOUND_STREAMABLE = 0x00004000;
	/**"name" will be interpreted as a pointer to data for streaming and samples. */
	public final static int FSOUND_LOADMEMORY = 0x00008000;
	/**Will ignore file format and treat as raw pcm. */
	public final static int FSOUND_LOADRAW = 0x00010000;
	/**For FSOUND_Stream_Open - for accurate FSOUND_Stream_GetLengthMs/FSOUND_Stream_SetTime. WARNING, see FSOUND_Stream_Open for inital opening time performance issues. */
	public final static int FSOUND_MPEGACCURATE = 0x00020000;
	/**For forcing stereo streams and samples to be mono - needed if using FSOUND_HW3D and stereo data - incurs a small speed hit for streams */
	public final static int FSOUND_FORCEMONO = 0x00040000;
	/**2D hardware sounds. allows hardware specific effects */
	public final static int FSOUND_HW2D = 0x00080000;
	/**Allows DX8 FX to be played back on a sound. Requires DirectX 8 - Note these sounds cannot be played more than once, be 8 bit, be less than a certain size, or have a changing frequency */
	public final static int FSOUND_ENABLEFX = 0x00100000;
	/**For FMODCE only - decodes mpeg streams using a lower quality decode, but faster execution */
	public final static int FSOUND_MPEGHALFRATE = 0x00200000;
	/**Contents are stored compressed as IMA ADPCM */
	public final static int FSOUND_IMAADPCM = 0x00400000;
	/**For PS2 only - Contents are compressed as Sony VAG format */
	public final static int FSOUND_VAG = 0x00800000;
	/**For FSOUND_Stream_Open/FMUSIC_LoadSong - Causes stream or music to open in the background and not block the foreground app. See FSOUND_Stream_GetOpenState or FMUSIC_GetOpenState to determine when it IS ready. */
	public final static int FSOUND_NONBLOCKING = 0x01000000;
	/**For Gamecube only - Contents are compressed as Gamecube DSP-ADPCM format */
	public final static int FSOUND_GCADPCM = 0x02000000;
	/**For PS2 and Gamecube only - Contents are interleaved into a multi-channel (more than stereo) format */
	public final static int FSOUND_MULTICHANNEL = 0x04000000;
	/**For PS2 only - Sample/Stream is forced to use hardware voices 00-23 */
	public final static int FSOUND_USECORE0 = 0x08000000;
	/**For PS2 only - Sample/Stream is forced to use hardware voices 24-47 */
	public final static int FSOUND_USECORE1 = 0x10000000;
	/**For PS2 only - "name" will be interpreted as a pointer to data for streaming and samples. The address provided will be an IOP address */
	public final static int FSOUND_LOADMEMORYIOP = 0x20000000;
	/**Skips id3v2 etc tag checks when opening a stream, to reduce seek/read overhead when opening files (helps with CD performance) */
	public final static int FSOUND_IGNORETAGS = 0x40000000;
	/**Specifies an internet stream */
	public final static int FSOUND_STREAM_NET = 0x80000000;
	public final static int FSOUND_NORMAL = (FSOUND_16BITS | FSOUND_SIGNED | FSOUND_MONO);
}