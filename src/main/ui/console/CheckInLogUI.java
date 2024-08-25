package ui.console;

import model.CheckInEntry;
import model.CheckInLog;
import model.CheckInTask;
import model.CheckList;

// Displays the user interface and allows user input for the check-in log
public class CheckInLogUI extends RecoveryAppScanner {

    // MODIFIES: this
    // EFFECTS: Creates a new CheckInLogUI with a main menu
    public CheckInLogUI() {
        super();
    }

    @Override
    // EFFECTS: displays check-in log menu options
    public void displayMenu() {
        System.out.println("\nCHECK-IN LOG");
        System.out.println("(1) Create new entry");
        System.out.println("(2) View entries");
        System.out.println("(3) View statistics");
        System.out.println("(4) Return to menu");
    }

    @Override
    // REQUIRES: command is one of 1,2,3,4
    // EFFECTS: Processes command, sends user to corresponding tool
    public boolean processCommand(String command) {
        switch (command) {
            case "1":
                createEntry();
                break;
            case "2":
                printEntries();
                break;
            case "3":
                statisticsMenu();
                break;
            case "4":
                System.out.println();
                return true;
        }
        return false;
    }

    // REQUIRES: The first two user inputs are numbers
    // MODIFIES: Menu.checkInLog
    // EFFECTS: inputs entry values and saves the entry to checkInLog
    public void createEntry() {
        CheckInEntry entry = new CheckInEntry();
        CheckList checkList = entry.getCheckList();

        System.out.println("\n--- NEW ENTRY ---");
        System.out.println("Estimated weight (lbs): ");
        entry.setWeightEstimate(Double.parseDouble(ui.next()));
        System.out.println("Actual weight (lbs):");
        entry.setWeight(Double.parseDouble(ui.next()));
        System.out.println("Thoughts: ");
        ui.nextLine();
        entry.setThoughts(ui.nextLine());
        fillInCheckList(checkList);
        fillInGoals(entry);

        checkInLog.addEntry(entry);
        System.out.println("--- ENTRY SAVED ---");
    }

    // REQUIRES: user input is either "y" or "n"
    // MODIFIES: checklist
    // EFFECTS: Prints question and submits corresponding answer to the checklist
    public void fillInCheckList(CheckList checkList) {
        boolean answer;

        System.out.println("\nChecklist:");
        System.out.println("----------------");
        System.out.println("For the following, enter \ny for yes" + "\nn for no");
        System.out.println("----------------");

        for (CheckInTask task: checkList.getTaskList()) {
            System.out.println(task.getDescription() + ":");
            answer = yesOrNoToBoolean(ui.nextLine());
            task.setChecked(answer);
        }
    }

    // MODIFIES: entry
    // EFFECTS: Prints and fills in completion of goals from previous entry and
    // fills in new goals
    public void fillInGoals(CheckInEntry entry) {
        int numEntries = checkInLog.getCheckInEntries().size();
        String input;

        System.out.println();
        if (numEntries != 0) {
            CheckInEntry lastEntry = checkInLog.getCheckInEntries().get(numEntries - 1);
            updateGoals(lastEntry);
        }
        System.out.println("Enter two goals to complete by your next entry: ");
        System.out.println("GOAL 1: ");
        input = ui.nextLine();
        entry.createGoal1(input);
        System.out.println("GOAL 2: ");
        input = ui.nextLine();
        entry.createGoal2(input);
    }

    // REQUIRES: user input is either "y" or "n"
    // MODIFIES: entry
    // EFFECTS: Processes user input to mark goals as completed or uncompleted
    public void updateGoals(CheckInEntry entry) {
        CheckInTask goal1 = entry.getGoal1();
        CheckInTask goal2 = entry.getGoal2();
        boolean completed;

        System.out.println("Goals:");
        System.out.println("----------------");
        System.out.println("Enter \"y\" if the goal was completed, \"n\" if it was not");
        System.out.println("----------------");
        System.out.println("Goals from previous entry:");
        System.out.println("GOAL 1: \"" + goal1.getDescription() + "\"");
        completed = yesOrNoToBoolean(ui.nextLine());
        goal1.setChecked(completed);
        System.out.println("GOAL 2: \"" + goal2.getDescription() + "\"");
        completed = yesOrNoToBoolean(ui.nextLine());
        goal2.setChecked(completed);
        System.out.println();
    }

    // EFFECTS: Prints all saved check-in entries to console
    public void printEntries() {
        int count = 1;
        for (CheckInEntry entry: checkInLog.getCheckInEntries()) {
            System.out.println("------- ENTRY #" + count + " -------");
            printEntry(entry);
            System.out.println();
            count++;
        }
    }

