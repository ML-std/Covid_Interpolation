import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

public class Direct_Method{
    public static Function<Double,Double> directMethodFunction;
    public static HashMap<Double,Double> directMethodHasHMap = new HashMap<>();
    public static  double[] interpLinear(double[] x, double[] y, double xi) throws IllegalArgumentException {

        if (x.length != y.length) {
            throw new IllegalArgumentException("X and Y must be the same length");
        }
        if (x.length == 1) {
            throw new IllegalArgumentException("X must contain more than one value");
        }
        for (int i=0;i<x.length;i++){
            directMethodHasHMap.put(x[i],y[i]);
        }

        double[] dx = new double[x.length - 1];
        double[] dy = new double[x.length - 1];
        double[] slope = new double[x.length - 1];
        double[] intercept = new double[x.length - 1];

        // Calculate the line equation (i.e. slope and intercept) between each point
        directMethodCalculator(x, y, dx, dy, slope, intercept);

        // Perform the interpolation here
        double[] yi = new double[1];
        for (int i = 0; i < 1; i++) {
            if ((xi > x[x.length - 1]) || (xi < x[0])) {
                yi[i] = Double.NaN;
            }
            else {
                int loc = Arrays.binarySearch(x, xi);
                if (loc < -1) {
                    loc = -loc - 2;
                    yi[i] = slope[loc] * xi + intercept[loc];
                }
                else {
                    yi[i] = y[loc];
                }
            }
        }
        directMethodFunction = aDouble -> {
            directMethodCalculator(x, y, dx, dy, slope, intercept);
            double yi1 =0;
            for (int i = 0; i < 1; i++) {
                if ((aDouble > x[x.length - 1]) || (aDouble < x[0])) {
                    yi1 = Double.NaN;
                }
                else {
                    int loc = Arrays.binarySearch(x, aDouble);
                    if (loc < -1) {
                        loc = -loc - 2;
                        yi1 = slope[loc] * aDouble + intercept[loc];
                    }
                    else {
                        yi1 = y[loc];
                    }
                }
            }

            return yi1;
        };

        return yi;
    }

    private static void directMethodCalculator(double[] x, double[] y, double[] dx, double[] dy, double[] slope, double[] intercept) {
        for (int i = 0; i < x.length - 1; i++) {
            dx[i] = x[i + 1] - x[i];
            if (dx[i] == 0) {
                throw new IllegalArgumentException("X must be monotonic. A duplicate " + "x-value was found");
            }
            if (dx[i] < 0) {
                throw new IllegalArgumentException("X must be sorted");
            }
            dy[i] = y[i + 1] - y[i];
            slope[i] = dy[i] / dx[i];
            intercept[i] = y[i] - x[i] * slope[i];
        }
    }

    public static double[] calculateLinear(){
        double[] x={3, 7, 11, 14, 17, 20, 25, 30, 35, 40, 42, 43};
        double[] y={1,47,670,1529,3629,9217,20921,38226,61049,82329,90980,95591};
        double xi=37;
        System.out.println(Arrays.toString(interpLinear(x, y, xi)));
        return interpLinear(x,y,xi);

    }


}
