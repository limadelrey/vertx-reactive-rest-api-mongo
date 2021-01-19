package service;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import model.Book;
import repository.BookRepository;

import java.util.List;

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Single<List<Book>> getAll() {
        return bookRepository.getAll();
    }

    public Maybe<Book> getById(String id) {
        return bookRepository.getById(id);
    }

    public Maybe<Book> insert(Book book) {
        return bookRepository.insert(book);
    }

    public Completable update(String id, Book book) {
        return bookRepository.update(id, book);
    }

    public Completable delete(String id) {
        return bookRepository.delete(id);
    }

}
