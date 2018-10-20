package main.java;
import com.mongodb.DBCollection;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


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
                .append("Password", password);
        // insert document into collection
        myCollection.insertOne(doc);
    }

    public String getdata()
    {
        return "";
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
