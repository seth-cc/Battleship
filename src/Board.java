import java.util.Scanner;

// On notation:
// In the gameStatus[][] array 0 is an empty space, 1 is a player ship, 2 is a player grenade, 3 is a computer ship, 4 is a computer grenade.
// 5 signifies a space already called
// Both shot methods return int values with the same meaning

public class Board {
private static char[] letterList = {'A','B','C','D','E','F','G','H'};
private static char[] shipDisplay = {'*','s','g','S','G','*'}; // last one is for repeats.
private int gameStatus[][] = new int[8][8];
private char gameOutput[][] = new char[8][8];
Scanner key = new Scanner(System.in);

public Board(){         //Constructor will take input and check its validity
	for(int y = 0; y < gameStatus.length; y++) {
		for(int x = 0; x < gameStatus[0].length; x++) {
			gameStatus[x][y] = 0;
			gameOutput[x][y] = '_';
		}
	}

	for(int x = 0; x < 1; x++) {
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
	//Computer placement
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
public boolean playerShoot(){
	boolean[] valid = {false,false};
	int[] coord = {99,99}; // default value to prevent errors
	while(!valid[0]) {
		System.out.println("\nPlease enter the coordinates of the cell you would like to shoot. You cannot shoot somewhere someone has already shot.");
		coord = getUserCoord();
		// int target = gameStatus[coord[0]][coord[1]];
		valid = checkShot(coord, gameStatus[coord[0]][coord[1]]);
	}
	return valid[1];
}
public boolean computerShoot(){
	boolean[] valid = {false,false}; //first val is whether shoot coords are accepted, second is whether it was a grenade
	int[] coord = {99,99};
	while(!valid[0]) {
		coord[0] = (int)(Math.random()*8);
		coord[1] = (int)(Math.random()*8);
		valid = checkShot(coord, gameStatus[coord[0]][coord[1]]);
	}
	return valid[1];
}
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
public int[] getUserCoord(){
	int x = 99;
	int y = 99;
	while(true) {
		String coord = key.next(); // Receive coord from user.
		if (coord.length() == 2) {
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
public boolean[] checkShot(int[] coord, int target){
	boolean[] vals = {false,false};
	if (gameStatus[coord[0]][coord[1]] == 5) {
		return vals;
	}
	else{
		vals[0] = true;
		if(gameStatus[coord[0]][coord[1]] == 2 || gameStatus[coord[0]][coord[1]] == 4){
			vals[1] = true;
		}
		gameOutput[coord[0]][coord[1]] = shipDisplay[target];
		gameStatus[coord[0]][coord[1]] = 5;
		return vals;
	}
}
public boolean fetchStatus(int ship, int grenade){
	for(int[] row : gameStatus){
		for(int cell : row){
			if(cell == ship || cell == grenade)
				return true;
		}
	}
	return false;
}
}
