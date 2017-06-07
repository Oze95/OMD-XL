package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class SlotLabel extends ColoredLabel implements Observer, MouseListener{
	private String label;
	private CurrentSlot currentSlot;
	
	
    public SlotLabel(String label, CurrentSlot currentSlot) {
        super("                    ", Color.WHITE, RIGHT);
        this.label = label;
        this.currentSlot = currentSlot;
        addMouseListener(this);
    }

	@Override
	public void update(Observable o, Object arg) {
		this.setBackground(Color.WHITE);
		currentSlot.deleteObserver(this);
	}
	
	public String getLabel() {
		return label;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		currentSlot.setCurrent(label);
		currentSlot.addObserver(this);
		this.setBackground(Color.YELLOW);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}