package com.example.polishnotation;

import java.util.Stack;

public class Conversion {
    //1. Method to return precedence of any given operator
    public int Prec(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1; // error
    }

    // 2. Convert Infix to Postfix
    public String InfixToPostfix(String exp) {
        try {
            // Initializing empty string to store result
            String result = "";

            // Initializing empty stack
            Stack<Character> myStack = new Stack<>();

            // Traverse through string
            for (int i = 0; i < exp.length(); i++) {
                char ch = exp.charAt(i);

                // If character is an operand, append to result string
                if (Character.isLetterOrDigit(ch)) {
                    result += ch;
                }

                // If character is '(', push onto the stack
                else if (ch == '(') {
                    myStack.push(ch);
                }

                // If character is ')', pop off all operators and append
                // them to result string until you hit '('. Then,
                // discard '('.
                else if (ch == ')') {
                    while (!myStack.empty() && myStack.peek() != '(') {
                        result += myStack.peek();
                        myStack.pop();
                    }

                    // Discard '(' after while-loop
                    myStack.pop();
                }

                // An operator is encountered
                else {
                    // Pop off all operators that have EQUAL OR HIGHER PRECEDENCE
                    // than current operator and append them to result string
                    while (!myStack.empty() && (Prec(myStack.peek()) >= Prec(ch))) {
                        result += myStack.peek();
                        myStack.pop();
                    }

                    // Finally, push current operator onto stack
                    myStack.push(ch);
                }
            }

            // Pop all operators from the stack once you
            // reach end of input string
            while (!myStack.isEmpty()) {
                if (myStack.peek() == '(') {
                   return "Invalid Expression!";
                }
                result += myStack.peek();
                myStack.pop();
            }

            // Return result string
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }

        return "";
    }

    // 3. Infix to Prefix

}
