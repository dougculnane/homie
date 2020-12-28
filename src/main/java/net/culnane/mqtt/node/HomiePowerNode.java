package net.culnane.mqtt.node;

import net.culnane.mqtt.HomieDevice;
import net.culnane.mqtt.property.HomiePowerProperty;

public class HomiePowerNode extends HomieNode {

	private HomiePowerProperty property;
	
	public HomiePowerNode(final HomieDevice homieDevice, String nodeId, String name) {
		super(homieDevice, nodeId, name);
		property = new HomiePowerProperty(this);
		addProperty(property);
	}
	
	public void updatePowerProperty(double newValue) {
		property.setValue(newValue);
	}

}
