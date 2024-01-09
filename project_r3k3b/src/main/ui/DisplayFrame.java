package ui;

import model.Schedule;
import model.Workday;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayFrame extends JFrame implements ActionListener {

    //The class represents the frame that displayed the workdays of a given schedule

    private static final int HEIGHT = 275;
    private static final int WIDTH = 200;

    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;

    private Schedule schedule;

    //MODIFIES: this
    //EFFECTS: create a frame that contains the content of the given schedule
    public void createFrame(Schedule sc) {
        this.schedule = sc;
        new JFrame("Workout Schedule");
        this.setTitle("Weekly schedule");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
        createButtons();
        JPanel panel = new JPanel();
        panel.setSize(350,520);
        panel.setVisible(true);
        addButtons(panel);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        this.add(panel);
    }

    //MODIFIES: this
    //EFFECTS: add buttons onto the panel
    private void addButtons(JPanel panel) {
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        panel.add(b7);
    }

    //MODIFIES: this
    //EFFECTS: create the buttons that will be added onto the frame
    private void createButtons() {
        b1 = createButton(50,20,"Day 1 -" + " # of exercises: "
                + String.valueOf(schedule.getWorkDay(1).getNumWorkout()));
        b2 = createButton(50,80,"Day 2 -" + " # of exercises: "
                + String.valueOf(schedule.getWorkDay(2).getNumWorkout()));
        b3 = createButton(50,140,"Day 3 -" + " # of exercises: "
                + String.valueOf(schedule.getWorkDay(3).getNumWorkout()));
        b4 = createButton(50,200,"Day 4 -" + " # of exercises: "
                + String.valueOf(schedule.getWorkDay(4).getNumWorkout()));
        b5 = createButton(50,260,"Day 5 -" + " # of exercises: "
                + String.valueOf(schedule.getWorkDay(5).getNumWorkout()));
        b6 = createButton(50,320,"Day 6 -" + " # of exercises: "
                + String.valueOf(schedule.getWorkDay(6).getNumWorkout()));
        b7 = createButton(50,380,"Day 7 -" + " # of exercises: "
                + String.valueOf(schedule.getWorkDay(7).getNumWorkout()));
    }

    //REQUIES: 0<=x<=WIDTH, 0<=y<=HEIGHT
    //EFFECTS: create a single button with given x,y coordinate and text contained in the button.
    private JButton createButton(int x, int y,String text) {
        JButton b = new JButton();
        b.setBounds(x,y,250,40);
        b.addActionListener(this);
        b.setText(text);
        b.setFont(new Font("Comic Sans",Font.BOLD,10));
        return b;
    }

    //EFFECTS: Displayed frames when the buttons are clicked.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            displayWorkday(1);
        } else if (e.getSource() == b2) {
            displayWorkday(2);
        } else if (e.getSource() == b3) {
            displayWorkday(3);
        } else if (e.getSource() == b4) {
            displayWorkday(4);
        } else if (e.getSource() == b5) {
            displayWorkday(5);
        } else if (e.getSource() == b6) {
            displayWorkday(6);
        } else if (e.getSource() == b7) {
            displayWorkday(7);
        }
    }

    //REQUIES: 1<=i<=7
    //EFFECTS: Display the information in ith workday
    private void displayWorkday(int i) {
        Workday wd = schedule.getWorkDay(i);
        WorkdayFrame wdf = new WorkdayFrame(wd);
    }
}
