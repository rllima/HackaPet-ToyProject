package Business.exception;

public class ProductNotFoundException extends Exception {
	public ProductNotFoundException() {
		super("O produto n�o p�de ser encontrado");
	}

}
