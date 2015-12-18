/**
 * Copyright (C) 2015, Misakai Ltd.  All rights reserved.
 *
 *     http://emitter.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.emitter;

import java.util.HashMap;
import java.util.Map;
import java.lang.Math;

import com.cedarsoftware.util.io.*;

/**
 * Represents an key generation request.
 */
public class KeyGenResponse
{
	public int status;
	public String key;
	public String channel;
	
	/**
	 * Constructs a new instance of a request.
	 */
	private KeyGenResponse(){
		
	}
	
	
	/**
	 * Deserializes the JSON key-gen response.
	 */
	public static KeyGenResponse fromJson(String json){
		Map args = new HashMap();
		args.put(JsonReader.USE_MAPS, true);
		JsonObject data = (JsonObject) JsonReader.jsonToJava(json, args);
		KeyGenResponse response = new KeyGenResponse();
		
		if (data.containsKey("status"))
			response.status = safeLongToInt((Long) data.get("status"));
		
		if (data.containsKey("key"))
			response.key = (String) data.get("key");
		
		if (data.containsKey("channel"))
			response.channel = (String) data.get("channel");

		return response;
	}
	
	
	/**
	 * Converts the request to JSON format.
	 * @return JSON-formatted string.
	 */
	public String toString(){
		Map args = new HashMap();
		args.put(JsonWriter.TYPE, false);
		return JsonWriter.objectToJson(this, args);
	}
	
	/**
	 * Safely casts a long to int.
	 */
	private static int safeLongToInt(long l) {
	    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException
	            (l + " cannot be cast to int without changing its value.");
	    }
	    return (int) l;
	}
	
	
}
