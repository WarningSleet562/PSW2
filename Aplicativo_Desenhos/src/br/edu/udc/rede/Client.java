package br.edu.udc.rede;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import br.edu.udc.AplicacaoDesenho;
import br.edu.udc.GLobalConstants;
import br.edu.udc.formas.FabricaFormas;
import br.edu.udc.gui.PainelDesenho;

public class Client implements Runnable {
	private Scanner in;
	private PrintWriter out;
	private String serverHost;
	private Socket client;
	private boolean run = false;
	private JLabel label;
	private PainelDesenho painel;

	private String serverName;
	private String clientName;

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public Client() {
		
	}
	
	public boolean isRunning() {
		return run;
	}

	public void setPainel(PainelDesenho painel) {
		this.painel = painel;
	}

	public void setServerHostName(String host) {
		serverHost = host;
	}

	public void run() {
		String nome = null;
		System.out.println("cliente iniciando execucao");
		run = true;
		try {
			if (serverHost == null || serverHost.isEmpty()) {
				System.out.println("Server host Name nao definido");
				return;
			}

			client = new Socket(InetAddress.getByName(serverHost), GLobalConstants.SOCKET_PORT);
			client.setSoTimeout(GLobalConstants.SOCKET_TIMEOUT);
			displayMessage("Connected to: " + client.getInetAddress().getHostName());
			System.out.println("Connected to: " + client.getInetAddress().getHostName());

			in = new Scanner(client.getInputStream());
			out = new PrintWriter(client.getOutputStream());
			System.out.println("client got I/O strems");

			client.setSoTimeout(0);
			while (run) {
				String line;
				try {
					line = in.nextLine();
				} catch (NoSuchElementException e) {
					try {
						wait(1000);
					} catch (InterruptedException e1) {
					} catch (IllegalMonitorStateException e1) {
					}
					continue;
				}
				if (line.startsWith("SUBMITNAME")) {
					//nome = AplicacaoDesenho.getAplicacao().getJanela().getName();
					//out.println(nome);
				} else if (line.startsWith("NAMEACCEPTED")) {
					clientName = "Servidor";
				} else if (line.startsWith("MESSAGE")) {
					displayMessage(line.substring(8));
				} else if (line.startsWith("DATA")) {
					processesData(line.substring(5));
				}
			}
		} catch (UnknownHostException e) {
			displayMessage("Conection Error: " + e);
		} catch (ConnectException e) {
			displayMessage("Conection Error: " + e);
		} catch (IOException e) {
			displayMessage("Conection Error: " + e);
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			try {
				if (client != null)
					client.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}

	private void processesData(String data) {
		int j = data.indexOf(" ");
		String nome = data.substring(0, j).trim();
		if (nome.equals(serverName)) {
			return;
		}
		data = data.substring(j).trim();
		int i = data.indexOf('(');

		if (data.startsWith("MOUSE")) {
			painel.atualizaPosMouse(FabricaFormas.fabricarPonto(data.substring(i)), nome);
		} else if (data.startsWith("PONTO")) {
			AplicacaoDesenho.getAplicacao().getDocumento().novaForma(FabricaFormas.fabricarPonto(data.substring(i)));
		} else if (data.startsWith("LINHA")) {
			AplicacaoDesenho.getAplicacao().getDocumento().novaForma(FabricaFormas.fabricarLinha(data.substring(i)));
		} else if (data.startsWith("TRIANGULO")) {
			AplicacaoDesenho.getAplicacao().getDocumento()
					.novaForma(FabricaFormas.fabricarTriangulo(data.substring(i)));
		} else if (data.startsWith("LAPIS")) {
			AplicacaoDesenho.getAplicacao().getDocumento().novaForma(FabricaFormas.fabricarLapis(data.substring(i)));
		}
	}

	public void displayMessage(final String messageToDisplay) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				label.setText(messageToDisplay);
			}
		});

	}

	public void sendData(String message) {
        try 
        {
            out.println("DATA " + message);
            out.flush(); 
            displayMessage("DATA " + message);
        }
        catch (Exception Exception) {
            displayMessage("\nError writing object");
        }
    }
	
	public void stop() {
		run = false;
	}
}
