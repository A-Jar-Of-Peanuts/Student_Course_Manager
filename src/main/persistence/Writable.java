package persistence;

import org.json.JSONObject;

// creates object that can be written to JSON
public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
