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
        Scanner input = new Scanner(System.in);

        while ((arestas.size()) < 200 && (vertices.size()) < 100) {
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
                    v = Grafos.insertVertex(valorV, vertices.size() + 1);
                    break;
                case "DV":
                    System.out.println("DV - Deleta vértice");

                    break;
                case "CA":
                    System.out.println("CA - Cria aresta");
                    a = Grafos.insertEdge(Integer.parseInt(r[1]), Integer.parseInt(r[2]), Integer.parseInt(r[3]), arestas.size() + 1);
                    break;
                case "DA":
                    System.out.println("DA - Deleta aresta");
                    break;
                case "TV":
                    System.out.println("TV - Troca valor do vértice");
                    replaceVertex(Integer.parseInt(r[1]), Integer.parseInt(r[2]));
                    break;
                case "TA":
                    System.out.println("TA - Troca valor da aresta");
                    replaceEdge(Integer.parseInt(r[1]), Integer.parseInt(r[2]));
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
                default:
                    System.out.println("O que deseja? 1 - EndVetices; 2 - EdgeValue; 3 - VertexValue; 4 - AreAdjacent; 5 - Opposite;");
                    int op = input.nextInt();
                    switch (op) {
                        case 1:
                            System.out.println("EndVertices");
                            Vertice[] x;
                            x = endVertices(Integer.parseInt(r[0]));
                            System.out.println("Os vertices da aresta " + refAresta(op).id + " são: Origem -> " + x[0].id + "; Destino -> " + x[1].id);
                            input.nextLine();
                            break;
                        case 2:
                            System.out.println("EdgeValue");
                            System.out.println("O valor da aresta de id " + Integer.parseInt(r[0]) + " é: " + edgeValue(Integer.parseInt(r[0])));
                            input.nextLine();
                            break;
                        case 3:
                            System.out.println("VertexValue");
                            System.out.println("O valor do vertice de id " + Integer.parseInt(r[0]) + " é: " + vertexValue(Integer.parseInt(r[0])));
                            input.nextLine();
                            break;
                        case 4:
                            System.out.println("AreAdjacent");
                            System.out.println("O vertice de id " + Integer.parseInt(r[0]) + " e o vertice de id " + Integer.parseInt(r[1]) + " são adjacentes? " + areAdjacent(Integer.parseInt(r[0]), Integer.parseInt(r[1])));
                            input.nextLine();
                            break;
                        case 5:
                            System.out.println("Opposite");
                            Vertice v1 = opposite(Integer.parseInt(r[0]), Integer.parseInt(r[1]));
                            System.out.println("O vertice de id " + Integer.parseInt(r[0]) + " com valor " + vertices.get(Integer.parseInt(r[0]) - 1).ValorVertice + " e o vertice de id " + v1.id + " com valor " + v1.ValorVertice + " são opostos e ligados pela aresta de id " + Integer.parseInt(r[1]) + " com valor " + arestas.get(Integer.parseInt(r[1]) - 1).valorAresta);
                            input.nextLine();
                            break;
                    }
            }
        }

    }

    static public Vertice insertVertex(int x, int id) {
        Vertice v = new Vertice(x, id);
        vertices.add(v);
        return v;
    }

    static public Aresta insertEdge(int idOrigem, int idDestino, int x, int id) {
        Vertice origem = refVertice(idOrigem), destino = refVertice(idDestino);

        Aresta e = new Aresta(origem, destino, x, id);

        origem.addAdj(new Aresta(origem, destino, x, id));
        destino.addAdj(new Aresta(destino, origem, x, id));

        arestas.add(e);
        return e;
    }

    @Override
    public String toString() {
        String r = "";
        for (Vertice u : vertices) {
            r += u.ValorVertice + " -> ";
            for (Aresta e : u.adj) {
                Vertice v = e.destino;
                r += v.ValorVertice + " IdVerDestino: " + v.id + " {ValorAres : " + e.valorAresta + ", IdAres: " + e.id + ", IdVerOrigem: " + e.origem.id + ", IdVerDestino: " + e.destino.id + "}; ";
            }
            r += "\n";
        }
        return r;
    }

    static public Vertice refVertice(int id) {
        Vertice v;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).id == id) {
                return v = vertices.get(i);
            }
        }
        return null;
    }

    static public Aresta refAresta(int id) {
        Aresta a;
        for (int i = 0; i < arestas.size(); i++) {
            if (arestas.get(i).id == id) {
                return a = arestas.get(i);
            }
        }
        return null;
    }

    static public void replaceVertex(int id, int o) {
        vertices.get(id-1).ValorVertice = o;   
    }

    static public void replaceEdge(int id, int o) {
        
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.get(i).adj.size(); j++) {
                if (vertices.get(i).adj.get(j).id == id) {
                    vertices.get(i).adj.get(j).valorAresta = o;
                }
            }
        }
        
    }

    static public Vertice[] endVertices(int id) {
        Aresta e = refAresta(id);
        Vertice[] retorno = {e.origem, e.destino};
        return retorno;
    }

    static public int edgeValue(int id) {
        Aresta a = refAresta(id);
        return a.valorAresta;
    }

    static public int vertexValue(int id) {
        Vertice v = refVertice(id);
        return v.ValorVertice;
    }

    static public boolean areAdjacent(int v, int w) {
        for (Aresta aresta : arestas) {
            if ((aresta.origem.id == v && aresta.destino.id == w) || (aresta.origem.id == w && aresta.destino.id == v)) {
                return true;
            }
        }
        return false;
    }

    static public Vertice opposite(int idV, int idA) {
        if (vertices.get(idV - 1).id == idV) {
            if (arestas.get(idA - 1).destino.id == idV) {
                return arestas.get(idA - 1).origem;
            } else if (arestas.get(idA - 1).origem.id == idV) {
                return arestas.get(idA - 1).destino;
            }
        }
        return null;
    }

}
