import javafx.scene.chart.XYChart;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MyGraph {

    private XYChart<Double, Double> graph;
     double range;

    public MyGraph(final XYChart<Double, Double> graph, final double range) {
        this.graph = graph;
        this.range = range;
    }

    public void plotLine(final Function<Double, Double> function, HashMap<Double, Double> data) {
        final XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();

        for (double x = -range; x <= range; x = x + 0.02) {
            plotPoint(x, function.apply(x), series);


        }
        graph.getData().add(series);
        for (Map.Entry<Double, Double> mapElement : data.entrySet()) {
            XYChart.Series<Double, Double> series2 = new XYChart.Series<Double, Double>();
            double x = mapElement.getKey();
            double y = mapElement.getValue();
            plotPoint(x,y,series2);
            graph.getData().add(series2);
        }


    }

    public void plotPoint(final double x, final double y,
                           final XYChart.Series<Double, Double> series) {
        series.getData().add(new XYChart.Data<Double, Double>(x, y));
    }

    public void clear() {
        graph.getData().clear();
    }
}