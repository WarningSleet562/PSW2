package br.edu.udc.formas;

import br.edu.udc.formas.manipulador.ManipuladorFormas;
import br.edu.udc.formas.manipulador.ManipuladorRetangulo;

public class Retangulo implements FormaGeometrica {
	private static final long serialVersionUID = 1L;
	private Ponto a;
	private Ponto b;
	
	private transient ManipuladorRetangulo manipulador = null;
	
	public Retangulo(Ponto a, Ponto b) {
		this.a = a.clone();
		this.b = b.clone();
	}
	
	public Retangulo(Retangulo r) {
		this.a = r.a.clone();
		this.b = r.b.clone();
	}
	
	public void setA(Ponto a) {
		this.a = a;
	}
	
	public void setB(Ponto b) {
		this.b = b;
	}
	
	public Ponto getA() {
		return a;
	}
	
	public Ponto getB() {
		return b;
	}
	
	@Override
	public Ponto centro() {
		return new Ponto((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
	}

	@Override
	public double area() {
		return (base() * altura());
	}

	@Override
	public double perimetro() {
		return ((altura() * 2) + (base() * 2));
	}

	@Override
	public double base() {
		return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()));
	}

	@Override
	public double altura() {
		return Math.sqrt((a.getY() - b.getY()) * (a.getY() - b.getY()));
	}

	@Override
	public double distancia(FormaGeometrica f) {
		Ponto cf = f.centro();
		Ponto cl = centro();
		int dx = cl.getX() - cf.getX();
		int dy = cl.getY() - cf.getY();

		return Math.sqrt(dx * dx + dy * dy);
	}

	public String toString() {
		return String.format("[%s%s%s%s]", a, new Ponto(b.getX(), a.getY()), b, new Ponto(a.getX(), b.getY()));
	}
	
	@Override
	public String getNome() {
		return String.format("Retangulo");
	}

	@Override
	public Ponto getEnd() {
		return new Ponto(a.getX() > b.getX() ? a.getX() : b.getX(), a.getY() > b.getY() ? a.getY() : b.getY());
	}

	@Override
	public Ponto getStart() {
		return new Ponto(a.getX() < b.getX() ? a.getX() : b.getX(), a.getY() < b.getY() ? a.getY() : b.getY());
	}

	@Override
	public FormaGeometrica clone() {
		return new Retangulo(this);
	}

	@Override
	public ManipuladorFormas getManipulador() {
		if(manipulador == null)
			manipulador = new ManipuladorRetangulo(this);
		return manipulador;
	}

}
