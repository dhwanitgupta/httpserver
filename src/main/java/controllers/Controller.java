package controllers;


import request.HttpRequest;
import response.HttpResponse;

public abstract class Controller {
    public abstract HttpResponse enact(HttpRequest httpRequest);
    private long jobId;

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }
}
