import io.vertx.reactivex.core.Vertx;
import verticle.MainVerticle;

public class Main {
    public static void main(String[] args) {
        final Vertx vertx = Vertx.vertx();

        vertx.rxDeployVerticle(MainVerticle.class.getName())
                .subscribe(
                        verticle -> System.out.println("New verticle started!"),
                        throwable -> {
                            System.out.println("Error occurred before deploying a new verticle: " + throwable.getMessage());
                            System.exit(1);
                        });
    }

}
