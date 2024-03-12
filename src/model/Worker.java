package model;

import java.util.Random;

public class Worker {
	private String name;
	private String code;
	private int salary;
	private Job job;
	private String mail;
	private String lastname;

	public String getlastname() {
		return lastname;
	}

	public void setlastname(String apellido) {
		this.lastname = apellido;
	}

	public Worker() {
		generateCode();
	}

	private void generateCode() {
		int cod = 0;
		Random rd = new Random();
		cod = rd.nextInt(100000, 1000000);
		code = String.valueOf(cod);

	}

	public int CalcularSalary(int HorasTabajadas) {

		salary = HorasTabajadas * job.gethourValue();
		return salary;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job cargo) {
		this.job = cargo;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String codigo) {
		this.code = codigo;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int sueldo) {
		salary = sueldo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String correo) {
		this.mail = correo;
	}

}
