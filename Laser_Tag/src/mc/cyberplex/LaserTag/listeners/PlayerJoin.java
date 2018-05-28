package mc.cyberplex.LaserTag.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import mc.cyberplex.LaserTag.Main;
import mc.cyberplex.LaserTag.kits.enumerations.Colors;
import mc.cyberplex.LaserTag.kits.enumerations.CoolDownTime;
import mc.cyberplex.LaserTag.kits.enumerations.GunRanges;
import mc.cyberplex.LaserTag.kits.enumerations.GunTypes;

public class PlayerJoin implements Listener{

	Main main = Main.getMain();
	
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
