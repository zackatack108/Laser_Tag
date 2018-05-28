package mc.cyberplex.LaserTag.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class ReloadTime {

	public void createReloadShop(Player player) {

		//create an inventory for the reload time shop
		Inventory reloadTime = Bukkit.createInventory(null, 27, ChatColor.DARK_RED + "Select Reload Time");

		//create the back arrow item for the inventory
		ItemStack backArrow = new ItemStack(Material.ARROW, 1);
		ItemMeta backMeta = backArrow.getItemMeta();
		backMeta.setDisplayName(ChatColor.YELLOW + "Go back to main menu");
		backArrow.setItemMeta(backMeta);
		
		//create the 10 second reload time item
		ItemStack ten = new ItemStack(Material.WATCH, 10);
		ItemMeta tenMeta = ten.getItemMeta();
		tenMeta.setDisplayName(ChatColor.YELLOW + "Choose 10 second reload time");
		ten.setItemMeta(tenMeta);
		
		//create the 9 second reload time item
		ItemStack nine = new ItemStack(Material.WATCH, 9);
		ItemMeta nineMeta = nine.getItemMeta();
		nineMeta.setDisplayName(ChatColor.YELLOW + "Choose 9 second reload time");
		nine.setItemMeta(nineMeta);
		
		//create the 8 second reload time item
		ItemStack eight = new ItemStack(Material.WATCH, 8);
		ItemMeta eightMeta = eight.getItemMeta();
		eightMeta.setDisplayName(ChatColor.YELLOW + "Choose 8 second reload time");
		eight.setItemMeta(eightMeta);
		
		//create the 7 second reload time item
		ItemStack seven = new ItemStack(Material.WATCH, 7);
		ItemMeta sevenMeta = seven.getItemMeta();
		sevenMeta.setDisplayName(ChatColor.YELLOW + "Choose 7 second reload time");
		seven.setItemMeta(sevenMeta);
		
		//create the 6 second reload time item
		ItemStack six = new ItemStack(Material.WATCH, 6);
		ItemMeta sixMeta = six.getItemMeta();
		sixMeta.setDisplayName(ChatColor.YELLOW + "Choose 6 second reload time");
		six.setItemMeta(sixMeta);
		
		//create the 5 second reload time item
		ItemStack five = new ItemStack(Material.WATCH, 5);
		ItemMeta fiveMeta = five.getItemMeta();
		fiveMeta.setDisplayName(ChatColor.YELLOW + "Choose 5 second reload time");
		five.setItemMeta(fiveMeta);
		
		//create the 4 second reload time item
		ItemStack four = new ItemStack(Material.WATCH, 4);
		ItemMeta fourMeta = four.getItemMeta();
		fourMeta.setDisplayName(ChatColor.YELLOW + "Choose 4 second reload time");
		four.setItemMeta(fourMeta);
		
		//create the 3 second reload time item
		ItemStack three = new ItemStack(Material.WATCH, 3);
		ItemMeta threeMeta = three.getItemMeta();
		threeMeta.setDisplayName(ChatColor.YELLOW + "Choose 3 second reload time");
		three.setItemMeta(threeMeta);
		
		//create the 2 second reload time item
		ItemStack two = new ItemStack(Material.WATCH, 2);
		ItemMeta twoMeta = two.getItemMeta();
		twoMeta.setDisplayName(ChatColor.YELLOW + "Choose 2 second reload time");
		two.setItemMeta(twoMeta);
		
		//set all the items into the reload time inventory
		reloadTime.setItem(0, backArrow);
		reloadTime.setItem(9, ten);
		reloadTime.setItem(10, nine);
		reloadTime.setItem(11, eight);
		reloadTime.setItem(12, seven);
		reloadTime.setItem(13, six);
		reloadTime.setItem(14, five);
		reloadTime.setItem(15, four);
		reloadTime.setItem(16, three);
		reloadTime.setItem(17, two);
		
		//open the reload time inventory for the player
		player.openInventory(reloadTime);

	}

}
