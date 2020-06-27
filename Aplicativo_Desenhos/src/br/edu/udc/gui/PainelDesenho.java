package br.edu.udc.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Iterator;

import br.edu.udc.AplicacaoDesenho;
import br.edu.udc.formas.FormaGeometrica;
import br.edu.udc.formas.Ponto;

public class PainelDesenho extends JPanel implements PainelFormaGeometrica, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private JLabel status;
	
	private FormaGeometrica formaAtual;
	
	private Map<String, Ponto> mapaPosMouse;
	private Map<String, FormaGeometrica> mapaFormas;
	
	public PainelDesenho(JLabel status){
		this.status = status;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		formaAtual = null;
		mapaPosMouse = new HashMap<String, Ponto>();
	}
	
	public void formaAtual(FormaGeometrica forma) {
		formaAtual = forma;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (formaAtual != null) {
			formaAtual.getManipulador().desenhar(g);
		}
		Set<String> setCodes = mapaPosMouse.keySet();
		Iterator<String> iterator = setCodes.iterator();
		while (iterator.hasNext()) {
			String code = iterator.next();
			Ponto pos = mapaPosMouse.get(code);

			Color color = g.getColor();
			g.setColor(new Color(255, 0, 0));
			g.drawOval(pos.getX() - 5, pos.getY() - 5, 10, 10);
			g.fillOval(pos.getX() - 2, pos.getY() - 2, 4, 4);
			g.setColor(color);
		}

		Iterator<FormaGeometrica> it = AplicacaoDesenho.getAplicacao().getDocumento().getIterador();
		while (it.hasNext()) {
			it.next().getManipulador().desenhar(g);
		}

	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (formaAtual != null) {
			formaAtual.getManipulador().arrastar(e.getX(), e.getY());
			repaint();
		}
		String msg = String.format("Mouse Arrastado em (%d; %d)", e.getX(), e.getY());
		if (formaAtual != null)
			msg = msg + "- desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (formaAtual != null) {
			formaAtual.getManipulador().mover(e.getX(), e.getY());
			repaint();
		}

		if (AplicacaoDesenho.getAplicacao().isServerRunning()) {
			AplicacaoDesenho.getAplicacao().getActiveServer()
					.sendData("MOUSE " + String.format("(%d; %d)", e.getX(), e.getY()));
		} else if (AplicacaoDesenho.getAplicacao().isClientRunning()) {
			AplicacaoDesenho.getAplicacao().getActiveClient().sendData("MOUSE " + String.format("(%d; %d)", e.getX(), e.getY()));
		}

		String msg = String.format("Mouse movimentado em (%d; %d)", e.getX(), e.getY());
		if (formaAtual != null)
			msg = msg + "- desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (formaAtual != null) {
			if (formaAtual.getManipulador().clicar(e.getX(), e.getY())) {
				AplicacaoDesenho.getAplicacao().getDocumento().novaForma(formaAtual);
				formaAtual = formaAtual.clone();
			}
			repaint();
		}
		String msg = String.format("Mouse apertado em (%d; %d)", e.getX(), e.getY());
		if (formaAtual != null)
			msg = msg + "- desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		String msg = String.format("Mouse entrou em (%d; %d)", e.getX(), e.getY());
		if (formaAtual != null)
			msg = msg + "- desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		String msg = String.format("Mouse saiu em (%d; %d)", e.getX(), e.getY());
		if (formaAtual != null)
			msg = msg + "- desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (formaAtual != null) {
			formaAtual.getManipulador().apertar(e.getX(), e.getY());

			repaint();
		}
		String msg = String.format("Mouse presionado em (%d; %d)", e.getX(), e.getY());
		if (formaAtual != null)
			msg = msg + "- desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (formaAtual != null) {
			if (formaAtual.getManipulador().soltar(e.getX(), e.getY())) {
				AplicacaoDesenho.getAplicacao().getDocumento().novaForma(formaAtual);

				formaAtual = formaAtual.clone();
			}
			repaint();
		}
		String msg = String.format("Mouse solto em (%d; %d)", e.getX(), e.getY());
		if (formaAtual != null)
			msg = msg + "- desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);

	}

	@Override
	public void atualizar() {
		repaint();
	}
	
	public void novaFormaGeometrica(FormaGeometrica forma) {
		formaAtual = forma;
	}
	
	public void atualizaPosMouse(Ponto p, String nome) {
		if (mapaPosMouse.containsKey(nome)) {
			mapaPosMouse.replace(nome, p);
		} else {
			mapaPosMouse.put(nome, p);
		}

	}

	public void atualizaForma(FormaGeometrica f, String nome) {
		if(mapaFormas.containsKey(nome)) {
			mapaFormas.replace(nome, f);
		}else {
			mapaFormas.put(nome, f);
		}
		repaint();
	}
}

