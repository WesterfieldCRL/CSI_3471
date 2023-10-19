package store;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {
	// bar code 
	Map<String,ProductDescription> map = new HashMap<>();
	
	
	
	ProductCatalog() {
		for (Object[] desc : ProductDescription.simulation) {
			map.put(desc[0].toString(), new ProductDescription(desc[0].toString(), Integer.parseInt(desc[1].toString()), desc[2].toString()));
		}
	}
	
	ProductDescription find(String barCode) {
		return map.get(barCode);
	}
}
