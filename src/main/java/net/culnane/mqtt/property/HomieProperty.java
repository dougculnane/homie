package net.culnane.mqtt.property;

public abstract class HomieProperty<T> {
	
	private String type = "";
	
	private String name = "";

	public HomieProperty(String type, String name) {
		this.type = type;
		this.name = name;	
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

	protected abstract void setValueFromMessage(String message);;
}
