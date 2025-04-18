package cmpt213.assignment.importantdaystracker.view;

import cmpt213.assignment.importantdaystracker.control.ImportantDaysManager;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * A modal Swing dialog for adding a new {@code ImportantDay} of type Anniversary, Birthday, or Occasion.
 * <p>
 * This dialog gathers user input through a form and delegates the creation of the day
 * to the {@link ImportantDaysManager}. The UI updates dynamically based on the selected day type.
 */
public class AddImportantDayDialog extends JDialog {
    private ImportantDaysManager manager;
    private JComboBox<String> typeComboBox;
    private JTextField nameField;
    private DatePicker datePicker;
    private JTextField descriptionField;
    private JTextField extraInfoField;
    private JLabel extraInfoLabel;

    /**
     * Constructs the dialog with the parent frame and a reference to the manager to handle user input.
     *
     * @param parent  the parent frame (usually MainFrame)
     * @param manager the controller that handles adding the new day
     */
    public AddImportantDayDialog(JFrame parent, ImportantDaysManager manager) {
        super(parent, "Add Important Day", true);
        this.manager = manager;
        initializeUI();
    }

    /**
     * Initializes the dialog’s layout, components, and action listeners.
     */
    private void initializeUI() {
        setSize(400, 300);
        setLocationRelativeTo(getParent());

        JPanel panel = new JPanel(new GridLayout(6, 2));

        // Type selection
        panel.add(new JLabel("Type:"));
        typeComboBox = new JComboBox<>(new String[]{"Anniversary", "Birthday", "Occasion"});
        typeComboBox.addActionListener(e -> updateExtraInfoField());
        panel.add(typeComboBox);

        // Name
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        // Date
        panel.add(new JLabel("Date:"));
        datePicker = new DatePicker();
        panel.add(datePicker);

        // Description
        panel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        panel.add(descriptionField);

        // Extra info (dynamic based on type)
        extraInfoLabel = new JLabel();
        panel.add(extraInfoLabel);
        extraInfoField = new JTextField();
        panel.add(extraInfoField);

        // Buttons
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addImportantDay());
        panel.add(addButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);

        add(panel);
        updateExtraInfoField();
    }

    /**
     * Updates the extra field label depending on the selected day type.
     */
    private void updateExtraInfoField() {
        String type = (String) typeComboBox.getSelectedItem();
        switch (type) {
            case "Anniversary":
                extraInfoLabel.setText("Location:");
                break;
            case "Birthday":
                extraInfoLabel.setText("Person's Name:");
                break;
            case "Occasion":
                extraInfoLabel.setText("Frequency:");
                break;
        }
    }

    /**
     * Validates input, sends the new day to the manager, and closes the dialog.
     */
    private void addImportantDay() {
        String type = (String) typeComboBox.getSelectedItem();
        String name = nameField.getText().trim(); // Trim to remove leading/trailing spaces
        LocalDate date = datePicker.getDate();
        String description = descriptionField.getText().trim(); // Trim to remove leading/trailing spaces
        String extraInfo = extraInfoField.getText().trim(); // Trim to remove leading/trailing spaces
    
        // Validate required fields (excluding description)
        if (name.isEmpty() || date == null || extraInfo.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "All fields except description are required!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Add the important day
        manager.addImportantDay(type.toLowerCase(), name, date, description, extraInfo);
        dispose();
    }
}