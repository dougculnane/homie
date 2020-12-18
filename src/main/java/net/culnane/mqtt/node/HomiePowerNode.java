package net.culnane.mqtt.node;

import net.culnane.mqtt.property.HomiePowerProperty;
import net.culnane.mqtt.property.HomieProperty;

public class HomiePowerNode extends HomieNode {

	private HomieProperty property = new HomiePowerProperty();
	
	public HomiePowerNode(String nodeId, String name) {
		super(nodeId, name);
		addProperty(property);
	}

}
