/* 
 *
 * Deck.java - represents a deck of cards. 
 *
 */

import java.util.*;

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck

	// add more instance variables if needed
	
	public Deck(){
		// make a 52 card deck here
        // a nested for loop; outer suits, inner ranks, up to 13
        cards = new Card[52];
        top = 0; // keeps track of the position in array cards that we're 
                 // on right now
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                Card card = new Card(i+1, j+1); // need +1 b/c started at 0
                cards[top] = card;// for arrays, ALWAYS indexing to add items
                top++; // to move forward within the array cards
            }
        }
        top = 0; // reset the top so that 1st card is the current top
	}
	
	public void shuffle(){
		// shuffle the deck here
        Random r = new Random();
        for (int i = 52 - 1; i >= 0; i--) {
            //select a random position between 0 and i
            int pos = r.nextInt(i + 1);
            //exchange the element at the random position with the element at i
            // this way, each position is only swapped once!
            Card temp = cards[i];
            cards[i] = cards[pos];
            cards[pos] = temp;
        }
        
    }
	
	public Card deal(){
		// deal the top card in the deck
        // right now top is length of card array because of Deck method
        // there will be a point where we reach beyond 52
        // make another method to make sure that top is always <52
        if(top < 52){
            top++;
        }
        return cards[top-1]; // return next card
	}

    public void reset() {
        top = 0;
        shuffle();
    }
	
}