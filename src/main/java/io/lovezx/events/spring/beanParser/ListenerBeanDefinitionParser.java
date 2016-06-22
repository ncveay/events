package io.lovezx.events.spring.beanParser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import io.lovezx.events.ListenerDefinition;
import io.lovezx.events.spring.ListenerRegistryBeanPostProcessor;

public class ListenerBeanDefinitionParser extends ProcessorSupportBeanDefinitionParser{

	@Override
	protected BeanDefinition doParse(Element element, ParserContext parserContext) {
		String listenTo = element.getAttribute("event");
		List<String> refBeans = new ArrayList<>();
		NodeList list = element.getElementsByTagNameNS("http://www.lovezx.io/schema/events", "trigger");
		for(int i=0; i<list.getLength(); i++){
			Element child = (Element)list.item(i);
			String refBean = child.getAttribute("ref");
			refBeans.add(refBean);
		}
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
		builder.getRawBeanDefinition().setBeanClass(ListenerDefinition.class);
		builder.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
		builder.setLazyInit(false);
		builder.addPropertyValue("refBeans", refBeans);
		builder.addPropertyValue("event", listenTo);
		AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
		BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, listenTo+"$ListenerList$byEvents", new String[]{});
		BeanDefinitionReaderUtils.registerBeanDefinition(holder, parserContext.getRegistry());
		return beanDefinition;
	}

	@Override
	protected Class<?> getProcessorClass() {
		return ListenerRegistryBeanPostProcessor.class;
	}
	
	
}
