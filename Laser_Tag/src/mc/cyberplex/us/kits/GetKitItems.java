package mc.cyberplex.us.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import mc.cyberplex.us.Main;

public class GetKitItems {

	Main main = Main.getMain();

	public String getLaserColor(Player player) {

		String laserColor = main.getConfig().getString("Players." + player.getUniqueId().toString() + ".laser color");

		return laserColor;

	}

	public String getFireworkColor(Player player) {

		String fireworkColor = main.getConfig().getString("Players." + player.getUniqueId().toString() + ".firework color");

		return fireworkColor;

	}

	public Material getGunType(Player player) {

		String gunType = main.getConfig().getString("Players." + player.getUniqueId().toString() + ".gun type");

		switch (gunType) {

		case "WOOD":

			return Material.WOOD_HOE;

		case "STONE":

			return Material.STONE_HOE;

		case "IRON":

			return Material.IRON_HOE;

		case "GOLD":

			return Material.GOLD_HOE;

		case "DIAMOND":

			return Material.DIAMOND_HOE;

		default:

			return null;

		}

	}

	public int getGunRange(Player player) {

		String gunRange = main.getConfig().getString("Players." + player.getUniqueId().toString() + ".gun range");

		switch (gunRange) {

		case "FIVE":

			return 5;

		case "TEN":

			return 10;

		case "FIFTEEN":

			return 15;

		case "TWENTY":

			return 20;

		case "TWENTYFIVE":

			return 25;

		default:

			return 5;

		}

	}

	public int getReloadTime(Player player) {

		String reloadTime = main.getConfig().getString("Players." + player.getUniqueId().toString() + ".reload time");

		switch (reloadTime) {

		case "TEN":

			return 10;

		case "NINE":

			return 9;

		case "EIGHT":

			return 8;

		case "SEVEN":

			return 7;

		case "SIX":

			return 6;
			
		case "FIVE":

			return 5;
			
		case "FOUR":

			return 4;
			
		case "THREE":

			return 3;
			
		case "TWO":

			return 2;

		default:

			return 10;

		}

	}

}