    // EFFECTS: Prints the entry to console
    public void printEntry(CheckInEntry entry) {
        System.out.println("OVERVIEW:");
        System.out.println("Estimated weight: " + entry.getWeightEstimate());
        System.out.println("Actual weight: " + entry.getWeight());
        System.out.println("Thoughts: " + entry.getThoughts());

        System.out.println("-----------------------");
        System.out.println("CHECKLIST:");
        printCheckList(entry.getCheckList());

        System.out.println("-----------------------");
        System.out.println("GOALS:");
        System.out.println("Goal 1: \"" + entry.getGoal1().getDescription() + "\"");
        System.out.println(booleanToComplete(entry.getGoal1().getChecked()));
        System.out.println("Goal 2: \"" + entry.getGoal2().getDescription() + "\"");
        System.out.println(booleanToComplete(entry.getGoal2().getChecked()));
    }

    // EFFECTS: Prints checklist questions and saved answers in the format "question: answer"
    public void printCheckList(CheckList checkList) {
        String answer;

        for (CheckInTask task: checkList.getTaskList()) {
            System.out.print(task.getDescription() + ": ");
            answer = booleanToYesOrNo(task.getChecked());
            System.out.println(answer);
        }
    }

    // EFFECTS: Returns true if the entered string is "Y", not case-sensitive, spaces do not count
    public boolean yesOrNoToBoolean(String answer) {
        return answer.toLowerCase().trim().equals("y");
    }

    // EFFECTS: Returns yes if b is true, no if b is false
    public String booleanToYesOrNo(boolean b) {
        if (b) {
            return "Yes";
        }
        return "No";
    }

    // EFFECTS: Returns "incomplete" if false, "complete" if true
    public String booleanToComplete(boolean b) {
        if (b) {
            return "complete";
        }
        return "incomplete";
    }

    // REQUIRES: user input is either 1 or 2
    // EFFECTS: Prints menu and processes user input to print statistics for specified number of entries
    public void statisticsMenu() {
        int numSavedEntries = checkInLog.getCheckInEntries().size();
        System.out.println("\nPrint statistics for...");
        System.out.println("(1) all entries");
        System.out.println("(2) a certain number of recent entries");
        ui.nextLine();

        switch (ui.nextLine().trim()) {
            case "1":
                printStatistics(1, numSavedEntries, numSavedEntries);
                break;
            case "2":
                System.out.println("Enter the number of entries");
                int numEntries = Integer.parseInt(ui.nextLine());
                printStatistics(numSavedEntries - numEntries + 1, numSavedEntries, numSavedEntries);
                break;
            default:
                System.out.println("Please enter 1 or 2");
        }
    }

    // REQUIRES: The number of saved entries >= entry2num, entry1num >= 1, entry1Num <= entry2Num
    // EFFECTS: Prints The average difference between expected and actual weights
    // and the overall and average number of goals completed from entry 1 to entry 2
    public void printStatistics(int entry1Num, int entry2Num, int numSavedEntries) {
        int numEntries = entry2Num - entry1Num + 1;
        int totalGoalsCompleted = checkInLog.totalGoalsCompleted(entry1Num, entry2Num);
        double avgGoalsCompleted = checkInLog.averageGoalsCompletedWithoutRecent(entry1Num, entry2Num, numSavedEntries);
        double avgWeightDifference = checkInLog.averageWeightDifference(entry1Num, entry2Num);

        System.out.println("\n--- STATISTICS FOR THE LAST " + numEntries + " ENTRIES ---");

        System.out.println("\nTOTAL GOALS COMPLETED: ");
        System.out.println("You have completed " + totalGoalsCompleted + " goals");

        System.out.println("\nAVERAGE NUMBER OF GOALS COMPLETED: ");
        if (avgGoalsCompleted != -1) {
            System.out.println("* Note: does not include goals in progress");
            System.out.println("You completed an average of " + avgGoalsCompleted + " goals per check-in");
        } else {
            System.out.println("All goals are currently in progress");
        }

        System.out.println("\nAVERAGE WEIGHT DIFFERENCE: ");
        double roundedWeight = Math.round(avgWeightDifference * 100.0) / 100.0;
        System.out.println("Your weight estimate was off by an average of " + roundedWeight + "lbs");
    }

    // EFFECTS: Returns checkInLog
    public CheckInLog getCheckInLog() {
        return checkInLog;
    }

    // MODIFIES: this
    // EFFECTS: Sets checkInLog to cil
    public void setCheckInLog(CheckInLog cil) {
        this.checkInLog = cil;
    }
}
