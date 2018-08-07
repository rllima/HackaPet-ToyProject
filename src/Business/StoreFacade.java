package Business;

import Business.exception.ProductAlreadyExistException;
import Business.exception.ProductNotFoundException;
import Data.productRepository;

public class StoreFacade {
	private ProductBusiness repProd;
	private static StoreFacade instance;
	private int productAmount;
	
	public static StoreFacade getInstance() {
		if(instance == null)
			instance = new StoreFacade();
		return instance;
	}
	public StoreFacade() {
		this.repProd = new ProductBusiness(new productRepository());
		this.productAmount = 0;
	}
	
	public int getProductAmount() {
		return this.productAmount;
	}
	public void insertProduct(Product prod) throws ProductAlreadyExistException, Exception {
		repProd.insert(prod);
	}
	public void removeProduct(String cod) throws ProductNotFoundException, Exception {
		repProd.remove(cod);
	}
	public void updateProduct(Product prod) throws Exception {
		repProd.update(prod.getCod(), prod);
	}
	public Product searchProduct(String cod) throws ProductNotFoundException, Exception {
		return repProd.search(cod);
	}

}
