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

package org.jouvieje.FmodDyn;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.jouvieje.Fmod.Init;
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
import org.jouvieje.Fmod.Defines.INIT_MODES;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Misc.Pointer;

class FmodDynJNI
{
	static
	{
		//If the libraries are not loaded, tries to load them here
		if(!Init.isFmodDynLibrariesLoaded())
		{
			try
			{
				Init.loadLibraries(INIT_MODES.INIT_FMOD_DYN);
			}
			catch(InitException e)
			{
				System.err.println(e.getMessage());
				System.out.println("Use Init.loadLibraries(INIT_FMOD_DYN) to control the loading of the libraries for FMOD Dyn.");
				System.exit(1);
			}
		}
	}

									/*fmoddyn.h*/

	protected final static native long FMOD_CreateInstance(String jarg1);
	protected final static native void FMOD_FreeInstance(long jarg1);

									/*FMOD_INSTANCE*/

	protected final static native boolean FMOD_INSTANCE_FSOUND_SetOutput(long jarg1, int jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetDriver(long jarg1, int jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetMixer(long jarg1, int jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetBufferSize(long jarg1, int jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetHWND(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetMinHardwareChannels(long jarg1, int jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetMaxHardwareChannels(long jarg1, int jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetMemorySystem(long jarg1, ByteBuffer jarg2, int jarg2_, int jarg3, FSOUND_ALLOCCALLBACK jarg4, FSOUND_REALLOCCALLBACK jarg5, FSOUND_FREECALLBACK jarg6);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Init(long jarg1, int jarg2, int jarg3, int jarg4);
	protected final static native void FMOD_INSTANCE_FSOUND_Close(long jarg1);
	protected final static native void FMOD_INSTANCE_FSOUND_Update(long jarg1);
	protected final static native void FMOD_INSTANCE_FSOUND_SetSpeakerMode(long jarg1, int jarg2);
	protected final static native void FMOD_INSTANCE_FSOUND_SetSFXMasterVolume(long jarg1, int jarg2);
	protected final static native void FMOD_INSTANCE_FSOUND_SetPanSeperation(long jarg1, float jarg2);
	protected final static native void FMOD_INSTANCE_FSOUND_File_SetCallbacks(long jarg1, FSOUND_OPENCALLBACK jarg2, FSOUND_CLOSECALLBACK jarg3, FSOUND_READCALLBACK jarg4, FSOUND_SEEKCALLBACK jarg5, FSOUND_TELLCALLBACK jarg6);
	protected final static native int FMOD_INSTANCE_FSOUND_GetError(long jarg1);
	protected final static native float FMOD_INSTANCE_FSOUND_GetVersion(long jarg1);
	protected final static native int FMOD_INSTANCE_FSOUND_GetOutput(long jarg1);
	protected final static native long FMOD_INSTANCE_FSOUND_GetOutputHandle(long jarg1);
	protected final static native int FMOD_INSTANCE_FSOUND_GetDriver(long jarg1);
	protected final static native int FMOD_INSTANCE_FSOUND_GetMixer(long jarg1);
	protected final static native int FMOD_INSTANCE_FSOUND_GetNumDrivers(long jarg1);
	protected final static native String FMOD_INSTANCE_FSOUND_GetDriverName(long jarg1, int jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_GetDriverCaps(long jarg1, int jarg2, int[] jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_GetDriverCaps(long jarg1, int jarg2, IntBuffer jarg3, int jarg3_);
	protected final static native int FMOD_INSTANCE_FSOUND_GetOutputRate(long jarg1);
	protected final static native int FMOD_INSTANCE_FSOUND_GetMaxChannels(long jarg1);
	protected final static native int FMOD_INSTANCE_FSOUND_GetMaxSamples(long jarg1);
	protected final static native int FMOD_INSTANCE_FSOUND_GetSpeakerMode(long jarg1);
	protected final static native int FMOD_INSTANCE_FSOUND_GetSFXMasterVolume(long jarg1);
	protected final static native boolean FMOD_INSTANCE_FSOUND_GetNumHWChannels(long jarg1, int[] jarg2, int[] jarg3, int[] jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_GetNumHWChannels(long jarg1, IntBuffer jarg2, int jarg2_, IntBuffer jarg3, int jarg3_, IntBuffer jarg4, int jarg4_);
	protected final static native int FMOD_INSTANCE_FSOUND_GetChannelsPlaying(long jarg1);
	protected final static native float FMOD_INSTANCE_FSOUND_GetCPUUsage(long jarg1);
	protected final static native void FMOD_INSTANCE_FSOUND_GetMemoryStats(long jarg1, int[] jarg2, int[] jarg3);
	protected final static native void FMOD_INSTANCE_FSOUND_GetMemoryStats(long jarg1, IntBuffer jarg2, int jarg2_, IntBuffer jarg3, int jarg3_);
	protected final static native long FMOD_INSTANCE_FSOUND_Sample_Load(long jarg1, int jarg2, String jarg3, int jarg4, int jarg5, int jarg6);
	protected final static native long FMOD_INSTANCE_FSOUND_Sample_Load(long jarg1, int jarg2, ByteBuffer jarg3, int jarg3_, int jarg4, int jarg5, int jarg6);
	protected final static native long FMOD_INSTANCE_FSOUND_Sample_Alloc(long jarg1, int jarg2, int jarg3, int jarg4, int jarg5, int jarg6, int jarg7, int jarg8);
	protected final static native void FMOD_INSTANCE_FSOUND_Sample_Free(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_Upload(long jarg1, long jarg2, ByteBuffer jarg3, int jarg3_, int jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_Lock(long jarg1, long jarg2, int jarg3, int jarg4, Pointer jarg5, Pointer jarg6, int[] jarg7, int[] jarg8);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_Lock(long jarg1, long jarg2, int jarg3, int jarg4, Pointer jarg5, Pointer jarg6, IntBuffer jarg7, int jarg7_, IntBuffer jarg8, int jarg8_);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_Unlock(long jarg1, long jarg2, long jarg3, long jarg4, int jarg5, int jarg6);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_Unlock(long jarg1, long jarg2, ByteBuffer jarg3, int jarg3_, ByteBuffer jarg4, int jarg4_, int jarg5, int jarg6);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_SetMode(long jarg1, long jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_SetLoopPoints(long jarg1, long jarg2, int jarg3, int jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_SetDefaults(long jarg1, long jarg2, int jarg3, int jarg4, int jarg5, int jarg6);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_SetDefaultsEx(long jarg1, long jarg2, int jarg3, int jarg4, int jarg5, int jarg6, int jarg7, int jarg8, int jarg9);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_SetMinMaxDistance(long jarg1, long jarg2, float jarg3, float jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_SetMaxPlaybacks(long jarg1, long jarg2, int jarg3);
	protected final static native long FMOD_INSTANCE_FSOUND_Sample_Get(long jarg1, int jarg2);
	protected final static native String FMOD_INSTANCE_FSOUND_Sample_GetName(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_Sample_GetLength(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_GetLoopPoints(long jarg1, long jarg2, int[] jarg3, int[] jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_GetLoopPoints(long jarg1, long jarg2, IntBuffer jarg3, int jarg3_, IntBuffer jarg4, int jarg4_);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_GetDefaults(long jarg1, long jarg2, int[] jarg3, int[] jarg4, int[] jarg5, int[] jarg6);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_GetDefaults(long jarg1, long jarg2, IntBuffer jarg3, int jarg3_, IntBuffer jarg4, int jarg4_, IntBuffer jarg5, int jarg5_, IntBuffer jarg6, int jarg6_);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_GetDefaultsEx(long jarg1, long jarg2, int[] jarg3, int[] jarg4, int[] jarg5, int[] jarg6, int[] jarg7, int[] jarg8, int[] jarg9);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_GetDefaultsEx(long jarg1, long jarg2, IntBuffer jarg3, int jarg3_, IntBuffer jarg4, int jarg4__, IntBuffer jarg5, int jarg5_, IntBuffer jarg6, int jarg6_, IntBuffer jarg7, int jarg7_, IntBuffer jarg8, int jarg8_, IntBuffer jarg9, int jarg9_);
	protected final static native int FMOD_INSTANCE_FSOUND_Sample_GetMode(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_GetMinMaxDistance(long jarg1, long jarg2, float[] jarg3, float[] jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Sample_GetMinMaxDistance(long jarg1, long jarg2, FloatBuffer jarg3, int jarg3_, FloatBuffer jarg4, int jarg4_);
	protected final static native int FMOD_INSTANCE_FSOUND_PlaySound(long jarg1, int jarg2, long jarg3);
	protected final static native int FMOD_INSTANCE_FSOUND_PlaySoundEx(long jarg1, int jarg2, long jarg3, long jarg4, boolean jarg5);
	protected final static native boolean FMOD_INSTANCE_FSOUND_StopSound(long jarg1, int jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetFrequency(long jarg1, int jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetVolume(long jarg1, int jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetVolumeAbsolute(long jarg1, int jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetPan(long jarg1, int jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetSurround(long jarg1, int jarg2, boolean jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetMute(long jarg1, int jarg2, boolean jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetPriority(long jarg1, int jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetReserved(long jarg1, int jarg2, boolean jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetPaused(long jarg1, int jarg2, boolean jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetLoopMode(long jarg1, int jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_SetCurrentPosition(long jarg1, int jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_3D_SetAttributes(long jarg1, int jarg2, float[] jarg3, float[] jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_3D_SetAttributes(long jarg1, int jarg2, FloatBuffer jarg3, int jarg3_, FloatBuffer jarg4, int jarg4_);
	protected final static native boolean FMOD_INSTANCE_FSOUND_3D_SetMinMaxDistance(long jarg1, int jarg2, float jarg3, float jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_IsPlaying(long jarg1, int jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_GetFrequency(long jarg1, int jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_GetVolume(long jarg1, int jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_GetAmplitude(long jarg1, int jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_GetPan(long jarg1, int jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_GetSurround(long jarg1, int jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_GetMute(long jarg1, int jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_GetPriority(long jarg1, int jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_GetReserved(long jarg1, int jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_GetPaused(long jarg1, int jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_GetLoopMode(long jarg1, int jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_GetCurrentPosition(long jarg1, int jarg2);
	protected final static native long FMOD_INSTANCE_FSOUND_GetCurrentSample(long jarg1, int jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_GetCurrentLevels(long jarg1, int jarg2, float[] jarg3, float[] jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_GetCurrentLevels(long jarg1, int jarg2, FloatBuffer jarg3, int jarg3_, FloatBuffer jarg4, int jarg4_);
	protected final static native int FMOD_INSTANCE_FSOUND_GetNumSubChannels(long jarg1, int jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_GetSubChannel(long jarg1, int jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_3D_GetAttributes(long jarg1, int jarg2, float[] jarg3, float[] jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_3D_GetAttributes(long jarg1, int jarg2, FloatBuffer jarg3, int jarg3_, FloatBuffer jarg4, int jarg4_);
	protected final static native boolean FMOD_INSTANCE_FSOUND_3D_GetMinMaxDistance(long jarg1, int jarg2, float[] jarg3, float[] jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_3D_GetMinMaxDistance(long jarg1, int jarg2, FloatBuffer jarg3, int jarg3_, FloatBuffer jarg4, int jarg4_);
	protected final static native void FMOD_INSTANCE_FSOUND_3D_SetDopplerFactor(long jarg1, float jarg2);
	protected final static native void FMOD_INSTANCE_FSOUND_3D_SetDistanceFactor(long jarg1, float jarg2);
	protected final static native void FMOD_INSTANCE_FSOUND_3D_SetRolloffFactor(long jarg1, float jarg2);
	protected final static native void FMOD_INSTANCE_FSOUND_3D_Listener_SetCurrent(long jarg1, int jarg2, int jarg3);
	protected final static native void FMOD_INSTANCE_FSOUND_3D_Listener_SetAttributes(long jarg1, float[] jarg2, float[] jarg3, float jarg4, float jarg5, float jarg6, float jarg7, float jarg8, float jarg9);
	protected final static native void FMOD_INSTANCE_FSOUND_3D_Listener_SetAttributes(long jarg1, FloatBuffer jarg2, int jarg2_, FloatBuffer jarg3, int jarg3_, float jarg4, float jarg5, float jarg6, float jarg7, float jarg8, float jarg9);
	protected final static native void FMOD_INSTANCE_FSOUND_3D_Listener_GetAttributes(long jarg1, float[] jarg2, float[] jarg3, float[] jarg4, float[] jarg5, float[] jarg6, float[] jarg7, float[] jarg8, float[] jarg9);
	protected final static native void FMOD_INSTANCE_FSOUND_3D_Listener_GetAttributes(long jarg1, FloatBuffer jarg2, int jarg2_, FloatBuffer jarg3, int jarg3_, FloatBuffer jarg4, int jarg4_, FloatBuffer jarg5, int jarg5_, FloatBuffer jarg6, int jarg6_, FloatBuffer jarg7, int jarg7_, FloatBuffer jarg8, int jarg8_, FloatBuffer jarg9, int jarg9_);
	protected final static native int FMOD_INSTANCE_FSOUND_FX_Enable(long jarg1, int jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_FX_Disable(long jarg1, int jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_FX_SetChorus(long jarg1, int jarg2, float jarg3, float jarg4, float jarg5, float jarg6, int jarg7, float jarg8, int jarg9);
	protected final static native boolean FMOD_INSTANCE_FSOUND_FX_SetCompressor(long jarg1, int jarg2, float jarg3, float jarg4, float jarg5, float jarg6, float jarg7, float jarg8);
	protected final static native boolean FMOD_INSTANCE_FSOUND_FX_SetDistortion(long jarg1, int jarg2, float jarg3, float jarg4, float jarg5, float jarg6, float jarg7);
	protected final static native boolean FMOD_INSTANCE_FSOUND_FX_SetEcho(long jarg1, int jarg2, float jarg3, float jarg4, float jarg5, float jarg6, boolean jarg7);
	protected final static native boolean FMOD_INSTANCE_FSOUND_FX_SetFlanger(long jarg1, int jarg2, float jarg3, float jarg4, float jarg5, float jarg6, int jarg7, float jarg8, int jarg9);
	protected final static native boolean FMOD_INSTANCE_FSOUND_FX_SetGargle(long jarg1, int jarg2, int jarg3, int jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_FX_SetI3DL2Reverb(long jarg1, int jarg2, int jarg3, int jarg4, float jarg5, float jarg6, float jarg7, int jarg8, float jarg9, int jarg10, float jarg11, float jarg12, float jarg13, float jarg14);
	protected final static native boolean FMOD_INSTANCE_FSOUND_FX_SetParamEQ(long jarg1, int jarg2, float jarg3, float jarg4, float jarg5);
	protected final static native boolean FMOD_INSTANCE_FSOUND_FX_SetWavesReverb(long jarg1, int jarg2, float jarg3, float jarg4, float jarg5, float jarg6);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_SetBufferSize(long jarg1, int jarg2);
	protected final static native long FMOD_INSTANCE_FSOUND_Stream_Open(long jarg1, String jarg2, int jarg3, int jarg4, int jarg5);
	protected final static native long FMOD_INSTANCE_FSOUND_Stream_Open(long jarg1, ByteBuffer jarg2, int jarg2_, int jarg3, int jarg4, int jarg5);
	protected final static native long FMOD_INSTANCE_FSOUND_Stream_Create(long jarg1, FSOUND_STREAMCALLBACK jarg2, int jarg3, int jarg4, int jarg5, long jarg6);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_Close(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_Stream_Play(long jarg1, int jarg2, long jarg3);
	protected final static native int FMOD_INSTANCE_FSOUND_Stream_PlayEx(long jarg1, int jarg2, long jarg3, long jarg4, boolean jarg5);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_Stop(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_SetPosition(long jarg1, long jarg2, int jarg3);
	protected final static native int FMOD_INSTANCE_FSOUND_Stream_GetPosition(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_SetTime(long jarg1, long jarg2, int jarg3);
	protected final static native int FMOD_INSTANCE_FSOUND_Stream_GetTime(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_Stream_GetLength(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_Stream_GetLengthMs(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_SetMode(long jarg1, long jarg2, int jarg3);
	protected final static native int FMOD_INSTANCE_FSOUND_Stream_GetMode(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_SetLoopPoints(long jarg1, long jarg2, int jarg3, int jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_SetLoopCount(long jarg1, long jarg2, int jarg3);
	protected final static native int FMOD_INSTANCE_FSOUND_Stream_GetOpenState(long jarg1, long jarg2);
	protected final static native long FMOD_INSTANCE_FSOUND_Stream_GetSample(long jarg1, long jarg2);
	protected final static native long FMOD_INSTANCE_FSOUND_Stream_CreateDSP(long jarg1, long jarg2, FSOUND_DSPCALLBACK jarg3, int jarg4, long jarg5);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_SetEndCallback(long jarg1, long jarg2, FSOUND_STREAMCALLBACK jarg3, long jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_SetSyncCallback(long jarg1, long jarg2, FSOUND_STREAMCALLBACK jarg3, long jarg4);
	protected final static native long FMOD_INSTANCE_FSOUND_Stream_AddSyncPoint(long jarg1, long jarg2, int jarg3, String jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_DeleteSyncPoint(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_Stream_GetNumSyncPoints(long jarg1, long jarg2);
	protected final static native long FMOD_INSTANCE_FSOUND_Stream_GetSyncPoint(long jarg1, long jarg2, int jarg3);
	protected final static native String FMOD_INSTANCE_FSOUND_Stream_GetSyncPointInfo(long jarg1, long jarg2, int[] jarg3);
	protected final static native String FMOD_INSTANCE_FSOUND_Stream_GetSyncPointInfo(long jarg1, long jarg2, IntBuffer jarg3, int jarg3_);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_SetSubStream(long jarg1, long jarg2, int jarg3);
	protected final static native int FMOD_INSTANCE_FSOUND_Stream_GetNumSubStreams(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_SetSubStreamSentence(long jarg1, long jarg2, int[] jarg3, int jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_SetSubStreamSentence(long jarg1, long jarg2, IntBuffer jarg3, int jarg3_, int jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_GetNumTagFields(long jarg1, long jarg2, int[] jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_GetNumTagFields(long jarg1, long jarg2, IntBuffer jarg3, int jarg3_);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_GetTagField(long jarg1, long jarg2, int jarg3, int[] jarg4, Pointer jarg5, Pointer jarg6, int[] jarg7);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_GetTagField(long jarg1, long jarg2, int jarg3, IntBuffer jarg4, int jarg4_, Pointer jarg5, Pointer jarg6, IntBuffer jarg7, int jarg7_);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_FindTagField(long jarg1, long jarg2, int jarg3, String jarg4, Pointer jarg5, int[] jarg6);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_FindTagField(long jarg1, long jarg2, int jarg3, String jarg4, Pointer jarg5, IntBuffer jarg6, int jarg6_);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_Net_SetProxy(long jarg1, String jarg2);
	protected final static native boolean FSOUND_Stream_Net_SetTimeout(long jarg1, int jarg2);
	protected final static native String FMOD_INSTANCE_FSOUND_Stream_Net_GetLastServerStatus(long jarg1);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_Net_SetBufferProperties(long jarg1, int jarg2, int jarg3, int jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_Net_GetBufferProperties(long jarg1, int[] jarg2, int[] jarg3, int[] jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_Net_GetBufferProperties(long jarg1, IntBuffer jarg2, int jarg2_, IntBuffer jarg3, int jarg3_, IntBuffer jarg4, int jarg4_);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_Net_SetMetadataCallback(long jarg1, long jarg2, FSOUND_METADATACALLBACK jarg3, long jarg4);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_Net_GetStatus(long jarg1, long jarg2, int[] jarg3, int[] jarg4, int[] jarg5, int[] jarg6);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Stream_Net_GetStatus(long jarg1, long jarg2, IntBuffer jarg3, int jarg3_, IntBuffer jarg4, int jarg4_, IntBuffer jarg5, int jarg5_, IntBuffer jarg6, int jarg6_);
	protected final static native boolean FMOD_INSTANCE_FSOUND_CD_Play(long jarg1, char jarg2, int jarg3);
	protected final static native void FMOD_INSTANCE_FSOUND_CD_SetPlayMode(long jarg1, char jarg2, byte jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_CD_Stop(long jarg1, char jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_CD_SetPaused(long jarg1, char jarg2, boolean jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_CD_SetVolume(long jarg1, char jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_CD_SetTrackTime(long jarg1, char jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_CD_Eject(long jarg1, char jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_CD_OpenTray(long pointer, char drive, boolean open);
	protected final static native boolean FMOD_INSTANCE_FSOUND_CD_GetPaused(long jarg1, char jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_CD_GetTrack(long jarg1, char jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_CD_GetNumTracks(long jarg1, char jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_CD_GetVolume(long jarg1, char jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_CD_GetTrackLength(long jarg1, char jarg2, int jarg3);
	protected final static native int FMOD_INSTANCE_FSOUND_CD_GetTrackTime(long jarg1, char jarg2);
	protected final static native long FMOD_INSTANCE_FSOUND_DSP_Create(long jarg1, FSOUND_DSPCALLBACK jarg2, int jarg3, long jarg4);
	protected final static native void FMOD_INSTANCE_FSOUND_DSP_Free(long jarg1, long jarg2);
	protected final static native void FMOD_INSTANCE_FSOUND_DSP_SetPriority(long jarg1, long jarg2, int jarg3);
	protected final static native int FMOD_INSTANCE_FSOUND_DSP_GetPriority(long jarg1, long jarg2);
	protected final static native void FMOD_INSTANCE_FSOUND_DSP_SetActive(long jarg1, long jarg2, boolean jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_DSP_GetActive(long jarg1, long jarg2);
	protected final static native long FMOD_INSTANCE_FSOUND_DSP_GetClearUnit(long jarg1);
	protected final static native long FMOD_INSTANCE_FSOUND_DSP_GetSFXUnit(long jarg1);
	protected final static native long FMOD_INSTANCE_FSOUND_DSP_GetMusicUnit(long jarg1);
	protected final static native long FMOD_INSTANCE_FSOUND_DSP_GetFFTUnit(long jarg1);
	protected final static native long FMOD_INSTANCE_FSOUND_DSP_GetClipAndCopyUnit(long jarg1);
	protected final static native boolean FMOD_INSTANCE_FSOUND_DSP_MixBuffers(long jarg1, ByteBuffer jarg2, int jarg2_, ByteBuffer jarg3, int jarg3_, int jarg4, int jarg5, int jarg6, int jarg7, int jarg8);
	protected final static native void FMOD_INSTANCE_FSOUND_DSP_ClearMixBuffer(long jarg1);
	protected final static native int FMOD_INSTANCE_FSOUND_DSP_GetBufferLength(long jarg1);
	protected final static native int FMOD_INSTANCE_FSOUND_DSP_GetBufferLengthTotal(long jarg1);
	protected final static native ByteBuffer FMOD_INSTANCE_FSOUND_DSP_GetSpectrum(long jarg1);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Reverb_SetProperties(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Reverb_GetProperties(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Reverb_SetChannelProperties(long jarg1, int jarg2, long jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Reverb_GetChannelProperties(long jarg1, int jarg2, long jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Record_SetDriver(long jarg1, int jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_Record_GetNumDrivers(long jarg1);
	protected final static native String FMOD_INSTANCE_FSOUND_Record_GetDriverName(long jarg1, int jarg2);
	protected final static native int FMOD_INSTANCE_FSOUND_Record_GetDriver(long jarg1);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Record_StartSample(long jarg1, long jarg2, boolean jarg3);
	protected final static native boolean FMOD_INSTANCE_FSOUND_Record_Stop(long jarg1);
	protected final static native int FMOD_INSTANCE_FSOUND_Record_GetPosition(long jarg1);
	protected final static native long FMOD_INSTANCE_FMUSIC_LoadSong(long jarg1, String jarg2);
	protected final static native long FMOD_INSTANCE_FMUSIC_LoadSongEx(long jarg1, String jarg2, int jarg3, int jarg4, int jarg5, int[] jarg6, int jarg7);
	protected final static native long FMOD_INSTANCE_FMUSIC_LoadSongEx(long jarg1, ByteBuffer jarg2, int jarg2_, int jarg3, int jarg4, int jarg5, int[] jarg6, int jarg7);
	protected final static native long FMOD_INSTANCE_FMUSIC_LoadSongEx(long jarg1, String jarg2, int jarg3, int jarg4, int jarg5, IntBuffer jarg6, int jarg6_, int jarg7);
	protected final static native long FMOD_INSTANCE_FMUSIC_LoadSongEx(long jarg1, ByteBuffer jarg2, int jarg2_, int jarg3, int jarg4, int jarg5, IntBuffer jarg6, int jarg6_, int jarg7);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetOpenState(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_FreeSong(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_PlaySong(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_StopSong(long jarg1, long jarg2);
	protected final static native void FMOD_INSTANCE_FMUSIC_StopAllSongs(long jarg1);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_SetZxxCallback(long jarg1, long jarg2, FMUSIC_CALLBACK jarg3);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_SetRowCallback(long jarg1, long jarg2, FMUSIC_CALLBACK jarg3, int jarg4);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_SetOrderCallback(long jarg1, long jarg2, FMUSIC_CALLBACK jarg3, int jarg4);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_SetInstCallback(long jarg1, long jarg2, FMUSIC_CALLBACK jarg3, int jarg4);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_SetSample(long jarg1, long jarg2, int jarg3, long jarg4);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_SetUserData(long jarg1, long jarg2, long jarg3);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_OptimizeChannels(long jarg1, long jarg2, int jarg3, int jarg4);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_SetReverb(long jarg1, boolean jarg2);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_SetLooping(long jarg1, long jarg2, boolean jarg3);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_SetOrder(long jarg1, long jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_SetPaused(long jarg1, long jarg2, boolean jarg3);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_SetMasterVolume(long jarg1, long jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_SetMasterSpeed(long jarg1, long jarg2, float jarg3);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_SetPanSeperation(long jarg1, long jarg2, float jarg3);
	protected final static native String FMOD_INSTANCE_FMUSIC_GetName(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetType(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetNumOrders(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetNumPatterns(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetNumInstruments(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetNumSamples(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetNumChannels(long jarg1, long jarg2);
	protected final static native long FMOD_INSTANCE_FMUSIC_GetSample(long jarg1, long jarg2, int jarg3);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetPatternLength(long jarg1, long jarg2, int jarg3);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_IsFinished(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_IsPlaying(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetMasterVolume(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetGlobalVolume(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetOrder(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetPattern(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetSpeed(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetBPM(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetRow(long jarg1, long jarg2);
	protected final static native boolean FMOD_INSTANCE_FMUSIC_GetPaused(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetTime(long jarg1, long jarg2);
	protected final static native int FMOD_INSTANCE_FMUSIC_GetRealChannel(long jarg1, long jarg2, int jarg3);
	protected final static native long FMOD_INSTANCE_FMUSIC_GetUserData(long jarg1, long jarg2);
}