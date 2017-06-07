package model;
import expr.Environment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Map;
import util.XLException;


public class Sheet extends Observable implements Environment {
	HashMap<String, Slot> sheet;

	public Sheet(){
		sheet = new HashMap<String, Slot>();
	}

	/**
	 * Clears all the stored Expr
	 */
	public void clearAll() {
		sheet.clear();
		setChanged();
		notifyObservers();
	}

	/**
	 * Clears the Expr in slot address
	 * @param address
	 */
	public void clearCurrentSlot(String address) {
		if (sheet.containsKey(address)) {
			sheet.remove(address);			
			setChanged();
			this.notifyObservers();
		}
	}

	
	public void saveToFile(String path) throws FileNotFoundException {
		XLPrintStream print = new XLPrintStream(path);
    	print.save(sheet.entrySet());
    	print.close();
	}

	/**
	 * Adds an Slot slot to a Slot address.
	 * @param address the label of the slot
	 * @param expr the expression
	 * @throws IOException invalid input
	 * @throws XLException invalid grammar
	 */
	public void addExprToSlot(String expr, String address) throws IOException, XLException {
		Slot slot;
		if (!expr.equals("") ) {
			try {
				slot = SlotWorkshop.createSlot(expr);				
			} catch (XLException e){
				throw new XLException("ERROR: slot insert fail. REASON: " + e.getMessage());
			}
			try {
				isCircular(address, slot);				
			} catch (XLException e) {
				throw new XLException("ERROR: slot insert fail. REASON: " + e.getMessage());
			} catch (NullPointerException e) {
				throw new NullPointerException("ERROR: slot insert fail. REASON: " + "referenced to empty slot.");
			}
			sheet.put(address, slot);
			setChanged();
			this.notifyObservers();
		}
	}


	/**
	 * Returns a String representation of the expression of slot address
	 * @param address
	 * @return String representation of the expression
	 */
	public String getExpr(String address) {
		if (!sheet.containsKey(address)) {
			return "";
		} else {
			return sheet.get(address).toString();
		}
	}

	/**
	 * Returns a double containing the value of the slot address. It is primarily used when searching for a value
	 * @param address
	 */
	public double value(String address) {
		return sheet.get(address).value(this);
	}

	/**
	 * Returns a string representation of the value of the slot address
	 * @param address
	 * @return String containing the value of address
	 */
	public String valueString(String address) {
		if (!sheet.containsKey(address)) {
			return "";
		} else {
			return sheet.get(address).valueString(this);
		}
	}	


	private void isCircular(String address, Slot slot) throws XLException {
		sheet.put(address, new CircularSlot());
		try {
			slot.value(this);
		} catch (XLException e) {
			sheet.remove(address);
			throw e;
		} catch (NullPointerException e) {
			sheet.remove(address);
			throw e;
		}
	}

	public void load(String path) throws IOException {
		
		Set<Map.Entry<String, Slot>> set = sheet.entrySet();
		for (Map.Entry<String,Slot> entry : set) {
			sheet.put(entry.getKey(), entry.getValue());
		}        
		XLBufferedReader xlBufferedReader = new XLBufferedReader(path);
		xlBufferedReader.load(sheet);
		xlBufferedReader.close();

	}
}
