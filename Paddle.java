import java.awt.event.*;
import java.awt.*;

public class Paddle extends Rectangle {

    int X_VELOCITY;
    int speed = 10;


    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT){
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }
    public void keyPressed(KeyEvent e){
        //move paddle left and right
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            setXDirection(-speed);

        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            setXDirection(speed);
        }

    }
    public void keyReleased(KeyEvent e){
        //stops paddle from moving after key is released
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            setXDirection(0);

        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            setXDirection(0);

        }

    }
    public void setXDirection(int XDirection){
        X_VELOCITY = XDirection;

    }
    public void move(){
        x = x + X_VELOCITY;

    }
    public void draw(Graphics g){
        //draws paddle
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);

    }
}
