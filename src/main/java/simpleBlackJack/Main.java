package simpleBlackJack;

public class Main {

    public static void main(String[] args) throws Exception{

        Deck deck = new Deck();
        View view = new View();

        //both player and dealer are stored in a single array
        Hand[] user = new Hand[2];

        user[0] = new Hand("Player");
        user[1] = new Hand("Dealer");


        //choosing console vs file input
        char cf = view.inputOrConsole();
        if(cf == 'C'){
            view.handleConsole(deck);
        }
        else{
            view.handleFileInput("./test files/test_5.txt", deck);
        }

        //game starts by dealing each player two cards
        System.out.println("\n******* GAME BEGINS *******\n");
        view.dealTwoCards(user, deck);

        //pause
        view.pressEnter();

        //check for dealer first
        //since if both have blackjack, dealer will win.
        for(int i=1;i>=0; i--){
            if(user[i].hasBlackjack()){
                System.out.printf("\n\n====> %s wins. ", user[i].getType());
                return;
            }
        }

        for(int i=0;i<2; i++){
            //when the first two cards are having a same rank
            if(user[i].hasIdenticalCards()){

                System.out.printf("\n\n---- %s's turn----\n",user[i].getType());

                //result can be "split" "bust" or "stand"
                String result = view.userTurn(user[i], deck, true);

                //when user prefers to split
                if(result.equals("split")) {
                    //if both splitted hands bust, then the other player wins, program ends
                    if (view.handleSplitOption(user[i], deck).equals("bust")) {
                        System.out.printf("====> %s wins.", (i==0) ? "Dealer" : "Player");
                        return;
                    }
                }
                //when user hits and busts
                else if(result.equals("bust")){
                    System.out.printf("====> %s wins.", (i==0) ? "Dealer" : "Player");
                    return;
                }
            }
            //when first two cards are not identical
            else{
                System.out.printf("\n\n---- %s's turn----\n",user[i].getType());
                if(view.userTurn(user[i], deck, false).equals("bust")){
                    System.out.printf("====> %s wins.", (i==0) ? "Dealer" : "Player");
                    return;
                }
            }

            //pause
            view.pressEnter();
        }

        //checking for winner
        view.checkWinner(user[0], user[1]);
    }
}
