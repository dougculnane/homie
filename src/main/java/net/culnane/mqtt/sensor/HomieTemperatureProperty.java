package net.culnane.mqtt.sensor;

import java.util.ArrayList;
import java.util.List;

import net.culnane.mqtt.HomieMessage;
import net.culnane.mqtt.HomieProperty;
import net.culnane.mqtt.Message;

public class HomieTemperatureProperty extends HomieProperty {

	/**
	 *  homie / device123 / mythermostat / $properties → temperature
	 *  
	 *  homie / device123 / mythermostat / temperature → 22 
	 *  homie / device123 / mythermostat / temperature / $name → Temperature
	 *  homie / device123 / mythermostat / temperature / $unit → °C
	 *  homie / device123 / mythermostat / temperature / $datatype → integer
	 *  homie / device123 / mythermostat / temperature / $settable → true
	 * @return
	 */
	public List<Message> getAnouncementMessages(String topicNodeRoot) {
		List<Message> messages = new ArrayList<Message>();
		messages.add(new Message(topicNodeRoot + "$properties", "temperature"));
		messages.add(new Message(topicNodeRoot + "temperature" + HomieMessage.TOPIC_SEPARATOR + "$name", "Temperature"));
		messages.add(new Message(topicNodeRoot + "temperature" + HomieMessage.TOPIC_SEPARATOR + "$unit", "°C"));
		messages.add(new Message(topicNodeRoot + "temperature" + HomieMessage.TOPIC_SEPARATOR + "$datatype ", "integer"));
		messages.add(new Message(topicNodeRoot + "temperature" + HomieMessage.TOPIC_SEPARATOR + "$settable ", "true"));
		return messages;
		
	}

}
