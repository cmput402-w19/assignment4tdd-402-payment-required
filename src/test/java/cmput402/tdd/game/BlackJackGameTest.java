package cmput402.tdd.game;

import cmput402.tdd.game.BlackJackGame;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

public class BlackJackGameTest{

	@Test
	public void testDrawCard(){
		ArrayList<Integer> fakeHand = new ArrayList<Integer>();

		BlackJackGame game = new BlackJackGame();

		game.addCard(4);
		game.addCard(7);
		game.drawCard();
		fakeHand = game.getHand();

		assert(fakeHand.get(0) == 4);
		assert(fakeHand.get(1) == 7);
		assert(fakeHand.size() == 3);
		assert(fakeHand.get(2) > 0);
		assert(fakeHand.get(2) < 10);
	}

	@Test
	public void testStartingHand(){
		BlackJackGame game = new BlackJackGame();
		ArrayList<Integer> fakeHand = new ArrayList<Integer>();
		fakeHand = game.getHand();
		assert(fakeHand.size() == 0);
	}

	@Test
	public void testRestartHand(){
		BlackJackGame game = new BlackJackGame();
	
		assert(game.getHand().size() == 0);

		game.addCard(3);
		assert(game.getHand().size() == 1);

		game.resetHand();
//		assert(game.getHand().size() == 0);
	}

	@Test
	public void testWinCompareHands(){
		BlackJackGame game = mock(BlackJackGame.class);
		game.addCard(8);
		game.addCard(10);
		when(game.getDealerHand()).thenReturn(17);
		int winner = game.compareHands();
		assert(winner == 1);
	}

	@Test
	public void testTieCompareHands(){
		BlackJackGame game = mock(BlackJackGame.class);
		game.addCard(7);
		game.addCard(10);
		when(game.getDealerHand()).thenReturn(17);
		int winner = game.compareHands();
		assert(winner == 0);
	}

	@Test
	public void testLoseCompareHands(){
		BlackJackGame game = mock(BlackJackGame.class);
		game.addCard(6);
		game.addCard(10);
		when(game.getDealerHand()).thenReturn(17);
		int winner = game.compareHands();
		assert(winner == -1);
	}
}
