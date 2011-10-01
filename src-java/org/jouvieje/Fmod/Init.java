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

import org.jouvieje.Fmod.Defines.INIT_MODES;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.libloader.LibLoader;

/**
 * Initialization of <code>NativeFmod</code> / <code>FMOD.<BR>
 * You should call <code><a href="#loadLibraries()">Init.loadLibraries</a></code> before trying to use <code>NativeFmodEx</code> & <code>FmodEx</code>.
 */
public class Init {
	private Init(){}

	/** Display errors when loading libraries */
	public static boolean DEBUG = false;
	/** */
	protected static boolean fmodLibrariesLoaded = false;
	/** */
	protected static boolean fmodDynLibrariesLoaded = false;
	/** */
	protected static int initMode = -1;

	/**
	 * This is the first thing to do before trying to use Fmod.<BR>
	 * This method loads requiered libraries. If all requiered libraries
	 * are not loaded an exception occures. So, you can decide by you own
	 * to continue executing you application or exiting it (in previous
	 * version, I quit automaticly).
	 * @throws InitException exception that occures when all libraries are not properly loaded.
	 */
	public static void loadLibraries() throws InitException {
		loadLibraries(INIT_MODES.INIT_FMOD);
	}

	/**
	 * This is the first thing to do before trying to use FMOD Ex / FMOD Designer.<BR>
	 * <BR>
	 * This method loads all requiered libraries.<BR>
	 * If all libraries are not loaded an exception occures. Then, you can decide what should
	 * be done.<BR>
	 * @throws InitException exception that occures when all libraries are not properly loaded.
	 * @see INIT_MODES
	 */
	public static void loadLibraries(int mode) throws InitException {
		switch(mode) {
			case INIT_MODES.INIT_DEFAULT:
			case INIT_MODES.INIT_FMOD:
				loadFmod();
				fmodLibrariesLoaded = true;
				initMode = INIT_MODES.INIT_FMOD;
				break;
			case INIT_MODES.INIT_FMOD_DYN:
				loadFmod();
				loadFmodDyn();
				fmodLibrariesLoaded = true;
				fmodDynLibrariesLoaded = true;
				initMode = INIT_MODES.INIT_FMOD_DYN;
				break;
		}
	}

	private static void loadFmod() throws InitException {
		//Platform library names: [FMOD_LIB_NAME], [FMOD_LIB_FULLNAME], [NATIVEFMOD_LIB_FULLNAME]
		final String[][] libs = new String[][] {
			{"fmod", "fmod.dll", "NativeFmod", "NativeFmod.dll"},
			{"fmod-3.75", "libfmod-3.75.so", "NativeFmod", "libNativeFmod.so"},
			{null, null, "NativeFmod", "libNativeFmod.jnilib"},
			{"fmodce", "fmodce.dll", "NativeFmodCe", "NativeFmodCe.dll"},
		};
		final boolean[] libLoaderFirst = new boolean[] {false, true};
		
		//Propagate debug
		LibLoader.DEBUG = DEBUG;
		
		boolean loaded = false;
		for(int i = 0; i < libs.length; i++) {
			final String[] libNames = libs[i];
			final boolean hasFirstLib = (libNames[0] != null) && (libNames[1] != null);
			if(!hasFirstLib || LibLoader.loadLibrary(libNames[0], libNames[1], libLoaderFirst[0])) {
				if(hasFirstLib) {
					printlnDebug("FMOD successfully loaded");
				}
				if(LibLoader.loadLibrary(libNames[2], libNames[3], libLoaderFirst[1])) {
					printlnDebug("NativeFmod successfully loaded");
					loaded = true;
					break;
				}
			}
		}
		if(!loaded) {
			throw new InitException("no NativeFmod in java.library.path or org.lwjgl.librarypath");
		}
		
		InitJNI.attachJavaVM();
	}
	private static void loadFmodDyn() throws InitException {
		//Platform library names: [FMOD_LIB_NAME], [FMOD_LIB_FULLNAME], [NATIVEFMOD_LIB_FULLNAME]
		final String[][] libs = new String[][] {
			{"NativeFmodDyn", "NativeFmodDyn.dll"},
			{"NativeFmodDynCe", "NativeFmodDynCe.dll"},
		};
		
		//Propagate debug
		LibLoader.DEBUG = DEBUG;
		
		boolean loaded = false;
		for(int i = 0; i < libs.length; i++) {
			final String[] libNames = libs[i];
			if(LibLoader.loadLibrary(libNames[0], libNames[1], true)) {
				printlnDebug("NativeFmodDyn successfully loaded");
				loaded = true;
				break;
			}
		}
		if(!loaded) {
			throw new InitException("no NativeFmodDyn in java.library.path or org.lwjgl.librarypath");
		}
		
		InitDynJNI.attachJavaVM();
	}
	
	/**
	 * You can use this method to know if all libraries needed are loaded.
	 * @return true if all libraries requiered are loaded.
	 */
	public static boolean isFmodLibrariesLoaded() {
		return fmodLibrariesLoaded;
	}

	/**
	 * You can use this method to know if all libraries needed are loaded.
	 * @return true if all libraries requiered are loaded.
	 */
	public static boolean isFmodDynLibrariesLoaded() {
		return fmodLibrariesLoaded;
	}

	/**
	 * Mode used for the initialization.
	 * @return <code>INIT_MODES</code> value specified to <code>loadLibraries</code>.<BR>
	 * -1 if the loading is not done.
	 * @see #loadLibraries(int)
	 * @see INIT_MODES
	 */
	public static int getInitMode() {
		return initMode;
	}

	private static void printlnDebug(String s) {
		if(DEBUG) {
			java.lang.System.out.println(s);
		}
	}
	private static void stackTraceDebug(Throwable t) {
		if(DEBUG) {
			t.printStackTrace();
		}
	}
}
