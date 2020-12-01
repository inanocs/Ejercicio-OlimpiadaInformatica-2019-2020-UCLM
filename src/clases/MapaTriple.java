package clases;

import java.util.Map;
import java.util.HashMap;

import excepciones.NoExiste;
import excepciones.YaExiste;

public class MapaTriple {
	
	private HashMap<String, String> mapa1; //DNI Y NOMBRE
	private HashMap<String,Integer> mapa2; //DNI Y SS
	private HashMap<Integer,String> mapa3; //SS Y NOMBRE
	
	public MapaTriple() {
		//Instanciamos los mapas
		mapa1=new HashMap<String,String>();
		mapa2=new HashMap<String,Integer>();
		mapa3=new HashMap<Integer,String>();
	}
	
//	Método inserta
	public void inserta(String dni, int segsocial,String nombre) throws YaExiste {
//		Pasamos la letra del dni a mayúsculas, por si la pasan por parametro en minúsculas.
		dni=dni.toUpperCase();
		//Comprobamos si existe el dni
		if(!(noExisteDNI(dni))) {
			throw new YaExiste("No se ha podido añadir. El DNI "+dni+" ya existe.");
		}
		
		if(!(noExisteSegSocial(segsocial))) {
			throw new YaExiste("No se ha podido añadir. El num de la seguridad social "+segsocial+" ya existe.");
		}
		
//		Convertimos el nombre en mayúsculas, por cuestiones de formato.
		nombre=nombre.toUpperCase();
//		Insertamos los datos.
		mapa1.put(dni, nombre);
		mapa2.put(dni, segsocial);
		mapa3.put(segsocial, nombre);
		System.out.println();
		System.out.println(nombre+" con DNI: "+dni+" y numero de la seguridad social: "+segsocial+" añadido.");
		
	}
	
//	Método elimina
	public void elimina(String dni,int segsocial) throws NoExiste{
		
		boolean ambosNoExisten=true;
		String nombre="";
		
		//Buscamos si el dni concuerda con el num de la seguridad social.
		for(Map.Entry<String, Integer> entrada:mapa2.entrySet()) {
			
			
			String nif=entrada.getKey();
			Integer ss=entrada.getValue();
			
			if (nif.equalsIgnoreCase(dni)&& ss==segsocial) {
				
				ambosNoExisten=false;
				
			}
			
		}

		
		//Comprobamos si el dni no existe
		if(noExisteDNI(dni)) {
			throw new NoExiste("No se ha podido eliminar. El DNI introducido no existe");
		}else if(noExisteSegSocial(segsocial)) { //Comprobamos si el num de la seguridad social no existe.
			
			throw new NoExiste("No se ha podido eliminar. El num de la seguridad social no existe");
		}else if(ambosNoExisten) { //Comprobamos si no se corresponde la clave dni con el valor de la seguridad social.
			
			throw new NoExiste("No se ha podido eliminar. No se corresponde el dni con su numero de la seguridad social");
		}else {
			
//			Buscamos el nombre correspondiente a ese DNI y seguridad social.
			for(Map.Entry<String, String> entrada:mapa1.entrySet()) {
				
				
				String nif=entrada.getKey();
				String nom=entrada.getValue();
				
				if(nif.equalsIgnoreCase(dni)) {
					
					nombre=nom;
				}
				
			}
			
			//Los borramos
			mapa1.remove(dni.toUpperCase(), nombre);
			mapa2.remove(dni.toUpperCase(), segsocial);
			mapa3.remove(segsocial,nombre);
			System.out.println();
			System.out.println(nombre+" con DNI: "+dni.toUpperCase()+" y numero de la seguridad social: "+segsocial+" eliminado.");
		}
		
		
	}
		
//	Método segSocial	
	public int segSocial(String dni) throws NoExiste{
		
		int seguridadsocial=0;
//		Comprobamos si no existe el dni que se pasa por parametro.
		if(noExisteDNI(dni)) {
			throw new NoExiste("El DNI no existe");
		}else {
//			Recorremos el mapa2 para encontrar el num de la seguridad social correspondiente a ese dni.
			for(Map.Entry<String, Integer> entrada:mapa2.entrySet()) {
				
				
				String nif=entrada.getKey();
				seguridadsocial=entrada.getValue();
				
				if(nif.equalsIgnoreCase(dni)) {
					break;
				}	
			}
		}
		
		
		
		return seguridadsocial;
	}
	
//	Método dni con segsocial por parametro
	public String dni(int segsocial) throws NoExiste{
		String dni="";
//		Comprobamos si el numero de la seguridad social que se ha introducido por parámetro no eiste.
		if(noExisteSegSocial(segsocial)) {
			throw new NoExiste("El número de la seguridad social "+segsocial+" no existe");
		}else {
//			Recorremos el mapa 2 para encontrar el dni correspondiente a ese numero de la seguridad social.
			for(Map.Entry<String, Integer> entrada:mapa2.entrySet()) {
				
				
				dni=entrada.getKey();
				int ss=entrada.getValue();
				
				if(ss==segsocial) {
					break;
				}	
			}
		}

		return dni;
	}
	
//	Método dni con nombre por parametro
	public String dni(String nombre) throws NoExiste{
		
		String dnis="";
//		Comprobamos si el nombre que se pasa por parámetro no concuerda con ningún dni que haya en el mapa 3..
		if(noExisteNom(nombre)) {
			throw new NoExiste("No hay ningun usuario con nombre "+nombre+" en nuestra base de datos.");
		}
//		Añadimos el dni que hay con ese nombre en ese mapa de datos.
		for(Map.Entry<String, String> entrada:mapa1.entrySet()) {
			
			
			String dni=entrada.getKey();
			String nom=entrada.getValue();
			
			if(nom.equalsIgnoreCase(nombre)) {
				dnis=dnis+dni+" ";
			}	
		}
		
		return dnis;
	}
	
//	Método muestra
	public void muestra() {
		int segsocial=0;
		boolean No_hay_pacientes=false;
		
		if(mapa1.isEmpty()) {
			No_hay_pacientes=true;
		}
		
		if(No_hay_pacientes) {
			
			System.out.println("Actualmente no hay ningún usuario en nuestra base de datos");
			
		}else {
			
			System.out.println("-----Listado de pacientes----");
			for(Map.Entry<String, String> entrada:mapa1.entrySet()) {
				
//				Guardamos el dni
				String dni=entrada.getKey();
//				Guardamos el nombre
				String nombre=entrada.getValue();
//				Obtenemos el num de la seguridad social para ese dni, como lanza la excepcion NoExiste tenemos que atraparla.
				try {
					segsocial=segSocial(dni);
				} catch (NoExiste e) {
					
					e.printStackTrace();
				}
				
				System.out.println("DNI: "+dni+", Seguridad social: "+segsocial+", Nombre: "+nombre);
				
			}
			
		}

	}
	
//	Metodo privado que comprueba si existe no existe la segsocial
	private boolean noExisteSegSocial(int segsocial) {
		
		boolean no_existe=true;
		if(mapa3.containsKey(segsocial)) {
			
			no_existe=false;
		}
		
		return no_existe;
	}
	
//	Método privado que comprueba si existe o no un dni
	private boolean noExisteDNI(String dni) {
		
		boolean no_existe=true;
//		Pasamos la letra del DNI a mayusculas (por si la han introducido en minusculas)
		if(mapa1.containsKey(dni.toUpperCase())) {
			
			no_existe=false;
		}
		return no_existe;
	}
//	Método privado que comprueba si existe o no un nombre
	private boolean noExisteNom(String nombre) {
		
		boolean no_existe=true;
		
//		Pasamos el nombre a mayúsculas por formato, y comprobamos si ese nombre está en el mapa1.
		
		if(mapa1.containsValue(nombre.toUpperCase())) {
			
			no_existe=false;
		}
		
		return no_existe;
	}
}
