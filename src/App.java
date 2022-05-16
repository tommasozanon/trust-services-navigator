import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import org.json.JSONObject;

public class App extends Application {

    public static void main(String[] args) throws IOException {
        // launch();

        SiteRequests sr = SiteRequests.getInstance();
        System.out.println(sr.country_list());

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("prova.fxml"));
        primaryStage.setTitle("prova");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}