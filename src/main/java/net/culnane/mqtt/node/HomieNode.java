package net.culnane.mqtt.node;

import java.util.ArrayList;
import java.util.List;

import net.culnane.mqtt.property.HomieProperty;

public class HomieNode {
	
	final protected String nodeId;
	final protected String name;
	final protected List<HomieProperty> properties = new ArrayList<HomieProperty>();
	
	public HomieNode(final String nodeId, final String name) {
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
	
	public boolean update(Object newValue) {
		boolean changes = false;
		for (HomieProperty property : properties) {
			if (property.update(newValue)) {
				changes = true;
			}
		}
		return changes;
	}

}
