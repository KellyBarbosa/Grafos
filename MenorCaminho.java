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
    private int menorDistancia = 0;
    
    public MenorCaminho()
    {
         this.naoVizitados = new ArrayList<>();
         this.vizitados = new ArrayList<>();
         this.menorCaminho = new ArrayList<>();
         this.caminho = new Vertice();
         this.atual = new Vertice();
         this.adijacente = new Vertice();
    }
    
    public int custo(ArrayList<Vertice> menores)
    {
        Vertice ultimo = menores.get(menores.size() -1);
        return ultimo.getDistancia();
    }
    
    public ArrayList<Vertice> dijkstra(Grafo G, int o, int d)
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
                
                if(!this.vizitados.contains(this.adijacente))
                {
                    if(this.adijacente.getDistancia() > pesoCaminho)
                    {
                        this.adijacente.setDistancia(pesoCaminho);
                        this.adijacente.setParente(this.atual);

                        if(this.adijacente == destino)
                        {
                            this.menorCaminho.clear();
                            this.menorDistancia = 0;
                            
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
            }
            
            this.vizitados.add(atual);
            this.naoVizitados.remove(atual);
            Collections.sort(this.naoVizitados);
        }
        
        //Vertice ultimo = this.menorCaminho.get(this.menorCaminho.size() -1);
        //this.menorDistancia = ultimo.getDistancia();
        return this.menorCaminho;
    }
}
