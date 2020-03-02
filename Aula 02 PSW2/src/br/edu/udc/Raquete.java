package br.edu.udc;

import java.io.Serializable;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Raquete implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	float peso;
	int comprimento;
	String cor;
	
	public Raquete(float peso, int comprimento, String cor) {
		this.peso = peso;
		this.comprimento = comprimento;
		this.cor = cor;	
	}
	
	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public int getComprimento() {
		return comprimento;
	}

	public void setComprimento(int comprimento) {
		this.comprimento = comprimento;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public String toString() {
		return peso + "/" + comprimento + "/" + cor;
	}
	
	public String toFileString() {
		return peso + "; " + comprimento + "; " + cor + "\n";
	}
	
	public static Raquete fromFileString(String str) {
		float peso;
		int comprimento;
		String cor;
		StringTokenizer token = new StringTokenizer(str);
	
		peso = Float.parseFloat(token.nextToken(";").trim());
		comprimento = Integer.parseInt(token.nextToken(";").trim());
		cor = token.nextToken(";").trim();
		
		return new Raquete(peso, comprimento, cor);
		
	}
	
	//Assegura que a cor tenha um comprimento adequado.
	private String readCor (RandomAccessFile file) throws IOException {
		char cor[] = new char [15], temp;
		
		for (int count = 0; count < cor.length; count++) {
			temp = file.readChar();
			cor [count] = temp;
		}
		return new String(cor).replace('\0', ' ').trim();
	}
	
	//Assegura que a cor tenha um comprimento adequado.
	private void writeCor (RandomAccessFile file, String cor) throws IOException {
		StringBuffer buffer = null;
		
		if (cor != null) 
			buffer = new StringBuffer(cor);
		else
			buffer = new StringBuffer(15);
		
		buffer.setLength(15);
		file.writeChars(buffer.toString());
	}

	//Grava um registro em um RandomAccessFile especificado.
	public void write (RandomAccessFile file) throws IOException {
		file.writeFloat(peso);
		writeCor(file, cor);
		file.writeInt(comprimento);
	}
	
	//Lê um registro em um RandomAccessFile especificado.
	public void read (RandomAccessFile file) throws IOException {
		setPeso(file.readFloat());
		setCor(readCor(file));
		setComprimento(file.readInt());
	}
	
	public static int getSize() {
		return 4 + 4 + 30; /* FLOAT (4 BYTES) + INT (4 BYTES) + CHAR[15] (15 * 2 BYTES) */ 
		/* Cada char = 16 bits, codificação Java UTF-16 */
	}
	
	
}
