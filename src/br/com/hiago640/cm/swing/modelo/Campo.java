package br.com.hiago640.cm.swing.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Campo {

	private final int linha;
	private final int coluna;

	private boolean aberto;
	private boolean minado;
	private boolean marcado;

	private List<Campo> vizinhos = new ArrayList<>();
	private Set<CampoObserver> observers = new HashSet<>();

	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	public void addObserver(CampoObserver observer) {
		observers.add(observer);
	}

	private void notificarObserver(CampoEventoEnum evento) {
		observers.forEach(o -> o.eventoOcorreu(this, evento));
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

	public void alternarMarcacao() {
		if (!aberto) {
			this.marcado = !marcado;

			notificarObserver(marcado ? CampoEventoEnum.MARCAR : CampoEventoEnum.DESMARCAR);

		}

	}

	void minar() {
		this.minado = true;
	}

	public boolean abrir() {

		if (!aberto && !marcado) {

			if (minado) {
				notificarObserver(CampoEventoEnum.EXPLODIR);
				return true;
			}

			setAberto(true);

			if (vizinhancaSegura())
				vizinhos.forEach(Campo::abrir);

			return true;
		} else {
			return false;
		}

	}

	public boolean vizinhancaSegura() {
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

	public void setAberto(boolean aberto) {
		this.aberto = aberto;

		if (aberto)
			notificarObserver(CampoEventoEnum.ABRIR);
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

	public int minasNaVizinhanca() {
		return (int) vizinhos.stream().filter(v -> v.minado).count();
	}

	void reiniciar() {
		this.aberto = false;
		this.minado = false;
		this.marcado = false;
		
		notificarObserver(CampoEventoEnum.REINICIAR);
	}

}
