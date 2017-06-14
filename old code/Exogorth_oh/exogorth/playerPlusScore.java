package exogorth;

public class playerPlusScore {
	String player;
	int score;
	
	playerPlusScore(String player, int score){
		this.player = player;
		this.score = score;
	}

	public String getPlayer() {
		return player;
	}

	public int getScore() {
		return score;
	}
}
