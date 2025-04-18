package cmpt213.assignment.importantdaystracker.webappserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Important Days Tracker web application.
 * <p>
 * This class launches the embedded Spring Boot server and auto-configures all
 * components, controllers, and REST endpoints.
 */
@SpringBootApplication
public class WebappserverApplication {
	
    /**
     * The main method that launches the Spring Boot application.
     *
     * @param args command-line arguments (not used).
     */
	public static void main(String[] args) {
		SpringApplication.run(WebappserverApplication.class, args);
	}

}
