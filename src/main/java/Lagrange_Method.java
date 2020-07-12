

import java.util.HashMap;
import java.util.function.Function;

public class Lagrange_Method  {
    public static Function<Double,Double> lagrangeFunction;
    public static HashMap<Double,Double> lagrangeMethodHashMap = new HashMap<>();

    static class Data {
        double x, y;

        public Data(double x, double y)
        {
            super();
            this.x = x;
            this.y = y;
        }

    }

    // function to interpolate the given
// data points using Lagrange's formula
// xi corresponds to the new data point
// whose value is to be obtained n
// represents the number of known data points
    static double interpolate(Data[] f, double xi)
    {
        for (Data data:f) {
            lagrangeMethodHashMap.put(data.x,data.y);
        }
        lagrangeFunction = aDouble -> LagrangeMethodCalculator(f, aDouble);

        return LagrangeMethodCalculator(f, xi);
    }

    private static double LagrangeMethodCalculator(Data[] f, Double aDouble) {
        double result1 = 0; // Initialize result

        for (int i = 0; i < f.length; i++)
        {
            // Compute individual terms of above formula
            double term = f[i].y;
            for (int j = 0; j < f.length; j++)
            {
                if (j != i)
                    term = term*(aDouble - f[j].x) / (f[i].x - f[j].x);

            }

            // Add current term to result
            result1 += term;

        }
        return result1;
    }
}



