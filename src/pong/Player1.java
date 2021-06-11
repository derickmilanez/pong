package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player1 {

	public boolean up,down;
	
	public int x,y;
	
	public int width,height;
	
	public Player1(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 5;
		this.height = 40;
	}
	
	public void tick(){
		if(up)
		{
			y-=2;
		}
		else if(down) {
			y+=2;
		}
		
		if(y+height > Game.HEIGHT)
		{
			y = Game.HEIGHT - height;
		}
		
		else if(y < 0) {
			y = 0;
		}
	}
	
	public void render(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(x,y,width,height);
	}
	
}
