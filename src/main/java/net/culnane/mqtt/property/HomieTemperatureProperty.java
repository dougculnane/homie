package net.culnane.mqtt.property;

/**
 * Basic Temperature value.
 */
public class HomieTemperatureProperty extends HomieProperty {

	@Override
	public String getType() {
		return "temperature";
	}

	@Override
	public String getName() {
		return "Temperature";
	}

	@Override
	public String getUnit() {
		return "°C";
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
