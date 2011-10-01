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

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * This class allow you to read some datas from a <code>RandomAccessFile</code> object.
 */
public class FileReaderUtils implements SizeOfPrimitive
{
	private static ByteBuffer shortBuffer = null;
	private static ByteBuffer charBuffer = null;
	private static ByteBuffer intBuffer = null;
	private static ByteBuffer longBuffer = null;
	private static ByteBuffer floatBuffer = null;
	private static ByteBuffer doubleBuffer = null;
	
	/** Call this to free internal memory allocated */
	public static void freeMemory() {
		shortBuffer = null;
		charBuffer = null;
		intBuffer = null;
		longBuffer = null;
		floatBuffer = null;
		doubleBuffer = null;
	}
	
	/**
	 * Read a simple <code>byte</code> into the file.
	 * @return the <code>byte</code> read from the file
	 * @throws IOException if an I/O error occures.
	 */
	public static byte readByte(RandomAccessFile file) throws IOException {
		return file.readByte();
	}
	/**
	 * Read a <code>short</code> from 2 bytes of the file.
	 * @return the <code>short</code> read from the file.
	 * @throws IOException if an I/O error occures.
	 */
	public static short readShort(RandomAccessFile file) throws IOException {
		synchronized(getShortBuffer()) {
			file.getChannel().read(shortBuffer);
			shortBuffer.rewind();
			return shortBuffer.getShort(0);
		}
	}
	/**
	 * Read a <code>char</code> from 2 bytes of the file.
	 * @return the <code>char</code> read from the file.
	 * @throws IOException if an I/O error occures.
	 */
	public static char readChar(RandomAccessFile file) throws IOException {
		synchronized(getCharBuffer()) {
			file.getChannel().read(charBuffer);
			charBuffer.rewind();
			return charBuffer.getChar(0);
		}
	}
	/**
	 * Read an <code>int</code> from 4 bytes of the file.
	 * @return the <code>int</code> read from the file.
	 * @throws IOException if an I/O error occures.
	 */
	public static int readInt(RandomAccessFile file) throws IOException {
		synchronized(getIntBuffer()) {
			file.getChannel().read(intBuffer);
			intBuffer.rewind();
			return intBuffer.getInt(0);
		}
	}
	/**
	 * Read a <code>float</code> from 4 bytes of the file.
	 * @return the <code>float</code> read from the file.
	 * @throws IOException if an I/O error occures.
	 */
	public static float readFloat(RandomAccessFile file) throws IOException {
		synchronized(getFloatBuffer()) {
			file.getChannel().read(floatBuffer);
			floatBuffer.rewind();
			return floatBuffer.getFloat(0);
		}
	}
	/**
	 * Read a <code>long</code> from 8 bytes of the file.
	 * @return the <code>long</code> read from the file.
	 * @throws IOException if an I/O error occures.
	 */
	public static long readLong(RandomAccessFile file) throws IOException {
		synchronized(getLongBuffer()) {
			file.getChannel().read(longBuffer);
			longBuffer.rewind();
			return longBuffer.getLong(0);
		}
	}
	/**
	 * Read a <code>double</code> from 8 bytes of the file.
	 * @return the <code>double</code> read from the file.
	 * @throws IOException if an I/O error occures.
	 */
	public static double readDouble(RandomAccessFile file) throws IOException {
		synchronized(getDoubleBuffer()) {
			file.getChannel().read(doubleBuffer);
			doubleBuffer.rewind();
			return doubleBuffer.getDouble(0);
		}
	}
	
	/**
	 * Read a <code>byte[]</code> from a file.
	 * @param nbBytes number of bytes to read.
	 * @return the <code>byte[]</code> read from the file.
	 * @throws IOException if an I/O error occures.
	 */
	public static byte[] readByteArray(RandomAccessFile file, int nbBytes) throws IOException {
		byte[] datas = new byte[nbBytes];
		file.read(datas, 0, datas.length);
		return datas;
	}
	/**
	 * Read a <code>byte[]</code> from a file.
	 * @param datas <code>byte[]</code> into which the data is read.
	 * @param offset offset
	 * @param nbBytes number of bytes to be read.
	 * @throws IOException if an I/O error occures.
	 */
	public static void readByteArray(RandomAccessFile file, byte[] datas, int offset, int nbBytes) throws IOException {
		file.read(datas, offset, nbBytes);
	}
	
	/**
	 * Read a <code>ByteBuffer</code> from a file.
	 * @param nbBytes number of bytes to be read.
	 * @return the <code>ByteBuffer</code> read from the file
	 * @throws IOException if an I/O error occures.
	 */
	public static ByteBuffer readByteBuffer(RandomAccessFile file, int nbBytes) throws IOException {
		ByteBuffer datas = BufferUtils.newByteBuffer(nbBytes);
		file.getChannel().read(datas);
		return datas;
	}
	/**
	 * Read a <code>ByteBuffer</code> from a file.<BR>
	 * @param buffer <code>ByteBuffer</code> into which the data is read.
	 * @param nbBytes number of bytes to be read.
	 * @throws IOException if an I/O error occures.
	 */
	public static void readByteBuffer(RandomAccessFile file, ByteBuffer buffer, int nbBytes) throws IOException {
		ByteBuffer view = buffer.duplicate();
		view.limit(view.position()+nbBytes);
		file.getChannel().read(view);
		buffer.position(view.position());
	}
	
					/*PRIVATE*/
	
	private static ByteBuffer getShortBuffer() {
		if(shortBuffer == null) {
			shortBuffer = BufferUtils.newByteBuffer(SIZEOF_SHORT);
		}
		return shortBuffer;
	}

	private static ByteBuffer getCharBuffer() {
		if(charBuffer == null) {
			charBuffer = BufferUtils.newByteBuffer(SIZEOF_CHAR);
		}
		return charBuffer;
	}

	private static ByteBuffer getIntBuffer() {
		if(intBuffer == null) {
			intBuffer = BufferUtils.newByteBuffer(SIZEOF_INT);
		}
		return intBuffer;
	}

	private static ByteBuffer getLongBuffer() {
		if(longBuffer == null) {
			longBuffer = BufferUtils.newByteBuffer(SIZEOF_LONG);
		}
		return longBuffer;
	}

	private static ByteBuffer getFloatBuffer() {
		if(floatBuffer == null) {
			floatBuffer = BufferUtils.newByteBuffer(SIZEOF_FLOAT);
		}
		return floatBuffer;
	}

	private static ByteBuffer getDoubleBuffer() {
		if(doubleBuffer == null) {
			doubleBuffer = BufferUtils.newByteBuffer(SIZEOF_DOUBLE);
		}
		return doubleBuffer;
	}
}