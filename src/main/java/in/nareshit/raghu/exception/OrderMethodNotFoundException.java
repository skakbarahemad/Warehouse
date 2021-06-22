package in.nareshit.raghu.exception;

public class OrderMethodNotFoundException extends RuntimeException {
	
	public  OrderMethodNotFoundException() {
		super();
	}
	
	public OrderMethodNotFoundException(String msg) {
		super(msg);
	}
}
