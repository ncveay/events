package io.lovezx.events;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class EventRegistry implements Iterable<String>{
	
	private static Map<String, EventDefinition> EventRegistry = new HashMap<>();
	
	private static Map<String, LinkedList<EventListener>> listenerRegistry = new HashMap<>();
	
	public void registListener(String listenTo, EventListener ld){
		LinkedList<EventListener> ldContainer = listenerRegistry.get(listenTo);
		if(ldContainer == null) ldContainer = new LinkedList<>();
		ldContainer.addLast(ld);
		listenerRegistry.put(listenTo, ldContainer);
	}

	public LinkedList<EventListener> getListener(String listenTo){
		return listenerRegistry.get(listenTo);
	}
	
	public void registEvent(String name, EventDefinition ed){
		EventRegistry.put(name, ed);
	}

	public EventDefinition getEventDefinition(String name){
		return EventRegistry.get(name);
	}
	
	@Override
	public Iterator<String> iterator() {
		return EventRegistry.keySet().iterator();
	}
	
}
