package jobs.jobs.listeners;

import jobs.jobs.Jobs;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class StatEvents implements Listener {

    @EventHandler
    public void onPlayerBreakAnything(BlockBreakEvent e){
        if(e.getPlayer() != null){
            Jobs.INSTANCE.session.getJPlayer(e.getPlayer()).blocksMinded++;
        }
        return;
    }

    @EventHandler
    public void onPlayerPlaceAnything(BlockPlaceEvent e){
        if(e.getPlayer() != null){
            Jobs.INSTANCE.session.getJPlayer(e.getPlayer()).blocksPlaced++;
        }
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent e){
        if(e.getPlayer() != null){
            Jobs.INSTANCE.session.getJPlayer(e.getPlayer()).deaths++;
        }

        if(e.getPlayer().getKiller() instanceof Player){
            Jobs.INSTANCE.session.getJPlayer(e.getPlayer().getKiller()).playersKilled++;
        }
    }

    @EventHandler
    public void onMonsterKill(EntityDeathEvent e){
        if(e.getEntity().getKiller() instanceof Player){
            switch(e.getEntity().getName().toUpperCase()){
                case "CREEPER":
                case "ENDERMITE":
                case "PILLAGER":
                case "STRAY":
                case "ZOMBIE":
                case "DROWNED":
                case "ZOMBIE_VILLAGER":
                case "HUSK":
                case "SPIDER":
                case "SKELETON":
                case "WITHER_SKELETON":
                case "PHANTOM":
                case "SHULKER":
                case "WITCH":
                case "BLAZE":
                case "ELDER_GUARDIAN":
                case "ENDERMAN":
                case "GUADRIAN":
                case "HOGLIN":
                case "ZOGLIN":
                case "ENDER_DRAGON":
                case "EVOKER":
                case "GHAST":
                case "RAVAGER":
                case "VINDICATOR":
                case "MAGMA_CUBE":
                case "SILVERFISH":
                case "SLIME":
                case "PIGLIN_BRUTE":
                case "WARDEN":
                    Jobs.INSTANCE.session.getJPlayer(e.getEntity().getKiller()).monstersKilled++;
                    break;

                case "PIG":
                case "COW":
                case "CHICKEN":
                case "SQUID":
                case "HORSE":
                case "SHEEP":
                case "CAT":
                case "DONKEY":
                case "RABBIT":
                case "WOLF":
                case "PANDA":
                case "BEE":
                    Jobs.INSTANCE.session.getJPlayer(e.getEntity().getKiller()).animalsKilled++;
                    break;

                default: return;
            }
        }


    }

}
