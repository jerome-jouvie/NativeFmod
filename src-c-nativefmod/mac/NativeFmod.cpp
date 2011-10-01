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

#if defined(__GNUC__)
    typedef long long __int64; /*For gcc on Windows */
#endif

					/*Includes*/

#include "org_jouvieje_Fmod_FmodJNI.h"
#include "org_jouvieje_Fmod_InitJNI.h"
#include "org_jouvieje_Fmod_Defines_DefineJNI.h"
#include "fmod.h"
#include "fmod_errors.h"
#include "Utils.h"
#include "JavaObject.h"
#include "Callback.h"
#include "CallbackStorer.h"
#include "Pointer.h"
#pragma comment(lib,"fmodvc.lib")

							/***********
							 * Defines *
							 ***********/

#define NATIVEFMOD_LIBRARY_VERSION 3.4f

//#define PLATFORM 0				//WIN32
//#define PLATFORM 1				//WIN64
//#define PLATFORM 2				//LINUX
#define PLATFORM 3				//MAC

							/************
							 * Versions *
							 ************/

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Defines_DefineJNI_get_1FMOD_1VERSION(JNIEnv *jenv, jclass jcls){
    float result = (float) FMOD_VERSION;
    return (jfloat)result;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Defines_DefineJNI_get_1NATIVEFMOD_1LIBRARY_1VERSION(JNIEnv *jenv, jclass jcls){
    float result = (float) NATIVEFMOD_LIBRARY_VERSION;
    return (jfloat)result;
}

							/*Deprecated*/

/*Deprecated since Fmod 3.73*/
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1CD_1Eject(JNIEnv *jenv, jclass jcls, jchar jarg1) {
    char arg1 = (char)jarg1;
    return (jboolean)FSOUND_CD_Eject(arg1);
}

								/*Callback*/

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_InitJNI_attachJavaVM(JNIEnv *jenv, jclass jcls) {
	Callback::connectMethods(jenv);
}

								/*Platform*/

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_InitJNI_get_1PLATFORM(JNIEnv *jenv, jclass jcls) {
	return PLATFORM;
}

							/**************
							 * FSOUND API *
							 **************/

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetOutput(JNIEnv *jenv, jclass jcls, jint jarg1) {
    return (jboolean)FSOUND_SetOutput((int)jarg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetDriver(JNIEnv *jenv, jclass jcls, jint jarg1) {
    return (jboolean)FSOUND_SetDriver((int)jarg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetMixer (JNIEnv *jenv, jclass jcls, jint jarg1) {
    return (jboolean)FSOUND_SetMixer((int)jarg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetBufferSize(JNIEnv *jenv, jclass jcls, jint jarg1) {
    return (jboolean)FSOUND_SetBufferSize((int)jarg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetHWND(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    void *arg1 = *(void **)&jarg1;
    return (jboolean)FSOUND_SetHWND(arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetMinHardwareChannels(JNIEnv *jenv, jclass jcls, jint jarg1) {
    return (jboolean)FSOUND_SetMinHardwareChannels((int)jarg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetMaxHardwareChannels(JNIEnv *jenv, jclass jcls, jint jarg1) {
    return (jboolean)FSOUND_SetMaxHardwareChannels((int)jarg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetMemorySystem(JNIEnv *jenv, jclass jcls, jobject jarg1, jint jarg1_, jint jarg2, jobject jarg3, jobject jarg4, jobject jarg5) {
    int arg2 = (int)jarg2;
	if(arg2 > 0) {
		void *arg1 = 0;
		if(jarg1) {
			jobject jarg1Global = jenv->NewGlobalRef(jarg1);
			arg1 = (char *)jenv->GetDirectBufferAddress(jarg1Global)+jarg1_;
		}
		return (jboolean)FSOUND_SetMemorySystem(arg1, arg2, NULL, NULL, NULL);
	}
	else if(jarg3 && jarg4 && jarg5) {
		if(reallocCallback) {
			delete reallocCallback;
			reallocCallback = NULL;
		}
		allocCallback = new Callback(jenv, jarg3);
		if(reallocCallback) {
			delete reallocCallback;
			reallocCallback = NULL;
		}
		reallocCallback = new Callback(jenv, jarg4);
		if(freeCallback) {
			delete freeCallback;
			freeCallback = NULL;
		}
		freeCallback = new Callback(jenv, jarg5);

		return (jboolean)FSOUND_SetMemorySystem(NULL, 0, AllocCallback, ReallocCallback, FreeCallback);
	}
	else {
		return (jboolean)false;
	}
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Init(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2, jint jarg3) {
    int arg1 = (int)jarg1;
    int arg2 = (int)jarg2;
    unsigned int arg3 = (unsigned int)jarg3;
    return (jboolean)FSOUND_Init(arg1,arg2,arg3);
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Close(JNIEnv *jenv, jclass jcls) {
    FSOUND_Close();
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Update(JNIEnv *jenv, jclass jcls) {
    FSOUND_Update();
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetSpeakerMode(JNIEnv *jenv, jclass jcls, jint jarg1) {
    unsigned int arg1 = (unsigned int)jarg1;
    FSOUND_SetSpeakerMode(arg1);
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetSFXMasterVolume(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    FSOUND_SetSFXMasterVolume(arg1);
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetPanSeperation(JNIEnv *jenv, jclass jcls, jfloat jarg1) {
    float arg1 = (float)jarg1;
    FSOUND_SetPanSeperation(arg1);
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1File_1SetCallbacks(JNIEnv *jenv, jclass jcls, jobject jarg1, jobject jarg2, jobject jarg3, jobject jarg4, jobject jarg5) {
	if(cOpenCallback) {
		delete cOpenCallback;
		cOpenCallback = NULL;
	}
	if(jarg1)
		cOpenCallback = new Callback(jenv, jarg1);
	if(cCloseCallback) {
		delete cCloseCallback;
		cCloseCallback = NULL;
	}
	if(jarg2)
	   cCloseCallback = new Callback(jenv, jarg2);
	if(cReadCallback) {
		delete cReadCallback;
		cReadCallback = NULL;
	}
	if(jarg3)
	    cReadCallback = new Callback(jenv, jarg3);
	if(cSeekCallback) {
		delete cSeekCallback;
		cSeekCallback = NULL;
	}
	if(jarg4)
	    cSeekCallback = new Callback(jenv, jarg4);
	if(cTellCallback) {
		delete cTellCallback;
		cTellCallback = NULL;
	}
	if(jarg5)
	    cTellCallback = new Callback(jenv, jarg5);

	FSOUND_File_SetCallbacks(cOpenCallback ? OpenCallback : NULL,
		cCloseCallback ? CloseCallback : NULL,
		cReadCallback ? ReadCallback : NULL,
		cSeekCallback ? SeekCallback : NULL,
		cTellCallback ? TellCallback : NULL);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetError(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_GetError();
    return (jint)result;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetVersion(JNIEnv *jenv, jclass jcls) {
    float result = (float)FSOUND_GetVersion();
    return (jfloat)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetOutput(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_GetOutput();
    return (jint)result;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetOutputHandle(JNIEnv *jenv, jclass jcls) {
    void *result = (void *)FSOUND_GetOutputHandle();

    jlong jresult = 0;
    *(void **)&jresult = result;
    return jresult;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetDriver(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_GetDriver();
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetMixer(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_GetMixer();
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetNumDrivers(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_GetNumDrivers();
    return (jint)result;
}

JNIEXPORT jstring JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetDriverName(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    char *result = (char *)FSOUND_GetDriverName(arg1);

    jstring jresult = 0;
	if(result) jresult = jenv->NewStringUTF(result);
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetDriverCaps__I_3I(JNIEnv *jenv, jclass jcls, jint jarg1, jintArray jarg2) {
    int arg1 = (int)jarg1;
	unsigned int *arg2 = 0;
	if(jarg2) {
		arg2 = (unsigned int *)jenv->GetIntArrayElements(jarg2, 0);
	}
    signed char result = FSOUND_GetDriverCaps(arg1, arg2);
	if (arg2) jenv->ReleaseIntArrayElements(jarg2, (jint *)arg2, 0);
	return (jboolean)result;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetDriverCaps__ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jint jarg1, jobject jarg2, jint jarg2_) {
    int arg1 = (int)jarg1;
	unsigned int *arg2 = 0;
	if(jarg2)
		arg2 = (unsigned int *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
    return (jboolean)FSOUND_GetDriverCaps(arg1, arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetOutputRate(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_GetOutputRate();
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetMaxChannels(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_GetMaxChannels();
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetMaxSamples(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_GetMaxSamples();
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetSpeakerMode(JNIEnv *jenv, jclass jcls) {
    unsigned int result = (unsigned int)FSOUND_GetSpeakerMode();
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetSFXMasterVolume(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_GetSFXMasterVolume();
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetNumHWChannels___3I_3I_3I(JNIEnv *jenv, jclass jcls, jintArray jarg1, jintArray jarg2, jintArray jarg3) {
	int *arg1 = 0;
	if(jarg1) {
	 	arg1 = (int *)jenv->GetIntArrayElements(jarg1, 0);
	}
	int *arg2 = 0;
	if(jarg2) {
	 	arg2 = (int *)jenv->GetIntArrayElements(jarg2, 0);
	}
	int *arg3 = 0;
	if(jarg3) {
	 	arg3 = (int *)jenv->GetIntArrayElements(jarg3, 0);
	}
    jboolean jresult = (jboolean)FSOUND_GetNumHWChannels(arg1,arg2,arg3);
	if (arg1) jenv->ReleaseIntArrayElements(jarg1, (jint *)arg1, 0);
	if (arg2) jenv->ReleaseIntArrayElements(jarg2, (jint *)arg2, 0);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetNumHWChannels__Ljava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jobject jarg1, jint jarg1_, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_) {
	int *arg1 = 0;
	if(jarg1)
		arg1= (int *)((char *)jenv->GetDirectBufferAddress(jarg1)+jarg1_);
	int *arg2 = 0;
	if(jarg2)
		arg2 = (int *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	int *arg3 = 0;
	if(jarg3)
		arg3 = (int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
    return (jboolean)FSOUND_GetNumHWChannels(arg1,arg2,arg3);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetChannelsPlaying(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_GetChannelsPlaying();
    return (jint)result;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetCPUUsage(JNIEnv *jenv, jclass jcls) {
    jfloat result = (float)FSOUND_GetCPUUsage();
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetMemoryStats___3I_3I(JNIEnv *jenv, jclass jcls, jintArray jarg1, jintArray jarg2) {
	unsigned int *arg1 = 0;
	if(jarg1) {
		arg1 = (unsigned int *)jenv->GetIntArrayElements(jarg1, 0);
	}
	unsigned int *arg2 = 0;
	if(jarg2) {
	 	arg2 = (unsigned int *)jenv->GetIntArrayElements(jarg2, 0);
	}
    FSOUND_GetMemoryStats(arg1,arg2);
	if (arg1) jenv->ReleaseIntArrayElements(jarg1, (jint *)arg1, 0);
 	if (arg2) jenv->ReleaseIntArrayElements(jarg2, (jint *)arg2, 0);
}
JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetMemoryStats__Ljava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jobject jarg1, jint jarg1_, jobject jarg2, jint jarg2_) {
	unsigned int *arg1 = 0;
	if(jarg1)
		arg1 = (unsigned int *)((char *)jenv->GetDirectBufferAddress(jarg1)+jarg1_);
	unsigned int *arg2 = 0;
	if(jarg2)
		arg2 = (unsigned int *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
    FSOUND_GetMemoryStats(arg1,arg2);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1Load__ILjava_lang_String_2III(JNIEnv *jenv, jclass jcls, jint jarg1, jstring jarg2, jint jarg3, jint jarg4, jint jarg5) {
    int arg1 = (int)jarg1;
	char *arg2 = 0;
	if (jarg2) {
		arg2 = getStringElements(jenv, jarg2);
    }
    unsigned int arg3 = (unsigned int)jarg3;
    int arg4 = (int)jarg4;
    int arg5 = (int)jarg5;
    FSOUND_SAMPLE *result = (FSOUND_SAMPLE *)FSOUND_Sample_Load(arg1,arg2,arg3,arg4,arg5);

	releaseStringElements(jenv, jarg2, arg2);

    jlong jresult = 0;
    *(FSOUND_SAMPLE **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1Load__ILjava_nio_ByteBuffer_2IIII(JNIEnv *jenv, jclass jcls, jint jarg1, jobject jarg2, jint jarg2_, jint jarg3, jint jarg4, jint jarg5) {
    int arg1 = (int)jarg1;
	char *arg2 = 0;
	if(jarg2)
		arg2 = (char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_;
    unsigned int arg3 = (unsigned int)jarg3;
    int arg4 = (int)jarg4;
    int arg5 = (int)jarg5;
    FSOUND_SAMPLE *result = (FSOUND_SAMPLE *)FSOUND_Sample_Load(arg1,arg2,arg3,arg4,arg5);

    jlong jresult = 0;
    *(FSOUND_SAMPLE **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1Alloc(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2, jint jarg3, jint jarg4, jint jarg5, jint jarg6, jint jarg7) {
    int arg1 = (int)jarg1;
    int arg2 = (int)jarg2;
    unsigned int arg3 = (unsigned int)jarg3;
    int arg4 = (int)jarg4;
    int arg5 = (int)jarg5;
    int arg6 = (int)jarg6;
    int arg7 = (int)jarg7;
    FSOUND_SAMPLE *result = (FSOUND_SAMPLE *)FSOUND_Sample_Alloc(arg1,arg2,arg3,arg4,arg5,arg6,arg7);

    jlong jresult = 0;
    *(FSOUND_SAMPLE **)&jresult = result;
    return jresult;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1Free(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
    FSOUND_Sample_Free(arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1Upload(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jint jarg3) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
	void *arg2 = 0;
	if(jarg2)
		arg2 = (char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_;
    unsigned int arg3 = (unsigned int)jarg3;
    return (jboolean)FSOUND_Sample_Upload(arg1,arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1Lock__JIILorg_jouvieje_Fmod_Misc_Pointer_2Lorg_jouvieje_Fmod_Misc_Pointer_2_3I_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3, jobject jarg4, jobject jarg5, jintArray jarg6, jintArray jarg7) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    void *arg4;
    void *arg5;
	unsigned int *arg6 = 0;
	if(jarg6) {
	 	arg6 = (unsigned int *)jenv->GetIntArrayElements(jarg6, 0);
	}
	unsigned int *arg7 = 0;
	if(jarg7) {
	 	arg7 = (unsigned int *)jenv->GetIntArrayElements(jarg7, 0);
	}
    jboolean jresult = (jboolean)FSOUND_Sample_Lock(arg1,arg2,arg3,&arg4,&arg5,arg6,arg7);

	if(arg4 && jarg4) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg4;
		setPointerAddress(jenv, jarg4, newAddress);
	}
	if(arg5 && jarg5) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg5;
		setPointerAddress(jenv, jarg5, newAddress);
	}

	if (arg6) jenv->ReleaseIntArrayElements(jarg6, (jint *)arg6, 0);
	if (arg7) jenv->ReleaseIntArrayElements(jarg7, (jint *)arg7, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1Lock__JIILorg_jouvieje_Fmod_Misc_Pointer_2Lorg_jouvieje_Fmod_Misc_Pointer_2Ljava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3, jobject jarg4, jobject jarg5, jobject jarg6, jint jarg6_, jobject jarg7, jint jarg7_) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    void *arg4;
    void *arg5;
	unsigned int *arg6 = 0;
	if(jarg6) {
		arg6 = (unsigned int *)((char *)jenv->GetDirectBufferAddress(jarg6)+jarg6_);
	}
	unsigned int *arg7 = 0;
	if(jarg7) {
		arg7 = (unsigned int *)((char *)jenv->GetDirectBufferAddress(jarg7)+jarg7_);
	}
    jboolean jresult = (jboolean)FSOUND_Sample_Lock(arg1,arg2,arg3,&arg4,&arg5,arg6,arg7);

	if(arg4 && jarg4) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg4;
		setPointerAddress(jenv, jarg4, newAddress);
	}
	if(arg5 && jarg5) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg5;
		setPointerAddress(jenv, jarg5, newAddress);
	}

	return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1Unlock__JJJII(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jlong jarg3, jint jarg4, jint jarg5) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
    void *arg2 = *(void **)&jarg2;
    void *arg3 = *(void **)&jarg3;
    unsigned int arg4 = (unsigned int)jarg4;
    unsigned int arg5 = (unsigned int)jarg5;
    return (jboolean)FSOUND_Sample_Unlock(arg1,arg2,arg3,arg4,arg5);
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1Unlock__JLjava_nio_ByteBuffer_2ILjava_nio_ByteBuffer_2III(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_, jint jarg4, jint jarg5) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
	void *arg2 = 0;
	if(jarg2)
		arg2 = (char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_;
	void *arg3 = 0;
	if(jarg3)
		arg3 = (char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_;
    unsigned int arg4 = (unsigned int)jarg4;
    unsigned int arg5 = (unsigned int)jarg5;
    return (jboolean)FSOUND_Sample_Unlock(arg1,arg2,arg3,arg4,arg5);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1SetMode(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
    unsigned int arg2 = (unsigned int)jarg2;
    return (jboolean)FSOUND_Sample_SetMode(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1SetLoopPoints(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)FSOUND_Sample_SetLoopPoints(arg1,arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1SetDefaults(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3, jint jarg4, jint jarg5) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    int arg5 = (int)jarg5;
    return (jboolean)FSOUND_Sample_SetDefaults(arg1,arg2,arg3,arg4,arg5);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1SetDefaultsEx(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3, jint jarg4, jint jarg5, jint jarg6, jint jarg7, jint jarg8) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    int arg5 = (int)jarg5;
    int arg6 = (int)jarg6;
    int arg7 = (int)jarg7;
    int arg8 = (int)jarg8;
    return (jboolean)FSOUND_Sample_SetDefaultsEx(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1SetMinMaxDistance(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2, jfloat jarg3) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
    float arg2 = (float)jarg2;
    float arg3 = (float)jarg3;
    return (jboolean)FSOUND_Sample_SetMinMaxDistance(arg1,arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1SetMaxPlaybacks(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)FSOUND_Sample_SetMaxPlaybacks(arg1,arg2);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1Get(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    FSOUND_SAMPLE *result = (FSOUND_SAMPLE *)FSOUND_Sample_Get(arg1);

    jlong jresult = 0;
    *(FSOUND_SAMPLE **)&jresult = result;
    return jresult;
}

JNIEXPORT jstring JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1GetName(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
    char *result = (char *)FSOUND_Sample_GetName(arg1);

    jstring jresult = 0;
	if(result) jresult = jenv->NewStringUTF(result);
    return jresult;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1GetLength(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
    unsigned int result = (unsigned int)FSOUND_Sample_GetLength(arg1);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1GetLoopPoints__J_3I_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jintArray jarg2, jintArray jarg3) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
	int *arg2 = 0;
	if(jarg2) {
	 	arg2 = (int *)jenv->GetIntArrayElements(jarg2, 0);
	}
	int *arg3 = 0;
	if(jarg3) {
	 	arg3 = (int *)jenv->GetIntArrayElements(jarg3, 0);
	}
    jboolean jresult = (jboolean)FSOUND_Sample_GetLoopPoints(arg1,arg2,arg3);
	if (arg2) jenv->ReleaseIntArrayElements(jarg2, (jint *)arg2, 0);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1GetLoopPoints__JLjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
	int *arg2 = 0;
	if(jarg2)
		arg2 = (int *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	int *arg3 = 0;
	if(jarg3)
		arg3 = (int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
    return (jboolean)FSOUND_Sample_GetLoopPoints(arg1,arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1GetDefaults__J_3I_3I_3I_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jintArray jarg2, jintArray jarg3, jintArray jarg4, jintArray jarg5) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
	int *arg2 = 0;
	if(jarg2) {
	 	arg2 = (int *)jenv->GetIntArrayElements(jarg2, 0);
	}
	int *arg3 = 0;
	if(jarg3) {
	 	arg3 = (int *)jenv->GetIntArrayElements(jarg3, 0);
	}
	int *arg4 = 0;
	if(jarg4) {
	 	arg4 = (int *)jenv->GetIntArrayElements(jarg4, 0);
	}
	int *arg5 = 0;
	if(jarg5) {
	 	arg5 = (int *)jenv->GetIntArrayElements(jarg5, 0);
	}
    jboolean jresult = (jboolean)FSOUND_Sample_GetDefaults(arg1,arg2,arg3,arg4,arg5);
	if (arg2) jenv->ReleaseIntArrayElements(jarg2, (jint *)arg2, 0);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	if (arg4) jenv->ReleaseIntArrayElements(jarg4, (jint *)arg4, 0);
	if (arg5) jenv->ReleaseIntArrayElements(jarg5, (jint *)arg5, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1GetDefaults__JLjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_, jobject jarg5, jint jarg5_) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
	int *arg2 = 0;
	if(jarg2)
		arg2 = (int *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	int *arg3 = 0;
	if(jarg3)
		arg3 = (int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	int *arg4 = 0;
	if(jarg4)
		arg4 = (int *)((char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_);
	int *arg5 = 0;
	if(jarg5)
		arg5 = (int *)((char *)jenv->GetDirectBufferAddress(jarg5)+jarg5_);
    return (jboolean)FSOUND_Sample_GetDefaults(arg1,arg2,arg3,arg4,arg5);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1GetDefaultsEx__J_3I_3I_3I_3I_3I_3I_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jintArray jarg2, jintArray jarg3, jintArray jarg4, jintArray jarg5, jintArray jarg6, jintArray jarg7, jintArray jarg8) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
	int *arg2 = 0;
	if(jarg2) {
	 	arg2 = (int *)jenv->GetIntArrayElements(jarg2, 0);
	}
	int *arg3 = 0;
	if(jarg3) {
	 	arg3 = (int *)jenv->GetIntArrayElements(jarg3, 0);
	}
	int *arg4 = 0;
	if(jarg4) {
	 	arg4 = (int *)jenv->GetIntArrayElements(jarg4, 0);
	}
	int *arg5 = 0;
	if(jarg5) {
	 	arg5 = (int *)jenv->GetIntArrayElements(jarg5, 0);
	}
	int *arg6 = 0;
	if(jarg6) {
	 	arg6 = (int *)jenv->GetIntArrayElements(jarg6, 0);
	}
	int *arg7 = 0;
	if(jarg7) {
	 	arg7 = (int *)jenv->GetIntArrayElements(jarg7, 0);
	}
	int *arg8 = 0;
	if(jarg8) {
	 	arg8 = (int *)jenv->GetIntArrayElements(jarg8, 0);
	}
    jboolean jresult = (jboolean)FSOUND_Sample_GetDefaultsEx(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
	if (arg2) jenv->ReleaseIntArrayElements(jarg2, (jint *)arg2, 0);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	if (arg4) jenv->ReleaseIntArrayElements(jarg4, (jint *)arg4, 0);
	if (arg5) jenv->ReleaseIntArrayElements(jarg5, (jint *)arg5, 0);
	if (arg6) jenv->ReleaseIntArrayElements(jarg6, (jint *)arg6, 0);
	if (arg7) jenv->ReleaseIntArrayElements(jarg7, (jint *)arg7, 0);
	if (arg8) jenv->ReleaseIntArrayElements(jarg8, (jint *)arg8, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1GetDefaultsEx__JLjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_, jobject jarg5, jint jarg5_, jobject jarg6, jint jarg6_, jobject jarg7, jint jarg7_, jobject jarg8, jint jarg8_) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
	int *arg2 = 0;
	if(jarg2)
		arg2 = (int *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	int *arg3 = 0;
	if(jarg3)
		arg3 = (int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	int *arg4 = 0;
	if(jarg4)
		arg4 = (int *)((char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_);
	int *arg5 = 0;
	if(jarg5)
		arg5 = (int *)((char *)jenv->GetDirectBufferAddress(jarg5)+jarg5_);
	int *arg6 = 0;
	if(jarg6)
		arg6 = (int *)((char *)jenv->GetDirectBufferAddress(jarg6)+jarg6_);
	int *arg7 = 0;
	if(jarg7)
		arg7 = (int *)((char *)jenv->GetDirectBufferAddress(jarg7)+jarg7_);
	int *arg8 = 0;
	if(jarg8)
		arg8 = (int *)((char *)jenv->GetDirectBufferAddress(jarg8)+jarg8_);
    return (jboolean)FSOUND_Sample_GetDefaultsEx(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1GetMode(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
    unsigned int result = (unsigned int)FSOUND_Sample_GetMode(arg1);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1GetMinMaxDistance__J_3F_3F(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloatArray jarg2, jfloatArray jarg3) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
	float *arg2 = 0;
	if(jarg2) {
		arg2 = jenv->GetFloatArrayElements(jarg2, 0);
	}
	float *arg3 = 0;
	if(jarg3) {
		arg3 = jenv->GetFloatArrayElements(jarg3, 0);
	}
    jboolean result = (jboolean)FSOUND_Sample_GetMinMaxDistance(arg1,arg2,arg3);
	if (arg2) jenv->ReleaseFloatArrayElements(jarg2, arg2, 0);
	if (arg3) jenv->ReleaseFloatArrayElements(jarg3, arg3, 0);
	return result;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Sample_1GetMinMaxDistance__JLjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
	float *arg2 = 0;
	if(jarg2)
		arg2 = (float *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	float *arg3 = 0;
	if(jarg3)
		arg3 = (float *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
    return (jboolean)FSOUND_Sample_GetMinMaxDistance(arg1,arg2,arg3);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1PlaySound(JNIEnv *jenv, jclass jcls, jint jarg1, jlong jarg2) {
    int arg1 = (int)jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    int result = (int)FSOUND_PlaySound(arg1,arg2);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1PlaySoundEx(JNIEnv *jenv, jclass jcls, jint jarg1, jlong jarg2, jlong jarg3, jboolean jarg4) {
    int arg1 = (int)jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    FSOUND_DSPUNIT *arg3 = *(FSOUND_DSPUNIT **)&jarg3;
    signed char arg4 = (signed char)jarg4;
    int result = (int)FSOUND_PlaySoundEx(arg1,arg2,arg3,arg4);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1StopSound(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    return (jboolean)FSOUND_StopSound(arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetFrequency(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2) {
    int arg1 = (int)jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)FSOUND_SetFrequency(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetVolume(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2) {
    int arg1 = (int)jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)FSOUND_SetVolume(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetVolumeAbsolute(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2) {
    int arg1 = (int)jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)FSOUND_SetVolumeAbsolute(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetPan(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2) {
    int arg1 = (int)jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)FSOUND_SetPan(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetSurround(JNIEnv *jenv, jclass jcls, jint jarg1, jboolean jarg2) {
    int arg1 = (int)jarg1;
    signed char arg2 = (signed char)jarg2;
    return (jboolean)FSOUND_SetSurround(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetMute(JNIEnv *jenv, jclass jcls, jint jarg1, jboolean jarg2) {
    int arg1 = (int)jarg1;
    signed char arg2 = (signed char)jarg2;
    return (jboolean)FSOUND_SetMute(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetPriority(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2) {
    int arg1 = (int)jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)FSOUND_SetPriority(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetReserved(JNIEnv *jenv, jclass jcls, jint jarg1, jboolean jarg2) {
    int arg1 = (int)jarg1;
    signed char arg2 = (signed char)jarg2;
    return (jboolean)FSOUND_SetReserved(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetPaused(JNIEnv *jenv, jclass jcls, jint jarg1, jboolean jarg2) {
    int arg1 = (int)jarg1;
    signed char arg2 = (signed char)jarg2;
    return (jboolean)FSOUND_SetPaused(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetLoopMode(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2) {
    int arg1 = (int)jarg1;
    unsigned int arg2 = (unsigned int)jarg2;
    return (jboolean)FSOUND_SetLoopMode(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1SetCurrentPosition(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2) {
    int arg1 = (int)jarg1;
    unsigned int arg2 = (unsigned int)jarg2;
    return (jboolean)FSOUND_SetCurrentPosition(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1SetAttributes__I_3F_3F(JNIEnv *jenv, jclass jcls, jint jarg1, jfloatArray jarg2, jfloatArray jarg3) {
    int arg1 = (int)jarg1;
	float *arg2 = 0;
	if(jarg2) {
		arg2 = jenv->GetFloatArrayElements(jarg2, 0);
	}
	float *arg3 = 0;
	if(jarg3) {
		arg3 = jenv->GetFloatArrayElements(jarg3, 0);
	}
    jboolean result = (jboolean)FSOUND_3D_SetAttributes(arg1,(float const *)arg2,(float const *)arg3);
	if (arg2) jenv->ReleaseFloatArrayElements(jarg2, arg2, 0);
	if (arg3) jenv->ReleaseFloatArrayElements(jarg3, arg3, 0);
	return result;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1SetAttributes__ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2I(JNIEnv *jenv, jclass jcls, jint jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_) {
    int arg1 = (int)jarg1;
	float *arg2 = 0;
	if(jarg2)
		arg2 = (float *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	float *arg3 = 0;
	if(jarg3)
		arg3 = (float *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
    return (jboolean)FSOUND_3D_SetAttributes(arg1,(float const *)arg2,(float const *)arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1SetMinMaxDistance(JNIEnv *jenv, jclass jcls, jint jarg1, jfloat jarg2, jfloat jarg3) {
    int arg1 = (int)jarg1;
    float arg2 = (float)jarg2;
    float arg3 = (float)jarg3;
    return (jboolean)FSOUND_3D_SetMinMaxDistance(arg1,arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1IsPlaying(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    return (jboolean)FSOUND_IsPlaying(arg1);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetFrequency(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    int result = (int)FSOUND_GetFrequency(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetVolume(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    int result = (int)FSOUND_GetVolume(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetAmplitude(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    int result = (int)FSOUND_GetAmplitude(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetPan(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    int result = (int)FSOUND_GetPan(arg1);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetSurround(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    return (jboolean)FSOUND_GetSurround(arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetMute(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    return (jboolean)FSOUND_GetMute(arg1);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetPriority(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    int result = (int)FSOUND_GetPriority(arg1);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetReserved(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    return (jboolean)FSOUND_GetReserved(arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetPaused(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    return (jboolean)FSOUND_GetPaused(arg1);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetLoopMode(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    unsigned int result = (unsigned int)FSOUND_GetLoopMode(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetCurrentPosition(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    unsigned int result = (unsigned int)FSOUND_GetCurrentPosition(arg1);
    return (jint)result;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetCurrentSample(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    FSOUND_SAMPLE *result = (FSOUND_SAMPLE *)FSOUND_GetCurrentSample(arg1);

    jlong jresult = 0;
    *(FSOUND_SAMPLE **)&jresult = result;
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetCurrentLevels__I_3F_3F(JNIEnv *jenv, jclass jcls, jint jarg1, jfloatArray jarg2, jfloatArray jarg3) {
    int arg1 = (int)jarg1;
	float *arg2 = 0;
	if(jarg2) {
		arg2 = jenv->GetFloatArrayElements(jarg2, 0);
	}
	float *arg3 = 0;
	if(jarg3) {
		arg3 = jenv->GetFloatArrayElements(jarg3, 0);
	}
    jboolean result = (jboolean)FSOUND_GetCurrentLevels(arg1,arg2,arg3);
	if (arg2) jenv->ReleaseFloatArrayElements(jarg2, (jfloat *)arg2, 0);
	if (arg3) jenv->ReleaseFloatArrayElements(jarg3, (jfloat *)arg3, 0);
	return result;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetCurrentLevels__ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2I(JNIEnv *jenv, jclass jcls, jint jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_) {
    int arg1 = (int)jarg1;
	float *arg2 = 0;
	if(jarg2)
		arg2 = (float *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	float *arg3 = 0;
	if(jarg3)
		arg3 = (float *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
    return (jboolean)FSOUND_GetCurrentLevels(arg1,arg2,arg3);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetNumSubChannels(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    int result = (int)FSOUND_GetNumSubChannels(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1GetSubChannel(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2) {
    int arg1 = (int)jarg1;
    int arg2 = (int)jarg2;
    int result = (int)FSOUND_GetSubChannel(arg1,arg2);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1GetAttributes__I_3F_3F(JNIEnv *jenv, jclass jcls, jint jarg1, jfloatArray jarg2, jfloatArray jarg3) {
    int arg1 = (int)jarg1;
	float *arg2 = 0;
	if(jarg2) {
		arg2 = jenv->GetFloatArrayElements(jarg2, 0);
	}
	float *arg3 = 0;
	if(jarg3) {
		arg3 = jenv->GetFloatArrayElements(jarg3, 0);
	}
    jboolean result = (jboolean)FSOUND_3D_GetAttributes(arg1,arg2,arg3);
	if (arg2) jenv->ReleaseFloatArrayElements(jarg2, (jfloat *)arg2, 0);
	if (arg3) jenv->ReleaseFloatArrayElements(jarg3, (jfloat *)arg3, 0);
	return result;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1GetAttributes__ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2I(JNIEnv *jenv, jclass jcls, jint jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_) {
    int arg1 = (int)jarg1;
	float *arg2 = 0;
	if(jarg2)
		arg2 = (float *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	float *arg3 = 0;
	if(jarg3)
		arg3 = (float *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
    return (jboolean)FSOUND_3D_GetAttributes(arg1,arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1GetMinMaxDistance__I_3F_3F(JNIEnv *jenv, jclass jcls, jint jarg1, jfloatArray jarg2, jfloatArray jarg3) {
    int arg1 = (int)jarg1;
	float *arg2 = 0;
	if(jarg2) {
		arg2 = jenv->GetFloatArrayElements(jarg2, 0);
	}
	float *arg3 = 0;
	if(jarg3) {
		arg3 = jenv->GetFloatArrayElements(jarg3, 0);
	}
    jboolean result = (jboolean)FSOUND_3D_GetMinMaxDistance(arg1,arg2,arg3);
	if (arg2) jenv->ReleaseFloatArrayElements(jarg2, arg2, 0);
	if (arg3) jenv->ReleaseFloatArrayElements(jarg3, arg3, 0);
	return result;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1GetMinMaxDistance__ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2I(JNIEnv *jenv, jclass jcls, jint jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_) {
    int arg1 = (int)jarg1;
	float *arg2 = 0;
	if(jarg2)
		arg2 = (float *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	float *arg3 = 0;
	if(jarg3)
		arg3 = (float *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
    return (jboolean)FSOUND_3D_GetMinMaxDistance(arg1,arg2,arg3);
}


JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1Listener_1SetAttributes___3F_3FFFFFFF(JNIEnv *jenv, jclass jcls, jfloatArray jarg1, jfloatArray jarg2, jfloat jarg3, jfloat jarg4, jfloat jarg5, jfloat jarg6, jfloat jarg7, jfloat jarg8) {
	float *arg1 = 0;
	if(jarg1) {
		arg1 = jenv->GetFloatArrayElements(jarg1, 0);
	}
	float *arg2 = 0;
	if(jarg2) {
		arg2 = jenv->GetFloatArrayElements(jarg2, 0);
	}
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    float arg6 = (float)jarg6;
    float arg7 = (float)jarg7;
    float arg8 = (float)jarg8;
    FSOUND_3D_Listener_SetAttributes(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
	if (arg1) jenv->ReleaseFloatArrayElements(jarg1, arg1, 0);
	if (arg2) jenv->ReleaseFloatArrayElements(jarg2, arg2, 0);
}
JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1Listener_1SetAttributes__Ljava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2IFFFFFF(JNIEnv *jenv, jclass jcls, jobject jarg1, jint jarg1_, jobject jarg2, jint jarg2_, jfloat jarg3, jfloat jarg4, jfloat jarg5, jfloat jarg6, jfloat jarg7, jfloat jarg8) {
	float *arg1 = 0;
	if(jarg1)
		arg1 = (float *)((char *)jenv->GetDirectBufferAddress(jarg1)+jarg1_);
	float *arg2 = 0;
	if(jarg2)
		arg2 = (float *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    float arg6 = (float)jarg6;
    float arg7 = (float)jarg7;
    float arg8 = (float)jarg8;
    FSOUND_3D_Listener_SetAttributes(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
}


JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1Listener_1GetAttributes___3F_3F_3F_3F_3F_3F_3F_3F(JNIEnv *jenv, jclass jcls, jfloatArray jarg1, jfloatArray jarg2, jfloatArray jarg3, jfloatArray jarg4, jfloatArray jarg5, jfloatArray jarg6, jfloatArray jarg7, jfloatArray jarg8) {
	float *arg1 = 0;
	if(jarg1) {
		arg1 = (float *)jenv->GetFloatArrayElements(jarg1, 0);
	}
	float *arg2 = 0;
	if(jarg2) {
		arg2 = (float *)jenv->GetFloatArrayElements(jarg2, 0);
	}
	float *arg3 = 0;
	if(jarg3) {
		arg3 = (float *)jenv->GetFloatArrayElements(jarg3, 0);
	}
	float *arg4 = 0;
	if(jarg4) {
		arg4 = (float *)jenv->GetFloatArrayElements(jarg4, 0);
	}
	float *arg5 = 0;
	if(jarg5) {
		arg5 = (float *)jenv->GetFloatArrayElements(jarg5, 0);
	}
	float *arg6 = 0;
	if(jarg6) {
		arg6 = (float *)jenv->GetFloatArrayElements(jarg6, 0);
	}
	float *arg7 = 0;
	if(jarg7) {
		arg7 = (float *)jenv->GetFloatArrayElements(jarg7, 0);
	}
	float *arg8 = 0;
	if(jarg8) {
		arg8 = (float *)jenv->GetFloatArrayElements(jarg8, 0);
	}
    FSOUND_3D_Listener_GetAttributes(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
	if (arg1) jenv->ReleaseFloatArrayElements(jarg1, (jfloat *)arg1, 0);
	if (arg2) jenv->ReleaseFloatArrayElements(jarg2, (jfloat *)arg2, 0);
	if (arg3) jenv->ReleaseFloatArrayElements(jarg3, (jfloat *)arg3, 0);
	if (arg4) jenv->ReleaseFloatArrayElements(jarg4, (jfloat *)arg4, 0);
	if (arg5) jenv->ReleaseFloatArrayElements(jarg5, (jfloat *)arg5, 0);
	if (arg6) jenv->ReleaseFloatArrayElements(jarg6, (jfloat *)arg6, 0);
	if (arg7) jenv->ReleaseFloatArrayElements(jarg7, (jfloat *)arg7, 0);
	if (arg8) jenv->ReleaseFloatArrayElements(jarg8, (jfloat *)arg8, 0);
}
JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1Listener_1GetAttributes__Ljava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2I(JNIEnv *jenv, jclass jcls, jobject jarg1, jint jarg1_, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_, jobject jarg5, jint jarg5_, jobject jarg6, jint jarg6_, jobject jarg7, jint jarg7_, jobject jarg8, jint jarg8_) {
	float *arg1 = 0;
	if(jarg1)
		arg1 = (float *)((char *)jenv->GetDirectBufferAddress(jarg1)+jarg1_);
	float *arg2 = 0;
	if(jarg2)
		arg2 = (float *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	float *arg3 = 0;
	if(jarg3)
		arg3 = (float *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	float *arg4 = 0;
	if(jarg4)
		arg4 = (float *)((char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_);
	float *arg5 = 0;
	if(jarg5)
		arg5 = (float *)((char *)jenv->GetDirectBufferAddress(jarg5)+jarg5_);
	float *arg6 = 0;
	if(jarg6)
		arg6 = (float *)((char *)jenv->GetDirectBufferAddress(jarg6)+jarg6_);
	float *arg7 = 0;
	if(jarg7)
		arg7 = (float *)((char *)jenv->GetDirectBufferAddress(jarg7)+jarg7_);
	float *arg8 = 0;
	if(jarg8)
		arg8 = (float *)((char *)jenv->GetDirectBufferAddress(jarg8)+jarg8_);
    FSOUND_3D_Listener_GetAttributes(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1Listener_1SetCurrent(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2) {
    int arg1 = (int)jarg1;
    int arg2 = (int)jarg2;
    FSOUND_3D_Listener_SetCurrent(arg1,arg2);
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1SetDopplerFactor(JNIEnv *jenv, jclass jcls, jfloat jarg1) {
    float arg1 = (float)jarg1;
    FSOUND_3D_SetDopplerFactor(arg1);
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1SetDistanceFactor(JNIEnv *jenv, jclass jcls, jfloat jarg1) {
    float arg1 = (float)jarg1;
    FSOUND_3D_SetDistanceFactor(arg1);
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_13D_1SetRolloffFactor(JNIEnv *jenv, jclass jcls, jfloat jarg1) {
    float arg1 = (float)jarg1;
    FSOUND_3D_SetRolloffFactor(arg1);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1FX_1Enable(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2) {
    int arg1 = (int)jarg1;
    unsigned int arg2 = (unsigned int)jarg2;
    int result = (int)FSOUND_FX_Enable(arg1,arg2);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1FX_1Disable(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    return (jboolean)FSOUND_FX_Disable(arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1FX_1SetChorus(JNIEnv *jenv, jclass jcls, jint jarg1, jfloat jarg2, jfloat jarg3, jfloat jarg4, jfloat jarg5, jint jarg6, jfloat jarg7, jint jarg8) {
    int arg1 = (int)jarg1;
    float arg2 = (float)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    int arg6 = (int)jarg6;
    float arg7 = (float)jarg7;
    int arg8 = (int)jarg8;
    return (jboolean)FSOUND_FX_SetChorus(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1FX_1SetCompressor(JNIEnv *jenv, jclass jcls, jint jarg1, jfloat jarg2, jfloat jarg3, jfloat jarg4, jfloat jarg5, jfloat jarg6, jfloat jarg7) {
    int arg1 = (int)jarg1;
    float arg2 = (float)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    float arg6 = (float)jarg6;
    float arg7 = (float)jarg7;
    return (jboolean)FSOUND_FX_SetCompressor(arg1,arg2,arg3,arg4,arg5,arg6,arg7);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1FX_1SetDistortion(JNIEnv *jenv, jclass jcls, jint jarg1, jfloat jarg2, jfloat jarg3, jfloat jarg4, jfloat jarg5, jfloat jarg6) {
    int arg1 = (int)jarg1;
    float arg2 = (float)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    float arg6 = (float)jarg6;
    return (jboolean)FSOUND_FX_SetDistortion(arg1,arg2,arg3,arg4,arg5,arg6);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1FX_1SetEcho(JNIEnv *jenv, jclass jcls, jint jarg1, jfloat jarg2, jfloat jarg3, jfloat jarg4, jfloat jarg5, jboolean jarg6) {
    int arg1 = (int)jarg1;
    float arg2 = (float)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    int arg6 = (int)jarg6;
    return (jboolean)FSOUND_FX_SetEcho(arg1,arg2,arg3,arg4,arg5,arg6);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1FX_1SetFlanger(JNIEnv *jenv, jclass jcls, jint jarg1, jfloat jarg2, jfloat jarg3, jfloat jarg4, jfloat jarg5, jint jarg6, jfloat jarg7, jint jarg8) {
    int arg1 = (int)jarg1;
    float arg2 = (float)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    int arg6 = (int)jarg6;
    float arg7 = (float)jarg7;
    int arg8 = (int)jarg8;
    return (jboolean)FSOUND_FX_SetFlanger(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1FX_1SetGargle(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2, jint jarg3) {
    int arg1 = (int)jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)FSOUND_FX_SetGargle(arg1,arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1FX_1SetI3DL2Reverb(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2, jint jarg3, jfloat jarg4, jfloat jarg5, jfloat jarg6, jint jarg7, jfloat jarg8, jint jarg9, jfloat jarg10, jfloat jarg11, jfloat jarg12, jfloat jarg13) {
    int arg1 = (int)jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    float arg6 = (float)jarg6;
    int arg7 = (int)jarg7;
    float arg8 = (float)jarg8;
    int arg9 = (int)jarg9;
    float arg10 = (float)jarg10;
    float arg11 = (float)jarg11;
    float arg12 = (float)jarg12;
    float arg13 = (float)jarg13;
    return (jboolean)FSOUND_FX_SetI3DL2Reverb(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1FX_1SetParamEQ(JNIEnv *jenv, jclass jcls, jint jarg1, jfloat jarg2, jfloat jarg3, jfloat jarg4) {
    int arg1 = (int)jarg1;
    float arg2 = (float)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    return (jboolean)FSOUND_FX_SetParamEQ(arg1,arg2,arg3,arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1FX_1SetWavesReverb(JNIEnv *jenv, jclass jcls, jint jarg1, jfloat jarg2, jfloat jarg3, jfloat jarg4, jfloat jarg5) {
    int arg1 = (int)jarg1;
    float arg2 = (float)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    return (jboolean)FSOUND_FX_SetWavesReverb(arg1,arg2,arg3,arg4,arg5);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1SetBufferSize(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    return (jboolean)FSOUND_Stream_SetBufferSize(arg1);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Open__Ljava_lang_String_2III(JNIEnv *jenv, jclass jcls, jstring jarg1, jint jarg2, jint jarg3, jint jarg4) {
    char *arg1 = 0;
	if (jarg1) {
		arg1 = getStringElements(jenv, jarg1);
    }

    unsigned int arg2 = (unsigned int)jarg2;
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;

    FSOUND_STREAM *result = (FSOUND_STREAM *)FSOUND_Stream_Open(arg1,arg2,arg3,arg4);

	releaseStringElements(jenv, jarg1, arg1);

    jlong jresult = 0;
    *(FSOUND_STREAM **)&jresult = result;
    return jresult;
}
JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Open__Ljava_nio_ByteBuffer_2IIII(JNIEnv *jenv, jclass jcls, jobject jarg1, jint jarg1_, jint jarg2, jint jarg3, jint jarg4) {
	char *arg1 = 0;
	if(jarg1)
		arg1 = (char *)jenv->GetDirectBufferAddress(jarg1)+jarg1_;
    unsigned int arg2 = (unsigned int)jarg2;
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    FSOUND_STREAM *result = (FSOUND_STREAM *)FSOUND_Stream_Open(arg1,arg2,arg3,arg4);

    jlong jresult = 0;
    *(FSOUND_STREAM **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Create(JNIEnv *jenv, jclass jcls, jobject jarg1, jint jarg2, jint jarg3, jint jarg4, jlong jarg5) {
	if(!jarg1) {
		ThrowException(jenv, NullPointerException, "null FSOUND_STREAMCALLBACK");
		return 0;
	}

	Callback *jStreamCreate = new Callback(jenv, jarg1);
    int arg2 = (int)jarg2;
    unsigned int arg3 = (unsigned int)jarg3;
    int arg4 = (int)jarg4;
	jStreamCreate->setUserData(jarg5);

    FSOUND_STREAM *result = (FSOUND_STREAM *)FSOUND_Stream_Create(StreamCallback,arg2,arg3,arg4,jStreamCreate);

    jlong jresult = 0;
    *(FSOUND_STREAM **)&jresult = result;
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Close(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    return (jboolean)FSOUND_Stream_Close(arg1);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Play(JNIEnv *jenv, jclass jcls, jint jarg1, jlong jarg2) {
    int arg1 = (int)jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    int result = (int)FSOUND_Stream_Play(arg1,arg2);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1PlayEx(JNIEnv *jenv, jclass jcls, jint jarg1, jlong jarg2, jlong jarg3, jboolean jarg4) {
    int arg1 = (int)jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    FSOUND_DSPUNIT *arg3 = *(FSOUND_DSPUNIT **)&jarg3;
    signed char arg4 = (signed char)jarg4;
	int result = (int)FSOUND_Stream_PlayEx(arg1,arg2,arg3,arg4);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Stop(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    return (jboolean)FSOUND_Stream_Stop(arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1SetPosition(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    unsigned int arg2 = (unsigned int)jarg2;
    return (jboolean)FSOUND_Stream_SetPosition(arg1,arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetPosition(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    unsigned int result = (unsigned int)FSOUND_Stream_GetPosition(arg1);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1SetTime(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)FSOUND_Stream_SetTime(arg1,arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetTime(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    int result = (int)FSOUND_Stream_GetTime(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetLength(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    int result = (int)FSOUND_Stream_GetLength(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetLengthMs(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    int result = (int)FSOUND_Stream_GetLengthMs(arg1);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1SetMode(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    unsigned int arg2 = (unsigned int)jarg2;
    return (jboolean)FSOUND_Stream_SetMode(arg1,arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetMode(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    unsigned int result = (unsigned int)FSOUND_Stream_GetMode(arg1);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1SetLoopPoints(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    unsigned int arg2 = (unsigned int)jarg2;
    unsigned int arg3 = (unsigned int)jarg3;
    return (jboolean)FSOUND_Stream_SetLoopPoints(arg1,arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1SetLoopCount(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)FSOUND_Stream_SetLoopCount(arg1,arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetOpenState(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    int result = (int)FSOUND_Stream_GetOpenState(arg1);
    return (jint)result;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetSample(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    FSOUND_SAMPLE *result = (FSOUND_SAMPLE *)FSOUND_Stream_GetSample(arg1);

    jlong jresult = 0;
    *(FSOUND_SAMPLE **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1CreateDSP(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg3, jlong jarg4) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
	Callback *dspCallback = new Callback(jenv, jarg2);
    int arg3 = (int)jarg3;
	dspCallback->setUserData(jarg4);

    FSOUND_DSPUNIT *result = (FSOUND_DSPUNIT *)FSOUND_Stream_CreateDSP(arg1, DspCallback, arg3, dspCallback);

    jlong jresult = 0;
    *(FSOUND_DSPUNIT **)&jresult = result;
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1SetEndCallback(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jlong jarg3) {
	FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    Callback *jEndCallback = new Callback(jenv, jarg2);
	jEndCallback->setUserData(jarg3);

    return (jboolean)FSOUND_Stream_SetEndCallback(arg1, StreamCallback, jEndCallback);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1SetSyncCallback(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jlong jarg3) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    Callback *jSyncCallback = new Callback(jenv, jarg2);
	jSyncCallback->setUserData(jarg3);

    return (jboolean)FSOUND_Stream_SetSyncCallback(arg1, StreamCallback, jSyncCallback);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1AddSyncPoint(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jstring jarg3) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    unsigned int arg2 = (unsigned int)jarg2;
    char *arg3 = 0;
	if (jarg3) {
		arg3 = getStringElements(jenv, jarg3);
    }
    FSOUND_SYNCPOINT *result = (FSOUND_SYNCPOINT *)FSOUND_Stream_AddSyncPoint(arg1,arg2,arg3);

	releaseStringElements(jenv, jarg3, arg3);

    jlong jresult = 0;
    *(FSOUND_SYNCPOINT **)&jresult = result;
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1DeleteSyncPoint(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_SYNCPOINT *arg1 = *(FSOUND_SYNCPOINT **)&jarg1;
    return (jboolean)FSOUND_Stream_DeleteSyncPoint(arg1);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetNumSyncPoints(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    int result = (int)FSOUND_Stream_GetNumSyncPoints(arg1);
    return (jint)result;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetSyncPoint(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    int arg2 = (int)jarg2;
    FSOUND_SYNCPOINT *result = (FSOUND_SYNCPOINT *)FSOUND_Stream_GetSyncPoint(arg1,arg2);

    jlong jresult = 0;
    *(FSOUND_SYNCPOINT **)&jresult = result;
    return jresult;
}

JNIEXPORT jstring JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetSyncPointInfo__J_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jintArray jarg2) {
    FSOUND_SYNCPOINT *arg1 = *(FSOUND_SYNCPOINT **)&jarg1;
	unsigned int *arg2 = 0;
	if(jarg2) {
	 	arg2 = (unsigned int *)jenv->GetIntArrayElements(jarg2, 0);
	}
	char *result = (char *)FSOUND_Stream_GetSyncPointInfo(arg1,arg2);

	if (arg2) jenv->ReleaseIntArrayElements(jarg2, (jint *)arg2, 0);
    jstring jresult = 0;
	if (result) jresult = jenv->NewStringUTF(result);
    return jresult;
}
JNIEXPORT jstring JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetSyncPointInfo__JLjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_) {
    FSOUND_SYNCPOINT *arg1 = *(FSOUND_SYNCPOINT **)&jarg1;
	unsigned int *arg2 = 0;
	if(jarg2)
		arg2 = (unsigned int *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	char *result = (char *)FSOUND_Stream_GetSyncPointInfo(arg1,arg2);

    jstring jresult = 0;
	if (result) jresult = jenv->NewStringUTF(result);
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1SetSubStream(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)FSOUND_Stream_SetSubStream(arg1,arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetNumSubStreams(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    int result = (int)FSOUND_Stream_GetNumSubStreams(arg1);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1SetSubStreamSentence__J_3II(JNIEnv *jenv, jclass jcls, jlong jarg1, jintArray jarg2, jint jarg3) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
	int *arg2 = 0;
	if(jarg2) {
	 	arg2 = (int *)jenv->GetIntArrayElements(jarg2, 0);
	}
    int arg3 = (int)jarg3;
    jboolean jresult = (jboolean)FSOUND_Stream_SetSubStreamSentence(arg1,(int const *)arg2,arg3);
	if (arg2) jenv->ReleaseIntArrayElements(jarg2, (jint *)arg2, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1SetSubStreamSentence__JLjava_nio_IntBuffer_2II(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jint jarg3) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
	int *arg2 = 0;
	if(jarg2)
		arg2 = (int *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
    int arg3 = (int)jarg3;
    return (jboolean)FSOUND_Stream_SetSubStreamSentence(arg1,(int const *)arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetNumTagFields__J_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jintArray jarg2) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
	int *arg2 = 0;
	if(jarg2) {
	 	arg2 = (int *)jenv->GetIntArrayElements(jarg2, 0);
	}
    jboolean jresult = (jboolean)FSOUND_Stream_GetNumTagFields(arg1,arg2);
	if (arg2) jenv->ReleaseIntArrayElements(jarg2, (jint *)arg2, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetNumTagFields__JLjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
	int *arg2 = 0;
	if(jarg2)
		arg2 = (int *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
    return (jboolean)FSOUND_Stream_GetNumTagFields(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetTagField__JI_3ILorg_jouvieje_Fmod_Misc_Pointer_2Lorg_jouvieje_Fmod_Misc_Pointer_2_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jintArray jarg3, jobject jarg4, jobject jarg5, jintArray jarg6) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    int arg2 = (int)jarg2;
	int *arg3 = 0;
	if(jarg3) {
	 	arg3 = (int *)jenv->GetIntArrayElements(jarg3, 0);
	}
    char *arg4;
    void *arg5;
	int *arg6 = 0;
	if(jarg6) {
	 	arg6 = (int *)jenv->GetIntArrayElements(jarg6, 0);
	}

    jboolean result = (jboolean)FSOUND_Stream_GetTagField(arg1, arg2, arg3, &arg4, &arg5, arg6);

	if(arg4 && jarg4) {
		jlong newAddress = 0;
		*(char **)&(newAddress) = arg4;
		setPointerAddress(jenv, jarg4, newAddress);
	}
	if(arg5 && jarg5) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg5;
		setPointerAddress(jenv, jarg5, newAddress);
	}

	if(arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	if(arg6) jenv->ReleaseIntArrayElements(jarg6, (jint *)arg6, 0);
	return result;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1GetTagField__JILjava_nio_IntBuffer_2ILorg_jouvieje_Fmod_Misc_Pointer_2Lorg_jouvieje_Fmod_Misc_Pointer_2Ljava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jobject jarg3, jint jarg3_, jobject jarg4, jobject jarg5, jobject jarg6, jint jarg6_) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    int arg2 = (int)jarg2;
	int *arg3 = 0;
	if(jarg3) {
		arg3 = (int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	}
    char *arg4;
    void *arg5;
	int *arg6 = 0;
	if(jarg6) {
		arg6 = (int *)((char *)jenv->GetDirectBufferAddress(jarg6)+jarg6_);
	}

    jboolean result = (jboolean)FSOUND_Stream_GetTagField(arg1, arg2, arg3, &arg4, &arg5, arg6);

	if(arg4 && jarg4) {
		jlong newAddress = 0;
		*(char **)&(newAddress) = arg4;
		setPointerAddress(jenv, jarg4, newAddress);
	}
	if(arg5 && jarg5) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg5;
		setPointerAddress(jenv, jarg5, newAddress);
	}

	return result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1FindTagField__JILjava_lang_String_2Lorg_jouvieje_Fmod_Misc_Pointer_2_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jstring jarg3, jobject jarg4, jintArray jarg5) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    int arg2 = (int)jarg2;
    char *arg3 = 0;
	if (jarg3) {
		arg3 = getStringElements(jenv, jarg3);
	}
	void *arg4;
	int *arg5 = 0;
	if(jarg5) {
	 	arg5 = (int *)jenv->GetIntArrayElements(jarg5, 0);
	}
    jboolean jresult = (jboolean)FSOUND_Stream_FindTagField(arg1,arg2,arg3,&arg4,arg5);

	releaseStringElements(jenv, jarg3, arg3);

	if(arg4 && jarg4) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg4;
		setPointerAddress(jenv, jarg4, newAddress);
	}

	if (arg5) jenv->ReleaseIntArrayElements(jarg5, (jint *)arg5, 0);

    return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1FindTagField__JILjava_lang_String_2Lorg_jouvieje_Fmod_Misc_Pointer_2Ljava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jstring jarg3, jobject jarg4, jobject jarg5, jint jarg5_) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    int arg2 = (int)jarg2;
    char *arg3 = 0;
	if (jarg3) {
		arg3 = getStringElements(jenv, jarg3);
	}
	void *arg4;
	int *arg5 = 0;
	if(jarg5) {
		arg5 = (int *)((char *)jenv->GetDirectBufferAddress(jarg5)+jarg5_);
	}
    jboolean jresult = (jboolean)FSOUND_Stream_FindTagField(arg1,arg2,arg3,&arg4,arg5);

	if(arg4 && jarg4) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg4;
		setPointerAddress(jenv, jarg4, newAddress);
	}

	releaseStringElements(jenv, jarg3, arg3);

    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Net_1SetProxy(JNIEnv *jenv, jclass jcls, jstring jarg1) {
    char *arg1 = 0;
	if (jarg1) {
		arg1 = getStringElements(jenv, jarg1);
    }
    signed char result = FSOUND_Stream_Net_SetProxy(arg1);

	releaseStringElements(jenv, jarg1, arg1);
    return (jboolean)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Net_1SetTimeout(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    signed char result = FSOUND_Stream_Net_SetTimeout(arg1);
	return (jboolean)result;
}

JNIEXPORT jstring JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Net_1GetLastServerStatus(JNIEnv *jenv, jclass jcls) {
    char *result = (char *)FSOUND_Stream_Net_GetLastServerStatus();

    jstring jresult = 0;
    if(result) jresult = jenv->NewStringUTF(result);
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Net_1SetBufferProperties(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2, jint jarg3) {
    int arg1 = (int)jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)FSOUND_Stream_Net_SetBufferProperties(arg1,arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Net_1GetBufferProperties___3I_3I_3I(JNIEnv *jenv, jclass jcls, jintArray jarg1, jintArray jarg2, jintArray jarg3) {
	int *arg1 = 0;
	if(jarg1) {
	 	arg1 = (int *)jenv->GetIntArrayElements(jarg1, 0);
	}
	int *arg2 = 0;
	if(jarg2) {
		arg2 = (int *)jenv->GetIntArrayElements(jarg2, 0);
	}
	int *arg3 = 0;
	if(jarg3) {
	 	arg3 = (int *)jenv->GetIntArrayElements(jarg3, 0);
	}
	jboolean jresult = (jboolean)FSOUND_Stream_Net_GetBufferProperties(arg1,arg2,arg3);
	if (arg1) jenv->ReleaseIntArrayElements(jarg1, (jint *)arg1, 0);
	if (arg2) jenv->ReleaseIntArrayElements(jarg2, (jint *)arg2, 0);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Net_1GetBufferProperties__Ljava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jobject jarg1, jint jarg1_, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_) {
	int *arg1 = 0;
	if(jarg1)
		arg1 = (int *)((char *)jenv->GetDirectBufferAddress(jarg1)+jarg1_);
	int *arg2 = 0;
	if(jarg2)
		arg2 = (int *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	int *arg3 = 0;
	if(jarg3)
		arg3 = (int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	return (jboolean)FSOUND_Stream_Net_GetBufferProperties(arg1,arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Net_1SetMetadataCallback(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jlong jarg3) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
    Callback *jMetadataCallback = new Callback(jenv, jarg2);
	jMetadataCallback->setUserData(jarg3);

    return (jboolean)FSOUND_Stream_Net_SetMetadataCallback(arg1, MetadataCallback, jMetadataCallback);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Net_1GetStatus__J_3I_3I_3I_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jintArray jarg2, jintArray jarg3, jintArray jarg4, jintArray jarg5) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
	int *arg2 = 0;
	if(jarg2) {
	 	arg2 = (int *)jenv->GetIntArrayElements(jarg2, 0);
	}
	int *arg3 = 0;
	if(jarg3) {
	 	arg3 = (int *)jenv->GetIntArrayElements(jarg3, 0);
	}
	int *arg4 = 0;
	if(jarg4) {
	 	arg4 = (int *)jenv->GetIntArrayElements(jarg4, 0);
	}
	unsigned int *arg5 = 0;
	if(jarg5) {
	 	arg5 = (unsigned int *)jenv->GetIntArrayElements(jarg5, 0);
	}
    jboolean jresult = (jboolean)FSOUND_Stream_Net_GetStatus(arg1,arg2,arg3,arg4,arg5);
	if (arg2) jenv->ReleaseIntArrayElements(jarg2, (jint *)arg2, 0);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	if (arg4) jenv->ReleaseIntArrayElements(jarg4, (jint *)arg4, 0);
	if (arg5) jenv->ReleaseIntArrayElements(jarg5, (jint *)arg5, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Stream_1Net_1GetStatus__JLjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_, jobject jarg5, jint jarg5_) {
    FSOUND_STREAM *arg1 = *(FSOUND_STREAM **)&jarg1;
	int *arg2 = 0;
	if(jarg2)
		arg2 = (int *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	int *arg3 = 0;
	if(jarg3)
		arg3 = (int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	int *arg4 = 0;
	if(jarg4)
		arg4 = (int *)((char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_);
	unsigned int *arg5 = 0;
	if(jarg5)
		arg5 = (unsigned int *)((char *)jenv->GetDirectBufferAddress(jarg5)+jarg5_);
    return (jboolean)FSOUND_Stream_Net_GetStatus(arg1,arg2,arg3,arg4,arg5);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1CD_1Play(JNIEnv *jenv, jclass jcls, jchar jarg1, jint jarg2) {
    char arg1 = (char)jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)FSOUND_CD_Play(arg1,arg2);
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1CD_1SetPlayMode(JNIEnv *jenv, jclass jcls, jchar jarg1, jbyte jarg2) {
    char arg1 = (char)jarg1;
    signed char arg2 = (signed char)jarg2;
    FSOUND_CD_SetPlayMode(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1CD_1Stop(JNIEnv *jenv, jclass jcls, jchar jarg1) {
    char arg1 = (char)jarg1;
    return (jboolean)FSOUND_CD_Stop(arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1CD_1SetPaused(JNIEnv *jenv, jclass jcls, jchar jarg1, jboolean jarg2) {
    char arg1 = (char)jarg1;
    signed char arg2 = (signed char)jarg2;
    return (jboolean)FSOUND_CD_SetPaused(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1CD_1SetVolume(JNIEnv *jenv, jclass jcls, jchar jarg1, jint jarg2) {
    char arg1 = (char)jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)FSOUND_CD_SetVolume(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1CD_1SetTrackTime(JNIEnv *jenv, jclass jcls, jchar jarg1, jint jarg2) {
    char arg1 = (char)jarg1;
    unsigned int arg2 = (unsigned int)jarg2;
    return (jboolean)FSOUND_CD_SetTrackTime(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1CD_1GetPaused(JNIEnv *jenv, jclass jcls, jchar jarg1) {
    char arg1 = (char)jarg1;
    return (jboolean)FSOUND_CD_GetPaused(arg1);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1CD_1GetTrack(JNIEnv *jenv, jclass jcls, jchar jarg1) {
    char arg1 = (char)jarg1;
    int result = (int)FSOUND_CD_GetTrack(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1CD_1GetNumTracks(JNIEnv *jenv, jclass jcls, jchar jarg1) {
    char arg1 = (char)jarg1;
    int result = (int)FSOUND_CD_GetNumTracks(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1CD_1GetVolume(JNIEnv *jenv, jclass jcls, jchar jarg1) {
    char arg1 = (char)jarg1;
    int result = (int)FSOUND_CD_GetVolume(arg1);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1CD_1OpenTray(JNIEnv *jenv, jclass jcls, jchar jarg1, jboolean jarg2) {
    return (jboolean)false;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1CD_1GetTrackLength(JNIEnv *jenv, jclass jcls, jchar jarg1, jint jarg2) {
    char arg1 = (char)jarg1;
    int arg2 = (int)jarg2;
    int result = (int)FSOUND_CD_GetTrackLength(arg1,arg2);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1CD_1GetTrackTime(JNIEnv *jenv, jclass jcls, jchar jarg1) {
    char arg1 = (char)jarg1;
    int result = (int)FSOUND_CD_GetTrackTime(arg1);
    return (jint)result;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1Create(JNIEnv *jenv, jclass jcls, jobject jarg1, jint jarg2, jlong jarg3) {
	int arg2 = (int)jarg2;
	FSOUND_DSPUNIT *result = 0;
	if(jarg1 != 0)
	{
		Callback *dspCallback = new Callback(jenv, jarg1);
		dspCallback->setUserData(jarg3);

		result = (FSOUND_DSPUNIT *)FSOUND_DSP_Create(DspCallback, arg2, dspCallback);
	}
	else
	{
		result = (FSOUND_DSPUNIT *)FSOUND_DSP_Create(NULL, arg2, NULL);
	}

	jlong jresult = 0;
	*(FSOUND_DSPUNIT **)&jresult = result;
	return jresult;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1Free(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_DSPUNIT *arg1 = *(FSOUND_DSPUNIT **)&jarg1;
    FSOUND_DSP_Free(arg1);
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1SetPriority(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_DSPUNIT *arg1 = *(FSOUND_DSPUNIT **)&jarg1;
    int arg2 = (int)jarg2;
    FSOUND_DSP_SetPriority(arg1,arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1GetPriority(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_DSPUNIT *arg1 = *(FSOUND_DSPUNIT **)&jarg1;
    int result = (int)FSOUND_DSP_GetPriority(arg1);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1SetActive(JNIEnv *jenv, jclass jcls, jlong jarg1, jboolean jarg2){
    FSOUND_DSPUNIT *arg1 = *(FSOUND_DSPUNIT **)&jarg1;
    signed char arg2 = (signed char)jarg2;
	FSOUND_DSP_SetActive(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1GetActive(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_DSPUNIT *arg1 = *(FSOUND_DSPUNIT **)&jarg1;
    return (jboolean)FSOUND_DSP_GetActive(arg1);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1GetClearUnit(JNIEnv *jenv, jclass jcls) {
    FSOUND_DSPUNIT *result = (FSOUND_DSPUNIT *)FSOUND_DSP_GetClearUnit();

    jlong jresult = 0;
    *(FSOUND_DSPUNIT **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1GetSFXUnit(JNIEnv *jenv, jclass jcls) {
    FSOUND_DSPUNIT *result = (FSOUND_DSPUNIT *)FSOUND_DSP_GetSFXUnit();

    jlong jresult = 0;
    *(FSOUND_DSPUNIT **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1GetMusicUnit(JNIEnv *jenv, jclass jcls) {
    FSOUND_DSPUNIT *result = (FSOUND_DSPUNIT *)FSOUND_DSP_GetMusicUnit();

    jlong jresult = 0;
    *(FSOUND_DSPUNIT **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1GetFFTUnit(JNIEnv *jenv, jclass jcls) {
    FSOUND_DSPUNIT *result = (FSOUND_DSPUNIT *)FSOUND_DSP_GetFFTUnit();

    jlong jresult = 0;
    *(FSOUND_DSPUNIT **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1GetClipAndCopyUnit(JNIEnv *jenv, jclass jcls) {
    FSOUND_DSPUNIT *result = (FSOUND_DSPUNIT *)FSOUND_DSP_GetClipAndCopyUnit();

    jlong jresult = 0;
    *(FSOUND_DSPUNIT **)&jresult = result;
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1MixBuffers(JNIEnv *jenv, jclass jcls, jobject jarg1, jint jarg1_, jobject jarg2, jint jarg2_, jint jarg3, jint jarg4, jint jarg5, jint jarg6, jint jarg7) {
	void *arg1 = 0;
	if(jarg1)
		arg1 = (char *)jenv->GetDirectBufferAddress(jarg1)+jarg1_;
	void *arg2 = 0;
	if(jarg2)
		arg2 = (char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_;
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    int arg5 = (int)jarg5;
    int arg6 = (int)jarg6;
    unsigned int arg7 = (unsigned int)jarg7;
    return (jboolean)FSOUND_DSP_MixBuffers(arg1,arg2,arg3,arg4,arg5,arg6,arg7);
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1ClearMixBuffer(JNIEnv *jenv, jclass jcls) {
    FSOUND_DSP_ClearMixBuffer();
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1GetBufferLength(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_DSP_GetBufferLength();
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1GetBufferLengthTotal(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_DSP_GetBufferLengthTotal();
    return (jint)result;
}
JNIEXPORT jobject JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1DSP_1GetSpectrum(JNIEnv *jenv, jclass jcls) {
	float *result = (float *)FSOUND_DSP_GetSpectrum();

	if (result) {
		long capacity = 512 * sizeof(float);
		return jenv->NewDirectByteBuffer((void *)result, (jlong)capacity);
	}
	else
		return NULL;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Reverb_1SetProperties(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    return (jboolean)FSOUND_Reverb_SetProperties((FSOUND_REVERB_PROPERTIES const *)arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Reverb_1GetProperties(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    return (jboolean)FSOUND_Reverb_GetProperties(arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Reverb_1SetChannelProperties(JNIEnv *jenv, jclass jcls, jint jarg1, jlong jarg2) {
    int arg1 = (int)jarg1;
    FSOUND_REVERB_CHANNELPROPERTIES *arg2 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg2;
    return (jboolean)FSOUND_Reverb_SetChannelProperties(arg1,(FSOUND_REVERB_CHANNELPROPERTIES const *)arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Reverb_1GetChannelProperties(JNIEnv *jenv, jclass jcls, jint jarg1, jlong jarg2) {
    int arg1 = (int)jarg1;
    FSOUND_REVERB_CHANNELPROPERTIES *arg2 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg2;
    return (jboolean)FSOUND_Reverb_GetChannelProperties(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Record_1SetDriver(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    return (jboolean)FSOUND_Record_SetDriver(arg1);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Record_1GetNumDrivers(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_Record_GetNumDrivers();
    return (jint)result;
}

JNIEXPORT jstring JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Record_1GetDriverName(JNIEnv *jenv, jclass jcls, jint jarg1) {
    int arg1 = (int)jarg1;
    char *result = (char *)FSOUND_Record_GetDriverName(arg1);

    jstring jresult = 0;
	if(result) jresult = jenv->NewStringUTF(result);
    return jresult;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Record_1GetDriver(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_Record_GetDriver();
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Record_1StartSample(JNIEnv *jenv, jclass jcls, jlong jarg1, jboolean jarg2) {
    FSOUND_SAMPLE *arg1 = *(FSOUND_SAMPLE **)&jarg1;
	signed char arg2 = (signed char)jarg2;
    return (jboolean)FSOUND_Record_StartSample(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Record_1Stop(JNIEnv *jenv, jclass jcls) {
    return (jboolean)FSOUND_Record_Stop();
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FSOUND_1Record_1GetPosition(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_Record_GetPosition();
    return (jint)result;
}

							/**************
							 * FMUSIC API *
							 **************/

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1LoadSong(JNIEnv *jenv, jclass jcls, jstring jarg1) {
    jlong jresult = 0;
    char *arg1 = 0;
	if (jarg1) {
		arg1 = getStringElements(jenv, jarg1);
    }
    FMUSIC_MODULE *result = (FMUSIC_MODULE *)FMUSIC_LoadSong(arg1);

	releaseStringElements(jenv, jarg1, arg1);

    *(FMUSIC_MODULE **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1LoadSongEx__Ljava_lang_String_2III_3II(JNIEnv *jenv, jclass jcls, jstring jarg1, jint jarg2, jint jarg3, jint jarg4, jintArray jarg5, jint jarg6) {
    jlong jresult = 0;
    char *arg1 = 0;
	if (jarg1) {
		arg1 = getStringElements(jenv, jarg1);
    }
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    unsigned int arg4 = (unsigned int)jarg4;
	int *arg5 = 0;
	if(jarg5) {
	 	arg5 = (int *)jenv->GetIntArrayElements(jarg5, 0);
	}
    int arg6 = (int)jarg6;
    FMUSIC_MODULE *result = (FMUSIC_MODULE *)FMUSIC_LoadSongEx(arg1,arg2,arg3,arg4,(int const *)arg5,arg6);

	releaseStringElements(jenv, jarg1, arg1);

    *(FMUSIC_MODULE **)&jresult = result;
	if (arg5) jenv->ReleaseIntArrayElements(jarg5, (jint *)arg5, 0);
    return jresult;
}
JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1LoadSongEx__Ljava_lang_String_2IIILjava_nio_IntBuffer_2II(JNIEnv *jenv, jclass jcls, jstring jarg1, jint jarg2, jint jarg3, jint jarg4, jobject jarg5, jint jarg5_, jint jarg6) {
    jlong jresult = 0;
    char *arg1 = 0;
	if (jarg1) {
		arg1 = getStringElements(jenv, jarg1);
    }
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    unsigned int arg4 = (unsigned int)jarg4;
	int *arg5 = 0;
	if(jarg5)
		arg5 = (int *)((char *)jenv->GetDirectBufferAddress(jarg5)+jarg5_);
    int arg6 = (int)jarg6;
    FMUSIC_MODULE *result = (FMUSIC_MODULE *)FMUSIC_LoadSongEx(arg1,arg2,arg3,arg4,(int const *)arg5,arg6);

	releaseStringElements(jenv, jarg1, arg1);

    *(FMUSIC_MODULE **)&jresult = result;
    return jresult;
}
JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1LoadSongEx__Ljava_nio_ByteBuffer_2IIII_3II(JNIEnv *jenv, jclass jcls, jobject jarg1, jint jarg1_, jint jarg2, jint jarg3, jint jarg4, jintArray jarg5, jint jarg6) {
    jlong jresult = 0;
	char *arg1 = 0;
	if(jarg1)
		arg1 = (char *)jenv->GetDirectBufferAddress(jarg1)+jarg1_;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    unsigned int arg4 = (unsigned int)jarg4;
	int *arg5 = 0;
	if(jarg5) {
	 	arg5 = (int *)jenv->GetIntArrayElements(jarg5, 0);
	}
    int arg6 = (int)jarg6;
    FMUSIC_MODULE *result = (FMUSIC_MODULE *)FMUSIC_LoadSongEx(arg1,arg2,arg3,arg4,(int const *)arg5,arg6);

    *(FMUSIC_MODULE **)&jresult = result;
	if (arg5) jenv->ReleaseIntArrayElements(jarg5, (jint *)arg5, 0);
    return jresult;
}
JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1LoadSongEx__Ljava_nio_ByteBuffer_2IIIILjava_nio_IntBuffer_2II(JNIEnv *jenv, jclass jcls, jobject jarg1, jint jarg1_, jint jarg2, jint jarg3, jint jarg4, jobject jarg5, jint jarg5_, jint jarg6) {
    jlong jresult = 0;
	char *arg1 = 0;
	if(jarg1)
		arg1 = (char *)jenv->GetDirectBufferAddress(jarg1)+jarg1_;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    unsigned int arg4 = (unsigned int)jarg4;
	int *arg5 = 0;
	if(jarg5)
		arg5 = (int *)((char *)jenv->GetDirectBufferAddress(jarg5)+jarg5_);
    int arg6 = (int)jarg6;
    FMUSIC_MODULE *result = (FMUSIC_MODULE *)FMUSIC_LoadSongEx(arg1,arg2,arg3,arg4,(int const *)arg5,arg6);

    *(FMUSIC_MODULE **)&jresult = result;
    return jresult;
}


JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetOpenState(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetOpenState(arg1);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1FreeSong(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    return (jboolean)FMUSIC_FreeSong(arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1PlaySong(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    return (jboolean)FMUSIC_PlaySong(arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1StopSong(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    return (jboolean)FMUSIC_StopSong(arg1);
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1StopAllSongs(JNIEnv *jenv, jclass jcls) {
    FMUSIC_StopAllSongs();
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1SetZxxCallback(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
	if(cZxxCallback) {
		delete cZxxCallback;
		cZxxCallback = NULL;
	}
	if(jarg2)
	    cZxxCallback = new Callback(jenv, jarg2);
    return (jboolean)FMUSIC_SetZxxCallback(arg1, ZxxCallback);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1SetRowCallback(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg3) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
	if(cRowCallback) {
		delete cRowCallback;
		cRowCallback = NULL;
	}
	if(jarg2)
	    cRowCallback = new Callback(jenv, jarg2);
    int arg3 = (int)jarg3;
    return (jboolean)FMUSIC_SetRowCallback(arg1, RowCallback, arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1SetOrderCallback(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg3) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
	if(cOrderCallback) {
		delete cOrderCallback;
		cOrderCallback = NULL;
	}
	if(jarg2)
	    cOrderCallback = new Callback(jenv, jarg2);
    int arg3 = (int)jarg3;
    return (jboolean)FMUSIC_SetOrderCallback(arg1, OrderCallback, arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1SetInstCallback(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg3) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
	if(cInstCallback) {
		delete cInstCallback;
		cInstCallback = NULL;
	}
	if(jarg2)
	    cInstCallback = new Callback(jenv, jarg2);
    int arg3 = (int)jarg3;
    return (jboolean)FMUSIC_SetInstCallback(arg1, InstCallback, arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1SetSample(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jlong jarg3) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int arg2 = (int)jarg2;
    FSOUND_SAMPLE *arg3 = *(FSOUND_SAMPLE **)&jarg3;
    return (jboolean)FMUSIC_SetSample(arg1,arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1SetUserData(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    void *arg2 = *(void **)&jarg2;
    return (jboolean)FMUSIC_SetUserData(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1OptimizeChannels(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)FMUSIC_OptimizeChannels(arg1,arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1SetReverb(JNIEnv *jenv, jclass jcls, jboolean jarg1) {
    signed char arg1 = (signed char)jarg1;
    return (jboolean)FMUSIC_SetReverb(arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1SetLooping(JNIEnv *jenv, jclass jcls, jlong jarg1, jboolean jarg2) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    signed char arg2 = (signed char)jarg2;
    return (jboolean)FMUSIC_SetLooping(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1SetOrder(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)FMUSIC_SetOrder(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1SetPaused(JNIEnv *jenv, jclass jcls, jlong jarg1, jboolean jarg2) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    signed char arg2 = (signed char)jarg2;
    return (jboolean)FMUSIC_SetPaused(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1SetMasterVolume(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)FMUSIC_SetMasterVolume(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1SetMasterSpeed(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    float arg2 = (float)jarg2;
    return (jboolean)FMUSIC_SetMasterSpeed(arg1,arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1SetPanSeperation(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    float arg2 = (float)jarg2;
    return (jboolean)FMUSIC_SetPanSeperation(arg1,arg2);
}

JNIEXPORT jstring JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetName(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    jstring jresult = 0;
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    char *result = (char *)FMUSIC_GetName(arg1);

	if(result) jresult = jenv->NewStringUTF(result);
    return jresult;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetType(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetType(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetNumOrders(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetNumOrders(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetNumPatterns(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetNumPatterns(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetNumInstruments(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetNumInstruments(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetNumSamples(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetNumSamples(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetNumChannels(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetNumChannels(arg1);
    return (jint)result;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetSample(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int arg2 = (int)jarg2;
    FSOUND_SAMPLE *result = (FSOUND_SAMPLE *)FMUSIC_GetSample(arg1,arg2);

    jlong jresult = 0;
    *(FSOUND_SAMPLE **)&jresult = result;
    return jresult;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetPatternLength(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int arg2 = (int)jarg2;
    int result = (int)FMUSIC_GetPatternLength(arg1,arg2);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1IsFinished(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    return (jboolean)FMUSIC_IsFinished(arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1IsPlaying(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    return (jboolean)FMUSIC_IsPlaying(arg1);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetMasterVolume(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetMasterVolume(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetGlobalVolume(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetGlobalVolume(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetOrder(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetOrder(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetPattern(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetPattern(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetSpeed(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetSpeed(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetBPM(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetBPM(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetRow(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetRow(arg1);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetPaused(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    return (jboolean)FMUSIC_GetPaused(arg1);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetTime(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int result = (int)FMUSIC_GetTime(arg1);
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetRealChannel(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    int arg2 = (int)jarg2;
    int result = (int)FMUSIC_GetRealChannel(arg1,arg2);
    return (jint)result;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMUSIC_1GetUserData(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMUSIC_MODULE *arg1 = *(FMUSIC_MODULE **)&jarg1;
    void *result = (void *)FMUSIC_GetUserData(arg1);

    jlong jresult = 0;
    *(void **)&jresult = result;
    return jresult;
}

							/***************
							 * fmod_errors *
							 ***************/

JNIEXPORT jstring JNICALL Java_org_jouvieje_Fmod_FmodJNI_FMOD_1ErrorString(JNIEnv *jenv, jclass jcls, jint arg1) {
	char *result = FMOD_ErrorString((int)arg1);

    jstring jresult = 0;
	if(result) jresult = jenv->NewStringUTF(result);
    return jresult;
}
