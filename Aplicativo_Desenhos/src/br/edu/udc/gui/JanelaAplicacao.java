package br.edu.udc.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import br.edu.udc.formas.Ponto;
import br.edu.udc.formas.Retangulo;
import br.edu.udc.AplicacaoDesenho;
import br.edu.udc.Documento;
import br.edu.udc.formas.Circulo;
import br.edu.udc.formas.Lapis;
import br.edu.udc.formas.Linha;
import br.edu.udc.formas.Triangulo;

public class JanelaAplicacao extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private PainelDesenho painelDesenho;
	private PainelTexto painelTexto;
	
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	public JanelaAplicacao() {
		super("Aplicação de desenho com o mouse");
	}
	
	public void iniJanelaAplicacao() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		
		getContentPane().setLayout(new BorderLayout());
	
		JLabel status = new JLabel("Status da aplicação");
		getContentPane().add(status, BorderLayout.SOUTH);
		
		painelDesenho = new PainelDesenho(status);
		getContentPane().add(painelDesenho, BorderLayout.CENTER);
		
		painelTexto = new PainelTexto(status);
		
		Documento doc = AplicacaoDesenho.getAplicacao().getDocumento();
		doc.adicionarPainel(painelDesenho);
		doc.adicionarPainel(painelTexto);
		
		// Criar Menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setMnemonic('A');
		menuBar.add(mnArquivo);
		
		JMenu mnFiguras = new JMenu("Figuras");
		mnFiguras.setMnemonic('F');
		menuBar.add(mnFiguras);
		
		JMenu mnVisualizar = new JMenu("Visualizar");
		mnFiguras.setMnemonic('V');
		menuBar.add(mnVisualizar);
		
		JRadioButtonMenuItem rdbtnmntmDesenho = new JRadioButtonMenuItem("Desenho");
		rdbtnmntmDesenho.setSelected(true);
		rdbtnmntmDesenho.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					getContentPane().remove(painelTexto);
					getContentPane().add(painelDesenho, BorderLayout.CENTER);
					getContentPane().revalidate();
					getContentPane().repaint();
				}
			}
		});
		buttonGroup.add(rdbtnmntmDesenho);
		mnVisualizar.add(rdbtnmntmDesenho);
		
		JRadioButtonMenuItem rdbtnmntmTexto = new JRadioButtonMenuItem("Texto");
		rdbtnmntmTexto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					getContentPane().remove(painelDesenho);
					getContentPane().add(painelTexto, BorderLayout.CENTER);
					getContentPane().revalidate();
					getContentPane().repaint();
				}
			}
		});
		buttonGroup.add(rdbtnmntmTexto);
		mnVisualizar.add(rdbtnmntmTexto);
		
		JMenuItem mntmPonto = new JMenuItem("Ponto");
		mntmPonto.setMnemonic('P');
		mntmPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelDesenho.novaFormaGeometrica(new Ponto(-1, -1));
			}
		});
		mnFiguras.add(mntmPonto);
		
		JMenuItem mntmLinha = new JMenuItem("Linha");
		mntmLinha.setMnemonic('L');
		mntmLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelDesenho.novaFormaGeometrica(new Linha(new Ponto(-1, -1), new Ponto(-1, -1)));
			}
		});
		mnFiguras.add(mntmLinha);
		
		JMenuItem mntmTriangulo = new JMenuItem("Triangulo");
		mntmTriangulo.setMnemonic('T');
		mntmTriangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelDesenho.novaFormaGeometrica(new Triangulo(new Ponto(-1, -1), new Ponto(-1, -1), new Ponto(-1, -1)));
			}
		});
		mnFiguras.add(mntmTriangulo);
		
		JMenuItem mntmRetangulo = new JMenuItem("Retangulo");
		mntmRetangulo.setMnemonic('R');
		mntmRetangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelDesenho.novaFormaGeometrica(new Retangulo(new Ponto(-1, -1), new Ponto(-1, -1)));
			}
		});
		mnFiguras.add(mntmRetangulo);
		
		JMenuItem mntmCirculo = new JMenuItem("Circulo");
		mntmCirculo.setMnemonic('C');
		mntmCirculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelDesenho.novaFormaGeometrica(new Circulo(new Ponto(-1, -1), new Ponto(-1, -1)));
			}
		});
		mnFiguras.add(mntmCirculo);
		
		JMenuItem mntmLapis = new JMenuItem("Lapis");
		mntmLapis.setMnemonic('L');
		mntmLapis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelDesenho.novaFormaGeometrica(new Lapis());
			}
		});
		mnFiguras.add(mntmLapis);
		
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
