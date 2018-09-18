package simpleBlackJack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {

    //get the user to choose to use console or file input
    public char inputOrConsole(){
        Scanner scan = new Scanner(System.in);
        char input = ' ';

        while(input != 'c' && input != 'f' && input != 'C' && input != 'F') {
            System.out.print("console[c] or file[f] input: ");
            input = scan.next().charAt(0);
        }
        return input;
    }

    //when console option is selected
    //generate a full shuffled deck of cards
    public void handleConsole(Deck myDeck){
        myDeck.generateWholeDeck();
        myDeck.deckShuffle();
    }

    //read a text file and stores it in a single string
    public String readFile(String path){

        File myFile = new File(path);
        String line;
        String context = "";

        //read the file and store the whole context in one string.
        try{
            BufferedReader in = new BufferedReader(new FileReader(myFile));
            while((line=in.readLine()) != null){
                context += line;
            }
            in.close();

        }catch(FileNotFoundException e){
            System.out.println("Cannot open file for reading");
        }catch(IOException e){
            System.out.println("Cannot read from file");
        }
        return context;
    }

    //when file input is selected
    public void handleFileInput(String path, Deck myDeck){

        //read the file
        String context = readFile(path);

        //convert to uppercase
        context = context.toUpperCase();

        //strings are separated by space
        //and stored into an array
        String[] junks = context.split(" ");

        //list of strings that stores only valid card names
        List<String> cardList = new ArrayList<String>();

        for(String j: junks){
            if(Card.checkValidCard(j)){  cardList.add(j); }
        }

        myDeck.generateGivenCards(cardList);
    }

    //deal each player and dealer two cards
    public void dealTwoCards(Hand[] user, Deck deck){

        for(int i=0; i<2; i++) {
            user[i].addCard(deck.drawCard());
            user[i].addCard(deck.drawCard());

            System.out.printf("%s has: %s %n", user[i].getType(), user[i].toString());
        }

        for(int k=0; k<2; k++){
            if(user[k].hasBlackjack()){
                System.out.printf("%s has Blackjack \n", user[k].getType());
            }
        }
    }

    //get user to choose whether hit or stand
    public char hitOrStand(){
        Scanner scan = new Scanner(System.in);
        char input = ' ';

        while(input != 'H' && input != 'S') {
            //get input from user
            System.out.print("hit[h] or stand[s]: ");
            input = scan.next().charAt(0);
            input = Character.toUpperCase(input);
        }
        return input;
    }


    //get user to choose whether hit or stand OR SPLIT
    public char hitStandSplit(){
        Scanner scan = new Scanner(System.in);
        char input = ' ';

        while(input != 'H' && input != 'S' && input != 'D') {
            //get input from user
            System.out.print("hit[h] or stand[s] or split[d]: ");
            input = scan.next().charAt(0);
            input = Character.toUpperCase(input);
        }
        return input;
    }

    //when "hit" is selected
    public void handleHitOption(Hand h, Deck d){

        Card newCard = d.drawCard();
        h.addCard(newCard);
        System.out.printf("%s has received a [%s]\n", h.getType(), newCard.toString());
    }


    //when "stand" is selected
    public void handleStandOption(Hand h){

        int score = h.getScore();

        System.out.println(h.getType() + " stands.");

        System.out.printf("\n\n" + h.getType() + " has: %s %n", h.toString());
        System.out.printf("%s has scored %d points %n",h.getType(), score);

        System.out.println("---------------------------------\n");

    }

    //user's turn: player or dealer
    //returns true when dealer scores
    //returns false when dealer busts
    public String userTurn(Hand user, Deck deck, boolean split){
        System.out.printf("\n\n" + user.getType() + " has: %s %n", user.toString());

        if(user.getType().equals("Dealer")){
            //if score is under 17, dealer hits
            if(split && user.getScore()<=17) return "split";

            //when dealer hits
            while(user.getScore() < 17 || (user.getScore()==17 && user.hasAce())) {
                System.out.println("Dealer hits");
                user.addCard(deck.drawCard());
                System.out.printf(user.getType() + " has: %s %n", user.toString());
            }
        }else if(user.getType().equals("Player")) {
            char hs = (split) ? hitStandSplit() : hitOrStand();

            if(hs == 'D') return "split";

            while(hs == 'H'){
                handleHitOption(user, deck);
                if(user.bustCheck()) break;
                hs = hitOrStand();
            }
        }

        //when player stops hitting
        if(user.bustCheck()){
            System.out.printf("%s busts.\n\n", user.getType());
            return "bust";
        }else{
            handleStandOption(user);
        }
        return "stand";
    }

    //determines who is the winner due to scores
    public void checkWinner(Hand player, Hand dealer){

        System.out.printf(player.getType() + " has: %s %n", player.toString());
        System.out.printf(dealer.getType() + " has: %s %n", dealer.toString());
        //the only time player wins is when player scores higher than dealer
        if(player.getScore() > dealer.getScore()){
            System.out.println("\n\n===> Player wins");
        }else{
            System.out.println("\n\n===> Dealer wins");
        }
    }

    public void pressEnter(){
        System.out.println("\nPress Enter to continue...");
        try{
            System.in.read();
        }catch(Exception e){}
    }

    //returns false when both splitted hands bust
    public boolean handleSplitOption(Hand user, Deck deck){

        Hand[] split = new Hand[2];
        split[0] = new Hand(user.getType());
        split[1] = new Hand(user.getType());

        split[0].addCard(user.removeCard(0));
        split[1].addCard(user.removeCard(0));

        int winner;

        System.out.printf("%s splits.\n\n", user.getType());

        for(int i=0; i<2;i++){
            pressEnter();

            split[i].addCard(deck.drawCard());

            System.out.printf("\n\n---- %s's %s hand----\n",user.getType(), (i==0) ? "first" : "second");
            userTurn(split[i], deck,false);
        }

        if(split[0].bustCheck() && split[1].bustCheck()){
            return false;
        }else if(split[0].bustCheck()){
            winner = 1;
        }else if(split[1].bustCheck()){
            winner = 0;
        }else{
            winner = (split[0].getScore() >= split[1].getScore()) ? 0 : 1;
        }


        for(int i=0; i<split[winner].getNumCards(); i++){
            user.addCard(split[winner].getCard(i));
        }


        System.out.printf("===>%s'S BEST SCORE:  %d points %n",user.getType().toUpperCase() ,user.getScore());
        return true;
    }
}
