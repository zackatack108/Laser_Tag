package mc.cyberplex.us.arena;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import mc.cyberplex.us.Main;

public class ArenaData {

	static Main main = Main.getMain();

	//inventory data
	private static HashMap<String, ItemStack[]> inventoryContents = new HashMap<String, ItemStack[]>();
	private static HashMap<String, ItemStack[]> armorContents = new HashMap<String, ItemStack[]>();	

	//private data types 
	//get arenas from config and save them to arena list
	private static Set<String> arenas = main.getConfig().getConfigurationSection("Arenas").getKeys(false);
	private static String[] arenaNames = new String[arenas.size()];
	private static ArenaData[] arenaList = new ArenaData[arenas.size()];
	
	//non static data types
	//player game data types
	private String inGame[];
	private int playerScore[], arenaMax, arenaMin, scoreToWin, spawnCount, gameCount;
	
	//time data types
	private int minutes;
	
	//--------------------
	//class constructor
	//--------------------
	public ArenaData(String arenaName) {
		
		if(main.getConfig().contains("Arenas." + arenaName)) {
			
			//get the max allowed in the arena from the config
			arenaMax = main.getConfig().getInt("Arenas." + arenaName + ".max");
			
			//create array objects for inGame and playerScore that is set to size of arenaMax
			inGame = new String[arenaMax];			
			playerScore = new int[arenaMax];
			
			//initialize playerScores to 0
			for(int index = 0; index < arenaMax; index++) {				
				playerScore[index] = 0;				
			}
			
			//set seconds and minutes to 0;
			minutes = 0;
			
			//initialize scoreToWin to config value
			scoreToWin = main.getConfig().getInt("Score to win");
			
			Set<String> spawnList = main.getConfig().getConfigurationSection("Arenas." + arenaName + ".spawn").getKeys(false);
			
			if(spawnList.size() > 0) {
				spawnCount = spawnList.size();
			} else {
				spawnCount = 0;
			}
			
			gameCount = 0;
			
		}
		
	}

	//--------------------
	//setters for class
	//--------------------
	public void setInGame(Player player){
		
		inGame[gameCount] = new String();

		if(player == null) {

			inGame[gameCount] = null;
			gameCount = 0;
			return;
			
		} else {
			
			inGame[gameCount] = player.getUniqueId().toString();
			gameCount++;
			
		}

	}
	
	public void setInGameCount(int count) {
		
		if(count > 0) {
			gameCount = count;
		}
		
	}

	public void setPlayerScore(Player player){

		if(player != null) {

			for(int index = 0; index < arenaMax; index++) {
				
				if(inGame[index] == player.getUniqueId().toString()) {
					
					int oldScore = getPlayerScore(player);
					
					playerScore[index] = oldScore++;
					
				}
				
			}

		}

	}
	
	public void setMinutes(int minutes_){
		if(minutes_ >= 0) {
			
			minutes = minutes_;
			
		}
	}

	public void setHub(Player player){

		if(player != null) {

			//create variables for where the player is at in the world
			String world = player.getWorld().getName();
			double x = player.getLocation().getX();
			double y = player.getLocation().getY();
			double z = player.getLocation().getZ();
			double yaw = player.getLocation().getYaw();
			double pitch = player.getLocation().getPitch();

			//save spawnpoint to config
			main.getConfig().set("Hub.world", world);
			main.getConfig().set("Hub.x", x);
			main.getConfig().set("Hub.y", y);
			main.getConfig().set("Hub.z", z);
			main.getConfig().set("Hub.yaw", yaw);
			main.getConfig().set("Hub.pitch", pitch);

		}

	}

