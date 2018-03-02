package mc.cyberplex.us.arena;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import mc.cyberplex.us.Main;

public class ArenaData {

	static Main main = Main.getMain();

	private final int MAX = 50;

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
	private String inGame[] = new String[MAX],
			timeMsg = " ";
	private int playerScore[] = new int[MAX], 
			scoreToWin = 25, 
			gameCount = 0,
			time = 0;

	/*static void getArenas() {

		if(main.getConfig().getConfigurationSection("Arenas") != null) {

			arenas = main.getConfig().getConfigurationSection("Arenas").getKeys(false);			
			arenaNames = new String[arenas.size()];
			arenaList = new ArenaData[arenas.size()];

		}

	}*/
	
	/*public ArenaData() {
		getArenas();
	}*/

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

	public void setPlayerScore(Player player, String arenaName){

		if(player != null) {

			for(int index = 0; index < getMaxPlayers(arenaName); index++) {

				if(inGame[index] == player.getUniqueId().toString()) {

					int oldScore = getPlayerScore(player, arenaName);

					playerScore[index] = oldScore++;

				}

			}

		}

	}

	public void setTime(int time_){
		if(time_ >= 0) {

			time = time_;

		}
	}

	public void setHub(Player player){

		if(player != null) {

			//create variables for where the player is at in the world
			String world = player.getWorld().getName();
			double x = player.getLocation().getX(),
					y = player.getLocation().getY(),
					z = player.getLocation().getZ(),
					yaw = player.getLocation().getYaw(),
					pitch = player.getLocation().getPitch();

			//save spawnpoint to config
			main.getConfig().set("Hub.world", world);
			main.getConfig().set("Hub.x", x);
			main.getConfig().set("Hub.y", y);
			main.getConfig().set("Hub.z", z);
			main.getConfig().set("Hub.yaw", yaw);
			main.getConfig().set("Hub.pitch", pitch);
			main.saveConfig();
			
		}

	}

	public void setLobby(Player player, String arenaName){

		if(player != null) {

			//create variables for where the player is at in the world
			String world = player.getWorld().getName();
			double x = player.getLocation().getX(),
					y = player.getLocation().getY(),
					z = player.getLocation().getZ(),
					yaw = player.getLocation().getYaw(),
					pitch = player.getLocation().getPitch();

			//save spawnpoint to config
			main.getConfig().set("Arenas." + arenaName + ".lobby.world", world);
			main.getConfig().set("Arenas." + arenaName + ".lobby.x", x);
			main.getConfig().set("Arenas." + arenaName + ".lobby.y", y);
			main.getConfig().set("Arenas." + arenaName + ".lobby.z", z);
			main.getConfig().set("Arenas." + arenaName + ".lobby.yaw", yaw);
			main.getConfig().set("Arenas." + arenaName + ".lobby.pitch", pitch);
			main.saveConfig();
			
		}

	}

	public void setSpawn(Player player, String arenaName){

		if(player != null) {

			int spawnCount = getNumSpawns(arenaName);

			//create variables for where the player is at in the world
			String world = player.getWorld().getName();
			double x = player.getLocation().getX(),
					y = player.getLocation().getY(),
					z = player.getLocation().getZ(),
					yaw = player.getLocation().getYaw(),
					pitch = player.getLocation().getPitch();

			//save spawnpoint to config
			main.getConfig().set("Arenas." + arenaName + ".spawn." + spawnCount + ".world", world);
			main.getConfig().set("Arenas." + arenaName + ".spawn." + spawnCount + ".x", x);
			main.getConfig().set("Arenas." + arenaName + ".spawn." + spawnCount + ".y", y);
			main.getConfig().set("Arenas." + arenaName + ".spawn." + spawnCount + ".z", z);
			main.getConfig().set("Arenas." + arenaName + ".spawn." + spawnCount + ".yaw", yaw);
			main.getConfig().set("Arenas." + arenaName + ".spawn." + spawnCount + ".pitch", pitch);
			main.saveConfig();
		}

	}

