package io.lovezx.events.support;

import java.util.LinkedList;

import io.lovezx.events.Event;
import io.lovezx.events.EventListener;
import io.lovezx.events.EventPublisher;
import io.lovezx.events.EventRegistry;

public class DefaultEventPublisher implements EventPublisher {
	
	@Override
	public void publish(EventRegistry registry, Event event) {
		LinkedList<EventListener> list = registry.getListener(event.getId());
		for(EventListener el : list){
			el.fire(event);
		}
	}

}
