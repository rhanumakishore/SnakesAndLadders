package main.java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SnakeAndLadderGame {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		List<String> configList = getBoardConfiguration();
		GameConstructs constructs = new GameConstructs();
		
		constructs.setNoOfSquares(Integer.parseInt(configList.get(0)));
		constructs.setNoOfPlayers(Integer.parseInt(configList.get(1)));
		
		List<String> snakeConfigList = configList.stream().filter(s -> s.startsWith("S")).collect(Collectors.toList());
		List<String> ladderConfigList = configList.stream().filter(s -> s.startsWith("L")).collect(Collectors.toList());
		List<String> memorySquareConfigList = configList.stream().filter(s -> s.startsWith("ME")).collect(Collectors.toList());
		List<String> magicSquareConfigList = configList.stream().filter(s -> s.startsWith("MA")).collect(Collectors.toList());
		List<String> trampolineConfigList = configList.stream().filter(s -> s.startsWith("T")).collect(Collectors.toList());
		List<String> elevatorConfigList = configList.stream().filter(s -> s.startsWith("E")).collect(Collectors.toList());
		List<String> pitstopConfigList = configList.stream().filter(s -> s.startsWith("P")).collect(Collectors.toList());
		
		
		Iterator snakeIter = snakeConfigList.iterator();
		List<Snake> snakes = new ArrayList<Snake>();
		while(snakeIter.hasNext())
		{
			String snakePosition = (String) snakeIter.next();
			String[] snakeArr = snakePosition.split(" ");
			Snake snake = new Snake(); 
			snake.setSnakeStartPoint(Integer.parseInt(snakeArr[1]));
			snake.setSnakeEndPoint(Integer.parseInt(snakeArr[2]));
			snakes.add(snake);
		}
		
		Iterator ladderIter = ladderConfigList.iterator();
		List<Ladder> ladders = new ArrayList<Ladder>();
		while(ladderIter.hasNext())
		{
			String ladderPosition = (String) ladderIter.next();
			String[] ladderArr = ladderPosition.split(" ");
			Ladder ladder = new Ladder(); 
			ladder.setLadderBase(Integer.parseInt(ladderArr[1]));
			ladder.setLadderTop(Integer.parseInt(ladderArr[2]));
			ladders.add(ladder);
		}
		
		Iterator memSquareIter = memorySquareConfigList.iterator();
		List<Integer> memorySquares = new ArrayList<Integer>();
		while(memSquareIter.hasNext())
		{
			String memSquarePosition = (String) memSquareIter.next();
			String[] memSqArr = memSquarePosition.split(" ");
			memorySquares.add(Integer.parseInt(memSqArr[1]));
		}
		constructs.setMemorySquares(memorySquares);
		
		Iterator magSquareIter = magicSquareConfigList.iterator();
		List<Integer> magicSquares = new ArrayList<Integer>();
		while(magSquareIter.hasNext())
		{
			String magSquarePosition = (String) magSquareIter.next();
			String[] magSqArr = magSquarePosition.split(" ");
			magicSquares.add(Integer.parseInt(magSqArr[1]));
		}
		constructs.setMagicSquares(magicSquares);
		
		Iterator trampolineIter = trampolineConfigList.iterator();
		List<Integer> trampolines = new ArrayList<Integer>();
		while(trampolineIter.hasNext())
		{
			String trampolinePosition = (String) trampolineIter.next();
			String[] trampolineArr = trampolinePosition.split(" ");
			trampolines.add(Integer.parseInt(trampolineArr[1]));
		}
		constructs.setTrampolines(trampolines);
		
		Iterator elevatorIter = elevatorConfigList.iterator();
		List<Integer> elevators = new ArrayList<Integer>();
		while(elevatorIter.hasNext())
		{
			String elevatorPosition = (String) elevatorIter.next();
			String[] elevatorArr = elevatorPosition.split(" ");
			elevators.add(Integer.parseInt(elevatorArr[1]));
		}
		constructs.setElevators(elevators);
		
		Iterator pitstopIter = pitstopConfigList.iterator();
		List<Integer> pitstops = new ArrayList<Integer>();
		while(pitstopIter.hasNext())
		{
			String pitstopPosition = (String) pitstopIter.next();
			String[] pitstopArr = pitstopPosition.split(" ");
			pitstops.add(Integer.parseInt(pitstopArr[1]));
		}
		constructs.setPitstops(pitstops);
		
		
		
		
		System.out.println("[1:1], [2:1]");
		int player1Position = 1;
		int player2Position = 1;
		do
		{
			BufferedReader playerDice1 = new BufferedReader (new InputStreamReader (System.in));
			System.out.print ("Dice Throw for Player 1 : "); 
			int pDice1 = Integer.parseInt(playerDice1.readLine());
			player1Position = player1Position + pDice1;
			
			Iterator snakeIterator1 = snakes.iterator();
			while(snakeIterator1.hasNext())
			{
				Snake snake = (Snake) snakeIterator1.next();
				if(player1Position == snake.getSnakeStartPoint())
				{
					player1Position = snake.getSnakeEndPoint();
				}
			}
			Iterator ladderIterator1 = ladders.iterator();
			while(ladderIterator1.hasNext())
			{
				Ladder ladder = (Ladder) ladderIterator1.next();
				if(player1Position == ladder.getLadderBase())
				{
					player1Position = ladder.getLadderTop();
				}
			}
			
			if(player1Position > constructs.getNoOfSquares())
			{
				player1Position = player1Position - pDice1;
			}
			else if(player1Position == constructs.getNoOfSquares())
			{
				System.out.println("**********Player 1 WON**************");
				break;
			}
			System.out.println("[2:"+player2Position+"],[1:"+player1Position+"]");
			System.out.print ("Dice Throw for Player 2 : "); 
			BufferedReader playerDice2 = new BufferedReader (new InputStreamReader (System.in));
			int pDice2 = Integer.parseInt(playerDice2.readLine());
			player2Position = player2Position + pDice2;
			
			Iterator snakeIterator2 = snakes.iterator();
			while(snakeIterator2.hasNext())
			{
				Snake snake = (Snake) snakeIterator2.next();
				if(player2Position == snake.getSnakeStartPoint())
				{
					player2Position = snake.getSnakeEndPoint();
				}
			}
			Iterator ladderIterator2 = ladders.iterator();
			while(ladderIterator2.hasNext())
			{
				Ladder ladder = (Ladder) ladderIterator2.next();
				if(player2Position == ladder.getLadderBase())
				{
					player2Position = ladder.getLadderTop();
				}
			}
			
			if(player2Position > constructs.getNoOfSquares())
			{
				player2Position = player2Position - pDice2;
			}
			else if(player2Position == constructs.getNoOfSquares())
			{
				System.out.println("**********Player 2 WON***************");
				break;
			}
			System.out.println("[1:"+player1Position+"],[2:"+player2Position+"]");
		}while(player1Position <= constructs.getNoOfSquares() || player2Position <= constructs.getNoOfSquares());
		
		/*List<Player> players = new ArrayList<Player>();
		for(int i=1; i<=constructs.getNoOfPlayers(); i++)
		{
			Player player = new Player();
			players.add(player);
		}
		
		int playerPosition = 1;
		int playerNo = 1;
		HashMap<Integer, Integer> playerPositionsMap = new HashMap<Integer, Integer>();
		for(Player player : players)
		{
			System.out.print ("Dice Throw for Player " + playerNo +  " : ");
			BufferedReader playerDice = new BufferedReader (new InputStreamReader (System.in));
			int pDice = Integer.parseInt(playerDice.readLine());
			playerPosition = playerPosition + pDice;
			player.setPlayerPosition(playerPosition);
			
			if(playerPosition > constructs.getNoOfSquares())
			{
				playerPosition = playerPosition - pDice;
			}
			else if(playerPosition == constructs.getNoOfSquares())
			{
				System.out.println("**********Player "+ playerNo +" WON***************");
				break;
			}
			
			playerPositionsMap.put(playerNo, playerPosition);
			playerNo++;
		}*/
		
	}

	private static List<String> getBoardConfiguration() {
		System.out.println("Enter Board Configuration. Enter empty line when finished.");
		Scanner inputConfig = new Scanner(System.in);
        List<String> configurations = new ArrayList<String>();
        String lineNew;

        while (inputConfig.hasNextLine()) {
            lineNew = inputConfig.nextLine();
            if (lineNew.isEmpty()) {
                break;
            }
            //System.out.println(lineNew);
            configurations.add(lineNew);
        }

        return configurations;
	}

}
