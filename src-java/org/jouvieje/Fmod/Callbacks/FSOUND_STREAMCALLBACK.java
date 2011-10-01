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

package org.jouvieje.Fmod.Callbacks;

import java.nio.ByteBuffer;

import org.jouvieje.Fmod.Misc.Pointer;
import org.jouvieje.Fmod.Structures.FSOUND_STREAM;

/**
 * Callback used with user streams.
 */
public interface FSOUND_STREAMCALLBACK
{
	/**
	 * Callback used with user streams.
	 * @param stream Pointer to the stream in question.
	 * @param buff
	 * from FSOUND_Stream_Create - Pointer to the stream data buffer to write to<BR>
	 * from FSOUND_Stream_SetEndCallback - NULL<BR>
	 * from FSOUND_Stream_SetSyncCallback - Pointer to a string
	 * @param len
	 * from FSOUND_Stream_Create - Length of buffer specified in BYTES.<BR>
	 * from FSOUND_Stream_SetEndCallback - 0<BR>
	 * from FSOUND_Stream_SetSyncCallback - 0
	 * @param userdata A user data value specified from FSOUND_Stream_Create,
	 * @return On success, a pointer to an opened stream is returned.<BR>
	 * On failure, NULL is returned.
	 */
	public boolean FSOUND_STREAMCALLBACK(FSOUND_STREAM stream, ByteBuffer buff, int len, Pointer userdata);
}