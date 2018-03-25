package mc.cyberplex.us.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import mc.cyberplex.us.Main;
import mc.cyberplex.us.arena.ArenaData;
import mc.cyberplex.us.arena.Message;
import mc.cyberplex.us.arena.PlayerList;
import mc.cyberplex.us.arena.PlayerState;

public class PlayerLeaveGame implements Listener{
	
	Main main = Main.getMain();
	ArenaData data = new ArenaData();
	PlayerState playerState = new PlayerState();
	PlayerList playerList = new PlayerList();
	PlayerDeath playerDeath = new PlayerDeath();
	
	BukkitTask rejoinTime;
	
	int tempScore;
	String arena;
	boolean inArena = false;
	
	@EventHandler
	public void onPlayerQuitMinecraft(PlayerQuitEvent event) {
		
		Player player = event.getPlayer();
		
		for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {
			
			arena = arenaName;
			int arenaNum = data.getArenaNum(arenaName);
			
			for(int index = 0; index < data.getArena(arenaNum).getInGameCount(); index++) {
				
				if(player.getUniqueId().toString().equals(data.getArena(arenaNum).getInGame(index))) {
					
					tempScore = data.getArena(arenaNum).getPlayerScore(index);
					playerState.leaveGame(arenaName, player);
					inArena = true;
					
					rejoinTime = new BukkitRunnable() {
						
						int seconds = 60;
						
						@Override
						public void run() {
							
							if(seconds == 0) {
								
								inArena = false;
								cancel();							
								
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
		
		if(main.getConfig().getString("Arenas." + arena + ".state").equalsIgnoreCase("running") && inArena == true) {
			
			int arenaNum = data.getArenaNum(arena);
			
			rejoinTime.cancel();
			data.getArena(arenaNum).addPlayer(player);
			
			for(int index = 0; index < data.getArena(arenaNum).getInGameCount(); index++) {
				
				if(data.getArena(arenaNum).getInGame(index).equals(player.getUniqueId().toString())) {					
					data.getArena(arenaNum).setPlayerScore(index, tempScore);					
				}
				
			}			

			playerList.getPlayer(arena, Message.GAME);
			player.setHealth(0);
			
		} else {
			
			rejoinTime.cancel();
			
		}
		
	}
	
}
