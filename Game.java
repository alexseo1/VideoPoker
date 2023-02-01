/* 
 *
 * Game.java - class for the game video poker.
 *
 */


import java.util.*;

public class Game {
	
	private Player p;
	private Deck d;
	private ArrayList<Card> hand;

	
	
	public Game(String[] testHand){
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush

		p = new Player();
		d = new Deck();

		for (String input: testHand) {
            // The first character of the input string tells you what 
            // suit the card is.
            char s = input.charAt(0);
            int suit = 0;
            switch (s) {
                case 'c':
                    suit = 1;
                    break;
                case 'd':
                    suit = 2;
                    break;
                case 'h':
                    suit = 3;
                    break;
                case 's':
                    suit = 4;
                    break;
            }
			int rank = Integer.parseInt(input.substring(1));
			Card newCard = new Card(suit, rank);
			p.addCard(newCard);
		}
	}
	
	public Game(){
		// This no-argument constructor is to actually play a normal game
		p = new Player();
		d = new Deck();	
		d.shuffle();
		for(int i=0; i<5; i++){		
			p.addCard(d.deal());
		}
		hand = new ArrayList<Card>(p.getHand());

	}
	
	public void play(){
		// this method should play the game	 
		// Prompt the user to input their name and instantiate a human player
		boolean cont = true;
		Scanner nombre = new Scanner(System.in);
		System.out.println("Enter name: ");
		String name = nombre.nextLine();//name of user
		System.out.println("Welcome to Video Poker " + name + " !");
		Scanner contin = new Scanner(System.in);
		System.out.println("Would you like to play? (Yes or No)");
		String yesno = contin.nextLine();
		if(yesno.equals("yes") || yesno.equals("Yes") || yesno.equals("YES")){
			cont = true;
		}else if (yesno.equals("no") || yesno.equals("No") || yesno.equals("NO")){
			cont = false;
		}else{
			System.out.println("Sorry I could not understand. Would you like to play? (Yes or No)");
				yesno = contin.nextLine();
		}
		if(p.getBankroll() <=0){
			System.out.println("Sorry, You are out of tokens!");
		}
		
		
		Scanner wager = new Scanner(System.in);
		System.out.println("Do you know how to play?");
		Scanner know = new Scanner(System.in);
		String knows = know.nextLine();
		if(knows.equals("Yes") || knows.equals("yes") || knows.equals("YES")){
			System.out.println("Great! Let's play!");
		}else if(knows.equals("No") || knows.equals("no") || knows.equals("NO")){
			System.out.println("Let me explain the rules.");
			System.out.println("No pair        = Lose your bet");
			System.out.println("One pair       = 1 times your bet");
			System.out.println("Two pair       = 2 times your bet");
			System.out.println("Three of Kind  = 3 times your bet");
			System.out.println("Straight       = 4 times your bet");
			System.out.println("Flush          = 5 times your bet");
			System.out.println("Full House     = 6 times your bet");
			System.out.println("Four of Kind   = 25 times your bet");
			System.out.println("Straight Flush = 50 times your bet");
			System.out.println("Royal Flush    = 250 times your bet");//explain rules of poker

		}
		
		System.out.println("You have " + p.getBankroll() + " tokens.");	
		while(cont && p.getBankroll() > 0){
			System.out.println("Please place the number of tokens you would like to bet, from 1 to 5: ");
			int bets = wager.nextInt();
			if(bets == 0 || bets > 5){
				System.out.println("Please wager between 1 and 5 tokens: ");
					bets = wager.nextInt();
			}if(bets > p.getBankroll()){
				System.out.println("Insufficient Token Amount");
					bets = wager.nextInt();
			}if(p.getBankroll() <= 0){
				System.out.println("You do not have any tokens left. Thank you for playing.");
			}
			System.out.println("These are your cards dealt: ");
			System.out.println("*Shuffling...*");
			d.shuffle();//shuffle the deck
			System.out.println("*Dealing cards...*");	
			if (p.getHand().size() == 0){//reset hand once round is over.
                d.reset();
                dealHand();
            }
            p.bets(bets);//how much user wagered
            p.displayHand(); // display hand
            if (exchangeCards()){//if user wants to exchange and replace any cards.
                p.displayHand();
            }

			p.sortHand();//sort hand so it is in order.
            String result = checkHand(hand); //puts cards in same line in order.
            System.out.println("You got a " + result + "!");//result (no pair, one pair, etc)
            int payout = getPayout(result) * bets; // if i bet 2 and get royal flush, that is 500 winnings. 
            System.out.println("Winnings: " + payout + " tokens");
            p.winnings(payout);
            p.clearHand();//clear for new round
			System.out.println("You have " + p.getBankroll() + " tokens.");	//new balance after winnings
			System.out.println("Would you like to continue?");//continue?
			Scanner goOn = new Scanner(System.in);
			String gooOn = goOn.nextLine();
			if(gooOn.equals("Yes") || gooOn.equals("yes") || gooOn.equals("YES")){
				cont=true;//if continue then continue
		}else if(gooOn.equals("No") || gooOn.equals("no") || gooOn.equals("NO")){
			cont = false;// if no, then end game.
			System.out.println("Okay, Goodbye!");
		} 
		}

		
		
		if(p.getBankroll() <= 0){
		System.out.println("You are out of tokens. Thank you for playing.");//end game if no token

		}

	}

