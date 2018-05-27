package mc.cyberplex.LaserTag.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import mc.cyberplex.LaserTag.Main;
import mc.cyberplex.LaserTag.arena.ArenaData;
import mc.cyberplex.LaserTag.arena.ArenaState;
import mc.cyberplex.LaserTag.arena.Message;
import mc.cyberplex.LaserTag.arena.PlayerList;
import mc.cyberplex.LaserTag.kits.GetKitItems;

public class GunFire implements Listener{
	
	Main main = Main.getMain();
	GetKitItems kit = new GetKitItems();
	PlayerList playerList = new PlayerList();
	ArenaData data = new ArenaData();
	ArenaState state = new ArenaState();

	Location otherPlayerLoc = null;
	static HashMap<String, Long> coolDowns = new HashMap<String, Long>();

	@EventHandler
	public void getGunClick(PlayerInteractEvent event) {

		Player player = event.getPlayer();

		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR) {
			
			String playerID = player.getUniqueId().toString();
			
			boolean inArena = false;
			String arenaName = null;
			int tempArenaNum = -1;
			int arenaNum = -1;

			for(String arena : main.getConfig().getConfigurationSection("Arenas").getKeys(false)) {
				tempArenaNum = data.getArenaNum(arena);
				for(int count = 0; count < data.getArena(tempArenaNum).getInGameCount(); count++) {
					if(data.getArena(tempArenaNum).getInGame(count).equals(playerID)) {
						inArena = true;
						arenaName = arena;
						arenaNum = data.getArenaNum(arenaName);
					}
				}
			}

			ItemStack gunType = new ItemStack(kit.getGunType(player));

			if(player.getInventory().getItemInMainHand().equals(gunType) && inArena == true) {

				Location start = player.getEyeLocation();
				Vector increase = start.getDirection();
				Player otherPlayer = getNearestEntityInSight(player, kit.getGunRange(player));

				if(otherPlayer!=null) {					
					this.otherPlayerLoc = otherPlayer.getLocation();					
				}

				start.setY(start.getY() - 0.2D);

				int coolDownTime = this.kit.getReloadTime(player);

				if(coolDowns.containsKey(player.getName())) {

					long seconds = coolDowns.get(player.getName()).longValue() / 1000L + coolDownTime - System.currentTimeMillis() / 1000L;
					
					player.setLevel((int) seconds);
					
					if (seconds > 0L)
					{
						player.sendMessage(ChatColor.RED + "You can fire again in " + seconds + " seconds!");
						return;
					}

				}

				coolDowns.put(player.getName(), Long.valueOf(System.currentTimeMillis()));
				reloadMsg(player, coolDownTime);

				for(int range = 0; range <= kit.getGunRange(player); range++) {

					Location point = start.add(increase);

					float x = (float) point.getX();
					float y = (float) point.getY();
					float z = (float) point.getZ();

					kit.getLaserColor(player, x, y, z);
					player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 1, 2);

					if ((otherPlayer != null) && 
							(Math.round(point.getX()) == Math.round(this.otherPlayerLoc.getX())) && 
							(Math.round(point.getY()) == Math.round(this.otherPlayerLoc.getY() + 1.0D)) && 
							(Math.round(point.getZ()) == Math.round(this.otherPlayerLoc.getZ())))
					{
						
						final Firework fw = (Firework)point.getWorld().spawnEntity(point, EntityType.FIREWORK);
						kit.getFireworkColor(fw, player, point);
						otherPlayer.setHealth(0.0D);
						playerList.deathMessage(arenaName, otherPlayer, player);
						
						new BukkitRunnable()
						{
							public void run()
							{
								fw.detonate();
							}
						}.runTaskLater(this.main, 1L);
						
						for(int index = 0; index < data.getArena(arenaNum).getInGameCount(); index++) {
							
							if(data.getArena(arenaNum).getInGame(index).equals(playerID)) {
								
								int oldScore = data.getArena(arenaNum).getPlayerScore(index);
								data.getArena(arenaNum).setPlayerScore(index, ++oldScore);
								playerList.getPlayer(arenaName, Message.GAME);
								
								if(data.getArena(arenaNum).getPlayerScore(index) >= data.getScoreToWin()) {
									
									main.getConfig().set("Arenas." + arenaName + ".state", "stopping");
									state.stop(arenaName);		
								}
								
							}
							
						}										
						
					}

				}

			}

		}

	}
	
	public void reloadMsg(Player player, int seconds) {
		
		if(coolDowns.containsKey(player.getName())) {
			
			new BukkitRunnable() {
				
				int count = seconds;
				
				@Override
				public void run() {
					
					if(count > 0) {						
						player.setLevel(count);						
					} else if(count == 0) {
						player.setLevel(0);
						cancel();
					}					
					--count;					
				}
				
			}.runTaskTimer(main, 0, 20);
			
		}
		
	}

	public static Player getNearestEntityInSight(Player player, int range)
	{
		ArrayList<Entity> entities = (ArrayList<Entity>)player.getNearbyEntities(range, range, range);
		ArrayList<Block> sightBlock = (ArrayList<Block>)player.getLineOfSight(null, range);
		ArrayList<Location> sight = new ArrayList<Location>();
		for (int i = 0; i < sightBlock.size(); i++) {
			sight.add(sightBlock.get(i).getLocation());
		}
		for (int i = 0; i < sight.size(); i++) {
			for (int k = 0; k < entities.size(); k++) {
				if ((Math.abs(entities.get(k).getLocation().getX() - sight.get(i).getX()) < 1.3D) && 
						(Math.abs(entities.get(k).getLocation().getY() - sight.get(i).getY()) < 1.5D) && 
						(Math.abs(entities.get(k).getLocation().getZ() - sight.get(i).getZ()) < 1.3D)) {
					return (Player)entities.get(k);
				}
				
			}
			
		}
		return null;
	}

}
