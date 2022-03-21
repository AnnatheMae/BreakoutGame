import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{
    Random random;
    int X_VELOCITY;
    int Y_VELOCITY;
    int initialSpeed = 5;

    Ball(int x, int y, int width, int height){
        super(x, y, width, height);
        //sets random direction for the ball
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0)
            randomXDirection--;
        randomXDirection = -randomXDirection;
        setXDirection(randomXDirection * initialSpeed);

        int randomYDirection = random.nextInt(1);
        if(randomYDirection == 0)
            randomYDirection--;
        randomYDirection = -randomYDirection;
        setYDirection(randomYDirection * initialSpeed);

    }
    public void setXDirection(int randomXDirection){
        X_VELOCITY = randomXDirection;

    }
    public void setYDirection(int randomYDirection){
        Y_VELOCITY = randomYDirection;

    }
    public void move(){
        x += X_VELOCITY;
        y += Y_VELOCITY;

    }
    public void draw(Graphics g){
        //draws ball
        g.setColor(Color.white);
        g.fillOval(x, y, height, width);

    }

}
