package mc.cyberplex.us.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import mc.cyberplex.us.kits.GetKitItems;

public class GunFire implements Listener{

	GetKitItems kit = new GetKitItems();

	@EventHandler
	public void getGunClick(PlayerInteractEvent event) {

		Player player = event.getPlayer();

		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR) {

			ItemStack gunType = new ItemStack(kit.getGunType(player));
			
			if(player.getInventory().getItemInMainHand().equals(gunType)) {
				
				Bukkit.broadcastMessage("gun was clicked");
				
			}

		}

	}

}
