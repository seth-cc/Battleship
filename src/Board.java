/**
 * The Board class is the class which handles the board and all its eventual modifications which happen throughout
 * the course of the game. It is employed by the Game class.
   @author Anne Ehresmann - 27858906
   @author Seth - ID HERE
   @version 1.0
   @see Game
 */

import java.util.Scanner;


/**
 * On notation:
 * In the gameStatus[][] array 0 is an empty space, 1 is a player ship, 2 is a player grenade,
 * 3 is a computer ship, 4 is a computer grenade.
 * 5 signifies a space already shot at.
 */

public class Board {
private static char[] letterList = {'A','B','C','D','E','F','G','H'};
private static char[] shipDisplay = {'*','s','g','S','G','*'}; // last one is for repeats.
private int gameStatus[][] = new int[8][8];
private char gameOutput[][] = new char[8][8];
private static Scanner key = new Scanner(System.in);

/**
 * constructor which creates a board, as well as placing player
 * ships/grenades based on input, and generating random coordinates for enemy ships.
 * two 2D arrays are used: one for ship tracking, and one for the actual visible output.
   @see getUserCoord()
 */
public Board(){
	for(int y = 0; y < gameStatus.length; y++) {
		for(int x = 0; x < gameStatus[0].length; x++) {
			gameStatus[x][y] = 0;
			gameOutput[x][y] = '_';
		}
	}

	for(int x = 0; x < 6; x++) {
		boolean valid = false;
		while(!valid) {
			System.out.println("Please enter the coordinates for ship "+ "#" + (x+1) + " in the form \"A1\": ");
			int[] coord = getUserCoord();
			if(gameStatus[coord[0]][coord[1]] == 0) {
				gameStatus[coord[0]][coord[1]] = 1;
				valid = true;
			}
			else System.out.println("There is already a ship here!");
		}
	}

	for(int x = 0; x < 4; x++) {
		boolean valid = false;
		while(!valid) {
			System.out.println("Please enter the coordinates for grenade "+ "#" + (x+1) + " in the form \"A1\": ");
			int[] coord = getUserCoord();
			if(gameStatus[coord[0]][coord[1]] == 0) {
				gameStatus[coord[0]][coord[1]] = 2;
				valid = true;
			}
			else System.out.println("There is already a ship here!");
		}
	}
	for(int x = 0; x < 6; ) {
		int coordX = (int)(Math.random()*8);
		int coordY = (int)(Math.random()*8);

		if(gameStatus[coordX][coordY] != 0) continue;
		else gameStatus[coordX][coordY] = 3;
		x++;
	}
	for(int x = 0; x < 4; ) {
		int coordX = (int)(Math.random()*8);
		int coordY = (int)(Math.random()*8);

		if(gameStatus[coordX][coordY] != 0) continue;
		else gameStatus[coordX][coordY] = 4;
		x++;
	}
	System.out.println("The computer has randomly placed its ships and grenades, lets play!");

}

/**
 * used for fetching the coordinates that the player wants to shoot at.
   @return {boolean} returns whether or not the area shot was a grenade.
 */
public boolean playerShoot(){
	boolean[] valid = {false,false};
	int[] coord = {99,99}; // default value to prevent errors
	while(!valid[0]) {
		System.out.println("\nPlease enter the coordinates of the cell you would like to shoot.");
		coord = getUserCoord();
		// int target = gameStatus[coord[0]][coord[1]];
		valid = checkShot(coord);
	}
	return valid[1];
}

/**
 * used for randomly generating coordinates that the computer will shoot at.
   @return {boolean} returns whether or not the area shot was a grenade.
 */
public boolean computerShoot(){
	boolean[] valid = {false,false};
	int[] coord = {99,99};
	while(!valid[0]) {
		coord[0] = (int)(Math.random()*8);
		coord[1] = (int)(Math.random()*8);
		valid = checkShot(coord);
	}
	String stringCoord = Character.toString(letterList[coord[0]]) + Integer.toString(coord[1] + 1);
	System.out.println("\t Hit "+ stringCoord +"!");
	return valid[1];
}
/**
 * used for printing out the board, displaying which slots have been hit and which have not yet been hit.
   @return {String} ret - the actual board, normal view.
 */
public String toString(){
	String ret = "";
	for(int y = 0; y < gameOutput.length; y++) {
		for(int x = 0; x < gameOutput[0].length; x++) {
			ret += gameOutput[x][y] + "  ";
			if(x == 7) ret+= "\n";
		}
	}
	return ret;
}
/**
 * used for printing out the board status, including ships that have not yet been hit.
 * useful for checking for bugs.
   @return {String} ret - the actual board, "cheat" view.
 */
public String viewGameState(){
	String ret = "";
	for(int y = 0; y < gameStatus.length; y++) {
		for(int x = 0; x < gameStatus[0].length; x++) {
			ret += gameStatus[x][y] + "  ";
			if(x == 7) ret+= "\n";
		}
	}
	return ret;
}
/**
 * used to fetch user coordinates using a scanner object, and validate said coordinate.
   @return {int[]} val - the coordinates that the user has chosen
 */
public int[] getUserCoord(){
	int x = 99;
	int y = 99;
	while(true) {
		String coord = key.next(); // Receive coord from user.
		if (coord.length() == 2) {
			coord = coord.substring(0,1).toUpperCase() + coord.substring(1);
			//Make sure first char is a letter within bounds.
			// Make sure second char is a number within bounds.
			if ((int) coord.charAt(0) > 64 && (int) coord.charAt(0) < 73 && (int) coord.charAt(1) -1 > 47 && coord.charAt(1) - 1 < 56) {

				// Convert first char to a useable x value.
				for (int num = 0; num < letterList.length; num++) {
					if(coord.charAt(0) == letterList[num]) {
						x = num;
					}
				}

				// Get second char as y value.
				y = Character.getNumericValue(coord.charAt(1)) - 1;
				int[] val = {x,y};
				return val;
			}
			else{
				System.out.println("Your coordinate is out of bounds!\nPlease try again, in the form \"A1\":");
			}
		}
		else{
			System.out.println("Your coordinate is of invalid size!\nPlease try again, in the form \"A1\":");
		}
	}
}
/**
 * used to validate the coordinate given, and check that coordinate's status.
 * @param {int[]} coord - an array of size 2, containing the X and Y values for the coordinates of the shot.
 * @return {boolean[]}  first val is whether shoot coords are accepted, second is whether it was a grenade.
 */
public boolean[] checkShot(int[] coord){
	int target = gameStatus[coord[0]][coord[1]];
	boolean[] vals = {true,false};
	if (gameStatus[coord[0]][coord[1]] == 5) {
		return vals;
	}
	else{
		if(gameStatus[coord[0]][coord[1]] == 2 || gameStatus[coord[0]][coord[1]] == 4) {
			vals[1] = true;
		}
		gameOutput[coord[0]][coord[1]] = shipDisplay[target];
		gameStatus[coord[0]][coord[1]] = 5;
		return vals;
	}
}

/**
 * Checks whether or not there is at least one ship of the shiptype provided.
 * @param {int} ship - the ship number to be checked: 1 for player, 3 for computer.
 * @return {boolean} <code>true</code> if there is at least one ship of the shiptype provided. Else <code>false</code>
 */

public boolean fetchStatus(int ship){
	for(int[] row : gameStatus) {
		for(int cell : row) {
			if(cell == ship)
				return true;
		}
	}
	return false;
}

/**
 * getter for game status just in case
 @return {int[][]} gameStatus - 2d array of the board with ship status, i.e. cheat view.
 */
public int[][] getGameStatus() {
	return gameStatus;
}
/**
 * setter for game status just in case
 * @param {int[][]} gameStatus - 2d array of the board with ship status, i.e. cheat view.
 */
public void setGameStatus(int[][] gameStatus) {
	this.gameStatus = gameStatus;
}
/**
 * getter for player view just in case
 * @return {char[][]} gameOutput - 2d array of the board from player view, i.e. normal view.
 */
public char[][] getGameOutput() {
	return gameOutput;
}

/**
 * setter for player view just in case
 * @param {char[][]} gameOutput - 2d array of the board from player view, i.e. normal view.
 */
public void setGameOutput(char[][] gameOutput) {
	this.gameOutput = gameOutput;
}
/**
 * checking if another board is equal
 * @param {Object} obj - the other object to test equality with
 * @return {boolean} returns whether or not the two objects are equal.
 */
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	return false;
}

}
