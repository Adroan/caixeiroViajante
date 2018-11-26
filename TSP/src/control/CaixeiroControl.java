package control;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.CaixeiroViajante;
import view.Main;

public class CaixeiroControl extends JPanel implements ActionListener {

    private Image fundo;

    private ArrayList<Cidade> cidades;

    private boolean emExecucao;

    private static int km = 0;

    private int[][] coordenadas = {{404, 994}, {607, 991}, {792, 908},
    {926, 756}, {987, 562}, {964, 361}, {861, 186},
    {696, 68}, {497, 28}, {299, 72}, {136, 192}, {36, 369},
    {17, 571}, {81, 763}, {218, 913}};
    
    private int indiceInicial = 0;

    private boolean gerarSolucao = false;

    public CaixeiroControl() {
        setFocusable(false);

        ImageIcon referencia = new ImageIcon(Main.class.getResource("/res/branco.png"));
        fundo = referencia.getImage();

        emExecucao = true;
        inicializarCidades();

    }

    public void inicializarCidades() {
        CaixeiroViajante cv = new CaixeiroViajante();
        cidades = cv.criarCidades();
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        ImageIcon referencia = new ImageIcon(Main.class.getResource("/res/vertice.png"));
        CaixeiroViajante cv = new CaixeiroViajante();
        String[] nome = cv.getNomes();
        float dash1[] = {10.0f};
        float dash2[] = {0.0f};
        // Cria um padrão pontilhado para as linhas
        BasicStroke dashed = new BasicStroke(1.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f, dash1, 0.0f);
        
        if(!gerarSolucao){
              int indice = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número correspondente a cidade inicial entre 0 e 14: \n" + 
                                                                             "Digite -1 para sair"));
              indiceInicial = indice;
              gerarSolucao = true;
              if(indice == -1){
                  JOptionPane.showMessageDialog(this,"Obrigado e volte sempre!");
                  System.exit(0);
              }
            }
        if (emExecucao) {
            // Desenhar vértices
            for (int i = 0; i < cidades.size(); i++) {

                Cidade c = (Cidade) cidades.get(i);
                c.setImagem(referencia.getImage());
                graficos.drawImage(c.getImagem(), c.getX() - 10, c.getY() - 10, this);
                if (indiceInicial == i) {
                    graficos.setColor(Color.green);
                    graficos.drawString("" + i + " = " + nome[i], c.getX(), c.getY() - 20);
                } else {
                    graficos.setColor(Color.red);
                    graficos.drawString("" + i + " = " + nome[i], c.getX(), c.getY() - 20);
                }

            }

            // Desenhar arestas
            for (int i = 0; i < cidades.size(); i++) {
                Cidade origem = cidades.get(i);
                for (int j = 0; j < cidades.size(); j++) {
                    Cidade destino = cidades.get(j);
                    if ((origem.getX() == destino.getX()) && (origem.getY() == destino.getY())) {

                    } else {
                        graficos.setColor(Color.blue);
                        graficos.setStroke(dashed);
                        graficos.draw(new Line2D.Double(origem.getX(), origem.getY(), destino.getX(), destino.getY()));
                    }
                }
            }

            
            
            
            
            // Desenhar caminhos
            if (gerarSolucao) {
                Cidade cauda = cidades.get(indiceInicial);
                Cidade inicio = cidades.get(indiceInicial);
                Cidade atual = cidades.get(indiceInicial);
                indiceInicial = indiceInicial;
                inicio.foiVisitado = true;
                while (atual != null) {
                    ArrayList<Cidade> vizinhos = cv.acharVizinho(atual);
                    atual = cv.maisPerto(vizinhos, atual);
                    if (atual != null) {
                        atual.foiVisitado = true;
                        graficos.setStroke(new BasicStroke(2.5f));
                        graficos.setColor(Color.red);
                        JOptionPane.showMessageDialog(this, "Próxima cidade escolhida: " + atual.getNome() + 
                                                            "\nDistância percorrida: " + cv.getKm() + "km");
                        graficos.draw(new Line2D.Double(cauda.getX(), cauda.getY(), atual.getX(), atual.getY()));
                        cauda = atual;                   
                    }
                }
                graficos.draw(new Line2D.Double(cauda.getX(), cauda.getY(), inicio.getX(), inicio.getY()));
                km += cv.matrizAdjacentes[cauda.INDICE][inicio.INDICE];
                km += cv.getKm();
                graficos.drawString("Kilômetros percorridos: " + km, 5, 30);
                graficos.setColor(Color.green);
                graficos.drawString("tempo de execução: " + cv.getTempoExecucao() + "ms", 5, 45);
                JOptionPane.showMessageDialog(this, "Retornou para a primeira cidade: " + cauda.getNome() + 
                                                            "\nDistância percorrida: " + km + "km");
                
            }

            graficos.setColor(Color.blue);
            graficos.drawString("Cidades: " + cidades.size(), 5, 15);

        } else {
            ImageIcon fimJogo = new ImageIcon(Main.class.getResource("/res/game_over.jpg"));

            graficos.drawImage(fimJogo.getImage(), 0, 0, null);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void repintar(){
        repaint();
    }

    public void setIndiceInicial(int indiceInicial) {
        this.indiceInicial = indiceInicial;
    }

    public void setGerarSolucao(boolean gerarSolucao) {
        this.gerarSolucao = gerarSolucao;
    }
    
    
    

}
