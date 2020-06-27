package br.edu.udc.formas;

public class FabricaFormas {
	
	public static FormaGeometrica fabricarForma(String forma) {
		int i = forma.indexOf(' ');
		String nome = forma.substring(0,i);
		FormaGeometrica formageometrica = null;
		
		if(nome.equals(Ponto.class.getSimpleName())) {
			formageometrica = fabricarPonto(forma.substring(i+1));
		}else if (nome.equals(Linha.class.getSimpleName())) {
			formageometrica = fabricarLinha(forma.substring(i + 1));
		}else if (nome.equals(Triangulo.class.getSimpleName())) {
			formageometrica = fabricarTriangulo(forma.substring(i + 1));
		}else if (nome.equals(Retangulo.class.getSimpleName())) {
			formageometrica = fabricarRetangulo(forma.substring(i + 1));
		}else if (nome.equals(Lapis.class.getSimpleName())) {
			formageometrica = fabricarLapis(forma.substring(i + 1));
		}
		return formageometrica;
	}
	
	private static Retangulo fabricarRetangulo(String retangulo) {
		int i =retangulo.indexOf(')');
		Ponto p1 = fabricarPonto(retangulo.substring(1,i+1));
		Ponto p2 = fabricarPonto(retangulo.substring(i+1));
		Retangulo r = new Retangulo(p1,p2);
		return r;
	}

	public static Triangulo fabricarTriangulo(String triangulo) {
		int i =triangulo.indexOf(')');
		Ponto p1 = fabricarPonto(triangulo.substring(1,i+1));
		triangulo = triangulo.substring(i+1);
		i =triangulo.indexOf(')');
		Ponto p2 = fabricarPonto(triangulo.substring(0,i+1));
		triangulo = triangulo.substring(i+1);
		i =triangulo.indexOf(')');
		Ponto p3 = fabricarPonto(triangulo.substring(0,i+1));
		Triangulo t = new Triangulo(p1,p2,p3);
		return t;
	}

	public static Linha fabricarLinha(String linha) {
		int i =linha.indexOf(')');
		Ponto p1 = fabricarPonto(linha.substring(1,i+1));
		Ponto p2 = fabricarPonto(linha.substring(i+1));
		Linha l = new Linha(p1,p2);
		return l;
	}

	public static Ponto fabricarPonto(String ponto) {
		int i = ponto.indexOf(';');
		int j = ponto.indexOf(')');
		int x = Integer.parseInt(ponto.substring(1,i));
		int y = Integer.parseInt(ponto.substring(i+2,j));
		Ponto p = new Ponto(x, y);
		return p;
	}
	
	public static Lapis fabricarLapis(String lapis) {
		Lapis lapisl = new Lapis();
		int i = lapis.indexOf(')');
		lapisl.adicionar(fabricarPonto(lapis.substring(1,i+1)));
		while(lapis.indexOf('}') != i+1) {
			lapis = lapis.substring(i+1);
			i = lapis.indexOf(')');
			lapisl.adicionar(fabricarPonto(lapis.substring(0,i+1)));
		}
		
		return lapisl;
	}
}
