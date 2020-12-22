package net.culnane.mqtt.node;

import net.culnane.mqtt.property.HomieProperty;
import net.culnane.mqtt.property.HomieTemperatureReadingProperty;

public class HomieTemperatureReadingNode extends HomieNode {

	HomieProperty property = new HomieTemperatureReadingProperty();

	public HomieTemperatureReadingNode(String nodeId, String name) {
		super(nodeId, name);
		this.addProperty(property);
	}
	
	public HomieTemperatureReadingNode(String nodeId, String name, double initValue) {
		this(nodeId, name);
		property.setValue(initValue);
	}
	
	public void updateTemperatureProperty(double newValue) {
		property.setValue(newValue);
	}
	
}
