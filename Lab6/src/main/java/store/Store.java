package store;

import java.util.ArrayList;
import java.util.List;

public class Store {
	ProductCatalog productCatalog = new ProductCatalog();
	List<Product> products = new ArrayList<>();
	
	Store() {
		for(Object[] product : Product.simulation) {
			products.add(new Product(product[0].toString(), Integer.parseInt(product[1].toString())));
		}
	}

	public ProductCatalog getProductCatalog() {
		return productCatalog;
	}
	
	
}
