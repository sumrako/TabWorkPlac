package sample;

import functions.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ControlShowTable implements Initializable {
    @FXML private TableView<FunctionPoint> points;
    @FXML private TableColumn<FunctionPoint, Double> x;
    @FXML private TableColumn<FunctionPoint, Double> y;
    @FXML private TextField xValue;
    @FXML private TextField yValue;

    private ObservableList<FunctionPoint> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ArrayList<FunctionPoint> arrayList = new ArrayList<>();
            for (int i = 0; i < Main.curFunction.getPointsCount(); i++) arrayList.add(Main.curFunction.getPoint(i));
            list = FXCollections.observableList(arrayList);
            x.setCellValueFactory(new PropertyValueFactory<>("x"));
            y.setCellValueFactory(new PropertyValueFactory<>("y"));
            points.setItems(list);
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
    public void rewrite() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select file for rewrite (txt, tabf)");
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Tabulated functions", "*.txt", "*.tabf");
            fileChooser.getExtensionFilters().add(filter);
            File file = fileChooser.showOpenDialog(Main.getCurStage());
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
    public void addPoint(){
        try {
            Main.curFunction.addPoint(new FunctionPoint(Double.parseDouble(xValue.getText()), Double.parseDouble(yValue.getText())));
            ArrayList<FunctionPoint> arrayList = new ArrayList<>();
            for (int i = 0; i < Main.curFunction.getPointsCount(); i++) arrayList.add(Main.curFunction.getPoint(i));
            list = FXCollections.observableList(arrayList);
            points.setItems(list);
        } catch (Exception e) {
            Main.getErrorWindow(e);
        }
    }

    @FXML
    public void deletePoint(){
        try {
            Main.curFunction.deletePoint(points.getSelectionModel().getSelectedIndex());
            points.getItems().remove(points.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            Main.getErrorWindow(e);
        }
    }

    @FXML
    public void setPoint(){
        try {
            FunctionPoint newPoint = new FunctionPoint(Double.parseDouble(xValue.getText()), Double.parseDouble(yValue.getText()));
            Main.curFunction.setPoint(points.getSelectionModel().getSelectedIndex(), newPoint);
            points.getItems().set(points.getSelectionModel().getSelectedIndex(), newPoint);
        } catch (Exception e) {
            Main.getErrorWindow(e);
        }
    }

    @FXML
    public void setX(){
        try {
            FunctionPoint newPoint = new FunctionPoint(Double.parseDouble(xValue.getText()), points.getItems().get(points.getSelectionModel().getSelectedIndex()).getY());
            Main.curFunction.setPointX(points.getSelectionModel().getSelectedIndex(), Double.parseDouble(xValue.getText()));
            points.getItems().set(points.getSelectionModel().getSelectedIndex(), newPoint);
        } catch (Exception e) {
            Main.getErrorWindow(e);
        }
    }

    @FXML
    public void setY(){
        try {
            FunctionPoint newPoint = new FunctionPoint(points.getItems().get(points.getSelectionModel().getSelectedIndex()).getX(), Double.parseDouble(yValue.getText()));
            Main.curFunction.setPointY(points.getSelectionModel().getSelectedIndex(), Double.parseDouble(yValue.getText()));
            points.getItems().set(points.getSelectionModel().getSelectedIndex(), newPoint);
        } catch (Exception e) {
            Main.getErrorWindow(e);
        }
    }
}
