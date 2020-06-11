package br.edu.udc;

import br.edu.udc.gui.JanelaAplicacao;

public class AplicacaoDesenho {
	
	static AplicacaoDesenho aplicacao;
	private Documento documento;

	private AplicacaoDesenho(){
		
	}
	
	private void iniAplicacaoDesenho() {
		JanelaAplicacao janela = new JanelaAplicacao();
		janela.iniJanelaAplicacao();
		janela.setVisible(true);
	}
	
	public static AplicacaoDesenho getAplicacao() {
		if(aplicacao == null) {
			aplicacao = new AplicacaoDesenho();
			aplicacao.iniAplicacaoDesenho();
		}
		return aplicacao;
	}
	
	public Documento getDocumento() {
		if (documento == null)
			documento = new Documento();
		return documento;
	}
	
	public static void main(String[] args) {
		getAplicacao();
	}
}
