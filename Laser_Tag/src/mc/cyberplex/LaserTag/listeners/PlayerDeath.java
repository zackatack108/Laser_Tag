package mc.cyberplex.LaserTag.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import mc.cyberplex.LaserTag.Main;
import mc.cyberplex.LaserTag.arena.ArenaData;
import mc.cyberplex.LaserTag.kits.GetKitItems;

public class PlayerDeath implements Listener{

	Main main = Main.getMain();
	ArenaData data = new ArenaData();
	GetKitItems kit = new GetKitItems();

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {

		Player player;

		if(event.getEntity() instanceof Player) {

			player = event.getEntity().getPlayer();

			for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {

				int arenaNum = data.getArenaNum(arenaName);

				for(int index = 0; index < data.getArena(arenaNum).getInGameCount(); index++) {

					boolean inGame = false;
					if(player.getUniqueId().toString().equals(data.getArena(arenaNum).getInGame(index))) {
						inGame = true;
					}

					if(inGame == true) {
						
						event.setDeathMessage(null);

						player.getInventory().clear();
						event.getDrops().clear();
						player.setHealth(20);
						player.setGameMode(GameMode.SPECTATOR);

						onRespawn(player, arenaName);

					}

				}

			}

		}

	}

	public void onRespawn(Player player, String arenaName) {

		new BukkitRunnable() {

			int seconds = 5;

			@Override
			public void run() {

				if(main.getConfig().getString("Arenas." + arenaName + ".state").equalsIgnoreCase("stopping")) {
					this.cancel();
				} else {

					player.setLevel(seconds);		

					if(this.seconds == 0 && main.getConfig().getString("Arenas." + arenaName + ".state").equalsIgnoreCase("running")) {

						player.setGameMode(GameMode.SURVIVAL);
						player.getInventory().clear();
						player.teleport(data.getSpawn(arenaName));

						//get the gun type that the player has and give it to them
						ItemStack gun = new ItemStack(kit.getGunType(player));				
						player.getInventory().addItem(gun);

						player.setHealth(20);
						player.removePotionEffect(PotionEffectType.INVISIBILITY);

						this.cancel();

					} else if(main.getConfig().getString("Arenas." + arenaName + ".state").equalsIgnoreCase("waiting for players")) {
						cancel();
					}

					seconds--;

				}

			}

		}.runTaskTimer(main, 0, 20);

	}

}
