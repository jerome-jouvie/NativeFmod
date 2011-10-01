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

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.jouvieje.Fmod.Callbacks.FMUSIC_CALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_ALLOCCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_CLOSECALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_DSPCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_FREECALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_METADATACALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_OPENCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_READCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_REALLOCCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_SEEKCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_STREAMCALLBACK;
import org.jouvieje.Fmod.Callbacks.FSOUND_TELLCALLBACK;
import org.jouvieje.Fmod.Misc.Pointer;

class FmodJNI {
	static {
		//If the libraries are not loaded, tries to load them here
		if(!Init.isFmodLibrariesLoaded()) {
			if(Init.DEBUG) {
				Thread.dumpStack();
			}
			throw new RuntimeException("Libraries not yet loaded ! Use Init.loadLibraries() before using NativeFmod.");
		}
	}

						/*Deprecated methods*/

	protected final static native boolean FSOUND_CD_Eject(char jarg1);

						/*FSOUND API*/

	protected final static native boolean FSOUND_SetOutput(int jarg1);
	protected final static native boolean FSOUND_SetDriver(int jarg1);
	protected final static native boolean FSOUND_SetMixer(int jarg1);
	protected final static native boolean FSOUND_SetBufferSize(int jarg1);
	protected final static native boolean FSOUND_SetHWND(long jarg1);
	protected final static native boolean FSOUND_SetMinHardwareChannels(int jarg1);
	protected final static native boolean FSOUND_SetMaxHardwareChannels(int jarg1);
	protected final static native boolean FSOUND_SetMemorySystem(ByteBuffer jarg1, int jarg1_, int jarg2, FSOUND_ALLOCCALLBACK jarg3, FSOUND_REALLOCCALLBACK jarg4, FSOUND_FREECALLBACK jarg5);
	protected final static native boolean FSOUND_Init(int jarg1, int jarg2, int jarg3);
	protected final static native void FSOUND_Close();
	protected final static native void FSOUND_Update();
	protected final static native void FSOUND_SetSpeakerMode(int jarg1);
	protected final static native void FSOUND_SetSFXMasterVolume(int jarg1);
	protected final static native void FSOUND_SetPanSeperation(float jarg1);
	protected final static native void FSOUND_File_SetCallbacks(FSOUND_OPENCALLBACK jarg1, FSOUND_CLOSECALLBACK jarg2, FSOUND_READCALLBACK jarg3, FSOUND_SEEKCALLBACK jarg4, FSOUND_TELLCALLBACK jarg5);
	protected final static native int FSOUND_GetError();
	protected final static native float FSOUND_GetVersion();
	protected final static native int FSOUND_GetOutput();
	protected final static native long FSOUND_GetOutputHandle();
	protected final static native int FSOUND_GetDriver();
	protected final static native int FSOUND_GetMixer();
	protected final static native int FSOUND_GetNumDrivers();
	protected final static native String FSOUND_GetDriverName(int jarg1);
	protected final static native boolean FSOUND_GetDriverCaps(int jarg1, int[] jarg2);
	protected final static native boolean FSOUND_GetDriverCaps(int jarg1, IntBuffer jarg2, int jarg2_);
	protected final static native int FSOUND_GetOutputRate();
	protected final static native int FSOUND_GetMaxChannels();
	protected final static native int FSOUND_GetMaxSamples();
	protected final static native int FSOUND_GetSpeakerMode();
	protected final static native int FSOUND_GetSFXMasterVolume();
	protected final static native boolean FSOUND_GetNumHWChannels(int[] jarg1, int[] jarg2, int[] jarg3);
	protected final static native boolean FSOUND_GetNumHWChannels(IntBuffer jarg1, int jarg1_, IntBuffer jarg2, int jarg2_, IntBuffer jarg3, int jarg3_);
	protected final static native int FSOUND_GetChannelsPlaying();
	protected final static native float FSOUND_GetCPUUsage();
	protected final static native void FSOUND_GetMemoryStats(int[] jarg1, int[] jarg2);
	protected final static native void FSOUND_GetMemoryStats(IntBuffer jarg1, int jarg1_, IntBuffer jarg2, int jarg2_);
	protected final static native long FSOUND_Sample_Load(int jarg1, String jarg2, int jarg3, int jarg4, int jarg5);
	protected final static native long FSOUND_Sample_Load(int jarg1, ByteBuffer jarg2, int jarg2_, int jarg3, int jarg4, int jarg5);
	protected final static native long FSOUND_Sample_Alloc(int jarg1, int jarg2, int jarg3, int jarg4, int jarg5, int jarg6, int jarg7);
	protected final static native void FSOUND_Sample_Free(long jarg1);
	protected final static native boolean FSOUND_Sample_Upload(long jarg1, ByteBuffer jarg2, int jarg2_, int jarg3);
	protected final static native boolean FSOUND_Sample_Lock(long jarg1, int jarg2, int jarg3, Pointer jarg4, Pointer jarg5, int[] jarg6, int[] jarg7);
	protected final static native boolean FSOUND_Sample_Lock(long jarg1, int jarg2, int jarg3, Pointer jarg4, Pointer jarg5, IntBuffer jarg6, int jarg6_, IntBuffer jarg7, int jarg7_);
	protected final static native boolean FSOUND_Sample_Unlock(long jarg1, long jarg2, long jarg3, int jarg4, int jarg5);
	protected final static native boolean FSOUND_Sample_Unlock(long jarg1, ByteBuffer jarg2, int jarg2_, ByteBuffer jarg3, int jarg3_, int jarg4, int jarg5);
	protected final static native boolean FSOUND_Sample_SetMode(long jarg1, int jarg2);
	protected final static native boolean FSOUND_Sample_SetLoopPoints(long jarg1, int jarg2, int jarg3);
	protected final static native boolean FSOUND_Sample_SetDefaults(long jarg1, int jarg2, int jarg3, int jarg4, int jarg5);
	protected final static native boolean FSOUND_Sample_SetDefaultsEx(long jarg1, int jarg2, int jarg3, int jarg4, int jarg5, int jarg6, int jarg7, int jarg8);
	protected final static native boolean FSOUND_Sample_SetMinMaxDistance(long jarg1, float jarg2, float jarg3);
	protected final static native boolean FSOUND_Sample_SetMaxPlaybacks(long jarg1, int jarg2);
	protected final static native long FSOUND_Sample_Get(int jarg1);
	protected final static native String FSOUND_Sample_GetName(long jarg1);
	protected final static native int FSOUND_Sample_GetLength(long jarg1);
	protected final static native boolean FSOUND_Sample_GetLoopPoints(long jarg1, int[] jarg2, int[] jarg3);
	protected final static native boolean FSOUND_Sample_GetLoopPoints(long jarg1, IntBuffer jarg2, int jarg2_, IntBuffer jarg3, int jarg3_);
	protected final static native boolean FSOUND_Sample_GetDefaults(long jarg1, int[] jarg2, int[] jarg3, int[] jarg4, int[] jarg5);
	protected final static native boolean FSOUND_Sample_GetDefaults(long jarg1, IntBuffer jarg2, int jarg2_, IntBuffer jarg3, int jarg3_, IntBuffer jarg4, int jarg4_, IntBuffer jarg5, int jarg5_);
	protected final static native boolean FSOUND_Sample_GetDefaultsEx(long jarg1, int[] jarg2, int[] jarg3, int[] jarg4, int[] jarg5, int[] jarg6, int[] jarg7, int[] jarg8);
	protected final static native boolean FSOUND_Sample_GetDefaultsEx(long jarg1, IntBuffer jarg2, int jarg2_, IntBuffer jarg3, int jarg3_, IntBuffer jarg4, int jarg4_, IntBuffer jarg5, int jarg5_, IntBuffer jarg6, int jarg6_, IntBuffer jarg7, int jarg7_, IntBuffer jarg8, int jarg8_);
	protected final static native int FSOUND_Sample_GetMode(long jarg1);
	protected final static native boolean FSOUND_Sample_GetMinMaxDistance(long jarg1, float[] jarg2, float[] jarg3);
	protected final static native boolean FSOUND_Sample_GetMinMaxDistance(long jarg1, FloatBuffer jarg2, int jarg2_, FloatBuffer jarg3, int jarg3_);
	protected final static native int FSOUND_PlaySound(int jarg1, long jarg2);
	protected final static native int FSOUND_PlaySoundEx(int jarg1, long jarg2, long jarg3, boolean jarg4);
	protected final static native boolean FSOUND_StopSound(int jarg1);
	protected final static native boolean FSOUND_SetFrequency(int jarg1, int jarg2);
	protected final static native boolean FSOUND_SetVolume(int jarg1, int jarg2);
	protected final static native boolean FSOUND_SetVolumeAbsolute(int jarg1, int jarg2);
	protected final static native boolean FSOUND_SetPan(int jarg1, int jarg2);
	protected final static native boolean FSOUND_SetSurround(int jarg1, boolean jarg2);
	protected final static native boolean FSOUND_SetMute(int jarg1, boolean jarg2);
	protected final static native boolean FSOUND_SetPriority(int jarg1, int jarg2);
	protected final static native boolean FSOUND_SetReserved(int jarg1, boolean jarg2);
	protected final static native boolean FSOUND_SetPaused(int jarg1, boolean jarg2);
	protected final static native boolean FSOUND_SetLoopMode(int jarg1, int jarg2);
	protected final static native boolean FSOUND_SetCurrentPosition(int jarg1, int jarg2);
	protected final static native boolean FSOUND_3D_SetAttributes(int jarg1, float[] jarg2, float[] jarg3);
	protected final static native boolean FSOUND_3D_SetAttributes(int jarg1, FloatBuffer jarg2, int jarg2_, FloatBuffer jarg3, int jarg3_);
	protected final static native boolean FSOUND_3D_SetMinMaxDistance(int jarg1, float jarg2, float jarg3);
	protected final static native boolean FSOUND_IsPlaying(int jarg1);
	protected final static native int FSOUND_GetFrequency(int jarg1);
	protected final static native int FSOUND_GetVolume(int jarg1);
	protected final static native int FSOUND_GetAmplitude(int jarg1);
	protected final static native int FSOUND_GetPan(int jarg1);
	protected final static native boolean FSOUND_GetSurround(int jarg1);
	protected final static native boolean FSOUND_GetMute(int jarg1);
	protected final static native int FSOUND_GetPriority(int jarg1);
	protected final static native boolean FSOUND_GetReserved(int jarg1);
	protected final static native boolean FSOUND_GetPaused(int jarg1);
	protected final static native int FSOUND_GetLoopMode(int jarg1);
	protected final static native int FSOUND_GetCurrentPosition(int jarg1);
	protected final static native long FSOUND_GetCurrentSample(int jarg1);
	protected final static native boolean FSOUND_GetCurrentLevels(int jarg1, float[] jarg2, float[] jarg3);
	protected final static native boolean FSOUND_GetCurrentLevels(int jarg1, FloatBuffer jarg2, int jarg2_, FloatBuffer jarg3, int jarg3_);
	protected final static native int FSOUND_GetNumSubChannels(int jarg1);
	protected final static native int FSOUND_GetSubChannel(int jarg1, int jarg2);
	protected final static native boolean FSOUND_3D_GetAttributes(int jarg1, float[] jarg2, float[] jarg3);
	protected final static native boolean FSOUND_3D_GetAttributes(int jarg1, FloatBuffer jarg2, int jarg2_, FloatBuffer jarg3, int jarg3_);
	protected final static native boolean FSOUND_3D_GetMinMaxDistance(int jarg1, float[] jarg2, float[] jarg3);
	protected final static native boolean FSOUND_3D_GetMinMaxDistance(int jarg1, FloatBuffer jarg2, int jarg2_, FloatBuffer jarg3, int jarg3_);
	protected final static native void FSOUND_3D_Listener_SetAttributes(float[] jarg1, float[] jarg2, float jarg3, float jarg4, float jarg5, float jarg6, float jarg7, float jarg8);
	protected final static native void FSOUND_3D_Listener_SetAttributes(FloatBuffer jarg1, int jarg1_, FloatBuffer jarg2, int jarg2_, float jarg3, float jarg4, float jarg5, float jarg6, float jarg7, float jarg8);
	protected final static native void FSOUND_3D_Listener_GetAttributes(float[] jarg1, float[] jarg2, float[] jarg3, float[] jarg4, float[] jarg5, float[] jarg6, float[] jarg7, float[] jarg8);
	protected final static native void FSOUND_3D_Listener_GetAttributes(FloatBuffer jarg1, int jarg1_, FloatBuffer jarg2, int jarg2_, FloatBuffer jarg3, int jarg3_, FloatBuffer jarg4, int jarg4_, FloatBuffer jarg5, int jarg5_, FloatBuffer jarg6, int jarg6_, FloatBuffer jarg7, int jarg7_, FloatBuffer jarg8, int jarg8_);
	protected final static native void FSOUND_3D_Listener_SetCurrent(int jarg1, int jarg2);
	protected final static native void FSOUND_3D_SetDopplerFactor(float jarg1);
	protected final static native void FSOUND_3D_SetDistanceFactor(float jarg1);
	protected final static native void FSOUND_3D_SetRolloffFactor(float jarg1);
	protected final static native int FSOUND_FX_Enable(int jarg1, int jarg2);
	protected final static native boolean FSOUND_FX_Disable(int jarg1);
	protected final static native boolean FSOUND_FX_SetChorus(int jarg1, float jarg2, float jarg3, float jarg4, float jarg5, int jarg6, float jarg7, int jarg8);
	protected final static native boolean FSOUND_FX_SetCompressor(int jarg1, float jarg2, float jarg3, float jarg4, float jarg5, float jarg6, float jarg7);
	protected final static native boolean FSOUND_FX_SetDistortion(int jarg1, float jarg2, float jarg3, float jarg4, float jarg5, float jarg6);
	protected final static native boolean FSOUND_FX_SetEcho(int jarg1, float jarg2, float jarg3, float jarg4, float jarg5, boolean jarg6);
	protected final static native boolean FSOUND_FX_SetFlanger(int jarg1, float jarg2, float jarg3, float jarg4, float jarg5, int jarg6, float jarg7, int jarg8);
	protected final static native boolean FSOUND_FX_SetGargle(int jarg1, int jarg2, int jarg3);
	protected final static native boolean FSOUND_FX_SetI3DL2Reverb(int jarg1, int jarg2, int jarg3, float jarg4, float jarg5, float jarg6, int jarg7, float jarg8, int jarg9, float jarg10, float jarg11, float jarg12, float jarg13);
	protected final static native boolean FSOUND_FX_SetParamEQ(int jarg1, float jarg2, float jarg3, float jarg4);
	protected final static native boolean FSOUND_FX_SetWavesReverb(int jarg1, float jarg2, float jarg3, float jarg4, float jarg5);
	protected final static native boolean FSOUND_Stream_SetBufferSize(int jarg1);
	protected final static native long FSOUND_Stream_Open(String jarg1, int jarg2, int jarg3, int jarg4);
	protected final static native long FSOUND_Stream_Open(ByteBuffer jarg1, int jarg1_, int jarg2, int jarg3, int jarg4);
	protected final static native long FSOUND_Stream_Create(FSOUND_STREAMCALLBACK jarg1, int jarg2, int jarg3, int jarg4, long jarg5);
	protected final static native boolean FSOUND_Stream_Close(long jarg1);
	protected final static native int FSOUND_Stream_Play(int jarg1, long jarg2);
	protected final static native int FSOUND_Stream_PlayEx(int jarg1, long jarg2, long jarg3, boolean jarg4);
	protected final static native boolean FSOUND_Stream_Stop(long jarg1);
	protected final static native boolean FSOUND_Stream_SetPosition(long jarg1, int jarg2);
	protected final static native int FSOUND_Stream_GetPosition(long jarg1);
	protected final static native boolean FSOUND_Stream_SetTime(long jarg1, int jarg2);
	protected final static native int FSOUND_Stream_GetTime(long jarg1);
	protected final static native int FSOUND_Stream_GetLength(long jarg1);
	protected final static native int FSOUND_Stream_GetLengthMs(long jarg1);
	protected final static native boolean FSOUND_Stream_SetMode(long jarg1, int jarg2);
	protected final static native int FSOUND_Stream_GetMode(long jarg1);
	protected final static native boolean FSOUND_Stream_SetLoopPoints(long jarg1, int jarg2, int jarg3);
	protected final static native boolean FSOUND_Stream_SetLoopCount(long jarg1, int jarg2);
	protected final static native int FSOUND_Stream_GetOpenState(long jarg1);
	protected final static native long FSOUND_Stream_GetSample(long jarg1);
	protected final static native long FSOUND_Stream_CreateDSP(long jarg1, FSOUND_DSPCALLBACK jarg2, int jarg3, long jarg4);
	protected final static native boolean FSOUND_Stream_SetEndCallback(long jarg1, FSOUND_STREAMCALLBACK jarg2, long jarg3);
	protected final static native boolean FSOUND_Stream_SetSyncCallback(long jarg1, FSOUND_STREAMCALLBACK jarg2, long jarg3);
	protected final static native long FSOUND_Stream_AddSyncPoint(long jarg1, int jarg2, String jarg3);
	protected final static native boolean FSOUND_Stream_DeleteSyncPoint(long jarg1);
	protected final static native int FSOUND_Stream_GetNumSyncPoints(long jarg1);
	protected final static native long FSOUND_Stream_GetSyncPoint(long jarg1, int jarg2);
	protected final static native String FSOUND_Stream_GetSyncPointInfo(long jarg1, int[] jarg2);
	protected final static native String FSOUND_Stream_GetSyncPointInfo(long jarg1, IntBuffer jarg2, int jarg2_);
	protected final static native boolean FSOUND_Stream_SetSubStream(long jarg1, int jarg2);
	protected final static native int FSOUND_Stream_GetNumSubStreams(long jarg1);
	protected final static native boolean FSOUND_Stream_SetSubStreamSentence(long jarg1, int[] jarg2, int jarg3);
	protected final static native boolean FSOUND_Stream_SetSubStreamSentence(long jarg1, IntBuffer jarg2, int jarg2_, int jarg3);
	protected final static native boolean FSOUND_Stream_GetNumTagFields(long jarg1, int[] jarg2);
	protected final static native boolean FSOUND_Stream_GetNumTagFields(long jarg1, IntBuffer jarg2, int jarg2_);
	protected final static native boolean FSOUND_Stream_GetTagField(long jarg1, int jarg2, int[] jarg3, Pointer jarg4, Pointer jarg5, int[] jarg6);
	protected final static native boolean FSOUND_Stream_GetTagField(long jarg1, int jarg2, IntBuffer jarg3, int jarg3_, Pointer jarg4, Pointer jarg5, IntBuffer jarg6, int jarg6_);
	protected final static native boolean FSOUND_Stream_FindTagField(long jarg1, int jarg2, String jarg3, Pointer jarg4, int[] jarg5);
	protected final static native boolean FSOUND_Stream_FindTagField(long jarg1, int jarg2, String jarg3, Pointer jarg4, IntBuffer jarg5, int jarg5_);
	protected final static native boolean FSOUND_Stream_Net_SetProxy(String jarg1);
	protected final static native boolean FSOUND_Stream_Net_SetTimeout(int jarg1);
	protected final static native String FSOUND_Stream_Net_GetLastServerStatus();
	protected final static native boolean FSOUND_Stream_Net_SetBufferProperties(int jarg1, int jarg2, int jarg3);
	protected final static native boolean FSOUND_Stream_Net_GetBufferProperties(int[] jarg1, int[] jarg2, int[] jarg3);
	protected final static native boolean FSOUND_Stream_Net_GetBufferProperties(IntBuffer jarg1, int jarg1_, IntBuffer jarg2, int jarg2_, IntBuffer jarg3, int jarg3_);
	protected final static native boolean FSOUND_Stream_Net_SetMetadataCallback(long jarg1, FSOUND_METADATACALLBACK jarg2, long jarg3);
	protected final static native boolean FSOUND_Stream_Net_GetStatus(long jarg1, int[] jarg2, int[] jarg3, int[] jarg4, int[] jarg5);
	protected final static native boolean FSOUND_Stream_Net_GetStatus(long jarg1, IntBuffer jarg2, int jarg2_, IntBuffer jarg3, int jarg3_, IntBuffer jarg4, int jarg4_, IntBuffer jarg5, int jarg5_);
	protected final static native boolean FSOUND_CD_Play(char jarg1, int jarg2);
	protected final static native void FSOUND_CD_SetPlayMode(char jarg1, byte jarg2);
	protected final static native boolean FSOUND_CD_Stop(char jarg1);
	protected final static native boolean FSOUND_CD_SetPaused(char jarg1, boolean jarg2);
	protected final static native boolean FSOUND_CD_SetVolume(char jarg1, int jarg2);
	protected final static native boolean FSOUND_CD_SetTrackTime(char jarg1, int jarg2);
	protected final static native boolean FSOUND_CD_GetPaused(char jarg1);
	protected final static native int FSOUND_CD_GetTrack(char jarg1);
	protected final static native int FSOUND_CD_GetNumTracks(char jarg1);
	protected final static native int FSOUND_CD_GetVolume(char jarg1);
	protected final static native boolean FSOUND_CD_OpenTray(char jarg1, boolean jarg2);
	protected final static native int FSOUND_CD_GetTrackLength(char jarg1, int jarg2);
	protected final static native int FSOUND_CD_GetTrackTime(char jarg1);
	protected final static native long FSOUND_DSP_Create(FSOUND_DSPCALLBACK jarg1, int jarg2, long jarg3);
	protected final static native void FSOUND_DSP_Free(long jarg1);
	protected final static native void FSOUND_DSP_SetPriority(long jarg1, int jarg2);
	protected final static native int FSOUND_DSP_GetPriority(long jarg1);
	protected final static native void FSOUND_DSP_SetActive(long jarg1, boolean jarg2);
	protected final static native boolean FSOUND_DSP_GetActive(long jarg1);
	protected final static native long FSOUND_DSP_GetClearUnit();
	protected final static native long FSOUND_DSP_GetSFXUnit();
	protected final static native long FSOUND_DSP_GetMusicUnit();
	protected final static native long FSOUND_DSP_GetFFTUnit();
	protected final static native long FSOUND_DSP_GetClipAndCopyUnit();
	protected final static native boolean FSOUND_DSP_MixBuffers(ByteBuffer jarg1, int jarg1_, ByteBuffer jarg2, int jarg2_, int jarg3, int jarg4, int jarg5, int jarg6, int jarg7);
	protected final static native void FSOUND_DSP_ClearMixBuffer();
	protected final static native int FSOUND_DSP_GetBufferLength();
	protected final static native int FSOUND_DSP_GetBufferLengthTotal();
	protected final static native ByteBuffer FSOUND_DSP_GetSpectrum();
	protected final static native boolean FSOUND_Reverb_SetProperties(long jarg1);
	protected final static native boolean FSOUND_Reverb_GetProperties(long jarg1);
	protected final static native boolean FSOUND_Reverb_SetChannelProperties(int jarg1, long jarg2);
	protected final static native boolean FSOUND_Reverb_GetChannelProperties(int jarg1, long jarg2);
	protected final static native boolean FSOUND_Record_SetDriver(int jarg1);
	protected final static native int FSOUND_Record_GetNumDrivers();
	protected final static native String FSOUND_Record_GetDriverName(int jarg1);
	protected final static native int FSOUND_Record_GetDriver();
	protected final static native boolean FSOUND_Record_StartSample(long jarg1, boolean jarg2);
	protected final static native boolean FSOUND_Record_Stop();
	protected final static native int FSOUND_Record_GetPosition();

