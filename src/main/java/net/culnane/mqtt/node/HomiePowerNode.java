package net.culnane.mqtt.node;

import net.culnane.mqtt.property.HomiePowerProperty;

public class HomiePowerNode extends HomieNode {

	private HomiePowerProperty property = new HomiePowerProperty();
	
	public HomiePowerNode(final String parentDeviceTopicRoot, String nodeId, String name) {
		super(parentDeviceTopicRoot, nodeId, name);
		addProperty(property);
	}
	
	public void updatePowerProperty(double newValue) {
		property.setValue(newValue);
	}

}
