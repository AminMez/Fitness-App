package ui;

import model.StrengthExercise;
import model.Workday;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StrengthFrame extends JFrame implements ActionListener {

    // The class represents the Window of page for adding cardio workouts.

    private Workday workday;

    private static final int HEIGHT = 250;
    private static final int WIDTH = 360;

    private JButton submitButton;

    private JTextField textname;
    private JTextField textpart;
    private JTextField textrep;
    private JTextField textsets;
    private JTextField textweight;

    private String name;
    private String part;
    private String rep;
    private String sets;
    private String weight;

    private JLabel nameLabel;
    private JLabel partLabel;
    private JLabel repLabel;
    private JLabel setLabel;
    private JLabel weightLabel;

    //MODIFIES: this
    //EFFECTS: Create the window frame with components on it
    public StrengthFrame(Workday wd) {
        this.workday = wd;

        setSubmitButton();
        setTextBoxes();
        setLabels();

        this.add(nameLabel);
        this.add(textname);
        this.add(partLabel);
        this.add(textpart);
        this.add(repLabel);
        this.add(textrep);
        this.add(setLabel);
        this.add(textsets);
        this.add(weightLabel);
        this.add(textweight);
        this.add(submitButton);
        this.setTitle("Editing" + workday.getDay());
        this.setSize(WIDTH,HEIGHT);
        this.setResizable(true);
        this.setVisible(true);
        this.setLayout(new FlowLayout());

    }

    //MODIFIES: this
    //EFFECTS: set the text boxes to submit the response
    private void setTextBoxes() {
        textname = new JTextField();
        textpart = new JTextField();
        textrep = new JTextField();
        textsets = new JTextField();
        textweight = new JTextField();
        textname.setPreferredSize(new Dimension(100, 30));
        textpart.setPreferredSize(new Dimension(100,30));
        textrep.setPreferredSize(new Dimension(100, 30));
        textsets.setPreferredSize(new Dimension(100,30));
        textweight.setPreferredSize(new Dimension(100, 30));
        textname.addActionListener(this);
        textpart.addActionListener(this);
        textrep.addActionListener(this);
        textsets.addActionListener(this);
        textweight.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: set the labels of text to tell user what to type into response
    private void setLabels() {
        nameLabel = new JLabel();
        nameLabel.setText("Type in the name of weight exercise");
        nameLabel.setSize(100,30);
        partLabel = new JLabel();
        partLabel.setText("Type in the target muscle group");
        nameLabel.setSize(100,30);
        repLabel = new JLabel();
        repLabel.setText("Type in the reps of exercise");
        repLabel.setSize(100,30);
        setLabel = new JLabel();
        setLabel.setText("Type in the sets of exercise");
        setLabel.setSize(100,30);
        weightLabel = new JLabel();
        weightLabel.setText("Type in the weight");
        weightLabel.setSize(100,30);
    }

    //MODIFIES: this
    //EFFECTS: create a submit button
    private void setSubmitButton() {
        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(JButton.CENTER);
        submitButton.setAlignmentY(JButton.BOTTOM);
        submitButton.addActionListener(this);
    }

    //EFFECTS: return the workday data that is stored in the frame
    public Workday getWorkday() {
        return workday;
    }

    //MODIFIES: this
    //EFFECTS: Enter the value in textbox to the system and create a strength exercise object and add it
    //         to the workday.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            name = textname.getText();
            part = textpart.getText();
            rep = textrep.getText();
            sets = textsets.getText();
            weight = textweight.getText();
            StrengthExercise se = new StrengthExercise(name,part);
            se.setReps(Integer.valueOf(rep));
            se.setSets(Integer.valueOf(sets));
            se.setWeight(Integer.valueOf(weight));
            workday.addStrengthWorkout(se);
            JOptionPane.showMessageDialog(null,"Strength added successfully");
        }
    }






}
