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

#include "Callback.h"

bool      Callback::initialized = false;
jclass    Callback::caller;
jmethodID Callback::methodID[12];

void Callback::connectMethods(JNIEnv *java_env) {
	static struct {
		const char *name;
	    const char *signature;
	} methods[12] = {
	    {
			"FMUSIC_CALLBACK", "(JSLorg/jouvieje/Fmod/Callbacks/FMUSIC_CALLBACK;)V"
	    },
	    {
			"FSOUND_ALLOCCALLBACK", "(ILorg/jouvieje/Fmod/Callbacks/FSOUND_ALLOCCALLBACK;)Ljava/nio/ByteBuffer;"
	    },
	    {
			"FSOUND_CLOSECALLBACK", "(JLorg/jouvieje/Fmod/Callbacks/FSOUND_CLOSECALLBACK;)V"
	    },
	    {
			"FSOUND_DSPCALLBACK", "(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;IJLorg/jouvieje/Fmod/Callbacks/FSOUND_DSPCALLBACK;)Ljava/nio/ByteBuffer;"
	    },
	    {
			"FSOUND_FREECALLBACK", "(Ljava/nio/ByteBuffer;Lorg/jouvieje/Fmod/Callbacks/FSOUND_FREECALLBACK;)V"
	    },
	    {
			"FSOUND_METADATACALLBACK", "(Ljava/lang/String;Ljava/lang/String;JLorg/jouvieje/Fmod/Callbacks/FSOUND_METADATACALLBACK;)Z"
	    },
	    {
			"FSOUND_OPENCALLBACK", "(Ljava/lang/String;Lorg/jouvieje/Fmod/Callbacks/FSOUND_OPENCALLBACK;)J"
	    },
	    {
			"FSOUND_READCALLBACK", "(Ljava/nio/ByteBuffer;IJLorg/jouvieje/Fmod/Callbacks/FSOUND_READCALLBACK;)I"
	    },
	    {
			"FSOUND_REALLOCCALLBACK", "(Ljava/nio/ByteBuffer;ILorg/jouvieje/Fmod/Callbacks/FSOUND_REALLOCCALLBACK;)Ljava/nio/ByteBuffer;"
	    },
	    {
			"FSOUND_SEEKCALLBACK", "(JIBLorg/jouvieje/Fmod/Callbacks/FSOUND_SEEKCALLBACK;)I"
	    },
	    {
	        "FSOUND_STREAMCALLBACK", "(JLjava/nio/ByteBuffer;IJLorg/jouvieje/Fmod/Callbacks/FSOUND_STREAMCALLBACK;)Z"
	    },
	    {
			"FSOUND_TELLCALLBACK", "(JLorg/jouvieje/Fmod/Callbacks/FSOUND_TELLCALLBACK;)I"
	    }
	};

	caller = (jclass)java_env->NewGlobalRef(java_env->FindClass("org/jouvieje/Fmod/CallbackBridge"));
	for (int i = 0; i <= 11; i++) {
		methodID[i] = java_env->GetStaticMethodID(caller, methods[i].name, methods[i].signature);
		java_env->ExceptionClear();

		if(java_env->ExceptionCheck()) {
			java_env->Throw(java_env->ExceptionOccurred());
			if(java_env->ExceptionCheck()) {
				java_env->ExceptionDescribe();
				java_env->FatalError(FATAL_ERROR_MESSAGE);
			}
		}
	}
	initialized = true;
}
jclass Callback::getCaller(JNIEnv *java_env) {
	if(!initialized) {
		connectMethods(java_env);
	}
	return caller;
}
jmethodID Callback::getMethodId(JNIEnv *java_env, int index) {
	if(!initialized) {
		connectMethods(java_env);
	}
	return methodID[index];
}

Callback::~Callback() {
	if(callback) {
		JNIEnv *java_env = 0;
		bool attached = acquire_jenv(&java_env);
		java_env->DeleteGlobalRef(callback);
		leave_jenv(attached);
	}
}

