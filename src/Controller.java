
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    Label label;

    public void changeTitle(ActionEvent actionEvent) throws IOException {
        label.setText("eeeeeeeeeeeeeeeeeeeeeee");
        System.out.println("press");
    }
}