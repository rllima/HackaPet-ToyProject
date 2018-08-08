package Data;

import java.io.IOException;

import Business.Product;

public interface IProductRepository {
	
	void insert(Product product) throws IOException, Exception;
	Product search(String cod) throws Exception;
	void update(Product product) throws Exception;
	void remove(String cod) throws Exception;

}
