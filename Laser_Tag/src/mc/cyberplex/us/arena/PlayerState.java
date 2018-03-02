package mc.cyberplex.us.arena;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import mc.cyberplex.us.Main;
import net.md_5.bungee.api.ChatColor;

public class PlayerState {

	Main main = Main.getMain();
	ArenaState arenaState = new ArenaState();
	PlayerList getPlayerList = new PlayerList();
	ArenaData data = new ArenaData();;

	String state;

	public void joinGame(Player player, String arenaName) {

		state = main.getConfig().getString("Arenas." + arenaName + ".state");

		if(main.getConfig().getString("Arenas." + arenaName + ".lobby.world") == null) {

			player.sendMessage(ChatColor.RED + "Sorry, a lobby doesn't exist for the arena");

		} else if(state.equals("running")) {

			player.sendMessage(ChatColor.RED + "Sorry, the arena is currently running");

		} else {

			ArenaData.saveInventory(player);

			player.teleport(data.getLobby(arenaName));

			int arenaNum = data.getArenaNum(arenaName);

			data.getArena(arenaNum).setInGame(player);

			arenaState.waiting(arenaName);

			getPlayerList.getPlayer(arenaName, Message.LOBBY);

		}

	}

	public void leaveGame(String arenaName, Player player){

		//get the state of the arena and who has the flags
		String gameState = main.getConfig().getString("Arenas." + arenaName + ".state");

		//get the minimum number of players for arena
		int minPlayers = main.getConfig().getInt("Arenas." + arenaName + ".min");

		int arenaNum = data.getArenaNum(arenaName);
		int arenaTotal = data.getArena(arenaNum).getInGameCount();									

		//return inventory to player
		ArenaData.returnInventory(player);

		String inGame;
		String[] playerList = new String[arenaTotal];
		int count = 0;

		for(int index = 0; index < arenaTotal; index++){			

			inGame = data.getArena(arenaNum).getInGame(index);

			if(!(inGame.equals(player.getUniqueId().toString()))){
				playerList[count] = inGame;
				count++;
			}
		}

		UUID playerID;
		Player newPlayer;
		data.getArena(arenaNum).setInGameCount(0);
		--arenaTotal;

		if(playerList[0] == null) {

			data.getArena(arenaNum).setInGame(null);
			main.getConfig().set("Arenas." + arenaName + ".state", "waiting for players");
			main.saveConfig();

		} else {

			for(int index = 0; index < arenaTotal; index++){

				playerID = UUID.fromString(playerList[index]);
				newPlayer = Bukkit.getPlayer(playerID);
				data.getArena(arenaNum).setInGame(newPlayer);

			}

		}

		//gives player an empty scoreboard
		player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

		arenaTotal = data.getArena(arenaNum).getInGameCount();

		//check the state of the arena
		if(gameState.equalsIgnoreCase("waiting for players")){

			if(data.getArena(arenaNum).getInGameCount() > 0){
				//calls the getPlayerList class to load the lobby scoreboard
				getPlayerList.getPlayer(arenaName, Message.LOBBY);
			}


		} else if(gameState.equalsIgnoreCase("running")){

			//checks to see if the total players is less then the minimum for arena
			if(data.getArena(arenaNum).getInGameCount() < minPlayers){

				//stops the arena
				arenaState.stop(arenaName);

			} else {

				//Calls the getPlayerList class to load the game scoreboard
				getPlayerList.getPlayer(arenaName, Message.GAME);

			}									

		}

		//Send player a message saying they are leaving the arena and are being teleported to the hub
		player.sendMessage(ChatColor.YELLOW + "Leaving laser tag arena");
		player.sendMessage(ChatColor.YELLOW + "Sending you to the laser tag hub.");									

		//teleport player to the hub
		player.teleport(data.getHub());

	}

}
