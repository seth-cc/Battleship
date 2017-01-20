import java.util.Scanner;
public class Test {

	public static void main(String[] args) {
		Board test = new Board();
		
		System.out.println(test);
		
		System.out.println(test.viewGameState());
		
		Scanner key = new Scanner(System.in);
		int irr = 0;
		boolean test2 = false;
		while(test2 == false){
			String acoord = key.next();
			irr = test.playerShot(acoord);
			irr = test.computerShot();
			System.out.println(test);
			
			System.out.println(test.viewGameState());
			
		}
		

		
	
	}

}
