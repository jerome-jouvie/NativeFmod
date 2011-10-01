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

/**
 * Callback for reading from a file.
 */
public interface FSOUND_READCALLBACK
{
	/**
	 * Callback for reading from a file.
	 * @param buffer You must read and copy your file data into this pointer.
	 * @param size You must read this many bytes from your file data.
	 * @param handle This is the handle you returned from the open callback to use for your own file routines.
	 * @return Return the number of bytes that were *successfully* read here.<BR>
	 * Normally this is just the same as 'length', but if you are at the end of the file, you will probably
	 * only read successfully the number of bytes up to the end of the file (if you tried to read more than that)
	 */
	public int FSOUND_READCALLBACK(ByteBuffer buffer, int size, Pointer handle);
}