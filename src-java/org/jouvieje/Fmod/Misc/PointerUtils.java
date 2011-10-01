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

package org.jouvieje.Fmod.Misc;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Utility routines for dealing with Pointer.
 */
public class PointerUtils
{
	/**
	 * Convert a <code>Pointer</code> object to a <code>ByteBuffer</code>.
	 * @param pointer Pointer object to convert into a ByteBuffer.
	 * @param capacityInBytes capacity in BYTES of the buffer.
	 * @return the Pointer object as a ByteBuffer.
	 */
	public static ByteBuffer toBuffer(Pointer pointer, long capacityInBytes)
	{
		ByteBuffer result = BufferUtilsJNI.newDirectByteBuffer(Pointer.getPointer(pointer), capacityInBytes);
		if(result != null)
		{
			result.order(ByteOrder.nativeOrder());
		}
		return result;
	}

	/**
	 * Retrieve the <code>String</code> value stored in a <code>Pointer</code> (null terminated string).<BR>
	 * @param pointer a <code>Pointer</code> that holds a String.
	 * @return the String stored in the Pointer.<BR>
	 * null if no String is stored in the buffer.
	 * @see #toString(Pointer, int, int)
	 */
	public static String toString(Pointer pointer)
	{
		long address = Pointer.getPointer(pointer);
		return (address == 0) ? null : PointerUtilsJNI.Pointer_toString(address);
	}
	/**
	 * Retrieve the <code>String</code> value stored in the <code>Pointer</code>.
	 * @param pointer a <code>Pointer</code> that holds a String.
	 * @param offset offset (in characters) from the current position in the <code>Pointer</code>.
	 * @param length length of the String to retrieve.
	 * @return the string stored in the <code>Pointer</code>.
	 * @see #toString(Pointer)
	 * @see BufferUtils#toString(ByteBuffer, int, int)
	 */
	public static String toString(Pointer pointer, int offset, int length)
	{
		long tempAddress = Pointer.getPointer(pointer);
		if(tempAddress == 0)
			return null;
		long address = tempAddress+offset;

		String s = PointerUtilsJNI.Pointer_toString(address);
		if(s != null && s.length() > length)
			s = s.substring(0, length);
		return s;
	}
}