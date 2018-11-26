package control;

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

	private List<Cidade> cidades;

	private boolean emExecucao;

	private int[][] coordenadas = { { 404, 974 }, { 607, 971 }, { 792, 888 },
			{ 926, 736 }, { 987, 542 }, { 964, 341 }, { 861, 166 },
			{ 696, 48 }, { 497, 8 }, { 299, 52 }, { 136, 172 }, { 36, 349 },
			{ 17, 551 }, { 81, 743 }, { 218, 893 }};

	public CaixeiroControl() {
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon(Main.class.getResource("/res/fundo.png"));
		fundo = referencia.getImage();

		emExecucao = true;

		inicializarCidades();

		timer = new Timer(5, this);
		timer.start();
	}

	public void inicializarCidades() {
		cidades = new ArrayList<>();
                CaixeiroViajante cv = new CaixeiroViajante();
                Cidade cidade;
		for (int i = 0; i < coordenadas.length; i++) {
                    cidade = cv.cidades.get(i);
                    cidades.add(new Cidade(cidade.getINDICE(),cidade.getNome(),coordenadas[i][0], coordenadas[i][1]));
		}
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
                ImageIcon referencia = new ImageIcon(Main.class.getResource("/res/nave.png"));
		if (emExecucao) {
			for (int i = 0; i < cidades.size(); i++) {
				Cidade c = (Cidade) cidades.get(i);
                                c.setImagem(referencia.getImage());
				graficos.drawImage(c.getImagem(), c.getX(), c.getY(), this);
			}
                        // Desenhar arestas
			for (int i = 0; i < cidades.size(); i++) {
				Cidade origem = cidades.get(i);
                                for(int j = 0; j < cidades.size(); j++){
                                    Cidade destino = cidades.get(j);
                                    if((origem.getX() == destino.getX()) && (origem.getY() == destino.getY())){
                                        
                                    }else{
                                        graficos.draw(new Line2D.Double(origem.getX(), origem.getY(), destino.getX(), destino.getY()));
                                    }
                                }
                        }
                                
                        graficos.setColor(Color.black);
			graficos.drawString("Cidades: " + cidades.size(), 5, 15);     
                                
			}else {
			ImageIcon fimJogo = new ImageIcon(Main.class.getResource("/res/game_over.jpg"));

			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
