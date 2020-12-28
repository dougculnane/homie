package net.culnane.mqtt.property;

import net.culnane.mqtt.Message;
import net.culnane.mqtt.node.HomieNode;

public abstract class HomieProperty<T> {
	
	private String type = "";
	
	private String name = "";
	
	private String topicRoot;
	
	public HomieProperty(final HomieNode node, String type, String name) {
		this.topicRoot = node.getTopicRoot() + type;
		this.type = type;
		this.name = name;
		node.addProperty(this);
	}
	
	public void setTopicRoot(final String parentNodeTopicRoot) {
		this.topicRoot = parentNodeTopicRoot + type;
	}
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Recommended unit strings:
     *
	 *  °C: Degree Celsius
	 *  °F: Degree Fahrenheit
	 *  °: Degree
	 *  L: Liter
	 *  gal: Galon
	 *  V: Volts
	 *  W: Watt
	 *  A: Ampere
	 *  %: Percent
	 *  m: Meter
	 *  ft: Feet
	 *  Pa: Pascal
	 *  psi: PSI
	 *  #: Count or Amount
     *
	 * @return
	 */
	public abstract String getUnit();

	public abstract String getDataType();

	public abstract boolean isSetable();
	
	abstract public T getValue();
	
	abstract public void setValue(T newValue);
	
	public String getMessagePayload() {
		return getValue() == null ? "" : getValue().toString();
	}

	public void handleCommandMessagePayload(String message) {
		if (message != null && message.trim().length() > 0 && isSetable()) {
			setValueFromMessage(message);
		}
	}

	protected abstract void setValueFromMessage(String message);

	public Message getStateMessage() {
		 return new Message(topicRoot, getMessagePayload());
	}
}
