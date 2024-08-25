package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a task with a checkbox and description
public class CheckInTask implements Writable {

    private boolean checked;
    private String description;

    // MODIFIES: this
    // EFFECTS: Creates new unchecked task without description
    public CheckInTask() {
        this.checked = false;
        this.description = "";
    }

    // EFFECTS: Creates new unchecked task with description
    public CheckInTask(String description) {
        this.checked = false;
        this.description = description;
    }

    public void setChecked(boolean b) {
        this.checked = b;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    // Portion of code received from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Returns this as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("checked", checked);
        json.put("description", description);

        return json;
    }
}
