package poker;

import java.util.ArrayList;
import java.util.Collections;

public class Deck{
	
	//Initialization
	private ArrayList<Card> deck = new ArrayList<Card>();
	
	//Constructor
	public Deck(){
		int[] suitList = {1,2,3,4};
		int[] valueList = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		for (int suit : suitList){
			for (int value : valueList){
				deck.add(new Card(value,suit)); 
			}
		}
	}
	
	//shuffle method
	public void shuffle(){
		Collections.shuffle(deck);
	}
	
	//draw method
	public Card draw(){
		Card firstCard = deck.get(0);
		deck.remove(0);
		return firstCard;
	}
	
	//cardsLeft method
	public int cardsLeft(){
		return deck.size();
	}
}
