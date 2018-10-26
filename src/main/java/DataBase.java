package main.java;
import com.mongodb.DBCollection;
import static com.mongodb.client.model.Filters.*;
//import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.eclipse.jetty.server.Authentication;
import sun.security.util.Password;

import javax.print.Doc;
import java.util.ArrayList;


public class DataBase {

    private  static DataBase instance = null;

    private MongoClient mongoClient;
    private MongoDatabase db;
    private MongoCollection<Document> myCollection;

    private DataBase()
    {
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("Test");
        myCollection = db.getCollection("Users");

    }

    public boolean createUser(String username, String password)
    {
        Document search = myCollection.find(eq("User", username)).first();
        if(search == null)
        {
            Document doc = new Document("User", username)
                    .append("Password", password).append("Friends", new Document());
            myCollection.insertOne(doc);
            return true;
        }
        return false;
    }

    public boolean loginCheck(String username, String password)
    {
        try {
            Document search = myCollection.find(eq("User", username)).first();
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
            Document search = myCollection.find(eq("User", friend)).first();
            Document user1 = myCollection.find(eq("User", user)).first();
            if (search != null) {
                String f = "Friend." + friend;
                Document updateInstruction = new Document("$set", new Document(f, friend));
                myCollection.updateOne(user1, updateInstruction);
                return true;
            }
        }catch (Exception e)
        {
            return false;
        }
        return false;
    }

    public void friendList(String user)
    {
        Document search = myCollection.find(eq("User", user)).first();
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }


    // testing field
    public void test()
    {
        Document search = myCollection.find(eq("User", "JJ")).first();

        Document updateInstruction = new Document("$set", new Document("Friends.3", "P3333"));
        //System.out.println(search.get("Friends"));
        System.out.println(search.get("Friends"));
        myCollection.updateOne(search,updateInstruction);
        //System.out.println(updateInstruction);

        //doc.append("info", new Document("z",20));
        //Document temp = new Document();
       // temp.append("info", doc.get("info"));

    }
    public void update(String username, String password)
    {
        myCollection.updateOne(eq("User", username), new Document("$set", new Document("Friend", 110)));

    }
}
