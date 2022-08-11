package com.terminusgroup.messagebroker;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttClient {
	
	public static MqttAsyncClient myClient;

	public static void subscriber() throws MqttException {

		/**
		 * Mqtt subscriber.
		 */
		myClient = new MqttAsyncClient("tcp://192.168.1.70:1883", UUID.randomUUID().toString());
		MessageCallback myCallback = new MessageCallback();
		myClient.setCallback(myCallback);

		IMqttToken token = myClient.connect();
		token.waitForCompletion();

		myClient.subscribe("/ESP-01_MQTT_client1/Input_Switch/On_Off_Value = ", 0);

	}
}
