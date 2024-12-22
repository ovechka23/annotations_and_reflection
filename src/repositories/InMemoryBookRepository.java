package repositories;

import java.util.ArrayList;
import java.util.List;
import annotations.Repository;

@Repository
public class InMemoryBookRepository implements BookRepository {
    private final List<String> books = new ArrayList<>();

    @Override
    public void addBook(String title, String author) {
        books.add(title + ", " + author);
        System.out.println("Добавлено: " + title + ", " + author);
    }

    @Override
    public void removeBook(String title) {
        books.removeIf(book -> book.startsWith(title));
        System.out.println("Удалено: \"" + title + "\"");
        System.out.println();
    }

    @Override
    public List<String> listBooks() {
        System.out.println("\nКниги в хранилище:");
        for (String book : books) {
            System.out.println(book);
        }
        System.out.println();
        return books;
    }
}
