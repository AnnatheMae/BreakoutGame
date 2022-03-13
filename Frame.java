import java.awt.Color;

import javax.swing.*;

public class Frame extends JFrame{
    Panel panel;
    Frame(){
        panel = new Panel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.decode("#CBC3E3"));
    }


}
