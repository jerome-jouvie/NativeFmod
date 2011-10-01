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
 * These values are used with FSOUND_FX_Enable to enable DirectX 8 FX for a channel.
 */
public interface FSOUND_FX_MODES
{
	public final static int FSOUND_FX_CHORUS = EnumerationJNI.get_FSOUND_FX_CHORUS();
	public final static int FSOUND_FX_COMPRESSOR = EnumerationJNI.get_FSOUND_FX_COMPRESSOR();
	public final static int FSOUND_FX_DISTORTION = EnumerationJNI.get_FSOUND_FX_DISTORTION();
	public final static int FSOUND_FX_ECHO = EnumerationJNI.get_FSOUND_FX_ECHO();
	public final static int FSOUND_FX_FLANGER = EnumerationJNI.get_FSOUND_FX_FLANGER();
	public final static int FSOUND_FX_GARGLE = EnumerationJNI.get_FSOUND_FX_GARGLE();
	public final static int FSOUND_FX_I3DL2REVERB = EnumerationJNI.get_FSOUND_FX_I3DL2REVERB();
	public final static int FSOUND_FX_PARAMEQ = EnumerationJNI.get_FSOUND_FX_PARAMEQ();
	public final static int FSOUND_FX_WAVES_REVERB = EnumerationJNI.get_FSOUND_FX_WAVES_REVERB();
	public final static int FSOUND_FX_MAX = EnumerationJNI.get_FSOUND_FX_MAX();
}