	public void setLobby(Player player, String arenaName){

		if(player != null) {

			//create variables for where the player is at in the world
			String world = player.getWorld().getName();
			double x = player.getLocation().getX();
			double y = player.getLocation().getY();
			double z = player.getLocation().getZ();
			double yaw = player.getLocation().getYaw();
			double pitch = player.getLocation().getPitch();

			//save spawnpoint to config
			main.getConfig().set("Arenas." + arenaName + ".lobby.world", world);
			main.getConfig().set("Arenas." + arenaName + ".lobby.x", x);
			main.getConfig().set("Arenas." + arenaName + ".lobby.y", y);
			main.getConfig().set("Arenas." + arenaName + ".lobby.z", z);
			main.getConfig().set("Arenas." + arenaName + ".lobby.yaw", yaw);
			main.getConfig().set("Arenas." + arenaName + ".lobby.pitch", pitch);

		}

	}

	public void setSpawn(Player player, String arenaName){

		if(player != null) {
			
			//create variables for where the player is at in the world
			String world = player.getWorld().getName();
			double x = player.getLocation().getX();
			double y = player.getLocation().getY();
			double z = player.getLocation().getZ();
			double yaw = player.getLocation().getYaw();
			double pitch = player.getLocation().getPitch();

			//save spawnpoint to config
			main.getConfig().set("Arenas." + arenaName + ".spawn." + spawnCount + ".world", world);
			main.getConfig().set("Arenas." + arenaName + ".spawn." + spawnCount + ".x", x);
			main.getConfig().set("Arenas." + arenaName + ".spawn." + spawnCount + ".y", y);
			main.getConfig().set("Arenas." + arenaName + ".spawn." + spawnCount + ".z", z);
			main.getConfig().set("Arenas." + arenaName + ".spawn." + spawnCount + ".yaw", yaw);
			main.getConfig().set("Arenas." + arenaName + ".spawn." + spawnCount + ".pitch", pitch);

			spawnCount++;
			
		}

	}

	public void setScoreToWin(int score) {
		
		if(score > 0) {			
			main.getConfig().set("Score to win", score);			
		}
		
	}
	
	//--------------------
	//getters for class
	//--------------------
	//---------------------------------------------------
	//getters for class
	//---------------------------------------------------
	public ArenaData getArena(int index, String arenaName){

		if(index < 0 || index > arenas.size())
			return null;
		if(arenaList[index] == null)
			arenaList[index] = new ArenaData(arenaName);

		return arenaList[index];

	}
	
	public int getArenaNum(String arenaName) {
		
		int arenaNum = -1;
		int count = 0;
		
		arenas.toArray(arenaNames);
		
		for(String arena: arenaNames) {
			
			if(arena.equalsIgnoreCase(arenaName)) {
				arenaNum = count;
			}
			count++;
		}
		
		return arenaNum;
		
	}

	public int getInGameCount(){
		return gameCount;
	}
	

	public String getInGame(int index){
		return inGame[index];
	}
	

	public int getMinutes(){
		return minutes;
	}

	public int getPlayerScore(Player player){
		
		for(int index = 0; index < arenaMax; index++) {
			
			if(inGame[index] == player.getUniqueId().toString()) {
				
				return playerScore[index];
				
			}
			
		}
		
		return 0;
	}
	

	public int getScoreToWin() {
		return scoreToWin;		
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

	public int getMin() {		
		return arenaMin;		
	}
	
	public int getMax() {
		return arenaMax;
	}
	
	//-------------------
	//inventory functions
	//-------------------
	//------------------------------------------------------------------------------------------------------
	public static void saveInventory(Player player){

		//get iventory and armor contents from player and save them to hashmap
		inventoryContents.put(player.getUniqueId().toString(), player.getInventory().getContents());
		armorContents.put(player.getUniqueId().toString(), player.getInventory().getArmorContents());

		//clear player inventory
		player.getInventory().clear();

	}

	public static void returnInventory(Player player){

		//clear player inventory
		player.getInventory().clear();

		//restore player inventory
		if(inventoryContents.containsKey(player.getUniqueId().toString()) && armorContents.containsKey(player.getUniqueId().toString())){

			player.getInventory().setContents(inventoryContents.get(player.getUniqueId().toString()));
			player.getInventory().setArmorContents(armorContents.get(player.getUniqueId().toString()));

		}

	}

}
