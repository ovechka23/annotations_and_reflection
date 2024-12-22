package repositories;

import java.util.List;

public interface BookRepository {
    void addBook(String title, String author);
    void removeBook(String title);
    List<String> listBooks();
}
