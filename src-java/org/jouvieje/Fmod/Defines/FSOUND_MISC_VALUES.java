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
 * Miscellaneous values for FMOD functions.
 */
public interface FSOUND_MISC_VALUES
{
	/**value to play on any free channel, or to allocate a sample in a free sample slot.*/
	public final static int FSOUND_FREE = -1;
	/**value to allocate a sample that is NOT managed by FSOUND or placed in a sample slot.*/
	public final static int FSOUND_UNMANAGED = -2;
	/**for a channel index , this flag will affect ALL channels available! Not supported by every function.*/
	public final static int FSOUND_ALL = -3;
	/**value for FSOUND_SetPan so that stereo sounds are not played at half volume. See FSOUND_SetPan for more on this.*/
	public final static int FSOUND_STEREOPAN = -1;
	/**special 'channel' ID for all channel based functions that want to alter the global FSOUND software mixing output channel*/
	public final static int FSOUND_SYSTEMCHANNEL = -1000;
	/**special 'sample' ID for all sample based functions that want to alter the global FSOUND software mixing output sample*/
	public final static int FSOUND_SYSTEMSAMPLE = -1000;
}