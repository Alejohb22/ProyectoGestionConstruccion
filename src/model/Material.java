package model;

import java.util.Random;

public class Material {
	private String Id;
	private double uniteprice;
	private String name;
	private double amount;
	private String measureUnit;
	private double pricetotal;
	
	
	public Material() {
		// TODO Auto-generated constructor stub
		int aux = new Random().nextInt(10000);
		Id= String.valueOf(aux);
		
	}
	
	

	public String getId() {
		return Id;
	}



	public void setId(String id) {
		Id = id;
	}



	public double getPrecio() {
		return uniteprice;
	}

	public void setPrecio(double precio) {
		this.uniteprice = precio;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double cantidad) {
		amount = cantidad;
	}

	public double getUniteprice() {
		return uniteprice;
	}

	public void setUniteprice(int precioUnidad) {
		this.uniteprice = precioUnidad;
	}
	
	public String getUnidadMedida() {
		return measureUnit;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.measureUnit = unidadMedida;
	}

	public double getPrecioTotal() {
		return pricetotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.pricetotal = precioTotal;
	}

	public void calculoMaterial(double cantidadCompra, double PrecioCompra) {
		uniteprice = PrecioCompra / cantidadCompra;
	}

	public void calcularPrecioMaterial() {
		pricetotal = uniteprice * amount;
	}
}

