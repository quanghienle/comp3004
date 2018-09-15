package simpleBlackJack;

import java.util.List;
import java.util.ArrayList;

public class Hand {

    private List<Card> hand;

    public Hand(){
        this.hand = new ArrayList<Card>();
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

        return score;
    }

    public boolean blackJack(){
        String rank1 = this.getCard(0).getRank();
        String rank2 = this.getCard(1).getRank();

        //after this step, hand has to have an A
        if(!rank1.equals("A") && !rank2.equals("A")){
            return false;
        }
        //in this step, hand must have a J, Q, or K
        if(rank1.equals("J") || rank1.equals("Q") || rank1.equals("K") ||
                rank2.equals("J") || rank2.equals("Q") || rank2.equals("K")){
            return true;
        }
        return false;

    }

    public boolean bustCheck(){
        int score = this.getScore();
        return (score>21) ? true : false;
    }

    @Override
    public String toString(){
        String cards = "";

        for(int i=0; i < this.hand.size(); i++){
            cards += "[" + this.getCard(i) + "]  ";
        }

        return cards;
    }
}

