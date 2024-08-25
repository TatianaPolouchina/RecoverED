package ui;

import model.CheckInLog;
import model.MealLog;
import model.WeightLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the RecoverED app with a mealLog, checkInLog, and weightLog
public abstract class RecoveryApp {
    protected MealLog mealLog;
    protected CheckInLog checkInLog;
    protected WeightLog weightLog;

    protected Font sourceSans3Bold;
    protected Font sourceSans3Regular;
    private static final String JSON_STORE = "./data/recoverED.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: constructs a RecoveryApp with empty meal, check-in, and weight logs,
    //          and a JSON reader and writer
    public RecoveryApp() {
        mealLog = new MealLog();
        checkInLog = new CheckInLog();
        weightLog = new WeightLog();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setUpFonts();
    }

    // MODIFIES: sourceSans3, sourceSans3Bold
    // EFFECTS: Initializes fonts
    private void setUpFonts() {
        try {
            sourceSans3Bold = Font.createFont(Font.TRUETYPE_FONT,
                    new File("resources/fonts/SourceSans3-Bold.ttf"));
            sourceSans3Regular = Font.createFont(Font.TRUETYPE_FONT,
                    new File("resources/fonts/SourceSans3-Regular.ttf"));
        } catch (FontFormatException | IOException e) {
            sourceSans3Bold = UIManager.getFont("Label.font");
            sourceSans3Regular = UIManager.getFont("Label.font");
        }
    }

    //Portions of code received from
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: saves the weightLog, mealLog, and checkInLog to file
    protected void saveData(WeightLog weightLog, MealLog mealLog, CheckInLog checkInLog) {
        try {
            jsonWriter.open();
            jsonWriter.write(weightLog, mealLog, checkInLog);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            // nothing happens
        }
    }

    //Portions of code received from
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: Loads weightLog, mealLog, and checkInLog data
    protected void loadData() {
        try {
            loadWeightlog();
            loadMealLog();
            loadCheckInLog();
        } catch (IOException e) {
            // nothing happens
        }
    }

    //Portions of code received from
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads weightLog from file
    protected void loadWeightlog() throws IOException {
        weightLog = jsonReader.readWeightLog();
    }

    //Portions of code received from
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads mealLog from file
    private void loadMealLog() throws IOException {
        mealLog = jsonReader.readMealLog();
    }

    //Portions of code received from
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads checkInLog from file
    private void loadCheckInLog() throws IOException {
        checkInLog = jsonReader.readCheckInLog();
    }


}
