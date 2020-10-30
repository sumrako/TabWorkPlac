package sample;

import functions.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class ControlModificationFunction {
    @FXML private TextField functionPath;

    @FXML
    public void showTable(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showtable.fxml"));
            Stage tableStage = new Stage();

            Main.curFunction = new TabulatedFunctionDocument(functionPath.getText());

            tableStage.setScene(new Scene(loader.load()));
            tableStage.initModality(Modality.APPLICATION_MODAL);
            tableStage.setTitle("Table of tabulated function");
            tableStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
            tableStage.showAndWait();
        } catch (Exception e) {
            Main.getErrorWindow(e);
        }
    }

    @FXML
    public void showGraphic(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showgraphic.fxml"));
            Stage tableStage = new Stage();

            Main.curFunction = new TabulatedFunctionDocument(functionPath.getText());

            tableStage.setScene(new Scene(loader.load()));
            tableStage.initModality(Modality.APPLICATION_MODAL);
            tableStage.setTitle("Graphic of tabulated function");
            tableStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
            tableStage.showAndWait();
        } catch (Exception e) {
            Main.getErrorWindow(e);
        }
    }

    @FXML
    public void back() throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        Scene newScene = new Scene(loader);
        Main.getCurStage().setScene(newScene);
    }

    @FXML
    public void chooseFile(){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select function class of java (.class)");
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Tabulated functions", "*.tabf", "*.txt");
            fileChooser.getExtensionFilters().add(filter);
            File file = fileChooser.showOpenDialog(Main.getCurStage());
            functionPath.setText(file.getAbsolutePath());
        } catch (Exception e) {
            Main.getErrorWindow(e);
        }
    }
}
