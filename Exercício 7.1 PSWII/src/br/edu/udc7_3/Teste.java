package br.edu.udc7_3;

import br.edu.udc7_1.Ponto2D;

public class Teste {

	public static void main(String[] args) {
		Trapezio Tra = new Trapezio(new Ponto2D(1,3), new Ponto2D(4,5));
		Tra.mostrarDados();
		
		Paralelogramo Par = new Paralelogramo(2, 7, 10, 12);
		Par.mostrarDados();
		
		Retangulo Ret  = new Retangulo(1,2,3,4);
		Ret.mostrarDados();
		
		Quadrado qua = new Quadrado(2, 4, 6, 8);
		qua.mostrarDados();
	}
}
