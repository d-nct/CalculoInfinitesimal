import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolinomioTest {
  private Polinomio parabola, dParabola;
  private Polinomio funcaoEstranha, dFuncaoEstranha;
  public static final double DELTA = 1e-8;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    parabola = new Polinomio(new Double[]{1., 0., 0.});      // x^2 = 0
    dParabola = new Polinomio(parabola.getCoeficientesDf()); // 2*x = 0

    funcaoEstranha = new Polinomio(new Double[]{1., 0., 0., 0., -1., 1.}); // x^5 -x +1 = 0
    dFuncaoEstranha = new Polinomio(funcaoEstranha.getCoeficientesDf());   // 5*x^4 -1 = 0
  }

  @org.junit.jupiter.api.Test
  void getPonto() {
    assertEquals(0., parabola.getPonto(0.), DELTA);
    assertEquals(4., parabola.getPonto(2.), DELTA);
    assertEquals(4., parabola.getPonto(-2.), DELTA);
    assertEquals(10., parabola.getPonto(Math.sqrt(10)), DELTA);

    assertEquals(1., funcaoEstranha.getPonto(0.), DELTA);
    assertEquals(0.53125, funcaoEstranha.getPonto(0.5), DELTA);
  }

  @org.junit.jupiter.api.Test
  void testGetGrau() {
    assertEquals(2, parabola.getGrau());
    assertEquals(5, funcaoEstranha.getGrau());
  }

  @org.junit.jupiter.api.Test
  void testGetCoeficientesDfSimples() {
    Double[] obtidos = parabola.getCoeficientesDf();
    Double[] esperados = new Double[]{2., 0.};
    for (int i = 0; i < esperados.length; i++) {
      assertEquals(esperados[i], obtidos[i]);
    }
  }
  @org.junit.jupiter.api.Test
  void testGetCoeficientesDfComPolMaisComplicado() {
    Double[] obtidos = funcaoEstranha.getCoeficientesDf();
    Double[] esperados = new Double[]{5.,0.,0., 0., -1.};
    for (int i = 0; i < esperados.length; i++) {
      assertEquals(esperados[i], obtidos[i]);
    }
  }

  @org.junit.jupiter.api.Test
  void testNewton() throws Exception {
    assertEquals(0, parabola.newton(1., DELTA, 0., 100), DELTA);
//    assertEquals(Exception, funcaoEstranha.newton(1., DELTA, 0., 100), DELTA);
    // TODO Implementar teste. O correto é o método lançar uma exceção, pois a função não possui raízes reais. Logo, não deve convergir.
  }
}