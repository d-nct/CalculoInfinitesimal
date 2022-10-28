import java.util.function.Consumer;

public abstract class Funcao {
  protected final Consumer<Float> funcao;
  public Funcao(Consumer<Float> funcao) {
    this.funcao = funcao;
  }
  public abstract void Plot(Float a, Float b);
}
