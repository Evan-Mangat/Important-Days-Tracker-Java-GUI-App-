package cmpt213.assignment.importantdaystracker.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Represents a birthday event, a specific subtype of {@link ImportantDay}.
 * <p>
 * A Birthday includes an additional field for the person's name whose birthday is being tracked.
 */
@JsonTypeName("birthday")
public class Birthday extends ImportantDay {
    private String personName;

    public Birthday() {
        // Default constructor for Jackson
    }

    /**
     * Constructor to initialize a Birthday object with its name, date, description, and the person's name.
     * 
     * @param name The name of the birthday event.
     * @param date The date of the birthday.
     * @param description A description of the birthday event.
     * @param personName The name of the person whose birthday it is.
     */
    public Birthday(String name, LocalDate date, String description, String personName) {
        super(name, date, description); 
        this.personName = personName;
    }

    /**
     * Returns the name of the person whose birthday is being tracked.
     *
     * @return The person's name.
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * Returns the type of this ImportantDay.
     *
     * @return The string "birthday".
     */
    @Override
    public String getType() {
        return "birthday";
    }
    
    /**
     * Returns a string representation of the Birthday object.
     * 
     * @return A string representation of the birthday, including the type, name, date, description,
     *         and the person's age.
     */
    @Override
    public String toString() {
        int age = LocalDate.now().getYear() - getDate().getYear();
        return super.toString() + "\nNote: " + personName + " is turning " + age + " year(s) old.";
    }
}