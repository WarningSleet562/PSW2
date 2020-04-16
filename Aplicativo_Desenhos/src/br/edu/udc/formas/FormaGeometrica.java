package br.edu.udc.formas;

import java.awt.Graphics;

import br.edu.udc.formas.manipulador.ManipuladorFormas;

public interface FormaGeometrica {
	Ponto centro();
	double area();
	double perimetro();
	double base();
	double altura();
	double distancia(FormaGeometrica p);
	
	String toString();
	String getNome();
	
	Ponto getEnd();
	Ponto getStart();
	
	FormaGeometrica clone();
	
	public ManipuladorFormas getManipulador();
}
