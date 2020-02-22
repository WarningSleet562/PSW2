package br.edu.udc7_3;

public class Paralelogramo extends Trapezio {

	public Paralelogramo(float ax, float bx, float ay, float by) {
		super(ax, bx, ay, by);
	}

	void mostrarDados() {
		System.out.print("\nArea do Paralelogramo : " + area());
	}
}
