package br.edu.udc7_1;

public class Ponto2D {
	private double x;
	private double y;

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Ponto2D() {
		x = 0.0;
		y = 0.0;
	}

	public Ponto2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public Ponto2D clone() {
		return new Ponto2D(x, y);
	}

	public double distancia(Ponto2D p) {
		return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
	}

	@Override
	public String toString() {
		return String.format("(%.2f; %.2f)", x, y);
	}
	
	public boolean equals(Object obj) { //Comparando objetos
		if(obj == null)
			return false;
		if(obj.getClass().equals(Ponto2D.class))
			return false;
		if(obj == this)//Comparando com ele mesmo
			return true;
		
		if(this.x != ((Ponto2D)obj).x)
			return false;
		if(this.y != ((Ponto2D)obj).y)
			return false;
		
		return true;
	}

	public Ponto2D centro() {
		return clone();
	}

	public double area() {
		return 0;
	}

	public double perimetro() {
		return 0;
	}

	public double base() {
		return 0;
	}

	public double altura() {
		return 0;
	}
}