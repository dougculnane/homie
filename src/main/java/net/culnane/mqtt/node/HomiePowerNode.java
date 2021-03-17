package net.culnane.mqtt.node;

import net.culnane.mqtt.HomieDevice;
import net.culnane.mqtt.property.HomiePowerProperty;
import net.culnane.mqtt.property.HomieSwitchProperty;

public class HomiePowerNode extends HomieNode {

	private HomiePowerProperty power;
	private HomieSwitchProperty powerSwitch;
	
	public HomiePowerNode(final HomieDevice homieDevice, String nodeId, String name) {
		super(homieDevice, nodeId, name);
		power = new HomiePowerProperty(this);
		powerSwitch = new HomieSwitchProperty(this);
	}
	
	public void updatePowerProperty(double newValue) {
		power.setValue(newValue);
	}
	
	public void updatePowerSwitch(boolean newValue) {
		powerSwitch.setValue(newValue);
	}

	public HomieSwitchProperty getPowerSwitch() {
		return powerSwitch;
	}

}
