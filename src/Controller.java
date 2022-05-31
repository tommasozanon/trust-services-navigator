
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class Controller {

    @FXML
    Label label;

    private Stage stage;
    private Scene scene;
    private Parent root;

    // public void changeTitle(ActionEvent actionEvent) throws IOException {
    // label.setText("eeeeeeeeeeeeeeeeeeeeeee");
    // System.out.println("press");
    // }

    public void switchScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("prova.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("prova2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}