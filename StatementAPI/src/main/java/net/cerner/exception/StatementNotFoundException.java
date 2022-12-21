package net.cerner.exception;

public class StatementNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StatementNotFoundException(String error) {
		super(error);
	}
}