	public boolean exchangeCards(){
		System.out.println("How many cards would you like to exchange?(0-5)");
		Scanner trade = new Scanner(System.in);
		int xchange = Integer.parseInt(trade.nextLine());//how many cards you would exchange.
		int i = 0;
        ArrayList<Card> removed  = new ArrayList<Card>();//arraylist of cards u wanna remove.
		int[] positions = new int[xchange]; //set position of cards
		
		while(i<xchange){//while less than number of cards u wanna exchange
			System.out.println("Which card would you like to exchange? (1-5)");
			Scanner changee = new Scanner(System.in);
			int changes = changee.nextInt();//1-5 which position u would like to change
			boolean cont = true;
			while(cont){
				cont = false;
				if(changes < 1 || changes >5){
					System.out.println("Pick a valid position (1-5).");//can only exchange card u have.
					changes = changee.nextInt();
					cont = true;
				}else {
                    // check if user already chose to remove the selected card
                    for (int prev: positions) {
                        if (prev == changes) {
                            System.out.println("You cannot exchange a card that you previously exchanged.");
                            System.out.println("Please enter another position: ");
							changes = changee.nextInt();//can only exchange an already exchanged card.
                            cont = true;
                        }
                    }
                }
			}
            positions[i] = changes;
            Card rem = p.getCard(changes - 1);
            removed.add(rem);//remove card
            i++;
		}

		for (Card rem: removed) {
            p.removeCard(rem);	//for removed card, add new card
		    dealToPlayer();
        }
		p.sortHand();//sort
		System.out.println(p.getHand());//print new hand

        return xchange == 0;

	}


	private void dealHand() {
        for (int i = 0; i < 5; i++) {
        	dealToPlayer(); 
        }			

    }


	private void dealToPlayer() {
        Card dealt = null;
        boolean alreadyPresent = true;
        // deal card to player and make sure the dealt card isn't already 
        // in the player's hand.
        while (alreadyPresent) {
            dealt = d.deal();
            if (dealt.getSuit() == 0 && dealt.getRank() == 0) {
                d.reset();
                dealt = d.deal();
            }
            alreadyPresent = false;
            for (Card c: p.getHand()) {
                if (c.compareTo(dealt) == 0) {
                    alreadyPresent = true;
                }
            }
        }
        p.addCard(dealt);
    }


