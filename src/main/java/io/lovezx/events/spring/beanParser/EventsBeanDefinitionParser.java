package io.lovezx.events.spring.beanParser;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import io.lovezx.events.EventDefinition;
import io.lovezx.events.expression.Expression;
import io.lovezx.events.expression.ExpressionParser;
import io.lovezx.events.spring.JavaProxyRegistryBeanPostProcessor;

public class EventsBeanDefinitionParser extends ProcessorSupportBeanDefinitionParser{
	
	@Override
	protected BeanDefinition doParse(Element element, ParserContext parserContext) {
		EventDefinition ed = new EventDefinition();
		//发布事件的类
		String className = element.getAttribute("class");
		//触发事件的方法
		String method = element.getAttribute("method");
		
		//事件属性
		Map<String, String> properties = new HashMap<String, String>();
		Element source = (Element)element.getElementsByTagNameNS("http://www.lovezx.io/schema/events", "source").item(0);
		NodeList list = source.getElementsByTagNameNS("http://www.lovezx.io/schema/events", "property");
		for(int i=0; i<list.getLength(); i++){
			Element property = (Element)list.item(i);
			property.getAttribute("name");
			property.getAttribute("value");
		}
		//事件触发条件
		String condition = element.getAttribute("condition");
		ExpressionParser ep = null;//TODO 
		ed.setCondition(ep.parse(condition));
		
		try {
			Class<?> eventClass = this.getClass().getClassLoader().loadClass(source.getAttribute("class"));
			ed.setEventClass(eventClass);
			Class<?> clazz = this.getClass().getClassLoader().loadClass(className);
			ed.setSourceClass(clazz);
			Method eventMethod = clazz.getMethod(method);
			ed.setSourceMethod(eventMethod);
			String name = element.getAttribute("id");
			ed.setName(name);
			context.getRegistry().registEvent(name, ed);
		} catch (ClassNotFoundException e) {
			if(logger.isErrorEnabled()){
				logger.error("类不存在->"+className, e);
			}
		} catch (NoSuchMethodException e) {
			if(logger.isErrorEnabled()){
				logger.error("方法不存在->"+method, e);
			}
		} catch (SecurityException e) {
			if(logger.isErrorEnabled()){
				logger.error(e);
			}
		}
		return null;
	}

	@Override
	protected Class<?> getProcessorClass() {
		return JavaProxyRegistryBeanPostProcessor.class;
	}
	
}
