package simpleBlackJack;

public class Main {

    public static void main(String[] args) throws Exception{

        Deck deck = new Deck();
        View view = new View();

        Hand[] user = new Hand[2];

        user[0] = new Hand("Player");
        user[1] = new Hand("Dealer");

        char cf = view.inputOrConsole();
        if(cf == 'C'){
            view.handleConsole(deck);
        }
        else{
            view.handleFileInput("./test files/test_5.txt", deck);
        }

        //System.out.println("DECK: " + deck.toString());
        System.out.println("\n******* GAME BEGINS *******\n");
        view.dealTwoCards(user, deck);

        //checking for blackjack

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
            if(user[i].hasIdenticalCards()){
                System.out.printf("\n\n---- %s's turn----\n",user[i].getType());
                String result = view.userTurn(user[i], deck, true);

                if(result.equals("split")) {
                    if (!view.handleSplitOption(user[i], deck)) {
                        System.out.printf("====> %s wins.", (i==0) ? "Dealer" : "Player");
                        return;
                    }
                }else if(result.equals("bust")){
                    System.out.printf("====> %s wins.", (i==0) ? "Dealer" : "Player");
                    return;
                }
            }else{
                System.out.printf("\n\n---- %s's turn----\n",user[i].getType());
                if(view.userTurn(user[i], deck, false).equals("bust")){
                    System.out.printf("====> %s wins.", (i==0) ? "Dealer" : "Player");
                    return;
                }
            }

            view.pressEnter();

        }
/*
        if(user[0].hasIdenticalCards()){
            String result = view.userTurn(user[0], deck, true);

            if(result.equals("split")) {
                if (!view.handleSplitOption(user[0], deck)) {
                    System.out.println("Player busts.\n\n====> Dealer wins.");
                    return;
                }
            }else if(result.equals("bust")){
                System.out.println("Player busts.\n\n====> Dealer wins.");
                return;
            }
        }else{
            if(view.userTurn(user[0], deck, false).equals("bust")){
                System.out.println("Player busts.\n\n====> Dealer wins.");
                return;
            }
        }

        view.pressEnter();

        if(user[1].hasIdenticalCards()){
            String result2 = view.userTurn(user[1], deck, true);

            if(result2.equals("split")) {
                if (!view.handleSplitOption(user[1], deck)) {
                    System.out.println("Dealer busts.\n\n====> Player wins.");
                    return;
                }
            }else if(result2.equals("bust")){
                System.out.println("Dealer busts.\n\n====> Player wins.");
                return;
            }
        }else{
            if(view.userTurn(user[1], deck,false).equals("bust")){
                System.out.println("Dealer busts.\n\n===> Player wins.");
                return;
            }
        }
        view.pressEnter();
*/
        view.checkWinner(user[0], user[1]);
    }
}
