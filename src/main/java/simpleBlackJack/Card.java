package simpleBlackJack;

public class Card {

    //all possible cards' ranks and suits
    public static String Rank[] = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    public static String Suit[] = {"S","H","C","D"};

    private String rank;
    private String suit;
    private int cardValue;

    //constructor
    public Card(String newCard) {
        newCard = newCard.toUpperCase();
        this.suit = newCard.charAt(0) + "";
        this.rank =  newCard.substring(1);

        //set the value of the card
        for(int i=0; i<Rank.length; i++){
            if(this.rank.equals(Rank[i])){
                if(i>=9 && i<=11){
                    this.cardValue = 10;
                }else if(i == 12){
                    this.cardValue = 11;
                }else {
                    this.cardValue = Integer.parseInt(Rank[i]);
                }
            }
        }

    }

    //set the card value
    public void setCardValue(int num){
        this.cardValue = num;
    }
    //get numeral value of the card
    public int getCardValue(){
        return this.cardValue;
    }

    public String getRank(){
        return this.rank;
    }

    public String getSuit(){
        return suit;
    }

    public static boolean checkValidCard(String c){
        if(c.length()<2 || c.length()>3){
            return false;
        }
        String cardSuit = c.charAt(0) + "";
        String cardRank =  c.substring(1);

        for(String r: Rank){
            for(String s: Suit){
                if(cardRank.equals(r) && cardSuit.equals(s)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return suit + rank;
    }
}
