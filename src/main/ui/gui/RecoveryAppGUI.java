package ui.gui;

import model.Event;
import model.EventLog;
import ui.RecoveryApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

// Displays the graphical user interface and allows user input for the main menu of the app
public class RecoveryAppGUI extends RecoveryApp {
    private final DataHandlerGUI dataHandler;
    private JFrame mainFrame;
    private JPanel photoPanel;
    private JPanel innerMenuPanel;
    private JPanel menuPanel;
    private JPanel buttonPanel;

    private JButton weightLogButton;
    private JButton checkInJournalButton;
    private JButton mealLogButton;
    private JButton exitButton;

    // MODIFIES: this
    // EFFECTS: Starts the main menu GUI
    public RecoveryAppGUI() {
        super(); // create the logs
        EventLog.getInstance().clear();
        this.dataHandler = new DataHandlerGUI();
        dataHandler.displayLoadOptions();
        addLoadActionListeners();
    }

    // MODIFIES: loadButton, doNotLoadButton
    // EFFECTS: adds action listeners to loadButton and doNotLoadButton
    private void addLoadActionListeners() {
        dataHandler.getLoadButton().addActionListener(e -> {
            loadData();
            setupMainFrame();
        });
        dataHandler.getDoNotLoadButton().addActionListener(e -> setupMainFrame());
    }

    // MODIFIES: mainFrame
    // EFFECTS: Displays the main menu GUI
    private void setupMainFrame() {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.getContentPane().setBackground(Color.WHITE);
        setUpPanels();
        setUpComponents();
        addMainFrameActionListeners();
        mainFrame.pack();
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        addWindowListener();
        mainFrame.setVisible(true);
    }

    // MODIFIES: mainFrame
    // EFFECTS: Places two JPanels horizontally on the main frame
    private void setUpPanels() {
        mainFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        addPhotoPanel(gbc);
        addMenuPanel(gbc);
    }

    // MODIFIES: mainFrame
    // EFFECTS: initializes and places the photo panel on the main frame
    private void addPhotoPanel(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        photoPanel = new JPanel();
        loadImage();
        photoPanel.setBackground(Color.WHITE);
        mainFrame.add(photoPanel, gbc);
    }

    // MODIFIES: logoImage, photoPanel
    // EFFECTS: Displays logo image on photoPanel
    private void loadImage() {
        photoPanel.setLayout(new BorderLayout());
        String logoPath = "resources/images/Logo.png";
        ImageIcon logoImage = new ImageIcon(logoPath);
        JLabel picLabel = new JLabel(logoImage);
        JPanel bottomLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        bottomLeftPanel.add(picLabel);
        bottomLeftPanel.setBackground(Color.WHITE);
        photoPanel.add(bottomLeftPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: mainFrame
    // EFFECTS: initializes and places the menu panel on the main frame
    private void addMenuPanel(GridBagConstraints gbc) {
        gbc.gridx = 1;
        gbc.weightx = 1;
        menuPanel = new JPanel();
        menuPanel.setBackground(Color.WHITE);
        mainFrame.add(menuPanel, gbc);
    }

    // MODIFIES: mainFrame
    // EFFECTS: Creates and displays components on main frame
    private void setUpComponents() {
        menuPanel.setLayout(new BorderLayout());
        innerMenuPanel = new JPanel();
        innerMenuPanel.setBackground(Color.WHITE);
        innerMenuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        addInnerMenuPanelComponents(gbc);
        menuPanel.add(innerMenuPanel, BorderLayout.WEST);
    }

    // EFFECTS: constrains and adds a welcome label, title label, and button panel to innerMenuPanel
    private void addInnerMenuPanelComponents(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        addWelcomeLabel(gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(0,0,15,0);
        gbc.anchor = GridBagConstraints.CENTER;
        addTitleLabel(gbc);
        gbc.gridy = 2;
        gbc.insets = new Insets(0,0,0,0);
        gbc.anchor = GridBagConstraints.NORTH;
        addButtonPanel(gbc);
    }

    // MODIFIES: buttonPanel, mainFrame
    // EFFECTS: Creates and adds buttonPanel to mainFrame
    private void addButtonPanel(GridBagConstraints gbc) {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,1,10,10));
        buttonPanel.setBackground(Color.WHITE);
        createButtons();
        addButtons();
        innerMenuPanel.add(buttonPanel, gbc);
    }

    // MODIFIES: mainFrame
    // EFFECTS: adds a welcome label to the mainFrame
    private void addWelcomeLabel(GridBagConstraints gbc) {
        JLabel welcomeLabel = new JLabel("Welcome to");
        welcomeLabel.setFont(sourceSans3Regular.deriveFont(32f));
        welcomeLabel.setForeground(Colors.PRIMARY_COLOUR);
        innerMenuPanel.add(welcomeLabel, gbc);
    }

    private void addTitleLabel(GridBagConstraints gbc) {
        JLabel titleLabel = new JLabel("RecoverED");
        titleLabel.setFont(sourceSans3Bold.deriveFont(66f));
        titleLabel.setForeground(Colors.PRIMARY_COLOUR);
        innerMenuPanel.add(titleLabel, gbc);
    }

    // MODIFIES: buttonPanel
    // EFFECTS: adds a weight log, check-in, meal log, and exit button to the buttonPanel
    private void addButtons() {
        buttonPanel.add(weightLogButton);
        buttonPanel.add(checkInJournalButton);
        buttonPanel.add(mealLogButton);
        buttonPanel.add(exitButton);
    }

    // MODIFIES: weightLogButton, checkInJournalButton, mealLogButton, exitButton
    // EFFECTS: Creates buttons for the weight Log, check-in journal, meal log, and to exit
    private void createButtons() {
        weightLogButton = createFormattedButton("Weight Log");
        checkInJournalButton = createFormattedButton("Check-in Journal");
        mealLogButton = createFormattedButton("Meal Log");
        exitButton = createFormattedButton("Exit");
    }

    // RETURN: A button in the menu page style
    // EFFECTS: Returns a button formatted for the menu page
    private JButton createFormattedButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setFont(sourceSans3Regular.deriveFont(20f));
        button.setForeground(Colors.DARK_COLOUR);
        return button;
    }

