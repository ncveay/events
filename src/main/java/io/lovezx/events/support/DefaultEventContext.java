package io.lovezx.events.support;

import io.lovezx.events.EventContext;
import io.lovezx.events.EventCreator;
import io.lovezx.events.EventPublisher;
import io.lovezx.events.EventRegistry;

public class DefaultEventContext implements EventContext {

	public DefaultEventContext(){}
	
	private EventRegistry registry = new EventRegistry();
	private EventCreator creator = new DefaultEventCreator();
	private EventPublisher publisher = new DefaultEventPublisher();
	
	@Override
	public EventRegistry getRegistry() {
		return registry;
	}

	@Override
	public EventCreator getCreator() {
		return creator;
	}

	@Override
	public EventPublisher getPublisher() {
		return publisher;
	}

}
