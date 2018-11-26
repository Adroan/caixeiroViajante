package view;

import control.CaixeiroControl;
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
                construirCaixeiro();
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
		

		JMenuItem mostrarSolucao = new JMenuItem("Mostrar Solucao");
		mostrarSolucao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                            int indice = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número correspondente a cidade inicial entre 0 e 14: \n"));
                                  fazerBusca(indice); 
                                  
			}
		});
                
                JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		
		menu.add(mostrarSolucao);
                menu.add(new JSeparator());
                menu.add(sair);
		menuBar.add(menu);
		setJMenuBar(menuBar);

		return menuBar;
	}

	private JPanel construirCaixeiro() {
		CaixeiroControl cc = new CaixeiroControl();
		add(cc);
		return cc;
	}
        private JPanel fazerBusca(int indice){
                            CaixeiroControl cc = new CaixeiroControl();
                            cc.setGerarSolucao(true);
                            cc.setIndiceInicial(indice);
                            cc.repintar();
                            add(cc);
                            return cc;
        }

	private void configurarTela() {
		setSize(1080, 1200);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Caixeiro Viajante 45-EST");
	}
}
