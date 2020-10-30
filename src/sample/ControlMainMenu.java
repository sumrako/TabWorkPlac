package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;


public class ControlMainMenu {

    @FXML
    public void createNewFunction(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("createtabfunction.fxml"));
        Scene newScene = new Scene(loader);
        Main.getCurStage().setScene(newScene);
    }

    @FXML
    public void about() throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("about.fxml"));
        Scene newScene = new Scene(loader);
        Main.getCurStage().setScene(newScene);
    }

    @FXML
    public void help() throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("help.fxml"));
        Scene newScene = new Scene(loader);
        Main.getCurStage().setScene(newScene);
    }

    @FXML
    public void showTabulatedFunction() throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("modificationfunction.fxml"));
        Scene newScene = new Scene(loader);
        Main.getCurStage().setScene(newScene);
    }
}
