package cmpt213.assignment.importantdaystracker.webappserver.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a birthday event, a specific subtype of {@link ImportantDay}.
 * <p>
 * Includes an additional field for the name of the person whose birthday it is.
 */
public class Birthday extends ImportantDay {
    private String personName;

    /**
     * No-argument constructor for JSON deserialization.
     */
    public Birthday() {
        // Default constructor for Jackson
    }

    /**
     * Constructs a Birthday with the specified name, date, description, and person name.
     *
     * @param name        the title of the birthday event.
     * @param date        the date the birthday occurs.
     * @param description a short description or note.
     * @param personName  the name of the person whose birthday is being tracked.
     */
    public Birthday(String name, LocalDate date, String description, String personName) {
        super(name, date, description, "birthday");
        this.personName = personName;
    }

    /**
     * Gets the name of the person whose birthday it is.
     *
     * @return the person's name.
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * Sets the name of the person whose birthday it is.
     *
     * @param personName the updated person's name.
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * Returns the type of this important day.
     *
     * @return the string "birthday".
     */
    @Override
    public String getType() {
        return "birthday";
    }

    /**
     * Returns a string representation of the Birthday,
     * including the calculated age of the person.
     *
     * @return a descriptive string of the birthday.
     */
    @Override
    public String toString() {
        int age = LocalDate.now().getYear() - getDate().getYear();
        return super.toString() + "\nNote: " + personName + " is turning " + age + " year(s) old.";
    }

    /**
     * Compares this Birthday to another object for equality,
     * considering both inherited fields and {@code personName}.
     *
     * @param o the object to compare.
     * @return true if the birthdays are equal in content and type.
     */
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Birthday that = (Birthday) o;
        return Objects.equals(personName, that.personName);
    }

    /**
     * Generates a hash code including both inherited fields and {@code personName}.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), personName);
    }
}
