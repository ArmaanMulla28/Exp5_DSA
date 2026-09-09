import java.util.*;

class InfixToPostfix {

    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;

            default:
                return 0;
        }
    }

    static String infixToPostfix(String infix) {

        Stack<Character> stack = new Stack<>();
        String postfix = "";

        for (int i = 0; i < infix.length(); i++) {

            char ch = infix.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                postfix += ch;
            }

            else if (ch == '(') {
                stack.push(ch);
            }

            else if (ch == ')') {

                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix += stack.pop();
                }

                stack.pop(); // Remove '('
            }

            else {
                while (!stack.isEmpty()
                        && precedence(stack.peek()) >= precedence(ch)) {
                    postfix += stack.pop();
                }

                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            postfix += stack.pop();
        }

        return postfix;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter infix expression: ");
        String infix = sc.nextLine();

        String postfix = infixToPostfix(infix);

        System.out.println("Postfix Expression: " + postfix);

        sc.close();
    }
}
