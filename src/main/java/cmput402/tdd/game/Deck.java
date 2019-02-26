package cmput402.tdd.game;

import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.nashorn.internal.ir.WhileNode;

import java.util.Arrays;
import java.util.Random;

public class Deck {







    private Card[] deck = new Card[52];
    private boolean[] drawn = new boolean[52];
    private int y;




    public Deck(){





        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < 13; i++) {

                Card temp_card = new Card(i+1);
                this.deck[y] = temp_card;
                y++;
            }
        }



        return ;

    }

    public Card drawCard(){



        Random rand = new Random();
        int random_int = rand.nextInt(52);


        while(true) {


            while (drawn[random_int] == false) {


                drawn[random_int] = true;

                return deck[random_int];

            }

            random_int = rand.nextInt(52);
        }





    }







}