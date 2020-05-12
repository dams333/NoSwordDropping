package ch.dams333.noSwordDropping;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoSwordDropping extends JavaPlugin implements Listener {

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("choose").setExecutor(new ChooseCommand(this));
    }

    private Map<Player, ItemStack> give = new HashMap<>();

    @EventHandler
    public void death(PlayerDeathEvent e){
        if(getConfig().isSet("Name")){
            String name = getConfig().getString("Name");
            List<ItemStack> remove = new ArrayList<>();
            for(ItemStack it : e.getDrops()){
                if(it.hasItemMeta() && it.getItemMeta().getDisplayName().equals(name)){
                    remove.add(it);
                    give.put(e.getEntity(), it);
                }
            }
            for(ItemStack it : remove){
                e.getDrops().remove(it);
            }
        }
    }

    @EventHandler
    public void respawn(PlayerRespawnEvent e){
        if(give.keySet().contains(e.getPlayer())){
            e.getPlayer().getInventory().addItem(give.get(e.getPlayer()));
            give.remove(e.getPlayer());
        }
    }

}
