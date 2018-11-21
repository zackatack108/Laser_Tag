package mc.cyberplex.LaserTag.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import mc.cyberplex.LaserTag.Main;
import mc.cyberplex.LaserTag.arena.Arena;

public class PlayerDamage implements Listener{

	Main main = Main.getMain();
	Arena data = new Arena();

	@EventHandler
	public void playerHitEvent(EntityDamageByEntityEvent event) {

		boolean attackerInGame = false, victimInGame = false;

		if(event.getDamager() instanceof Player && event.getEntity() instanceof Player) {

			Player hitter = (Player) event.getDamager();
			Player victim = (Player) event.getEntity();

			for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {

				int arenaNum = data.getArenaNum(arenaName);

				for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {

					if(hitter.getUniqueId().equals(data.getArena(arenaNum).getPlayer(index))) {
						attackerInGame = true;
					}

					if(victim.getUniqueId().equals(data.getArena(arenaNum).getPlayer(index))) {
						victimInGame = true;
					}

				}

			}

			if(attackerInGame == true && victimInGame == true) {
				event.setCancelled(true);				
			} else if(attackerInGame == true && victimInGame == false) {				
				event.setCancelled(true);				
			} else if(attackerInGame == false && victimInGame == true) {
				event.setCancelled(true);
			}

		}

	}

	@EventHandler
	public void playerFallDamage(EntityDamageEvent event) {

		if(event.getEntity() instanceof Player) {

			Player player = (Player) event.getEntity();

			for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {

				int arenaNum = data.getArenaNum(arenaName);

				for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {

					if(player.getUniqueId().equals(data.getArena(arenaNum).getPlayer(index)) && event.getCause() == EntityDamageEvent.DamageCause.FALL) {						
						event.setCancelled(true);						
					}

				}

			}

		}

	}

	@EventHandler
	public void playerFireDamage(EntityDamageEvent event){

		if(event.getEntity() instanceof Player) {

			Player player = (Player) event.getEntity();

			for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {

				int arenaNum = data.getArenaNum(arenaName);

				for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {

					if(player.getUniqueId().equals(data.getArena(arenaNum).getPlayer(index)) && event.getCause() == EntityDamageEvent.DamageCause.FIRE) {						
						event.setCancelled(true);	
						player.setFireTicks(0);
					}
					
					if(player.getUniqueId().equals(data.getArena(arenaNum).getPlayer(index)) && event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {						
						event.setCancelled(true);
						player.setFireTicks(0);
					}
					
					if(player.getUniqueId().equals(data.getArena(arenaNum).getPlayer(index)) && event.getCause() == EntityDamageEvent.DamageCause.LAVA) {						
						event.setCancelled(true);
						player.setFireTicks(0);
					}
					
					if(player.getUniqueId().equals(data.getArena(arenaNum).getPlayer(index)) && event.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR) {						
						event.setCancelled(true);
					}

				}

			}

		}

	}

	@EventHandler
	public void playerDrown(EntityDamageEvent event) {
		
		if(event.getEntity() instanceof Player) {

			Player player = (Player) event.getEntity();

			for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {

				int arenaNum = data.getArenaNum(arenaName);

				for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {

					if(player.getUniqueId().equals(data.getArena(arenaNum).getPlayer(index)) && event.getCause() == EntityDamageEvent.DamageCause.DROWNING) {						
						event.setCancelled(true);						
					}

				}

			}

		}
		
	}
	
	@EventHandler
	public void fireworkDamage(EntityDamageEvent event) {
		
		if(event.getEntity() instanceof Player) {

			Player player = (Player) event.getEntity();

			for(String arenaName : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {

				int arenaNum = data.getArenaNum(arenaName);

				for(int index = 0; index < data.getArena(arenaNum).getGameCount(); index++) {

					if(player.getUniqueId().equals(data.getArena(arenaNum).getPlayer(index)) && event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {						
						event.setCancelled(true);						
					}

				}

			}

		}
		
	}
	
}
