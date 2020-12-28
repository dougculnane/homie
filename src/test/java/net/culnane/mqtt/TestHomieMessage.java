package net.culnane.mqtt;


import org.junit.Test;

import org.junit.Assert;

import net.culnane.mqtt.HomieDevice.DeviceState;
import net.culnane.mqtt.node.HomieTemperatureNode;
import net.culnane.mqtt.node.HomieTemperatureReadingNode;

/**
 * Unit tests and example of how to use the devices.
 */
public class TestHomieMessage {

	/**
	 * Example
	 * 
	 *  homie / device123 / $homie → 3.0
	 *  homie / device123 / $name → My device
	 *  homie / device123 / $state → ready
	 *  homie / device123 / $nodes → mythermostat
	 *
	 *  homie / device123 / mythermostat / $name → My thermostat
	 *  homie / device123 / mythermostat / $properties → temperature
	 *
	 *  homie / device123 / mythermostat / temperature → 22 
	 *  homie / device123 / mythermostat / temperature / $name → Temperature
	 *  homie / device123 / mythermostat / temperature / $unit → °C
	 *  homie / device123 / mythermostat / temperature / $datatype → integer
	 *  homie / device123 / mythermostat / temperature / $settable → true
	 */
	@Test
	public void testExample() {
		
		HomieDevice device = new HomieDevice("device123", "My device");
		device.addNode(new HomieTemperatureNode(device.getTopicRoot(), "mythermostat", "My thermostat", 22.0));
		for (Message message: device.getMessages()) {
			System.out.println(message.toString());
		}
	}
	
	/**
	 * Device with thermostat and temperature sensor.
	 */
	@Test
	public void testDeviceWithThermostatAndTemperatureSensor() {
		
		HomieDevice device = new HomieDevice("heatingDevice", "My Heater");
	    device.addNode(new HomieTemperatureNode(device.getTopicRoot(), "myThermostat", "My Thermostat", 22.0));
		device.addNode(new HomieTemperatureReadingNode(device.getTopicRoot(), "insideTemperature", "Inside Temperature", 20));
		for (Message message: device.getMessages()) {
			System.out.println(message.toString());
		}
	}

	/**
	 * Device with many temperature sensors
	 */
	@Test
	public void testDeviceWithManyTemperatureSensors() {
		
		HomieDevice device = new HomieDevice("monitoringSystem", "Monitoring System");
		device.addNode(new HomieTemperatureReadingNode(device.getTopicRoot(), "outside", "Outside Temperature"));
		device.addNode(new HomieTemperatureReadingNode(device.getTopicRoot(), "bedroom", "Bedroom Temperature", 21));
		device.addNode(new HomieTemperatureReadingNode(device.getTopicRoot(), "kitchen", "Kitchen Temperature", 23));
		device.addNode(new HomieTemperatureReadingNode(device.getTopicRoot(), "cellar", "Cellar Temperature", 17));
		device.addNode(new HomieTemperatureReadingNode(device.getTopicRoot(), "garage", "Garage Temperature", 16));
		device.addNode(new HomieTemperatureReadingNode(device.getTopicRoot(), "hall", "Hall Temperature", 20));
		for (Message message: device.getMessages()) {
			System.out.println(message.toString());
		}
	}
	
	/**
	 * Test the device life-cycle.
	 */
	@Test
	public void testDeviceInitialized() {
		
		// Init the device.
		HomieDevice device = new HomieDevice("newdevice", "New Device");
		Assert.assertEquals("init", device.getStateMessage().getMessage());
		
		// here you would send messages to MQTT for configuration then call hasBeenInitialized.
		for (Message message: device.getMessages()) {
			System.out.println(message.toString());
		}
		device.setState(DeviceState.ready);
		
		// Device is ready
		for (Message message: device.getMessages()) {
			System.out.println(message.toString());
		}
		Assert.assertEquals("ready", device.getStateMessage().getMessage());
	}
	
}
