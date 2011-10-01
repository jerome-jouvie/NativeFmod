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

#include "Utils.h"

const char * NULL_OBJECT_POINTER =					"";
const char * NULL_FSOUND_TOC_TAG =					"";
const char * NULL_FSOUND_REVERB_PROPERTIES =		"";
const char * NULL_FSOUND_REVERB_CHANNEL_PROPERTIES= "";
const char * FATAL_ERROR_MESSAGE =					"An exception occures but it can't be thrown !!!";

void ThrowException(JNIEnv *jenv, ExceptionType type, const char *message) {
	jclass exception;
	switch(type) {
		case NullPointerException:			exception = jenv->FindClass("java/lang/NullPointerException"); break;
		case InitException:					exception = jenv->FindClass("org/jouvieje/Fmod/Exceptions/InitException"); break;
		default:							exception = jenv->FindClass("java/lang/UnknownError");
	}

	if(exception) {
		jenv->ThrowNew(exception, message);
		jenv->DeleteLocalRef(exception);
	}
}

jclass stringClass = 0;
jmethodID getBytesId = 0;

jmethodID getGetBytesId(JNIEnv *jenv) {
	if(getBytesId == 0)
	{
		stringClass = jenv->FindClass("java/lang/String");
		getBytesId = jenv->GetMethodID(stringClass, "getBytes", "()[B");
	}
	return getBytesId;
}
char *getStringElements(JNIEnv *jenv, jstring string) {
	if(string)
	{
		jbyteArray array = (jbyteArray)jenv->CallObjectMethod(string, getGetBytesId(jenv));

		const jsize length = jenv->GetArrayLength(array);
		const jbyte *chars = jenv->GetByteArrayElements(array, 0);
		char *copy = new char[length+1];

		for(int i = 0; i < length; i++) {
			copy[i] = (char)chars[i];
		}
		copy[length] = 0;
		jenv->ReleaseByteArrayElements(array, (jbyte *)chars, 0);

		return copy;
	}
	return 0;
}

void releaseStringElements(JNIEnv *jenv, jstring string, const char *chars) {
	if(string) {
		//TODO Need to free chars
	}
}
