package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a meal documentation entry documenting the food type, location, meal type, stress level,
// worries, responses, and other thoughts
public class MealEntry implements Writable {

    private String foods;
    private String location;
    private String mealType;
    private String stressLevel;
    private String worry;
    private String worryResponse;
    private String otherThoughts;

    // MODIFIES: this
    // EFFECTS: Creates a new meal entry with empty fields
    public MealEntry() {
        this.foods = "";
        this.location = "";
        this.mealType = "";
        this.stressLevel = "";
        this.worry = "";
        this.worryResponse = "";
        this.otherThoughts = "";
    }

    // MODIFIES: this
    // EFFECTS: Creates a new meal entry with specified fields
    public MealEntry(String foods, String location, String mealType, String stressLevel,
                     String worry, String worryResponse, String otherThoughts) {
        this.foods = foods;
        this.location = location;
        this.mealType = mealType;
        this.stressLevel = stressLevel;
        this.worry = worry;
        this.worryResponse = worryResponse;
        this.otherThoughts = otherThoughts;
    }

    public String getFoods() {
        return foods;
    }

    public void setFoods(String foods) {
        this.foods = foods;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStressLevel() {
        return stressLevel;
    }

    public void setStressLevel(String stressLevel) {
        this.stressLevel = stressLevel;
    }

    public String getWorry() {
        return worry;
    }

    public void setWorry(String worry) {
        this.worry = worry;
    }

    public String getWorryResponse() {
        return worryResponse;
    }

    public void setWorryResponse(String worryResponse) {
        this.worryResponse = worryResponse;
    }

    public String getOtherThoughts() {
        return otherThoughts;
    }

    public void setOtherThoughts(String otherThoughts) {
        this.otherThoughts = otherThoughts;
    }

    @Override
    // Portion of code received from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Returns this as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("foods", foods);
        json.put("location", location);
        json.put("mealType", mealType);
        json.put("stressLevel", stressLevel);
        json.put("worry", worry);
        json.put("worryResponse", worryResponse);
        json.put("otherThoughts", otherThoughts);
        return json;
    }
}
