package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingConstants;

import model.Sheet;

public class SlotLabels extends GridPanel implements Observer {
    private List<SlotLabel> labelList;

    public SlotLabels(int rows, int cols, CurrentSlot currentSlot, Sheet sheet) {
        super(rows + 1, cols);
        sheet.addObserver(this);
        labelList = new ArrayList<SlotLabel>(rows * cols);
        for (char ch = 'A'; ch < 'A' + cols; ch++) {
            add(new ColoredLabel(Character.toString(ch), Color.LIGHT_GRAY,
                    SwingConstants.CENTER));
        }
        for (int row = 1; row <= rows; row++) {
            for (char ch = 'A'; ch < 'A' + cols; ch++) {
            	String strLabel = Character.toString(ch) + Integer.valueOf(row);
                SlotLabel label = new SlotLabel(strLabel, currentSlot);
                add(label);
                labelList.add(label);
            }
        }
        labelList.get(0).setBackground(Color.YELLOW);
        currentSlot.addObserver(labelList.get(0));
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		Sheet temp = (Sheet) arg0;
		for (SlotLabel sl : labelList) {
			sl.setText(temp.valueString(sl.getLabel()));
		}
	}
	
	public List<SlotLabel> getSlots() {
		return labelList;
	}
}
