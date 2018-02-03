package mc.cyberplex.us;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import mc.cyberplex.us.kits.Shop;

public class Main extends JavaPlugin{

	private static Main main;
	public static Main getMain(){
		return main;
	}
	
	public void onEnable(){
		
		main = this;
		
		//main.getConfig().options().copyDefaults(true);
		//createConfig();
		//main.saveConfig();
		
		//register commands
		this.getCommand("lt").setExecutor(new Commands());
		
		//register listeners
		getServer().getPluginManager().registerEvents(new Shop(), this);
		//getServer().getPluginManager().registerEvents(new LaserColor(), this);
		//getServer().getPluginManager().registerEvents(new FireworkColor(), this);
		//getServer().getPluginManager().registerEvents(new GunType(), this);
		//getServer().getPluginManager().registerEvents(new GunRange(), this);
		//getServer().getPluginManager().registerEvents(new ReloadTime(), this);
		
	}
	
	public void onDisable(){
		
		//main.saveConfig();
		
	}
	
	/*private void createConfig(){
		
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
		
	}*/
	
}
