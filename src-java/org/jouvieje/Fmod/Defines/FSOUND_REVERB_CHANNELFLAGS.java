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
 * Values for the Flags member of the FSOUND_REVERB_CHANNELPROPERTIES structure.
 */
public interface FSOUND_REVERB_CHANNELFLAGS
{
	 /**Automatic setting of 'Direct' due to distance from listener*/
	public final static int FSOUND_REVERB_CHANNELFLAGS_DIRECTHFAUTO = 0x00000001;
	/**Automatic setting of 'Room' due to distance from listener*/
	public final static int FSOUND_REVERB_CHANNELFLAGS_ROOMAUTO = 0x00000002;
	/**Automatic setting of 'RoomHF' due to distance from listener*/
	public final static int FSOUND_REVERB_CHANNELFLAGS_ROOMHFAUTO = 0x00000004;
	public final static int FSOUND_REVERB_CHANNELFLAGS_DEFAULT = (FSOUND_REVERB_CHANNELFLAGS_DIRECTHFAUTO |
            FSOUND_REVERB_CHANNELFLAGS_ROOMAUTO | FSOUND_REVERB_CHANNELFLAGS_ROOMHFAUTO);
}