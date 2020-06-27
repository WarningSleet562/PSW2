package br.edu.udc;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import br.edu.udc.gui.JanelaAplicacao;
import br.edu.udc.rede.Client;
import br.edu.udc.rede.Server;
import java.awt.EventQueue;

public class AplicacaoDesenho {
	
	static AplicacaoDesenho aplicacao;
	private Documento documento;
	private Server server;
	private Client client;	
	private static JanelaAplicacao janela;
	private ThreadPoolExecutor pool;

	AplicacaoDesenho() {
		pool = new ThreadPoolExecutor(5, 5, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
	}

	private void initAplicacao() {
		try {
			for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				janela = new JanelaAplicacao();
				janela.initJanelaAplicacao();
				janela.setVisible(true);
			}
		});
	}
	
	public static AplicacaoDesenho getAplicacao() {
		if (aplicacao == null)
			aplicacao = new AplicacaoDesenho();
		return aplicacao;
	}

	public Documento getDocumento() {
		if(documento == null) {
			documento = new Documento();
		}
		return documento;
	}
	
	public JanelaAplicacao getJanela() {
		return janela;
	}
	
	private Client getClient() {
		if(client == null)
			client = new Client();
		return client;
	}
	
	private Server getServer() {
		if (server == null) {
			server = new Server();
		}
		return server;
	}
	
	public boolean isServerRunning() {
		if(server != null && server.isRunning())
			return true;
		return false;
	}

	public boolean isClientRunning() {
		if(client != null && client.isRunning())
			return true;
		return false;
	}
	
	public boolean startServer(String nome) {
		if (client != null)
			return false;
		if(server == null) {
			getServer();
		}
		server.setNome(nome);
		server.setLabel(janela.getStatus());
		server.setPainel(janela.getPainel());
		
		if (!server.isRunning()) {
			pool.execute(server);
		}
		return true;
	}
	public boolean stopServer() {
		if (server == null)
			return false;
		if(!server.isRunning())
			return false;
		
		server.stop();
		server = null;
		return true;
	}
	public boolean stopClient() {
		if (client == null)
			return false;
		if(!client.isRunning())
			return false;
		
		client.stop();
		client = null;
		return true;
	}
	
	public boolean startClient(String serverHost) {
		if(serverHost == null || serverHost.isEmpty())
			return false;
		if(server != null)
			return false;
		if(client == null)
			getClient();
		client.setServerHostName(serverHost);
		client.setLabel(janela.getStatus());
		client.setPainel(janela.getPainel());
		if(!client.isRunning()) {
			pool.execute(client);
		}
		return true;
	}
	
	public Server getActiveServer() {
		if(isServerRunning())
			return server;
		return null;
	}
	public Client getActiveClient() {
		if(isClientRunning())
			return client;
		return null;
	}

	public static void main(String[] args) {
		getAplicacao().initAplicacao();
	}
}
