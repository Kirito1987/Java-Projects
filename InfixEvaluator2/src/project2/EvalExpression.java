package project2;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

public class EvalExpression {

    private Stack<Node> expressionStack = new Stack<>();
    private Node operator;


    String evaluate(String expression) throws InvalidTokenException, EmptyStackException, IOException {
        String[] tokens = expression.split("");

        for (String token : tokens) {
            String operandPat = "[\\d.?]+";
            String operatorPat = "[*/+\\-]";

            if (!token.matches(operandPat) && !token.matches(operatorPat)) {
                throw new InvalidTokenException(token);
            }

            if (token.matches(operandPat)) {
                expressionStack.push(new OperandNode(token));
            } else if (token.matches(operatorPat)) {
                operator = new OperatorNode(token, expressionStack.pop(), expressionStack.pop());
                expressionStack.push(operator);
            }
        }

        // Creates the Assembly Code written to File that will generate the txt showing the binary tree
        operator.post();

        return expressionStack.pop().inOrderWalk();
    }
}
