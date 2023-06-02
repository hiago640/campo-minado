package br.com.hiago640.cm.swing.visao;

import javax.swing.JFrame;

import br.com.hiago640.cm.swing.modelo.Tabuleiro;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 257236987336424176L;

	public TelaPrincipal() {
		Tabuleiro tabuleiro = new Tabuleiro(10, 15, 10);
		
		add(new PainelTabuleiro(tabuleiro));
		
		setTitle("Campo Minado");
		setSize(690, 438);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setVisible(true);		
	}
	
	public static void main(String[] args) {
		new TelaPrincipal();
	}
}
