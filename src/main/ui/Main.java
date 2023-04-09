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
        PrintLog pl = new PrintLog(gui);
        StudentManagerConsole smc = new StudentManagerConsole();
    }
}
