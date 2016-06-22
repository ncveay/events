package io.lovezx.events.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import io.lovezx.events.Event;
import io.lovezx.events.EventContext;
import io.lovezx.events.EventDefinition;
import io.lovezx.events.EventRegistry;
import io.lovezx.events.support.EventContextHolder;

public abstract class EventRegistryBeanPostProcessor implements BeanPostProcessor {

	protected final Log logger = LogFactory.getLog(getClass());
	
	protected EventContext context = EventContextHolder.getContext();
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		EventRegistry registry = context.getRegistry();
		for(String name : registry){
			EventDefinition ed = registry.getEventDefinition(name);
			if(bean.getClass().equals(ed.getSourceClass())){
				if(logger.isInfoEnabled()){
					logger.info("creating event proxy for "+ed.getSourceClass().getName());
				}
				return createProxy(bean, ed);
			}
		}
		return bean;
	}

	protected abstract Object createProxy(Object bean, EventDefinition ed);
	
	void processEventBefore(EventDefinition ed, Object[] params){
		Event event = context.getCreator().createBefore(ed, params);
		context.getPublisher().publish(context.getRegistry(), event);
	}

	void processEventAfter(EventDefinition ed, Object[] params, Object reval){
		Event event = context.getCreator().createAfter(ed, params, reval);
		context.getPublisher().publish(context.getRegistry(), event);
	}
}
