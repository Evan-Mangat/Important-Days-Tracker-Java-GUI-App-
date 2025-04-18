package cmpt213.assignment.importantdaystracker.webappserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A lightweight REST controller used to verify that the server is running.
 * <p>
 * Provides a simple health check endpoint at {@code /ping}.
 */
@RestController
public class PingController {
    /**
     * Returns a confirmation string indicating that the server is active.
     *
     * @return the string "System is up!"
     */
    @GetMapping("/ping")
    public String ping() {
        return "System is up!";
    }
}