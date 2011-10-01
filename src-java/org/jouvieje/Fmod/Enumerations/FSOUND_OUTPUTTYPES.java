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
 * These output types are used with FSOUND_SetOutput, to choose which output driver to use.
 * FSOUND_OUTPUT_DSOUND will not support hardware 3d acceleration if the sound card driver
 * does not support DirectX 6 Voice Manager Extensions.
 * FSOUND_OUTPUT_WINMM is recommended for NT and CE.
 */
public interface FSOUND_OUTPUTTYPES
{
	/**NoSound driver, all calls to this succeed but do nothing.*/
	public final static int FSOUND_OUTPUT_NOSOUND = EnumerationJNI.get_FSOUND_OUTPUT_NOSOUND();
	/**Windows Multimedia driver.*/
	public final static int FSOUND_OUTPUT_WINMM = EnumerationJNI.get_FSOUND_OUTPUT_WINMM();
	/**DirectSound driver. You need this to get EAX2 or EAX3 support, or FX api support.*/
	public final static int FSOUND_OUTPUT_DSOUND = EnumerationJNI.get_FSOUND_OUTPUT_DSOUND();
	/**A3D driver.*/
	public final static int FSOUND_OUTPUT_A3D = EnumerationJNI.get_FSOUND_OUTPUT_A3D();
	/**Linux/Unix OSS (Open Sound System) driver, i.e. the kernel sound drivers.*/
	public final static int FSOUND_OUTPUT_OSS = EnumerationJNI.get_FSOUND_OUTPUT_OSS();
	/**Linux/Unix ESD (Enlightment Sound Daemon) driver.*/
	public final static int FSOUND_OUTPUT_ESD = EnumerationJNI.get_FSOUND_OUTPUT_ESD();
	/**Linux Alsa driver.*/
	public final static int FSOUND_OUTPUT_ALSA = EnumerationJNI.get_FSOUND_OUTPUT_ALSA();
	/**Low latency ASIO driver*/
	public final static int FSOUND_OUTPUT_ASIO = EnumerationJNI.get_FSOUND_OUTPUT_ASIO();
	/**Xbox driver*/
	public final static int FSOUND_OUTPUT_XBOX = EnumerationJNI.get_FSOUND_OUTPUT_XBOX();
	/**PlayStation 2 driver*/
	public final static int FSOUND_OUTPUT_PS2 = EnumerationJNI.get_FSOUND_OUTPUT_PS2();
	/**Mac SoundManager driver*/
	public final static int FSOUND_OUTPUT_MAC = EnumerationJNI.get_FSOUND_OUTPUT_MAC();
	/**Gamecube driver*/
	public final static int FSOUND_OUTPUT_GC = EnumerationJNI.get_FSOUND_OUTPUT_GC();
	/**PlayStation Portable driver*/
	public final static int FSOUND_OUTPUT_PSP = EnumerationJNI.get_FSOUND_OUTPUT_PSP();
	/**This is the same as nosound, but the sound generation is driven by FSOUND_Update*/
	public final static int FSOUND_OUTPUT_NOSOUND_NONREALTIME = EnumerationJNI.get_FSOUND_OUTPUT_NOSOUND_NONREALTIME();
}