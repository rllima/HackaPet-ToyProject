package Data;

import java.io.IOException;

import Business.Product;

public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Product prod = new Product("Test", "Testando", 15.50, "123");
		productRepository rep = new productRepository();
		rep.insert(prod);
		System.out.println(rep.search("123").getName());
		rep.update(new Product("Test1", "Testando", 15.50, "123"));
		System.out.println(rep.search("123").getName());
		rep.remove("123");

	}

}