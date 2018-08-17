package grafos;

public class Aresta {

    public Vertice origem;
    public Vertice destino;
    public int valorAresta;
    int id;

    public Aresta() {
    }

    Aresta(Vertice origem, Vertice destino, int valorAresta, int id) {
        this.origem = origem;
        this.destino = destino;
        this.valorAresta = valorAresta;
        this.id = id;
    }
}
