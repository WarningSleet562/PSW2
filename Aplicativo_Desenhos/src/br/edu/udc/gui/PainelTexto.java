package br.edu.udc.gui;

import java.util.Iterator;
import javax.swing.JTextArea;

import br.edu.udc.formas.FormaGeometrica;
import br.edu.udc.AplicacaoDesenho;

public class PainelTexto extends JTextArea implements PainelFormaGeometrica {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private FormaGeometrica formaAtual;
	
	public PainelTexto() {
		super(8,8);
	
		formaAtual = null;
	}
	
	public void formaAtual(FormaGeometrica forma) {
		formaAtual = forma;
	}

	@Override
	public void atualizar() {
		StringBuffer buf = new StringBuffer();
		Iterator<FormaGeometrica> it = AplicacaoDesenho.getAplicacao().getDocumento().getIterador();
		while(it.hasNext()) {
			buf.append(it.next());
			buf.append("\n");
		}
		setText(buf.toString());
	}
	
	@Override
	public void novaFormaGeometrica(FormaGeometrica forma) {
		formaAtual = forma;
	}
}
