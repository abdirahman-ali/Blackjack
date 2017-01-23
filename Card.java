//Abdirahman Ali
//aha2146
//Card.java
//Programming Project 4
//A class that models a playing card with values for its face, suit and point value.
//This class has methods for returning the value, face and suit of the card

public class Card {
private int face;
private int suit;
public int value;
public String outputFace;
public String outputSuit;

	//Constructor that takes two parameters inputFace and inputSuit that give the face and suit of the card as an integer
	//(1-13 for the face and 1-4 for the suit)
	public Card(int inputFace, int inputSuit)
	{
		face = inputFace;
		suit = inputSuit;
	}
	//Method that returns the point value of the card using the value provided for face. 
	//A face value of one corresponds to an Ace, the default value for which I've set to 11. This can be dealt with later on in the player, dealer, and game classes.
	//Face cards i.e. Jack, King and Queen correspond to the values 11, 12 and 13 respectively and have a point value of 10.
	//Cards with face values of 2-9 have a point value corresponding to their pip value.
	public int getValue()
	{
		if (face == 1)
		{
			value = 11;
		}
		if (face >1 && face < 10)
		{
			value = face;
		}
		if (face >= 10 && face < 14)
		{
			value = 10;
		}
		return value;
	}
	
	//Method that returns the face of the card using the value provided for face.
	//1 is an ace, 2-10 are regular numbered cards, 11 is a Jack, 12 is a Queen, 13 is a King
	public String getFace()
	{
		if (face == 1)
		{
			outputFace = "Ace";
		}
		if (face > 1 && face <=10)
		{
			outputFace = Integer.toString(face);
		}
		if (face == 11)
		{
			outputFace = "Jack";
		}
		if (face == 12)
		{
			outputFace = "Queen";
		}
		if (face == 13)
		{
			outputFace = "King";
		}
		return outputFace;
	}
	
	//Method that returns the suit of the card using the value provided for suit.
	//1 is Clubs, 2 is Spades, 3 is Hearts and 4 is Diamonds
	public String getSuit()
	{
		if (suit == 1)
		{
			outputSuit = "Clubs";
		}
		if (suit == 2)
		{
			outputSuit = "Spades";
		}
		if (suit == 3)
		{
			outputSuit = "Hearts";
		}
		if (suit == 4)
		{
			outputSuit = "Diamonds";
		}
		return outputSuit;
	}
}
