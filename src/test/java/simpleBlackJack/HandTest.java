package simpleBlackJack;

import static org.junit.Assert.*;

public class HandTest {

    public void testAddCard(){
        Hand user = new Hand("Player");
        Card ex = new Card("DQ");
        user.addCard(ex);

        assertEquals(user.getCard(0), ex);
    }

    public void testScore(){
        Hand user1 = new Hand("Player");
        Hand user2 = new Hand("Player");
        Hand user3 = new Hand("Player");


        Card card_1 = new Card("DQ");
        Card card_2 = new Card("H8");
        Card card_3 = new Card("CA");
        Card card_4 = new Card("DA");

        //A as 11
        user1.addCard(card_1);
        user1.addCard(card_3);

        assertEquals(21, user1.getScore());

        //A as 1
        user2.addCard(card_1);
        user2.addCard(card_2);
        user2.addCard(card_3);

        assertEquals(19, user1.getScore());

        //A as 11 and 1
        user3.addCard(card_1);
        user3.addCard(card_2);
        user3.addCard(card_3);
        user3.addCard(card_4);

        assertEquals(20, user1.getScore());


    }

    public void testBlackjack(){
        Hand user = new Hand("Player");
        Hand user2 = new Hand("Player");

        Card card_1 = new Card("DQ");
        Card card_2 = new Card("H10");
        Card card_3 = new Card("CA");

        //not a blackjack
        user.addCard(card_1);
        user.addCard(card_2);

        assertFalse(user.hasBlackjack());

        ///has a blackjack
        user2.addCard(card_1);
        user2.addCard(card_3);

        assertTrue(user2.hasBlackjack());
    }

    public void testBusted(){
        Hand user = new Hand("Player");

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