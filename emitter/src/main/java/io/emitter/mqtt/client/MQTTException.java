package io.emitter.mqtt.client;

import java.io.IOException;

import io.emitter.mqtt.codec.CONNACK;

public class MQTTException extends IOException {
  public final CONNACK connack;

  public MQTTException(String msg, CONNACK connack) {
    super(msg);
    this.connack = connack;
  }
}
