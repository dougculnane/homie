package net.culnane.mqtt.node;

import net.culnane.mqtt.HomieDevice;
import net.culnane.mqtt.property.HomieTemperatureProperty;

public class HomieTemperatureNode extends HomieNode {

	private HomieTemperatureProperty property = new HomieTemperatureProperty();
	
	public HomieTemperatureNode(final String parentDeviceTopicRoot, String nodeId, String name) {
		super(parentDeviceTopicRoot, nodeId, name);
		this.addProperty(property);
	}
	
	public HomieTemperatureNode(final String parentDeviceTopicRoot, String nodeId, String name, Double initialValue) {
		this(parentDeviceTopicRoot, nodeId, name);
		property.setValue(initialValue);
	}

	public Double getTemperature() {
		return property.getValue();
	}

}
