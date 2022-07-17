package jobs.jobs.commands;


import jobs.jobs.Jobs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

public class dropPlayer implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender || sender.getName() == "Kabl"){
            if(args.length == 0){
                sender.sendMessage("Bitte namen des spielers angeben");
                return false;
            } else {
                Jobs.INSTANCE.db.dropPlayer(args[0]);
                Jobs.log("Dropped Player: "+args[0]);
                return true;
            }
        } else {
            sender.sendMessage("Du darst das nicht");
            return true;
        }


    }
}
