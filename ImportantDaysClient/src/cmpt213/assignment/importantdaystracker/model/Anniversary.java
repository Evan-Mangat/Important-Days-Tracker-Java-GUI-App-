package cmpt213.assignment.importantdaystracker.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonTypeName;


/**
 * The Anniversary class represents an anniversary, which is a specific type of ImportantDay.
 * It extends the ImportantDay class and adds an additional field for the location of the anniversary.
 */

@JsonTypeName("anniversary")
public class Anniversary extends ImportantDay {
    private String location;

    /**
     * No-argument constructor required for JSON deserialization.
     * Initializes the anniversary with default values.
     */
     public Anniversary() {
        super();  // calls the no-arg constructor of ImportantDay
        this.location = "";
    }

    /**
     * Constructs an Anniversary with specified name, date, description, and location.
     *
     * @param name        The name/title of the anniversary.
     * @param date        The date the anniversary occurs on.
     * @param description A description or note about the anniversary.
     * @param location    The location where the anniversary is celebrated.
     */

    public Anniversary(String name, LocalDate date, String description, String location) {
        super(name, date, description); 
        this.location = location;
    }

    /**
     * Returns the location where the anniversary is celebrated.
     *
     * @return The anniversary location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the type of this ImportantDay.
     *
     * @return The string "anniversary".
     */
    @Override
    public String getType() {
        return "anniversary";
    }
    
    /**
     * Returns a string representation of the Anniversary object.
     * 
     * @return A string representation of the anniversary, including the type, name, date, description, and location.
     */
    @Override
    public String toString() {
        return super.toString() + "\nLocated at: " + location;
    }
}