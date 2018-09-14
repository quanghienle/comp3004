package simpleBlackJack;

public class Card {

    //all possible cards' ranks and suits
    public static String Rank[] = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    public static String Suit[] = {"S","H","C","D"};

    private String rank;
    private String suit;

    //constructor
    public Card(String newCard) {
        if (newCard.length() == 2) {
            this.rank = newCard.charAt(1) + "";
            this.suit = newCard.charAt(0) + "";
        }

        if (newCard.length() == 3) {
            this.rank = newCard.charAt(2) + "";
            this.suit = newCard.charAt(0) + newCard.charAt(1) + "";

        }
    }

    public String getRank(){
        return this.rank;
    }

    public String getSuit(){
        return suit;
    }

    //get numeral value of the card
    public int getCardValue(boolean bustedHand){
        for(int i=0; i<9; i++)
            if (this.rank.equals(Rank[i])) return i + 2;

        if(this.rank.equals("J") || this.rank.equals("Q") || this.rank.equals("K")){
            return 10;
        }else if (this.rank.equals("A")){
            return bustedHand ? 1 : 11;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        //object is itself
        if (o == this) { return true; }

        // Check if o is an instance of Card
        if (!(o instanceof Card)) { return false; }

        // typecast o to Card
        Card c = (Card) o;

        // Compare rank and suit
        return this.rank.equals(c.getRank()) && this.suit.equals(c.getSuit());
    }

    @Override
    public String toString(){
        return suit + rank;
    }
}
