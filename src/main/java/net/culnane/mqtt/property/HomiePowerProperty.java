package net.culnane.mqtt.property;

import java.text.DecimalFormat;

/**
 * Basic Temperature value.
 */
public class HomiePowerProperty extends HomieProperty<Double> {

	DecimalFormat decimalFormat = new DecimalFormat("####.##");
	
	private Double value = null;
	
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
		return "float";
	}

	@Override
	public boolean isSetable() {
		return true;
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
