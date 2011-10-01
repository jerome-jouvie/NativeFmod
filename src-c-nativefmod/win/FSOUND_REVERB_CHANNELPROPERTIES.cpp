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

							/***********************************
							 * FSOUND_REVERB_CHANNELPROPERTIES *
							 ***********************************/

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1Direct(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->Direct = (int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1Direct(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    int result = (int) ((arg1)->Direct);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1DirectHF(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->DirectHF = (int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1DirectHF(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    int result = (int) ((arg1)->DirectHF);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1Room(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->Room = (int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1Room(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    int result = (int) ((arg1)->Room);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1RoomHF(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->RoomHF = (int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1RoomHF(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    int result = (int) ((arg1)->RoomHF);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1Obstruction(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->Obstruction = (int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1Obstruction(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    int result = (int) ((arg1)->Obstruction);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1ObstructionLFRatio(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->ObstructionLFRatio = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1ObstructionLFRatio(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->ObstructionLFRatio);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1Occlusion(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->Occlusion = (int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1Occlusion(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    int result = (int) ((arg1)->Occlusion);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1OcclusionLFRatio(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->OcclusionLFRatio = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1OcclusionLFRatio(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->OcclusionLFRatio);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1OcclusionRoomRatio(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->OcclusionRoomRatio = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1OcclusionRoomRatio(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->OcclusionRoomRatio);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1OcclusionDirectRatio(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->OcclusionDirectRatio = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1OcclusionDirectRatio(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->OcclusionDirectRatio);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1Exclusion(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->Exclusion = (int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1Exclusion(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    int result = (int) ((arg1)->Exclusion);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1ExclusionLFRatio(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->ExclusionLFRatio = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1ExclusionLFRatio(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->ExclusionLFRatio);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1OutsideVolumeHF(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->OutsideVolumeHF = (int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1OutsideVolumeHF(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    int result = (int) ((arg1)->OutsideVolumeHF);
    return (jint)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1DopplerFactor(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->DopplerFactor = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1DopplerFactor(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->DopplerFactor);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1RolloffFactor(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->RolloffFactor = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1RolloffFactor(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->RolloffFactor);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1RoomRolloffFactor(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->RoomRolloffFactor = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1RoomRolloffFactor(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->RoomRolloffFactor);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1AirAbsorptionFactor(JNIEnv *jenv, jclass jcls, jlong jarg1, jfloat jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->AirAbsorptionFactor = (float)jarg2;
}

JNIEXPORT jfloat JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1AirAbsorptionFactor(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    float result = (float) ((arg1)->AirAbsorptionFactor);
    return (jfloat)result;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_set_1FSOUND_1REVERB_1CHANNELPROPERTIES_1Flags(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return ;
    }
    (arg1)->Flags = (int)jarg2;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_get_1FSOUND_1REVERB_1CHANNELPROPERTIES_1Flags(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    if (!arg1) {
        ThrowException(jenv, NullPointerException, NULL_FSOUND_REVERB_CHANNEL_PROPERTIES);
        return 0;
    }
    int result = (int) ((arg1)->Flags);
    return (jint)result;
}

JNIEXPORT jlong JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_new_1FSOUND_1REVERB_1CHANNELPROPERTIES(JNIEnv *jenv, jclass jcls) {
    FSOUND_REVERB_CHANNELPROPERTIES *result = (FSOUND_REVERB_CHANNELPROPERTIES *)new FSOUND_REVERB_CHANNELPROPERTIES();

    jlong jresult = 0 ;
    *(FSOUND_REVERB_CHANNELPROPERTIES **)&jresult = result;
    return jresult;
}

JNIEXPORT void JNICALL Java_org_jouvieje_Fmod_Structures_StructureJNI_delete_1FSOUND_1REVERB_1CHANNELPROPERTIES(JNIEnv *jenv, jclass jcls, jlong jarg1) {
    FSOUND_REVERB_CHANNELPROPERTIES *arg1 = *(FSOUND_REVERB_CHANNELPROPERTIES **)&jarg1;
    delete arg1;
}
