package cmpt213.assignment.importantdaystracker.webappserver.model;

import java.time.LocalDate;

/**
 * Represents an occasion event, which is a specific subtype of {@link ImportantDay}.
 * <p>
 * An Occasion includes an additional field representing how often the event recurs (in years).
 */
public class Occasion extends ImportantDay {
    private int frequency;

    /**
     * No-argument constructor required for JSON deserialization.
     */
    public Occasion() {
        // Default constructor for Jackson
    }

    /**
     * Constructor to initialize an Occasion object with its name, date, description, and frequency.
     * 
     * @param name The name of the occasion.
     * @param date The date of the occasion.
     * @param description A description of the occasion.
     * @param frequency The frequency of the occasion (how many years between each occurrence).
     */
    public Occasion(String name, LocalDate date, String description, int frequency) {
        super(name, date, description, "occasion"); // Pass the type
        this.frequency = frequency;
    }

    /**
     * Gets the frequency of the occasion in years.
     *
     * @return how many years between recurrences.
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Sets the frequency of the occasion.
     *
     * @param frequency the new recurrence interval in years.
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
    /**
     * Returns the type of this important day.
     *
     * @return the string "occasion".
     */
    @Override
    public String getType() {
        return "occasion";
    }
    
    /**
     * Returns a string representation of the Occasion object.
     * 
     * @return A string representation of the occasion, including the type, name, date, description,
     *         and the frequency of the occasion.
     */
    @Override
    public String toString() {
        return super.toString() + "\nFrequency: every " + frequency + " year(s)";
    }
    
    /**
     * Compares this Occasion to another object for equality,
     * considering both inherited fields and {@code frequency}.
     *
     * @param o the object to compare.
     * @return true if the occasions are equal in content and type.
     */
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Occasion that = (Occasion) o;
        return frequency == that.frequency;
    }

    /**
     * Generates a hash code including both inherited fields and {@code frequency}.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), frequency);
    }
    
}