package mc.cyberplex.LaserTag.listeners;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import mc.cyberplex.LaserTag.Main;
import mc.cyberplex.LaserTag.Scoreboards;
import mc.cyberplex.LaserTag.arena.Arena;
import mc.cyberplex.LaserTag.arena.ArenaState;
import mc.cyberplex.LaserTag.arena.Message;
import mc.cyberplex.LaserTag.arena.PlayerList;
import mc.cyberplex.LaserTag.arena.PlayerState;

public class PlayerLeaveGame implements Listener{

	Main main = Main.getMain();
	Arena data = new Arena();
	PlayerState playerState = new PlayerState();
	PlayerList playerList = new PlayerList();
	PlayerDeath playerDeath = new PlayerDeath();
	BukkitTask rejoinTime;
	
	private static ArrayList<UUID> leftMC = new ArrayList<UUID>();

	@EventHandler
	public void onPlayerQuitMinecraft(PlayerQuitEvent event) {

		Player player = event.getPlayer();

		for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {

			int arenaNum = data.getArenaNum(arenaName);

			for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {

				if(player.getUniqueId().equals(data.getArena(arenaNum).getPlayer(index))) {

					leftMC.add(player.getUniqueId());
					
					rejoinTime = new BukkitRunnable() {

						int seconds = 60;

						@Override
						public void run() {

							if(seconds == 0) {

								cancel();
								kickPlayerFromGame(player, arenaName);														

							}

							seconds--;

						}

					}.runTaskTimer(main, 0, 20);

				}

			}

		}

	}

	@EventHandler
	public void onPlayerJoinMinecraft(PlayerJoinEvent event) {

		Player player = event.getPlayer();
		
		if(leftMC.contains(player.getUniqueId())) {
			
			for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {
				
				int arenaNum = data.getArenaNum(arenaName);
				
				for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {
					
					rejoinTime.cancel();
					leftMC.remove(player.getUniqueId());
					playerList.getPlayer(arenaName, Message.GAME);
					
					new BukkitRunnable() {
						@Override
						public void run() {
							player.setHealth(0);
						}
					}.runTaskLater(main, 10);
					
				}
				
			}
			
			if(leftMC.contains(player.getUniqueId())) {
				
				rejoinTime.cancel();
				leftMC.remove(player.getUniqueId());
				
				for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {
					
					if(data.getInArenaArea(arenaName, player) == true && !player.hasPermission("lt.arena.override")) {
						
						player.teleport(data.getHub());
						Arena.returnInventory(player);
						
						player.setGameMode(GameMode.SURVIVAL);
						player.setHealth(20);
						player.setFireTicks(0);
						
					}
					
				}
				
			}
			
		} else {
			
			for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {
				
				if(data.getInArenaArea(arenaName, player) == true && !player.hasPermission("lt.arena.override")) {
					
					player.teleport(data.getHub());
					Arena.returnInventory(player);
					
					player.setGameMode(GameMode.SURVIVAL);
					player.setHealth(20);
					player.setFireTicks(0);
					
				}
				
			}
			
		}

	}

	public void kickPlayerFromGame(Player player, String arenaName) {
		
		int arenaNum = data.getArenaNum(arenaName);
		leftMC.remove(player.getUniqueId());
		
		if(data.getState(arenaName).equalsIgnoreCase("running")) {			
			for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {				
				if(data.getArena(arenaNum).getPlayer(index).equals(player.getUniqueId())) {
					data.getLaserTagData(arenaNum).removeFromPlayerKits(index);
					data.getLaserTagData(arenaNum).removeFromScoreboard(index);
				}				
			}			
		}
		
		data.getArena(arenaNum).removePlayer(player);
		
		for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {
			
			UUID playerID = data.getArena(arenaNum).getPlayer(index);
			Player inGamePlayer = Bukkit.getPlayer(playerID);
			
			Scoreboards board = new Scoreboards();
			board.gameBoard(arenaNum, inGamePlayer, arenaName);			
		}
		
		if(data.getArena(arenaNum).getGameCount() < data.getMinPlayers(arenaName) && data.getState(arenaName).equalsIgnoreCase("running")) {
			ArenaState arenaState = new ArenaState();
			arenaState.stop(arenaName);			
		} else if(data.getState(arenaName).equalsIgnoreCase("running")) {
			PlayerList getPlayerList = new PlayerList();
			getPlayerList.getPlayer(arenaName, Message.GAME);			
			JoinSign sign = new JoinSign();
			sign.updateSign(arenaName);			
		} else if(data.getState(arenaName).equalsIgnoreCase("waiting for players")) {
			PlayerList getPlayerList = new PlayerList();
			getPlayerList.getPlayer(arenaName, Message.LOBBY);			
			JoinSign sign = new JoinSign();
			sign.updateSign(arenaName);
		}
		
	}
	
}
