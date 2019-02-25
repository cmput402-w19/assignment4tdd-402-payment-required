package cmput402.tdd.game;

import cmput402.tdd.User;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class BlackJackGameTest{

	@Test
	public void testDrawCard(){
		ArrayList<Card> fakeHand = new ArrayList<Card>();

		BlackJackGame game = new BlackJackGame();

		Card card_four = new Card(4);
		Card card_seven = new Card(7);

		game.addCard(card_four);
		game.addCard(card_seven);

		game.drawCard();


		fakeHand = game.getHand();

		assert(fakeHand.get(0).retunCardNumber() == 4);
		assert(fakeHand.get(1).retunCardNumber() == 7);
		assert(fakeHand.size() == 3);
		assert(fakeHand.get(2).retunCardNumber() > 0);
		assert(fakeHand.get(2).retunCardNumber() <= 10);
	}

	@Test
	public void testDrawCardBust(){
		ArrayList<Card> fakeHand = new ArrayList<Card>();

		BlackJackGame game = new BlackJackGame();
		game.user = new User();


		Card card_ten = new Card(10);
		Card card_nine = new Card(9);
		Card card_two = new Card(2);


		game.addCard(card_ten);
		game.addCard(card_nine);
		game.addCard(card_two);
		game.drawCard();
		fakeHand = game.getHand();

		assert(fakeHand.get(0).retunCardNumber() == 10);
		assert(fakeHand.get(1).retunCardNumber() == 9);
		assert(fakeHand.get(2).retunCardNumber() == 2);
		assert(fakeHand.size() == 4);
		assert(fakeHand.get(3).retunCardNumber() > 0);
		assert(fakeHand.get(3).retunCardNumber() <= 10);
		
		int winner = game.compareHands();
		assert(winner == -1);
	}

	@Test
	public void testDealerDraws(){
		ArrayList<Card> fakeHand = new ArrayList<Card>();

		BlackJackGame game = new BlackJackGame();
		game.user = new User();


		Card card_ten = new Card(11);
		Card card_nine = new Card(10);



		game.addCard(card_ten);
		game.addCard(card_nine);





		int winner = game.compareHands();
		

		if(winner ==1) {
			assert (game.getDealerHandValue() > 21);
		}
		else{
			assert( game.getDealerHandValue() == 21);
		}

	}

	@Test
	public void testStartingHand(){
		BlackJackGame game = new BlackJackGame();
		ArrayList<Card> fakeHand = new ArrayList<Card>();
		game.resetHand();
		fakeHand = game.getHand();
		assert(fakeHand.size() == 2);
	}

	@Test
	public void testGetTotalHandValue(){
		BlackJackGame game = new BlackJackGame();

		game.addCard(new Card(10));
		game.addCard(new Card(9));
		game.addCard(new Card(2));
		int totalHandValue = game.getTotalHandValue();

		assert(totalHandValue == 21);
	}

	@Test
	public void testRestartHand(){
		BlackJackGame game = new BlackJackGame();
		game.resetHand();
		assert(game.getHand().size() == 2);

		game.addCard(new Card(3));
		assert(game.getHand().size() == 3);

		game.resetHand();
		assert(game.getHand().size() == 2);
	}

	@Test
	public void testWinCompareHands(){
		BlackJackGame game = spy(new BlackJackGame());
		game.user = new User();
		game.addCard(new Card(10));
		game.addCard(new Card(8));
		when(game.getDealerHand()).thenReturn(17);
		int winner = game.compareHands();
		verify(game).getDealerHand();
		assert(winner == 1);
	}

	@Test
	public void testTieCompareHands(){
		BlackJackGame game = spy(new BlackJackGame());
		game.addCard(new Card(10));
		game.addCard(new Card(7));
		when(game.getDealerHand()).thenReturn(17);
		int winner = game.compareHands();
		verify(game).getDealerHand();
		assert(winner == 0);
	}

	@Test
	public void testLoseCompareHands(){
		BlackJackGame game = spy(new BlackJackGame());
		game.addCard(new Card(10));
		game.addCard(new Card(6));
		when(game.getDealerHand()).thenReturn(17);
		int winner = game.compareHands();
		verify(game).getDealerHand();
		assert(winner == -1);
	}
	
	@Test
    	public void testPlayGameStayAndWin(){
		String input = "2";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		BlackJackGame game = spy(new BlackJackGame());
		game.user = new User();
		game.addCard(new Card(10));
		game.addCard(new Card(6));
		game.addCard(new Card(5));
		when(game.getDealerHand()).thenReturn(17);

		game.playGame();
	}

	@Test
    	public void testPlayGameStayAndTie(){
		String input = "2";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		BlackJackGame game = spy(new BlackJackGame());
		game.addCard(new Card(10));
		game.addCard(new Card(7));
		when(game.getDealerHand()).thenReturn(17);

		game.playGame();
	}


	@Test
    	public void testPlayGameDrawOver21(){
		String input = "1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		BlackJackGame game = spy(new BlackJackGame());
		game.addCard(new Card(10));
		game.addCard(new Card(6));
		game.addCard(new Card(5));
		when(game.getDealerHand()).thenReturn(17);

		game.playGame();
	}

}
