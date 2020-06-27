package br.edu.udc.rede;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import br.edu.udc.AplicacaoDesenho;
import br.edu.udc.GLobalConstants;
import br.edu.udc.formas.FabricaFormas;
import br.edu.udc.formas.FormaGeometrica;
import br.edu.udc.gui.PainelDesenho;

public class Server implements Runnable {
	private ServerSocket server; //socket de server
	private Socket connection; // conexao com cliente
	private int counter = 0;// contador do numero de conexoes
	private boolean run = false;
	private int conCount = GLobalConstants.CONCURRENT_CLIENTS;
	private ThreadPoolExecutor pool;
	
	private JLabel label;
	private PainelDesenho painel;
	
	private String serverName;
	
	private Set<String> names;

	private Set<PrintWriter> writers;
	
	public Server() {
		names = new HashSet<>();
		writers = new HashSet<>();
		System.out.println("teste1");
		pool = new ThreadPoolExecutor(conCount,conCount,5,TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
		System.out.println("server instanciado");
	}
	
	public void setLabel(JLabel label) {
		this.label = label;
	}
	
	public int getMaxClients() {
		return conCount;
	}
	
	public void setMaxClients(int clients) {
		conCount = clients;
		pool.setMaximumPoolSize(conCount);
		pool.setCorePoolSize(conCount);
	}
	
	public boolean isRunning() {
		return run;
	}
	
	public void stop(){
		run = false;
	}
	
	public void setPainel(PainelDesenho painel) {
		this.painel = painel;
	}
	
	public boolean setNome(String name) {
		if(!name.isEmpty() && !name.contains(name)) {
			names.add(name);
			serverName = name;
			return true;
		}
		return false;
	}
	public void run() {
		System.out.println("Server iniciando execucao");
		run = true;
		try {
			server = new ServerSocket(GLobalConstants.SOCKET_PORT);
			displayMessage("servidor de desenho esta executando");
			server.setSoTimeout(GLobalConstants.SOCKET_TIMEOUT);
			while(run) {
				try {
					connection = server.accept();
				} catch (SocketTimeoutException e) {
					continue;
				}
				HandlerDesenho net = new HandlerDesenho(connection);
				counter++;
				displayMessage("Connection"+counter + "received from: " + connection.getInetAddress());
				pool.execute(net);
			}
		} catch (BindException e) {
			System.out.println("Erro: " + e);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			displayMessage("Servidor de desenho desconectado");
		}
	}
	
	private class HandlerDesenho implements Runnable{
		private Socket socket;
		private String name;
		private Scanner in;
		private PrintWriter out;
		private boolean run = true;
		
		HandlerDesenho(Socket socket) {
			this.socket = socket;
			System.out.println("Handler instanciado " + socket);
			try {
				socket.setSoTimeout(0);
			} catch (SocketException e) {
				e.printStackTrace();
			}
		}
	
		@Override
		public void run() {
			try {
				in = new Scanner(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream(),true);
				
				socket.setSoTimeout(0);
				
				out.println("SUBMITNAME");
				
				while(run) {
					if(!in.hasNextLine()) {
						continue;
					}
					name = in.nextLine();
					synchronized (names) {
						if(!name.isEmpty() && !names.contains(name)) {
							names.add(name);
							break;
						}
						out.println("SUBMITNAME");
					}
				}
				
				out.println("NAMEACCEPTED " + name);
				for(PrintWriter writer : writers) {
					writer.println("MESSAGE " + name + "has joined");
				}
				writers.add(out);
				
				Iterator<FormaGeometrica> it = AplicacaoDesenho.getAplicacao().getDocumento().getIterador();
				while(it.hasNext()) {
					FormaGeometrica f = it.next();
					sendData(f.getClass().getSimpleName().toUpperCase() + " " + f.toString());
				}
			
				while(run) {
					String input;
					try {
						input = in.nextLine();
					} catch (NoSuchElementException e) {
						System.out.println("Erro: " + e);
						continue;
					}
					if(input.startsWith("QUIT")) {
						return;
					}
					for(PrintWriter writer : writers) {
						writer.println(input);
					}
					if(input.startsWith("DATA")){
						processesData(input.substring(5));
					}
				}
			} catch (IOException e) {
				displayMessage("Error: " + e);
			}finally {
				close();
			}
		}
		
		private void close() {
			if(out != null) {
				writers.remove(out);
			}
			if(name != null) {
				displayMessage(name + " is leaving");
				names.remove(name);
				for(PrintWriter writer : writers) {
					writer.println("MESSAGE " + name + "has left");
				}
			}
			try {
				socket.close();
			} catch (IOException e) {
				displayMessage("Error: " + e);
			}
		}
		private void processesData(String data) {
			int j = data.indexOf(" ");
			String nome = data.substring(0, j ).trim();
			if(nome.equals(serverName)) {
				return;
			}
			data = data.substring(j).trim();
			int i = data.indexOf('(');
			
			if(data.startsWith("MOUSE")) {
				painel.atualizaPosMouse(FabricaFormas.fabricarPonto(data.substring(i)), name);
			}else if (data.startsWith("PONTO")) {
				AplicacaoDesenho.getAplicacao().getDocumento().novaForma(
						FabricaFormas.fabricarPonto(data.substring(i)));
			}else if (data.startsWith("LINHA")) {
				AplicacaoDesenho.getAplicacao().getDocumento().novaForma(
						FabricaFormas.fabricarLinha(data.substring(i)));
			}else if (data.startsWith("TRIANGULO")) {
				AplicacaoDesenho.getAplicacao().getDocumento().novaForma(
						FabricaFormas.fabricarTriangulo(data.substring(i)));
			}else if (data.startsWith("LAPIS")) {
				AplicacaoDesenho.getAplicacao().getDocumento().novaForma(
						FabricaFormas.fabricarLapis(data.substring(i)));
			}
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
		if(writers.size() == 0) {
			return;
		}
		for(PrintWriter writer : writers) {
			writer.println("DATA " + serverName + " " + message);
		}
		
	}
}
