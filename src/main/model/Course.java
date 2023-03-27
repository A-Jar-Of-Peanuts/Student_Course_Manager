package model;

import persistence.Writable;
import org.json.JSONObject;
import org.json.JSONArray;

// A course with a name and the amount of credit its worth
public class Course implements Writable {
    private final String name;
    private final int credit;

    // EFFECTS: creates a course with the specified name and credit amount
    public Course(String name, int credit) {
        this.name = name;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    // EFFECTS: returns this as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("credit", credit);
        return json;
    }
}
