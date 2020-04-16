package br.edu.udc.gui;

import java.awt.Graphics;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.udc.formas.FormaGeometrica;

public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private JLabel status;
	
	private FormaGeometrica formaAtual;
	
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
	}
	
	@Override
	public void paint (Graphics g) {
		super.paint(g);
		
		if (formaAtual != null)
			formaAtual.getManipulador().desenhar(g);
		
		for(FormaGeometrica f : listaFormas) {
			f.getManipulador().desenhar(g);
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
			listaFormas.add(formaAtual);
			formaAtual = formaAtual.clone();
		}
	}
		String message = String.format("Bot�o apertado em [%d; %d]", e.getX(), e.getY());
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
		String message = String.format("Bot�o pressionado em [%d; %d]", e.getX(), e.getY());
		if (formaAtual != null)
			message = message + " - desenhando " + formaAtual.getNome() + " em" + formaAtual;
		status.setText(message);		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		String message = String.format("Bot�o solto em [%d; %d]", e.getX(), e.getY());
		if (formaAtual != null)
			message = message + " - desenhando " + formaAtual.getNome() + " em" + formaAtual;
		status.setText(message);		
	}

	public void salvar(File file) {
		try {
			ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(file));
			
			for (FormaGeometrica f : listaFormas) {
				oss.writeObject(f);
			}
			
			oss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ler(File file) {
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			
			listaFormas.clear();
			
			FormaGeometrica formaAux = null;
			while (true) {
				formaAux = (FormaGeometrica) ois.readObject();
				listaFormas.add(formaAux);
			}
		} catch (EOFException endOfFileException) {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		repaint();
	}
	
}
