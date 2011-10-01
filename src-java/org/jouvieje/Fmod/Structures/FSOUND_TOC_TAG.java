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

/**
 * Structure defining a CD table of contents. This structure is returned as a tag from FSOUND_Stream_FindTagField when the tag name "CD_TOC" is specified.
 * Note: All tracks on the CD - including data tracks- will be represented in this structure so it's use for anything other than generating disc id information is not recommended.
 * See the cdda example program for info on retrieving and using this structure.
 */
public class FSOUND_TOC_TAG extends Pointer
{
	/**
	 * Create a view of the <code>Pointer</code> object as a <code>FSOUND_TOC_TAG</code> object.<br>
	 * This view is valid only if the memory holded by the <code>Pointer</code> holds a FSOUND_TOC_TAG object.
	 */
	public static FSOUND_TOC_TAG createView(Pointer pointer)
	{
		return new FSOUND_TOC_TAG(Pointer.getPointer(pointer));
	}
	/**
	 * Create a new <code>FSOUND_TOC_TAG</code>.<br>
	 * The call <code>isNull()</code> on the object created will return false.<br>
	 * <pre><code>  FSOUND_TOC_TAG obj = FSOUND_TOC_TAG.create();
	 *  (obj == null) <=> obj.isNull() <=> false
	 * </code></pre>
	 */
	public static FSOUND_TOC_TAG create()
	{
		return new FSOUND_TOC_TAG(StructureJNI.new_FSOUND_TOC_TAG());
	}

	protected FSOUND_TOC_TAG(long pointer)
	{
		super(pointer);
	}

	/**
	 * Create an object that holds a null <code>FSOUND_TOC_TAG</code>.<br>
	 * The call <code>isNull()</code> on the object created will returns true.<br>
	 * <pre><code>  FSOUND_TOC_TAG obj = new FSOUND_TOC_TAG();
	 *  (obj == null) <=> false
	 *  obj.isNull() <=> true
	 * </code></pre>
	 * To creates a new <code>FSOUND_TOC_TAG</code>, use the static "constructor" :
	 * <pre><code>  FSOUND_TOC_TAG obj = FSOUND_TOC_TAG.create();</code></pre>
	 * @see FSOUND_TOC_TAG#create()
	 */
	public FSOUND_TOC_TAG()
	{
		super();
	}

	public void release()
	{
		if(pointer != 0)
		{
			StructureJNI.delete_FSOUND_TOC_TAG(pointer);
		}
		pointer = 0;
	}

	/**
	 * The string "TOC", just in case this structure is accidentally treated as a string
	 * @return the value of name
	 */
	public String getName()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_TOC_TAG_name(pointer);
	}

	/**
	 * The number of tracks on the CD
	 * @return the value of numtracks
	 */
	public int getNumtracks()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_TOC_TAG_numtracks(pointer);
	}


	/**
	 * The start offset of each track in minutes
	 * @return the value of min (int[100])
	 */
	public int[] getMin()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_TOC_TAG_min(pointer);
	}

	/**
	 * The start offset of each track in seconds
	 * @return the value of sec
	 */
	public int[] getSec()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_TOC_TAG_sec(pointer);
	}

	/**
	 * The start offset of each track in frames
	 * @return the value of frame
	 */
	public int[] getFrame()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_TOC_TAG_frame(pointer);
	}
}