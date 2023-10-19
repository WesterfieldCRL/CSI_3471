package store;

public class UseCase {

	Store store = new Store();
	User user = null;

	public void login() {
		user = new User("McDonnald");
	}

	public void shopping() {
		for (Product product : store.products) {
			if (Math.random() > 0.8) {
				user.getCart().add(product);
			}
		}
	}

	public Receipt processShopping() {
		Receipt receipt = new Receipt();
		
		// Discount 10% if lucky
		Integer discount = 100; // 100% original price
		double luck = Math.random();
		if (luck > 0.8) {
			discount = 90; // 90%
			receipt.addItem("Discount\t10%");
		} else if (luck > 0.6) {
			discount = 95; // 95%
			receipt.addItem("Discount\t5%");
		}	
		
		
		for (Product product : user.getCart().getItems()) {
			ProductDescription productDescription = store.getProductCatalog().find(product.getBarCode());
			Integer discountedPrice = productDescription.getPrice() * discount / 100;
			String line = productDescription.getDescription() +"\t"+ discountedPrice;
			
			receipt.addItem(line);
			receipt.addTotal(discountedPrice);
		}

		return receipt;

	}

	public static void main(String[] args) {
		UseCase uc = new UseCase();
		uc.login();
		uc.shopping();
		Receipt receipt = uc.processShopping();
		receipt.print();
	}
}
