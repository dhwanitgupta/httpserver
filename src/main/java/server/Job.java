package server;


import java.util.Timer;

public class Job {
    public synchronized void setStatus(String status) {
        this.status = status;
        if (status.equals("RUNNING")) {
            new Thread(new RunningJob(this)).start();
        }
    }

    private String status;
    private long id;
    private Integer duration;

    public Job(String status, final Integer duration) {
        this.status = status;
        this.id = System.currentTimeMillis();
        this.duration = duration;

        if (status.equals("RUNNING")) {
            new Thread(new RunningJob(this)).start();
        }
    }

    public String getStatus() {
        return status;
    }

    public long getId() {
        return id;
    }

    public Integer getDuration() {
        return duration;
    }

    public class RunningJob implements Runnable {
        Job job;

        public RunningJob(Job job) {
            this.job = job;
        }

        public void run() {
            try {
                Thread.sleep(job.getDuration());
                job.setStatus("SUCCESS");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


