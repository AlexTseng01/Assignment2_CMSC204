import java.util.ArrayList;

public class Notation {
	
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		MyStack<String> stack = new MyStack<String>(10);
		char[] postfixToArray = postfixExpr.toCharArray();
		double answer = 0;
		
		try {
			for (int i = 0; i < postfixExpr.length(); i++) {
				if (postfixToArray[i] == ' ') {
					continue;
				}
				else if (Character.isDigit(postfixToArray[i])) {
					stack.push(postfixToArray[i] + "");
				}
				else if (postfixToArray[i] == '+') {
					// check if invalid arithmetic expression
					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					
					// pop the top 2 values from stack
					String a = stack.pop();
					String b = stack.pop();
					
					answer = Double.parseDouble(b) + Double.parseDouble(a);
					stack.push(answer + "");
				}
				else if (postfixToArray[i] == '-') {
					// check if invalid arithmetic expression
					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					
					// pop the top 2 values from stack
					String a = stack.pop();
					String b = stack.pop();
					
					answer = Double.parseDouble(b) - Double.parseDouble(a);
					stack.push(answer + "");
				}
				else if (postfixToArray[i] == '/') {
					// check if invalid arithmetic expression
					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					
					// pop the top 2 values from stack
					String a = stack.pop();
					String b = stack.pop();
					
					answer = Double.parseDouble(b) / Double.parseDouble(a);
					stack.push(answer + "");
				}
				else if (postfixToArray[i] == '*') {
					// check if invalid arithmetic expression
					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					
					// pop the top 2 values from stack
					String a = stack.pop();
					String b = stack.pop();
					
					answer = Double.parseDouble(b) * Double.parseDouble(a);
					stack.push(answer + "");
				}
			}
			// if there is one operand in stack, return that operand
			if (stack.size() == 1) {
				return Double.parseDouble(stack.pop());
			}
			else if (stack.size() > 1) {
				throw new InvalidNotationFormatException();
			}
		}
		catch (StackOverflowException e) {
			e.printStackTrace();
		}
		catch (StackUnderflowException e) {
			e.printStackTrace();
		}
		
		return answer;
	}
	 
	// postfix -> infix
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		MyStack<String> stack = new MyStack<String>(10);
		char[] postfixToArray = postfix.toCharArray();
		String infix = "";
		
		try {
			for (int i = 0; i < postfix.length(); i++) {
				if (postfixToArray[i] == ' ') {
					continue;
				}
				else if (Character.isDigit(postfixToArray[i])) {
					stack.push(postfixToArray[i] + "");
				}
				else if (postfixToArray[i] == '+' || 
						postfixToArray[i] == '-' || 
						postfixToArray[i] == '/' || 
						postfixToArray[i] == '*') {
					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					else {
						// 1st & 2nd value
						String a = stack.pop();
						String b = stack.pop();
						
						infix = "(" + b + postfixToArray[i] + a + ")";
						stack.push(infix);
					}
				}
			}
		}
		catch (StackOverflowException e){
			e.printStackTrace();
		}
		catch (StackUnderflowException e){
			e.printStackTrace();
		}
		
		return infix;
	}
	
	// infix -> postfix
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		MyStack<String> stack = new MyStack<String>(15);
		MyQueue<String> queue = new MyQueue<String>(15);
		char[] infixToArray = infix.toCharArray();
		
		// temporary fix to invalid infix expression
		int leftCount = 0;
		int rightCount = 0;
		for (int i = 0; i < infixToArray.length; i++) {
			if (infixToArray[i] == '(') {
				leftCount++;
			}
			else if (infixToArray[i] == ')') {
				rightCount++;
			}
		}
		if (leftCount != rightCount) {
			throw new InvalidNotationFormatException();
		}
		
		try {
			for (int i = 0; i < infix.length(); i++) {
				// filters out exclusively digits and handles left parenthesis
				if (infixToArray[i] == ' ') {
					continue;
				}
				else if (Character.isDigit(infixToArray[i])) {
					queue.enqueue(infixToArray[i] + "");
				}
				else if (infixToArray[i] == '(') {
					stack.push(infixToArray[i] + "");
				}
				else if (infixToArray[i] == '+' || infixToArray[i] == '-') {
					if (!stack.isEmpty()) {
						// while operators have equal or higher precendence
						while(stack.top().equals("+") || stack.top().equals("-") || stack.top().equals("/") || stack.top().equals("*")) {
							queue.enqueue(stack.pop());
						}
					}
					// push current character into stack
					stack.push(infixToArray[i] + "");
				}
				else if (infixToArray[i] == '/' || infixToArray[i] == '*') {
					if (!stack.isEmpty()) {
						// while operators have equal or higher precendence, in this case, equal precendence
						while(stack.top().equals("/") || stack.top().equals("*")) {
							queue.enqueue(stack.pop());
						}
					}
					// push current character into stack
					stack.push(infixToArray[i] + "");
				}
				else if (infixToArray[i] == ')') {
					// keeps popping off stack and inserting to queue as long as no left parenthesis is met
					while(!stack.top().equals("(")) {
						queue.enqueue(stack.pop());
					}
					// if no left parenthesis, throw exception
					if (!stack.top().equals("(")) {
						throw new InvalidNotationFormatException();
					}
					// discards left parenthesis
					stack.pop();
				}
			}
		}
		catch (StackOverflowException e) {
			e.printStackTrace();
		}
		catch (StackUnderflowException e) {
			e.printStackTrace();
		}
		catch (QueueOverflowException e) {
			e.printStackTrace();
		}
		
		return queue.toString();
	}
	
}
