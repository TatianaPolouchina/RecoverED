package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an entry in the weight log, containing a weight value
public class WeightEntry implements Writable {

    private double lbs;
    private double kg;
    private double stone;

    // REQUIRES: system must be "lbs", "kg", or "stone"
    // MODIFIES: this
    // EFFECTS: Creates a new weight entry with entered weight
    public WeightEntry(double weight, String system) {
        setWeight(weight, system);
    }

    // REQUIRES: The weights must all be equal in their respective systems
    // MODIFIES: this
    // EFFECTS: Creates a new weight entry with entered weight
    public WeightEntry(double lbs, double kg, double stone) {
        this.lbs = lbs;
        this.kg = kg;
        this.stone = stone;
    }

    // REQUIRES: system must be "lbs", "kg", or "stone"
    // EFFECTS: Sets weight value to the entered value, without changing this.weightSystem
    public void setWeight(double weight, String weightSystem) {
        if (weightSystem.equals("lbs")) {
            this.lbs = weight;
            this.kg = convertToKg(weight, "lbs");
            this.stone = convertToStone(weight, "lbs");
        } else if (weightSystem.equals("kg")) {
            this.lbs = convertTolbs(weight, "kg");
            this.kg = weight;
            this.stone = convertToStone(weight, "kg");
        } else {
            this.lbs = convertTolbs(weight, "stone");
            this.kg = convertToKg(weight, "stone");
            this.stone = weight;
        }
    }

    // REQUIRES: weightSystem must be "lbs", "kg", or "stone"
    // EFFECTS: returns the weight corresponding to the current weight system
    public double getWeight(String weightSystem) {
        if (weightSystem.equals("lbs")) {
            return lbs;
        } else if (weightSystem.equals("kg")) {
            return kg;
        } else {
            return stone;
        }
    }

    // REQUIRES: originalSystem must be "lbs", "kg", or "stone"
    // MODIFIES: this
    // EFFECTS: Converts entered weight to kg
    public double convertToKg(double weight, String originalSystem) {
        if (originalSystem.equals("lbs")) {
            weight = weight / 2.205;
        } else if (originalSystem.equals("stone")) {
            weight = weight * 6.35;
        }
        return weight;
    }

    // REQUIRES: originalSystem must be "lbs", "kg", or "stone"
    // MODIFIES: this
    // EFFECTS: Converts entered weight to lbs
    public double convertTolbs(double weight, String originalSystem) {
        if (originalSystem.equals("kg")) {
            weight = weight / 0.45359237;
        } else if (originalSystem.equals("stone")) {
            weight = weight * 14;
        }
        return weight;
    }

    // REQUIRES: originalSystem must be "lbs", "kg", or "stone"
    // MODIFIES: this
    // EFFECTS: Converts entered weight to stone
    public double convertToStone(double weight, String originalSystem) {
        if (originalSystem.equals("kg")) {
            weight = weight / 6.35029;
        } else if (originalSystem.equals("lbs")) {
            weight = weight / 14;
        }
        return weight;
    }

    @Override
    // Portion of code received from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Returns this as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("lbs", lbs);
        json.put("kg", kg);
        json.put("stone", stone);
        return json;
    }
}
