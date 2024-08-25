package model;

import org.json.JSONObject;
import org.json.JSONArray;
import persistence.Writable;

import java.util.ArrayList;

// Represents a log of weight entries
public class WeightLog implements Writable {

    private ArrayList<WeightEntry> weightEntries;
    private WeightEntry minHealthyWeight;
    private WeightEntry maxHealthyWeight;
    private boolean setRange;
    private String weightSystem;

    // MODIFIES: this
    // EFFECTS: Creates a new weight log with lbs as units, no weight entries, and no set healthy range
    public WeightLog() {
        weightSystem = "lbs";
        weightEntries = new ArrayList<>();
        minHealthyWeight = new WeightEntry(0, "lbs");
        maxHealthyWeight = new WeightEntry(0, "lbs");
        setRange = false;
    }

    // MODIFIES: this
    // EFFECTS: Creates a new weight log with a specified weight system, entries, and healthy range
    public WeightLog(String weightSystem, ArrayList<WeightEntry> entries, WeightEntry minHealthyWeight,
                     WeightEntry maxHealthyWeight, boolean setRange) {
        this.weightSystem = weightSystem;
        this.weightEntries = entries;
        this.minHealthyWeight = minHealthyWeight;
        this.maxHealthyWeight = maxHealthyWeight;
        this.setRange = setRange;
    }

    // MODIFIES: this
    // EFFECTS: adds an entry to the weight log
    public void addEntry(WeightEntry entry) {
        this.weightEntries.add(entry);

        double roundedWeight = Math.round((entry.getWeight(weightSystem)) * 100.0) / 100.0;
        EventLog.getInstance().logEvent(
                new Event("Added new entry to weight log: " + roundedWeight + weightSystem));
    }

    // EFFECTS: Returns true if the weight is within or equal to healthyRange
    public boolean isHealthy(WeightEntry weightEntry) {
        double weight = weightEntry.getWeight(weightSystem);
        return (weight >= minHealthyWeight.getWeight(weightSystem)
                && weight <= maxHealthyWeight.getWeight(weightSystem));
    }

    // Returns true if a healthy range has been set
    public boolean rangeExists() {
        return this.setRange;
    }

    // REQUIRES: min <= max
    // EFFECTS: Sets the minimum and maximum healthy weight values
    public void setHealthyRange(double min, double max) {
        this.minHealthyWeight.setWeight(min, this.weightSystem);
        this.maxHealthyWeight.setWeight(max, this.weightSystem);
        this.setRange = true;
    }

    // REQUIRES: weight in min <= weights in max
    // EFFECTS: Sets the minimum and maximum healthy weight values
    public void setHealthyRange(WeightEntry minHealthyWeight, WeightEntry maxHealthyWeight) {
        this.minHealthyWeight = minHealthyWeight;
        this.maxHealthyWeight = maxHealthyWeight;
        this.setRange = true;
    }

    // EFFECTS: Returns the minimum healthy weight according to current weightSystem
    public double getMinHealthyWeight() {
        return minHealthyWeight.getWeight(weightSystem);
    }

    // EFFECTS: Returns the maximum healthy weight according to current weightSystem
    public double getMaxHealthyWeight() {
        return maxHealthyWeight.getWeight(weightSystem);
    }

    public void setSetRange(boolean setRange) {
        this.setRange = setRange;
    }

    public ArrayList<WeightEntry> getWeightEntries() {
        return weightEntries;
    }

    public void setWeightSystem(String weightSystem) {
        this.weightSystem = weightSystem;
        EventLog.getInstance().logEvent(new Event("Set weight system to " + weightSystem + "."));
    }

    public String getWeightSystem() {
        return weightSystem;
    }

    @Override
    // Portion of code received from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Returns this as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("weightEntries", weightEntriesToJson());
        json.put("minHealthyWeight", minHealthyWeightToJson());
        json.put("maxHealthyWeight", maxHealthyWeightToJson());
        json.put("setRange", setRange);
        json.put("weightSystem", weightSystem);
        return json;
    }

    // Portion of code received from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Returns weightEntries as a JSON array
    public JSONArray weightEntriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (WeightEntry entry: weightEntries) {
            jsonArray.put(entry.toJson());
        }

        return jsonArray;
    }

    // Portion of code received from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Returns minHealthyWeight as a JSON object
    public JSONObject minHealthyWeightToJson() {
        JSONObject json = new JSONObject();
        json.put("lbs", minHealthyWeight.getWeight("lbs"));
        json.put("kg", minHealthyWeight.getWeight("kg"));
        json.put("stone", minHealthyWeight.getWeight("stone"));
        return json;
    }

    // Portion of code received from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Returns minHealthyWeight as a JSON object
    public JSONObject maxHealthyWeightToJson() {
        JSONObject json = new JSONObject();
        json.put("lbs", maxHealthyWeight.getWeight("lbs"));
        json.put("kg", maxHealthyWeight.getWeight("kg"));
        json.put("stone", maxHealthyWeight.getWeight("stone"));
        return json;
    }

}
