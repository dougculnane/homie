package net.culnane.mqtt.property;

public abstract class HomieProperty<T> {
	
	abstract public T getValue();
	
	abstract public void setValue(T newValue);
	
	public abstract String getType();

	public abstract String getName();

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
	
	public String getMessagePayload() {
		return getValue() == null ? "" : getValue().toString();
	}
}
