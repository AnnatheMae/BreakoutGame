import java.awt.Graphics;
import java.awt.Color;


public class MapGenerator{
    public int map[][];
    public int BRICK_WIDTH = 100;
    public int BRICK_HEIGHT = 50;
    int BLOCK_X;
    int BLOCK_Y;


    public MapGenerator (int row, int col){
		//creates an array for the blocks
		map = new int[row][col];
		for(int i = 0; i<map.length; i++){
			for(int j =0; j<map[0].length; j++){
				map[i][j] = 1;
			}
		}
	}

	public void draw(Graphics g){
		for(int i = 0; i<map.length; i++){
			for(int j =0; j<map[0].length; j++){
				if(map[i][j] > 0){
					//draws blocks
                    BLOCK_X = j * BRICK_WIDTH + 100;
                    BLOCK_Y = i * BRICK_HEIGHT + 100;
					g.setColor(Color.white);
					g.fillRect(BLOCK_X, BLOCK_Y, BRICK_WIDTH, BRICK_HEIGHT);

					g.setColor(Color.red);
					g.drawRect(BLOCK_X, BLOCK_Y, BRICK_WIDTH, BRICK_HEIGHT);
				}
			}
		}
	}

	public void setBrickValue(int value, int row, int col){
		map[row][col] = value;
	}

}
