package net.culnane.mqtt;

import java.util.List;

public abstract class HomieProperty {
	
	public abstract List<Message> getAnouncementMessages(String topicNodeRoot);
}
