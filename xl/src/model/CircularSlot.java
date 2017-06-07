package model;

import expr.Environment;
import util.XLException;

public class CircularSlot implements Slot {
	
	@Override
	public double value(Environment env) {
		throw new XLException("circular dependancy");
	}

	@Override
	public String valueString(Environment env) {
		throw new XLException("circular Slot");
	}

}
