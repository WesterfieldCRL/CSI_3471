package tester;

import bookstores.Book;
import bookstores.Bookstore;
import bookstores.CSVBookstoreUtils;
import bookstores.XMLBookstoreUtils;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookstoreUtilsTest {

    private XMLBookstoreUtils xmlBookstoreUtils;
    private CSVBookstoreUtils csvBookstoreUtils;
    private String testFilePathXML;
    private String testFilePathCSV;

    @Before
    public void setUp() {
        xmlBookstoreUtils = new XMLBookstoreUtils();
        csvBookstoreUtils = new CSVBookstoreUtils();
        testFilePathXML = "sampleXML.xml";
        testFilePathCSV = "sampleCSV.csv";
    }

    @Test
    public void testLoadXML() throws IOException {
        Bookstore bookstore = xmlBookstoreUtils.load(testFilePathXML);

        assertNotNull("Bookstore object should not be null", bookstore);
        assertEquals("Bookstore name should match", "Baylor Bookstore", bookstore.getName());
        assertEquals("Bookstore location should match", "Waco", bookstore.getLocation());

        ArrayList<Book> books = bookstore.getBooksList();
        assertNotNull("Books list should not be null", books);
        assertEquals("Number of books should match", 2, books.size());

        // Test book details
        Book book1 = books.get(0);
        assertEquals("Book 1 name should match", "The Tragedy", book1.getName());
        assertEquals("Book 1 author should match", "Neil Kahn", book1.getAuthor());
        assertEquals("Book 1 publisher should match", "2012-08-21T13:21:58.000Z", book1.getPublishDate());
        assertEquals("Book 1 ISBN should match", "978-0060554736", book1.getIsbn());

        Book book2 = books.get(1);
        assertEquals("Book 2 name should match", "Ghost", book2.getName());
        assertEquals("Book 2 author should match", "Charlotte Blum", book2.getAuthor());
        assertEquals("Book 2 publisher should match", "2012-08-21T13:21:58.000Z", book2.getPublishDate());
        assertEquals("Book 2 ISBN should match", "978-3832180577", book2.getIsbn());
    }

    @Test
    public void testSaveXML() throws JAXBException, FileNotFoundException {
        Bookstore bookstore = xmlBookstoreUtils.load(testFilePathXML);
        xmlBookstoreUtils.save(bookstore);

        Bookstore bs = xmlBookstoreUtils.load("outputXML.xml");
        assertEquals(bs, bookstore);
    }

    @Test(expected = IOException.class)
    public void testLoadInvalidFileXML() throws IOException {
        String invalidFilePath = "invalid.xml";
        xmlBookstoreUtils.load(invalidFilePath);
    }

    @Test
    public void testLoadCSV() throws IOException {
        Bookstore bookstore = csvBookstoreUtils.load(testFilePathCSV);

        assertNotNull("Bookstore object should not be null", bookstore);
        assertEquals("Bookstore name should match", "Baylor Bookstore", bookstore.getName());
        assertEquals("Bookstore location should match", "Waco", bookstore.getLocation());

        ArrayList<Book> books = bookstore.getBooksList();
        assertNotNull("Books list should not be null", books);
        assertEquals("Number of books should match", 2, books.size());

        // Test book details
        Book book1 = books.get(0);
        assertEquals("Book 1 name should match", "The Tragedy", book1.getName());
        assertEquals("Book 1 author should match", "Neil Kahn", book1.getAuthor());
        assertEquals("Book 1 publisher should match", "2012-08-21T13:21:58.000Z", book1.getPublishDate());
        assertEquals("Book 1 ISBN should match", "978-0060554736", book1.getIsbn());

        Book book2 = books.get(1);
        assertEquals("Book 2 name should match", "Ghost", book2.getName());
        assertEquals("Book 2 author should match", "Charlotte Blum", book2.getAuthor());
        assertEquals("Book 2 publisher should match", "2012-08-21T13:21:58.000Z", book2.getPublishDate());
        assertEquals("Book 2 ISBN should match", "978-3832180577", book2.getIsbn());
    }

    @Test
    public void testSaveCSV() throws IOException {
        Bookstore bookstore = csvBookstoreUtils.load(testFilePathCSV);
        csvBookstoreUtils.save(bookstore);

        Bookstore bs = csvBookstoreUtils.load("outputCSV.csv");
        assertEquals(bookstore, bs);

    }

    @Test(expected = IOException.class)
    public void testLoadInvalidFile() throws IOException {
        String invalidFilePath = "invalid.csv";
        csvBookstoreUtils.load(invalidFilePath);
    }

    @Test
    public void testLoadXMLSaveCSV() throws IOException {
        Bookstore bookstore = xmlBookstoreUtils.load(testFilePathXML);
        csvBookstoreUtils.save(bookstore);

        Bookstore bs = csvBookstoreUtils.load("outputCSV.csv");
        assertEquals(bookstore, bs);
    }

    @Test
    public void testLoadCSVSaveXML() throws IOException {
        Bookstore bookstore = csvBookstoreUtils.load(testFilePathCSV);
        xmlBookstoreUtils.save(bookstore);

        Bookstore bs = xmlBookstoreUtils.load("outputXML.xml");
        assertEquals(bookstore, bs);
    }
}
