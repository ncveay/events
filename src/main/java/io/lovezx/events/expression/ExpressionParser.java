package io.lovezx.events.expression;

public interface ExpressionParser {

	public Expression parse(String expressionString);
	
	public Expression parse(String expressionString, ExpressionParseContext context);
	
}
