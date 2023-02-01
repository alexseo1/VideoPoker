/*
 *
 * Player.java - Represents a player in the card game.
 */

import java.util.*;

public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;
    private String name;

		
	public Player(){		//human player
        bankroll=50;
        hand = new ArrayList<Card>();
        name = name; // whatever user's input is
    }

	public void addCard(Card c){
	    // add the card c to the end of the player's hand
        hand.add(c);
	}

	public void removeCard(Card c){
	    // remove the card c from the player's hand
        hand.remove(c);
    }
    
		
    public void bets(double amt){
        bet = amt;
        bankroll -= bet;// player makes a bet
    }

    public void winnings(double odds){
        bankroll = bankroll + odds;//	adjust bankroll if player wins
    }

    public double getBankroll(){
        return bankroll;// return current balance of bankroll
    }

    public ArrayList<Card> getHand(){
        return hand; //get hand, 5 cards.
    }

    public void clearHand(){
        hand.clear(); //reset the hand, for new round.
    }

    public void sortHand(){
        Collections.sort(hand); //sort hand into ascending order.
    }

    public void displayHand(){
        System.out.println("Your hand consists of: ");
        sortHand();
        for(Card c: getHand()){
            System.out.println(c); //displays the cards in hand.
        }
    }

    public Card getCard(int i) { //Returns the card at corresponding position (i) in the hand 
        if (i >= 0 && i < hand.size()) {
            return hand.get(i);
        }
        return null;
    }

  
} 


