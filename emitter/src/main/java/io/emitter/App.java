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

import io.emitter.mqtt.client.*;


/**
 * Hello world!
 *
 */
public class App 
{   
	static final String key = "<your key>";
	
	
    public static void main( String[] args )
    {
        App app = new App();
        //app.demoSync();
        app.demoAsync();
    }

    /**
     * This demonstrates the blocking API.
     */
    private void demoSync() {
    	
    	// Get an implementation of a blocking connection
    	final BlockingConnection connection = Emitter.getDefault().blockingConnection();
    	try {
    		// Connect to emitter service
			connection.connect();
			
			// Subscribe to some channel
	    	connection.subscribe(new Topic(key, "hello"));
	    	
	    	// Publish a message
	    	connection.publish(key, "hello", "hello, emitter!".getBytes());
	    	
	    	// Receive 10 messages
	    	for(int i=0; i < 10; ++i){
	    		// Receive a message and get the payload buffer
	    		Message msg = connection.receive();
	    		
	    		// Print it out
	    		System.out.println(msg.getPayloadBuffer());
	    	}
	    	
	    	// Disconnect ourselves
	    	connection.disconnect();
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Demonstrates asynchronous API using futures & promises.
     */
    private void demoAsync(){
    	
    	final FutureConnection connection = Emitter.getDefault().futureConnection();
    	
    	// Connects asynchronously to the service
    	connection.connect().then(new Callback<Void>(){
    		
    		// We've successfully connected
			public void onSuccess(Void value) {
				
	           	// Create a topic to subscribe to
            	final Topic t = new Topic(key, "hello");
            	
            	// Subscribe to the topic
            	connection.subscribe(t);
            	
            	// Publish a message asynchronously
            	connection.publish(key, "hello", "hello, emitter!".getBytes());
            	
            	// Receives a message asynchronously
            	connection.receive().then(new Callback<Message>() {
						// We've successfully received a message
						public void onSuccess(Message msg) {
							
				    		// Print it out
				    		System.out.println(msg.getPayloadBuffer());
						}
						
						// Occurs if a receive operation fails
						public void onFailure(Throwable value) { }
					});
			}

			// Occurs if the connect operation fails
			public void onFailure(Throwable value) { }
    		
    	});
    	
    	try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	connection.disconnect();
    	
    	
    }

}
