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
 * Values for the Flags member of the FSOUND_REVERB_PROPERTIES structure.
 */
public interface FSOUND_REVERB_FLAGS
{
	/**'EnvSize' affects reverberation decay time*/
	public final static int FSOUND_REVERB_FLAGS_DECAYTIMESCALE = 0x00000001;
	/**'EnvSize' affects reflection level*/
	public final static int FSOUND_REVERB_FLAGS_REFLECTIONSSCALE = 0x00000002;
	/**'EnvSize' affects initial reflection delay time*/
	public final static int FSOUND_REVERB_FLAGS_REFLECTIONSDELAYSCALE = 0x00000004;
	/**'EnvSize' affects reflections level*/
	public final static int FSOUND_REVERB_FLAGS_REVERBSCALE = 0x00000008;
	/**'EnvSize' affects late reverberation delay time*/
	public final static int FSOUND_REVERB_FLAGS_REVERBDELAYSCALE = 0x00000010;
	/**AirAbsorptionHF affects DecayHFRatio*/
	public final static int FSOUND_REVERB_FLAGS_DECAYHFLIMIT = 0x00000020;
	/**'EnvSize' affects echo time*/
	public final static int FSOUND_REVERB_FLAGS_ECHOTIMESCALE = 0x00000040;
	/**'EnvSize' affects modulation time*/
	public final static int FSOUND_REVERB_FLAGS_MODULATIONTIMESCALE = 0x00000080;
	/**PS2 Only - Reverb is applied to CORE0 (hw voices 0-23)*/
	public final static int FSOUND_REVERB_FLAGS_CORE0 = 0x00000100;
	/**PS2 Only - Reverb is applied to CORE1 (hw voices 24-47)*/
	public final static int FSOUND_REVERB_FLAGS_CORE1 = 0x00000200;
	public final static int FSOUND_REVERB_FLAGS_DEFAULT = (FSOUND_REVERB_FLAGS_DECAYTIMESCALE |
			FSOUND_REVERB_FLAGS_REFLECTIONSSCALE | FSOUND_REVERB_FLAGS_REFLECTIONSDELAYSCALE |
            FSOUND_REVERB_FLAGS_REVERBSCALE | FSOUND_REVERB_FLAGS_REVERBDELAYSCALE |
            FSOUND_REVERB_FLAGS_DECAYHFLIMIT | FSOUND_REVERB_FLAGS_CORE0 |
            FSOUND_REVERB_FLAGS_CORE1);
}