package Classes;

public class StockException extends Exception{
	
	public StockException() {}
	
	public StockException(String message) {
		super(message);
		System.out.println("StockException: " + message);
	}

}