Callback::Callback(JNIEnv *java_env, jobject callback_) : JavaObject(java_env) {
	//WARNING : I must creates a global reference to my Java callback if I want to use it afterwards
	callback = java_env->NewGlobalRef(callback_);
	userData = 0;
}

void Callback::FMUSIC_CALLBACK(FMUSIC_MODULE *arg1, unsigned char arg2) {
	JNIEnv *java_env = 0;
	bool attached = acquire_jenv(&java_env);
	long carg1 = 0;
    *(FMUSIC_MODULE **)&carg1 = arg1;
    jlong jarg1 = (jlong) carg1;
    jshort jarg2 = (jshort) arg2;
	java_env->CallStaticVoidMethod(getCaller(java_env), getMethodId(java_env, 0), jarg1, jarg2, callback);

	if(java_env->ExceptionCheck()) {
		java_env->Throw(java_env->ExceptionOccurred());
		if(java_env->ExceptionCheck()) {
			java_env->ExceptionDescribe();
			java_env->FatalError(FATAL_ERROR_MESSAGE);
		}
	}
	leave_jenv(attached);
}

void * Callback::FSOUND_ALLOCCALLBACK(unsigned int arg1) {
	JNIEnv *java_env = 0;
	bool attached = acquire_jenv(&java_env);
    jint jarg1 = (jint) arg1;
	jobject jresult = java_env->CallStaticObjectMethod(getCaller(java_env), getMethodId(java_env, 1), jarg1, callback);

	if(java_env->ExceptionCheck()) {
		java_env->Throw(java_env->ExceptionOccurred());
		if(java_env->ExceptionCheck()) {
			java_env->ExceptionDescribe();
			java_env->FatalError(FATAL_ERROR_MESSAGE);
		}
		return NULL;
	}
	void *result = NULL;
	if(jresult) {
		jobject jresult2 = java_env->NewGlobalRef(jresult);
		result = java_env->GetDirectBufferAddress(jresult2);
	}
	leave_jenv(attached);
	return result;
}

void Callback::FSOUND_CLOSECALLBACK(void *arg1) {
	JNIEnv *java_env = 0;
	bool attached = acquire_jenv(&java_env);
	long carg1 = 0;
    *(void **)&carg1 = arg1;
    jlong jarg1 = (jlong) carg1;
	java_env->CallStaticVoidMethod(getCaller(java_env), getMethodId(java_env, 2), jarg1, callback);

	if(java_env->ExceptionCheck()) {
		java_env->Throw(java_env->ExceptionOccurred());
		if(java_env->ExceptionCheck()) {
			java_env->ExceptionDescribe();
			java_env->FatalError(FATAL_ERROR_MESSAGE);
		}
	}
	leave_jenv(attached);
}

void * Callback::FSOUND_DSPCALLBACK(void *arg1, void *arg2, int arg3, void *arg4) {
	JNIEnv *java_env;
	bool attached = acquire_jenv(&java_env);

	int size = 0;
	switch(FSOUND_GetMixer()) {
		//MMX user : 4 bytes per sample
		case FSOUND_MIXER_QUALITY_MMXP5:
		case FSOUND_MIXER_QUALITY_MMXP6: size = 4; break;
		//Others : 8 bytes per sample
		case FSOUND_MIXER_AUTODETECT:
		case FSOUND_MIXER_QUALITY_AUTODETECT:
		case FSOUND_MIXER_QUALITY_FPU:
		case FSOUND_MIXER_MONO:
		case FSOUND_MIXER_QUALITY_MONO:
		case FSOUND_MIXER_MAX:
		default: size = 8; break;
	}

	jobject jarg1, jarg2;
	if(arg1) {
		jarg1 = java_env->NewDirectByteBuffer(arg1, (jlong)(arg3*size));
	}
	else
		jarg1 = NULL;
	if(arg2) {
		jarg2 = java_env->NewDirectByteBuffer(arg2, (jlong)(arg3*size));
	}
	else
		jarg2 = NULL;
	jint jarg3 = (jint) arg3;

	jobject jresult = NULL;java_env->CallStaticObjectMethod(getCaller(java_env), getMethodId(java_env, 3), jarg1, jarg2, jarg3, userData, callback);

	if(java_env->ExceptionCheck()) {
		java_env->Throw(java_env->ExceptionOccurred());
		if(java_env->ExceptionCheck()) {
			java_env->ExceptionDescribe();
			java_env->FatalError(FATAL_ERROR_MESSAGE);
		}
		return 0;
	}
	void *result = arg2;//NULL;
	if(jresult) {
		result = java_env->GetDirectBufferAddress(jresult);
	}
	leave_jenv(attached);

	return result;
}

