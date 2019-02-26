package cmput402.tdd.game;

import cmput402.tdd.App;
import cmput402.tdd.IntegerAsker;

import java.util.ArrayList;
import java.util.Random;

public class BlackJackGame extends Game{
	ArrayList<Card> playerHand = new ArrayList<Card>();
	ArrayList<Card> dealerHand = new ArrayList<Card>();

	int winner = 0;

	int dealerValue = 0;

	Deck deck = new Deck();

	public void playGame(){
		boolean playing = true;
		dealerDrawsHand();
		int userSelection;
		while(playing){
			String playerCardsString = "";
			for(int i = 0; i<playerHand.size(); i++){
				playerCardsString += playerHand.get(i).getCardNumber() + " ";
			}
			System.out.println("Current hand:" + playerCardsString);
			System.out.println("Total: " + getTotalHandValue());
			System.out.println("1: Draw a card");
			System.out.println("2: Stay");
			userSelection = App.getUserInput(new IntegerAsker(System.in, System.out));
			if(userSelection == 1){
				drawCard();
				if(getTotalHandValue() > 21){
					winner = compareHands();
					playing = false;
				}
			}
			if(userSelection == 2){
				winner = compareHands();
				playing = false;
			}
		}
		if(winner == 1){
			this.winBet();
			System.out.println("You won!");
		}
		if(winner == 0){
			System.out.println("Tie!");
		}
		if(winner == -1){
			System.out.println("You lost!");
		}
	}

	public ArrayList<Card> getHand(){
		return playerHand;
	}

	public int getTotalHandValue(){
		int totalValue = 0;
		for(int i = 0; i < playerHand.size();i++){
			totalValue += playerHand.get(i).getCardNumber();
		}
		return totalValue;
	}

	public void addCard(Card card){

		playerHand.add(card);

	}

	public void addDealerCard(Card card){
		dealerHand.add(card);
	}

	public void drawCard(){
		//Random rand = new Random();
		//int newCard = rand.nextInt(10)+1; // Returns 1-10
		//playerHand.add(newCard);

		playerHand.add(deck.drawCard());
	}



	public void resetHand(){
		playerHand = new ArrayList<Card>();
		drawCard();
		drawCard();
	}

	public int getDealerHand(){



		dealerValue = 0;

		for (Card card : dealerHand){
			dealerValue = dealerValue + card.getCardNumber();

		}

		return dealerValue;
	}


	public void dealerDrawsHand(){


		dealerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());

		return;
	}
	public int dealerDrawCard(){
		addDealerCard(deck.drawCard());


		return getDealerHand();
	}

	public int compareHands(){

		int totalValue = getTotalHandValue();




		while ((getDealerHand() < totalValue) && (totalValue < 22) && (dealerValue < 17)){
			dealerValue = dealerDrawCard();

		}

		int dealerHand = dealerValue;


		System.out.println("Your hand: " + totalValue);
		System.out.println("Dealer's hand: " + dealerHand);
		if(totalValue > 21){	// Bust
			return -1;
		}
		else if( dealerHand > 21){
			return 1;
		}
		else if(totalValue > dealerHand ){
			return 1;
		}
		else if(totalValue < dealerHand){
			return -1;
		}
		return 0;	//Tie
	}
}