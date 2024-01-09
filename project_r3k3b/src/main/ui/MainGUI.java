package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainGUI {


    //EFFECTS: Run the graphic user interface while adding the pictures and icon to the main frame.
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();

        ImageIcon image = new ImageIcon("data/Gym poster.png");
        Border border = BorderFactory.createLineBorder(Color.pink,5);

        JLabel label = new JLabel();
        label.setIcon(image);
        label.setSize(200,100);
        label.setText("Welcome to the Workout planner !");
        label.setHorizontalTextPosition(label.CENTER);
        label.setVerticalTextPosition(label.TOP);
        label.setFont(new Font("MV Boli",Font.ITALIC,30));
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);

        frame.add(label);
        frame.setVisible(true);
    }
}
