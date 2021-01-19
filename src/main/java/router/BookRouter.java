package router;

import handler.BookHandler;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.handler.BodyHandler;

public class BookRouter {

    private Vertx vertx;
    private BookHandler bookHandler;

    public BookRouter(Vertx vertx, BookHandler bookHandler) {
        this.vertx = vertx;
        this.bookHandler = bookHandler;
    }

    public Router getRouter() {
        final Router bookRouter = Router.router(vertx);

        bookRouter.route("/api/v1/books*").handler(BodyHandler.create());
        bookRouter.get("/api/v1/books").handler(bookHandler::getAll);
        bookRouter.get("/api/v1/books/:id").handler(bookHandler::getOne);
        bookRouter.post("/api/v1/books").handler(bookHandler::insertOne);
        bookRouter.put("/api/v1/books/:id").handler(bookHandler::updateOne);
        bookRouter.delete("/api/v1/books/:id").handler(bookHandler::deleteOne);

        return bookRouter;
    }

}
