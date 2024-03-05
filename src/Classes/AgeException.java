package Classes;

public class AgeException extends Exception{
	
	public AgeException() {}
	
	public AgeException(String message) {
		super(message);
		System.out.println("AgeException: " + message);
	}

}
