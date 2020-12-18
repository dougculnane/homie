package net.culnane.mqtt.property;

public abstract class HomieProperty {
	
	protected Object value;

	public String getValue() {
		return value == null ? "" : value.toString();
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

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

	public boolean update(Object newValue) {
		if (this.value.equals(newValue)) {
			return false;
		}
		this.value = newValue;
		return true;
	}
	
}
