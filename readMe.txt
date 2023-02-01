Card.java

    This is a Card class that implements the Comparable interface. 
    It contains two private variables, suit and rank, which hold the suit and rank of a card. 
    The Card class also has two arrays called suitsArr and rankArr, which hold the names of the different suits and ranks.
    The Card class has a constructor that takes in two integers, s and r, and sets the suit and rank variables to these values. 
    The compareTo method is used to compare the rank of this card with the rank of another card. 
    The toString method returns a string representation of the card. 
        It uses the rankArr and suitsArr arrays to get the names of the suit and rank and concatenates them together. 
    Finally, the getSuit and getRank methods return the suit and rank of the card, respectively.


Deck.java

    This is a Deck class that represents a deck of cards. 
    It has two instance variables, cards, which is an array of Card objects, and top, 
    which is an integer representing the index of the top of the deck.

    The Deck class has a constructor that creates an array of Card objects and sets the top variable to 0. 
    It also has several methods including shuffle, which shuffles the deck; 
    deal(), which deals the top card in the deck; and 
    reset(), which resets the top variable to 0 and shuffles the deck.


Player.java

    This is a Player class that represents a player in a card game. 
    It has several instance variables including an ArrayList called hand that holds the player's cards, 
    a double called bankroll that holds the player's current balance, 
    a double called bet that holds the player's current bet, and 
    a String called name that holds the player's name.

    The Player class has a constructor that sets the bankroll to $50, 
    initializes the hand ArrayList, and sets the name to the user's input. 
    It also has several methods including addCard, which adds a card to the player's hand; 
    removeCard, which removes a card from the player's hand; 
    bets, which takes in an amount and subtracts it from the player's bankroll; 
    winnings, which takes in an amount and adds it to the player's bankroll; 
    getBankroll, which returns the player's current bankroll; getHand, which returns the player's hand; 
    clearHand, which clears the player's hand; sortHand, which sorts the cards in the player's hand; 
    displayHand, which prints out the cards in the player's hand; and 
    getCard, which returns the card at a given index in the player's hand.


Game.java  

    The Game class is a class for a game of video poker. 
    It has fields for a player, a deck, and a hand of cards. 
    The testHand is for testing purposes, 
        I used switch, break to represent the letter of the suit
    Game() is a constructor to play a normal game.
    The play() method allows the user to actually play the game of video poker, 
    including prompting for their name, asking if they want to play, and explaining the rules if necessary.
    I did this to make it seem more like a fun video game. 
    It also allows the user to make a wager and then choose which cards to keep or discard before showing 
    the final hand and determining the winnings based on the hand.
    I set certain hands to certain winnings, and once the player runs out of tokens or wishes to stop playing, the game will end.
    If the player has enough tokens and wants to continue playing, they can do so.
    
    For the checkRoyalFlush(), checkTwoPairs(), etc, I created the methods so that the first index (the first card in the hand) 
    equals whatever it needs to equal for it to be royal flush, two pairs, etc. 
    For example, for checkRoyalFlush(ArrayList<Card> hand), I first sorted the hand, so it is in ascending order,
    Then, since royal flush is an ace, 10, jack, queen, king with all same suit, I wrote an if statement,
    returning true, if the first card = ace, second card = 10, third card = jack, etc, and the suit of all cards are equal. 
    I can do this because the cards are sorted.
    Same for other methods.
    For example checkOnePair, was first sorted. 
    And then, if 0 = 1, or 1=2, or 2=3, or 3=4. then it is one pair. 

    I also organized the order in order of greatest return to lowest return. Because order of code matters, 
    I did not want to have it show one pair when in actuality there was three of kind.

    If all is false, then return no pair.

    One thing I realized while testing the code, is in Poker, the Ace can be first or last to create the straight.
    So it can either be Ace, Two, Three, Four, Five----- Or Ten, Jack, Queen, King, Ace.
    So I created another function to make up for this exception.