package net.culnane.mqtt.property;

import net.culnane.mqtt.node.HomieNode;

/**
 * Basic Temperature value.
 */
public class HomieBatteryLevelProperty extends HomieProperty<Integer> {

	private Integer value = null;
	
	public HomieBatteryLevelProperty(final HomieNode node) {
		super(node, "battery", "Battery");
	}

	public HomieBatteryLevelProperty(final HomieNode node, String type, String name) {
		super(node, type, name);
	}

	@Override
	public String getUnit() {
		return "%";
	}

	@Override
	public String getDataType() {
		return "integer";
	}

	@Override
	public boolean isSetable() {
		return true;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public void setValue(Integer newValue) {
		this.value = newValue;
	}

	@Override
	protected void setValueFromMessage(String message) {
		value = Integer.valueOf(message);
	}
}
