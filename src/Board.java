import java.util.Scanner;

// On notation:
// In the gameStatus[][] array 0 is an empty space, 1 is a player ship, 2 is a player grenade, 3 is a computer ship, 4 is a computer grenade.
// 5 signifies a space already called
// Both shot methods return int values with the same meaning


public class Board {

	int gameStatus[][] = new int[8][8];
	char gameOutput[][] = new char[8][8];


	Scanner key = new Scanner(System.in);


	public Board(){ //Constructor will take input and check its validity

		for(int y = 0; y < gameStatus.length; y++){
			for(int x = 0; x < gameStatus[0].length; x++){
				gameStatus[x][y] = 0;
			}
		}

		for(int y = 0; y < gameOutput.length; y++){
			for(int x = 0; x < gameOutput[0].length; x++){
				gameOutput[x][y] = '_';
			}
		}

		for(int x = 0; x < 6;  ){
			String coord = "";
			System.out.print("Please enter the co-ordinates of your ship #" + (x+1)+ ": ");
			coord = key.next();

			if(coord.length() != 2){
				System.out.println("Sorry! That is not a valid co-ordinate. Please try again.");
				continue;
			}

			char temp_coordX = coord.charAt(0);
			temp_coordX = Character.toUpperCase(temp_coordX);

			//System.out.println("\nDebug: coordX = " +temp_coordX);

			if(temp_coordX < 'A' || temp_coordX >'H'){
				System.out.println("Sorry! That is not a valid co-ordinate. Please try again.");
				continue;
			}
			int coordX = 0;
			switch (temp_coordX){
			case 'A' : coordX = 0;
			break;
			case 'B' : coordX = 1;
			break;
			case 'C' : coordX = 2;
			break;
			case 'D' : coordX = 3;
			break;
			case 'E' : coordX = 4;
			break;
			case 'F' : coordX = 5;
			break;
			case 'G' : coordX = 6;
			break;
			case 'H' : coordX = 7;
			break;
			}

			//System.out.println("\nDebug: coordX = " +coordX);

			if(coord.charAt(1) < '1' || coord.charAt(1) > '8'){
				System.out.println("Sorry! That is not a valid co-ordinate. Please try again.");
				continue;
			}

			int coordY = Integer.parseInt(Character.toString(coord.charAt(1)));
			coordY -= 1;

			//System.out.println("\nDebug: coordY = " +coordY + "\n");

			if(gameStatus[coordX][coordY] != 0){
				System.out.println("Sorry! Something is already at that co-ordinate. Please try again.");
				continue;
			}
			else{
				gameStatus[coordX][coordY] = 1;
			}

			x++;

		}
		for(int x = 0; x < 4;  ){
			String coord = "";
			System.out.print("Please enter the co-ordinates of your grenade #" + (x+1)+ ": ");
			coord = key.next();

			if(coord.length() > 2){
				System.out.println("Sorry! That is not a valid co-ordinate. Please try again.");
				continue;
			}

			char temp_coordX = coord.charAt(0);
			temp_coordX = Character.toUpperCase(temp_coordX);

			//System.out.println("\nDebug: coordX = " +temp_coordX);

			if(temp_coordX < 'A' || temp_coordX >'H'){
				System.out.println("Sorry! That is not a valid co-ordinate. Please try again.");
				continue;
			}
			int coordX = 0;
			switch (temp_coordX){
			case 'A' : coordX = 0;
			break;
			case 'B' : coordX = 1;
			break;
			case 'C' : coordX = 2;
			break;
			case 'D' : coordX = 3;
			break;
			case 'E' : coordX = 4;
			break;
			case 'F' : coordX = 5;
			break;
			case 'G' : coordX = 6;
			break;
			case 'H' : coordX = 7;
			break;
			}

			//System.out.println("\nDebug: coordX = " +coordX);

			if(coord.charAt(1) < '1' || coord.charAt(1) > '8'){
				System.out.println("Sorry! That is not a valid co-ordinate. Please try again.");
				continue;
			}

			int coordY = Integer.parseInt(Character.toString(coord.charAt(1)));
			coordY -= 1;

			//System.out.println("\nDebug: coordY = " +coordY + "\n");

			if(gameStatus[coordX][coordY] != 0){
				System.out.println("Sorry! Something is already at that co-ordinate. Please try again.");
				continue;
			}
			else{
				gameStatus[coordX][coordY] = 2;
			}
			x++;
		}

		for(int x = 0; x < 6; ){ //Computer placement
			int coordX = (int)(Math.random()*8);
			int coordY = (int)(Math.random()*8);

			if(gameStatus[coordX][coordY] != 0) continue;
			else gameStatus[coordX][coordY] = 3;
			x++;
		}
		for(int x = 0; x < 4; ){
			int coordX = (int)(Math.random()*8);
			int coordY = (int)(Math.random()*8);

			if(gameStatus[coordX][coordY] != 0) continue;
			else gameStatus[coordX][coordY] = 4;
			x++;
		}
		System.out.println("The computer has randomly placed its ships and grenades, lets play!");

	}


