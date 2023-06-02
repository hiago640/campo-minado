package br.com.hiago640.cm.swing.visao;

import br.com.hiago640.cm.swing.modelo.Tabuleiro;

public class Temp {

	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(3, 3, 9);
		tabuleiro.addObservers(e -> {
			System.out.println(e ? "Ganhou!" : "Perdeu!");
		});
		
		tabuleiro.alternarMarcacao(0, 0);
		tabuleiro.alternarMarcacao(0, 1);
		tabuleiro.alternarMarcacao(0, 2);
		tabuleiro.alternarMarcacao(1, 0);
		tabuleiro.alternarMarcacao(1, 1);
		tabuleiro.alternarMarcacao(1, 2);
		tabuleiro.alternarMarcacao(2, 0);
//		tabuleiro.alternarMarcacao(2, 1);
		tabuleiro.abrir(2, 2);
		
	}
}
