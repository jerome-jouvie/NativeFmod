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

#include "org_jouvieje_Fmod_InitDynJNI.h"
#include "org_jouvieje_FmodDyn_FmodDynJNI.h"
#include "fmoddyn.h"
#include "Utils.h"
#include "Pointer.h"
#include "JavaObject.h"
#include "Callback.h"
#include "CallbackStorer.h"
#pragma comment(lib,"fmodvc.lib")

									/*Callback*/

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_InitDynJNI_attachJavaVM(JNIEnv *jenv, jclass jcls) {
	Callback::connectMethods(jenv);
}

							/*************
							 * fmoddyn.h *
							 *************/

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1CreateInstance(JNIEnv *jenv, jclass jcls, jstring jarg1) {
    char *arg1 = 0;
	if (jarg1) {
		arg1 = getStringElements(jenv, jarg1);
    }
    FMOD_INSTANCE *result = (FMOD_INSTANCE *)FMOD_CreateInstance(arg1);

	releaseStringElements(jenv, jarg1, arg1);

    jlong jresult = 0;
    *(FMOD_INSTANCE **)&jresult = result;
    return jresult;
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1FreeInstance(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMOD_FreeInstance(arg1);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetOutput(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_SetOutput(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetDriver(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_SetDriver(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetMixer(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_SetMixer(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetBufferSize(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_SetBufferSize(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetHWND(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    void *arg2 = *(void **)&jarg2;
    return (jboolean)(arg1)->FSOUND_SetHWND(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetMinHardwareChannels(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_SetMinHardwareChannels(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetMaxHardwareChannels(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_SetMaxHardwareChannels(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetMemorySystem(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jint jarg3, jobject jarg4, jobject jarg5, jobject jarg6) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg3 = (int)jarg3;

	if(arg3 > 0){
		void *arg2 = 0;
		if(jarg2) {
			jobject jarg2Global = jenv->NewGlobalRef(jarg2);
			arg2 = (char *)jenv->GetDirectBufferAddress(jarg2Global)+jarg2_;
		}
		return (jboolean)(arg1)->FSOUND_SetMemorySystem(arg2, arg3, NULL, NULL, NULL);
	}
	else if(jarg4 && jarg5 && jarg6) {
		if(allocCallback) {
			delete allocCallback;
			allocCallback = NULL;
		}
		allocCallback = new Callback(jenv, jarg4);
		if(reallocCallback) {
			delete reallocCallback;
			reallocCallback = NULL;
		}
		reallocCallback = new Callback(jenv, jarg5);
		if(freeCallback) {
			delete freeCallback;
			freeCallback = NULL;
		}
		freeCallback = new Callback(jenv, jarg6);

		return (jboolean)(arg1)->FSOUND_SetMemorySystem(NULL, 0, AllocCallback, ReallocCallback, FreeCallback);
	}
	else {
		return (jboolean)false;
	}
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Init(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3, jint jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    unsigned int arg4 = (unsigned int)jarg4;
    return (jboolean)(arg1)->FSOUND_Init(arg2,arg3,arg4);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Close(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    (arg1)->FSOUND_Close();
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Update(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    (arg1)->FSOUND_Update();
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetSpeakerMode(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    unsigned int arg2 = (unsigned int)jarg2;
    (arg1)->FSOUND_SetSpeakerMode(arg2);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetSFXMasterVolume(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    (arg1)->FSOUND_SetSFXMasterVolume(arg2);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetPanSeperation(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    float arg2 = (float)jarg2;
    (arg1)->FSOUND_SetPanSeperation(arg2);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1File_1SetCallbacks(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jobject jarg3, jobject jarg4, jobject jarg5, jobject jarg6) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
	if(cOpenCallback) {
		delete cOpenCallback;
		cOpenCallback = NULL;
	}
	if(jarg2)
		cOpenCallback = new Callback(jenv, jarg2);
	if(cCloseCallback) {
		delete cCloseCallback;
		cCloseCallback = NULL;
	}
	if(jarg3)
	   cCloseCallback = new Callback(jenv, jarg3);
	if(cReadCallback) {
		delete cReadCallback;
		cReadCallback = NULL;
	}
	if(jarg4)
	    cReadCallback = new Callback(jenv, jarg4);
	if(cSeekCallback) {
		delete cSeekCallback;
		cSeekCallback = NULL;
	}
	if(jarg5)
	    cSeekCallback = new Callback(jenv, jarg5);
	if(cTellCallback) {
		delete cTellCallback;
		cTellCallback = NULL;
	}
	if(jarg6)
	    cTellCallback = new Callback(jenv, jarg6);

	//TODO add management for NULL callbacks (use default callbacks)
    arg1->FSOUND_File_SetCallbacks(cOpenCallback ? OpenCallback : NULL,
		cCloseCallback ? CloseCallback : NULL,
		cReadCallback ? ReadCallback : NULL,
		cSeekCallback ? SeekCallback : NULL,
		cTellCallback ? TellCallback : NULL);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetError(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_GetError();
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetVersion(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jfloat)(arg1)->FSOUND_GetVersion();
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetOutput(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_GetOutput();
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetOutputHandle(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    void  *result = (void *)(arg1)->FSOUND_GetOutputHandle();

    jlong jresult = 0;
    *(void **)&jresult = result;
    return jresult;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetDriver(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_GetDriver();
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetMixer(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_GetMixer();
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetNumDrivers(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_GetNumDrivers();
}

JNIEXPORT jstring JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetDriverName(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    char *result = (char *)(arg1)->FSOUND_GetDriverName(arg2);

    jstring jresult = 0;
	if(result) jresult = jenv->NewStringUTF(result);
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetDriverCaps__JI_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jintArray jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
	unsigned int *arg3 = 0;
	if(jarg3) {
	 	arg3 = (unsigned int *)jenv->GetIntArrayElements(jarg3, 0);
	}
    jboolean jresult = (jboolean)(arg1)->FSOUND_GetDriverCaps(arg2,arg3);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetDriverCaps__JILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jobject jarg3, jint jarg3_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
	unsigned int *arg3 = 0;
	if(jarg3)
		arg3 = (unsigned int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
    return (jboolean)(arg1)->FSOUND_GetDriverCaps(arg2,arg3);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetOutputRate(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_GetOutputRate();
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetMaxChannels(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_GetMaxChannels();
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetMaxSamples(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_GetMaxSamples();
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetSpeakerMode(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    unsigned int result = (unsigned int)(arg1)->FSOUND_GetSpeakerMode();
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetSFXMasterVolume(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_GetSFXMasterVolume();
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetNumHWChannels__J_3I_3I_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jintArray jarg2, jintArray jarg3, jintArray jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
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
    jboolean jresult = (jboolean)(arg1)->FSOUND_GetNumHWChannels(arg2,arg3,arg4);
	if (arg2) jenv->ReleaseIntArrayElements(jarg2, (jint *)arg2, 0);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	if (arg4) jenv->ReleaseIntArrayElements(jarg4, (jint *)arg4, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetNumHWChannels__JLjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
	int *arg2 = 0;
	if(jarg2)
		arg2 = (int *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	int *arg3 = 0;
	if(jarg3)
		arg3 = (int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	int *arg4 = 0;
	if(jarg4)
		arg4 = (int *)((char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_);
    return (jboolean)(arg1)->FSOUND_GetNumHWChannels(arg2,arg3,arg4);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetChannelsPlaying(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_GetChannelsPlaying();
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetCPUUsage(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jfloat)(arg1)->FSOUND_GetCPUUsage();
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetMemoryStats__J_3I_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jintArray jarg2, jintArray jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
	unsigned int *arg2 = 0;
	if(jarg2) {
 		arg2 = (unsigned int *)jenv->GetIntArrayElements(jarg2, 0);
	}
	unsigned int *arg3 = 0;
	if(jarg3) {
	 	arg3 = (unsigned int *)jenv->GetIntArrayElements(jarg3, 0);
	}
    (arg1)->FSOUND_GetMemoryStats(arg2,arg3);
	if (arg2) jenv->ReleaseIntArrayElements(jarg2, (jint *)arg2, 0);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
}
JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetMemoryStats__JLjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
	unsigned int *arg2 = 0;
	if(jarg2)
		arg2 = (unsigned int *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	unsigned int *arg3 = 0;
	if(jarg3)
		arg3 = (unsigned int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
    (arg1)->FSOUND_GetMemoryStats(arg2,arg3);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1Load__JILjava_lang_String_2III(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jstring jarg3, jint jarg4, jint jarg5, jint jarg6) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    char *arg3 = 0;
	if (jarg3) {
		arg3 = getStringElements(jenv, jarg3);
    }
    unsigned int arg4 = (unsigned int)jarg4;
    int arg5 = (int)jarg5;
    int arg6 = (int)jarg6;
    FSOUND_SAMPLE *result = (FSOUND_SAMPLE *)(arg1)->FSOUND_Sample_Load(arg2,arg3,arg4,arg5,arg6);

	releaseStringElements(jenv, jarg3, arg3);

    jlong jresult = 0;
    *(FSOUND_SAMPLE **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1Load__JILjava_nio_ByteBuffer_2IIII(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jobject jarg3, jint jarg3_, jint jarg4, jint jarg5, jint jarg6) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    char *arg3 = 0;
	if (jarg3) {
		arg3 = (char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_;
    }
    unsigned int arg4 = (unsigned int)jarg4;
    int arg5 = (int)jarg5;
    int arg6 = (int)jarg6;
    FSOUND_SAMPLE *result = (FSOUND_SAMPLE *)(arg1)->FSOUND_Sample_Load(arg2,arg3,arg4,arg5,arg6);

    jlong jresult = 0;
    *(FSOUND_SAMPLE **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1Alloc(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3, jint jarg4, jint jarg5, jint jarg6, jint jarg7, jint jarg8) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    unsigned int arg4 = (unsigned int)jarg4;
    int arg5 = (int)jarg5;
    int arg6 = (int)jarg6;
    int arg7 = (int)jarg7;
    int arg8 = (int)jarg8;
    FSOUND_SAMPLE *result = (FSOUND_SAMPLE *)(arg1)->FSOUND_Sample_Alloc(arg2,arg3,arg4,arg5,arg6,arg7,arg8);

    jlong jresult = 0;
    *(FSOUND_SAMPLE **)&jresult = result;
    return jresult;
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1Free(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    (arg1)->FSOUND_Sample_Free(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1Upload(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jint jarg3_, jint jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
	void *arg3 = 0;
	if(jarg3)
		arg3 = (char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_;
    unsigned int arg4 = (unsigned int)jarg4;
    return (jboolean)(arg1)->FSOUND_Sample_Upload(arg2,arg3,arg4);
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1Lock__JJIILorg_jouvieje_Fmod_Misc_Pointer_2Lorg_jouvieje_Fmod_Misc_Pointer_2_3I_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3, jint jarg4, jobject jarg5, jobject jarg6, jintArray jarg7, jintArray jarg8) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    void *arg5;
    void *arg6;
	unsigned int *arg7 = 0;
	if(jarg7) {
	 	arg7 = (unsigned int *)jenv->GetIntArrayElements(jarg7, 0);
	}
	unsigned int *arg8 = 0;
	if(jarg8) {
	 	arg8 = (unsigned int *)jenv->GetIntArrayElements(jarg8, 0);
	}
    jboolean jresult = (jboolean)(arg1)->FSOUND_Sample_Lock(arg2,arg3,arg4,&arg5,&arg6,arg7,arg8);

	if(arg5 && jarg5) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg5;
		setPointerAddress(jenv, jarg5, newAddress);
	}
	if(arg6 && jarg6) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg6;
		setPointerAddress(jenv, jarg6, newAddress);
	}

	if (arg7) jenv->ReleaseIntArrayElements(jarg7, (jint *)arg7, 0);
	if (arg8) jenv->ReleaseIntArrayElements(jarg8, (jint *)arg8, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1Lock__JJIILorg_jouvieje_Fmod_Misc_Pointer_2Lorg_jouvieje_Fmod_Misc_Pointer_2Ljava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3, jint jarg4, jobject jarg5, jobject jarg6, jobject jarg7, jint jarg7_, jobject jarg8, jint jarg8_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    void *arg5;
    void *arg6;
	unsigned int *arg7 = 0;
	if(jarg7) {
		arg7 = (unsigned int *)((char *)jenv->GetDirectBufferAddress(jarg7)+jarg7_);
	}
	unsigned int *arg8 = 0;
	if(jarg8) {
		arg8 = (unsigned int *)((char *)jenv->GetDirectBufferAddress(jarg8)+jarg8_);
	}
    jboolean jresult = (jboolean)(arg1)->FSOUND_Sample_Lock(arg2,arg3,arg4,&arg5,&arg6,arg7,arg8);

	if(arg5 && jarg5) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg5;
		setPointerAddress(jenv, jarg5, newAddress);
	}
	if(arg6 && jarg6) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg6;
		setPointerAddress(jenv, jarg6, newAddress);
	}
	return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1Unlock__JJJJII(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jlong jarg3, jlong jarg4, jint jarg5, jint jarg6) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    void *arg3 = *(void **)&jarg3;
    void *arg4 = *(void **)&jarg4;
    unsigned int arg5 = (unsigned int)jarg5;
    unsigned int arg6 = (unsigned int)jarg6;
    return (jboolean)(arg1)->FSOUND_Sample_Unlock(arg2,arg3,arg4,arg5,arg6);
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1Unlock__JJLjava_nio_ByteBuffer_2ILjava_nio_ByteBuffer_2III(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_, jint jarg5, jint jarg6) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
	void *arg3 = 0;
	if(jarg3)
		arg3 = (char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_;
	void *arg4 = 0;
	if(jarg4)
		arg4 = (char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_;
    unsigned int arg5 = (unsigned int)jarg5;
    unsigned int arg6 = (unsigned int)jarg6;
    return (jboolean)(arg1)->FSOUND_Sample_Unlock(arg2,arg3,arg4,arg5,arg6);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1SetMode(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    unsigned int arg3 = (unsigned int)jarg3;
    return (jboolean)(arg1)->FSOUND_Sample_SetMode(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1SetLoopPoints(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3, jint jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    return (jboolean)(arg1)->FSOUND_Sample_SetLoopPoints(arg2,arg3,arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1SetDefaults(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3, jint jarg4, jint jarg5, jint jarg6) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    int arg5 = (int)jarg5;
    int arg6 = (int)jarg6;
    return (jboolean)(arg1)->FSOUND_Sample_SetDefaults(arg2,arg3,arg4,arg5,arg6);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1SetDefaultsEx(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3, jint jarg4, jint jarg5, jint jarg6, jint jarg7, jint jarg8, jint jarg9) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    int arg5 = (int)jarg5;
    int arg6 = (int)jarg6;
    int arg7 = (int)jarg7;
    int arg8 = (int)jarg8;
    int arg9 = (int)jarg9;
    return (jboolean)(arg1)->FSOUND_Sample_SetDefaultsEx(arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1SetMinMaxDistance(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jfloat jarg3, jfloat jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    return (jboolean)(arg1)->FSOUND_Sample_SetMinMaxDistance(arg2,arg3,arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1SetMaxPlaybacks(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)(arg1)->FSOUND_Sample_SetMaxPlaybacks(arg2,arg3);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1Get(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    FSOUND_SAMPLE *result = (FSOUND_SAMPLE *)(arg1)->FSOUND_Sample_Get(arg2);

    jlong jresult = 0;
    *(FSOUND_SAMPLE **)&jresult = result;
    return jresult;
}

JNIEXPORT jstring JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1GetName(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    char *result = (char *)(arg1)->FSOUND_Sample_GetName(arg2);

    jstring jresult = 0;
	if(result) jresult = jenv->NewStringUTF(result);
    return jresult;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1GetLength(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    unsigned int result = (unsigned int)(arg1)->FSOUND_Sample_GetLength(arg2);
    return (jint)result;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1GetLoopPoints__JJ_3I_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jintArray jarg3, jintArray jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
	int *arg3 = 0;
	if(jarg3) {
 		arg3 = (int *)jenv->GetIntArrayElements(jarg3, 0);
	}
	int *arg4 = 0;
	if(jarg4) {
	 	arg4 = (int *)jenv->GetIntArrayElements(jarg4, 0);
	}
    jboolean jresult = (jboolean)(arg1)->FSOUND_Sample_GetLoopPoints(arg2,arg3,arg4);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	if (arg4) jenv->ReleaseIntArrayElements(jarg4, (jint *)arg4, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1GetLoopPoints__JJLjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
	int *arg3 = 0;
	if(jarg3)
		arg3 = (int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	int *arg4 = 0;
	if(jarg4)
		arg4 = (int *)((char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_);
    return (jboolean)(arg1)->FSOUND_Sample_GetLoopPoints(arg2,arg3,arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1GetDefaults__JJ_3I_3I_3I_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jintArray jarg3, jintArray jarg4, jintArray jarg5, jintArray jarg6) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
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
    jboolean jresult = (jboolean)(arg1)->FSOUND_Sample_GetDefaults(arg2,arg3,arg4,arg5,arg6);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	if (arg4) jenv->ReleaseIntArrayElements(jarg4, (jint *)arg4, 0);
	if (arg5) jenv->ReleaseIntArrayElements(jarg5, (jint *)arg5, 0);
	if (arg6) jenv->ReleaseIntArrayElements(jarg6, (jint *)arg6, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1GetDefaults__JJLjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_, jobject jarg5, jint jarg5_, jobject jarg6, jint jarg6_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
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
    return (jboolean)(arg1)->FSOUND_Sample_GetDefaults(arg2,arg3,arg4,arg5,arg6);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1GetDefaultsEx__JJ_3I_3I_3I_3I_3I_3I_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jintArray jarg3, jintArray jarg4, jintArray jarg5, jintArray jarg6, jintArray jarg7, jintArray jarg8, jintArray jarg9) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
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
	int *arg9 = 0;
	if(jarg9) {
	 	arg9 = (int *)jenv->GetIntArrayElements(jarg9, 0);
	}
    jboolean jresult = (jboolean)(arg1)->FSOUND_Sample_GetDefaultsEx(arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	if (arg4) jenv->ReleaseIntArrayElements(jarg4, (jint *)arg4, 0);
	if (arg5) jenv->ReleaseIntArrayElements(jarg5, (jint *)arg5, 0);
	if (arg6) jenv->ReleaseIntArrayElements(jarg6, (jint *)arg6, 0);
	if (arg7) jenv->ReleaseIntArrayElements(jarg7, (jint *)arg7, 0);
	if (arg8) jenv->ReleaseIntArrayElements(jarg8, (jint *)arg8, 0);
	if (arg9) jenv->ReleaseIntArrayElements(jarg9, (jint *)arg9, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1GetDefaultsEx__JJLjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_, jobject jarg5, jint jarg5_, jobject jarg6, jint jarg6_, jobject jarg7, jint jarg7_, jobject jarg8, jint jarg8_, jobject jarg9, jint jarg9_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
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
	int *arg9 = 0;
	if(jarg9)
		arg9 = (int *)((char *)jenv->GetDirectBufferAddress(jarg9)+jarg9_);
    return (jboolean)(arg1)->FSOUND_Sample_GetDefaultsEx(arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1GetMode(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    return (jint)(arg1)->FSOUND_Sample_GetMode(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1GetMinMaxDistance__JJ_3F_3F(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jfloatArray jarg3, jfloatArray jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
	float *arg3 = 0;
	if(jarg3) {
		arg3 = jenv->GetFloatArrayElements(jarg3, 0);
	}
	float *arg4 = 0;
	if(jarg4) {
	    arg4 = jenv->GetFloatArrayElements(jarg4, 0);
	}
    jboolean result = (jboolean)(arg1)->FSOUND_Sample_GetMinMaxDistance(arg2,arg3,arg4);
	if (arg3) jenv->ReleaseFloatArrayElements(jarg3, arg3, 0);
	if (arg4) jenv->ReleaseFloatArrayElements(jarg4, arg4, 0);
	return result;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Sample_1GetMinMaxDistance__JJLjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
	float *arg3 = 0;
	if(jarg3)
		arg3 = (float *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	float *arg4 = 0;
	if(jarg4)
		arg4 = (float *)((char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_);
    return (jboolean)(arg1)->FSOUND_Sample_GetMinMaxDistance(arg2,arg3,arg4);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1PlaySound(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jlong jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    FSOUND_SAMPLE *arg3 = *(FSOUND_SAMPLE **)&jarg3;
    return (jint)(arg1)->FSOUND_PlaySound(arg2,arg3);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1PlaySoundEx(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jlong jarg3, jlong jarg4, jboolean jarg5) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    FSOUND_SAMPLE *arg3 = *(FSOUND_SAMPLE **)&jarg3;
    FSOUND_DSPUNIT *arg4 = *(FSOUND_DSPUNIT **)&jarg4;
    signed char arg5 = (signed char)jarg5;
    return (jint)(arg1)->FSOUND_PlaySoundEx(arg2,arg3,arg4,arg5);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1StopSound(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_StopSound(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetFrequency(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)(arg1)->FSOUND_SetFrequency(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetVolume(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)(arg1)->FSOUND_SetVolume(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetVolumeAbsolute(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)(arg1)->FSOUND_SetVolumeAbsolute(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetPan(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)(arg1)->FSOUND_SetPan(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetSurround(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jboolean jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    signed char arg3 = (signed char)jarg3;
    return (jboolean)(arg1)->FSOUND_SetSurround(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetMute(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jboolean jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    signed char arg3 = (signed char)jarg3;
    return (jboolean)(arg1)->FSOUND_SetMute(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetPriority(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)(arg1)->FSOUND_SetPriority(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetReserved(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jboolean jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    signed char arg3 = (signed char)jarg3;
    return (jboolean)(arg1)->FSOUND_SetReserved(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetPaused(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jboolean jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    signed char arg3 = (signed char)jarg3;
    return (jboolean)(arg1)->FSOUND_SetPaused(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetLoopMode(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    signed char arg3 = (unsigned int)jarg3;
    return (jboolean)(arg1)->FSOUND_SetLoopMode(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1SetCurrentPosition(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    unsigned int arg3 = (unsigned int)jarg3;
    return (jboolean)(arg1)->FSOUND_SetCurrentPosition(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1SetAttributes__JI_3F_3F(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jfloatArray jarg3, jfloatArray jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
	float *arg3 = 0;
	if(jarg3) {
		arg3 = jenv->GetFloatArrayElements(jarg3, 0);
	}
	float *arg4 = 0;
	if(jarg4) {
		arg4 = jenv->GetFloatArrayElements(jarg4, 0);
	}
    jboolean result = (jboolean)(arg1)->FSOUND_3D_SetAttributes(arg2,arg3,arg4);
	if (arg3) jenv->ReleaseFloatArrayElements(jarg3, arg3, 0);
	if (arg4) jenv->ReleaseFloatArrayElements(jarg4, arg4, 0);
	return result;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1SetAttributes__JILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
	float *arg3 = 0;
	if(jarg3)
		arg3 = (float *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	float *arg4 = 0;
	if(jarg4)
		arg4 = (float *)((char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_);
    return (jboolean)(arg1)->FSOUND_3D_SetAttributes(arg2,arg3,arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1SetMinMaxDistance(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jfloat jarg3, jfloat jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    return (jboolean)(arg1)->FSOUND_3D_SetMinMaxDistance(arg2,arg3,arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1IsPlaying(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_IsPlaying(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetFrequency(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jint)(arg1)->FSOUND_GetFrequency(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetVolume(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jint)(arg1)->FSOUND_GetVolume(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetAmplitude(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jint)(arg1)->FSOUND_GetAmplitude(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetPan(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jint)(arg1)->FSOUND_GetPan(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetSurround(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_GetSurround(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetMute(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_GetMute(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetPriority(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jint)(arg1)->FSOUND_GetPriority(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetReserved(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_GetReserved(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetPaused(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_GetPaused(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetLoopMode(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jint)(arg1)->FSOUND_GetLoopMode(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetCurrentPosition(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jint)(arg1)->FSOUND_GetCurrentPosition(arg2);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetCurrentSample(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    FSOUND_SAMPLE *result = (FSOUND_SAMPLE *)(arg1)->FSOUND_GetCurrentSample(arg2);

    jlong jresult = 0;
    *(FSOUND_SAMPLE **)&jresult = result;
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetCurrentLevels__JI_3F_3F(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jfloatArray jarg3, jfloatArray jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
	float *arg3 = 0;
	if(jarg3) {
		arg3 = jenv->GetFloatArrayElements(jarg3, 0);
	}
	float *arg4 = 0;
	if(jarg4) {
		arg4 = jenv->GetFloatArrayElements(jarg4, 0);
	}
    jboolean result = (jboolean)(arg1)->FSOUND_GetCurrentLevels(arg2,arg3,arg4);
	if (arg3) jenv->ReleaseFloatArrayElements(jarg3, arg3, 0);
	if (arg4) jenv->ReleaseFloatArrayElements(jarg4, arg4, 0);
	return result;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetCurrentLevels__JILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
	float *arg3 = 0;
	if(jarg3)
		arg3 = (float *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	float *arg4 = 0;
	if(jarg4)
		arg4 = (float *)((char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_);
    return (jboolean)(arg1)->FSOUND_GetCurrentLevels(arg2,arg3,arg4);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetNumSubChannels(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jint)(arg1)->FSOUND_GetNumSubChannels(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1GetSubChannel(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    return  (jint)(arg1)->FSOUND_GetSubChannel(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1GetAttributes__JI_3F_3F(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jfloatArray jarg3, jfloatArray jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
	float *arg3 = 0;
	if(jarg3) {
		arg3 = jenv->GetFloatArrayElements(jarg3, 0);
	}
	float *arg4 = 0;
	if(jarg4) {
		arg4 = jenv->GetFloatArrayElements(jarg4, 0);
	}
    jboolean result = (jboolean)(arg1)->FSOUND_3D_GetAttributes(arg2,arg3,arg4);
	if (arg3) jenv->ReleaseFloatArrayElements(jarg3, arg3, 0);
	if (arg4) jenv->ReleaseFloatArrayElements(jarg4, arg4, 0);
	return result;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1GetAttributes__JILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
	float *arg3 = 0;
	if(jarg3)
		arg3 = (float *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	float *arg4 = 0;
	if(jarg4)
		arg4 = (float *)((char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_);
    return (jboolean)(arg1)->FSOUND_3D_GetAttributes(arg2,arg3,arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1GetMinMaxDistance__JI_3F_3F(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jfloatArray jarg3, jfloatArray jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
	float *arg3 = 0;
	if(jarg3) {
	    arg3 = jenv->GetFloatArrayElements(jarg3, 0);
	}
	float *arg4 = 0;
	if(jarg4) {
	    arg4 = jenv->GetFloatArrayElements(jarg4, 0);
	}
    jboolean result = (jboolean)(arg1)->FSOUND_3D_GetMinMaxDistance(arg2,arg3,arg4);
	if (arg3) jenv->ReleaseFloatArrayElements(jarg3, arg3, 0);
	if (arg4) jenv->ReleaseFloatArrayElements(jarg4, arg4, 0);
	return result;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1GetMinMaxDistance__JILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
	float *arg3 = 0;
	if(jarg3)
		arg3 = (float *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	float *arg4 = 0;
	if(jarg4)
		arg4 = (float *)((char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_);
    return (jboolean)(arg1)->FSOUND_3D_GetMinMaxDistance(arg2,arg3,arg4);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1SetDopplerFactor(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    float arg2 = (float)jarg2;
    (arg1)->FSOUND_3D_SetDopplerFactor(arg2);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1SetDistanceFactor(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    float arg2 = (float)jarg2;
    (arg1)->FSOUND_3D_SetDistanceFactor(arg2);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1SetRolloffFactor(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    float arg2 = (float)jarg2;
    (arg1)->FSOUND_3D_SetRolloffFactor(arg2);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1Listener_1SetCurrent(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    (arg1)->FSOUND_3D_Listener_SetCurrent(arg2,arg3);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1Listener_1SetAttributes__J_3F_3FFFFFFF(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloatArray jarg2, jfloatArray jarg3, jfloat jarg4, jfloat jarg5, jfloat jarg6, jfloat jarg7, jfloat jarg8, jfloat jarg9) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
	float *arg2 = 0;
	if(jarg2) {
		arg2 = jenv->GetFloatArrayElements(jarg2, 0);
	}
	float *arg3 = 0;
	if(jarg3) {
		arg3 = jenv->GetFloatArrayElements(jarg3, 0);
	}
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    float arg6 = (float)jarg6;
    float arg7 = (float)jarg7;
    float arg8 = (float)jarg8;
    float arg9 = (float)jarg9;
    (arg1)->FSOUND_3D_Listener_SetAttributes(arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
	if (arg2) jenv->ReleaseFloatArrayElements(jarg2, arg2, 0);
	if (arg3) jenv->ReleaseFloatArrayElements(jarg3, arg3, 0);
}
JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1Listener_1SetAttributes__JLjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2IFFFFFF(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_, jfloat jarg4, jfloat jarg5, jfloat jarg6, jfloat jarg7, jfloat jarg8, jfloat jarg9) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
	float *arg2 = 0;
	if(jarg2)
		arg2 = (float *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	float *arg3 = 0;
	if(jarg3)
		arg3 = (float *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    float arg6 = (float)jarg6;
    float arg7 = (float)jarg7;
    float arg8 = (float)jarg8;
    float arg9 = (float)jarg9;
    (arg1)->FSOUND_3D_Listener_SetAttributes(arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1Listener_1GetAttributes__J_3F_3F_3F_3F_3F_3F_3F_3F(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloatArray jarg2, jfloatArray jarg3, jfloatArray jarg4, jfloatArray jarg5, jfloatArray jarg6, jfloatArray jarg7, jfloatArray jarg8, jfloatArray jarg9) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
	float *arg2 = 0;
	if(jarg2) {
		arg2 = jenv->GetFloatArrayElements(jarg2, 0);
	}
	float *arg3 = 0;
	if(jarg3) {
		arg3 = jenv->GetFloatArrayElements(jarg3, 0);
	}
	float *arg4 = 0;
	if(jarg4) {
		arg4 = jenv->GetFloatArrayElements(jarg4, 0);
	}
	float *arg5 = 0;
	if(jarg5) {
		arg5 = jenv->GetFloatArrayElements(jarg5, 0);
	}
	float *arg6 = 0;
	if(jarg6) {
		arg6 = jenv->GetFloatArrayElements(jarg6, 0);
	}
	float *arg7 = 0;
	if(jarg7) {
		arg7 = jenv->GetFloatArrayElements(jarg7, 0);
	}
	float *arg8 = 0;
	if(jarg8) {
		arg8 = jenv->GetFloatArrayElements(jarg8, 0);
	}
	float *arg9 = 0;
	if(jarg9) {
		arg9 = jenv->GetFloatArrayElements(jarg9, 0);
	}
    (arg1)->FSOUND_3D_Listener_GetAttributes(arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
	if (arg2) jenv->ReleaseFloatArrayElements(jarg2, arg2, 0);
	if (arg3) jenv->ReleaseFloatArrayElements(jarg3, arg3, 0);
	if (arg4) jenv->ReleaseFloatArrayElements(jarg4, arg4, 0);
	if (arg5) jenv->ReleaseFloatArrayElements(jarg5, arg5, 0);
	if (arg6) jenv->ReleaseFloatArrayElements(jarg6, arg6, 0);
	if (arg7) jenv->ReleaseFloatArrayElements(jarg7, arg7, 0);
	if (arg8) jenv->ReleaseFloatArrayElements(jarg8, arg8, 0);
	if (arg9) jenv->ReleaseFloatArrayElements(jarg9, arg9, 0);
}
JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_13D_1Listener_1GetAttributes__JLjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2ILjava_nio_FloatBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_, jobject jarg5, jint jarg5_, jobject jarg6, jint jarg6_, jobject jarg7, jint jarg7_, jobject jarg8, jint jarg8_, jobject jarg9, jint jarg9_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
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
	float *arg9 = 0;
	if(jarg9)
		arg9 = (float *)((char *)jenv->GetDirectBufferAddress(jarg9)+jarg9_);
    (arg1)->FSOUND_3D_Listener_GetAttributes(arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1FX_1Enable(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    unsigned int arg3 = (unsigned int)jarg3;
    return (jint)(arg1)->FSOUND_FX_Enable(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1FX_1Disable(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_FX_Disable(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1FX_1SetChorus(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jfloat jarg3, jfloat jarg4, jfloat jarg5, jfloat jarg6, jint jarg7, jfloat jarg8, jint jarg9) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    float arg6 = (float)jarg6;
    int arg7 = (int)jarg7;
    float arg8 = (float)jarg8;
    int arg9 = (int)jarg9;
    return (jboolean)(arg1)->FSOUND_FX_SetChorus(arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1FX_1SetCompressor(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jfloat jarg3, jfloat jarg4, jfloat jarg5, jfloat jarg6, jfloat jarg7, jfloat jarg8) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    float arg6 = (float)jarg6;
    float arg7 = (float)jarg7;
    float arg8 = (float)jarg8;
    return (jboolean)(arg1)->FSOUND_FX_SetCompressor(arg2,arg3,arg4,arg5,arg6,arg7,arg8);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1FX_1SetDistortion(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jfloat jarg3, jfloat jarg4, jfloat jarg5, jfloat jarg6, jfloat jarg7) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    float arg6 = (float)jarg6;
    float arg7 = (float)jarg7;
    return (jboolean)(arg1)->FSOUND_FX_SetDistortion(arg2,arg3,arg4,arg5,arg6,arg7);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1FX_1SetEcho(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jfloat jarg3, jfloat jarg4, jfloat jarg5, jfloat jarg6, jboolean jarg7) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    float arg6 = (float)jarg6;
    int arg7 = (int)jarg7;
    return (jboolean)(arg1)->FSOUND_FX_SetEcho(arg2,arg3,arg4,arg5,arg6,arg7);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1FX_1SetFlanger(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jfloat jarg3, jfloat jarg4, jfloat jarg5, jfloat jarg6, jint jarg7, jfloat jarg8, jint jarg9) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    float arg6 = (float)jarg6;
    int arg7 = (int)jarg7;
    float arg8 = (float)jarg8;
    int arg9 = (int)jarg9;
    return (jboolean)(arg1)->FSOUND_FX_SetFlanger(arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1FX_1SetGargle(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3, jint jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    return (jboolean)(arg1)->FSOUND_FX_SetGargle(arg2,arg3,arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1FX_1SetI3DL2Reverb(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3, jint jarg4, jfloat jarg5, jfloat jarg6, jfloat jarg7, jint jarg8, jfloat jarg9, jint jarg10, jfloat jarg11, jfloat jarg12, jfloat jarg13, jfloat jarg14) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    float arg5 = (float)jarg5;
    float arg6 = (float)jarg6;
    float arg7 = (float)jarg7;
    int arg8 = (int)jarg8;
    float arg9 = (float)jarg9;
    int arg10 = (int)jarg10;
    float arg11 = (float)jarg11;
    float arg12 = (float)jarg12;
    float arg13 = (float)jarg13;
    float arg14 = (float)jarg14;
    return (jboolean)(arg1)->FSOUND_FX_SetI3DL2Reverb(arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1FX_1SetParamEQ(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jfloat jarg3, jfloat jarg4, jfloat jarg5) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    return (jboolean)(arg1)->FSOUND_FX_SetParamEQ(arg2,arg3,arg4,arg5);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1FX_1SetWavesReverb(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jfloat jarg3, jfloat jarg4, jfloat jarg5, jfloat jarg6) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    float arg3 = (float)jarg3;
    float arg4 = (float)jarg4;
    float arg5 = (float)jarg5;
    float arg6 = (float)jarg6;
    return (jboolean)(arg1)->FSOUND_FX_SetWavesReverb(arg2,arg3,arg4,arg5,arg6);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1SetBufferSize(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_Stream_SetBufferSize(arg2);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Open__JLjava_lang_String_2III(JNIEnv *jenv, jclass jcls, jlong jarg1, jstring jarg2, jint jarg3, jint jarg4, jint jarg5) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char *arg2 = 0;
	if (jarg2) {
		arg2 = getStringElements(jenv, jarg2);
    }
    unsigned int arg3 = (unsigned int)jarg3;
    int arg4 = (int)jarg4;
    int arg5 = (int)jarg5;
    FSOUND_STREAM *result = (FSOUND_STREAM *)(arg1)->FSOUND_Stream_Open(arg2,arg3,arg4,arg5);

	releaseStringElements(jenv, jarg2, arg2);

    jlong jresult = 0;
    *(FSOUND_STREAM **)&jresult = result;
    return jresult;
}
JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Open__JLjava_nio_ByteBuffer_2IIII(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jint jarg3, jint jarg4, jint jarg5) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
	char *arg2 = 0;
	if(jarg2)
		arg2 = (char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_;
    unsigned int arg3 = (unsigned int)jarg3;
    int arg4 = (int)jarg4;
    int arg5 = (int)jarg5;
    FSOUND_STREAM *result = (FSOUND_STREAM *)(arg1)->FSOUND_Stream_Open(arg2,arg3,arg4,arg5);

    jlong jresult = 0;
    *(FSOUND_STREAM **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Create(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg3, jint jarg4, jint jarg5, jlong jarg6) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
	if(!jarg2) {
		ThrowException(jenv, NullPointerException, "null FSOUND_STREAMCALLBACK");
		return 0;
	}

	Callback *jStreamCreate = new Callback(jenv, jarg2);
    int arg3 = (int)jarg3;
    unsigned int arg4 = (unsigned int)jarg4;
    int arg5 = (int)jarg5;
	jStreamCreate->setUserData(jarg6);

    FSOUND_STREAM *result = (FSOUND_STREAM *)(arg1)->FSOUND_Stream_Create(StreamCallback,arg3,arg4,arg5,jStreamCreate);

    jlong jresult = 0;
    *(FSOUND_STREAM **)&jresult = result;
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Close(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    return (jboolean)(arg1)->FSOUND_Stream_Close(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Play(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jlong jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    FSOUND_STREAM *arg3 = *(FSOUND_STREAM **)&jarg3;
    return (jint)(arg1)->FSOUND_Stream_Play(arg2,arg3);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1PlayEx(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jlong jarg3, jlong jarg4, jboolean jarg5) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    FSOUND_STREAM *arg3 = *(FSOUND_STREAM **)&jarg3;
    FSOUND_DSPUNIT *arg4 = *(FSOUND_DSPUNIT **)&jarg4;
    signed char arg5 = (signed char)jarg5;
    return (jint)(arg1)->FSOUND_Stream_PlayEx(arg2,arg3,arg4,arg5);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Stop(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    return (jboolean)(arg1)->FSOUND_Stream_Stop(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1SetPosition(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    unsigned int arg3 = (unsigned int)jarg3;
    return (jboolean)(arg1)->FSOUND_Stream_SetPosition(arg2,arg3);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetPosition(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    return (jint)(arg1)->FSOUND_Stream_GetPosition(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1SetTime(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)(arg1)->FSOUND_Stream_SetTime(arg2,arg3);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetTime(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    return (jint)(arg1)->FSOUND_Stream_GetTime(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetLength(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    return (jint)(arg1)->FSOUND_Stream_GetLength(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetLengthMs(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    return (jint)(arg1)->FSOUND_Stream_GetLengthMs(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1SetMode(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    unsigned int arg3 = (unsigned int)jarg3;
    return (jboolean)(arg1)->FSOUND_Stream_SetMode(arg2,arg3);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetMode(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    return (jint)(arg1)->FSOUND_Stream_GetMode(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1SetLoopPoints(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3, jint jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    unsigned int arg3 = (unsigned int)jarg3;
    unsigned int arg4 = (unsigned int)jarg4;
    return (jboolean)(arg1)->FSOUND_Stream_SetLoopPoints(arg2,arg3,arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1SetLoopCount(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)(arg1)->FSOUND_Stream_SetLoopCount(arg2,arg3);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetOpenState(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    return (jint)(arg1)->FSOUND_Stream_GetOpenState(arg2);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetSample(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    FSOUND_SAMPLE *result = (FSOUND_SAMPLE *)(arg1)->FSOUND_Stream_GetSample(arg2);

    jlong jresult = 0;
    *(FSOUND_SAMPLE **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1CreateDSP(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jint jarg4, jlong jarg5) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
	Callback *dspCallback = new Callback(jenv, jarg3);			//TODO
    int arg4 = (int)jarg4;
	dspCallback->setUserData(jarg5);

	FSOUND_DSPUNIT *result = (FSOUND_DSPUNIT *)(arg1)->FSOUND_Stream_CreateDSP(arg2, DspCallback, arg4, dspCallback);

    jlong jresult = 0;
    *(FSOUND_DSPUNIT **)&jresult = result;
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1SetEndCallback(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jlong jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    Callback *jEndCallback = new Callback(jenv, jarg3);
	jEndCallback->setUserData(jarg4);

    return (jboolean)(arg1)->FSOUND_Stream_SetEndCallback(arg2, StreamCallback, jEndCallback);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1SetSyncCallback(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jlong jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    Callback *jSyncCallback = new Callback(jenv, jarg3);
	jSyncCallback->setUserData(jarg4);

    return (jboolean)(arg1)->FSOUND_Stream_SetSyncCallback(arg2, StreamCallback, jSyncCallback);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1AddSyncPoint(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3, jstring jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    unsigned int arg3 = (unsigned int)jarg3;
    char *arg4 = 0;
	if (jarg4) {
		arg4 = getStringElements(jenv, jarg4);
    }
    FSOUND_SYNCPOINT *result = (FSOUND_SYNCPOINT *)(arg1)->FSOUND_Stream_AddSyncPoint(arg2,arg3,arg4);

	releaseStringElements(jenv, jarg4, arg4);

    jlong jresult = 0;
    *(FSOUND_SYNCPOINT **)&jresult = result;
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1DeleteSyncPoint(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SYNCPOINT *arg2 = *(FSOUND_SYNCPOINT **)&jarg2;
    return (jboolean)(arg1)->FSOUND_Stream_DeleteSyncPoint(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetNumSyncPoints(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    return (jint)(arg1)->FSOUND_Stream_GetNumSyncPoints(arg2);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetSyncPoint(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    int arg3 = (int)jarg3;
    FSOUND_SYNCPOINT *result = (FSOUND_SYNCPOINT *)(arg1)->FSOUND_Stream_GetSyncPoint(arg2,arg3);

    jlong jresult = 0;
    *(FSOUND_SYNCPOINT **)&jresult = result;
    return jresult;
}

JNIEXPORT jstring JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetSyncPointInfo__JJ_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jintArray jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SYNCPOINT *arg2 = *(FSOUND_SYNCPOINT **)&jarg2;
	unsigned int *arg3 = 0;
	if(jarg3) {
	 	arg3 = (unsigned int *)jenv->GetIntArrayElements(jarg3, 0);
	}
    char *result = (char *)(arg1)->FSOUND_Stream_GetSyncPointInfo(arg2,arg3);

	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
    jstring jresult = 0;
	if(result) jresult = jenv->NewStringUTF(result);
    return jresult;
}
JNIEXPORT jstring JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetSyncPointInfo__JJLjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jint jarg3_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SYNCPOINT *arg2 = *(FSOUND_SYNCPOINT **)&jarg2;
	unsigned int *arg3 = 0;
	if(jarg3)
		arg3 = (unsigned int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
    char *result = (char *)(arg1)->FSOUND_Stream_GetSyncPointInfo(arg2,arg3);

    jstring jresult = 0;
	if(result) jresult = jenv->NewStringUTF(result);
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1SetSubStream(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)(arg1)->FSOUND_Stream_SetSubStream(arg2,arg3);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1SetSubStreamSentence__JJ_3II(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    return (jint)(arg1)->FSOUND_Stream_GetNumSubStreams(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1SetSubStreamSentence(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jintArray jarg3, jint jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
	int *arg3 = 0;
	if(jarg3) {
	 	arg3 = (int *)jenv->GetIntArrayElements(jarg3, 0);
	}
    int arg4 = (int)jarg4;
    jboolean jresult = (jboolean)(arg1)->FSOUND_Stream_SetSubStreamSentence(arg2,arg3,arg4);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1SetSubStreamSentence__JJLjava_nio_IntBuffer_2II(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jint jarg3_, jint jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
	int *arg3 = 0;
	if(jarg3)
		arg3 = (int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
    int arg4 = (int)jarg4;
    return (jboolean)(arg1)->FSOUND_Stream_SetSubStreamSentence(arg2,arg3,arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetNumTagFields__JJ_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jintArray jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
	int *arg3 = 0;
	if(jarg3) {
	 	arg3 = (int *)jenv->GetIntArrayElements(jarg3, 0);
	}
    jboolean jresult  =(jboolean)(arg1)->FSOUND_Stream_GetNumTagFields(arg2,arg3);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetNumTagFields__JJLjava_nio_IntBuffer_2I
(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jint jarg3_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
	int *arg3 = 0;
	if(jarg3)
		arg3 = (int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
    return (jboolean)(arg1)->FSOUND_Stream_GetNumTagFields(arg2,arg3);
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetTagField__JJI_3ILorg_jouvieje_Fmod_Misc_Pointer_2Lorg_jouvieje_Fmod_Misc_Pointer_2_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3, jintArray jarg4, jobject jarg5, jobject jarg6, jintArray jarg7) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    int arg3 = (int)jarg3;
	int *arg4 = 0;
	if(jarg4) {
	 	arg4 = (int *)jenv->GetIntArrayElements(jarg4, 0);
	}
    char *arg5;
    void *arg6;
	int *arg7 = 0;
	if(jarg7) {
	 	arg7 = (int *)jenv->GetIntArrayElements(jarg7, 0);
	}

    jboolean jresult = (jboolean)(arg1)->FSOUND_Stream_GetTagField(arg2,arg3,arg4,&arg5,&arg6,arg7);

	if(arg5 && jarg5) {
		jlong newAddress = 0;
		*(char **)&(newAddress) = arg5;
		setPointerAddress(jenv, jarg5, newAddress);
	}
	if(arg6 && jarg6) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg6;
		setPointerAddress(jenv, jarg6, newAddress);
	}

	if (arg4) jenv->ReleaseIntArrayElements(jarg4, (jint *)arg4, 0);
	if (arg7) jenv->ReleaseIntArrayElements(jarg7, (jint *)arg7, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1GetTagField__JJILjava_nio_IntBuffer_2ILorg_jouvieje_Fmod_Misc_Pointer_2Lorg_jouvieje_Fmod_Misc_Pointer_2Ljava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3, jobject jarg4, jint jarg4_, jobject jarg5, jobject jarg6, jobject jarg7, jint jarg7_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    int arg3 = (int)jarg3;
	int *arg4 = 0;
	if(jarg4) {
		arg4 = (int *)((char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_);
	}
    char *arg5;
    void *arg6;
	int *arg7 = 0;
	if(jarg7) {
		arg7 = (int *)((char *)jenv->GetDirectBufferAddress(jarg7)+jarg7_);
	}

    jboolean jresult = (jboolean)(arg1)->FSOUND_Stream_GetTagField(arg2,arg3,arg4,&arg5,&arg6,arg7);

	if(arg5 && jarg5) {
		jlong newAddress = 0;
		*(char **)&(newAddress) = arg5;
		setPointerAddress(jenv, jarg5, newAddress);
	}
	if(arg6 && jarg6) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg6;
		setPointerAddress(jenv, jarg6, newAddress);
	}
	return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1FindTagField__JJILjava_lang_String_2Lorg_jouvieje_Fmod_Misc_Pointer_2_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3, jstring jarg4, jobject jarg5, jintArray jarg6) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    int arg3 = (int)jarg3;
	char *arg4 = 0;
	if (jarg4) {
		arg4 = getStringElements(jenv, jarg4);
    }
    void *arg5;
	int *arg6 = 0;
	if(jarg6) {
	 	arg6 = (int *)jenv->GetIntArrayElements(jarg6, 0);
	}
    jboolean jresult = (jboolean)(arg1)->FSOUND_Stream_FindTagField(arg2,arg3,arg4,&arg5,arg6);

	releaseStringElements(jenv, jarg4, arg4);

	if(arg5 && jarg5) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg5;
		setPointerAddress(jenv, jarg5, newAddress);
	}

	if (arg6) jenv->ReleaseIntArrayElements(jarg6, (jint *)arg6, 0);
    return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1FindTagField__JJILjava_lang_String_2Lorg_jouvieje_Fmod_Misc_Pointer_2Ljava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3, jstring jarg4, jobject jarg5, jobject jarg6, jint jarg6_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    int arg3 = (int)jarg3;
	char *arg4 = 0;
	if (jarg4) {
		arg4 = getStringElements(jenv, jarg4);
    }
    void *arg5;
	int *arg6 = 0;
	if(jarg6) {
		arg6 = (int *)((char *)jenv->GetDirectBufferAddress(jarg6)+jarg6_);
	}
    jboolean jresult = (jboolean)(arg1)->FSOUND_Stream_FindTagField(arg2,arg3,arg4,&arg5,arg6);

	releaseStringElements(jenv, jarg4, arg4);

	if(arg5 && jarg5) {
		jlong newAddress = 0;
		*(void **)&(newAddress) = arg5;
		setPointerAddress(jenv, jarg5, newAddress);
	}
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Net_1SetProxy(JNIEnv *jenv, jclass jcls, jlong jarg1, jstring jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char *arg2 = 0;
	if (jarg2) {
		arg2 = getStringElements(jenv, jarg2);
    }
    jboolean jresult = (jboolean)(arg1)->FSOUND_Stream_Net_SetProxy(arg2);

	releaseStringElements(jenv, jarg2, arg2);
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Net_1SetTimeout(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    signed char result = (arg1)->FSOUND_Stream_Net_SetTimeout(arg2);
	return (jboolean)result;
}

JNIEXPORT jstring JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Net_1GetLastServerStatus(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char *result = (char *)(arg1)->FSOUND_Stream_Net_GetLastServerStatus();

    jstring jresult = 0;
	if(result) jresult = jenv->NewStringUTF(result);
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Net_1SetBufferProperties(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3, jint jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    return (jboolean)(arg1)->FSOUND_Stream_Net_SetBufferProperties(arg2,arg3,arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Net_1GetBufferProperties__J_3I_3I_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jintArray jarg2, jintArray jarg3, jintArray jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
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
    jboolean jresult = (jboolean)(arg1)->FSOUND_Stream_Net_GetBufferProperties(arg2,arg3,arg4);
	if (arg2) jenv->ReleaseIntArrayElements(jarg2, (jint *)arg2, 0);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	if (arg4) jenv->ReleaseIntArrayElements(jarg4, (jint *)arg4, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Net_1GetBufferProperties__JLjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
	int *arg2 = 0;
	if(jarg2)
		arg2 = (int *)((char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_);
	int *arg3 = 0;
	if(jarg3)
		arg3 = (int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	int *arg4 = 0;
	if(jarg4)
		arg4 = (int *)((char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_);
    return (jboolean)(arg1)->FSOUND_Stream_Net_GetBufferProperties(arg2,arg3,arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Net_1SetMetadataCallback(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jlong jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
    Callback *jMetadataCallback = new Callback(jenv, jarg3);
	jMetadataCallback->setUserData(jarg4);

    return (jboolean)(arg1)->FSOUND_Stream_Net_SetMetadataCallback(arg2, MetadataCallback, jMetadataCallback);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Net_1GetStatus__JJ_3I_3I_3I_3I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jintArray jarg3, jintArray jarg4, jintArray jarg5, jintArray jarg6) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
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
	unsigned int *arg6 = 0;
	if(jarg6) {
	 	arg6 = (unsigned int *)jenv->GetIntArrayElements(jarg6, 0);
	}
    jboolean jresult = (jboolean)(arg1)->FSOUND_Stream_Net_GetStatus(arg2,arg3,arg4,arg5,arg6);
	if (arg3) jenv->ReleaseIntArrayElements(jarg3, (jint *)arg3, 0);
	if (arg4) jenv->ReleaseIntArrayElements(jarg4, (jint *)arg4, 0);
	if (arg5) jenv->ReleaseIntArrayElements(jarg5, (jint *)arg5, 0);
	if (arg6) jenv->ReleaseIntArrayElements(jarg6, (jint *)arg6, 0);
	return jresult;
}
JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Stream_1Net_1GetStatus__JJLjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2ILjava_nio_IntBuffer_2I(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jint jarg3_, jobject jarg4, jint jarg4_, jobject jarg5, jint jarg5_, jobject jarg6, jint jarg6_) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_STREAM *arg2 = *(FSOUND_STREAM **)&jarg2;
	int *arg3 = 0;
	if(jarg3)
		arg3 = (int *)((char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_);
	int *arg4 = 0;
	if(jarg4)
		arg4 = (int *)((char *)jenv->GetDirectBufferAddress(jarg4)+jarg4_);
	int *arg5 = 0;
	if(jarg5)
		arg5 = (int *)((char *)jenv->GetDirectBufferAddress(jarg5)+jarg5_);
	unsigned int *arg6 = 0;
	if(jarg6)
		arg6 = (unsigned int *)((char *)jenv->GetDirectBufferAddress(jarg6)+jarg6_);
    return (jboolean)(arg1)->FSOUND_Stream_Net_GetStatus(arg2,arg3,arg4,arg5,arg6);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1CD_1Play(JNIEnv *jenv, jclass jcls, jlong jarg1, jchar jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char arg2 = (char)jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)(arg1)->FSOUND_CD_Play(arg2,arg3);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1CD_1SetPlayMode(JNIEnv *jenv, jclass jcls, jlong jarg1, jchar jarg2, jbyte jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char arg2 = (char)jarg2;
    signed char arg3 = (signed char)jarg3;
    (arg1)->FSOUND_CD_SetPlayMode(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1CD_1Stop(JNIEnv *jenv, jclass jcls, jlong jarg1, jchar jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char arg2 = (char)jarg2;
    return (jboolean)(arg1)->FSOUND_CD_Stop(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1CD_1SetPaused(JNIEnv *jenv, jclass jcls, jlong jarg1, jchar jarg2, jboolean jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char arg2 = (char)jarg2;
    signed char arg3 = (signed char)jarg3;
    return (jboolean)(arg1)->FSOUND_CD_SetPaused(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1CD_1SetVolume(JNIEnv *jenv, jclass jcls, jlong jarg1, jchar jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char arg2 = (char)jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)(arg1)->FSOUND_CD_SetVolume(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1CD_1SetTrackTime(JNIEnv *jenv, jclass jcls, jlong jarg1, jchar jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char arg2 = (char)jarg2;
    unsigned int arg3 = (unsigned int)jarg3;
    return (jboolean)(arg1)->FSOUND_CD_SetTrackTime(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1CD_1Eject(JNIEnv *jenv, jclass jcls, jlong jarg1, jchar jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char arg2 = (char)jarg2;
    return (jboolean)(arg1)->FSOUND_CD_Eject(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1CD_1OpenTray(JNIEnv *jenv, jclass jcls, jlong jarg1, jchar jarg2, jboolean jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char arg2 = (char)jarg2;
    signed char arg3 = (signed char)jarg3;
	return (jboolean)(arg1)->FSOUND_CD_OpenTray(arg2, arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1CD_1GetPaused(JNIEnv *jenv, jclass jcls, jlong jarg1, jchar jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char arg2 = (char)jarg2;
    return (jboolean)(arg1)->FSOUND_CD_GetPaused(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1CD_1GetTrack(JNIEnv *jenv, jclass jcls, jlong jarg1, jchar jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char arg2 = (char)jarg2;
    return (jint)(arg1)->FSOUND_CD_GetTrack(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1CD_1GetNumTracks(JNIEnv *jenv, jclass jcls, jlong jarg1, jchar jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char arg2 = (char)jarg2;
    return (jint)(arg1)->FSOUND_CD_GetNumTracks(arg2);
}


JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1CD_1GetVolume(JNIEnv *jenv, jclass jcls, jlong jarg1, jchar jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char arg2 = (char)jarg2;
    return (jint)(arg1)->FSOUND_CD_GetVolume(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1CD_1GetTrackLength(JNIEnv *jenv, jclass jcls, jlong jarg1, jchar jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char arg2 = (char)jarg2;
    int arg3 = (int)jarg3;
    return (jint)(arg1)->FSOUND_CD_GetTrackLength(arg2,arg3);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1CD_1GetTrackTime(JNIEnv *jenv, jclass jcls, jlong jarg1, jchar jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char arg2 = (char)jarg2;
    return (jint)(arg1)->FSOUND_CD_GetTrackTime(arg2);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1Create(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg3, jlong jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg3 = (int)jarg3;
	FSOUND_DSPUNIT *result = 0;

	if(jarg2 != 0)
	{
		Callback *dspCallback = new Callback(jenv, jarg2);
		dspCallback->setUserData(jarg4);

		result = (FSOUND_DSPUNIT *)(arg1)->FSOUND_DSP_Create(DspCallback, arg3, dspCallback);
	}
	else
	{
		result = (FSOUND_DSPUNIT *)(arg1)->FSOUND_DSP_Create(NULL, arg3, NULL);
	}

    jlong jresult = 0;
    *(FSOUND_DSPUNIT **)&jresult = result;
    return jresult;
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1Free(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_DSPUNIT *arg2 = *(FSOUND_DSPUNIT **)&jarg2;
    (arg1)->FSOUND_DSP_Free(arg2);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1SetPriority(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_DSPUNIT *arg2 = *(FSOUND_DSPUNIT **)&jarg2;
    int arg3 = (int)jarg3;
    (arg1)->FSOUND_DSP_SetPriority(arg2,arg3);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1GetPriority(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_DSPUNIT *arg2 = *(FSOUND_DSPUNIT **)&jarg2;
    return (jint)(arg1)->FSOUND_DSP_GetPriority(arg2);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1SetActive(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jboolean jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_DSPUNIT *arg2 = *(FSOUND_DSPUNIT **)&jarg2;
    signed char arg3 = (signed char)jarg3;
    (arg1)->FSOUND_DSP_SetActive(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1GetActive(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_DSPUNIT *arg2 = *(FSOUND_DSPUNIT **)&jarg2;
    return (jboolean)(arg1)->FSOUND_DSP_GetActive(arg2);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1GetClearUnit(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_DSPUNIT *result = (FSOUND_DSPUNIT *)(arg1)->FSOUND_DSP_GetClearUnit();

    jlong jresult = 0;
    *(FSOUND_DSPUNIT **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1GetSFXUnit(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_DSPUNIT *result = (FSOUND_DSPUNIT *)(arg1)->FSOUND_DSP_GetSFXUnit();

    jlong jresult = 0;
    *(FSOUND_DSPUNIT **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1GetMusicUnit(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_DSPUNIT *result = (FSOUND_DSPUNIT *)(arg1)->FSOUND_DSP_GetMusicUnit();

    jlong jresult = 0;
    *(FSOUND_DSPUNIT **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1GetFFTUnit(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_DSPUNIT *result = (FSOUND_DSPUNIT *)(arg1)->FSOUND_DSP_GetFFTUnit();

    jlong jresult = 0;
    *(FSOUND_DSPUNIT **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1GetClipAndCopyUnit(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_DSPUNIT *result = (FSOUND_DSPUNIT *)(arg1)->FSOUND_DSP_GetClipAndCopyUnit();

    jlong jresult = 0;
    *(FSOUND_DSPUNIT **)&jresult = result;
    return jresult;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1MixBuffers(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jobject jarg3, jint jarg3_, jint jarg4, jint jarg5, jint jarg6, jint jarg7, jint jarg8) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
	void *arg2 = 0;
	if(jarg2)
		arg2 = (char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_;
	void *arg3 = 0;
	if(jarg3)
		arg3 = (char *)jenv->GetDirectBufferAddress(jarg3)+jarg3_;
    int arg4 = (int)jarg4;
    int arg5 = (int)jarg5;
    int arg6 = (int)jarg6;
    int arg7 = (int)jarg7;
    unsigned int arg8 = (unsigned int)jarg8;
    return (jboolean)(arg1)->FSOUND_DSP_MixBuffers(arg2,arg3,arg4,arg5,arg6,arg7,arg8);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1ClearMixBuffer(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    (arg1)->FSOUND_DSP_ClearMixBuffer();
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1GetBufferLength(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_DSP_GetBufferLength();
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1GetBufferLengthTotal(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_DSP_GetBufferLengthTotal();
}

JNIEXPORT jobject JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1DSP_1GetSpectrum(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    float *result = (float *)(arg1)->FSOUND_DSP_GetSpectrum();

	if (result) {
		long capacity = 512 * sizeof(float);
		return jenv->NewDirectByteBuffer((void *)result, (jlong)capacity);
	}
	else
		return NULL;
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Reverb_1SetProperties(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_REVERB_PROPERTIES *arg2 = *(FSOUND_REVERB_PROPERTIES **)&jarg2;
    return (jboolean)(arg1)->FSOUND_Reverb_SetProperties(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Reverb_1GetProperties(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_REVERB_PROPERTIES *arg2 = *(FSOUND_REVERB_PROPERTIES **)&jarg2;
    return (jboolean)(arg1)->FSOUND_Reverb_GetProperties(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Reverb_1SetChannelProperties(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jlong jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    FSOUND_REVERB_CHANNELPROPERTIES *arg3 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg3;
    return (jboolean)(arg1)->FSOUND_Reverb_SetChannelProperties(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Reverb_1GetChannelProperties(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jlong jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    FSOUND_REVERB_CHANNELPROPERTIES *arg3 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg3;
    return (jboolean)(arg1)->FSOUND_Reverb_GetChannelProperties(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Record_1SetDriver(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    return (jboolean)(arg1)->FSOUND_Record_SetDriver(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Record_1GetNumDrivers(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_Record_GetNumDrivers();
}

JNIEXPORT jstring JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Record_1GetDriverName(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    int arg2 = (int)jarg2;
    char *result = (char *)(arg1)->FSOUND_Record_GetDriverName(arg2);

    jstring jresult = 0;
	if(result) jresult = jenv->NewStringUTF(result);
    return jresult;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Record_1GetDriver(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_Record_GetDriver();
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Record_1StartSample(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jboolean jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FSOUND_SAMPLE *arg2 = *(FSOUND_SAMPLE **)&jarg2;
    signed char arg3 = (signed char)jarg3;
    return (jboolean)(arg1)->FSOUND_Record_StartSample(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Record_1Stop(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jboolean)(arg1)->FSOUND_Record_Stop();
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FSOUND_1Record_1GetPosition(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    return (jint)(arg1)->FSOUND_Record_GetPosition();
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1LoadSong(JNIEnv *jenv, jclass jcls, jlong jarg1, jstring jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char *arg2 = 0;
	if (jarg2) {
		arg2 = getStringElements(jenv, jarg2);
    }
    FMUSIC_MODULE *result = (FMUSIC_MODULE *)(arg1)->FMUSIC_LoadSong(arg2);

	releaseStringElements(jenv, jarg2, arg2);

    jlong jresult = 0;
    *(FMUSIC_MODULE **)&jresult = result;
    return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1LoadSongEx__JLjava_lang_String_2III_3II(JNIEnv *jenv, jclass jcls, jlong jarg1, jstring jarg2, jint jarg3, jint jarg4, jint jarg5, jintArray jarg6, jint jarg7) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char *arg2 = 0;
	if (jarg2) {
		arg2 = getStringElements(jenv, jarg2);
    }
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    unsigned int arg5 = (unsigned int)jarg5;
	int *arg6 = 0;
	if(jarg6) {
	 	arg6 = (int *)jenv->GetIntArrayElements(jarg6, 0);
	}
    int arg7 = (int)jarg7;
    FMUSIC_MODULE *result = (FMUSIC_MODULE *)(arg1)->FMUSIC_LoadSongEx(arg2,arg3,arg4,arg5,arg6,arg7);

	releaseStringElements(jenv, jarg2, arg2);

	if (arg6) jenv->ReleaseIntArrayElements(jarg6, (jint *)arg6, 0);
    jlong jresult = 0;
    *(FMUSIC_MODULE **)&jresult = result;
    return jresult;
}
JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1LoadSongEx__JLjava_nio_ByteBuffer_2IIII_3II(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jint jarg3, jint jarg4, jint jarg5, jintArray jarg6, jint jarg7) {
	FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
	char *arg2 = 0;
	if(jarg2)
		arg2 = (char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_;
	int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    unsigned int arg5 = (unsigned int)jarg5;
	int *arg6 = 0;
	if(jarg6) {
	 	arg6 = (int *)jenv->GetIntArrayElements(jarg6, 0);
	}
    int arg7 = (int)jarg7;
    FMUSIC_MODULE *result = (FMUSIC_MODULE *)(arg1)->FMUSIC_LoadSongEx(arg2,arg3,arg4,arg5,arg6,arg7);

	if (arg6) jenv->ReleaseIntArrayElements(jarg6, (jint *)arg6, 0);
    jlong jresult = 0;
    *(FMUSIC_MODULE **)&jresult = result;
    return jresult;
}
JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1LoadSongEx__JLjava_lang_String_2IIILjava_nio_IntBuffer_2II(JNIEnv *jenv, jclass jcls, jlong jarg1, jstring jarg2, jint jarg3, jint jarg4, jint jarg5, jobject jarg6, jint jarg6_, jint jarg7) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    char *arg2 = 0;
	if (jarg2) {
		arg2 = getStringElements(jenv, jarg2);
    }
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    unsigned int arg5 = (unsigned int)jarg5;
	int *arg6 = 0;
	if(jarg6)
		arg6 = (int *)((char *)jenv->GetDirectBufferAddress(jarg6)+jarg6_);
    int arg7 = (int)jarg7;
    FMUSIC_MODULE *result = (FMUSIC_MODULE *)(arg1)->FMUSIC_LoadSongEx(arg2,arg3,arg4,arg5,arg6,arg7);

	releaseStringElements(jenv, jarg2, arg2);

    jlong jresult = 0;
    *(FMUSIC_MODULE **)&jresult = result;
    return jresult;
}
JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1LoadSongEx__JLjava_nio_ByteBuffer_2IIIILjava_nio_IntBuffer_2II(JNIEnv *jenv, jclass jcls, jlong jarg1, jobject jarg2, jint jarg2_, jint jarg3, jint jarg4, jint jarg5, jobject jarg6, jint jarg6_, jint jarg7) {
	FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
	char *arg2 = 0;
	if(jarg2)
		arg2 = (char *)jenv->GetDirectBufferAddress(jarg2)+jarg2_;
	int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    unsigned int arg5 = (unsigned int)jarg5;
	int *arg6 = 0;
	if(jarg6)
		arg6 = (int *)((char *)jenv->GetDirectBufferAddress(jarg6)+jarg6_);
    int arg7 = (int)jarg7;
    FMUSIC_MODULE *result = (FMUSIC_MODULE *)(arg1)->FMUSIC_LoadSongEx(arg2,arg3,arg4,arg5,arg6,arg7);

    jlong jresult = 0;
    *(FMUSIC_MODULE **)&jresult = result;
    return jresult;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetOpenState(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jint)(arg1)->FMUSIC_GetOpenState(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1FreeSong(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jboolean)(arg1)->FMUSIC_FreeSong(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1PlaySong(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jboolean)(arg1)->FMUSIC_PlaySong(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1StopSong(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jboolean)(arg1)->FMUSIC_StopSong(arg2);
}

JNIEXPORT void JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1StopAllSongs(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    (arg1)->FMUSIC_StopAllSongs();
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1SetZxxCallback(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
	if(cZxxCallback) {
		delete cZxxCallback;
		cZxxCallback = NULL;
	}
	if(jarg3)
	    cZxxCallback = new Callback(jenv, jarg3);
    return (jboolean)(arg1)->FMUSIC_SetZxxCallback(arg2, ZxxCallback);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1SetRowCallback(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jint jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
	if(cRowCallback) {
		delete cRowCallback;
		cRowCallback = NULL;
	}
	if(jarg3)
	    cRowCallback = new Callback(jenv, jarg3);
    int arg4 = (int)jarg4;
    return (jboolean)(arg1)->FMUSIC_SetRowCallback(arg2, RowCallback, arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1SetOrderCallback(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jint jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
	if(cOrderCallback) {
		delete cOrderCallback;
		cOrderCallback = NULL;
	}
	if(jarg3)
	    cOrderCallback = new Callback(jenv, jarg3);
    int arg4 = (int)jarg4;
    return (jboolean)(arg1)->FMUSIC_SetOrderCallback(arg2, OrderCallback, arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1SetInstCallback(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jobject jarg3, jint jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
	if(cInstCallback) {
		delete cInstCallback;
		cInstCallback = NULL;
	}
	if(jarg3)
	    cInstCallback = new Callback(jenv, jarg3);
    int arg4 = (int)jarg4;
    return (jboolean)(arg1)->FMUSIC_SetInstCallback(arg2, InstCallback, arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1SetSample(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3, jlong jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    int arg3 = (int)jarg3;
    FSOUND_SAMPLE *arg4 = *(FSOUND_SAMPLE **)&jarg4;
    return (jboolean)(arg1)->FMUSIC_SetSample(arg2,arg3,arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1SetUserData(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jlong jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    void *arg3 = *(void **)&jarg3;
    return (jboolean)(arg1)->FMUSIC_SetUserData(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1OptimizeChannels(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3, jint jarg4) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    int arg3 = (int)jarg3;
    int arg4 = (int)jarg4;
    return (jboolean)(arg1)->FMUSIC_OptimizeChannels(arg2,arg3,arg4);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1SetReverb(JNIEnv *jenv, jclass jcls, jlong jarg1, jboolean jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    signed char arg2 = (signed char)jarg2;
    return (jboolean)(arg1)->FMUSIC_SetReverb(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1SetLooping(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jboolean jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    signed char arg3 = (signed char)jarg3;
    return (jboolean)(arg1)->FMUSIC_SetLooping(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1SetOrder(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)(arg1)->FMUSIC_SetOrder(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1SetPaused(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jboolean jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    signed char arg3 = (signed char)jarg3;
    return (jboolean)(arg1)->FMUSIC_SetPaused(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1SetMasterVolume(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    int arg3 = (int)jarg3;
    return (jboolean)(arg1)->FMUSIC_SetMasterVolume(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1SetMasterSpeed(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jfloat jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    float arg3 = (float)jarg3;
    return (jboolean)(arg1)->FMUSIC_SetMasterSpeed(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1SetPanSeperation(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jfloat jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    float arg3 = (float)jarg3;
    return (jboolean)(arg1)->FMUSIC_SetPanSeperation(arg2,arg3);
}

JNIEXPORT jstring JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetName(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    char *result = (char *)(arg1)->FMUSIC_GetName(arg2);

	jstring jresult = 0;
	if(result) jresult = jenv->NewStringUTF(result);
    return jresult;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetType(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jint)(arg1)->FMUSIC_GetType(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetNumOrders(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jint)(arg1)->FMUSIC_GetNumOrders(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetNumPatterns(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jint)(arg1)->FMUSIC_GetNumPatterns(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetNumInstruments(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jint)(arg1)->FMUSIC_GetNumInstruments(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetNumSamples(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jint)(arg1)->FMUSIC_GetNumSamples(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetNumChannels(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jint)(arg1)->FMUSIC_GetNumChannels(arg2);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetSample(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    int arg3 = (int)jarg3;
    FSOUND_SAMPLE *result = (FSOUND_SAMPLE *)(arg1)->FMUSIC_GetSample(arg2,arg3);

    jlong jresult = 0;
    *(FSOUND_SAMPLE **)&jresult = result;
    return jresult;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetPatternLength(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    int arg3 = (int)jarg3;
    return (jint)(arg1)->FMUSIC_GetPatternLength(arg2,arg3);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1IsFinished(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jboolean)(arg1)->FMUSIC_IsFinished(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1IsPlaying(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jboolean)(arg1)->FMUSIC_IsPlaying(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetMasterVolume(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jint)(arg1)->FMUSIC_GetMasterVolume(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetGlobalVolume(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jint)(arg1)->FMUSIC_GetGlobalVolume(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetOrder(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jint)(arg1)->FMUSIC_GetOrder(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetPattern(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jint)(arg1)->FMUSIC_GetPattern(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetSpeed(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (int)(arg1)->FMUSIC_GetSpeed(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetBPM(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (int)(arg1)->FMUSIC_GetBPM(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetRow(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jint)(arg1)->FMUSIC_GetRow(arg2);
}

JNIEXPORT jboolean JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetPaused(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jboolean)(arg1)->FMUSIC_GetPaused(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetTime(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jint)(arg1)->FMUSIC_GetTime(arg2);
}

JNIEXPORT jint JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetRealChannel(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    int arg3 = (int)jarg3;
    return (jint)(arg1)->FMUSIC_GetRealChannel(arg2,arg3);
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_FmodDyn_FmodDynJNI_FMOD_1INSTANCE_1FMUSIC_1GetUserData(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
    FMOD_INSTANCE *arg1 = *(FMOD_INSTANCE **)&jarg1;
    FMUSIC_MODULE *arg2 = *(FMUSIC_MODULE **)&jarg2;
    return (jlong)(arg1)->FMUSIC_GetUserData(arg2);
}

