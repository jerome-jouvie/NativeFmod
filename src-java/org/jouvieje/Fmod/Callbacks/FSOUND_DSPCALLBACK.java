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
 * Callback definition for DSP units.
 */
public interface FSOUND_DSPCALLBACK
{
	/**
	 * Callback definition for DSP units.
	 * @param originalbuffer This is the pointer to the original buffer passed into the first DSP unit.<BR>
	 * This is useful if you want the clean, original data, and you have been returning new modified buffers for the DSP chain to use.
	 * @param newBuffer This is a pointer to the previous DSP buffer that *it* returned.<BR>
	 * This buffer that this DSP returns will be passed into the newbuffer parameter of the NEXT unit in the DSP chain.
	 * @param length The length of the buffer provided in SAMPLES, not bytes.
	 * @param userdata A user data value specified in FSOUND_DSP_Create.
	 * @return Pointer to a sample buffer for the next DSP unit to use.
	 */
	public ByteBuffer FSOUND_DSPCALLBACK(ByteBuffer originalbuffer, ByteBuffer newBuffer, int length, Pointer userdata);
}