package cmpt213.assignment.importantdaystracker.view;

import cmpt213.assignment.importantdaystracker.model.ImportantDay;
import cmpt213.assignment.importantdaystracker.model.Anniversary;
import cmpt213.assignment.importantdaystracker.model.Birthday;
import cmpt213.assignment.importantdaystracker.model.Occasion;

import javax.swing.*;
import java.awt.*;

/**
 * A Swing panel that visually displays the details of a single {@link ImportantDay}.
 * <p>
 * Depending on the specific type of ImportantDay (e.g., Birthday, Anniversary, Occasion),
 * the panel dynamically adds type-specific labels to show extra information.
 */
public class ImportantDayPanel extends JPanel {
    /**
     * Constructs a panel that displays all relevant information about a given {@link ImportantDay}.
     *
     * @param day the ImportantDay instance to be displayed.
     */
    public ImportantDayPanel(ImportantDay day) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Display common fields
        add(new JLabel("Type: " + day.getType()));
        add(new JLabel("Name: " + day.getName()));
        add(new JLabel("Date: " + day.getDate()));
        add(new JLabel("Description: " + day.getDescription()));

        // Display type-specific fields
        if (day instanceof Anniversary) {
            Anniversary anniversary = (Anniversary) day;
            add(new JLabel("Location: " + anniversary.getLocation()));
        } else if (day instanceof Birthday) {
            Birthday birthday = (Birthday) day;
            add(new JLabel("Person's Name: " + birthday.getPersonName()));
        } else if (day instanceof Occasion) {
            Occasion occasion = (Occasion) day;
            add(new JLabel("Frequency: " + occasion.getFrequency()));
        }
    }
}