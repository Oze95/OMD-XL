package model;

import java.io.IOException;

import expr.Environment;
import expr.Expr;
import expr.ExprParser;

public class ExprSlot implements Slot {
	Expr expr;

	public ExprSlot(String input) throws IOException {
		ExprParser parser = new ExprParser();
		try {
			this.expr = parser.build(input);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	@Override
	public double value(Environment env) {
		return expr.value(env);
	}

	@Override
	public String toString() {
		return expr.toString();
	}
	
	@Override
	public String valueString(Environment env) {
		return Double.toString(value(env));
	}

}
