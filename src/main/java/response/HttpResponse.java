package response;

public class HttpResponse {
    private Integer statusCode;
    private String errorMessage;
    private String payload;
    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
