package logic;

import java.util.ArrayList;

import model.Material;
import model.Worker;

public class Utilidades{
	public int buscarSecuencial(ArrayList<Worker> list, String valor) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getCode().equalsIgnoreCase(valor)) {
				return i;//
			}
		}
		return -1;
	}
	public Material[] reducirArray (Material[] ListaMateriales) {
		int a=0;
		Material[] materialAuxiliar=new Material[ListaMateriales.length-1];
		for (int i = 0; i < ListaMateriales.length; i++) {
			if (ListaMateriales[i].getName()!=null) {
				materialAuxiliar[a]=ListaMateriales[i];
				a++;
			}
		}
		ListaMateriales=materialAuxiliar;
		return ListaMateriales;
	}

}

