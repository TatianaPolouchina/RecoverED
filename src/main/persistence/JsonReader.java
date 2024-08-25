package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads MealLog, CheckInLog, and WeightLog from JSON data stored in file
// Portions of code received from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: reads weightLog from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WeightLog readWeightLog() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject jsonWeightLog = jsonObject.getJSONObject("weightLog");
        return parseWeightLog(jsonWeightLog);
    }

    // EFFECTS: reads mealLog from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MealLog readMealLog() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject jsonMealLog = jsonObject.getJSONObject("mealLog");
        return parseMealLog(jsonMealLog);
    }

    // EFFECTS: reads checkInLog from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CheckInLog readCheckInLog() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject jsonCheckInLog = jsonObject.getJSONObject("checkInLog");
        return parseCheckInLog(jsonCheckInLog);
    }

    // EFFECTS: parses weightLog from JSON object and returns it
    private WeightLog parseWeightLog(JSONObject jsonObject) {
        String weightSystem = jsonObject.getString("weightSystem");
        ArrayList<WeightEntry> entries = new ArrayList<>();

        addWeightEntries(entries,jsonObject);
        WeightEntry minHealthyWeight = parseWeight(jsonObject.getJSONObject("minHealthyWeight"));
        WeightEntry maxHealthyWeight = parseWeight(jsonObject.getJSONObject("maxHealthyWeight"));
        boolean setRange = jsonObject.getBoolean("setRange");

        WeightLog weightLog = new WeightLog(weightSystem, entries, minHealthyWeight, maxHealthyWeight, setRange);
        return weightLog;
    }

    // EFFECTS: parses a weight entry from JSON object and returns it
    private WeightEntry parseWeight(JSONObject jsonObject) {
        double lbs = jsonObject.getDouble("lbs");
        double kg = jsonObject.getDouble("kg");
        double stone = jsonObject.getDouble("stone");
        return new WeightEntry(lbs, kg, stone);
    }

    // MODIFIES: weightLog
    // EFFECTS: parses weight entries from JSON object and adds them to weightLog
    private void addWeightEntries(ArrayList entries, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("weightEntries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addWeightEntry(entries, nextEntry);
        }
    }

    // MODIFIES: weightLog
    // EFFECTS: parses weightEntry from JSON object and adds it to weightLog
    private void addWeightEntry(ArrayList<WeightEntry> entries, JSONObject jsonObject) {
        double lbs = jsonObject.getDouble("lbs");
        double kg = jsonObject.getDouble("kg");
        double stone = jsonObject.getDouble("stone");
        WeightEntry weightEntry = new WeightEntry(lbs, kg, stone);
        entries.add(weightEntry);
    }

    // EFFECTS: parses mealLog from JSON object and returns it
    private MealLog parseMealLog(JSONObject jsonObject) {
        MealLog mealLog = new MealLog();
        addMealEntries(mealLog, jsonObject);
        return mealLog;
    }

    // MODIFIES: mealLog
    // EFFECTS: parses meal entries from JSON object and adds them to mealLog
    private void addMealEntries(MealLog mealLog, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("mealEntries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addMealEntry(mealLog, nextEntry);
        }
    }

    // MODIFIES: mealLog
    // EFFECTS: parses mealEntry from JSON object and adds it to mealLog
    private void addMealEntry(MealLog mealLog, JSONObject jsonObject) {
        String foods = jsonObject.getString("foods");
        String location = jsonObject.getString("location");
        String mealType = jsonObject.getString("mealType");
        String stressLevel = jsonObject.getString("stressLevel");
        String worry = jsonObject.getString("worry");
        String worryResponse = jsonObject.getString("worryResponse");
        String otherThoughts = jsonObject.getString("otherThoughts");

        MealEntry mealEntry = new MealEntry(foods, location, mealType, stressLevel,
                worry, worryResponse, otherThoughts);
        mealLog.addEntry(mealEntry);
    }

    // EFFECTS: parses checkInLog from JSON object and returns it
    private CheckInLog parseCheckInLog(JSONObject jsonObject) {
        CheckInLog checkInLog = new CheckInLog();
        addCheckInEntries(checkInLog, jsonObject);
        return checkInLog;
    }

    // MODIFIES: checkInLog
    // EFFECTS: parses checkIn entries from JSON object and adds them to checkInLog
    private void addCheckInEntries(CheckInLog checkInLog, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("checkInEntries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addCheckInEntry(checkInLog, nextEntry);
        }
    }

    // MODIFIES: checkInLog
    // EFFECTS: parses checkInEntry from JSON object and adds it to checkInLog
    private void addCheckInEntry(CheckInLog checkInLog, JSONObject jsonObject) {
        double weightEstimate = jsonObject.getDouble("weightEstimate");
        double weight = jsonObject.getDouble("weight");
        String thoughts = jsonObject.getString("thoughts");
        CheckList checkList = parseCheckList(jsonObject);
        CheckInTask goal1 = parseCheckInTask(jsonObject.getJSONObject("goal1"));
        CheckInTask goal2 = parseCheckInTask(jsonObject.getJSONObject("goal2"));

        CheckInEntry checkInEntry = new CheckInEntry(weightEstimate, weight, thoughts, checkList,
                goal1, goal2);
        checkInLog.addEntry(checkInEntry);
    }

    // EFFECTS: parses CheckList data from jsonObject and returns the CheckList
    private CheckList parseCheckList(JSONObject jsonObject) {
        JSONObject json = jsonObject.getJSONObject("checklist");
        JSONArray jsonCheckList = json.getJSONArray("checklist");

        CheckInTask normalizedEatingPattern = parseCheckInTask(jsonCheckList.getJSONObject(0));
        CheckInTask foodVariety = parseCheckInTask(jsonCheckList.getJSONObject(1));
        CheckInTask noOverExercising = parseCheckInTask(jsonCheckList.getJSONObject(2));
        CheckInTask noBinging = parseCheckInTask(jsonCheckList.getJSONObject(3));
        CheckInTask noPurging = parseCheckInTask(jsonCheckList.getJSONObject(4));
        CheckInTask noBodyChecking = parseCheckInTask(jsonCheckList.getJSONObject(5));
        CheckInTask noUntimelyWeighIns = parseCheckInTask(jsonCheckList.getJSONObject(6));
        CheckInTask maintainWeight = parseCheckInTask(jsonCheckList.getJSONObject(7));
        CheckInTask noShapeAvoidance = parseCheckInTask(jsonCheckList.getJSONObject(8));
        CheckInTask noUnhelpfulShapeContributions = parseCheckInTask(jsonCheckList.getJSONObject(9));

        return  new CheckList(normalizedEatingPattern, foodVariety, noOverExercising, noBinging,
                noPurging, noBodyChecking, noUntimelyWeighIns, maintainWeight, noShapeAvoidance,
                noUnhelpfulShapeContributions);
    }

    // EFFECTS: parses CheckInTask data from jsonObject and returns the CheckInTask
    private CheckInTask parseCheckInTask(JSONObject jsonObject) {
        CheckInTask task = new CheckInTask();
        String description = jsonObject.getString("description");
        boolean checked = jsonObject.getBoolean("checked");
        task.setDescription(description);
        task.setChecked(checked);
        return task;
    }
}