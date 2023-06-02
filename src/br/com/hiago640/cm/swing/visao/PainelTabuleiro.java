package br.com.hiago640.cm.swing.visao;

import java.awt.GridLayout;

import javax.swing.JPanel;

import br.com.hiago640.cm.swing.modelo.Tabuleiro;

public class PainelTabuleiro extends JPanel {

	private static final long serialVersionUID = -232935414021795466L;
	public PainelTabuleiro(Tabuleiro tabuleiro) {
		setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));
		
		tabuleiro.forEach(c -> add(new BotaoCampo(c)));
		
		tabuleiro.addObservers(e -> {
			
		});
	}


}
