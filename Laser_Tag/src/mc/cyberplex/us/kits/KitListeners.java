package mc.cyberplex.us.kits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import mc.cyberplex.us.Main;
import mc.cyberplex.us.kits.enumerations.Colors;
import mc.cyberplex.us.kits.enumerations.CoolDownTime;
import mc.cyberplex.us.kits.enumerations.GunRanges;
import mc.cyberplex.us.kits.enumerations.GunTypes;

public class KitListeners implements Listener{
	
	Main main = Main.getMain();
	Shop shop = new Shop();
	LaserColor lsColor = new LaserColor();
	FireworkColor fwColor = new FireworkColor();
	GunType gnType = new GunType();
	GunRange gnRange = new GunRange();
	ReloadTime rlTime = new ReloadTime();
	
	@EventHandler //listener for the shop so the shop can be used
	public void shopListener(InventoryClickEvent event) {

		//get the player that is clicking in the inventory 
		Player player = (Player) event.getWhoClicked();

		//check to see if the inventory the player has open is the laser tag shop
		if(event.getInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "laser tag shop")) {

			//cancel the event so the item isn't pulled from the inventory
			event.setCancelled(true);

			switch(event.getSlot()) {

			case 11:				

				//open the inventory for the laser color
				lsColor.createLaserShop(player);

				break;

			case 12:

				//open the inventory for the firework color
				fwColor.createFireworkShop(player);

				break;

			case 13:

				//open the inventory for gun type
				gnType.createGunTypeShop(player);
				
				break;

			case 14:

				//open the inventory for the range
				gnRange.createGunRangeShop(player);

				break;

			case 15:

				//open the inventory for the reload time
				rlTime.createReloadShop(player);
				
				break;

			}

		}

	}
	
	@EventHandler //listener for the laser color inventory
	public void laserColorListener(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		
		if(event.getInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "select laser color")) {
			
			//cancel the event so the item can't be pulled from the inventory
			event.setCancelled(true);
			
			switch(event.getSlot()) {
			
			case 0: //back arrow
				
				shop.createShop(player);
				
				break;
				
			case 9: //red color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".laser color", Colors.RED.toString());  
				
				break;
				
			case 10: //orange color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".laser color", Colors.ORANGE.toString());  
				
				break;
				
			case 11: //yellow color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".laser color", Colors.YELLOW.toString());  
				
				break;
				
			case 12: //green color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".laser color", Colors.GREEN.toString());  
				
				break;
				
			case 13: //blue color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".laser color", Colors.BLUE.toString());  
				
				break;
				
			case 14: //indigo color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".laser color", Colors.INDIGO.toString());  
				
				break;
				
			case 15: //violet color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".laser color", Colors.VIOLET.toString());  
				
				break;
				
			case 16: //pink color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".laser color", Colors.PINK.toString());  
				
				break;
				
			case 17: //rainbow color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".laser color", Colors.RAINBOW.toString());  
				
				break;
			
			}
			
			main.saveConfig();
			
		}
		
	}

	@EventHandler //listener for the firework color inventory
	public void fireworkColorListener(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		
		if(event.getInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "select firework color")) {
			
			//cancel the event so the item can't be pulled from the inventory
			event.setCancelled(true);
			
			switch(event.getSlot()) {
			
			case 0: //back arrow
				
				shop.createShop(player);
				
				break;
				
			case 9: //red color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".firework color", Colors.RED.toString());  
				
				break;
				
			case 10: //orange color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".firework color", Colors.ORANGE.toString());
				
				break;
				
			case 11: //yellow color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".firework color", Colors.YELLOW.toString());
				
				break;
				
			case 12: //green color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".firework color", Colors.GREEN.toString());
				
				break;
				
			case 13: //blue color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".firework color", Colors.BLUE.toString());
				
				break;
				
			case 14: //indigo color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".firework color", Colors.INDIGO.toString());
				
				break;
				
			case 15: //violet color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".firework color", Colors.VIOLET.toString());
				
				break;
				
			case 16: //pink color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".firework color", Colors.PINK.toString());
				
				break;
				
			case 17: //rainbow color
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".firework color", Colors.RAINBOW.toString());
				
				break;
			
			}
			
			main.saveConfig();
			
		}
		
	}

	@EventHandler //listener for the gun shop
	public void gunListener(InventoryClickEvent event) {

		//get the player that is clicking in the inventory 
		Player player = (Player) event.getWhoClicked();

		//check to see if the inventory the player has open is the laser tag shop
		if(event.getInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "select gun type")) {

			//cancel the event so the item isn't pulled from the inventory
			event.setCancelled(true);

			switch(event.getSlot()) {
			
			case 0: //back arrow
				
				shop.createShop(player);
				
				break;

			case 11:				

				//save gun type to wooden gun
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".gun type", GunTypes.WOOD.toString());

				break;

			case 12:

				//save gun type to stone gun
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".gun type", GunTypes.STONE.toString());
				
				break;

			case 13:

				//save gun type to iron gun
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".gun type", GunTypes.IRON.toString());
				
				break;

			case 14:

				//save gun type to gold gun
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".gun type", GunTypes.GOLD.toString());

				break;

			case 15:

				//save gun type to diamond gun
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".gun type", GunTypes.DIAMOND.toString());

				break;

			}
			
			main.saveConfig();

		}

	}
	
	@EventHandler //listener for the gun range shpo
	public void gunRangeListener(InventoryClickEvent event) {

		//get the player that is clicking in the inventory 
		Player player = (Player) event.getWhoClicked();

		//check to see if the inventory the player has open is the laser tag shop
		if(event.getInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "select gun range")) {

			//cancel the event so the item isn't pulled from the inventory
			event.setCancelled(true);

			switch(event.getSlot()) {
			
			case 0: //back arrow
				
				shop.createShop(player);
				
				break;

			case 11:				

				//save gun range to 5 blocks
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".gun range", GunRanges.FIVE.toString());

				break;

			case 12:

				//save gun range to 10 blocks
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".gun range", GunRanges.TEN.toString());
				
				break;

			case 13:

				//save gun range to 15 blocks
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".gun range", GunRanges.FIFTEEN.toString());
				
				break;

			case 14:

				//save gun range to 20 blocks
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".gun range", GunRanges.TWENTY.toString());

				break;

			case 15:

				//save gun range to 25 blocks
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".gun range", GunRanges.TWENTYFIVE.toString());

				break;

			}
			
			main.saveConfig();

		}

	}

	@EventHandler //listener for the laser color inventory
	public void reloadTimeListener(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		
		if(event.getInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "select reload time")) {
			
			//cancel the event so the item can't be pulled from the inventory
			event.setCancelled(true);
			
			switch(event.getSlot()) {
			
			case 0: //back arrow
				
				shop.createShop(player);
				
				break;
				
			case 9: //10 second
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".reload time", CoolDownTime.TEN.toString());
				
				break;
				
			case 10: //9 second
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".reload time", CoolDownTime.NINE.toString());
				
				break;
				
			case 11: //8 second
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".reload time", CoolDownTime.EIGHT.toString());
				
				break;
				
			case 12: //7 second
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".reload time", CoolDownTime.SEVEN.toString());
				
				break;
				
			case 13: //6 second
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".reload time", CoolDownTime.SIX.toString());
				
				break;
				
			case 14: //5 second
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".reload time", CoolDownTime.FIVE.toString());
				
				break;
				
			case 15: //4 second
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".reload time", CoolDownTime.FOUR.toString());
				
				break;
				
			case 16: //3 second
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".reload time", CoolDownTime.THREE.toString());
				
				break;
				
			case 17: //2 second
				
				main.getConfig().set("Players." + player.getUniqueId().toString() + ".reload time", CoolDownTime.TWO.toString());
				
				break;
			
			}
			
			main.saveConfig();
			
		}
		
	}

}