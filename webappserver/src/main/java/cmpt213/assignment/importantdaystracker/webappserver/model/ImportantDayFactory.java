package cmpt213.assignment.importantdaystracker.webappserver.model;

import java.time.LocalDate;
/**
 * The ImportantDayFactory class is responsible for creating instances of different types of ImportantDay objects.
 * It provides a method to create an instance based on the provided type and relevant information.
 */

public class ImportantDayFactory {
    /**
     * Returns an instance of an ImportantDay subclass based on the specified type.
     * 
     * @param type The type of the important day (e.g., "anniversary", "birthday", "occasion").
     * @param name The name of the important day.
     * @param date The date of the important day.
     * @param description A description of the important day.
     * @param extraInfo Additional information specific to the type (e.g., location for Anniversary, 
     *                  person's name for Birthday, and frequency for Occasion).
     * @return An instance of the corresponding subclass of ImportantDay.
     * @throws IllegalArgumentException If an invalid type is provided.
     */
    public static ImportantDay getInstance(String type, String name, LocalDate date, String description, String extraInfo) {
        switch (type.toLowerCase()) {
            case "anniversary":
                return new Anniversary(name, date, description, extraInfo);
            case "birthday":
                return new Birthday(name, date, description, extraInfo);
            case "occasion":
                return new Occasion(name, date, description, Integer.parseInt(extraInfo));
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }
}