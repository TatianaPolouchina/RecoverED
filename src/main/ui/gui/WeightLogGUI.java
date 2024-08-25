package ui.gui;

import model.WeightEntry;
import model.WeightLog;
import ui.RecoveryApp;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

// Displays the GUI for Weight log
public class WeightLogGUI extends RecoveryApp {
    private final WeightLog weightLog;
    private final JFrame weightLogFrame;
    private JFrame weightSystemFrame;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JPanel healthyRangePanel;
    private JPanel weightSystemPanel;
    private JPanel newEntryPanel;
    private JPanel editHealthyRangePanel;
    private JTextField newEntryTextField;
    private JTextField minWeightTextField;
    private JTextField maxWeightTextField;
    private JTextArea entryTextArea;
    private JScrollPane entryScrollPane;
    private JLabel titleLabel;
    private JLabel entriesTitleLabel;
    private JLabel addEntryUnitLabel;
    private JLabel weightSystemPanelUnitLabel;
    private JLabel healthyRangeTextLabel;
    private JLabel minWeightLabel;
    private JLabel maxWeightLabel;
    private JButton submitWeightSystemButton;
    private JComboBox<String> weightMenu;



    // EFFECTS: Creates the frame for the Weight Log and adds all components
    public WeightLogGUI(WeightLog weightLog) {
        this.weightLog = weightLog;
        weightLogFrame = new JFrame();
        weightLogFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        weightLogFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        weightLogFrame.setBackground(Colors.BACKGROUND_COLOR);
        setUpResources();
        setUpComponents();
        loadData();
        weightLogFrame.setVisible(true);
    }

    // MODIFIES: sourceSans3
    // EFFECTS: Initializes font
    private void setUpResources() {
        try {
            String sourceSans3BoldPath = "resources/fonts/SourceSans3-Bold.ttf";
            sourceSans3Bold = Font.createFont(Font.TRUETYPE_FONT, new File(sourceSans3BoldPath));
            String sourceSans3RegularPath = "resources/fonts/SourceSans3-Regular.ttf";
            sourceSans3Regular = Font.createFont(Font.TRUETYPE_FONT, new File(sourceSans3RegularPath));
        } catch (FontFormatException | IOException e) {
            sourceSans3Bold = UIManager.getFont("Label.font");
            sourceSans3Regular = UIManager.getFont("Label.font");
        }
    }

    // EFFECTS: Initializes the west and east panels, adding them to the contentpanel
    private void setUpComponents() {
        setupWestPanel();
        setupEastPanel();
        setupContentPanel();
    }

    // MODIFIES: westPanel
    // EFFECTS: Initializes westPanel and its contents
    private void setupWestPanel() {
        westPanel = new JPanel();
        westPanel.setBorder(BorderFactory.createEmptyBorder(0,30,10,10));
        westPanel.setBackground(Colors.BACKGROUND_COLOR);
        GridBagLayout layout = new GridBagLayout();
        westPanel.setLayout(layout);
        createWestPanelComponents();
        addWestPanelComponents();
        constrainWestPanelComponents(layout);
    }

    // MODIFIES: entriesTitleLabel
    // EFFECTS: creates the title and scroll pane, adding them to westPanel
    private void createWestPanelComponents() {
        createTitleLabel();
        entriesTitleLabel = createSmallText("Previous Entries");
        createEntryScrollPane();
    }

    // MODIFIES: westPanel
    // EFFECTS: adds westPanel's title, entries title, and entries display
    private void addWestPanelComponents() {
        westPanel.add(titleLabel);
        westPanel.add(entriesTitleLabel);
        westPanel.add(entryScrollPane);
    }

    // EFFECTS: Constrains westPanel's components
    private void constrainWestPanelComponents(GridBagLayout layout) {
        setConstraints(layout, titleLabel, 1, 1, 0, 0, 1.0, 1.0, true);
        setConstraints(layout, entriesTitleLabel, 1, 1, 0, 1, 1.0, 1.0, true);
        setConstraints(layout, entryScrollPane, 1, 10, 0, 2, 1.0, 10.0, true);
    }

