package net.culnane.mqtt;

public class HomieNode {
	
	final protected String nodeId;
	final protected String name;
	final protected HomieProperty[] properties;
	
	public HomieNode(final String nodeId, final String name, HomieProperty[] properties) {
		this.nodeId = nodeId; 
		this.name = name;
		this.properties = properties;
	}

	public String getName() {
		return name;
	}
	
	public String getNodeId() {
		return nodeId;
	}
	
	public HomieProperty[] getProperties() {
		return properties; 
	}

}
