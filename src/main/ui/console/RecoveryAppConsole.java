package ui.console;

import java.io.IOException;

// Displays the user interface and allows user input for the main menu of the app
public class RecoveryAppConsole extends RecoveryAppScanner {
    private MealLogUI mealLogUI;
    private CheckInLogUI checkInLogUI;
    private WeightLogUI weightLogUI;

    //Portions of code received from
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: Creates a new RecoveryApp with a main menu and mealLogUI, checkInLogUI, weightLogUI
    public RecoveryAppConsole() {
        super();
        mealLogUI = new MealLogUI();
        checkInLogUI = new CheckInLogUI();
        weightLogUI = new WeightLogUI();
        loadOptions();
        start();
    }

    @Override
    // EFFECTS: Prints menu options to console
    public void displayMenu() {
        System.out.println("Welcome to RecoverED");
        System.out.println("(1) Open meal log");
        System.out.println("(2) Open check-in log");
        System.out.println("(3) Open weight log");
        System.out.println("(4) Quit");
    }

    @Override
    // REQUIRES: command is one of 1,2,3,4
    // EFFECTS: Processes command, sends user to corresponding tool
    public boolean processCommand(String command) {
        switch (command) {
            case "1":
                mealLogUI.start();
                break;
            case "2":
                checkInLogUI.start();
                break;
            case "3":
                weightLogUI.start();
                break;
            case "4":
                saveOptions();
                end();
                return true;
        }
        return false;
    }

    // EFFECTS: Prints and allows user option to save files
    private void saveOptions() {
        System.out.println("Save changes?");
        System.out.println("(y/n)");
        if (ui.next().equalsIgnoreCase("y")) {
            saveData();
        }
    }

    //Portions of code received from
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: saves the weightLog, mealLog, and checkInLog to file
    private void saveData() {
        super.saveData(weightLogUI.getWeightLog(), mealLogUI.getMealLog(), checkInLogUI.getCheckInLog());
    }

    // MODIFIES: this
    // EFFECTS: Prints and allows user option to save files
    private void loadOptions() {
        System.out.println("Load data?");
        System.out.println("(y/n)");
        if (ui.next().equalsIgnoreCase("y")) {
            loadData();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads weightLog from file
    public void loadWeightlog() throws IOException {
        super.loadWeightlog();
        weightLogUI.setWeightLog(super.weightLog);
    }

    // MODIFIES: this
    // EFFECTS: loads mealLog from file
    private void loadMealLog() throws IOException {
        mealLogUI.setMealLog(super.mealLog);
    }

    // MODIFIES: this
    // EFFECTS: loads checkInLog from file
    private void loadCheckInLog() throws IOException {
        checkInLogUI.setCheckInLog(super.checkInLog);
    }
}
