package handler;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.RoutingContext;
import model.Book;
import service.BookService;

import java.util.NoSuchElementException;

public class BookHandler {

    private BookService bookService;

    public BookHandler(BookService bookService) {
        this.bookService = bookService;
    }

    public void getAll(RoutingContext rc) {
        bookService.getAll()
                .subscribe(
                        result -> onSuccessResponse(rc, 200, result),
                        throwable -> onErrorResponse(rc, 400, throwable));
    }

    public void getOne(RoutingContext rc) {
        final String id = rc.pathParam("id");

        bookService.getById(id)
                .subscribe(
                        result -> onSuccessResponse(rc, 200, result),
                        throwable -> onErrorResponse(rc, 400, throwable),
                        () -> onErrorResponse(rc, 400, new NoSuchElementException("No book with id " + id)));
    }

    public void insertOne(RoutingContext rc) {
        final Book book = mapRequestBodyToBook(rc);

        bookService.insert(book)
                .subscribe(
                        result -> onSuccessResponse(rc, 201, result),
                        throwable -> onErrorResponse(rc, 400, throwable));
    }

    public void updateOne(RoutingContext rc) {
        final String id = rc.pathParam("id");
        final Book book = mapRequestBodyToBook(rc);

        bookService.update(id, book)
                .subscribe(
                        () -> onSuccessResponse(rc, 204, null),
                        throwable -> onErrorResponse(rc, 400, throwable));
    }

    public void deleteOne(RoutingContext rc) {
        final String id = rc.pathParam("id");

        bookService.delete(id)
                .subscribe(
                        () -> onSuccessResponse(rc, 204, null),
                        throwable -> onErrorResponse(rc, 400, throwable));
    }

    // Mapping between book class and request body JSON object
    private Book mapRequestBodyToBook(RoutingContext rc) {
        Book book = new Book();

        try {
            book = rc.getBodyAsJson().mapTo(Book.class);
        } catch (IllegalArgumentException ex) {
            onErrorResponse(rc, 400, ex);
        }

        return book;
    }

    // Generic responses
    private void onSuccessResponse(RoutingContext rc, int status, Object object) {
        rc.response()
                .setStatusCode(status)
                .putHeader("Content-Type", "application/json")
                .end(Json.encodePrettily(object));
    }

    private void onErrorResponse(RoutingContext rc, int status, Throwable throwable) {
        final JsonObject error = new JsonObject().put("error", throwable.getMessage());

        rc.response()
                .setStatusCode(status)
                .putHeader("Content-Type", "application/json")
                .end(Json.encodePrettily(error));
    }

}
