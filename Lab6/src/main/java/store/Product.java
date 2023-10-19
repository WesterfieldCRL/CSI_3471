package store;

public class Product {
	String barCode;
	Integer serialNumber;
	
	static Object[][] simulation 
	= {{"88221", 3001},
		{"88222", 3002},
		{"88222", 3003},
		{"98221", 3004},
		{"98221", 3005},
		{"98222", 3006},
		{"98222", 3007},
		{"18221", 3008},
		{"18221", 3009},
		{"18222", 3010},
		{"18222", 3011},
		{"18222", 3012},
	};
	
	public Product(String barCode, Integer serialNumber) {
		super();
		this.barCode = barCode;
		this.serialNumber = serialNumber;
	}
	
	public String getBarCode() {
		return barCode;
	}
	
	public Integer getSerialNumber() {
		return serialNumber;
	}

}
