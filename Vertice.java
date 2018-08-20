package grafos;

import java.util.ArrayList;
import java.util.List;

public class Vertice implements Comparable<Vertice>{

    public int ValorVertice;
    public List<Aresta> adj;
    public int id;
    private int distancia;
    private Vertice parente;
    
    public Vertice(int ValorVertice, int id) {
        this.ValorVertice = ValorVertice;
        this.id = id;
        this.adj = new ArrayList<>();
    }

    public Vertice() {
    }
    
    public void setDistancia(int distancia)
    {
        this.distancia = distancia;
    }
    
    public int getDistancia()
    {
        return this.distancia;
    }
    
    public void addAdj(Aresta e) {
        adj.add(e);
    }
    
    public void setParente(Vertice v)
    {
        this.parente = v;
    }
    
    public Vertice getParente()
    {
        return this.parente;
    }
    
    public int compareTo(Vertice vertice) 
    {
        
        if(this.getDistancia() < vertice.getDistancia())
        {
            return -1;
        }
        
        else if(this.getDistancia() == vertice.getDistancia()) 
        {
            return 0;
        }
          
        return 1;
          
    }
}
