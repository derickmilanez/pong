package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener{
	
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 160;
	public static int HEIGHT = 120;
	public static int SCALE = 3;
	public static ImageIcon img = new ImageIcon("res/icon.png");
	
	public BufferedImage layer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	public static Player1 player1;
	public static Player2 player2;
	public static Ball ball;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.addKeyListener(this);
		player1 = new Player1(0,HEIGHT/2 - 20);
		player2 = new Player2(155,HEIGHT/2 - 20);
		ball = new Ball(100,HEIGHT/2 - 1);
	}

	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("PONG");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setIconImage(img.getImage());
		new Thread(game).start();
	}
	
	public void tick() {
		player1.tick();
		player2.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		player1.render(g);
		player2.render(g);
		ball.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE,HEIGHT*SCALE,null);
		
		bs.show();
	}
	
	@Override
	public void run() {
		while(true) {
			requestFocus();
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player1.up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			player1.down = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player2.up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player2.down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player1.up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			player1.down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player2.up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player2.down = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
