package net.culnane.mqtt.property;

import java.text.DecimalFormat;

/**
 * Basic Temperature value.
 */
public class HomiePowerProperty extends HomieProperty<Double> {

	DecimalFormat decimalFormat = new DecimalFormat("####.##");
	
	private Double value = null;
	
	public HomiePowerProperty() {
		super("power", "Power");
	}
	
	public HomiePowerProperty(String type, String name) {
		super(type, name);
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
	@Override
	protected void setValueFromMessage(String message) {
		value = Double.valueOf(message);
	}
	
}
