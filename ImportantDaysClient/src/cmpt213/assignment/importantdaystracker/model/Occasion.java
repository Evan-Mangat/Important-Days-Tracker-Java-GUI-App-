package cmpt213.assignment.importantdaystracker.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonTypeName;


/**
 * The Occasion class represents an occasion event, which is a specific type of ImportantDay.
 * It extends the ImportantDay class and adds an additional field for the frequency of the occasion.
 */
@JsonTypeName("occasion")
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
        super(name, date, description); 
        this.frequency = frequency;
    }

    /**
     * Returns the frequency of the occasion in years.
     *
     * @return how often the occasion repeats.
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Returns the type of this ImportantDay.
     *
     * @return The string "occasion".
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
}