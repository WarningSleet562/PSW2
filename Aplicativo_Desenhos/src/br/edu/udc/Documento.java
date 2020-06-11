package br.edu.udc;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import br.edu.udc.formas.FabricaFormas;
import br.edu.udc.formas.FormaGeometrica;
import br.edu.udc.gui.PainelDesenho;
import br.edu.udc.gui.PainelFormaGeometrica;
import br.edu.udc.gui.PainelTexto;

public class Documento {

	private List<FormaGeometrica> listaFormas;
	private List<PainelFormaGeometrica> listaOuvintes;
	
	public Documento() {
		listaFormas = new LinkedList<FormaGeometrica>();
		listaOuvintes = new LinkedList<PainelFormaGeometrica>();
	}
	
	public void adicionarPainel(PainelFormaGeometrica painel) {
		listaOuvintes.add(painel);
	}
	
	public void removePainel(PainelFormaGeometrica painel) {
		listaOuvintes.remove(painel);
	}
	
	public void novaForma(FormaGeometrica forma) {
		listaFormas.add(forma);
		atualizarPaineis();
	}
	
	public void atualizarPaineis() {
		for (PainelFormaGeometrica painel : listaOuvintes)
			painel.atualizar();
	}
	
	public Iterator<FormaGeometrica> getIterador() {
		return listaFormas.iterator();
	}
	
	public void salvar(File file) {
		try {
			ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(file));
			
			for (FormaGeometrica f : listaFormas) {
				oss.writeObject(f);
			}
			
			oss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ler(File file) {
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			
			listaFormas.clear();
			
			FormaGeometrica formaAux = null;
			while (true) {
				formaAux = (FormaGeometrica) ois.readObject();
				listaFormas.add(formaAux);
			}
		} catch (EOFException endOfFileException) {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		atualizarPaineis();
	}

	public void salvarTxt(File file) {
		try {
			FileWriter fw = new FileWriter(file);
			
			for (FormaGeometrica f : listaFormas) {
				fw.append(f.getClass().getSimpleName() + " " + f.toString() + "\n");
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void lerTxt(File file) {
		try {
			Scanner sc = new Scanner(file);
			
			listaFormas.clear();
			
			FormaGeometrica formaAux = null;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				formaAux = FabricaFormas.fabricarForma(line);
				listaFormas.add(formaAux);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		atualizarPaineis();
	}
}