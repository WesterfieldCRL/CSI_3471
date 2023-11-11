package bookstores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class PromptXMLBookstoreUtils extends XMLBookstoreUtils{

    @Override
    protected Bookstore hook(Bookstore b) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter book information:");
            Book book = new Book();

            String name = promptUserForInput(reader, "Please input book name: ");
            book.setName(name);

            String author = promptUserForInput(reader, "Please input author: ");
            book.setAuthor(author);

            String publishDate = promptUserForInput(reader, "Please input publishDate: ");
            book.setPublishDate(publishDate);

            String isbn = "";
            while (!isValidISBN(isbn)) {
                isbn = promptUserForInput(reader, "Please input the ISBN: ");
                if (isValidISBN(isbn)) {
                    book.setIsbn(isbn);
                    break;
                } else {
                    System.out.println("Invalid ISBN format. Please use numbers, dashes, or underscores.");
                }
            }

            b.addBook(book);

            super.doSave(b);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return b;
    }

    private String promptUserForInput(BufferedReader reader, String prompt) throws IOException {
        String input;
        do {
            System.out.print(prompt);
            input = reader.readLine().trim();
            if (input.isEmpty() || input.length() > 100)
            {
                System.out.println("Invalid data try again");
            }
        } while (input.isEmpty() || input.length() > 100);
        return input;
    }

    private boolean isValidISBN(String isbn) {
        String regex = "^[0-9\\-\\_]+$";
        return Pattern.matches(regex, isbn);
    }
}
