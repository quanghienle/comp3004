package simpleBlackJack;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void testCardValue(){
        Card card_1 = new Card("SA");
        Card card_2 = new Card("H5");
        Card card_3 = new Card("D10");
        Card card_4 = new Card("CK");

        assertEquals(11,card_1.getCardValue());
        card_1.setCardValue(1);
        assertEquals(1,card_1.getCardValue());
        assertEquals(5,card_2.getCardValue());
        assertEquals(10,card_3.getCardValue());
        assertEquals(10,card_4.getCardValue());
    }

    @Test
    public void testCardSuit(){
        Card card_1 = new Card("SA");
        Card card_2 = new Card("H5");

        assertEquals("S",card_1.getSuit());
        assertEquals("H",card_2.getSuit());

    }

    @Test
    public void testCardRank(){

        Card card_3 = new Card("D10");
        Card card_4 = new Card("CK");

        assertEquals("10",card_3.getRank());
        assertEquals("K",card_4.getRank());
    }

    @Test
    public void testValidCard(){
        String[] cards = {"AS", "SA", "S"};

        assertFalse(Card.checkValidCard(cards[0]));
        assertTrue(Card.checkValidCard(cards[1]));
        assertFalse(Card.checkValidCard(cards[2]));
    }
}