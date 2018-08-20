/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author lucas
 */
public class MenorCaminho {
    
    private ArrayList<Vertice> naoVizitados;
    private ArrayList<Vertice> vizitados;
    private ArrayList<Vertice> menorCaminho;
    private Vertice caminho;
    private Vertice atual;
    private Vertice adijacente;
    private int infinito = 1000000000;
    
    public MenorCaminho()
    {
         this.naoVizitados = new ArrayList<>();
         this.vizitados = new ArrayList<>();
         this.menorCaminho = new ArrayList<>();
         this.caminho = new Vertice();
         this.atual = new Vertice();
         this.adijacente = new Vertice();
    }
    
    public int custo(ArrayList<Vertice> caminhoMinimo)
    {
        int valor = 0;
        for(Vertice v : caminhoMinimo)
        {
            valor += v.getDistancia();
        }
        
        return valor;
    }
    
    public ArrayList<Vertice> dijkstra(Grafos G, int o, int d)
    {
        Vertice origem = G.vertices.get(o - 1);
        Vertice destino = G.vertices.get(d - 1);
        
        this.menorCaminho.add(origem);
        
        for(int i = 0; i < G.vertices.size(); i++)
        {
            if(G.vertices.get(i) == origem){
                G.vertices.get(i).setDistancia(0);
            }
            else
            {
                G.vertices.get(i).setDistancia(infinito);
            }
            
            this.naoVizitados.add(G.vertices.get(i));
        }

        Collections.sort(this.naoVizitados);
        
        while(!this.naoVizitados.isEmpty())
        {
            this.atual = this.naoVizitados.get(0);
            for(int i = 0; i < this.atual.adj.size(); i++)
            {
                this.adijacente = this.atual.adj.get(i).destino;
                int pesoCaminho = this.atual.getDistancia() + this.atual.adj.get(i).valorAresta;
                
                if(this.adijacente.getDistancia() > pesoCaminho)
                {
                    this.adijacente.setDistancia(pesoCaminho);
                    this.adijacente.setParente(this.atual);
                    
                    if(this.adijacente == destino)
                    {
                        this.menorCaminho.clear();
                        this.caminho = adijacente;
                        this.menorCaminho.add(adijacente);
                        
                        while(caminho.getParente() != null)
                        {
                            menorCaminho.add(caminho.getParente());
                            caminho = caminho.getParente();
                        }
                        
                        Collections.sort(this.menorCaminho);
                    }
                }
            }
            
            this.vizitados.add(atual);
            this.naoVizitados.remove(atual);
            Collections.sort(this.naoVizitados);
        }
        
        return this.menorCaminho;
    }
}