	//Both shot methods return int values which correspond to what was hit *(see top comment)


	public int playerShot(String coord){
		int ret = 0;

		char temp_coordX = coord.charAt(0);
		temp_coordX = Character.toUpperCase(temp_coordX);

		int coordX = 0;
		switch (temp_coordX){
		case 'A' : coordX = 0;
		break;
		case 'B' : coordX = 1;
		break;
		case 'C' : coordX = 2;
		break;
		case 'D' : coordX = 3;
		break;
		case 'E' : coordX = 4;
		break;
		case 'F' : coordX = 5;
		break;
		case 'G' : coordX = 6;
		break;
		case 'H' : coordX = 7;
		break;
		}
		int coordY = Integer.parseInt(Character.toString(coord.charAt(1)));
		coordY -= 1;

		int target = gameStatus[coordX][coordY];

		switch(target){
		case 0 : gameOutput[coordX][coordY] = '*';//changing both array entries
		gameStatus[coordX][coordY] = 5;
		ret = 0;
		break;
		case 1 : gameOutput[coordX][coordY] = 's';
		gameStatus[coordX][coordY] = 5;
		ret = 1;
		break;
		case 2 : gameOutput[coordX][coordY] = 'g';
		gameStatus[coordX][coordY] = 5;
		ret = 2;
		break;
		case 3 : gameOutput[coordX][coordY] = 'S';
		gameStatus[coordX][coordY] = 5;
		ret = 3;
		break;
		case 4 : gameOutput[coordX][coordY] = 'G';
		gameStatus[coordX][coordY] = 5;
		ret = 4;
		break;
		case 5 : ret = 5;
		break;
		}
		return ret;
	}

	public int computerShot(){
		int ret = 0;

		int coordX = (int)(Math.random()*8);
		int coordY = (int)(Math.random()*8);

		int target = gameStatus[coordX][coordY];

		switch(target){
		case 0 : gameOutput[coordX][coordY] = '*';
		gameStatus[coordX][coordY] = 5;
		ret = 0;
		break;
		case 1 : gameOutput[coordX][coordY] = 's';
		gameStatus[coordX][coordY] = 5;
		ret = 1;
		break;
		case 2 : gameOutput[coordX][coordY] = 'g';
		gameStatus[coordX][coordY] = 5;
		ret = 2;
		break;
		case 3 : gameOutput[coordX][coordY] = 'S';
		gameStatus[coordX][coordY] = 5;
		ret = 3;
		break;
		case 4 : gameOutput[coordX][coordY] = 'G';
		gameStatus[coordX][coordY] = 5;
		ret = 4;
		break;
		case 5 : ret = 5;
		break;
		}
		return ret;


	}


	public String toString(){

		String ret = "";

		for(int y = 0; y < gameOutput.length; y++){
			for(int x = 0; x < gameOutput[0].length; x++){
				ret += gameOutput[x][y] + "  ";
				if(x == 7) ret+= "\n";
			}
		}
		return ret;
	}

	public String viewGameState(){
		String ret = "";

		for(int y = 0; y < gameStatus.length; y++){
			for(int x = 0; x < gameStatus[0].length; x++){
				ret += gameStatus[x][y] + "  ";
				if(x == 7) ret+= "\n";
			}
		}
		return ret;
	}

}
