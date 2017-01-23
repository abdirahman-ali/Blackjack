//Abdirahman Ali
//aha2146
//Game.java
//Programming Project 4
//A class that models a game of blackjack.
//This class has one method, play(). This method brings together the Card, Deck, Player and Dealer classes to model a whole game of blackjack.

import java.util.Scanner;

public class Game {
private static double bet;
private static Scanner scan = new Scanner(System.in);
private static boolean win = false;
private static boolean push = false;
private static boolean loss = false;
private static boolean playerBlackjack = false;
private static boolean dealerBlackjack = false;
private static int pPoints = 0;
private static int dPoints = 0;
private static boolean replay = true;

	public Game()
	{
	}

	public static void play()
	{
		//Instantiating Deck, Player and Dealer classes
		Deck deckOne = new Deck();
		Player playerOne = new Player();
		Dealer dealerOne = new Dealer();
		System.out.println("*********************" + "\n" + "Welcome to Blackjack!" + "\n" + "*********************" + "\n");
		//Asking for the buy-in amount, which becomes the new balance. This does not accept amounts smaller than 100.
		System.out.println("How much would you like to buy-in for?");
		double buyIn = scan.nextDouble();
		while (buyIn < 100)
		{
			System.out.println("\n" + "This is not an acceptable amount." + "\n" + "Please buy-in for an amount that is at least $100.");
			buyIn = scan.nextDouble();
		}
		playerOne.changeBalance(buyIn);
		//While loop that will make sure the game replays from here if the player decides to play another hand
		while (replay)
		{
			//Dealing two cards to both the player and the dealer
			playerOne.acceptCard(deckOne);
			playerOne.acceptCard(deckOne);
			dealerOne.acceptCard(deckOne);
			dealerOne.acceptCard(deckOne);
			
			//while loop that terminates once the player wins, loses, or the hand ends in a push
			while (win == false && push == false && loss == false)
			{
				System.out.println("\n" + "Your balance is $" + playerOne.getBalance());
				//Asking the player to bet
				System.out.println("Please place your bet: ");
				bet = scan.nextInt();
				//This does not accept bets smaller than $10 or larger than $1000
				while (bet<10 || bet>playerOne.getBalance() || bet>1000)
				{
					System.out.println("\n" + "This is not an acceptable bet." + "\n" + "Please bet an amount you can afford between $10.00 and $1000.00: ");
					bet = scan.nextInt();
				}
				System.out.println("\n" + "Your bet is $" + bet);
				//Displays to the player their own cards and the dealer's top card
				//These commands are placed before their associated if statements so if a player gets a blackjack the program won't output the dealer's number of points as 0
				System.out.println(playerOne.getCards());
				System.out.println(dealerOne.getCards());
				//Determining whether the player or dealer has a blackjack before continuing. If both have a blackjack it's a push.
				if (playerOne.getPoints()==21)
				{
					playerBlackjack = true;
					System.out.println("You have a blackjack!" + "\n");
				}
				if (dealerOne.getPoints()==21)
				{
					dealerBlackjack = true;
					System.out.println("The dealer has a blackjack!");
				}
				if (playerBlackjack==true && dealerBlackjack==false)
				{
					win = true;
					break;
				}
				if (playerBlackjack == true && dealerBlackjack == true)
				{
					push = true;
					break;
				}
				if (playerBlackjack == false && dealerBlackjack == true)
				{
					loss = true;
					break;
				}
				//These commands are placed before their associated if statements so if a player busts the program won't output the dealer's number of points as 0
				playerOne.turn(deckOne);
				pPoints = playerOne.getPoints();
				dealerOne.turn(deckOne);
				dPoints = dealerOne.getPoints();
				//Determining whether the player or the dealer busts before continuing. If both bust the player still loses.
				//pPoints and dPoints are meant to represent the player's points and dealer's points respectively; if they go over 21, we change the value of the aces in the hand to 1
				//one by one to ensure the largest amount of points below 21. If there isn't a bust, we continue
				if (pPoints>21)
				{
					int i = 0;
					while (pPoints>21 && i<playerOne.getAces())
					{
						pPoints = pPoints-10;
						i++;
					}
					if (pPoints > 21)
					{
						System.out.println("\n" + "Your number of points is over 21! You have busted.");
						loss = true;
						break;
					}
				}
				if (dPoints>21)
				{
					int i = 0;
					while (dPoints>21 && i<dealerOne.getAces())
					{
						dPoints = dPoints-10;
						i++;
					}
					if (dPoints > 21)
					{
						System.out.println("\n" + "The dealer's number of points is over 21! They have busted.");
						win = true;
						break;
					}
				}
				//Determining whether the player's points are larger than, equal to or smaller than the dealer's.
				if (pPoints > dPoints)
				{
					win = true;
					break;
				}
				if (pPoints == dPoints)
				{
					push = true;
					break;
				}
				if (pPoints < dPoints)
				{
					loss = true;
					break;
				}
			}
			//Determining what kid of win, loss, or push it was (e.g. if it was a win with a blackjack, in which case the player gets 1.5x their bet)
			//Also determining the associated payout and changing the player's balance accordingly.
			if (win == true && playerBlackjack == true)
			{
				System.out.println("You win this hand.");
				playerOne.changeBalance(1.5 * bet);
				System.out.println("You won $" + 1.5*bet);
			}
			if (win == true && playerBlackjack == false)
			{
				System.out.println("\n" + "You have " + pPoints + " points " + "while the dealer has " + dPoints + " points.");
				System.out.println("\n" + "You win this hand.");
				playerOne.changeBalance(bet);
				System.out.println("\n" + "You won $" + bet + "\n");
			}
			if (loss == true && dealerBlackjack == true)
			{
				System.out.println("\n" + "You lose this hand.");
				playerOne.changeBalance(-1 * bet);
				System.out.println("\n" + "You lost $" + bet + "\n");
			}
			if (loss == true && dealerBlackjack == false)
			{
				System.out.println("\n" + "You have " + pPoints + " points " + "while the dealer has " + dPoints + " points.");
				System.out.println("\n" + "You lose this hand.");
				playerOne.changeBalance(-1 * bet);
				System.out.println("\n" + "You lost $" + bet + "\n");
			}
			if (push == true)
			{
				System.out.println("\n" + "You have " + pPoints + " points " + "while the dealer has " + dPoints + " points.");
				System.out.println("\n" + "You and the dealer have the same amount of points. This round ends in a push." + "\n");
			}
			//Checking the player's balance to see if they can play another hand
			if (playerOne.getBalance() < 10)
			{
				System.out.println("You don't have enough money to continue!" + "\n" + "Game over!");
				replay = false;
			}
			else
			//Providing the player's new balance and asking if they'd like to play another hand or not. If so, we go back to the beginning of the while loop.
			{
				System.out.println("You currently have $" + playerOne.getBalance());
				System.out.println("Input 1 if you'd like to play another hand or 2 if you'd like to quit: ");
				int option = scan.nextInt();
				while (option < 1 || option > 2)
				{
					System.out.println("\n" + "This is not an acceptable input." + "\n" + "Input 1 if you'd like to play another hand or 2 if you'd like to quit: ");
					option = scan.nextInt();
				}
				if (option == 2)
				{
					System.out.println("\n" + "Goodbye!");
					replay = false;
				}
				if (option == 1)
				//If the player chooses to play again, the hands must be cleared and the booleans reset to false
				{
					playerOne.newHand();
					dealerOne.newHand();
					win = false;
					push = false;
					loss = false;
					playerBlackjack = false;
					dealerBlackjack = false;
					//Checking if the size of the deck has gone below 12 and if so clearing it, refilling and resetting it using the reset() method
					if (deckOne.getSize() < 12)
					{
						deckOne.reset();
					}
				}
			}
		}
	}
}
