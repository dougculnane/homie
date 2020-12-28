package net.culnane.mqtt;

import java.util.ArrayList;
import java.util.List;

import net.culnane.mqtt.node.HomieNode;
import net.culnane.mqtt.property.HomieProperty;

/**
 * 
 * Use the homie convention for MQTT Message
 * 
 *  https://homieiot.github.io
 *  
 *  Homies topic layout follows the pattern “homie/device/node/property”.
 *  
 *  Example
 * 
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
 *  
 * @author Doug
 *
 */
public class HomieDevice {

	/**
	 * Device Lifecycle
	 * 
	 * The $state device attribute represents the current state of the device. There are 6 different states:
	 */
	public enum DeviceState {
		init,          // device is connected to the MQTT broker, but has not yet sent all Homie messages and is not yet ready to operate. This state is optional, and may be sent if the device takes a long time to initialize, but wishes to announce to consumers that it is coming online.
        ready,         // device is connected to the MQTT broker, has sent all Homie messages and is ready to operate. A Homie Controller can assume default values for all optional topics.
        disconnected,  // device is cleanly disconnected from the MQTT broker. You must send this message before cleanly disconnecting.
        sleeping,      // device is sleeping. You have to send this message before sleeping.
        lost,          // device has been “badly” disconnected. You must define this message as LWT.
        alert          // device connected to the MQTT broker, but something wrong is happening. E.g. a sensor is not providing data and needs human intervention. You have to send this message when something is wrong.

	}
	private DeviceState state = DeviceState.init;
	
	final static protected String HOMIE_VERSION = "4.0";
	final static public String TOPIC_SEPARATOR = "/";
	final static protected String TOPIC_PREFIX = "homie";
	
	final protected String device;
	final protected List<HomieNode> nodes = new ArrayList<HomieNode>();
	final protected String name;
	final protected String topicRoot;
			
	public HomieDevice(final String deviceName) {
		this(deviceName, deviceName);
	}
	
	public HomieDevice(final String device, final String name) {
		this.device = device.toLowerCase();
		this.name = name;
		this.topicRoot = HomieDevice.TOPIC_PREFIX + HomieDevice.TOPIC_SEPARATOR 
				+ this.device + HomieDevice.TOPIC_SEPARATOR;
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
     */
	public List<Message> getMessages() {
		List<Message> messages = new ArrayList<Message>();
		messages.add(getVersionMessage());
		messages.add(getNameMessage());
		messages.add(getStateMessage());
		messages.addAll(getNodeMessages());
		return messages;
	}
	
	/**
	 * homie / device123 / $homie → 4.0
	 * 
	 * @return
	 */
	protected Message getVersionMessage() {
		return new Message(topicRoot + "$homie", HomieDevice.HOMIE_VERSION);
	}
	
	/**
	 * homie / device123 / $name → My device
	 * 
	 * @return
	 */
	protected Message getNameMessage() {
		return new Message(topicRoot + "$name", this.name);
	}
	
	/**
	 * homie / device123 / $state → ready
	 * 
	 * @return
	 */
	public Message getStateMessage() {
		return new Message(topicRoot + "$state", state.toString());
	}

	public void addNode(HomieNode node) {
		nodes.add(node);
	}
	
	public void setState(DeviceState state) {
		this.state = state;
	}
	
	protected List<Message> getNodeMessages() {
		List<Message> messages = new ArrayList<Message>();
		String nodeIds = "";
		for (HomieNode node : this.nodes) {
			if (nodeIds.length() == 0) {
				nodeIds = node.getNodeId();
			} else {
				nodeIds = nodeIds + "," + node.getNodeId();
			}
			messages.add(new Message(node.getTopicRoot() + "$name", node.getName()));
			int properiesPosition = messages.size();
			String propTypes = "";
			for (HomieProperty prop : node.getProperties()) {
				if (propTypes.length() == 0) {
					propTypes = prop.getType();
				} else {
					propTypes = propTypes + "," + prop.getType();
				}
				String topicPropPath = node.getTopicRoot() + prop.getType() + HomieDevice.TOPIC_SEPARATOR;
				messages.add(new Message(topicPropPath + "$name", prop.getName()));
				if (prop.getUnit() != null) {
					messages.add(new Message(topicPropPath + "$unit", prop.getUnit()));
				}
				messages.add(new Message(topicPropPath + "$datatype", prop.getDataType()));
				messages.add(new Message(topicPropPath + "$settable", prop.isSetable() ? "true" : "false" ));
			}
			messages.add(properiesPosition, new Message(node.getTopicRoot() + "$properties", propTypes));
		}
		messages.add(0, new Message(topicRoot + "$nodes", nodeIds));
		return messages;
	}
	
	public List<Message> getStateMessages() {
		List<Message> messages = new ArrayList<Message>();
		messages.add(getStateMessage());
		for (HomieNode node : this.nodes) {
			for (HomieProperty prop : node.getProperties()) {
				messages.add(new Message(topicRoot + node.getNodeId() + HomieDevice.TOPIC_SEPARATOR + prop.getType(), prop.getMessagePayload()));
			}
		}
		return messages;
	}
	
	public String getTopicRoot() {
		return topicRoot;
	}
	
}