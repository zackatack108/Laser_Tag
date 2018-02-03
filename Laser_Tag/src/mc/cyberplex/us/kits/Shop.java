package mc.cyberplex.us.kits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Shop implements Listener{
	
	LaserColor lsColor = new LaserColor();
	FireworkColor fwColor = new FireworkColor();
	GunType gnType = new GunType();
	GunRange gnRange = new GunRange();
	ReloadTime rlTime = new ReloadTime();

	//create laser tag shop
	public void createShop(Player player) {
		
		//create an inventory object for the laser tag shop
		Inventory ltShop = Bukkit.createInventory(null, 27, ChatColor.DARK_RED + "Laser Tag Shop");

		//create the laser color item to be put in the inventory
		ItemStack laserColor = new ItemStack(Material.STAINED_GLASS, 1, (byte) 14);
		ItemMeta laserMeta = laserColor.getItemMeta();
		laserMeta.setDisplayName(ChatColor.YELLOW + "Choose laser color");
		laserColor.setItemMeta(laserMeta);
		
		//create the firework color item to be put in the inventory
		ItemStack fireworkColor = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta fireworkMeta = fireworkColor.getItemMeta();
		fireworkMeta.setDisplayName(ChatColor.YELLOW + "Choose firework color");
		fireworkColor.setItemMeta(fireworkMeta);
		
		//create the gun item to be put in the inventory
		ItemStack gun = new ItemStack(Material.STONE_HOE, 1);
		ItemMeta gunMeta = gun.getItemMeta();
		gunMeta.setDisplayName(ChatColor.YELLOW + "Choose gun type");
		gun.setItemMeta(gunMeta);
		
		//create the shoot range item to be put in the inventory
		ItemStack range = new ItemStack(Material.ENDER_PEARL, 1);
		ItemMeta rangeMeta = range.getItemMeta();
		rangeMeta.setDisplayName(ChatColor.YELLOW + "Choos gun range");
		range.setItemMeta(rangeMeta);
		
		//create the reload time item to be put in the inventory
		ItemStack reload = new ItemStack(Material.WATCH, 1);
		ItemMeta reloadMeta = reload.getItemMeta();
		reloadMeta.setDisplayName(ChatColor.YELLOW + "Choose reload time");
		reload.setItemMeta(reloadMeta);
		
		//set the items into the shop inventory
		ltShop.setItem(11, laserColor);
		ltShop.setItem(12, fireworkColor);
		ltShop.setItem(13, gun);
		ltShop.setItem(14, range);
		ltShop.setItem(15, reload);
		
		//open the laser tag shop to the player
		player.openInventory(ltShop);
		
	}

	//listener for the shop so the shop can be used
	@EventHandler
	public void shopListener(InventoryClickEvent event) {
		
		//get the player that is clicking in the inventory 
		Player player = (Player) event.getWhoClicked();
		
		//check to see if the inventory the player has open is the laser tag shop
		if(event.getInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "Laser tag shop")) {
			
			switch(event.getSlot()) {
			
			case 11:
				
				//cancel the event so the item isn't pulled from the inventory
				event.setCancelled(true);
				
				//open the inventory for the laser color
				lsColor.createLaserShop(player);
				
				break;
				
			case 12:
				
				//cancel the event so the item isn't pulled from the inventory
				event.setCancelled(true);
				
				//open the inventory for the firework color
				
				break;
				
			case 13:
				
				//cancel the event so the item isn't pulled from the inventory
				event.setCancelled(true);
				
				//open the inventory for gun type
				
				break;
				
			case 14:
				
				//cancel the event so the item isn't pulled from the inventory
				event.setCancelled(true);
				
				//open the inventory for the range
				
				break;
				
			case 15:
				
				//cancel the event so the item isn't pulled from the inventory
				event.setCancelled(true);
				
				//open the inventory for the reload time
				
				break;
			
			}
			
			//cancel the event so the item isn't pulled from the inventory
			event.setCancelled(true);
			
		}
		
	}
	
}
