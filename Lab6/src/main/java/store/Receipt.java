package store;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
	
	List<String> items = new ArrayList<>();
	int total;
	
	void print() {
		System.out.println("Your Purchase");
		System.out.println("---------");
		for (String string : items) {
			System.out.println(string);
		}
		System.out.println("---------");
		System.out.println("Your total: "+ total);
	}
	
	public void addTotal(int total) {
		this.total += total;
	}
	
	void addItem(String line) {
		items.add(line);
	}
}
