package sample;

import functions.Function;
import functions.TabulatedFunctionDocument;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage curStage;
    public static TabulatedFunctionDocument curFunction = new TabulatedFunctionDocument();
    public static Function baseFunction;

    public static Stage getCurStage() {
        return curStage;
    }

    public static void getErrorWindow(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(e.getClass().getSimpleName());
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }


    @Override
    public void start(Stage stage) throws IOException {
        curStage = stage;
        stage.setTitle("TabWorkPlace");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainmenu.fxml"));
        stage.setScene(new Scene(loader.load()));

        //Displaying the contents of the stage
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
