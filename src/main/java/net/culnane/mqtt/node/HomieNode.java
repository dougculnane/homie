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
	
	public HomieNode(final String parentDeviceTopicRoot, final String nodeId, final String name) {
		topicRoot = parentDeviceTopicRoot + nodeId  + HomieDevice.TOPIC_SEPARATOR;
		this.nodeId = nodeId.toLowerCase(); 
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String getNodeId() {
		return nodeId;
	}
	
	public void addProperty(HomieProperty property) {
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
	
}
