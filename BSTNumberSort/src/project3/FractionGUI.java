package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FractionGUI extends JFrame {

    // Variables
    private String list;
    private String result;

    private FractionGUI() {
        // Set Title
        super ("Binary Search Tree Sort");

        // Create Panels
        JPanel main = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel outputPanel = new JPanel();
        JPanel performPanel = new JPanel();
        JPanel optionsPanel = new JPanel();
        JPanel sortPanel = new JPanel();
        JPanel numericPanel = new JPanel();

        // Set Layout
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        performPanel.setLayout(new GridBagLayout());
        optionsPanel.setLayout(new GridLayout());
        sortPanel.setLayout(new BoxLayout(sortPanel, BoxLayout.Y_AXIS));
        numericPanel.setLayout(new BoxLayout(numericPanel, BoxLayout.Y_AXIS));

        // Create Components
        JLabel inputLabel = new JLabel("Original List");
        JTextField inputTxt = new JTextField(null,20);
        JLabel outputLabel = new JLabel("Sorted List");
        JTextField outputTxt = new JTextField(null,20);
        JButton performBtn = new JButton("Perform Sort");
        JRadioButton ascendingRBtn = new JRadioButton("Ascending");
        JRadioButton descendingRBtn = new JRadioButton("Descending");
        JRadioButton integerRBtn = new JRadioButton("Integer");
        JRadioButton fractionRBtn = new JRadioButton("Fraction");

        // Add Input Components
        inputPanel.add(inputLabel);
        inputPanel.add(inputTxt);
        inputPanel.setPreferredSize(new Dimension(500,65));

        // Add Output Components
        outputPanel.add(outputLabel);
        outputPanel.add(outputTxt);
        outputTxt.setEditable(false);

        // Add Perform Components
        performPanel.add(performBtn);

        // Add Sort Components
        sortPanel.setBorder(BorderFactory.createTitledBorder("Sort Order"));
        sortPanel.add(ascendingRBtn);
        sortPanel.add(descendingRBtn);

        // Group Sort Buttons
        ButtonGroup sortGroup = new ButtonGroup();
        sortGroup.add(ascendingRBtn);
        sortGroup.add(descendingRBtn);

        // Add Numeric Components
        numericPanel.setBorder(BorderFactory.createTitledBorder("Numeric Type"));
        numericPanel.add(integerRBtn);
        numericPanel.add(fractionRBtn);

        // Group Numeric Buttons
        ButtonGroup numericGroup = new ButtonGroup();
        numericGroup.add(integerRBtn);
        numericGroup.add(fractionRBtn);

        // Add Options Components
        optionsPanel.add(sortPanel);
        optionsPanel.add(numericPanel);

        // Add Panels to main
        main.add(inputPanel);
        main.add(outputPanel);
        main.add(performPanel);
        main.add(optionsPanel);

        // Add main to JFrame
        add(main);

        // JFrame Settings
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(550,400);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);

        // Set Default Radio Buttons
        ascendingRBtn.setSelected(true);
        integerRBtn.setSelected(true);
        ascendingRBtn.doClick();
        integerRBtn.doClick();

        performBtn.addActionListener((ActionEvent e) -> {
            list = inputTxt.getText();

            try {
                if (list.isEmpty()) {
                    throw new NullPointerException();
                }

                String[] tokens = list.split(" ");

                if (integerRBtn.isSelected()) {

                    BinarySearchTable<Integer> bst = new BinarySearchTable<>();

                    for (String token : tokens) {
                        bst.insertNode(Integer.parseInt(token));
                    }

                    if (ascendingRBtn.isSelected()) {
                        result = bst.getAscending();
                    } else if (descendingRBtn.isSelected()) {
                        result = bst.getDescending();
                    }
                }

                if (fractionRBtn.isSelected()) {

                    BinarySearchTable<Fraction> bst = new BinarySearchTable<>();

                    for (String token : tokens) {
                        Fraction fraction = new Fraction(token);
                        bst.insertNode(fraction);
                    }

                    if (ascendingRBtn.isSelected()) {
                        result = bst.getAscending();
                    } else if (descendingRBtn.isSelected()) {
                        result = bst.getDescending();
                    }
                }

                outputTxt.setText(result);

            } catch (NullPointerException e1) {
                JOptionPane.showMessageDialog(null, "A List is required.","Error",JOptionPane.ERROR_MESSAGE);
            } catch (InvalidFractionException e1) {
                JOptionPane.showMessageDialog(null, "Invalid Fraction used: " + e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Non-Numeric Input.","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        new FractionGUI();
    }
}
