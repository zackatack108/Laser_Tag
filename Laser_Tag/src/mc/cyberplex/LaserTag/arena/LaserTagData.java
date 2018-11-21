package mc.cyberplex.LaserTag.arena;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import mc.cyberplex.LaserTag.Main;

class KitData {

	UUID playerID;
	String laserColor;
	String fireworkColor;
	String gunType;
	String gunRange;
	String reloadTime;

	KitData(UUID playerID_, String laserColor_, String fireworkColor_, String gunType_, String gunRange_, String reloadTime_) {

		playerID = playerID_;
		laserColor = laserColor_;
		fireworkColor = fireworkColor_;
		gunType = gunType_;
		gunRange = gunRange_;
		reloadTime = reloadTime_;

	}

}

public class LaserTagData {

	static Main main = Main.getMain();

	private final int MAX = 50;
	public BukkitTask Timer = null;
	private int playerScores[] = new int[MAX], scoreToWin = 25;
	private ArrayList<KitData> playerKit = new ArrayList<KitData>();

	//--------------------
	//setters for class
	//--------------------	
	public void setPlayerScore(int index, int score){

		if(score >= 0) {
			playerScores[index] = score;
		}

	}

	public void setPlayerKit(Player player) {

		String laserColor = main.getConfig().getString("Players." + player.getUniqueId().toString() + ".laser color");
		String fireworkColor =  main.getConfig().getString("Players." + player.getUniqueId().toString() + ".firework color");
		String gunType = main.getConfig().getString("Players." + player.getUniqueId().toString() + ".gun type");
		String gunRange = main.getConfig().getString("Players." + player.getUniqueId().toString() + ".gun range");
		String reloadTime = main.getConfig().getString("Players." + player.getUniqueId().toString() + ".reload time");

		playerKit.add(new KitData(player.getUniqueId(), laserColor, fireworkColor, gunType, gunRange, reloadTime));

	}

	//---------------------------------------------------
	//removers for class
	//---------------------------------------------------
	public void removeFromPlayerKits(int index) {
		if(playerKit != null && playerKit.get(index) != null) {
			playerKit.remove(index);			
		}
	}
	
	public void removeFromScoreboard(int index) {
		
		if(playerScores[index] >= 0) {
			
			int tempScores[] = new int[MAX];
			int count = 0;
			
			for(int i = 0; i < MAX; i++) {
				
				if(i != index) {
					tempScores[count] = playerScores[i];
					count++;
				}			
				
			}
			
			for(int i = 0; i < MAX; i++) {
				
				playerScores[i] = tempScores[i];
				
			}
			
		}
		
	}

	//---------------------------------------------------
	//getters for class
	//---------------------------------------------------
	public int getPlayerScore(int index){
		return playerScores[index];
	}

	public int getScoreToWin() {
		return scoreToWin;		
	}

	public String getLaserColor(Player player) {		

		for(int index = 0; index < playerKit.size(); index++) {

			if(playerKit.get(index).playerID.equals(player.getUniqueId())) {
				return playerKit.get(index).laserColor;
			}

		}

		return null;
	}

	public String getFireworkColor(Player player) {

		for(int index = 0; index < playerKit.size(); index++) {

			if(playerKit.get(index).playerID.equals(player.getUniqueId())) {
				return playerKit.get(index).fireworkColor;
			}

		}

		return null;
	}

	public String getGunType(Player player) {

		String gunType = null;

		for(int index = 0; index < playerKit.size(); index++) {

			if(playerKit.get(index).playerID.equals(player.getUniqueId())) {
				gunType = playerKit.get(index).gunType;
			}

		}

		return gunType;
	}

	public String getGunRange(Player player) {

		for(int index = 0; index < playerKit.size(); index++) {

			if(playerKit.get(index).playerID.equals(player.getUniqueId())) {
				return playerKit.get(index).gunRange;
			}

		}

		return null;
	}

	public String getReloadTime(Player player) {

		for(int index = 0; index < playerKit.size(); index++) {

			if(playerKit.get(index).playerID.equals(player.getUniqueId())) {
				return playerKit.get(index).reloadTime;
			}

		}

		return null;
	}

}
