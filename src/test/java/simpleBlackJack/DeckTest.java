package simpleBlackJack;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void testNumCards(){

        //the whole legit deck of cards has 52 cards
        Deck myDeck = new Deck();
        myDeck.generateWholeDeck();
        assertEquals(52, myDeck.getNumCards());

        //deck with given cards
        Deck myDeck2 = new Deck();
        List<String> cards = new ArrayList<String>();
        cards.add("H5");
        cards.add("H2");
        cards.add("H4");

        myDeck2.generateGivenCards(cards);
        assertEquals(3, myDeck2.getNumCards());
    }

    @Test
    public void testShuffle(){
        Deck myDeck = new Deck();
        List<String> cards = new ArrayList<String>();

        cards.add("H6");
        cards.add("CK");
        cards.add("D3");

        myDeck.generateGivenCards(cards);


        myDeck.deckShuffle();
        Card card_1 = myDeck.getDeck().get(0);
        Card card_2 = myDeck.getDeck().get(1);
        Card card_3 = myDeck.getDeck().get(2);

        //test that they are not in the same order
        assertFalse(card_1.toString().equals("H6") && card_2.toString().equals("CK") && card_3.toString().equals("D3"));

    }

    @Test
    public void testDrawCard(){
        Deck myDeck = new Deck();
        List<String> cards = new ArrayList<String>();

        cards.add("H6");
        cards.add("CK");
        cards.add("D3");

        myDeck.generateGivenCards(cards);



        Card myCard = myDeck.drawCard();
        assertTrue(myCard.toString().equals("H6"));

        Card myCard2 = myDeck.drawCard();
        assertTrue(myCard2.toString().equals("CK"));
    }

}