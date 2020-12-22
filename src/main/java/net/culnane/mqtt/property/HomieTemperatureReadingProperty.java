package net.culnane.mqtt.property;

import java.text.DecimalFormat;

/**
 * Temperature sensor reading read only value.
 */
public class HomieTemperatureReadingProperty extends HomieProperty<Double> {

	DecimalFormat decimalFormat = new DecimalFormat("####.##");
	
	private Double value = null;
	
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
	
	@Override
	public Double getValue() {
		return this.value;
	}

	@Override
	public void setValue(Double newValue) {
		this.value = newValue;
	}
	
	@Override
	public String getMessagePayload() {
		return this.value == null ? "" : decimalFormat.format(value);
	}

}
