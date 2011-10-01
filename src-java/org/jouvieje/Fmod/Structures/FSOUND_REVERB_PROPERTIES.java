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

import org.jouvieje.Fmod.Defines.FSOUND_REVERB_PRESETS;
import org.jouvieje.Fmod.Misc.Pointer;

/**
 * Structure defining a reverb environment.
 * For more indepth descriptions of the reverb properties under win32, please see the EAX2 and EAX3
 * documentation at http://developer.creative.com/ under the 'downloads' section.
 * If they do not have the EAX3 documentation, then most information can be attained from
 * the EAX2 documentation, as EAX3 only adds some more parameters and functionality on top of EAX2.
 * Note the default reverb properties are the same as the FSOUND_PRESET_GENERIC preset.
 * Note that integer values that typically range from -10,000 to 1000 are represented in decibels, and are of a logarithmic scale, not linear, wheras float values are typically linear.
 * PORTABILITY: Each member has the platform it supports in braces ie (win32/xbox).
 * Some reverb parameters are only supported in win32 and some only on xbox. If all parameters are set then the reverb should product a similar effect on either platform.
 * The numerical values listed below are the maximum, minimum and default values for each variable respectively.
 */
public class FSOUND_REVERB_PROPERTIES extends Pointer implements FSOUND_REVERB_PRESETS
{
	/**
	 * Create an <code>FSOUND_REVERB_PROPERTIES</code> using a preset <code>FMOD_REVERB_PRESETS</code>.<br>
	 * @param preset a preset of the interface <code>FMOD_REVERB_PRESETS</code>.
	 * @see org.jouvieje.Fmod.Defines.FSOUND_REVERB_PRESETS
	 */
	public static FSOUND_REVERB_PROPERTIES create(int preset)
	{
		switch(preset)
		{
			case FSOUND_PRESET_OFF: 			return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_OFF());
			case FSOUND_PRESET_GENERIC: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_GENERIC());
			case FSOUND_PRESET_PADDEDCELL: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_PADDEDCELL());
			case FSOUND_PRESET_ROOM: 			return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_ROOM());
			case FSOUND_PRESET_BATHROOM: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_BATHROOM());
			case FSOUND_PRESET_LIVINGROOM: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_LIVINGROOM());
			case FSOUND_PRESET_STONEROOM: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_STONEROOM());
			case FSOUND_PRESET_AUDITORIUM: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_AUDITORIUM());
			case FSOUND_PRESET_CONCERTHALL: 	return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_CONCERTHALL());
			case FSOUND_PRESET_CAVE: 			return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_CAVE());
			case FSOUND_PRESET_ARENA: 			return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_ARENA());
			case FSOUND_PRESET_HANGAR: 			return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_HANGAR());
			case FSOUND_PRESET_CARPETTEDHALLWAY:return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_CARPETTEDHALLWAY());
			case FSOUND_PRESET_HALLWAY: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_HALLWAY());
			case FSOUND_PRESET_STONECORRIDOR:	return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_STONECORRIDOR());
			case FSOUND_PRESET_ALLEY: 			return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_ALLEY());
			case FSOUND_PRESET_FOREST: 			return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_FOREST());
			case FSOUND_PRESET_CITY: 			return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_CITY());
			case FSOUND_PRESET_MOUNTAINS: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_MOUNTAINS());
			case FSOUND_PRESET_QUARRY: 			return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_QUARRY());
			case FSOUND_PRESET_PLAIN: 			return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_PLAIN());
			case FSOUND_PRESET_PARKINGLOT: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_PARKINGLOT());
			case FSOUND_PRESET_SEWERPIPE: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_SEWERPIPE());
			case FSOUND_PRESET_UNDERWATER: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_UNDERWATER());
			case FSOUND_PRESET_DRUGGED: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_DRUGGED());
			case FSOUND_PRESET_DIZZY: 			return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_DIZZY());
			case FSOUND_PRESET_PSYCHOTIC: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_PSYCHOTIC());
			case FSOUND_PRESET_PS2_ROOM: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_PS2_ROOM());
			case FSOUND_PRESET_PS2_STUDIO_A:	return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_PS2_STUDIO_A());
			case FSOUND_PRESET_PS2_STUDIO_B:	return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_PS2_STUDIO_B());
			case FSOUND_PRESET_PS2_STUDIO_C:	return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_PS2_STUDIO_C());
			case FSOUND_PRESET_PS2_HALL: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_PS2_HALL());
			case FSOUND_PRESET_PS2_SPACE: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_PS2_SPACE());
			case FSOUND_PRESET_PS2_ECHO: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_PS2_ECHO());
			case FSOUND_PRESET_PS2_DELAY: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_PS2_DELAY());
			case FSOUND_PRESET_PS2_PIPE: 		return new FSOUND_REVERB_PROPERTIES(StructureJNI.get_FSOUND_PRESET_PS2_PIPE());
			default: return new FSOUND_REVERB_PROPERTIES(StructureJNI.new_FSOUND_REVERB_PROPERTIES());
		}
	}

	/**
	 * Create a view of the <code>Pointer</code> object as a <code>FSOUND_REVERB_PROPERTIES</code> object.<br>
	 * This view is valid only if the memory holded by the <code>Pointer</code> holds a FSOUND_REVERB_PROPERTIES object.
	 */
	public static FSOUND_REVERB_PROPERTIES createView(Pointer pointer)
	{
		return new FSOUND_REVERB_PROPERTIES(Pointer.getPointer(pointer));
	}
	/**
	 * Create a new <code>FSOUND_REVERB_PROPERTIES</code>.<br>
	 * The call <code>isNull()</code> on the object created will return false.<br>
	 * <pre><code>  FSOUND_REVERB_PROPERTIES obj = FSOUND_REVERB_PROPERTIES.create();
	 *  (obj == null) <=> obj.isNull() <=> false
	 * </code></pre>
	 */
	public static FSOUND_REVERB_PROPERTIES create()
	{
		return new FSOUND_REVERB_PROPERTIES(StructureJNI.new_FSOUND_REVERB_PROPERTIES());
	}

	protected FSOUND_REVERB_PROPERTIES(long pointer)
	{
		super(pointer);
	}

	/**
	 * Create an object that holds a null <code>FSOUND_REVERB_PROPERTIES</code>.<br>
	 * The call <code>isNull()</code> on the object created will returns true.<br>
	 * <pre><code>  FSOUND_REVERB_PROPERTIES obj = new FSOUND_REVERB_PROPERTIES();
	 *  (obj == null) <=> false
	 *  obj.isNull() <=> true
	 * </code></pre>
	 * To creates a new <code>FSOUND_REVERB_PROPERTIES</code>, use the static "constructor" :
	 * <pre><code>  FSOUND_REVERB_PROPERTIES obj = FSOUND_REVERB_PROPERTIES.create();</code></pre>
	 * @see FSOUND_REVERB_PROPERTIES#create()
	 */
	public FSOUND_REVERB_PROPERTIES()
	{
		super();
	}

	public void release()
	{
		if(pointer != 0)
		{
			StructureJNI.delete_FSOUND_REVERB_PROPERTIES(pointer);
		}
		pointer = 0;
	}

	/**
	 * 0 , 25 , 0 , sets all listener properties (WIN32/PS2 only)
	 */
	public void setEnvironment(int Environment)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_Environment(pointer, Environment);
	}
	/**
	 * @return value of Environment
	 */
	public int getEnvironment()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_Environment(pointer);
	}

	/**
	 * 1.0 , 100.0 , 7.5 , environment size in meters (WIN32 only)
	 */
	public void setEnvSize(float EnvSize)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_EnvSize(pointer, EnvSize);
	}
	/**
	 * @return value of EnvSize
	 */
	public float getEnvSize()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_EnvSize(pointer);
	}

	/**
	 * 0.0 , 1.0 , 1.0 , environment diffusion (WIN32/XBOX)
	 */
	public void setEnvDiffusion(float EnvDiffusion)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_EnvDiffusion(pointer, EnvDiffusion);
	}
	/**
	 * @return value of EnvDiffusion
	 */
	public float getEnvDiffusion()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_EnvDiffusion(pointer);
	}

	/**
	 * -10000, 0 , -1000 , room effect level (at mid frequencies) (WIN32/XBOX/PS2)
	 */
	public void setRoom(int Room)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_Room(pointer, Room);
	}
	/**
	 * @return value of Room
	 */
	public int getRoom()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_Room(pointer);
	}

	/**
	 * -10000, 0 , -100 , relative room effect level at high frequencies (WIN32/XBOX)
	 */
	public void setRoomHF(int RoomHF)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_RoomHF(pointer, RoomHF);
	}
	/**
	 * @return value of RoomHF
	 */
	public int getRoomHF()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_RoomHF(pointer);
	}

	/**
	 * -10000, 0 , 0 , relative room effect level at low frequencies (WIN32 only)
	 */
	public void setRoomLF(int RoomLF)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_RoomLF(pointer, RoomLF);
	}
	/**
	 * @return value of RoomLF
	 */
	public int getRoomLF()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_RoomLF(pointer);
	}

	/**
	 * 0.1 , 20.0 , 1.49 , reverberation decay time at mid frequencies (WIN32/XBOX)
	 */
	public void setDecayTime(float DecayTime)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_DecayTime(pointer, DecayTime);
	}
	/**
	 * @return value of DecayTime
	 */
	public float getDecayTime()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_DecayTime(pointer);
	}

	/**
	 * 0.1 , 2.0 , 0.83 , high-frequency to mid-frequency decay time ratio (WIN32/XBOX)
	 */
	public void setDecayHFRatio(float DecayHFRatio)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_DecayHFRatio(pointer, DecayHFRatio);
	}
	/**
	 * @return value of DecayHFRatio
	 */
	public float getDecayHFRatio()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_DecayHFRatio(pointer);
	}

	/**
	 * 0.1 , 2.0 , 1.0 , low-frequency to mid-frequency decay time ratio (WIN32 only)
	 */
	public void setDecayLFRatio(float DecayLFRatio)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_DecayLFRatio(pointer, DecayLFRatio);
	}
	/**
	 * @return value of DecayLFRatio
	 */
	public float getDecayLFRatio()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_DecayLFRatio(pointer);
	}

	/**
	 * -10000, 1000 , -2602 , early reflections level relative to room effect (WIN32/XBOX)
	 */
	public void setReflections(int Reflections)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_Reflections(pointer, Reflections);
	}
	/**
	 * @return value of Reflections
	 */
	public int getReflections()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_Reflections(pointer);
	}

	/**
	 * 0.0 , 0.3 , 0.007 , initial reflection delay time (WIN32/XBOX)
	 */
	public void setReflectionsDelay(float ReflectionsDelay)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_ReflectionsDelay(pointer, ReflectionsDelay);
	}
	/**
	 * @return value of ReflectionsDelay
	 */
	public float getReflectionsDelay()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_ReflectionsDelay(pointer);
	}

	/**
	 * , , [0,0,0], early reflections panning vector (WIN32 only)
	 */
	public void setReflectionsPan(float[] ReflectionsPan)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_ReflectionsPan(pointer, ReflectionsPan);
	}
	/**
	 * @return value of ReflectionPan
	 */
	public float[] getReflectionsPan()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_ReflectionsPan(pointer);
	}

	/**
	 * -10000, 2000 , 200 , late reverberation level relative to room effect (WIN32/XBOX)
	 */
	public void setReverb(int Reverb)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_Reverb(pointer, Reverb);
	}
	/**
	 * @return value of Reverb
	 */
	public int getReverb()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_Reverb(pointer);
	}

	/**
	 * 0.0 , 0.1 , 0.011 , late reverberation delay time relative to initial reflection (WIN32/XBOX)
	 */
	public void setReverbDelay(float ReverbDelay)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_ReverbDelay(pointer, ReverbDelay);
	}
	/**
	 * @return value of ReverbDelay
	 */
	public float getReverbDelay()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_ReverbDelay(pointer);
	}

	/**
	 * , , [0,0,0], late reverberation panning vector (WIN32 only)
	 */
	public void setReverbPan(float[] ReverbPan)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_ReverbPan(pointer, ReverbPan);
	}
	/**
	 * @return value of ReverbPan
	 */
	public float[] getReverbPan()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_ReverbPan(pointer);
	}

	/**
	 * .075 , 0.25 , 0.25 , echo time (WIN32/PS2 only. PS2 = Delay time for ECHO/DELAY modes only)
	 */
	public void setEchoTime(float EchoTime)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_EchoTime(pointer, EchoTime);
	}
	/**
	 * @return value of EchoTime
	 */
	public float getEchoTime()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_EchoTime(pointer);
	}

	/**
	 * 0.0 , 1.0 , 0.0 , echo depth (WIN32/PS2 only. PS2 = Feedback level for ECHO mode only)
	 * */
	public void setEchoDepth(float EchoDepth)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_EchoDepth(pointer, EchoDepth);
	}
	/**
	 * @return value of EchoDepth
	 */
	public float getEchoDepth()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_EchoDepth(pointer);
	}

	/**
	 * 0.04 , 4.0 , 0.25 , modulation time (WIN32 only)
	 */
	public void setModulationTime(float ModulationTime)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_ModulationTime(pointer, ModulationTime);
	}
	/**
	 * @return value of ModulationTime
	 */
	public float getModulationTime()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_ModulationTime(pointer);
	}

	/**
	 * 0.0 , 1.0 , 0.0 , modulation depth (WIN32 only)
	 */
	public void setModulationDepth(float ModulationDepth)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_ModulationDepth(pointer, ModulationDepth);
	}
	/**
	 * @return value of ModulationDepth
	 */
	public float getModulationDepth()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_ModulationDepth(pointer);
	}

	/**
	 * -100 , 0.0 , -5.0 , change in level per meter at high frequencies (WIN32 only)
	 */
	public void setAirAbsorptionHF(float AirAbsorptionHF)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_AirAbsorptionHF(pointer, AirAbsorptionHF);
	}
	/**
	 * @return value of AirAbsorptionHF
	 */
	public float getAirAbsorptionHF()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_AirAbsorptionHF(pointer);
	}

	/**
	 * 1000.0, 20000 , 5000.0 , reference high frequency (hz) (WIN32/XBOX)
	 */
	public void setHFReference(float HFReference)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_HFReference(pointer, HFReference);
	}
	/**
	 * @return value of HFReference
	 */
	public float getHFReference()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_HFReference(pointer);
	}

	/**
	 * 20.0 , 1000.0, 250.0 , reference low frequency (hz) (WIN32 only)
	 */
	public void setLFReference(float LFReference)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_LFReference(pointer, LFReference);
	}
	/**
	 * @return value of LFReference
	 */
	public float getLFReference()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_LFReference(pointer);
	}

	/**
	 * 0.0 , 10.0 , 0.0 , like FSOUND_3D_SetRolloffFactor but for room effect (WIN32/XBOX)
	 */
	public void setRoomRolloffFactor(float RoomRolloffFactor)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_RoomRolloffFactor(pointer, RoomRolloffFactor);
	}
	/**
	 * @return value of RoomRolloffFactor
	 */
	public float getRoomRolloffFactor()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_RoomRolloffFactor(pointer);
	}

	/**
	 * 0.0 , 100.0 , 100.0 , Value that controls the echo density in the late reverberation decay. (XBOX only)
	 */
	public void setDiffusion(float Diffusion)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_Diffusion(pointer, Diffusion);
	}
	/**
	 * @return value of Diffusion
	 */
	public float getDiffusion()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_Diffusion(pointer);
	}

	/**
	 * 0.0 , 100.0 , 100.0 , Value that controls the modal density in the late reverberation decay (XBOX only)
	 */
	public void setDensity(float Density)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_Density(pointer, Density);
	}
	/**
	 * @return value of Density
	 */
	public float getDensity()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_Density(pointer);
	}

	/**
	 * FSOUND_REVERB_FLAGS - modifies the behavior of above properties (WIN32/PS2 only)
	 */
	public void setFlags(int Flags)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_PROPERTIES_Flags(pointer, Flags);
	}
	/**
	 * @return value of Flags
	 */
	public int getFlags()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_PROPERTIES_Flags(pointer);
	}
}