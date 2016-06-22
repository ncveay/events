package io.lovezx.events;

public class Event {
	
	private String id;
	
	private Object source;

	
	public String getId() {
		return id;
	}

	public Event setId(String id) {
		this.id = id;
		return this;
	}

	public Object getSource() {
		return source;
	}

	public Event setSource(Object source) {
		this.source = source;
		return this;
	}
	
}
