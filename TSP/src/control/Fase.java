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
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {


	private Image fundo;

	private Timer timer;

	private List<Cidade> cidades;

	private boolean emExecucao;

	private int[][] coordenadas = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 },
			{ 780, 109 }, { 580, 139 }, { 880, 239 }, { 790, 259 },
			{ 760, 50 }, { 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
			{ 930, 159 }, { 590, 80 }, { 530, 60 }};

	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter());

		ImageIcon referencia = new ImageIcon(Main.class.getResource("/res/fundo.png"));
		fundo = referencia.getImage();

		emExecucao = true;

		inicializarCidades();

		timer = new Timer(5, this);
		timer.start();
	}

	public void inicializarCidades() {
		cidades = new ArrayList<>();

		for (int i = 0; i < coordenadas.length; i++) {
			cidades.add(new Cidade(coordenadas[i][0], coordenadas[i][1]));
		}
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);

		if (emJogo) {
			graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);

			List<Missel> misseis = nave.getMisseis();

			for (int i = 0; i < misseis.size(); i++) {
				Missel m = (Missel) misseis.get(i);
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			for (int i = 0; i < cidades.size(); i++) {
				Cidade in = cidades.get(i);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}

			graficos.setColor(Color.white);
			graficos.drawString("Cidades: " + cidades.size(), 5, 15);

		} else {
			ImageIcon fimJogo = new ImageIcon(Main.class.getResource("/res/game_over.jpg"));

			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (cidades.size() == 0) {
			emJogo = false;
		}

		List<Missel> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {
			Missel m = (Missel) misseis.get(i);

			if (m.isVisible()) {
				m.mexer();
			} else {
				misseis.remove(i);
			}
		}

		for (int i = 0; i < cidades.size(); i++) {
			Cidade in = cidades.get(i);

			if (in.isVisible()) {
				in.mexer();
			} else {
				cidades.remove(i);
			}
		}

		nave.mexer();
		checarColisoes();
		repaint();
	}

	public void checarColisoes() {
		Rectangle formaNave = nave.getBounds();
		Rectangle formaCidade;
		Rectangle formaMissel;

		for (int i = 0; i < Cidades.size(); i++) {

			Cidade tempCidade = Cidades.get(i);
			formaCidade = tempCidade.getBounds();

			if (formaNave.intersects(formaCidade)) {
				nave.setVisivel(false);
				tempCidade.setVisible(false);
				emJogo = false;
			}
		}

		List<Missel> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {
			Missel tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			for (int j = 0; j < Cidades.size(); j++) {
				Cidade tempCidade = Cidades.get(j);
				formaCidade = tempCidade.getBounds();

				if (formaMissel.intersects(formaCidade)) {
					tempCidade.setVisible(false);
					tempMissel.setVisible(false);
				}
			}
		}
	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				emJogo = true;
				nave = new Nave();
				inicializarCidades();
			}

			nave.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e);
		}
	}
}
