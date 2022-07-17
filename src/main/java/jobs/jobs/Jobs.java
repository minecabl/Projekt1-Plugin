package jobs.jobs;

import jobs.jobs.commands.JobsList;
import jobs.jobs.commands.dropPlayer;
import jobs.jobs.core.DatabaseUtils;
import jobs.jobs.core.Session;
import jobs.jobs.listeners.JobEvent;
import jobs.jobs.listeners.PlayerJoin;
import jobs.jobs.listeners.PlayerLeave;
import jobs.jobs.listeners.StatEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class Jobs extends JavaPlugin {

    public static Jobs INSTANCE;
    public DatabaseUtils db;
    public Session session;


    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;

        db = new DatabaseUtils();

        session = new Session();

        this.register();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static void log(String msg){
        Bukkit.getConsoleSender().sendMessage(msg);
    }

    private void register(){
        PluginManager PM = Bukkit.getPluginManager();

        PM.registerEvents(new PlayerJoin(), this);
        PM.registerEvents(new PlayerLeave(), this);
        PM.registerEvents(new JobEvent(), this);
        PM.registerEvents(new StatEvents(), this);

        getCommand("jobs").setExecutor(new JobsList());
        getCommand("destroy").setExecutor(new dropPlayer());
    }

    public static int xptolvl(int xp){
        return (int)Math.sqrt((xp-100)/25);
    }

    public static int lvltoxp(int lvl){
        return (int)((Math.pow(lvl, 2) * 25)+100);
    }
}

