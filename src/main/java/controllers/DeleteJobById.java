package controllers;

import request.HttpRequest;
import response.HttpResponse;
import server.DataSource;
import server.Job;

import java.util.HashMap;
import java.util.Map;

public class DeleteJobById extends Controller{
    public HttpResponse enact(HttpRequest httpRequest) {
        HttpResponse httpResponse = new HttpResponse();

        Job job;
        Map<String, String> payload = new HashMap<String, String>();

        if(DataSource.jobs.containsKey(getJobId())) {
            job = DataSource.jobs.get(getJobId());
            if(job.getStatus().equals("SUCCESS") || job.getStatus().equals("FAILED")){
                httpResponse.setStatusCode(403);
            } else {
                job.setStatus("DELETED");
                payload.put("id", Long.toString(getJobId()));
                payload.put("status", job.getStatus());
                httpResponse.setPayload(payload.toString());
                httpResponse.setStatusCode(200);
            }
        } else {
            httpResponse.setStatusCode(404);
        }

        return httpResponse;
    }
}
