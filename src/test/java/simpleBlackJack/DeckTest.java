package simpleBlackJack;

import static org.junit.Assert.*;

public class DeckTest {

    public void testNumCards(){
        Deck myDeck = new Deck();
        myDeck.generateWholeDeck();
        assertEquals(52, myDeck.getNumCards());
    }

    public void testShuffle(){
        Deck myDeck = new Deck();
        String[] cards = {"H6", "CK", "D3"};
        myDeck.generateGivenCards(cards);



        myDeck.deckShuffle();
        Card card_1 = myDeck.getDeck().get(0);
        Card card_2 = myDeck.getDeck().get(1);
        Card card_3 = myDeck.getDeck().get(2);

        assertFalse(card_1.toString().equals("H6") && card_2.toString().equals("CK") && card_3.toString().equals("D3"));

    }
    public void testDrawCard(){
        Deck myDeck = new Deck();
        String[] cards = {"H6", "CK", "D3"};
        myDeck.generateGivenCards(cards);

        Card myCard = myDeck.drawCard();

        assertTrue(myCard.toString().equals("H6"));
        assertEquals(51, myDeck.getNumCards());
    }

}