package project2;

/*Filename: InfixEval2
    Author: Laudro Pineda
    Date: June 16, 2019
    Description: Takes an integer in postfix notation with the operators not separated by spaces. It then processes
    the expression and builds an expression tree that represents said expression
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.EmptyStackException;

public class InfixEval2 extends JFrame {
    private String expression;

    // Creates Instance of ExpressionEval
    private EvalExpression evalExpression = new EvalExpression();

    /**
     * Creates the GUI for the program
     */
    private InfixEval2() {
        // Set Title
        super("Three Address Generator");

        JPanel main = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel constructPanel = new JPanel();
        JPanel resultPanel = new JPanel();

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        JLabel inputLabel = new JLabel("Enter Postfix Expression");
        JTextField inputTxt = new JTextField(null,10);
        JButton constructBtn = new JButton("Construct Tree");
        JLabel resultLabel = new JLabel("Infix Expression");
        JTextField resultTxt = new JTextField(null,20);

        inputPanel.add(inputLabel);
        inputPanel.add(inputTxt);

        constructPanel.add(constructBtn);

        resultPanel.add(resultLabel);
        resultPanel.add(resultTxt);
        resultTxt.setEditable(false);

        main.add(inputPanel);
        main.add(constructPanel);
        main.add(resultPanel);

        add(main);

        // JFrame Settings
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,245);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);

            constructBtn.addActionListener((ActionEvent e) -> {
            expression = inputTxt.getText();

            try {
                if (expression.isEmpty()) {
                    throw new NullPointerException();
                } else {
                    resultTxt.setText(evalExpression.evaluate(expression));
                }
            } catch (InvalidTokenException e1) {
                JOptionPane.showMessageDialog(null, "Invalid Token: " + e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException e2) {
                JOptionPane.showMessageDialog(null, "A Postfix Expression is required.","Error",JOptionPane.ERROR_MESSAGE);
            } catch (EmptyStackException empty) {
                JOptionPane.showMessageDialog(null, "Invalid Postfix Expression. Check Operators and Operands.","Error",JOptionPane.ERROR_MESSAGE);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Error Writing to File.","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        new InfixEval2();
    }
}
