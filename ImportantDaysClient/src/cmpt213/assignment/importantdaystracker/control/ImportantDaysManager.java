package cmpt213.assignment.importantdaystracker.control;

import cmpt213.assignment.importantdaystracker.model.ImportantDay;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * Acts as the controller between the client UI and the server.
 * <p>
 * Manages the list of important days by interacting with the server via the HttpClientHelper.
 * Provides functionality to add, remove, and retrieve important days, and maintain the local list.
 */

public class ImportantDaysManager {
    private final List<ImportantDay> dayList = new ArrayList<>();

    /**
     * Returns a copy of the current important days list.
     *
     * @return a new List containing all important days.
     */
    public List<ImportantDay> getDayList() {
        return new ArrayList<>(dayList);
    }

    /**
     * Loads all important days from the server and updates the local list.
     */
    public void loadFromServer() {
        try {
            dayList.clear();
            dayList.addAll(HttpClientHelper.getAllDays());
        } catch (IOException e) {
            showError("Failed to load days from server: " + e.getMessage());
        }
    }

    /**
     * Sends a request to add an important day to the server and updates the local list.
     *
     * @param day the ImportantDay to add.
     */
    public void addDay(ImportantDay day) {
        try {
            dayList.clear();
            dayList.addAll(HttpClientHelper.addDay(day));
        } catch (IOException e) {
            showError("Failed to add day: " + e.getMessage());
        }
    }

    /**
     * Sends a request to remove an important day from the server and updates the local list.
     *
     * @param day the ImportantDay to remove.
     */
    public void removeDay(ImportantDay day) {
        try {
            dayList.clear();
            dayList.addAll(HttpClientHelper.removeDay(day));
        } catch (IOException e) {
            showError("Failed to remove day: " + e.getMessage());
        }
    }

    /**
     * Sends an exit signal to the server to save the data before the client shuts down.
     */
    public void sendExitSignal() {
        try {
            HttpClientHelper.sendExit();
        } catch (IOException e) {
            showError("Failed to notify server on exit: " + e.getMessage());
        }
    }

    /**
     * Displays an error message dialog.
     *
     * @param message the error message to display.
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Server Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Creates a new ImportantDay using the factory and attempts to add it via the server.
     *
     * @param type        the type of the important day (e.g., birthday, anniversary).
     * @param name        the name of the day.
     * @param date        the LocalDate of the day.
     * @param description the optional description.
     * @param extraInfo   type-specific additional field (e.g., location or person’s name).
     */

    public void addImportantDay(String type, String name, LocalDate date, String description, String extraInfo) {
        try {
            ImportantDay newDay = cmpt213.assignment.importantdaystracker.model.ImportantDayFactory.getInstance(
                type, name, date, description, extraInfo
            );
            addDay(newDay);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding day: " + e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Loads only the important days that have already passed this year.
     */
    public void loadPassedDaysThisYear() {
        try {
            dayList.clear();
            dayList.addAll(HttpClientHelper.getPassedDaysThisYear());
        } catch (IOException e) {
            showError("Failed to load passed days: " + e.getMessage());
        }
    }
    
    /**
     * Loads only the important days that are upcoming (or today) in the current year.
     */
    public void loadUpcomingDaysThisYear() {
        try {
            dayList.clear();
            dayList.addAll(HttpClientHelper.getUpcomingDaysThisYear());
        } catch (IOException e) {
            showError("Failed to load upcoming days: " + e.getMessage());
        }
    }
}