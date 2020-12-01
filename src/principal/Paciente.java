package principal;

import java.util.Scanner;

import clases.MapaTriple;
import excepciones.YaExiste;
import excepciones.NoExiste;

public class Paciente {

	public static void main(String[] args) {
		
		lanzarMenu();
	}

	
//	Método que lanza el menú de opciones.
	private static void lanzarMenu() {
		
		MapaTriple pacientes=new MapaTriple();
		int menu,submenu,seguridad_social;
		Scanner sc=new Scanner(System.in);
		String nombre,dni;
		
		do { //INICIO MENU PRINCIPAL
			
			
			System.out.println("------Bienvenido al menu------\n\n");
			System.out.println("1: Añadir paciente");
			System.out.println("2: Eliminar paciente");
			System.out.println("3: Mostrar pacientes");
			System.out.println("0: Salir");
			
			System.out.print("Introduzca una opcion: ");
			menu=sc.nextInt();
			
			
			switch (menu) {
			
				case 0:
					System.out.println("Hasta luego.");
					break;
			
				case 1: //OPCION 1
					
					System.out.println("------Ha elegido Añadir Paciente------\n\n");
					
				
						
					System.out.print("Introduce el dni con letra del paciente: ");
					dni=sc.next(); //Nos aseguramos de que se guarde solo el dni hasta antes de un espacio.
					
					sc.nextLine(); //En caso de que introduzca algun espacio y caracteres limpiamos el buffer
					System.out.print("Introduce el numero de la seguridad social del paciente: ");
					seguridad_social=sc.nextInt();
					
//					Limpiamos el buffer 
					sc.nextLine();
					
					System.out.print("Introduce el nombre del paciente (Si es compuesto, insertar el nombre delimitado de un -): ");
					nombre=sc.next(); //Nos aseguramos de que solo se guarde el nombre y no un nombre con apellidos.
					
					
					System.out.println("\nSolo tendremos en cuenta el nombre...");
					
					
					
					try {
						pacientes.inserta(dni, seguridad_social, nombre);
					}catch(YaExiste e) {
						System.out.println(e.getMessage());
					}finally {	
						
						System.out.println("\nRedirigiendo al menu principal...");
						esperar();
						
					}
								
						
						
					break; //SE SALE DE LA OPCION 1 y vuelve a ejecutarse el menu principal
					
				case 2: //OPCION 2
					
					
					System.out.println("------Ha elegido Eliminar Paciente------\n\n");
					
					System.out.print("Introduce el dni con letra del paciente: ");
					dni=sc.next();
					
					System.out.print("Introduce el numero de la seguridad social del paciente: ");
					seguridad_social=sc.nextInt();
					
					try {
						pacientes.elimina(dni, seguridad_social);

					} catch (NoExiste e) {
						
						System.out.println(e.getMessage());
					}finally {
						System.out.println("\nRedirigiendo al menu principal...");
						esperar();
						
					}
					
						
					
					break; //SE SALE DE LA OPCION 2 Y VUELVE A EJECUTARSE EL MENU PRINCIPAL
					
				case 3: //OPCION 3
					System.out.println("------Ha elegido Mostrar------\n\n");
					
					do { //SUB-MENU 1
						
						sc.nextLine(); //Limpiamos buffer por si se han introducido erroneamente los datos del dni añadiendo espacios.
						
						System.out.println("1: Mostrar DNI buscando por el numero de la seguridad social.");
						System.out.println("2: Mostrar DNI o DNIs buscando por nombre.");
						System.out.println("3: Mostrar Numero de la seguridad social buscando por DNI");
						System.out.println("4: Mostrar todos los pacientes");
						System.out.println("0: Volver al menu anterior");
						
						System.out.print("Introduzca una opcion: ");
						submenu=sc.nextInt();
						System.out.println();
						switch (submenu) {
							
							case 0:
								System.out.println("Redirigiendo al menu principal...");
								esperar();
								break;
						
							case 1:
								
								System.out.print("Introduce el num de la seguridad social:  ");
								seguridad_social=sc.nextInt();
								
								try {
									System.out.println("\nEl DNI coincidente con el num de la SS: "+seguridad_social+" es: "+pacientes.dni(seguridad_social));
								} catch (NoExiste e) {
									
									System.out.println(e.getMessage());
									
								}finally {
									
									System.out.println("\nRedirigiendo al menu mostrar...\n");
									esperar();
								}
								
								break;
							case 2:

								
								System.out.print("Introduce el nombre:  ");
								nombre=sc.next(); //Nos aseguramos de que solo se introduzca un nombre y no nombre con apellidos.
								
								try {
									System.out.println("\nEl/Los DNI coincidentes con el nombre: "+nombre+" son: "+pacientes.dni(nombre));
								} catch (NoExiste e) {
									
									System.out.println(e.getMessage());
									
								}finally {
									
									System.out.println("\nRedirigiendo al menu mostrar...\n");
									esperar();
								}
											
								break;
								
							case 3:
								
								System.out.print("Introduce el DNI Con letra:  ");
								dni=sc.next();
								
								try {
									System.out.println("\nEl numero de la SS correspondiente al DNI: "+dni.toUpperCase()+" es: "+pacientes.segSocial(dni));
								} catch (NoExiste e) {
									
									System.out.println(e.getMessage());
									
								}finally {
									
									
									System.out.println("\nRedirigiendo al menu mostrar...\n");
									esperar();
								}
								
								break;
							case 4:
								
								pacientes.muestra();
								System.out.println("\nRedirigiendo al menu mostrar...\n");
								esperar();
								break;
								
							default:
								System.out.println("Opcion incorrecta");
								break;
						}
					
					}while(submenu!=0); //OPCIONES DISPONIBLES DEL SUB-MENU 1. 0: ES RETROCEDER AL MENU PRINCIPAL.
					
					
					break;
				default:
					System.out.println("Opcion incorrecta.\n");
					break;
					
					
			}	//FIN OPCION 3
		
		sc.nextLine(); //Limpiamos buffer por si en alguna opcion se ha introducido un nombre con apellidos.
		
		}while (menu!=0); //FIN MENU PRINCIPAL
		
		sc.close();

	}
	
//	Método que sirve para esperar 3 segundos para redirigir al menú principal o al menú mostrar.
	private static void esperar() {
		
		try {
			Thread.sleep (3000);
		} catch (Exception e) {
			System.out.println("No se ha podido redirigir al menu principal");
		}
	}
}
