package br.edu.udc.gui;

import java.awt.Color;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import br.edu.udc.formas.FormaGeometrica;
import br.edu.udc.AplicacaoDesenho;

public class PainelTexto extends JTextArea implements PainelFormaGeometrica {
	private static final long serialVersionUID = 1L;
	private JLabel status;
	
	public PainelTexto(JLabel status) {
		this.status = status;
		setBackground(new Color(220, 220, 250));
	}

	public void atualizar() {
		StringBuffer buf = new StringBuffer();
		
		Iterator<FormaGeometrica> it = AplicacaoDesenho.getAplicacao().getDocumento().getIterador();
		
		while(it.hasNext()) {
			buf.append(it.next());
			buf.append("\n");
		}
		setText(buf.toString());
		repaint();
	}
	
	public void novaFormaGeometrica(FormaGeometrica forma) {
		AplicacaoDesenho.getAplicacao().getDocumento().novaForma(forma);
	}
}
