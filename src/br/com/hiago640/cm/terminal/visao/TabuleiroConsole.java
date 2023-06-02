package br.com.hiago640.cm.terminal.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.hiago640.cm.terminal.excecao.ExplosaoException;
import br.com.hiago640.cm.terminal.excecao.SairException;
import br.com.hiago640.cm.terminal.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;

		executarJogo();
	}

	private void executarJogo() {

		try {
			cicloDoJogo();

			boolean continuar = true;

			while (continuar) {
				System.out.println("Outra partida? (S/n)");
				String resposta = entrada.nextLine();

				if ("n".equalsIgnoreCase(resposta))
					continuar = false;
				else
					tabuleiro.reiniciar();
			}

		} catch (SairException e) {
			System.out.println("Tchau!!");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {

		try {

			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);

				String input = capturarInput("Digite (x, y): ");
				Iterator<Integer> xy = Arrays.stream(input.trim().split(",")).map(Integer::parseInt).iterator();
				
				int linha = xy.next();
				int coluna = xy.next();

				input = capturarInput("1 - Abrir ou 2 - (Des)Marcar: ");
				if ("1".equals(input))
					tabuleiro.abrir(linha, coluna);
				else if ("2".equals(input))
					tabuleiro.alternarMarcacao(linha, coluna);
			}

			System.out.println("Você ganhou!!!");
		} catch (ExplosaoException e) {
			System.out.println("Você perdeu!");
			System.out.println("\nVisão do Tabuleiro: ");
			System.out.println(tabuleiro);
		}
	}

	private String capturarInput(String mensagem) {
		System.out.print(mensagem);

		String resposta = entrada.nextLine();
		if ("sair".equalsIgnoreCase(resposta))
			throw new SairException();

		return resposta;
	}

}
