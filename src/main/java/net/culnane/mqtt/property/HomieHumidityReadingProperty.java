package net.culnane.mqtt.property;

import java.text.DecimalFormat;

import net.culnane.mqtt.node.HomieNode;

/**
 * Temperature sensor reading read only value.
 */
public class HomieHumidityReadingProperty extends HomieProperty<Double> {

	DecimalFormat decimalFormat = new DecimalFormat("####.##");
	
	private Double value = null;
	
	public HomieHumidityReadingProperty(final HomieNode node) {
		super(node, "humidity", "Humidity");
	}
	
	public HomieHumidityReadingProperty(final HomieNode node, String type, String name) {
		super(node, type, name);
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
