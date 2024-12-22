package services;

import annotations.Inject;
import repositories.BookRepository;
import annotations.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Inject
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String title, String author) {
        bookRepository.addBook(title, author);
    }

    public void removeBook(String title) {
        bookRepository.removeBook(title);
    }

    public void listBooks() {
        bookRepository.listBooks();
    }
}
