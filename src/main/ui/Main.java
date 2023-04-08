package ui;

import model.EventLog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

// creates new student manager console and runs it
public class Main {
    // creates and runs a student manager console
    public static void main(String[] args) throws FileNotFoundException {
        GUI gui = new GUI();
        gui.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog el = EventLog.getInstance();
                for (model.Event next : el) {
                    System.out.println(next.toString());
                }
            }
        });
        StudentManagerConsole smc = new StudentManagerConsole();
    }
}
