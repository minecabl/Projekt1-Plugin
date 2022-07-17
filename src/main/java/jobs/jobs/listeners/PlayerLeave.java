package jobs.jobs.listeners;

import jobs.jobs.Jobs;
import org.bson.Document;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Jobs.INSTANCE.session.getJPlayer(e.getPlayer()).playtime += System.currentTimeMillis()/1000 - Jobs.INSTANCE.session.getJPlayer(e.getPlayer()).logintime;

        Document d = Jobs.INSTANCE.session.getJPlayer(e.getPlayer()).getPlayerDocument();

        Jobs.INSTANCE.db.updatePlayer(e.getPlayer().getUniqueId().toString(), d);

        Jobs.INSTANCE.session.removePlayer(e.getPlayer());
    }
}
