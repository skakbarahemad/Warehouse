package in.nareshit.raghu.exception;

public class ShipmentTypeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShipmentTypeNotFoundException() {
		super();
	}

	public ShipmentTypeNotFoundException(String message) {
		super(message);
		
	}

	public ShipmentTypeNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ShipmentTypeNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ShipmentTypeNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
