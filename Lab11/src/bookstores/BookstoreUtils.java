package bookstores;

import java.io.IOException;

public abstract class BookstoreUtils {
	abstract public Bookstore load(String s) throws IOException;
	
	public final void save(Bookstore b) {
		b = hook(b);
		try {
			doSave(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected Bookstore hook(Bookstore b) {
		return b;
	}
	
	abstract void doSave(Bookstore b) throws IOException;
}
