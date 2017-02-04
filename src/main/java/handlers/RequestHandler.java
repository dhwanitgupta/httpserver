package handlers;

import com.google.gson.Gson;
import controllers.Controller;
import request.HttpRequest;
import response.HttpResponse;

public class RequestHandler {
    private final Gson gson;
    private final Routes routes;

    public RequestHandler(){
        this.gson = new Gson();
        this.routes = new Routes();
    }

    public String handleIncomingRequest(String requestString) {
        HttpRequest httpRequest = parseRequestString(requestString);
        return parseResponseToString(routeToController(httpRequest));
    }

    private String parseResponseToString(HttpResponse httpResponse) {
        return gson.toJson(httpResponse);
    }

    private HttpResponse routeToController(HttpRequest httpRequest) {
        System.out.println("Request " + gson.toJson(httpRequest));
        Controller controller = routes.getControllerForRoute(httpRequest.getMethod() + " " + httpRequest.getPath());
        return controller.enact(httpRequest);
    }

    private HttpRequest parseRequestString(String requestString) {
        return gson.fromJson(requestString, HttpRequest.class);
    }
}
