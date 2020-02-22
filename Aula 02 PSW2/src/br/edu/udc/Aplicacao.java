package br.edu.udc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Aplicacao {

	//private FileWriter output; //Objeto utilizado para gerar saída de texto no arquivo.
	
	public static void main(String[] args) {
		new Aplicacao();
	}
	
	public Aplicacao() {
		Raquete raquete = lerRaquete();
		
		System.out.println("A sua raquete: " + raquete);
		
		FileWriter output = writeTextFile();
		File arquivoTexto = new File("raquetes.txt");
		File arquivoSerial = new File("raquetes.serial");
		
		try {
			output.append(raquete.toString());
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ObjectOutputStream objectOutput = writeObjectFile();
		
		try {
			objectOutput.writeObject(raquete);
			objectOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Scanner sc;
		try {
			sc = new Scanner(arquivoTexto);
			String s = sc.next();
			System.out.println("Printando do arquivo texto: " + s);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		ObjectInputStream objectInput = readObjectFile();
		
		try {
			sc = new Scanner(arquivoSerial);
			
			try {
					Object r;
					
					try {
						r = objectInput.readObject();
						System.out.println("Printando do arquivo serial: " + r.toString());
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}					
				
				objectInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Fim!");
	}
	
	static Raquete lerRaquete() {
		float peso;
		int comprimento;
		String cor;
		Scanner sc = new Scanner(System.in);
	
		System.out.printf("Entre com o peso da raquete: ");
		peso = sc.nextFloat();
		System.out.printf("Entre com o comprimento da raquete: ");
		comprimento = sc.nextInt();
		System.out.printf("Entre com a cor da raquete: ");
		cor = sc.next();
		
		Raquete raquete = new Raquete (peso, comprimento, cor);
		sc.close();
		return raquete;
	}
	
	//Permite ao usuário abrir o arquivo.
	public FileWriter writeTextFile() {
		FileWriter output = null;
		
		try {
			output = new FileWriter("raquetes.txt");
		} //Fim do try.
		catch (SecurityException securityException) {
			System.err.println(
					"You do not have write access to this file.");
			System.exit(1);
		} //Fim do catch.
		catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error creating file.");
			System.exit(1);
		} //Fim do catch.
		catch (IOException e) {
			System.err.println("Error creating file.");
			System.exit(1);
			e.printStackTrace();
		}
		
		return output;
	}
	
	public ObjectOutputStream writeObjectFile() {
		ObjectOutputStream oos = null;
		
		try {
		oos = new ObjectOutputStream(new FileOutputStream("raquetes.serial"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return oos;
	}
	
	public ObjectInputStream readObjectFile() {
		ObjectInputStream ois = null;
		
		try {
		ois = new ObjectInputStream(new FileInputStream("raquetes.serial"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ois;
	}

}