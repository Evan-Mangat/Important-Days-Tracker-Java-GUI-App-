package cmpt213.assignment.importantdaystracker.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * This class is a custom TypeAdapter for Gson to handle serialization and deserialization
 * of LocalDate objects. It converts LocalDate objects to JSON strings and vice versa.
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    /**
     * Writes a LocalDate object to the JSON output.
     * This method converts the LocalDate to a string in ISO format.
     * 
     * @param out the JsonWriter used to write to the JSON output
     * @param value the LocalDate object to be serialized
     * @throws IOException if an I/O error occurs while writing
     */
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        out.value(value.toString());
    }

    /**
     * Reads a LocalDate object from the JSON input.
     * This method parses a string from the JSON input and converts it to a LocalDate object.
     * 
     * @param in the JsonReader used to read the JSON input
     * @return the LocalDate object read from the JSON input
     * @throws IOException if an I/O error occurs while reading
     */
    @Override
    public LocalDate read(JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString());
    }
}
