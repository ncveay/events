package io.lovezx.events.spring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import io.lovezx.events.EventDefinition;

public class JavaProxyRegistryBeanPostProcessor extends EventRegistryBeanPostProcessor {

	protected Object createProxy(Object bean, EventDefinition ed) {
		return Proxy.newProxyInstance(this.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler(){
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				processEventBefore(ed, args);
				Object reval = method.invoke(bean, args);
				processEventAfter(ed, args, reval);
				return reval;
			}
		});
	}
	
}
