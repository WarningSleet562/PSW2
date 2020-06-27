package br.edu.udc.formas;

import br.edu.udc.formas.manipulador.ManipuladorRetanguloAngular;

public class RetanguloAngular implements FormaGeometrica{

	private static final long serialVersionUID = 1L;
	private Ponto a;
	private Ponto b;
	private Ponto c;
	private Ponto d;

	private transient ManipuladorRetanguloAngular manipulador;
	
	public RetanguloAngular(RetanguloAngular r) {
		this.a = r.a.clone();
		this.b = r.b.clone();
		this.c = r.c.clone();
		this.d = r.d.clone();
	}
	
	public RetanguloAngular(Ponto a, Ponto b, Ponto c, Ponto d) {
		this.a = a.clone();
		this.b = b.clone();
		this.c = c.clone();
		this.d = d.clone();
	} 
	
	public void setA(Ponto a) {
		this.a = a;
	}
	public Ponto getA() {
		return a;
	}
	public void setB(Ponto b) {
		this.b = b;
	}
	public Ponto getB() {
		return b;
	}
	public void setC(Ponto c) {
		this.c = c;
	}
	public Ponto getC() {
		return c;
	}
	public void setD(Ponto d) {
		this.d = d;
	}
	public Ponto getD() {
		return d;
	}
	
	@Override
	public Ponto centro() {
		return null;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double perimetro() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double base() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double altura() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double distancia(FormaGeometrica f) {
		Ponto cf = f.centro();
		Ponto cl = centro();
		int dx = cl.getX() - cf.getX();
		int dy = cl.getY() - cf.getY();

		return Math.sqrt(dx * dx + dy * dy);
	}
	
	@Override
	public String toString() {
		return String.format("A: [%d, %d] / B: [%d, %d]", a.getX(), a.getY(), b.getX(), b.getY());
	}

	@Override
	public String getNome() {
		return String.format("RetanguloAngular");
	}

	@Override
	public Ponto getEnd() {
		return new Ponto(a.getX() > b.getX() ? (a.getX() > c.getX() ? (a.getX() > d.getX() ? a.getX() : d.getX()): c.getX()) : (b.getX() > c.getX() ? (b.getX() > d.getX() ? b.getX() : d.getX()): c.getX())
				
				, a.getY());
	}

	@Override
	public Ponto getStart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaGeometrica clone() {
		return new RetanguloAngular(this);
	}

	@Override
	public ManipuladorRetanguloAngular getManipulador() {
		if(manipulador == null)
			manipulador = new ManipuladorRetanguloAngular(this);
		return manipulador;
	}

}
