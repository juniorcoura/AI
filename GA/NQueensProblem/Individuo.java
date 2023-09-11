import java.util.List;

public interface Individuo<T> {

    public int avaliar();
    List<Individuo> recombinar(Individuo ind);
    Individuo mutar();
   
}
