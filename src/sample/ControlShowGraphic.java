package sample;

import functions.Function;
import functions.FunctionPointIndexOutOfBoundsException;
import functions.TabulatedFunction;
import functions.TabulatedFunctionDocument;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ControlShowGraphic implements Initializable {
    @FXML private ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
    @FXML private ObservableList<XYChart.Series<Number, Number>> series = FXCollections.observableArrayList();
    @FXML private NumberAxis yAxis;
    @FXML private NumberAxis xAxis;
    @FXML private LineChart<Number, Number> chart;
    private final Function baseFunction = Main.baseFunction;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for (int i = 0; i < Main.curFunction.getPointsCount(); i++) datas.add(new XYChart.Data(Main.curFunction.getPointX(i), Main.curFunction.getPointY(i)));
            series.add(new XYChart.Series(datas));

            double stepX = Math.ceil((Main.curFunction.getPointX(Main.curFunction.getPointsCount() - 1) - Main.curFunction.getPointX(0)) / Main.curFunction.getPointsCount());
            xAxis = new NumberAxis(Math.ceil(Main.curFunction.getPointX(0)), Math.ceil(Main.curFunction.getPointX(Main.curFunction.getPointsCount() - 1)), stepX);

            double minY = minY();
            double maxY = maxY();
            double stepY = Math.ceil((maxY - minY) / Main.curFunction.getPointsCount());
            yAxis = new NumberAxis(Math.ceil(minY), Math.ceil(maxY), stepY);

            chart.setData(series);

            if (baseFunction != null) chart.setTitle(baseFunction.getClass().getSimpleName());

            series.get(0).getData().forEach(data->{
                Node node = data.getNode();
                Tooltip tooltip = new Tooltip("x: " + data.getXValue() + '\n' + "y: " + data.getYValue());
                Tooltip.install(node, tooltip);
            });
        } catch (Exception e) {
            Main.getErrorWindow(e);
        }
    }

    private double minY(){
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < Main.curFunction.getPointsCount(); i++) list.add(Main.curFunction.getPointY(i));
        return Collections.min(list);
    }

    private double maxY(){
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < Main.curFunction.getPointsCount(); i++) list.add(Main.curFunction.getPointY(i));
        return Collections.max(list);
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



}
