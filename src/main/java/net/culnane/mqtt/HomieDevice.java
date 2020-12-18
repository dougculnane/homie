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
	enum DeviceState {
		init,          // device is connected to the MQTT broker, but has not yet sent all Homie messages and is not yet ready to operate. This state is optional, and may be sent if the device takes a long time to initialize, but wishes to announce to consumers that it is coming online.
        ready,         // device is connected to the MQTT broker, has sent all Homie messages and is ready to operate. A Homie Controller can assume default values for all optional topics.
        disconnected,  // device is cleanly disconnected from the MQTT broker. You must send this message before cleanly disconnecting.
        sleeping,      // device is sleeping. You have to send this message before sleeping.
        lost,          // device has been “badly” disconnected. You must define this message as LWT.
        alert          // device connected to the MQTT broker, but something wrong is happening. E.g. a sensor is not providing data and needs human intervention. You have to send this message when something is wrong.

	}
	private DeviceState state = DeviceState.init;
	
	final static protected String HOMIE_VERSION = "3.0";
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
	
	public void hasBeenInitialized(boolean initDone) {
		this.state = initDone ? DeviceState.ready : DeviceState.init;
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
	 * homie / device123 / $homie → 3.0
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
	protected Message getStateMessage() {
		return new Message(topicRoot + "$state", state.toString());
	}

	public void addNode(HomieNode node) {
		nodes.add(node);
	}
	
	protected List<Message> getNodeMessages() {
		List<Message> messages = new ArrayList<Message>();
		for (HomieNode node : this.nodes) {
			messages.add(new Message(topicRoot + "$nodes", node.getNodeId()));
			messages.add(new Message(topicRoot + node.getNodeId() + HomieDevice.TOPIC_SEPARATOR + "$name", node.getName()));
			for (HomieProperty prop : node.getProperties()) {
				String topicPath = topicRoot + node.getNodeId() + HomieDevice.TOPIC_SEPARATOR;
				messages.add(new Message(topicPath + "$properties", prop.getType()));
				topicPath = topicPath + prop.getType();
				messages.add(new Message(topicPath, prop.getValue()));
				topicPath = topicPath + HomieDevice.TOPIC_SEPARATOR;
				messages.add(new Message(topicPath + "$name", prop.getName()));
				messages.add(new Message(topicPath + "$unit", prop.getUnit()));
				messages.add(new Message(topicPath + "$datatype ", prop.getDataType()));
				messages.add(new Message(topicPath + "$settable ", prop.isSetable() ? "true" : "false" ));
			}
		}
		return messages;
	}

	public List<Message> getStateMessages() {
		List<Message> messages = new ArrayList<Message>();
		messages.add(getStateMessage());
		for (HomieNode node : this.nodes) {
			for (HomieProperty prop : node.getProperties()) {
				messages.add(new Message(topicRoot + node.getNodeId() + HomieDevice.TOPIC_SEPARATOR + prop.getType(), prop.getValue()));
			}
		}
		return messages;
	}
	
	public String getTopicRoot() {
		return topicRoot;
	}
	
}