/**
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

package org.jouvieje.Fmod.Structures;

import org.jouvieje.Fmod.Init;

class StructureJNI
{
	static
	{
		//If the libraries are not loaded, tries to load them here
		if(!Init.isFmodLibrariesLoaded())
		{
			if(Init.DEBUG)
				Thread.dumpStack();
			throw new RuntimeException("Libraries not yet loaded ! Use Init.loadLibraries() before using NativeFmod.");
		}
	}

					/*FSOUND_REVERB_PROPERTIES*/

	protected final static native void set_FSOUND_REVERB_PROPERTIES_Environment(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_PROPERTIES_Environment(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_EnvSize(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_EnvSize(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_EnvDiffusion(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_EnvDiffusion(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_Room(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_PROPERTIES_Room(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_RoomHF(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_PROPERTIES_RoomHF(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_RoomLF(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_PROPERTIES_RoomLF(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_DecayTime(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_DecayTime(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_DecayHFRatio(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_DecayHFRatio(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_DecayLFRatio(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_DecayLFRatio(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_Reflections(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_PROPERTIES_Reflections(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_ReflectionsDelay(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_ReflectionsDelay(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_ReflectionsPan(long jarg1, float[] jarg2);
	protected final static native float[] get_FSOUND_REVERB_PROPERTIES_ReflectionsPan(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_Reverb(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_PROPERTIES_Reverb(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_ReverbDelay(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_ReverbDelay(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_ReverbPan(long jarg1, float[] jarg2);
	protected final static native float[] get_FSOUND_REVERB_PROPERTIES_ReverbPan(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_EchoTime(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_EchoTime(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_EchoDepth(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_EchoDepth(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_ModulationTime(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_ModulationTime(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_ModulationDepth(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_ModulationDepth(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_AirAbsorptionHF(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_AirAbsorptionHF(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_HFReference(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_HFReference(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_LFReference(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_LFReference(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_RoomRolloffFactor(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_RoomRolloffFactor(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_Diffusion(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_Diffusion(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_Density(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_PROPERTIES_Density(long jarg1);
	protected final static native void set_FSOUND_REVERB_PROPERTIES_Flags(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_PROPERTIES_Flags(long jarg1);
	protected final static native long new_FSOUND_REVERB_PROPERTIES();
	protected final static native void delete_FSOUND_REVERB_PROPERTIES(long jarg1);

						/*FSOUND_REVERB_PRESETS*/

	protected final static native long get_FSOUND_PRESET_OFF();
	protected final static native long get_FSOUND_PRESET_GENERIC();
	protected final static native long get_FSOUND_PRESET_PADDEDCELL();
	protected final static native long get_FSOUND_PRESET_ROOM();
	protected final static native long get_FSOUND_PRESET_BATHROOM();
	protected final static native long get_FSOUND_PRESET_LIVINGROOM();
	protected final static native long get_FSOUND_PRESET_STONEROOM();
	protected final static native long get_FSOUND_PRESET_AUDITORIUM();
	protected final static native long get_FSOUND_PRESET_CONCERTHALL();
	protected final static native long get_FSOUND_PRESET_CAVE();
	protected final static native long get_FSOUND_PRESET_ARENA();
	protected final static native long get_FSOUND_PRESET_HANGAR();
	protected final static native long get_FSOUND_PRESET_CARPETTEDHALLWAY();
	protected final static native long get_FSOUND_PRESET_HALLWAY();
	protected final static native long get_FSOUND_PRESET_STONECORRIDOR();
	protected final static native long get_FSOUND_PRESET_ALLEY();
	protected final static native long get_FSOUND_PRESET_FOREST();
	protected final static native long get_FSOUND_PRESET_CITY();
	protected final static native long get_FSOUND_PRESET_MOUNTAINS();
	protected final static native long get_FSOUND_PRESET_QUARRY();
	protected final static native long get_FSOUND_PRESET_PLAIN();
	protected final static native long get_FSOUND_PRESET_PARKINGLOT();
	protected final static native long get_FSOUND_PRESET_SEWERPIPE();
	protected final static native long get_FSOUND_PRESET_UNDERWATER();
	protected final static native long get_FSOUND_PRESET_DRUGGED();
	protected final static native long get_FSOUND_PRESET_DIZZY();
	protected final static native long get_FSOUND_PRESET_PSYCHOTIC();
	protected final static native long get_FSOUND_PRESET_PS2_ROOM();
	protected final static native long get_FSOUND_PRESET_PS2_STUDIO_A();
	protected final static native long get_FSOUND_PRESET_PS2_STUDIO_B();
	protected final static native long get_FSOUND_PRESET_PS2_STUDIO_C();
	protected final static native long get_FSOUND_PRESET_PS2_HALL();
	protected final static native long get_FSOUND_PRESET_PS2_SPACE();
	protected final static native long get_FSOUND_PRESET_PS2_ECHO();
	protected final static native long get_FSOUND_PRESET_PS2_DELAY();
	protected final static native long get_FSOUND_PRESET_PS2_PIPE();

						/*FSOUND_REVERB_CHANNELPROPERTIES*/

	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_Direct(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_CHANNELPROPERTIES_Direct(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_DirectHF(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_CHANNELPROPERTIES_DirectHF(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_Room(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_CHANNELPROPERTIES_Room(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_RoomHF(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_CHANNELPROPERTIES_RoomHF(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_Obstruction(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_CHANNELPROPERTIES_Obstruction(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_ObstructionLFRatio(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_CHANNELPROPERTIES_ObstructionLFRatio(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_Occlusion(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_CHANNELPROPERTIES_Occlusion(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_OcclusionLFRatio(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_CHANNELPROPERTIES_OcclusionLFRatio(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_OcclusionRoomRatio(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_CHANNELPROPERTIES_OcclusionRoomRatio(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_OcclusionDirectRatio(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_CHANNELPROPERTIES_OcclusionDirectRatio(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_Exclusion(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_CHANNELPROPERTIES_Exclusion(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_ExclusionLFRatio(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_CHANNELPROPERTIES_ExclusionLFRatio(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_OutsideVolumeHF(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_CHANNELPROPERTIES_OutsideVolumeHF(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_DopplerFactor(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_CHANNELPROPERTIES_DopplerFactor(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_RolloffFactor(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_CHANNELPROPERTIES_RolloffFactor(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_RoomRolloffFactor(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_CHANNELPROPERTIES_RoomRolloffFactor(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_AirAbsorptionFactor(long jarg1, float jarg2);
	protected final static native float get_FSOUND_REVERB_CHANNELPROPERTIES_AirAbsorptionFactor(long jarg1);
	protected final static native void set_FSOUND_REVERB_CHANNELPROPERTIES_Flags(long jarg1, int jarg2);
	protected final static native int get_FSOUND_REVERB_CHANNELPROPERTIES_Flags(long jarg1);
	protected final static native long new_FSOUND_REVERB_CHANNELPROPERTIES();
	protected final static native void delete_FSOUND_REVERB_CHANNELPROPERTIES(long jarg1);

						/*FSOUND_TOC_TAG*/

	protected final static native String get_FSOUND_TOC_TAG_name(long jarg1);
	protected final static native int get_FSOUND_TOC_TAG_numtracks(long jarg1);
	protected final static native int[] get_FSOUND_TOC_TAG_min(long jarg1);
	protected final static native int[] get_FSOUND_TOC_TAG_sec(long jarg1);
	protected final static native int[] get_FSOUND_TOC_TAG_frame(long jarg1);
	protected final static native long new_FSOUND_TOC_TAG();
	protected final static native void delete_FSOUND_TOC_TAG(long jarg1);
}