import javax.swing.*;
import java.awt.*;


public class MyFrame extends JFrame{

    MyPanel panel;

    MyFrame(){
        panel = new MyPanel();

        this.add(panel);
        this.setTitle("Breakout Game");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
