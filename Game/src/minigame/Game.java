package minigame;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Game {
	static final int TILESIZE = 50;
	static final int NUMTILE_X = 4;
	static final int NUMTILE_Y = 4;
	
	public static void main(String[] args) throws IOException {
		new GameStart();
		new GameController();
//		// TODO Auto-generated method stub
//		new Stage();
//		Image image;
//		Graphics graphics;
//		
//		synchronized(Actor.actors) {
//			URL url1 = Stage.stage.getClass().getResource("img/rock.gif");
//			URL url2 = Stage.stage.getClass().getResource("img/tree.gif");
//			
//			try {
//				image = Stage.stage.createImage((ImageProducer) url1.getContent());
//			} catch (Exception ex) {
//				System.out.println("Error");
//				image = null;
//			}
//			new Actor(image, 2, 3);
//			
//			try {
//				image = Stage.stage.createImage((ImageProducer) url2.getContent());
//			} catch (Exception ex) {
//				System.out.println("Error");
//				image = null;
//			}
//			new Actor(image, 8, 6);
//			
//			image = new BufferedImage(Game.TILESIZE, Game.TILESIZE, BufferedImage.TYPE_INT_ARGB);
//			graphics = image.getGraphics();
//			graphics.setColor(Color.GREEN);
//			graphics.fillRect(4, 4, 24, 24);
//			new Character(image, 5, 6, 1000);
//			
//			for(int i = 0; i < 10; i++) {
//			image = new BufferedImage(Game.TILESIZE, Game.TILESIZE, BufferedImage.TYPE_INT_ARGB);
//			graphics = image.getGraphics();
//			graphics.setColor(Color.BLACK);
//			if(i % 2 == 0) {
//				graphics.fillOval(2, 2, 28, 28);
//			} else {
//				graphics.drawOval(2, 2, 28, 28);
//			}
//			new Character(image, 9, i, 1000);
//			}
//		}
//		Stage.stage.frame.setVisible(true);
	}

}
