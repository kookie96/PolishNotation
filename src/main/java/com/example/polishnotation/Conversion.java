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
                    // Pop off all operators that have ONLY HIGHER PRECEDENCE
                    // than current operator and append them to result string
                    while (!myStack.empty() && (Prec(myStack.peek()) > Prec(ch))) {
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
    // 4. Postfix to Infix
    public String PostfixToInfix(String postExp) {
        try {
            // Initializing empty string stack
            Stack<String> strStck = new Stack<>();

            // Traverse through postfix expression
            for (int i = 0; i < postExp.length(); i++) {
                // If character is a space, go to next character
                if (postExp.charAt(i) == ' ') {
                    // do nothing and go to next iteration
                }

                // Push operands onto stack
                else if (isOperand(postExp.charAt(i))) {
                    String str = String.valueOf(postExp.charAt(i));

                    // Checking to see if operand is not a single digit; ex. 10
                    while (postExp.charAt(i + 1) != ' ') {
                        str = str + postExp.charAt(i + 1);
                        i++;
                    }
                    strStck.push(str);
                }

                // Otherwise, deal with operator
                else {
                    String op1 = strStck.peek();
                    strStck.pop();
                    String op2 = strStck.peek();
                    strStck.pop();
                    strStck.push("(" + op2 + postExp.charAt(i) +
                            op1 + ")");
                    // |     |
                    // |__5__|--> op1    operator = '+' ---> (op2 + op1) ---> (2+5)
                    // |__2__|--> op2
                }
            }

            // There must be a single element in stack now which
            // is the infix expression.
            return strStck.pop();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // 5. Prefix to Infix by reading expression from right to left
    public String PrefixToInfix(String prefixExp) {
        // Initializing empty string stack
        Stack<String> strStack = new Stack<>();

        for (int i = prefixExp.length() - 1; i >= 0; i--) {
            // If character is a space, go to next character
            if (prefixExp.charAt(i) == ' ') {
                // do nothing and go to next iteration
            }
            // Push operands onto the stack
            else if (isOperand(prefixExp.charAt(i))) {
                String str = String.valueOf(prefixExp.charAt(i));

                // Checking to see if operand is not a single digit; ex. 10
                while (prefixExp.charAt(i - 1) != ' ') {
                    str = prefixExp.charAt(i - 1) + str;
                    i--;
                }
                strStack.push(str);
            }

            //Otherwise, deal with operators
            else {
                String op1 = strStack.peek();
                strStack.pop();
                String op2 = strStack.peek();
                strStack.pop();
                strStack.push("(" + op1 + prefixExp.charAt(i) +
                        op2 + ")");
                // |     |
                // |__5__|--> op1  operator = '+'---> (op1 + op2) ---> (5+2)
                // |__2__|--> op2
            }
        }
        // There must be a single element in stack now which
        // is the infix expression.
        return strStack.pop();
    }

    // 6. Postfix to Prefix
    public String PostfixToPrefix(String postExp) {
        // Postfix -> Infix -> Prefix
        String infixExp = PostfixToInfix(postExp);
        return InfixToPrefix(infixExp);
    }


    // 7. Prefix to Postfix
    public String PrefixToPostfix(String prefixExp) {
        // Prefix -> Infix -> Postfix
        String infixExp = PrefixToInfix(prefixExp);
        return InfixToPostfix(infixExp);
    }
}
