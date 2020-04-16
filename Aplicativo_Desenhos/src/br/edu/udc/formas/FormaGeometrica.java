package br.edu.udc.formas;

import java.io.Serializable;

import br.edu.udc.formas.manipulador.ManipuladorFormas;

public interface FormaGeometrica extends Serializable{
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
