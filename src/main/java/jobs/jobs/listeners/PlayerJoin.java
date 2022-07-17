package jobs.jobs.listeners;

import jobs.jobs.Jobs;
import net.kyori.adventure.text.Component;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;



public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Jobs.log("Player Join Event!: "+e.getPlayer().getUniqueId());

        //Abfrage an die Datenbank: Existiert der Spielereintrag, liefert dies ein Dokument, falls nicht null
        Document result = Jobs.INSTANCE.db.getPlayer(p.getUniqueId().toString());


        if(result == null){
            Jobs.log("Unknown Player, generating new Player Document");
            Bukkit.broadcast(Component.text("Welcome to the server "+e.getPlayer().getName()));

            result = Jobs.INSTANCE.db.makePlayerDocument(e.getPlayer().getUniqueId().toString(), e.getPlayer().getName(), 0, 100, 100, 100, 100, 100, 0, 0, 0, 0, 0, 0, 0);
            Jobs.INSTANCE.db.writePlayer(result);

        } else {
            Jobs.log("Player found!");
            Jobs.log("Document loaded: "+result);
            e.getPlayer().sendMessage("Welcome back!");
        }

        //Spieler der Laufzeit-Session hinzufügen
        Jobs.INSTANCE.session.addPlayer(e.getPlayer(), result);

    }
}
