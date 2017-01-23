//Abdirahman Ali
//aha2146
//Deck.java
//Programming Project 4
//A class that models a deck of 52 playing cards using an arraylist of Card elements.
//This class has methods for shuffling and refilling and reshuffling the deck, as well as a method that returns the card at the "top" of the deck,
//a method that removes the card at the "top", and a method that provides the current size of the deck.

import java.util.ArrayList;
import java.util.Collections;
public class Deck {
//Using an arraylist as opposed to an array because it makes it easier to optimize the filling of the deck 
private static ArrayList<Card> deckList = new ArrayList<Card>(52);
	public Deck()
	{
		//This for loop runs the following loop four times, filling the deck with cards of each of the four suits.
		for (int i=1; i<5; i++)
		{
			//The second for loop runs 14 times, adding cards to the deck by ascending face i.e. Ace --> King
			for (int j = 1; j<14; j++)
			{
				//An arraylist was used to the add() method could be used to make filling the deck simpler
				deckList.add(new Card(j, i));
			}
		}
		//Shuffling the deck as is required by the rules of Blackjack.
		shuffle();
	}
	
	private void shuffle()
	{
		//Using Collections.shuffle() for the shuffle() method because it's simpler and neater than writing up new code
		Collections.shuffle(deckList);
	}
	
	//Method for clearing, refilling and reshuffling the deck for when the number of cards goes below 12
	public void reset()
	{
		deckList.clear();
		for (int i=1; i<5; i++)
		{
			for (int j = 1; j<14; j++)
			{
				deckList.add(new Card(j, i));
			}
		}
		shuffle();
	}
	
	//Method for returning card at the top of the deck
	public Card giveCard()
	{
		return deckList.get(0);
	}
	
	//Method for removing card at the top of the deck, after the card is returned 
	//so that it's as if the card was taken from the deck and placed into a hand
	public void removeCard()
	{
		deckList.remove(0);
	}
	
	//Method for getting the size of the deck; used to keep track of whether the deck has gone below 12 cards
	public int getSize()
	{
		return deckList.size();
	}

}
