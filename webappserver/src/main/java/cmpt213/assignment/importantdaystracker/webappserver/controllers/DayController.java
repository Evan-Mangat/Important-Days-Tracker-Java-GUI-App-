package cmpt213.assignment.importantdaystracker.webappserver.controllers;

import cmpt213.assignment.importantdaystracker.webappserver.control.ImportantDayManager;
import cmpt213.assignment.importantdaystracker.webappserver.model.ImportantDay;
import cmpt213.assignment.importantdaystracker.webappserver.model.Occasion;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

/**
 * REST controller providing endpoints to add, remove, and filter important days.
 * <p>
 * This controller interacts with {@link ImportantDayManager} to perform all data operations.
 */
@RestController
public class DayController {

    /**
     * Adds a new {@link ImportantDay} to the list.
     *
     * @param day the day to be added, sent in the request body as JSON.
     * @return the updated list of all important days.
     */
    @PostMapping("/addDay")
    public List<ImportantDay> addDay(@RequestBody ImportantDay day) {
        ImportantDayManager.addDay(day);
        return ImportantDayManager.getAllDays();
    }

    /**
     * Removes a given {@link ImportantDay} from the list.
     *
     * @param day the day to remove, sent in the request body as JSON.
     * @return the updated list of all important days.
     */
    @PostMapping("/removeDay")
    public List<ImportantDay> removeDay(@RequestBody ImportantDay day) {
        ImportantDayManager.removeDay(day);
        return ImportantDayManager.getAllDays();
    }

    /**
     * Retrieves all important days that have already passed in the current year.
     *
     * @return a sorted list of passed days from the current year.
     */
    @GetMapping("/listPassedDaysThisYear")
    public List<ImportantDay> listPassed() {
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();

        return ImportantDayManager.getAllDays().stream()
            .filter(day -> {
                LocalDate adjusted = LocalDate.of(currentYear, day.getDate().getMonth(), day.getDate().getDayOfMonth());

                // For Occasion: check if it occurs this year
                if (day instanceof Occasion) {
                    int baseYear = day.getDate().getYear();
                    int frequency = ((Occasion) day).getFrequency();
                    if ((currentYear - baseYear) % frequency != 0) {
                        return false;
                    }
                }

                return adjusted.isBefore(now);
            })
            .sorted(Comparator.comparing(day -> LocalDate.of(currentYear, day.getDate().getMonth(), day.getDate().getDayOfMonth())))
            .collect(Collectors.toList());
    }

    /**
     * Retrieves all upcoming important days (including today) from the current year.
     *
     * @return a sorted list of upcoming days from the current year.
     */
    @GetMapping("/listUpcomingDaysThisYear")
    public List<ImportantDay> listUpcoming() {
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
    
        return ImportantDayManager.getAllDays().stream()
            .filter(day -> {
                LocalDate adjusted = LocalDate.of(currentYear, day.getDate().getMonth(), day.getDate().getDayOfMonth());
    
                // For Occasion: check if it occurs this year
                if (day instanceof Occasion) {
                    int baseYear = day.getDate().getYear();
                    int frequency = ((Occasion) day).getFrequency();
                    if ((currentYear - baseYear) % frequency != 0) {
                        return false;
                    }
                }
    
                return !adjusted.isBefore(now);
            })
            .sorted(Comparator.comparing(day -> LocalDate.of(currentYear, day.getDate().getMonth(), day.getDate().getDayOfMonth())))
            .collect(Collectors.toList());
    }

    /**
     * Retrieves all important days from the current year, regardless of date position.
     *
     * @return a sorted list of all important days occurring in the current year.
     */
    @GetMapping("/listDaysThisYear")
    public List<ImportantDay> listThisYear() {
        int currentYear = LocalDate.now().getYear();
    
        return ImportantDayManager.getAllDays().stream()
            .filter(day -> {
                if (day instanceof Occasion) {
                    int baseYear = day.getDate().getYear();
                    int frequency = ((Occasion) day).getFrequency();
    
                    // Robust check: only show if the current year is on the schedule
                    return frequency > 0 && currentYear >= baseYear && 
                           (currentYear - baseYear) % frequency == 0;
                }
    
                // Birthdays and anniversaries always recur annually
                return true;
            })
            .sorted(Comparator.comparing(day ->
                LocalDate.of(currentYear, day.getDate().getMonth(), day.getDate().getDayOfMonth())))
            .collect(Collectors.toList());
    }

    /**
     * Triggers a save operation to persist the current list to disk.
     *
     * @return a simple confirmation message.
     */
    @GetMapping("/exit")
    public String exitAndSave() {
        ImportantDayManager.saveToJson();
        return "Saved and exiting.";
    }
}