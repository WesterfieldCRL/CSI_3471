package bookstores;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.ArrayList;

public class CSVBookstoreUtils extends  BookstoreUtils{
    public Bookstore load(String filePath) throws IOException {
        Bookstore bookstore = new Bookstore();
        ArrayList<Book> books = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4)
            {
                Book book = new Book();
                book.setName(parts[1]);
                book.setAuthor(parts[0]);
                book.setPublishDate(parts[2]);
                book.setIsbn(parts[3]);
                books.add(book);
            }
            else
            {
                bookstore.setLocation(parts[0]);
                bookstore.setName(parts[1]);
            }
        }

        bookstore.setBookList(books);
        return bookstore;
    }

    void doSave(Bookstore b) throws IOException {
        try {
            // Set the second argument to 'true' to enable appending
            FileWriter fileWriter = new FileWriter("outputCSV.csv");
            StringBuilder sb = new StringBuilder();
            for (Book book: b.getBooksList())
            {
                sb.append(book.getAuthor());
                sb.append(",");
                sb.append(book.getName());
                sb.append(",");
                sb.append(book.getPublishDate());
                sb.append(",");
                sb.append(book.getIsbn());
                sb.append("\n");
            }

            sb.append(b.getLocation());
            sb.append(",");
            sb.append(b.getName());
            sb.append("\n");

            fileWriter.write(sb.toString());
            System.out.print(sb);

            // Close the file writer
            fileWriter.close();

        } catch (IOException e) {
           e.printStackTrace();
        }

    }


}
