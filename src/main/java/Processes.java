package main.java;

import spark.Request;
import spark.Response;
import spark.Route;

import javax.servlet.http.Cookie;
import static spark.Spark.*;

public class Processes {

    //return false if username is already in use by another user
    public static Route createNewUser = (Request request, Response response) -> {
        DataBase data = DataBase.getInstance();
        String x = request.queryParams("username");
        String y = request.queryParams("password");
        if(data.createUser(x, y)) {
            return "okay";
        }
        return "Username already in use.";
    };

    //method used for user login. returns a null cookie if login failed otherwise a valid cookie is returned
    public static Cookie userLogin(String username, String password) {
        DataBase d = DataBase.getInstance();
        String token = Long.toString(System.nanoTime());
        Cookie authentication = new Cookie("token", token);
        if (!(d.loginCheck(username, password))) {
            return null;
        } else {
            return authentication;
        }
    }
}
