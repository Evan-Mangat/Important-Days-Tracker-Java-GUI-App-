package cmpt213.assignment.importantdaystracker.webappserver.model;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import java.util.Objects;
import java.time.LocalDate;

/**
 * An abstract base class representing an important day with common fields such as name, date,
 * description, and type. Subclasses include {@link Birthday}, {@link Anniversary}, and {@link Occasion}.
 * <p>
 * This class supports polymorphic JSON serialization and deserialization using Jackson annotations.
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
    private String type; 

    /**
     * No-argument constructor for Jackson deserialization.
     */
    public ImportantDay() {
        // for Jackson deserialization
    }

    /**
     * Constructor to initialize an ImportantDay object with its name, date, description, and type.
     * 
     * @param name The name of the important day.
     * @param date The date of the important day.
     * @param description A description of the important day.
     * @param type The type or category of the important day.
     */
    public ImportantDay(String name, LocalDate date, String description, String type) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.type = type; // Initialize the type
    }

    /**
     * Gets the name/title of the important day.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the date of the important day.
     *
     * @return the date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the description or note for the important day.
     *
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the type of the important day.
     *
     * @return the type string (e.g., "birthday", "anniversary").
     */
    public String getType() {
        return type;
    }
    
    /**
     * Sets the name of the important day.
     *
     * @param name the new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the date of the important day.
     *
     * @param date the new date.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    /**
     * Sets the description of the important day.
     *
     * @param description the new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the type of the important day.
     *
     * @param type the new type.
     */
    public void setType(String type) {
        this.type = type;
    }
    
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

    /**
     * Compares this ImportantDay to another for equality.
     * Considers name, date, description, and type.
     *
     * @param o the object to compare.
     * @return true if the two objects are logically equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportantDay that = (ImportantDay) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(date, that.date) &&
               Objects.equals(description, that.description);
    }

    /**
     * Generates a hash code for this ImportantDay based on all fields.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, date, description);
    }
    
}