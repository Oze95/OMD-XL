package gui;

import java.io.IOException;
import java.util.Observable;
import model.Sheet;
import util.XLException;

public class CurrentSlot extends Observable{
	private String currentSlot;
	private Sheet sh;
	private StatusLabel statusLabel;
	
	public CurrentSlot(Sheet sh, StatusLabel statusLabel) {
		currentSlot = "A1";
		this.sh = sh;
		this.statusLabel = statusLabel;
		setChanged();
		notifyObservers();
	}

	public void setCurrent(String slotLabel) {
		currentSlot = slotLabel;
		setChanged();
		notifyObservers();
	}
	
	public String getCurrentSlot() {
		return currentSlot;
	}
	
	public String getExpr() {
		return sh.getExpr(currentSlot);
	}
	
	public void inputExpr(String expr) {
		try {
			sh.addExprToSlot(expr, currentSlot);
			statusLabel.setText("");
		} catch (XLException | IOException | NullPointerException e) {
			statusLabel.setText(e.getMessage());
		} catch (Exception e) {
			statusLabel.setText("ERROR: 500");
			e.printStackTrace();
		}
	}
}
