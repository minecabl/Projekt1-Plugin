package jobs.jobs.core;

import jobs.jobs.Jobs;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Session {
    private HashMap<UUID, JobsPlayer> session;

    public Session(){session = new HashMap<UUID, JobsPlayer>();};

    public void addPlayer(Player p, Document d){
        this.session.put(p.getUniqueId(), new JobsPlayer(p, d));
        Jobs.log("Spieler "+p.getName()+" wurde session hinzugefügt");
    }

    public void removePlayer(Player p){
        this.session.remove(p.getUniqueId());
        Jobs.log("Spieler "+p.getName()+" wurde aus session entfernt");
    }

    public JobsPlayer getJPlayer(Player p){
        if(session.get(p.getUniqueId()) != null){
            return session.get(p.getUniqueId());
        } else {
            Jobs.log("Unbekannter Spieler in Session gesucht: "+p.getName()+" | "+p.getUniqueId());
            p.sendMessage("Ein Fehler ist aufgetreten, bitte melde dies einem Admin und Verbinde dich neu zum Server");
            return null;
        }
    }

    public String getJobName(int i){
        switch (i){
            case 1: return "Farmer";
            case 2: return "Miner";
            case 3: return "Lumberjack";
            case 4: return "Digger";
            case 5: return "Fighter";
            default: return "Wer auch immer das geschrieben hat war zu blöd";
        }
    }

}
