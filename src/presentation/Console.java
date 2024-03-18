package presentation;

import java.util.*;

//import javax.swing.JOptionPane;

import logic.Admin;
import logic.Controller;
import logic.Utilidades;
import model.Job;
import model.Material;
import model.Worker;

public class Console {
	private Scanner scanner = new Scanner(System.in);
	private int sw = 1, sw2 = 0;

	private long gastoTotal = 0;
	private String idTrabajador = "";
	Admin admin = new Admin();
	//private Job cantCargos[] = new Job[4];
	// private Material material[] = new Material[0];

	boolean outTrabajadores = false, casoUno = false;

	public void menu() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		admin.addJob(new Job("Jefe de obra", 12000));
		admin.addJob(new Job("Alba�il", 7500));
		admin.addJob(new Job("Soldador", 7000));
		admin.addJob(new Job("Electricista", 8000));
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		while (sw != 0) {
			try {
				System.out.println(

						"______________________________________________________________________________________________________________________________________");
				System.out.println("Bienvenido al programa de gestion de obras de construcci�n");
				System.out.println("1.Trabajadores");
				System.out.println("2.Materiales");
				System.out.println("3.Calcular precio total de la obra para el dia de hoy");
				System.out.println("0.Salir");
				System.out.println("Digite la opcion que desea ");
				System.out.println(
						"_______________________________________________________________________________________________________________________________________");
				sw = scanner.nextInt();
				switch (sw) {

				case 1:
					workerMenu();
					break;
				case 2:// Materiales//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					materialMenu();
					break;// Materiales fin

				case 3:// Calculo total de la obra
					calSalary();
					break;// Calculo total de la obra
				case 0:
					System.out.println("Gracias por usar nuestro softwar+e");
					break;

				default:
					System.out.println("Opcion Invalida por favor escoga una nuevamente");
					break;
				}
			} catch (Exception e) {
				System.out.println("Solo puede digitar numeros");
				scanner.next();
				sw = 1;
			}
		}
	}

	///////////////////////////////////// worker menu
	
	private void workerMenu() {
		while (sw2 != 5) {
			try {
				outTrabajadores = false;
				System.out.println(
						"________________________________________________________________________________________________________________________________________");
				System.out.println("GESTI�N DE TRABAJADORES");
				System.out.println("1.Crear Trabajadores");
				System.out.println("2.Crear cargos para los trabajadores");
				System.out.println("3.Mostrar informacion de los trabajadores");
				System.out.println("4.Calcular salario de los trabajadores");
				System.out.println("5.Volver al menu principal");
				System.out.println("Digite la opcion que desea");
				System.out.println(
						"________________________________________________________________________________________________________________________________________");
				sw2 = scanner.nextInt();

				switch (sw2) {

				case 1:
					createWorker();
					break;
				case 2:
					createJob();
					break;
				case 3:
					showWorkerInfo();
					break;
				case 4:
					calcWorkerSalary();
					break;
				case 5:
					break;
				default:
					System.out.println("Opcion invalida intentelo nuevamente");
					break;
				}
			} catch (Exception e) {
				System.out.println("Solo puede digitar numeros");
			}
			scanner.nextLine();

		} // fin switch opcion 1 men� principal
		sw2 = 0;
		// TODO Auto-generated method stub

	}

	private void createWorker() {
		// TODO Auto-generated method stub

		while (outTrabajadores == false) {
			Worker w = new Worker();
			scanner.nextLine();
			while (true) {

				try {
					System.out.println("Digite el nombre del trabajador ");
					w.setName(scanner.nextLine());
					int test = Integer.parseInt(w.getName());
					System.out.println("Recuerde que los nombres no tienen numeros");

				} catch (Exception e) {
					while (true) {
						try {
							System.out.println("Digite el apellido del trabajador");
							w.setlastname(scanner.nextLine());
							int test = Integer.parseInt(w.getlastname());
							System.out.println("Recuerde que los apellidos no tienen numeros");
						} catch (Exception i) {
							break;
						}
					}
					break;

				}

			}
			while (true) {
				try {
					while (true) {

						System.out.println("Seleccione el cargo del trabajador");
						for (int i = 0; i < admin.getListJob().size(); i++) {
							System.out.println((i + 1) + " " + admin.getListJob().get(i).getName());
						}
						//Sisaaa
						int opsCargo = scanner.nextInt();
						if (opsCargo > 0 && opsCargo <= admin.getListJob().size()) {
							w.setJob(admin.getListJob().get(opsCargo - 1));
							w.setSalary(0);
							admin.addWorker(w);
							Controller c = new Controller();
							c.addWorker(w);
	//aaaaaaaaa

							break;
						} else {
							System.out.println("Opcion invalida,intentelo nuevamente");
						}
					}

					break;
				} catch (Exception e) {
					System.out.println("Solo puede ingresar numeros"+e.getMessage());
					scanner.nextLine();
				}
			}

			while (true) {
				try {
					while (true) {
						System.out.println("�Desea ingresar la informacion de un nuevo trabajador?");
						System.out.println("1.Si");
						System.out.println("2.No");
						int opc = scanner.nextInt();
						if (opc == 1) {
							break;
						} else if (opc == 2) {
							outTrabajadores = true;
							break;
						} else {
							System.out.println("Opcion Invalida intente nuevamente");
						}
					}
					break;
				} catch (Exception e) {
					System.out.println("Solo puede ingresar numeros");
					scanner.nextLine();
				}
			}

		}
		casoUno = true;

	}

	private void createJob() {
		// TODO Auto-generated method stub
		boolean v = false;
		int opc3 = 0;

		while (v == false) {
			Job j= new Job();
			System.out.println("Recuerde que ya existen 4 cargos predeterminados");
			scanner.nextLine();
			while (true) {
				try {
					System.out.println("Digite el nombre del cargo");
					j.setName(scanner.nextLine());
					int testl = Integer.parseInt(j.getName());
					System.out.println("Nombre invalido");

				} catch (Exception e) {
					break;
				}

			}
			while (true) {
				try {
					while (true) {
						System.out.println("Digite el valor pagado por hora del cargo");
						j.sethourValue(scanner.nextInt());
						
						if (j.gethourValue() > 4833) {
							admin.addJob(j);
							break;
						} else {

							//JOptionPane.showMessageDialog(null,
									//"Salario inferior al salario minimo, por problemas legales ingrese un valor superior a $4833\r\n");

						}
					}
					break;
				} catch (Exception e) {
					System.out.println("Solo puede ingresar numeros");
					scanner.nextLine();
				}
			}

			System.out.println("Rol asignado");
			while (true) {
				try {
					while (true) {
						System.out.println("�Desea crear un nuevo rol?");
						System.out.println("1.Si");
						System.out.println("2.No");
						opc3 = scanner.nextInt();
						if (opc3 == 1) {
							break;
						} else if (opc3 == 2) {
							v = true;
							break;

						} else {
							System.out.println("Opcion invalida intente Nuevamente");
						}

					}
					break;
				} catch (Exception e) {
					System.out.println("Solo puede digitar numeros");
					scanner.nextLine();
				}

			}

		}
	}

	private boolean showWorkerInfo() {
		// TODO Auto-generated method stub
		if (admin.getListWorker().size() == 0) {
			System.out.println("Aun no han sido creado trabajadores");
			return false;
		}
		Worker aux = new Worker();
		System.out.println("La informacion de los trabajadores es: ");
		Collections.sort(admin.getListWorker(), Comparator.comparing(Worker::getName));
		for (int i = 0; i < admin.getListWorker().size(); i++) {
			System.out.println(admin.getListWorker().get(i).getName() + " " + admin.getListWorker().get(i).getlastname()
					+ " " + " Codigo: " + admin.getListWorker().get(i).getCode() + " " + "Cargo: "
					+ admin.getListWorker().get(i).getJob().getName() + " " + "Sueldo: "
					+ admin.getListWorker().get(i).getSalary());
		}
		return true;
	}

	private boolean calcWorkerSalary() {
		// TODO Auto-generated method stub
		boolean otroTrabajador = false;
		int opc2 = 0;
		if (casoUno == false) {
			System.out.println("Debe primero crear trabajadores en la opcion 1, para ingresar a esta opcion");
			return false;
		}
		boolean salarioTrabajador = false;
		while (otroTrabajador == false) {
			scanner.nextLine();
			System.out.println("Digite el ID del trabajador");
			idTrabajador = scanner.nextLine();
			while (true) {
				try {
					if (new Utilidades().buscarSecuencial(admin.getListWorker(), idTrabajador) != -1) {
						int pos = new Utilidades().buscarSecuencial(admin.getListWorker(), idTrabajador),
								numHorasTrabajadas;
						System.out.println("Ingrese la cantidad de horas trabajadas por el "
								+ admin.getListWorker().get(pos).getJob().getName() + " "
								+ admin.getListWorker().get(pos).getName());
						numHorasTrabajadas = scanner.nextInt();
						if (numHorasTrabajadas >= 0) {
							System.out.println("El salario del trabajador es $"
									+ admin.getListWorker().get(pos).CalcularSalary(numHorasTrabajadas));
							break;
						} else {
							System.out.println("No se deben ingresar numeros negativos");

						}

					} else {
						System.out.println("Codigo no registrado");
						break;
					}

				} catch (Exception e) {
					System.out.println("Debe ingresar numeros");
					scanner.nextLine();
				}

			}
			while (true) {
				try {
					while (true) {
						System.out.println("�Desea Calcular el salario de otro trabajador?");
						System.out.println("1.Si");
						System.out.println("2.No");
						opc2 = scanner.nextInt();
						if (opc2 == 1) {
							break;
						} else if (opc2 == 2) {
							otroTrabajador = true;
							break;
						} else {
							System.out.println("Opcion invalida intente nuevamente");
						}
					}
					break;
				} catch (Exception e) {
					System.out.println("Solo puede ingresar numeros");
					scanner.nextLine();
				}
			}

		}
		return true;
	}

	///////////////////////////////////////// material menu
	private void materialMenu() {
		// TODO Auto-generated method stub
		int wh = 1;
		while (wh != 4) {
			try {
				System.out.println(
						"________________________________________________________________________________________________________________________________________");
				System.out.println("GESTION DE LOS MATERIALES");
				System.out.println("1.Crear Materiales ");
				System.out.println("2.Calcular precio de los materiales");
				System.out.println("3.Eliminar materiales");
				System.out.println("4.Volver al menu principal");
				System.out.println("Digite la opcion que desea");
				System.out.println(
						"________________________________________________________________________________________________________________________________________");
				wh = scanner.nextInt();
				switch (wh) {
				case 1:

					createMaterials();
					break;
				case 2:
					calcPriceMat();
					break;

				case 3:
					deleteMaterials();
					break;
				case 4:
					break;

				default:
					System.out.println("Opcion Invailda intentelo nuevamente");
					break;
				}
			} catch (Exception e) {
				System.out.println("Solo se pueden ingresar numeros");
			}
			scanner.nextLine();

		}
		wh = 1;
	}

	private void deleteMaterials() {
		// TODO Auto-generated method stub
		int op4 = 0;
		if (admin.getListMaterH().size() > 0) {
			while (true) {
				try {

					while (true) {
						System.out.println("La lista de materiales se muestra a continuacion");
						for (Map.Entry<String, Material> entry : admin.getListMaterH().entrySet()) {
							String key = entry.getKey();
							Material value = entry.getValue();
							System.out.println(key + " " + value.getName() + " " + value.getAmount() + " "
									+ value.getUnidadMedida() + " $" + value.getPrecioTotal());

						}
						System.out.println(admin.getListMaterH().size() + 1 + ". Cancelar");
						System.out.println(
								"Ingrese el id del material a eliminar o cancelar para volver al menu anterior");
						String mat = scanner.nextLine();

						if (mat.equalsIgnoreCase("cancelar")) {
							break;
						} else {
							admin.getListMaterH().remove(mat);
							System.out.println("Material elminado con exito");
							break;
						}

					}
					break;
				} catch (Exception e) {
					System.out.println("El id ingresado no coincide con ningun material");
					scanner.nextLine();
				}
			}

		} else {
			System.out.println("Para ingresar a esta opcion debe crear materiales primero");
		}

	}

	private void createMaterials() {
		// TODO Auto-generated method stub
		boolean otroMaterial = false;
		while (otroMaterial == false) {
			Material m= new Material();
			scanner.nextLine();
			System.out.println("Digite el nombre del material");
			m.setName(scanner.nextLine());
			while (true) {
				try {
					System.out.println("Digite la unidad de medida de compra");
					m.setUnidadMedida(scanner.nextLine());
					int test = Integer.parseInt(m.getUnidadMedida());
					System.out.println("No se pueden digitar numeros");
				} catch (Exception e) {
					break;
				}
			}
			double cantidadDiaria = 0;
			while (true) {
				try {
					System.out.println("�Cuanto compra en un dia?");
					cantidadDiaria = scanner.nextDouble();
					if (cantidadDiaria <= 0) {
						System.out.println("la cantidad de material adquirido debe ser mayor a 0");
					} else {
						break;
					}

				} catch (Exception e) {
					System.out.println("No se pueden digitar letras");
					scanner.nextLine();
				}
			}
			while (true) {
				try {
					System.out.println("�Cuanto cuesta la compra del material total?");
					double compraDiaria = scanner.nextDouble();
					m.calculoMaterial(cantidadDiaria, compraDiaria);
					admin.addMaterial(m);
					System.out.println(
							("El precio por unidad del material es: ") + m.getUniteprice());
					break;
				} catch (Exception e) {
					System.out.println("No puede digitar letras");
					scanner.nextLine();
				}
			}
			while (true) {
				try {
					while (true) {
						int opc3 = 0;
						System.out.println("�Desea crear otro material?");
						System.out.println("1.Si");
						System.out.println("2.No");
						opc3 = scanner.nextInt();
						if (opc3 == 1) {
							break;
						} else if (opc3 == 2) {
							otroMaterial = true;
							break;
						} else {
							System.out.println("Opcion invalida intente nuevamente");
						}
					}
					break;
				} catch (Exception e) {
					System.out.println("Solo se pueden digitar numeros");
					scanner.nextLine();
				}
			}

		}
	}

	private void calcPriceMat() {
		// TODO Auto-generated method stub
		scanner.nextLine();
		if (admin.getListMaterH().size() > 0) {
			String sel="";

			while (true) {
				while (true) {
					try {
						System.out.println("ingrese id del material que desea comprar ");
						for(Map.Entry<String, Material> entry: admin.getListMaterH().entrySet()) {
							String key =entry.getKey();
							Material value = entry.getValue();
							System.out.println(key+" "+ value.getName());
						}
						sel= scanner.nextLine();
						break;
					} catch (Exception e) {
						System.out.println("Solo puede ingresar numeros");
						scanner.nextLine();
					}
				}
				while (true) {
					try {
							System.out.println("Ingrese la cantidad de " + admin.getListMaterH().get(sel).getUnidadMedida()
									+ " " + "que va a adquirir");
							double cantCompra = scanner.nextDouble();
							admin.getListMaterH().get(sel).setAmount(cantCompra);
							admin.getListMaterH().get(sel).calcularPrecioMaterial();
							System.out.println(admin.getListMaterH().get(sel).getPrecioTotal());
							break;

						
					} catch (Exception e) {
						System.out.println("Opcion invalida intentelo nuevamente");
						scanner.nextLine();
						break;
						
					}
				}
				break;
			}
		} else {
			System.out.println("Para entrar en esta opcion,debe crear materiales primero");
		}

	}

	////////////////////////////////////////
	private void calSalary() {
		double dayExpense = admin.calcDailyExpense();
		//JOptionPane.showMessageDialog(null,
				//"$" + gastoTotal + " es el gasto total de la obra generado el " + new Date());
		System.out.println("$" + gastoTotal + " es el gasto total de la obra generado el " + new Date());

	}
}

