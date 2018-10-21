package main.java;

import javax.servlet.http.Cookie;

public class Processes {

    //return false if username is already in use by another user
    public static Boolean createNewUser(String username, String password) {
        //change length variable to the number of users in the database
        int length = 50;
        for (int i = 0; i < length; i++) {
            //check all the database for the usernames to compare
            //if username is already taken return false;
        }
        //create a new User object using the username and password
        //store the User object into the database
        return true;
    }

    public static Cookie userLogin(String username, String password) {
        //change length variable to the number of users in the database
        int length = 50;
        for (int i = 0; i < length; i++) {
            //check all the database for the username and compare the passwords
            //if passwords match return the cookie else break out of the loop
        }
       return null;
    }
}
