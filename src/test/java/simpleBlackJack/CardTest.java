package simpleBlackJack;

import static org.junit.Assert.*;

public class CardTest {

    public void testCardValue(){
        Card card_1 = new Card("SA");
        Card card_2 = new Card("H5");
        Card card_3 = new Card("D10");
        Card card_4 = new Card("CK");

        assertEquals(1,card_1.getCardValue(true));
        assertEquals(11,card_1.getCardValue(false));
        assertEquals(5,card_2.getCardValue(false));
        assertEquals(10,card_3.getCardValue(false));
        assertEquals(10,card_4.getCardValue(false));
    }

    public void testCardSuit(){
        Card card_1 = new Card("SA");
        Card card_2 = new Card("H5");

        assertEquals("S",card_1.getSuit());
        assertEquals("H",card_2.getSuit());

    }

    public void testCardRank(){

        Card card_3 = new Card("D10");
        Card card_4 = new Card("CK");

        assertEquals("10",card_3.getRank());
        assertEquals("k",card_4.getRank());
    }
}