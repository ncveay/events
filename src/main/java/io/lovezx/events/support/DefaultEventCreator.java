package io.lovezx.events.support;

import io.lovezx.events.Event;
import io.lovezx.events.EventCreator;
import io.lovezx.events.EventDefinition;

public class DefaultEventCreator implements EventCreator {

	@Override
	public Event createBefore(EventDefinition ed, Object[] params) {
		return new Event().setSource(params).setId(ed.getName());
	}

	@Override
	public Event createAfter(EventDefinition ed, Object[] params, Object reval) {
		return new Event().setSource(reval).setId(ed.getName());
	}

}
