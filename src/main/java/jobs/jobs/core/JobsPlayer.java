package jobs.jobs.core;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jobs.jobs.Jobs;
import org.bson.Document;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;



public class JobsPlayer {
    public Player jplayer;
    private int currentJob;
    private int[] exp;
    private int[] lvl;
    public int blocksMinded;
    public int blocksPlaced;
    public int playersKilled;
    public int deaths;
    public long playtime;
    public int animalsKilled;
    public int monstersKilled;
    public long logintime;

    JobsPlayer(Player p, Document data){
        Jobs.log("Neuer JobsPlayer wird erstellt");
        exp = new int[6];
        lvl = new int[6];
        this.jplayer = p;

        this.logintime = System.currentTimeMillis() / 1000;

        /*p.sendMessage("Current stats:");
        p.sendMessage("Current Job: "+data.get("currentJob"));
        p.sendMessage("Current EXP: "+data.get("currentExp"));*/

        if(data.get("currentJob") == null){
            currentJob = 0;
        } else {
            currentJob = Integer.parseInt(data.get("currentJob").toString());
        }

        Document berufe = (Document)data.get("currentExp");

        JsonObject b = JsonParser.parseString(berufe.toJson()).getAsJsonObject();

        for(int i = 1; i < 6; i++){
            exp[i] = Integer.parseInt(b.get(""+i).getAsString()) ;
            lvl[i] = Jobs.xptolvl(exp[i]);
        }

        this.blocksMinded = Integer.parseInt(data.get("blocksMined").toString());
        this.blocksPlaced = Integer.parseInt(data.get("blocksPlaced").toString());
        this.playersKilled = Integer.parseInt(data.get("playersKilled").toString());
        this.deaths = Integer.parseInt(data.get("deaths").toString());
        this.playtime = Long.parseLong(data.get("playtime").toString());
        this.animalsKilled = Integer.parseInt(data.get("animalsKilled").toString());
        this.monstersKilled = Integer.parseInt(data.get("monstersKilled").toString());

    }

    public Document getPlayerDocument(){

        Document result = new Document();

        Document berufe = new Document();
        for(int i = 1; i < 6; i++){
            berufe.put(""+i, exp[i]);
        }

        result.put("name", this.jplayer.getPlayer().getName());
        result.put("currentJob", this.currentJob);
        result.put("currentExp", berufe);
        result.put("blocksMined", this.blocksMinded);
        result.put("blocksPlaced", this.blocksPlaced);
        result.put("playersKilled", this.playersKilled);
        result.put("deaths", this.deaths);
        result.put("playtime", this.playtime);
        result.put("animalsKilled", this.animalsKilled);
        result.put("monstersKilled", this.monstersKilled);
        return result;
    }

    public int getJob(){return this.currentJob;}

    public void addExp(int job, int rexp){
        this.exp[job] += rexp;

        if(this.exp[job] > Jobs.lvltoxp(this.lvl[job]+1)){
            this.lvl[job]++;
            this.jplayer.playSound(this.jplayer.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0f, 1.0f);
            this.jplayer.sendMessage("You reached Level "+this.lvl[job]+" "+ Jobs.INSTANCE.session.getJobName(job)+"!");
            for(int i = 0; i < 50; i++){
                Location test = new Location(this.jplayer.getWorld(), this.jplayer.getLocation().getX()+Math.random()-0.5, this.jplayer.getLocation().getY()+Math.random()+0.7, this.jplayer.getLocation().getZ()+Math.random()-0.5);
                this.jplayer.spawnParticle(Particle.ENCHANTMENT_TABLE, test, 3);
            }

        }
    }

    public int getExp(int i) {
        return this.exp[i];
    }

    public int getLvl(int i) {
        return this.lvl[i];
    }

}
