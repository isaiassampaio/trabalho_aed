public class ListaIdentificadores<T> {
    private Celula<T> head;
    private Celula<T> tail;
    private long size;

    public ListaIdentificadores() {
        head = new Celula<T>(null);
        tail = head;
    }

    public long getSize() {
        return size;
    }

    // método para 
    public T get(int posicao) {
        if (posicao >= 0 && posicao < size) {
            Celula<T> atual = head.prox;
            int cont = 0;

            while (cont < posicao) {
                atual = atual.prox;
                cont++;
            }

            return atual.item;
        } else {
            throw new IndexOutOfBoundsException(posicao);
        }
    }

    public void add(long posicao, T item) {
        if (item != null) {
            if (posicao >= 0 && posicao <= size) {
                Celula<T> atual = head.prox;
                Celula<T> ant = head;
                long cont = 0;
                while (cont < posicao) {
                    ant = atual;
                    atual = atual.prox;
                    cont++;
                }
                ant.prox = new Celula<T>(item);
                ant.prox.prox = atual;
                if (tail == ant)
                    tail = tail.prox;
                size++;
            } else {
                throw new IndexOutOfBoundsException(posicao);
            }
        }
    }

    public T remove(int posicao) {
        if (posicao >= 0 && posicao < size) {
            Celula<T> atual = head.prox;
            Celula<T> ant = head;
            long cont = 0;
            while (cont < posicao) {
                ant = atual;
                atual = atual.prox;
                cont++;
            }
            ant.prox = atual.prox;
            if (atual == tail)
                tail = ant;
            size--;
            return atual.item;
        } else {
            throw new IndexOutOfBoundsException(posicao);
        }
    }
}