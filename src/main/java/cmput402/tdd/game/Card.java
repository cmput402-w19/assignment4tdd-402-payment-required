package cmput402.tdd.game;


public class Card {



    private int cardNumber;



    public enum number {
        ace, two, three, four, five, six, seven, eight, nine, jack, queen, king
    }


    // constructor method
    public Card (int number){

        this.cardNumber  = number;


    }



    // Return the number of the card
    public int retunCardNumber(){


       return this.cardNumber;

    }

}
