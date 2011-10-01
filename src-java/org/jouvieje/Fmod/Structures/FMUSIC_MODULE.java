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

public class FMUSIC_MODULE extends Pointer
{
	/**
	 * Create a view of the <code>Pointer</code> object as a <code>FMUSIC_MODULE</code> object.<br>
	 * This view is valid only if the memory holded by the <code>Pointer</code> holds a FMUSIC_MODULE object.
	 */
	public static FMUSIC_MODULE createView(Pointer pointer)
	{
		return new FMUSIC_MODULE(Pointer.getPointer(pointer));
	}
	protected FMUSIC_MODULE(long pointer)
	{
		super(pointer);
	}

	/**
	 * Create an object that holds a null <code>FMUSIC_MODULE</code>.<br>
	 * The call <code>isNull()</code> on the object created will returns true.<br>
	 * <pre><code>  FMUSIC_MODULE obj = new FMUSIC_MODULE();
	 *  (obj == null) <=> false
	 *  obj.isNull() <=> true
	 * </code></pre>
	 * To creates a new <code>FMUSIC_MODULE</code>, use the static "constructor" :
	 * <pre><code>  FMUSIC_MODULE obj = FMUSIC_MODULE.create();</code></pre>
	 */
	public FMUSIC_MODULE()
	{
		super();
	}

	public void release()
	{
		pointer = 0;
	}
}