package net.culnane.mqtt.node;

import net.culnane.mqtt.HomieDevice;
import net.culnane.mqtt.property.HomieHumidityReadingProperty;
import net.culnane.mqtt.property.HomieTemperatureReadingProperty;

public class HomieHumidityTemperatureReadingNode extends HomieNode {

	HomieHumidityReadingProperty propertyHum;
	HomieTemperatureReadingProperty propertyTemp;

	public HomieHumidityTemperatureReadingNode(final HomieDevice homieDevice, String nodeId, String name) {
		super(homieDevice, nodeId, name);
		propertyHum = new HomieHumidityReadingProperty(this);
		propertyTemp = new HomieTemperatureReadingProperty(this);
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
