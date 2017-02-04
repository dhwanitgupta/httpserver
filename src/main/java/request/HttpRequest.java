package request;


public class HttpRequest {
    private final String method;
    private final String host;
    private final String requestBody;
    private final Integer port;
    private final String path;

    public HttpRequest(String method, String host, String requestBody, Integer port, String path) {
        this.method = method;
        this.host = host;
        this.requestBody = requestBody;
        this.port = port;
        this.path = path;
    }

    public String getHost() {
        return host;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public Integer getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    public String getMethod(){ return  method; }

}
