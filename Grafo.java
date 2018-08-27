/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Grafo implements Cloneable{
    public ArrayList<Vertice> vertices;
    public ArrayList<Aresta> arestas;
    
    public Grafo() {
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
    }
    
    @Override
    public Grafo clone() throws CloneNotSupportedException {
        return (Grafo) super.clone();
    }
    
    public Vertice insertVertex(int x, int id) {
        Vertice v = new Vertice(x, id);
        vertices.add(v);
        return v;
    }
    
    public Aresta insertEdge(int idOrigem, int idDestino, int x, int id) {
        Vertice origem = refVertice(idOrigem), destino = refVertice(idDestino);

        Aresta e = new Aresta(origem, destino, x, id);

        origem.addAdj(new Aresta(origem, destino, x, id));
        destino.addAdj(new Aresta(destino, origem, x, id));

        arestas.add(e);
        return e;
    }
    
    public Object print() {
        /*System.out.println(vertices.size());
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println(vertices.get(i).id + " " + vertices.get(i).ValorVertice);
        }
        System.out.println(arestas.size());
        for (int i = 0; i < arestas.size(); i++) {
            System.out.println(arestas.get(i).id + " " + arestas.get(i).origem.id + " " + arestas.get(i).destino.id + " " + arestas.get(i).valorAresta);
        }
        */
        
        Object objeto = new Object[]{arestas, vertices};
        
        return objeto;
    }
    
    public Vertice refVertice(int id) {
        Vertice v;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).id == id) {
                return v = vertices.get(i);
            }
        }
        return null;
    }
    
    public Aresta refAresta(int id) {
        Aresta a;
        for (int i = 0; i < arestas.size(); i++) {
            if (arestas.get(i).id == id) {
                return a = arestas.get(i);
            }
        }
        return null;
    }
    
    public void replaceVertex(int id, int o) {
        vertices.get(id - 1).ValorVertice = o;
    }
    
    public void replaceEdge(int id, int o) {

        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.get(i).adj.size(); j++) {
                if (vertices.get(i).adj.get(j).id == id) {
                    vertices.get(i).adj.get(j).valorAresta = o;
                }
            }
        }
    }
    
    public Vertice[] endVertices(int id) {
        Aresta e = refAresta(id);
        Vertice[] retorno = {e.origem, e.destino};
        return retorno;
    }
    
    public int edgeValue(int id) {
        Aresta a = refAresta(id);
        return a.valorAresta;
    }
    
    public int vertexValue(int id) {
        Vertice v = refVertice(id);
        return v.ValorVertice;
    }
    
    public boolean areAdjacent(int v, int w) {
        for (Aresta aresta : arestas) {
            if ((aresta.origem.id == v && aresta.destino.id == w) || (aresta.origem.id == w && aresta.destino.id == v)) {
                return true;
            }
        }
        return false;
    }
    
    public Vertice opposite(int idV, int idA) {
        if (arestas.get(idA - 1).destino.id == idV) {
            return arestas.get(idA - 1).origem;
        } else if (arestas.get(idA - 1).origem.id == idV) {
            return arestas.get(idA - 1).destino;
        }
        return null;
    }
    
    public int removeEdge(int idA) {
        Aresta aresta = refAresta(idA);
        for (int i = 0; i < arestas.size(); i++) {
            if (arestas.get(i).id == idA) {
                arestas.remove(i);
            }
        }
        return aresta.valorAresta;
    }
    
    public int removeVertex(int idV) {
        Vertice vertice = refVertice(idV);
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).id == idV) {  
                for (int j = 0; j < vertices.get(i).adj.size()  ; j++) {
                    removeEdge(vertices.get(i).adj.get(j).id);
                }
                vertices.remove(i);
            }
        }
        return vertice.ValorVertice;
    }
    
    public ArrayList<Vertice> Dijkstra(Grafo G, int v1, int v2)
    {
        MenorCaminho menor = new MenorCaminho();
        ArrayList<Vertice> menorCaminho = menor.dijkstra(G, v1, v2);
        
        return menorCaminho;
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }
    
    public int custo(ArrayList<Vertice> menores)
    {
        MenorCaminho menor = new MenorCaminho();
        int valor = menor.custo(menores);
        
        return valor;
    }
}
