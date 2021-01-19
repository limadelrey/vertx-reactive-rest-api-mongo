package verticle;

import handler.BookHandler;
import io.reactivex.Single;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.config.ConfigRetriever;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.ext.mongo.MongoClient;
import io.vertx.reactivex.ext.web.Router;
import repository.BookRepository;
import router.BookRouter;
import service.BookService;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() {
        final ConfigStoreOptions store = new ConfigStoreOptions().setType("env");
        final ConfigRetrieverOptions options = new ConfigRetrieverOptions().addStore(store);
        final ConfigRetriever retriever = ConfigRetriever.create(vertx, options);

        retriever.rxGetConfig()
                .flatMap(configurations -> {
                    final MongoClient client = createMongoClient(vertx, configurations);

                    final BookRepository bookRepository = new BookRepository(client);
                    final BookService bookService = new BookService(bookRepository);
                    final BookHandler bookHandler = new BookHandler(bookService);
                    final BookRouter bookRouter = new BookRouter(vertx, bookHandler);

                    return createHttpServer(bookRouter.getRouter(), configurations);
                })
                .subscribe(
                        server -> System.out.println("HTTP Server listening on port " + server.actualPort()),
                        throwable -> {
                            System.out.println("Error occurred before creating a new HTTP server: " + throwable.getMessage());
                            System.exit(1);
                        });
    }

    // Private methods
    private MongoClient createMongoClient(Vertx vertx, JsonObject configurations) {
        final JsonObject config = new JsonObject()
                .put("host", configurations.getString("HOST"))
                .put("username", configurations.getString("USERNAME"))
                .put("password", configurations.getString("PASSWORD"))
                .put("authSource", configurations.getString("AUTHSOURCE"))
                .put("db_name", configurations.getString("DB_NAME"))
                .put("useObjectId", true);

        return MongoClient.createShared(vertx, config);
    }

    private Single<HttpServer> createHttpServer(Router router, JsonObject configurations) {
        return vertx
                .createHttpServer()
                .requestHandler(router)
                .rxListen(configurations.getInteger("HTTP_PORT"));
    }

}
