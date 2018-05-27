package mc.cyberplex.LaserTag.arena;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import mc.cyberplex.LaserTag.Main;
import mc.cyberplex.LaserTag.Timer;
import mc.cyberplex.LaserTag.listeners.JoinSign;
import net.md_5.bungee.api.ChatColor;

public class PlayerState {

	Main main = Main.getMain();
	PlayerList getPlayerList = new PlayerList();
	ArenaData data = new ArenaData();
	JoinSign joinSign = new JoinSign();

	String state;

	public void joinGame(Player player, String arenaName) {

		state = main.getConfig().getString("Arenas." + arenaName + ".state");

		if(main.getConfig().getString("Arenas." + arenaName + ".lobby.world") == null) { //check to see if a lobby exist for the arena
			player.sendMessage(ChatColor.RED + "Sorry, a lobby doesn't exist for the arena");
		} else if(state.equals("running")) { //check to see if the arena is currently running
			player.sendMessage(ChatColor.RED + "Sorry, the arena is currently running");
		} else {

			//--------------------------------------------------------------------------------------------------
			//puts the player in arena, saves their inventory and will send them the scoreboard
			//--------------------------------------------------------------------------------------------------
			int arenaNum = data.getArenaNum(arenaName);
			ArenaData.saveInventory(player);
			player.teleport(data.getLobby(arenaName));
			data.getArena(arenaNum).addPlayer(player);
			ArenaState arenaState = new ArenaState();
			arenaState.waiting(arenaName);
			joinSign.updateSign(arenaName);
			//--------------------------------------------------------------------------------------------------

		}

	}

	public void leaveGame(String arenaName, Player player){
		
		state = main.getConfig().getString("Arenas." + arenaName + ".state");		
		int arenaNum = data.getArenaNum(arenaName);
		
		if(state.equalsIgnoreCase("running")){
			
			//checks to see if the total players is less then the minimum for arena
			if(data.getArena(arenaNum).getInGameCount()-1 < data.getMinPlayers(arenaName)){
				
				main.getConfig().set("Arenas." + arenaName + ".state", "stopping");
				ArenaState arenaState = new ArenaState();
				
				//stops the arena
				arenaState.stop(arenaName);
				return;
			}
			
		} else if(state.equalsIgnoreCase("waiting for players")) {
			
			if(data.getArena(arenaNum).getInGameCount()-1 <= data.getMinPlayers(arenaName)){
				
				Timer time = new Timer();
				time.stopTimer(arenaName);
				
			}
			
		}

		//--------------------------------------------------------------------------------------------------
		//this bit actually will remove the player from the arena and take them back to hub with their stuff
		//--------------------------------------------------------------------------------------------------
		if(player != null) {
			ArenaData.returnInventory(player);
			player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());		
			player.teleport(data.getHub());
			player.sendMessage(ChatColor.YELLOW + "Leaving laser tag arena");
			player.sendMessage(ChatColor.YELLOW + "Sending you to the laser tag hub.");	
		}
		data.getArena(arenaNum).removePlayer(player);
		joinSign.updateSign(arenaName);
		//--------------------------------------------------------------------------------------------------

		//--------------------------------------------------------------------------------------------------
		//this bit will update the scoreboards to those still in the game
		//--------------------------------------------------------------------------------------------------
		if(player !=null) {
			if(state.equalsIgnoreCase("waiting for players")){

				getPlayerList.getPlayer(arenaName, Message.LOBBY);

			} else if(state.equalsIgnoreCase("running")){

				//Calls the getPlayerList class to load the game scoreboard
				getPlayerList.getPlayer(arenaName, Message.GAME);

			}
		}
		//--------------------------------------------------------------------------------------------------

	}

}
