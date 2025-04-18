package cmpt213.assignment.importantdaystracker.control;

import cmpt213.assignment.importantdaystracker.model.ImportantDay;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * A utility class that handles HTTP communication between the client and the server
 * for performing operations related to important days.
 * <p>
 * This class supports sending and receiving JSON data to/from the server
 * using HTTP requests, including adding, removing, listing, and exiting.
 */

public class HttpClientHelper {
    private static final String BASE_URL = "http://localhost:8080";
    private static final ObjectMapper mapper = new ObjectMapper();

    // Static block to register subtypes and configure date handling for JSON mapping
    static {
        mapper.registerSubtypes(
            cmpt213.assignment.importantdaystracker.model.Anniversary.class,
            cmpt213.assignment.importantdaystracker.model.Birthday.class,
            cmpt213.assignment.importantdaystracker.model.Occasion.class
        );
        mapper.registerModule(new JavaTimeModule());
    }

    /**
     * Sends a GET request to retrieve all important days from the server.
     *
     * @return a list of all important days.
     * @throws IOException if a network or server error occurs.
     */
    public static List<ImportantDay> getAllDays() throws IOException {
        URL url = URI.create(BASE_URL + "/listAll").toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (InputStream inputStream = conn.getInputStream()) {
            return mapper.readValue(inputStream, new TypeReference<List<ImportantDay>>() {});
        }
    }

    /**
     * Sends a POST request to add a new important day to the server.
     *
     * @param day the ImportantDay object to add.
     * @return the updated list of important days.
     * @throws IOException if a network or server error occurs.
     */

    public static List<ImportantDay> addDay(ImportantDay day) throws IOException {
        return sendPostRequest("/addDay", day);
    }

    /**
     * Sends a POST request to remove an existing important day from the server.
     *
     * @param day the ImportantDay object to remove.
     * @return the updated list of important days.
     * @throws IOException if a network or server error occurs.
     */

    public static List<ImportantDay> removeDay(ImportantDay day) throws IOException {
        return sendPostRequest("/removeDay", day);
    }

    /**
     * Sends a POST request with JSON content to the given endpoint.
     *
     * @param endpoint the relative path of the REST endpoint.
     * @param day      the ImportantDay object to send.
     * @return the server's response as a list of ImportantDay objects.
     * @throws IOException if a network or server error occurs.
     */

    private static List<ImportantDay> sendPostRequest(String endpoint, ImportantDay day) throws IOException {
        URL url = URI.create(BASE_URL + endpoint).toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
    
        
        String json = mapper.writeValueAsString(day);
        // System.out.println("DEBUG - Sending JSON to server: " + json);  // ← Add this for debug
    
        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes(StandardCharsets.UTF_8));
        }
    
        try (InputStream inputStream = conn.getInputStream()) {
            return mapper.readValue(inputStream, new TypeReference<List<ImportantDay>>() {});
        }
    }
    
    /**
     * Sends a GET request to signal the server to save the current list and exit.
     *
     * @throws IOException if a network or server error occurs.
     */

    public static void sendExit() throws IOException {
        URL url = URI.create(BASE_URL + "/exit").toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.getInputStream().close();
    }

    /**
     * Retrieves all important days that have already occurred this year.
     *
     * @return a list of past important days.
     * @throws IOException if a network or server error occurs.
     */

    public static List<ImportantDay> getPassedDaysThisYear() throws IOException {
        URL url = URI.create(BASE_URL + "/listPassedDaysThisYear").toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (InputStream inputStream = conn.getInputStream()) {
            return mapper.readValue(inputStream, new com.fasterxml.jackson.core.type.TypeReference<List<ImportantDay>>() {});
        }
    }

    /**
     * Retrieves all important days that are upcoming or today.
     *
     * @return a list of upcoming important days.
     * @throws IOException if a network or server error occurs.
     */
    
    public static List<ImportantDay> getUpcomingDaysThisYear() throws IOException {
        URL url = URI.create(BASE_URL + "/listUpcomingDaysThisYear").toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (InputStream inputStream = conn.getInputStream()) {
            return mapper.readValue(inputStream, new com.fasterxml.jackson.core.type.TypeReference<List<ImportantDay>>() {});
        }
    }
}