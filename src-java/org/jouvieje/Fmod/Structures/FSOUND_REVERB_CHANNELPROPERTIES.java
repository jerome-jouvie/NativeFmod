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

import org.jouvieje.Fmod.Misc.Pointer;

/**
 * Structure defining the properties for a reverb source, related to a FSOUND channel.
 * For more indepth descriptions of the reverb properties under win32, please see the EAX3
 * documentation at http://developer.creative.com/ under the 'downloads' section.
 * If they do not have the EAX3 documentation, then most information can be attained from
 * the EAX2 documentation, as EAX3 only adds some more parameters and functionality on top of
 * EAX2.
 * Note the default reverb properties are the same as the FSOUND_PRESET_GENERIC preset.
 * Note that integer values that typically range from -10,000 to 1000 are represented in
 * decibels, and are of a logarithmic scale, not linear, wheras float values are typically linear.
 * PORTABILITY: Each member has the platform it supports in braces ie (win32/xbox).
 * Some reverb parameters are only supported in win32 and some only on xbox. If all parameters are set then
 * the reverb should product a similar effect on either platform.
 * Linux and FMODCE do not support the reverb api.
 * The numerical values listed below are the maximum, minimum and default values for each variable respectively.
 */
public class FSOUND_REVERB_CHANNELPROPERTIES extends Pointer
{
	/**
	 * Create a view of the <code>Pointer</code> object as a <code>FSOUND_REVERB_CHANNELPROPERTIES</code> object.<br>
	 * This view is valid only if the memory holded by the <code>Pointer</code> holds a FSOUND_REVERB_CHANNELPROPERTIES object.
	 */
	public static FSOUND_REVERB_CHANNELPROPERTIES createView(Pointer pointer)
	{
		return new FSOUND_REVERB_CHANNELPROPERTIES(Pointer.getPointer(pointer));
	}
	/**
	 * Create a new <code>FSOUND_REVERB_CHANNELPROPERTIES</code>.<br>
	 * The call <code>isNull()</code> on the object created will return false.<br>
	 * <pre><code>  FSOUND_REVERB_CHANNELPROPERTIES obj = FSOUND_REVERB_CHANNELPROPERTIES.create();
	 *  (obj == null) <=> obj.isNull() <=> false
	 * </code></pre>
	 */
	public static FSOUND_REVERB_CHANNELPROPERTIES create()
	{
		return new FSOUND_REVERB_CHANNELPROPERTIES(StructureJNI.new_FSOUND_REVERB_CHANNELPROPERTIES());
	}

	protected FSOUND_REVERB_CHANNELPROPERTIES(long pointer)
	{
		super(pointer);
	}

	/**
	 * Create an object that holds a null <code>FSOUND_REVERB_CHANNELPROPERTIES</code>.<br>
	 * The call <code>isNull()</code> on the object created will returns true.<br>
	 * <pre><code>  FSOUND_REVERB_CHANNELPROPERTIES obj = new FSOUND_REVERB_CHANNELPROPERTIES();
	 *  (obj == null) <=> false
	 *  obj.isNull() <=> true
	 * </code></pre>
	 * To creates a new <code>FSOUND_REVERB_CHANNELPROPERTIES</code>, use the static "constructor" :
	 * <pre><code>  FSOUND_REVERB_CHANNELPROPERTIES obj = FSOUND_REVERB_CHANNELPROPERTIES.create();</code></pre>
	 * @see FSOUND_REVERB_CHANNELPROPERTIES#create()
	 */
	public FSOUND_REVERB_CHANNELPROPERTIES()
	{
		super();
	}

	public void release()
	{
		if(pointer != 0)
		{
			StructureJNI.delete_FSOUND_REVERB_CHANNELPROPERTIES(pointer);
		}
		pointer = 0;
	}

