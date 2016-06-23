package io.lovezx.events.expression;

public interface Expression {
	
	Object getValue();
	
	Class<?> getValueType();
	
	Object getValue(ExpressionContext context);
	
	Class<?> getValueType(ExpressionContext context);
}
