package model;


public class Job {
	private String name;
	private int hourValue;

	public Job() {
		name = "";
		hourValue = 0;
	}

	public Job(String nombre, int valorHora) {
		this.name = nombre;
		this.hourValue = valorHora;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public int gethourValue() {
		return hourValue;
	}

	public void sethourValue(int valorHora) {
		this.hourValue = valorHora;
	}

	public void valorxHora(int tiempo) {
	}

	@Override
	public String toString() {
		return "Job{" +
				"name='" + name + '\'' +
				", hourValue=" + hourValue +
				'}';
	}
}
