package br.com.hiago640.cm.terminal;

import br.com.hiago640.cm.terminal.modelo.Tabuleiro;
import br.com.hiago640.cm.terminal.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {

		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 4);
		new TabuleiroConsole(tabuleiro);
	}

}
