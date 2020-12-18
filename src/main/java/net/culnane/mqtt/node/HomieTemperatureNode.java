package net.culnane.mqtt.node;

import net.culnane.mqtt.property.HomieProperty;
import net.culnane.mqtt.property.HomieTemperatureProperty;

public class HomieTemperatureNode extends HomieNode {

	private HomieProperty property = new HomieTemperatureProperty();
	
	public HomieTemperatureNode(String nodeId, String name) {
		super(nodeId, name);
		this.addProperty(property);
	}
	
	public HomieTemperatureNode(String nodeId, String name, int initialValue) {
		this(nodeId, name);
		property.setValue(initialValue);
	}

}
