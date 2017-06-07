package model;
import util.XLException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class XLBufferedReader extends BufferedReader {
    public XLBufferedReader(String name) throws FileNotFoundException {
        super(new FileReader(name));
    }

    public void load(Map<String, Slot> map) {
        try {
            while (ready()) {
                String string = readLine();
                String[] index = string.split("=");
                String label = index[0];
                String expr = index[1];
                Slot slot = SlotWorkshop.createSlot(expr);
                map.put(label, slot);
            }
        } catch (Exception e) {
            throw new XLException(e.getMessage());
        }
    }
}
