package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a log of meal entries
public class MealLog implements Writable {

    private ArrayList<MealEntry> mealEntries;

    // MODIFIES: this
    // EFFECTS: Creates a new meal log with 0 meal entries
    public MealLog() {
        mealEntries = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds an entry to the log
    public void addEntry(MealEntry entry) {
        mealEntries.add(entry);
        EventLog.getInstance().logEvent(
                new Event("Added new meal entry: " + entry.getMealType()));
    }

    public ArrayList<MealEntry> getMealEntries() {
        return mealEntries;
    }

    @Override
    // Portion of code received from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Returns this as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("mealEntries", mealEntriesToJson());
        return json;
    }

    // Portion of code received from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Returns weightEntries as a JSON array
    public JSONArray mealEntriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (MealEntry entry: mealEntries) {
            jsonArray.put(entry.toJson());
        }

        return jsonArray;
    }
}
