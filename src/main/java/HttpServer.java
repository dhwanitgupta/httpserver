import com.google.gson.Gson;
import request.HttpRequest;

import java.io.IOException;

public class HttpServer {

    public static void main(String []args) throws IOException {
          new Server(8011).start();
    }

}
