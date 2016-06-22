package io.lovezx.events.exception;

public class IllegalRefenceListenerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IllegalRefenceListenerException(){
		super();
	}
	
	public IllegalRefenceListenerException(String message){
		super(message);
	}

	public IllegalRefenceListenerException(String message, Throwable cause) {
        super(message, cause);
    }

}
