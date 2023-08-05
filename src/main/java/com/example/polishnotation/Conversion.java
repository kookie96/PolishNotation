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
            // Display if expression is empty
            if (exp.equals("")) {
                return "Enter non-empty expression.";
            }

            // Remove spaces in expression
            exp = exp.replace(" ", "");

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
            return e.getMessage();
        }
    }

    // 3. Infix to Prefix by reversing string (recursion)
    public String reverseString(String str) {
        if (str.length() == 1 || str.length() == 0) {
            return str;
        } else {
            return reverseString(str.substring(1)) + str.charAt(0);
        }
    }
    public String InfixToPrefix(String exp) {
        try {
            // Reverse given expression
            String reverseExp = reverseString(exp);

            // Display if expression is empty
            if (reverseExp.equals("")) {
                return "Enter non-empty expression.";
            }

            // Remove spaces in expression
            reverseExp = reverseExp.replace(" ", "");

            // Initializing empty string to store result
            String result = "";

            // Initializing empty stack
            Stack<Character> myStack = new Stack<>();

            // Traverse through string
            for (int i = 0; i < reverseExp.length(); i++) {
                char ch = reverseExp.charAt(i);

                // If character is an operand, append to result string
                if (Character.isLetterOrDigit(ch)) {
                    result += ch;
                }

                // If character is ')', push onto the stack
                else if (ch == ')') {
                    myStack.push(ch);
                }

                // If character is ')', pop off all operators and append
                // them to result string until you hit '('. Then,
                // discard '('.
                else if (ch == '(') {
                    while (!myStack.empty() && myStack.peek() != ')') {
                        result += myStack.peek();
                        myStack.pop();
                    }

                    // Discard ')' after while-loop
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

            // Reverse string once again and return result
            return (reverseString(result));

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //3.  Method to check if character is an operand
    public boolean isOperand(char x) {
        return (x >= 'a' && x <= 'z') ||
                (x >= 'A' && x <= 'Z') ||
                (x >= '0' && x <= '9');
    }
    // Postfix to Infix
    public String PostfixToInfix(String postExp) {
        try {
            // Initializing empty string stack
            Stack<String> strStck = new Stack<>();

            // Traverse through postfix expression
            for (int i = 0; i < postExp.length(); i++) {
                // Push operands onto stack
                if (isOperand(postExp.charAt(i))) {
                    strStck.push(String.valueOf(postExp.charAt(i)));
                }

                // Otherwise, deal with operator
                else {
                    String op1 = strStck.peek();
                    strStck.pop();
                    String op2 = strStck.peek();
                    strStck.pop();
                    strStck.push("(" + op2 + postExp.charAt(i) +
                            op1 + ")");
                }
            }

            // There must be a single element in stack now which
            // is the infix expression.
            return strStck.peek();
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    // Prefix to Infix



    // Postfix to Prefix



    // Prefix to Postfix
}
