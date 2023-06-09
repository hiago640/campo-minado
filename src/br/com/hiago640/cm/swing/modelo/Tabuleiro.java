package br.com.hiago640.cm.swing.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class Tabuleiro implements CampoObserver {

	private final int linhas;
	private final int colunas;
	private final int minas;

	private final List<Campo> campos = new ArrayList<>();
	private final List<Consumer<Boolean>> observers = new ArrayList<>();

	public void forEach(Consumer<Campo> consumer) {
		campos.forEach(consumer);
	}
	
	public void addObservers(Consumer<Boolean> observer) {
		observers.add(observer);
	}

	private void notificarObserver(PartidaEventoEnum event) {
		observers.forEach(o -> o.accept(event.getValue()));
	}

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;

		gerarCampos();
		associarVizinhos();
		gerarMinas();
	}

	private void gerarCampos() {
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				Campo campo = new Campo(i, j);
				campo.addObserver(this);
				campos.add(campo);
			}

		}

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
		campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
				.ifPresent(Campo::abrir);
	}

	private void mostrarMinas() {
		campos.stream().filter(Campo::isMinado).filter(c -> !c.isMarcado()).forEach(c -> c.setAberto(true));

	}

	public void alternarMarcacao(int linha, int coluna) {
		campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
				.ifPresent(Campo::alternarMarcacao);
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public int getMinas() {
		return minas;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < colunas; i++) {
			if (i == 0)
				sb.append("  ");
			sb.append("| " + i + " ");
			int delta = colunas - i;
			if (delta == 1)
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
				if (delta == 1)
					sb.append("|");
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	@Override
	public void eventoOcorreu(Campo campo, CampoEventoEnum event) {

		if (event.equals(CampoEventoEnum.EXPLODIR)) {
			mostrarMinas();
			notificarObserver(PartidaEventoEnum.DERROTA);
		} else if (objetivoAlcancado()) {
			notificarObserver(PartidaEventoEnum.VITORIA);
		}

	}

}
