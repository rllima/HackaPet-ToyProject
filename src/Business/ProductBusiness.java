package Business;

import java.util.ArrayList;

import Business.exception.ProductAlreadyExistException;
import Business.exception.ProductNotFoundException;
import Data.ProductRepository;

public class ProductBusiness {
	private ProductRepository rep;
	
	public ProductBusiness(ProductRepository rep) {
		this.rep = rep;
	}
	
	public void insert(Product product) throws Exception,ProductAlreadyExistException {
		if(rep.search(product.getCod()) == null) {
			rep.insert(product);
		}else {
			throw new ProductAlreadyExistException();
		}
	}
	public void remove(String cod) throws ProductNotFoundException, Exception {
		if(rep.search(cod) != null) {
			rep.remove(cod);
		}else {
			throw new ProductNotFoundException();
		}
	}
	public Product search(String cod) throws Exception,ProductNotFoundException {
		Product prod = rep.search(cod);
		if(prod == null) {
			throw new ProductNotFoundException();
		}
		else return prod;
	}
	public void update(String cod, Product product) throws Exception {
		if(rep.search(cod)== null)
			throw new ProductNotFoundException();
		else
			rep.update(product);
	}
	public ArrayList<Product> getProducts() throws Exception{
		return rep.getAllProducts();
	}

}
