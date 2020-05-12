package ch.dams333.noSwordDropping;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChooseCommand implements CommandExecutor {
    NoSwordDropping main;
    public ChooseCommand(NoSwordDropping noSwordDropping) {
        this.main = noSwordDropping;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.getInventory().getItemInHand() != null && p.getInventory().getItemInHand().hasItemMeta()){
                main.getConfig().set("Name", p.getInventory().getItemInHand().getItemMeta().getDisplayName());
                main.saveConfig();
                return true;
            }
            p.sendMessage(ChatColor.RED + "Veuillez choisir un item avec un nom particulier");
            return true;
        }
        sender.sendMessage(ChatColor.RED + "Il faut etre connecté sur le serveur pour faire celà");
        return false;
    }
}
