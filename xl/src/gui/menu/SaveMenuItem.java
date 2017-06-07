package gui.menu;

import gui.StatusLabel;
import gui.XL;
import model.Sheet;
import model.XLPrintStream;

import java.io.FileNotFoundException;
import javax.swing.JFileChooser;

class SaveMenuItem extends OpenMenuItem {
	Sheet sh;
	StatusLabel sl;
    public SaveMenuItem(XL xl, StatusLabel statusLabel, Sheet sh) {
        super(xl, statusLabel, "Save");
        this.sh = sh;
        this.sl = statusLabel;
    }

    protected void action(String path){
    	try {
			sh.saveToFile(path);
		} catch (FileNotFoundException e) {
			sl.setText(e.getMessage());
			e.printStackTrace();
		}
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showSaveDialog(xl);
    }
}