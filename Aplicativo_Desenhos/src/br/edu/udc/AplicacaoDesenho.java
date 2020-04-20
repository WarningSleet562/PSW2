package br.edu.udc;

import br.edu.udc.gui.JanelaAplicacao;

public class AplicacaoDesenho {
	
	static AplicacaoDesenho aplicacao;
	private Documento documento;

	private AplicacaoDesenho(){
		documento = new Documento();
		JanelaAplicacao janela = new JanelaAplicacao();
		janela.setVisible(true);
	}
	
	public static AplicacaoDesenho getAplicacao() {
		if(aplicacao == null)
			aplicacao = new AplicacaoDesenho();
		return aplicacao;
	}
	
	public Documento getDocumento() {
		return documento;
	}
	
	public static void main(String[] args) {
		getAplicacao();
	}
}
