import handlers.RequestHandler;
import javafx.scene.chart.PieChart;
import server.Constants;
import server.DataSource;
import server.Job;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private final Integer port;
    private final ServerSocket serverSocket;
    private final RequestHandler requestHandler;

    public Server(Integer port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.requestHandler = new RequestHandler();
    }

    public void start(){
        init();
        while(true) {
            try {
                new Thread(new Runnable() {
                    public void run() {
                        while(true) {
                            if (DataSource.getSizeByStatus("QUEUED") > 0
                                && DataSource.getSizeByStatus("RUNNING") < Constants.MAX_JOBS) {
                                Job job = DataSource.getJobByStatus("QUEUED");
                                job.setStatus("RUNNING");
                            }
                        }
                    }
                }).start();
                System.out.println("Waiting for client on port " +
                        serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();

                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                String responseString = requestHandler.handleIncomingRequest(in.readUTF());
                System.out.println("Response " + responseString);
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
                        + "\nGoodbye! " + responseString);
                server.close();

            }catch(SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            }catch(IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private void init() {
        DataSource.jobs = new HashMap<Long, Job>();
    }
}
