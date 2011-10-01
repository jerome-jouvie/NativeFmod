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

package org.jouvieje.Fmod;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.jouvieje.Fmod.Callbacks.FMUSIC_CALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_ALLOCCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_CLOSECALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_DSPCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_FREECALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_METADATACALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_OPENCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_READCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_REALLOCCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_SEEKCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_STREAMCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_TELLCALLBACK;
import org.jouvieje.Fmod.Misc.Pointer;
import org.jouvieje.Fmod.Structures.FMUSIC_MODULE;
import org.jouvieje.Fmod.Structures.FSOUND_STREAM;

class CallbackBridge extends Pointer
{
	public static void FMUSIC_CALLBACK(long mod, short param, FMUSIC_CALLBACK callback)
	{
		callback.FMUSIC_CALLBACK((mod == 0) ? null : FMUSIC_MODULE.createView(Pointer.newPointer(mod)), param);
	}
	public static ByteBuffer FSOUND_ALLOCCALLBACK(int size, FSOUND_ALLOCCALLBACK callback)
	{
		return callback.FSOUND_ALLOCCALLBACK(size);
	}
	public static void FSOUND_CLOSECALLBACK(long handle, FSOUND_CLOSECALLBACK callback)
	{
		callback.FSOUND_CLOSECALLBACK((handle == 0) ? null : Pointer.newPointer(handle));
	}
	public static ByteBuffer FSOUND_DSPCALLBACK(ByteBuffer originalbuffer, ByteBuffer newBuffer, int length, long userdata, FSOUND_DSPCALLBACK callback)
	{
		if(originalbuffer != null)
			originalbuffer.order(ByteOrder.nativeOrder());
		if(newBuffer != null)
			newBuffer.order(ByteOrder.nativeOrder());
		return callback.FSOUND_DSPCALLBACK(originalbuffer, newBuffer, length,
				(userdata == 0) ? null : Pointer.newPointer(userdata));
	}
	public static void FSOUND_FREECALLBACK(ByteBuffer ptr, FSOUND_FREECALLBACK callback)
	{
		callback.FSOUND_FREECALLBACK(ptr);
	}
	public static boolean FSOUND_METADATACALLBACK(String name, String value, long userdata, FSOUND_METADATACALLBACK callback)
	{
		return callback.FSOUND_METADATACALLBACK(name, value, (userdata == 0) ? null : Pointer.newPointer(userdata));
	}
	public static long FSOUND_OPENCALLBACK(String name, FSOUND_OPENCALLBACK callback)
	{
		Pointer result = callback.FSOUND_OPENCALLBACK(name);
		return Pointer.getPointer(result);
	}
	public static int FSOUND_READCALLBACK(ByteBuffer buffer, int size, long handle, FSOUND_READCALLBACK callback)
	{
		if(buffer != null)
			buffer.order(ByteOrder.nativeOrder());
		return callback.FSOUND_READCALLBACK(buffer, size,
				(handle == 0) ? null : Pointer.newPointer(handle));
	}
	public static ByteBuffer FSOUND_REALLOCCALLBACK(ByteBuffer ptr, int size, FSOUND_REALLOCCALLBACK callback)
	{
		if(ptr != null)
			ptr.order(ByteOrder.nativeOrder());
		return callback.FSOUND_REALLOCCALLBACK(ptr, size);
	}
	public static int FSOUND_SEEKCALLBACK(long handle, int pos, byte mode, FSOUND_SEEKCALLBACK callback)
	{
		return callback.FSOUND_SEEKCALLBACK((handle == 0) ? null : Pointer.newPointer(handle), pos, mode);
	}
	public static boolean FSOUND_STREAMCALLBACK(long stream, ByteBuffer buff, int len, long userdata, FSOUND_STREAMCALLBACK callback)
	{
		if(buff != null)
			buff.order(ByteOrder.nativeOrder());
		boolean result = callback.FSOUND_STREAMCALLBACK((stream == 0) ? null : FSOUND_STREAM.createView(Pointer.newPointer(stream)),
				buff, len,
				(userdata == 0) ? null : Pointer.newPointer(userdata));
		return result;
	}
	public static int FSOUND_TELLCALLBACK(long handle, FSOUND_TELLCALLBACK callback)
	{
		return callback.FSOUND_TELLCALLBACK((handle ==0) ? null : Pointer.newPointer(handle));
	}
}