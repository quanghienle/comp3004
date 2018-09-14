package simpleBlackJack;

import static org.junit.Assert.*;

public class HandTest {

    public void testAddCard(){
        Hand user = new Hand();
        Card ex = new Card("DQ");
        user.addCard(ex);

        assertTrue(user.getCard(0).equals(ex));
    }

    public void testScore(){
        Hand user = new Hand();

        Card card_1 = new Card("DQ");
        Card card_2 = new Card("H10");
        Card card_3 = new Card("CA");

        user.addCard(card_1);
        user.addCard(card_2);

        assertEquals(20, user.getScore());

        user.addCard(card_3);
        //this ace will act as 1
        assertEquals(21, user.getScore());

    }

    public void testBusted(){
        Hand user = new Hand();

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