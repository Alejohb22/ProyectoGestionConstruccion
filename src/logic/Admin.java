package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import model.Job;
import model.Material;
import model.Worker;
public class Admin {
	
	
	
	private ArrayList<Worker> listWorker = new ArrayList<Worker>();
	private HashMap<String, Material> listMaterH= new HashMap<String, Material>();
	private ArrayList<Job> listJob= new ArrayList<Job>();
	
	
	
	
	public void addJob (Job j) {
		listJob.add(j);
	}
	
	public ArrayList<Job> getListJob() {
		return listJob;
	}

	public void setListJob(ArrayList<Job> listJob) {
		this.listJob = listJob;
	}

	public void addMaterial(Material m) {
		listMaterH.put(m.getId(), m);
	}
	
	public HashMap<String, Material> getListMaterH() {
		return listMaterH;
	}


	public void setListMaterH(HashMap<String, Material> listMaterH) {
		this.listMaterH = listMaterH;
	}


	public void addWorker(Worker w) {
		listWorker.add(w);
	}

	public ArrayList<Worker> getListWorker() {
		return listWorker;
	}

	public void setListWorker(ArrayList<Worker> listWorker) {
		this.listWorker = listWorker;
	}
	
	
	public double calcDailyExpense() {
		Double dayExpense = 0.0;
		for (Worker worker : listWorker) {
			dayExpense+= worker.getSalary();
		}
		for (Entry<String, Material> entry : listMaterH.entrySet()) {
			String key = entry.getKey();
			Material val = entry.getValue();
			dayExpense+=val.getPrecioTotal();
		}
		
		return dayExpense;
	}
	 
	
	
	
	
}
