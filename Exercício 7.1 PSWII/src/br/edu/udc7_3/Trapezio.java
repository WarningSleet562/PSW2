package br.edu.udc7_3;

import br.edu.udc7_1.Ponto2D;

public class Trapezio extends Quadrilatero {
	
	public Trapezio(float ax, float bx, float ay, float by) {
		super(ax, bx, ay, by);
	}
	
	public Trapezio(Ponto2D a, Ponto2D b) {
		super(a, b);
	}

	void mostrarDados() {
		System.out.print("Area do Trapezio : " + area());
	}
}
