package Data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Business.Product;

public class ProductRepository implements IProductRepository {
	
	private Serialize serialize;
	private Deserialize deserialize;
	private ArrayList<Product> prods;
	private String fileName;
	public ProductRepository(Serialize serialize,Deserialize deserialize) {
		this.serialize = serialize;
		this.deserialize = deserialize;
		this.prods = new ArrayList<Product>();
		this.fileName = "stock";
		
	}
	
	
	@Override
	public void insert(Product product) throws Exception {
		if(!exist()) {
			prods.add(product);
			serialize.serialize(prods, fileName);
		}else {
			prods = (ArrayList<Product>) deserialize.deserializer(fileName);
			prods.add(product);
			serialize.serialize(prods, fileName);
		}
		
		
	}

	@Override
	public Product search(String cod) throws Exception {
		Product result = null;
		if(exist()) {
			ArrayList<Product> aux = (ArrayList<Product>)deserialize.deserializer(fileName);
			for(Product prod : aux) {
				if(prod.getCod().equalsIgnoreCase(cod)) {
					result = prod;
					return result;
				}
			}
		}
		return result;
		
		
	}

	@Override
	public void update(Product product) throws Exception {
		if(exist()) {
			ArrayList<Product> aux = (ArrayList<Product>)deserialize.deserializer(fileName);
			ArrayList<Product> aux1 = (ArrayList<Product>)deserialize.deserializer(fileName);
			for(int i = 0; i < aux.size(); i++) {
				Product prod = aux.get(i);
				if(prod.getCod().equalsIgnoreCase(product.getCod())) {
					aux1.remove(i);
					aux1.add(product);
				}
			}
			serialize.serialize(aux1, fileName);
		}
		
	}

	@Override
	public void remove(String cod) throws Exception {
		if(exist()) {
			ArrayList<Product> aux = (ArrayList<Product>)deserialize.deserializer(fileName);
			ArrayList<Product> aux1 = (ArrayList<Product>)deserialize.deserializer(fileName);

			for(int i = 0; i < aux.size(); i++) {
				Product prod = aux.get(i);
				if(prod.getCod().equalsIgnoreCase(cod)) {
					aux1.remove(i);
				}
			}
			serialize.serialize(aux1, fileName);
		}
		
	}
	public ArrayList<Product> getAllProducts() throws Exception {
		if(exist()) {
			ArrayList<Product> aux = (ArrayList<Product>)deserialize.deserializer(fileName);
			return aux;
		}
		return null;
	}
	public boolean exist() {
		File file = new File(fileName);
		if(file.exists()) return true;
		else return false;
	}

}