    // MODIFIES: entryTextArea, entryScrollPane
    // EFFECTS: Creates a non-editable text area
    private void createEntryScrollPane() {
        entryTextArea = new JTextArea();
        entryTextArea.setEditable(false);
        entryTextArea.setLineWrap(true);
        entryTextArea.setFont(sourceSans3Regular.deriveFont(24f));
        entryTextArea.setForeground(Colors.DARK_COLOUR);
        entryScrollPane = new JScrollPane(entryTextArea);
        Border emptyBorder = BorderFactory.createEmptyBorder(0,0,30,100);
        Border lineBorder = BorderFactory.createLineBorder(Colors.PRIMARY_COLOUR);
        entryScrollPane.setBorder(BorderFactory.createCompoundBorder(emptyBorder,lineBorder));
    }

    // MODIFIES: titleLabel
    // EFFECTS: Initializes the title label for the container panel
    private void createTitleLabel() {
        titleLabel = new JLabel("Weight Log");
        titleLabel.setFont(sourceSans3Bold.deriveFont(54f));
        titleLabel.setForeground(Colors.PRIMARY_COLOUR);
    }

    // MODIFIES: eastPanel
    // EFFECTS: Initializes and populates eastPanel with an image, healthyWeightPanel, weightSystemPanel, and
    // newEntryPanel
    private void setupEastPanel() {
        eastPanel = new JPanel(new GridBagLayout());
        eastPanel.setBackground(Colors.BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHEAST;
        addCircleImage(gbc);
        addHealthyWeightPanel(gbc);
        addWeightSystemPanel(gbc);
        addNewEntryPanel(gbc);
    }

    // MODIFIES: eastPanel
    // EFFECTS: creates and adds an image of circles to eastPanel
    private void addCircleImage(GridBagConstraints gbc) {
        addConstraints(gbc, 1, 0, 1, 1, 1.0, 1.0);
        String circlesImagePath = "resources/images/Circles.png";
        JLabel imageLabel = new JLabel(new ImageIcon(circlesImagePath));
        eastPanel.add(imageLabel, gbc);
    }

    // MODIFIES: eastPanel, addHealthyWeightPanel
    // EFFECTS: creates and adds addHealthyWeightPanel to eastPanel
    private void addHealthyWeightPanel(GridBagConstraints gbc) {
        gbc.anchor = GridBagConstraints.NORTHWEST;
        addConstraints(gbc, 0, 0, 1, 1, 1.0, 10.0);
        setupHealthyRangePanel();
        eastPanel.add(healthyRangePanel, gbc);
    }

    // MODIFIES: eastPanel, weightSystemPanel
    // EFFECTS: creates and adds weightSystemPanel to eastPanel
    private void addWeightSystemPanel(GridBagConstraints gbc) {
        gbc.gridy = 1;
        setupWeightSystemPanel();
        eastPanel.add(weightSystemPanel, gbc);
    }

    // MODIFIES: eastPanel, newEntryPanel
    // EFFECTS: creates and adds newEntryPanel to eastPanel
    private void addNewEntryPanel(GridBagConstraints gbc) {
        setupNewEntryPanel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addConstraints(gbc, 0, 2, 2, 1, 2.0, 1.0);
        eastPanel.add(newEntryPanel, gbc);
    }

    // EFFECTS: updates the gridx, gridy, gridwidth, gridheight, weightx and weighty of the entered GridBagConstraints
    private void addConstraints(GridBagConstraints gbc, int gridX, int gridY, int gridWidth, int gridHeight,
                                double weightX, double weightY) {
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.gridwidth = gridWidth;
        gbc.gridheight = gridHeight;
        gbc.weightx = weightX;
        gbc.weighty = weightY;
    }

    // MODIFIES: newEntryPanel, newEntryTextField, addEntryUnitLabel
    // EFFECTS: initializes and populates newEntryPanel
    private void setupNewEntryPanel() {
        GridBagLayout gbl = new GridBagLayout();
        newEntryPanel = new JPanel(gbl);
        newEntryPanel.setBackground(Colors.BACKGROUND_COLOR);
        JLabel titleLabel = createSmallText("Add Entry");
        setUpNewEntryTextField();
        addEntryUnitLabel = createLargeText(weightLog.getWeightSystem());
        JButton enterButton = createEnterNewEntryButton();
        addAddEntryPanelComponents(gbl, titleLabel, enterButton);
    }

    // MODIFIES: newEntryTextField
    // EFFECTS: Initializes and designs newEntryTextField
    private void setUpNewEntryTextField() {
        newEntryTextField = new JTextField();
        newEntryTextField.setBorder(BorderFactory.createLineBorder(Colors.PRIMARY_COLOUR));
        newEntryTextField.setFont(sourceSans3Regular.deriveFont(32f));
        newEntryTextField.setForeground(Colors.DARK_COLOUR);
    }

    // EFFECTS: Returns a JButton that allows valid entries to be entered
    private JButton createEnterNewEntryButton() {
        JButton enterButton = createFormattedButton("Enter", 24f);
        enterButton.addActionListener(e -> {
            double weight = Double.parseDouble(newEntryTextField.getText());
            if (weight > 0) {
                saveNewEntry(weight);
                newEntryTextField.setText("");
            }
        });
        return enterButton;
    }

    // MODIFIES: newEntryPanel
    // EFFECTS: Adds and constrains a title, textfield, and enter button to newEntryPanel
    private void addAddEntryPanelComponents(GridBagLayout gbl, JLabel titleLabel, JButton enterButton) {
        newEntryPanel.add(titleLabel);
        newEntryPanel.add(enterButton);
        newEntryPanel.add(newEntryTextField);
        newEntryPanel.add(addEntryUnitLabel);
        setConstraints(gbl, titleLabel, 2,1,0,0,2.0,1.0, true);
        setConstraints(gbl, newEntryTextField, 1,1,0,1,1.0,1.0, true);
        setConstraints(gbl, addEntryUnitLabel, 1,1,1,1,1.0,1.0, true);
        GridBagConstraints gbc = new GridBagConstraints(1,2,1,1,1,1,
                GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0);
        gbl.setConstraints(enterButton, gbc);
    }

    // MODIFIES: healthyRangePanel
    // EFFECTS: Initializes and populates healthyRangePanel
    private void setupHealthyRangePanel() {
        GridBagLayout gbl = new GridBagLayout();
        healthyRangePanel = new JPanel(gbl);
        healthyRangePanel.setBackground(Colors.BACKGROUND_COLOR);
        populateHealthyRangePanel(gbl);
        healthyRangePanel.setBorder(BorderFactory.createEmptyBorder(150,0,0,0));
    }

    // MODIFIES: healthyRangePanel, healthyRangeTextLabel
    // EFFECTS: Creates and adds components to healthyRangePanel
    private void populateHealthyRangePanel(GridBagLayout gbl) {
        JButton editButton = createFormattedButton("Edit", 24f);
        editButton.addActionListener(e -> displayEditHealthyRangeFrame());
        JLabel titleLabel = createSmallText("Healthy Range");
        healthyRangeTextLabel = createLargeText("");
        healthyRangeTextLabel.setHorizontalAlignment(JLabel.LEFT);

        updateHealthyRangeLabel();
        healthyRangePanel.add(editButton);
        healthyRangePanel.add(titleLabel);
        healthyRangePanel.add(healthyRangeTextLabel);
        setConstraints(gbl, editButton, 1, 1, 1, 0, 1.0, 1.0, false);
        setConstraints(gbl, titleLabel, 1, 1, 0, 0, 1.0, 1.0, true);
        setConstraints(gbl, healthyRangeTextLabel, 2, 1, 0, 1, 2.0, 1.0, true);
    }

    // MODIFIES: healthyRangeTextLabel
    // EFFECTS: Updates healthyRangeTextLabel to display weightLog's healthy range
    private void updateHealthyRangeLabel() {
        if (weightLog.rangeExists()) {
            double minWeight = roundToTenth(weightLog.getMinHealthyWeight());
            double maxWeight = roundToTenth(weightLog.getMaxHealthyWeight());
            healthyRangeTextLabel.setText(minWeight + weightLog.getWeightSystem()
                    + " - " + maxWeight + weightLog.getWeightSystem());
        } else {
            healthyRangeTextLabel.setText("Not Set");
        }
    }

    // MODIFIES: weightSystemPanel
    // EFFECTS: Initalizes and populates weightSystemPanel
    private void setupWeightSystemPanel() {
        GridBagLayout gbl = new GridBagLayout();
        weightSystemPanel = new JPanel(gbl);
        weightSystemPanel.setBackground(Colors.BACKGROUND_COLOR);
        populateWeightSystemPanel(gbl);
    }

    // MODIFIES: weightSystemPanel, weightSystemPanelUnitLabel
    // EFFECTS: IPopulates weightSystemPanel with a title, unit label, and edit button
    private void populateWeightSystemPanel(GridBagLayout gbl) {
        JButton editButton = createFormattedButton("Edit", 24f);
        JLabel titleLabel = createSmallText("Weight System");
        weightSystemPanelUnitLabel = createLargeText(weightLog.getWeightSystem());
        editButton.addActionListener(e -> displayWeightSystemFrame());
        weightSystemPanel.add(editButton);
        weightSystemPanel.add(titleLabel);
        weightSystemPanel.add(weightSystemPanelUnitLabel);
        setConstraints(gbl, editButton, 1, 1, 5, 0, 1.0, 1.0, true);
        setConstraints(gbl, titleLabel, 4, 1, 0, 0, 4.0, 1.0, true);
        setConstraints(gbl, weightSystemPanelUnitLabel, 5, 1, 0, 1, 5.0, 1.0, true);
    }

    // MODIFIES: weightSystemFrame
    // EFFECTS: Creates and displays JFrame to change the weight system
    private void displayWeightSystemFrame() {
        weightSystemFrame = new JFrame();
        weightSystemFrame.setSize(320,120);
        addWeightSystemFrameContents();
        weightSystemFrame.setVisible(true);
    }

    // MODIFIES: submitWeightSystemButton, weightSystemFrame
    // EFFECTS: adds JLabels, a JComboBox, and submit JButton to allow users to submit a new weight system
    private void addWeightSystemFrameContents() {
        JPanel weightSystemPanel = new JPanel();
        weightSystemPanel.setBackground(Colors.BACKGROUND_COLOR);
        weightSystemPanel.setLayout(new FlowLayout());
        JLabel systemLabel = new JLabel("Current Weight System: ");
        systemLabel.setFont(sourceSans3Regular.deriveFont(18f));
        systemLabel.setForeground(Colors.DARK_COLOUR);
        setUpWeightMenu();
        submitWeightSystemButton = createFormattedButton("Save changes", 20f);
        submitWeightSystemButton.addActionListener(e -> {
            updateWeightSystem();
            weightSystemFrame.dispatchEvent(new WindowEvent(weightLogFrame, WindowEvent.WINDOW_CLOSING));
        });
        weightSystemPanel.add(systemLabel);
        weightSystemPanel.add(weightMenu);
        weightSystemPanel.add(submitWeightSystemButton);
        weightSystemFrame.add(weightSystemPanel);
    }

    // EFFECTS: displays an unhealthy weight warning message
    private void displayWarning() {
        JFrame warningFrame = new JFrame();
        warningFrame.setSize(400,140);
        warningFrame.setLocationRelativeTo(null);
        JPanel warningPanel = new JPanel(new FlowLayout());
        warningPanel.setBackground(Colors.BACKGROUND_COLOR);
        JLabel titleLabel = new JLabel("WARNING");
        JLabel warningText = new JLabel("This weight is outside of your healthy range");
        JButton closeButton = new JButton("Ok");
        closeButton.addActionListener(e ->
                warningFrame.dispatchEvent(new WindowEvent(warningFrame, WindowEvent.WINDOW_CLOSING)));
        titleLabel.setFont(sourceSans3Bold.deriveFont(20f));
        warningText.setFont(sourceSans3Regular.deriveFont(18f));
        titleLabel.setForeground(Colors.PRIMARY_COLOUR);
        warningText.setForeground(Colors.DARK_COLOUR);
        warningPanel.add(titleLabel);
        warningPanel.add(warningText);
        warningPanel.add(closeButton);
        warningFrame.add(warningPanel);
        warningFrame.setVisible(true);
    }

    // MODIFIES: editHealthyRangeFrame, editHealthyRangePanel
    // EFFECTS: Initializes, populates, and displays editHealthyRangeFrame
    private void displayEditHealthyRangeFrame() {
        JFrame editHealthyRangeFrame = new JFrame();
        editHealthyRangePanel = new JPanel();
        editHealthyRangePanel.setBackground(Colors.BACKGROUND_COLOR);
        editHealthyRangePanel.setLayout(new FlowLayout());
        createEditHealthyRangeFrameComponents(editHealthyRangeFrame);
        addHealthyRangeFrameComponents();
        editHealthyRangeFrame.setSize(320,160);
        editHealthyRangeFrame.add(editHealthyRangePanel);
        editHealthyRangeFrame.setVisible(true);
    }

    // MODIFIES: minWeightLabel, minWeightTextField, maxWeightLabel, maxWeightTextField, submitWeightSystemButton
    // EFFECTS: initializes the components contained in editHealthyRangeFrame
    private void createEditHealthyRangeFrameComponents(JFrame frame) {
        minWeightLabel = new JLabel("Minimum healthy weight: ");
        minWeightLabel.setFont(sourceSans3Regular.deriveFont(18f));
        minWeightTextField = formattedTextField();
        maxWeightLabel = new JLabel("Maximum healthy weight: ");
        maxWeightTextField = formattedTextField();
        maxWeightLabel.setFont(sourceSans3Regular.deriveFont(18f));
        maxWeightLabel.setForeground(Colors.DARK_COLOUR);
        submitWeightSystemButton = createFormattedButton("Save changes", 20f);
        submitWeightSystemButton.addActionListener(e -> {
            if (submitHealthyRangeChanges()) {
                frame.dispatchEvent(new WindowEvent(weightLogFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    // MODIFIES: weightLog
    // EFFECTS: saves the entered min and max weight to the weightLog if they are valid (min < max, min >0),
    //          returns true only if successful.
    private boolean submitHealthyRangeChanges() {
        boolean result = false;
        try {
            double minEnteredWeight = Double.parseDouble(minWeightTextField.getText());
            double maxEnteredWeight = Double.parseDouble(maxWeightTextField.getText());
            if (minEnteredWeight < maxEnteredWeight && minEnteredWeight > 0) {
                weightLog.setHealthyRange(minEnteredWeight, maxEnteredWeight);
                result = true;
            }
            updateHealthyRangeLabel();
        } catch (Exception e) {
            // do nothing
        }
        return result;
    }

    // MODIFIES: editHealthyRangePanel
    // EFFECTS: adds all components to editHealthyRangePanel
    private void addHealthyRangeFrameComponents() {
        editHealthyRangePanel.add(minWeightLabel);
        editHealthyRangePanel.add(minWeightTextField);
        editHealthyRangePanel.add(getWeightSystemLabel());
        editHealthyRangePanel.add(maxWeightLabel);
        editHealthyRangePanel.add(maxWeightTextField);
        editHealthyRangePanel.add(getWeightSystemLabel());
        editHealthyRangePanel.add(submitWeightSystemButton);
    }

    // EFFECTS: Returns a formatted JLabel displaying the current weight system
    private JLabel getWeightSystemLabel() {
        JLabel weightSystemLabel = new JLabel(weightLog.getWeightSystem());
        weightSystemLabel.setFont(sourceSans3Regular.deriveFont(18f));
        weightSystemLabel.setForeground(Colors.DARK_COLOUR);
        return weightSystemLabel;
    }

    // EFFECTS: Returns a formatted JTextField
    private static JTextField formattedTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(60,30));
        textField.setForeground(Colors.PRIMARY_COLOUR);
        textField.setForeground(Colors.DARK_COLOUR);
        return textField;
    }

    // MODIFIES: weightMenu
    // EFFECTS: intializes and returns a formatted JComboBox with all possible weight Systems
    private void setUpWeightMenu() {
        weightMenu = new JComboBox<>();
        weightMenu.setFont(sourceSans3Regular.deriveFont(18f));
        weightMenu.setForeground(Colors.DARK_COLOUR);
        weightMenu.addItem("lbs");
        weightMenu.addItem("kg");
        weightMenu.addItem("stone");
        weightMenu.setSelectedItem(weightLog.getWeightSystem());
    }

    // MODIFIES: entryTextArea, weightLog
    // EFFECTS: Updates the output of entryTextArea to the correct weight system
    private void updateWeightSystem() {
        String system = weightMenu.getItemAt(weightMenu.getSelectedIndex());
        weightLog.setWeightSystem(system);
        addEntryUnitLabel.setText(weightLog.getWeightSystem());
        weightSystemPanelUnitLabel.setText(weightLog.getWeightSystem());
        updateHealthyRangeLabel();
        loadData();
    }

    // EFFECTS: Returns a formatted button displaying buttonText
    private JButton createFormattedButton(String buttonText, float textSize) {
        JButton button = new JButton();
        button.setText(buttonText);
        button.setForeground(Colors.DARK_COLOUR);
        button.setFont(sourceSans3Bold.deriveFont(textSize));
        button.setHorizontalTextPosition(JLabel.CENTER);
        button.setVerticalTextPosition(JLabel.CENTER);
        button.setForeground(Colors.DARK_COLOUR);
        return button;
    }

    // EFFECTS: Returns a formatted label with small text
    private JLabel createSmallText(String text) {
        JLabel label = new JLabel(text);
        label.setFont(sourceSans3Bold.deriveFont(30f));
        label.setForeground(Colors.PRIMARY_COLOUR);
        return label;
    }

    // EFFECTS: Returns a formatted label with large text
    private JLabel createLargeText(String text) {
        JLabel label = new JLabel(text);
        label.setFont(sourceSans3Bold.deriveFont(56f));
        label.setForeground(Colors.DARK_COLOUR);
        return label;
    }

    // MODIFIES: contentPanel, weightLogFrame
    // EFFECTS: Initializes and populates the contentPanel with the east and west panels and adds it to the frame
    private void setupContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Colors.BACKGROUND_COLOR);
        contentPanel.setLayout(new GridLayout(1,2));
        contentPanel.add(westPanel);
        contentPanel.add(eastPanel);
        weightLogFrame.add(contentPanel);
    }

    // MODIFIES: entryTextArea
    // EFFECTS: updates weight log data on entryTextArea
    protected void loadData() {
        entryTextArea.setText(weightEntriesToString());
    }

    // EFFECTS: Prints all saved weight entries to console
    private String weightEntriesToString() {
        StringBuilder entries = new StringBuilder();
        int count = 1;
        String system = weightLog.getWeightSystem();
        for (WeightEntry entry: weightLog.getWeightEntries()) {
            double roundedWeight = roundToTenth(entry.getWeight(system));
            entries.append("Weight ").append(count).append(": ").append(roundedWeight).append(system).append("\n");
            count++;
        }
        return entries.toString();
    }

    // EFFECTS: Returns num rounded to the nearest tenth
    private double roundToTenth(double num) {
        return Math.round(num * 10.0) / 10.0;
    }

    // MODIFIES: weightLog
    // EFFECTS: Creates and saves a new entry with the data from newEntryPanel
    private void saveNewEntry(double weight) {
        try {
            WeightEntry newEntry = new WeightEntry(weight, weightLog.getWeightSystem());
            weightLog.addEntry(newEntry);
            loadData();
            if (weightLog.rangeExists() && !weightLog.isHealthy(newEntry)) {
                displayWarning();
            }
        } catch (Exception e) {
            // do not save
        }
    }

    // Received from https://gist.github.com/3688373
    // REQUIRES: w, h, x, y are > 0
    // MODIFIES: gbl
    // EFFECTS: Generates constraints for Swing components
    /**
     * @param gbl     a gridbaglayout that used to embed Swing component
     * @param comp    a Swing component intended to be embedded in gbl
     * @param w       desired component width
     * @param h       desired component height
     * @param x       desired location in x-axis
     * @param y       desired location in y-axis
     * @param weightx desired weight in terms of x-axis
     * @param weighty desired weight in terms of y-axis
     */
    private void setConstraints(GridBagLayout gbl, JComponent comp, int w, int h, int x, int y,
                               double weightx, double weighty, boolean fill) {
        GridBagConstraints constraints = new GridBagConstraints();
        if (fill) {
            constraints.fill = GridBagConstraints.BOTH;
        }
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        gbl.setConstraints(comp, constraints);
    }
}
