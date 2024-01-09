package ui;

import model.CardioExercise;
import model.Workday;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardioFrame extends JFrame implements ActionListener {

    // The class represents the Window of page for adding cardio workouts.

    private Workday workday;

    private static final int HEIGHT = 185;
    private static final int WIDTH = 300;

    private JButton submitButton;

    private JTextField textname;
    private JTextField textduration;

    private String name;
    private String duration;

    private JLabel nameLabel;
    private JLabel durationLabel;

    //MODIFIES: this, workday
    //EFFECTS: Create a frame for users to edit cardio workout and changes the value of workday.
    public CardioFrame(Workday wd) {
        this.workday = wd;

        setTextBoxes();
        setSubmitButton();
        setLabels();

        this.add(nameLabel);
        this.add(textname);
        this.add(durationLabel);
        this.add(textduration);
        this.add(submitButton);
        this.setTitle("Editing" + workday.getDay());
        this.setSize(WIDTH,HEIGHT);
        this.setResizable(true);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
    }

    //MODIFIES: this
    //EFFECTS: Set the text box to Submit response
    private void setTextBoxes() {
        textname = new JTextField();
        textduration = new JTextField();
        textname.setPreferredSize(new Dimension(100, 30));
        textduration.setPreferredSize(new Dimension(100,30));
        textname.addActionListener(this);
        textduration.addActionListener(this);
        textname.setAlignmentX(JTextField.CENTER);
        textname.setAlignmentY(JTextField.TOP);
        textduration.setAlignmentX(JTextField.CENTER);
        textduration.setAlignmentY(JTextField.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: Create a submit button to submit the answers;
    private void setSubmitButton() {
        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(JButton.CENTER);
        submitButton.setAlignmentY(JButton.BOTTOM);
        submitButton.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: Create a label of text to explain content in the textbox
    private void setLabels() {
        nameLabel = new JLabel();
        nameLabel.setText("Type in the name of cardio");
        nameLabel.setSize(100,30);
        durationLabel = new JLabel();
        durationLabel.setText("Type in the duration of cardio");
        nameLabel.setSize(100,30);
    }

    //EFFECTS: get the workday object that is currently contained in the frame
    public Workday getWorkday() {
        return workday;
    }

    //MODIFIES: this
    //EFFECTS: Enter the value in textbox to the system and create a cardio exercise object and add it
    //         to the workday.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            name = textname.getText();
            duration = textduration.getText();
            CardioExercise ce = new CardioExercise(name);
            ce.setDuration(Integer.valueOf(duration));
            workday.addCardioWorkout(ce);
            JOptionPane.showMessageDialog(null,"Cardio added successfully");
        }
    }
}
