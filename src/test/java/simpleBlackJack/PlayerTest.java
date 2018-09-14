package simpleBlackJack;

import static org.junit.Assert.*;

public class PlayerTest {

    public void testAddCard(){
        Player user = new Player();
        Card ex = new Card("DQ");
        user.addCard(ex);

        assertTrue(user.getPlayerCard(0).equals(ex));
    }

    public void testScore(){
        Player user = new Player();

        Card card_1 = new Card("DQ");
        Card card_2 = new Card("H10");
        Card card_3 = new Card("CA");

        user.addCard(card_1);
        user.addCard(card_2);

        assertEquals(20, user.getScore());

        user.addCard(card_3);

        assertEquals(21, user.getScore());

    }

    public void testBusted(){
        Player user = new Player();

        Card card_1 = new Card("DQ");
        Card card_2 = new Card("H10");
        Card card_3 = new Card("C5");

        user.addCard(card_1);
        user.addCard(card_2);

        assertFalse(user.bustCheck());

        user.addCard(card_3);

        assertTrue(user.bustCheck());
    }

}