## Overview

This repository contains Java client for [Emitter](https://emitter.io) (see also on [Emitter GitHub](https://github.com/emitter-io/emitter)). Emitter is an **open-source** real-time communication service for connecting online devices. At its core, emitter.io is a distributed, scalable and fault-tolerant publish-subscribe messaging platform based on MQTT protocol and featuring message storage.

The library uses core technolgy developed by [FuseSource Corp.](https://github.com/fusesource/mqtt-client) and is released under ASL 2.0 license. The library takes care of automatically reconnecting to your MQTT server and restoring your client session if any network failures occur. Applications can use a blocking API style, a futures based API, or a callback/continuations passing API style.

## Using Synchronous (Blocking) API

All three APIs (blocking, future, and callback) share the same connection setup. In order to get started, you'll need to create a new instance of the `Emitter` class (or get a default one) and retrieve the desired API implementation. Below, the simple usage of the blocking API is demonstrated.

```java
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
```

## Using Asynchronous (Non-Blocking) API

The asynchronous API is using [futures & promises](https://en.wikipedia.org/wiki/Futures_and_promises) pattern to orchestrate the method calls. The usage is similar to the previous example and is demonstrated below.

```java
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
```


## Advanced API Features

As mentioned earlier, the API is using the underlying MQTT client originally developed by folks at FuseSource. You can find more information about various API members and configuration options here: https://github.com/fusesource/mqtt-client
    
