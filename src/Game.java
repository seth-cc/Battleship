public class Game {

public static void main(String[] args) {
	Board bd = new Board();

	boolean playerStatus = true;
	boolean compStatus = true;
	boolean didPlayerHitGrenade = false;
	boolean didCompHitGrenade = false;
	int turn = 1;
	while(playerStatus && compStatus) {
		System.out.println("Starting turn " + turn + ":");
		if(!didPlayerHitGrenade) {
			System.out.println(bd);
			didPlayerHitGrenade = bd.playerShoot();
			playerStatus = bd.fetchStatus(3,4); // checks if there's any enemy ships or grenades alive
		}
		else {
			System.out.println("Your turn skipped! Computer is going again...");
			didPlayerHitGrenade = false;
		}
		if(!didCompHitGrenade) {
			System.out.println("Comp is shooting...");
			didCompHitGrenade = bd.computerShoot();
			playerStatus = bd.fetchStatus(1,2); // checks if there's any player ships or grenades alive
		}
		else {
			System.out.println("Computer skips his turn! You get another turn: \n");
			didCompHitGrenade = false;
		}
		turn++;
	}
	System.out.println(bd);
	String winner;
	if(playerStatus) {
		winner = "you";
	}
	else {
		winner = "the computer";
	}
	System.out.println("Game finished! Looks like " + winner + " won! \nClosing the game...");
	System.exit(0);
}

}
