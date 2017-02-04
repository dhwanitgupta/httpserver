package handlers;

import controllers.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Routes {
    private Map<Pattern, Controller> routes;

    public Routes(){
        routes = new HashMap<Pattern, Controller>();
        routes.put( Pattern.compile("GET job/(\\d+)"), new GetJobById());
        routes.put(Pattern.compile("PUT job"), new CreateJob());
        routes.put(Pattern.compile("DELETE job/(\\d+)"), new DeleteJobById());
        routes.put(Pattern.compile("POST job/(\\d+)/pause"), new PauseJobById());
        routes.put(Pattern.compile("POST job/(\\d+)/resume"), new ResumeJobById());
    }

    public Controller getControllerForRoute(String route){

        Iterator it = routes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Pattern, Controller> pair = (Map.Entry)it.next();

            Pattern pattern = pair.getKey();
            System.out.println(pattern + " " + route);
            Matcher m = pattern.matcher(route);
            if(m.find()){
                System.out.println(" count " + m.groupCount());
                if(m.groupCount() == 1){
                    pair.getValue().setJobId(Long.parseLong(m.group(1)));
                    System.out.println("GROUPED " + m.group(1));
                }
                return pair.getValue();
            }
        }
        return null;
    }

}