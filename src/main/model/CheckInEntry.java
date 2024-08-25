package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a check-in entry with a weight estimate, weight, thoughts, checklist of tasks, and new goals
public class CheckInEntry implements Writable {

    private double weightEstimate;
    private double weight;

    private String thoughts;
    private CheckList checkList;
    private CheckInTask goal1;
    private CheckInTask goal2;

    // MODIFIES: this
    // EFFECTS: creates new CheckInEntry with empty values
    public CheckInEntry() {
        this.weightEstimate = 0.0;
        this.weight = 0.0;
        this.thoughts = "";
        this.checkList = new CheckList();
        this.goal1 = null;
        this.goal2 = null;
    }

    // MODIFIES: this
    // EFFECTS: creates new CheckInEntry with specified values
    public CheckInEntry(double weightEstimate, double weight, String thoughts, CheckList checkList,
                        CheckInTask goal1, CheckInTask goal2) {
        this.weightEstimate = weightEstimate;
        this.weight = weight;
        this.thoughts = thoughts;
        this.checkList = checkList;
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    // MODIFIES: this
    // EFFECTS: creates and assigns description to goal1
    public void createGoal1(String goal) {
        this.goal1 = new CheckInTask(goal);
    }

    // MODIFIES: this
    // EFFECTS: creates and assigns description to goal2
    public void createGoal2(String goal) {
        this.goal2 = new CheckInTask(goal);
    }

    // EFFECTS: Returns the number of completed goals
    public int numGoalsCompleted() {
        int goalsCompleted = 0;

        if (this.getGoal1().getChecked()) {
            goalsCompleted++;
        }
        if (this.getGoal2().getChecked()) {
            goalsCompleted++;
        }
        return goalsCompleted;
    }

    public void setWeightEstimate(double weightEstimate) {
        this.weightEstimate = weightEstimate;
    }

    public double getWeightEstimate() {
        return weightEstimate;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }

    public String getThoughts() {
        return thoughts;
    }

    public CheckList getCheckList() {
        return checkList;
    }

    public CheckInTask getGoal1() {
        return this.goal1;
    }

    public CheckInTask getGoal2() {
        return this.goal2;
    }

    @Override
    // Portion of code received from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Returns this as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("weightEstimate", weightEstimate);
        json.put("weight", weight);
        json.put("thoughts", thoughts);
        json.put("checklist", checkList.toJson());
        json.put("goal1", goal1.toJson());
        json.put("goal2", goal2.toJson());
        return json;
    }
}
