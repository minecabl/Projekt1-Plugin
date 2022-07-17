package jobs.jobs.core;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jobs.jobs.Jobs;

public final class Database {
    private static Database db;
    //private static MongoClient client = MongoClients.create("mongodb+srv://<name>:<passwort>!@projekt1.sgnbqhy.mongodb.net/?retryWrites=true&w=majority");
    private static MongoClient client = MongoClients.create("mongodb://<name>:<passwort>@181.214.231.116:27017/userdata");
    //Username und Passwort wurde zu abgabezwecken durch Platzhalter ersetzt

    private Database(){

    }
    public static MongoClient getDb(){
        if(db == null){
            Jobs.log("Database = null, creating new Instance");
            db = new Database();
        }

        return client;
    }

}
