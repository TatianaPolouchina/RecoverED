package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

import static java.lang.Math.abs;

// Represents a check-in log containing check-in entries
public class CheckInLog implements Writable {

    private ArrayList<CheckInEntry> checkInEntries;

    // MODIFIES: this
    // EFFECTS: Creates new CheckInLog with no entries
    public CheckInLog() {
        checkInEntries = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds an entry to the log
    public void addEntry(CheckInEntry entry) {
        this.checkInEntries.add(entry);
        EventLog.getInstance().logEvent(
                new Event("Added new check-in entry"));
    }

    // REQUIRES: There are at least as many entries as finalEntry, firstEntry <= finalEntry
    // EFFECTS: Returns the average difference in actual vs. expected weights from firstEntry
    //          to finalEntry
    public double averageWeightDifference(int firstEntry, int finalEntry) {
        double totalWeightDifferences = 0;
        double weightDifference;
        double numEntries = finalEntry - firstEntry + 1;
        CheckInEntry currentEntry;

        for (int n = firstEntry - 1; n < finalEntry; n++) {
            currentEntry = checkInEntries.get(n);
            weightDifference = (abs(currentEntry.getWeight() - currentEntry.getWeightEstimate()));
            totalWeightDifferences += weightDifference;
        }

        return totalWeightDifferences / numEntries;
    }

    // REQUIRES: There are at least as many entries as finalEntry, firstEntry <= finalEntry
    // EFFECTS: Returns the average number of goals completed per entry from firstEntry
    //          to finalEntry
    public double averageGoalsCompleted(int firstEntry, int finalEntry) {
        double totalGoalsCompleted = totalGoalsCompleted(firstEntry, finalEntry);
        double numEntries = finalEntry - firstEntry + 1;
        return totalGoalsCompleted / numEntries;
    }

    // REQUIRES: The number of saved entries >= entry2num, entry1num >= 1, entry1Num <= entry2Num
    // EFFECTS: Returns the average number of goals completed, discarding the final saved entry.
    //          Returns -1 if only one entry has been entered, and it is the most recent entry.
    public double averageGoalsCompletedWithoutRecent(int entry1Num, int entry2Num, int numSavedEntries) {
        if (entry2Num == numSavedEntries) {
            if (entry1Num != entry2Num) {
                return averageGoalsCompleted(entry1Num, entry2Num - 1);
            }
        } else {
            return averageGoalsCompleted(entry1Num, entry2Num);
        }
        return -1;
    }

    // REQUIRES: There are at least as many entries as finalEntry, firstEntry <= finalEntry
    // EFFECTS: Returns the total number of goals completed in the last num entries
    public int totalGoalsCompleted(int firstEntry, int finalEntry) {
        int goalsCompleted = 0;
        CheckInEntry currentEntry;

        for (int n = firstEntry - 1; n < finalEntry; n++) {
            currentEntry = checkInEntries.get(n);
            goalsCompleted += currentEntry.numGoalsCompleted();
        }
        return goalsCompleted;
    }

    public ArrayList<CheckInEntry> getCheckInEntries() {
        return checkInEntries;
    }

    @Override
    // Portion of code received from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Returns this as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("checkInEntries", checkInEntriesToJson());
        return json;
    }

    // Portion of code received from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: Returns weightEntries as a JSON array
    public JSONArray checkInEntriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CheckInEntry entry : checkInEntries) {
            jsonArray.put(entry.toJson());
        }

        return jsonArray;
    }

}
