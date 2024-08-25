package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a checklist containing check-in tasks
public class CheckList implements Writable {

    private ArrayList<CheckInTask> taskList;
    private CheckInTask normalizedEatingPattern;
    private CheckInTask foodVariety;
    private CheckInTask noOverExercising;
    private CheckInTask noBinging;
    private CheckInTask noPurging;
    private CheckInTask noBodyChecking;
    private CheckInTask noUntimelyWeighIns;
    private CheckInTask maintainWeight;
    private CheckInTask noShapeAvoidance;
    private CheckInTask noUnhelpfulShapeContributions;

    // MODIFIES: this
    // EFFECTS: Creates a new checklist containing 10 tasks
    public CheckList() {
        taskList = new ArrayList<>();
        setupTasks();
        addTasks();
    }

    // MODIFIES: this
    // EFFECTS: Creates a new checklist containing entered tasks
    public CheckList(CheckInTask normalizedEatingPattern, CheckInTask foodVariety, CheckInTask noOverExercising,
                     CheckInTask noBinging, CheckInTask noPurging, CheckInTask noBodyChecking,
                     CheckInTask noUntimelyWeighIns, CheckInTask maintainWeight, CheckInTask noShapeAvoidance,
                     CheckInTask noUnhelpfulShapeContributions) {

        this.normalizedEatingPattern = normalizedEatingPattern;
        this.foodVariety = foodVariety;
        this.noOverExercising = noOverExercising;
        this.noBinging = noBinging;
        this.noPurging = noPurging;
        this.noBodyChecking = noBodyChecking;
        this.noUntimelyWeighIns = noUntimelyWeighIns;
        this.maintainWeight = maintainWeight;
        this.noShapeAvoidance = noShapeAvoidance;
        this.noUnhelpfulShapeContributions = noUnhelpfulShapeContributions;
        taskList = new ArrayList<>();
        addTasks();
    }

    // EFFECTS: Assigns descriptions to 10 tasks
    public void setupTasks() {
        normalizedEatingPattern = new CheckInTask();
        foodVariety = new CheckInTask();
        noOverExercising = new CheckInTask();
        noBinging = new CheckInTask();
        noPurging = new CheckInTask();
        noBodyChecking = new CheckInTask();
        noUntimelyWeighIns = new CheckInTask();
        maintainWeight = new CheckInTask();
        noShapeAvoidance = new CheckInTask();
        noUnhelpfulShapeContributions = new CheckInTask();

        normalizedEatingPattern.setDescription("Maintaining a normalized eating pattern");
        foodVariety.setDescription("Eating a variety of foods (i.e. not excluding types of foods)");
        noOverExercising.setDescription("Interrupting overexercising");
        noBinging.setDescription("Interrupting binge eating");
        noPurging.setDescription("Interrupting purging");
        noBodyChecking.setDescription("No body checking");
        noUntimelyWeighIns.setDescription("No weighing self outside of planned times");
        maintainWeight.setDescription("Maintaining weight");
        noShapeAvoidance.setDescription("Not avoiding shape through loose clothing");
        noUnhelpfulShapeContributions.setDescription("Not attributing uncomfortable emotions to body type");
    }

    // MODIFIES: this
    // EFFECTS: adds tasks to taskList
    public void addTasks() {
        taskList.add(normalizedEatingPattern);
        taskList.add(foodVariety);
        taskList.add(noOverExercising);
        taskList.add(noBinging);
        taskList.add(noPurging);
        taskList.add(noBodyChecking);
        taskList.add(noUntimelyWeighIns);
        taskList.add(maintainWeight);
        taskList.add(noShapeAvoidance);
        taskList.add(noUnhelpfulShapeContributions);
    }

    public void setTaskList(ArrayList<CheckInTask> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<CheckInTask> getTaskList() {
        return taskList;
    }

    public void setNoUnhelpfulShapeContributions(boolean b) {
        this.noUnhelpfulShapeContributions.setChecked(b);
    }

    public void setNoShapeAvoidance(boolean b) {
        this.noShapeAvoidance.setChecked(b);
    }

    public void setMaintainWeight(boolean b) {
        this.maintainWeight.setChecked(b);
    }

    public void setNoUntimelyWeighIns(boolean b) {
        this.noUntimelyWeighIns.setChecked(b);
    }

    public void setNoBodyChecking(boolean b) {
        this.noBodyChecking.setChecked(b);
    }

    public void setNoPurging(boolean b) {
        this.noPurging.setChecked(b);
    }

    public void setNoBinging(boolean b) {
        this.noBinging.setChecked(b);
    }

    public void setNoOverExercising(boolean b) {
        this.noOverExercising.setChecked(b);
    }

    public void setFoodVariety(boolean b) {
        this.foodVariety.setChecked(b);
    }

    public void setNormalizedEatingPattern(boolean b) {
        this.normalizedEatingPattern.setChecked(b);
    }

    @Override
    // Portion of code received from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Returns this as a JSON array
    public JSONObject toJson() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(normalizedEatingPattern.toJson());
        jsonArray.put(foodVariety.toJson());
        jsonArray.put(noOverExercising.toJson());
        jsonArray.put(noBinging.toJson());
        jsonArray.put(noPurging.toJson());
        jsonArray.put(noBodyChecking.toJson());
        jsonArray.put(noUntimelyWeighIns.toJson());
        jsonArray.put(maintainWeight.toJson());
        jsonArray.put(noShapeAvoidance.toJson());
        jsonArray.put(noUnhelpfulShapeContributions.toJson());

        JSONObject json = new JSONObject();
        json.put("checklist", jsonArray);

        return json;
    }
}
