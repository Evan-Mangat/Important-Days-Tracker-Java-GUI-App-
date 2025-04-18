package cmpt213.assignment.importantdaystracker.webappserver.model;

import java.time.LocalDate;

/**
 * The Anniversary class represents an anniversary, a specific subtype of {@link ImportantDay},
 * It extends the ImportantDay class and adds an additional field for the location of the anniversary.
 */
public class Anniversary extends ImportantDay {
    private String location;

    /**
     * No-argument constructor required for JSON deserialization.
     */
    public Anniversary() {
        // Default constructor for Jackson
    }

    /**
     * Constructor to initialize an Anniversary object with its name, date, description, and location.
     * 
     * @param name The name of the anniversary.
     * @param date The date of the anniversary.
     * @param description A description of the anniversary event.
     * @param location The location where the anniversary event takes place.
     */
    public Anniversary(String name, LocalDate date, String description, String location) {
        super(name, date, description, "anniversary"); // Pass the type
        this.location = location;
    }

    /**
     * Gets the location of the anniversary.
     *
     * @return the location where the anniversary is celebrated.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the anniversary.
     *
     * @param location the location to update.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the type name for this important day.
     *
     * @return the string "anniversary".
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
    
    /**
     * Checks for deep equality between this and another object.
     *
     * @param o the object to compare.
     * @return true if the objects are equal in content and type.
     */

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anniversary that = (Anniversary) o;
        return location.equals(that.location);
    }
    
    /**
     * Returns a hash code consistent with {@link #equals(Object)}.
     *
     * @return a hash based on the anniversary's state.
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), location);
    }
    
}