package jobs.jobs.listeners;

import jobs.jobs.Jobs;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;

public class JobEvent implements Listener {

    @EventHandler
    public void onPlayerBreakWood(BlockBreakEvent e){
        if(e.getPlayer() == null){
            return;
        }

        Component msg = Component.text("[Lumberjack] ").color(TextColor.color(0, 200, 0)   );

        if(e.getBlock().getType().name().endsWith("_LOG") == true){
            int exp = 10;

            if(Jobs.INSTANCE.session.getJPlayer(e.getPlayer()).getJob() == 3 ){
                exp *= 2;
                msg = msg.append(Component.text("+"+exp+"[x2]").color(TextColor.color(255, 255, 255)) );
            } else {
                msg = msg.append(Component.text("+"+exp).color(TextColor.color(255, 255, 255)));
            }

            e.getPlayer().sendMessage(msg);

            Jobs.INSTANCE.session.getJPlayer(e.getPlayer()).addExp(3, exp);

        } else {
            return;
        }

        return;
    }

    @EventHandler
    public void onPlayerBreakStones(BlockBreakEvent e){
        if(e.getPlayer() == null){
            return;
        }

        Component msg = Component.text("[Miner] ").color(TextColor.color(0, 200, 0)   );
        int exp;

        switch(e.getBlock().getType().name()){
            case "COAL_ORE": exp = 10; break;
            case "IRON_ORE": exp = 20; break;
            case "GOLD_ORE": exp = 30; break;
            case "EMERALD_ORE": exp = 200; break;
            case "DIAMOND_ORE": exp = 100; break;
            case "COPPER_ORE": exp = 15; break;
            case "REDSTONE_ORE": exp = 20; break;
            case "LAPIS_ORE": exp = 50; break;

            case "DEEPSLATE_COAL_ORE": exp = 10; break;
            case "DEEPSLATE_IRON_ORE": exp = 20; break;
            case "DEEPSLATE_GOLD_ORE": exp = 30; break;
            case "DEEPSLATE_EMERALD_ORE": exp = 200; break;
            case "DEEPSLATE_DIAMOND_ORE": exp = 100; break;
            case "DEEPSLATE_COPPER_ORE": exp = 15; break;
            case "DEEPSLATE_REDSTONE_ORE": exp = 20; break;
            case "DEEPSLATE_LAPIS_ORE": exp = 50; break;

            case "STONE": exp = 1; break;
            case "ANDESITE": exp = 1; break;
            case "GRANITE": exp = 1; break;
            case "DIORITE": exp = 1; break;

            default: return;
        }

        if(Jobs.INSTANCE.session.getJPlayer(e.getPlayer()).getJob() == 2 ){
            exp *= 2;
            msg = msg.append(Component.text("+"+exp+"[x2]").color(TextColor.color(255, 255, 255)) );
        } else {
            msg = msg.append(Component.text("+"+exp).color(TextColor.color(255, 255, 255)));
        }

        e.getPlayer().sendMessage(msg);

        Jobs.INSTANCE.session.getJPlayer(e.getPlayer()).addExp(2, exp);

        return;
    }

    @EventHandler
    public void onPlayerHarvestSomething(BlockBreakEvent e){
        if(e.getPlayer() == null) return;

        int exp;

        Component msg = Component.text("[Farmer] ").color(TextColor.color(0, 200, 0)   );

        switch(e.getBlock().getType().name()){
            case "WHEAT": exp = 5; break;
            case "POTATO": exp = 5; break;
            case "CARROT": exp = 5; break;
            case "NETHER_WART": exp = 10; break;
            case "BEETROOT": exp = 10; break;
            case "SUGAR_CANE": exp = 10; break;

            default: return;
        }

        if( Jobs.INSTANCE.session.getJPlayer(e.getPlayer()).getJob() == 1 ){
            exp *= 2;
            msg = msg.append(Component.text("+"+exp+"[x2]").color(TextColor.color(255, 255, 255)) );
        } else {
            msg = msg.append(Component.text("+"+exp).color(TextColor.color(255, 255, 255)));
        }

        e.getPlayer().sendMessage(msg);

        Jobs.INSTANCE.session.getJPlayer(e.getPlayer()).addExp(1, exp);

        return;
    }

    @EventHandler
    public void onPlayerDigBlock(BlockBreakEvent e){
        if(e.getPlayer() == null) return;

        int exp;

        Component msg = Component.text("[Digger] ").color(TextColor.color(0, 200, 0)   );

        switch(e.getBlock().getType().name()){
            case "GRASS_BLOCK": exp = 1; break;
            case "DIRT": exp = 1; break;
            case "DIRT_PATH": exp = 1; break;
            case "GRAVEL": exp = 5; break;
            case "SAND": exp = 1; break;
            case "RED_SAND": exp = 1; break;
            default: return;
        }
        if( Jobs.INSTANCE.session.getJPlayer(e.getPlayer()).getJob() == 4 ){
            exp *= 2;
            msg = msg.append(Component.text("+"+exp+"[x2]").color(TextColor.color(255, 255, 255)) );
        } else {
            msg = msg.append(Component.text("+"+exp).color(TextColor.color(255, 255, 255)));
        }

        e.getPlayer().sendMessage(msg);

        Jobs.INSTANCE.session.getJPlayer(e.getPlayer()).addExp(4, exp);

        return;
    }
    @EventHandler
    public void onPlayerKillMob(EntityDeathEvent e){
        if(e.getEntity().getKiller() instanceof Player){
            int exp, max, min;
            Component msg = Component.text("[Fighter] ").color(TextColor.color(0, 200, 0)   );

            switch(e.getEntity().getName().toUpperCase()){
                case "CREEPER":
                case "ENDERMITE":
                case "PILLAGER":
                case "STRAY":
                    max = 200; min = 150; break;
                case "ZOMBIE":
                case "DROWNED":
                case "ZOMBIE_VILLAGER":
                case "HUSK":
                case "SPIDER":
                    max = 100; min = 50; break;
                case "SKELETON": max = 150; min = 100; break;
                case "WITHER_SKELETON":
                case "PHANTOM":
                case "SHULKER":
                case "WITCH":
                    max = 300; min = 250; break;
                case "BLAZE": max = 250; min = 200; break;
                case "ELDER_GUARDIAN": max = 1500; min = 1000; break;
                case "ENDERMAN":
                case "GUADRIAN":
                case "HOGLIN":
                case "ZOGLIN":
                    max = 400; min = 300; break;
                case "ENDER_DRAGON": max = 20000; min = 17500; break;
                case "EVOKER": max = 1000; min = 500; break;
                case "GHAST":
                case "RAVAGER":
                case "VINDICATOR":
                    max = 750; min = 500; break;
                case "MAGMA_CUBE":
                case "SILVERFISH":
                case "SLIME":
                    max = 50; min = 30; break;
                case "PIGLIN_BRUTE": max = 500; min = 400; break;
                case "WARDEN": max = 5000; min = 4000; break;

                default: return;
            }
            exp = (int)Math.floor(Math.random()*(max-min+1)+min);

            if( Jobs.INSTANCE.session.getJPlayer(e.getEntity().getKiller()).getJob() == 5 ){
                exp *= 2;
                msg = msg.append(Component.text("+"+exp+"[x2]").color(TextColor.color(255, 255, 255)) );
            } else {
                msg = msg.append(Component.text("+"+exp).color(TextColor.color(255, 255, 255)));
            }

            e.getEntity().getKiller().sendMessage(msg);

            Jobs.INSTANCE.session.getJPlayer(e.getEntity().getKiller()).addExp(5, exp);

        } else {
            return;
        }
        return;
    }
}
