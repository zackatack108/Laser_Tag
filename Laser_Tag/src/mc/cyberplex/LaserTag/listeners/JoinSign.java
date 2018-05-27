package mc.cyberplex.LaserTag.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import mc.cyberplex.LaserTag.Main;
import mc.cyberplex.LaserTag.arena.Arena;
import mc.cyberplex.LaserTag.arena.PlayerState;

public class JoinSign implements Listener{

	Main main = Main.getMain();
	Arena data = new Arena();

	@EventHandler
	public void onSignCreate(SignChangeEvent event) {

		Player player = event.getPlayer();

		if(player.hasPermission("lt.create.joinsign")) {

			if(event.getLine(0).equalsIgnoreCase("[lt]") && event.getLine(1).equalsIgnoreCase("join")) {

				if(main.getConfig().contains("Arenas." + event.getLine(2).toLowerCase())) {

					String arenaName = event.getLine(2).toLowerCase();
					int arenaNum = data.getArenaNum(arenaName);

					Block block = event.getBlock();

					data.setJoinSign(arenaName, block);

					event.setLine(0, ChatColor.DARK_RED + "" + ChatColor.BOLD + "[Laser tag]");
					event.setLine(1, ChatColor.GOLD + "Join");
					event.setLine(2, ChatColor.BLUE + arenaName.substring(0,1).toUpperCase() + arenaName.substring(1));
					event.setLine(3, ChatColor.GRAY + "" + data.getArena(arenaNum).getGameCount() + " / " + data.getMaxPlayers(arenaName));

				}

			}

		}

	}

	@EventHandler
	public void playerRightClickSign(PlayerInteractEvent event) {

		Player player = event.getPlayer();
		Boolean inGame = false;

		if((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) && event.getClickedBlock().getState() instanceof Sign) {

			Sign sign = (Sign) event.getClickedBlock().getState();
			String arenaName = ChatColor.stripColor(sign.getLine(2)).toLowerCase();

			if(main.getConfig().contains("Arenas." + arenaName)) {

				if(ChatColor.stripColor(sign.getLine(1)).equalsIgnoreCase("join") && sign.getBlock().getLocation().equals(data.getJoinSign(arenaName))) {

					for(String name : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {

						int arenaNum = data.getArenaNum(name);

						for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {

							if(player.getUniqueId().equals(data.getArena(arenaNum).getPlayer(index))) {

								inGame = true;
								player.sendMessage(ChatColor.RED + "Sorry, you are currently in a game. Do /lt leave");

							} else {
								inGame = false;
							}

						}

					}

					if(inGame == false) {
						PlayerState playerState = new PlayerState();
						playerState.joinGame(player, arenaName);
					}

				}

			}
			
		}

	}

	public void updateSign(String arenaName) {

		String arenaState = main.getConfig().getString("Arenas." + arenaName + ".state");

		Location signLocation = data.getJoinSign(arenaName);

		Block block = signLocation.getWorld().getBlockAt(signLocation);
		BlockState state = block.getState();

		int arenaNum = data.getArenaNum(arenaName);

		Sign sign = (Sign) state;

		if(arenaState.equalsIgnoreCase("waiting for players") || arenaState.equalsIgnoreCase("stopping")) {
			sign.setLine(3, ChatColor.GRAY + "" + data.getArena(arenaNum).getGameCount() + " / " + data.getMaxPlayers(arenaName));
			sign.update();
		} else if(arenaState.equalsIgnoreCase("running")) {
			sign.setLine(3, ChatColor.DARK_RED + "Running");
			sign.update();			
		}

	}

}
