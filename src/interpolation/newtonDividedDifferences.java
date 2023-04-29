package interpolation;

public class newtonDividedDifferences
{
    //evaluates f[x1...xn]
    public static double delta(double[]...params)
    {
        double res;
        if(params.length == 1) res = params[0][1];
        else
        {
            //delta[x1..xl]
            double[][] delta1 = new double[params.length - 1][2];
            System.arraycopy(params, 1, delta1, 0, delta1.length);

            double[][] delta2 = new double[params.length - 1][2];
            System.arraycopy(params, 0, delta2, 0, delta2.length);

            res = (delta(delta1) - delta(delta2)) / (delta1[delta1.length - 1][0] - delta2[0][0]);
        }
        return res;
    }

    public static double[] NewtonDD(double[][] data)
    {
        double[] x = new double[data.length];
        for(int i=0; i< data.length; i++) x[i] = data[i][0];
        double[] y = new double[data.length];
        for(int i=0; i< data.length; i++) y[i] = data[i][1];

        double[] a = new double[data.length];

        for(int i=0; i<a.length; i++) //calculate coeffs
        {
            double[][] params = new double[i + 1][2];
            for(int j=0; j< params.length; j++)
            {
                params[j][0] = x[j];
                params[j][1] = y[j];
            }
            a[i] = delta(params);
        }
        return a;
    }

    public static String formattedNewtonDD(double[][] data)
    {
        double[] a = NewtonDD(data);
        StringBuilder res = new StringBuilder();
        for(int i=0; i<a.length; i++)
        {
            if(a[i] == 0) continue;
            else if(i != 0) res.append(a[i] >= 0 ? "+" : "-").append(Math.abs(a[i]));
            else res.append(a[i]);

            for(int j=0; j<i; j++) res.append(String.format("(x %s %f)",data[j][0] >= 0 ? "+" : "-",Math.abs(data[j][0])));
        }
        return res.toString();
    }

    public static void main(String[] args)
    {
        double[] res = NewtonDD(new double[][]{{0, 2}, {1, 1}, {2, 0}, {3, -1}});
        for(int i=0; i<res.length; i++) System.out.println(res[i]);

        System.out.println(formattedNewtonDD(new double[][]{{0, 2}, {1, 1}, {2, 0}, {3, -1}}));
    }
}
