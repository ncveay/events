package io.lovezx.events.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import io.lovezx.events.spring.beanParser.EventsBeanDefinitionParser;
import io.lovezx.events.spring.beanParser.ListenerBeanDefinitionParser;

public class EventsNamespaceHandler extends NamespaceHandlerSupport{

	@Override
	public void init() {
		registerBeanDefinitionParser("event", new EventsBeanDefinitionParser());
		registerBeanDefinitionParser("listener", new ListenerBeanDefinitionParser());
	}

}
