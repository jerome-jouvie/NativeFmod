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

package org.jouvieje.Fmod.Enumerations;

import org.jouvieje.Fmod.Init;

class EnumerationJNI
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

						/*FMOD_ERROS*/

	protected final static native int get_FMOD_ERR_NONE();
	protected final static native int get_FMOD_ERR_BUSY();
	protected final static native int get_FMOD_ERR_UNINITIALIZED();
	protected final static native int get_FMOD_ERR_INIT();
	protected final static native int get_FMOD_ERR_ALLOCATED();
	protected final static native int get_FMOD_ERR_PLAY();
	protected final static native int get_FMOD_ERR_OUTPUT_FORMAT();
	protected final static native int get_FMOD_ERR_COOPERATIVELEVEL();
	protected final static native int get_FMOD_ERR_CREATEBUFFER();
	protected final static native int get_FMOD_ERR_FILE_NOTFOUND();
	protected final static native int get_FMOD_ERR_FILE_FORMAT();
	protected final static native int get_FMOD_ERR_FILE_BAD();
	protected final static native int get_FMOD_ERR_MEMORY();
	protected final static native int get_FMOD_ERR_VERSION();
	protected final static native int get_FMOD_ERR_INVALID_PARAM();
	protected final static native int get_FMOD_ERR_NO_EAX();
	protected final static native int get_FMOD_ERR_CHANNEL_ALLOC();
	protected final static native int get_FMOD_ERR_RECORD();
	protected final static native int get_FMOD_ERR_MEDIAPLAYER();
	protected final static native int get_FMOD_ERR_CDDEVICE();

						/*FMUSIC_TYPES*/

	protected final static native int get_FMUSIC_TYPE_NONE();
	protected final static native int get_FMUSIC_TYPE_MOD();
	protected final static native int get_FMUSIC_TYPE_S3M();
	protected final static native int get_FMUSIC_TYPE_XM();
	protected final static native int get_FMUSIC_TYPE_IT();
	protected final static native int get_FMUSIC_TYPE_MIDI();
	protected final static native int get_FMUSIC_TYPE_FSB();

						/*FSOUND_MIXERTYPES*/

	protected final static native int get_FSOUND_MIXER_AUTODETECT();
	protected final static native int get_FSOUND_MIXER_QUALITY_AUTODETECT();
	protected final static native int get_FSOUND_MIXER_QUALITY_FPU();
	protected final static native int get_FSOUND_MIXER_QUALITY_MMXP5();
	protected final static native int get_FSOUND_MIXER_QUALITY_MMXP6();
	protected final static native int get_FSOUND_MIXER_MONO();
	protected final static native int get_FSOUND_MIXER_QUALITY_MONO();
	protected final static native int get_FSOUND_MIXER_MAX();

						/*FSOUND_OUTPUTTYPES*/

	protected final static native int get_FSOUND_OUTPUT_NOSOUND();
	protected final static native int get_FSOUND_OUTPUT_WINMM();
	protected final static native int get_FSOUND_OUTPUT_DSOUND();
	protected final static native int get_FSOUND_OUTPUT_A3D();
	protected final static native int get_FSOUND_OUTPUT_OSS();
	protected final static native int get_FSOUND_OUTPUT_ESD();
	protected final static native int get_FSOUND_OUTPUT_ALSA();
	protected final static native int get_FSOUND_OUTPUT_ASIO();
	protected final static native int get_FSOUND_OUTPUT_XBOX();
	protected final static native int get_FSOUND_OUTPUT_PS2();
	protected final static native int get_FSOUND_OUTPUT_MAC();
	protected final static native int get_FSOUND_OUTPUT_GC();
	protected final static native int get_FSOUND_OUTPUT_PSP();
	protected final static native int get_FSOUND_OUTPUT_NOSOUND_NONREALTIME();

						/*FSOUND_SPEAKERMODES*/

	protected final static native int get_FSOUND_SPEAKERMODE_DOLBYDIGITAL();
	protected final static native int get_FSOUND_SPEAKERMODE_HEADPHONES();
	protected final static native int get_FSOUND_SPEAKERMODE_MONO();
	protected final static native int get_FSOUND_SPEAKERMODE_QUAD();
	protected final static native int get_FSOUND_SPEAKERMODE_STEREO();
	protected final static native int get_FSOUND_SPEAKERMODE_SURROUND();
	protected final static native int get_FSOUND_SPEAKERMODE_DTS();
	protected final static native int get_FSOUND_SPEAKERMODE_PROLOGIC2();
	protected final static native int get_FSOUND_SPEAKERMODE_PROLOGIC2_INTERIOR();


						/*FSOUND_STREAM_NET_STATUS*/

	protected final static native int get_FSOUND_STREAM_NET_NOTCONNECTED();
	protected final static native int get_FSOUND_STREAM_NET_CONNECTING();
	protected final static native int get_FSOUND_STREAM_NET_BUFFERING();
	protected final static native int get_FSOUND_STREAM_NET_READY();
	protected final static native int get_FSOUND_STREAM_NET_ERROR();

						/*FSOUND_TAGFIELD_TYPE*/

	protected final static native int get_FSOUND_TAGFIELD_VORBISCOMMENT();
	protected final static native int get_FSOUND_TAGFIELD_ID3V1();
	protected final static native int get_FSOUND_TAGFIELD_ID3V2();
	protected final static native int get_FSOUND_TAGFIELD_SHOUTCAST();
	protected final static native int get_FSOUND_TAGFIELD_ICECAST();
	protected final static native int get_FSOUND_TAGFIELD_ASF();

						/*FSOUND_FX_MODES*/

	protected final static native int get_FSOUND_FX_CHORUS();
	protected final static native int get_FSOUND_FX_COMPRESSOR();
	protected final static native int get_FSOUND_FX_DISTORTION();
	protected final static native int get_FSOUND_FX_ECHO();
	protected final static native int get_FSOUND_FX_FLANGER();
	protected final static native int get_FSOUND_FX_GARGLE();
	protected final static native int get_FSOUND_FX_I3DL2REVERB();
	protected final static native int get_FSOUND_FX_PARAMEQ();
	protected final static native int get_FSOUND_FX_WAVES_REVERB();
	protected final static native int get_FSOUND_FX_MAX();
}