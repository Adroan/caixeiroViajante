package control;

import java.awt.BasicStroke;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.Timer;
import model.CaixeiroViajante;
import view.Main;

public class CaixeiroControl extends JPanel implements ActionListener {


	private Image fundo;

	private Timer timer;

	private ArrayList<Cidade> cidades;

	private boolean emExecucao;

	private int[][] coordenadas = { { 404, 994 }, { 607, 991 }, { 792, 908 },
			{ 926, 756 }, { 987, 562 }, { 964, 361 }, { 861, 186 },
			{ 696, 68 }, { 497, 28 }, { 299, 72 }, { 136, 192 }, { 36, 369 },
			{ 17, 571 }, { 81, 763 }, { 218, 913 }};

	public CaixeiroControl() {
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon(Main.class.getResource("/res/branco.png"));
		fundo = referencia.getImage();

		emExecucao = true;
		inicializarCidades();

		timer = new Timer(5, this);
		timer.start();
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
                // Cria um padrão pontilhado para as linhas
                BasicStroke dashed = new BasicStroke(1.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
                if (emExecucao) {
			// Desenhar vértices
                        for (int i = 0; i < cidades.size(); i++) {
				
                                Cidade c = (Cidade) cidades.get(i);
                                c.setImagem(referencia.getImage());
				graficos.drawImage(c.getImagem(), c.getX()-10, c.getY()-10, this);
                                graficos.setColor(Color.red);
                                graficos.setStroke(dashed);
                                graficos.drawString("" + i +" = " + nome[i],c.getX(), c.getY() - 20);
			}
                        // Desenhar arestas
			for (int i = 0; i < cidades.size(); i++) {
				Cidade origem = cidades.get(i);
                                for(int j = 0; j < cidades.size(); j++){
                                    Cidade destino = cidades.get(j);
                                    if((origem.getX() == destino.getX()) && (origem.getY() == destino.getY())){
                                        
                                    }else{
                                        graficos.setColor(Color.blue);
                                        graficos.draw(new Line2D.Double(origem.getX(), origem.getY(), destino.getX(), destino.getY()));
                                    }
                                }
                        }
                                
                        graficos.setColor(Color.blue);
			graficos.drawString("Cidades: " + cidades.size(), 5, 15);     
                                
			}else {
			ImageIcon fimJogo = new ImageIcon(Main.class.getResource("/res/game_over.jpg"));

			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
       

}
