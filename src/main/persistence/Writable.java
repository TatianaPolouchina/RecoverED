package persistence;

import org.json.JSONObject;

// Code received from
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Interface for objects whose data can be written to a JSON file
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
