package mc.cyberplex.LaserTag.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import mc.cyberplex.LaserTag.Main;
import mc.cyberplex.LaserTag.arena.Arena;

public class InventoryMove implements Listener{
	
	Main main = Main.getMain();
	Arena data = new Arena();
	
	@EventHandler
	public void denyInventoryMove(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		
		for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {
			
			int arenaNum = data.getArenaNum(arenaName);
			
			for(int count = 0; count < data.getArena(arenaNum).getGameCount(); count++) {
				
				if(player.getUniqueId().equals(data.getArena(arenaNum).getPlayer(count))) {
					event.setCancelled(true);
				}
				
			}
			
		}
		
	}

}
