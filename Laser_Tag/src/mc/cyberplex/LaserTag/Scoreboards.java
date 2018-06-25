package mc.cyberplex.LaserTag;

import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import mc.cyberplex.LaserTag.arena.Arena;

public class Scoreboards {

	Main plugin = Main.getMain();

	Set<String> arenas = plugin.getConfig().getConfigurationSection("Arenas").getKeys(false);
	Arena data = new Arena();

	public void lobbyBoard(int arenaNum, Player player, String arenaName) {
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		
		Team time = board.registerNewTeam("Time");
		Team playerCount = board.registerNewTeam("Player Count");
		
		Objective lobbyObjective = board.registerNewObjective("Lobby", "dummy");

		lobbyObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
		lobbyObjective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Laser Tag");

		//set up the players 
		playerCount.addEntry(ChatColor.YELLOW.toString());
		playerCount.setPrefix(ChatColor.YELLOW + "Players: ");
		playerCount.setSuffix(ChatColor.WHITE + Integer.toString(data.getArena(arenaNum).getGameCount()));

		//set up the time 
		time.addEntry(ChatColor.GREEN.toString());
		time.setPrefix(ChatColor.GREEN + "Time: ");
		time.setSuffix(ChatColor.WHITE + addPadding(2, Integer.toString(data.getArena(arenaNum).getMinutes())) + ":" + addPadding(2,Integer.toString(data.getArena(arenaNum).getSeconds())));

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

	public void gameBoard(int arenaNum, Player player, String arenaName) {
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		
		Team time = board.registerNewTeam("Time");		
		Objective gameObjective = board.registerNewObjective("Game", "dummy");
		
		gameObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
		gameObjective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Laser Tag");	

		time.addEntry(ChatColor.GREEN.toString());
		time.setPrefix(ChatColor.GREEN + "Time: ");
		time.setSuffix(ChatColor.WHITE + addPadding(2, Integer.toString(data.getArena(arenaNum).getMinutes())) + ":" + addPadding(2,Integer.toString(data.getArena(arenaNum).getSeconds())));

		Score arena = gameObjective.getScore(ChatColor.YELLOW + "Arena: " + ChatColor.WHITE + arenaName.substring(0,1).toUpperCase() + arenaName.substring(1));
		Score blank1 = gameObjective.getScore("  ");
		Score blank2 = gameObjective.getScore("   ");
		Score count = gameObjective.getScore(ChatColor.GREEN.toString());

		int scoreCount = 3;

		for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {			

			UUID playerID = data.getArena(arenaNum).getPlayer(index);
			String playerName = Bukkit.getPlayer(playerID).getName();
			int playerScore = data.getLaserTagData(arenaNum).getPlayerScore(index);
			
			Team playerTeam = board.registerNewTeam("player" + Integer.toString(index));			
			playerTeam.addEntry(ChatColor.BLUE + playerName + ": ");
			playerTeam.setSuffix(ChatColor.WHITE + Integer.toString(playerScore));
			
			Score score = gameObjective.getScore(ChatColor.BLUE + playerName + ": ");
			score.setScore(scoreCount);
			
			scoreCount++;

		}

		arena.setScore(scoreCount+2);
		blank1.setScore(scoreCount+1);

		blank2.setScore(2);
		count.setScore(1);

		player.setScoreboard(board);

	}

	public String addPadding(int length, String text) {

		StringBuilder sb = new StringBuilder();

		for(int i = length - text.length(); i > 0; i--) {
			sb.append('0');
		}

		sb.append(text);
		return sb.toString();		
	}

	public void updateLobby(int arenaNum, Player player, String arenaName) {

		Scoreboard board = player.getScoreboard();
		
		board.getTeam("Player Count").setSuffix(ChatColor.WHITE + Integer.toString(data.getArena(arenaNum).getGameCount()));
		
		board.getTeam("Time").setSuffix(ChatColor.WHITE + addPadding(2, Integer.toString(data.getArena(arenaNum).getMinutes())) + ":" + addPadding(2,Integer.toString(data.getArena(arenaNum).getSeconds())));

	}

	public void updateGame(int arenaNum, Player player, String arenaName) {

		Scoreboard board = player.getScoreboard();
		
		board.getTeam("Time").setSuffix(ChatColor.WHITE + addPadding(2, Integer.toString(data.getArena(arenaNum).getMinutes())) + ":" + addPadding(2,Integer.toString(data.getArena(arenaNum).getSeconds())));
		
		for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {
			
			int playerScore = data.getLaserTagData(arenaNum).getPlayerScore(index);
			
			board.getTeam("player" + Integer.toString(index)).setSuffix(ChatColor.WHITE + Integer.toString(playerScore));
			
		}
		
	}
}
