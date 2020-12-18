package net.culnane.mqtt.sensor;

import java.util.ArrayList;
import java.util.List;

import net.culnane.mqtt.HomieMessage;
import net.culnane.mqtt.HomieNode;
import net.culnane.mqtt.HomieProperty;
import net.culnane.mqtt.Message;

public class TemperatureSensorHomieMessage extends HomieMessage {

	public TemperatureSensorHomieMessage(String device, String name, String nodeId, String nodeName) {
		super(device, name, new HomieTemperatureNode(nodeId, nodeName));
	}
	
	/** 
	 *  homie / device123 / $homie → 3.0
	 *  homie / device123 / $name → My device
	 *  homie / device123 / $state → ready
	 *  homie / device123 / $nodes → mythermostat
	 *
	 *  homie / device123 / mythermostat / $name → My thermostat
	 *  homie / device123 / mythermostat / $properties → temperature
	 *
	 *  homie / device123 / mythermostat / temperature → 22 
	 *  homie / device123 / mythermostat / temperature / $name → Temperature
	 *  homie / device123 / mythermostat / temperature / $unit → °C
	 *  homie / device123 / mythermostat / temperature / $datatype → integer
	 *  homie / device123 / mythermostat / temperature / $settable → true
 **/
	
	public List<Message> getAnouncementMessages() {
		List<Message> messages = new ArrayList<Message>();
		messages.add(getVersionMessage());
		messages.add(getNameMessage());
		messages.add(getStateMessage());
		messages.addAll(getNodeAnouncementMessages());
		return messages;
	}
	
	
	protected List<Message> getNodeAnouncementMessages() {
		List<Message> messages = new ArrayList<Message>();
		for (HomieNode node : this.nodes) {
			messages.add(new Message(topicRoot + "$nodes", node.getNodeId()));
			messages.add(new Message(topicRoot + node.getNodeId() + HomieMessage.TOPIC_SEPARATOR + "$name", node.getName()));
			for (HomieProperty prop : node.getProperties()) {
				messages.addAll(prop.getAnouncementMessages(topicRoot + node.getNodeId() + HomieMessage.TOPIC_SEPARATOR ));
			}
		}
		return messages;
	}
	
}
