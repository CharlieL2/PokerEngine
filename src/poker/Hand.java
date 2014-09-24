package poker;

import java.util.Arrays;

/*
 * Note: This code was configured to use arrays but it is way too much work to be able to handle
 * certain high card possibilities with just arrays. It is much more possible with ArrayList, but I
 * would then be essentially be copying your lab code due to having to refactor the whole class.
 * So I just turned in this flawed class.
 */
public class Hand {
	public Card[] hand;

	// Constructor
	public Hand() {
		hand = new Card[5];
	}

	// Draw five cards. Returns deck after drawing cards
	// CL: It does not need to return the deck
	public void drawNewHand(Deck deck) {
		for (int i = 0; i < 5; i++) {
			hand[i] = deck.draw();
		}

	}

	/*
	 * int[] handValues = { hand[0].getValue(), hand[1].getValue(),
	 * hand[2].getValue(), hand[3].getValue(), hand[4].getValue() }; int[]
	 * handSuits = { hand[0].getSuit(), hand[1].getSuit(), hand[2].getSuit(),
	 * hand[3].getSuit(), hand[4].getSuit() };
	 */

	private int[] organize() { // organize card values, ace high card (1) }
		int[] handValues = { hand[0].getValue(), hand[1].getValue(),
				hand[2].getValue(), hand[3].getValue(), hand[4].getValue() };
		Arrays.sort(handValues);
		return handValues;
	}

	// Evaluation // Evaluate datatype

	/*
	 * There are 4 categories for evaluation 1. Hand i.e. Royal Flush = 10 2.
	 * and high card 3. Hand low card 4. Hand left over high card
	 */

	private int[] myEvaluation = new int[4];

	// CL :getter for myEvaluation

	protected int[] getEvaluation() {
		return myEvaluation;
	}

	private int getNumberOfSuits(int suit) {
		int num = 0;
		for (int i = 0; i < 5; i++) {
			if (hand[i].getSuit() == suit) {
				num += 1;
			}
		}
		return num;
	}

	private int getNumberOfValues(int val) {
		int num = 0;
		for (int i = 0; i < 5; i++) {
			if (hand[i].getValue() == val) {
				num += 1;
			}
		}
		return num;
	}

	// Check methods

	private void checkRoyalFlush() {
		int suit = hand[0].getSuit();
		for (int i = 0; i <= 4; i++) {
			Card card = hand[i];
			if (card.getSuit() != suit) {
				break;
			}
			if (card.getValue() == (13 - i)) {
				if (i == 4 && card.getValue() == 10) {
					myEvaluation[0] = 10;
					myEvaluation[1] = 14;
					myEvaluation[2] = 10;
					// myEvaluation[3] = 0; CL: no need since checkHighCard sets
					// this value already
				}
				continue;
			} else {
				break;
			}
		}
	}

	private void checkStraightFlush() {
		int[] handValues = organize();
		for (int i = 0; i <= 4; i++) {
			if (getNumberOfSuits(i) == 5) {//check flush
				if (handValues[4] == handValues[3] + 1//check straight
						&& handValues[3] == handValues[2] + 1
						&& handValues[2] == handValues[1] + 1
						&& handValues[1] == handValues[0] + 1) {
					myEvaluation[0] = 9;
					myEvaluation[1] = handValues[0];
					myEvaluation[2] = handValues[1];
				}

				else if (handValues[0] == 1) { // to handle aces
					handValues[0] = 14;
					if (handValues[0] == handValues[4] + 1
							&& handValues[4] == handValues[3] + 1
							&& handValues[3] == handValues[2] + 1
							&& handValues[2] == handValues[1] + 1) {
						myEvaluation[0] = 5;
						myEvaluation[1] = handValues[0];
						myEvaluation[2] = handValues[1];
					}
				}
			}
		}
	}

	// need kicker work
	private void checkFourOfAKind() {
		for (int i = 1; i <= 13; i++) {
			if (getNumberOfValues(i) == 4) {
				myEvaluation[0] = 8;
				myEvaluation[1] = i;
				myEvaluation[2] = 0;
				break;
			}
		}
	}

