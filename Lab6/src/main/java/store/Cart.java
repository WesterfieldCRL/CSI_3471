package store;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	List<Product> items = new ArrayList<>();
	
	void add(Product p) {
		items.add(p);
	}
	
	public List<Product> getItems() {
		return items;
	}
}
