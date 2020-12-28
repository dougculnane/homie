package net.culnane.mqtt.node;

import java.util.ArrayList;
import java.util.List;

import net.culnane.mqtt.HomieDevice;
import net.culnane.mqtt.Message;
import net.culnane.mqtt.property.HomieProperty;

public class HomieNode {
	
	final private String topicRoot;
	final protected String nodeId;
	final protected String name;
	final protected List<HomieProperty> properties = new ArrayList<HomieProperty>();
	
	public HomieNode(final HomieDevice homieDevice, final String nodeId, final String name) {
		this.topicRoot = homieDevice.getTopicRoot() + nodeId  + HomieDevice.TOPIC_SEPARATOR;
		this.nodeId = nodeId.toLowerCase(); 
		this.name = name;
		homieDevice.addNode(this);
	}
	
	public String getName() {
		return name;
	}
	
	public String getNodeId() {
		return nodeId;
	}
	
	public void addProperty(HomieProperty property) {
		property.setTopicRoot(this.getTopicRoot());
		properties.add(property);
	}
	
	public List<HomieProperty> getProperties() {
		return properties; 
	}
	
	public boolean handleMessage(Message message) {
		boolean commandMatachedAndCalled = false;
		if (message.getTopic().startsWith(topicRoot)) {
			for (HomieProperty property : properties) {
				if (message.getTopic().equals(topicRoot + property.getType() + "/set")) {
					property.handleCommandMessagePayload(message.getMessage());
					commandMatachedAndCalled = true;
				}
			}
		}
		return commandMatachedAndCalled;
	}

	public String getTopicRoot() {
		return topicRoot;
	}

	public List<Message> getStateMessages() {
		List<Message> messages = new ArrayList<Message>();
		for (HomieProperty prop : getProperties()) {
			messages.add(prop.getStateMessage());
		}
		return messages;
	}
	
}
