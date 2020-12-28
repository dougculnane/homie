package net.culnane.mqtt.property;

import java.text.DecimalFormat;

import net.culnane.mqtt.node.HomieNode;

/**
 * Basic Temperature value.
 */
public class HomiePowerProperty extends HomieProperty<Double> {

	DecimalFormat decimalFormat = new DecimalFormat("####.##");
	
	private Double value = null;
	
	public HomiePowerProperty(final HomieNode node) {
		super(node, "power", "Power");
	}
	
	public HomiePowerProperty(final HomieNode node, String type, String name) {
		super(node, type, name);
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
