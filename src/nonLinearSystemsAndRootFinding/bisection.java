package nonLinearSystemsAndRootFinding;

public class bisection
{
    public static double Bisection(function_def fct, double a, double b, double tolerance) throws Exception {
        if (fct.function_(a) * fct.function_(b) >= 0) {
            throw new Exception("F(a)*F(b) < 0 not satisfied!");
        }

        double c = Double.POSITIVE_INFINITY;
        double fa = fct.function_(a);
        double fc;
        while ((b - a) / 2 > tolerance) {
            c = (a + b) / 2;
            fc = fct.function_(c);
            if (fc == 0)
                break;
            else if (fa * fc < 0)
                b = c;
            else {
                a = c;
                fa = fc;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        function_def fct = x -> Math.exp(x[0]) - x[0] - 2;
        try {
            System.out.println(Bisection(fct, 1.0, 2.0, 0.0000000000000005));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
