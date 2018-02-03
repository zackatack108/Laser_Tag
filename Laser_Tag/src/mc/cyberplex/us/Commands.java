package mc.cyberplex.us;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import mc.cyberplex.us.kits.Shop;

public class Commands implements CommandExecutor{

	Main main = Main.getMain();
	Shop shop = new Shop();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("lt")) {
			
			switch (args[0].toLowerCase()) {
			
			case "help":
				
				break;
			
			case "join":
				
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
