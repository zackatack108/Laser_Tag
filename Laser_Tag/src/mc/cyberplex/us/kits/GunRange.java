package mc.cyberplex.us.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class GunRange {

	//create gun range shop
	public void createGunRangeShop(Player player) {

		//create an inventory for gun range shop
		Inventory rangeShop = Bukkit.createInventory(null, 27, ChatColor.DARK_RED + "Select Gun Range");

		//create the back arrow item for the inventory
		ItemStack backArrow = new ItemStack(Material.ARROW, 1);
		ItemMeta backMeta = backArrow.getItemMeta();
		backMeta.setDisplayName(ChatColor.YELLOW + "Go back to main menu");
		backArrow.setItemMeta(backMeta);

		//create an item for 5 block range
		ItemStack five = new ItemStack(Material.ENDER_PEARL, 5);
		ItemMeta fiveMeta = five.getItemMeta();
		fiveMeta.setDisplayName(ChatColor.YELLOW + "Choose 5 block range");
		five.setItemMeta(fiveMeta);

		//create an item for 10 block range
		ItemStack ten = new ItemStack(Material.ENDER_PEARL, 10);
		ItemMeta tenMeta = ten.getItemMeta();
		tenMeta.setDisplayName(ChatColor.YELLOW + "Choose 10 block range");
		ten.setItemMeta(tenMeta);

		//create an item for 15 block range
		ItemStack fifteen = new ItemStack(Material.ENDER_PEARL, 15);
		ItemMeta fifteenMeta = fifteen.getItemMeta();
		fifteenMeta.setDisplayName(ChatColor.YELLOW + "Choose 15 block range");
		fifteen.setItemMeta(fifteenMeta);

		//create an item for 20 block range
		ItemStack twenty = new ItemStack(Material.ENDER_PEARL, 20);
		ItemMeta twentyMeta = twenty.getItemMeta();
		twentyMeta.setDisplayName(ChatColor.YELLOW + "Choose 20 block range");
		twenty.setItemMeta(twentyMeta);

		//create an item for 25 block range
		ItemStack twentyFive = new ItemStack(Material.ENDER_PEARL, 25);
		ItemMeta twentyFiveMeta = twentyFive.getItemMeta();
		twentyFiveMeta.setDisplayName(ChatColor.YELLOW + "Choose 25 block range");
		twentyFive.setItemMeta(twentyFiveMeta);
		
		//put the items in the inventory
		rangeShop.setItem(0, backArrow);
		rangeShop.setItem(11, five);
		rangeShop.setItem(12, ten);
		rangeShop.setItem(13, fifteen);
		rangeShop.setItem(14, twenty);
		rangeShop.setItem(15, twentyFive);
		
		//open the inventory for the player
		player.openInventory(rangeShop);

	}

}
