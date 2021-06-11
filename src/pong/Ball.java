package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
		public double x,y;
		public int width,height;
		
		public double dx,dy;
		public double speed = 1.5;
		
		public int scoreP1 = 0;
		public int scoreP2 = 0;
		
		public String sp1;
		public String sp2;
		
		public Ball(int x,int y) {
			this.x = x;
			this.y = y;
			this.width = 4;
			this.height = 4;
			
			int angle = new Random().nextInt(180 - 105) + 105 + 1;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
		}
		
		public void tick() {
			speed += 0.001;
			if(y+(dy*speed) + height >= Game.HEIGHT ) {
				dy*=-1;
			}else if(y+(dy*speed) < 0) {
				dy*=-1;
			}
			sp1 = String.valueOf(scoreP1);
			sp2 = String.valueOf(scoreP2);
			if(x >= Game.WIDTH)
			{
				//Ponto do Player 1.
				this.x = 100;
				this.y = Game.HEIGHT/2 - 1;
				scoreP1++;
				speed = 1;
				return;
			}else if(x < 0) {
				//Ponto do Player 2.
				this.x = 100;
				this.y = Game.HEIGHT/2 - 1;
				scoreP2++;
				speed = 1;
				return;
			}
			
			Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)),width,height);
			
			Rectangle boundsPlayer1 = new Rectangle(Game.player1.x,Game.player1.y,Game.player1.width,Game.player1.height);
			Rectangle boundsPlayer2 = new Rectangle((int)Game.player2.x,(int)Game.player2.y,Game.player2.width,Game.player2.height);
			
			if(bounds.intersects(boundsPlayer1)) {
				int angle = new Random().nextInt(180 - 105) + 105 + 1;
				dx = Math.cos(Math.toRadians(angle));
				dy = Math.sin(Math.toRadians(angle));
				if(dx < 0)
					dx*=-1;
			}else if(bounds.intersects(boundsPlayer2)) {
				int angle = new Random().nextInt(180 - 105) + 105 + 1;
				dx = Math.cos(Math.toRadians(angle));
				dy = Math.sin(Math.toRadians(angle));
				if(dx > 0)
					dx*=-1;
			}
			x+=dx*speed;
			y+=dy*speed;
		}
		
		public void render(Graphics g) {
			g.setColor(Color.yellow);
			g.fillRect((int)x,(int)y,width,height);
			g.setFont(new Font("Arial", Font.BOLD, 10));
			g.setColor(Color.blue);
			g.drawString(sp1, 40, 20);
			g.setColor(Color.red);
			g.drawString(sp2, 110, 20);
		}
}
