package net.culnane.mqtt;

public class Message {
	
	private String topic;
	
	private String message;
	
	public Message(String topic, String message){
		this.topic = topic;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public String getTopic() {
		return topic;
	}

	@Override
	public String toString(){
		return getTopic() + " → " + getMessage();
	}

}
