package store;

public class User {
	String login;
	Cart cart = new Cart();

	public User(String login) {
		super();
		this.login = login;
	}
	
	Cart getCart() {
		return cart;
	}
	

}
