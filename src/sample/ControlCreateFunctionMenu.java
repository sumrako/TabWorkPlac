package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class ControlCreateFunctionMenu  {
    @FXML
    public void createUnknownFunction() throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("createunknown.fxml"));
        Scene newScene = new Scene(loader);
        Main.getCurStage().setScene(newScene);
    }

    @FXML
    public void tabulateKnownFunction() throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("tabulateknownfunction.fxml"));
        Scene newScene = new Scene(loader);
        Main.getCurStage().setScene(newScene);
    }

    @FXML
    public void back() throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        Scene newScene = new Scene(loader);
        Main.getCurStage().setScene(newScene);
    }
}
