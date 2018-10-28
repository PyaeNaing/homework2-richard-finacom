package main.java;
import static com.mongodb.client.model.Filters.*;
//import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import javax.servlet.http.Cookie;
import org.eclipse.jetty.server.Authentication;

import javax.print.Doc;
import java.util.ArrayList;


public class DataBase {

    private  static DataBase instance = null;

    private MongoClient mongoClient;
    private MongoDatabase db;
    private MongoCollection<Document> myCollectionUsers;
    private MongoCollection<Document> myCollectionToken;

    private DataBase()
    {
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("REST2");
        myCollectionUsers = db.getCollection("users ");
        myCollectionToken = db.getCollection("auth ");


    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    //This is Users

    public boolean createUser(String username, String password)
    {
        Document search = myCollectionUsers.find(eq("User", username)).first();
        if(search == null)
        {
            Document doc = new Document("User", username)
                    .append("Password", password).append("Friends", new Document());
            myCollectionUsers.insertOne(doc);
            return true;
        }
        return false;
    }

    public boolean loginCheck(String username, String password)
    {
        try {
            Document search = myCollectionUsers.find(eq("User", username)).first();
            return (search.getString("User").equals(username)&&
                    search.getString("Password").equals(password));
        }catch (Exception e)
        {
            return false;
        }
    }

    public boolean addFriend(String user, String friend)
    {
        //Find Friend and Add them.
        //How do you find friend?
        try {
            Document search = myCollectionUsers.find(eq("User", friend)).first();
            Document user1 = myCollectionUsers.find(eq("User", user)).first();
            if (search != null && !user.equals(friend)) {
                String f = "Friends." + friend;
                Document updateInstruction = new Document("$set", new Document(f, friend));
                myCollectionUsers.updateOne(user1, updateInstruction);
                return true;
            }
        }catch (Exception e)
        {
            return false;
        }
        return false;
    }

    public String getFriendList(String user)
    {
        String x = "[]";
        try {
            Document search = myCollectionUsers.find(eq("User", user)).first();
            Document t = (Document) search.get("Friends");
            x = t.values().toString();
        } catch (Exception e) {

        }
        return x;
    }

    //Auth

    public void createCookie(Cookie cookie) {
        String key = cookie.getValue();
        String userName = cookie.getName();
        Document doc = new Document(key, userName);
        myCollectionToken.insertOne(doc);
    }

    public String checkToken(String token) {
        return null;
    }

}
