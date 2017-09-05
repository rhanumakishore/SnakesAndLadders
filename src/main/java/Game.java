package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game {
	
	public void play() throws NumberFormatException, IOException
	{
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
		constructs.setSnakeList(snakes);
		
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
		constructs.setLadderList(ladders);
		
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
		boolean isMagicPlayEnabledForPlayer1 = false;
		boolean isMagicPlayEnabledForPlayer2 = false;
		List<Integer> player1Turns = new LinkedList<Integer>();
		List<Integer> player2Turns = new LinkedList<Integer>();
		do
		{
			BufferedReader playerDice1 = new BufferedReader (new InputStreamReader (System.in));
			System.out.print ("Dice Throw for Player 1 : "); 
			int pDice1 = Integer.parseInt(playerDice1.readLine());
			player1Position = player1Position + pDice1;
			
			if(constructs.getMemorySquares().contains(player1Position))
			{
				if(pDice1 < player1Turns.size())
				{
					player1Position = player1Turns.get(pDice1 - 1);
				}
				else
				{
					player1Position = 1;
				}
			}
			
			if(constructs.getMagicSquares().contains(player1Position))
			{
				if(isMagicPlayEnabledForPlayer1)
				{
					isMagicPlayEnabledForPlayer1 = false;
					System.out.println("Magic Play disabled for Player 1");
				}
				else
				{
					isMagicPlayEnabledForPlayer1 = true;
					System.out.println("Magic Play enabled for Player 1");
				}
			}
			
			player1Position = computePlayerPosition(constructs, player1Position, pDice1, isMagicPlayEnabledForPlayer1);
			
			
			if(player1Position > constructs.getNoOfSquares())
			{
				player1Position = player1Position - pDice1;
			}
			else if(player1Position == constructs.getNoOfSquares())
			{
				System.out.println("**********Player 1 WON**************");
				break;
			}
			player1Turns.add(player1Position);
			System.out.println("[2:"+player2Position+"],[1:"+player1Position+"]");
			System.out.print ("Dice Throw for Player 2 : "); 
			BufferedReader playerDice2 = new BufferedReader (new InputStreamReader (System.in));
			int pDice2 = Integer.parseInt(playerDice2.readLine());
			player2Position = player2Position + pDice2;
			
			if(constructs.getMemorySquares().contains(player2Position))
			{
				if(pDice2 < player2Turns.size())
				{
					player2Position = player2Turns.get(pDice2 - 1);
				}
				else
				{
					player2Position = 1;
				}
			}
			
			if(constructs.getMagicSquares().contains(player2Position))
			{
				if(isMagicPlayEnabledForPlayer2)
				{
					isMagicPlayEnabledForPlayer2 = false;
					System.out.println("Magic Play disabled for Player 2");
				}
				else
				{
					isMagicPlayEnabledForPlayer2 = true;
					System.out.println("Magic Play enabled for Player 2");
				}
			}
			player2Position = computePlayerPosition(constructs, player2Position, pDice2, isMagicPlayEnabledForPlayer2);
			
			if(player2Position > constructs.getNoOfSquares())
			{
				player2Position = player2Position - pDice2;
			}
			else if(player2Position == constructs.getNoOfSquares())
			{
				System.out.println("**********Player 2 WON***************");
				break;
			}
			player2Turns.add(player2Position);
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
	
	private int computePlayerPosition(GameConstructs constructs, int playerPosition, int pDice, boolean isMagicPlayEnabled) {
		if(isMagicPlayEnabled)
		{
			playerPosition = computePlayerMagicPosition(constructs, playerPosition, pDice);
		}
		else
		{
			playerPosition = computePlayerNormalPosition(constructs, playerPosition, pDice);
		}
		return playerPosition;
	}

	private int computePlayerNormalPosition(GameConstructs constructs, int playerPosition, int pDice) {
		if(constructs.getTrampolines().contains(playerPosition))
		{
			int newPosition = playerPosition + pDice;
			if(newPosition < constructs.getNoOfSquares())
			{
				playerPosition = newPosition;
			}
		}
		else if(constructs.getElevators().contains(playerPosition))
		{
			int newPosition = playerPosition + (pDice * 4);
			if(newPosition < constructs.getNoOfSquares())
			{
				playerPosition = newPosition;
			}
		}
		
		Iterator snakeIterator = constructs.getSnakeList().iterator();
		while (snakeIterator.hasNext()) 
		{
			Snake snake = (Snake) snakeIterator.next();
			if (playerPosition == snake.getSnakeStartPoint()) 
			{
				playerPosition = snake.getSnakeEndPoint();
			}
		}
		Iterator ladderIterator = constructs.getLadderList().iterator();
		while (ladderIterator.hasNext()) 
		{
			Ladder ladder = (Ladder) ladderIterator.next();
			if (playerPosition == ladder.getLadderBase()) 
			{
				playerPosition = ladder.getLadderTop();
			}
		}
		
		return playerPosition;
	}

	private int computePlayerMagicPosition(GameConstructs constructs, int playerPosition, int pDice) {
		if(constructs.getTrampolines().contains(playerPosition))
		{
			playerPosition = playerPosition - pDice;
			
		}
		else if(constructs.getElevators().contains(playerPosition))
		{
			playerPosition = playerPosition - (pDice * 4);
			if(playerPosition < 1)
			{
				playerPosition = 1;
			}
		}
		
		Iterator snakeIterator = constructs.getSnakeList().iterator();
		while (snakeIterator.hasNext()) 
		{
			Snake snake = (Snake) snakeIterator.next();
			if (playerPosition == snake.getSnakeEndPoint()) 
			{
				playerPosition = snake.getSnakeStartPoint();
			}
		}
		Iterator ladderIterator = constructs.getLadderList().iterator();
		while (ladderIterator.hasNext()) 
		{
			Ladder ladder = (Ladder) ladderIterator.next();
			if (playerPosition == ladder.getLadderTop()) 
			{
				playerPosition = ladder.getLadderBase();
			}
		}
		
		return playerPosition;
	}

	private List<String> getBoardConfiguration() {
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
