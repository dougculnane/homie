package net.culnane.mqtt.property;

import java.text.DecimalFormat;

import net.culnane.mqtt.node.HomieNode;

/**
 * Temperature sensor reading read only value.
 */
public class HomieTemperatureReadingProperty extends HomieProperty<Double> {

	DecimalFormat decimalFormat = new DecimalFormat("####.##");
	
	private Double value = null;
	
	public HomieTemperatureReadingProperty(final HomieNode node) {
		super(node, "temperature", "Temperature");
	}

	public HomieTemperatureReadingProperty(final HomieNode node, String type, String name) {
		super(node, type, name);
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
		return value;
	}

	@Override
	public void setValue(Double newValue) {
		value = newValue;
	}
	
	@Override
	public String getMessagePayload() {
		return value == null ? "" : decimalFormat.format(value);
	}

	@Override
	protected void setValueFromMessage(String message) {
		value = Double.valueOf(message);
	}

}
