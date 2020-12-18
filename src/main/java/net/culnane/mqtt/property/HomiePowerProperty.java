package net.culnane.mqtt.property;

/**
 * Basic Temperature value.
 */
public class HomiePowerProperty extends HomieProperty {

	@Override
	public String getType() {
		return "power";
	}

	@Override
	public String getName() {
		return "Power";
	}

	@Override
	public String getUnit() {
		return "W";
	}

	@Override
	public String getDataType() {
		return "integer";
	}

	@Override
	public boolean isSetable() {
		return true;
	}
	
}
