package br.edu.udc.formas;

import br.edu.udc.formas.manipulador.ManipuladorFormas;
import br.edu.udc.formas.manipulador.ManipuladorCirculo;

public class Circulo implements FormaGeometrica {
	private static final long serialVersionUID = 1L;
	
	private Ponto a;
	private Ponto b;

	private ManipuladorCirculo manipulador = null;
	
	public Circulo(Circulo c) {
		this.a = c.a.clone();
		this.b = c.b.clone();
	}
	
	public Circulo(Ponto a, Ponto b) {
		this.a = a.clone();
		this.b = b.clone();
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ponto getEnd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ponto getStart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaGeometrica clone() {
		return new Circulo(this);
	}

	@Override
	public ManipuladorFormas getManipulador() {
		if (manipulador == null)
			manipulador = new ManipuladorCirculo(this);
		return manipulador;
	}

}
