package bookstores;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "bookstore")
public class Bookstore {

   
	// XmLElementWrapper generates a wrapper element around XML representation
    @XmlElementWrapper(name = "bookList")
    // XmlElement sets the name of the entities
    @XmlElement(name = "book")
    private ArrayList<Book> bookList;
    private String name;
    private String location;

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<Book> getBooksList() {
        return bookList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

	public void addBook(Book book)
	{
		if (bookList == null)
		{
			bookList = new ArrayList<>();
		}
		bookList.add(book);
	}
    
    @Override
   	public int hashCode() {
   		final int prime = 31;
   		int result = 1;
   		result = prime * result + ((bookList == null) ? 0 : bookList.hashCode());
   		result = prime * result + ((location == null) ? 0 : location.hashCode());
   		result = prime * result + ((name == null) ? 0 : name.hashCode());
   		return result;
   	}

   	@Override
   	public boolean equals(Object obj) {
   		if (this == obj)
   			return true;
   		if (obj == null)
   			return false;
   		if (getClass() != obj.getClass())
   			return false;
   		Bookstore other = (Bookstore) obj;
   		if (bookList == null) {
   			if (other.bookList != null)
   				return false;
   		} else if (!bookList.equals(other.bookList))
   			return false;
   		if (location == null) {
   			if (other.location != null)
   				return false;
   		} else if (!location.equals(other.location))
   			return false;
   		if (name == null) {
   			if (other.name != null)
   				return false;
   		} else if (!name.equals(other.name))
   			return false;
   		return true;
   	}

}