package br.edu.udc7_3;

public class Retangulo extends Paralelogramo {
	
	public Retangulo(float ax, float bx, float ay, float by) {
		super(ax, bx, ay, by);
	}

	void mostrarDados() {
		System.out.print("\nArea do Retangulo : " + area());
	}
}
