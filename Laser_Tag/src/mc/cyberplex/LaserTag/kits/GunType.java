package mc.cyberplex.LaserTag.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class GunType {

	//create shop for the different gun types
	public void createGunTypeShop(Player player) {

		//create an inventory for the gun type shop
		Inventory gunShop = Bukkit.createInventory(null, 27, ChatColor.DARK_RED + "Select Gun Type");

		//create the back arrow item for the inventory
		ItemStack backArrow = new ItemStack(Material.ARROW, 1);
		ItemMeta backMeta = backArrow.getItemMeta();
		backMeta.setDisplayName(ChatColor.YELLOW + "Go back to main menu");
		backArrow.setItemMeta(backMeta);

		//create the wooden gun item for the inventory
		ItemStack wood = new ItemStack(Material.WOODEN_HOE, 1);
		ItemMeta woodMeta = wood.getItemMeta();
		woodMeta.setDisplayName(ChatColor.YELLOW + "Choose the wooden gun");
		wood.setItemMeta(woodMeta);

		//create the stone gun item for the inventory
		ItemStack stone = new ItemStack(Material.STONE_HOE, 1);
		ItemMeta stoneMeta = stone.getItemMeta();
		stoneMeta.setDisplayName(ChatColor.YELLOW + "Choose the stone gun");
		stone.setItemMeta(stoneMeta);

		//create the iron gun item for the inventory
		ItemStack iron = new ItemStack(Material.IRON_HOE, 1);
		ItemMeta ironMeta = iron.getItemMeta();
		ironMeta.setDisplayName(ChatColor.YELLOW + "Choose the iron gun");
		iron.setItemMeta(ironMeta);

		//create the gold gun item for the inventory
		ItemStack gold = new ItemStack(Material.GOLDEN_HOE, 1);
		ItemMeta goldMeta = gold.getItemMeta();
		goldMeta.setDisplayName(ChatColor.YELLOW + "Choose the gold gun");
		gold.setItemMeta(goldMeta);

		//create the diamond gun item for the inventory
		ItemStack diamond = new ItemStack(Material.DIAMOND_HOE, 1);
		ItemMeta diamondMeta = diamond.getItemMeta();
		diamondMeta.setDisplayName(ChatColor.YELLOW + "Choose the diamond gun");
		diamond.setItemMeta(diamondMeta);

		//set the items into the gun inventory
		gunShop.setItem(0, backArrow);
		gunShop.setItem(11, wood);
		gunShop.setItem(12, stone);
		gunShop.setItem(13, iron);
		gunShop.setItem(14, gold);
		gunShop.setItem(15, diamond);
		
		//open the inventory for the player
		player.openInventory(gunShop);
		
	}

}
