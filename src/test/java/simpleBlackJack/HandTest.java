package simpleBlackJack;

import org.junit.Test;
import static org.junit.Assert.*;

public class HandTest {

    @Test
    public void testAddCard(){
        Hand user = new Hand("Player");
        Card ex = new Card("DQ");
        user.addCard(ex);

        assertEquals(user.getCard(0), ex);
    }
    @Test
    public void testAce1(){
        Hand user = new Hand("Player");



        Card card_1 = new Card("DQ");
        Card card_2 = new Card("H8");
        Card card_3 = new Card("CA");


        //A as 1
        user.addCard(card_1);
        user.addCard(card_2);
        user.addCard(card_3);

        assertEquals(19, user.getScore());

    }
    @Test
    public void testAce11(){
        Hand user = new Hand("Player");

        Card card_1 = new Card("DQ");
        Card card_3 = new Card("CA");

        //A as 11
        user.addCard(card_1);
        user.addCard(card_3);

        assertEquals(21, user.getScore());
    }

    @Test
    public void testAce1and11(){
        Hand user = new Hand("Player");

        Card card_2 = new Card("H8");
        Card card_3 = new Card("CA");
        Card card_4 = new Card("DA");

        //A as 1 and 11
        user.addCard(card_4);
        user.addCard(card_2);
        user.addCard(card_3);
        assertEquals(20, user.getScore());

    }

    @Test
    public void testAce11then1(){

        Hand user = new Hand("Player");

        Card card_1 = new Card("DQ");
        Card card_2 = new Card("H8");
        Card card_3 = new Card("CA");

        //A as 11 then 1
        user.addCard(card_3);
        user.addCard(card_1);
        assertEquals(21, user.getScore());

        user.addCard(card_2);
        assertEquals(19, user.getScore());

    }

    @Test
    public void test2AcesAs1(){
        Hand user = new Hand("Player");

        Card card_1 = new Card("DQ");
        Card card_2 = new Card("H8");
        Card card_3 = new Card("CA");
        Card card_4 = new Card("DA");



        //2 a's as 1
        user.addCard(card_1);
        user.addCard(card_2);
        user.addCard(card_3);
        user.addCard(card_4);

        assertEquals(20, user.getScore());
    }

    @Test
    public void testFaceCards(){
        Hand user = new Hand("Player");

        Card card_1 = new Card("DQ");
        Card card_2 = new Card("SJ");
        Card card_3 = new Card("HK");


        user.addCard(card_1);
        assertEquals(10, user.getScore());
        user.removeCard(0);

        user.addCard(card_2);
        assertEquals(10, user.getScore());
        user.removeCard(0);

        user.addCard(card_3);
        assertEquals(10, user.getScore());
    }

    @Test
    public void testPlayerBlackjack(){
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

    @Test
    public void testDealerBlackjack(){
        Hand user = new Hand("Dealer");
        Hand user2 = new Hand("Dealer");

        Card card_1 = new Card("HK");
        Card card_2 = new Card("S9");
        Card card_3 = new Card("SA");

        //not a blackjack
        user.addCard(card_1);
        user.addCard(card_2);

        assertFalse(user.hasBlackjack());

        ///has a blackjack
        user2.addCard(card_1);
        user2.addCard(card_3);

        assertTrue(user2.hasBlackjack());
    }

    @Test
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

    @Test
    //testing the first two having the same rank
    public void testIdenticalCards(){
        Hand user = new Hand("Player");
        Hand user2 = new Hand("Player");

        Card c1 = new Card("DQ");
        Card c2 = new Card("CQ");
        Card c3 = new Card("DJ");

        user.addCard(c1);
        user.addCard(c2);

        user2.addCard(c1);
        user2.addCard(c3);

        assertTrue(user.hasIdenticalCards());
        assertFalse(user2.hasIdenticalCards());
    }

    public void testHasAce(){
        Hand user = new Hand("Player");
        Hand user2 = new Hand("Player");

        Card c1 = new Card("DQ");
        Card c2 = new Card("CQ");
        Card c3 = new Card("DA");

        user.addCard(c1);
        user.addCard(c2);

        user2.addCard(c1);
        user2.addCard(c3);

        assertFalse(user.hasAce());
        assertTrue(user2.hasAce());
    }

}