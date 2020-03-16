package br.edu.udc.gui;

import java.awt.Graphics;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.udc.formas.FormaGeometrica;
import br.edu.udc.formas.Linha;
import br.edu.udc.formas.Ponto;
import br.edu.udc.formas.Triangulo;

public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private JLabel status;
	
	private FormaGeometrica formaAtual;
	
	private int estado;
	
	private List<FormaGeometrica> listaFormas;
	
	public PainelDesenho(JLabel status){
		this.status = status;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		formaAtual = null;
		listaFormas = new LinkedList<FormaGeometrica>();
	}
	
	public void formaAtual (FormaGeometrica forma) {
		formaAtual = forma;
		estado = 0;
	}
	
	@Override
	public void paint (Graphics g) {
		super.paint(g);
		
		if (formaAtual != null)
			formaAtual.desenhar(g);
		
		for(FormaGeometrica f : listaFormas) {
			f.desenhar(g);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		String message = String.format("Mouse arrastado em [%d; %d]", e.getX(), e.getY());
		if (formaAtual != null)
			message = message + " - desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(message);		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (formaAtual != null && formaAtual.getClass().equals(Linha.class)) {
			if(estado == 1) {
				((Linha)formaAtual).setB(new Ponto(e.getX(), e.getY()));
				repaint();
			}
		}
		
		else if (formaAtual != null && formaAtual.getClass().equals(Triangulo.class)) {
			switch(estado) {
			case 1:
				((Triangulo)formaAtual).setB(new Ponto(e.getX(), e.getY()));
				((Triangulo)formaAtual).setC(new Ponto(e.getX(), e.getY()));
				repaint();
				break;
			case 2:
				((Triangulo)formaAtual).setC(new Ponto(e.getX(), e.getY()));
				repaint();
				break;
			}
	
		}
		String message = String.format("Mouse movimentado em [%d; %d]", e.getX(), e.getY());
		if (formaAtual != null)
			message = message + " desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(message);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (formaAtual != null && formaAtual.getClass().equals(Ponto.class)) {
			formaAtual = new Ponto(e.getX(), e.getY());
			
			listaFormas.add(formaAtual);
			formaAtual = formaAtual.clone();
			repaint();
		}
		else if (formaAtual != null && formaAtual.getClass().equals(Linha.class)) {
			switch(estado) {
				case 0:	
					((Linha)formaAtual).setA(new Ponto(e.getX(), e.getY()));
					((Linha)formaAtual).setB(new Ponto(e.getX(), e.getY()));
					estado = 1;
					repaint();
					break;
				case 1:
					((Linha)formaAtual).setB(new Ponto(e.getX(), e.getY()));
					estado = 0;
					listaFormas.add(formaAtual);
					formaAtual = formaAtual.clone();
					repaint();
					break;
			}
		}
		else if (formaAtual != null && formaAtual.getClass().equals(Triangulo.class)) {
			switch(estado) {
				case 0:
					((Triangulo)formaAtual).setA(new Ponto(e.getX(), e.getY()));
					((Triangulo)formaAtual).setB(new Ponto(e.getX(), e.getY()));
					((Triangulo)formaAtual).setC(new Ponto(e.getX(), e.getY()));
					estado = 1;
					repaint();
					break;
				case 1:
					((Triangulo)formaAtual).setB(new Ponto(e.getX(), e.getY()));
					((Triangulo)formaAtual).setC(new Ponto(e.getX(), e.getY()));
					repaint();
					estado = 2;
					break;
				case 2:
					((Triangulo)formaAtual).setC(new Ponto(e.getX(), e.getY()));
					estado = 0;
					listaFormas.add(formaAtual);
					formaAtual = formaAtual.clone();
					repaint();
					break;
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
		if (formaAtual != null)
			message = message + " - desenhando " + formaAtual.getNome() + " em" + formaAtual;
		status.setText(message);		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		String message = String.format("Botão solto em [%d; %d]", e.getX(), e.getY());
		if (formaAtual != null)
			message = message + " - desenhando " + formaAtual.getNome() + " em" + formaAtual;
		status.setText(message);		
	}

	
}
