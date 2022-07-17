package jobs.jobs.core;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import jobs.jobs.Jobs;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;

public class DatabaseUtils {

    private static final String databaseName = "userdata";

    public Document makePlayerDocument(String uuid, String name, int beruf, int exp1, int exp2, int exp3, int exp4, int exp5, int mined, int placed, int kills, int deaths, long time, int animals, int monsters){
        Document result = new Document();
        Document berufe = new Document();

        berufe.put("1", exp1);
        berufe.put("2", exp2);
        berufe.put("3", exp3);
        berufe.put("4", exp4);
        berufe.put("5", exp5);

        result.put("uuid", uuid);
        result.put("name", name);
        result.put("currentJob", beruf);
        result.put("currentExp", berufe);

        result.put("blocksMined", mined);
        result.put("blocksPlaced", placed);
        result.put("playersKilled", kills);
        result.put("deaths", deaths);
        result.put("playtime", time);
        result.put("animalsKilled", animals);
        result.put("monstersKilled", monsters);

        return result;
    }

    public boolean writePlayer(Document in){

        MongoCollection collection = Database.getDb().getDatabase("userdata").getCollection("players");

        collection.insertOne(in);

        return true;
    }

    public Document getPlayer(String uuid){
        Jobs.log("Trying to load "+uuid+" from DB");
        MongoCollection collection = Database.getDb().getDatabase(databaseName).getCollection("players");

        //Filtern welche Felder des Dokuments returned werden sollen
        Bson projectionFields = Projections.fields(
                Projections.include("uuid", "name", "currentJob", "currentExp", "blocksMined", "blocksPlaced", "playersKilled", "deaths", "playtime", "animalsKilled", "monstersKilled"),
                Projections.excludeId()
        );
        //Spieler finden, funktion liefert null falls Spieler nicht gefunden oder error
        try{
            return (Document) collection.find(eq("uuid", uuid)).projection(projectionFields).first();
        } catch(MongoException e){
            Jobs.log("[ERROR]: find DB error:"+e.getMessage());
            return null;
        }
    }

    public boolean updatePlayer(String uuid, Document d){
        Jobs.log("Playerdocument is beeing updated");
        MongoCollection collection = Database.getDb().getDatabase(databaseName).getCollection("players");

        //Such Key-Value paar
        Document query = new Document().append("uuid", uuid);

        //Updates
        Bson updates = Updates.combine(
                Updates.set("name", d.get("name").toString()),
                Updates.set("currentJob", d.get("currentJob").toString()),
                Updates.set("currentExp", d.get("currentExp")),
                Updates.set("blocksMined", d.get("blocksMined").toString()),
                Updates.set("blocksPlaced", d.get("blocksPlaced").toString()),
                Updates.set("playersKilled", d.get("playersKilled").toString()),
                Updates.set("deaths", d.get("deaths").toString()),
                Updates.set("playtime", d.get("playtime").toString()),
                Updates.set("animalsKilled", d.get("animalsKilled").toString()),
                Updates.set("monstersKilled", d.get("monstersKilled").toString())
        );
        //Optionen: Dokument aktualisieren, ggf. neu erstellen
        UpdateOptions options = new UpdateOptions().upsert(true);

        //Funktionsaufruf, true bei erfolg, false bei fehler
        try{
            UpdateResult result = collection.updateOne(query, updates, options);
            Jobs.log("Update success! Results: Amount:"+result.getModifiedCount()+", Upserted: "+result.getUpsertedId());
            return true;
        } catch (MongoException me){
            Jobs.log("[ERROR] : Update DB error:"+ me.getMessage());
            return false;
        }
    }

    public void dropPlayer(String name){
        Jobs.log("Destroying Player Document for "+name);
        MongoCollection collection = Database.getDb().getDatabase(databaseName).getCollection("players");

        try{
            DeleteResult result = collection.deleteOne(Filters.eq("name", name));
            Jobs.log(name+" is no more...");
        } catch (MongoException e){
            Jobs.log("Error @DropPlayer: "+e.getMessage());
        }
    }

}
