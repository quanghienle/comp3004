package simpleBlackJack;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;


public class Deck {

    private List<Card> deck;

    //constructor
    public Deck() {
        //deck stores 52 cards as a linkedlist
        this.deck = new LinkedList<Card>();
    }

    //generates all possible combinations of 4 suits and 13 ranks
    public void generateWholeDeck(){
        for(String r: Card.Rank) {
            for (String s: Card.Suit) {
                this.deck.add(new Card(s + r));
            }
        }

    }

    //generates and deck with given cards
    public void generateGivenCards(List<String> cards){

        //initialize cards of a deck with given cards
        for(int i=0; i< cards.size(); i++ ) {
            this.deck.add(new Card(cards.get(i)));
        }

    }


    //shuffle the deck
    public void deckShuffle(){
        //Shuffle the whole deck
        Collections.shuffle(this.deck);
    }

    //get number of cards in the deck
    public int getNumCards(){
        return this.deck.size();
    }

    //get deck: list of cards
    public List<Card> getDeck(){
        return this.deck;
    }

    //withdraw the first card from the deck
    public Card drawCard(){
        //return the first card if deck has more than 0 cards
        return getNumCards() != 0 ? this.deck.remove(0) : null;
    }

    //output the whole deck
    @Override
    public String toString(){
        String cards = "";

        for(int i=0; i < this.deck.size(); i++){
            cards += "[" +this.deck.get(i) + "]  ";
        }

        return cards;
    }
}
