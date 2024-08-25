package ui.console;

import model.MealEntry;
import model.MealLog;

// Displays the user interface and allows user input for the meal log
public class MealLogUI extends RecoveryAppScanner {

    // MODIFIES: this
    // EFFECTS: Creates a new MealLogUI with a main menu
    public MealLogUI() {
        super();
    }

    @Override
    // EFFECTS: displays meal log menu options
    public void displayMenu() {
        System.out.println("\nMEAL LOG");
        System.out.println("(1) Enter new meal");
        System.out.println("(2) View entries");
        System.out.println("(3) Return to menu");
    }

    @Override
    // REQUIRES: command is one of 1, 2, or 3
    // EFFECTS: Processes command, sends user to corresponding tool
    public boolean processCommand(String command) {
        switch (command) {
            case "1":
                inputNewMeal();
                break;
            case "2":
                printEntries();
                break;
            case "3":
                System.out.println();
                return true;
        }
        return false;
    }

    // MODIFIES: mealLog
    // EFFECTS: processes user input to create a new meal and adds it to meal log
    public void inputNewMeal() {
        MealEntry entry = new MealEntry();
        System.out.println("\n--- NEW MEAL ENTRY ---");
        ui.nextLine();
        System.out.println("Food eaten: ");
        entry.setFoods(ui.nextLine());
        System.out.println("Location: ");
        entry.setLocation(ui.nextLine());
        printMealOptions();
        fillInMealType(entry);
        printStressOptions();
        fillInStressLevel(entry);
        fillInThoughts(entry);
        mealLog.addEntry(entry);
        System.out.println("--- ENTRY SAVED ---");
    }

    // EFFECTS: Prints meal options to console
    public void printMealOptions() {
        System.out.println("Meal type: ");
        System.out.println("  (1) Breakfast");
        System.out.println("  (2) Morning Snack");
        System.out.println("  (3) Lunch");
        System.out.println("  (4) Afternoon Snack");
        System.out.println("  (5) Dinner");
        System.out.println("  (6) Evening Snack");
        System.out.println("  (7) Other");
    }

    // REQUIRES: user input is an integer from 1-7
    // MODIFIES: entry, mealLog
    // EFFECTS: Prints meal type options and saves input
    public void fillInMealType(MealEntry entry) {
        switch (ui.nextLine()) {
            case "1":
                entry.setMealType("Breakfast");
                break;
            case "2":
                entry.setMealType("Morning Snack");
                break;
            case "3":
                entry.setMealType("Lunch");
                break;
            case "4":
                entry.setMealType("Afternoon Snack");
                break;
            case "5":
                entry.setMealType("Dinner");
                break;
            case "6":
                entry.setMealType("Evening Snack");
                break;
            case "7":
                entry.setMealType("Other");
                break;
        }
    }

    // EFFECTS: Prints stress level options to console
    public void printStressOptions() {
        System.out.println("Stress level:");
        System.out.println("  (1) None");
        System.out.println("  (2) Low");
        System.out.println("  (3) Moderate");
        System.out.println("  (4) High");
        System.out.println("  (5) Very High");
    }

    // REQUIRES: user input is one of 1, 2, 3, 4, or 5
    // MODIFIES: entry, mealLog
    // EFFECTS: Prints meal type options and saves input
    public void fillInStressLevel(MealEntry entry) {
        switch (ui.nextLine()) {
            case "1":
                entry.setStressLevel("None");
                break;
            case "2":
                entry.setStressLevel("Low");
                break;
            case "3":
                entry.setStressLevel("Moderate");
                break;
            case "4":
                entry.setStressLevel("High");
                break;
            case "5":
                entry.setStressLevel("Very High");
                break;
        }
    }

    // MODIFIES: entry, mealLog
    // EFFECTS: Prints thought entry options and saves input
    public void fillInThoughts(MealEntry entry) {
        System.out.println("Did you have any unhelpful thoughts during this meal? (y/n)");
        if (ui.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Describe your worries: ");
            entry.setWorry(ui.nextLine());
            System.out.println("Now, challenge and respond to the unhelpful thoughts: ");
            entry.setWorryResponse(ui.nextLine());
        }

        System.out.println("Enter additional thoughts? (y/n)");

        if (ui.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Additional thoughts:");
            entry.setOtherThoughts(ui.nextLine());
        }
    }

    // EFFECTS: Prints all saved meal entries to console
    public void printEntries() {
        for (MealEntry entry: mealLog.getMealEntries()) {
            System.out.println("\n------- ENTRY: " + entry.getMealType().toUpperCase() + " -------");
            printEntry(entry);
        }
    }

    // EFFECTS: Prints meal entry data
    public void printEntry(MealEntry entry) {
        System.out.println("Foods: " + entry.getFoods());
        System.out.println("Location: " + entry.getLocation());
        System.out.println("Meal Type: " + entry.getMealType());
        System.out.println("Stress Level: " + entry.getStressLevel());
        if (!entry.getWorry().equals("")) {
            System.out.println("Worries: " + entry.getWorry());
            System.out.println("Worry Response: " + entry.getWorryResponse());
        }
        if (!entry.getOtherThoughts().equals("")) {
            System.out.println("Additional Thoughts: " + entry.getOtherThoughts());
        }
    }

    // EFFECTS: Returns mealLog
    public MealLog getMealLog() {
        return mealLog;
    }

    // EFFECTS: Sets mealLog to ml
    public void setMealLog(MealLog ml) {
        this.mealLog = ml;
    }
}
