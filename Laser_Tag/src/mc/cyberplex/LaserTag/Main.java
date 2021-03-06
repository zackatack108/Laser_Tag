package mc.cyberplex.LaserTag;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import mc.cyberplex.LaserTag.arena.Arena;
import mc.cyberplex.LaserTag.arena.ArenaState;
import mc.cyberplex.LaserTag.kits.KitListeners;
import mc.cyberplex.LaserTag.listeners.GunFire;
import mc.cyberplex.LaserTag.listeners.InventoryMove;
import mc.cyberplex.LaserTag.listeners.JoinSign;
import mc.cyberplex.LaserTag.listeners.PlayerBreakBlock;
import mc.cyberplex.LaserTag.listeners.PlayerDamage;
import mc.cyberplex.LaserTag.listeners.PlayerDeath;
import mc.cyberplex.LaserTag.listeners.PlayerJoin;
import mc.cyberplex.LaserTag.listeners.PlayerLeaveGame;

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
		
		Arena.rand.setSeed(System.currentTimeMillis());
		Arena arena = new Arena();
		arena.setArenaList();
		
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
		getServer().getPluginManager().registerEvents(new PlayerDamage(), this);
		getServer().getPluginManager().registerEvents(new PlayerBreakBlock(), this);
		
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
