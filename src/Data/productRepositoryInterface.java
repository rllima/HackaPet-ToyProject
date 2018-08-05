package Data;

import java.io.IOException;

import Business.Product;

public interface productRepositoryInterface {
	
	void insert(Product product) throws IOException;
	Product search(String cod) throws Exception;
	void update(Product product) throws Exception;
	void remove(String cod) throws Exception;

}
