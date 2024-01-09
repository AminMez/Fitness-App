package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import model.*;


public class WorkdayFrame extends JFrame {

    //The class represent the view window of a day of workouts

    private static final int HEIGHT = 400;
    private static final int WIDTH = 500;

    private Workday workday;

    //EFFECTS: create a window frame
    public WorkdayFrame(Workday wd) {
        workday = wd;
        createFrame();
    }

    //EFFECTS: create a window frame with components
    public void createFrame() {
        new JFrame();
        this.setTitle(workday.getDay());
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(true);
        this.setVisible(true);
        JLabel label1 = createLabel("Cardio Exercises");
        JLabel label2 = createLabel("Strength Exercises");

        String[] columnNamesCardio = { "Name", "Duration"};
        String[] columnNameStrength = {"Name","Part","Rep","Set","Weight"};
        String[][] cardioData = convertCardio();
        String[][] strengthData = convertStrength();
        JTable table1 = new JTable(cardioData,columnNamesCardio);
        table1.setBounds(30, 150, 200, 300);
        JTable table2 = new JTable(strengthData,columnNameStrength);
        JScrollPane scrollPane1 = new JScrollPane(table1);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        this.add(label1);
        this.add(scrollPane1);
        this.add(label2);
        this.add(scrollPane2);
        this.setLayout(new GridLayout(0,1));
    }

    //EFFECTS: create labels
    public JLabel createLabel(String text) {
        JLabel lab = new JLabel();
        lab.setSize(100, 30);
        lab.setText(text);
        return lab;
    }

    //EFFECTS: convert cardio exercise to display table
    public String[][] convertCardio() {
        ArrayList<CardioExercise> cardios = workday.getCardioExercises();
        String [][] returned = new String[cardios.size()][2];
        int i = 0;
        for (CardioExercise c :cardios) {
            returned[i][0] = c.getName();
            returned[i][1] = String.valueOf(c.getAmount()) + " minutes";
            i++;
        }

        return returned;
    }

    //EFFECTS: convert strength exercise to display table
    public String[][] convertStrength() {
        ArrayList<StrengthExercise> strengths = workday.getStrengthExercise();
        String [][] returned = new String[strengths.size()][5];
        int i = 0;
        for (StrengthExercise s: strengths) {
            returned[i][0] = s.getName();
            returned[i][1] = s.getPart();
            returned[i][2] = String.valueOf(s.getReps());
            returned[i][3] = String.valueOf(s.getSets());
            returned[i][4] = String.valueOf(s.getWeight());
            i++;
        }
        return returned;
    }

}
