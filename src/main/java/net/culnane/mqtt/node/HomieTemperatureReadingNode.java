package net.culnane.mqtt.node;

import net.culnane.mqtt.HomieDevice;
import net.culnane.mqtt.property.HomieTemperatureReadingProperty;

public class HomieTemperatureReadingNode extends HomieNode {

	HomieTemperatureReadingProperty property;

	public HomieTemperatureReadingNode(final HomieDevice homieDevice, String nodeId, String name) {
		super(homieDevice, nodeId, name);
		property = new HomieTemperatureReadingProperty(this);
	}
	
	public HomieTemperatureReadingNode(final HomieDevice homieDevice, String nodeId, String name, double initValue) {
		this(homieDevice, nodeId, name);
		property.setValue(initValue);
	}
	
	public void updateTemperatureProperty(double newValue) {
		property.setValue(newValue);
	}
	
}
