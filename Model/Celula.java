package Model;
public class Celula<T> {
    T item;
    Celula prox;

    public Celula(T item) {
        this.item = item;
    }
}
