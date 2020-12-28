package net.culnane.mqtt.property;

/**
 * Basic Temperature value.
 */
public class HomieTemperatureProperty extends HomieProperty<Double> {

	private Double value = null;
	
	public HomieTemperatureProperty() {
		super("temperature", "Temperature");
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
		return true;
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public void setValue(Double newValue) {
		this.value = newValue;
	}

	@Override
	protected void setValueFromMessage(String message) {
		value = Double.valueOf(message);
	}
}
