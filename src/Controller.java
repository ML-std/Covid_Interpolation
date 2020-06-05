import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.function.Function;

public class Controller implements Initializable {
    @FXML
    private TextField value;
    @FXML
    private NumberAxis yAreaAxis;
    @FXML
    private NumberAxis yLineAxis;
    @FXML
    private Button inputResultButton;
    @FXML
    private TextField xValues;
    @FXML
    private TextField yValues;
    @FXML
    private LineChart<Double, Double> lineGraph;

    @FXML
    private AreaChart<Double, Double> areaGraph;
    @FXML
    private NumberAxis xAreaAxis;
    @FXML
    private NumberAxis xLineAxis;

    @FXML
    public ChoiceBox<String> choiceBox;


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
        mathsGraph = new MyGraph(lineGraph, 100);
        areaMathsGraph = new MyGraph(areaGraph, 100);
        lineGraph.setVisible(true);
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
    

    private void plotLine(Function<Double, Double> function, HashMap<Double, Double> hashMap) {

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
        Direct_Method.calculateLinear();
        plotLine(Direct_Method.directMethodFunction,Direct_Method.directMethodHasHMap);
    }

    public void LagrangeMethod(ActionEvent actionEvent) {
        Lagrange_Method.calculateLagrange();
        plotLine(Lagrange_Method.lagrangeFunction,Lagrange_Method.lagrangeMethodHashMap);
    }

    public void NewtonsMethod(ActionEvent actionEvent) {
        Newtons_Divided_Method.dividedMethodHashMap.clear();
        double valueToCalculate = 37;
        double result;
        double[] x = new double[]{3, 7, 11, 14, 17, 20, 25, 30, 35, 40, 42, 43};
        double[][] y = new double[x.length][x.length];
        y[0][0] = 1;
        y[1][0] = 47;
        y[2][0] = 670;
        y[3][0] = 1529;
        y[4][0] = 3629;
        y[5][0] = 9217;
        y[6][0] = 20921;
        y[7][0] = 38226;
        y[8][0] = 61049;
        y[9][0] = 82329;
        y[10][0] = 90980;
        y[11][0] = 95591;
        Newtons_Divided_Method.functionCalculate(x,y,37);


        xAreaAxis.setUpperBound(50);
        yAreaAxis.setUpperBound(12000);
        xLineAxis.setUpperBound(50);
        yLineAxis.setUpperBound(120000);
        result = Newtons_Divided_Method.applyFormula(valueToCalculate,x,y,x.length);
        plotLine(Newtons_Divided_Method.dividedMethodFunction, Newtons_Divided_Method.dividedMethodHashMap);
        showResult(valueToCalculate,result);
    }

    public void Clear(ActionEvent actionEvent) {
        clear();
    }

    public void ResultButton(ActionEvent actionEvent) {
        double result;
        Newtons_Divided_Method.dividedMethodHashMap.clear();
        StringTokenizer xTokenizer = new StringTokenizer(xValues.getText(), " ");
        StringTokenizer yTokenizer = new StringTokenizer(yValues.getText(), " ");
        int valueToCalculate = Integer.parseInt(value.getText());

        double[] x = new double[xTokenizer.countTokens()];
        double[][] y = new double[yTokenizer.countTokens()][yTokenizer.countTokens()];
       // System.out.println(x.length);
       // System.out.println(y.length);
        if (y.length!=x.length){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Invalid Inputs");
            alert.setContentText("Please reconsider your x and y inputs (eg x = {1, 1, 1) y = {1, 2, 3}" );
            alert.showAndWait();}
        else {
            double tmpMaxX = 0;
            double tmpMaxY = 0;

            for (int i = 0; i < x.length; i++) {
                x[i] = Integer.parseInt(xTokenizer.nextToken());
                y[i][0] = Integer.parseInt(yTokenizer.nextToken());
                System.out.println(x[i] + " " + y[i][0]);
                if (i == 0) {
                    tmpMaxX = x[0];
                    tmpMaxY = y[0][0];
                }
                else {
                    if (tmpMaxX < x[i]){
                        tmpMaxX = x[i];
                    }
                    if (tmpMaxY < y[i][0]){
                        tmpMaxY = y[i][0];
                    }
                }
                if (tmpMaxX < valueToCalculate){
                    tmpMaxX = valueToCalculate;
                }

                mathsGraph.range = tmpMaxX;
                areaMathsGraph.range = tmpMaxY;
                xAreaAxis.setUpperBound(tmpMaxX + 5);
                yAreaAxis.setUpperBound(tmpMaxY + 5);
                xLineAxis.setUpperBound(tmpMaxX + 5);
                yLineAxis.setUpperBound(tmpMaxY + 5);

            }

        }

        switch (choiceBox.getValue()) {
            case "Newton's Divided Method":
                Newtons_Divided_Method.functionCalculate(x, y, valueToCalculate);
                result = Newtons_Divided_Method.applyFormula(valueToCalculate, x, y, x.length);
                if (yAreaAxis.getUpperBound() < result) {
                    yAreaAxis.setUpperBound(result);
                    yLineAxis.setUpperBound(result);
                }
                plotLine(Newtons_Divided_Method.dividedMethodFunction, Newtons_Divided_Method.dividedMethodHashMap);
                showResult(valueToCalculate, result);
                break;
            case "Lagrange Method":
                Lagrange_Method.Data[] data = new Lagrange_Method.Data[x.length];
                for (int i = 0; i < x.length; i++) {
                    data[i] = new Lagrange_Method.Data(x[i], y[i][0]);
                }
                Lagrange_Method.interpolate(data, valueToCalculate);
                plotLine(Lagrange_Method.lagrangeFunction, Lagrange_Method.lagrangeMethodHashMap);
                break;
            case "Direct Method":
                double[] tmpY = new double[x.length];
                for (int i = 0; i < x.length; i++) {
                    tmpY[i] = y[i][0];
                }
                Direct_Method.interpLinear(x, tmpY, valueToCalculate);
                plotLine(Direct_Method.directMethodFunction, Direct_Method.directMethodHasHMap);
                break;
        }

            }


    public void FunctionButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Function for last Graph Drawn");
        alert.setHeaderText("Function for this Graph");
        alert.setContentText(Newtons_Divided_Method.stringFunction);

        alert.showAndWait();

    }
    public void showResult(double valueToCalculate, double result){

        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Result");
        infoAlert.setHeaderText("Result");
        infoAlert.setContentText("For given X " + valueToCalculate + " the result is : " + result );
        infoAlert.showAndWait();
    }
}