	private int getPayout(String hand) {//winnings (rules)
        if(hand.equals("Royal Flush")){
            return 250;
		}else if(hand.equals("Straight Flush")){
            return 50;
		}else if(hand.equals("Four of a Kind")){
            return 25;
		}else if(hand.equals("Full House")){
            return 6;
		}else if(hand.equals("Flush")){
            return 5;
		}else if(hand.equals("Straight")){
            return 4;
		}else if(hand.equals("Three of a Kind")){
            return 3;
		}else if(hand.equals("Two Pairs")){
            return 2;
		}else if(hand.equals("One Pair")){
        	return 1;
		}else{
			return 0;
		}
    } 

	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String		
		if(checkRoyalFlush(hand)){
			return "Royal Flush";
		}else if(checkStraightFlush(hand)){
			return "Straight Flush";
		}else if(checkFourOfKind(hand)){
			return "Four of a Kind";
		}else if(checkFullHouse(hand)){
			return "Full House";
        }else if(checkFlush(hand)){
            return "Flush";
		}else if(checkStraight(hand)){
			return "Straight";
        }else if(checkThreeOfKind(hand)){
			return "Three of a Kind";
		}else if(checkTwoPairs(hand)){
			return "Two Pairs";
		}else if(checkOnePair(hand)){
			return "One Pair";
		}else{
			return "No Pair";
		}
	} 

	public boolean checkRoyalFlush(ArrayList<Card> hand){
        p.sortHand();//sort hand to ascending order.
		if((p.getHand().get(0).getRank()==1//if first card is ace
		&& p.getHand().get(1).getRank()==10 // if second card is 10
		&& p.getHand().get(2).getRank()==11 // if third card is jack
		&& p.getHand().get(3).getRank()==12 // if fourth card is queen
		&& p.getHand().get(4).getRank()==13)// if fifth card is king
		&& (p.getHand().get(0).getSuit()==p.getHand().get(1).getSuit() // if first card suit = second card suit
        && p.getHand().get(1).getSuit()==p.getHand().get(2).getSuit() // if second card suit = third card suit
        && p.getHand().get(2).getSuit()==p.getHand().get(3).getSuit() // if third card suit = fourth card suit
        && p.getHand().get(3).getSuit()==p.getHand().get(4).getSuit())){// if fourth card suit = fifth card suit
			return true; // return true if all face cards and same suit.
		}return false;
	} 

	public boolean checkStraightFlush(ArrayList<Card> hand){
        p.sortHand();//sort hand
        if((p.getHand().get(0).getSuit()==p.getHand().get(1).getSuit()
        && p.getHand().get(1).getSuit()==p.getHand().get(2).getSuit()
        && p.getHand().get(2).getSuit()==p.getHand().get(3).getSuit()
        && p.getHand().get(3).getSuit()==p.getHand().get(4).getSuit())//if flush
        && (p.getHand().get(4).getRank()==(p.getHand().get(3).getRank()+1)
		&& p.getHand().get(3).getRank()==(p.getHand().get(2).getRank()+1)
		&& p.getHand().get(2).getRank()==(p.getHand().get(1).getRank()+1)
		&& p.getHand().get(1).getRank()==(p.getHand().get(0).getRank()+1))){//AND Straight
			return true;
		}return false;
	}

	public boolean checkFourOfKind(ArrayList<Card> hand){
        p.sortHand();
		if((p.getHand().get(0).getRank()==p.getHand().get(1).getRank()
		&& p.getHand().get(1).getRank()==p.getHand().get(2).getRank()
		&& p.getHand().get(2).getRank()==p.getHand().get(3).getRank())//1,2,3,4
		|| (p.getHand().get(1).getRank()==p.getHand().get(2).getRank()
		&& p.getHand().get(2).getRank()==p.getHand().get(3).getRank()
		&& p.getHand().get(3).getRank()==p.getHand().get(4).getRank())){//2,3,4,5
			return true;
		}return false;
	}

	public boolean checkFullHouse(ArrayList<Card> hand){//pair and three of kind
		p.sortHand();
		if((p.getHand().get(0).getRank()==p.getHand().get(1).getRank()
		&& p.getHand().get(2).getRank()==p.getHand().get(3).getRank()
		&& p.getHand().get(3).getRank()==p.getHand().get(4).getRank())//2(pair) and 3 (threeofkind)
		|| (p.getHand().get(0).getRank()==p.getHand().get(1).getRank()
		&& p.getHand().get(1).getRank()==p.getHand().get(2).getRank()
		&& p.getHand().get(3).getRank()==p.getHand().get(4).getRank())){//3(three of kind) and 2(pair)
			return true;
		}return false;
	}

	public boolean checkFlush(ArrayList<Card> hand){
        p.sortHand();
        if((p.getHand().get(0).getSuit()==p.getHand().get(1).getSuit()
        && p.getHand().get(1).getSuit()==p.getHand().get(2).getSuit()
        && p.getHand().get(2).getSuit()==p.getHand().get(3).getSuit()
        && p.getHand().get(3).getSuit()==p.getHand().get(4).getSuit())){//if suit is all same
			return true;
		}return false; 
    }

	public boolean checkStraight(ArrayList<Card> hand){
		p.sortHand();
		if((p.getHand().get(4).getRank()==p.getHand().get(3).getRank()+1
		&& p.getHand().get(3).getRank()==p.getHand().get(2).getRank()+1
		&& p.getHand().get(2).getRank()==p.getHand().get(1).getRank()+1//returns if next index is +1 than previous
		&& p.getHand().get(1).getRank()==p.getHand().get(0).getRank()+1)
		|| (p.getHand().get(0).getRank()==1 
		&& p.getHand().get(1).getRank()==9
		&& p.getHand().get(2).getRank()==10//Straight rule exception for when Ace is highest,
		&& p.getHand().get(3).getRank()==11//Like if the hand is [Ace, 10, Jack, Queen, King]
		&& p.getHand().get(4).getRank()==12)){//also known as [10, Jack, Queen, King, Ace]
			return true;//true if second card is +1 than first card, etc and rule exception
		}return false;
	}

	public boolean checkThreeOfKind(ArrayList<Card> hand){
		p.sortHand();
		if((p.getHand().get(0).getRank()==p.getHand().get(1).getRank() 
		&& p.getHand().get(1).getRank()==p.getHand().get(2).getRank())//0,1,2 is same
		|| (p.getHand().get(1).getRank()==p.getHand().get(2).getRank() 
		&& p.getHand().get(2).getRank()==p.getHand().get(3).getRank())// 1, 2, 3 is same
		|| (p.getHand().get(2).getRank()==p.getHand().get(3).getRank() 
		&& p.getHand().get(3).getRank()==p.getHand().get(4).getRank())){ // 2, 3, 4 is same
			return true;
		}return false;
	}

	public boolean checkTwoPairs(ArrayList<Card> hand){
		p.sortHand();
		if((p.getHand().get(0).getRank() ==p.getHand().get(1).getRank() //if 0 = 1 and
		&& p.getHand().get(2).getRank()==p.getHand().get(3).getRank()) // 2 = 3 : 2 diff pairs.
		|| (p.getHand().get(1).getRank()==p.getHand().get(2).getRank() // if 1 = 2 and 
		&& p.getHand().get(3).getRank()==p.getHand().get(4).getRank()) // 3 = 4
		|| (p.getHand().get(0).getRank()==p.getHand().get(1).getRank()// if 0=1 and 
		&& p.getHand().get(3).getRank()==p.getHand().get(4).getRank())){ // 3=4
			return true;
		}return false;
	} 

	public boolean checkOnePair(ArrayList<Card> hand){
		p.sortHand();
		if(p.getHand().get(0).getRank()==p.getHand().get(1).getRank() //if 0 = 1
		|| p.getHand().get(1).getRank()==p.getHand().get(2).getRank() // or 1 = 2
		|| p.getHand().get(2).getRank()==p.getHand().get(3).getRank() // or 2 = 3
		|| p.getHand().get(3).getRank()==p.getHand().get(4).getRank()){ // or 3 = 4
			return true;
		}
		return false;

	}
}


