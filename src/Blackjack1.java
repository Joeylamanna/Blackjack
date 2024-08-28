import java.util.*;

public class Blackjack1 {
    
	
	public static int countValue(ArrayList<Card> hand){
		int toReturn = 0;
		int aces = 0;
		for(Card c : hand){
			if(c.getRank().equals(Card.Rank.Ace))
				aces++;
		}
		
		if(aces == 0){
			for(Card c : hand){
				toReturn += c.getRank().getValue();
			}
		}
		else{
			int[] possibleSums = new int[2];
			for(Card c : hand){
				if(!c.getRank().equals(Card.Rank.Ace))
					toReturn += c.getRank().getValue();
			}
			possibleSums[0] = 11 + toReturn + (aces - 1);
			possibleSums[1] = toReturn + (aces);
			if(possibleSums[0] <= 21)
				toReturn = possibleSums[0];
			else
				toReturn = possibleSums[1];
		}
		
		
		return toReturn;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Blackjack!");
		
		ArrayList<Card> myHand = new ArrayList<>();
		ArrayList<Card> dealer = new ArrayList<>();
		CardDeck deck = new CardDeck();
		
		
		//deal the cards
		System.out.println("Shuffling and dealing");
		deck.shuffle();
		
		for(int i = 0;i < 2;i++){
			Card newCard = deck.nextCard();
			myHand.add(newCard);
			newCard = deck.nextCard();
			dealer.add(newCard);
		}
		System.out.println("Your hand: " + myHand.get(0) + " and " + myHand.get(1));
		System.out.println("The dealer: " + dealer.get(0));
		
		//play my hand
		boolean playing = true;
		Scanner scan = new Scanner(System.in);
		char choice;
		while(playing){
			System.out.println("Your current hand is best valued at: " + countValue(myHand));
			System.out.println("Hit (H) or Stay (S)?"); 
			choice = scan.nextLine().charAt(0);
			if(choice == 'S' || choice == 's'){
				playing = false;
			}
			else{
				Card next = deck.nextCard();
				myHand.add(next);
				System.out.println("You drew: " + next);
				if(countValue(myHand) > 21)
					playing = false;
			}
		}
		
		//Check to see if the player busted
		if(countValue(myHand) > 21){
			System.out.println("You went over.  You lose!");
			System.exit(0);
		}
		else{
			System.out.println("Your current hand is worth: " + countValue(myHand));
		}
		
		//play the dealer hand
		boolean dealerPlaying = true;
		
		while(dealerPlaying){
			if(countValue(dealer) >= 17){
				dealerPlaying = false;
			}
			else{
				Card next = deck.nextCard();
				dealer.add(next);
			}
		}
		
		System.out.println("The dealer's hand is worth: " + countValue(dealer));
		System.out.println("The dealer's cards: "+dealer);
		
		
		//Determine the winner
		if(countValue(myHand) == countValue(dealer)){
			System.out.println("You've tied!");
		}
		else if(countValue(myHand) > countValue(dealer) || countValue(dealer) > 21){
			System.out.println("You've won! Congratulations!");
		}
		else{
			System.out.println("You lose!  The House always wins!");
		}

	}

}