								/*FMUSIC API*/

	protected final static native long FMUSIC_LoadSong(String jarg1);
	protected final static native long FMUSIC_LoadSongEx(String jarg1, int jarg2, int jarg3, int jarg4, int[] jarg5, int jarg6);
	protected final static native long FMUSIC_LoadSongEx(String jarg1, int jarg2, int jarg3, int jarg4, IntBuffer jarg5, int jarg5_, int jarg6);
	protected final static native long FMUSIC_LoadSongEx(ByteBuffer jarg1, int jarg1_, int jarg2, int jarg3, int jarg4, int[] jarg5, int jarg6);
	protected final static native long FMUSIC_LoadSongEx(ByteBuffer jarg1, int jarg1_, int jarg2, int jarg3, int jarg4, IntBuffer jarg5, int jarg5_, int jarg6);
	protected final static native int FMUSIC_GetOpenState(long jarg1);
	protected final static native boolean FMUSIC_FreeSong(long jarg1);
	protected final static native boolean FMUSIC_PlaySong(long jarg1);
	protected final static native boolean FMUSIC_StopSong(long jarg1);
	protected final static native void FMUSIC_StopAllSongs();
	protected final static native boolean FMUSIC_SetZxxCallback(long jarg1, FMUSIC_CALLBACK jarg2);
	protected final static native boolean FMUSIC_SetRowCallback(long jarg1, FMUSIC_CALLBACK jarg2, int jarg3);
	protected final static native boolean FMUSIC_SetOrderCallback(long jarg1, FMUSIC_CALLBACK jarg2, int jarg3);
	protected final static native boolean FMUSIC_SetInstCallback(long jarg1, FMUSIC_CALLBACK jarg2, int jarg3);
	protected final static native boolean FMUSIC_SetSample(long jarg1, int jarg2, long jarg3);
	protected final static native boolean FMUSIC_SetUserData(long jarg1, long jarg2);
	protected final static native boolean FMUSIC_OptimizeChannels(long jarg1, int jarg2, int jarg3);
	protected final static native boolean FMUSIC_SetReverb(boolean jarg1);
	protected final static native boolean FMUSIC_SetLooping(long jarg1, boolean jarg2);
	protected final static native boolean FMUSIC_SetOrder(long jarg1, int jarg2);
	protected final static native boolean FMUSIC_SetPaused(long jarg1, boolean jarg2);
	protected final static native boolean FMUSIC_SetMasterVolume(long jarg1, int jarg2);
	protected final static native boolean FMUSIC_SetMasterSpeed(long jarg1, float jarg2);
	protected final static native boolean FMUSIC_SetPanSeperation(long jarg1, float jarg2);
	protected final static native String FMUSIC_GetName(long jarg1);
	protected final static native int FMUSIC_GetType(long jarg1);
	protected final static native int FMUSIC_GetNumOrders(long jarg1);
	protected final static native int FMUSIC_GetNumPatterns(long jarg1);
	protected final static native int FMUSIC_GetNumInstruments(long jarg1);
	protected final static native int FMUSIC_GetNumSamples(long jarg1);
	protected final static native int FMUSIC_GetNumChannels(long jarg1);
	protected final static native long FMUSIC_GetSample(long jarg1, int jarg2);
	protected final static native int FMUSIC_GetPatternLength(long jarg1, int jarg2);
	protected final static native boolean FMUSIC_IsFinished(long jarg1);
	protected final static native boolean FMUSIC_IsPlaying(long jarg1);
	protected final static native int FMUSIC_GetMasterVolume(long jarg1);
	protected final static native int FMUSIC_GetGlobalVolume(long jarg1);
	protected final static native int FMUSIC_GetOrder(long jarg1);
	protected final static native int FMUSIC_GetPattern(long jarg1);
	protected final static native int FMUSIC_GetSpeed(long jarg1);
	protected final static native int FMUSIC_GetBPM(long jarg1);
	protected final static native int FMUSIC_GetRow(long jarg1);
	protected final static native boolean FMUSIC_GetPaused(long jarg1);
	protected final static native int FMUSIC_GetTime(long jarg1);
	protected final static native int FMUSIC_GetRealChannel(long jarg1, int jarg2);
	protected final static native long FMUSIC_GetUserData(long jarg1);

							/*fmod_errors.h*/

	protected final static native String FMOD_ErrorString(int jarg1);
}