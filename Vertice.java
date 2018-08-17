package grafos;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

    public int ValorVertice;
    public List<Aresta> adj;
    public int id;

    public Vertice(int ValorVertice, int id) {
        this.ValorVertice = ValorVertice;
        this.id = id;
        this.adj = new ArrayList<>();
    }

    public Vertice() {
    }

    void addAdj(Aresta e) {
        adj.add(e);
    }
}
