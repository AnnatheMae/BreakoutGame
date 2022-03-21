import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class MyPanel extends JPanel implements Runnable{

    static final int GAME_WIDTH = 1500;
    static final int GAME_HEIGHT = 900;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 30;
    static final int PADDLE_WIDTH = 300;
    static final int PADDLE_HEIGHT = 10;
    static final int NUM_IN_ROW = 13;
    static final int NUM_IN_COLUMN = 5;
    public static boolean youWin = false;
    int totalBricks = NUM_IN_ROW * NUM_IN_COLUMN;
    int NUM_LIVES = 3;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle;
    Ball ball;
    Score score;
    MapGenerator map;

    MyPanel(){
        newPaddle();
        newBall();
        newMap();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void newMap(){
        map = new MapGenerator(NUM_IN_COLUMN, NUM_IN_ROW);
    }

    public void newBall(){
        ball = new Ball((GAME_WIDTH/2 - BALL_DIAMETER/2), (GAME_HEIGHT/2 - BALL_DIAMETER/2), BALL_DIAMETER, BALL_DIAMETER);

    }

    public void newPaddle(){
        paddle = new Paddle((GAME_WIDTH/2 - PADDLE_WIDTH/2), (GAME_HEIGHT - PADDLE_HEIGHT - 20), PADDLE_WIDTH, PADDLE_HEIGHT);

    }
    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }
    public void draw(Graphics g){
        map.draw(g);
        paddle.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync();

    }
    public void move(){
        paddle.move();
        ball.move();

    }
    public void checkIfWon(){
        //if all the bricks are gone from the panel then the player wins
        if(totalBricks <= 0){
            youWin = true;
        }
    }
    public void checkCollision(){
        //bounce ball off top and sides of panel
        if(ball.y <= 0){
            ball.setYDirection(-ball.Y_VELOCITY);
        }
        if(ball.x >= GAME_WIDTH - BALL_DIAMETER){
            ball.setXDirection(-ball.X_VELOCITY);
        }
        if(ball.x <= 0){
            ball.setXDirection(-ball.X_VELOCITY);
        }
        //if ball goes past bounds generates new life
        if(ball.y >= GAME_HEIGHT - BALL_DIAMETER && score.player > 0 && youWin == false){
            score.player--;
            if(NUM_LIVES > 1){
                NUM_LIVES--;
                newPaddle();
                newBall();
            }
        }
        //bounce ball off paddle
        if(ball.intersects(paddle)){
            ball.Y_VELOCITY = -ball.Y_VELOCITY;
            if(ball.X_VELOCITY <= 0){
                ball.X_VELOCITY--;
            }
            ball.setXDirection(ball.X_VELOCITY);
            ball.setYDirection(ball.Y_VELOCITY);
        }
        //stops paddle at the edges of the panel
        if(paddle.x <= 0){
            paddle.x = 0;
        }
        if (paddle.x >= (GAME_WIDTH - PADDLE_WIDTH)){
            paddle.x = GAME_WIDTH - PADDLE_WIDTH;
        }

        for(int i = 0; i<map.map.length; i++){
            for(int j =0; j<map.map[0].length; j++){
                if(map.map[i][j] > 0){
                    int brick_X = j * map.BRICK_WIDTH + 100;
					int brick_Y = i * map.BRICK_HEIGHT + 100;
					int brickWidth = map.BRICK_WIDTH;
					int brickHeight = map.BRICK_HEIGHT;

                    Rectangle blockRect = new Rectangle(brick_X, brick_Y, brickWidth, brickHeight);
                    Rectangle ballRect = new Rectangle(ball.x, ball.y, BALL_DIAMETER, BALL_DIAMETER);

                        if(ballRect.intersects(blockRect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            if(totalBricks <= 0){
                                totalBricks = 0;
                            }
                            // when ball hit right or left of brick
                            if(ball.x + BALL_DIAMETER - 1 <= blockRect.x || ball.x + 1 >= blockRect.x + blockRect.width){
                                ball.X_VELOCITY = -ball.X_VELOCITY;
                            } else {
                                ball.Y_VELOCITY = -ball.Y_VELOCITY;
                            }
                        }
                }
            }
        }
		repaint();

    }


    public void run(){
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                checkIfWon();
                repaint();
                delta--;
            }
        }

    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle.keyPressed(e);

        }
        public void keyReleased(KeyEvent e){
            paddle.keyReleased(e);

        }
    }

}
