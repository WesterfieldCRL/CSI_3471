package bookstores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLBookstoreUtils extends BookstoreUtils {
	public Bookstore load(String s) throws FileNotFoundException {
		Bookstore bookstore = null;
		try {
			JAXBContext context = JAXBContext.newInstance(bookstores.Bookstore.class);
			Unmarshaller um = context.createUnmarshaller();
			bookstore = (Bookstore) um.unmarshal(new FileReader(s));
	        ArrayList<Book> list = bookstore.getBooksList();
		} catch (JAXBException e) {
			e.printStackTrace();
        }
		return bookstore;
	}
	void doSave(Bookstore b) {
		try {
			JAXBContext context = JAXBContext.newInstance(bookstores.Bookstore.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	        // Write to System.out
	        m.marshal(b, System.out);


	        // Write to File
	        m.marshal(b, new File("outputXML.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
}
