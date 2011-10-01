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
 * On failure of commands in FMOD, use FSOUND_GetError to attain what happened.
 */
public interface FMOD_ERRORS
{
	/**No errors*/
	public final static int FMOD_ERR_NONE = EnumerationJNI.get_FMOD_ERR_NONE();
	/**Cannot call this command after FSOUND_Init. Call FSOUND_Close first.*/
	public final static int FMOD_ERR_BUSY = EnumerationJNI.get_FMOD_ERR_BUSY();
	/**This command failed because FSOUND_Init or FSOUND_SetOutput was not called*/
	public final static int FMOD_ERR_UNINITIALIZED = EnumerationJNI.get_FMOD_ERR_UNINITIALIZED();
	/**Error initializing output device.*/
	public final static int FMOD_ERR_INIT = EnumerationJNI.get_FMOD_ERR_INIT();
	/**Error initializing output device, but more specifically, the output device is already in use and cannot be reused.*/
	public final static int FMOD_ERR_ALLOCATED = EnumerationJNI.get_FMOD_ERR_ALLOCATED();
	/**Playing the sound failed.*/
	public final static int FMOD_ERR_PLAY = EnumerationJNI.get_FMOD_ERR_PLAY();
	/**Soundcard does not support the features needed for this soundsystem (16bit stereo output)*/
	public final static int FMOD_ERR_OUTPUT_FORMAT = EnumerationJNI.get_FMOD_ERR_OUTPUT_FORMAT();
	/**Error setting cooperative level for hardware.*/
	public final static int FMOD_ERR_COOPERATIVELEVEL = EnumerationJNI.get_FMOD_ERR_COOPERATIVELEVEL();
	/**Error creating hardware sound buffer.*/
	public final static int FMOD_ERR_CREATEBUFFER = EnumerationJNI.get_FMOD_ERR_CREATEBUFFER();
	/**File not found*/
	public final static int FMOD_ERR_FILE_NOTFOUND = EnumerationJNI.get_FMOD_ERR_FILE_NOTFOUND();
	/**Unknown file format*/
	public final static int FMOD_ERR_FILE_FORMAT = EnumerationJNI.get_FMOD_ERR_FILE_FORMAT();
	/**Error loading file*/
	public final static int FMOD_ERR_FILE_BAD = EnumerationJNI.get_FMOD_ERR_FILE_BAD();
	/**Not enough memory or resources*/
	public final static int FMOD_ERR_MEMORY = EnumerationJNI.get_FMOD_ERR_MEMORY();
	/**The version number of this file format is not supported*/
	public final static int FMOD_ERR_VERSION = EnumerationJNI.get_FMOD_ERR_VERSION();
	/**An invalid parameter was passed to this function*/
	public final static int FMOD_ERR_INVALID_PARAM = EnumerationJNI.get_FMOD_ERR_INVALID_PARAM();
	/**Tried to use an EAX command on a non EAX enabled channel or output.*/
	public final static int FMOD_ERR_NO_EAX = EnumerationJNI.get_FMOD_ERR_NO_EAX();
	/**Failed to allocate a new channel*/
	public final static int FMOD_ERR_CHANNEL_ALLOC = EnumerationJNI.get_FMOD_ERR_CHANNEL_ALLOC();
	/**Recording is not supported on this machine*/
	public final static int FMOD_ERR_RECORD = EnumerationJNI.get_FMOD_ERR_RECORD();
	/**Windows Media Player not installed so cannot play wma or use internet streaming.*/
	public final static int FMOD_ERR_MEDIAPLAYER = EnumerationJNI.get_FMOD_ERR_MEDIAPLAYER();
	/**An error occured trying to open the specified CD device*/
	public final static int FMOD_ERR_CDDEVICE = EnumerationJNI.get_FMOD_ERR_CDDEVICE();
}