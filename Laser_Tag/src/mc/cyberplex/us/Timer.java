package mc.cyberplex.us;

import org.bukkit.Bukkit;
import mc.cyberplex.us.arena.ArenaData;
import mc.cyberplex.us.arena.ArenaState;
import mc.cyberplex.us.arena.Message;
import mc.cyberplex.us.arena.PlayerList;

public class Timer {
	
	Main main = Main.getMain();
	ArenaData data = new ArenaData();
	PlayerList playerList = new PlayerList();
	
	public void lobbyTime(String arenaName, int time) {
		
		int arenaNum = data.getArenaNum(arenaName);
		
		data.getArena(arenaNum).setSeconds(0);
		data.getArena(arenaNum).setMinutes(time);
		
		data.getArena(arenaNum).Timer = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			
			@Override
			public void run() {
				
				int seconds = data.getArena(arenaNum).getSeconds();
				int minutes = data.getArena(arenaNum).getMinutes();
				
				if(data.getArena(arenaNum).getInGameCount() < data.getMinPlayers(arenaName)) {
					stopTimer(data.getArena(arenaNum).Timer);
				}
				
				if(seconds <= 0 && minutes >= 1) {
					
					data.getArena(arenaNum).setTimeMsg(minutes + " Minutes");
					playerList.getPlayer(arenaName, Message.LOBBY);
					
					data.getArena(arenaNum).setSeconds(60);
					data.getArena(arenaNum).setMinutes(--minutes);					
					
				} else if (minutes < 1) {
					
					data.getArena(arenaNum).setTimeMsg(seconds + " Seconds");
					playerList.getPlayer(arenaName, Message.LOBBY);
					
					if(seconds == 0) {
						
						stopTimer(data.getArena(arenaNum).Timer);
						
						ArenaState state = new ArenaState();
						state.start(arenaName);
						
					}
					
				}
				
				data.getArena(arenaNum).setSeconds(--seconds);
				
			}
			
		}, 0, 20L);
				
	}
	
	public void gameTime(String arenaName, int time) {
		
		int arenaNum = data.getArenaNum(arenaName);
		
		data.getArena(arenaNum).setSeconds(0);
		data.getArena(arenaNum).setMinutes(time);
		
		data.getArena(arenaNum).Timer = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			
			@Override
			public void run() {
				
				int seconds = data.getArena(arenaNum).getSeconds();
				int minutes = data.getArena(arenaNum).getMinutes();
				
				if(data.getArena(arenaNum).getInGameCount() < data.getMinPlayers(arenaName)) {
					stopTimer(data.getArena(arenaNum).Timer);
				}
				
				if(seconds <= 0 && minutes >= 1) {
					
					data.getArena(arenaNum).setTimeMsg(minutes + " Minutes");
					playerList.getPlayer(arenaName, Message.GAME);
					
					data.getArena(arenaNum).setSeconds(60);
					data.getArena(arenaNum).setMinutes(--minutes);					
					
				} else if (minutes < 1) {
					
					data.getArena(arenaNum).setTimeMsg(seconds + " Seconds");
					playerList.getPlayer(arenaName, Message.GAME);
					
					if(seconds == 0) {
						
						main.getConfig().set("Arenas." + arenaName + ".state", "stopping");
						
						stopTimer(data.getArena(arenaNum).Timer);
						
						ArenaState state = new ArenaState();
						state.stop(arenaName);
						
					}
					
				}
				
				data.getArena(arenaNum).setSeconds(--seconds);
				
			}
			
		}, 0, 20L);
				
	}
	
	public void stopTimer(int task){
		Bukkit.getScheduler().cancelTask(task);
	}
	
}
