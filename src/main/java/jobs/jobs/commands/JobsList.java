package jobs.jobs.commands;

import jobs.jobs.Jobs;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JobsList implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            int exp, cExp, nExp;

            p.sendMessage(Component.text("===[Jobs]===").color(TextColor.color(0, 200, 0)));

            exp = Jobs.INSTANCE.session.getJPlayer(p).getExp(1);
            cExp = Jobs.lvltoxp(Jobs.INSTANCE.session.getJPlayer(p).getLvl(1));
            nExp = Jobs.lvltoxp(Jobs.INSTANCE.session.getJPlayer(p).getLvl(1)+1);
            p.sendMessage(Component.text("Farmer: Lvl: "+Jobs.INSTANCE.session.getJPlayer(p).getLvl(1)+" Exp: "+(exp-cExp)+"/"+(nExp-cExp)));

            exp = Jobs.INSTANCE.session.getJPlayer(p).getExp(2);
            cExp = Jobs.lvltoxp(Jobs.INSTANCE.session.getJPlayer(p).getLvl(2));
            nExp = Jobs.lvltoxp(Jobs.INSTANCE.session.getJPlayer(p).getLvl(2)+1);
            p.sendMessage(Component.text("Miner: Lvl: "+Jobs.INSTANCE.session.getJPlayer(p).getLvl(2)+" Exp: "+(exp-cExp)+"/"+(nExp-cExp)));

            exp = Jobs.INSTANCE.session.getJPlayer(p).getExp(3);
            cExp = Jobs.lvltoxp(Jobs.INSTANCE.session.getJPlayer(p).getLvl(3));
            nExp = Jobs.lvltoxp(Jobs.INSTANCE.session.getJPlayer(p).getLvl(3)+1);
            p.sendMessage(Component.text("Lumberjack: Lvl: "+Jobs.INSTANCE.session.getJPlayer(p).getLvl(3)+" Exp: "+(exp-cExp)+"/"+(nExp-cExp)));

            exp = Jobs.INSTANCE.session.getJPlayer(p).getExp(4);
            cExp = Jobs.lvltoxp(Jobs.INSTANCE.session.getJPlayer(p).getLvl(4));
            nExp = Jobs.lvltoxp(Jobs.INSTANCE.session.getJPlayer(p).getLvl(4)+1);
            p.sendMessage(Component.text("Digger: Lvl: "+Jobs.INSTANCE.session.getJPlayer(p).getLvl(4)+" Exp: "+(exp-cExp)+"/"+(nExp-cExp)));

            exp = Jobs.INSTANCE.session.getJPlayer(p).getExp(5);
            cExp = Jobs.lvltoxp(Jobs.INSTANCE.session.getJPlayer(p).getLvl(5));
            nExp = Jobs.lvltoxp(Jobs.INSTANCE.session.getJPlayer(p).getLvl(5)+1);
            p.sendMessage(Component.text("Fighter: Lvl: "+Jobs.INSTANCE.session.getJPlayer(p).getLvl(5)+" Exp: "+(exp-cExp)+"/"+(nExp-cExp)));
        }

        return true;
    }
}
