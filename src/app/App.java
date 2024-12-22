package app;

import ioccontainer.SimpleIoCContainer;
import repositories.BookRepository;
import repositories.InMemoryBookRepository;
import services.BookService;

public class App {
    public static void main(String[] args) throws Exception {
        // Создание IoC контейнера
        SimpleIoCContainer container = new SimpleIoCContainer();

        // Регистрация зависимостей
        container.register(BookRepository.class, new InMemoryBookRepository()); // Регистрация репозитория
        container.register(BookService.class, new BookService(container.resolve(BookRepository.class))); // Регистрация сервиса с зависимостью

        // Разрешение зависимости BookService
        BookService bookService = container.resolve(BookService.class);

        // Использование сервиса
        bookService.addBook("Гарри Поттер и философский камень", "Джоан Роулинг");
        bookService.addBook("Гарри Поттер и Тайная комната", "Джоан Роулинг");
        bookService.addBook("Шерлок Холмс: Этюд в багровых тонах", "Артур Конан Дойл");
        bookService.addBook("Шерлок Холмс: Собака Баскервилей", "Артур Конан Дойл");
        bookService.addBook("Время всегда хорошее", "Евгения Пастернак, Андрей Жвалевский");
        bookService.addBook("Вредные советы", "Григорий Остер");
        bookService.addBook("Голодные игры", "Сьюзен Коллинз");

        // Список книг
        bookService.listBooks();

        // Удаление одной книги и показ обновленного списка
        bookService.removeBook("Вредные советы");
        bookService.listBooks();
    }
}
