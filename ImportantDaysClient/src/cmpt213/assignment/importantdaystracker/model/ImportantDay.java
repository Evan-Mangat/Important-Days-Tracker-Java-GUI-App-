package cmpt213.assignment.importantdaystracker.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * An abstract base class representing an important day with common fields:
 * name, date, and description.
 * <p>
 * This class is extended by specific types of important days like {@link Birthday},
 * {@link Anniversary}, and {@link Occasion}, and is configured for use with JSON polymorphic deserialization.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @Type(value = Anniversary.class, name = "anniversary"),
    @Type(value = Birthday.class, name = "birthday"),
    @Type(value = Occasion.class, name = "occasion")
})

public abstract class ImportantDay implements Comparable<ImportantDay> {
    private String name;
    private LocalDate date;
    private String description;
    // removed to fix bug - private String type; 


    /**
     * No-argument constructor for JSON deserialization.
     */
     public ImportantDay() {
        // For Jackson
    }

    /**
     * Constructor to initialize an ImportantDay object with its name, date, description, and type.
     * 
     * @param name The name of the important day.
     * @param date The date of the important day.
     * @param description A description of the important day.
     * type is excluded on the client side 
     */
    
    public ImportantDay(String name, LocalDate date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
        // this.type = type; // Initialize the type
    }

    /**
     * Gets the name of the important day.
     *
     * @return the event name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the date of the important day.
     *
     * @return the event date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the description of the important day.
     *
     * @return the event description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the specific type name of this important day (e.g., "birthday", "anniversary").
     *
     * @return a string identifier for the subclass.
     */
    @JsonIgnore
    public abstract String getType();
    
    /**
     * Compares two ImportantDay objects based on their date.
     * 
     * @param other The other ImportantDay object to compare against.
     * @return A negative integer, zero, or a positive integer as this date is earlier than, equal to, or later than the other date.
     */
    @Override
    public int compareTo(ImportantDay other) {
        return this.date.compareTo(other.date);
    }
    /**
     * Returns a string representation of the ImportantDay object.
     * 
     * @return A string representation of the important day including its type, name, date, and description.
     */
    @Override
    public String toString() {
        return "Important Day Type: " + this.getClass().getSimpleName() + "\n" +
               name + " on " + date.getYear() + " " + date.getMonth() + " " + date.getDayOfMonth() + "\n" +
               description;
    }
}