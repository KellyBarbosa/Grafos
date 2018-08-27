/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class Main {

    public static void main(String[] Args) {
        Grafo G = new Grafo();
        Scanner input = new Scanner(System.in);
        int contVertices = 0;
        int contArestas = 0;

        while (true) {

            if (!((G.arestas.size()) < 200 && (G.vertices.size()) < 100)) {
                break;
            }

            System.out.println("Informe um comando: ");
            String l = input.nextLine();
            l = l.toUpperCase();
            String r[] = l.split(" ");

            switch (r[0]) {
                case "CV":
                    G.insertVertex(Integer.parseInt(r[1]), ++contVertices);
                    break;
                case "DV":
                    G.removeVertex(Integer.parseInt(r[1]));
                    break;
                case "CA":
                    G.insertEdge(Integer.parseInt(r[1]), Integer.parseInt(r[2]), Integer.parseInt(r[3]), ++contArestas);
                    break;
                case "DA":
                    G.removeEdge(Integer.parseInt(r[1]));
                    break;
                case "TV":
                    G.replaceVertex(Integer.parseInt(r[1]), Integer.parseInt(r[2]));
                    break;
                case "TA":
                    G.replaceEdge(Integer.parseInt(r[1]), Integer.parseInt(r[2]));
                    break;
                case "IG":
                    G.print();
                    break;
                case "CM":
                    ArrayList<Vertice> menorCaminho = new ArrayList<>();
                        try {
                            menorCaminho = G.Dijkstra(G.clone(), Integer.parseInt(r[1]), Integer.parseInt(r[2]));
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    System.out.println(G.custo(menorCaminho));

                    menorCaminho.forEach((ver) -> {
                        System.out.print(ver.id + " ");
                    });
                    System.out.println();
                    break;
                case "FM":
                    System.out.println("FM - Termina a exeução");
                    System.exit(0);
                    break;
                   
            }
        }
    }
}
