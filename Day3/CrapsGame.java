package diceGame;

import java.util.Random;
import java.util.Scanner;

public class CrapsGame {

	public static void main(String[] args) {
		CrapsRoll cr = new CrapsRoll();
		int point = 0;
		String userInput = "";
		Scanner scan = new Scanner(System.in);
		boolean frontlineWinner = false;
		
		do {
			cr.roll();
			frontlineWinner = false;
			if (cr.is7()) {
				System.out.println("Frontline Winner 7!");
				frontlineWinner = true;
			} else if (cr.isCrap()) {
				System.out.println(cr.getTotal() + "Crap, line away!");
			} else {
				point = cr.getTotal();
				System.out.println("Point of " + point + " established");
				do {
					cr.roll();
				} while (cr.getTotal() != 7 && cr.getTotal() != point);
			}
			if (cr.is7()) {
				if (!frontlineWinner) System.out.println("7 Out!");
			} else if(!cr.isCrap()){
				System.out.println("Winner " + point + "!");
			}
	
			System.out.println("Continue y/n: ");
			userInput = scan.nextLine();
			userInput = userInput.trim();
		} while (!userInput.equalsIgnoreCase("n"));
		
	scan.close();
	}

}

class CrapsRoll {

	private Dice d;
	private int roll1;
	private int roll2;
	
	public CrapsRoll() {
		d = new Dice();
	}
	
	public void roll() {
		roll1 = d.roll();
		roll2 = d.roll();
		System.out.println(this);
	}
	
	public int getRoll1() {
		return roll1;
	}
	
	public int getRoll2() {
		return roll2;
	}
	
	public int getTotal() {
		return roll1 + roll2;
	}
	
	public boolean is7() {
		return getTotal()==7;
	}
	
	public boolean isCrap() {
		return (getTotal() == 2 || getTotal() == 3 || getTotal() == 12);
	}
	
	public String toString() {
		String dice1 = d.graphicDice(roll1);
		String dice2 = d.graphicDice(roll2);
		return dice1.substring(0,5) + " " + dice2.substring(0,5) + '\n' +
			   dice1.substring(6,11) + " " + dice2.substring(6,11) + '\n' +
			   dice1.substring(12,17) + " " + dice2.substring(12,17) + '\n' +
			   dice1.substring(18,23) + " " + dice2.substring(18,23) + '\n' +
			   dice1.substring(24,29) + " " + dice2.substring(24,29) + '\n';
	}
}

class Dice {
	
	private Random rn;
	
	public Dice() {
		rn = new Random();
	}
	
	public int roll() {
		return rn.nextInt(6) + 1;
	}
	
	public String graphicDice(int value) {
		switch (value) {
		case 1:
			return "-----\n" +
			       "|   |\n" +
			       "| * |\n" +
			       "|   |\n" +
			       "-----\n";
		case 2:
			return "-----\n" +
			       "|*  |\n" +
			       "|   |\n" +
			       "|  *|\n" +
			       "-----\n";
		case 3:
			return "-----\n" +
			       "|*  |\n" +
			       "| * |\n" +
			       "|  *|\n" +
			       "-----\n";
		case 4:
			return "-----\n" +
			       "|* *|\n" +
			       "|   |\n" +
			       "|* *|\n" +
			       "-----\n";
		case 5:
			return "-----\n" +
			       "|* *|\n" +
			       "| * |\n" +
			       "|* *|\n" +
			       "-----\n";
		case 6:
			return "-----\n" +
			       "|* *|\n" +
			       "|* *|\n" +
			       "|* *|\n" +
			       "-----\n";
		}
		return "";
	}
}
