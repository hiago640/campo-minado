package br.com.hiago640.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.hiago640.cm.excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;

	private boolean aberto;
	private boolean minado;
	private boolean marcado;

	private List<Campo> vizinhos = new ArrayList<>();

	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = this.linha != vizinho.linha;
		boolean colunaDiferente = this.coluna != vizinho.coluna;

		boolean isDiagonal = linhaDiferente && colunaDiferente;

		int deltaLinha = Math.abs(this.linha - vizinho.linha);
		int deltaColuna = Math.abs(this.coluna - vizinho.coluna);

		int deltaGeral = deltaLinha + deltaColuna;

		if ((deltaGeral == 1 && !isDiagonal) || (deltaGeral == 2 && isDiagonal)) {
			this.vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
	}

	void alternarMarcacao() {
		if (!aberto)
			this.marcado = !marcado;

	}

	void minar() {
		this.minado = true;
	}

	boolean abrir() {

		if (!aberto && !marcado) {
			aberto = true;

			if (minado)
				throw new ExplosaoException();

			if (vizinhancaSegura())
				vizinhos.forEach(Campo::abrir);

			return true;
		} else {
			return false;
		}

	}

	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

	public boolean isMarcado() {
		return marcado;
	}

	public boolean isMinado() {
		return minado;
	}

	public boolean isAberto() {
		return aberto;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	boolean objetivoAlcancado() {

		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;

		return (desvendado || protegido);
	}

	long minasNaVizinhanca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}

	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}

	@Override
	public String toString() {

		if (marcado)
			return "x";
		else if (aberto && minado)
			return "*";
		else if (aberto && minasNaVizinhanca() > 0)
			return Long.toString(minasNaVizinhanca());
		else if (aberto)
			return " ";
		else
			return "?";
	}

	void setAberto(boolean aberto) {
		this.aberto = aberto;
	}
	
	

}
