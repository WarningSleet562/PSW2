package br.edu.udc.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.edu.udc.formas.Ponto;
import br.edu.udc.formas.Retangulo;
import br.edu.udc.AplicacaoDesenho;
import br.edu.udc.formas.Circulo;
import br.edu.udc.formas.Linha;
import br.edu.udc.formas.Triangulo;

public class JanelaAplicacao extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public JanelaAplicacao() {
		super("Desenhos com o mouse");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setLayout(new BorderLayout());
	
		JLabel status = new JLabel("Mensagens de evento do mouse");
		this.add(status, BorderLayout.SOUTH);
		
		PainelDesenho painel = new PainelDesenho(status);
		this.add(painel, BorderLayout.CENTER);
		
		// Criar Menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setMnemonic('A');
		menuBar.add(mnArquivo);
		
		JMenu mnFiguras = new JMenu("Figuras");
		mnFiguras.setMnemonic('F');
		menuBar.add(mnFiguras);
		
		JMenuItem mntmPonto = new JMenuItem("Ponto");
		mntmPonto.setMnemonic('P');
		mntmPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel.formaAtual(new Ponto(-1, -1));
			}
		});
		mnFiguras.add(mntmPonto);
		
		JMenuItem mntmLinha = new JMenuItem("Linha");
		mntmLinha.setMnemonic('L');
		mntmLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel.formaAtual(new Linha(new Ponto(-1, -1), new Ponto(-1, -1)));
			}
		});
		mnFiguras.add(mntmLinha);
		
		JMenuItem mntmTriangulo = new JMenuItem("Triangulo");
		mntmTriangulo.setMnemonic('T');
		mntmTriangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel.formaAtual(new Triangulo(new Ponto(-1, -1), new Ponto(-1, -1), new Ponto(-1, -1)));
			}
		});
		mnFiguras.add(mntmTriangulo);
		
		JMenuItem mntmRetangulo = new JMenuItem("Retangulo");
		mntmRetangulo.setMnemonic('R');
		mntmRetangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel.formaAtual(new Retangulo(new Ponto(-1, -1), new Ponto(-1, -1)));
			}
		});
		mnFiguras.add(mntmRetangulo);
		
		JMenuItem mntmCirculo = new JMenuItem("Circulo");
		mntmCirculo.setMnemonic('C');
		mntmCirculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel.formaAtual(new Circulo(new Ponto(-1, -1), new Ponto(-1, -1)));
			}
		});
		mnFiguras.add(mntmCirculo);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.setMnemonic('S');
		mntmSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					AplicacaoDesenho.getAplicacao().getDocumento().salvar(f);
				}
			}
		});
		mnArquivo.add(mntmSalvar);
		
		JMenuItem mntmLer = new JMenuItem("Ler");
		mntmLer.setMnemonic('L');
		mntmLer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					AplicacaoDesenho.getAplicacao().getDocumento().ler(f);
				}
			}
		});
		mnArquivo.add(mntmLer);
		
		JMenuItem mntmSalvarTxt = new JMenuItem("Salvar Texto");
		mntmSalvarTxt.setMnemonic('X');
		mntmSalvarTxt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					AplicacaoDesenho.getAplicacao().getDocumento().salvarTxt(f);
				}
			}
		});
		mnArquivo.add(mntmSalvarTxt);
		
		JMenuItem mntmLerTxt = new JMenuItem("Ler Texto");
		mntmLerTxt.setMnemonic('T');
		mntmLerTxt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					AplicacaoDesenho.getAplicacao().getDocumento().lerTxt(f);
				}
			}
		});
		mnArquivo.add(mntmLerTxt);
	}

}
