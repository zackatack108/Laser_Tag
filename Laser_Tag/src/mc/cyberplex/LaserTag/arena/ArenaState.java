package mc.cyberplex.LaserTag.arena;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import mc.cyberplex.LaserTag.Main;
import mc.cyberplex.LaserTag.Timer;
import mc.cyberplex.LaserTag.kits.GetKitItems;
import mc.cyberplex.LaserTag.listeners.JoinSign;
import net.md_5.bungee.api.ChatColor;

public class ArenaState {

	Main main = Main.getMain();
	Arena data = new Arena();
	PlayerList playerList = new PlayerList();
	GetKitItems kit = new GetKitItems();
	JoinSign joinSign = new JoinSign();
	Timer time = new Timer();

	public void waiting(String arenaName){

		int arenaNum = data.getArenaNum(arenaName);

		playerList.getPlayer(arenaName, Message.LOBBY);

		if(data.getArena(arenaNum).getGameCount() == data.getMinPlayers(arenaName)) {

			time.lobbyTime(arenaName, 5);

		} else if(data.getArena(arenaNum).getGameCount() == data.getMaxPlayers(arenaName)) {

			time.stopTimer(arenaName);
			time.lobbyTime(arenaName, 1);

		}

	}

	public void start(String arenaName){

		//set the arena state to running
		main.getConfig().set("Arenas." + arenaName + ".state", "running");
		joinSign.updateSign(arenaName);

		int arenaNum = data.getArenaNum(arenaName);

		time.stopTimer(arenaName);
		
		time.gameTime(arenaName, 30);

		//get each player in the arena and teleport them into the arena
		for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {

			//get the player from the arena
			UUID playerID = data.getArena(arenaNum).getPlayer(index);
			Player player = Bukkit.getPlayer(playerID);

			player.setGameMode(GameMode.SURVIVAL);

			//teleport the player to the arena to a random spawn point				
			Location spawn = data.getSpawn(arenaName);
			player.teleport(spawn);
			playerList.getPlayer(arenaName, Message.GAME);

			//get the gun type that the player has and give it to them
			ItemStack gun = new ItemStack(kit.getGunType(player));				
			player.getInventory().addItem(gun);

			data.getLaserTagData(arenaNum).setPlayerScore(index, 0);

		}

	}

	public void stop(String arenaName){

		//check if the arena exist in the config
		if(main.getConfig().contains("Arenas." + arenaName)) {

			//get the arena num
			int arenaNum = data.getArenaNum(arenaName);
			int gameCount = data.getArena(arenaNum).getGameCount();
			int highScore = 0;
			Player winner = null;

			for(int index = 0; index < gameCount; index++) {
				if(data.getLaserTagData(arenaNum).getPlayerScore(index) >= data.getLaserTagData(arenaNum).getPlayerScore(highScore)) {
					highScore = index;
					winner = Bukkit.getPlayer(data.getArena(arenaNum).getPlayer(index));
				}
			}

			for(int index = 0; index < gameCount; index++) {

				//get the player UUID from the in game list
				UUID playerID = data.getArena(arenaNum).getPlayer(index);
				Player player = Bukkit.getPlayer(playerID);	

				if(data.getLaserTagData(arenaNum).getPlayerScore(highScore) == 0) {
					player.sendMessage(ChatColor.BLUE + "Score was tied");
				} else {
					player.sendMessage(ChatColor.BLUE + "The winner is: " + ChatColor.GREEN + winner.getName());
				}

			}

			for(int index = 0; index < gameCount; index++) {

				//get the player UUID from the in game list
				UUID playerID = data.getArena(arenaNum).getPlayer(0);
				Player player = Bukkit.getPlayer(playerID);				

				player.setGameMode(GameMode.SURVIVAL);
				player.removePotionEffect(PotionEffectType.INVISIBILITY);
				player.setHealth(20);

				PlayerState playerState = new PlayerState();
				playerState.leaveGame(arenaName, player);

			}			

			if(data.getArena(arenaNum).getGameCount() == 0) {
				main.getConfig().set("Arenas." + arenaName + ".state", "waiting for players");
			}

		}

	}

}
