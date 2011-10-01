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
#include "fmod.h"
#include "org_jouvieje_Fmod_Structures_StructureJNI.h"

JNIEXPORT jstring JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1TOC_1TAG_1name(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_TOC_TAG *arg1 = *(FSOUND_TOC_TAG **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_TOC_TAG);
        return 0;
    }
    char *result = (char *)((arg1)->name);

    jstring jresult = 0 ;
	if(result) jresult = jenv->NewStringUTF(result);
    return jresult;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1TOC_1TAG_1numtracks(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_TOC_TAG *arg1 = *(FSOUND_TOC_TAG **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_TOC_TAG);
        return 0;
    }
    int result = (int) ((arg1)->numtracks);
    return (jint)result;
}

JNIEXPORT jintArray JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1TOC_1TAG_1min(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_TOC_TAG *arg1 = *(FSOUND_TOC_TAG **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_TOC_TAG);
        return 0;
    }
    int *result = (int *)(int *) ((arg1)->min);

    jintArray jresult = 0;
	if (result) {
		jresult = jenv->NewIntArray((jsize)100);
		jenv->ReleaseIntArrayElements(jresult, (jint *)result, JNI_COMMIT);
	}
	return jresult;
}

JNIEXPORT jintArray JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1TOC_1TAG_1sec(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_TOC_TAG *arg1 = *(FSOUND_TOC_TAG **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_TOC_TAG);
        return 0;
    }
    int *result = (int *)(int *) ((arg1)->sec);

    jintArray jresult = 0;
	if (result) {
		jresult = jenv->NewIntArray((jsize)100);
		jenv->ReleaseIntArrayElements(jresult, (jint *)result, JNI_COMMIT);
	}
	return jresult;
}

JNIEXPORT jintArray JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1TOC_1TAG_1frame(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_TOC_TAG *arg1 = *(FSOUND_TOC_TAG **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_TOC_TAG);
        return 0;
    }
    int *result = (int *)(int *) ((arg1)->frame);

    jintArray jresult = 0;
	if (result) {
		jresult = jenv->NewIntArray((jsize)100);
		jenv->ReleaseIntArrayElements(jresult, (jint *)result, JNI_COMMIT);
	}
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_new_1FSOUND_1TOC_1TAG(JNIEnv *jenv, jclass jcls) {
    FSOUND_TOC_TAG *result = new FSOUND_TOC_TAG();

    jlong jresult = 0 ;
    *(FSOUND_TOC_TAG **)&jresult = result;
    return jresult;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_delete_1FSOUND_1TOC_1TAG(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_TOC_TAG *arg1 = *(FSOUND_TOC_TAG **)&jarg1;
    delete arg1;
}

