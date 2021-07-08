package project4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class GraphUI extends JFrame{

    // Variables
    private String fileName;
    private String className;

    private DirectedGraph<String> directedGraph;

    private GraphUI() {
        super ("Class Dependency Graph");

        // Create Panels
        JPanel main = new JPanel();
        JPanel labelsPanel = new JPanel();
        JPanel txtPanel = new JPanel();
        JPanel btnPanel = new JPanel();
        JPanel inputs = new JPanel();
        JPanel output = new JPanel();

        // Panel Colors
        Color subtleGray = new Color(174, 174, 174);
        inputs.setBackground(subtleGray);
        labelsPanel.setBackground(subtleGray);
        txtPanel.setBackground(subtleGray);
        btnPanel.setBackground(subtleGray);

        // Set Layouts
        main.setLayout(new GridBagLayout());
        labelsPanel.setLayout(new GridBagLayout());
        txtPanel.setLayout(new GridBagLayout());
        btnPanel.setLayout(new GridBagLayout());
        inputs.setLayout(new GridBagLayout());
        output.setLayout(new BorderLayout());

        // Create Components
        JLabel fileLabel = new JLabel("Input file name:");
        JTextField fileTxt = new JTextField(null,20);
        JButton buildBtn = new JButton("Build Directed Graph");
        JLabel classLabel = new JLabel("Class to recompile:");
        JTextField classTxt = new JTextField(null,20);
        JButton orderBtn = new JButton("Topological Order");
        JTextArea outputTxt = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTxt);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = .5;
        labelsPanel.add(fileLabel, c);


        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = .5;
        labelsPanel.add(classLabel, c);

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = .5;
        txtPanel.add(fileTxt, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = .5;
        txtPanel.add(classTxt, c);

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = .5;
        btnPanel.add(buildBtn, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = .5;
        btnPanel.add(orderBtn, c);

        output.setBorder(BorderFactory.createTitledBorder("Recompilation Order"));
        outputTxt.setLineWrap(true);
        outputTxt.setWrapStyleWord(true);
        outputTxt.setEditable(false);
        output.add(scrollPane);

        c.fill = GridBagConstraints.BOTH;

        inputs.setBorder(BorderFactory.createTitledBorder(""));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        inputs.add(labelsPanel, c);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        inputs.add(txtPanel, c);
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        inputs.add(btnPanel, c);

        c.insets = new Insets(1,2,1,2);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0;
        main.add(inputs, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        main.add(output, c);

        add(main);

        // JFrame Settings
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700,350);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);

        buildBtn.addActionListener((ActionEvent e) -> {

            directedGraph = new DirectedGraph<>();

            fileName = fileTxt.getText();

            try {
                if (fileName.isEmpty()) {
                    throw new NullPointerException();
                }

                directedGraph.buildDirectedGraphFromFile(directedGraph.tokenizeFile(fileName));

                JOptionPane.showMessageDialog(null, "Graph Built Successfully","Message",JOptionPane.INFORMATION_MESSAGE);

            } catch (NullPointerException e1) {
                JOptionPane.showMessageDialog(null, "A File Name is Required","Error",JOptionPane.ERROR_MESSAGE);
            } catch (IOException e2) {
                JOptionPane.showMessageDialog(null, "File Did Not Open","Error",JOptionPane.ERROR_MESSAGE);
            }
        });

        orderBtn.addActionListener((ActionEvent e) -> {

            className = classTxt.getText();

            try {
                outputTxt.setText(directedGraph.topOrdGeneration(className));

            } catch (InvalidClassNameException e1) {
                JOptionPane.showMessageDialog(null, "Invalid Class Name: " + className,"Error",JOptionPane.ERROR_MESSAGE);
            } catch (ContainsCycleException e2) {
                JOptionPane.showMessageDialog(null, "This DirectedGraph contains a Cycle!","Message",JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        new GraphUI();
    }
}
