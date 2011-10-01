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

import org.jouvieje.Fmod.Defines.FSOUND_CAPS;
import org.jouvieje.Fmod.Defines.FSOUND_CDPLAYMODES;
import org.jouvieje.Fmod.Defines.FSOUND_DSP_PRIORITIES;
import org.jouvieje.Fmod.Defines.FSOUND_INIT_FLAGS;
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;
import org.jouvieje.Fmod.Defines.FSOUND_REVERB_CHANNELFLAGS;
import org.jouvieje.Fmod.Defines.FSOUND_REVERB_FLAGS;
import org.jouvieje.Fmod.Defines.FSOUND_REVERB_PRESETS;
import org.jouvieje.Fmod.Defines.FSOUND_STATUS_FLAGS;
import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FMOD_ERRORS;
import org.jouvieje.Fmod.Enumerations.FMUSIC_TYPES;
import org.jouvieje.Fmod.Enumerations.FSOUND_FX_MODES;
import org.jouvieje.Fmod.Enumerations.FSOUND_MIXERTYPES;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Enumerations.FSOUND_SPEAKERMODES;
import org.jouvieje.Fmod.Enumerations.FSOUND_STREAM_NET_STATUS;
import org.jouvieje.Fmod.Enumerations.FSOUND_TAGFIELD_TYPE;

/**
 * This interface contains all Fmod and Fmod related constants
 */
public interface FMOD_CONSTANTS extends FMOD_ERRORS, FMUSIC_TYPES, FSOUND_CAPS, FSOUND_CDPLAYMODES,
		FSOUND_DSP_PRIORITIES, FSOUND_FX_MODES, FSOUND_INIT_FLAGS, FSOUND_MISC_VALUES,
		FSOUND_MIXERTYPES, FSOUND_MODES, FSOUND_OUTPUTTYPES, FSOUND_REVERB_CHANNELFLAGS,
		FSOUND_REVERB_FLAGS, FSOUND_REVERB_PRESETS, FSOUND_SPEAKERMODES, FSOUND_STATUS_FLAGS,
		FSOUND_STREAM_NET_STATUS, FSOUND_TAGFIELD_TYPE, VERSIONS {}