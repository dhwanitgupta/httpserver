package server;

import controllers.Controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DataSource {
    public static Map<Long, Job> jobs;

    public static Integer getSizeByStatus(String status) {
        Integer count = 0;
        for (Map.Entry<Long, Job> entry : jobs.entrySet()) {
            Job value = entry.getValue();
            if (value.getStatus().equals(status)) count++;
        }

        return count;
    }

    public static Job getJobByStatus(String status) {
        for (Map.Entry<Long, Job> entry : jobs.entrySet()) {
            Job value = entry.getValue();
            if (value.getStatus().equals(status)) return value;
        }

        return null;
    }
}
