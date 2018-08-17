package grafos;

public class Aresta {

    public Vertice origem;
    public Vertice destino;
    public int num;

    public Aresta() {
    }

    Aresta(Vertice origem, Vertice destino, int num) {
        this.origem = origem;
        this.destino = destino;
        this.num = num;
    }
}
