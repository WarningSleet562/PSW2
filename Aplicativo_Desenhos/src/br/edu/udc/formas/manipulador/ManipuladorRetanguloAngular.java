package br.edu.udc.formas.manipulador;

import java.awt.Graphics;

import br.edu.udc.formas.Ponto;
import br.edu.udc.formas.RetanguloAngular;

public class ManipuladorRetanguloAngular implements ManipuladorFormas{

	private RetanguloAngular retangulo;
	private int estado;

	public ManipuladorRetanguloAngular(RetanguloAngular r) {
		retangulo = r;
		estado = 0;
	}
	
	@Override
	public void desenhar(Graphics g) {
		g.drawLine(retangulo.getA().getX(), retangulo.getA().getY(), retangulo.getB().getX(), retangulo.getB().getY());
		g.drawLine(retangulo.getB().getX(), retangulo.getB().getY(), retangulo.getC().getX(), retangulo.getC().getY());
		g.drawLine(retangulo.getC().getX(), retangulo.getC().getY(), retangulo.getD().getX(), retangulo.getD().getY());
		g.drawLine(retangulo.getD().getX(), retangulo.getD().getY(), retangulo.getA().getX(), retangulo.getA().getY());
		
	}

	@Override
	public void arrastar(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override

	public boolean clicar(int x, int y) {
		switch(estado){
		case 0:
			retangulo.setA(new Ponto(x, y));
			retangulo.setB(new Ponto(x, y));
			retangulo.setC(new Ponto(x, y));
			retangulo.setD(new Ponto(x, y));
			estado++;
			return false;
		case 1:
			retangulo.setB(new Ponto(x, y));
			retangulo.setC(new Ponto(x, y));
			estado++;
			return false;
		case 2:
			Ponto c = new Ponto(0,0);
			Ponto d = new Ponto(0,0);
			
			calcPontosCeD(x, y, c, d);
			
			retangulo.setC(c);
			retangulo.setD(d);

			estado++;
			return true;
		}
		return false;  
	}
	
	private void calcPontosCeD(int x, int y, Ponto c, Ponto d) {
		float m = ((retangulo.getB().getY() - retangulo.getA().getY()) / (retangulo.getB().getX() - retangulo.getA().getX()));
		float h = (y - m * x);
		
		int xc = (int)((m / (m*m + 1) ) * (retangulo.getB().getX() / m + retangulo.getB().getY() - h));
		int xd = (int)((m / (m*m + 1) ) * (retangulo.getA().getX() / m + retangulo.getA().getY() - h));
		
		int yc = (int)((-1 / m) * (xc - retangulo.getB().getX()) + retangulo.getB().getY());
		int yd = (int)((-1 / m) * (xd - retangulo.getA().getX()) + retangulo.getA().getY());
		c.setX(xc);
		c.setY(yc);
		d.setX(xd);
		d.setY(yd);
	}


	@Override
	public void mover(int x, int y) {
		switch (estado) {
		case 1:
			retangulo.setB(new Ponto(x, y));
			retangulo.setC(new Ponto(x, y));
			break;

		case 2:
			Ponto c = new Ponto(0,0);
			Ponto d = new Ponto(0,0);
			
			calcPontosCeD(x, y, c, d);
			
			retangulo.setC(c);
			retangulo.setD(d);
			break;
		default:
			break;
		}
		
	}

	@Override
	public boolean soltar(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void apertar(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
