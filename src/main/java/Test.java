package main.java;

import org.bson.Document;

public class Test {
    public static void main(String[] args)
    {
        DataBase d = DataBase.getInstance();
        //d.test();

        System.out.println(d.getFriendList("JJ"));

        d.createUser("JJ", "Jee");
        d.createUser("Osbaldo", "o123");
        d.createUser("Pyae", "1234");
        d.createUser("Pyae", "9999");
        d.addFriend("Pyae", "JJ");
        d.addFriend("Pyyae  ", "JJ");
        d.addFriend("Pyae", "KK");
        d.addFriend("Pyae" , "Osbaldo");
        d.addFriend("Pyae" , "Pyae");
        d.createUser("Sawyer", "OwO :3");
        d.addFriend("JJ", "Sawyer");

        /**/
        /*Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new Document("x", 203).append("y", 102));
        doc.append("info", new Document("x",200).append("y",200).append("z",130));
        System.out.println(doc);*/
    }
}
