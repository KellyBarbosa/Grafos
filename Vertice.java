package grafos;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

    public int num;
    public List<Aresta> adj;

    public Vertice(int num) {
        this.num = num;
        this.adj = new ArrayList<Aresta>();
    }

    public Vertice() {
    }

    void addAdj(Aresta e) {
        adj.add(e);
    }
}
