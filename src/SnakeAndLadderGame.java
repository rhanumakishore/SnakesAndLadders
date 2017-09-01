import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class SnakeAndLadderGame {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader squaresInput = new BufferedReader (new InputStreamReader (System.in));
		System.out.print ("Enter number of squares :  "); 
		int noOfSquares = Integer.parseInt(squaresInput.readLine());
		
		BufferedReader playersInput = new BufferedReader (new InputStreamReader (System.in));
		System.out.print ("Enter number of players :  "); 
		String noOfPlayers = playersInput.readLine(); 
		
		BufferedReader snakePos = new BufferedReader (new InputStreamReader (System.in));
		System.out.print ("Enter snake starting point and ending point, with hunger level : "); 
		String snakePosition = snakePos.readLine();
		
		BufferedReader ladderPos = new BufferedReader (new InputStreamReader (System.in));
		System.out.print ("Enter ladder starting point and ending point, with hunger level : "); 
		String ladderPosition = ladderPos.readLine();
		
		BufferedReader memSq = new BufferedReader (new InputStreamReader (System.in));
		System.out.print ("Enter memory square position : "); 
		String memPosition = memSq.readLine();
		
		BufferedReader magSq = new BufferedReader (new InputStreamReader (System.in));
		System.out.print ("Enter magic square position : "); 
		String magPosition = magSq.readLine();
		
		BufferedReader tramp = new BufferedReader (new InputStreamReader (System.in));
		System.out.print ("Enter trampoline location : "); 
		String trampolinePosition = tramp.readLine();
		
		BufferedReader elev = new BufferedReader (new InputStreamReader (System.in));
		System.out.print ("Enter elevator location : "); 
		String elevatorPosition = elev.readLine();
		
		BufferedReader pit = new BufferedReader (new InputStreamReader (System.in));
		System.out.print ("Enter pitstop location : "); 
		String pitstopPosition = pit.readLine();
		
		System.out.println("No of squares : "+noOfSquares);
		System.out.println("No of players : "+noOfPlayers);
		System.out.println("Snake Position : "+snakePosition);
		System.out.println("Ladder Position : "+ladderPosition);
		
		String[] snakeArr = snakePosition.split(" ");
		Snake snake = new Snake();
		snake.setSnakeStartPoint(Integer.parseInt(snakeArr[1]));
		snake.setSnakeEndPoint(Integer.parseInt(snakeArr[1]));
		snake.setSnakeHungerLevel(snakeArr[3]);
		
		
		System.out.println("Snake Starting Point :- "+snake.getSnakeStartPoint());
		System.out.println("Snake Ending Point :- "+snake.getSnakeEndPoint());
		System.out.println("Snake Hunger Level :- "+snake.getSnakeHungerLevel());
		
		String[] ladderArr = ladderPosition.split(" ");
		Ladder ladder = new Ladder();
		ladder.setLadderBase(Integer.parseInt(ladderArr[1]));
		ladder.setLadderTop(Integer.parseInt(ladderArr[2]));
		
		System.out.println("Ladder Base :- "+ladder.getLadderBase());
		System.out.println("Ladder Top :- "+ladder.getLadderTop());
		
		System.out.println("[1:1], [2:1]");
		int player1Position = 1;
		int player2Position = 1;
		do
		{
			BufferedReader playerDice1 = new BufferedReader (new InputStreamReader (System.in));
			System.out.print ("Dice Throw for Player 1 : "); 
			int pDice1 = Integer.parseInt(playerDice1.readLine());
			player1Position = player1Position + pDice1;
			if(player1Position==snake.getSnakeStartPoint())
			{
				player1Position = snake.getSnakeEndPoint();
			}
			else if(player1Position == ladder.getLadderBase())
			{
				player1Position = ladder.getLadderTop();
			}
			if(player1Position > noOfSquares)
			{
				player1Position = player1Position - pDice1;
			}
			else if(player1Position == noOfSquares)
			{
				System.out.println("**********Player 1 WON**************");
				break;
			}
			System.out.println("[2:"+player2Position+"],[1:"+player1Position+"]");
			System.out.print ("Dice Throw for Player 2 : "); 
			BufferedReader playerDice2 = new BufferedReader (new InputStreamReader (System.in));
			int pDice2 = Integer.parseInt(playerDice2.readLine());
			player2Position = player2Position + pDice2;
			if(player2Position==snake.getSnakeStartPoint())
			{
				player2Position = snake.getSnakeEndPoint();
			}
			else if(player2Position == ladder.getLadderBase())
			{
				player2Position = ladder.getLadderTop();
			}
			if(player2Position > noOfSquares)
			{
				player2Position = player2Position - pDice2;
			}
			else if(player2Position == noOfSquares)
			{
				System.out.println("**********Player 2 WON***************");
				break;
			}
			System.out.println("[1:"+player1Position+"],[2:"+player2Position+"]");
		}while(player1Position <= noOfSquares || player2Position <= noOfSquares);
		
	}

}
