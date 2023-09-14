package com.example.polishnotation;

import java.util.Stack;
import java.lang.Math;

public class Evaluation {
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
    //2.  Method to check if character is an operand
    public boolean isNumber(char x) {
        return (x >= '0' && x <= '9');
    }

    //3. Method to check if character is an operator
    public boolean isOperator(char y) {
        return (y == '+' || y == '-' ||
                y == '*' || y == '/' ||
                y == '^');
    }

    // 4. Method to apply operators
    public int applyOperator(char op, int n1, int n2) {
        switch (op) {
            case '+':
                return n2 + n1;
            case '-':
                return n2 - n1;
            case '*':
                return n2 * n1;
            case '/':
                return n2 / n1;
            case '^':
                return (int) Math.pow(n2, n1);
            default:
                return 0;
        }
    }

    // 5. Evaluate arithmetic (infix) expression
    public String evaluateArithmetic(String exp) {
        // Initialize two stacks: integers and characters
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> ops = new Stack<>();

        // Display if expression is empty
        if (exp.equals("")) {
            return "Enter non-empty expression.";
        }

        // Traverse through expression
        for (int i = 0; i < exp.length(); i++) {
            // If character is a space, go to next character
            if (exp.charAt(i) == ' ') {
                // do nothing and go to next iteration
            }

            // If character is number, push number onto nums stack
            else if (isNumber(exp.charAt(i))) {
                // Create an empty string to put all digits
                String str = "";
                /*
                // ORIGINAL WAY
                // Turn character into string
                String str = String.valueOf(exp.charAt(i));

                // Checking to see if operand is not a single digit; ex. 10
                // can't check if next digit is a number in while-condition
                while (i < exp.length() && isNumber(exp.charAt(i))) {
                     str = str + exp.charAt(i + 1);
                     i++;
                }

                // Turn string into an integer
                 int num = Integer.parseInt(str);
                 numbers.push(num);

                 */

                // USING STRING BUFFER
                // Create a string buffer
                StringBuffer sbuf = new StringBuffer();

                while (i < exp.length() && isNumber(exp.charAt(i))) {
                    sbuf.append(exp.charAt(i++));
                }
                numbers.push(Integer.parseInt(sbuf.toString()));

                // right now the i points to
                // the character next to the last digit,
                // since the for loop also increases
                // the i, we would skip one
                //  token position; we need to
                // decrease the value of i by 1 to
                // correct the offset.
                i--;
            }

            // If character is an operator, push operator onto ops stack
            else if (isOperator(exp.charAt(i))) {
                char ch = exp.charAt(i);

                // Pop off all operators that have HIGHER OR EQUAL PRECEDENCE
                // than current operator and evaluate with the top two values
                // from numbers stack
                while (!ops.empty() && (Prec(ops.peek()) >= Prec(exp.charAt(i)))) {
                    int n1 = numbers.pop();
                    int n2 = numbers.pop();
                    numbers.push(applyOperator(ops.pop(), n1, n2));
                }

                // Push operator to ops stack
                ops.push(ch);

            }

            // If character is '(', then push onto stack
            else if (exp.charAt(i) == '(') {
                ops.push(exp.charAt(i));
            }

            // If character is ')', then
            else if (exp.charAt(i) == ')') {
                while (!ops.empty() && ops.peek() != '(') {
                    int n1 = numbers.pop();
                    int n2 = numbers.pop();
                    numbers.push(applyOperator(ops.pop(), n1, n2));
                }
            }
        }

        // Once your reached the end of the expression, evaluate the
        // remaining ops and numbers
        while (!ops.empty()) {
            int n1 = numbers.pop();
            int n2 = numbers.pop();
            numbers.push(applyOperator(ops.pop(), n1, n2));
        }

        // Return final result as a string
        return String.valueOf(numbers.pop());
    }

    // 6. Evaluate postfix expression
    public String evaluatePostfix() {
        return "";
    }

    // 7. Evaluate prefix expression
    public String evaluatePrefix() {
        return "";
    }
}
