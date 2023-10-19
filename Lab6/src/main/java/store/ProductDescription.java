package store;

public class ProductDescription {
	String barCode;
	Integer price;
	String description;
	
	static Object[][] simulation 
		= {{"88221", 300, "IPad 12"},
			{"88222", 350, "IPad 12 HQ"},
			{"98221", 500, "IPhone X"},
			{"98222", 550, "IPhone 11"},
			{"18221", 1350, "MacBook Mini"},
			{"18222", 2550, "MacBook Maxi"},
	};

	public ProductDescription(String barCode, Integer price, String description) {
		super();
		this.barCode = barCode;
		this.price = price;
		this.description = description;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
	
}
