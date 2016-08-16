package Day2;

import java.util.Random;

public class Day2 {

	public static void main(String[] args) {
		DiceGame dg = new DiceGame(6,2);
		int[] score = new int[2]; //score[0] = score of game, score[1] = number of rolls
		int highScore = 0;
		int lowScore = 1000;
		int maxRolls = 0;
		
		score[0] = 0;
		score[1] = 0;
		
		for(int i = 1;i<=100;i++) {
			score = dg.playGame();
			if (score[0] > highScore) {
				highScore = score[0];
			} else {
				if (score[0] < lowScore) {
					lowScore = score[0];
				}
			}
			if (score[1] > maxRolls) {
				maxRolls = score[1];
			}
			System.out.println("Game" + i + ":");
			System.out.println("Score: " + score[0]);
			System.out.println("Rolls: " + score[1]);
		}
		
		System.out.println("High Score: " + highScore);
		System.out.println("Low Score:  " + lowScore);
		System.out.println("Max Rolls:  " + maxRolls);
	}

}

class DiceGame {
	private Dice dice;
	private int numDice;
	
	public DiceGame(int numSides, int numDice) {
		this.numDice = numDice;
		dice = new Dice(numSides);
	}
	
	public int[] playGame() {
		int[] score = new int[2];
		score[0] = 0; //Running score of the dice
		score[1] = 1; //Running roll count of the dice
		if (numDice == 1) {
			int roll = dice.roll();
			while (roll != 1) {
				score[0] += roll;
				roll = dice.roll();
				score[1]++;
			}
		} else {
			int roll1 = dice.roll();
			int roll2 = dice.roll();
			while ((roll1+roll2) != 7) {
				score[0] = score[0] + roll1 + roll2;
				roll1 = dice.roll();
				roll2 = dice.roll();
				score[1]++;
			}
		}
		return score;
	}
}

class Dice {
	
	private int maxSides;
	private Random rn;
	
	public Dice(int maxSides) {
		this.maxSides = maxSides;
		rn = new Random();
	}
	
	public int roll() {
		return rn.nextInt(maxSides) + 1;
	}
}
