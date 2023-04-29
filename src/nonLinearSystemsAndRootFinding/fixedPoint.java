package nonLinearSystemsAndRootFinding;

import static linearSystems.MatrixUtilities.norm;

public class fixedPoint
{
    public static double fixedPointIteration(function_def g, double guess, int iteration, double error) throws Exception
    {
        if(iteration == 0) return guess;

        double newGuess = g.function_(guess);
        if(Math.abs(newGuess - guess) <= error) return newGuess;
        else if(iteration == 1) throw new Exception("FIP diverges");
        else return fixedPointIteration(g, newGuess, iteration - 1, error);
    }

    public static double[] fixedPointIteration(function_def[] g, double[] guess, int iteration, double error) throws Exception
    {
        if(iteration == 0) return guess;

        double[] newGuess = new double[guess.length];
        for(int i=0; i<newGuess.length; i++)  newGuess[i] = g[i].function_(guess);
        if(norm(guess, newGuess) <= error) return newGuess;
        else if(iteration == 1) throw new Exception("FIP diverges");
        else return fixedPointIteration(g, newGuess, iteration - 1, error);
    }

    public static void main(String[] args) {

        try
        {
//            //test 1 equation
//            function_def g = x -> Math.log(2 + x[0]);
//            System.out.println(fixedPointIteration(g, 0.1, 6, 0.005));

            //test a system of 2 equations
            function_def g1 = params -> -0.02*params[0]*params[0] - 0.02*params[1]*params[1] - 0.02*params[2]*params[2] + 4;
            function_def g2 = params -> -0.05*params[0]*params[0] - 0.05*params[2]*params[2] + 2.5;
            function_def g3 = params -> 0.025*params[0]*params[0] + 0.025*params[1]*params[1] - 1.875;

            function_def[] g = {g1, g2, g3};
            double[] guess = {0, 0, 0};
            double[] finalRes = fixedPointIteration(g, guess, 100, 1.e-4);
            System.out.println(finalRes[0] + " " +finalRes[1]+" "+finalRes[2]);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
