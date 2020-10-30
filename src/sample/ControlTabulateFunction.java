package sample;

import functions.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlTabulateFunction implements Initializable {
    @FXML private TextField leftBorder;
    @FXML private TextField rightBorder;
    @FXML private Spinner pointsCount;
    @FXML private TextField fileFunction;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10000, 2);
        pointsCount.setValueFactory(valueFactory);
    }

    @FXML
    public void back() throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("createtabfunction.fxml"));
        Scene newScene = new Scene(loader);
        Main.getCurStage().setScene(newScene);
    }

    @FXML
    public void chooseFile(){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select function class of java (.class)");
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Functions", "*.class");
            fileChooser.getExtensionFilters().add(filter);
            File file = fileChooser.showOpenDialog(Main.getCurStage());
            fileFunction.setText(file.getAbsolutePath());
        } catch (Exception e) {
            Main.getErrorWindow(e);
        }
    }

    @FXML
    public void create() {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select path for create new tabulated function file");
            File file = directoryChooser.showDialog(Main.getCurStage());

            DownloadFunction download = new DownloadFunction();
            Main.baseFunction = (Function) download.loadClassFromFile(fileFunction.getText()).newInstance();
            if (Main.baseFunction != null) Main.curFunction.tabulateFunction(Main.baseFunction,
                    Double.parseDouble(leftBorder.getText()),
                    Double.parseDouble(rightBorder.getText()),
                    Integer.parseInt(pointsCount.getValue().toString()));
            else Main.curFunction.newFunction(Double.parseDouble(leftBorder.getText()),
                    Double.parseDouble(rightBorder.getText()),
                    Integer.parseInt(pointsCount.getValue().toString()));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("filename.fxml"));
            Stage filename = new Stage();
            filename.setScene(new Scene(loader.load()));
            filename.initModality(Modality.APPLICATION_MODAL);
            filename.setResizable(false);
            ControlFileName controller = loader.getController();
            filename.showAndWait();

            File newFile = new File(file.getAbsoluteFile() + "\\" + controller.fileName() + ".tabf");
            newFile.createNewFile();
            Main.curFunction.saveFunctionAs(newFile.getAbsolutePath());

        } catch (Exception e){
            Main.getErrorWindow(e);
        }
    }

    @FXML
    public void save() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select file for rewrite (txt, tabf)");
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Tabulated functions", "*.txt", "*.tabf");
            fileChooser.getExtensionFilters().add(filter);
            File file = fileChooser.showOpenDialog(Main.getCurStage());

            DownloadFunction download = new DownloadFunction();
            Main.baseFunction = (Function) download.loadClassFromFile(fileFunction.getText()).newInstance();
            if (Main.baseFunction != null) Main.curFunction.tabulateFunction(Main.baseFunction,
                    Double.parseDouble(leftBorder.getText()),
                    Double.parseDouble(rightBorder.getText()),
                    Integer.parseInt(pointsCount.getValue().toString()));
            else Main.curFunction.newFunction(Double.parseDouble(leftBorder.getText()),
                    Double.parseDouble(rightBorder.getText()),
                    Integer.parseInt(pointsCount.getValue().toString()));

            Main.curFunction.saveFunctionAs(file.getAbsolutePath());
        } catch (Exception e) {
            Main.getErrorWindow(e);
        }
    }

    @FXML
    public void help() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("help.fxml"));
        Stage filename = new Stage();
        filename.setScene(new Scene(loader.load()));
        filename.initModality(Modality.APPLICATION_MODAL);
        filename.setResizable(false);
        filename.showAndWait();
    }

    @FXML
    public void showTable() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showtable.fxml"));
            Stage tableStage = new Stage();

            DownloadFunction download = new DownloadFunction();
            Main.baseFunction = (Function) download.loadClassFromFile(fileFunction.getText()).newInstance();
            if (Main.baseFunction != null) Main.curFunction.tabulateFunction(Main.baseFunction,
                    Double.parseDouble(leftBorder.getText()),
                    Double.parseDouble(rightBorder.getText()),
                    Integer.parseInt(pointsCount.getValue().toString()));
            else Main.curFunction.newFunction(Double.parseDouble(leftBorder.getText()),
                    Double.parseDouble(rightBorder.getText()),
                    Integer.parseInt(pointsCount.getValue().toString()));

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
    public void showGrafic(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showgraphic.fxml"));
            Stage tableStage = new Stage();

            DownloadFunction download = new DownloadFunction();
            Main.baseFunction = (Function) download.loadClassFromFile(fileFunction.getText()).newInstance();
            if (Main.baseFunction != null) Main.curFunction.tabulateFunction(Main.baseFunction,
                    Double.parseDouble(leftBorder.getText()),
                    Double.parseDouble(rightBorder.getText()),
                    Integer.parseInt(pointsCount.getValue().toString()));
            else Main.curFunction.newFunction(Double.parseDouble(leftBorder.getText()),
                    Double.parseDouble(rightBorder.getText()),
                    Integer.parseInt(pointsCount.getValue().toString()));

            tableStage.setScene(new Scene(loader.load()));
            tableStage.initModality(Modality.APPLICATION_MODAL);
            tableStage.setTitle("Graphic of tabulated function");
            tableStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
            tableStage.showAndWait();
        } catch (Exception e) {
            Main.getErrorWindow(e);
        }
    }


}