    // MODIFIES: weightLogButton, checkInJournalButton, mealLogButton, exitButton
    // EFFECTS: Adds action listeners to weightLogButton and exitButton
    private void addMainFrameActionListeners() {
        addSaveActionListeners();
        weightLogButton.addActionListener(e -> new WeightLogGUI(weightLog));
        checkInJournalButton.addActionListener(e -> displayUnfinishedMessage());
        mealLogButton.addActionListener(e -> displayUnfinishedMessage());
        exitButton.addActionListener(e -> dataHandler.displaySaveOptions());
    }


    // MODIFIES: saveButton, doNotSaveButton, mainFrame
    // EFFECTS: Adds action listeners to saveButton and doNotSaveButton
    private void addSaveActionListeners() {
        dataHandler.getSaveButton().addActionListener(e -> {
            super.saveData(weightLog, mealLog, checkInLog);
            mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
        });
        dataHandler.getDoNotSaveButton().addActionListener(e ->
                mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING)));
    }

    // MODIFIES: mainFrame
    // EFFECTS: Adds a window listener to print the event log when the window closes
    private void addWindowListener() {
        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog(EventLog.getInstance());
            }
        });
    }

    // EFFECTS: Prints all events in el to console
    private void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    // EFFECTS: Displays a JFrame notifying the user that the feature has not been implemented in the UI
    private void displayUnfinishedMessage() {
        JFrame messageFrame = new JFrame();
        JPanel messagePanel = new JPanel(new FlowLayout());
        messagePanel.setBackground(Colors.BACKGROUND_COLOR);
        messageFrame.setSize(500,150);
        messageFrame.setLocationRelativeTo(null);
        JLabel titleLabel = new JLabel("Oops! Feature in Progress.");
        JLabel subtitleLabel = new JLabel("This feature is currently not available in the visual interface.");
        JLabel subtitle2Label = new JLabel("To test it, please use the console version of the application.");
        titleLabel.setForeground(Colors.PRIMARY_COLOUR);
        subtitleLabel.setForeground(Colors.DARK_COLOUR);
        subtitle2Label.setForeground(Colors.DARK_COLOUR);
        titleLabel.setFont(sourceSans3Bold.deriveFont(24f));
        subtitleLabel.setFont(sourceSans3Regular.deriveFont(18f));
        subtitle2Label.setFont(sourceSans3Regular.deriveFont(18f));
        messagePanel.add(titleLabel);
        messagePanel.add(subtitleLabel);
        messagePanel.add(subtitle2Label);
        messageFrame.add(messagePanel);
        messageFrame.setVisible(true);
    }
    
}
