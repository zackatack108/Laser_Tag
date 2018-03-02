package mc.cyberplex.us;

import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import mc.cyberplex.us.arena.ArenaData;

public class Scoreboards {

	Main plugin = Main.getMain();

	Set<String> arenas = plugin.getConfig().getConfigurationSection("Arenas").getKeys(false);
	ArenaData data = new ArenaData();

	ScoreboardManager manager = Bukkit.getScoreboardManager();
	org.bukkit.scoreboard.Scoreboard board = manager.getNewScoreboard();

	Team time = board.registerNewTeam("Time");
	Team playerCount = board.registerNewTeam("Player Count");
	Team playerTeam = board.registerNewTeam("Player Team");

	Objective lobbyObjective = board.registerNewObjective("Lobby", "dummy");
	Objective gameObjective = board.registerNewObjective("Game", "dummy");

	public void lobbyBoard(int arenaNum, Player player, String arenaName) {

		lobbyObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
		lobbyObjective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Laser Tag");

		//set up the players 
		playerCount.addEntry(ChatColor.YELLOW.toString());
		playerCount.setPrefix(ChatColor.YELLOW + "Players: ");
		playerCount.setSuffix(ChatColor.WHITE + Integer.toString(data.getArena(arenaNum).getInGameCount()));

		//set up the time 
		time.addEntry(ChatColor.GREEN.toString());
		time.setPrefix(ChatColor.GREEN + "Time: ");
		time.setSuffix(ChatColor.WHITE + data.getArena(arenaNum).getTimeMsg());

		Score arena = lobbyObjective.getScore(ChatColor.YELLOW + "Arena: " + ChatColor.WHITE + arenaName.substring(0, 1).toUpperCase() + arenaName.substring(1));
		Score blank = lobbyObjective.getScore("  ");
		Score blank2 = lobbyObjective.getScore(" ");
		Score players = lobbyObjective.getScore(ChatColor.YELLOW.toString());
		Score count = lobbyObjective.getScore(ChatColor.GREEN.toString());

		arena.setScore(5);
		blank.setScore(4);
		players.setScore(3);
		blank2.setScore(2);
		count.setScore(1);

		player.setScoreboard(board);

	}

	public void gameBoard(int arenaNum, Player player, String arenaName){

		gameObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
		gameObjective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Laser Tag");	

		time.addEntry(ChatColor.GREEN.toString());
		time.setPrefix(ChatColor.GREEN + "Time: ");
		time.setSuffix(ChatColor.WHITE + data.getArena(arenaNum).getTimeMsg());
		
		Score arena = gameObjective.getScore(ChatColor.YELLOW + "Arena: " + ChatColor.WHITE + arenaName.substring(0,1).toUpperCase() + arenaName.substring(1));
		Score blank1 = gameObjective.getScore("  ");
		Score blank2 = gameObjective.getScore("   ");
		Score count = gameObjective.getScore(ChatColor.GREEN.toString());
		
		int scoreCount = 0;

		for(int index = 0; index < data.getArena(arenaNum).getInGameCount(); index++) {
			
			UUID playerID = UUID.fromString(data.getArena(arenaNum).getInGame(index));
			Player playerName = Bukkit.getPlayer(playerID);
			
			playerTeam.addEntry(" " + index);
			playerTeam.setPrefix(data.getArena(arenaNum).getInGame(index) + ": ");
			playerTeam.setSuffix(ChatColor.WHITE + "" + data.getArena(arenaNum).getPlayerScore(playerName, arenaName));
			
			scoreCount++;
			
			Score playerPos = gameObjective.getScore(ChatColor.RED.toString());
			playerPos.setScore(scoreCount);
			
		}
		
		arena.setScore(scoreCount+2);
		blank1.setScore(scoreCount+1);

		blank2.setScore(2);
		count.setScore(1);

		player.setScoreboard(board);

	}

}
