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

    static double[] B = new double[12];
    static double proTerm(int i, double value, double x[])
    {
        double pro = 1;
        for (int j = 0; j < i; j++) {
            pro = pro * (value - x[j]);
        }
        return pro;
    }

    // Function for calculating
// divided difference table
    static void dividedDiffTable(double x[], double y[][], int n)
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
    static double applyFormula(double value, double x[],
                              double y[][], int n)
    {
        double sum = y[0][0];

        for (int i = 1; i < n; i++) {
            sum = sum + proTerm(i, value, x) * y[0][i];

        }
        return sum;

    }

    // Function for displaying
// divided difference table
    static void printDiffTable(double y[][],int n)
    {
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.HALF_UP);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                String str1 = df.format(y[i][j]);
                if (i==0){
               B[j] = y[i][j];
                }
                System.out.print(str1+"\t ");
            }
            System.out.println();
        }
    }
 public static void functionCalculate(){
     int n = 12;
     double value, sum;
     double y[][]=new double[12][12];
     double x[] = {3, 7, 11, 14, 17, 20, 25, 30, 35, 40, 42, 43};

     // y[][] is used for divided difference
     // table where y[][0] is used for input
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
     for (int i = 0; i<x.length;i++) {
         hashMap.put(x[i],y[i][0]);
     }

     function1 = aDouble -> {
         double function = 0 ;
         for (int i = 0;i<x.length;i++) {
             double multiplier = 1;
             for (int j = 0; j<i;j++){
           multiplier = multiplier * (aDouble-x[j]);
         }
             if (i==0)
                 function=B[i];
        else function = function + B[i]*multiplier;
     }
         return function;
 };



     // calculating divided difference table
     dividedDiffTable(x, y, n);

     // displaying divided difference table
     printDiffTable(y,n);

     // value to be interpolated
     value = 13;
     hashMap.put(value,applyFormula(value, x, y, n));

     // printing the value
     DecimalFormat df = new DecimalFormat("#.##");
     df.setRoundingMode(RoundingMode.HALF_UP);

     System.out.println("\nValue at "+df.format(value)+" is "
             +df.format(applyFormula(value, x, y, n)));

 }
}

    // Driver Function
