package ssa;

import java.util.Stack;

public class RPNCalculator {
	
	public static void main(String[] args) {
		char[] problem = new char[] {'2','9','*','3','/','7','+'};
			
		/*
		 * RPNStack constructor can 
		 * be called with either
		 * a String or a char[]
		 */
		RPNStack rpn = new RPNStack(problem);
		System.out.println(rpn);
	}
}

class RPNStack {

	private Stack<String> s;
	private String inp;
	
	public RPNStack(String inp) {
		s = new Stack<String>();
		this.inp = inp;
	}
	
	
	public String solve() {
		for (int i=0;i<inp.length(); i++) {
			if(isOperator(inp.substring(i,i+1))) {
				s.push(evaluate(inp.substring(i,i+1)));
			} else {
				s.push(inp.substring(i,i+1));
			}
		}
		return "------------------------------\nRPN expression evaluates to: " + s.pop();
	}

	public RPNStack(char[] c) {
		this(String.copyValueOf(c));
	}
	
	private boolean isOperator(String s) {
		return s.equals("+") || s.equals("-") || 
				s.equals("*") || s.equals("/");
	}
	
	private String evaluate(String operator) {
		
		int op2 = Integer.parseInt(s.pop());
		int op1 = Integer.parseInt(s.pop());
		int answer = 0;
		
		switch (operator) {
		case "+" :
			answer = op1 + op2;
			break;
		case "-" :
			answer = op1 - op2;
			break;
		case "/" :
			answer = op1 / op2;
			break;
		case "*" :
			answer = op1 * op2;
			break;
	}
		System.out.println("Calculating " + op1 + "," + op2 + "," + operator +
				" Answer is " + answer);
		
		return answer + "";
	}
	
	public String toString() {
		return solve();
	}
}
