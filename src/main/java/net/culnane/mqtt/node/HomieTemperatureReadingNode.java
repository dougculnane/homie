package net.culnane.mqtt.node;

import net.culnane.mqtt.HomieDevice;
import net.culnane.mqtt.property.HomieProperty;
import net.culnane.mqtt.property.HomieTemperatureReadingProperty;

public class HomieTemperatureReadingNode extends HomieNode {

	HomieTemperatureReadingProperty property = new HomieTemperatureReadingProperty();

	public HomieTemperatureReadingNode(final String parentDeviceTopicRoot, String nodeId, String name) {
		super(parentDeviceTopicRoot, nodeId, name);
		this.addProperty(property);
	}
	
	public HomieTemperatureReadingNode(final String parentDeviceTopicRoot, String nodeId, String name, double initValue) {
		this(parentDeviceTopicRoot, nodeId, name);
		property.setValue(initValue);
	}
	
	public void updateTemperatureProperty(double newValue) {
		property.setValue(newValue);
	}
	
}
