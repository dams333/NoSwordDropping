package ch.dams333.noSwordDropping;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class NoSwordDropping extends JavaPlugin implements Listener {

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("choose").setExecutor(new ChooseCommand(this));
    }

    @EventHandler
    public void death(PlayerDeathEvent e){
        if(getConfig().isSet("Name")){
            String name = getConfig().getString("Name");
            List<ItemStack> remove = new ArrayList<>();
            for(ItemStack it : e.getDrops()){
                if(it.hasItemMeta() && it.getItemMeta().getDisplayName().equals(name)){
                    remove.add(it);
                }
            }
            for(ItemStack it : remove){
                e.getDrops().remove(it);
            }
        }
    }

}
