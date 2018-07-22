package mc.cyberplex.LaserTag.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import mc.cyberplex.LaserTag.Main;
import net.md_5.bungee.api.ChatColor;

public class LaserColor {

	Main main = Main.getMain();

	int color = 0;
	int count = 1000;

	//create the shop inventory to choose the color for the laser
	public void createLaserShop(Player player) {

		//create an inventory object for the laser color
		Inventory laserColor = Bukkit.createInventory(null, 27, ChatColor.DARK_RED + "Select Laser Color");

		//create the back arrow item for the inventory
		ItemStack backArrow = new ItemStack(Material.ARROW, 1);
		ItemMeta backMeta = backArrow.getItemMeta();
		backMeta.setDisplayName(ChatColor.YELLOW + "Go back to main menu");
		backArrow.setItemMeta(backMeta);

		//create the different colored items for the inventory
		ItemStack red = new ItemStack(Material.RED_STAINED_GLASS, 1);
		ItemMeta redMeta = red.getItemMeta();
		redMeta.setDisplayName(ChatColor.YELLOW + "Choose laser color red");
		red.setItemMeta(redMeta);

		ItemStack orange = new ItemStack(Material.ORANGE_STAINED_GLASS, 1);
		ItemMeta orangeMeta = orange.getItemMeta();
		orangeMeta.setDisplayName(ChatColor.YELLOW + "Choose laser color orange");
		orange.setItemMeta(orangeMeta);

		ItemStack yellow = new ItemStack(Material.YELLOW_STAINED_GLASS, 1);
		ItemMeta yellowMeta = yellow.getItemMeta();
		yellowMeta.setDisplayName(ChatColor.YELLOW + "Choose laser color yellow");
		yellow.setItemMeta(yellowMeta);

		ItemStack green = new ItemStack(Material.LIME_STAINED_GLASS, 1);
		ItemMeta greenMeta = green.getItemMeta();
		greenMeta.setDisplayName(ChatColor.YELLOW + "Choose laser color green");
		green.setItemMeta(greenMeta);

		ItemStack blue = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS, 1);
		ItemMeta blueMeta = blue.getItemMeta();
		blueMeta.setDisplayName(ChatColor.YELLOW + "Choose laser color blue");
		blue.setItemMeta(blueMeta);

		ItemStack indigo = new ItemStack(Material.BLUE_STAINED_GLASS, 1);
		ItemMeta indigoMeta = indigo.getItemMeta();
		indigoMeta.setDisplayName(ChatColor.YELLOW + "Choose laser color indigo");
		indigo.setItemMeta(indigoMeta);

		ItemStack violet = new ItemStack(Material.PURPLE_STAINED_GLASS, 1);
		ItemMeta violetMeta = violet.getItemMeta();
		violetMeta.setDisplayName(ChatColor.YELLOW + "Choose laser color violet");
		violet.setItemMeta(violetMeta);

		ItemStack pink = new ItemStack(Material.PINK_STAINED_GLASS, 1);
		ItemMeta pinkMeta = pink.getItemMeta();
		pinkMeta.setDisplayName(ChatColor.YELLOW + "Choose laser color pink");
		pink.setItemMeta(pinkMeta);

		//create the rainbow colored item for the inventory
		count = 90;
		new BukkitRunnable() {

			public void run() {

				//check to see if count is greater than 0
				if(count > 0) {
					
					//check if on black and set it back to 0
					if(color == 6) {						
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
				
				ItemStack rainbow = new ItemStack(Material.RED_STAINED_GLASS, 1);

				switch(color) {

				case 0:
					rainbow = new ItemStack(Material.RED_STAINED_GLASS, 1);
					break;
				case 1:
					rainbow = new ItemStack(Material.ORANGE_STAINED_GLASS, 1);
					break;
				case 2:
					rainbow = new ItemStack(Material.YELLOW_STAINED_GLASS, 1);
					break;
				case 3:
					rainbow = new ItemStack(Material.LIME_STAINED_GLASS, 1);
					break;
				case 4:
					rainbow = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS, 1);
					break;
				case 5:
					rainbow = new ItemStack(Material.BLUE_STAINED_GLASS, 1);
					break;
				case 6:
					rainbow = new ItemStack(Material.PURPLE_STAINED_GLASS, 1);
					break;
					
				}

				//create the rainbow item
				ItemMeta rainbowMeta = rainbow.getItemMeta();
				rainbowMeta.setDisplayName(ChatColor.YELLOW + "Choose laser color rainbow");
				rainbow.setItemMeta(rainbowMeta);				

				//set the rainbow item to the inventory
				laserColor.setItem(17, rainbow);				

			}

		}.runTaskTimer(main, 0, 10);

		//set the items into the inventory
		laserColor.setItem(0, backArrow);
		laserColor.setItem(9, red);
		laserColor.setItem(10, orange);
		laserColor.setItem(11, yellow);
		laserColor.setItem(12, green);
		laserColor.setItem(13, blue);
		laserColor.setItem(14, indigo);
		laserColor.setItem(15, violet);
		laserColor.setItem(16, pink);

		//open the inventory for the player
		player.openInventory(laserColor);

	}

}
