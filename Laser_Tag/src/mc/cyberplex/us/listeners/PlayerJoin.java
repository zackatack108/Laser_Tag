package mc.cyberplex.us.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import mc.cyberplex.us.Main;
import mc.cyberplex.us.arena.ArenaData;
import mc.cyberplex.us.kits.enumerations.Colors;
import mc.cyberplex.us.kits.enumerations.CoolDownTime;
import mc.cyberplex.us.kits.enumerations.GunRanges;
import mc.cyberplex.us.kits.enumerations.GunTypes;

public class PlayerJoin implements Listener{

	Main main = Main.getMain();
	ArenaData data = new ArenaData();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		
		if(!main.getConfig().contains("Players." + player.getUniqueId().toString())) {
			
			main.getConfig().set("Players." + player.getUniqueId().toString() + ".laser color", Colors.RED.toString());
			main.getConfig().set("Players." + player.getUniqueId().toString() + ".firework color", Colors.RED.toString());
			main.getConfig().set("Players." + player.getUniqueId().toString() + ".gun type", GunTypes.WOOD.toString());
			main.getConfig().set("Players." + player.getUniqueId().toString() + ".gun range", GunRanges.FIVE.toString());
			main.getConfig().set("Players." + player.getUniqueId().toString() + ".reload time", CoolDownTime.TEN.toString());
			main.saveConfig();
			
		}
		
	}
	
}
