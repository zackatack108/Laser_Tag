package mc.cyberplex.LaserTag;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import mc.cyberplex.LaserTag.arena.Arena;
import mc.cyberplex.LaserTag.arena.ArenaState;
import mc.cyberplex.LaserTag.arena.Message;
import mc.cyberplex.LaserTag.arena.PlayerList;

public class Timer {

	Main main = Main.getMain();
	Arena data = new Arena();
	PlayerList playerList = new PlayerList();
	
	public void lobbyTime(String arenaName, int time) {

		int arenaNum = data.getArenaNum(arenaName);

		data.getArena(arenaNum).setSeconds(0);
		data.getArena(arenaNum).setMinutes(time);

		data.getLaserTagData(arenaNum).Timer = new BukkitRunnable() {

			@Override
			public void run() {

				int seconds = data.getArena(arenaNum).getSeconds();
				int minutes = data.getArena(arenaNum).getMinutes();

				if(data.getArena(arenaNum).getGameCount() < data.getMinPlayers(arenaName)) {
					stopTimer(arenaName);
					data.getArena(arenaNum).setSeconds(0);
					data.getArena(arenaNum).setMinutes(5);
				}
				
				playerList.getPlayer(arenaName, Message.LOBBY);

				if(seconds <= 0 && minutes >= 1) {					

					seconds = 60;
					data.getArena(arenaNum).setSeconds(seconds);
					data.getArena(arenaNum).setMinutes(--minutes);					

				} else if (minutes < 1) {

					if(seconds == 0) {

						cancel();

						ArenaState state = new ArenaState();
						state.start(arenaName);

					}

				}
				
				for(int count = 0; count < data.getArena(arenaNum).getGameCount(); count++) {

					Player player;
					UUID playerID = data.getArena(arenaNum).getPlayer(count);

					if(Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(playerID))) {
						player = Bukkit.getPlayer(playerID);						
						player.setFoodLevel(20);
					}

				}

				data.getArena(arenaNum).setSeconds(--seconds);

			}

		}.runTaskTimer(main, 0, 20);

	}

	public void gameTime(String arenaName, int time) {

		int arenaNum = data.getArenaNum(arenaName);

		data.getArena(arenaNum).setSeconds(0);
		data.getArena(arenaNum).setMinutes(time);

		data.getLaserTagData(arenaNum).Timer = new BukkitRunnable() {

			@Override
			public void run() {

				int seconds = data.getArena(arenaNum).getSeconds();
				int minutes = data.getArena(arenaNum).getMinutes();
				
				playerList.getPlayer(arenaName, Message.GAME);

				if(seconds <= 0 && minutes >= 1) {					

					seconds = 60;
					data.getArena(arenaNum).setSeconds(seconds);
					data.getArena(arenaNum).setMinutes(--minutes);					

				} else if (minutes < 1) {

					if(seconds == 0) {

						data.setState(arenaName, "stopping");

						cancel();

						ArenaState state = new ArenaState();
						state.stop(arenaName);

					}

				}	
				
				for(int count = 0; count < data.getArena(arenaNum).getGameCount(); count++) {

					Player player;
					UUID playerID = data.getArena(arenaNum).getPlayer(count);

					if(Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(playerID))) {
						player = Bukkit.getPlayer(playerID);						
						player.setFoodLevel(20);
					}

				}

				data.getArena(arenaNum).setSeconds(--seconds);

			}

		}.runTaskTimer(main, 0, 20);

	}

	public void stopTimer(String arenaName){

		int arenaNum = data.getArenaNum(arenaName);

		if(data.getLaserTagData(arenaNum).Timer != null) {
			data.getLaserTagData(arenaNum).Timer.cancel();
		}
	}

}
