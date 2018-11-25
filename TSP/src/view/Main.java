package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

public class Main extends JFrame {



	public Main() {
		construirMenuBar();
		construirFase();
		configurarTela();
	}

	public static void main(String[] args) {
		new Main();
	}

	private JMenuBar construirMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setBorder(new LineBorder(Color.red));
		JMenu menu = new JMenu("Caixeiro Viajante");
		JMenuItem criarGrafo = new JMenuItem("Criar grafo");
		criarGrafo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Ação do menu criar grafo
			}
		});

		JMenuItem mostrarSolucao = new JMenuItem("Mostrar Solucao");
		mostrarSolucao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Pintar a solucao
			}
		});
                
                JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menu.add(criarGrafo);
		menu.add(new JSeparator());
		menu.add(mostrarSolucao);
                menu.add(sair);
		menuBar.add(menu);
		setJMenuBar(menuBar);

		return menuBar;
	}

	private JPanel construirFase() {
		Fase fase = new Fase();
		add(fase);
		return fase;
	}

	private void configurarTela() {
		setSize(1000, 1000);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Caixeiro Viajante 45-EST");
	}
}