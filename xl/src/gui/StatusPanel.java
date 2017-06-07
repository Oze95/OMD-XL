package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class StatusPanel extends BorderPanel {
    protected StatusPanel(StatusLabel statusLabel, CurrentLabel currentLabel) {
        add(WEST, currentLabel);
        add(CENTER, statusLabel);
    }
}