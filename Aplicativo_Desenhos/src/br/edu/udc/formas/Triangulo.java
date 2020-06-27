package br.edu.udc.formas;

import br.edu.udc.formas.manipulador.ManipuladorTriangulo;

public class Triangulo implements FormaGeometrica {
	private static final long serialVersionUID = 1L;
	private Ponto a;
	private Ponto b;
	private Ponto c;
	
	private transient ManipuladorTriangulo manipulador = null;
	
	public Triangulo(Ponto a, Ponto b, Ponto c) {
		this.a = new Ponto(a);
		this.b = new Ponto(b);
		this.c = new Ponto(c);
	}
	
	public Triangulo(Triangulo t) {
		a = new Ponto(t.a);
		b = new Ponto(t.b);
		c = new Ponto(t.c);
	}
	
	public void setA(Ponto a) {
		this.a = a.clone();
	}
	
	public void setB(Ponto b) {
		this.b = b.clone();
	}
	
	public void setC(Ponto c) {
		this.c = c.clone();
	}
	
	public Ponto getA() {
		return a;
	}
	
	public Ponto getB() {
		return b;
	}
	
	public Ponto getC() {
		return c;
	}
	
	@Override
	public Ponto centro() {
		return new Ponto((a.getX()+b.getX()+c.getX()) / 3, (a.getY()+b.getY()+c.getY()) / 3);
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double perimetro() {
		return a.distancia(b) + b.distancia(c) + c.distancia(a);
	}

	@Override
	public double base() {
		return (a.distancia(b));
	}

	@Override
	public double altura() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double distancia(FormaGeometrica f) {
		Ponto cf = f.centro();
		Ponto ct = centro();
		
		int dx = ct.getX() - cf.getX();
		int dy = ct.getY() - cf.getY();
		
		return Math.sqrt(dx*dx + dy*dy);
	}

	public String toString() {
		return String.format("[%s%s%s]", this.a, this.b, this.c);
	}
	
	public String getNome() {
		return String.format("Triangulo");
	}
	
	@Override
	public Ponto getEnd() {
		return new Ponto(a.getX() > b.getX() ? (a.getX() > c.getX() ? a.getX() : (b.getX() > c.getX() ? b.getX() : c.getX())) : b.getX() > c.getX() ? b.getX() : c.getX(),
				         a.getY() > b.getY() ? (a.getY() > c.getY() ? a.getY() : (b.getY() > c.getY() ? b.getY() : c.getY())) : b.getY() > c.getY() ? b.getY() : c.getY());

	}

	@Override
	public Ponto getStart() {
		return new Ponto(a.getX() < b.getX() ? (a.getX() < c.getX() ? a.getX() : (b.getX() < c.getX() ? b.getX() : c.getX())) : b.getX() < c.getX() ? b.getX() : c.getX(),
						 a.getY() < b.getY() ? (a.getY() < c.getY() ? a.getY() : (b.getY() < c.getY() ? b.getY() : c.getY())) : b.getY() < c.getY() ? b.getY() : c.getY());				
	}

	@Override
	public FormaGeometrica clone() {
		return new Triangulo(this);
	}

	@Override
	public ManipuladorTriangulo getManipulador() {
		if(manipulador == null)
			manipulador = new ManipuladorTriangulo(this); 
		return manipulador; 
	}
	
}
