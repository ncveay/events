package io.lovezx.events;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import io.lovezx.events.expression.Expression;

public class EventDefinition {

	private Class<?> sourceClass;
	
	private Method sourceMethod;
	
	private Class<?> eventClass;
	
	private String name;

	private Expression condition;
	
	private Map<String, String> sourceProperties = new HashMap<>();
	
	public void addProperty(String name, String value){
		sourceProperties.put(name, value);
	}
	
	public Map<String, String> getProperties(){
		return sourceProperties;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getSourceClass() {
		return sourceClass;
	}

	public void setSourceClass(Class<?> sourceClass) {
		this.sourceClass = sourceClass;
	}

	public Method getSourceMethod() {
		return sourceMethod;
	}

	public void setSourceMethod(Method sourceMethod) {
		this.sourceMethod = sourceMethod;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public Class<?> getEventClass() {
		return eventClass;
	}

	public void setEventClass(Class<?> eventClass) {
		this.eventClass = eventClass;
	}

}
