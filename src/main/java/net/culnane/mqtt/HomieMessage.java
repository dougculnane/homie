package net.culnane.mqtt;

import java.util.List;

/**
 * 
 * Use the homie convention for MQTT Message
 * 
 *  https://homieiot.github.io
 *  
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
public abstract class HomieMessage {

	final static protected String HOMIE_VERSION = "3.0";
	final static public String TOPIC_SEPARATOR = " / ";
	final static protected String TOPIC_PREFIX = "homie";
	
	final protected String device;
	final protected HomieNode[] nodes;
	final protected String name;
	final protected String topicRoot;
			
	public HomieMessage(final String device, final HomieNode node) {
		this(device, node.name, node);
	}
	
	public HomieMessage(final String device, final String name, final HomieNode node) {
		this.device = device;
		this.nodes = new HomieNode[]{node};
		this.name = name;
		this.topicRoot = HomieMessage.TOPIC_PREFIX + HomieMessage.TOPIC_SEPARATOR 
				+ this.device + HomieMessage.TOPIC_SEPARATOR;
	}
	
	abstract public List<Message> getAnouncementMessages();
	
	
	/**
	 * homie / device123 / $homie → 3.0
	 * 
	 * @return
	 */
	protected Message getVersionMessage() {
		return new Message(topicRoot + "$homie", HomieMessage.HOMIE_VERSION);
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
		return new Message(topicRoot + "$state", "ready");
	}
	
}