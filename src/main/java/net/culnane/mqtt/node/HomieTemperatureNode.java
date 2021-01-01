package net.culnane.mqtt.node;

import net.culnane.mqtt.HomieDevice;
import net.culnane.mqtt.property.HomieTemperatureProperty;

public class HomieTemperatureNode extends HomieNode {

	private HomieTemperatureProperty property;
	
	public HomieTemperatureNode(final HomieDevice homieDevice, String nodeId, String name) {
		super(homieDevice, nodeId, name);
		property = new HomieTemperatureProperty(this);
	}
	
	public HomieTemperatureNode(final HomieDevice homieDevice, String nodeId, String name, Double initialValue) {
		this(homieDevice, nodeId, name);
		property.setValue(initialValue);
	}

	public Double getTemperature() {
		return property.getValue();
	}

}
