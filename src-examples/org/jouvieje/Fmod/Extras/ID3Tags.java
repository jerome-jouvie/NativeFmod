/**
 * Created on 23 jul. 2004
 * @author Jérôme JOUVIE (Jouvieje)
 * 
 * WANT TO CONTACT ME ?
 * @author Jérôme JOUVIE (Jouvieje)
 * @site   http://jerome.jouvie.free.fr/
 * @mail   jerome.jouvie@gmail.com
 * 
 * ABOUT
 * This sample show you how to gets tags (ID3 ...) stored in a mp3 file.
 * Suggested link to learn ID3 tags :
 * 	    http://www.id3.org/
 */

package org.jouvieje.Fmod.Extras;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.jouvieje.Fmod.Fmod;
import org.jouvieje.Fmod.Init;
import org.jouvieje.Fmod.Defines.FSOUND_MISC_VALUES;
import org.jouvieje.Fmod.Defines.FSOUND_MODES;

import org.jouvieje.Fmod.Defines.VERSIONS;
import org.jouvieje.Fmod.Enumerations.FSOUND_OUTPUTTYPES;
import org.jouvieje.Fmod.Enumerations.FSOUND_TAGFIELD_TYPE;
import org.jouvieje.Fmod.Exceptions.InitException;
import org.jouvieje.Fmod.Misc.BufferUtils;
import org.jouvieje.Fmod.Misc.Pointer;
import org.jouvieje.Fmod.Misc.PointerUtils;
import org.jouvieje.Fmod.Structures.FSOUND_STREAM;


public class ID3Tags implements FSOUND_OUTPUTTYPES, FSOUND_MISC_VALUES, FSOUND_MODES, FSOUND_TAGFIELD_TYPE, VERSIONS {
	public static void main(String[] args) {
		/*
		 * NativeFmod Init
		 */
		try {
			Init.loadLibraries();
		}
		catch(InitException e) {
			System.out.println("NativeFmod error! " + e.getMessage());
			System.exit(1);
		}

		/*
		 * Checking NativeFmodEx version
		 */
		if(NATIVEFMOD_LIBRARY_VERSION != NATIVEFMOD_JAR_VERSION) {
			System.out.println("Error!  NativeFmod library version (" + NATIVEFMOD_LIBRARY_VERSION
					+ ") is different to jar version (" + NATIVEFMOD_JAR_VERSION + ")");
			System.exit(0);
		}
		
		/*==================================================*/
		
		/*
		 * Checking Fmod version
		 */
		if(Fmod.FSOUND_GetVersion() < FMOD_VERSION) {
	        System.out.println("Error : You are using the wrong DLL version!  You should be using FMOD "+FMOD_VERSION);
	        System.exit(1);
	    }
		
		System.out.println("ID3Tags example");
		System.out.println("");
		
		// ==========================================================================================
		// INITIALIZE FMOD
		// ==========================================================================================
		System.out.print("Initialize Fmod...");
		if(!Fmod.FSOUND_Init(44100, 32, 0)) {
			System.err.println(Fmod.FMOD_ErrorString(Fmod.FSOUND_GetError()));
			System.exit(1);
		}
		System.out.println("OK");
		
		// ==========================================================================================
		// OPENING THE STREAM
		// ==========================================================================================
		String streamName = "Media/ID3 tags.mp3";
		System.out.println("Opening the stream "+streamName);
		FSOUND_STREAM stream = Fmod.FSOUND_Stream_Open(streamName, FSOUND_NORMAL, 0, 0);
		if(stream == null) {
			System.err.println(streamName+" not found !");
			System.out.println("Closing Fmod...");
			Fmod.FSOUND_Close();
			System.out.println("Finish");
			System.exit(0);
		}
		System.out.println();
		
		// ==========================================================================================
		// READING TAGS
		// ==========================================================================================
		//Number of tag field stored in the stream
		int[] number = new int[1];
		Fmod.FSOUND_Stream_GetNumTagFields(stream, number);
		System.out.println("The stream contains "+number[0]+" tags.");
		
		//For each tag field, print its value
		for(int i = 0; i <= number[0] - 1; i++) {
			//Object to store values
			Pointer pValue = new Pointer();
			IntBuffer type = BufferUtils.newIntBuffer(1);
			IntBuffer length = BufferUtils.newIntBuffer(1);
			Pointer pName = new Pointer();
			
			//Get the tag field i properties into : type, name, value, length
			Fmod.FSOUND_Stream_GetTagField(stream, i, type, pName, pValue, length);
			
			/*
			 * Length is unknow
			 */
			String name = PointerUtils.toString(pName);
			//Convert to a char buffer because in certain case, you need to skip the first character
			ByteBuffer value = PointerUtils.toBuffer(pValue, length.get(0));
			
			/**
			 * To get ID3V2, you need to call something like this
			 */
			String valueString = null;
			if(type.get(0) == FSOUND_TAGFIELD_ID3V2) {
				if(name.startsWith("T") /*&& BufferUtils.toString(value).length() == 0*/) {
					/*
					 * Text encoding :
					 * US-ASCII, ISO-8859-1, UTF-8, UTF-16BE, UTF-16LE, UTF-16
					 */
					byte encoding = value.get();	//Skip i character
					
					byte[] bs = new byte[value.remaining()];
					value.get(bs, 0, bs.length);
					try {
						switch(encoding) {
							default:
							case 0: valueString = new String(bs, "ISO-8859-1"); //break;
							case 1: valueString = new String(bs, "UTF-16"); //break;
							case 2: valueString = new String(bs, "UTF-16BE"); //break;
							case 3: valueString = new String(bs, "UTF-8"); //break;
							case 5: valueString = new String(bs, "ASCII"); //break;
							case 6: valueString = new String(bs, "UNICODE");// break;
						}
					} catch(UnsupportedEncodingException e) {}
				}
				else if(!(name.startsWith("COMM")) && length.get(0) > 8) {
					value.get();	//Skip i character
				}
			}
			if(valueString == null)
				valueString = BufferUtils.toString(value, 0, value.remaining()-1);
			
			//Convert integer type as a string value
			String tagType;
			if(type.get(0) == FSOUND_TAGFIELD_VORBISCOMMENT)
				tagType = "VORBISCOMMENT";
			else if(type.get(0) == FSOUND_TAGFIELD_ID3V1)
				tagType = "ID3V1";
			else if(type.get(0) == FSOUND_TAGFIELD_ID3V2)
				tagType = "ID3V2";
			else if(type.get(0) == FSOUND_TAGFIELD_SHOUTCAST)
				tagType = "SHOUTCAST";
			else if(type.get(0) == FSOUND_TAGFIELD_ICECAST)
				tagType = "ICECAST";
			else if(type.get(0) == FSOUND_TAGFIELD_ASF)
				tagType = "ASF";
			else
				tagType = "Unknow tag field";
			
			System.out.println(tagType+" - "+	//type of the tag field
					name+" - "+					//name of the tag
					valueString+" - "+			//value of the tag
					length.get(0));				//length of the data
		}
		
		// ==========================================================================================
		// END
		// ==========================================================================================
		System.out.println("");
		System.out.print("Close the music...");
		Fmod.FSOUND_Stream_Close(stream);
		System.out.println("Finish");
		System.out.print("Close Fmod...");
		Fmod.FSOUND_Close();
		System.out.println("Finish");
		System.exit(0);
	}
}