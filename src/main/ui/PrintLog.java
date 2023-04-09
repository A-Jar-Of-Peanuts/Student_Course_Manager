package ui;

import model.EventLog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// prints event log when window closes
public class PrintLog {
    private GUI gui;

    // EFFECTS: creates new print log and adds window listener to gui
    public PrintLog(GUI gui) {
        this.gui = gui;
        gui.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog el = EventLog.getInstance();
                for (model.Event next : el) {
                    System.out.println(next.toString());
                }
            }
        });
    }
}
