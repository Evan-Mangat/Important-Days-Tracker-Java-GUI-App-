package cmpt213.assignment.importantdaystracker.webappserver.control;

import cmpt213.assignment.importantdaystracker.webappserver.model.ImportantDay;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A static manager class responsible for handling the storage and retrieval of important days.
 * <p>
 * This class maintains an in-memory list of {@link ImportantDay} objects and supports
 * operations such as adding, removing, listing, and persisting the data to a JSON file.
 */
public class ImportantDayManager {
    private static final List<ImportantDay> dayList = new ArrayList<>();
    private static final String FILE_PATH = "./list.json";

    // Load data from JSON when the class is first loaded.
    static {
        loadFromJson();
    }

    /**
     * Returns an unmodifiable list of all important days currently stored.
     *
     * @return a list of all important days.
     */
    public static List<ImportantDay> getAllDays() {
        return Collections.unmodifiableList(dayList);
    }

    /**
     * Adds a new important day to the in-memory list.
     *
     * @param day the important day to be added.
     */
    public static void addDay(ImportantDay day) {
        dayList.add(day);
    }

    /**
     * Removes a matching important day from the in-memory list.
     *
     * @param day the important day to be removed.
     */
    public static void removeDay(ImportantDay day) {
        dayList.removeIf(d -> d.equals(day));
    }

    /**
     * Saves the current list of important days to a JSON file.
     * This method is typically called when the client exits the application.
     */
    public static void saveToJson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File(FILE_PATH), dayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of important days from a JSON file into memory.
     * This method is called once during class initialization.
     */
    private static void loadFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try {
                ImportantDay[] loaded = mapper.readValue(file, ImportantDay[].class);
                Collections.addAll(dayList, loaded);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}