public class Main {
    public static void main(String[] args) throws Exception {
        Polinomio parabola = new Polinomio(new Double[]{1., 0., 0.});      // x^2 = 0
        Polinomio dParabola = new Polinomio(parabola.getCoeficientesDf()); // 2*x = 0

        Polinomio funcaoEstranha = new Polinomio(new Double[]{1., 0., 0., 0., -1., 1.}); // x^5 -x +1 = 0
        Polinomio dFuncaoEstranha = new Polinomio(funcaoEstranha.getCoeficientesDf());   // 5*x^4 -1 = 0

        funcaoEstranha.plot(-1, 1, 200);

        // Plotamos a raiz
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.setPenRadius(0.02);
//        StdDraw.point(parabola.newton(0.5), 0);

    }
}