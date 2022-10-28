import org.jetbrains.annotations.NotNull;

public final class Aritimetica {
  public static Double abs (Double x) {
    if (x > 0) return x;
    return -x;
  }
  public static double min( double @NotNull [] a ) {
	double min = a[0];
	for (int i = 1; i < a.length; i++) {
	  if (a[i] < min) min = a[i];
	}
	return min;
  }
  public static double max( double @NotNull [] a ) {
	double max = a[0];
	for (int i = 1; i < a.length; i++) {
	  if (a[i] > max) max = a[i];
	}
	return max;
  }
}
