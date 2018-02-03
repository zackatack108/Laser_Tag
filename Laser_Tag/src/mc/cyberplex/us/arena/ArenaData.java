package lt;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArenaData {

	static Main main = Main.getMain();

	//inventory data
	private static HashMap<String, ItemStack[]> inventoryContents = new HashMap<String, ItemStack[]>();
	private static HashMap<String, ItemStack[]> armorContents = new HashMap<String, ItemStack[]>();	

	//get arenas from config and save them to arena list
	static Set<String> arenas = main.getConfig().getConfigurationSection("Arenas").getKeys(false);
	static String[] arenaList = new String[arenas.size()];
	static ArenaData[] dataArray = new ArenaData[arenas.size()];
	
	//--------------------
	//setters for class
	//--------------------
	public void setInGame(Player player){

	}

	public void setPlayerScore(Player player){

	}

	public void setGameTime(String time){

	}

	public void setMinutes(int minutes){

	}

	public void setSeconds(int seconds){

	}

	public void setHub(Player player){

	}

	public void setLobby(Player player){

	}

	public void setSpawn(Player player){

	}

	//---------------------------------------------------
	//getters for class
	//---------------------------------------------------
	public ArenaData getArena(int index){
		
		if(index < 0 || index > arenas.size())
			return null;
		if(dataArray[index] == null)
			dataArray[index] = new ArenaData();
		
		return dataArray[index];
		
	}
	
	public int getInGameCount(){
		return 0;
	}
	
	public int getInGame(int index){
		return 0;
	}
	
	public int getMinutes(){
		return 0;
	}
	
	public int getSeconds(){
		return 0;
	}
	
	public int getPlayerScore(Player player){
		return 0;
	}
	
	public Location getHub(String arenaName){
		return null;
	}
	
	public Location getLobby(String arenaName){
		return null;
	}
	
	public Location getSpawn(String arenaName){
		return null;
	}
	
	//------------------------------------------------------------------------------------------------------
	//inventory functions
	//------------------------------------------------------------------------------------------------------
	public void saveInventory(Player player){

		//get iventory and armor contents from player and save them to hashmap
		inventoryContents.put(player.getUniqueId().toString(), player.getInventory().getContents());
		armorContents.put(player.getUniqueId().toString(), player.getInventory().getArmorContents());

		//clear player inventory
		player.getInventory().clear();

	}

	public void returnInventory(Player player){

		//clear player inventory
		player.getInventory().clear();

		//restore player inventory
		if(inventoryContents.containsKey(player.getUniqueId().toString()) && armorContents.containsKey(player.getUniqueId().toString())){

			player.getInventory().setContents(inventoryContents.get(player.getUniqueId().toString()));
			player.getInventory().setArmorContents(armorContents.get(player.getUniqueId().toString()));

		}

	}

}
