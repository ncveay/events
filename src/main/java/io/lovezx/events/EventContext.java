package io.lovezx.events;

public interface EventContext {
	
	public EventRegistry getRegistry();
	
	public EventCreator getCreator();
	
	public EventPublisher getPublisher();

}
