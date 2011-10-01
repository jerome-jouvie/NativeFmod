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

#include "fmod.h"
#include "Utils.h"
#include "org_jouvieje_Fmod_Structures_StructureJNI.h"

							/****************************
							 * FSOUND_REVERB_PROPERTIES *
							 ****************************/

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1Environment(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
		ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->Environment = (unsigned int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1Environment(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    unsigned int result = (unsigned int) ((arg1)->Environment);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1EnvSize(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->EnvSize = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1EnvSize(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->EnvSize);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1EnvDiffusion(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->EnvDiffusion = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1EnvDiffusion(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->EnvDiffusion);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1Room(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->Room = (int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1Room(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    int result = (int) ((arg1)->Room);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1RoomHF(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->RoomHF = (int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1RoomHF(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    int result = (int) ((arg1)->RoomHF);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1RoomLF(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->RoomLF = (int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1RoomLF(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    int result = (int) ((arg1)->RoomLF);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1DecayTime(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->DecayTime = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1DecayTime(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->DecayTime);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1DecayHFRatio(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->DecayHFRatio = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1DecayHFRatio(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->DecayHFRatio);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1DecayLFRatio(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
	FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->DecayLFRatio = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1DecayLFRatio(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->DecayLFRatio);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1Reflections(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->Reflections = (int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1Reflections(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    int result = (int) ((arg1)->Reflections);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1ReflectionsDelay(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->ReflectionsDelay = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1ReflectionsDelay(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->ReflectionsDelay);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1ReflectionsPan(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloatArray jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }

	float *arg2 = 0;
	if(jarg2) {
		arg2 = (float *)jenv->GetFloatArrayElements(jarg2, 0);
	}

	float *b = (float *) arg1->ReflectionsPan;
	for (int ii = 0; ii < 3; ii++) b[ii] = *((float *) arg2 + ii);

	//TODO Did I need to release the float array ?
}

JNIEXPORT jfloatArray JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1ReflectionsPan(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }

	float *result = (float *) ((arg1)->ReflectionsPan);

	jfloatArray jresult = 0;
	if (result) {
		jresult = jenv->NewFloatArray((jsize)3);
		jenv->ReleaseFloatArrayElements(jresult, (jfloat *)result, JNI_COMMIT);
	}
	return jresult;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1Reverb(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->Reverb = (int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1Reverb(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    int result = (int) ((arg1)->Reverb);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1ReverbDelay(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->ReverbDelay = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1ReverbDelay(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->ReverbDelay);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1ReverbPan(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloatArray jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }

	float *arg2 = 0;
	if(jarg2) {
	    arg2 = (float *)jenv->GetFloatArrayElements(jarg2, 0);
	}

	float *b = (float *) arg1->ReverbPan;
	for(int ii = 0; ii < 3; ii++)
		b[ii] = *((float *) arg2 + ii);
}

JNIEXPORT jfloatArray JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1ReverbPan(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }

    float *result = (float *) ((arg1)->ReverbPan);

	jfloatArray jresult = 0;
	if (result) {
		jresult = jenv->NewFloatArray((jsize)3);
		jenv->ReleaseFloatArrayElements(jresult, (jfloat *)result, JNI_COMMIT);
	}
	return jresult;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1EchoTime(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->EchoTime = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1EchoTime(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->EchoTime);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1EchoDepth(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->EchoDepth = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1EchoDepth(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->EchoDepth);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1ModulationTime(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->ModulationTime = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1ModulationTime(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->ModulationTime);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1ModulationDepth(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->ModulationDepth = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1ModulationDepth(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->ModulationDepth);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1AirAbsorptionHF(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->AirAbsorptionHF = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1AirAbsorptionHF(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->AirAbsorptionHF);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1HFReference(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->HFReference = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1HFReference(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->HFReference);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1LFReference(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->LFReference = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1LFReference(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->LFReference);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1RoomRolloffFactor(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->RoomRolloffFactor = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1RoomRolloffFactor(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->RoomRolloffFactor);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1Diffusion(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->Diffusion = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1Diffusion(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->Diffusion);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1Density(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->Density = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1Density(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->Density);
    return (jfloat)result;
}
JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1PROPERTIES_1Flags(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return ;
    }
    (arg1)->Flags = (unsigned int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1PROPERTIES_1Flags(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_PROPERTIES);
        return 0;
    }
    unsigned int result = (unsigned int) ((arg1)->Flags);
    return (jint)result;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_new_1FSOUND_1REVERB_1PROPERTIES(JNIEnv *jenv, jclass jcls) {
    FSOUND_REVERB_PROPERTIES *result = (FSOUND_REVERB_PROPERTIES *)new FSOUND_REVERB_PROPERTIES();

    jlong jresult = 0 ;
    *(FSOUND_REVERB_PROPERTIES **)&jresult = result;
    return jresult;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_delete_1FSOUND_1REVERB_1PROPERTIES(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_PROPERTIES *arg1 = *(FSOUND_REVERB_PROPERTIES **)&jarg1;
    delete arg1;
}

							/*************************
							 * FSOUND_REVERB_PRESETS *
							 *************************/

void setFSOUND_REVERB_PRESETS(FSOUND_REVERB_PROPERTIES *prop, unsigned int Environment, float EnvSize, float EnvDiffusion,
							  int Room, int RoomHF, int RoomLF, float DecayTime, float DecayHFRatio, float DecayLFRatio,
							  int Reflections, float ReflectionsDelay, float ReflectionsPan0, float ReflectionsPan1,
							  float ReflectionsPan2, int Reverb, float ReverbDelay, float ReverbPan0, float ReverbPan1,
							  float ReverbPan2, float EchoTime, float EchoDepth, float ModulationTime, float ModulationDepth,
							  float AirAbsorptionHF, float HFReference, float LFReference, float RoomRolloffFactor, float Diffusion,
							  float Density, unsigned int Flags)
{
	prop->Environment = Environment;
	prop->EnvSize = EnvSize;
	prop->EnvDiffusion = EnvDiffusion;
	prop->Room = Room;
	prop->RoomHF = RoomHF;
	prop->RoomLF = RoomLF;
	prop->DecayTime = DecayTime;
	prop->DecayHFRatio = DecayHFRatio;
	prop->DecayLFRatio = DecayLFRatio;
	prop->Reflections = Reflections;
	prop->ReflectionsDelay = ReflectionsDelay;
	prop->ReflectionsPan[0] = ReflectionsPan0;
	prop->ReflectionsPan[1] = ReflectionsPan1;
	prop->ReflectionsPan[2] = ReflectionsPan2;
	prop->Reverb = Reverb;
	prop->ReverbDelay = ReverbDelay;
	prop->ReverbPan[0] = ReverbPan0;
	prop->ReverbPan[1] = ReverbPan1;
	prop->ReverbPan[2] = ReverbPan2;
	prop->EchoTime = EchoTime;
	prop->EchoDepth = EchoDepth;
	prop->ModulationTime = ModulationTime;
	prop->ModulationDepth = ModulationDepth;
	prop->AirAbsorptionHF = AirAbsorptionHF;
	prop->HFReference = HFReference;
	prop->LFReference = LFReference;
	prop->RoomRolloffFactor = RoomRolloffFactor;
	prop->Diffusion = Diffusion;
	prop->Density = Density;
	prop->Flags = Flags;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1OFF(JNIEnv *jenv, jclass jcls) {
//	FSOUND_REVERB_PROPERTIES preset = FSOUND_PRESET_OFF;		//there are memory problems using this !
//	FSOUND_REVERB_PROPERTIES *result = &preset;

	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 0, 7.5f, 1.00f, -10000, -10000, 0, 1.00f, 1.00f, 1.0f, -2602, 0.007f, 0.0f, 0.0f, 0.0f , 200, 0.011f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 0.0f, 0.0f, 0x33f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1GENERIC(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 0, 7.5f, 1.00f, -1000, -100, 0, 1.49f, 0.83f, 1.0f, -2602, 0.007f, 0.0f, 0.0f, 0.0f, 200, 0.011f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1PADDEDCELL(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 1,	1.4f, 1.00f, -1000, -6000, 0, 0.17f, 0.10f, 1.0f, -1204, 0.001f, 0.0f, 0.0f, 0.0f, 207, 0.002f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1ROOM(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 2,	1.9f, 1.00f, -1000, -454, 0, 0.40f, 0.83f, 1.0f, -1646, 0.002f, 0.0f, 0.0f, 0.0f, 53, 0.003f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1BATHROOM(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 3,	1.4f, 1.00f, -1000, -1200, 0, 1.49f, 0.54f, 1.0f, -370, 0.007f, 0.0f, 0.0f, 0.0f, 1030, 0.011f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 60.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1LIVINGROOM(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 4,	2.5f, 1.00f, -1000, -6000, 0, 0.50f, 0.10f, 1.0f, -1376, 0.003f, 0.0f, 0.0f, 0.0f, -1104, 0.004f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1STONEROOM(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 5,	11.6f, 1.00f, -1000, -300, 0, 2.31f, 0.64f, 1.0f, -711, 0.012f, 0.0f, 0.0f, 0.0f, 83, 0.017f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1AUDITORIUM(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 6,	21.6f, 1.00f, -1000, -476, 0, 4.32f, 0.59f, 1.0f, -789, 0.020f, 0.0f, 0.0f, 0.0f, -289, 0.030f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1CONCERTHALL(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 7,	19.6f, 1.00f, -1000, -500, 0, 3.92f, 0.70f, 1.0f, -1230, 0.020f, 0.0f, 0.0f, 0.0f, -2, 0.029f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1CAVE(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 8,	14.6f, 1.00f, -1000, 0, 0, 2.91f, 1.30f, 1.0f, -602, 0.015f, 0.0f, 0.0f, 0.0f, -302, 0.022f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x1f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1ARENA(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 9,	36.2f, 1.00f, -1000, -698, 0, 7.24f, 0.33f, 1.0f, -1166, 0.020f, 0.0f, 0.0f, 0.0f, 16, 0.030f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1HANGAR(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 10, 50.3f,	1.00f, -1000, -1000, 0, 10.05f, 0.23f, 1.0f, -602, 0.020f, 0.0f, 0.0f, 0.0f, 198, 0.030f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1CARPETTEDHALLWAY(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 11, 1.9f, 1.00f, -1000, -4000, 0, 0.30f, 0.10f, 1.0f, -1831, 0.002f, 0.0f, 0.0f, 0.0f, -1630, 0.030f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1HALLWAY(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 12, 1.8f, 1.00f, -1000, -300, 0, 1.49f, 0.59f, 1.0f, -1219, 0.007f, 0.0f, 0.0f, 0.0f, 441, 0.011f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1STONECORRIDOR(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 13, 13.5f,	1.00f, -1000, -237, 0, 2.70f, 0.79f, 1.0f, -1214, 0.013f, 0.0f, 0.0f, 0.0f, 395, 0.020f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1ALLEY(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 14, 7.5f, 0.30f, -1000, -270, 0, 1.49f, 0.86f, 1.0f, -1204, 0.007f, 0.0f, 0.0f ,0.0f, -4, 0.011f, 0.0f, 0.0f, 0.0f, 0.125f, 0.95f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1FOREST(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 15, 38.0f,	0.30f, -1000, -3300, 0, 1.49f, 0.54f, 1.0f, -2560, 0.162f, 0.0f, 0.0f, 0.0f, -229, 0.088f, 0.0f, 0.0f, 0.0f, 0.125f, 1.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f,  79.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1CITY(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 16, 7.5f, 0.50f, -1000, -800, 0, 1.49f, 0.67f, 1.0f, -2273, 0.007f, 0.0f, 0.0f, 0.0f, -1691, 0.011f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 50.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1MOUNTAINS(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 17, 100.0f, 0.27f, -1000, -2500, 0, 1.49f, 0.21f, 1.0f, -2780, 0.300f, 0.0f, 0.0f, 0.0f, -1434, 0.100f, 0.0f, 0.0f, 0.0f, 0.250f, 1.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f,  27.0f, 100.0f, 0x1f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1QUARRY(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 18, 17.5f,	1.00f, -1000, -1000, 0, 1.49f, 0.83f, 1.0f, -10000, 0.061f, 0.0f, 0.0f, 0.0f, 500, 0.025f, 0.0f, 0.0f, 0.0f, 0.125f, 0.70f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1PLAIN(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 19, 42.5f,	0.21f, -1000, -2000, 0, 1.49f, 0.50f, 1.0f, -2466, 0.179f, 0.0f, 0.0f, 0.0f, -1926, 0.100f, 0.0f, 0.0f, 0.0f, 0.250f, 1.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f,  21.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1PARKINGLOT(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 20,	8.3f,	1.00f, -1000,  0,      0,   1.65f,  1.50f, 1.0f,  -1363, 0.008f, 0.0f,0.0f,0.0f, -1153, 0.012f, 0.0f,0.0f,0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x1f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1SEWERPIPE(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 21,	1.7f,	0.80f, -1000,  -1000,  0,   2.81f,  0.14f, 1.0f,    429, 0.014f, 0.0f,0.0f,0.0f,  1023, 0.021f, 0.0f,0.0f,0.0f, 0.250f, 0.00f, 0.25f, 0.000f, -5.0f, 5000.0f, 250.0f, 0.0f,  80.0f,  60.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1UNDERWATER(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 22,	1.8f,	1.00f, -1000,  -4000,  0,   1.49f,  0.10f, 1.0f,   -449, 0.007f, 0.0f,0.0f,0.0f,  1700, 0.011f, 0.0f,0.0f,0.0f, 0.250f, 0.00f, 1.18f, 0.348f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x3f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1DRUGGED(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 23,	1.9f,	0.50f, -1000,  0,      0,   8.39f,  1.39f, 1.0f,  -115,  0.002f, 0.0f, 0.0f, 0.0f,   985, 0.030f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 0.25f, 1.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x1f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1DIZZY(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 24,	1.8f,	0.60f, -1000,  -400,   0,   17.23f, 0.56f, 1.0f,  -1713, 0.020f, 0.0f, 0.0f, 0.0f,  -613, 0.030f, 0.0f, 0.0f, 0.0f, 0.250f, 1.00f, 0.81f, 0.310f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x1f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1PSYCHOTIC(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 25,	1.0f,	0.50f, -1000,  -151,   0,   7.56f,  0.91f, 1.0f,  -626,  0.020f, 0.0f, 0.0f, 0.0f,   774, 0.030f, 0.0f, 0.0f, 0.0f, 0.250f, 0.00f, 4.00f, 1.000f, -5.0f, 5000.0f, 250.0f, 0.0f, 100.0f, 100.0f, 0x1f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1PS2_1ROOM(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 1,	0,	    0,         0,  0,      0,   0.0f,   0.0f,  0.0f,     0,  0.000f, 0.0f, 0.0f, 0.0f,     0, 0.000f, 0.0f, 0.0f, 0.0f, 0.000f, 0.00f, 0.00f, 0.000f,  0.0f, 0000.0f,   0.0f, 0.0f,   0.0f,   0.0f, 0x31f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1PS2_1STUDIO_1A(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 2,	0,	    0,         0,  0,      0,   0.0f,   0.0f,  0.0f,     0,  0.000f, 0.0f, 0.0f, 0.0f,     0, 0.000f, 0.0f, 0.0f, 0.0f, 0.000f, 0.00f, 0.00f, 0.000f,  0.0f, 0000.0f,   0.0f, 0.0f,   0.0f,   0.0f, 0x31f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1PS2_1STUDIO_1B(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 3,	0,	    0,         0,  0,      0,   0.0f,   0.0f,  0.0f,     0,  0.000f, 0.0f, 0.0f, 0.0f,     0, 0.000f, 0.0f, 0.0f, 0.0f, 0.000f, 0.00f, 0.00f, 0.000f,  0.0f, 0000.0f,   0.0f, 0.0f,   0.0f,   0.0f, 0x31f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1PS2_1STUDIO_1C(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 4,	0,	    0,         0,  0,      0,   0.0f,   0.0f,  0.0f,     0,  0.000f, 0.0f, 0.0f, 0.0f,     0, 0.000f, 0.0f, 0.0f, 0.0f, 0.000f, 0.00f, 0.00f, 0.000f,  0.0f, 0000.0f,   0.0f, 0.0f,   0.0f,   0.0f, 0x31f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1PS2_1HALL(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 5,	0,	    0,         0,  0,      0,   0.0f,   0.0f,  0.0f,     0,  0.000f, 0.0f, 0.0f, 0.0f,     0, 0.000f, 0.0f, 0.0f, 0.0f, 0.000f, 0.00f, 0.00f, 0.000f,  0.0f, 0000.0f,   0.0f, 0.0f,   0.0f,   0.0f, 0x31f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1PS2_1SPACE(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 6,	0,	    0,         0,  0,      0,   0.0f,   0.0f,  0.0f,     0,  0.000f, 0.0f, 0.0f, 0.0f,     0, 0.000f, 0.0f, 0.0f, 0.0f, 0.000f, 0.00f, 0.00f, 0.000f,  0.0f, 0000.0f,   0.0f, 0.0f,   0.0f,   0.0f, 0x31f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1PS2_1ECHO(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 7,	0,	    0,         0,  0,      0,   0.0f,   0.0f,  0.0f,     0,  0.000f, 0.0f, 0.0f, 0.0f,     0, 0.000f, 0.0f, 0.0f, 0.0f, 0.000f, 0.00f, 0.00f, 0.000f,  0.0f, 0000.0f,   0.0f, 0.0f,   0.0f,   0.0f, 0x31f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1PS2_1DELAY(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 8,	0,	    0,         0,  0,      0,   0.0f,   0.0f,  0.0f,     0,  0.000f, 0.0f, 0.0f, 0.0f,     0, 0.000f, 0.0f, 0.0f, 0.0f, 0.000f, 0.00f, 0.00f, 0.000f,  0.0f, 0000.0f,   0.0f, 0.0f,   0.0f,   0.0f, 0x31f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1PRESET_1PS2_1PIPE(JNIEnv *jenv, jclass jcls) {
	FSOUND_REVERB_PROPERTIES *result = new FSOUND_REVERB_PROPERTIES();
	setFSOUND_REVERB_PRESETS(result, 9,	0,	    0,         0,  0,      0,   0.0f,   0.0f,  0.0f,     0,  0.000f, 0.0f, 0.0f, 0.0f,     0, 0.000f, 0.0f, 0.0f, 0.0f, 0.000f, 0.00f, 0.00f, 0.000f,  0.0f, 0000.0f,   0.0f, 0.0f,   0.0f,   0.0f, 0x31f);

	jlong jresult = 0;
	*(FSOUND_REVERB_PROPERTIES **)&jresult = result;
	return jresult;
}
