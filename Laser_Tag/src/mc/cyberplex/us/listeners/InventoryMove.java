package mc.cyberplex.us.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import mc.cyberplex.us.Main;
import mc.cyberplex.us.arena.ArenaData;

public class InventoryMove implements Listener{
	
	Main main = Main.getMain();
	ArenaData data = new ArenaData();
	
	@EventHandler
	public void denyInventoryMove(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		
		for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {
			
			int arenaNum = data.getArenaNum(arenaName);
			
			for(int count = 0; count < data.getArena(arenaNum).getInGameCount(); count++) {
				
				if(player.getUniqueId().toString().equals(data.getArena(arenaNum).getInGame(count))) {
					event.setCancelled(true);
				}
				
			}
			
		}
		
	}

}
