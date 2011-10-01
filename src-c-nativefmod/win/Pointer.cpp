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

#include "Pointer.h"

jclass pointerClass = 0;
jclass getPointerClass(JNIEnv *jenv) {
	if(!pointerClass) {
		pointerClass = (jclass)jenv->NewGlobalRef(jenv->FindClass("org/jouvieje/Fmod/Misc/Pointer"));
	}
	return pointerClass;
}
jmethodID newPointerID = 0;
jmethodID getNewPointerID(JNIEnv *jenv) {
	if(!newPointerID) {
		newPointerID = jenv->GetStaticMethodID(getPointerClass(jenv), "newPointer", "(J)Lorg/jouvieje/Fmod/Misc/Pointer;");
	}
	return newPointerID;
}
jfieldID addressId = 0;
jfieldID getFieldID(JNIEnv *jenv) {
	if(!addressId) {
		addressId = jenv->GetFieldID(getPointerClass(jenv),
			"pointer",
			"J");
	}
	return addressId;
}

jobject newPointer(JNIEnv *jenv) {
	jobject result = jenv->CallStaticObjectMethod(getPointerClass(jenv), getNewPointerID(jenv), 0);
	return result;
}

long getPointerAddress(JNIEnv *jenv, jobject obj) {
	if(obj) {
		return (long)jenv->GetLongField(obj, getFieldID(jenv));
	}
	else {
		return 0;
	}
}

void setPointerAddress(JNIEnv *jenv, jobject obj, jlong newAddress) {
	if(obj) {
		jenv->SetLongField(obj, getFieldID(jenv), newAddress);
	}
}
