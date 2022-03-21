import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class Score extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int rectWidth = 100;
    int rectHeight = 30;
    int player = 3;


    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;

    }
    public void draw(Graphics g){
        Font font = new Font("Consolas", Font.PLAIN, 60);
        Rectangle middleRect = new Rectangle(GAME_WIDTH/2 - rectWidth/2, GAME_HEIGHT/2 - rectHeight/2, rectWidth, rectHeight);
        Rectangle topRect = new Rectangle(GAME_WIDTH/2 - rectWidth/2, 50, rectWidth, rectHeight);
        g.setColor(Color.white);
		g.setFont(font);
        centerString(g, topRect, String.valueOf(player/10)+String.valueOf(player%10), font);
        if(player == 0){
            //if player runs out of lives then draws the string "You Lose"
            centerString(g, middleRect, "You Lose", font);
        }
        if(MyPanel.youWin == true){
            //if player wins then draws the string "You Win"
            centerString(g, middleRect, "You Win", font);

        }


    }
    public void centerString(Graphics g, Rectangle r, String s, Font font) {
    FontRenderContext frc = new FontRenderContext(null, true, true);
    Rectangle2D r2D = font.getStringBounds(s, frc);
    int rWidth = (int) Math.round(r2D.getWidth());
    int rHeight = (int) Math.round(r2D.getHeight());
    int rX = (int) Math.round(r2D.getX());
    int rY = (int) Math.round(r2D.getY());

    int a = (r.width / 2) - (rWidth / 2) - rX;
    int b = (r.height / 2) - (rHeight / 2) - rY;

    g.setFont(font);
    g.drawString(s, r.x + a, r.y + b);
}
}
