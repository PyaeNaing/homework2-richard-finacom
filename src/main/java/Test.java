package main.java;

import org.bson.Document;

public class Test {
    public static void main(String[] args)
    {
        DataBase d = DataBase.getInstance();
        d.storedata("JJ", "suxdek");
        //d.loginCheck("Pyae", "1234");
        //d.update("Pyae", "1334");
        /**/
        /*Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new Document("x", 203).append("y", 102));
        doc.append("info", new Document("x",200).append("y",200).append("z",130));
        System.out.println(doc);*/
    }
}
