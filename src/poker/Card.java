package poker;

public class Card {
	
	//Initialization
	private int value;
	
	private int suit;
	
	//constructor
	public Card(int cvalue, int csuit){
		value = cvalue;
		suit = csuit;
	}
	
	//get methods
	protected int getValue(){
		return value;
	}
	
	protected int getSuit(){
		return suit;
	}
}
