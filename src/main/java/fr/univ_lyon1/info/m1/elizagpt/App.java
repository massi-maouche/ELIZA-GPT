package fr.univ_lyon1.info.m1.elizagpt;

import fr.univ_lyon1.info.m1.elizagpt.controller.Controller;
import fr.univ_lyon1.info.m1.elizagpt.view.SearchStrategy.SubstringSearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main application class for ElizaGPT.
 */
public class App extends Application {

    /**
     * The main entry point for the application.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(final Stage stage) {
        Controller controller = Controller.getInstance();
        View view1 = new View(new Stage(), 400, 400, controller);
        View view2 = new View(new Stage(), 600, 600, controller);
        view2.setSearchStrategy(new SubstringSearchStrategy());
        view1.setSearchStrategy(new SubstringSearchStrategy());

        view1.replyToUser("Bonjour");
    }

    /**
     * The main method to launch the application.
     *
     * @param args The command line arguments.
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }
}
