package cmpt213.assignment.importantdaystracker.client;

import cmpt213.assignment.importantdaystracker.control.ImportantDaysManager;
import cmpt213.assignment.importantdaystracker.view.MainFrame;

import javax.swing.*;

// STUDENT INFO
// Student #: 301398647
// Name: Evan Mangat
// Source of help: StackOverflow (debugging), ChatGPT (syntax issue with show-type button and AddButton duplication bug)

/**
 * The main class to launch the Important Days Tracker application.
 */

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImportantDaysManager manager = new ImportantDaysManager();
            manager.loadFromServer();  // Load data from server on start

            MainFrame frame = new MainFrame(manager);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    manager.sendExitSignal(); // Save to server on exit
                    frame.dispose();
                    System.exit(0);
                }
            });

            frame.setVisible(true);
        });
    }
}