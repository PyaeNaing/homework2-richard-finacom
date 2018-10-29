package main.java;

import javax.servlet.http.Cookie;
import java.util.Random;

public class Processes {

    //return false if username is already in use by another user
    public static String createNewUser(String username, String password) {
        DataBase data = DataBase.getInstance();
        if(data.createUser(username, password)) {
            return "okay";
        }
        return "Username already in use.";
    };

    //method used for user login. returns a null cookie if login failed otherwise a valid cookie is returned
    public static String userLogin(String username, String password) {
        DataBase d = DataBase.getInstance();
        String token = generateString();
        Cookie authentication = new Cookie(username, token);
        d.createCookie(authentication);
        if (!(d.loginCheck(username, password))) {
            return "login_failed";
        } else {
            return token;
        }
    }

    public static String generateString() {
        Random x = new Random();
        String w = "";
        for (int i = 0; i < 10; i++) {
             w += x.nextInt(10);
        }
        return w;
    }

    public static String addFriendProcess(String token, String friendUserId) {
        DataBase data = DataBase.getInstance();
        String s = data.checkToken(token);
        if(s.equals(null)) {
            return "failed_authentication";
        } else {
            if(data.addFriend(s, friendUserId)) {
                return "okay";
            } else {
                return "could not add Friend";
            }
        }
    }

    public static String printFriendList(String token) {
        DataBase data = DataBase.getInstance();
        String s = data.checkToken(token);
        if(s.equals(null)) {
            return "failed_authentication";
        } else {
            return data.getFriendList(s);
        }
    }
}
