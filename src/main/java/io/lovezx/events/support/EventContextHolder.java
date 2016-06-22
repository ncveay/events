package io.lovezx.events.support;

import io.lovezx.events.EventContext;

public class EventContextHolder {
	
	private static final DefaultEventContext INSTANCE = new DefaultEventContext();
	
	public static EventContext getContext(){
		return INSTANCE;
	}
	
}
