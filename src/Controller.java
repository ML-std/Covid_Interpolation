import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.function.Function;

public class Controller implements Initializable
{
    @FXML
    private LineChart<Double, Double> lineGraph;

    @FXML
    private AreaChart<Double, Double> areaGraph;

    @FXML
    private Button lineGraphButton;

    @FXML
    private Button areaGraphButton;


    @FXML
    private Button xyButton;

    @FXML
    private Button xyButton2;

    @FXML
    private Button squaredButton;

    @FXML
    private Button squaredButton2;

    @FXML
    private Button cubedButton;

    @FXML
    private Button cubedButton2;

    @FXML
    private Button clearButton;

    private MyGraph mathsGraph;
    private MyGraph areaMathsGraph;

    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
        mathsGraph = new MyGraph(lineGraph, 50);
        areaMathsGraph = new MyGraph(areaGraph, 50);
    }

    @FXML
    private void handleLineGraphButtonAction(final ActionEvent event) {
        lineGraph.setVisible(true);
        areaGraph.setVisible(false);
    }

    @FXML
    private void handleAreaGraphButtonAction(final ActionEvent event) {
        areaGraph.setVisible(true);
        lineGraph.setVisible(false);
    }
    

    private void plotLine(Function<Double, Double> function, HashMap<Double,Double> hashMap) {
        

        if (lineGraph.isVisible()) {
           
            mathsGraph.plotLine(function,hashMap);
        } else {
            areaMathsGraph.plotLine(function,hashMap);
        }
    }

    

    private void clear() {
        if (lineGraph.isVisible()) {
            mathsGraph.clear();
        } else {
            areaMathsGraph.clear();
        }
    }


    public void DirectMethod(ActionEvent actionEvent) {
        Newtons_Divided_Method.functionCalculate();
        plotLine(Newtons_Divided_Method.function1, Newtons_Divided_Method.hashMap);
    }

    public void LagrangeMethod(ActionEvent actionEvent) {
    }

    public void NewtonsMethod(ActionEvent actionEvent) {
    }

    public void Clear(ActionEvent actionEvent) {
        clear();
    }
}
