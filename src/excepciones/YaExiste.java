package excepciones;

public class YaExiste extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4323122455209265379L;

	public YaExiste(String msg) {
		
		super(msg);
	}
	
	public YaExiste() {
		super();
	}
}
