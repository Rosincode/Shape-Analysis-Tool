package nl.thairosi.sat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Overrides the JavaFX start method in order setup our own JavaFX Stage
     *
     * @param stage is a JavaFX Stage object that is to be used for setting up our own application
     * @throws IOException if importing the fxml fails
     */
    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        stage.setTitle("Shape Analysis Tool");
        scene = new Scene(loadFXML("homeView"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Automatically sets the root of the fxml given as a parameter
     *
     * @param fxml is the fxml name that is to be used for the application
     * @throws IOException if importing the fxml fails
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads the given fxml file for the JavaFX Stage
     *
     * @param fxml is the fxml name that is to be used for the application
     * @return the execution of the instantiated FXMLLoader object using our imported fxml file
     * @throws IOException if importing the fxml file fails
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Automatically starts when the application is executed
     * @param args stores Java command line arguments
     */
    public static void main(String[] args) {
        launch();
    }

}