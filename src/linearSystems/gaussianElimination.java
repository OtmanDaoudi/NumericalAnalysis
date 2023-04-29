package linearSystems;

public class gaussianElimination
{
    public static double[] gauss(double[][] A, double[] B) {
        //elimination phase
        for (int column = 0; column < A.length; column++) {
            for (int line = column + 1; line < A.length; line++) {
                MatrixUtilities.eliminateEntry(A, B, line, column);
            }
        }
        //back-solving phase
        return MatrixUtilities.backSolve(A, B);
    }

    public static void main(String[] args)
    {

    }
}
