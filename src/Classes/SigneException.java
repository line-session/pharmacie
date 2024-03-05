package Classes;

public class SigneException extends Exception{
	
	public SigneException() {}
	
	public SigneException(String message) {
		super(message);
		System.out.println("SigneException: " + message);
	}

}
