package ui.console;

import ui.RecoveryApp;

import java.util.Scanner;

// Represents a menu and contains the scanner, meal log, check-in log, and weight log
public abstract class RecoveryAppScanner extends RecoveryApp {
    protected Scanner ui;

    // MODIFIES: this
    // EFFECTS: Creates a new menu with a mealLog, checkInLog, weightLog, and Scanner
    public RecoveryAppScanner() {
        super();
        ui = new Scanner(System.in);
    }

    // EFFECTS: Displays menu and processes user input
    // Portions of code received from TellerAppRobust
    public void start() {
        boolean quit;
        do {
            displayMenu();
            quit = processCommand(ui.next());

        } while (!quit);
    }

    // MODIFIES: this
    // EFFECTS: closes scanner
    public void end() {
        ui.close();
    }

    // EFFECTS: Prints menu options to console
    public abstract void displayMenu();

    // MODIFIES: this
    // EFFECTS: Processes command
    public abstract boolean processCommand(String command);

}
