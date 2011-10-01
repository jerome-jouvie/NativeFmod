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
 * These are speaker types defined for use with the FSOUND_SetSpeakerMode command.
 * Note - Only reliably works with FSOUND_OUTPUT_DSOUND or FSOUND_OUTPUT_XBOX output modes. Other output modes will only
 * interpret FSOUND_SPEAKERMODE_MONO and set everything else to be stereo.
 * Using either DolbyDigital or DTS will use whatever 5.1 digital mode is available if destination hardware is unsure.
 */
public interface FSOUND_SPEAKERMODES
{
	/**Dolby Digital Output (XBOX or PC only).*/
	public final static int FSOUND_SPEAKERMODE_DOLBYDIGITAL = EnumerationJNI.get_FSOUND_SPEAKERMODE_DOLBYDIGITAL();
	/**The speakers are headphones.*/
	public final static int FSOUND_SPEAKERMODE_HEADPHONES = EnumerationJNI.get_FSOUND_SPEAKERMODE_HEADPHONES();
	/**The speakers are monaural.*/
	public final static int FSOUND_SPEAKERMODE_MONO = EnumerationJNI.get_FSOUND_SPEAKERMODE_MONO();
	/**The speakers are quadraphonic.*/
	public final static int FSOUND_SPEAKERMODE_QUAD = EnumerationJNI.get_FSOUND_SPEAKERMODE_QUAD();
	/**The speakers are stereo (default value).*/
	public final static int FSOUND_SPEAKERMODE_STEREO = EnumerationJNI.get_FSOUND_SPEAKERMODE_STEREO();
	/**The speakers are surround sound.*/
	public final static int FSOUND_SPEAKERMODE_SURROUND = EnumerationJNI.get_FSOUND_SPEAKERMODE_SURROUND();
	/**DTS output (XBOX only).*/
	public final static int FSOUND_SPEAKERMODE_DTS = EnumerationJNI.get_FSOUND_SPEAKERMODE_DTS();
	/**Dolby Prologic 2. Playstation 2 and Gamecube only. PlayStation 2 doesnt support interior panning, but supports 48 voices simultaneously.*/
	public final static int FSOUND_SPEAKERMODE_PROLOGIC2 = EnumerationJNI.get_FSOUND_SPEAKERMODE_PROLOGIC2();
	/**Dolby Prologic 2. Playstation 2 and Gamecube only. PlayStation 2 does support interior panning, but only supports 24 voices simultaneously.*/
	public final static int FSOUND_SPEAKERMODE_PROLOGIC2_INTERIOR = EnumerationJNI.get_FSOUND_SPEAKERMODE_PROLOGIC2_INTERIOR();
}