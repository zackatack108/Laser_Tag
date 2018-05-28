package mc.cyberplex.LaserTag.kits;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import mc.cyberplex.LaserTag.Main;

public class GetKitItems {

	//get the main class
	Main main = Main.getMain();

	//create variables for red, green, and blue colors
	private float red;
	private float green;
	private float blue;
	
	//get kit items constructor
	public GetKitItems(){
		
		//Initialize variables 
		red = 0.1F;
		green = 0.1F;
		blue = 0.1F;
		
	}

	//get the laser color from the config for the player
	public void getLaserColor(Player player, float x, float y, float z) {

		//create a variable laser color and initialize it to the value from the config
		String laserColor = main.getConfig().getString("Players." + player.getUniqueId().toString() + ".laser color");

		//check to see what the color is and set red, green, and blue values to display the color
		switch (laserColor) {

		case "RED":

			red = 255;
			green = 0.1F;
			blue = 0.1F;

			break;			
		case "ORANGE":

			red = 255;
			green = 165;
			blue = 0.1F;

			break;			
		case "YELLOW":

			red = 255;
			green = 255;
			blue = 0.1F;

			break;			
		case "GREEN":

			red = 0.1F;
			green = 128;
			blue = 0.1F;

			break;			
		case "BLUE":

			red = 0.1F;
			green = 0.1F;
			blue = 255;

			break;			
		case "INDIGO":

			red = 238;
			green = 130;
			blue = 238;

			break;			
		case "VIOLET":
			
			red = 75;
			green = 0.1F;
			blue = 130;			

			break;			
		case "PINK":

			red = 255;
			green = 192;
			blue = 203;

			break;			
		case "RAINBOW":

			red = 127.5F;
			green =127.5F;
			blue = 127.5F;

			player.getWorld().spawnParticle(Particle.REDSTONE, x, y, z, 0, red, green, blue, 1); //spawn rainbow particle

			return;	
		}

		//spawn colored particle based off of red, green, and blue color
		player.getWorld().spawnParticle(Particle.REDSTONE, x, y, z, 0, red/255D, green/255D, blue/255D, 1);

	}

	//get the firework color from the config for the player
	public void getFireworkColor(Firework fw, Player player, Location point) {

		//create variable for firework color and initialize it to the color from the config for the user
		String fireworkColor = main.getConfig().getString("Players." + player.getUniqueId().toString() + ".firework color");

		//create firework meta to show the color
		FireworkMeta meta = fw.getFireworkMeta();
		FireworkEffect.Builder builder = FireworkEffect.builder();

		//see what the color of the firework is and set the builder to the color for the firework
		switch (fireworkColor)
		{
		case "BLUE":
			
			builder.withFlicker().withColor(Color.BLUE).with(FireworkEffect.Type.BALL);			

			break;
		case "GREEN": 

			builder.withFlicker().withColor(Color.GREEN).with(FireworkEffect.Type.BALL);

			break;
		case "INDIGO":		
			
			builder.withFlicker().withColor(Color.NAVY).with(FireworkEffect.Type.BALL);

			break;
		case "ORANGE": 
			
			builder.withFlicker().withColor(Color.ORANGE).with(FireworkEffect.Type.BALL);			

			break;
		case "PINK": 
			
			builder.withFlicker().withColor(Color.FUCHSIA).with(FireworkEffect.Type.BALL);			

			break;
		case "RAINBOW": 
			
			builder.withFlicker().withColor(new Color[] { Color.RED, Color.ORANGE, Color.GREEN, Color.BLUE, Color.PURPLE }).with(FireworkEffect.Type.BALL);

			break;
		case "RED": 
			
			builder.withFlicker().withColor(Color.RED).with(FireworkEffect.Type.BALL);

			break;
		case "VIOLET": 

			builder.withFlicker().withColor(Color.PURPLE).with(FireworkEffect.Type.BALL);

			break;
		case "YELLOW": 

			builder.withFlicker().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL);

			break;
		default: 

			builder.withFlicker().withColor(Color.RED).with(FireworkEffect.Type.BALL);	      

		}

		//set the firework meta based off of the color
		meta.addEffects(new FireworkEffect[] { builder.build() });
		meta.setPower(0);		
		fw.setFireworkMeta(meta);

	}

	//get the gun type from the config for the player
	public Material getGunType(Player player) {

		//create variable for gun type and initialize it to config value for the user 
		String gunType = main.getConfig().getString("Players." + player.getUniqueId().toString() + ".gun type");

		//check to see what the gun type is and return the material value
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

	//get the gun range from the config for the player
	public int getGunRange(Player player) {

		//create variable for gun range and initialize it to value from the config for the user
		String gunRange = main.getConfig().getString("Players." + player.getUniqueId().toString() + ".gun range");

		//check to see what the gun range is and return the range value
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

	//get the reload time from the config for the player
	public int getReloadTime(Player player) {

		//create variable for reload time and initilize it to the value from the config for the user
		String reloadTime = main.getConfig().getString("Players." + player.getUniqueId().toString() + ".reload time");

		//check to see what the reload time is and return the reload value
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
