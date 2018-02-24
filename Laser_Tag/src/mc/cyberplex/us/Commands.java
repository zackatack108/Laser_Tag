package mc.cyberplex.us;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import mc.cyberplex.us.arena.ArenaData;
import mc.cyberplex.us.kits.Shop;
import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor{

	Main main = Main.getMain();
	Shop shop = new Shop();
	ArenaData data;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("lt")) {
			
			switch (args[0].toLowerCase()) {
			
			case "help":
				
				break;
			
			case "join":
				
				if(player.hasPermission("lt.join")) {
					
					boolean inGame = false;
					
					if(args.length == 2) {
						
						String arenaName = args[1].toLowerCase();
						
						data = new ArenaData(arenaName);
						
						int arenaNum = data.getArenaNum(args[1].toLowerCase());
						
						for(int count = 0; count < data.getArena(arenaNum, arenaName).getInGameCount(); count++) {
							
							if(data.getArena(arenaNum, arenaName).getInGame(count).equals(player.getUniqueId().toString())) {
								inGame = true;								
							}
							
						}
						
					}
					
					if(inGame == true) {						
						player.sendMessage(ChatColor.RED + "Sorry you are currently in an arena");
						player.sendMessage(ChatColor.RED + "Please do " + ChatColor.DARK_RED + "/lt leave " + ChatColor.RED + "before joining the arena" );
					} else {
						
						if(args.length == 2) {
							
							String arenaName = args[1].toLowerCase();
							
							if(main.getConfig().contains("Arenas." + arenaName)) {
								
								
								
							}
							
						}
						
					}
					
				}
				
				break;
				
			case "leave":
				
				break;
				
			case "start":
				
				break;
			
			case "stop":
				
				break;
				
			case "create":
				
				break;
				
			case "delete":
				
				break;
				
			case "min":
				
				break;
				
			case "max":
				
				break;
				
			case "reload":
				
				break;
				
			case "list":
				
				break;
				
			case "shop":
				
				shop.createShop(player);
				
				break;
			
			}
			
		}
		
		return false;
	}

}
