package io.lovezx.events;

public interface EventPublisher {

	public void publish(EventRegistry registry, Event event);
	
}
