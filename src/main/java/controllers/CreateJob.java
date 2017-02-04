package controllers;

import request.HttpRequest;
import response.HttpResponse;
import server.Constants;
import server.DataSource;
import server.Job;

import java.util.HashMap;
import java.util.Map;

public class CreateJob extends Controller {
    public HttpResponse enact(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        Job newJob;
        httpResponse.setStatusCode(200);

        if (DataSource.getSizeByStatus("RUNNING") < Constants.MAX_JOBS) {
            newJob = new Job("RUNNING", Constants.DEFAULT_EXECUTION_TIME);
        } else {
            newJob = new Job("QUEUED", Constants.DEFAULT_EXECUTION_TIME);
        }

        DataSource.jobs.put(newJob.getId(), newJob);

        Map<String, String> payload = new HashMap<String, String>();
        payload.put("id", Long.toString(newJob.getId()));
        payload.put("status", newJob.getStatus());

        httpResponse.setPayload(payload.toString());
        return httpResponse;
    }
}
