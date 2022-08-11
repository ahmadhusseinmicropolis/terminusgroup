package com.terminusgroup.messagebroker;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MessageCallback implements MqttCallback{

	/**
	 * Mqtt callback.
	 */
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub

	}

	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub

	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {

		MqttClient.myClient.publish("/ESP-01_MQTT_client2/Output_Switch/On_Off_Value = ", message.getPayload(), 0, false);

	}

}
