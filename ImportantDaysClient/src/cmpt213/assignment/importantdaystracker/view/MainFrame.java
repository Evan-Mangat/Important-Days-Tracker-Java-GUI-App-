package cmpt213.assignment.importantdaystracker.view;

import cmpt213.assignment.importantdaystracker.control.ImportantDaysManager;
import cmpt213.assignment.importantdaystracker.model.ImportantDay;
import cmpt213.assignment.importantdaystracker.model.Occasion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainFrame extends JFrame {
    private ImportantDaysManager manager;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private List<JCheckBox> checkBoxes;
    private JButton removeButton;
    private JComboBox<String> typeComboBox;

    /**
     * Constructs the main window and loads the initial list of important days.
     *
     * @param manager the controller used to manage server communication and data.
     */

    public MainFrame(ImportantDaysManager manager) {
        this.manager = manager;
        this.checkBoxes = new ArrayList<>();
        initializeUI();
        refreshList();
    }

    /**
     * Sets up the window layout, buttons, and event listeners.
     * Also handles the shutdown behavior when the window is closed.
     */
    private void initializeUI() {
        setTitle("Important Days Tracker");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        // Handle graceful shutdown
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                manager.sendExitSignal();
                dispose();
                System.exit(0);
            }
        });

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel setup
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        JButton showAllButton = new JButton("Show All");
        JButton showPassedButton = new JButton("Show Passed This Year");
        JButton showUpcomingButton = new JButton("Show Upcoming This Year");
        JButton filterByTypeButton = new JButton("Show By Type");

        typeComboBox = new JComboBox<>(new String[]{"All", "Anniversary", "Birthday", "Occasion"});

        // Button listeners
        addButton.addActionListener(e -> {
            AddImportantDayDialog dialog = new AddImportantDayDialog(this, manager);
            dialog.setVisible(true);
            refreshList();
        });

        removeButton.addActionListener(e -> {
            List<ImportantDay> currentList = manager.getDayList();
            List<ImportantDay> toRemove = new ArrayList<>();
            for (int i = 0; i < checkBoxes.size(); i++) {
                if (checkBoxes.get(i).isSelected()) {
                    toRemove.add(currentList.get(i));
                }
            }
            for (ImportantDay day : toRemove) {
                manager.removeDay(day);
            }
            refreshList();
        });

        showAllButton.addActionListener(e -> refreshList());

        showPassedButton.addActionListener(e -> {
            manager.loadPassedDaysThisYear();
            displayList(manager.getDayList());
        });

        showUpcomingButton.addActionListener(e -> {
            manager.loadUpcomingDaysThisYear();
            displayList(manager.getDayList());
        });

        filterByTypeButton.addActionListener(e -> {
            String selected = (String) typeComboBox.getSelectedItem();
            if ("All".equals(selected)) {
                refreshList();
            } else {
                manager.loadFromServer();
                String type = selected.toLowerCase();
                int currentYear = LocalDate.now().getYear();

                List<ImportantDay> filtered = manager.getDayList().stream()
                    .filter(day -> day.getType().equalsIgnoreCase(type))
                    .filter(day -> {
                        if (day instanceof Occasion) {
                            int baseYear = day.getDate().getYear();
                            int frequency = ((Occasion) day).getFrequency();
                            return frequency > 0 && currentYear >= baseYear &&
                                (currentYear - baseYear) % frequency == 0;
                        }
                        return true; // birthdays/anniversaries always included
                    })
                    .collect(Collectors.toList());

                displayList(filtered);
            }
        });


        // Add components to button panel
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(showAllButton);
        buttonPanel.add(showPassedButton);
        buttonPanel.add(showUpcomingButton);
        buttonPanel.add(typeComboBox);
        buttonPanel.add(filterByTypeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Reloads the full list of important days from the server and displays them.
     */
    private void refreshList() {
        manager.loadFromServer();
        displayList(manager.getDayList());
    }

    /**
     * Displays a given list of important days in the main scrollable panel,
     * including a checkbox for selecting each one for potential removal.
     *
     * @param days the list of important days to display.
     */
    private void displayList(List<ImportantDay> days) {
        mainPanel.removeAll();
        checkBoxes.clear();
        for (ImportantDay day : days) {
            JCheckBox box = new JCheckBox();
            checkBoxes.add(box);
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.add(box, BorderLayout.WEST);
            itemPanel.add(new ImportantDayPanel(day), BorderLayout.CENTER);
            mainPanel.add(itemPanel);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}