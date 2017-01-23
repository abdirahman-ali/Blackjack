//Abdirahman Ali
//aha2146
//Dealer.java
//Programming Project 4
//A class that models a Blackjack dealer, holding values for an arraylist of Card elements representing the cards in their hand, the number of aces they have and their points.
//This class has methods for accepting a card into the dealer's hand from the deck, determining the dealer's next move based on their current amount of points,
//clearing the dealer's hand, returning the dealer's points according to their hand, returning the names of the last card in the dealer's hand, and returning
//the number of aces in the dealer's hand.

import java.util.ArrayList;
public class Dealer {
	//Arraylist was used here because we can't know what size the hand will come to beforehand
	private ArrayList<Card> dealerHand = new ArrayList<Card>();
	public int dealerPoints;
	public String topCard;
	public int dealerAces = 0;
	public Dealer ()
	{
	}
	
	//Method that gets a card from the top of the deck and then removes that card 
	//so that it's as if the card has moved from the deck to the hand
	public void acceptCard(Deck inputDeck)
	{
		dealerHand.add(inputDeck.giveCard());
		inputDeck.removeCard();
	}
	
	//Method that makes the dealer hit repeatedly if the point value of the cards in their hand is 16 or below until the point value is above 16.
	//The method makes the dealer stand if the point value of the cards is 17 or above.
	//The method also changes the dealer's hand according to the move.
	public void turn(Deck inputDeck)
	{
		if ((getPoints()-(getAces()*10)) <= 16)
		//subtracting number of aces times 10 so all aces in the hand are effectively valued at 1 during this loop which allows dealer to hit more times
		{
			acceptCard(inputDeck);
			System.out.println("\n" + "The dealer chose to hit.");
			while ((getPoints()-(getAces()*10))<=16) 
			{
				acceptCard(inputDeck);
				System.out.println("\n" + "The dealer chose to hit again.");
			}
		}
		else
		{
			System.out.println("\n" + "The dealer chose to stand.");
		}
	}
	
	//Method that clears the dealer's hand, for when they play a new hand.
	public void newHand()
	{
		dealerHand.clear();
	}
	
	//Method that adds up the point values of the cards in the dealer's hand and returns this sum
	public int getPoints()
	{
		dealerPoints = 0;
		for(Card x : dealerHand)
		{
			dealerPoints += x.getValue();
		}
		return dealerPoints;
	}
	
	//Method that returns a String revealing the dealer's last card to the player
	public String getCards()
	{
		topCard = "The dealer's top card is a ";
		topCard += dealerHand.get(dealerHand.size()-1).getFace() + " of " + dealerHand.get(dealerHand.size()-1).getSuit() + "\n";
		return topCard;
	}
	
	//Method that determines and returns the number of aces in the dealer's hand
	public int getAces()
	{
		dealerAces = 0;
		for (Card x : dealerHand)
		{
			if (x.getFace().equals("Ace"))
			{
				dealerAces++;
			}	
		}
		return dealerAces;
	}
}
