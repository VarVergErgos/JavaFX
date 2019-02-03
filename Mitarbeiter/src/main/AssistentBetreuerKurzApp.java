package main;

import java.util.Locale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
  
public class AssistentBetreuerKurzApp extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/BetreuerViewKurz.fxml"));

        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("/css/betreuer.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Betreuer KurzDialog");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.Category.DISPLAY, Locale.GERMAN);
        launch(args);
    }
}
