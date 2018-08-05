package Data;

import java.io.File;
import java.io.IOException;

import Business.Product;

public class productRepository implements productRepositoryInterface {
	
	Serialize serialize = new Serialize("/Documents/DataBase");
	Deserialize deserialize = new Deserialize(serialize.getPath());

	@Override
	public void insert(Product product) throws IOException {
		String fileName = "/"+ product.getCod();
		serialize.serialize(product,fileName);
		
	}

	@Override
	public Product search(String cod) throws Exception {
		String fileName = deserialize.getPath() + "/"+cod;
		Product prod = (Product) deserialize.deserializer(fileName);
		return prod;
	}

	@Override
	public void update(Product product) throws Exception {
		Product prodTemp = search(product.getCod());
		prodTemp = product;
		serialize.serialize(prodTemp, "/"+prodTemp.getCod());
	}

	@Override
	public void remove(String cod) throws Exception {
		Product prodTemp = search(cod);
		File deleteFile = new File(serialize.getPath()+"/"+prodTemp.getCod());
		deleteFile.delete();
		
	}

}