	private void checkFullHouse() {
		for (int i = 1; i <= 13; i++) {
			for (int b = 1; b <= 13; b++)
				if (getNumberOfValues(i) == 3 && getNumberOfValues(b) == 2
						&& b != i) {
					myEvaluation[0] = 7;
					if (i == 1)
						myEvaluation[1] = 14;
					else
						myEvaluation[1] = i;

					if (b == 1)
						myEvaluation[2] = 14;
					else
						myEvaluation[2] = b;
					myEvaluation[3] = 0;
				}
		}
	}

	private void checkFlush() {
		for (int i = 1; i <= 4; i++) {
			if (getNumberOfSuits(i) == 5) {
				myEvaluation[0] = 6;
				myEvaluation[1] = 0;// doesn't matter it's a flush just check
									// high card
				myEvaluation[2] = 0;

				break;
			}
		}
	}

	private void checkStraight() {
		int[] handValues = organize();
		if (handValues[4] == handValues[3] + 1
				&& handValues[3] == handValues[2] + 1
				&& handValues[2] == handValues[1] + 1
				&& handValues[1] == handValues[0] + 1) {
			myEvaluation[0] = 5;
			myEvaluation[1] = handValues[4];
			myEvaluation[2] = handValues[0];

		} else if (handValues[0] == 1) { // to handle aces
			handValues[0] = 14;
			if (handValues[0] == handValues[4] + 1
					&& handValues[4] == handValues[3] + 1
					&& handValues[3] == handValues[2] + 1
					&& handValues[2] == handValues[1] + 1) {
				myEvaluation[0] = 5;
				myEvaluation[1] = handValues[0];
				myEvaluation[2] = handValues[1];
			}
		}

	}

	// needs kicker work
	private void checkThreeOfAKind() {
		for (int i = 1; i <= 13; i++) {
			if (getNumberOfValues(i) == 3) {
				myEvaluation[0] = 4;
				if (i == 1) {// handle aces
					myEvaluation[1] = 14;
					myEvaluation[2] = 0;
				} else {
					myEvaluation[1] = i;
					myEvaluation[2] = 0;
				}

			}
		}
	}

	// needs work on aces
	private void checkTwoPair() {
		for (int i = 1; i <= 13; i++) {
			for (int b = 1; b <= 13; b++) {
				if (getNumberOfValues(i) == 2 && getNumberOfValues(b) == 2
						&& b != i) {
					myEvaluation[0] = 3;
					if (b > i) {
						myEvaluation[1] = b;
						myEvaluation[2] = i;
					} else {
						myEvaluation[1] = i;
						myEvaluation[2] = b;
					}
				}
			}
		}
	}

	// needs kicker work
	private void checkOnePair() {
		for (int i = 1; i <= 13; i++) {
			if (getNumberOfValues(i) == 2) {
				myEvaluation[0] = 2;
				if (i == 1) {// handle aces
					myEvaluation[1] = 14;
					myEvaluation[2] = 0;
				} else {
					myEvaluation[1] = i;
					myEvaluation[2] = 0;
				}
			}
		}
	}

	private void checkHighCard() {
		int highCard = 0;
		for (int i = 0; i < 5; i++) {
			Card card = hand[i];
			if (card.getValue() > highCard) {
				highCard = card.getValue();
			} else if (card.getValue() == 1) {
				highCard = 14;
				break;
			}
		}
		myEvaluation[3] = highCard;
	}

	public void evaluate() {
		checkHighCard();
		checkOnePair();
		checkTwoPair();
		checkThreeOfAKind();
		checkStraight();
		checkFlush();
		checkFullHouse();
		checkFourOfAKind();
		checkStraightFlush();
		checkRoyalFlush();
	}

	public Hand evaluate(Hand[] hands) {
		int winner = 0;
		for (int i = 0; i < hands.length; i++) {
			for (int n = 0; n < hands.length; n++) {
				if (n == i) {
					continue;
				}
				if (hands[i].myEvaluation[0] <= hands[n].myEvaluation[0]) {
					break;
				} else if (n == 4) {
					winner = i;
				}
			}
		}
		return hands[winner];
	}

}
