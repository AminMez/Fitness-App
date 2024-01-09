package ui;

import model.Event;
import model.EventLog;
import model.Schedule;
import model.Workday;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import static java.lang.System.exit;

public class MyFrame extends JFrame implements ActionListener, WindowListener {

    //The class represent the major window that have options to display, save, load, add, remove the schedule.

    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;

    private static final String PATH = "./data/testWriterSchedule.json";

    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;

    private Schedule schedule;
    private Reader reader;
    private Writer writer;

    private EventLog log;

    //MODIFIES: this
    //EFFECTS: create the window frame with all the major component on it and set the background and layout.
    public MyFrame() {

        init();
        createFrame();
        b1 = createButton(125,525,"View schedule");
        b2 = createButton(325,525,"Add strength");
        b3 = createButton(125,625,"Add cardio");
        b4 = createButton(325,625,"Remove workout");
        b5 = createButton(525,525,"Save schedule");
        b6 = createButton(525,625,"Load schedule");

        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(b4);
        this.add(b5);
        this.add(b6);
        this.getContentPane().setBackground(new Color(254,216,177));
        this.setLayout(new BorderLayout());
        this.log = EventLog.getInstance();
        this.addWindowListener(this);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    //MODIFIES: this
    //EFFECTS: set the size, title and visibility of the frame
    public void createFrame() {
        new JFrame();
        this.setTitle("My workout planner");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);
        this.setResizable(true);
        this.setVisible(true);
    }

    //REQUIES: 0<=x<=WIDTH, 0<=y<=HEIGHT
    //EFFECTS: create a button
    public JButton createButton(int x, int y, String text) {
        JButton b = new JButton();
        b.setBounds(x,y,150,80);
        b.addActionListener(this);
        b.setText(text);
        b.setFont(new Font("Comic Sans",Font.BOLD,12));
        return b;
    }

    //EFFECTS: perform actions when buttons are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            DisplayFrame df = new DisplayFrame();
            df.createFrame(this.schedule);
        } else if (e.getSource() == b2) { //add strength
            addStrength();
        } else if (e.getSource() == b3) { //add cardio
            addCardio();
        } else if (e.getSource() == b4) { //remove workout
            removeWorkOut();
        } else if (e.getSource() == b5) { //save schedule
            saveSchedule();
            JOptionPane.showMessageDialog(null,"Schedule saved successfully");
        } else if (e.getSource() == b6) {
            loadSchedule();
            JOptionPane.showMessageDialog(null,"Schedule loaded successfully");
            b6.setEnabled(false);
        }
    }

    //MODITIES: this
    //EFFECTS: open up the add strength window and add the strength exercise into the schedule
    private void addStrength() {
        String day = JOptionPane.showInputDialog("What day do you want to add strength workout to ?");
        Workday wd = schedule.getWorkDay(Integer.parseInt(String.valueOf(day)));
        StrengthFrame sf = new StrengthFrame(wd);
        Workday wdNew = sf.getWorkday();
        schedule.setWorkday(wdNew,Integer.parseInt(String.valueOf(day)) - 1);
    }

    //MODITIES: this
    //EFFECTS: open up the add cardio window and add the cardio exercise into the schedule
    private void addCardio() {
        String day = JOptionPane.showInputDialog("What day do you want to add cardio workout to ?");
        Workday wd = schedule.getWorkDay(Integer.parseInt(String.valueOf(day)));
        CardioFrame cf = new CardioFrame(wd);
        Workday wdNew = cf.getWorkday();
        schedule.setWorkday(wdNew,Integer.parseInt(String.valueOf(day)) - 1);

    }

    //MODITIES: this
    //EFFECTS: remove the workout with the entered name, produce "cannot find exercise" if there
    //         are not there
    private void removeWorkOut() {
        String day = JOptionPane.showInputDialog("What day do you want to edit workout?");
        String name = JOptionPane.showInputDialog("Type in the name of workout to remove");
        Workday wd = schedule.getWorkDay(Integer.parseInt(String.valueOf(day)));
        if (wd.removeWorkout(name)) {
            JOptionPane.showMessageDialog(null,"Exercise removed!");
        } else {
            JOptionPane.showMessageDialog(null,"Cannot find exercise");
        }
    }

    //EFFECTS: save the schedule into the file in data folder
    private void saveSchedule() {
        try {
            writer.openFile();
            writer.write(schedule);
            writer.close();
        } catch (IOException e) {
            System.out.println("What?");
        }
    }

    //MODITIES: this
    //EFFECTS: load the schedeule object into GUI
    private void loadSchedule() {
        try {
            schedule = reader.read();
        } catch (IOException e) {
            System.out.println("Cannot find saved file");
        }
    }

    //MODITIES: this
    //EFFECTS: create an empty schedule and add reeder and writer to the GUI
    private void init() {
        this.schedule = new Schedule(7);
        this.writer = new Writer(PATH);
        this.reader = new Reader(PATH);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        for (Event ev :log) {
            System.out.println(ev.toString());
        }
        exit(1);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        for (Event ev :log) {
            System.out.println(ev.toString());
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
