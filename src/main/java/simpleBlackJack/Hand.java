package simpleBlackJack;

import java.util.List;
import java.util.ArrayList;

public class Hand {

    //cards in a hand is stored in an arraylist
    private List<Card> hand;

    //what type of user: Player or Dealer.
    private String type;

    //constructor
    public Hand(String userType){
        this.hand = new ArrayList<Card>();
        this.type = userType;
    }

    //getters
    public int getNumCards(){return this.hand.size();}
    public String getType(){
        return this.type;
    }
    public Card getCard(int index) {

        return (index >= 0 && index < this.hand.size()) ? this.hand.get(index) : null;
    }

    //add a card object to this hand
    public boolean addCard(Card c){

        return (!bustCheck()) ? this.hand.add(c) : false;
    }

    public Card removeCard(int index){
        return this.hand.remove(index);
    }

    //calculate how much this hand scores
    public int getScore(){
        int score = 0;
        int totalCards = this.hand.size();


        //if there are more than 1 A, then set first A = 11, and other A's are 1
        //just because if both A's are 11, then the score is 22 (bust).
        int numAces = 0;
        for(int i=0; i<totalCards; i++){
            if(this.getCard(i).getRank().equals("A")){
                if(numAces >= 1){
                    this.getCard(i).setCardValue(1);
                }else{
                    this.getCard(i).setCardValue(11);
                }
                numAces ++;
            }
        }

        //pre-calculate total score
        //if it does not bust, then use this score.
        if(totalCards != 0){
            for(int i=0; i<totalCards; i++){
                score += this.hand.get(i).getCardValue();
            }
        }

        //if the calculation above is over 21 (bust)
        //then if the hand contains any A's, they will all count as 1.
        if(score > 21){
            score = 0;
            for(int i=0; i<totalCards; i++){
                if(this.getCard(i).getRank().equals("A")){
                    this.getCard(i).setCardValue(1);
                }
            }

            for(int i=0; i<totalCards; i++){
                score += this.hand.get(i).getCardValue();
            }
        }

        return score;
    }

    //returns true if this hand has a blackjack. otherwise false.
    public boolean hasBlackjack(){

        String rank1 = this.getCard(0).getRank();
        String rank2 = this.getCard(1).getRank();

        //after this step, hand has to have an A
        if(!rank1.equals("A") && !rank2.equals("A")){
            return false;
        }
        //in this step, hand must have a J, Q, or K
        if(rank1.equals("J") || rank1.equals("Q") || rank1.equals("K") || rank1.equals("10") ||
                rank2.equals("J") || rank2.equals("Q") || rank2.equals("K") || rank2.equals("10")){
            return true;
        }
        return false;

    }

    //returns true if this hand busts, otherwise false.
    public boolean bustCheck(){
        int score = this.getScore();
        return (score>21) ? true : false;
    }

    //returns true if this hand contains at least one A, otherwise false
    public boolean hasAce(){
        for(int i=0; i < this.hand.size(); i++){
            if(this.getCard(i).getRank().equals("A")){
                return true;
            }
        }
        return false;
    }

    public boolean hasIdenticalCards(){
        if(this.getCard(0).getRank().equals(this.getCard(1).getRank())){
            return true;
        }
        return false;
    }

    //prints cards' name in console
    @Override
    public String toString(){
        String cards = "";

        for(int i=0; i < this.hand.size(); i++){
            cards += "[" + this.getCard(i) + "]  ";
        }
        return cards;
    }
}

