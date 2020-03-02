package br.edu.udc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Aplicacao {
	//private FileWriter output; //Objeto utilizado para gerar saída de texto no arquivo.
	
	public static void main(String[] args) {
		new Aplicacao();
	}
	
	public Aplicacao() {
		Scanner sc = new Scanner (System.in);
		Raquete raquete = lerRaquete(sc);
		
		System.out.println("A sua raquete: " + raquete);
		
		FileWriter output = writeTextFile();
		
		try {
			output.append(raquete.toFileString());
			raquete.setComprimento(raquete.getComprimento()+17);
			output.append(raquete.toFileString());
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Scanner in = getReadTextFile("raquetes.txt");
		
		String str = in.nextLine();
		System.out.println(str);
		Raquete r = Raquete.fromFileString(str);
		System.out.println(r);
		
		str = in.nextLine();
		System.out.println(str);
		r = Raquete.fromFileString(str);
		System.out.println(r);
		
		RandomAccessFile file = getRandomAccessFile();
		
		for (int i = 0; i < 5; i++) {
			try {
				//file.seek(i * Raquete.getSize());
				
				raquete = lerRaquete(sc);
				
				raquete.write(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 4; i >= 0; i--) {
			try {
				file.seek(i * Raquete.getSize());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				raquete.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println(raquete);
		}
		sc.close();
		System.out.println("Fim!");
	}
	
	
	private Raquete lerRaquete(Scanner sc) {
		float peso;
		int comprimento;
		String cor;
	
		System.out.printf("Entre com o peso da raquete: ");
		peso = sc.nextFloat();
		System.out.printf("Entre com o comprimento da raquete: ");
		comprimento = sc.nextInt();
		if (sc.hasNextLine()) sc.hasNextLine();
		System.out.printf("Entre com a cor da raquete: ");
		cor = sc.next();
		
		Raquete raquete = new Raquete (peso, comprimento, cor);
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
	
	public Scanner getReadTextFile(String fileInput){
		Scanner input = null;
		
		try{
			input = new Scanner(new File(fileInput));
			
		} catch (IOException e) {
			System.err.println("Error opening file.");
			e.printStackTrace();
			System.exit(1);
		}
		
		return input;
	}
	
	public RandomAccessFile getRandomAccessFile() {
		RandomAccessFile file = null;
		
		try{
			file = new RandomAccessFile("raquetes.dat", "rw");
		} catch (FileNotFoundException e) {
			System.err.println("Error opening file.");
			e.printStackTrace();
			System.exit(1);
		}
		
		return file;
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