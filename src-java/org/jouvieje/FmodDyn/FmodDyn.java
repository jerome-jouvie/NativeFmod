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

package org.jouvieje.FmodDyn;

import java.util.Vector;

import org.jouvieje.Fmod.Misc.Pointer;

/**
 * Manage multiple instances of Fmod
 */
public class FmodDyn extends Pointer
{
	private FmodDyn(){}

	private static Vector libraryPaths = null;
	private static String pathSeparator = null;
	private static String fileSeparator = null;

	/**
	 * Creates a new Instance of Fmod
	 * @param dllName name of the Fmod library
	 * @return the instance of Fmod
	 */
	public static FMOD_INSTANCE FMOD_CreateInstance(String dllName)
	{
		long pointer = FmodDynJNI.FMOD_CreateInstance(dllName);

		//If the library is not found, searching in java.library.path
		if(pointer == 0)
		{
			if(libraryPaths == null || pathSeparator == null)
			{
				String libPaths = System.getProperty("java.library.path");
				libraryPaths = new Vector();
				pathSeparator = System.getProperty("path.separator");
				fileSeparator = System.getProperty("file.separator");

				int index;
				while((index = libPaths.indexOf(pathSeparator)) != -1)
				{
					String path = libPaths.substring(0, index);
					if(!path.endsWith(fileSeparator))
						path += fileSeparator;
					libraryPaths.add(path);

					libPaths = libPaths.substring(index+pathSeparator.length(), libPaths.length());
				}
				if(!libPaths.endsWith(fileSeparator))
					libPaths += fileSeparator;
				libraryPaths.add(libPaths);
			}

			for(int i = 0; i < libraryPaths.size(); i++)
			{
				pointer = FmodDynJNI.FMOD_CreateInstance((String)libraryPaths.get(i)+dllName);
				if(pointer != 0)
					break;
			}
		}
		return (pointer == 0) ? null : new FMOD_INSTANCE(pointer);
	}

	/**
	 * Free an instance of Fmod
	 * @param instance Instance to need
	 */
	public static void FMOD_FreeInstance(FMOD_INSTANCE instance)
	{
		FmodDynJNI.FMOD_FreeInstance(Pointer.getPointer(instance));
	}
}
