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

package org.jouvieje.Fmod.Structures;

import org.jouvieje.Fmod.Misc.Pointer;

public class FSOUND_SAMPLE extends Pointer
{
	/**
	 * Create a view of the <code>Pointer</code> object as a <code>FSOUND_SAMPLE</code> object.<br>
	 * This view is valid only if the memory holded by the <code>Pointer</code> holds a FSOUND_SAMPLE object.
	 */
	public static FSOUND_SAMPLE createView(Pointer pointer)
	{
		return new FSOUND_SAMPLE(Pointer.getPointer(pointer));
	}
	protected FSOUND_SAMPLE(long pointer)
	{
		super(pointer);
	}

	/**
	 * Create an object that holds a null <code>FSOUND_SAMPLE</code>.<br>
	 * <pre><code>  FSOUND_SAMPLE obj = new FSOUND_SAMPLE();
	 *  (obj == null) <=> false
	 *  obj.isNull() <=> true
	 * </code></pre>
	 * To creates a new <code>FSOUND_SAMPLE</code>, use the static "constructor" :
	 * <pre><code>  FSOUND_SAMPLE obj = FSOUND_SAMPLE.create();</code></pre>
	 */
	public FSOUND_SAMPLE()
	{
		super();
	}

	public void release()
	{
		pointer = 0;
	}

}