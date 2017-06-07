package gui.menu;

import gui.StatusLabel;
import gui.XL;
import model.Sheet;
import java.io.IOException;
import javax.swing.JFileChooser;

class LoadMenuItem extends OpenMenuItem {
	Sheet sheet;
	StatusLabel statusLabel;

	public LoadMenuItem(XL xl, StatusLabel statusLabel, Sheet sheet) {
		super(xl, statusLabel, "Load");
		this.sheet = sheet;
		this.statusLabel = statusLabel;
		
	}
	
	protected void action(String path) {
		try {
			sheet.load(path);
		} catch (IOException e) {
			statusLabel.setText("ERROR: failed to load file. REASON: file not found");
			e.printStackTrace();
		}

	}

	protected int openDialog(JFileChooser fileChooser) {
		return fileChooser.showOpenDialog(xl);
	}
}