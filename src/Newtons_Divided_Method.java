import jdk.nashorn.internal.ir.CallNode;
import org.omg.PortableServer.IdUniquenessPolicy;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Function;

public class Newtons_Divided_Method {
    static HashMap<Double,Double> hashMap = new HashMap<>();
    static Function<Double, Double> function1;


    static String stringFunction;
    static double proTerm(int i, double value, double[] x)
    {
        double pro = 1;
        for (int j = 0; j < i; j++) {
            pro = pro * (value - x[j]);
        }
        return pro;
    }

    // Function for calculating
// divided difference table
    static void dividedDiffTable(double[] x, double[][] y, int n)
    {
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                y[j][i] = (y[j][i - 1] - y[j + 1]
                        [i - 1]) / (x[j] - x[i + j]);
            }
        }
    }

    // Function for applying Newton's
// divided difference formula
    static double applyFormula(double value, double[] x,
                               double[][] y, int n)
    {
        double sum = y[0][0];

        for (int i = 1; i < n; i++) {
            sum = sum + proTerm(i, value, x) * y[0][i];

        }
        System.out.println("omomıoomkıjmıo " + sum);
        return sum;

    }

    // Function for displaying
// divided difference table

    public static void functionCalculate(double[] x, double[][] y, double value){
         double[] B;
        // y[][] is used for divided difference
        // table where y[][0] is used for input

        B = new double[x.length];
        int n = x.length;

        // calculating divided difference table
        dividedDiffTable(x, y, n);

        // displaying divided difference table
      //  printDiffTable(y,n);

        // value to be interpolated


        // printing the value


        System.out.println("\nValue at "+  value +" is " +
                (applyFormula(value, x, y, n)));

        for (int i = 0; i<x.length;i++) {
            hashMap.put(x[i],y[i][0]);
            B[i] = y[0][i];
        }
        hashMap.put(value,applyFormula(value, x, y, n));
        function1 = aDouble -> {
            double function = 0 ;
            for (int i = 0; i< x.length; i++) {
                double multiplier = 1;
                for (int j = 0; j<i;j++){
                    multiplier = multiplier * (aDouble- x[j]);
                }
                if (i==0)
                    function=B[i];
                else function = function + B[i]*multiplier;
            }
            return function;
        };
            System.out.println();
            showFunction(x,B);
    }
    public static void showFunction(double[] x,double[] B ){
        stringFunction = "Function = ";
        for (int i = 0; i<x.length;i++) {
            boolean isEndOfTheFunction = false;
            if (i!=0){
                stringFunction = stringFunction + "[" + B[i] + " ";
            }
            else
                stringFunction = stringFunction + "[" + B[i] +"]" + " + ";

            for (int j = 0; j < i; j++){
                if (i==x.length-1){
                    stringFunction = stringFunction + " * (x - " + x[j] + ")";
                    isEndOfTheFunction = true;
                }
                else if ((j==i-1) )
                    stringFunction = stringFunction + "* (x - " + x[j] + ")] + ";
                else{
                    stringFunction = stringFunction + "* (x - " + x[j] + ") ";
                }
            }
            if (isEndOfTheFunction){
                stringFunction = stringFunction + "]";
            }



        }
        System.out.println();
        System.out.println(stringFunction);
    }
    public static void showSteps(){

    }
}

// Driver Function