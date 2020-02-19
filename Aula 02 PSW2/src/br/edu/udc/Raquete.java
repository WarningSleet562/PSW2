package br.edu.udc;

import java.io.Serializable;

public class Raquete implements Serializable{
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
		return comprimento + "/" + cor;
	}
}

