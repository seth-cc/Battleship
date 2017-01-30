/**
 * The Game class is the class which handles the actual game, including players and turn ordering.
 * employs the Board class.
   @author Anne Ehresmann - 27858906
   @author Seth - ID HERE
   @version 1.0
   @see Board
 */
public class Game {
/**
 * the main function that runs the game. makes a board, keeps track of the status of the game, and handles turn ordering.
 */
public static void main(String[] args) {
	Board bd = new Board();

	boolean playerStatus = true;
	boolean compStatus = true;
	boolean playerHitGrenade = false;
	boolean compHitGrenade = false;
	int playerMissed = 0;
	int compMissed = 0;
	int turn = 1;
	String winner;

	while(playerStatus && compStatus) {
		System.out.println("Starting turn " + turn + ":");
		if(!playerHitGrenade) {
			System.out.println(bd);
			// System.out.println(bd.viewGameState()); // For troubleshooting
			playerHitGrenade = bd.playerShoot();
			compStatus = bd.fetchStatus(3); // checks if there's any enemy ships alive
		}
		else {
			System.out.println("Your turn skipped! Computer is going again...");
			playerMissed++;
			playerHitGrenade = false;
		}
		if(!compHitGrenade) {
			System.out.println(bd);
			System.out.print("Comp is shooting...");
			compHitGrenade = bd.computerShoot();
			playerStatus = bd.fetchStatus(1); // checks if there's any player ships alive
		}
		else {
			compMissed++;
			System.out.println("Computer skips his turn! You get another turn: \n");
			compHitGrenade = false;
		}
		turn++;
	}
	System.out.println(bd);
	if(playerStatus) {
		winner = "you";
	}
	else {
		winner = "the computer";
	}
	System.out.println("Game finished! Looks like " + winner + " won! \nYou hit " + playerMissed +" grenades.\nThe Computer hit " + compMissed +" grenades.\nClosing the game...");
	System.exit(0);
}

}
