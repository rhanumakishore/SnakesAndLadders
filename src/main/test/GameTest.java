package main.test;

import java.util.Arrays;

import org.junit.Test;

import main.java.Game;
import main.java.GameConstructs;
import main.java.Ladder;
import main.java.Snake;

import static org.junit.Assert.assertEquals;

public class GameTest {
	
	GameConstructs gameConstructs = null;
	Game testGame = new Game();
	int actualplayerPosition = 0;
	int expectedPlayerPosition = 0;
	
	@Test
	public void test_computePlayerNormalPosition()
	{
		givenGameConstructs();
		whenComputePlayerNormalPositionIsCalled();
		thenVerifyPlayerNormalPosition();
		
	}
	
	@Test
	public void test_computePlayerMagicPosition()
	{
		givenGameConstructs();
		whenComputePlayerMagicPositionIsCalled();
		thenVerifyPlayerMagicPosition();
		
	}

	private void thenVerifyPlayerMagicPosition() {
		expectedPlayerPosition = 27;
		assertEquals(expectedPlayerPosition, actualplayerPosition);
		
	}

	private void whenComputePlayerMagicPositionIsCalled() {
		actualplayerPosition = testGame.computePlayerPosition(gameConstructs, 7, 6, true);
		
	}

	private void thenVerifyPlayerNormalPosition() {
		expectedPlayerPosition = 7;
		assertEquals(expectedPlayerPosition, actualplayerPosition);
		
	}

	private void whenComputePlayerNormalPositionIsCalled() {
		actualplayerPosition = testGame.computePlayerPosition(gameConstructs, 7, 6, false);
		
	}

	private void givenGameConstructs() {
		gameConstructs = new GameConstructs();
		gameConstructs.setNoOfSquares(64);
		gameConstructs.setNoOfPlayers(2);
		gameConstructs.setMagicSquares(Arrays.asList(15,25,35,45));
		gameConstructs.setMemorySquares(Arrays.asList(20,20,40,50,60));
		gameConstructs.setTrampolines(Arrays.asList(22,32,42,52,62));
		gameConstructs.setElevators(Arrays.asList(23,33,43,53));
		Snake snake1 = new Snake();
		snake1.setSnakeStartPoint(16);
		snake1.setSnakeEndPoint(6);
		Snake snake2 = new Snake();
		snake2.setSnakeStartPoint(27);
		snake2.setSnakeEndPoint(7);
		gameConstructs.setSnakeList(Arrays.asList(snake1, snake2));
		Ladder ladder1 = new Ladder();
		ladder1.setLadderBase(8);
		ladder1.setLadderTop(28);
		Ladder ladder2 = new Ladder();
		ladder2.setLadderBase(18);
		ladder2.setLadderTop(38);
		gameConstructs.setLadderList(Arrays.asList(ladder1, ladder2));
		
		
	}

}
