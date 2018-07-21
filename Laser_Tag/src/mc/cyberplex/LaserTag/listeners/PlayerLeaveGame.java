package mc.cyberplex.LaserTag.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import mc.cyberplex.LaserTag.Main;
import mc.cyberplex.LaserTag.arena.Arena;
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

	@EventHandler
	public void onPlayerQuitMinecraft(PlayerQuitEvent event) {

		Player player = event.getPlayer();

		for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {

			int arenaNum = data.getArenaNum(arenaName);

			for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {

				if(player.getUniqueId().equals(data.getArena(arenaNum).getPlayer(index))) {

					rejoinTime = new BukkitRunnable() {

						int seconds = 60;

						@Override
						public void run() {

							if(seconds == 0) {

								playerState.leaveGame(arenaName, player);
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
		boolean inArena = false;
		String arena = null;

		for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {

			
			int arenaNum = data.getArenaNum(arenaName);

			for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {

				if(player.getUniqueId().equals(data.getArena(arenaNum).getPlayer(index))) {					
					inArena = true;
					arena = arenaName;
				}

			}

		}

		if(inArena == true && data.getState(arena).equalsIgnoreCase("running")) {

			rejoinTime.cancel();			

			playerList.getPlayer(arena, Message.GAME);
			player.setHealth(0);

		} else {

			if(rejoinTime != null) {
				rejoinTime.cancel();
			}

		}

	}

}
