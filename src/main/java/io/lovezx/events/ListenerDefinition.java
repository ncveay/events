package io.lovezx.events;

import java.util.List;

public class ListenerDefinition {

	private String event;
	private List<String> refBeans;
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public List<String> getRefBeans() {
		return refBeans;
	}
	public void setRefBeans(List<String> refBeans) {
		this.refBeans = refBeans;
	}
	
	
}
