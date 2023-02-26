package persistence;

import model.*;
import org.json.JSONObject;
import java.io.*;

// Represents a writer that writes JSON representation of a course manager and student manager to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: contructs writer to write to destination
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer and throws FileNotFoundException if
    // file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of StudentManager and CourseManager to file
    public void write(StudentManager sm, CourseManager cm) {
        JSONObject smjson = sm.toJson();
        JSONObject cmjson = cm.toJson();
        saveToFile(smjson.toString(TAB));
        saveToFile(cmjson.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
