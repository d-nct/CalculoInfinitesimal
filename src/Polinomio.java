import java.util.ArrayList;

import static java.lang.Math.abs;

public class Polinomio {
  public static final double MARGEM_PLOT = 0.5;
  private final Double[] coeficientesDf;
  private final Double[] coeficientes;
  private final int grau;
  public Polinomio(Double[] coeficientes) {
    // x^3 -2x +4 -> Polinomio( {1., 0., -1., 4.} )
    this.coeficientes = coeficientes;
    this.grau = coeficientes.length - 1;

    // Calculamos a derivada "analítica"
    int grauDaDf = this.grau - 1;
    this.coeficientesDf = new Double[grauDaDf + 1]; // P de grau n tem n+1 termos
    for (int i = 0; i < grauDaDf + 1; i++) {
      this.coeficientesDf[i] = coeficientes[i] * (this.grau - i);
    }
  }

  public int getGrau() {
    return grau;
  }

  public Double[] getCoeficientesDf() {
    return coeficientesDf;
  }

  public Double getPonto(Double x) {
    Double valor = 0.;
    int grau_tmp = this.grau;
    for (Double coef : coeficientes) {
      valor += coef * Math.pow(x, grau_tmp--);
    }
    return valor;
  }

  public Double newton(Double x0, Double precisao, Double ytol, int maxIter) throws Exception {
  //    Aplica o método de Newton na função  f  e retorna a aprox. da raiz (com o erro mínimo  precisao  ).
  //    Parameters
  //    ----------
  //    f : function
  //        Função a qual será aplicada o Método de Newton
  //    df : function
  //        Derivada de f.
  //    x0 : float, opcional
  //        Ponto inicial a ser vasculhado. Idealmente, está próximo da raiz. O padrão é 0.
  //    prec : float, optional
  //        Tamanho do passo de newton pequeno o suficiente para retornarmos um valor. O padrão é 1e-8.
  //    ytol : float, optional
  //        Tamanho da tolerância ao redor do eixo y para retornarmos um valor. O padrão é 1e-8.
  //    maxiter : int, optional
  //        Máximo de iterações da função. O padrão é 100.
  //    Returns
  //    -------
  //        <raíz: Double>
  // Criamos uma lista com os valores encontrados
  Polinomio df = new Polinomio(this.coeficientesDf);
//  ArrayList<Double> trace = new ArrayList<>();
//  trace.add(x0);

  int numIter = 0;

  // Declaramos variáveis auxiliares
  Double x_i = x0;
  Double Fxi, passo, novo_x;

  do {
    Fxi = getPonto(x_i);
    passo = Fxi / df.getPonto(x_i);
    novo_x = x_i - passo;
//    trace.add(novo_x);
    x_i = novo_x;
  } while (Aritimetica.abs(passo) > precisao && Aritimetica.abs(Fxi) > ytol);

    // Checamos o numero de Iterações
    if (numIter > maxIter) {
      throw new Exception("Extrapolamos o máximo de iterações");
    }
    return novo_x;
  }
  public Double newton(Double x0) throws Exception {
    return newton(x0, 1e-8, 1e-8, 100);
  }
  
  public void plot(double a, double b, int numPontos) {
    // Variaveis auxiliares
    double intervaloA, intervaloB;
    intervaloA = a -1;
    intervaloB = b +1;

	  // Primeiro, criamos o array de pontos xs
	  double step = Aritimetica.abs(b - a) / numPontos;
	  double[] xs = new double[numPontos];
	  for (int i = 0; i < numPontos; i++) {
		  xs[i] = a + i * step;
	  }
	  // Depois, calculamos os valores de f(x) para cada x no array ys
	  double[] ys = new double[numPontos];
	  for (int i = 0; i < numPontos; i++) {
		  ys[i] = this.getPonto(xs[i]);
	  }



    // Plotamos os eixos x e y
    StdDraw.setXscale(intervaloA, intervaloB);
    StdDraw.setYscale(Aritimetica.min(xs) - MARGEM_PLOT , Aritimetica.max(ys) + MARGEM_PLOT); // do mínimo ao máximo

    StdDraw.setPenRadius(0.005);
    StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
    StdDraw.line(intervaloA, 0, intervaloB, 0);
    StdDraw.line(0, intervaloA, 0, intervaloB);

	  // Agora, plotamos os pontos
	  StdDraw.setPenRadius(0.01);
	  StdDraw.setPenColor(StdDraw.BLUE);
    for (int i = 0; i < numPontos; i++) {
      StdDraw.point(xs[i], ys[i]);
    }

  }

}
