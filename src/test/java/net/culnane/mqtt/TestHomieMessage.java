package net.culnane.mqtt;

import static org.junit.Assert.*;

import org.junit.Test;

import net.culnane.mqtt.sensor.HomieTemperatureNode;
import net.culnane.mqtt.sensor.HomieThemostatMessage;
import net.culnane.mqtt.sensor.TemperatureSensorHomieMessage;

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
	public void test() {
		HomieMessage exampleMessage = new HomieThemostatMessage("device123", "My device", "mythermostat", "My thermostat");

		for (Message message: exampleMessage.getAnouncementMessages()) {
			System.out.println(message.toString());
		}
	}

}
