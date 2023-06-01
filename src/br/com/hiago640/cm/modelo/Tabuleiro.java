package br.com.hiago640.cm.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import br.com.hiago640.cm.excecao.ExplosaoException;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private int minas;

	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;

		gerarCampos();
		associarVizinhos();
		gerarMinas();
	}

	private void gerarCampos() {
		for (int i = 0; i < linhas; i++)
			for (int j = 0; j < colunas; j++)
				campos.add(new Campo(i, j));

	}

	private void associarVizinhos() {
		for (Campo c1 : campos)
			for (Campo c2 : campos)
				c1.adicionarVizinho(c2);

	}

	private void gerarMinas() {
		long minasArmadas = 0;

		do {
			int random = (new Random().nextInt(campos.size()));
			campos.get(random).minar();
			
			minasArmadas = campos.stream().filter(Campo::isMinado).count();
		} while (minasArmadas < minas);

	}

	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(Campo::objetivoAlcancado);
	}

	public void reiniciar() {
		campos.forEach(Campo::reiniciar);
		gerarMinas();
	}

	public void abrir(int linha, int coluna) {
		
		try {
			campos.parallelStream()
			.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
			.findFirst()
			.ifPresent(Campo::abrir);
		} catch (ExplosaoException e) {
			campos.forEach(c -> c.setAberto(true));
			throw e;
		}
	}
	
	public void alternarMarcacao(int linha, int coluna) {
		campos.parallelStream()
			.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
			.findFirst()
			.ifPresent(Campo::alternarMarcacao);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < colunas; i++) {
			if(i == 0) 
				sb.append("  ");
			sb.append("| " + i + " ");
			int delta = colunas - i;
			if(delta == 1)
				sb.append("|");
		}
		
		sb.append("\n");
		
		int i = 0;
		for (int linha = 0; linha < linhas; linha++) {
			sb.append(linha);
			sb.append("-");
			for (int coluna = 0; coluna < colunas; coluna++) {
				sb.append(String.format("| %s ", campos.get(i).toString()));
				i++;
				int delta = colunas - coluna;
				if(delta == 1)
					sb.append("|");
			}
			sb.append("\n");
		}

		return sb.toString();
	}

}
