package br.edu.udc7_1;

import java.util.Scanner;

public class Poligono {
	private Ponto2D pontos[] = new Ponto2D[10];
	private int lados;
	
	Scanner sc = new Scanner(System.in);

	public Poligono(int lados) {
		this.lados = lados;
		
		for (int i = 0; i < lados; i++) {
			pontos[i] = new Ponto2D(LerX(i),LerY(i));
		}
	}
	
	public double LerX(int i) {
		System.out.printf("Entre com a coordenada X do ponto: %d \n", ++i);
		return sc.nextDouble();
	}
	
	public double LerY(int i) {
		System.out.printf("Entre com a coordenada Y do ponto: %d\n", ++i);
		return sc.nextDouble();
	}

	public Ponto2D centro() {
		double x = 0, y = 0;

		for (int i = 0; i < lados; i++) {
			x += pontos[i].getX();
			y += pontos[i].getY();
		}

		x /= lados;
		y /= lados;

		return new Ponto2D(x, y);
	}
	
	public String getPontos(int i) {
		return pontos[i].toString();
	}

	public void setPontos(Ponto2D[] pontos) {
		this.pontos = pontos;
	}

	public int getLados() {
		return lados;
	}

	public void setLados(int lados) {
		this.lados = lados;
	}
}