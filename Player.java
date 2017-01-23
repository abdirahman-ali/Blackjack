//Abdirahman Ali
//aha2146
//Player.java
//Programming Project 4
//A class that models a Blackjack player, holding values for their monetary balance, an arraylist of Card elements representing the cards in their hand, the number of aces they have and their points.
//This class has methods for accepting a card into the player's hand from the deck, changing the player's balance, prompting the player on their move and changing the hand accordingly,
//clearing the player's hand, returning the player's balance, returning the player's points according to their hand, returning the names of the cards in the player's hands, and returning
//the number of aces in the player's hand.

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
//Arraylist was used here because we can't know what size the hand will come to beforehand
private ArrayList<Card> playerHand = new ArrayList<Card>();
public double balance = 0;
public static int playerPoints;
public String cards;
private static Scanner scan = new Scanner(System.in);
public int playerAces = 0;

	public Player()
	{
	}
	
	//Method that gets a card from the top of the deck and then removes that card 
	//so that it's as if the card has moved from the deck to the hand
	public void acceptCard(Deck inputDeck)
	{
		playerHand.add(inputDeck.giveCard());
		inputDeck.removeCard();
	}
	
	//Method that adds the input double to the balance - if negative then the input will be subtracted from the balance
	public void changeBalance(double balanceChange)
	{
		balance += balanceChange;
	}
	
	//Method that prompts the player on what to do when it is their turn in the game, either to hit or to stand.
	//If they hit, the method takes the top card from the deck and places it in their hand. If the player's points are < 21, it prompts whether the player wants to hit again.
	//This method also tells the player which card they received.
	public void turn(Deck inputDeck)
	{
		System.out.println("What would you like to do?" + "\n" + "Input 1 to hit or 2 to stand: ");
		int move = scan.nextInt();
		while (move < 1 || move > 2)
		{
			System.out.println("\n" + "This is not an acceptable input." + "\n" + "Input 1 to hit or 2 to stand: ");
			move = scan.nextInt();
		}
		if (move == 1)
		{
			acceptCard(inputDeck);
			System.out.println("\n" + "You chose to hit.");
			System.out.println("\n" + "You received a " + playerHand.get(playerHand.size()-1).getFace() + " of " + playerHand.get(playerHand.size()-1).getSuit());
			int hitAgain = 1;
			while ((getPoints()-(getAces()*10))<22 && hitAgain == 1)
				//I subtracted number of aces times 10 from the player's points here so all aces in the hand are effectively valued at 1 during this loop
				//This allows players to hit more times.
			{
				System.out.println("\n" + "Would you like to hit again?" + "\n" + "Input 1 for yes or 2 for no: ");
				hitAgain = scan.nextInt();
				while (hitAgain < 1 || hitAgain >2)
				{
					System.out.println("\n" + "This is not an acceptable input." + "\n" + "Input 1 for yes or 2 for no: ");
					hitAgain = scan.nextInt();
				}
				if (hitAgain == 1)
				{
					acceptCard(inputDeck);
					System.out.println("\n" + "You received a " + playerHand.get(playerHand.size()-1).getFace() + " of " + playerHand.get(playerHand.size()-1).getSuit());
				}
			}
		}
		if (move == 2)
		{
			System.out.println("\n" + "You chose to stand.");
		}
		
	}
	
	//Method that clears the player's hand, for when they play a new hand.
	public void newHand()
	{
		playerHand.clear();
	}
	
	//Method that returns the player's balance
	public double getBalance()
	{
		return balance;
	}
	
	//Method that adds up the point values of all the cards in the player's hand and returns this sum
	public int getPoints()
	{
		playerPoints = 0;
		for(Card x : playerHand)
		{
			int cardPoints = x.getValue();
			playerPoints += cardPoints;
		}
		return playerPoints;
	}
	
	//Method that returns a string listing the cards the player has in their hand 
	public String getCards()
	{
		cards = "\n" + "Your cards are:" + "\n";
		for (Card x : playerHand)
		{
			cards += x.getFace() + " of " + x.getSuit() + "\n";
		}
		return cards;
	}
	
	//Method that determines and returns the number of aces in the player's hand
	public int getAces()
	{
		playerAces = 0;
		for (Card x : playerHand)
		{
			if (x.getFace().equals("Ace"))
			{
				playerAces++;
			}	
		}
		return playerAces;
	}

}
