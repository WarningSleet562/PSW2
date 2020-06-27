package br.edu.udc.formas.manipulador;

import java.awt.Graphics;

import br.edu.udc.formas.Ponto;
import br.edu.udc.formas.Retangulo;

public class ManipuladorRetangulo implements ManipuladorFormas {

	private Retangulo retangulo;
	private int estado;
	
	public ManipuladorRetangulo(Retangulo r) {
		retangulo = r;
		estado = 0;
	}
	
	@Override
	public void desenhar(Graphics g) {
		g.drawLine(retangulo.getA().getX(), retangulo.getA().getY(), 
				retangulo.getA().getX(), retangulo.getB().getY());
		g.drawLine(retangulo.getA().getX(), retangulo.getB().getY(), 
				retangulo.getB().getX(), retangulo.getB().getY());
		g.drawLine(retangulo.getB().getX(), retangulo.getB().getY(), 
				retangulo.getB().getX(), retangulo.getA().getY());
		g.drawLine(retangulo.getB().getX(), retangulo.getA().getY(), 
				retangulo.getA().getX(), retangulo.getA().getY());
	}

	@Override
	public void arrastar(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mover(int x, int y) {
		if(estado == 1)
			retangulo.setB(new Ponto(x, y));
	}

	@Override
	public boolean clicar(int x, int y) {
		switch(estado) {
		case 0:
			retangulo.setA(new Ponto(x, y));
			retangulo.setB(new Ponto(x, y));
			estado = 1;
			return false;
		case 1:
			retangulo.setB(new Ponto(x, y));
			estado = 2;
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
