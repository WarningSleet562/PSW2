package br.edu.udc.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Iterator;

import br.edu.udc.AplicacaoDesenho;
import br.edu.udc.formas.FormaGeometrica;

public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private JLabel status;
	
	private FormaGeometrica formaAtual;
	
	public PainelDesenho(JLabel status){
		this.status = status;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		formaAtual = null;
	}
	
	public void formaAtual (FormaGeometrica forma) {
		formaAtual = forma;
	}
	
	@Override
	public void paint (Graphics g) {
		super.paint(g);
		
		if (formaAtual != null)
			formaAtual.getManipulador().desenhar(g);
		
		Iterator<FormaGeometrica> it = AplicacaoDesenho.getAplicacao().getDocumento().getIterador();
		while(it.hasNext()) {
			it.next().getManipulador().desenhar(g);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (formaAtual != null){
			formaAtual.getManipulador().arrastar(e.getX(), e.getY());
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (formaAtual != null){
			formaAtual.getManipulador().mover(e.getX(), e.getY());
			repaint();
	}
		String message = String.format("Mouse movimentado em [%d; %d]", e.getX(), e.getY());
		if (formaAtual != null)
			message = message + " desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(message);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (formaAtual != null) {
			if(formaAtual.getManipulador().clicar(e.getX(), e.getY())) {
			AplicacaoDesenho.getAplicacao().getDocumento().novaForma(formaAtual);
			formaAtual = formaAtual.clone();
		}
	}
		String message = String.format("Botão apertado em [%d; %d]", e.getX(), e.getY());
		if (formaAtual != null)
			message = message + " - desenhando " + formaAtual.getNome() + " em" + formaAtual;
		status.setText(message);		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		String message = String.format("Mouse entrou no painel em [%d; %d]", e.getX(), e.getY());
		if (formaAtual != null)	
			message = message + " - desenhando " + formaAtual.getNome() + " em" + formaAtual;
		status.setText(message);		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		String message = String.format("Mouse saiu do painel em [%d; %d]", e.getX(), e.getY());
		if (formaAtual != null)
			message = message + " - desenhando " + formaAtual.getNome() + " em" + formaAtual;
		status.setText(message);		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		String message = String.format("Botão pressionado em [%d; %d]", e.getX(), e.getY());
		if (formaAtual != null){
			formaAtual.getManipulador().apertar(e.getX(), e.getY());
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		String message = String.format("Botão solto em [%d; %d]", e.getX(), e.getY());
		if (formaAtual != null) {
			if(formaAtual.getManipulador().soltar(e.getX(), e.getY())) {
			AplicacaoDesenho.getAplicacao().getDocumento().novaForma(formaAtual);
			formaAtual = formaAtual.clone();
			}
		}
	}
}

