package br.com.hiago640.cm.swing.visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import br.com.hiago640.cm.swing.modelo.Campo;
import br.com.hiago640.cm.swing.modelo.CampoEventoEnum;
import br.com.hiago640.cm.swing.modelo.CampoObserver;

public class BotaoCampo extends JButton implements CampoObserver, MouseListener {

	private final Color BG_DEFAULT = new Color(184, 184, 184);
	private final Color BG_MARCADO = new Color(8, 179, 247);
	private final Color BG_EXPLOSAO = new Color(189, 66, 68);
	private final Color TEXTO = new Color(0, 100, 0);
	private static final long serialVersionUID = 7488518117743358621L;

	private Campo campo;

	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBackground(BG_DEFAULT);
		setOpaque(true);
		
		setBorder(BorderFactory.createBevelBorder(0));

		addMouseListener(this);
		campo.addObserver(this);
	}

	@Override
	public void eventoOcorreu(Campo campo, CampoEventoEnum event) {
		switch (event) {
		case ABRIR: {
			aplicarEstiloAbrir();
			break;
		}
		case MARCAR: {
			aplicarEstiloMarcar();
			break;
		}
		case EXPLODIR: {
			aplicarEstiloExplodir();
			break;
		}
		default:
			System.out.println(event);
			aplicarEstiloDefault();
		}
		
		SwingUtilities.invokeLater(() -> {
			repaint();
			validate();
		});
	}

	private void aplicarEstiloDefault() {
		setBackground(BG_DEFAULT);
		setBorder(BorderFactory.createBevelBorder(0));
		setText("");
	}

	private void aplicarEstiloExplodir() {
		setBackground(BG_EXPLOSAO);
		setForeground(Color.WHITE);
		setText("X");
	}

	private void aplicarEstiloMarcar() {
		setBackground(BG_MARCADO);
		setForeground(Color.BLACK);
		setText("M");

	}

	private void aplicarEstiloAbrir() {
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY));	

		if(campo.isMinado()) {
			setBackground(BG_EXPLOSAO);
			return;
		}
		
		setBackground(BG_DEFAULT);
		
		switch (campo.minasNaVizinhanca()) {
		case 1: {
			setForeground(TEXTO);
			break;
		}
		case 2: {
			setForeground(Color.BLUE);
			break;
		}
		case 3: {
			setForeground(Color.YELLOW);
			break;
		}
		case 4, 5, 6: {
			setForeground(Color.RED);
			break;
		}
		default:
			setForeground(Color.PINK);
		}
		
		String valor = !campo.vizinhancaSegura() ? campo.minasNaVizinhanca() + "" : "";
		setText(valor);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) {
			campo.abrir();		
		}
		else {
			campo.alternarMarcacao();
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
