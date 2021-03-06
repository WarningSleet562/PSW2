package br.edu.udc.formas.manipulador;

import java.awt.Graphics;

import br.edu.udc.formas.Ponto;

public class ManipuladorPonto implements ManipuladorFormas {

	private Ponto ponto;
	
	public ManipuladorPonto(Ponto p) {
		ponto = p;
	}
	
	@Override
	public void desenhar (Graphics g) {
		g.drawOval(ponto.getX(), ponto.getY(), 2, 2);
	}

	@Override
	public void arrastar(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mover(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean clicar(int x, int y) {
		ponto.setX(x);
		ponto.setY(y);
		return true;
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
