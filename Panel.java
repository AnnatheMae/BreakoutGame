import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Panel extends JPanel{
    int X_VELOCITY;
    int Y_VELOCITY;
    int BALL_WIDTH;
    int BALL_HEIGHT;
    int BALL_X;
    int BALL_Y;
    int PADDLE_WIDTH;
    int PADDLE_HEIGHT;
    int PADDLE_X;
    int PADDLE_Y;
    int SCREEN_WIDTH;
    int SCREEN_HEIGHT;
    int NUM_IN_COLUMN;
    int NUM_IN_ROW;
    int BLOCK_X;
    int BLOCK_Y;
    int a;
    int b;
    int c;

    Panel(){
        X_VELOCITY = 1;
        Y_VELOCITY = 1;
        PADDLE_WIDTH = 300;
        PADDLE_HEIGHT = 10;
        NUM_IN_ROW = 13;
        NUM_IN_COLUMN = 5;
        SCREEN_HEIGHT = 1000;
        SCREEN_WIDTH = 1500;
        a = 50;
        b = 100;
        c = 150;
        BLOCK_X = 50;
        BLOCK_Y = 50;
        PADDLE_X = SCREEN_WIDTH/2 - PADDLE_WIDTH/2;
        PADDLE_Y = SCREEN_HEIGHT - 100;
        BALL_WIDTH = 50;
        BALL_HEIGHT = 50;
        BALL_X = PADDLE_X + (PADDLE_WIDTH/2) - (BALL_WIDTH/2);
        BALL_Y = PADDLE_Y - BALL_HEIGHT;


        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));


    }
    public void paint(Graphics g){

        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.DARK_GRAY);
        g2D.fillOval(BALL_X, BALL_Y, BALL_WIDTH, BALL_HEIGHT);
        g2D.setColor(new Color (0, 128, 0));
        g2D.fillRect(PADDLE_X, PADDLE_Y, PADDLE_WIDTH, PADDLE_HEIGHT);
        for (int j = 0; j < NUM_IN_COLUMN; j++){
            for (int i = 0; i < NUM_IN_ROW; i++){
                g2D.setColor(new Color(a, b, c));
                g2D.fillRect(BLOCK_X, BLOCK_Y, 100, 50);
                BLOCK_X += 110;
                if (i == NUM_IN_ROW - 1){
                    BLOCK_X = 50;
                    BLOCK_Y += 60;
                    a += 20;
                    b += 20;
                    c += 20;
                }

            }
        }
    }

}
