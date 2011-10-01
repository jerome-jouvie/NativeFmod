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

/**
 * Store a Java object.<BR>
 * <BR>
 * This class is typicaly used to pass a Java object to Fmod (throw a callback as the userdata parameter...)<BR>
 * Look at the Fmod example <code>stream</code> to have an exemple on how to do this.<BR>
 * <BR>
 * <B><U>ACCESSING VALUE</U></B><BR>
 * If you want to access the Object store in ObjectPointer, use something like this :<BR>
 * <code><pre>
 *  float[] array = ...;   //float[] already created
 *  ObjectPointer objectPointer = new ObjectPointer(array);
 *  ...
 *  //Use a cast
 *  float[] arrayPointed = (float[])objectPointer.getObject();
 * </pre></code><BR>
 */
public class ObjectPointer extends Pointer
{
	/**
	 * Create a view of the <code>Pointer</code> as a <code>ObjectPointer</code> object.<br>
	 * This view is valid only if the memory holded by the <code>Pointer</code> holds an <code>Object</code>.
	 */
	public static ObjectPointer createView(Pointer pointer)
	{
		return new ObjectPointer(Pointer.getPointer(pointer));
	}
	/**
	 * Create a new <code>ObjectPointer</code>.<br>
	 * The call <code>isNull()</code> on the object created will return false.<br>
	 * <pre><code>  FMOD_CDTOC obj = FMOD_CDTOC.create();
	 *  (obj == null) <=> obj.isNull() <=> false
	 * </code></pre>
	 */
	public static ObjectPointer create(Object obj)
	{
		return new ObjectPointer(PointerUtilsJNI.new_ObjectPointer(obj));
	}
	protected ObjectPointer(long pointer)
	{
		super(pointer);
	}

	/**
	 * Create an object that holds a null <code>ObjectPointer</code>.<br>
	 * The call <code>isNull()</code> on the object created will returns true.<br>
	 * <pre><code>  ObjectPointer obj = new ObjectPointer();
	 *  (obj == null) <=> false
	 *  obj.isNull() <=> true
	 * </code></pre>
	 * To creates a new <code>ObjectPointer</code>, use the static "constructor" :
	 * <pre><code>  ObjectPointer obj = ObjectPointer.create();</code></pre>
	 * @see ObjectPointer#create(Object)
	 */
	public ObjectPointer()
	{
		super();
	}

	public void release()
	{
		if(pointer != 0)
		{
			PointerUtilsJNI.delete_ObjectPointer(pointer);
		}
		pointer = 0;
	}

	/**
	 * @return the object stored in the memory
	 */
	public Object getValue()
	{
		return getObject();
	}

	/**
	 * @return the object stored in the memory
	 */
	public Object getObject()
	{
		return PointerUtilsJNI.get_ObjectPointer(pointer);
	}

	/**
	 * @param obj object to store in the memory.
	 */
	public void setObject(Object obj)
	{
		PointerUtilsJNI.set_ObjectPointer(pointer, obj);
	}
}