	/**
	 * -10000, 1000, 0, direct path level (at low and mid frequencies) (WIN32/XBOX)
	 */
	public void setDirect(int Direct)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_Direct(pointer, Direct);
	}
	/**
	 * @return value of Direct
	 */
	public int getDirect()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_Direct(pointer);
	}

	/**
	 * -10000, 0, 0, relative direct path level at high frequencies (WIN32/XBOX)
	 */
	public void setDirectHF(int DirectHF)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_DirectHF(pointer, DirectHF);
	}
	/**
	 * @return value of DirectHF
	 */
	public int getDirectHF()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_DirectHF(pointer);
	}

	/**
	 * -10000, 1000, 0, room effect level (at low and mid frequencies) (WIN32/XBOX/PS2)
	 */
	public void setRoom(int Room)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_Room(pointer, Room);
	}
	/**
	 * @return value of Room
	 */
	public int getRoom()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_Room(pointer);
	}

	/**
	 * -10000, 0, 0, relative room effect level at high frequencies (WIN32/XBOX)
	 */
	public void setRoomHF(int RoomHF)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_RoomHF(pointer, RoomHF);
	}
	/**
	 * @return value of RoomHF
	 */
	public int getRoomHF()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_RoomHF(pointer);
	}

	/**
	 * -10000, 0, 0, main obstruction control (attenuation at high frequencies) (WIN32/XBOX)
	 */
	public void setObstruction(int Obstruction)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_Obstruction(pointer, Obstruction);
	}
	/**
	 * @return value of Obstruction
	 */
	public int getObstruction()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_Obstruction(pointer);
	}

	/**
	 * 0.0, 1.0, 0.0, obstruction low-frequency level re. main control (WIN32/XBOX)
	 */
	public void setObstructionLFRatio(float ObstructionLFRatio)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_ObstructionLFRatio(pointer,
				ObstructionLFRatio);
	}
	/**
	 * @return value of ObstructionLFRatio
	 */
	public float getObstructionLFRatio()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_ObstructionLFRatio(pointer);
	}

	/**
	 * -10000, 0, 0, main occlusion control (attenuation at high frequencies) (WIN32/XBOX)
	 */
	public void setOcclusion(int Occlusion)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_Occlusion(pointer, Occlusion);
	}
	/**
	 * @return value of Occlusion
	 */
	public int getOcclusion()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_Occlusion(pointer);
	}

	/**
	 * 0.0, 1.0, 0.25, occlusion low-frequency level re. main control (WIN32/XBOX)
	 */
	public void setOcclusionLFRatio(float OcclusionLFRatio)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_OcclusionLFRatio(pointer,
				OcclusionLFRatio);
	}
	/**
	 * @return value of OcclusionLFRatio
	 */
	public float getOcclusionLFRatio()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_OcclusionLFRatio(pointer);
	}

	/**
	 * 0.0, 10.0, 1.5, relative occlusion control for room effect (WIN32)
	 */
	public void setOcclusionRoomRatio(float OcclusionRoomRatio)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_OcclusionRoomRatio(pointer,
				OcclusionRoomRatio);
	}
	/**
	 * @return value of OcclusionRoomRatio
	 */
	public float getOcclusionRoomRatio()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_OcclusionRoomRatio(pointer);
	}

	/**
	 * 0.0, 10.0, 1.0, relative occlusion control for direct path (WIN32)
	 */
	public void setOcclusionDirectRatio(float OcclusionDirectRatio)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_OcclusionDirectRatio(pointer,
				OcclusionDirectRatio);
	}
	/**
	 * @return value of OcclusionDirectRatio
	 */
	public float getOcclusionDirectRatio()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_OcclusionDirectRatio(pointer);
	}

	/**
	 * -10000, 0, 0, main exlusion control (attenuation at high frequencies) (WIN32)
	 */
	public void setExclusion(int Exclusion)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_Exclusion(pointer, Exclusion);
	}
	/**
	 * @return value of Exclusion
	 */
	public int getExclusion()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_Exclusion(pointer);
	}

	/**
	 * 0.0, 1.0, 1.0, exclusion low-frequency level re. main control (WIN32)
	 */
	public void setExclusionLFRatio(float ExclusionLFRatio)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_ExclusionLFRatio(pointer,
				ExclusionLFRatio);
	}
	/**
	 * @return value of ExclusionLFRatio
	 */
	public float getExclusionLFRatio()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_ExclusionLFRatio(pointer);
	}

	/**
	 * -10000, 0, 0, outside sound cone level at high frequencies (WIN32)
	 */
	public void setOutsideVolumeHF(int OutsideVolumeHF)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI
				.set_FSOUND_REVERB_CHANNELPROPERTIES_OutsideVolumeHF(pointer, OutsideVolumeHF);
	}
	/**
	 * @return value of OutsideVolumeHF
	 */
	public int getOutsideVolumeHF()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_OutsideVolumeHF(pointer);
	}

	/**
	 * 0.0, 10.0, 0.0, like DS3D flDopplerFactor but per source (WIN32)
	 */
	public void setDopplerFactor(float DopplerFactor)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_DopplerFactor(pointer, DopplerFactor);
	}
	/**
	 * @return value of DopplerFactor
	 */
	public float getDopplerFactor()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_DopplerFactor(pointer);
	}

	/**
	 * 0.0, 10.0, 0.0, like DS3D flRolloffFactor but per source (WIN32)
	 */
	public void setRolloffFactor(float RolloffFactor)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_RolloffFactor(pointer, RolloffFactor);
	}
	/**
	 * @return value of RolloffFactor
	 */
	public float getRolloffFactor()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_RolloffFactor(pointer);
	}

	/**
	 * 0.0, 10.0, 0.0, like DS3D flRolloffFactor but for room effect (WIN32/XBOX)
	 */
	public void setRoomRolloffFactor(float RoomRolloffFactor)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_RoomRolloffFactor(pointer,
				RoomRolloffFactor);
	}
	/**
	 * @return value of RoomRolloverFactor
	 */
	public float getRoomRolloffFactor()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_RoomRolloffFactor(pointer);
	}

	/**
	 * 0.0, 10.0, 1.0, multiplies AirAbsorptionHF member of FSOUND_REVERB_PROPERTIES (WIN32)
	 * */
	public void setAirAbsorptionFactor(float AirAbsorptionFactor)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_AirAbsorptionFactor(pointer,
				AirAbsorptionFactor);
	}
	/**
	 * @return value of AirAbsorptionFactor
	 */
	public float getAirAbsorptionFactor()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_AirAbsorptionFactor(pointer);
	}

	/**
	 * FSOUND_REVERB_CHANNELFLAGS - modifies the behavior of properties (WIN32)
	 */
	public void setFlags(int Flags)
	{
		if(pointer == 0) throw new NullPointerException();
		StructureJNI.set_FSOUND_REVERB_CHANNELPROPERTIES_Flags(pointer, Flags);
	}
	/**
	 * @return value of EchoTime
	 */
	public int getFlags()
	{
		if(pointer == 0) throw new NullPointerException();
		return StructureJNI.get_FSOUND_REVERB_CHANNELPROPERTIES_Flags(pointer);
	}
}