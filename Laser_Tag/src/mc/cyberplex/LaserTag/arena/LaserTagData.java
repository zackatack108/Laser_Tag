package mc.cyberplex.LaserTag.arena;

import org.bukkit.scheduler.BukkitTask;

import mc.cyberplex.LaserTag.Main;

public class LaserTagData {

	static Main main = Main.getMain();

	private final int MAX = 50;
	public BukkitTask Timer = null;
	private int playerScores[] = new int[MAX], scoreToWin = 25;

	//--------------------
	//setters for class
	//--------------------	
	public void setPlayerScore(int index, int score){

		if(score >= 0) {
			playerScores[index] = score;	
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
}
