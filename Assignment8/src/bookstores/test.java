package bookstores;

import java.io.FileNotFoundException;
import java.io.IOException;

public class test {
	public static void main(String[] agrs) throws FileNotFoundException {
		Bookstore b = null;

		XMLBookstoreUtils loadThing = new XMLBookstoreUtils();
		b = loadThing.load("sampleXML.xml");

		PromptXMLBookstoreUtils thing = new PromptXMLBookstoreUtils();
		b = thing.hook(b);
		System.out.println(b.toString());
	}
}
