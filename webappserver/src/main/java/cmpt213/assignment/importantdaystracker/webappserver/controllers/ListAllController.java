package cmpt213.assignment.importantdaystracker.webappserver.controllers;

import cmpt213.assignment.importantdaystracker.webappserver.control.ImportantDayManager;
import cmpt213.assignment.importantdaystracker.webappserver.model.ImportantDay;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;       

/**
 * REST controller that provides an endpoint to list all important days.
 * <p>
 * The returned list is sorted by date using the natural ordering defined in {@link ImportantDay}.
 */
@RestController
public class ListAllController {

    /**
     * Retrieves all stored important days sorted by date.
     *
     * @return a sorted list of all important days.
     */
    @GetMapping("/listAll")
    public List<ImportantDay> listAll() {
            return ImportantDayManager.getAllDays().stream()
            .sorted()
            .collect(Collectors.toList());
    }
}