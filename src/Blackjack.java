import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack 
{
	
	public static int getValueOfDeck(ArrayList<Card> givenDeck)
    {

        int aceCounter = 0;
        for (int i = 0; i <= givenDeck.size()-1; i++)
        {
            if (givenDeck.get(i).getRank().equals(Card.Rank.Ace))
            {
                aceCounter++;
            }

        }

        int nonAceCounter = 0;
        for (int i = 0; i <= givenDeck.size()-1; i++)
        {
            if (givenDeck.get(i).getRank().equals(Card.Rank.Ace) == false)
            {
                nonAceCounter = nonAceCounter + givenDeck.get(i).getRank().getValue();
            }
        }

       int aceValue = 0;
       
        if (aceCounter == 1 && nonAceCounter > 10)
        {
            aceValue = 1;
        }
        else if (aceCounter == 1)
        {
            aceValue = 11;
        }
        else if (aceCounter == 2)
        {
            aceValue = 1 + 11;
        }
        else if (aceCounter == 3)
        {
            aceValue = 1 + 1 + 11;
        }
        else if (aceCounter == 4)
        {
            aceValue = 1 + 1 + 1 + 11;
        }



        return nonAceCounter + aceValue;

    }

	public static void main (String args [])
	{
		
		System.out.println("Let's play Blackjack!");
		Scanner in = new Scanner(System.in);
		
		Blackjack t1 = new Blackjack();
		
		ArrayList <Card> user = new ArrayList<Card>();
		ArrayList <Card>computer = new ArrayList<Card>();
		
		CardDeck mainDeck = new CardDeck();
		
		mainDeck.shuffle(); 
		
		user.add(mainDeck.deck.get(0));//where index 0 is the top of the deck
		Card nextCard = mainDeck.nextCard();
		user.add(mainDeck.deck.get(1));
		nextCard = mainDeck.nextCard();
		
		computer.add(mainDeck.deck.get(2));
		nextCard = mainDeck.nextCard();
		computer.add(mainDeck.deck.get(3));
		nextCard = mainDeck.nextCard();
		
		System.out.println();
		System.out.println("Your deck: [" + user.get(0).getRank() + " of " + user.get(0).getSuit() +"s" + " and " + user.get(1).getRank() + " of " + user.get(1).getSuit() +"s]");
		System.out.println();
		System.out.println("Opponent deck: [" + computer.get(0).getRank() + " of " + user.get(0).getSuit() +"s" + " and "+ " Unknown card]");

		
		boolean userGameIsGoing = true;
		
		while(userGameIsGoing)
		{
			System.out.println();
			System.out.println("Your deck value is: " + getValueOfDeck(user));
			System.out.println();
			System.out.print("Hit or Stay? ");
			
			String userAnswer = in.next();
			System.out.println();
			
			if (userAnswer.toLowerCase().equals("stay"))
			{
				userGameIsGoing = false;
			}
			else if (userAnswer.toLowerCase().equals("hit"))
			{
				Card next = mainDeck.nextCard();
				
				user.add(next);
				
				System.out.println("You got a: " + next);
				if(getValueOfDeck(user) > 21)
					userGameIsGoing = false;
			}
		}
			
			if(getValueOfDeck(user) > 21)
			{
				System.out.println(" YOU LOSE! Your hit went over 21!");
				System.exit(0);
			}
			else if (getValueOfDeck(user) <= 21)
			{
				System.out.println("Your deck value is " + getValueOfDeck(user));
			}
				
			boolean dealerGameIsGoing = true;
			
			while(dealerGameIsGoing)
			{
				if(getValueOfDeck(computer) >= 17)
					dealerGameIsGoing = false;
				else
				{
					nextCard = mainDeck.nextCard();
					computer.add(nextCard);
				}
			}
			
			System.out.println("The dealer deck total value: " + getValueOfDeck(computer));
			System.out.println("The dealer's cards: " + computer);
			
			
			if(getValueOfDeck(user) > getValueOfDeck(computer) || getValueOfDeck(computer) > 21)
			{
				System.out.println();
				System.out.println("$$$$$$$$$$$$$$$$$$$$");
				System.out.println("***** You Win! *****");
				System.out.println("$$$$$$$$$$$$$$$$$$$$");

			}
			else if(getValueOfDeck(computer) == getValueOfDeck(user))
			{
				System.out.println();
				System.out.println("You tied! So Close!");
			}
			
			else if(getValueOfDeck(computer) > getValueOfDeck(user) || getValueOfDeck(user) > 21)
			{
				System.out.println();
				System.out.println("You Lose! Better Luck Next Time! ");
			}

		
			
		}
		
		
	}