void Callback::FSOUND_FREECALLBACK(void * arg1) {
	JNIEnv *java_env = 0;
	bool attached = acquire_jenv(&java_env);
	jobject jarg1;
	if(arg1)
		jarg1 = java_env->NewDirectByteBuffer(arg1, 0);		//TODO size ?
	else
		jarg1 = NULL;
	java_env->CallStaticVoidMethod(getCaller(java_env), getMethodId(java_env, 4), jarg1, callback);
	jarg1 = NULL;

	if(java_env->ExceptionCheck()) {
		java_env->Throw(java_env->ExceptionOccurred());
		if(java_env->ExceptionCheck()) {
			java_env->ExceptionDescribe();
			java_env->FatalError(FATAL_ERROR_MESSAGE);
		}
	}
	leave_jenv(attached);
}

signed char Callback::FSOUND_METADATACALLBACK(char *arg1, char *arg2, void *arg3) {
	JNIEnv *java_env = 0;
	bool attached = acquire_jenv(&java_env);
	jstring jarg1 = (jstring) java_env->NewStringUTF(arg1);
	jstring jarg2 = (jstring) java_env->NewStringUTF(arg2);

	signed char result = (signed char) java_env->CallStaticBooleanMethod(getCaller(java_env), getMethodId(java_env, 5), jarg1, jarg2, userData, callback);

	if(java_env->ExceptionCheck()) {
		java_env->Throw(java_env->ExceptionOccurred());
		if(java_env->ExceptionCheck()) {
			java_env->ExceptionDescribe();
			java_env->FatalError(FATAL_ERROR_MESSAGE);
		}
		return 0;
	}
	leave_jenv(attached);
    return result;
}

void * Callback::FSOUND_OPENCALLBACK(const char *arg0) {
	JNIEnv *java_env = 0;
	bool attached = acquire_jenv(&java_env);
	jstring jarg0 = (jstring) java_env->NewStringUTF(arg0);
	long jresult = (long)java_env->CallStaticLongMethod(getCaller(java_env), getMethodId(java_env, 6), jarg0, callback);

	if(java_env->ExceptionCheck()) {
		java_env->Throw(java_env->ExceptionOccurred());
		if(java_env->ExceptionCheck()) {
			java_env->ExceptionDescribe();
			java_env->FatalError(FATAL_ERROR_MESSAGE);
		}
		return 0;
	}
	leave_jenv(attached);
	void *result = *(void **)&jresult;
    return result;
}

int Callback::FSOUND_READCALLBACK(void *arg1, int arg2, void *arg3) {
	JNIEnv *java_env = 0;
	bool attached = acquire_jenv(&java_env);
	jobject jarg1;
	if(arg1)
		jarg1 = java_env->NewDirectByteBuffer(arg1, (jlong)arg2);
	else
		jarg1 = NULL;
    jint jarg2 = (jint) arg2;
	long carg3 = 0;
    *(void **)&carg3 = arg3;
    jlong jarg3 = (jlong) carg3;
	int result = (int) java_env->CallStaticIntMethod(getCaller(java_env), getMethodId(java_env, 7), jarg1, jarg2, jarg3, callback);

	if(java_env->ExceptionCheck()) {
		java_env->Throw(java_env->ExceptionOccurred());
		if(java_env->ExceptionCheck()) {
			java_env->ExceptionDescribe();
			java_env->FatalError(FATAL_ERROR_MESSAGE);
		}
		return -1;
	}
	leave_jenv(attached);
    return result;
}