	public void setScoreToWin(int score) {

		if(score > 0) {			
			main.getConfig().set("Score to win", score);			
		}

	}

	public void setTimeMsg(String msg) {
		timeMsg = msg;
	}

	//---------------------------------------------------
	//getters for class
	//---------------------------------------------------
	public ArenaData getArena(int index){

		if(index < 0 || index > arenas.size())
			return null;
		if(arenaList[index] == null)
			arenaList[index] = new ArenaData();

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

	public int getTime(){
		return time;
	}

	public int getPlayerScore(Player player, String arenaName){

		for(int index = 0; index < getMaxPlayers(arenaName); index++) {

			if(inGame[index] == player.getUniqueId().toString()) {

				return playerScore[index];

			}

		}

		return 0;
	}

	public int getScoreToWin() {
		return scoreToWin;		
	}

	public Location getHub(){

		String world = main.getConfig().getString("Hub.world");
		double x = main.getConfig().getDouble("Hub.x"),
				y = main.getConfig().getDouble("Hub.y"),
				z = main.getConfig().getDouble("Hub.z"),
				yaw = main.getConfig().getDouble("Hub.yaw"),
				pitch = main.getConfig().getDouble("Hub.pitch");

		Location hub = new Location(Bukkit.getServer().getWorld(world), x, y, z,(float) yaw, (float) pitch);

		return hub;
	}

	public Location getLobby(String arenaName){

		String world = main.getConfig().getString("Arenas." + arenaName + ".lobby.world");
		double x = main.getConfig().getDouble("Arenas." + arenaName + ".lobby.x"),
				y = main.getConfig().getDouble("Arenas." + arenaName + ".lobby.y"),
				z = main.getConfig().getDouble("Arenas." + arenaName + ".lobby.z"),
				yaw = main.getConfig().getDouble("Arenas." + arenaName + ".lobby.yaw"),
				pitch = main.getConfig().getDouble("Arenas." + arenaName + ".lobby.pitch");

		Location lobby = new Location(Bukkit.getServer().getWorld(world), x, y, z,(float) yaw, (float) pitch);

		return lobby;
	}

	public Location getSpawn(String arenaName){

		Random rand = new Random(System.currentTimeMillis());
		final int min = 0;
		int max = getNumSpawns(arenaName)-1;
		int randomNum = rand.nextInt(max) + min;

		String world = main.getConfig().getString("Arenas." + arenaName + ".spawn." + randomNum + ".world");
		double x = main.getConfig().getDouble("Arenas." + arenaName + ".spawn." + randomNum + ".x"),
				y = main.getConfig().getDouble("Arenas." + arenaName + ".spawn." + randomNum + ".y"),
				z = main.getConfig().getDouble("Arenas." + arenaName + ".spawn." + randomNum + ".z"),
				yaw = main.getConfig().getDouble("Arenas." + arenaName + ".spawn." + randomNum + ".yaw"),
				pitch = main.getConfig().getDouble("Arenas." + arenaName + ".spawn." + randomNum + ".pitch");

		Location spawn = new Location(Bukkit.getServer().getWorld(world), x, y, z,(float) yaw, (float) pitch);

		return spawn;
	}

	public int getMinPlayers(String arenaName) {
		
		int min = main.getConfig().getInt("Arenas." + arenaName + ".min");
		
		return min;		
	}

	public int getMaxPlayers(String arenaName) {
		
		int max = main.getConfig().getInt("Arenas." + arenaName + ".max");
		
		return max;
	}

	public int getNumSpawns(String arenaName) {

		int spawnCount;

		Set<String> spawnList = null;
		
		if(main.getConfig().getConfigurationSection("Arenas." + arenaName + ".spawn") != null) {
			spawnList = main.getConfig().getConfigurationSection("Arenas." + arenaName + ".spawn").getKeys(false);
		}
		
		if(spawnList != null && spawnList.size() > 0) {
			spawnCount = spawnList.size();
		} else {
			spawnCount = 0;
		}

		return spawnCount;
	}

	public String getTimeMsg() {
		return timeMsg;
	}

	//-------------------
	//inventory functions
	//-------------------
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
