package br.edu.udc.formas.manipulador;

import java.awt.Graphics;

import br.edu.udc.formas.Lapis;
import br.edu.udc.formas.Ponto;

public class ManipuladorLapis implements ManipuladorFormas{

	private Lapis lapis;
	@SuppressWarnings("unused")
	private int estado;
	
	public ManipuladorLapis(Lapis l) {
		lapis = l;
		estado = 0;
	}
	
	@Override
	public void desenhar(Graphics g) {
		if(lapis.getTam() < 2)
			return;
		Ponto a = lapis.getPosicao(0);
		for(int i = 0; i < lapis.getTam(); i++) {
			Ponto b = lapis.getPosicao(i);
			g.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
			a = b;
		}
		
	}

	@Override
	public void arrastar(int x, int y) {
		if(estado == 1) {
			lapis.adicionar(new Ponto(x, y));
		}
	}

	@Override
	public void mover(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean clicar(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void apertar (int x, int y) {
		if(estado == 0) {
			lapis.adicionar(new Ponto(x, y));
			estado = 1;
		}
	}
	
	public boolean soltar(int x, int y) {
		if(estado == 1) {
			lapis.adicionar(new Ponto(x, y));
			estado = 0;
			return true;
		}
		return false;
	}

}
