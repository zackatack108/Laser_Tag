package mc.cyberplex.us.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import mc.cyberplex.us.Main;
import net.md_5.bungee.api.ChatColor;

public class FireworkColor {

	Main main = Main.getMain();

	int color = 0;
	int count = 1000;

	//create the shop inventory to choose the color for the laser
	public void createFireworkShop(Player player) {

		//create an inventory object for the laser color
		Inventory fireworkColor = Bukkit.createInventory(null, 27, ChatColor.DARK_RED + "Select Firework Color");

		//create the back arrow item for the inventory
		ItemStack backArrow = new ItemStack(Material.ARROW, 1);
		ItemMeta backMeta = backArrow.getItemMeta();
		backMeta.setDisplayName(ChatColor.YELLOW + "Go back to main menu");
		backArrow.setItemMeta(backMeta);

		//create the different colored items for the inventory
		ItemStack red = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta redMeta = red.getItemMeta();
		redMeta.setDisplayName(ChatColor.YELLOW + "Choose firework color red");
		red.setItemMeta(redMeta);

		ItemStack orange = new ItemStack(Material.WOOL, 1, (byte) 1);
		ItemMeta orangeMeta = orange.getItemMeta();
		orangeMeta.setDisplayName(ChatColor.YELLOW + "Choose firework color orange");
		orange.setItemMeta(orangeMeta);

		ItemStack yellow = new ItemStack(Material.WOOL, 1, (byte) 4);
		ItemMeta yellowMeta = yellow.getItemMeta();
		yellowMeta.setDisplayName(ChatColor.YELLOW + "Choose firework color yellow");
		yellow.setItemMeta(yellowMeta);

		ItemStack green = new ItemStack(Material.WOOL, 1, (byte) 5);
		ItemMeta greenMeta = green.getItemMeta();
		greenMeta.setDisplayName(ChatColor.YELLOW + "Choose firework color green");
		green.setItemMeta(greenMeta);

		ItemStack blue = new ItemStack(Material.WOOL, 1, (byte) 11);
		ItemMeta blueMeta = blue.getItemMeta();
		blueMeta.setDisplayName(ChatColor.YELLOW + "Choose firework color blue");
		blue.setItemMeta(blueMeta);

		ItemStack indigo = new ItemStack(Material.WOOL, 1, (byte) 2);
		ItemMeta indigoMeta = indigo.getItemMeta();
		indigoMeta.setDisplayName(ChatColor.YELLOW + "Choose firework color indigo");
		indigo.setItemMeta(indigoMeta);

		ItemStack violet = new ItemStack(Material.WOOL, 1, (byte) 10);
		ItemMeta violetMeta = violet.getItemMeta();
		violetMeta.setDisplayName(ChatColor.YELLOW + "Choose firework color violet");
		violet.setItemMeta(violetMeta);

		ItemStack pink = new ItemStack(Material.WOOL, 1, (byte) 6);
		ItemMeta pinkMeta = pink.getItemMeta();
		pinkMeta.setDisplayName(ChatColor.YELLOW + "Choose firework color pink");
		pink.setItemMeta(pinkMeta);

		//create the rainbow colored item for the inventory
		count = 90;
		new BukkitRunnable() {

			public void run() {

				//check to see if count is greater than 0
				if(count > 0) {
					
					//check if on black and set it back to 0
					if(color == 15) {						
						color = 0;
					} else {
						//increment color by 1
						color++;
					}
					
				} else if (count <= 0) { //check if count is less then 0
					
					cancel(); //cancel the timer
					
				}
				
				//decrement count by one
				count--;

				//create the rainbow item
				ItemStack rainbow = new ItemStack(Material.WOOL, 1, (byte) color);
				ItemMeta rainbowMeta = rainbow.getItemMeta();
				rainbowMeta.setDisplayName(ChatColor.YELLOW + "Choose firework color rainbow");
				rainbow.setItemMeta(rainbowMeta);				

				//set the rainbow item to the inventory
				fireworkColor.setItem(17, rainbow);				

			}

		}.runTaskTimer(main, 0, 10);

		//set the items into the inventory
		fireworkColor.setItem(0, backArrow);
		fireworkColor.setItem(9, red);
		fireworkColor.setItem(10, orange);
		fireworkColor.setItem(11, yellow);
		fireworkColor.setItem(12, green);
		fireworkColor.setItem(13, blue);
		fireworkColor.setItem(14, indigo);
		fireworkColor.setItem(15, violet);
		fireworkColor.setItem(16, pink);

		//open the inventory for the player
		player.openInventory(fireworkColor);

	}
	
}
