package io.lovezx.events;

public interface EventCreator {

	public Event createBefore(EventDefinition ed, Object[] params);
	
	public Event createAfter(EventDefinition ed, Object[] params, Object reval);
	
}
