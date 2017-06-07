package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

import model.Sheet;

public class Editor extends JTextField implements Observer{
	private ActionListener enter;
	private CurrentSlot currentSlot;
	
    public Editor(Sheet sheet, CurrentSlot currentSlot) {
        setBackground(Color.WHITE);
        this.currentSlot = currentSlot;
        enter = new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		inputExpr();
        	}
        };
        this.addActionListener(enter);
    }

	@Override
	public void update(Observable o, Object arg) {
		currentSlot = (CurrentSlot) o;
		setText(currentSlot.getExpr());
		
	}
	
	public void inputExpr() {
		currentSlot.inputExpr(this.getText());	
	}
}