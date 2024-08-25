package ui.gui;

import ui.RecoveryApp;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

// Class to display options for saving and loading data
public class DataHandlerGUI extends RecoveryApp {

    private final JButton loadButton;
    private final JButton doNotLoadButton;
    private final JButton saveButton;
    private final JButton doNotSaveButton;

    public DataHandlerGUI() {
        loadButton = new JButton();
        doNotLoadButton = new JButton();
        saveButton = new JButton();
        doNotSaveButton = new JButton();
    }

    // MODIFIES: loadButton, doNotLoadButton
    // EFFECTS: Creates and displays a frame with load options
    public void displayLoadOptions() {
        JFrame loadOptionsFrame = new JFrame();
        loadOptionsFrame.setSize(300, 70);
        loadOptionsFrame.setLocationRelativeTo(null);
        loadOptionsFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Font font = sourceSans3Regular.deriveFont(16f);
        JPanel loadPanel = new JPanel(new FlowLayout());
        loadPanel.setBackground(Colors.BACKGROUND_COLOR);
        JLabel loadPrompt = new JLabel("Load Data?");
        loadPrompt.setFont(font);
        loadButton.setText("Yes");
        loadButton.setFont(font);
        doNotLoadButton.setText("No");
        doNotLoadButton.setFont(font);
        loadPanel.add(loadPrompt);
        loadPanel.add(loadButton);
        loadPanel.add(doNotLoadButton);
        loadOptionsFrame.add(loadPanel);
        loadOptionsFrame.setVisible(true);
    }

    // EFFECTS: Creates a JFrame with the program's save options
    public void displaySaveOptions() {
        JFrame saveFrame = new JFrame();
        saveFrame.setSize(200, 110);
        saveFrame.setLocationRelativeTo(null);
        saveFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel savePanel = createSavePanel();
        saveFrame.add(savePanel);
        saveFrame.setVisible(true);
    }

    // MODIFIES: saveButton, doNotSaveButton
    // EFFECTS: Creates a JPanel with the program's save options
    private JPanel createSavePanel() {
        Font font = sourceSans3Regular.deriveFont(16f);
        JPanel savePanel = new JPanel(new FlowLayout());
        savePanel.setBackground(Colors.BACKGROUND_COLOR);
        saveButton.setText("Save and Exit");
        saveButton.setFont(font);
        doNotSaveButton.setText("Exit Without Saving");
        doNotSaveButton.setFont(font);
        savePanel.add(saveButton);
        savePanel.add(doNotSaveButton);
        return savePanel;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getDoNotLoadButton() {
        return doNotLoadButton;
    }

    public JButton getDoNotSaveButton() {
        return doNotSaveButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
