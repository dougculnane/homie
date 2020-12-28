package net.culnane.mqtt.property;

import net.culnane.mqtt.node.HomieNode;

/**
 * Basic Temperature value.
 */
public class HomieTemperatureProperty extends HomieProperty<Double> {

	private Double value = null;
	
	public HomieTemperatureProperty(final HomieNode node) {
		super(node, "temperature", "Temperature");
	}

	public HomieTemperatureProperty(final HomieNode node, String type, String name) {
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
