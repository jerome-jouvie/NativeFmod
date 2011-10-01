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
#include "org_jouvieje_Fmod_Enumerations_EnumerationJNI.h"

							/************************
							 * FSOUND_TAGFIELD_TYPE *
							 ************************/

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1TAGFIELD_1VORBISCOMMENT(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_TAGFIELD_VORBISCOMMENT;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1TAGFIELD_1ID3V1(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_TAGFIELD_ID3V1;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1TAGFIELD_1ID3V2(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_TAGFIELD_ID3V2;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1TAGFIELD_1SHOUTCAST(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_TAGFIELD_SHOUTCAST;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1TAGFIELD_1ICECAST(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_TAGFIELD_ICECAST;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1TAGFIELD_1ASF(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_TAGFIELD_ASF;
    return (jint)result;
}

							/***********************
							 * FSOUND_SPEAKERMODES *
							 ***********************/

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1SPEAKERMODE_1DOLBYDIGITAL(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_SPEAKERMODE_DOLBYDIGITAL;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1SPEAKERMODE_1HEADPHONES(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_SPEAKERMODE_HEADPHONES;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1SPEAKERMODE_1MONO(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_SPEAKERMODE_MONO;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1SPEAKERMODE_1QUAD(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_SPEAKERMODE_QUAD;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1SPEAKERMODE_1STEREO(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_SPEAKERMODE_STEREO;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1SPEAKERMODE_1SURROUND(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_SPEAKERMODE_SURROUND;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1SPEAKERMODE_1DTS(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_SPEAKERMODE_DTS;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1SPEAKERMODE_1PROLOGIC2(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_SPEAKERMODE_PROLOGIC2;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1SPEAKERMODE_1PROLOGIC2_1INTERIOR(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_SPEAKERMODE_PROLOGIC2_INTERIOR;
    return (jint)result;
}

							/****************************
							 * FSOUND_STREAM_NET_STATUS *
							 ****************************/

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1STREAM_1NET_1NOTCONNECTED(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_STREAM_NET_NOTCONNECTED;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1STREAM_1NET_1CONNECTING(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_STREAM_NET_CONNECTING;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1STREAM_1NET_1BUFFERING(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_STREAM_NET_BUFFERING;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1STREAM_1NET_1READY(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_STREAM_NET_READY;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1STREAM_1NET_1ERROR(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_STREAM_NET_ERROR;
    return (jint)result;
}

							/**********************
							 * FSOUND_OUTPUTTYPES *
							 **********************/

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1OUTPUT_1NOSOUND(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_OUTPUT_NOSOUND;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1OUTPUT_1WINMM(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_OUTPUT_WINMM;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1OUTPUT_1DSOUND(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_OUTPUT_DSOUND;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1OUTPUT_1A3D(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_OUTPUT_A3D;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1OUTPUT_1OSS(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_OUTPUT_OSS;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1OUTPUT_1ESD(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_OUTPUT_ESD;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1OUTPUT_1ALSA(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_OUTPUT_ALSA;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1OUTPUT_1ASIO(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_OUTPUT_ASIO;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1OUTPUT_1XBOX(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_OUTPUT_XBOX;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1OUTPUT_1PS2(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_OUTPUT_PS2;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1OUTPUT_1MAC(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_OUTPUT_MAC;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1OUTPUT_1GC(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_OUTPUT_GC;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1OUTPUT_1PSP(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_OUTPUT_PSP;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1OUTPUT_1NOSOUND_1NONREALTIME(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_OUTPUT_NOSOUND_NONREALTIME;
    return (jint)result;
}

							/*********************
							 * FSOUND_MIXERTYPES *
							 *********************/

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1MIXER_1AUTODETECT(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_MIXER_AUTODETECT;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1MIXER_1QUALITY_1AUTODETECT(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_MIXER_QUALITY_AUTODETECT;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1MIXER_1QUALITY_1FPU(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_MIXER_QUALITY_FPU;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1MIXER_1QUALITY_1MMXP5(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_MIXER_QUALITY_MMXP5;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1MIXER_1QUALITY_1MMXP6(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_MIXER_QUALITY_MMXP6;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1MIXER_1MONO(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_MIXER_MONO;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1MIXER_1QUALITY_1MONO(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_MIXER_QUALITY_MONO;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1MIXER_1MAX(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_MIXER_MAX;
    return (jint)result;
}

							/*******************
							 * FSOUND_FX_MODES *
							 *******************/

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1FX_1CHORUS(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_FX_CHORUS;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1FX_1COMPRESSOR(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_FX_COMPRESSOR;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1FX_1DISTORTION(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_FX_DISTORTION;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1FX_1ECHO(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_FX_ECHO;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1FX_1FLANGER(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_FX_FLANGER;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1FX_1GARGLE(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_FX_GARGLE;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1FX_1I3DL2REVERB(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_FX_I3DL2REVERB;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1FX_1PARAMEQ(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_FX_PARAMEQ;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1FX_1WAVES_1REVERB(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_FX_WAVES_REVERB;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FSOUND_1FX_1MAX(JNIEnv *jenv, jclass jcls) {
    int result = (int)FSOUND_FX_MAX;
    return (jint)result;
}

							/****************
							 * FMUSIC_TYPES *
							 ****************/

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMUSIC_1TYPE_1NONE(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMUSIC_TYPE_NONE;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMUSIC_1TYPE_1MOD(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMUSIC_TYPE_MOD;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMUSIC_1TYPE_1S3M(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMUSIC_TYPE_S3M;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMUSIC_1TYPE_1XM(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMUSIC_TYPE_XM;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMUSIC_1TYPE_1IT(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMUSIC_TYPE_IT;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMUSIC_1TYPE_1MIDI(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMUSIC_TYPE_MIDI;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMUSIC_1TYPE_1FSB(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMUSIC_TYPE_FSB;
    return (jint)result;
}

							/***************
							 * FMOD_ERRORS *
							 ***************/

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1NONE(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_NONE;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1BUSY(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_BUSY;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1UNINITIALIZED(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_UNINITIALIZED;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1INIT(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_INIT;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1ALLOCATED(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_ALLOCATED;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1PLAY(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_PLAY;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1OUTPUT_1FORMAT(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_OUTPUT_FORMAT;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1COOPERATIVELEVEL(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_COOPERATIVELEVEL;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1CREATEBUFFER(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_CREATEBUFFER;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1FILE_1NOTFOUND(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_FILE_NOTFOUND;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1FILE_1FORMAT(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_FILE_FORMAT;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1FILE_1BAD(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_FILE_BAD;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1MEMORY(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_MEMORY;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1VERSION(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_VERSION;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1INVALID_1PARAM(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_INVALID_PARAM;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1NO_1EAX(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_NO_EAX;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1CHANNEL_1ALLOC(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_CHANNEL_ALLOC;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1RECORD(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_RECORD;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1MEDIAPLAYER(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_MEDIAPLAYER;
    return (jint)result;
}

JNIEXPORT jint JNICALL Java_org_jouvieje_Fmod_Enumerations_EnumerationJNI_get_1FMOD_1ERR_1CDDEVICE(JNIEnv *jenv, jclass jcls) {
    int result = (int)FMOD_ERR_CDDEVICE;
    return (jint)result;
}
