package minigame;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

public class GameController implements Runnable, KeyListener {
	Actor PlayerCharacter;
	Actor onActor = null;
	long startTime;
	boolean cheat = false;
	ArrayList<Integer> numbers = new ArrayList<Integer>();
	//int endPosX = 9, endPosY = 9;
	int spaceX = 3, spaceY = 3;
	public GameController() {
		new Stage();
		numpos.set();
		Stage.stage.frame.addKeyListener(this);
		Stage.stage.frame.setVisible(true);
		(new Thread(this)).start();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		startTime = System.currentTimeMillis();
		synchronized(Actor.actors) {
			//各画像
			URL url;
			Image image = new BufferedImage(Game.TILESIZE, Game.TILESIZE, BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = image.getGraphics();
			for(int i = 1; i <= 15; i++) {
				numbers.add(i);
			}
			Collections.shuffle(numbers);
			int n = 0;
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					if(!(i == 3 && j == 3)) {
						String str = "img/num" + Integer.toString(numbers.get(n)) + ".png";
						url = Stage.stage.getClass().getResource(str);
						try {
							image = Stage.stage.createImage((ImageProducer) url.getContent());
						} catch (Exception ex) {
							System.out.println("Error");
							image = null;
						}
						System.out.println(str);
						new Actor(image, j, i, numbers.get(n));
						n++;
					}
				}
			}
//			//赤い丸(Actor)
//			Image image = new BufferedImage(Game.TILESIZE, Game.TILESIZE, BufferedImage.TYPE_INT_ARGB);
//			Graphics graphics = image.getGraphics();
//			graphics.setColor(Color.RED);
//			graphics.fillOval(2, 2, 28, 28);
//			new Actor(image, 2, 3, true, true);
//			
//			//緑の四角形(Character)
//			image = new BufferedImage(Game.TILESIZE, Game.TILESIZE, BufferedImage.TYPE_INT_ARGB);
//			graphics = image.getGraphics();
//			graphics.setColor(Color.GREEN);
//			graphics.fillRect(4, 4, 25, 25);
//			new Character(image, 3, 5, 1000, true, false);
//			
			//プレイヤーキャラ
			image = new BufferedImage(Game.TILESIZE, Game.TILESIZE, BufferedImage.TYPE_INT_ARGB);
			graphics = image.getGraphics();
			graphics.setColor(Color.RED);
			graphics.drawRect(2, 2, 47, 47);
			this.PlayerCharacter = new Actor(image, 3, 3, 0);
			
		}
		Stage.stage.repaint();
		
		synchronized(this) {
			while(!this.gameIsOver()) {
				try {
					this.wait();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		//System.out.println("gameIsOver");
		String endTime = getTime(System.currentTimeMillis() - startTime);
		Font font = new Font("Century", Font.BOLD, 20);
		JLabel l = new JLabel("Game Set!");
		l.setFont(font);
		JLabel l2 = new JLabel("SCORE TIME : " + endTime);
		l2.setFont(font);
		JFrame f = new JFrame();
		f.add(l,BorderLayout.NORTH);
		f.add(l2,BorderLayout.SOUTH);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		Stage.stage.frame.removeKeyListener(this);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("pushed");
		int x = 0; int y = 0;
		switch(arg0.getKeyCode()) {
		case KeyEvent.VK_SHIFT:
			cheat = !cheat;
			break;
		case KeyEvent.VK_UP:
			y--;
			break;
		case KeyEvent.VK_DOWN:
			y++;
			break;
		case KeyEvent.VK_RIGHT:
			x++;
			break;
		case KeyEvent.VK_LEFT:
			x--;
			break;
		case KeyEvent.VK_SPACE:
			if(onActor == null) break;
//			System.out.println("onActor.posX = " + onActor.posX );
//			System.out.println("onActor.posY = " + onActor.posY );
//			System.out.println("PlayerCharacter.posX = " + PlayerCharacter.posX);
//			System.out.println("PlayerCharacter.posY = " + PlayerCharacter.posY);
			if(checkposition()) {
				System.out.println("done");
				int boxX = onActor.posX;
				int boxY = onActor.posY;
				onActor.posX = spaceX;
				onActor.posY = spaceY;
				spaceX = boxX;
				spaceY = boxY;
			}
		}
		
		if(!Actor.tileOut(PlayerCharacter.posX + x, PlayerCharacter.posY + y)) {
//			if(Actor.frameOut(PlayerCharacter.posX + x, PlayerCharacter.posY + y)) {
//				Stage.baseX -= x;
//				Stage.baseY -= y;
//			}
			onActor = Actor.existsAt(PlayerCharacter.posX + x, PlayerCharacter.posY + y);
			PlayerCharacter.posX += x;
			PlayerCharacter.posY += y;
		}
 
//		else if(a.isEdible){
//			a.eaten();
//			PlayerCharacter.posX += x;
//			PlayerCharacter.posY += y;
//		} else {
//			a.pushed(x, y);
//		}
		synchronized(this) {
			this.notify();
		}
		Stage.stage.repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean gameIsOver() {
//		if(PlayerCharacter.posX == endPosX && PlayerCharacter.posY == endPosY) {
//			System.out.println("true");
//			return true;
//		}
		synchronized(Actor.actors) {
			for(Actor actor : Actor.actors) {
				if(actor.number == 0) break;
				if(!(numpos.table[actor.number].x == actor.posX && numpos.table[actor.number].y == actor.posY)) {
					return false;
				}
			}
			return true;
		}
	}
	
	public boolean checkposition() {
		boolean X = (onActor.posX - 1 == spaceX || onActor.posX + 1 == spaceX);
		boolean Y = (onActor.posY - 1 == spaceY || onActor.posY + 1 == spaceY);
		boolean D = (Math.abs(onActor.posX - spaceX) <= 1 && Math.abs(onActor.posY - spaceY) <= 1);
		if(cheat)return true;
		return ( (X ^ Y) && D);
	}
	
	public String getTime(long time) {
		String t = "", Smilli, Ssec, Smin, Shour;
		
		Smilli = Long.toString(time % 1000);
		if(Long.parseLong(Smilli) < 10) Smilli = "00" + Smilli;
		else if (Long.parseLong(Smilli) < 100) Smilli = "0" + Smilli;
		
		Ssec = Long.toString(time / 1000 % 60);
		if(Long.parseLong(Ssec) < 10) Ssec = "0" + Ssec;
		
		Smin = Long.toString(time / 1000 / 60 % 60);
		if(Long.parseLong(Smin) < 10) Smin = "0" + Smin;
		
		Shour = Long.toString(time / 1000 / 3600);
		if(Long.parseLong(Shour) < 10) Shour = "0" + Shour;
		
		t = Shour + ":" + Smin + ":" + Ssec;
		return t;
	}
	
}

class numpos {
	protected int x;
	protected int y;
	static numpos[] table = new numpos[16]; 
	numpos() {
		x = 0;
		y = 0;
	}
	static void set() {
		table[0] = null; //使わない
		for(int i = 1; i < table.length; i++) table[i] = new numpos();
		int n = 1;
		for(int yy = 0; yy < 4; yy++) {
			for(int xx = 0; xx < 4; xx++) {
				if(n != 16) {
					System.out.println(n);
					table[n].x = xx; table[n].y = yy;
					n++;
				}
			}
		}
	}
}

