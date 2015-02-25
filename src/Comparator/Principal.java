package Comparator;

import java.util.ArrayList;

public class Principal {
	private ArrayList<ObjetoVO> listaObjetos = new ArrayList<ObjetoVO>();
	public Principal(){
		listaObjetos.add(new ObjetoVO("Pepito", "Araque"));
		listaObjetos.add(new ObjetoVO("Carlitos", "Velasco"));
		listaObjetos.add(new ObjetoVO("Jaimito", "Morales"));
		listaObjetos.add(new ObjetoVO("Julito", "Torres"));
		System.out.println("Valor: " + listaObjetos.indexOf(new ObjetoVO("Pepito")));
	}
	public static void main(String[] args) {
		new Principal();

	}

}
