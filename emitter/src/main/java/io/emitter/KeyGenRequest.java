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

import com.cedarsoftware.util.io.*;

/**
 * Represents an key generation request.
 */
public class KeyGenRequest
{
	
	public String key;
	public String channel;
	public String type;
	public int ttl;
	
	/**
	 * Constructs a new instance of a request.
	 */
	private KeyGenRequest(){
		
	}
	
	/**
	 * Constructs a new instance of a request.
	 */
	public KeyGenRequest(String key, String channel, String type, int ttl) {
		 this.key = key;
		 this.channel = channel;
		 this.type = type;
		 this.ttl = ttl;
	}
	
	/**
	 * Converts the request to JSON format.
	 * @return JSON-formatted string.
	 */
	public String toJson(){
		Map args = new HashMap();
		args.put(JsonWriter.TYPE, false);
		return JsonWriter.objectToJson(this, args);
	}
	
	
}
