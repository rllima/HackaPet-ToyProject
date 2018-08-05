package Business.exception;

public class ProductNotFoundException extends Exception {
	public ProductNotFoundException() {
		super("O produto não pôde ser encontrado");
	}

}
