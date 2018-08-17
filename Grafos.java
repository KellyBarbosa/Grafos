package grafos;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Grafos {

    static List<Vertice> vertices;
    static List<Aresta> arestas;

    public Grafos() {
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
    }

    public static void main(String[] args) {
        Grafos g = new Grafos();
        int valorVertice1, valorVertice2, valorA;
        Scanner input = new Scanner(System.in);
        while (true) {
            Vertice v;
            Aresta a;
            System.out.println("Informe um comando: ");
            String l = input.nextLine();
            l = l.toUpperCase();
            String r[] = l.split(" ");
            switch (r[0]) {
                case "CV":
                    System.out.println("CV - Cria vértice");
                    int valorV;
                    valorV = Integer.parseInt(r[1]);
                    v = g.insertVertex(valorV);

                    break;
                case "DV":
                    System.out.println("DV - Deleta vértice");

                    break;
                case "CA":
                    System.out.println("CA - Cria aresta");
                    valorVertice1 = Integer.parseInt(r[1]);
                    valorVertice2 = Integer.parseInt(r[2]);
                    valorA = Integer.parseInt(r[3]);

                    boolean verifica = procuraVertice(valorVertice1, valorVertice2);
                    if (verifica) {
                        Vertice v1, v2;
                        v1 = ref(valorVertice1);
                        v2 = ref(valorVertice2);
                        a = g.insertEdge(v1, v2, valorA);
                    }
                    break;
                case "DA":
                    System.out.println("DA - Deleta aresta");
                    break;
                case "TV":
                    System.out.println("TV - Troca valor do vértice");
                    valorVertice1 = Integer.parseInt(r[1]);
                    valorA = Integer.parseInt(r[2]);
                    replaceVertex(valorVertice1, valorA);
                    break;
                case "TA":
                    System.out.println("TA - Troca valor da aresta");
                    int valorAOriginal = Integer.parseInt(r[1]);
                    valorA = Integer.parseInt(r[2]);
                    replaceEdge(valorAOriginal, valorA);
                    break;
                case "IG":
                    System.out.println("IG - Imprime grafo");
                    System.out.println(g);
                    break;
                case "CM":
                    System.out.println("CM - Caminho minimo");
                    System.out.println(g);
                    break;
                case "FM":
                    System.out.println("FM - Termina a exeução");
                    System.exit(0);
                    break;
            }
        }

    }

    static public Vertice insertVertex(int x) {
        Vertice v = new Vertice(x);
        vertices.add(v);
        return v;
    }

    static public Aresta insertEdge(Vertice origem, Vertice destino, int x) {
        Aresta e = new Aresta(origem, destino, x);

        origem.addAdj(new Aresta(origem, destino, x));
        destino.addAdj(new Aresta(destino, origem, x));

        arestas.add(e);
        return e;
    }

    @Override
    public String toString() {
        String r = "";
        for (Vertice u : vertices) {
            r += u.num + " -> ";
            for (Aresta e : u.adj) {
                Vertice v = e.destino;
                r += v.num + "(VA :" + e.num + " ),";
            }
            r += "\n";
        }
        return r;
    }

    static public Vertice ref(int n) {
        Vertice v;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).num == n) {
                return v = vertices.get(i);
            }
        }
        return null;
    }

    static public boolean procuraVertice(int v1, int v2) {
        boolean achouV1, achouV2;
        achouV1 = achouV2 = false;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).num == v1) {
                achouV1 = true;
            }
            if (vertices.get(i).num == v2) {
                achouV2 = true;
            }
        }
        if (achouV1 && achouV2) {
            return true;
        }
        return false;
    }

    static void replaceVertex(int v, int o) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).num == v) {
                vertices.get(i).num = o;
                // System.out.println("Valor de vertice alterado!");
                return;
            }
        }
        // System.out.println("Vertice não encontrado!");
    }

    static void replaceEdge(int v, int o) {
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.get(i).adj.size(); j++) {
                if (vertices.get(i).adj.get(j).num == v) {
                    vertices.get(i).adj.get(j).num = o;
                    //        System.out.println("Valor de aresta alterado!");
                }
            }
        }
        //   System.out.println("Aresta não encontrada!");
    }
}
