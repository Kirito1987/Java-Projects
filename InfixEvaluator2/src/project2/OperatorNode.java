package project2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OperatorNode implements Node {

    private String operator;
    private Node right, left;
    private static int i;   //
    private File file = new File("3-Address.txt");


    OperatorNode(String operator, Node right, Node left) {
        this.operator = operator;
        this.right = right;
        this.left = left;

        operatorEval(operator);
    }


    public String inOrderWalk() {
        return "(" + left.inOrderWalk() + " " + operator + " " + right.inOrderWalk() + ")";
    }


    public void post() throws IOException {
        i = 0;              // sets count
        postOrderWalk();    // starts the walk
    }


    public String postOrderWalk() throws IOException {
        String leftValue = left.postOrderWalk();
        String rightValue = right.postOrderWalk();
        String opValue = operatorEval(this.operator);

        String result = "R" + i++;

        String step = opValue + " " + result + " " + leftValue + " " + rightValue;
        writeToFile(step);

        return result;
    }


    private String operatorEval(String operator) {
        String op = "";

        switch (operator) {
            case "+":
                op = "Add";
                break;
            case "-":
                op = "Sub";
                break;
            case "*":
                op = "Mul";
                break;
            case "/":
                op = "Div";
                break;
        }

        return op;
    }


    private void writeToFile(String step) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
        bw.write(step);
        bw.newLine();
        bw.close();
    }
}
