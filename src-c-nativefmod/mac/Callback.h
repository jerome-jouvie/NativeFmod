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

#ifndef Callback_H_
#define Callback_H_

#include "fmod.h"
#include "JavaObject.h"
#include "Utils.h"

class Callback : public JavaObject {
private:
  static bool initialized;
  static jclass caller;
  static jmethodID methodID[12];

  static jclass getCaller(JNIEnv *java_env);
  static jmethodID getMethodId(JNIEnv *java_env, int index);
public:
  Callback(JNIEnv *jenv, jobject callback_);
  virtual ~Callback();

  static void connectMethods(JNIEnv *java_env);

  virtual void FMUSIC_CALLBACK(FMUSIC_MODULE *mod, unsigned char param);
  virtual void * FSOUND_ALLOCCALLBACK(unsigned int size);
  virtual void FSOUND_CLOSECALLBACK(void *handle);
  virtual void * FSOUND_DSPCALLBACK(void *originalbuffer, void *newBuffer, int length, void *userdata);
  virtual void FSOUND_FREECALLBACK(void * ptr);
  virtual signed char FSOUND_METADATACALLBACK(char *name, char *value, void *userdata);
  virtual void * FSOUND_OPENCALLBACK(const char *name);
  virtual int FSOUND_READCALLBACK(void *buffer, int size, void *handle);
  virtual void * FSOUND_REALLOCCALLBACK(void * ptr, unsigned int size);
  virtual int FSOUND_SEEKCALLBACK(void *handle, int pos, signed char mode);
  virtual signed char FSOUND_STREAMCALLBACK(FSOUND_STREAM *stream, void *buff, int len, void *userdata);
  virtual int FSOUND_TELLCALLBACK(void *arg0);

  void setUserData(jlong data) {
	  userData = data;
  }

private:
  jlong userData;
  jobject callback;
};

#endif