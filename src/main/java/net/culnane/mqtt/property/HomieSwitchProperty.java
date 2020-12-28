package net.culnane.mqtt.property;

import net.culnane.mqtt.node.HomieNode;

/**
 * Temperature sensor reading read only value.
 */
public class HomieSwitchProperty extends HomieProperty<Boolean> {

	private Boolean value = null;
	
	public HomieSwitchProperty(final HomieNode node) {
		super(node, "switch", "Switch");
	}
	
	public HomieSwitchProperty(final HomieNode node, String type, String name) {
		super(node, type, name);
	}

	@Override
	public String getUnit() {
		return null;
	}

	@Override
	public String getDataType() {
		return "boolean";
	}

	@Override
	public boolean isSetable() {
		return true;
	}
	
	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public void setValue(Boolean newValue) {
		value = newValue;
	}
	
	@Override
	public String getMessagePayload() {
		return value == null ? "" : value.toString();
	}

	@Override
	protected void setValueFromMessage(String message) {
		value = Boolean.valueOf(message);
	}

}
