package mc.cyberplex.LaserTag.arena;

import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import mc.cyberplex.LaserTag.Main;
import mc.cyberplex.LaserTag.Scoreboards;

public class PlayerList {
	
	ArenaData data = new ArenaData();
	Main main = Main.getMain();
	
	public void getPlayer(String arenaName, Message msg) {
		
		Set<String> arenas = main.getConfig().getConfigurationSection("Arenas").getKeys(false);
		Scoreboards[] board = new Scoreboards[arenas.size()];
		
		int arenaNum = data.getArenaNum(arenaName);
		
		for(int count = 0; count < data.getArena(arenaNum).getInGameCount(); count++) {
			
			UUID playerID = UUID.fromString(data.getArena(arenaNum).getInGame(count));
			Player player = Bukkit.getPlayer(playerID);
			
			if(msg == Message.LOBBY) {
				board[arenaNum] = new Scoreboards();
				board[arenaNum].lobbyBoard(arenaNum, player, arenaName);
			} else if(msg == Message.GAME) {
				board[arenaNum] = new Scoreboards();
				board[arenaNum].gameBoard(arenaNum, player, arenaName);
			}
			
		}
		
	}
	
	public void deathMessage(String arenaName, Player killed, Player killer) {
		
		int arenaNum = data.getArenaNum(arenaName);
		
		for(int count = 0; count < data.getArena(arenaNum).getInGameCount(); count++) {
			
			UUID playerID = UUID.fromString(data.getArena(arenaNum).getInGame(count));
			Player player = Bukkit.getPlayer(playerID);
			
			player.sendMessage(ChatColor.YELLOW + killed.getName() + " was killed by " + killer.getName());
			
		}
		
	}

}