void * Callback::FSOUND_REALLOCCALLBACK(void *arg1, unsigned int arg2) {
	JNIEnv *java_env = 0;
	bool attached = acquire_jenv(&java_env);
	jobject jarg1;
	if(arg1)
		jarg1 = java_env->NewDirectByteBuffer(arg1, 0);			//TODO size ?
	else
		jarg1 = NULL;
    jint jarg2 = (jint)arg2;
	jobject jresult = java_env->CallStaticObjectMethod(getCaller(java_env), getMethodId(java_env, 8), jarg1, jarg2, callback);

	if(java_env->ExceptionCheck()) {
		java_env->Throw(java_env->ExceptionOccurred());
		if(java_env->ExceptionCheck()) {
			java_env->ExceptionDescribe();
			java_env->FatalError(FATAL_ERROR_MESSAGE);
		}
		return 0;
	}

	void *result = NULL;
	if(jresult) {
		jobject jresult2 = java_env->NewGlobalRef(jresult);
		result = java_env->GetDirectBufferAddress(jresult2);
	}
	leave_jenv(attached);
	return result;
}

int Callback::FSOUND_SEEKCALLBACK(void *arg1, int arg2, signed char arg3) {
	JNIEnv *java_env = 0;
	bool attached = acquire_jenv(&java_env);
	long carg1 = 0;
    *(void **)&carg1 = arg1;
    jlong jarg1 = (jlong) carg1;
    jint jarg2 = (jint) arg2;
    jbyte jarg3 = (jbyte) arg3;
	int result = (int) java_env->CallStaticIntMethod(getCaller(java_env), getMethodId(java_env, 9), jarg1, jarg2, jarg3, callback);

	if(java_env->ExceptionCheck()) {
		java_env->Throw(java_env->ExceptionOccurred());
		if(java_env->ExceptionCheck()) {
			java_env->ExceptionDescribe();
			java_env->FatalError(FATAL_ERROR_MESSAGE);
		}
		return -1;
	}
	leave_jenv(attached);
	return result;
}

signed char Callback::FSOUND_STREAMCALLBACK(FSOUND_STREAM *arg1, void *arg2, int arg3, void *arg4) {
	JNIEnv *java_env = 0;
	bool attached = acquire_jenv(&java_env);
	long carg1 = 0;
    *(void **)&carg1 = arg1;
    jlong jarg1 = (jlong) carg1;
	jobject jarg2;
	if(arg2) {
		//from FSOUND_Stream_Create
		if(arg3 > 0) {
			jarg2 = java_env->NewDirectByteBuffer(arg2, arg3);
		}
		//from FSOUND_Stream_SetSyncCallback
		else {
			int length = strlen((char *)arg2);
			jarg2 = java_env->NewDirectByteBuffer(arg2, length);
		}
	}
	//from FSOUND_Stream_SetEndCallback
	else
		jarg2 = NULL;
    jint jarg3 = (jint) arg3;

	signed char result = (signed char)java_env->CallStaticBooleanMethod(getCaller(java_env), getMethodId(java_env, 10), jarg1, jarg2, jarg3, userData, callback);

	if(java_env->ExceptionCheck()) {
		java_env->Throw(java_env->ExceptionOccurred());
		if(java_env->ExceptionCheck()) {
			java_env->ExceptionDescribe();
			java_env->FatalError(FATAL_ERROR_MESSAGE);
		}
		return false;
	}
	leave_jenv(attached);
	return result;
}

int Callback::FSOUND_TELLCALLBACK(void *arg1) {
	JNIEnv *java_env = 0;
	bool attached = acquire_jenv(&java_env);
	long carg1 = 0;
    *(void **)&carg1 = arg1;
    jlong jarg1 = (jlong) carg1;
	int result = (int) java_env->CallStaticIntMethod(getCaller(java_env), getMethodId(java_env, 11), jarg1, callback);

	if(java_env->ExceptionCheck()) {
		java_env->Throw(java_env->ExceptionOccurred());
		if(java_env->ExceptionCheck()) {
			java_env->ExceptionDescribe();
			java_env->FatalError(FATAL_ERROR_MESSAGE);
		}
		return -1;
	}
	leave_jenv(attached);
	return result;
}

