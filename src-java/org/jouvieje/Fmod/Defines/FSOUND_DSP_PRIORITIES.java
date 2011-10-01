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
 * These default priorities are used by FMOD internal system DSP units. They describe the
 * position of the DSP chain, and the order of how audio processing is executed.
 * You can actually through the use of FSOUND_DSP_GetxxxUnit (where xxx is the name of the DSP
 * unit), disable or even change the priority of a DSP unit.
 */
public interface FSOUND_DSP_PRIORITIES
{
	/**DSP CLEAR unit - done first*/
	public final static int FSOUND_DSP_DEFAULTPRIORITY_CLEARUNIT = 0;
	/**DSP SFX unit - done second*/
	public final static int FSOUND_DSP_DEFAULTPRIORITY_SFXUNIT = 100;
	/**DSP MUSIC unit - done third*/
	public final static int FSOUND_DSP_DEFAULTPRIORITY_MUSICUNIT = 200;
	/**User priority, use this as reference for your own DSP units*/
	public final static int FSOUND_DSP_DEFAULTPRIORITY_USER = 300;
	/**This reads data for FSOUND_DSP_GetSpectrum, so it comes after user units*/
	public final static int FSOUND_DSP_DEFAULTPRIORITY_FFTUNIT = 900;
	/**DSP CLIP AND COPY unit - last*/
	public final static int FSOUND_DSP_DEFAULTPRIORITY_CLIPANDCOPYUNIT = 1000;
}