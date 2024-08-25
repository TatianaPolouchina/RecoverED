package persistence;

import model.CheckInLog;
import model.MealLog;
import model.WeightLog;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of MealLog, CheckInLog, and WeightLog to file
// Portions of code received from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of weightLog, mealLog, and checkInLog to file
    public void write(WeightLog wl, MealLog ml, CheckInLog cil) {
        JSONObject jsonWeightLog = wl.toJson();
        JSONObject jsonMealLog = ml.toJson();
        JSONObject jsonCheckInLog = cil.toJson();

        JSONObject json = new JSONObject();
        json.put("weightLog", jsonWeightLog);
        json.put("mealLog", jsonMealLog);
        json.put("checkInLog", jsonCheckInLog);

        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of weightLog to file
    public void writeWeightLog(WeightLog wl) {
        JSONObject jsonWeightLog = wl.toJson();
        JSONObject json = new JSONObject();
        json.put("weightLog", jsonWeightLog);
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of checkInLog to file
    public void writeCheckInLog(CheckInLog cil) {
        JSONObject jsonCheckInLog = cil.toJson();
        JSONObject json = new JSONObject();
        json.put("checkInLog", jsonCheckInLog);
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of mealLog to file
    public void writeMealLog(MealLog ml) {
        JSONObject jsonMealLog = ml.toJson();
        JSONObject json = new JSONObject();
        json.put("mealLog", jsonMealLog);
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
