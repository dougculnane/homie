package net.culnane.mqtt.property;

import java.text.DecimalFormat;

/**
 * Temperature sensor reading read only value.
 */
public class HomieHumidityReadingProperty extends HomieProperty<Double> {

	DecimalFormat decimalFormat = new DecimalFormat("####.##");
	
	private Double value = null;
	
	public HomieHumidityReadingProperty() {
		super("humidity", "Humidity");
	}
	
	public HomieHumidityReadingProperty(String type, String name) {
		super(type, name);
	}
	
	@Override
	public String getUnit() {
		return "%";
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
	
	@Override
	protected void setValueFromMessage(String message) {
		value = Double.valueOf(message);
	}
}
