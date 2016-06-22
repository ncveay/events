package io.lovezx.events.spring;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import io.lovezx.events.EventContext;
import io.lovezx.events.EventDefinition;
import io.lovezx.events.EventListener;
import io.lovezx.events.ListenerDefinition;
import io.lovezx.events.exception.IllegalRefenceListenerException;
import io.lovezx.events.support.EventContextHolder;

public class ListenerRegistryBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware{

	protected EventContext context = EventContextHolder.getContext();
	protected ApplicationContext appContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		appContext = applicationContext;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(!(bean instanceof ListenerDefinition)) return bean;
		
		ListenerDefinition ld = (ListenerDefinition)bean;
		
		String eventName = ld.getEvent();
		EventDefinition ed = context.getRegistry().getEventDefinition(eventName);
		if(ed == null) throw new IllegalRefenceListenerException("未定义的事件");
		
		List<String> refBeans = ld.getRefBeans();
		for(String listenerName : refBeans){
			Object obj = appContext.getBean(listenerName);
			EventListener listener = EventListener.class.cast(obj);
			context.getRegistry().registListener(eventName, listener);
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
