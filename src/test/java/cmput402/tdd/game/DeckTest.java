package cmput402.tdd.game;



        import cmput402.tdd.App;
        import cmput402.tdd.User;
        import org.junit.Test;

        import java.io.ByteArrayInputStream;

        import static org.junit.Assert.*;
        import static org.mockito.Mockito.mock;


public class DeckTest {





    @Test
    public void testDeck() {


        Deck new_deck = new Deck();
        Card new_card = new_deck.drawCard();

        for (int i = 0; i < 52; i++){
            assert(new_card.getCardNumber() != 0);
        }

    }


    @Test

    public void testDrawCard(){
        Deck new_deck = new Deck();

        Card tempCard;


        for (int i = 0; i < 52; i++){


            tempCard = new_deck.drawCard();
            assert(tempCard.getCardNumber() > 0);
            assert(tempCard.getCardNumber() < 14);

        }
    }


    @Test

    public void testUniqueCard(){


        Deck new_deck  = new Deck();
        Card tempCard;

        int totalCount[] = new int[13];


        for (int i = 0; i < 52; i++){
            tempCard = new_deck.drawCard();
            totalCount[tempCard.getCardNumber() - 1]++; // include 1 in 0 spot
        }

        for (int i = 0; i <13; i++){
            assert(totalCount[i] < 4);
        }




    }


}