package excepciones;

public class NoExiste extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7967837906152267842L;

	public NoExiste(String msg) {
		
		super(msg);
	}
	
	public NoExiste() {
		
		super();
	}

}
