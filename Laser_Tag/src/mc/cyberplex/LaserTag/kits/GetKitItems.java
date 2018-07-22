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
import mc.cyberplex.LaserTag.arena.Arena;

public class GetKitItems {

	//get the main class
	Main main = Main.getMain();
	Arena data = new Arena();

	//get the laser color from the config for the player
	public void getLaserColor(Player player, Location loc, int arenaNum) {
		
		//Player player, float x, float y, float z, int arenaNum

		//create a variable laser color and initialize it to the value from the config
		String laserColor = data.getLaserTagData(arenaNum).getLaserColor(player);

		//check to see what the color is and set red, green, and blue values to display the color
		switch (laserColor) {

		case "RED":			
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.RED, 1));
			break;			
		case "ORANGE":			
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.ORANGE, 1));
			break;			
		case "YELLOW":			
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.YELLOW, 1));
			break;			
		case "GREEN":			
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.LIME, 1));
			break;			
		case "BLUE":			
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.BLUE, 1));
			break;			
		case "INDIGO":			
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.NAVY, 1));
			break;			
		case "VIOLET":			
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.PURPLE, 1));
			break;			
		case "PINK":			
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.FUCHSIA, 1));
			break;			
		case "RAINBOW":			
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.RED, 1));
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.ORANGE, 1));
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.YELLOW, 1));
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.LIME, 1));
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.BLUE, 1));
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.NAVY, 1));
			player.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, new Particle.DustOptions(Color.PURPLE, 1));
			return;	
		}

	}

	//get the firework color from the config for the player
	public void getFireworkColor(Firework fw, Player player, Location point, int arenaNum) {

		//create variable for firework color and initialize it to the color from the config for the user
		String fireworkColor = data.getLaserTagData(arenaNum).getFireworkColor(player);

		//create firework meta to show the color
		FireworkMeta meta = fw.getFireworkMeta();;
		FireworkEffect.Builder builder = FireworkEffect.builder();;

		//see what the color of the firework is and set the builder to the color for the firework
		switch (fireworkColor)
		{
		case "BLUE":			
			builder.withFlicker().withColor(Color.BLUE).with(FireworkEffect.Type.BALL);
			break;
		case "GREEN":
			builder.withFlicker().withColor(Color.LIME).with(FireworkEffect.Type.BALL);
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
			builder.withFlicker().withColor(new Color[] { Color.RED, Color.ORANGE,Color.YELLOW, Color.LIME, Color.BLUE, Color.NAVY, Color.PURPLE }).with(FireworkEffect.Type.BALL);
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
	public Material getGunType(Player player, int arenaNum) {

		//create variable for gun type and initialize it to config value for the user 
		String gunType = data.getLaserTagData(arenaNum).getGunType(player);

		//check to see what the gun type is and return the material value
		switch (gunType) {

		case "WOOD":

			return Material.WOODEN_HOE;

		case "STONE":

			return Material.STONE_HOE;

		case "IRON":

			return Material.IRON_HOE;

		case "GOLD":

			return Material.GOLDEN_HOE;

		case "DIAMOND":

			return Material.DIAMOND_HOE;

		default:

			return null;

		}

	}

	//get the gun range from the config for the player
	public int getGunRange(Player player, int arenaNum) {

		//create variable for gun range and initialize it to value from the config for the user
		String gunRange = data.getLaserTagData(arenaNum).getGunRange(player);

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
	public int getReloadTime(Player player, int arenaNum) {

		//create variable for reload time and initilize it to the value from the config for the user
		String reloadTime = data.getLaserTagData(arenaNum).getReloadTime(player);

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
