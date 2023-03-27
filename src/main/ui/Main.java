package ui;

import java.io.FileNotFoundException;

// creates new student manager console and runs it
public class Main {
    // creates and runs a student manager console
    public static void main(String[] args) throws FileNotFoundException {
        GUI gui = new GUI();
        StudentManagerConsole smc = new StudentManagerConsole();
    }
}
