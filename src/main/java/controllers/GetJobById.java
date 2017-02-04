package controllers;


import request.HttpRequest;
import response.HttpResponse;
import server.DataSource;
import server.Job;

import java.util.HashMap;
import java.util.Map;

public class GetJobById extends Controller{
    public HttpResponse enact(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();

        httpResponse.setStatusCode(200);
        Job job;
        Map<String, String> payload = new HashMap<String, String>();

        if(DataSource.jobs.containsKey(getJobId())) {
            job = DataSource.jobs.get(getJobId());
            payload.put("id", Long.toString(getJobId()));
            payload.put("status", job.getStatus());
            httpResponse.setPayload(payload.toString());
        } else {
            httpResponse.setStatusCode(404);
        }

        return httpResponse;
    }
}
