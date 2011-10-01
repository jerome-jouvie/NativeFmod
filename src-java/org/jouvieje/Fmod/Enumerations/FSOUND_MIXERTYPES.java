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

package org.jouvieje.Fmod.Enumerations;

/**
 * These mixer types are used with FSOUND_SetMixer, to choose which mixer to use, or to act
 * upon for other reasons using FSOUND_GetMixer.
 * It is not nescessary to set the mixer. FMOD will autodetect the best mixer for you.
 */
public interface FSOUND_MIXERTYPES
{
	/**CE/PS2/GC Only - Non interpolating/low quality mixer.*/
	public final static int FSOUND_MIXER_AUTODETECT = EnumerationJNI.get_FSOUND_MIXER_AUTODETECT();
	/** All platforms - Autodetect the fastest quality mixer based on your cpu. */
	public final static int FSOUND_MIXER_QUALITY_AUTODETECT = EnumerationJNI.get_FSOUND_MIXER_QUALITY_AUTODETECT();
	/**Win32/Linux only - Interpolating/volume ramping FPU mixer.*/
	public final static int FSOUND_MIXER_QUALITY_FPU = EnumerationJNI.get_FSOUND_MIXER_QUALITY_FPU();
	/**Win32/Linux only - Interpolating/volume ramping P5 MMX mixer.*/
	public final static int FSOUND_MIXER_QUALITY_MMXP5 = EnumerationJNI.get_FSOUND_MIXER_QUALITY_MMXP5();
	/**Win32/Linux only - Interpolating/volume ramping ppro+ MMX mixer.*/
	public final static int FSOUND_MIXER_QUALITY_MMXP6 = EnumerationJNI.get_FSOUND_MIXER_QUALITY_MMXP6();
	/**CE/PS2/GC only - MONO non interpolating/low quality mixer. For speed*/
	public final static int FSOUND_MIXER_MONO = EnumerationJNI.get_FSOUND_MIXER_MONO();
	/**CE/PS2/GC only - MONO Interpolating mixer. For speed*/
	public final static int FSOUND_MIXER_QUALITY_MONO = EnumerationJNI.get_FSOUND_MIXER_QUALITY_MONO();
	public final static int FSOUND_MIXER_MAX = EnumerationJNI.get_FSOUND_MIXER_MAX();
}