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

    // Driver code
    public static void main(String[] args)
    {
        calculateLagrange();
    }
    public static void calculateLagrange(){
        Data[] f ={new Data(3, 1), new Data(7, 47), new Data(11, 670),
                new Data(14, 1529), new Data(17, 3629), new Data(20, 9217),
                new Data(25, 20921), new Data(30, 38226), new Data(35, 61049),
                new Data(40, 82329), new Data(42, 90980), new Data(43, 95591)};

        System.out.print("\n"+(int)interpolate(f, 37));
    }
}



