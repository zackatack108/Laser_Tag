package mc.cyberplex.us;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import mc.cyberplex.us.arena.ArenaData;
import mc.cyberplex.us.arena.ArenaState;
import mc.cyberplex.us.kits.KitListeners;
import mc.cyberplex.us.listeners.GunFire;
import mc.cyberplex.us.listeners.InventoryMove;
import mc.cyberplex.us.listeners.JoinSign;
import mc.cyberplex.us.listeners.PlayerDeath;
import mc.cyberplex.us.listeners.PlayerJoin;
import mc.cyberplex.us.listeners.PlayerLeaveGame;

public class Main extends JavaPlugin{

	private static Main main;
	public static Main getMain(){
		return main;
	}
	
	public void onEnable(){
		
		main = this;
		
		main.getConfig().options().copyDefaults(true);
		createConfig();
		main.saveConfig();
		
		ArenaData.rand.setSeed(System.currentTimeMillis());
		ArenaData.updateArenaList();
		
		//register commands
		this.getCommand("lt").setExecutor(new Commands());
		
		//register listeners
		getServer().getPluginManager().registerEvents(new KitListeners(), this);
		getServer().getPluginManager().registerEvents(new GunFire(), this);
		getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new InventoryMove(), this);
		getServer().getPluginManager().registerEvents(new PlayerLeaveGame(), this);
		getServer().getPluginManager().registerEvents(new JoinSign(), this);
		
	}
	
	public void onDisable(){
		
		main.saveConfig();
		
		ArenaState state = new ArenaState();
		
		for(String arenaName: main.getConfig().getConfigurationSection("Arenas").getKeys(false)){
			
			state.stop(arenaName);
			
		}
		
	}
	
	private void createConfig(){
		
		try {

			//Check to see theirs a folder
			if(!getDataFolder().exists()){
				getDataFolder().mkdirs();
			}

			//Create file 
			File configFile = new File(getDataFolder(), "config.yml");

			//Check if file doesn't exist
			if(!configFile.exists()){

				//Display error message to console
				getLogger().info("Config.yml not found, creating file");

				//create file
				saveDefaultConfig();

			} else {

				//Display message saying file was found
				getLogger().info("Config.yml found, loading file");

			}

		} catch (Exception except) {

			//display error message to console 
			except.printStackTrace();
		}
		
	}
	
}
