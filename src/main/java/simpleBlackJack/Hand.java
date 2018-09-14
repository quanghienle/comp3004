package simpleBlackJack;

import java.util.List;
import java.util.ArrayList;

public class Hand {

    private List<Card> hand;
    private boolean busted;

    public Hand(){
        this.hand = new ArrayList<Card>();
        this.busted = false;
    }

    public Card getCard(int index) {
        return (index >= 0 && index < this.hand.size()) ? this.hand.get(index) : null;
    }

    public boolean addCard(Card c){
        return (!bustCheck()) ? this.hand.add(c) : false;
    }


    public int getScore(){
        int score = 0;
        int totalCards = this.hand.size();

        //pre-calculate total score assuming that the hand is not busted
        //if the hand contains any A's, they will count as 11.
        if(totalCards != 0){
            for(int i=0; i<totalCards; i++){
                score += this.hand.get(i).getCardValue(false);
            }
        }

        //re-checking id the hand is busted
        //then if the hand contains any A's, they will count as 1.
        if(score > 21){
            for(int i=0; i<totalCards; i++){
                score += this.hand.get(i).getCardValue(true);
            }
        }

        //last check if it busts
        busted = (score>21) ? true : false;

        return score;
    }

    public boolean bustCheck(){
        return this.busted;
    }
}

