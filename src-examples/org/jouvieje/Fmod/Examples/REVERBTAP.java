/*===========================================================================================
DSP.EXE
Copyright (c), Firelight Technologies Pty, Ltd, 1999-2004.

This example demonstrates advanced DSP usage.
You can now attach sounds to dsp units.  The dsp units to be attached to must have a NULL
callback.  It is simply a holder for sounds to attach to, and have a specific position in 
the DSP chain.. see the diagram below for a visual representation of the DSP chain.
It also demonstrates the use of hardware DirectX 8 FX.
===========================================================================================*/
   

/*
	Priority :    0          100        320-332       400          1000
	Name     : [CLEAR]-->[samp1-WET]-->[REVERB]-->[samp1-DRY]-->[CLIPCOPY]-->[SOUNDCARD]

	Note the above priority values correspond to the values in FMOD.H

	FSOUND_DSP_DEFAULTPRIORITY_CLEARUNIT        0       
	FSOUND_DSP_DEFAULTPRIORITY_SFXUNIT          100     
	FSOUND_DSP_DEFAULTPRIORITY_MUSICUNIT        200     
	FSOUND_DSP_DEFAULTPRIORITY_USER             300     
	FSOUND_DSP_DEFAULTPRIORITY_FFTUNIT          900     
	FSOUND_DSP_DEFAULTPRIORITY_CLIPANDCOPYUNIT  1000    

	Notice how 'SFX' unit is wet (has reverb).  This is because it is the default destination
	For sound effects if NULL is passed to PlaySoundEx or PlaySound is used.
	Also the Reverb DSP has itself positioned AFTER the 'SFX' unit so then we will hear reverb.
	Now if a sound is attached to the 'Dry' DSP unit located at priority 400, then it will not
	be affected by reverb!
*/

/**
 * I've ported the C++ FMOD example to use it with NativeFmod
 * 
 * @author Jérôme JOUVIE (Jouvieje)
 * 
 * WANT TO CONTACT ME ?
 * E-mail :
 * 		jerome.jouvie@gmail.com
 * My web sites :
 * 		http://jerome.jouvie.free.fr/
 */

package org.jouvieje.Fmod.Examples;

import java.nio.ByteBuffer;

import org.jouvieje.Fmod.Misc.ObjectPointer;
import org.jouvieje.Fmod.Structures.FSOUND_DSPUNIT;

public class REVERBTAP
{
	FSOUND_DSPUNIT unit;
	ByteBuffer historyBuff;		/* storage space for tap history */
	ByteBuffer workArea;		/* a place to hold 1 buffer worth of data (for preverb) */
	int delayMs;				/* delay of p/reverb tab in milliseconds */
	int volume;					/* volume of p/reverb tab */
	int pan;					/* pan of p/reverb tab */
	int historyOffset;			/* running offset into history buffer */
	int historyLen;				/* size of history buffer in SAMPLES */
	ObjectPointer userData;		/* size of history buffer in SAMPLES */
	
	public REVERBTAP(){}
}