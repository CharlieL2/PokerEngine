package poker;

public class Tester {
	public static void main(String[] args){
		Deck d = new Deck();
		d.shuffle();
		Card a = d.draw();
		//gets value and suit of drawn card
		System.out.println(a.getSuit());
		System.out.println(a.getValue());
		//cards left, should be 51
		System.out.println(d.cardsLeft());
	}
}
