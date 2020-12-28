package net.culnane.mqtt.node;

import net.culnane.mqtt.HomieDevice;
import net.culnane.mqtt.property.HomieHumidityReadingProperty;
import net.culnane.mqtt.property.HomieProperty;
import net.culnane.mqtt.property.HomieTemperatureReadingProperty;

public class HomieHumidityTemperatureReadingNode extends HomieNode {

	HomieHumidityReadingProperty propertyHum = new HomieHumidityReadingProperty();
	HomieTemperatureReadingProperty propertyTemp = new HomieTemperatureReadingProperty();

	public HomieHumidityTemperatureReadingNode(final String parentDeviceTopicRoot, String nodeId, String name) {
		super(parentDeviceTopicRoot, nodeId, name);
		this.addProperty(propertyHum);
		this.addProperty(propertyTemp);
	}
	
	public void updateHumidityProperty(double newValue) {
		propertyHum.setValue(newValue);
	}
	
	public void updateTemperatureProperty(double newValue) {
		propertyTemp.setValue(newValue);
	}
	
}
