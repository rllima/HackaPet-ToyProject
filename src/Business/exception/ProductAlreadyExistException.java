package Business.exception;

public class ProductAlreadyExistException extends Exception {
	public ProductAlreadyExistException() {
		super("O produto já está cadastrado");
	}

}
