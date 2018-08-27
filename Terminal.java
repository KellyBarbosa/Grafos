/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

/**
 *
 * @author lucas
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author agostinhoneto
 */
public class Terminal extends javax.swing.JFrame {

    char letra;
    String cont = "";
    int tam;
    Grafo G = new Grafo();
    int contVertices = 0;
    int contArestas = 0;

    /**
     * Creates new form Terminal
     */
    public Terminal() {
        initComponents();
        //Coloco o menu inicial
        area.setText("Digite um comando!\n");
        area.setEnabled(false);
        Thread t1 = new Thread(new MyClass());
        t1.start();

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent k) {

                if (!((G.arestas.size()) < 200 && (G.vertices.size()) < 100)) {
                    System.exit(0);
                }

                letra = k.getKeyChar();
                int cod = k.getKeyCode();

                //Toda vez que der enter rodo o que tem escrito e limpo a tela
                if (letra == '\n') {
                    cont = cont.toLowerCase();
                    System.out.println(cont);
                    //Testo o conteudo
                    char[] ori = cont.toCharArray();
                    tam = cont.length();

                    char tipo[] = new char[2];
                    char val[] = new char[1];
                    //Pego o tipo
                    tipo[0] = ori[0];
                    tipo[1] = ori[1];

                    String u = new String(tipo);

                    //Testes
                    switch (u) {
                        case "ca":
                            try {
                                String r[] = cont.split(" ");

                                area.setText("");

                                //Chama a funçao
                                G.insertEdge(Integer.parseInt(r[1]), Integer.parseInt(r[2]), Integer.parseInt(r[3]), ++contArestas);
                                area.setText("Digite um comando!\n");
                            } catch (Exception e) {
                                area.setText("Comando inválido!\n");
                            }
                            break;

                        case "cv":
                            try {
                                String r[] = cont.split(" ");

                                area.setText("");

                                //Chama a funçao
                                G.insertVertex(Integer.parseInt(r[1]), ++contVertices);
                                area.setText("Digite um comando!\n");
                            } catch (Exception e) {
                                area.setText("Comando inválido!\n");
                            }
                            break;

                        case "dv":
                            try {
                                String r[] = cont.split(" ");

                                area.setText("");

                                //Chama a funçao
                                G.removeVertex(Integer.parseInt(r[1]));
                                area.setText("Digite um comando!\n");
                            } catch (Exception e) {
                                area.setText("Comando inválido!\n");
                            }
                            break;

                        case "da":
                            try {
                                String r[] = cont.split(" ");

                                area.setText("");

                                //Chama a funçao
                                G.removeEdge(Integer.parseInt(r[1]));
                                area.setText("Digite um comando!\n");
                            } catch (Exception e) {
                                area.setText("Comando inválido!\n");
                            }
                            break;

                        case "tv":

                            try {
                                String r[] = cont.split(" ");

                                area.setText("");

                                //Chama a funçao
                                G.replaceVertex(Integer.parseInt(r[1]), Integer.parseInt(r[2]));
                                area.setText("Digite um comando!\n");
                            } catch (Exception e) {
                                area.setText("Comando inválido!\n");
                            }
                            break;

                        case "ta":
                            try {
                                String r[] = cont.split(" ");

                                area.setText("");

                                //Chama a funçao
                                G.replaceEdge(Integer.parseInt(r[1]), Integer.parseInt(r[2]));
                                area.setText("Digite um comando!\n");
                            } catch (Exception e) {
                                area.setText("Comando inválido!\n");
                            }
                            break;

                        case "ig":
                            ArrayList<Vertice> vertice = new ArrayList();
                            vertice = G.getVertices();

                            ArrayList<Aresta> arestas = new ArrayList();
                            arestas = G.getArestas();

                            System.out.println(vertice.size());
                            for (int i = 0; i < vertice.size(); i++) {
                                System.out.println(vertice.get(i).id + " " + vertice.get(i).ValorVertice);
                            }
                            System.out.println(arestas.size());
                            for (int i = 0; i < arestas.size(); i++) {
                                System.out.println(arestas.get(i).id + " " + arestas.get(i).origem.id + " " + arestas.get(i).destino.id + " " + arestas.get(i).valorAresta);
                            }

                            break;

                        case "cm":
                            try {
                                String r[] = cont.split(" ");

                                area.setText("");
                                ArrayList<Vertice> menorCaminho = G.Dijkstra(G, Integer.parseInt(r[1]), Integer.parseInt(r[2]));
                                System.out.println(G.custo(menorCaminho));

                                menorCaminho.forEach((ver) -> {
                                    System.out.print(ver.id + " ");
                                });
                                ArrayList<String> tes = new ArrayList<>();
                                for(Vertice v : menorCaminho)
                                {
                                    tes.add(v.id + " ");
                                }
                                area.setText(G.custo(menorCaminho)+"\n"+tes.toString()+"\n");
                            } catch (Exception e) {
                                area.setText("Comando inválido!\n");
                            }
                            break;

                        case "fm":
                            System.exit(0);
                    }

                    //Zero a string
                    cont = "";

                } else {

                    if (cod == KeyEvent.VK_BACK_SPACE) {
                        char[] ori = cont.toCharArray();
                        tam = cont.length();

                        char vetor[] = new char[tam - 1];

                        for (int d = 0; d < tam - 1; d++) {
                            vetor[d] = ori[d];
                        }

                        cont = new String(vetor);
                        area.setText(cont);
                    } else {
                        cont += letra;
                        area.setText(cont);
                    }

                }

            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        area.setColumns(20);
        area.setRows(5);
        jScrollPane1.setViewportView(area);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Terminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Terminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Terminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Terminal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Terminal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public class MyClass implements Runnable {

        public void run() {

        }
    }

}
