/* 
 * 
 *Card.java - This is a Card class that implements the Comparable interface. 
 *   
 */

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
    private String[] suitsArr = {"Clubs", "Diamonds", "Spades", "Hearts"}; // an array with all suit names
    private String[] rankArr = {"Ace", "Two", "Three", "Four", "Five", "Six", 
                                "Seven", "Eight", "Nine", "Ten", "Jack", 
                                "Queen", "King"}; // an array for rank names
	
	public Card(int s, int r){
		//make a card with suit s and value r
        suit = s;
        rank = r;
	}
	
	public int compareTo(Card c){
		// use this method to compare cards
        // THREE outcomes: 1 if rank of this.card is greater than rank of 
        // the other card; need else if to not check every single condition
        int result = 0;
        if(this.rank > c.rank){
            result = 1;
        }
        else if(this.rank < c.rank){
            result = -1;
        }
        else if(this.rank == c.rank){
            result = 0;
        }
        return result;
	}
	
	public String toString(){
		
        return rankArr[rank-1] + " of " + suitsArr[suit-1];
        //Lines 8-9. For example, if rank =1, then it would be index 1-1 = 0, which is Ace.
        //Or if Suit = 2, then [2-1] would be diamonds
	}



    public int getSuit() {
        return suit;
    }

    public int getRank(){
        return rank;
    }
}