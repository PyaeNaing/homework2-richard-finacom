package main.java;
import spark.Request;
import spark.Response;

import java.util.Set;

import static spark.Spark.*;
public class Main {

  public static String processRoute(Request req, Response res) {
    Set<String> params = req.queryParams();
    for (String param : params) {
      // possible for query param to be an array
      System.out.println(param + " : " + req.queryParamsValues(param)[0]);
    }
    // do stuff with a mapped version http://javadoc.io/doc/com.sparkjava/spark-core/2.8.0
    // http://sparkjava.com/documentation#query-maps
    // print the id query value
    System.out.println(req.queryMap().get("id").value());
    return "done!";
  }

  public static void main(String[] args) {
      // staticFiles.externalLocation("public");
      // http://sparkjava.com/documentation
      port(1234);
      // calling get will make your app start listening for the GET path with the /hello endpoint
      get("/newuser", (req, res)->{
        String username = req.queryParams("username");
        String password = req.queryParams("password");5
        String response = Processes.newUser(username, password);
      return response;
      });

      get("/login", (req, res)->{
        String username = req.queryParams("username");
        String password = req.queryParams("password");
        String response = Processes.userLogin(username,password);
        return response;
      });

      get("/addfriend", (req, res)->{
        String friend = req.queryParams("friend");
        String token = req.queryParams("token");
        String response = Processes.addfiend(friend, token);

        return response;
      });

      get("/friends", (req, res)->{
        String token = req.queryParams("token");
        String response = Processes.friends(token);

        return response;
      });

    }
}
