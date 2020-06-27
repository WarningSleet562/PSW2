package br.edu.udc.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.edu.udc.formas.Ponto;
import br.edu.udc.formas.Retangulo;
import br.edu.udc.formas.RetanguloAngular;
import br.edu.udc.AplicacaoDesenho;
import br.edu.udc.formas.Lapis;
import br.edu.udc.formas.Linha;
import br.edu.udc.formas.Triangulo;

public class JanelaAplicacao extends JFrame {
	private static final long serialVersionUID = 1L;
	PainelDesenho painelDesenho;
	JLabel status = new JLabel("Status da aplicacao");
	
	private String netName;
	

	public void initJanelaAplicacao() {

	}

	public JanelaAplicacao() {
		super("Aplicacao de desenho com mouse");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 720);

		this.setLayout(new BorderLayout());

		this.add(status, BorderLayout.SOUTH);

		PainelTexto painelTexto = new PainelTexto();
		// this.add(painelTexto, BorderLayout.WEST);
		// AplicacaoDesenho.getAplicacao().getDocumento().adicionaPainel(painelTexto);
		// getPainel();
		painelDesenho = new PainelDesenho(status);
		this.add(painelDesenho, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnJArquivo = new JMenu("Arquivo");
		mnJArquivo.setMnemonic('A');
		menuBar.add(mnJArquivo);

		JMenu mnJFiguras = new JMenu("Figuras");
		mnJArquivo.setMnemonic('F');
		menuBar.add(mnJFiguras);

		ButtonGroup btnGroup = new ButtonGroup();

		JMenuItem mntmClient;
		JMenuItem mntmServer;

		JRadioButton rdbtnTexto = new JRadioButton("Texto");
		rdbtnTexto.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (((JRadioButton) e.getSource()).isSelected()) {
					remove(painelDesenho);
					AplicacaoDesenho.getAplicacao().getDocumento().removePainel(painelDesenho);
					add(painelTexto, BorderLayout.CENTER);
					AplicacaoDesenho.getAplicacao().getDocumento().adicionarPainel(painelTexto);
					revalidate();
					repaint();
				}
			}
		});
		btnGroup.add(rdbtnTexto);
		menuBar.add(rdbtnTexto);

		JRadioButton rdbtnDesenho = new JRadioButton("Desenho");
		rdbtnDesenho.setSelected(true);
		rdbtnDesenho.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (((JRadioButton) e.getSource()).isSelected()) {
					remove(painelTexto);
					AplicacaoDesenho.getAplicacao().getDocumento().removePainel(painelTexto);
					add(painelDesenho, BorderLayout.CENTER);
					AplicacaoDesenho.getAplicacao().getDocumento().adicionarPainel(painelDesenho);
					revalidate();
					repaint();
				}
			}
		});
		btnGroup.add(rdbtnDesenho);
		menuBar.add(rdbtnDesenho);

		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mnJArquivo.setMnemonic('s');
		mntmSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					AplicacaoDesenho.getAplicacao().getDocumento().salvar(f);
				}
			}
		});
		mnJArquivo.add(mntmSalvar);
		JMenuItem mntmLer = new JMenuItem("Ler");
		mnJArquivo.setMnemonic('l');
		mntmLer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					AplicacaoDesenho.getAplicacao().getDocumento().ler(f);
				}
			}
		});
		mnJArquivo.add(mntmLer);
		JMenuItem mntmSalvarTxt = new JMenuItem("Salvar Texto");
		mnJArquivo.setMnemonic('s');
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
		mnJArquivo.add(mntmSalvarTxt);
		JMenuItem mntmLerTxt = new JMenuItem("Ler Texto");
		mnJArquivo.setMnemonic('a');
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
		mnJArquivo.add(mntmLerTxt);

		JMenuItem mntmJPonto = new JMenuItem("Ponto");
		mnJFiguras.setMnemonic('p');
		mntmJPonto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelDesenho.formaAtual(new Ponto(0, 0));
				Dialog d = new Dialog("test", "2");
				d.setVisible(true);
				getNetName();
			}
		});
		mnJFiguras.add(mntmJPonto);
		
		JMenuItem mntmJLinha = new JMenuItem("Linha");
		mnJArquivo.setMnemonic('l');
		mntmJLinha.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelDesenho.formaAtual(new Linha(new Ponto(0, 0), new Ponto(0, 0)));
			}
		});
		mnJFiguras.add(mntmJLinha);

		JMenuItem mntmJTriangulo = new JMenuItem("Triangulo");
		mnJArquivo.setMnemonic('t');
		mntmJTriangulo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelDesenho.formaAtual(new Triangulo(new Ponto(0, 0), new Ponto(0, 0), new Ponto(0, 0)));
			}
		});
		mnJFiguras.add(mntmJTriangulo);
		JMenuItem mntmJLapis = new JMenuItem("Lapis");
		mnJArquivo.setMnemonic('s');
		mntmJLapis.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelDesenho.formaAtual(new Lapis());
			}
		});
		mnJFiguras.add(mntmJLapis);
		JMenuItem mntmJRetangulo = new JMenuItem("Retangulo");
		mnJArquivo.setMnemonic('r');
		mntmJRetangulo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelDesenho.formaAtual(new Retangulo(new Ponto(0, 0), new Ponto(0, 0)));
			}
		});
		mnJFiguras.add(mntmJRetangulo);
		JMenuItem mntmJRetanguloAngular = new JMenuItem("RetanguloA");
		mnJArquivo.setMnemonic('n');
		mntmJRetanguloAngular.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				painelDesenho.formaAtual(
						new RetanguloAngular(new Ponto(0, 0), new Ponto(0, 0), new Ponto(0, 0), new Ponto(0, 0)));
			}
		});
		mnJFiguras.add(mntmJRetanguloAngular);

		mntmServer = new JMenuItem("Server");
		mntmClient = new JMenuItem("Client");
		mnJArquivo.setMnemonic('r');
		mntmServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!AplicacaoDesenho.getAplicacao().isServerRunning()) {
					String nome = getNetName();
					if (AplicacaoDesenho.getAplicacao().startServer("Servidor")) {
						mntmServer.setText("Encerrar Servidor");
						mntmClient.setEnabled(false);
					} else {
						mntmServer.setText("Iniciar Servidor");
						mntmClient.setEnabled(true);
					}
				}
			}
		});
		mnJArquivo.add(mntmServer);

		mnJArquivo.setMnemonic('C');
		mntmClient.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!AplicacaoDesenho.getAplicacao().isServerRunning()) {
					String nome = getNetName();
					if (AplicacaoDesenho.getAplicacao().startClient("localhost")) {
						mntmClient.setText("Encerrar client");
						mntmServer.setEnabled(false);
					} else {
						mntmClient.setText("Iniciar client");
						mntmServer.setEnabled(true);
					}
				}
			}
		});
		mnJArquivo.add(mntmClient);

	}

	public String getNetName() {
        Dialog d = new Dialog("Insira o Nome", "Nome");
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setResizable(false);
        d.setVisible(true);
        netName = d.getValor();
        return netName;
    }
	
    public String getServerName() {
        Dialog d = new Dialog("Insira O Host","Host");
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setResizable(false);
        d.setVisible(true);
        netName = d.getValor();
        return netName;
    }

	public JLabel getStatus() {
		return status;
	}

	public PainelDesenho getPainel() {
		if (painelDesenho == null)
			painelDesenho = new PainelDesenho(status);
		return painelDesenho;
	}

}
