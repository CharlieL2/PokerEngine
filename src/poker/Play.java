package poker;

public class Play {
	public static void main(String[] args) {
		// Initialization
		Deck playDeck = new Deck();
		playDeck.shuffle();

		Hand playerHand = new Hand();
		playerHand.drawNewHand(playDeck);

		Hand computerHand = new Hand();
		computerHand.drawNewHand(playDeck);

		// Playing the game
		playerHand.evaluate();
		int[] playerScore = playerHand.getEvaluation();

		computerHand.evaluate();
		int[] computerScore = computerHand.getEvaluation();

		int[] genList = { 0, 1, 2, 3 };
		for (int num : genList) {
			if (playerScore[num] > computerScore[num]) {
				System.out.println("Win");
				break;
			} else if (playerScore[num] < computerScore[num]) {
				System.out.println("Lose");
				break;
			}
			else if (num == 3){
				System.out.println("Draw");
				break;
			}
		}
		System.out.println(playerHand.hand[0].getValue()+ " " +playerHand.hand[1].getValue()+ " " +playerHand.hand[2].getValue()+ " " +playerHand.hand[3].getValue()+ " " +playerHand.hand[4].getValue());
		System.out.println(playerScore[0]+ " " +playerScore[1]+ " " +playerScore[2]+ " " +playerScore[3]);
		
		System.out.println(computerScore[0] + " " + computerScore[1] + " " + computerScore[2] + " " + computerScore[3]);
	}
}
