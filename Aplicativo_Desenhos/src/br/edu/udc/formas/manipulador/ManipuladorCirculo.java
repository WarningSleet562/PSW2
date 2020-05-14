package br.edu.udc.formas.manipulador;

import java.awt.Graphics;

import br.edu.udc.formas.Circulo;
import br.edu.udc.formas.Ponto;

public class ManipuladorCirculo implements ManipuladorFormas {

	private Circulo circulo;
	private int raio;
	private int estado;
	
	public ManipuladorCirculo(Circulo c) {
		circulo = c;
		raio = 0;
		estado = 0;
	}
	
	@Override
	public void desenhar(Graphics g) {
		int x = Math.abs(circulo.getA().getX() - circulo.getB().getX());
		int y = Math.abs(circulo.getA().getY() - circulo.getB().getY());
		
		raio = (int) Math.sqrt((x*x) + (y*y));
		
		g.drawOval(circulo.getA().getX() - raio, circulo.getA().getY() - raio, raio*2, raio*2);

	}

	@Override
	public void arrastar(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mover(int x, int y) {
		if (estado == 1)
			circulo.setB(new Ponto(x, y));

	}

	@Override
	public boolean clicar(int x, int y) {
		switch(estado) {
		case 0:
			circulo.setA(new Ponto(x, y));
			circulo.setB(new Ponto(x, y));
			estado = 1;
			return false;
		case 1:
			circulo.setB(new Ponto(x, y));
			estado = 0;
			return true;
		}
		return false;
	}

	@Override
	public void apertar(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean soltar(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
