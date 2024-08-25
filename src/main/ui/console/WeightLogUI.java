package ui.console;

import model.WeightEntry;
import model.WeightLog;

// Displays the user interface and allows user input for the weight log
public class WeightLogUI extends RecoveryAppScanner {

    // MODIFIES: this
    // EFFECTS: Creates a new WeightLogUI with a main menu
    public WeightLogUI() {
        super();
    }

    @Override
    // EFFECTS: displays weight log menu options
    public void displayMenu() {
        System.out.println("\nWEIGHT LOG");
        System.out.println("(1) Enter new weight");
        System.out.println("(2) View entries");
        System.out.println("(3) Edit healthy weight range");
        System.out.println("(4) Change weight system");
        System.out.println("    Current system: " + weightLog.getWeightSystem());
        System.out.println("(5) Return to menu");
    }

    @Override
    // REQUIRES: command is one of 1,2,3,4
    // EFFECTS: Processes command, sends user to corresponding tool
    public boolean processCommand(String command) {
        switch (command) {
            case "1":
                weightEntryDisplay();
                break;
            case "2":
                printEntries();
                break;
            case "3":
                setHealthyRange();
                break;
            case "4" :
                weightSystemDisplay();
                break;
            case "5":
                System.out.println();
                return true;
            default:
                System.out.println("Enter 1,2,3,4 or 5");
        }
        return false;
    }

    // REQUIRES: entered weight is a number
    // MODIFIES: weightLog
    // EFFECTS: inputs entry values and saves the entry to weightLog
    public void weightEntryDisplay() {
        System.out.println("\nWEIGHT (" + weightLog.getWeightSystem() + "):");
        ui.nextLine();
        double weight = Double.parseDouble(ui.nextLine());
        WeightEntry entry = new WeightEntry(weight, weightLog.getWeightSystem());
        weightLog.addEntry(entry);

        if (weightLog.rangeExists() && !weightLog.isHealthy(entry)) {
            printWarning();
        }

        System.out.println("--- ENTRY SAVED ---");
    }

    // EFFECTS: prints warning to console
    public void printWarning() {
        System.out.println("NOTE: This weight is outside of your healthy range\n");
    }

    // EFFECTS: Prints all saved weight entries to console
    public void printEntries() {
        int count = 1;
        String system = weightLog.getWeightSystem();
        System.out.println("\n--- ENTRIES ---");
        for (WeightEntry entry: weightLog.getWeightEntries()) {
            double weight = entry.getWeight(system);
            double roundedWeight = Math.round(weight * 100.0) / 100.0;
            System.out.println("Weight " + count + ": " + roundedWeight + system);
            count++;
        }
    }

    // REQUIRES: inputted weights are numbers
    // MODIFIES: weightLog
    // EFFECTS: Allows user to input healthy weight range and saved values to weightLog
    public void setHealthyRange() {
        double min;
        double max;
        String system = weightLog.getWeightSystem();

        System.out.println("\nNOTE: Healthy weight ranges should be determined by a professional\n");
        System.out.println("MINIMUM HEALTHY WEIGHT (" + system + "): ");
        ui.nextLine();
        min = Double.parseDouble(ui.nextLine());
        System.out.println("MAXIMUM HEALTHY WEIGHT (" + system + "): ");
        max = Double.parseDouble(ui.nextLine());
        weightLog.setHealthyRange(min, max);

        System.out.println("--- HEALTHY RANGE SET FROM " + min + system + " TO " + max + system + " ---");
    }

    // REQUIRES: input is one of 1, 2, 3
    // MODIFIES: weightLog
    // EFFECTS: displays weight system options and processes user input
    public void weightSystemDisplay() {
        ui.nextLine();
        System.out.println("Choose your weight system: ");
        System.out.println("(1) lbs");
        System.out.println("(2) kg");
        System.out.println("(3) stone");

        switch (ui.nextLine()) {
            case "1":
                weightLog.setWeightSystem("lbs");
                break;
            case "2":
                weightLog.setWeightSystem("kg");
                break;
            case "3":
                weightLog.setWeightSystem("stone");
        }
    }

    // Returns weightLog
    public WeightLog getWeightLog() {
        return weightLog;
    }

    // MODIFIES: this
    // EFFECTS: Sets the current weightLog to wl
    public void setWeightLog(WeightLog wl) {
        this.weightLog = wl;
    }
}
