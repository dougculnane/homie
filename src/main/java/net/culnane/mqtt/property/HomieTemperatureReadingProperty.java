package net.culnane.mqtt.property;

/**
 * Temperature sensor reading read only value.
 */
public class HomieTemperatureReadingProperty extends HomieProperty {

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
		return "float";
	}

	@Override
	public boolean isSetable() {
		return false;
	}

}
