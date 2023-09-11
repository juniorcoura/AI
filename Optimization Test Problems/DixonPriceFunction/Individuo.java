package DixonPriceFunction;

import java.util.List;

public interface Individuo<T> {

    public double avaliar();
    List<Individuo> recombinar(Individuo ind, int tipo);
    Individuo mutar(Individuo p);  
}
