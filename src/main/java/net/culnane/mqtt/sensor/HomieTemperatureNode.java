package net.culnane.mqtt.sensor;

import net.culnane.mqtt.HomieNode;
import net.culnane.mqtt.HomieProperty;

public class HomieTemperatureNode extends HomieNode {

	private static HomieProperty[] properties = { new HomieTemperatureProperty() };

	public HomieTemperatureNode(String nodeId, String name) {
		super(nodeId, name, properties);
	}

}
