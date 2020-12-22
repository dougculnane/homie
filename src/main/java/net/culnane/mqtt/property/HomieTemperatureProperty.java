package net.culnane.mqtt.property;

/**
 * Basic Temperature value.
 */
public class HomieTemperatureProperty extends HomieProperty<Integer> {

	private Integer value = null;
	
	@Override
	public String getType() {
		return "temperature";
	}

	@Override
	public String getName() {
		return "Temperature";
	}

	@Override
	public String getUnit() {
		return "°C";
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

}
