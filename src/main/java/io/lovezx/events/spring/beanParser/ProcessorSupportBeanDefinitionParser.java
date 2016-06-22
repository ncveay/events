package io.lovezx.events.spring.beanParser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import io.lovezx.events.EventContext;
import io.lovezx.events.support.EventContextHolder;

public abstract class ProcessorSupportBeanDefinitionParser implements BeanDefinitionParser{

	protected EventContext context = EventContextHolder.getContext();
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		registryProcessorBeanDefinition(parserContext);
		return doParse(element, parserContext);
	}
	
	protected abstract BeanDefinition doParse(Element element, ParserContext parserContext);
	
	protected abstract Class<?> getProcessorClass();
	
	private void registryProcessorBeanDefinition(ParserContext parserContext){
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
		builder.getRawBeanDefinition().setBeanClass(getProcessorClass());
		builder.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
		builder.setLazyInit(false);
		AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
		BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, getProcessorClass().getName()+"$byEvents", new String[]{});
		BeanDefinitionReaderUtils.registerBeanDefinition(holder, parserContext.getRegistry());
	}
}
