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

#include "CallbackStorer.h"

Callback * allocCallback = NULL;
Callback * reallocCallback = NULL;
Callback * freeCallback = NULL;
void * F_CALLBACKAPI AllocCallback (unsigned int arg1) {
	if(!allocCallback) {
		//TODO add exception here ?
		return 0;
	}
	return allocCallback->FSOUND_ALLOCCALLBACK(arg1);
}
void * F_CALLBACKAPI ReallocCallback (void *arg1, unsigned int arg2) {
	if(!reallocCallback) {
		//TODO add exception here ?
		return 0;
	}
	return reallocCallback->FSOUND_REALLOCCALLBACK(arg1, arg2);
}
void F_CALLBACKAPI FreeCallback (void *arg1) {
	if(!freeCallback) {
		//TODO add exception here ?
		return ;
	}
	freeCallback->FSOUND_FREECALLBACK(arg1);
}

Callback *cOpenCallback = NULL;
Callback *cCloseCallback = NULL;
Callback *cReadCallback = NULL;
Callback *cSeekCallback = NULL;
Callback *cTellCallback = NULL;
void * F_CALLBACKAPI OpenCallback (const char *arg1) {
	if(!cOpenCallback) {
		//TODO add exception here ?
		return 0;
	}
	return cOpenCallback->FSOUND_OPENCALLBACK(arg1);
}
void F_CALLBACKAPI CloseCallback (void *arg1) {
	if(!cCloseCallback) {
		//TODO add exception here ?
		return ;
	}
	cCloseCallback->FSOUND_CLOSECALLBACK(arg1);
}
int F_CALLBACKAPI ReadCallback (void *arg1, int arg2, void *arg3) {
	if(!cReadCallback) {
		//TODO add exception here ?
		return 0;
	}
	return cReadCallback->FSOUND_READCALLBACK(arg1, arg2, arg3);
}
int F_CALLBACKAPI SeekCallback (void *arg1, int arg2, signed char arg3) {
	if(!cSeekCallback) {
		//TODO add exception here ?
		return 0;
	}
	return cSeekCallback->FSOUND_SEEKCALLBACK(arg1, arg2, arg3);
}
int F_CALLBACKAPI TellCallback (void *arg1) {
	if(!cTellCallback) {
		//TODO add exception here ?
		return 0;
	}
	return cTellCallback->FSOUND_TELLCALLBACK(arg1);
}

signed char F_CALLBACKAPI StreamCallback(FSOUND_STREAM *arg1, void *arg2, int arg3, void *arg4) {
	Callback *callback = (Callback *)arg4;
	if(!callback) {
		//TODO add exception here ?
		return 0;
	}
	return callback->FSOUND_STREAMCALLBACK(arg1, arg2, arg3, NULL);
}

void * F_CALLBACKAPI DspCallback(void *arg1, void *arg2, int arg3, void *arg4) {
	Callback *callback = (Callback *)arg4;
	if(!callback) {
		//TODO add exception here ?
		return 0;
	}
	return callback->FSOUND_DSPCALLBACK(arg1, arg2, arg3, NULL);
}

signed char F_CALLBACKAPI MetadataCallback(char *arg1, char *arg2, void *arg3) {
	Callback *callback = (Callback *)arg3;
    if (!callback) {
		//TODO add exception here ?
        return 0;
    }
	return callback->FSOUND_METADATACALLBACK(arg1, arg2, NULL);
}

Callback *cZxxCallback = NULL;
void F_CALLBACKAPI ZxxCallback (FMUSIC_MODULE *arg1, unsigned char arg2) {
	if(!cZxxCallback) {
		//TODO add exception here ?
		return ;
	}
	cZxxCallback->FMUSIC_CALLBACK(arg1, arg2);
}
Callback *cRowCallback = NULL;
void F_CALLBACKAPI RowCallback (FMUSIC_MODULE *arg1, unsigned char arg2) {
	if(!cRowCallback) {
		//TODO add exception here ?
		return ;
	}
	cRowCallback->FMUSIC_CALLBACK(arg1, arg2);
}
Callback *cOrderCallback = NULL;
void F_CALLBACKAPI OrderCallback (FMUSIC_MODULE *arg1, unsigned char arg2) {
	if(!cOrderCallback) {
		//TODO add exception here ?
		return ;
	}
	cOrderCallback->FMUSIC_CALLBACK(arg1, arg2);
}
Callback *cInstCallback = NULL;
void F_CALLBACKAPI InstCallback (FMUSIC_MODULE *arg1, unsigned char arg2) {
	if(!cInstCallback) {
		//TODO add exception here ?
		return ;
	}
	cInstCallback->FMUSIC_CALLBACK(arg1, arg2);
}

