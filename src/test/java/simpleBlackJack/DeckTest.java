package simpleBlackJack;

import static org.junit.Assert.*;

public class DeckTest {

    public void testNumCards(){
        Deck myDeck = new Deck();
        assertEquals(52, myDeck.getNumCards());
    }

    public void testShuffle(){
        Deck myDeck = new Deck();
        Card unshuffled_1 = myDeck.getDeck().get(10);
        Card unshuffled_2 = myDeck.getDeck().get(20);

        myDeck.deckShuffle();
        Card shuffled_1 = myDeck.getDeck().get(10);
        Card shuffled_2 = myDeck.getDeck().get(20);

        assertFalse(unshuffled_1.equals(shuffled_1) && unshuffled_2.equals(shuffled_2));

    }
    public void testDrawCard(){
        Deck myDeck = new Deck();
        myDeck.deckShuffle();

        Card firstCard = myDeck.getDeck().getFirst();
        Card cardFromDeck = myDeck.drawCard();

        assertTrue(firstCard.equals(cardFromDeck));
        assertEquals(51, myDeck.getNumCards());
    }

}