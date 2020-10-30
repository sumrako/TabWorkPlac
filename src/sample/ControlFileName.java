package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControlFileName {
    @FXML private TextField filename;

    public String fileName(){
        return filename.getText();
    }

    @FXML
    public void close(){
        Stage stage = (Stage) filename.getScene().getWindow();
        stage.close();
    }
}
