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

    public void storedata(String username, String password)
    {

        Document doc = new Document("User", username)
                .append("Password", password).append("Friennds", new Document());
        myCollection.insertOne(doc);
    }

    public void update(String username, String password)
    {
        myCollection.updateOne(eq("User", username), new Document("$set", new Document("Friend", 110)));

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

    public static DataBase getInstance()
    {
        if(instance == null)
        {
            instance = new DataBase();
        }
        return instance;
    }
}
