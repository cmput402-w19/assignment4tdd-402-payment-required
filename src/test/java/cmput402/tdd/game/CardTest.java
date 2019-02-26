package cmput402.tdd.game;



import cmput402.tdd.App;
import cmput402.tdd.User;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CardTest {



    @Test
    public void testCard() {

        Card new_card = new Card(5);
        int test_value = new_card.getCardNumber();

        assert(5 == test_value);

    }



}