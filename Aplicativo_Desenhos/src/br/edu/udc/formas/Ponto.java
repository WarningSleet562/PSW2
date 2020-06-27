package br.edu.udc.formas;

import br.edu.udc.formas.manipulador.ManipuladorPonto;

public class Ponto implements FormaGeometrica {
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	
	private transient ManipuladorPonto manipulador = null;
	
	public Ponto() {
		this.x = 0;
		this.y = 0;
	}
	
	public Ponto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Ponto(Ponto p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public Ponto centro() {
		return (Ponto) this.clone();
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
		return 0;
	}

	@Override
	public double altura() {
		return 0;
	}
	
	@Override
	public double distancia(FormaGeometrica f) {
		Ponto c = f.centro();
		int dx = x - c.x;
		int dy = y - c.y;
		
		return Math.sqrt(dx*dx + dy*dy);
	}

	@Override
	public String toString() {
		return String.format("(%s; %s)", this.x,this.y);
	}
	
	public String getNome() {
		return String.format("Ponto");
	}
	
	@Override
	public Ponto getEnd() {
		return new Ponto(this);
	}

	@Override
	public Ponto getStart() {
		return new Ponto(this);
	}

	@Override
	public Ponto clone() {
		return new Ponto(x, y);
	}
	
	@Override
	public ManipuladorPonto getManipulador() {
		if(manipulador == null)
			manipulador = new ManipuladorPonto(this);
		return manipulador; 
	}
}
