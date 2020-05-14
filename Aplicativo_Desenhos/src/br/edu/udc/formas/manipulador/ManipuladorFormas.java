package br.edu.udc.formas.manipulador;

import java.awt.Graphics;

public interface ManipuladorFormas {
	public void desenhar(Graphics g);
	
	public void arrastar(int x, int y);
	public void mover(int x, int y);
	public boolean clicar(int x, int y);
	public void apertar (int x, int y);
	public boolean soltar(int x, int y);
}
