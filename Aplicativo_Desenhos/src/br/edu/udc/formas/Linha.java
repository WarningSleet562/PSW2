package br.edu.udc.formas;

import java.awt.Graphics;

public class Linha implements FormaGeometrica {

	private Ponto a;
	private Ponto b;
	
	public Linha(Ponto a, Ponto b) {
		this.a = a.clone();
		this.b = b.clone();
	}
	
	public Linha(Linha l) {
		this.a = l.a.clone();
		this.b = l.b.clone();
	}
	
	public void setA(Ponto a) {
		this.a = a.clone();
	}
	
	public void setB(Ponto b) {
		this.b = b.clone();
	}
	
	@Override
	public Ponto centro() {
		return new Ponto((a.getX()+b.getX())/2, (a.getY()+b.getY())/2);
	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimetro() {
		return 0;
	}

	@Override
	public double base() {
		return Math.abs(a.getX() - b.getX());
	}

	@Override
	public double altura() {
		return Math.abs(a.getY() - b.getY());
	}
	
	@Override
	public double distancia(FormaGeometrica f) {
		Ponto cf = f.centro();
		Ponto cl = centro();
		
		int dx = cl.getX() - cf.getX();
		int dy = cl.getY() - cf.getY();
		
		return Math.sqrt(dx*dx + dy*dy);
	}

	@Override
	public String toString() {
		return String.format("A: [%d, %d] / B: [%d, %d]", a.getX(), a.getY(), b.getX(), b.getY());
	}
	
	public String getNome() {
		return "Linha";
	}
	
	@Override
	public Ponto getEnd() {
		return new Ponto(a.getX() > b.getX() ? a.getX() : b.getX(),
						 a.getY() > b.getY() ? b.getY() : b.getY());
	}

	@Override
	public Ponto getStart() {
		int x;
		int y;
		if(a.getX() < b.getX())
			x = a.getX();
		else 
			x = b.getX();
		if(a.getY() < b.getY())
			y = a.getY();
		else
			y = b.getY();
		
		return new Ponto(x,y);
	}

	@Override
	public FormaGeometrica clone() {
		return new Linha(this);
	}

	@Override
	public void desenhar (Graphics g) {
		g.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
	}
	
}