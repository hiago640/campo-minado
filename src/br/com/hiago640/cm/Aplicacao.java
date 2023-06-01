package br.com.hiago640.cm;

import br.com.hiago640.cm.modelo.Tabuleiro;
import br.com.hiago640.cm.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {

		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 4);
		new TabuleiroConsole(tabuleiro);
	}

}
